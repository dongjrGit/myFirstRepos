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
            url: "/SearchKey/P_GetList",
            type: "Post",
            data: { "index": index, "size": psize },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('searchlist', listdata);
                    $("#list_title").html(html);

                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    //绑定事件
                    Search.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "Search.getSearchList"));
                }
            },
            error: function () {

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
                url: "/SearchKey/P_Delete",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    Dalert(data.Desc);
                    if (data.Code == 0) {
                        location.reload();
                    }
                }
            })
        }
    },
    //添加关键字
    save: function () {
        var keyws = $("#addnew_keys").val();
        var order = $("#addnew_order").val();
        if (check().form()) {
            $.ajax({
                url: "/SearchKey/P_Add",
                type: "Post",
                data: { "keyword": keyws, "orderby": order },
                dataType: "json",
                success: function (data) {
                    Dalert(data.Desc);
                    if (data.Code == 0) {
                        location.reload();
                    }
                }
            })
        }
    },
    //修改排序
    setOrder: function () {
        var id = $(this).attr("data-id");
        var obtext = $("#" + id).val();
        $.ajax({
            url: "/SearchKey/P_ChangeOrder",
            type: "Post",
            data: { "id": id, "orderby": obtext },
            dataType: "json",
            success: function (data) {
                Dalert(data.Desc);
            },
            error: function () {
                Dalert("修改排序失败");
            }
        });
    }
}
//保存前参数验证
function check() {
    return $("#form").validate({
        rules: {
            addnew_keys: {
                required: true,
                stringCheck: true,
                stringRangeLength: [1, 10]
            },
            addnew_order: {
                required: true,
                digits: true
            },
        },
        message: {
            addnew_keys: {
                required: "关键字不可为空",
                stringRangeLength: "关键字长度在10个字符以内（一个汉字算两个字符）"
            },
            addnew_order: {
                required: "排序不能为空",
                digits: "必须输入整数"
            }
        }
    });
}