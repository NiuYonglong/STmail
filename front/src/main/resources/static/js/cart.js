/**
 * 购物车的操作
 */

// 统一调用方法
getCarts();

//1. ajax请求获取所有的购物车信息, 
function getCarts(){
	$.ajax({
		url: '/api/cart/getAll',
		type: 'get',
		data: {},
		dataType: 'json',
		success: function(data){
			var html = "";
			
			$.each(data, function(index, item){
				html += '<div class="cart-goods clear">\
									<div class="col col-check">\
								'+((item.count != 0)?'<input type="checkbox"  name="cartId" class="all" id="cart-'+item.id+'" value="'+item.id+'"/>\
								<label for="cart-'+item.id+'"></label>' : '')+'  \
							</div>\
							<div class="col col-img">\
								<a href=""><img src="upload/'+item.goods.pictures[0].name+'" /></a>\
							</div>\
							<div class="col col-name">\
								<a href="/goods?id='+item.goods.id+'">'
								+item.goods.name+' '+item.goods.version+' '+item.goods.color+'</a>\
							</div>\
							<div class="col col-price">\
								<span>'+item.goods.price+'</span>元\
							</div>\
							<div class="col col-count">\
								<div class="count-bar">\
									<a href="javascript:;" class="sub">-</a>\
									<span class="count">'+item.count+'</span>\
									<a href="javascript:;" class="add">+</a>\
								</div>\
							</div>\
							<div class="col col-total">\
								'+item.goods.price*item.count+'元\
							</div>\
							<div class="col col-action">\
								<a href="javascript:;" data-id="'+item.id+'"><i class="mi-icon icon-close"></i></a>\
							</div>\
						</div>';
			});
			
			$(".cart-list").html(html);
			
			// 设置商品的总数
			$(".all-count").text(data.length);
		},
		error: function(){
			
		}
	});
}

// 绑定+-操作  预绑定
$(".cart-list").on("click", ".sub", function(){
	var count = $(this).parent().find(".count").text();
	count = parseInt(count) - 1;
	count = count<1 ? 1 : count;
	
	// 更新服务器里的操作
	var id = $(this).parents(".cart-goods").find("input").val();
	updateCart(id, count);
});

$(".cart-list").on("click", ".add", function(){
	var count = $(this).parent().find(".count").text();
	count = parseInt(count) + 1;
	count = count<1 ? 1 : count;
	
	// 更新服务器里的操作
	var id = $(this).parents(".cart-goods").find("input").val();
	updateCart(id, count);
});


// 更新服务器里的操作
function updateCart(id, count){
	$.ajax({
		url: '/api/cart/update',
		type: 'get',
		data: {id: id, count: count},
		dataType: 'json',
		success: function(data){
			// 无论成功与失败, 都给我返回一个当前购物车真实的数量
			// 将数量重新写到页面中
			if (data.status != 1) {
				// 显示错误信息
				alert(data.msg)
			}
			
			// 重写数量
			$("#cart-"+data.data.id)
				.parents(".cart-goods")
				.find(".count").text(data.data.count)
				
			// 重写小计
			var price = $("#cart-"+data.data.id)
				.parents(".cart-goods")
				.find(".col-price span").text();
			price = parseFloat(price);
			
			// 计算小计
			$("#cart-"+data.data.id)
				.parents(".cart-goods")
				.find(".col-total").text(price*data.data.count+"元");
			
			// 重新计算
			reTotal();
		},
		error: function(){
			
		}
	});
}

/*
 * 监听全选按钮的点击事件
*/
$("#all").change(function(){
	// 获取当前选中状态
	// 选中下面的复选框
	$(".cart-goods input[type='checkbox']").prop("checked", $(this).prop("checked"));
	
	// 重新计算
	reTotal();
});

// 给购物车的复选框预绑定事件
$(".cart-list").on("change", "input[type='checkbox']", function(){
	// 
	var ch = $(".cart-goods input[type='checkbox']").not("input:checked");

	$("#all").prop("checked", ch.length==0);
	
	// 重新计算
	reTotal();
});
// 重新计算数量和价格
function reTotal() {
	// 获取所有被选中的checkBox
	var ch = $(".cart-goods input[type='checkbox']:checked");
	
	// 统计 1. 数量
	$(".check-count").text(ch.length);
	
	// 遍历所有的行, 统计总数量
	var sum = 0;
	ch.each(function(index, elem){
		var price = $(this).parents(".cart-goods").find(".col-price span").text();
		var count = $(this).parents(".cart-goods").find(".count").text();
		sum += parseFloat(price) * parseInt(count);
	});
	
	// 显示
	$(".cart-bar .right span").text(sum);
}


// 绑定表单提交
$("#cart-form").submit(function(){
	// 获取所有被选中的checkBox
	var ch = $(".cart-goods input[type='checkbox']:checked");
	
	if (ch.length == 0) {
		alert("未选中任何商品, 无法结算")
		return false;
	} else {
		return true;
	}
});








