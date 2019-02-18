$(document).ready(function() {
	$("#searchtitle").bind("click", function() {
		announcement.bind(1);
	});
	announcement.bind(1);
	
	
	
	// 删除广告
	$("body").on("click",".delete", function() {
		var id = $(this).parent().parent().find("input").val();
		ConfirmShow("确认要删除信息吗？", del, id);

	});
	
	// 编辑
	$("body").on("click",".cxtt", function() {
		var id = $(this).parent().parent().find("input").val();
		location.href = "/platform/announcement/editannoun?id=" + id;
	});
	
	
	// 全选
	$("#SelectAll").click(function() {
		if ($(this).prop('checked')) {
			$("input[name=chk_list]").prop("checked", true);
		} else {
			$("input[name=chk_list]").prop("checked", false);
		}
	})
	
	
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
				url : "/platform/announcement/UpdateSortList",
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
});


var announcement = {
		bind : function(index) {
			var title = $("#sTitle").val();
			var begin=$("#time").val();
			var end=$("#time1").val();
			$.ajax({
				url : "/platform/announcement/queryannouncement",
				type : "Get",
				dataType : "json",
				data : {
					page : index,
					size : 10,
					begin:begin,
					end:end,
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
						var html = template('announcementlist', listdata);
						$("#Announcedata").siblings().remove();
						$("#Announcedata").after(html);
						// 分页
//						title = title;
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
		}
	}
	function pagelist(index) {
		announcement.bind(index);
	}

function refresh() {
	// location.reload();
	announcement.bind(1);
}






//删除 id:图片id
function del(id) {
	$.ajax({
		type : "post",
		url : "/platform/announcement/deleteannounById",
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


//删除多条信息 idList：信息id集合（格式：1，2，3，...）
function dellist(idList) {
	$.ajax({
		type : "post",
		url : "/platform/announcement/delList",
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


//修改排序
function setOrder(announid, ob) {
	var obtext = $("#" + ob).val();
	$.ajax({
		url : "/platform/announcement/UpdateSort",
		type : "Post",
		data : {
			'id' : announid,
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