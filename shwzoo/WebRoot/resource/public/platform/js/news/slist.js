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
		var classid1=$("#firstID").val();
		var classid2=$("#secondID").val();
		var classid3=$("#thirdID").val();
		var starttime=$("#select_begin").val();
		var endtime=$("#select_end").val();
		var ctype=$("#type").val();
		var state=$("#type_select").val();
		$.ajax({
			url : "/platform/news/querySlist",
			type : "Post",
			data : {
				"pageindex" : index,
				"pagesize" : psize,
				"title" : title,
				"classid1":classid1,
				"classid2":classid2,
				"classid3":classid3,
				"starttime":starttime,
				"endtime":endtime,
				"ctype":ctype,
				"state":state
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
				url : "/platform/news/delSlist",
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
	var ctype=$("#ctype").val();
	window.location.href="/platform/news/slistedit?type="+type+"&ctype="+ctype;
}

function setstate(id,val){
	$.ajax({
		url:"/platform/news/setstate",
		type:"post",
		data:{"id":id,"isstate":val},
		dataType:"json",
		success:function(data){
			if(data.code<0){
				Dalert(data.desc);
			}else{
				Dalert("保存成功！");
				if(val==0){
					$("#td_"+id).html( ' <span class="lvs"><a data-id="'+id+'" data-status="1" onclick="setstate('+id+',1)" class="set" href="javascript:void(0);">发布</a></span>');
				}else {
					$("#td_"+id).html( ' <span class="lvs"><a data-id="'+id+'" data-status="1" onclick="setstate('+id+',0)" class="set" href="javascript:void(0);">未发布</a></span>');
				}
			}
		 }
	});
}


