$(function(){
	Tglx.getAll(1);
	$("input[name=select_button]").click(function(){
		Tglx.getAll(1);
	});
});

var pindex,psize=10,pcount=0;
var Tglx={
	getAll:function(index){
		var name=$("#spname").val();
		$.ajax({
			url:"/platform/tgroup/getlist",
			type:"post",
			data:{"name":name,"pageindex":index,"pagesize":psize},
			dataType:"json",
			success:function(data){
				if(data.code<0){
					Dalert(data.desc);
				}else{
					var listdata={
							list:data.data
					}
					var html=template("speciallist",listdata);
					
					//翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#list_title").parent().children().each(function () {
                        if ($(this).attr('id') != "list_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#list_title").after(html);
                    pcount = data.maxRow;
					pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
				}
			},
			error:function(){}
		});
	},
	//删除
    del: function (id) {
        ConfirmShow("确定要删除吗？", confrimDel, id, "");
    }
}

function pagelist(index) {
	Tglx.getAll(index);
}

//删除团购类型
function confrimDel(id) {
    $.ajax({
        url: "/platform/tgroup/delbyid",
        type: "Post",
        data: { "id": id },
        dataType: "json",
        success: function (data) {
        	Dalert(data.desc, "",function () { window.location.href = '/platform/tglx/tglx_list'; });
        }
    });
}

