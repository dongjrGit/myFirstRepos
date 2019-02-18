//店铺商品列表
var pcount, pindex, psize = size_product;
var SPU = {
	bind : function() {
		//SPU.getFirstClass();
		autoxl.bind("select_shop", SPU.getShopStartwithName, true);
		$("input[name='select_button']").first().bind("click", function() {
			SPU.getSPUList(1, SPU.unit);
		});
	},
	getFirstClass : function() {
		$.ajax({
					url : "/platform/commodity/GetClassByFatherID",
					type : "Post",
					data : {
						'fatherid' : 0
					},
					dataType : "json",
					success : function(data) {
						if (data.code < 0) {
							Dalert(data.desc);
						} else {
							var listdata = {
								list : data.data
							}
							// var html = template('flist', listdata);
							var html = '<option value="" id="defaultfc" selected>全部</option>'
									+ template('flist', listdata);
							$("#fc_select").html(html);
							// $("#sc_select").html('<option value=""
							// id="defaultsc" selected>全部</option>');
							// $("#tc_select").html('<option value=""
							// id="defaulttc" selected>全部</option>');
						}
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
	/*
	 * 获取到spu的列表
	 */
	getSPUList : function(index, callback) {
		var name = $("#name_select").val();
		var num = $("#num_select").val();
		var sid = $("#select_shop").attr("data");
		var status = $("#status_select").val();
		var isoffer = $("#isoffer").val();
		$
				.ajax({
					url : "/platform/procuctajax/p_getshopList",
					type : "Post",
					data : {
						"sid" : sid,
						"status" : status,
						"page" : index,
						"size" : psize,
						"name" : name,
						"num" : num,
						"isoffer" : isoffer
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
								$("#list_title").find("a").each(function(e) {
									var $obj = $(this);
									if ($obj.attr("data-spuid")) {
										$obj.bind("click", spdj);
									}
								});
							}
							// 状态记录绑定
							$(document).bind('click', function() {
								$('.l_xsztcon').css('display', 'none');
							});
						}
					},
					error : function() {

					}
				})
	},
	// callback: function (fc) {
	// SPU.fatherChange(SPU.callback,fc);
	// },
	unit : function() {
		$(".del").each(function() {
			$(this).bind("click", SPU.del);
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
	/*
	 * callback 成功 有数据时 调的方法 event 事件
	 */
	getShopStartwithName : function(callback, event) {
		var name = $("#select_shop").val();
	
		if (event)
			name += String.fromCharCode(event.keyCode);
		$.ajax({
			url : "/platform/shop/getShopStartWithName",
			type : "Post",
			data : {
				"name" : name
			},
			dataType : "json",
			success : function(data) {

				if (data.code == 0) {
					var listdata = {
						list : data.data
					}
					var html = template('select_shoplist', listdata);

					if (callback) {
						callback(html);
					}
				} else {
					Dalert(data.desc);
				}
			}
		});
	}
}
// 上架或下架商品
function spdj() {
	var $obj = $(this);
	var spid = $obj.attr("data-spuid");
	var type = $obj.attr("data-stauts");
	var spaction = "下架";
	if (parseInt(type) == 4) {
		spaction = "下架";
	}else{
		spaction = "上架";
		}
	
	if (confirm("您确定要" + spaction + "该商品吗？")) {
		$.ajax({
			url : "/platform/spu/shelve",
			type : "Post",
			data : {
				"id" : spid,
				"status" : type
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert(data.desc, 1000);
					ChangeStatus(type, $obj);// data.Status
					$("#jxdj_"+spid).hide();
				} else {
					Dalert(data.desc);
				}
			}
		})
	}
}
// 商品上下架
function ChangeStatus(type, $obj) {
	var $prev = $obj.parent().prev();
	switch (parseInt(type)) {
	case 3:
		$obj.html("<span class='shenlan'>下架</span>");
		$obj.attr("data-stauts", 4);
		// $prev.html("已冻结");
		$prev.find("span").first().html("已上架");
		break;
	case 4:
		$obj.html("<span class='shenlan'>上架</span>");
		$obj.attr("data-stauts", 3);
		// $prev.html("已下架");
		$prev.find("span").first().html("已下架");
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