//初始化
var Init = {
	bind : function() {
		// 获取手机号码
		var membermobilenum = GetQueryStringByName("membermobilenum");
		
		
		if(membermobilenum!= ""){
			$("#text_mobilenum").attr('value', membermobilenum);
			var action = "addMessageMobileRecord";
			$("#action").attr('value', action);
		}
		var idList = GetQueryStringByName("idList");
		if(idList!= ""){
			var action = "addMessageMobileRecords";
			$("#action").attr('value', action);
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/queryMobilenum",
			dataType : "json",
			data : {
				idList : idList,
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					$("#text_mobilenum").val(rsl.data.mobilenum);
					$("#text_username").val(rsl.data.usernames);
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
		$("#sendmobilemessageForm").validate({
			rules : {
				text_mobilenum : {
					required : true,
					//isMobile : true
				},
				text_mobilemessage : {
					required : true
				}
			},
			messages : {
				text_mobilenum : {
					required : "请输入手机号",
					//isMobile : "输入的手机号格式不正确"
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
		var memberID = GetQueryStringByName("memberID");
		var mobile = $("#text_mobilenum").val();
		var message = $("#text_mobilemessage").val();
		var action =$("#action").val();
		var mobilenum = $("#text_mobilenum").val();
		var usernames = $("#text_username").val();
		$.ajax(({
			type : "post",
			url : "/platform/message/"+action,
			dataType : "json",
			data : {
				type : 0,
				mobile : mobile,
				content : message,
				userid : memberID,
				mobiles:mobilenum,
				ids:usernames
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert("手机短信发送成功");
					$("#text_mobilemessage").attr("value", "");
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(es) {

			}
		}));
	}
}