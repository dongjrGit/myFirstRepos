$(document).ready(function() {
	$("#searchtitle").bind("click", function() {
		Find.bind(1);
	});
	Find.bind(1);
	
	// 删除图片
	$("body").on("click",".delete", function() {
		var id = $(this).parent().parent().find("input").val();
		ConfirmShow("确认要删除信息吗？", del, id);

	});
	
	
	// 全选
	$("#SelectAll").click(function() {
		if ($(this).prop('checked')) {
			$("input[name=chk_list]").prop("checked", true);
		} else {
			$("input[name=chk_list]").prop("checked", false);
		}
	})
	
	
	//全选更新序号
$("#updAll").click(function() {
	var idList = "";
	var orderList = ""
	$('input[name="chk_list"]:checked').each(function() {
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
			url : "/platform/find/UpdateSortList",
			dataType : "json",
			data : {
				ids : idList,
				sorts : orderList
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert(rsl.desc, "", refresh);// window.location.reload();
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
});

	/*$(".set").live("click", function(){
		var findid = $(this).attr("data-id");
		var ss = $(this).attr("data-status");
		$.ajax({
			url : "/platform/find/UpdateStatus",
			type : "Post",
			data : {
				'id' : findid,
				'status' : ss
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var td_html = "";
					if (ss == "0") {
						td_html = "<span class='lvs'><a href='#' data-id='"
								+ findid + "' data-status='0'>启用</a></span>";
					} else {
						td_html = "<span class='lvs'><a href='#'data-id='"
								+ findid + "' data-status='1'>禁用</a></span>";
					}
					$("#td_" + findid).html(td_html);
					refresh();
				}
			},
			error : function() {
				Dalert("修改状态失败");
			}
		});
	})*/
	
	
	
	
	// 编辑
	$("body").on("click",".cxtt", function() {
		var id = $(this).parent().parent().find("input").val();
		location.href = "/platform/find/editFind?id=" + id;
	});
	
	// 全部删除信息
	$("#delete_all").click(function() {
		var idList = "";
		$('input[name="chk_list"]:checked').each(function() {
			var id = $(this).val();
			idList += id + ",";
		});
		if (idList != "") {
			idList = idList.substring(0, idList.length - 1);
			ConfirmShow("确认要删除选取信息吗？", dellist, idList);
		} else {
			Dalert("还没有选取哦");
		}

	});
});

var title="";

var Find = {
		bind : function(index) {
			var title = $("#stitle").val();
			$.ajax({
				url : "/platform/find/queryFindRecord",
				type : "Get",
				dataType : "json",
				data : {
					page : index,
					size : 10,
					title:title
				},
				success : function(data) {
					if (data.code < 0) {
						$("#divshow").attr("style", "display:none");
						$("#pager").attr("style", "display:none");
					} else {
						$("#divshow").attr("style", "display:block;")
						$("#pager").attr("style", "display:block");
						var listdata = {
							list : data.data
						}
						var html = template('Findlist', listdata);
						$("#Finddata").siblings().remove();
						$("#Finddata").after(html);
						// 分页
						title = title;
						pcount = data.maxRow;
						pindex = data.pageIndex;
						$("#pager")
								.html(pagination(pcount, pindex, 10, "pagelist"));
					}
					if($("#SelectAll").attr("checked")=="checked"){
						$("input[name=ch_All]").attr("checked",false);
					}
				},
				error : function() {
					
				}
			});
		},
		
		// 编辑状态
		edit : function(id) {
			ConfirmShow("确定要修改吗？", confrimEdit, id, "");
		}
}

function pagelist(index) {
	Find.bind(index);
}

function refresh() {
	// location.reload();
	Find.bind(1);
}

//编辑状态
function confrimEdit(id) {
	$.ajax({
		url : "/platform/find/UpdateStatus",
		type : "Post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			Dalert(data.desc, "", refresh);
			refresh();
		}
	});

}

//删除 id:图片id
function del(id) {
	$.ajax({
		type : "post",
		url : "/platform/find/deleteRecordById",
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

		}
	});
}


//修改排序
function setOrder(findid, ob) {
	var obtext = $("#" + ob).val();
	$.ajax({
		url : "/platform/find/UpdateSort",
		type : "Post",
		data : {
			'id' : findid,
			'sort' : obtext
		},
		dataType : "json",
		success : function(data) {
			Dalert(data.desc);
		},
		error : function() {
			Dalert("修改排序失败");
		}
	});
}




//删除多条信息 idList：信息id集合（格式：1，2，3，...）
function dellist(idList) {
	$.ajax({
		type : "post",
		url : "/platform/find/delList",
		dataType : "json",
		data : {
			idlist : idList
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", refresh);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	})
}