
var pindex,psize=10,pcount=0;
var data={
	bind:function(index){
		var title=$("#title").val();
		var sname=$("#sname").val();
		var cid=$("#text_class").val();
		s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        var state=$("#type_select").val();
        var auditing=$("#aud_select").val();
		$.ajax({
			url:"/platform/dgroup/glist",
			type:"post",
			data:{"pageindex":index,"pagesize":psize,"title":title,"cid":cid,"sname":sname,
				"startf": s1, "startt": e1, "endf": s2, "endt": e2,"state":state,"auditing":auditing,"isower":1},
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
