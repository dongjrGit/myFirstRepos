var pcount;
var pindex;
var psize = 10;
var ZooNews = {
	getAll : function(index) {
        var title = $("#title").val();
        var state = $("#state").val();
        $.ajax({
            url : "/zoo/news/querylist",
            type : "post",
            dataType : "json",
            data : {
            	"pageindex" : index,
				"pagesize" : psize,
				"title" : title,
				"state" : state,
				"delState":1
            },
            success : function(data) {
                if (data.code < 0) {
                    $("#divshow").attr("style", "display:none");
                    $("#pager").attr("style", "display:none");
                    Dalert(data.desc);
                } else {
                    $("#divshow").attr("style", "display:block;")
                    $("#pager").attr("style", "display:block");
                    var listdata = {
                        list : data.data
                    }
                    var html = template('bannerList', listdata);
                    $("#datalist").html(html);
                    // 分页
                    // title = title;
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "ZooNews.getAll"));
                }
            },
            error : function(xhr, status, error) {
				alert("操作失败" + xhr.responseText);
			}
        });
    },
    updateStatus:function(obj){
        var id = $(obj).attr("data-id");
        var state = $(obj).attr("data-status");
        $.ajax({
            url : "/zoo/news/updateState",
            type : "post",
            data : {
                'id' : id,
                'state' : state
            },
            dataType : "json",
            success : function(data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                	ZooNews.getAll(pindex);
                }
            },
            error : function() {
                Dalert("修改状态失败");
            }
        });
    
    },
    del:function(id){
		var data={"id":id};
		$.ajax({
			url : "/zoo/news/dellist",
			type : "post",
			data :data,
			dataType : "json",
			success : function(data) {
				if(pindex!=1){
					if(pcount%psize==1){
						pindex--;
					}
				}
				if(data.code==0){
					Dalert(data.desc, "", ZooNews.getAll(pindex));
				}else{
					Dalert(data.desc, "", null);
				}
			},
			error : function(xhr, status, error) {
				alert("删除失败 \r" + xhr.responseText);
			}
		});
	},
	// 选择是否删除
	delCheck:function(id) {
		ConfirmShow("确认删除吗？", ZooNews.del, id, "是否删除");
	},
	add:function(){
		window.location.href="/zoo/news/listedit";
	}
}



