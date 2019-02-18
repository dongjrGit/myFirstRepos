$(document).ready(function () {
    $("#lastseven").attr("checked", "checked");
    var myDate = new Date();
    setDate(1);
    $("#lastseven").bind("click", function () {//上周
        $("#divdate").hide();
        setDate(1);
    });
    $("#lastthirty").bind("click", function () {//上月
        $("#divdate").hide();
        setDate(2);
    });
    $(".inquire").bind("click", function () {//查询
        $('input:radio[name="last"]').attr("checked", false);
        setDate(3);
    });
    $(".l_jygktop02").bind("click", divShow);
});
//自定义时间显示
function divShow() {
    if ($("#divdate").css("display") == "none") {
        $("#divdate").show();
    }
    else
        $("#divdate").hide();
}
//设置显示时间
function setDate(value) {
    var start, end;
    var myDate = new Date();
    switch (value) {
        case 1: end = new Date(myDate.getTime() - 1 * 24 * 60 * 60 * 1000).Format();
            start = new Date(myDate.getTime() - 7 * 24 * 60 * 60 * 1000).Format();  //对取得的时间减7天
            break;
        case 2: end = new Date(myDate.getTime() - 1 * 24 * 60 * 60 * 1000).Format();
            start = new Date(myDate.getTime() - 30 * 24 * 60 * 60 * 1000).Format();  //对取得的时间减30天
            break;
        default: start = $("#time").val(); end = $("#time1").val(); break;
    }
    $("#dateStart").html(start);
    $("#dateEnd").html(end);
    $("#dateStart").val(start);
    $("#dateEnd").val(end);
    getlist();
}
//获取数据 图表
function getlist() {
    var timef = $("#dateStart").val();
    var timet = $("#dateEnd").val();
    if ((timef != undefined && timef != "") || (timet != undefined && timet != "")) {
        $.ajax({
            url: "/Analysis/GetFolwList",
            type: "Post",
            data: { "start": timef, "end": timet, "type": "PC" },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var pvall = 0, uvall = 0, geall = 0, ugall = 0;
                    var list = data.Data;
                    var legen = ["浏览量", "独立访客数", "访客数", "独特访客占比"];
                    if (list.length > 0) {
                        var pv = [], uv = [], guest = [], ug = [];
                        for (var i = 0; i < list.length; i++) {
                            pv.push(list[i].pv);
                            uv.push(list[i].uv);
                            guest.push(list[i].guest);
                            if (list[i].guest == 0) {
                                ug.push(0);

                            } else {
                                ug.push(parseFloat((list[i].uv / list[i].guest) * 100).toFixed(2));
                            }
                            pvall += list[i].pv;
                            uvall += list[i].uv;
                            geall += list[i].guest;
                            if (geall != 0) {
                                ugall = parseFloat((uvall / geall) * 100).toFixed(2);
                            }
                        }
                        $("#pvall").text(pvall); $("#uvall").text(uvall); $("#geall").text(geall); $("#ugall").text(ugall+"%");
                        var flowlistdata = new Array();
                        flowlistdata.push(chartdata('浏览量', pv));
                        flowlistdata.push(chartdata('独立访客数', uv));
                        flowlistdata.push(chartdata('访客数', guest));
                        flowlistdata.push(chartdata('独特访客占比', ug));

                        var xData = [];
                        var dt1 = new Date(Date.parse(timet));
                        var dt2 = new Date(Date.parse(timef));
                        var diff = parseInt((dt1.getTime() - dt2.getTime()) / (1000 * 60 * 60 * 24));
                        if (diff <= 1) {
                            for (var j = 0; j < 24; j++) {
                                xData.push(j);
                            }
                        } else {
                            for (var j = 0; j <= diff; j++) {//"2016-01-05"

                                var time = AddDays(timef, j)

                                xData.push(time);
                            }
                        }
                        ShowChart(legen, flowlistdata, xData);
                    }
                }
            },
            error: function () {

            }
        });
    }
}
var flowChart;
function ShowChart(legenddata, pvdata, time) {
    if (flowChart == null) {
        CreateEChart_Lines("flowChart", function (x) {
            flowChart = x;
        }, "平台首页流量动态", "平台首页流量动态", legenddata, time, pvdata);
    }
    else {
        flowChart.setOption(CreateEChart_Lines_option("平台首页流量动态", "平台首页流量动态", legenddata, time, pvdata), true);
    }
}

function chartdata(name, data) {
    return { name: name, type: 'line', data: data };
}
//时间增加天数
function AddDays(date, days) {
    var nd = new Date(date);
    nd = nd.valueOf();
    nd = nd + days * 24 * 60 * 60 * 1000;
    nd = new Date(nd);
    //alert(nd.getFullYear() + "年" + (nd.getMonth() + 1) + "月" + nd.getDate() + "日");
    var y = nd.getFullYear();
    var m = nd.getMonth() + 1;
    var d = nd.getDate();
    if (m <= 9) m = "0" + m;
    if (d <= 9) d = "0" + d;
    var cdate = y + "-" + m + "-" + d;
    return cdate;
}