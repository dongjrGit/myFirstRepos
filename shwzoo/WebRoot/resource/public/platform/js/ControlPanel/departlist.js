//部门管理

$(function() {
	$("input[name=add]").bind("click", add); // 绑定添加事件
	$("input[name=Save]").bind("click", Save); // 绑定保存事件
	$("input[name=search]").bind("click", function() {
		depart.bind();
	});// 绑定查询事件
})

// 添加链接
function add() {
	self.location = "/platform/controlpanel/Department_Save";
}
// 列表数据加载
var depart = {
	bind : function() {
		// 部门名称 关键字
		var keys = $("#keys").val();
		$.ajax({
			url : "/platform/department/queryDepartList",
			type : "Post",
			data : {
				keyWords : keys
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('depart_list', listdata);

					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#depart_title").parent().children().each(function() {
						if ($(this).attr('id') != "depart_title") {
							this.parentNode.removeChild(this);
						}
					})

					$("#depart_title").after(html);
					init();
				}
			},
			error : function() {
			}
		});
	}
}
// 删除
function del(departid) {

	if (confirm('确定将此记录删除?')) {
		$.ajax({
			url : "/platform/department/deleteById",
			type : "Post",
			data : {
				id : departid
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					depart.bind();
				}
			},
			error : function() {
				Dalert("删除失败");
			}
		});
	}
}
// 更改状态 ss-状态
function setStatus(departid, ss) {
	$
			.ajax({
				url : "/platform/department/updateStatusById",
				type : "Post",
				data : {
					id : departid,
					status : ss
				},
				dataType : "json",
				success : function(data) {
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						var td_html = "";
						if (ss == 0) {
							td_html = "<span class='lvs'><a href='#' onclick=setStatus("
									+ departid + ",1)>启用</a></span>";
						} else {
							td_html = "<span class='lvs'><a href='#' onclick=setStatus("
									+ departid + ",0)>禁用</a></span>";
						}
						$("#td_" + departid).html(td_html);
						depart.bind();
					}
				},
				error : function() {
				}
			});
}
// 保存
function Save() {
	var action = $("#depart_action").val();
	var status = $("input[name=status]:checked").val();
	// 防止重复提交 点击保存后隐藏按钮
	$("input[name='Save']").hide();
	$.ajax({
		url : "/platform/department/" + action,
		type : "Post",
		data : {
			"id" : $("input[name=Id]").val(),
			"num" : $("input[name=num]").val(),
			"name" : $("input[name=name]").val(),
			"fatherid" : $("input[name=FatherID]").val(),
			"fullpath" : $("input[name=FullPath]").val(),
			"status" : status
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				Dalert("保存成功！", "", backhref);
			} else {
				$("input[name='Save']").show();
				Dalert(data.desc);
			}
		},
		error : function() {
		}
	});
	
}
// 保存前验证
function check() {
	return $("#form").validate({
		rules : {
			name : {
				rangelength : [ 1, 100 ],
				required : true
			},
			num : {
				required : true
			},
		},
		message : {
			name : {
				required : "部门名称不可为空"
			},
			num : {
				required : "部门编号不能为空"
			}
		}
	});

}
// 保存前验证
function formSubmit() {
	if (check().form()) {
		if ($("#FullPath").val() == "") {
			$("#fid").val(0);
		}
		return true;
	} else {
		return false;
	}

}
// 刷新
function backhref() {
	window.location.href = '/platform/controlpanel/Department_list';
}