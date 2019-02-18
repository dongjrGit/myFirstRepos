/*站内信*/
$(document).ready(function() {
	var userID = $("#userID").val();
	var mtype = 1;
	// 搜索
	$("#searchmes").bind("click", function() {
		Message.bind(userID, mtype, 1);
	});
	Message.bind(userID, mtype, 1);
	// 删除信息
	$("body").on("click",".delete", function() {
		var id = $(this).parent().parent().find("input").val();
		ConfirmShow("确认要删除信息吗？", del, id);

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
	// 标记为已读
	$("#read_all").click(function() {
		var idList = "";
		$('input[name="chk_list"]:checked').each(function() {
			var id = $(this).val();
			idList += id + ",";
		});
		if (idList != "") {
			idList = idList.substring(0, idList.length - 1);
			readMes(idList);
			Message.bind(userID, 1, 1);
		} else {
			Dalert("还没有选取哦")
		}

	});
	// 未读消息
	$("#one1").click(function() {

		$("#one2").removeClass("sj_hover");
		$("#one2").html('<a href="javascript:void(0);">已读消息</a>');
		$("#one1").addClass("sj_hover");
		$("#one1").append("<span class='sj-img'></span>");
		$("#time").attr("value", "");
		$("#time1").attr("value", "");
		mtype = 1;
		Message.bind(userID, 1, 1);
		$("#read_all").attr("style", "display:inline-block");
	})
	// 编辑
	$("body").on("click",".bjxx", function() {
		var id = $(this).parent().parent().find("input").val();
		if (mtype == 1) {
			readMes(id);
		}
		location.href = "/platform/member/showReadMessage?id=" + id;
	});
	// 全选
	$("#SelectAll").click(function() {
		if ($("#SelectAll").prop('checked')) {
			$("input[name=chk_list]").prop("checked", true);
		} else {
			$("input[name=chk_list]").prop("checked", false);
		}
	})
	// 已读消息
	$("#one2").click(function() {
		$("#one1").removeClass("sj_hover");
		$("#one1").html('<a href="javascript:void(0);">新消息</a>');
		$("#one2").addClass("sj_hover");
		$("#one2").append("<span class='sj-img'></span>");
		$("#time").attr("value", "");
		$("#time1").attr("value", "");
		mtype = 0;
		Message.bind(userID, 0, 1);
		$("#read_all").attr("style", "display:none");
	})

	// $("table tr").live("click", function () {
	// if ($(this).find("input").val() == undefined) {
	// alert("这是标题")
	// }
	// else { alert($(this).find("input").val()+"号"); }
	// });
});

var usrid = 0, stas = 0;
var Message = {
	/* uID：用户id，sta：信息状态（0已读，1未读），index：起始页 */
	bind : function(uID, sta, index) {
		var df = $("#time").val();
		var dt = $("#time1").val();
		var sname = $("#sName").val();
		var usertype = 2;
		$.ajax({
			url : "/platform/message/queryMemberMessageByCriteria",
			type : "Get",
			data : {
				usertype : usertype,
				sendid : uID,
				status : sta,
				page : index,
				size : 10,
				datef : df,
				datet : dt,
				username : sname
			},
			dataType : "json",
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

					var html = template('messagelist', listdata);
					$("#Messagedata").siblings().remove();
					$("#Messagedata").after(html);
					// 分页
					usrid = uID;
					stas = sta;
					pcount = data.maxRow;
					pindex = data.pageIndex;
					$("#pager")
							.html(pagination(pcount, pindex, 10, "pagelist"));
				}
			},
			error : function() {

			}
		});
	}
}
function pagelist(index) {
	Message.bind(usrid, stas, index);
}
function refresh() {
	// location.reload();
	Message.bind(usrid, stas, 1);
}
// 删除 id:信息id
function del(id) {
	$.ajax({
		type : "post",
		url : "/platform/message/deleteRecordById",
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
// 删除多条信息 idList：信息id集合（格式：1，2，3，...）
function dellist(idList) {
	$.ajax({
		type : "post",
		url : "/platform/message/delList",
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
// 设置为已读idList：信息id集合（格式：1，2，3，...）
function readMes(idList) {
	$.ajax({
		type : "post",
		url : "/platform/message/readMes",
		dataType : "json",
		data : {
			idlist : idList
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				// Dalert(rsl.Desc,"",refresh);
			} else {
				// Dalert(rsl.Desc);
			}
		},
		error : function(e) {

		}
	})
}