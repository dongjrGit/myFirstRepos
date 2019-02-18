//会员列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var PointsList = {
	bind : function(index) {
		
		 //var buyername = $("#select_buyername").val();
		  var type=$("#select_type").val();
		  
		  var buyerid = $("#select_buyer").attr("data");
	        var begin = $("#select_begin").val();
	        var end = $("#select_end").val();
	        
		$.ajax(({
					type : "post",
					url : "/platform/memberPoints/getpointsList",
					dataType : "json",
					data : {
						pageindex: index,
						pagesize: psize,
						buyerid: buyerid,
						type:type,
					    starttime: begin, 
					    endtime: end 
					},
					success : function(rsl) {
						
						if (rsl.code == 0) {
							var listdata = {
								list : rsl.data
							}
							var html = template('goodconsultlist', listdata);

							// 翻页时删除表头以外的所有节点，避免after()方法重复加载
							$("#goodconsultlist_title")
									.parent()
									.children()
									.each(
											function() {
												if ($(this).attr('id') != "goodconsultlist_title") {
													this.parentNode
															.removeChild(this);
												}
											})
							$("#goodconsultlist_title").after(html);

							indexlog = index;
							pcount = rsl.maxRow;
							pindex = rsl.pageIndex;
							// alert(pindex);
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"goodconsult_pagelist"));

							/*// 查看回复详情
							$(".a_feedBackdetail").on("click",
									FeedBackDetail);

							// 删除反馈
							$(".a_feedBackdelete").on(
									"click",
									function() {
										var id = $(this).parent()
												.find("#hidden_feedBackid")
												.val();
										ConfirmShow("确定要删除吗？", feedBackdel,
												id);
									});*/
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
function goodconsult_pagelist(index) {
	if (indexlog != index) {
		PointsList.bind(index);
	}
}

//按钮事件绑定
$(function () {
  
    autoxl.bind("select_buyer", getBuyerStartwithName);
})

function getBuyerStartwithName(callback, event) {
	
    var name = $("#select_buyer").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/accounts/getUserName",
        type: "Post",
        data: { "name": name},
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('select_buyerlist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.desc);
            }
        }
    });
}


// 商品咨询删除
/*function feedBackdel(id) {
	$.ajax(({
		type : "post",
		url : "/platform/memberfeedback/delFeedBack",
		dataType : "json",
		data : {
			id : id
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				GoodConsultList.bind(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {

		}
	}));
};*/

/*
function readMes(id) {
	$.ajax({
		type : "post",
		url : "/platform/memberfeedback/updateStatus",
		dataType : "json",
		data : {
			id : id
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				// Dalert(rsl.Desc,"",refresh);
			} else {
				// Dalert(rsl.Desc);
			}
		},
		error : function(e) {

		}
	})
}
*/


// 截取字符串，多余的部分用...代替
function setString(str, len) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	var strlen = 0;
	var s = "";
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 128) {
			strlen += 2;
		} else {
			strlen++;
		}
		s += str.charAt(i);
		if (strlen >= len) {
			return s + "...";
		}
	}
	return s;
}