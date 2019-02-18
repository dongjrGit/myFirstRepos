/*信息管理-发送站内信管理-站内信查看或发送*/
$(document).ready(function() {
	var receiveID = GetQueryStringByName("receiveid");
	var read = GetQueryStringByName("rd");
	if (read == "true") {
		$("#text_title").addClass("border");
		$("#text_content").addClass("border");
		$("#text_title").attr("readonly", "readonly");
		$("#text_content").attr("readonly", "readonly");
		GetForm(receiveID);
	} else {
		$("#mesHead").html("发送站内信");
		$("#saveBtn").attr("style", "display:inline-block");
	}
	// 返回
	$("#backBtn").click(function() {
		if (read == "true") {
			location.href = "/platform/message/showSendedMessage";

		} else {
			location.href = "/platform/message/showSendMessage";
		}
	})
	// 保存
	$("#saveBtn").click(function() {
		if (check().form()) {
			Submit.bind(receiveID);
		}
	});
	// 表单验证
	function check() {

		return $("#sendForm").validate({
			rules : {
				text_title : {
					required : true
				},
				text_content : {
					required : true
				}
			},
			messages : {
				text_title : {
					required : "请输入标题"
				},
				text_content : {
					required : "请输入内容"
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			}

		})
	}
})

// 表单提交receiveID：用户id
var Submit = {
	bind : function(receiveID) {
		$("#saveBtn").hide();
		var title = $("#text_title").val();
		var content = $("#text_content").val();
		var type = $("#types").val();
		/*
		 * $.ajax({ type : "post", url : "/platform/message/addMessageList",
		 * dataType : "json", data : { receiveid : receiveID, title : title,
		 * content : content }, success : function(rsl) { if (rsl.code == 0) {
		 * Dalert("站内信发送成功", "", backhref); } else { Dalert(rsl.desc);
		 * $("#saveBtn").show(); } }, error : function(es) { //
		 * Dalert(es.statusText); } });
		 */
		$.ajax({
			type : "post",
			url : "/platform/message/addMessageRecords",
			dataType : "json",
			data : {
				receiveid : receiveID,
				title : title,
				type : type,
				content : content
				
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert(rsl.desc, "", backhref);

				} else {
					// Dalert(rsl.Desc);
				}
			},
			error : function(es) {
				// Dalert(es.statusText);
			}
		});

	}
}
// 获取表单 mid：信息id
function GetForm(mid) {
	$.ajax(({
		type : "post",
		url : "/platform/message/queryMesRecById",
		dataType : "json",
		data : {
			id : mid
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var m = rsl.data;
				$("#text_title").prop("value", m.title);
				$("#text_content").prop("value", m.content);
			} else {
				// Dalert(rsl.Desc);
			}
		},
		error : function(es) {
			// Dalert(es.statusText);
		}
	}));
}
function backhref() {
	window.location.href = "/platform/message/showSendMessage";
}