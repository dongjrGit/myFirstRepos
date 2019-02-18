//店铺管理品牌

var Shop = {
	bind : function() {
		// $(".delete").each(function () {
		// $(this).bind("click", Shop.del);
		// })
		// $(".check").each(function () {
		// $(this).bind("click", Shop.check);
		// })
		Shop.getlist(1, Shop.unit);
	},
	// 删除
	del : function() {
		var id = $(this).attr("data");
		if (confirm("确定要删除该条数据么？")) {
			var id = $(this).attr("data");
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
			})
		}
	},
	// 审核
	check : function() {
		var id = $(this).attr("data");
		var check = $(this).attr("val");
		$.ajax({
			url : "/platform/shopbrand/checkStatus",
			type : "Post",
			data : {
				"id" : id,
				"status" : check
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert(data.desc, "", refresh);
				} else {
					Dalert(data.desc);
				}
			}
		})
	},
	// 获取品牌列表
	getlist : function(index, callback) {
		var pcount, pindex, psize = 10;
		$.ajax({
			url : "/platform/shopbrand/queryBrandCriteriaByShopid",
			type : "Post",
			data : {
				"page" : index,
				"size" : psize,
				"shopid" : $("#sid").val()
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {

						list : data.data
					}
					var html = template('sblist', listdata);

					// html 填充
					$("#list_title tbody").html(html);
					// 加载table样式：改变偶数行背景色，鼠标移动时背景色
					init();
					if (callback)
						callback();
					pcount = data.maxRow;
					pindex = data.pageIndex;
					// 分页
					$("#pager").html(
							pagination(pcount, pindex, psize, "Shop.getlist"));
				}
			},
			error : function() {
			}
		});
	},
	unit : function() {
		$(".delete").each(function() {
			$(this).bind("click", Shop.del);
		})
		$(".check").each(function() {
			$(this).bind("click", Shop.check);
		})
	}
}
// 页面刷新
function refresh() {
	location.reload();
}