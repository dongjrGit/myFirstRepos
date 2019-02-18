/*信息管理-发送站内信管理-发送站内信*/
$(document)
		.ready(
				function() {
					// 列表以及分页数据绑定
					MemberList.bind(1);

					// 全选或者取消全选
					$("#SelectAll").click(function() {
						if ($('#SelectAll').prop('checked')) {
							$("input[name=chk_list]").prop("checked", true);
						} else {
							$("input[name=chk_list]").prop("checked", false);
						}
					})
					// 发送站内信
					$("#sendMesList")
							.click(
									function() {
										var mList = "";
										$("input[name=chk_list]:checked").each(
												function() {
													mList += $(this).val()
															+ ",";
												});
										mList = mList.substring(0,
												mList.length - 1);
										if (mList != "" && mList != null) {
											location.href = "/platform/message/showSendMesInfo?receiveid="
													+ mList;
										} else {
											Dalert("请选择收信人");
										}
									});
					// 搜索
					$("#searchBtn").click(function() {
						var stime = $("#stime").val();
						var etime = $("#etime").val();
						MemberList.bind(1, stime, etime);
					})
					// 发送站内信
					$("body").on("click",".sendmes",
							function() {
								var memberID = $(this).parent().find("input")
										.val();
								$(".sendmes").attr(
										'href',
										"/platform/message/showSendMesInfo?receiveid="
												+ memberID);
							});

				})
var pcount;
var pindex;
var psize = 10;
var sTime;
var eTime;
var indexlog;
var MemberList = {
	// index:起始页，st：开始时间，et：结束时间
	bind : function(index, st, et) {
		var rname = $("#rName").val();
		var tel = $("#telnum").val();
		var utype = $("#userType option:selected").val();

		$.ajax(({
			type : "post",
			url : "/platform/message/queryMemberListByCriteria",
			dataType : "json",
			data : {
				page : index,
				size : psize,
				rName : rname,
				telNum : tel,
				startTime : st,
				endTime : et,
				uType : utype
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('memberlist', listdata);

					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#memberlist_title").parent().children().each(function() {
						if ($(this).attr('id') != "memberlist_title") {
							this.parentNode.removeChild(this);
						}
					})
					$("#memberlist_title").after(html);
					// 全选框取消选中
					$("#selectall").attr('checked', false);

					indexlog = index;
					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					sTime = st;
					eTime = et;

					$("#pager")
							.html(
									pagination(pcount, pindex, psize,
											"member_pagelist"));
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {
				// Dalert(e.statusText);
			}
		}));
	}
}
// 分页回调
function member_pagelist(index) {
	if (indexlog != index) {
		MemberList.bind(index, sTime, eTime);
	}
}
