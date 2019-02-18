var membermobilenumlist;
var memberidlist;
// 初始化
var Init = {
	bind : function() {
		membermobilenumstr = GetQueryStringByName("membermobilenumstr"); // 会员多个手机号码
		memberidstr = GetQueryStringByName("memberidstr");

		$("#text_mobilenum").attr('value', membermobilenumstr);
	}
}
// 表单验证
var Vaildate = {
	bind : function() {
		$("#sendmobilemessageForm").validate({
			rules : {
				text_mobilenum : {
					required : true
				// isMobile: true
				},
				text_title : {
					required : true 
				},
				text_mobilemessage : {
					required : true
				}
			},
			messages : {
				text_mobilenum : {
					required : "请输入手机号"
				// isMobile: "输入的手机号格式不正确"
				},
				text_title : {
					required : "请输入标题"
				},
				text_mobilemessage : {
					required : "请输入信息"
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
		var mobile = $("#text_mobilenum").val();
		var message = $("#text_mobilemessage").val();
		var title = $("#text_title").val();

		var mobilelist = mobile.split(',');
		if (mobilelist.length > 1) {
			$.ajax(({
				type : "post",
				url : "/platform/message/addMessageMobileRecords",
				dataType : "json",
				data : {
					type : 0,
					mobiles : mobile,
					content : message,
					title : title,
					ids : memberidstr
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						Dalert("手机短信发送成功", "", backhref);
						$("#text_mobilemessage").attr("value", "");
					} else {
						Dalert(rsl.desc, "", backhref);
					}
					// location.href = "SendMobileMessage";
				},
				error : function(es) {
					Dalert(es.statusText);
				}
			}));
		} else {
			$.ajax(({
				type : "post",
				url : "/platform/message/addMessageMobileRecord",
				dataType : "json",
				data : {
					type : 0,
					mobile : mobile,
					content : message,
					title : title,
					userid : memberidstr
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						Dalert("手机短信发送成功", "", backhref);
						$("#text_mobilemessage").attr("value", "");
					} else {
						Dalert(rsl.desc, "", backhref);
					}
				},
				error : function(es) {
					Dalert(es.statusText);
				}
			}));
		}

	}
}
function backhref() {
	location.href = "/platform/message/showSendMobileMessage";
}