//店铺审核类型
var checkStatus;
// 店铺审核列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var ShopCheckList = {
	bind : function() {
		// 绑定店铺下拉数据
		autoxl.bind("select_shop", ShopCheckList.getShopStartwithName, true);
	},
	getShopCheckList : function(index) {
		checkStatus = GetQueryStringByName("checkStatus");
		var companyname = $("#select_companyname").val();
		var sid = $("#select_shop").attr("data");
		$
				.ajax(({
					type : "post",
					url : "/platform/shop/queryAuthenticationByCriteria",
					dataType : "json",
					data : {
						status : checkStatus,
						page : index,
						size : psize,
						"companyname" : companyname,
						"shopid" : sid
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							var listdata = {
								list : rsl.data
							}
							var html = template('shopchecklist', listdata);

							// 翻页时删除表头以外的所有节点，避免after()方法重复加载
							$("#shopchecklist_title")
									.parent()
									.children()
									.each(
											function() {
												if ($(this).attr('id') != "shopchecklist_title") {
													this.parentNode
															.removeChild(this);
												}
											})
							$("#shopchecklist_title").after(html);

							indexlog = index;
							pcount = rsl.maxRow;
							pindex = rsl.pageIndex;
							// Dalert(pindex);
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"shopcheck_pagelist"));

							// 店铺审核明细
							$(".a_shopcheckdetail").bind("click",
									shopcheckdetail);
							// 删除店铺审核
							$(".a_shopcheckdelete").bind(
									"click",
									function() {
										var shopID = $(this).parent().find(
												"#hidden_shopid").val();
										ConfirmShow("确定要删除吗？", shopcheckdel,
												shopID);
									});

						} else {
							Dalert(rsl.desc);
						}
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
function shopcheck_pagelist(index) {
	if (indexlog != index) {
		ShopCheckList.getShopCheckList(index);
	}
}

// 店铺审核明细
function shopcheckdetail() {
	var fatherUrl = encodeURIComponent(window.location.href);
	var shopID = $(this).parent().find("#hidden_shopid").val();
	$(".a_shopcheckdetail").attr(
			'href',
			"/platform/shop/showShopDetail?shopid=" + shopID + "&fatherUrl="
					+ fatherUrl);
}

// 删除店铺审核
function shopcheckdel(shopID) {
	$.ajax(({
		type : "post",
		url : "/platform/shop/deleteCheck",
		dataType : "json",
		data : {
			shopid : shopID,
			status : 6
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				ShopCheckList.getShopCheckList(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {

		}
	}));
}