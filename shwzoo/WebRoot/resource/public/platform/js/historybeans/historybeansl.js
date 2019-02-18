//
var pcount, pindex, psize = 10;
var HB = {
	bind : function() {
		autoxl.bind("select_user", HB.getAllUserStartwithName); // 用户下拉框绑定

		$("#select_input").bind("click", function() {
			HB.getHbList(1);
		});

	},
	// 
	getHbList : function(index) {
		var userid = $("#select_user").attr("data");
		var begin = $("#select_begin").val();
		var end = $("#select_end").val();
		var status = -1;
		$.ajax({
			url : "/platform/beans/queryHistorybeans",
			type : "Post",
			data : {
				"page" : index,
				"size" : psize,
				"userid" : userid,
				"begin" : begin,
				"end" : end
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('slist', listdata);
					$("#list_title").html(html);
					pcount = data.maxRow;
					pindex = data.pageIndex;
					// 分页
					$("#pager").html(
							pagination(pcount, pindex, psize, "pagelist"));

				}
			}
		});
	},

	/*
	 * callback 成功 有数据时 调的方法 event 事件
	 */
	getAllUserStartwithName : function(callback, event) {
		var username = $("#select_user").val();
		if (event)
			username += String.fromCharCode(event.keyCode);
		$.ajax({
			url : "/platform/shop/queryUserByLikeName",
			type : "Post",
			data : {
				"name" : username,
				"usertype" : -1
			},
			dataType : "json",
			success : function(data) {

				if (data.code == 0) {
					var listdata = {
						list : data.data
					}
					var html = template('select_userlist', listdata);

					if (callback) {
						callback(html);
					}
				} else {
					Dalert(data.data);
				}
			}
		});
	},
}

// 分页回调函数
function pagelist(index) {
	HB.getHbList(index);
}
// 页面刷新
function refresh() {
	location.reload();
}

function deleteById(id) {
	$.ajax({
		url : "/platform/beans/delete",
		type : "Post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {

			if (data.code == 0) {
				Dalert(data.desc);
				HB.getHbList(1);
			} else {
				Dalert(data.desc);
			}
		}
	});
}