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
		// 下拉店铺数据绑定
		autoxl
				.bind("select_shop", ShopViolationList.getShopStartwithName,
						true);
	},
	getShopViolationList : function(index) {
		var violationStatus = GetQueryStringByName("violationStatus"); // 得到违规的状态
		// 为
		// 3
		var type = $("#select_type").val();
		var sid = $("#select_shop").attr("data");
		$
				.ajax(({
					type : "Get",
					url : "/platform/shop/queryVoilationShopByCriteria",
					data : {
						"page" : index,
						"size" : psize,
						"type" : type,
						"shopid" : sid
					},
					dataType : "json",
					success : function(data) {
						
						if (data.code == 0) {
							var listdata = {
								list : data.data
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
							pcount = data.maxRow;
							pindex = data.pageIndex;
							// Dalert(pindex);
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"shopviolation_pagelist"));

							// 解除店铺违规
							$(".a_shopviolationremove").bind(
									"click",
									function() {
										var violationID = $(this).parent()
												.find("#hidden_violationid")
												.val();
										var shopID = $(this).parent().find(
												"#hidden_shopid").val();
										var value = violationID + '|' + shopID;
										ConfirmShow("确定要解除违规吗？",
												shopviolationremove, value);
									});
							// 删除店铺违规
							$(".a_shopviolationdelete").bind(
									"click",
									function() {
										var violationID = $(this).parent()
												.find("#hidden_violationid")
												.val();
										var shopID = $(this).parent().find(
										"#hidden_shopid").val();
										var value = violationID + '|' + shopID;
										ConfirmShow("确定要删除吗？",
												shopviolationdel, value);
									});
						} else {
							Dalert(data.desc);
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

// 店铺违规解除
function shopviolationremove(value) {
	var val = value;
	var violationID = val.split('|')[0];
	var shopID = val.split('|')[1];

	$.ajax(({
		type : "post",
		url : "/platform/shop/removeVoilationShop",
		dataType : "json",
		data : {
			"violationId" : violationID,
			"shopid" : shopID
		},
		success : function(data) {
			if (data.code == 0) {
				ShopViolationList.getShopViolationList(indexlog);
			} else {
				Dalert(data.desc);
			}
		},
		error : function(es) {

		}
	}));
}

// 删除店铺违规
function shopviolationdel(value) {
	var val = value;
	var violationID = val.split('|')[0];
	var shopID = val.split('|')[1];
	$.ajax(({
		type : "post",
		url : "/platform/shop/delVoilationShop",
		dataType : "json",
		data : {
			violationId : violationID,
			shopid : shopID
		},
		success : function(data) {
			if (data.code == 0) {
				ShopViolationList.getShopViolationList(indexlog);
			} else {
				Dalert(data.desc);
			}
		},
		error : function(es) {

		}
	}));
}