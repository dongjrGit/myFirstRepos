$(function () {
    var date = new Date();
    $("#starttime").val(date.Format());
    $("#endtime").val(date.Format());
    getlist("PC");
    //选取固定时间
    $(".FastSelect").children().find("a").each(function (i) {
        var $obj = $(this);
        $obj.bind("click", function () { FastSelect($obj.attr("data-val")) });
    });
    //标签切换
    $("#tjtype").children().find("li").click(function (i) {
        $("#tjtype").find("li").removeClass("active");
        $(this).addClass("active");
        $("#dk").val($(this).attr("data_t"));
        var type = $("#dk").val();
        getlist(type)
    });
    //自定义时间查询按钮
    $("#search").bind("click", function () { getlist($("#dk").val()); });

});
//快速选择时间
function FastSelect(type) {
    var my = new MrYangUtil(); var date;
    switch (parseInt(type)) {
        case 0://本周
            date = my.getCurrentWeek();
            break;
        case 1://上周
            date = my.getPreviousWeek();
            break;
        case 2://本月
            date = my.getCurrentMonth();
            break;
        case 3://上月
            date = my.getPreviousMonth();
            break;
    }
    $("#starttime").val(date[0].Format());
    $("#endtime").val(date[1].Format());
    $(".FastSelect").children().find("a").each(function (i) {
        var $obj = $(this);
        if ($obj.attr("data-val") == type) {
            $obj.addClass("red");
        } else {
            $obj.removeClass("red");
        }
    });
}

var charts;
//获取数据 图表
function getlist(type) {
    var timef = $("#starttime").val();
    var timet = $("#endtime").val();
    //var type = $("#dk").val();
    if ((timef != undefined && timef != "") || (timet != undefined && timet != "")) {
        $.ajax({
            url: "/Analysis/GetDeepList",
            type: "Post",
            data: { "start": timef, "end": timet, "type": type },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {

                    var list = data.Data;


                    //获取漏斗图数据
                    //"home", "list", "detail", "addShopcarOK", "shopcar", "sellte", "pay", "payOK", "payFail"
                    var legend = ["主页", "列表页", "详情页", "添加购物车成功页", "购物车页", "结算页", "支付页", "支付成功页", "支付失败页"], values = [], maxdata = 0;
                    if (list != null && list != "") {
                        values.push({ value: list.home, name: "主页" });
                        values.push({ value: list.list, name: "列表页" });
                        values.push({ value: list.detail, name: "详情页" });
                        values.push({ value: list.addShopcarOK, name: "添加购物车成功页" });
                        values.push({ value: list.shopcar, name: "购物车页" });
                        values.push({ value: list.sellte, name: "结算页" });
                        values.push({ value: list.pay, name: "支付页" });
                        values.push({ value: list.payOK, name: "支付成功页" });
                        values.push({ value: list.payFail, name: "支付失败页" });

                        for (var j = 0; j < values.length; j++) {
                            if (values[j].value > maxdata) {
                                maxdata = values[j].value;
                            }
                        }
                        //创建漏斗图
                        $("#funnelchartshow").css("min-height", "300px");
                        EChart_FunnelEx("funnelchartshow", "购物流程深度统计", "购物流程深度统计", legend, "购物流程深度统计", values, maxdata, charts);
                    } else {
                        $("#funnelchartshow").css("max-height", "40px");
                    }
                }
            },
            error: function () {

            }
        });
    }
}
//function callback(ec) {
//    charts = ec;
//}

