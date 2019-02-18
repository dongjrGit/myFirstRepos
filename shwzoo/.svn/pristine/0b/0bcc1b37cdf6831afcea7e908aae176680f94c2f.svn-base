//店铺公告 author:rw
$(document).ready(
		function() {
			var s = $("#shopID").val();
			// alert(s);
			// Notics.bind(s);
			$("body").on("click",".edit_notice",function() {
						var id = $(this).find("input").val();
						var u = $("#editUser").val();
						location.href = "showShopNoticeEdit?id=" + id
								+ "&cur=ssc&dcur=dsnc" + "&shopid=" + s
								+ "&editUser=" + u;
					})
			// 删除公告
			$("body").on("click",".del_notice", function() {
				var id = $(this).find("input").val();
				ConfirmShow("确认要删除公告吗？", del, id);// confirm弹出框
				// js/dialogShow.js
			})
			// 添加公告
			$("#addNotice").click(
					function() {
						var u = $("#editUser").val();
						// alert(u);
						location.href = "showShopNoticeEdit?shopid=" + s
								+ "&editUser=" + u + "&cur=ssc&dcur=dsnc";
					})
			// 清空搜索
			$("#clearSearch").click(function() {
				$("#notTitle").val("");

			})
			// 搜索
			$("#searNotice").click(function() {
				var nt = $("#notTitle").val();
				Notics.bind(s, 1, nt);
			})

		})

// 店铺公告列表 分页数据绑定
var psize = 10;
var sID;
var title = ""
var Notics = {
	// id：店铺id，index：起始页，tl：公告标题
	bind : function(id, index, tl) {
		$.ajax({
			type : "post",
			url : "/seller/shop/queryShopNoticesByCriteria",
			dataType : "json",
			data : {
				shopid : id,
				page : index,
				size : psize,
				title : tl
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('noticelist', listdata);

					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#datalist").parent().children().each(function() {
						if ($(this).attr('id') != "datalist") {
							this.parentNode.removeChild(this);
						}
					})
					$("#datalist").after(html);

					title = tl;
					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					sID = id;
					$("#pager").html(
							pagination(pcount, pindex, psize, "pagelist"));

				} else {
					// Dalert(rsl.Desc);
				}
			},
			error : function(e) {
				// Dalert(e.statusText);
			}
		});
	}
}
// 分页回调
function pagelist(index) {
	Notics.bind(sID, index, title);
}

// 删除 did:公告id
function del(did) {

	$.ajax({
		type : "post",
		url : "/seller/shop/deleteNotice ",
		dataType : "json",
		data : {
			id : did
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", refresh);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {
			// Dalert(es.statusText);
		}
	});
};
// 页面刷新
function refresh() {
	location.reload();

}
