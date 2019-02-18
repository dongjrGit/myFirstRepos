//会员列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var MessageList = {
	bind : function(index) {
		var name = $("#select_name").val();
		var sendname = $("#select_sendname").val();
		var mobile = $("#select_mobile").val();
		var begin = $("#select_begin").val();
		var end = $("#select_end").val();
		$.ajax(({
			type : "post",
			url : "/platform/message/querySendMesMobileByCriteria",
			dataType : "json",
			data : {
				page : index,
				size : psize,
				"rName" : name,
				"sendname" : sendname,
				"telNum" : mobile,
				"startTime" : begin,
				"endTime" : end,
				type : 0
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('messagelist', listdata);

					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#messagelist_title").parent().children().each(
							function() {
								if ($(this).attr('id') != "messagelist_title") {
									this.parentNode.removeChild(this);
								}
							})
					$("#messagelist_title").after(html);

					indexlog = index;
					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					// Dalert(pindex);
					$("#pager").html(
							pagination(pcount, pindex, psize,
									"message_pagelist"));
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {
				Dalert(e.statusText);
			}
		}));
	}
}
// 分页回调
function message_pagelist(index) {
	if (indexlog != index) {
		MessageList.bind(index);
	}
}