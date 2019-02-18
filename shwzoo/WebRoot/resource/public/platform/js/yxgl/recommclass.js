//商品规格管理
var pcount;
var pindex;
var psize = size_product;

// 根据规格类型ID,商品分类ID获取商品规格分页列表数据
var reclass = {
	bind : function(index) {
		$.ajax({
			url : "/platform/RecommClass/getList",
			type : "Post",
			data : {
				'page' : index,
				'size' : psize
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {

						list : data.data
					}

					var html = template('recommlist', listdata);

					// html 填充
					$("#datalist").html(html);
					// 加载table样式：改变偶数行背景色，鼠标移动时背景色
					init();
					pcount = data.maxRow;
					pindex = data.pageIndex;
					reclass.unit();
					// 分页
					$("#pager").html(
							pagination(pcount, pindex, psize, "reclass.bind"));
				}
			},
			error : function() {

			}
		});
	},
	unit : function() {
		$(".del").each(function() {
			$(this).bind("click", reclass.del);
		});
	},
	del : function() {
		var recid = $(this).attr("data-id");
		if (confirm('确定将此记录删除?')) {
			$.ajax({
				url : "/platform/RecommClass/delete",
				type : "Post",
				data : {
					'id' : recid
				},
				dataType : "json",
				success : function(data) {
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						reclass.bind(pindex);
					}
				},
				error : function() {
					Dalert("删除失败");
				}
			});
		}
	}
}
// 页面加载触发事件
$(function() {

	$("input[name=btnadd]").bind("click", function() {
		self.location = "yxgl_RecommClassAdd";
	});
	/* $("input[name=btnsearch]").bind("click", function(){reclass.bind(1);}); */
	$("input[name=Save]").bind("click", Save);
	// 全选
	$("#ch_All").click(function() {
		if ($(this).attr("checked") == "checked") {
			$("input[name=ck_list]").attr("checked", true);
		} else {
			$("input[name=ck_list]").attr("checked", false);
		}
	});
	$("#updAll").bind("click", updateOrders);
});

// 更改排序 ob-排序
function setOrder(specsid, ob) {
	var obtext = $("#" + ob).val();
	$.ajax({
		url : "/platform/RecommClass/updateOrder",
		type : "Post",
		data : {
			'id' : specsid,
			'orderby' : obtext
		},
		dataType : "json",
		success : function(data) {
			Dalert(data.desc);
		},
		error : function() {
			Dalert("修改规格排序失败");
		}
	});
}
// 全选更新序号
function updateOrders() {
	var idList = "";
	var orderList = ""
	$('input[name="ck_list"]:checked').each(function() {
		var id = $(this).val();
		if (id != "" && id != undefined) {
			var or = $("#ob_" + id).val();
			if (or != "" && or != undefined) {
				orderList += or + ",";
				idList += id + ",";
			}
		}
	});
	if (idList != "" && orderList != "") {
		idList = idList.substring(0, idList.length - 1);
		orderList = orderList.substring(0, orderList.length - 1);
		$.ajax({
			type : "post",
			url : "/platform/RecommClass/updateOrderList",
			dataType : "json",
			data : {
				ids : idList,
				orderbys : orderList
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert(rsl.desc, "", backhref);// window.location.reload();
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {
				Dalert(e.statusText);
			}
		})

	} else {
		var d = dialog({
			title : '提示',
			content : '还没有选取哦',
			okValue : '确定',
			ok : function() {
			}
		}).show();
	}
}

// 保存
function Save() {
	if (formSubmit()) {
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		$.ajax({
			url : "/platform/RecommClass/add",
			type : "Post",
			data : $("#form").serialize(),
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
}
// 保存前参数验证
function check() {
	return $("#form").validate({
		rules : {
			thirdID : {
				required : true
			},
			orderby : {
				required : true,
				digits : true
			}
		},
		message : {
			thirdID : {
				required : "第三级分类不能为空"
			},
			orderby : {
				required : "排序不能为空",
				digits : "必须输入整数"
			}
		}
	});
}
function formSubmit() {
	if (check().form()) {
		if ($("#thirdID").val() != "0") {
			$("#ClassID").val($("#thirdID").val());
			return true;
		} else {
			Dalert("请选择到第三级商品分类");
			$("#thirdID").focus();
			return false;
		}
	}

}
// 返回刷新
function backhref() {
	window.location.href = 'yxgl_RecommClassList';
}