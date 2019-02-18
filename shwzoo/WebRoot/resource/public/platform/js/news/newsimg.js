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
		var province=$("#select_province").val();
		var city=$("#select_city").val();
		var area=$("#select_area").val();
		$.ajax({
			url : "/platform/news/querynewsimg",
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
				"state":state,
				"province":province,
				"city":city,
				"area":area
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
				url : "/platform/news/delnewsimg",
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

function add(){
	var type=$("#type").val();
	var ctype=$("#ctype").val();
	window.location.href="/platform/news/newsimgedit?type="+type+"&ctype="+ctype;
}

function setstate(id,val){
	$.ajax({
		url:"/platform/news/setimgstate",
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

function sethead(id,val){
	$.ajax({
		url:"/platform/news/setimghead",
		type:"post",
		data:{"id":id,"ishead":val},
		dataType:"json",
		success:function(data){
			if(data.code<0){
				Dalert(data.desc);
			}else{
				Dalert("保存成功！");
				if(val==0){
					$("#tt_"+id).html( '<a data-id="'+id+'" data-status="1" onclick="sethead('+id+',1)" class="set" href="javascript:void(0);">取消头条</a>');
				}else {
					$("#tt_"+id).html( '<a data-id="'+id+'" data-status="1" onclick="sethead('+id+',0)" class="set" href="javascript:void(0);">设置头条</a>');
				}
			}
		 }
	});
}

function setindex(id,val){
	$.ajax({
		url:"/platform/news/setimgindex",
		type:"post",
		data:{"id":id,"isindex":val},
		dataType:"json",
		success:function(data){
			if(data.code<0){
				Dalert(data.desc);
			}else{
				Dalert("保存成功！");
				if(val==0){
					$("#sy_"+id).html( '<a data-id="'+id+'" data-status="1" onclick="setindex('+id+',1)" class="set" href="javascript:void(0);">取消首页</a>');
				}else {
					$("#sy_"+id).html( '<a data-id="'+id+'" data-status="1" onclick="setindex('+id+',0)" class="set" href="javascript:void(0);">设置首页</a>');
				}
			}
		 }
	});
}

function setrecommend(id,val){
	$.ajax({
		url:"/platform/news/setimgrecommend",
		type:"post",
		data:{"id":id,"isrecommend":val},
		dataType:"json",
		success:function(data){
			if(data.code<0){
				Dalert(data.desc);
			}else{
				Dalert("保存成功！");
				if(val==0){
					$("#tj_"+id).html( '<a data-id="'+id+'" data-status="1" onclick="setrecommend('+id+',1)" class="set" href="javascript:void(0);">取消推荐</a>');
				}else {
					$("#tj_"+id).html( '<a data-id="'+id+'" data-status="1" onclick="setrecommend('+id+',0)" class="set" href="javascript:void(0);">设置推荐</a>');
				}
			}
		 }
	});
}

function settop(id,val){
	$.ajax({
		url:"/platform/news/setimgtop",
		type:"post",
		data:{"id":id,"istop":val},
		dataType:"json",
		success:function(data){
			if(data.code<0){
				Dalert(data.desc);
			}else{
				Dalert("保存成功！");
				if(val==0){
					$("#zd_"+id).html( '<a data-id="'+id+'" data-status="1" onclick="settop('+id+',1)" class="set" href="javascript:void(0);">取消置顶</a>');
				}else {
					$("#zd_"+id).html( '<a data-id="'+id+'" data-status="1" onclick="settop('+id+',0)" class="set" href="javascript:void(0);">设置置顶</a>');
				}
			}
		 }
	});
}


//所在地数据绑定
function GetRegionData(rType, pCode) {
	$.ajax(({
		type : "post",
		url : "/platform/news/queryRegion",
		dataType : "json",
		async : false,
		data : {
			type : rType,
			code : pCode
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var listdata = {
					list : rsl.data
				};
				var html;
				switch (rType) {
				case 0:
					html = template('proviceselect', listdata);
					$("#select_province").append(html);
					break;
				case 1:
					html = template('cityselect', listdata);
					$("#select_city").append(html);
					break;
				case 2:
					html = template('areaselect', listdata);
					$("#select_area").append(html);
					break;
				default:
					break;
				}

			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	}));
}

function BindRegion() {
	// 省数据绑定
	GetRegionData(0, "");

	// 省改变
	$("#select_province").change(function() {
		var p1 = $(this).children('option:selected').val();
		$.ajax(({
			type : "post",
			url : "/platform/news/queryRegion",
			dataType : "json",
			data : {
				type : 1,
				code : p1
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('cityselect', listdata);
					$("#select_city option[value!='-1']").remove();
					$("#select_area option[value!='-1']").remove();
					$("#select_city").append(html);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	})

	// 市改变
	$("#select_city").change(function() {
		var p1 = $(this).children('option:selected').val();
		$.ajax(({
			type : "post",
			url : "/platform/news/queryRegion",
			dataType : "json",
			data : {
				type : 2,
				code : p1
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('areaselect', listdata);
					$("#select_area option[value!='-1']").remove();
					$("#select_area").append(html);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	})

}


