//会员列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var MemberList = {
	bind : function(index) {
		var imgurl= $("aelect_imgurl").val();
		var name = $("#select_name").val();
		var mobile = $("#select_mobile").val();
		var email = $("#select_email").val();
		var sex = $("#select_sex").val();
		var begin = $("#select_begin").val();
		var end = $("#select_end").val();
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/queryMemberList",
			dataType : "json",
			data : {
				pageindex : index,
				pagesize : psize,
				"name" : name,
				"mobile" : mobile,
				"email" : email,
				"sex" : sex,
				"begin" : begin,
				"end" : end
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
					// 加载table样式：改变偶数行背景色，鼠标移动时背景色
					init();
					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					// alert(pindex);
					$("#pager")
							.html(
									pagination(pcount, pindex, psize,
											"member_pagelist"));

					// 编辑会员信息
					$(".a_memberupdate").bind("click", memberupdate);
					// 发送邮件
					$(".a_memberemail").bind("click", sendmemberemail);
					// 发送手机短信
					$(".a_membermobile").bind("click", sendmembermobile);
					// 发送站内信
					$(".a_memberinfo").bind("click", sendmemberinfo);
					// 修改密码
					$(".a_pwdupdate").bind("click", memberpwdupdate);
					//推送消息
					$(".a_memberpush").bind("click", memberpush);
					// 修改支付密码
				//	$(".a_pwdpaypaupdate").bind("click", memberpaypwdupdate);
					// 删除会员
					$(".a_memberdelete").bind(
							"click",
							function() {
								var memberID = $(this).parent().find(
										"#hidden_memberid").val();
								ConfirmShow("确定要删除吗？", memberdel, memberID);
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
function member_pagelist(index) {
	if (indexlog != index) {
		MemberList.bind(index);
	}
}

// 编辑会员信息
function memberupdate() {
	var memberID = $(this).parent().find("#hidden_memberid").val();
	$(".a_memberupdate").attr('href',
			"/platform/member/showMemberUpdate?memberid=" + memberID);
};
// 发送邮件
function sendmemberemail() {
	var memberemail = $(this).parent().parent().find("#td_memberemail").text();
	$(".a_memberemail").attr('href',
			"/platform/member/showSend_email?memberemail=" + memberemail);
};
// 发送手机短信
function sendmembermobile() {
	var memberID = $(this).parent().find("#hidden_memberid").val();
	var membermobilenum = $(this).parent().parent().find("#td_membermobile")
			.text();
	$(".a_membermobile").attr(
			'href',
			"/platform/member/showSend_MobileMessage?membermobilenum="
					+ membermobilenum + "&memberID=" + memberID);
};
// 发送站内信
function sendmemberinfo() {
	var memberID = $(this).parent().find("#hidden_memberid").val();
	$(".a_memberinfo").attr('href',
			"/platform/member/showSend_SystemMessageS?receiveid=" + memberID);
};
// 密码修改
function memberpwdupdate() {
	var memberID = $(this).parent().find("#hidden_memberid").val();
	$(".a_pwdupdate").attr('href',
			"/platform/member/showMemberPwd_update?memberID=" + memberID);
}
//推送消息
function memberpush() {
	var memberID = $(this).parent().find("#hidden_memberid").val();
	$(".a_memberpush").attr('href',
			"/platform/member/showMember_Push?memberID=" + memberID);
}
//支付密码修改
/*function memberpaypwdupdate() {
	var memberID = $(this).parent().find("#hidden_memberid").val();
	$(".a_pwdpaypaupdate").attr('href',
			"/platform/member/showMemberPayPwd_update?memberID=" + memberID);
}*/


// 删除会员
function memberdel(memberID) {
	$.ajax(({
		type : "post",
		url : "/platform/membermanagement/deleteMember",
		dataType : "json",
		data : {
			usertype : 2,
			memberid : memberID
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				MemberList.bind(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {

		}
	}));
};
// 修改账户冻结状态
function setStatus(id, ss) {
	$
			.ajax({
				url : "/platform/membermanagement/updateStatus",
				type : "Post",
				data : {
					'id' : id,
					'status' : ss
				},
				dataType : "json",
				success : function(data) {
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						var td_html = "";
						if (ss == 0) {
							td_html = "<span class='lvs'><a href='#' onclick=setStatus("
									+ id + ",1)>冻结</a></span>";
						} else {
							td_html = "<span class='lvs'><a href='#' onclick=setStatus("
									+ id + ",0)>解冻</a></span>";
						}
						$("#td_" + id).html(td_html);
						MemberList.bind(indexlog);
					}
				},
				error : function() {
					Dalert("修改状态失败");
				}
			});
}

// 数据初始化
var Init = {
	bind : function() {
		// 性别数据绑定
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/querySexAll",
			dataType : "json",
			data : {},
			success : function(rsll) {
				if (rsll.code == 0) {
					var listdata = {
						list : rsll.data
					}
					var html = template('sexselect', listdata);
					$("#select_sex").append(html);
				} else {
					Dalert(rsll.desc);
				}
			},
			error : function(es) {

			}
		}));

		// 全选或者取消全选
		$("#selectall").click(function() {
			if ($("#selectall").prop('checked')) {
				$(".member_check_list").prop("checked", true);
			} else {
				$(".member_check_list").prop("checked", false);
			}
		});
		$("#emailsend").bind("click", emailsend);

		$("#mobilesend").click("click",mobilesend);
		
		$("#messagesend").click("click",messagesend);
		
		$("#pushmsg").click("click",pushmessage);

	}
}
//批量邮件
function emailsend() {
	var idList = "";
	$('input[name="chk_list"]:checked').each(function() {
		var id = $(this).val();
		idList += id + ",";
	});
if(idList==null || idList==""){
	Dalert("请选择用户");
}else{
	window.location.href = "/platform/member/showSend_email?idList="
		+ idList;
}
	
}
//批量短信
function mobilesend() {
	var idList = "";
	$('input[name="chk_list"]:checked').each(function() {
		var id = $(this).val();
		idList += id + ",";
	});
	if(idList==null || idList==""){
		Dalert("请选择用户");
	}else{
	window.location.href = "/platform/member/showSend_MobileMessage?idList="
		+ idList;
	}
}
//批量站内信
function messagesend() {
	var idList = "";
	$('input[name="chk_list"]:checked').each(function() {
		var id = $(this).val();
		idList += id + ",";
	});
	if(idList==null || idList==""){
		Dalert("请选择用户");
	}else{
	window.location.href = "/platform/member/showSend_SystemMessageS?idList="
		+ idList;
	}
}
//批量推送消息
function pushmessage() {
	var idList = "";
	$('input[name="chk_list"]:checked').each(function() {
		var id = $(this).val();
		idList += id + ",";
	});
	if(idList==null || idList==""){
		Dalert("请选择用户");
	}else{
	window.location.href = "/platform/member/showMember_Push?idList="
		+ idList;
	}
}