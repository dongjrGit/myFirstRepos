
//秒杀活动-库存商品列表
var pcount, pindex, psize = size_common;
var spikesku = {
    bind: function () {
        $("input[name=btnadd]").bind("click", function () { location.href = "yxgl_SpikeSpuAdd?id=" + $("#spikeid").val(); });
        spikesku.getlist(1);
    },
    getlist: function (index) {
        var spikeid = $("#spikeid").val();
        $.ajax({
            url: "/platform/SpikeActivity/getSpikeSpuList",
            type: "Post",
            data: {
                "page": index, "size": psize, "id": spikeid
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('spikeskulist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    spikesku.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "spikesku.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", spikesku.del);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data-id");
            $.ajax({
                url: "/platform/SpikeActivity/delSpikeSpu",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        spikesku.getlist(pindex);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    }
}
//是否手机专享
function setIsPhone(id, ss) {
    $.ajax({
        url: "/platform/SpikeActivity/changeIsPhone",
        type: "Post",
        data: { "id": id, "isphone": ss },
        dataType: "json",
        success: function (data) {
            if (data.code < 0) { Dalert(data.desc); }
            else {
                var td_html = "";
                if (ss == 0) {
                    td_html = "<span class='lvs'><a href='#' onclick=setIsPhone(" + id + ",1)>否</a></span>";
                }
                else {
                    td_html = "<span class='lvs'><a href='#' onclick=setIsPhone(" + id + ",0)>是</a></span>";
                }
                $("#td_" + id).html(td_html);
            }
        },
        error: function () {
            Dalert("修改手机专享失败");
        }
    });
}