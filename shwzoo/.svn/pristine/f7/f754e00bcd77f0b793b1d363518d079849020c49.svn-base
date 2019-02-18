//会员收藏列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var CollectList = {
	bind : function(index) {
		var title = $("#select_title").val();
	//	var pronumstr = $("#select_pronum").val();
		$.ajax(({
			type : "post",
			url : "/platform/membercollect/queryMemberColler",
			dataType : "json",
			data : {
				title : title,
				pageindex : index,
				pagesize : psize
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('collectlist', listdata);

					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#collectlist_title").parent().children().each(
							function() {
								if ($(this).attr('id') != "collectlist_title") {
									this.parentNode.removeChild(this);
								}
							})
					$("#collectlist_title").after(html);

					indexlog = index;
					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					// alert(pindex);
					$("#pager").html(
							pagination(pcount, pindex, psize,
									"collect_pagelist"));

					// 会员收藏删除
					$(".a_collectdelete").bind("click", membercollectdel);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	}
}
// 分页回调
function collect_pagelist(index) {
	if (indexlog != index) {
		CollectList.bind(index);
	}
}

// 会员收藏删除
function membercollectdel() {
	var collectID = $(this).parent().find("#hidden_collectid").val();
	$.ajax(({
		type : "post",
		url : "/platform/membercollect/deleteMemberCollectById",
		dataType : "json",
		data : {
			id : collectID
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				CollectList.bind(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {

		}
	}));
}