//会员列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var List = {
	bind : function(index) {

		var title = $("#select_title").val();
		var pronum = $("#select_pronum").val();
		$.ajax(({
			type : "post",
			url : "/platform/logistics/pagelist",
			dataType : "json",
			data : {
				name : title,
				code : pronum,
				page : index,
				size : psize
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('list', listdata);

					$(".data_list tbody").html(html);
					indexlog = index;
					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					// alert(pindex);
					$("#pager").html(
							pagination(pcount, pindex, psize, "pagelist"));

				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	}
}
// 分页回调
function pagelist(index) {
	if (indexlog != index) {
		List.bind(index);
	}
}
function deleteid(id) {
	$.ajax(({
		type : "post",
		url : "/platform/logistics/deletebyid",
		dataType : "json",
		data : {
			id : id
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				List.bind(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {

		}
	}));
}
// 删除
function deletebyid(id) {
	ConfirmShow("确定要删除吗？", deleteid, id);

};

// 表单验证
function check() {

	return $("#form1").validate({
		rules : {
			name : {
				required : true
			},
			code : {
				required : true
			}
		},
		messages : {
			name : {
				required : "请输入名称！"
			},
			code : {
				required : "请输入编号！"
			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		},
		debug : true
	// submitHandler : function(form) {
	// $(form).ajaxSubmit(Submit.bind());
	// }
	});
}
// 表单提交
function save() {

	if (!check().form()) {
		return;
	}
	$("#form1").ajaxSubmit({
		type : "post",
		url : "/platform/logistics/save",
		dataType : "json",
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert("保存成功");
				window.location.href = "/platform/logistics/list";
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	});
}