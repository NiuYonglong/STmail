/**
 * 订单列表 的js操作
 */

//统一调用
getOrderList();

/*
 * 获取所有的订单信息
 */
function getOrderList(){
	// ajax
	$.ajax({
		url: '/api/order/getList',
		type: 'post',
		data: {},
		dataType: 'json',
		success: function(data){
			var html = "";
			$.each(data, function(index, item){
				
				// 遍历当前订单下的详情, 统计总金额
				var sum = 0;
				$.each(item.orderDetails, function(i, detail){
					sum += detail.price*detail.count;
				});
				
				html += '<div class="order-box '+(item.status<1 ? 'order-pay': '')+'">\
							<div class="order-header">\
								<div class="status '+(item.status<1 ? 'status-pay': '')+'">'
								+ ['等待付款', '等待发货', '运送中', '已收货', '退货中', '已退货'][item.status] +
								'</div>\
							</div>\
							\
							<table class="detail">\
								<thead>\
									<tr>\
										<th class="info">'+item.time+' | '+(item.address!=null?item.address.contact:'')+' | 订单号：'+item.id+'| 在线支付（支付宝快捷支付）</th>\
										<th class="money">应付金额： <span>'+sum+'</span> 元</th>\
									</tr>\
								</thead>\
								<tbody>'
						$.each(item.orderDetails, function(i, detail){
							html += '<tr>\
										<td class="goods">\
											<div class="goods-box">\
												<div class="img">\
													<a href=""><img src="upload/'+detail.goods.pictures[0].name+'" ></a>\
												</div>\
												<div class="goods-info">\
													<p class="name"><a href="/goods?id='+detail.goods.id+'">'+detail.goods.name+' '+detail.goods.version+' '+detail.goods.color+'</a></p>\
													<p class="price">'+detail.price+'元 x '+detail.count+'</p>\
												</div>\
											</div>\
										</td>\
										<td class="actions">'+
											( item.status == 0 ? '<a class="active" href="/user/order/pay?orderId='+item.id+'">立即付款</a>' : '') +
											'<a href="">订单详情</a>\
											<a href="">联系客服</a>\
										</td>\
									</tr>'
						});
				
						html +=	'</tbody>\
							</table>\
						</div>';
			});
			
			$('.order-list').html(html);
		},
		error: function(){
			
		}
	});
}
