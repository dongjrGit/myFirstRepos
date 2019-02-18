
var pcount, pindex, psize = 10;
var spike = {
    bind: function () {
        $("input[name=btnsearch]").bind("click", function () { spike.getlist(1); });
        $("input[name=btnadd]").bind("click", function () { location.href = "/platform/integra/editintepro"; });
    },
    getlist: function (index) {
        //活动类型 活动编号 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var num = "", s1, e1, s2, e2;
        num = $("#num").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/platform/integra/seljfsp",
            type: "Post",
            data: {
                "pageindex": index, "pagesize": psize,"name":num,"start1": s1, "end1": e1, "start2": s2, "end2": e2
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
        $(".del").each(function () {
            $(this).bind("click", spike.del);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/platform/integra/deljfsp",
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
