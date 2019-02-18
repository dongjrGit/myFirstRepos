$(document)
		.ready(
				function() {
					$("#searchtitle").bind("click", function() {

						Navigation.bind(1);
					});
					Navigation.bind(1);

					$("body").on("click",".set",
									function() {
										var id = $(this).attr("data-id");
										var ss = $(this).attr("data-status");
										$
												.ajax({
													url : "/platform/navigation/updateStatus",
													type : "Post",
													data : {
														'id' : id,
														'status' : ss
													},
													dataType : "json",
													success : function(data) {
														if (data.code < 0) {
															Dalert(data.desc);
														} else {

															/*
															 * var td_html = "";
															 * if (ss == "0") {
															 * td_html = "<span
															 * class='cxtt
															 * shenlan'>"+"编辑"+"</span>"+ "<span
															 * class='delete
															 * shenlan'>"+"删除"+"</span>"+"<span
															 * class='lvs'><a
															 * href='#'
															 * data-id=" + id + "'
															 * data-status'='0'>启用</a></span>"; }
															 * else { td_html = "<span
															 * class='lvs'><a
															 * href='#'
															 * data-id='" + id + "'
															 * data-status='1'>禁用</a></span>"; }
															 * $("#td_" + id)
															 * .html( td_html);
															 */
															var td_html = "";
															if (ss == 0) {
																td_html = "<span class='lvs'><a data-id="
																		+ id
																		+ " data-status='1' class='set'  href='javascript:void(0);'>启用</a></span>";
																$(
																		"#status_"
																				+ id)
																		.html(
																				"<span class='lvs'>禁用</span>");
															} else {
																td_html = "<span class='lvs'><a data-id="
																		+ id
																		+ " data-status='0' class='set'  href='javascript:void(0);'>禁用</a></span>";
																$(
																		"#status_"
																				+ id)
																		.html(
																				"<span class='lvs'>启用</span>");
															}
															$("#span_" + id)
																	.html(
																			td_html);
															// refresh();
														}
													},
													error : function() {
														Dalert("修改状态失败");
													}
												});

									});
					// 删除
					$("body").on("click",".delete", function() {
						var id = $(this).parent().parent().find("input").val();
						ConfirmShow("确认要删除信息吗？", del, id);

					});

					// 编辑
					$("body").on("click",".cxtt",
									function() {
										var id = $(this).parent().parent()
												.find("input").val();
										location.href = "/platform/navigation/showNavigationEdit?id="
												+ id;
									});

				})
var pcount, pindex, psize = 10;
var Navigation = {
	bind : function(index) {

		var title = $("#title").val();
		var status = $("#status option:selected").val();

		$.ajax({
			type : "post",
			url : "/platform/navigation/queryListByCriteria",
			dataType : "json",
			data : {
				page : index,
				size : psize,
				title : title,
				status : status
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
					var html = template('navigationlist', listdata);
					$("#navigationdata").siblings().remove();
					$("#navigationdata").after(html);
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
	Navigation.bind(index);
}

function refresh() {
	// location.reload();
	Navigation.bind(1);
}

// 删除 id:导航id
function del(id) {
	$.ajax({
		type : "post",
		url : "/platform/navigation/delete",
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
