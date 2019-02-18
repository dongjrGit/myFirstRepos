
//秒杀活动列表
var pcount, pindex, psize = 10;
var spike = {
    bind: function () {
        $("input[name=btnsearch]").bind("click", function () { spike.getlist(1); });
    },
    getlist: function (index) {
        //活动类型 活动编号 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var type1, num = "", s1, e1, s2, e2;
        type1 = $("#type_select").val();
        num = $("#num").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/seller/shopspike/getShopApplyList",
            type: "Post",
            data: {
                "page": index, "size": psize, "start1": s1, "end1": e1, "start2": s2, "end2": e2,"stype": type1, "num": num
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('spikelist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    spike.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "spike.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".apply").each(function () {
            $(this).bind("click", spike.apply);
        });
        $(".applyagain").each(function () {
            $(this).bind("click", spike.applyagain);
        });
        $(".out").each(function () {
            $(this).bind("click", spike.out);
        });
    },
    apply: function () {
        if (confirm("确定要申请吗？")) {
            var id = $(this).attr("data-id");
            $.ajax({
                url: "/seller/shopspike/addSpikeShop",
                type: "Post",
                data: { "spikeid": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        spike.getlist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    },
    applyagain: function () {
        if (confirm("确定要继续申请吗？")) {
            var id = $(this).attr("data-id");
            $.ajax({
                url: "/seller/shopspike/updateSpikeShop",
                type: "Post",
                data: { "spikeshopid": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        spike.getlist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    },
    out: function (value) {
        if (confirm("确定要退出吗？")) {
            var id = $(this).attr("data-id");
            $.ajax({
                url: "/seller/shopspike/delSpikeShop",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        spike.getlist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    }
}