
//经彩活动列表
var pcount, pindex, psize = size_common;
var spike = {
    bind: function () {
    	spike.getlist(1);
    },
    getlist: function (index) {
        var sid = $("#spikeid").val();
        $.ajax({
            url: "/platform/SpikeActivity/getUserListBySpikeID",
            type: "Post",
            data: {
                "page": index, "size": psize, "spikeid": sid
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
                    //绑定使用事件
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
        $(".usecode").each(function () {
            $(this).bind("click", spike.use);
        });
    },
    use: function () {
        if (confirm("确定要使用吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/platform/SpikeActivity/useCode",
                type: "Post",
                data: { "spikeuserid": id },
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