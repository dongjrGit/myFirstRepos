//添加、编辑信息模板

$(document).ready(function() {

	var id = GetQueryStringByName("id");
	// 获取表单
	GetTemByID(id)
	if (id > 0) {
		$("#contitle").html("编辑信息模板");
	} else {
		$("#contitle").html("添加信息模板");
	}
	// 取消
	$("#backbtn").click(function() {
		backhref();
	});
	// 保存
	$("#savebtn").click(function() {
		if (check().form()) {
			if (id > 0) {
				edit(id);
			} else {
				add();
			}
		}
	});
	// 验证表单
	function check() {
		return $("#templateForm").validate({
			rules : {
				content : {
					required : true
				},
               orderby: {
                    digits: true
                }
			},
			// 设置提示信息
			messages : {
				content : {
					required : "必填"
				},
	             orderby: {
	                    digits: "必须输入整数"
	                }
			},
			// 设置错误信息存放标签
			errorElement : "label"// ,
		})
	}
	$(".lanse").find("p").click(function() {
		var text=$(this).find("font").text().split(':')[0];
		TextWrite(text);
	});
});
function backhref() {
	location.href = "/platform/controlpanel/showSendTemplate";
}
// 根据id获取内容
function GetTemByID(id) {

	if (id > 0) {
		$
				.ajax({
					type : "post",
					url : "/platform/freightmanager/querySendTemplateById",
					dataType : "json",
					data : {
						id : id
					},
					success : function(data) {
						if (data.code < 0) {
							// Dalert(data.Desc);
						} else {
							var tem = data.data;

							$("#content").val(tem.content);
							$("#orderby").val(tem.sort);
							$("#stag").val(tem.tag);
							if (tem.type == "0") {
								$("input[name=sttype]").removeAttr("checked", "checked");							
								$("#st1").prop("checked", "checked");
							} else if (tem.type == "1") {
								$("input[name=sttype]").removeAttr("checked", "checked");		
								$("#st2").prop("checked", "checked");							
							} else if (tem.type == "2") {
								$("input[name=sttype]").removeAttr("checked", "checked");		
								$("#st3").prop("checked", "checked");							
							}else if (tem.type == "3") {
								$("input[name=sttype]").removeAttr("checked", "checked");		
								$("#st4").prop("checked", "checked");
								}
							if (tem.ctype == "0") {
								$("input[name='ctype']").first().attr(
										"checked", "checked");
								$("input[name='ctype']").last().removeAttr(
										"checked");
							} else {
								$("input[name='ctype']").first().removeAttr(
										"checked");
								$("input[name='ctype']").last().attr("checked",
										"checked");
							}
						}

					},
					error : function(e) {
						// Dalert(e.statusText);
					}
				});
	}
}
// 编辑内容
function edit(eid) {
	var sttype = $("input[name=sttype]:checked").val();
	var con = $("#content").val();
	var ctype = $("input[name=ctype]:checked").val();
	var stag = $("#stag").val();
	var order = $("#orderby").val();
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/updateSendTemplate",
		dataType : "json",
		data : {
			"id" : eid,
			"type" : sttype,
			"content" : con,
			"ctype" : ctype,
			"tag" : stag,
			"sort" : order
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", backhref);
			} else {
				Dalert(rsl.desc);
			}

		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}
// 添加内容
function add() {

	$("#savebtn").hide();
	var sttype = $("input[name=sttype]:checked").val();
	var con = $("#content").val();
	var ctype = $("input[name=ctype]:checked").val();
	var stag = $("#stag").val();
	var order = $("#orderby").val();
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/addSendTemplate",
		dataType : "json",
		data : {
			"type" : sttype,
			"content" : con,
			"ctype" : ctype,
			"tag" : stag,
			"sort" : order
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", backhref);
			} else {
				Dalert(rsl.desc);
				$("#savebtn").show();
			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}
//textarea自动标签赋值 
function TextWrite(str){
    var tc = $("#content");
    var tclen = tc.val().length;
    tc.focus();
    var startindex=tc.focus()[0].selectionStart;    
    tc.val(tc.val().substr(0,startindex)+str+tc.val().substring(startindex,tclen));    
}