//组合商品列表
var pcount, pindex, psize = 10;
var pack = {
    bind: function () {
        $("input[name=btnsearch]").bind("click", function () { pack.getlist(1); });
        $("input[name=btnadd]").bind("click", function () { location.href = "yxgl_PackageAdd"; });
    },
    getlist: function (index) {
        // 编号 名称 审核状态 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var num="",checkss="", name = "", s1, e1, s2, e2;
        num = $("#num").val();
        name = $("#name").val();
        checkss = $("#checkss").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/seller/ShopPackage/getList",
            type: "Post",
            data: {
                "page": index, "size": psize, "startf": s1, "startt": e1, "endf": s2, "endt": e2,
                "num": num, "name": name, "status": checkss
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('packlist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    pack.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pack.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", pack.del);
        });
        $(".ischeck").each(function () {
            $(this).bind("click", pack.ischeck);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/seller/ShopPackage/deletePackage",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        pack.getlist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    },
    ischeck: function () {
        var id = $(this).attr("data");
        $.ajax({
            url: "/seller/ShopPackage/updateIsCheck",
            type: "Post",
            data: { "id": id },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert(data.desc, 1000);
                    var td_html = "";

                    td_html = "<a href='sku_packageList?id=" + id + "'><span class='shenlan'>编辑</span></a>";
                    td_html += "<span style='margin-right:5px;'>";
                    td_html += "<a href='javascript:void(0);' class='del' data='" + id + "'><span class='shenlan'>删除</span></a>";

                    $("#cz_" + id).html(td_html);
                    $("#check_" + id).html("<span>已审核</span>");
                } else {
                    Dalert(data.desc);
                }
            }
        })
    }
}