//直营商品管理
var pcount, pindex, psize = 20;
var SPU = {
	bind : function() {
		SPU.getFirstClass();
		$("input[name='select_button']").first().bind("click", function() {
			SPU.getSPUList(1, SPU.unit);
		});
		var fc=GetQueryStringByName("fc");
		var tc=GetQueryStringByName("tc");
		var sc=GetQueryStringByName("sc");
		var status=GetQueryStringByName("status");
		if ( fc != '') {
			$("#fc_select").val(fc);
			SPU.fatherChange("fc");
			$("#sc_select").val(sc);
			SPU.fatherChange("sc");
			$("#tc_select").val(tc);
		}
		$("#status_select").val(status);
	},
	getFirstClass : function() {
		$
				.ajax({
					url : "/platform/commodity/GetClassByFatherID",
					type : "Post",
					data : {
						'fatherid' : 0
					},
					dataType : "json",

			           cache:false,
			           async:false,
					success : function(data) {
						if (data.code < 0) {
							Dalert(data.desc);
						} else {
							var listdata = {
								list : data.data
							}
							var html = '<option value="" id="defaultfc" selected>全部</option>'
									+ template('flist', listdata);
							$("#fc_select").html(html);
						}
					},
					error : function() {

					}
				});
	},
	fatherChange : function(fc) {
		var fid = 0;
		if (fc != null) {
			if (fc == "fc") {
				fid = $("#fc_select").val();
			} else {
				fid = $("#sc_select").val();
			}
		}
		if (fid > 0) {
			$
					.ajax({
						url : "/platform/commodity/GetClassByFatherID",
						type : "Post",
						data : {
							"fatherid" : fid
						},
						dataType : "json",
						 cache:false,
				           async:false,
						success : function(data) {
							if (data.code < 0) {
								Dalert(data.desc);
							} else {
								var listdata = {
									list : data.data
								}
								if (fc == "fc") {
									var html = '<option value="" id="defaultsc" selected>全部</option>'
											+ template('slist', listdata);
									$("#sc_select").html(html);
								} else {
									var html = '<option value="" id="defaulttc" selected>全部</option>'
											+ template('tlist', listdata);
									$("#tc_select").html(html);
								}
							}
						}
					})
		} else {
			if (fc == "fc") {
				$("#sc_select").html(
						'<option value="" id="defaultsc" selected>全部</option>');
				$("#tc_select").html(
						'<option value="" id="defaulttc" selected>全部</option>');
			} else {
				$("#tc_select").html(
						'<option value="" id="defaulttc" selected>全部</option>');
			}
		}
	},
	getSPUList : function(index, callback) {
		var cid = $("#tc_select").val();
		if (cid == "" || cid == undefined) {
			cid = $("#sc_select").val();
			if (cid == "" || cid == undefined) {
				cid = $("#fc_select").val();
			}
		}
		var name = $("#name_select").val();
		var num = $("#num_select").val();
		var status = $("#status_select").val();
		$
				.ajax({
					url : "/platform/procuctajax/p_getregularlist",
					type : "Post",
					data : {
						"cid" : cid,
						"status" : status,
						"page" : index,
						"size" : psize,
						"name" : name,
						"num" : num
					},
					dataType : "json",
					success : function(data) {
						if (data.code < 0) {
							Dalert(data.desc);
						} else {
							var listdata = {
								list : data.data
							}
							var html = template('spulist', listdata);
							$("#list_title").html(html);
							pcount = data.maxRow;
							pindex = data.pageIndex;
							// 分页
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"pagelist"));
							if (callback) {
								callback();
							}
							// //状态记录绑定
							// $(".l_xsztdj").bind("mouseover", function () {
							// var spuid = $(this).attr("data-id");
							// $.ajax({
							// url: "/Product/GetStatusRecords",
							// type: "Post",
							// data: { "spuid": spuid, "index": 1, "size":
							// size_prostatus },
							// dataType: "json",
							// success: function (data) {
							// Dalert(data.Desc);
							// if (data.Code == 0) {
							// var rehtml = "";
							// for (var i = 0; i < data.Data.length ; i++) {
							// rehtml += "<li>";
							// rehtml += "商品状态：";
							// if (data.Data[i].NewValue == 1) rehtml +=
							// "提交审核中";
							// else if (data.Data[i].NewValue == 2 ||
							// data.Data[i].NewValue == 4) rehtml += "下架";
							// else if (data.Data[i].NewValue == 3) rehtml +=
							// "上架";
							// else if (data.Data[i].NewValue == 5) rehtml +=
							// "冻结";
							// else if (data.Data[i].NewValue == 6) rehtml +=
							// "解冻申请中";
							// rehtml += "</li>";

							// rehtml += "<li>操作时间：" + data.Data[i].CreateTime +
							// "</li>";
							// rehtml += "<li>操作人：" +
							// data.Data[i].CreateUserName;
							// rehtml += "</li>";
							// }
							// $(".l_xszt ul").html(rehtml);
							// if (data.Data.length > 0)
							// $("#divrecord_" + spuid).show();
							// }
							// }
							// })
							// });
							// $(".l_xsztdj").bind("mouseout", function () {
							// $(".l_xsztcon").hide(); });

							$(document).bind('click', function() {
								$('.l_xsztcon').css('display', 'none');
							});

							$('.l_xsztdj')
									.bind(
											'click',
											function(e) {
												// 鼠标点击上一个div没有关闭
												if ($('.l_xsztcon').css(
														'display', 'block')) {
													$('.l_xsztcon').css(
															'display', 'none');
												}
												var spuid = $(this).attr(
														"data-id");
												$
														.ajax({
															url : "/Product/GetStatusRecords",
															type : "Post",
															data : {
																"spuid" : spuid,
																"index" : 1,
																"size" : psize
															},
															dataType : "json",
															success : function(
																	data) {
																Dalert(data.desc);
																if (data.code == 0) {
																	var rehtml = "";
																	for (var i = 0; i < data.data.length; i++) {
																		rehtml += "<li>";
																		rehtml += "商品状态：";
																		if (data.Data[i].newValue == 1)
																			rehtml += "提交审核中";
																		else if (data.data[i].newValue == 2
																				|| data.data[i].newValue == 4)
																			rehtml += "下架";
																		else if (data.data[i].newValue == 3)
																			rehtml += "上架";
																		else if (data.data[i].newValue == 5)
																			rehtml += "冻结";
																		else if (data.data[i].newValue == 6)
																			rehtml += "解冻申请中";
																		rehtml += "</li>";

																		rehtml += "<li>操作时间："
																				+ data.data[i].createTime
																				+ "</li>";
																		rehtml += "<li>操作人："
																				+ data.data[i].createUserName;
																		rehtml += "</li>";
																	}
																	$(
																			".l_xszt ul")
																			.html(
																					rehtml);
																	if (data.data.length > 0)
																		$(
																				"#divrecord_"
																						+ spuid)
																				.show();
																}
															}
														})
												stopPropagation(e);
											});
						}
					}
				})
	},
	callback : function(fc) {
		SPU.fatherChange(SPU.callback, fc);
	},
	unit : function() {
		$(".del").each(function() {
			$(this).bind("click", SPU.del);
		});
		$("a[name='shelve']").each(function() {
			$(this).bind("click", SPU.shelve);
		});
	},
	del : function() {
		if (confirm("确定要删除吗？")) {
			var id = $(this).attr("data");
			$.ajax({
				url : "/platform/spu/deletDirect",
				type : "Post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						Dalert(data.desc, 1000);
						pagelist(pindex);
					} else {
						Dalert(data.desc);
					}
				}
			})
		}
	},
	shelve : function() {
		var $obj = $(this);
		var id = $obj.attr("data");
		var type = $obj.attr("data_type");
		var action = "上架";
		if (type == 4) {
			action = "下架";
		}
		if (confirm("您确定要" + action + "吗？")) {
			$.ajax({
				url : "/platform/spu/zyshelve",
				type : "Post",
				data : {
					"id" : id,
					"status" : type
				},
				dataType : "json",
				success : function(data) {
					if (data.code == 0) {
						Dalert(action + "成功", 1000);
						ChangeStatus(type, $obj);
						
					} else {
						Dalert(action + "失败");
					}
				}
			})
		}
	}
}

function ChangeStatus(type, $obj) {
	var $prev = $obj.parent().prev();
	switch (parseInt(type)) {
	case 3:
		$obj.html("<span class='shenlan'>下架</span>"); // 已下架
		$obj.attr("data_type", 4);
		$prev.find("span").first().html("已上架");
		// $prev.html("已上架");
		break;
	case 4:
		$obj.html("<span class='shenlan'>上架</span>"); // 已下架
		$obj.attr("data_type", 3);
		$prev.find("span").first().html("已下架");
		// $prev.html("已下架");
		break;

	}
}
function pagelist(index) {
	SPU.getSPUList(index, SPU.unit);
}
function refresh() {
	location.reload();
}
// 对div的click事件绑定事件处理程序，阻止事件冒泡，防止其冒泡到document，而调用document的onclick方法隐藏了该div。
function stopPropagation(e) {
	if (e.stopPropagation)
		e.stopPropagation();
	else
		e.cancelBubble = true;
}
