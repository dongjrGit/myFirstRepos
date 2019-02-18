//店铺商品列表
var pcount, pindex, psize = size_product;
var OperateRecords = {
    bind: function () {
        $(".chaxun").bind("click", function () { OperateRecords.getOperateList(1); });
        $(".delete").bind("click", OperateRecords.del);
    },
    getOperateList: function (index) {
        var type = $("#select_type").val();
        var from = $("#select_from").val();
        var username = $("#select_username").val();
        var userid = $("#select_userid").val();
        var userip = $("#select_userip").val();
        var page = $("#select_page").val();
        var controller = $("#select_interface").val();
        var desc = $("#select_desc").val();
        var begin = $("#select_begin").val();
        var end = $("#select_end").val();
        $.ajax({
            url: "/OperateRecords/P_GetListByQuery",
            type: "Post",
            data: {
                "type": type, "from": from, "username": username, "page": index, "size": psize, "userid": userid,
                "userip": userip, "pagename": page, "controller": controller, "desc": desc, "begin": begin, "end": end
            },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('operatelist', listdata);
                    $("#list_title").html(html);
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "OperateRecords.getOperateList"));

                }
            }
        })
    },
    del: function () {
        if (confirm("确定要全部删除吗？")) {
            $.ajax({
                url: "/OperateRecords/P_DelAll",
                type: "Post",
                data: { },
                dataType: "json",
                success: function (data) {
                    if (data.Code == 0) {
                        Dalert(data.Desc, 1000);
                        pagelist(pindex);
                    } else {
                        Dalert(data.Desc);
                    }
                }
            })
        }
    }
}