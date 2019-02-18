$(function() {
	
	// 获取列表
	ShopViolationList.getShopViolationList(1);
	// 查询
	$("input[name=select_button]").click(function() {
		ShopViolationList.getShopViolationList(1);
	});

});
//店铺违规列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var ShopViolationList = {
	bind : function() {
		// 店铺下拉数据绑定
		autoxl
				.bind("select_shop", ShopViolationList.getShopStartwithName,
						true);
	},
	getShopViolationList : function(index) {
		var type = $("#select_type").val();
		var sid = $("#select_shop").attr("data");
		$
				.ajax(({
					type : "post",
					url : "/platform/shop/queryHistoryVoilationShopByCriteria",
					dataType : "json",
					data : {
						"page" : index,
						"size" : psize,
						"type" : type,
						"shopid" : sid,
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							var listdata = {
								list : rsl.data
							}
							var html = template('shopviolationlist', listdata);

							// 翻页时删除表头以外的所有节点，避免after()方法重复加载
							$("#shopviolationlist_title")
									.parent()
									.children()
									.each(
											function() {
												if ($(this).attr('id') != "shopviolationlist_title") {
													this.parentNode
															.removeChild(this);
												}
											})
							$("#shopviolationlist_title").after(html);

							indexlog = index;
							pcount = rsl.maxRow;
							pindex = rsl.pageIndex;
							// Dalert(pindex);
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"shopviolation_pagelist"));
						} else {
							Dalert(rsl.desc);
						}
					},
					error : function(e) {

					}
				}));
	},
	getShopStartwithName : function(callback, event) {
		var name = $("#select_shop").val();
		if (event)
			name += String.fromCharCode(event.keyCode);
		$.ajax({
			url : "/platform/shop/queryShopByLikeName",
			type : "Post",
			data : {
				"name" : name
			},
			dataType : "json",
			success : function(data) {

				if (data.code == 0) {
					var listdata = {
						list : data.data
					}
					var html = template('select_shoplist', listdata);

					if (callback) {
						callback(html);
					}
				} else {
					Dalert(data.data);
				}
			}
		});
	}
}
// 分页回调
function shopviolation_pagelist(index) {
	if (indexlog != index) {
		ShopViolationList.getShopViolationList(index);
	}
}