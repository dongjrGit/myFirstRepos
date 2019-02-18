//初始化
var Init = {
	bind : function() {
		// 获取邮箱地址
		var memberemail = GetQueryStringByName("memberemail");
		$("#text_emailrecipient").attr('value', memberemail);
			var action = "sendEmailOne";
			$("#action").attr('value', action);
		var idList = GetQueryStringByName("idList");
		if(idList!= ""){
			var action = "sendEmail";
			$("#action").attr('value', action);
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/queryEmail",
			dataType : "json",
			data : {
				idList : idList,
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					$("#text_emailrecipient").attr('value', rsl.data);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(es) {

			}
		}));
		
		}
	}
}
// 表单验证
var Vaildate = {
	bind : function() {
		$("#sendemailForm").validate({
			rules : {
				text_emailrecipient : {
					required : true,
					//email : true
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
					//email : "输入的邮箱格式不正确"

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
		var action = $("#action").val();
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/"+action,
			dataType : "json",
			data : {
				emails : email,
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

			}
		}));
	}
}