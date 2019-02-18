/*店铺状态变更；author:rw*/
$(document).ready(function() {
	var id = $("#shopID").val();
	// 验证表单
	function check() {
		return $("#stachange").validate({
			rules : {
				selectsta : {
					required : true
				}
			},
			messages : {
				selectsta : {
					required : "请选择变更状态"
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			}
		});
	}
	GetByID(id);// 根据店铺id获取店铺状态

	// 清空原因
	$("#clearbtn").click(function() {
		$("#reason").val("");
	})
	// 保存表单
	$("#savebtn").click(function() {
		if (check().form())
			save(id);
	})
});
// 保存 id：店铺id
function save(id) {
	var sta = $("#selectsta").val();
	var rea = $("#reason").val();
	$.ajax({
		type : "post",
		url : "/seller/shop/changeStatus",
		dataType : "json",
		data : {
			status : sta,
			id : id,
			reason : rea
		},
		success : function(data) {
			if (data.code < 0) {
				Dalert("保存成功", "", function(){
					location.reload();
				});
			} else {
				Dalert(data.desc, "", refresh);
				//location.reload();
			}
		},
		error : function(e) {
			// alert(e.statusText);
		}
	});
}
// 刷新
function refresh() {
	location.reload();
}
// 根据id获取店铺状态 id：店铺id
function GetByID(id) {
	$.ajax({
		url : "/seller/shop/queryShopById",
		type : "post",
		data : {
			id : id
		},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {
			} else {
				var af = data.data;
				if (af.status == 0) {
					$("#staNow").html("店铺正在审核中");
				}
				if (af.status == 1) {
					$("#staNow").html("店铺审核通过");
				}
				if (af.status == 2) {
					$("#staNow").html("店铺审核不通过");
				}
				if (af.status == 3) {
					$("#staNow").html("店铺违规");
				}
				if (af.status == 4) {
					$("#staNow").html("正在营业");
				}
				if (af.status == 5) {
					$("#staNow").html("店铺打烊");
				}
				if (af.status == 6) {
					$("#staNow").html("店铺删除");
				}
				if (af.status == 4 || af.status == 1 || af.status == 5) {
					$("#reason").attr("value", af.Reason);
				} else {
					$("#selectsta").attr("disabled", "disabled");
					$("#reason").attr("disabled", "disabled");
					$("#savebtn").attr("style", "display:none");
					$("#clearbtn").attr("style", "display:none");
				}
			}

		}
	});
}