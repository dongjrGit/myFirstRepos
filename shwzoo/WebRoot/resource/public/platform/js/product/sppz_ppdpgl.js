//店铺品牌关联
var psize = size_product;
var ShopBrand = {
	bind : function() {
		ShopBrand.unit();
		ShopBrand.brandChange();
		$("#select_brand").change(ShopBrand.brandChange);
		$("#savenew").click(ShopBrand.addNew);
		$("#addnew").click(ShopBrand.showNewDiv);
		$("#hidenew").bind("click", function() {
			$("#addDiv").hide();
		})
		// 设置品牌旗舰店 sid:店铺id
		$("body").on("click",".set", function() {
			sid = $(this).attr("data");
			setBrand(sid);
		});
	},

	brandChange : function() {
		var bid = GetQueryStringByName("id");
		var sid = $("#select_brand option:selected").attr("data");
		$.ajax({
			url : "/platform/shopbrand/queryCriteria",
			type : "Post",
			data : {
				"page" : 1,
				"size" : psize,
				"brandid" : bid,
				"shopid" : sid
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('shopbrandlist', listdata);
					$("#list_title").html(html);
					// $("#newbid").val(bid);
				}
			}
		});
	},
	unit : function() {
		var selectID = $("#selectid").val();
		$("#select_brand option").each(function() {
			if ($(this).val() == selectID) {
				$(this).attr("selected", "selected");
			}
		})
	},
	del : function(id) {
		if (confirm("确定要删除吗？")) {
			$.ajax({
				url : "/platform/shopbrand/delete",
				type : "Post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						Dalert(data.desc, "", refresh);
					} else {
						Dalert(data.desc);
					}
				}
			});
		}
	},
	addNew : function() {
		var bid = $("#select_brand").val();
		var sid = $("#newsid").attr("data");
		$.ajax({
			url : "/platform/shopbrand/save",
			type : "Post",
			data : {
				"brandid" : bid,
				"shopid" : sid
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert(data.desc, "", refresh);
				} else {
					Dalert(data.desc);
				}
			}
		});
	},
	showNewDiv : function() {
		$("#addDiv").show();
		// autoxl.bind("newbid", ShopBrand.getBrandsStartwithName, 112);
		autoxl.bind("newsid", ShopBrand.getShopsStartwithName, 112);
	},
	// getBrandsStartwithName: function (callback, event) {
	// var name = $("#newbid").val();
	// if (event)
	// name += String.fromCharCode(event.keyCode);
	// $.ajax({
	// url: "/Brand/GetBrandListStartwithName",
	// type: "Post",
	// data: { "name": name },
	// dataType: "json",
	// success: function (data) {

	// if (data.Code == 0) {
	// var listdata = {
	// list: data.Data
	// }
	// var html = template('select_brandlist', listdata);
	// if (callback) {
	// callback(html);
	// }
	// } else {
	// Dalert(data.Data);
	// }
	// }
	// });
	// },
	getShopsStartwithName : function(callback, event) {
		var name = $("#newsid").val();
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
function refresh() {
	location.reload();
}
// 设置品牌旗舰店 sid:店铺id
function setBrand(sid) {
	var bid = $("#select_brand option:selected").val();
	$.ajax({
		url : "/platform/shop/setFlag",
		type : "Post",
		data : {
			"brandid" : bid,
			"shopid" : sid
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				Dalert(data.desc, "", refresh);
			} else {
				Dalert(data.desc);
			}
		}
	});
}