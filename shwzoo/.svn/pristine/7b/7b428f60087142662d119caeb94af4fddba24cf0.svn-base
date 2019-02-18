//获取专题商品列表
var topicid = "";
var list = {
	bind : function() {
		$("#orderby").find("li").bind("click", function() {
			var $li = $(this);
			if ($li.attr("isselect") == "1") {
				if ($li.attr("data_tag") == "0") {
					$li.attr("data_tag", "1");
					$li.addClass("active");
				} else {
					$li.attr("data_tag", "0");
					$li.removeClass("active");
				}
			} else {
				$li.parent().find("li").each(function() {
					$(this).attr("isselect", "0");
					$(this).removeClass("l_zhss");
				})
				$li.attr("isselect", "1");
				$li.addClass("l_zhss");
			}
			list.getlist(1);
		})
	},
	getlist : function(index) {
		topicid=$("#h_tpid").val()
		var $select_order = $("#orderby").find("li[isselect='1']").first();
		var orderby = $select_order.attr("data");
		var orderType = $select_order.attr("data_tag");
		var pcount;
		var pindex;
		var psize = 20;
		var datahtml = "";
		$.ajax({
			url : "/pc/products/gettopicprolist",
			type : "Post",
			data : { 
				"topicid" : topicid,
				"page" : index,
				"size" : psize,
				"ch" : 1,
				"orderby" : orderby,
				"ordertype" : orderType,
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('pro_list', listdata);
					$("#productlist").html(html);

					pcount = data.maxRow;
					pindex = data.pageIndex;
					$("#pro_count").html(pcount);
					list.ImgMove();
					// 分页
					$("#pager").html(pagination(pcount, pindex, psize, "list.getlist"));
					// 绑定关注事件
					$("body").on("click", ".l_xtbgz", function() {
						var spuid = $(this).attr("data-id");
						if (WebLogin.isLogin()) {
							list.addConcernt(spuid);
						} else {
							showlogindiv();
						}

					});
				}
			},
			error : function() {
			}
		});
	},
	checkValueSelect : function() {
		var $li = $(this);
		var tag = $li.attr("data_tag");
		if (tag == "0") {
			if ($li.parent().attr("data") == "False") {
				$li.parent().children("li").each(function() {
					if ($li != $(this)) {
						$(this).attr("data_tag", "0");
						$(this).removeAttr("style");
					}
				})
			}
			$li.attr("data_tag", "1");
			$li.css("color", "red");
		} else {
			$li.attr("data_tag", "0");
			$li.removeAttr("style");
		}
		list.getlist(1);
	},
	ImgMove : function() {
		$(".l_xdimg").click(function() {
			var spuid = $(this).attr("data-id");
			$("#" + spuid).attr("src", $(this).attr("src"));
		});
	},
	// 添加商品关注
	addConcernt : function(spuid) {
		$.ajax({
			type : "post",
			url : "/pc/products/collectbuySpu",
			dataType : "json",
			data : {
				"spuid" : spuid,
				"ch" : 1
			},
			success : function(rsl) {				 
					alert(rsl.desc);
			},
			error : function(e) {
				// alert(e.statusText);
			}
		});
	}
	
}

$(document).ready(function() {
	// var pcount = $("#pro_total").val();
	// var pindex = $("#pro_index").val();
	// $("#pager").html(pagination(pcount, pindex, 20, "list.getlist"));	
	$("body").on("click", ".l_xtbgz", function() {
		var spuid = $(this).attr("data-id");
		if (WebLogin.isLogin()) {
			list.addConcernt(spuid);
		} else {
			showlogindiv();
		}

	});
})

function changeTwoDecimal_f(x) {
	// debugger;
	var f_x = parseFloat(x);
	var f_x = Math.round(x * 100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2) {
		s_x += '0';
	}
	return s_x;
}