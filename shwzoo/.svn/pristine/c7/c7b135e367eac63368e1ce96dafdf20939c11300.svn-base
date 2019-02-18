//商品分类添加

var Class = {
	unit : function(callback) {
		Class.bind();
		Class.getFirstClass(callback);
//		$("#firstID").change(Class.firstChange);
	},
	getFirstClass : function(callback) {
		$.ajax({
			url : "/platform/commodity/getClassByFatherID",
			type : "post",
			data : {
				'fatherid' : 0
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('flist', listdata);
					$("#defaultfc").after(html);
					if (callback) {
						Class.callback();
						if($("#fid").val()>0){
							Class.firstChange(callback);
						}
					}
				}
			},
			error : function() {

			}
		});
	},
	firstChange : function(callback) {
		var fid = $("#firstID").val();
		$.ajax({
					url : "/platform/commodity/getClassByFatherID",
					type : "Post",
					data : {
						"fatherid" : fid
					},
					dataType : "json",
					success : function(data) {
						if (data.code < 0) {
							Dalert(data.desc);
						} else {
							var listdata = {
								list : data.data
							}
							var html = '<option value="0" id="defaultsc" selected>无</option>'
									+ template('slist', listdata);
							$("#secondID").html(html);
							if (callback) {
								Class.callback();	
							}
						}
					},
					error : function() {

					}
				})
	},
	callback : function(value) {
		var fid = $("#fid").val(), sid = $("#sid").val();
		if (fid > 0) {
			$("#firstID option").each(function() {
				if ($(this).val() == fid) {
					$(this).attr("selected", "selected");
				} else {
					$(this).removeAttr("selected");
				}
			});
		}
		if (sid > 0) {
			$("#secondID option").each(function() {
				if ($(this).val() == sid) {
					$(this).attr("selected", "selected");
				} else {
					$(this).removeAttr("selected");
				}
			})
		}
	},
	bind : function() {
		$("#form").validate({
			rules : {
				name : "required",
				warnnum : "digits",
				sort : "digits"
			},
			message : {
				name : {
					required : "类别名称不可为空"
				},
				warnnum : {
					digits : "请输入整数"
				},
				sort : {
					digits : "请输入整数"
				}
			}
		});
	}
	 
}
function formSubmit(action) {
	// 防止重复提交 点击保存后隐藏按钮
	$("input[name='commit']").hide();
	// TODO 前端校验数据格式
	$("input[name='fatherid']").val($("#firstID").val());
	$("input[name='secondid']").val($("#secondID").val());
	var Url = "/platform/commodity/addCategory";
	if (action == "updateCategory")
		Url = "/platform/commodity/updateCategory";
	$.ajax({
		url : Url,
		data : $("#form").serialize(),
		type : "Post",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				Dalert("保存成功！", "", backhref);
			} else {
				$("input[name='commit']").show();
				Dalert(data.desc);
			}
		},
		error : function(e) {

		}
	});
}

function backhref() {
	window.location.href = "/platform/product/goods_class";
}