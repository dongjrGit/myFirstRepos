//初始化
$(document).ready(function() {
	$("#searchMem").click(function() {
		var u = $("#uName").val();
		MemberBrowserList.bind(1, u)
	})
});
// 会员最新浏览历史列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var uName = "";
var MemberBrowserList = {
	bind : function(index, uname) {
		$
				.ajax(({
					type : "post",
					url : "/platform/memberbrowserhistory/queryBrowsehistory",
					dataType : "json",
					data : {
						pageIndex : index,
						pageSize : psize,
						username : uname
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							var listdata = {
								list : rsl.data
							}
							var html = template('memberbrowserlist', listdata);

							// 翻页时删除表头以外的所有节点，避免after()方法重复加载
							$("#memberbrowserlist_title")
									.parent()
									.children()
									.each(
											function() {
												if ($(this).attr('id') != "memberbrowserlist_title") {
													this.parentNode
															.removeChild(this);
												}
											})
							$("#memberbrowserlist_title").after(html);

							indexlog = index;
							pcount = rsl.maxRow;
							pindex = rsl.pageIndex;
							uName = uname;
							// alert(pindex);
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"memberbrowser_pagelist"));

							// 浏览详细列表
							$(".a_browsedetail").bind("click",
									memberbrowsedetail);
							// 清除浏览历史记录
							$(".a_memberbrowserdelete").bind("click",
									memberbrowserdel);
						} else {
							Dalert(rsl.desc);
						}
					},
					error : function(e) {

					}
				}));
	}
}
// 分页回调
function memberbrowser_pagelist(index) {
	if (indexlog != index) {
		MemberBrowserList.bind(index, uName);
	}
}

// 清除浏览历史记录
function memberbrowserdel() {
	var memberID = $(this).parent().find("#hidden_memberid").val();
	$.ajax(({
		type : "post",
		url : "/platform/memberbrowserhistory/deleteBrowsehistory",
		dataType : "json",
		data : {
			memberid : memberID
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				MemberBrowserList.bind(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {

		}
	}));
};

// 浏览详细列表
function memberbrowsedetail() {
	var memberID = $(this).parent().find("#hidden_memberid").val();
	$
			.ajax(({
				type : "post",
				url : "/PlatformMemberManagement/P_GetBrowseHistory",
				dataType : "json",
				data : {
					memberid : memberID
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						var listdata = {
							list : rsl.data
						}
						var html = template('browserdetaillist', listdata);
						// 清除原先绑定的数据
						$("#browserdetaillist_title")
								.parent()
								.children()
								.each(
										function() {
											if ($(this).attr('id') != "browserdetaillist_title") {
												this.parentNode
														.removeChild(this);
											}
										})
						$("#browserdetaillist_title").after(html);
						// 显示DIV
						$("#div_browsedetail").show();
					} else {
						Dalert(rsl.desc);
					}
				},
				error : function(es) {

				}
			}));
};