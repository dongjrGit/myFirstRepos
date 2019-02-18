//自定义分类编辑
var spfltj = {
	unit : function(callback) {
		spfltj.getFirstClass(callback);
	},
	getFirstClass : function(callback) {
		$.ajax({
			url : "/platform/shop/getClassByFatherID",
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
						callback("fc");
					}
				}
			},
			error : function() {

			}
		});
	},
	firstChange : function(callback, value) {
		var fid = $("#firstID").val();
		if (value == "fc") {
			fid = $("#firstID").val();
		} 
		$
				.ajax({
					url : "/platform/shop/getClassByFatherID",
					type : "post",
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
							if (value == "fc") {
								var html = '<option value="0" id="defaultsc" selected>无</option>'
										+ template('slist', listdata);
								$("#secondID").html(html);
								if (callback) {
									callback("sc");
								}
							} 

						}
					},
					error : function() {

					}
				})
	},
	callback : function(value) {
		var fid = $("#fid").val(), sid = $("#sid").val();
		if (value == "fc" && fid > 0) {
			$("#firstID option").each(function() {
				if ($(this).val() == fid) {
					$(this).attr("selected", "selected");
				} else {
					$(this).removeAttr("selected");
				}
			});
		}
		if (value == "sc" && sid > 0) {
			$("#secondID option").each(function() {
				if ($(this).val() == sid) {
					$(this).attr("selected", "selected");
				} else {
					$(this).removeAttr("selected");
				}
			});
		}

		spfltj.firstChange(spfltj.callback, value);
	}
}

$(function() {
	$("input[name=Save]").bind("click", Save);
})
// 保存
function Save() {
	if (formSubmit()) {
		$("input[name='Save']").hide();
		var url = "/platform/shop/addCategory";
		if ($("input[name='id']").val() > 0) {
			url = "/platform/shop/updateCategory";
		}
		$.ajax({
			url : url,
			type : "Post",
			data : $("#form").serialize(),
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
					$("input[name='Save']").show();
				} else {
					Dalert(data.desc, "", backhref);
					// window.location.href = '/Seller/spgl/spgl_spflgl';
				}
			},
			error : function() {

			}
		});
	}
}
function formSubmit() {
	if (check().form()) {
		if ($("#secondID").val() > 0) {
			return true;
		} else {
			Dalert("请选择到第二级分类");
			$("#secondID").focus();
			return false;
		}
	}
}

function check(){
	return $("#form").validate({
		rules : {
			name : "required",
			sort : "required"
		},
		message : {
			name : {
				required : "类别名称不可为空"
			},
			sort : {
				required : "排序不可为空"
			}
		}
	});
}

function backhref() {
	window.location.href = '/platform/shop/spgl_customlist';
}