//积分商品列表
var pcount, pindex, psize = size_common;
var points = {
    bind: function () {
        $("input[name=btnsearch]").bind("click", function () { points.getlist(1); });
        $("input[name=btnadd]").bind("click", function () { location.href = "PointsSkuAdd"; });
    },
    getlist: function (index) {
        var name = "", s1, e1;
        name = $("#name").val();
        s1 = $("#start").val();
        e1 = $("#end").val();
        $.ajax({
            url: "/SkuPoints/P_GetList",
            type: "Post",
            data: { "index": index, "size": psize, "pointstart": s1, "pointend": e1, "name": name },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }

                    var html = template('pskulist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    //绑定删除事件
                    points.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "points.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", points.del);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/SkuPoints/P_Delete",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.Code == 0) {
                        Dalert(data.Desc, 1000);
                        points.getlist(pindex);
                    } else {
                        Dalert(data.Desc);
                    }
                }
            })
        }
    }
}
//禁用启用
function setStatus(id, ss) {
    $.ajax({
        url: "/SkuPoints/P_ChangeStatus",
        type: "Post",
        data: { "id": id, "ss": ss },
        dataType: "json",
        success: function (data) {
            if (data.Code < 0) { Dalert(data.Desc); }
            else {
                var td_html = "";
                if (ss == 0) {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + id + ",1)>启用</a></span>";
                }
                else {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + id + ",0)>禁用</a></span>";
                }
                $("#td_" + id).html(td_html);
            }
        },
        error: function () {
            Dalert("修改状态失败");
        }
    });
}
//修改排序
function setOrder(id, ob) {
    var obtext = $("#" + ob).val();
    $.ajax({
        url: "/SkuPoints/P_ChangeOrder",
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