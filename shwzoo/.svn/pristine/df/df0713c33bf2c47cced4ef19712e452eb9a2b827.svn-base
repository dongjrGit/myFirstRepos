//搜索属性值管理
var pcount, pindex, psize = size_product;

// 绑定事件

var S_Value = {
	bind : function() {

		S_Value.getList(1);
	},
	unit : function() {

		$(".del").each(function() {
			$(this).bind("click", S_Value.del);
		});
		$(".addnew_button").first().bind("click", function() {
			$("#addnew_type").val("0");
			S_Value.typeChange();
			$("#addnew_unit").val("");
			$("#addnew_id").val("0");
			$("#addnew_sort").val("0");
			$("#addnew_tr").show();
		});
		$(".edit").each(function() {
			$(this).bind("click", S_Value.unit_shownew);
		})
		$("#addnew_type").bind("change", S_Value.typeChange);
		$("#addnew_submit").bind("click", S_Value.save);
	},
	unit_shownew : function() {
		var id = $(this).attr("data");
		var tds = $(this).parent().parent().find("td");
		var type = $(tds[0]).html() == "固定值" ? "0" : "1";
		var s_value = $(tds[1]).html();
		var s_unit = $(tds[2]).html();
		var sort = $(tds[3]).html();

		$("#addnew_id").val(id);
		$("#addnew_type").val(type);
		S_Value.typeChange();
		var html = "";
		if (type == "0") {
			var attrtype = $("#h_attrtype").val().match(
					new RegExp("[\?\&]" + "attrtype" + "=([^\&]+)", "i"))[1];
			if (attrtype == 2)
				s_value = s_value.split("-")[1];

			html = '<input type="text" id="addnew_value" value="' + s_value
					+ '" />';
		} else {
			var min_value = s_value.split("-")[0];
			var max_value = s_value.split("-")[1];
			html = '<input type="text" id="addnew_minvalue" value="'
					+ min_value
					+ '" />-<input type="text" id="addnew_maxvalue" value="'
					+ max_value + '" />';
		}
		$("#addnew_td").html(html);
		$("#addnew_unit").val(s_unit);
		$("#addnew_sort").val(sort);
		$("#addnew_tr").show();
	},
	typeChange : function() {
		var type = $("#addnew_type").val();
		var html = "";
		if (type == "0") {
			html = '<input type="text" id="addnew_value" />';
		} else {
			html = '<input type="text" id="addnew_minvalue" />-<input type="text" id="addnew_maxvalue" />';
		}
		$("#addnew_td").html(html);
	},
	del : function() {
		if (confirm("确定要删除吗？")) {
			var id = $(this).attr("data");
			$.ajax({
				url : "/platform/searchattr/deleteSearchValue",
				type : "Post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					Dalert(data.Desc);
					if (data.code == 0) {
						location.reload();
						Dalert(data.desc, "", refresh);
					} else {
						Dalert(data.desc);
					}
				}
			})
		}
	},
	save : function() {
		var attrtype = $("#h_attrtype").val().match(
				new RegExp("[\?\&]" + "attrtype" + "=([^\&]+)", "i"))[1];
		var id = $("#addnew_id").val();
		var type = $("#addnew_type").val();
		var s_value = "";
		var minval = "";
		var maxval = "";
		if (type == "0") {
			s_value = $("#addnew_value").val()
		} else {
			minval = parseFloat($("#addnew_minvalue").val());
			if (isNaN(minval)) {
				minval = "";
			}
			maxval = parseFloat($("#addnew_maxvalue").val());
			if (isNaN(maxval)) {
				maxval = "";
			}
			if (minval < 0 || minval >= maxval) {
				Dalert("区间值错误，请确保值为正数并且最小值小于最大值");
				return;
			}
			s_value = minval + "-" + maxval;
		}
		var s_unit = $("#addnew_unit").val();
		var attrid = $("#attrid").val();
		var sort = $("#addnew_sort").val();
		var specsid = $("#specsid").val();
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='addnew_submit']").hide();
		$.ajax({
			url : "/platform/searchattr/updateSearchValue",
			type : "Post",
			data : {
				"id" : id,
				"attrID" : attrid,
				"type" : type,
				"attrtype" : attrtype,
				"value" : s_value,
				"minval" : minval,
				"maxval" : maxval,
				"unit" : s_unit,
				"sort" : sort,
				"specsid" : specsid
			},
			dataType : "json",
			success : function(data) {
				Dalert(data.Desc);
				if (data.code == 0) {
					location.reload();
					Dalert(data.desc, "", refresh);
				} else {
					$("input[name='addnew_submit']").show();
					Dalert(data.desc);
				}
			}
		})
	},
	getList : function(index) {
		var attr = $("#attrid").val()

		$
				.ajax({
					url : "/platform/searchattr/querySearchValue",
					type : "Post",
					data : {
						"attrid" : attr,
						"page" : index,
						"size" : psize
					},
					dataType : "json",

					success : function(data) {
						if (data.code < 0) {
							Dalert(data.desc);

						} else {
							if (data.data.length > 0) {
								// 获取搜索属性值 名称部分
								for (var i = 0; i < data.data.length; i++) {
									data.data[i].value = data.data[i].value
											.split(',')[0];
								}
							}

							var listdata = {
								list : data.data
							}
							var html = template('svlist', listdata);
							$("#list_title").html(html);

							pcount = data.maxRow;
							pindex = data.pageIndex;
							// 分页
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"S_Value.getList"));
							S_Value.unit();
						}
					},
					error : function() {

					}
				})
	}
}
function refresh() {
	location.reload();
}