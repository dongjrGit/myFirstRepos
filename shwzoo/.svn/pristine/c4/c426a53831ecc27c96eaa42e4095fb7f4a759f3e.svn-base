//查看站内信
$(document).ready(function() {
	var id = GetQueryStringByName("id");

	GetByID(id);
	$("#backBtn").click(function() {
		location.href = "/seller/message/showUserMessage";
	})

});
// 根据id获取信息
function GetByID(id) {
	$.ajax({
		url : "/seller/message/queryMessageById",
		type : "post",
		data : {
			id : id
		},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {

			} else {
				var af = data.data;
				$("#sendUser").prop("value", af.sendusername);

				$("#msContent").prop("value", af.content);
			}

		}
	});
}