//会员列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var MemberList = {
	bind : function(index) {
		var name = $("#select_name").val();
		var email = $("#select_email").val();
		var begin = $("#select_begin").val();
		var end = $("#select_end").val();
		$.ajax(({
			type : "post",
			url : "/platform/message/queryMemberListByCriteria",
			dataType : "json",
			data : {
				page : index,
				size : psize,
				"rName" : name,
				"email" : email,
				"startTime" : begin,
				"endTime" : end
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('memberlist', listdata);

					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#memberlist_title").parent().children().each(function() {
						if ($(this).attr('id') != "memberlist_title") {
							this.parentNode.removeChild(this);
						}
					})
					$("#memberlist_title").after(html);
					// 全选框取消选中
					$("#selectall").attr('checked', false);

					indexlog = index;
					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					// Dalert(pindex);
					$("#pager")
							.html(
									pagination(pcount, pindex, psize,
											"member_pagelist"));

					// 发送邮件
					$(".a_memberemail").bind("click", sendmemberemail);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {
				Dalert(e.statusText);
			}
		}));
	}
}
// 分页回调
function member_pagelist(index) {
	if (indexlog != index) {
		MemberList.bind(index);
	}
}

// 全选或者取消全选
var SelectAllorNoall = {
	bind : function() {
		$("#selectall").click(function() {
			if ($("#selectall").prop('checked')) {
				$(".member_check_list").prop("checked", true);
			} else {
				$(".member_check_list").prop("checked", false);
			}
		})
	}
}

// 发送邮件
function sendmemberemail() {
	var memberemail = $(this).parent().parent().find("#td_memberemail").text();
	$(".a_memberemail").attr('href',
			"/platform/message/showSendEmailAdd?memberemailstr=" + memberemail);
};