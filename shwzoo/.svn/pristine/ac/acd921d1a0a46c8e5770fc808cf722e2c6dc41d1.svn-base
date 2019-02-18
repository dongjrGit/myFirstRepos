/*站内信*/
$(document).ready(
		function() {
			var userID = $("#userID").val();
			var type = 1;
			Message.bind(userID, 1, 1);
			// 删除信息
			$("body").on("click",".delete", function() {
				var id = $(this).parent().parent().find("input").val();
				ConfirmShow("确认要删除信息吗?", del, id)
			});
			// 查看信息
			$("body").on("click",".lookinfo",function() {
						var id = $(this).parent().parent().find("input").val();
						if (type = 1) {
							readMessage(id, false);
						}
						location.href = "/seller/message/showMessageInfo?id="
								+ id + "&uid=" + userID;
					});
			// 全选
			$("#selectAll").click(function() {
				 var select=$('input[name="chk_list"]');
				if (($("#selectAll").get(0).checked)) {
					 for (var i = 0; i < select.length; i++) {
						 select[i].checked=true;
					}
				} else {
					for (var i = 0; i < select.length; i++) {
						select[i].checked=false;
					}
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
					ConfirmShow("确认要删除选取的信息吗?", dellist, idList)
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
					readMessage(idList, true);
				} else {
					Dalert("还没有选取哦");
				}

			});
			// 未读消息
			$("#Tabone").click(function() {
				$("#read_all").show();
				Message.bind(userID, 1, 1);
			})
			// 已读消息
			$("#Tabtwo").click(function() {
				$("#read_all").hide();
				Message.bind(userID, 0, 1);
			})
			// 设置信息为已读 idList:信息id集合（1，2，3，...）
			function readMessage(idList, isFresh) {
				$.ajax({
					type : "post",
					url : "/seller/message/readMesList",
					dataType : "json",
					data : {
						ids : idList
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							if (isFresh == true) {
								refresh();
							}// Dalert(rsl.Desc);
						} else {
							Dalert(rsl.desc);
						}

					},
					error : function(e) {
						// Dalert(e.statusText);
					}
				})
			}
		});
// 操作标签
function selectTab(showContent, selfObj) {
	var tag = document.getElementById("tags").getElementsByTagName("li");
	var taglength = tag.length;
	for (i = 0; i < taglength; i++) {
		tag[i].className = "";
	}
	selfObj.parentNode.className = "selectTag";
}
// 删除信息
function del(id) {
	if (confirm('确定将此记录删除?')) {
		$.ajax({
			type : "post",
			url : "/seller/message/deleteMessage",
			dataType : "json",
			data : {
				id : id
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert(rsl.desc, "", refresh); // window.location.reload();
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {
				// Dalert(e.statusText);
			}
		})
	}
}
// 删除多条信息
function dellist(idList) {
	idList = idList.substring(0, idList.length - 1);
	$.ajax({
		type : "post",
		url : "/seller/message/deleteMesList",
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

var usrid = 0, stas = 0;
var Message = {
	bind : function(uID, sta, index) {
		$
				.ajax({
					url : "/seller/message/queryMessagesByUserIdCriteria",
					type : "Get",
					data : {
						userid : uID,
						status : sta,
						page : index,
						size : 10
					},
					dataType : "json",
					success : function(data) {
						if (data.code < 0) {
							$("#tagContent").attr("style", "display:none");
							$("#pager").attr("style", "display:none");
						} else {
							$("#tagContent").attr("style", "display:block");
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
							$("#pager").html(
									pagination(pcount, pindex, 10, "pagelist"));
						}
					},
					error : function() {
						$("#con_one_1")
								.html(
										"<div class='con_box1_img'></div><div class='con_box1_mesno'>您还没有收到消息哦~</div>");
					}
				});
	}
}
function pagelist(index) {
	Message.bind(usrid, stas, index);
}

function refresh() {
	location.reload();
}