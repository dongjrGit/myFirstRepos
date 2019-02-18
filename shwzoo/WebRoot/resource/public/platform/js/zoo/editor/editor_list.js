var pcount;
var pindex;
var psize = 10;
var ZooEditor = {
	getAll : function(index) {
        var title = $("#title").val();
        var type = $("#type").val();
        $.ajax({
            url : "/zoo/editor/querylist",
            type : "post",
            dataType : "json",
            data : {
            	"pageindex" : index,
				"pagesize" : psize,
				"title" : title,
				"type" : type,
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
                    $("#pager").html(pagination(pcount, pindex, psize, "ZooEditor.getAll"));
                }
            },
            error : function(xhr, status, error) {
				alert("操作失败" + xhr.responseText);
			}
        });
    },
    del:function(id){
    	var type=$("#type").val();
		var data={"id":id,"type":type};
		$.ajax({
			url : "/zoo/editor/dellist",
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
					Dalert(data.desc, "", ZooEditor.getAll(pindex));
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
		ConfirmShow("确认删除吗？", ZooEditor.del, id, "是否删除");
	},
	add:function(){
		var type = $("#type").val();
		window.location.href="/zoo/editor/listedit?type="+type;
	}
}



