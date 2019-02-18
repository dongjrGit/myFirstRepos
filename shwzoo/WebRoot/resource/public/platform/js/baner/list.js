//品牌管理
var pcount;
var pindex;
var psize = 10;
var Brand = {
	bind : function(callback) {
		$("input[name=select_button]").bind("click", Brand.getAll(1));
	},
	getAll : function(index) {
		var title = $("#name_select").val();
		var ctype=$("#type_select").val();
		var type=$("#type").val();
		$.ajax({
			url : "/platform/baner/getlist",
			type : "Post",
			data : {
				"pageindex" : index,
				"pagesize" : psize,
				"title" : title,
				"ctype":ctype,
				"type":type
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('brandlist', listdata);
					// $("#list_title").after(html);
					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#list_title").parent().children().each(function() {
						if ($(this).attr('id') != "list_title") {
							this.parentNode.removeChild(this);
						}
					})
					// html 填充
					$("#list_title").after(html);
					// 加载table样式：改变偶数行背景色，鼠标移动时背景色
					init();
					pcount = data.maxRow;
					pindex = data.pageIndex;
					// 分页
					$("#pager").html(pagination(pcount, pindex, psize, "Brand.getAll"));
				}
			},
			error : function() {

			}
		});
	},
	// 删除
	del : function(id) {
		if (confirm("确定要删除么？")) {
			$.ajax({
				url : "/platform/baner/delbaner",
				type : "Post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					Dalert(data.desc, "", refresh);
					// location.reload();
				}
			});
		}
	},
}

function refresh() {
	location.reload();
}

function back(){
	var type=$("#type").val();
	var site=$("#site").val();
	window.location.href="/platform/baner/edit?type="+type+"&site="+site;
}



