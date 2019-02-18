//网站管理-关键字设置
var pcount, pindex;
var psize = size_common;
//绑定事件
var Search = {
    bind: function () {
        $("#addnew_submit").bind("click", Search.save);
        $("#addnew_cancel").bind("click", function () { $("#addnew_tr").hide(); });
        $(".addnew_button").first().bind("click", function () {
            $("#addnew_tr").show();
        });
        Search.getSearchList(1);
    },
    //获取搜索属性列表
    getSearchList: function (index) {
        $.ajax({
            url: "/platform/searchkey/index",
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
                    //绑定事件
                    Search.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "Search.getSearchList"));
                }
            },
            error: function () {
            	//alert("错误");
            }
        })
    },
    //绑定列表事件
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", Search.del);
        });      
    },
    //删除
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/platform/searchkey/delete",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {                    
                    if (data.code == 0) {
                    	Dalert(data.desc,"",function(){
                    		location.reload();
                    	});                        
                    }
                }
            })
        }
    },
    //添加关键字
    save: function () {
        if (check().form()) {
            $.ajax({
                url: "/platform/searchkey/insert",
                type: "Post",
                data:$("#forms").serialize(),
                dataType: "json",
                success: function (data) {                   
                    if (data.code == 0) {
                    	 Dalert(data.desc,"",function(){
                    	 	$("#forms")[0].reset();
                            location.reload();
                    	 });                    	
                    }
                }
            })
        }
    },
    //修改排序
    setOrder: function (id,obtext) {       
        $.ajax({
            url: "/platform/searchkey/orderByUpd",
            type: "Post",
            data: { "id": id, "orderBy": obtext },
            dataType: "json",
            success: function (data) {
                Dalert(data.desc);
            },
            error: function () {
                Dalert("修改排序失败");
            }
        });
    }
}
//保存前参数验证
function check() {
    return $("#forms").validate({
        rules: {
            keyword: {
                required: true,                
                rangelength: [1, 10]
            },
            orderby: {
                required: true,
                digits: true
            },
        },
        message: {
            keyword: {
                required: "关键字不可为空",
                rangelength: "关键字长度在10个字符以内（一个汉字算两个字符）"
            },
            orderby: {
                required: "排序不能为空",
                digits: "必须输入整数"
            }
        }
    });
}