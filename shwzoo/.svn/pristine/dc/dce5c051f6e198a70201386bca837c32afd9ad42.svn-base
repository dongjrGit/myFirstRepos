//品牌信息
$(document).ready(function() {
	var s = $("#shopID").val();
	// Brand.bind(s, 1);
	$("body").on("click",".lvse", function() {
		var id = $(this).parent().find("input").val();
		ConfirmShow("确认要删除品牌吗?", del, id);// confirm弹出框
		// js/dialogShow.js
		// del(id);
	})
	// 添加跳转
	$("#addBrand").click(function() {
		location.href = "/seller/shop/showAddBrand?id=" + s + "&shopid=" + s;
	});
	// 清空搜索条件
	$("#clearSearch").click(function() {
		$("#brandName").prop("value", "");
	})
	// 搜索
	$("#searchBrand").click(function() {
		var name = $("#brandName").val();
		Brand.bind(s, 1, name);
	})
})

// 品牌列表 分页数据绑定
var psize = 10;
var sID;
var pcount = 0;
var pindex = 0;
var Brand = {
	// sid:店铺id，index：起始页，na：品牌名称
	bind : function(sid, index, na) {
		var name = $("#brandName").val();
		$.ajax({
			type : "post",
			url : "/seller/shop/queryBrandByCriteria",
			dataType : "json",
			data : {
				shopid : sid,
				page : index,
				size : psize,
				brandname : na
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('brandlist', listdata);

					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#datalist").parent().children().each(function() {
						if ($(this).attr('id') != "datalist") {
							this.parentNode.removeChild(this);
						}
					})
					$("#datalist").after(html);

					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					sID = sid;
					if (na != undefined) {
						name = na;
					}
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
	var name = $("#brandName").val();
	Brand.bind(sID, index, name);
}

// function edit() {

// var commentID = $(this).parent().find("#hidden_commentid").val();
// $(".a_commentdetail").attr('href', "GoodComment_Detail?id=" + commentID);
// };

// 删除 bid：品牌id
function del(bid) {
	$.ajax({
		type : "post",
		url : "/seller/shop/delete",
		dataType : "json",
		data : {
			id : bid
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", refresh);
				// window.location.reload();
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {
			// Dalert(es.statusText);
		}
	});

};
// 刷新
function refresh() {
	location.reload();

}