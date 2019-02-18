
var idlist = "";
var ids = "";
var action = "";
var Init = {
	bind : function(){
		idlist = GetQueryStringByName("idList");
		if(idlist!= ""){
			action = "pushmessages";
		}else{
			action = "pushmessage";
		}
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/queryUserIds",
			dataType : "json",
			data : {
				idList : idlist,
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					ids = rsl.data;
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(es) {

			}
		}));
	}	
}
// 表单验证
var Vaildate = {
	bind : function() {
		$("#memberpush").validate({
			rules : {
				content : {
					required : true
				}
			},
			messages : {
				content : {
					required : "请输入内容"
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
		var memberid = GetQueryStringByName("memberID");
		var content = $("#content").val();
		$.ajax(({
			type : "post",
			url : "/platform/message/"+action,
			dataType : "json",
			data : {
				content : content,
				memberid : memberid,
				idlist:ids
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert(rsl.desc,"",function(){
						window.location.href="/platform/member/showMemberList";
					});
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(es) {

			}
		}));
	}
}