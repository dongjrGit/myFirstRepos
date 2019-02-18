
// 表单验证
var Vaildate = {
	bind : function() {
		$("#addannouncementForm").validate({
			rules : {
				text_title : {
					required : true,
				// byteRangeLength : [ 4, 15 ]
				},
				text_content : {
					required : true,
				/* byteRangeLength : [ 6, 15 ] */
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
		var title = $("#text_title").val();
		var content = $("#text_content").val();
		var sort = $("#text_sort").val();
		var oldid = $("#hidden_announid").val();
		if (oldid != null && oldid != "") {
			$.ajax(({
				type : "post",
				url : "/platform/announcement/editannounce",
				dataType : "json",
				data : {
					title : title,
					content : content,
					sort : sort,
					oldid : oldid
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						Dalert("编辑成功");
						$("#text_title").attr("value", "");
						$("#text_content").attr("value", "");
						$("#text_sort").attr("value", "");
					} else {
						Dalert(rsl.desc);
					}
				},
				error : function(e) {
				}
			}));
		} else {
			$.ajax(({
				type : "post",
				url : "/platform/announcement/announcementaddp",
				dataType : "json",
				data : {
					title : title,
					content : content,
					sort : sort
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						Dalert("添加成功");
						$("#text_title").attr("value", "");
						$("#text_content").attr("value", "");
						$("#text_sort").attr("value", "");
					} else {
						Dalert(rsl.desc);
					}
				},
				error : function(e) {
				}
			}));
		}
	}
}
