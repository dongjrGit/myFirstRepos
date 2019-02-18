/*发送信息模板管理*/
$(document).ready(function() {
	Template.bind(1);
	// 删除信息
	$("body").on("click",".delete", function() {
		var id = $(this).parent().find("input").val();
		ConfirmShow("确认要信息模板吗？", del, id);

	});
	// 编辑
	$("body").on("click",".bjxx", function() {
		var id = $(this).parent().find("input").val();

		location.href = "/platform/controlpanel/showSendTemplateEdit?id=" + id;
	});
	// 设为默认
	$("body").on("click",".setDef", function() {
		var id = $(this).parent().find("input").val();
		var txt = $(this).find("a").text();
		if (txt == "设为默认模板") {
			setDef(id);
		}
	});
	// 全选
	$("#SelectAll").click(function() {
		if ($(this).attr("checked") == "checked") {
			$("input[name=chk_list]").attr("checked", true);
		} else {
			$("input[name=chk_list]").attr("checked", false);
		}
	})
	// 全部删除信息
	$("#delete_all").click(function() {
		// var idList = "";
		// $("input[name=chk_list]:checked").each(function () {
		// var id = $(this).val();
		// idList += id + ",";
		// });
		// if (idList != "") {
		// idList = idList.substring(0, idList.length - 1);

		// ConfirmShow("确认要删除选取文章吗？", dellist, idList);
		// }
		// else {
		// Dalert("还没有选取哦");

		// }

	});
	// 添加跳转
	$("#addbtn").click(function() {
		location.href = "/platform/controlpanel/showSendTemplateEdit";
	});
	// 模板搜索
	$("#searchBtn").click(function() {
		Template.bind(1);
	});
});
var pindex;
var pcount;
var psize = 10;
var Template = {
	bind : function(index) {
		var sttype = $("#stType").val();
		var ctype = $("#cType").val();
		$.ajax({
			url : "/platform/freightmanager/getSendTemplateList",
			type : "post",
			data : {
				"page" : index,
				"size" : psize,
				"type" : sttype,
				"ctype" : ctype
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					// alert(data.Desc);
					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#trlist").parent().children().each(function() {
						if ($(this).attr('id') != "trlist") {
							this.parentNode.removeChild(this);
						}
					})
				} else {

					var listdata = {
						list : data.data
					}
					var html = template('datalist', listdata);
					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#trlist").parent().children().each(function() {
						if ($(this).attr('id') != "trlist") {
							this.parentNode.removeChild(this);
						}
					})
					$("#trlist").after(html);
					// 分页
					pcount = data.maxRow;
					pindex = data.pageIndex;

					$("#pager").html(
							pagination(pcount, pindex, psize, "pagelist"));
				}
			},
			error : function() {
			}
		});
	}
}
function pagelist(index) {
	Template.bind(index);
}

function del(id) {
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/deletSendTemplate",
		dataType : "json",
		data : {
			id : id
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", refresh);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}

function dellist(idList) {
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/deletTemplateList",
		dataType : "json",
		data : {
			ids : idList
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc);
				Message.bind(1);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	})
}

function refresh() {
	location.reload();
}
function setDef(id) {
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/setDefaultSendTemplate",
		dataType : "json",
		data : {
			id : id
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, 1, refresh);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}

function backhref() {
	location.href = "/platform/controlpanel/showSendTemplate";
}
