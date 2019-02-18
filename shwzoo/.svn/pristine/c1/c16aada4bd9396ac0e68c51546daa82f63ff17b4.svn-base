var receiveID;
//初始化
var Init = {
    bind: function () {
        //获取接收ID
    	receiveID = GetQueryStringByName("receiveid");
		if(receiveID!= ""){
			$("#ids").val(receiveID);
			var action = "AddMessageRecords";
			$("#action").attr('value', action);
		}
		var idList = GetQueryStringByName("idList");
		if(idList!= ""){
			var action = "addMessageList";
			$("#action").attr('value', action);
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/queryUserIds",
			dataType : "json",
			data : {
				idList : idList,
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					$("#ids").val(rsl.data);
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
//表单验证
var Vaildate = {
    bind: function () {
        $("#sendsystemmessageForm").validate({
            rules: {
                text_title: {
                    required: true
                },
                text_content: {
                    required: true
                }
            },
            messages: {
                text_title: {
                    required: "请输入标题"
                },
                text_content: {
                    required: "请输入内容"
                }
            },
            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
            },
            debug: true,
            submitHandler: function (form) {
                $(form).ajaxSubmit(Submit.bind());
            }
        })
    }
}
//表单提交
var Submit = {
    bind: function () {
    	var type = $("#type").val();
        var title = $("#text_title").val();
        var content = $("#text_content").val();
        var receiveID =  $("#ids").val();
        var action = $("#action").val();
        $.ajax({
            type: "post",
            url: "/platform/membermanagement/addMessageList",
            dataType: "json",
            data: { receiveid: receiveID, title: title, content: content, type:type },
            success: function (rsl) {
                if (rsl.code == 0) {
                    Dalert("站内信发送成功");
                    $("#text_title").attr("value", "");
                    $("#text_content").attr("value", "");
                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function (es) {
                
            }
        });
        $.ajax({
            type: "post",
            url: "/Message/addMessageList",
            dataType: "json",
            data: { receiveID: receiveID, reTitle: title, content: content },
            success: function (rsl) {
                if (rsl.code == 0) {
                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function (es) {
               
            }
        });
    }
}
function backhref(){
        window.location.href = "/platform/member/showSend_SystemMessageS";
    }
