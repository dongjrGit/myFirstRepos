//网站管理-关键字设置
var pcount, pindex;
var psize = size_common;
$(function(){
	initView.Page(1);
});

var initView={
		Modify:function(){
			var formdata=$("#form").serialize();
			$.ajax({
                url: "/platform/websitesConfig/modify",
                type: "Post",
                data: formdata,
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert("保存成功");
                    }else{
                    	 Dalert(data.desc);
                    }
                }
            })
		},Page: function (index) {
	        $.ajax({
	            url: "/platform/websitesConfig/index",
	            type: "Post",
	            data: { "page": index, "size": psize },
	            dataType: "json",
	            success: function (data) {
	                if (data.Code < 0) {
	                    Dalert(data.desc);
	                } else {
	                    var listdata = {
	                        list: data.data
	                    }
	                    var html = template('searchlist', listdata);
	                    $("#list_title").html(html);
	                    pcount = data.maxRow;
	                    pindex = data.pageIndex;
	                    //分页
	                    $("#pager").html(pagination(pcount, pindex, psize, "initView.Page"));
	                }
	            },
	            error: function () {
	            	alert("错误");
	            }
	        })
	    },//删除
	    del: function (id) {
	        if (confirm("确定要删除吗？")) {
	            $.ajax({
	                url: "/platform/websitesConfig/delete",
	                type: "Post",
	                data: { "id": id },
	                dataType: "json",
	                success: function (data) {
	                    Dalert(data.desc);
	                    if (data.code == 0) {
	                        location.reload();
	                    }
	                }
	            })
	    }
		}
};