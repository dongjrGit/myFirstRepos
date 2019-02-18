var pcount, pindex, psize = 10;
var solist = {
	// 获取订单日数据
	bind1 : function(index) {
		var number = $("#text_ordernum").val();
		var paynum = $("#text_paynum").val();
		var type = $("#select_status option:selected").val();
		if (parseInt(type) < 0) {
			type = "";
		}
		var timef = $("#time").val();
		var timet = $("#time1").val();
		$.ajax({
			url : "/seller/zhglshop/queryFinanceList",
			type : "Post",
			data : {
				"page" : index,
				"size" : psize,
				"type" : type,
				"number" : number,
				"paynum" : paynum,
				"datef" : timef,
				"datet" : timet
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('ddlist', listdata);
					// html 填充
					$("#dd_title").html(html);
					// 加载table样式：改变偶数行背景色，鼠标移动时背景色
					// init();
					pcount = data.maxRow;
					pindex = data.pageIndex;
					// 分页
					$("#pager").html(
							pagination(pcount, pindex, psize, "pagelist1"));
				}
			},
			error : function() {

			}
		});

	}
}

function pagelist1(index) {
	solist.bind1(index);
}

$(function() {
	$("input[name=search]").click(search);
})

function search() {
	solist.bind1(1);
}