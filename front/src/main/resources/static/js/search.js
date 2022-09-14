/**
 * 搜索页面的js
 */

var allSorts;

// 搜索商品
function searchGoods(name, categoryId){

	$.ajax({
		url: '/api/goods/search',
		type: 'get',
		data: {name: name, categoryId: categoryId},
		dataType: 'json',
		success: function(result){
			var html = "";
			$.each(result.data, function(index, item){
				if ((index+1)%4==0) {
					html += '<li class="r">';
				} else {
					html += '<li>';
				}
				
				html += '<a href="/goods?id='+item.id+'">\
							<div class="img">\
								<img src="/upload/'+( (item.pics[0]) ? item.pics[0].url : 'default.png' ) +'" >\
							</div>\
							<h2 class="desc">'+item.name+'</h2>\
							<p class="price">'+item.price+'元 起</p>\
							<div class="thumbs">\
								<ul class="clear">';
				
				$.each(item.pics, function(i, pic){
					html += '<li><img src="/upload/'+pic.url+'" ></li>'
				});
									
				html += '		</ul>\
							</div>\
							<div class="flags">\
								<span>赠</span>\
								<span>加价购</span>\
							</div>\
						</a>\
					</li>';
			});
			
			$(".goods>ul").html(html);
		},
		error: function(){
			
		}
	});
}

/**
 * 获取一级分类
 */
function getFirstSort(){
	$.ajax({
		url: '/api/category/getParent',
		type: 'get',
		data: {},
		dataType: 'json',
		success: function(result){
			// 保存所有分类信息
			allSorts = result.data;
			
			var html = '<span class="label">一级分类:</span>';
			
			if (categoryId == null){ // 没有传分类，显示全部
				html += '<li class="active"><a href="javascript:;" data-sort="0">全部</a></li>';
			} else {
				html += '<li><a href="javascript:;" data-sort="0">全部</a></li>';
			}
				
			
			$.each(allSorts, function(index, item){
				if (item.id == getFirstSortId(allSorts, categoryId)){
					html += '<li class="active"><a href="javascript:;" data-sort="'+item.id+'">'+item.name+'</a></li>';
				} else {
					html += '<li><a href="javascript:;" data-sort="'+item.id+'">'+item.name+'</a></li>';
				}
				
			});
			$(".first-sort").html(html);
			
			// 生成二级分类
			if (categoryId != null){
				// 调用方法生成代码
				initSecondSort(allSorts, getFirstSortId(allSorts, categoryId));
			} else {
				$(".second-sort").hide();
			}
		},
		error: function(){
			
		}
	});
}

function initSecondSort(data, firstSortId){
	// 遍历查找当前的一级分类
	for (var i=0; i<data.length; i++){
		if (data[i].id == firstSortId){
			// 遍历 data[i] 里面的二级分类 sonSorts
			var html = '<span class="label">二级分类:</span>';
			
			if (categoryId == getFirstSortId(data, firstSortId)){
				html += '<li class="active"><a href="javascript:;" data-sort="0">全部</a></li>';
			} else {
				html += '<li><a href="javascript:;" data-sort="0">全部</a></li>';
			}
			
			$.each(data[i].children, function(index, item){
				
				if (item.id == categoryId){
					html += '<li class="active"><a href="javascript:;" data-sort="'+item.id+'">'+item.name+'</a></li>';
				} else {
					html += '<li><a href="javascript:;"  data-sort="'+item.id+'">'+item.name+'</a></li>';
				}
			});
			
			$(".second-sort").html(html);
			$(".second-sort").show();
			
			break;
		}
	}
}

function getFirstSortId(data, secondSortId){
	
	// 遍历一级分类
	for(var i=0; i<data.length; i++){
		
		if (data[i].children == null) continue;

		// 判断一级分类
		if (data[i].id == secondSortId){
			return secondSortId;
		}
		
		// 遍历二级分类
		for (var j=0; j<data[i].children.length; j++){
			
			if (data[i].children[j].id == secondSortId){
				return data[i].id;
			}
		}
	}
	
	return 0;
}

/**
 * 给一级分类绑定事件, 预绑定
 */
$(".first-sort").on("click", "a", function(){
	$(".first-sort li").removeClass("active");
	$(this).parent().addClass("active");
	var firstSortId = $(this).attr("data-sort");

	categoryId = firstSortId;

	// 切换二级分类
	if (firstSortId != 0){
		initSecondSort(allSorts, firstSortId);
	} else {
		$(".second-sort").hide();
	}
	
	// 重新刷新数据
	searchGoods(name, categoryId);
});

$(".second-sort").on("click", "a", function(){
	$(".second-sort li").removeClass("active");
	$(this).parent().addClass("active");
	categoryId = $(this).attr("data-sort");
	var firstSortId = $(".first-sort .active a").attr("data-sort");

	if (categoryId == 0) categoryId = firstSortId;
	
	// 重新刷新数据
	searchGoods(name, categoryId);
});


