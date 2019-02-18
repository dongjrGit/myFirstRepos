/*抽奖活动*/
$(document).ready(function() {
	Template.bind(1);
	// 删除信息
	$("body").on("click",".delete", function() {
		var id = $(this).parent().find("input").val();
		ConfirmShow("确认删除吗？", del, id);

	});
	// 编辑
	$("body").on("click",".bjxx", function() {
		var id = $(this).parent().find("input").val();

		location.href = "/platform/controlpanel/lottery_param_edit?id=" + id;
	});
	// 添加跳转
	$("#addbtn").click(function() {
		location.href = "/platform/controlpanel/lottery_param_edit";
	});
	$("#searchBtn").click(function() {
		Template.bind(1);
	});
});
var pindex;
var pcount;
var psize = 10;
var Template = {
	bind : function(index) {
		$.ajax({
			url : "/platform/litteryrule/queryLotteryParams",
			type : "post",
			data : {},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					// alert(data.Desc);
					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					/*
					 * $("#trlist").parent().children().each(function() { if
					 * ($(this).attr('id') != "trlist") {
					 * this.parentNode.removeChild(this); } })
					 */
				} else {

					var listdata = {
						list : data.data
					}
					var html = template('datalist', listdata);
					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					/*
					 * $("#trlist").parent().children().each(function() { if
					 * ($(this).attr('id') != "trlist") {
					 * this.parentNode.removeChild(this); } })
					 */

					$("#trlist").after(html); // 分页 pcount = data.maxRow;
					/*
					 * pindex = data.pageIndex;
					 * 
					 * $("#pager").html( pagination(pcount, pindex, psize,
					 * "pagelist"));
					 */
				}
			},
			error : function() {
			}
		});
	}
}
/*
 * function pagelist(index) { Template.bind(index); }
 */

function del(id) {
	$.ajax({
		type : "post",
		url : "/platform/controlpanel/deleteParamById",
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
			// Dalert(e.statusText);
		}
	});
}

function refresh() {
	location.reload();
}

function backhref() {
	location.href = "/platform/controlpanel/showlotteryparam";
}
