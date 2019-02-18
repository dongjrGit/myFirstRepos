$(function () {
	var id = GetQueryStringByName("id");
	GetSelectData(id);
    //保存
   
    $("#savebtn").click(function () {
        var id = GetQueryStringByName("id");
   
        edit(id)
    });
    //返回
    $("#backbtn").click(function () {
        backhref();
    });
});

//页面返回
function backhref() {
	 window.location.href = "/seller/shopdd/ddgl_OrderThTk";
}


function edit(id){
		var body = $("#content").val();
		$.ajax({
			type : "post",
			url : "/seller/sh/editnote",
			dataType : "json",
			data : {
				"id":id,
				"note" : body
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					 window.location.href = "/seller/shopdd/ddgl_OrderThTk";
				} else {
					Dalert(rsl.desc);
				}
			}
		});
}

function GetSelectData(id){
	$.ajax({
		type : "post",
		url : "/seller/sh/getnote",
		dataType : "json",
		data : {
			id:id
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var res=rsl.data;
				 $("#content").val(res);
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
