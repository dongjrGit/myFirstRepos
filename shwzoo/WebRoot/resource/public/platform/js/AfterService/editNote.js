$(function () {
	var id = GetQueryStringByName("id");
	GetSelectData(id);
    //保存
   
    $("#submit_ok").click(function () {
        var id = GetQueryStringByName("id");
   
        edit(id)
    });
    //返回
    $("#btn_goback").click(function () {
        backhref();
    });
});

//页面返回
function backhref() {
	 window.location.href = "/platform/sh/ReturnTrade";
}


function edit(id){
		var body = $("#note").val();
		$.ajax({
			type : "post",
			url : "/platform/sh/editnote",
			dataType : "json",
			data : {
				"id":id,
				"note" : body
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					 window.location.href = "/platform/sh/ReturnTrade";
				} else {
					Dalert(rsl.desc);
				}
			}
		});
}

function GetSelectData(id){
	$.ajax({
		type : "post",
		url : "/platform/sh/getnote",
		dataType : "json",
		data : {
			id:id
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var res=rsl.data;
				 $("#note").val(res);
			} else {
				Dalert(rsl.desc);
			}
		}
	});
}

// 表单验证
/*var Vaildate = {
	bind : function() {
		$("#sendemailForm").validate({
			rules : {
				
				text_emailbody : {
					required : true
				}
			},
			messages : {
				
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
}*/
// 表单提交
/*var Submit = {
	bind : function() {
		 var id = GetQueryStringByName("id");
		var body = $("#text_emailbody").val();
		$.ajax(({
			type : "post",
			url : "/platform/message/sendEmail",
			dataType : "json",
			data : {
				id:id,
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
	}*/
