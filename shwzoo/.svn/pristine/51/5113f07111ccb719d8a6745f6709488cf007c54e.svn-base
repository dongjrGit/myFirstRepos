/**
 * 团购
 */
var pcount, pindex, psize = 10;
var groupb = {
    bind: function () {
        $("input[name=btnsearch]").bind("click", function () { groupb.getlist(1); });
        $("input[name=btnadd]").bind("click", function () { location.href = "yxgl_GroupBuyAdd"; });
    },
    getlist: function (index) {
        //分类路径 商品名称 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var sid = "",type1,name = "", s1, e1, s2, e2;
        var sid = $("#select_shop").attr("data");
        type1 = $("#type_select").val();
        title = $("#title").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/seller/shopgroupbuy/getList",
            type: "Post",
            data: { "page": index, "size": psize, "startf": s1, "startt": e1, "endf": s2, "endt": e2,
                "type": type1, "title": title, "shopid": sid
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('grouplist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    groupb.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "groupb.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", groupb.del);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/seller/shopgroupbuy/delete",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        groupb.getlist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    }
}
//更改状态
function setStatus(id, ss) {
    $.ajax({
        url: "/seller/shopgroupbuy/updateStatus",
        type: "Post",
        data: { "id": id, "status": ss },
        dataType: "json",
        success: function (data) {
            if (data.code < 0) { Dalert(data.desc); }
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