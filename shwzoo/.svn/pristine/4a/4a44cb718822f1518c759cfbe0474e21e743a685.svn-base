//专题管理
$(function() {
	// 关闭弹出层事件
	$(".l_close").bind("click", function() {
		$("#bigimg").hide();
	});
	$("#bigimg img").bind("click", Jump);
	// 获取列表
	Special.getAll(1);
	// 查询
	$("input[name=select_button]").click(function() {
		Special.getAll(1);
	});

});

var pindex, psize = 10, pcount = 0;
var Special = {
	getAll : function(index) {
		var na = $("#spname").val();
		var tp = $("#sptype").val();
		var dy = $("#spdisplay").val();
		var pg = $("#spmark").val();
		var issys=$("#issys").val();
		
				$.ajax({
					url : "/platform/topic/queryTopicListByCriteria",
					type : "Get",
					data : {
						"page" : index,
						"size" : psize,
						"title" : na,

						"type" : tp,

						"mark" : pg,
						
						"issys":issys

					},
					dataType : "json",
					success : function(data) {
						if (data.code < 0) {
							// Dalert(data.Desc);
						} else {
							var listdata = {
								list : data.data
							}
							var html = template('speciallist', listdata);

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
							pcount = data.maxRow;
							pindex = data.pageIndex;
							// 点击图片查看大图
							$(".ppimg img")
									.each(
											function() {
												$(this).click(
																function() {
																	if ($(this).attr("src") != ""&& $(this).attr("src") != undefined)Show("bigimg",$(this).attr("src"));});
											});
							// 分页
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"pagelist"));

						}
					},
					error : function() {

					}
				});
	},
	// 删除
	del : function(id) {
		ConfirmShow("确定要删除吗？", confrimDel, id, "");
	},
	// 编辑状态
	edit : function(id,status) {
		ConfirmShow("确定要修改吗？", confrimEdit, id, "");
	}
}

function confrimEdit(id) {
	$.ajax({
		url : "/platform/topic/updateStatus",
		type : "Post",
		data : {
			"id" : id,
			
		},
		dataType : "json",
		success : function(data) {
			Dalert(data.desc, "", refresh);
			refresh();
		}
	});

}

function confrimDel(id) {
	$.ajax({
		url : "/platform/topic/deleteById",
		type : "Post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			Dalert(data.desc, "", refresh);
		}
	});
	
}

function pagelist(index) {
	Special.getAll(index);
}
// 修改排序 tp=0位置，tp=1排序
function setSPValue(spid, selecter, tp) {
	var obtext = $("#" + selecter).val();
	$.ajax({
		url : "/HomeFloor/SetSPValue",
		type : "Post",
		data : {
			id : spid,
			setValue : obtext,
			type : tp
		},
		dataType : "json",
		success : function(data) {
			Dalert(data.desc);
		},
		error : function() {
			Dalert("修改失败");
		}
	});
}

function refresh() {
	location.reload();
}


// 点击大图跳转方法
function Jump() {
	var imgurl = $("#bigimg img").attr("src");
	var surl = "/Platform/img/ImageShow";
	ImageJump(imgurl, surl);
}