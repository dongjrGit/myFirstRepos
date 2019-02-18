
var pindex,psize=10,pcount=0;
var data={
	bind:function(index){
		var title=$(".inp-seller").val();
		var cid=$("#text_class").val();
		s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        var state=$("#type_select").val();
		$.ajax({
			url:"/platform/dgroup/glist",
			type:"post",
			data:{"pageindex":index,"pagesize":psize,"title":title,"cid":cid,
				"startf": s1, "startt": e1, "endf": s2, "endt": e2,"state":state,"isower":0},
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
	if ($("#text_title").val()=="") {
		Dalert("团购名称不能为空!");
		return;
	}
		editor.sync();
	$.ajax({
		url : "/platform/dgroup/save",
		type : "Post",
		data : {
			"id" : $("#id").val(),
			"title" : $("#text_title").val(),
			"imgurl" : $("#imgurl").val(),
			"yprice":$("#text_yprice").val(),
			"cid" : $("#text_class").val(),
			"price" : $("#text_price").val(),
			"num" : $("#text_num").val(),
			"state" : $('input[name="state"]:checked ').val(),
			"depict" : editor.html(),
			"starttime" : $("#text_stime").val(),
			"endtime" : $("#text_etime").val()
		},
		dataType : "json",
		success : function(data) {

			if (data.code == 0) {
				Dalert("保存成功！");
				window.location.href = "/platform/group/list";
			} else {
				Dalert(data.desc);
			}
		}
	});
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
				required : "团购名称不能为空"
			}
		/*
		 * , orderby: { required: "排序不能为空", digits: "必须输入整数" }
		 */
		}
	});
}
