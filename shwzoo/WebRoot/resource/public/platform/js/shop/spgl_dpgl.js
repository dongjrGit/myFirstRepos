//店铺关联品牌
var pcount, pindex, psize = 10;
var Shop = {
	bind : function() {
		autoxl.bind("select_user", Shop.getAllUserStartwithName); // 用户下拉框绑定
		autoxl.bind("select_shopName", Shop.getShopsStartwithName); // 店铺下拉框绑定
		$("#select_input").bind("click", function() {
			Shop.getShopList(1, Shop.unit);
		});
	},
	// 获取店铺列表
	getShopList : function(index, callback) {
		var userid = $("#select_user").attr("data");
		var name = $("#select_shopName").val();
		var num = $("#select_shopNum").val();
		var shoptype = $("#shoptype").val();
		var status = -1;
		$.ajax({
			url : "/platform/shop/queryByCriteria",
			type : "Post",
			data : {
				"page" : index,
				"size" : psize,
				"userid" : userid,
				"status" : status,
				"name" : name,
				"shopnum" : num,
				"shoptype" : shoptype
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('slist', listdata);
					$("#list_title tbody").html(html);
					pcount = data.maxRow;
					pindex = data.pageIndex;
					// 分页
					$("#pager").html(
							pagination(pcount, pindex, psize, "pagelist"));
					if (callback)
						callback();
				}
			}
		});
	},
	unit : function() {
		$("a[name='changestate']").each(function() {
			$(this).bind("click", function() {
				var obj = $(this);
				var id = $(this).attr("data-id");
				var status = $(this).attr("data-state");
				var value = id + '|' + status;
				ConfirmShow("确定要执行此操作吗？", changstatus, obj);
			})
		});
	},
	/*
	 * callback 成功 有数据时 调的方法 event 事件
	 */
	getAllUserStartwithName : function(callback, event) {
		var username = $("#select_user").val();
		if (event)
			username += String.fromCharCode(event.keyCode);
		$.ajax({
			url : "/platform/shop/queryUserByLikeName",
			type : "Post",
			data : {
				"name" : username,
				"usertype" : 5
			},
			dataType : "json",
			success : function(data) {

				if (data.code == 0) {
					var listdata = {
						list : data.data
					}
					var html = template('select_userlist', listdata);

					if (callback) {
						callback(html);
					}
				} else {
					Dalert(data.data);
				}
			}
		});
	},
	/*
	 * callback 成功 有数据时 调的方法 event 事件
	 */
	getShopsStartwithName : function(callback, event) {
		var name = $("#select_shopName").val();
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
	},
	del : function(id) {
		if (confirm("确定要删除店铺及店铺内的所有商品吗？")) {
			$.ajax({
				url : "/platform/shop/deleteShop",
				type : "Post",
				data : {
					"shopid" : id
				},
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						Dalert(data.desc, 1000);
						Shop.getShopList(pindex, Shop.unit);
					} else {
						Dalert(data.desc);
					}
				}
			})
		}
	}
}
// 分页回调函数
function pagelist(index) {
	Shop.getShopList(index, Shop.unit);
}
// 页面刷新
function refresh() {
	location.reload();
}
// 店铺状态变更方法
function changstatus(obj) {
	var id = obj.attr("data-id");
	var status = obj.attr("data-state");
	$.ajax({
		url : "/platform/shop/changeShopStatus",
		type : "Post",
		data : {
			"id" : id,
			"status" : status
		},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {
				Dalert(data.desc);
			} else {
				Dalert(data.desc, "", refreshrow(obj));
			}

			// location.reload();
		}
	});
}
// 刷新行店铺文本
function refreshrow(obj) {
	var status = parseInt(obj.attr("data-state"));
	if (status == 4) {
		obj.attr("data-state", "5");
		$(obj).find(".span_status").text("关闭");
		$(obj).parent().parent().find("#td_status").html(
				'<span class="lvs">营业中</span>');
	} else if (status == 5) {
		obj.attr("data-state", "4");
		$(obj).find(".span_status").text("开张");
		$(obj).parent().parent().find("#td_status").html(
				'<span class="red">打烊</span>');
	} else {

	}
}