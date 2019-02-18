//会员列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var GoodConsultList = {
	bind : function(index) {
		  var buyername = $("#select_buyername").val();
	        var begin = $("#select_begin").val();
	        var end = $("#select_end").val();
	        var type=$("#select_type").val();
		$.ajax(({
					type : "post",
					url : "/platform/memberfeedback/queryMemberFeedBack",
					dataType : "json",
					data : {
						pageindex: index,
						pagesize: psize,
						buyername:buyername,
					    starttime: begin, 
					    endtime: end 
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

							// 查看回复详情
							$(".a_feedBackdetail").on("click",
									FeedBackDetail);

							// 删除反馈
							$(".a_feedBackdelete").on(
									"click",
									function() {
										var id = $(this).parent()
												.find("#hidden_feedBackid")
												.val();
										ConfirmShow("确定要删除吗？", feedBackdel,
												id);
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
function feedBackdel(id) {
	$.ajax(({
		type : "post",
		url : "/platform/memberfeedback/delFeedBack",
		dataType : "json",
		data : {
			id : id
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
function FeedBackDetail() {
	var id = $(this).parent().find("#hidden_feedBackid").val();
	var status=$(this).parent().find("#hidden_feedBackstatus").val();
	if(status==0){
		readMes(id);
	}
	window.location.href = "/platform/member/showMemberFeedBack?id=" + id;
};


function readMes(id) {
	$.ajax({
		type : "post",
		url : "/platform/memberfeedback/updateStatus",
		dataType : "json",
		data : {
			id : id
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				// Dalert(rsl.Desc,"",refresh);
			} else {
				// Dalert(rsl.Desc);
			}
		},
		error : function(e) {

		}
	})
}



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
}