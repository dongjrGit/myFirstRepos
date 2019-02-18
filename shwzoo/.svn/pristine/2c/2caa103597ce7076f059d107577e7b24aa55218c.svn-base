//会员列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var GoodConsultList = {
	bind : function(index) {
		var title = $("#select_title").val();
		var pronumstr = $("#select_pronum").val();
		$
				.ajax(({
					type : "post",
					url : "/platform/goodconsult/queryGoodConsult",
					dataType : "json",
					data : {
						title : title,
						pronumstr : pronumstr,
						pageindex : index,
						pagesize : psize
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							var listdata = {
								list : rsl.data
							}
							var html = template('goodconsultlist', listdata);

							// 翻页时删除表头以外的所有节点，避免after()方法重复加载
							$("#goodconsultlist_title")
									.parent()
									.children()
									.each(
											function() {
												if ($(this).attr('id') != "goodconsultlist_title") {
													this.parentNode
															.removeChild(this);
												}
											})
							$("#goodconsultlist_title").after(html);

							indexlog = index;
							pcount = rsl.maxRow;
							pindex = rsl.pageIndex;
							// alert(pindex);
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"goodconsult_pagelist"));

							// 回复商品咨询
							$(".a_goodconsultreply").bind("click",
									goodconsultreply);

							// 删除商品咨询
							$(".a_goodconsultdelete").bind(
									"click",
									function() {
										var goodconsultID = $(this).parent()
												.find("#hidden_goodconsultid")
												.val();
										ConfirmShow("确定要删除吗？", goodconsultdel,
												goodconsultID);
									});
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
function goodconsult_pagelist(index) {
	if (indexlog != index) {
		GoodConsultList.bind(index);
	}
}
// 商品咨询删除
function goodconsultdel(goodconsultID) {
	$.ajax(({
		type : "post",
		url : "/platform/goodconsult/deleteGoodConsultById",
		dataType : "json",
		data : {
			id : goodconsultID
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				GoodConsultList.bind(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {

		}
	}));
};
// 商品咨询回复
function goodconsultreply() {
	var goodconsultID = $(this).parent().find("#hidden_goodconsultid").val();
	$(".a_goodconsultreply").attr(
			'href',
			"/platform/member/showReply_goodconsult?goodconsultID="
					+ goodconsultID);
};

// 截取字符串，多余的部分用...代替
function setString(str, len) {
	var strlen = 0;
	var s = "";
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 128) {
			strlen += 2;
		} else {
			strlen++;
		}
		s += str.charAt(i);
		if (strlen >= len) {
			return s + "...";
		}
	}
	return s;
};