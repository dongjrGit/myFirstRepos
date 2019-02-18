var memberemailstr;
// 初始化
var Init = {
	bind : function() {
		memberemailstr = GetQueryStringByName("memberemailstr");
		var emaillist = memberemailstr.split(',');
		var stremail = '';
		for (var i = 0; i < emaillist.length; i++) {
			if (emaillist[i] != '' && emaillist[i] != null
					&& emaillist[i] != undefined)
				stremail += emaillist[i] + ',';
		}
		if (stremail.length > 0)
			stremail = stremail.substring(0, stremail.length - 1);
		$("#text_emailrecipient").attr('value', stremail);
	}
}
// 表单验证
var Vaildate = {
	bind : function() {
		$("#sendemailForm").validate({
			rules : {
				text_emailrecipient : {
					required : true,
					//email: true
				},
				text_emailtheme : {
					required : true
				},
				text_emailbody : {
					required : true
				}
			},
			messages : {
				text_emailrecipient : {
					required : "请输入收件人",
					//email: "输入的邮箱格式不正确"

				},
				text_emailtheme : {
					required : "请输入邮件主题"
				},
				text_emailbody : {
					required : "请输入正文"
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			},
			debug : true,
			submitHandler : function(form) {
				$(form).ajaxSubmit(Submit.bind());
			}
		})
	}
}
// 表单提交
var Submit = {
	bind : function() {
		var email = $("#text_emailrecipient").val();
		var them = $("#text_emailtheme").val();
		var body = $("#text_emailbody").val();

		var emaillist = email.split(',');
		if (emaillist.length > 1) {
			$.ajax(({
				type : "post",
				url : "/platform/message/sendEmail",
				dataType : "json",
				data : {
					emails : email,
					title : them,
					content : body
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						Dalert("邮件发送成功");
						$("#text_emailtheme").attr("value", "");
						$("#text_emailbody").attr("value", "");
					} else {
						Dalert(rsl.desc);
					}
				},
				error : function(es) {
					Dalert(es.statusText);
				}
			}));
		} else {
			$.ajax(({
				type : "post",
				url : "/platform/message/sendEmailOne",
				dataType : "json",
				data : {
					email : email,
					title : them,
					content : body
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						Dalert("邮件发送成功");
						$("#text_emailtheme").attr("value", "");
						$("#text_emailbody").attr("value", "");
					} else {
						Dalert(rsl.desc);
					}
				},
				error : function(es) {
					Dalert(es.statusText);
				}
			}));
		}
	}
}