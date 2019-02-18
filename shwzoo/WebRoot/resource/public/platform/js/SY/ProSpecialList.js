//商品专题列表
var topicid;
var fatherid;
var spmark;
$(document).ready(
				function() {
					// 关闭弹出层事件
					$(".l_close").bind("click", function() {
						$("#bigimg").hide();
					});
					$("#bigimg img").bind("click", Jump);
					// 列表
					ProSpecial.getAll(1);
					// 主题id
					topicid = $("#topicid").val();
					spmark = $("#spmark").val();
					var type = $("#type").val();

					// 添加
					$("input[name=add]").click(function() {

						location.href = "/platform/topic/showProEdit?topicid="
									+ topicid + "&spmark=" + spmark+"&type="+type;
					});
					// 返回
					$("input[name=back]").click(function() {
										fatherid = $("#fatherid").val();
										
										if (fatherid != "") {
											location.href = "/platform/topic/showProList?topicid="
													+ fatherid + "&spmark=1"+"&type="+type+"&fatherid=";
										} else {
											location.href = "/platform/topic/list";
										}

					});
					// 查询
					$("#spsearch").click(function() {
						ProSpecial.getAll(1);
					});
				})
var pcount;
var pindex;
var psize = 10;
var ProSpecial = {
	getAll : function(index) {
		var spid = $("#topicid").val();
		var spmark = $("#spmark").val();
		//var dp = $("#fldisplay").val();
		//var ty = $("#prosptype").val();
		var type = $("#type").val();
		$.ajax({
					url : "/platform/topic/queryByTid",
					type : "Get",
					data : {
						"index" : index,
						"size" : psize,
						"tid" : spid,
						"spmark" : spmark,
						"type":type
					},
					dataType : "json",
					success : function(data) {
						if (data.code < 0) {
							// Dalert(data.Desc);
						} else {
							var listdata = {
								list : data.data
							}
							var html = template('SpecialProlist', listdata);

							// 翻页时删除表头以外的所有节点，避免after()方法重复加载
							$("#list_title")
									.parent()
									.children()
									.each(
											function() {
												if ($(this).attr('id') != "list_title") {
													this.parentNode
															.removeChild(this);
												}
											})
							// html 填充
							$("#list_title").after(html);

							psize = psize;
							pcount = data.maxRow;
							pindex = data.pageIndex;
							// 分页
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"pagelist"));
							// 点击图片查看大图
							$(".ppimg img")
									.each(
											function() {
												$(this)
														.click(
																function() {
																	if ($(this)
																			.attr(
																					"src") != ""
																			&& $(
																					this)
																					.attr(
																							"src") != undefined)
																		Show(
																				"bigimg",
																				$(
																						this)
																						.attr(
																								"src"));
																});
											});
						}
					},
					error : function() {

					}
				});
	},
	// 删除
	del : function(id) {
		$.ajax({
			url : "/platform/topic/delTopicR",
			type : "Post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(data) {
				Dalert(data.desc, "", refresh);
				// location.reload();
			}
		});
	},
	
	edit : function(id,status) {
		$.ajax({
			url : "/platform/topic/updateStatus",
			type : "Post",
			data : {
				"id" : id,
				"status":status
				
			},
			dataType : "json",
			success : function(data) {
				Dalert(data.desc, "", refresh);
				// location.reload();
			}
		});
	}
	
}
function Del(id) {
	ConfirmShow("确定要删除吗？", ProSpecial.del, id, "");
}


function refresh() {
	location.reload();
}
function pagelist(index) {
	ProSpecial.getAll(index);
}
// 点击大图跳转方法
function Jump() {
	var imgurl = $("#bigimg img").attr("src");
	var surl = "/Platform/img/ImageShow";
	ImageJump(imgurl, surl);
}
// 修改排序
function setValue(id, ob, tp) {
	var obtext = $("#" + ob).val();
	$.ajax({
		url : "/platform/topic/SetSpecialProValue",
		type : "Post",
		data : {
			"id" : id,
			"setValue" : obtext,
			"type" : tp
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