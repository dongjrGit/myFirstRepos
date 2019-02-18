//商品专题列表
var topicid;
var fatherid;
var spmark;
$(document)
		.ready(
				function() {
					// 关闭弹出层事件
					$(".l_close").bind("click", function() {
						$("#bigimg").hide();
					});
					$("#bigimg img").bind("click", Jump);
					// 列表
					ProSpecial.getAll(1);
					// 发现id
					findid = GetQueryStringByName("findid");
					var type = $("#type").val();
					
					/*
					 * $("span[name=editpf]").live("click", function () { var id =
					 * $(this).attr("data_id"); var proid =
					 * $(this).attr("data_pro"); location.href =
					 * "/platform/topic/showProEdit?id=" + id + "&proid=" +
					 * proid + "&spid=" + flid; });+"&type="+type
					 */
					// 添加
					$("input[name=add]")
							.click(
									function() {

										location.href = "/platform/find/showFindEdit?findid="
												+ findid + "&type="+type;
									});
					/*// 返回
					$("input[name=back]")
							
							.click(
									function() {
										location.href = "/platform/find/findRecord_list";

									});*/
					// 查询
					$("#spsearch").click(function() {
						ProSpecial.getAll(1);
					});
				})
var pcount;
var pindex=1;
var psize = 10;
var ProSpecial = {
	getAll : function(index) {
		var findid = GetQueryStringByName("findid");
		var type = $("#type").val();
		$.ajax({
					url : "/platform/find/queryFindRelate",
					type : "Get",
					data : {
						"page" : index,
						"size" : psize,
						"findid" : findid,
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
			url : "/platform/find/delFindRelate",
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
			url : "/platform/find/updateFindRelationSort",
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


function back(){
	window.location.href = "/platform/find/showFindRecord";
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
		url : "/platform/find/updateFindRelationSort",
		type : "Post",
		data : {
			"id" : id,
			"sort" : obtext
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