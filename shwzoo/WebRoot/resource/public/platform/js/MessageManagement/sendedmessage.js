/*信息管理-发送站内信管理-已发送站内信*/
$(document).ready(function() {
	// 列表以及分页数据绑定
	Message.bind(1);

	// 全选或者取消全选
	$("#SelectAll").click(function() {
		if ($('#SelectAll').prop('checked')) {
			$("input[name=chk_list]").prop("checked", true);
		} else {
			$("input[name=chk_list]").prop("checked", false);
		}
	});
	// 删除一条信息
	$("body").on("click",".deleteMes", function() {
		var id = $(this).parent().find("input").val();
		ConfirmShow("确认要删除信息吗？", del, id)

	})
	// 删除全部勾选信息
	$("#delAll").click(function() {
		var idList = "";
		$('input[name="chk_list"]:checked').each(function() {
			var id = $(this).val();
			idList += id + ",";
		});
		if (idList != "") {
			idList = idList.substring(0, idList.length - 1);
			ConfirmShow("确认要删除选取信息吗？", deleteAll, idList);
		} else {
			Dalert("还没有选取哦");
		}

	});
	// 搜索
	$("#searchBtn").click(function() {
		var st = $("#stime").val();
		var et = $("#etime").val();
		Message.bind(1, st, et);
	});
})
var pcount;
var pindex;
var stime, etime;
var psize = 10;
var Message = {
	// index:起始页，st：开始时间，et：结束时间
	bind : function(index, st, et) {
		var rname = $("#rName").val();
		$.ajax(({
			type : "post",
			url : "/platform/message/querySendMesByCriteria",
			dataType : "json",
			data : {
				page : index,
				size : psize,
				startTime : st,
				endTime : et,
				rName : rname,
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('memberlist', listdata);

					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#memberlist_title").parent().children().each(function() {
						if ($(this).attr('id') != "memberlist_title") {
							this.parentNode.removeChild(this);
						}
					})
					$("#memberlist_title").after(html);
					// 全选框取消选中
					$("#selectall").attr('checked', false);

					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					stime = st;
					etime = et;
					$("#pager")
							.html(
									pagination(pcount, pindex, psize,
											"member_pagelist"));
					// 查看站内信
					$(".sendmes").bind("click", readinfo);
				} else {
					// Dalert(rsl.Desc);
				}
			},
			error : function(e) {
				// Dalert(e.statusText);
			}
		}));
	}
}
// 分页回调
function member_pagelist(index) {
	Message.bind(index, stime, etime);

}
// 删除多条信息记录 idList:信息id集合
function deleteAll(idList) {
	$.ajax({
		type : "post",
		url : "/platform/message/bacthDeleteRecords",
		dataType : "json",
		data : {
			ids : idList
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
	})
}

// 查看站内信
function readinfo() {
	var memberID = $(this).parent().find("input").val();
	$(".sendmes").attr(
			'href',
			"/platform/message/showSendMesInfo?receiveid=" + memberID
					+ "&&rd=true");
};

function refresh() {
	Message.bind(1);
}
// 删除信息记录 id:信息id
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
				// window.location.reload();
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	})
}