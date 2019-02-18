
var pindex,psize=10,pcount=0;
var data={
	bind:function(index){
		var title=$(".inp-seller").val();
		var type=$("#type_select").val();
		$.ajax({
			url:"/platform/hispe/getList",
			type:"post",
			data:{"pageindex":index,"pagesize":psize,"title":title,"type":type},
			dataType:"json",
			success:function(data){
				if(data.code<0){
					Dalert(data.desc);
				}else{
					pindex=index;
					var listdata={
							list:data.data
					}
					var html=template("tdatalist",listdata);
					
					//翻页时删除表头以外的所有节点，避免after()方法重复加载
//                    $("#buy_title").parent().children().each(function () {
//                        if ($(this).attr('id') != "buy_title") {
//                            this.parentNode.removeChild(this);
//                        }
//                    })
                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
					pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
				}
			},
			error:function(){}
		});
	},
}

function pagelist(index) {
	data.bind(index);
}


function refresh() {
	// location.reload();
	data.bind(pindex);
}

function save() {
	if(check().form()){
		$.ajax({
			url : "/platform/hispe/edit",
			type : "Post",
			data : {
				"id" : $("#id").val(),
				"title" : $("#text_title").val(),
				"imgurl" : $("#imgurl").val(),
				"sort":$("#sort").val(),
				"type":$('input[name="state"]:checked ').val(),
				"kid":$("#select_sku").attr("data"),
				"province":$("#select_province").val(),
				"city":$("#select_city").val()
			},
			dataType : "json",
			success : function(data) {

				if (data.code == 0) {
					Dalert("保存成功！","",function(){window.location.href = "/platform/yztc/list"});
				} else {
					Dalert(data.desc);
				}
			}
		});
	}
}
function check() {
	
	return $("#form1").validate({
		rules : {
			text_title : {
				required : true,
				rangelength : [ 1, 100 ]
			}
		/*
		 * , orderby: { required: true, digits: true }
		 */
		},
		message : {
			text_title : {
				required : "特产名称不能为空"
			}
		/*
		 * , orderby: { required: "排序不能为空", digits: "必须输入整数" }
		 */
		}
	});
}

//所在地数据绑定
function GetRegionData(rType, pCode) {
	$.ajax(({
		type : "post",
		url : "/platform/hispe/queryRegion",
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
			url : "/platform/hispe/queryRegion",
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
					$("#select_city").append(html);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	})


}