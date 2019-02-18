/*$(document).ready(function () {
   
    //修改
    $("span[name=editpf]").live("click", function () {
        var id = $(this).attr("data_id");
        var proid = $(this).attr("data_pro");
        location.href = "ProFloorsEdit?id=" + id + "&proid=" + proid+ "&flid=" + flid;
    });
    //添加
    $("input[name=add]").click(function () {
       // location.href = "ProFloorsEdit?&id=-1" + "&flid=" + flid;
    	location.href = "/platform/floor/showProEdit?flid=" + flid;
    });
    
    // 返回
	$("input[name=back]").click(function() {
		 window.location.href = "/platform/floor/list";
	});
    
    
})*/


///将画多个图表的进行函数封装
function DrawCharts(ec) {
    //获取店铺订单销量
    Home.bindLine(ec);
    ////获取店铺当日商品销量
    //Home.bindPie1(ec);
    //获取店铺最近一周商品销量
    Home.bindPie2(ec);
    //获取店铺当月商品销量
    Home.bindPie3(ec);
    //DrawLineEChart(ec);
    //DrawPieEChartD(ec);
    //DrawPieEChartW(ec);
    //DrawPieEChartM(ec);
}
//创建ECharts折线图图表 店铺销量统计
function DrawLineEChart(ec,y1,y2) {
    //--- 折线图 ---
    var myChart = ec.init(document.getElementById('main'), "macarons");
    //图表显示提示信息
    myChart.showLoading({
        text: "图表数据正在努力加载..."
    });
    myChart.hideLoading();
    //加载图表数据
    myChart.setOption({
        //title: {
        //    text: "店铺销售情况"
        //},
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['订单总数', '订单总金额']
        },
        toolbox: {
            show: false
        },
        calculable: true,
        xAxis: [
        {
            type: 'category',
            //通过js函数返回json数组
            data: ['当日订单', '最近一周订单', '本月订单']
        }
        ],
        yAxis: [
        {
            type: 'value',
            splitArea: { show: true }
        }
        ],
        series: [
        {
            name: '订单总数',
            type: 'line', //序列展现类型为柱状图
            //通过js函数返回json数组
            data: y1
        },
        {
            name: '订单总金额',
            type: 'line',
            //通过js函数返回json数组
            data: y2
        }
        ]
    });
}
//创建ECharts饼图图表 当日商品销量统计
function DrawPieEChartD(ec, Pielegend, value,tipvalue) {
    //--- 饼图 ---
    var myPieChart = ec.init(document.getElementById('mainPieD'), "macarons");

    //图表显示提示信息

    myPieChart.showLoading({

        text: "图表数据正在努力加载..."

    });
    myPieChart.hideLoading();
    //加载图表数据
    myPieChart.setOption({

        //标题，每个图表最多仅有一个标题控件
        //title: {
        //    text: '当日商品销量统计',
        //    x: 'center'
        //},
        //提示框，鼠标悬浮交互时的信息提示
        tooltip: {
            trigger: 'item',
            //formatter: "{b} : {c} ({d}%)"
            formatter: function (a) {
                var relVal = "", name = "";
                $.each(tipvalue, function (n, v) {
                    name = v.name;
                    if (a[1] == cutString(name, 15)) {
                        relVal = v.name;
                        relVal += "<br/>";
                        relVal += "数量：";
                        relVal += a[2] + "(" + a[3] + "%)";
                    }
                });
                return relVal;
            }
        },
        //图例，每个图表最多仅有一个图例，混搭图表共享
        legend: {
            orient: 'vertical',
            x: 'left',
            data: Pielegend
        },
        //工具箱，每个图表最多仅有一个工具箱
        toolbox: {
            show: true,
        },
        //是否启用拖拽重计算特性，默认关闭
        calculable: true,

        //图表数据加载和图表类型设置
        series: [
            {
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: value
            }
        ]
    });
}
//创建ECharts饼图图表 最近一周商品销量统计
function DrawPieEChartW(ec, Pielegend, value, tipvalue) {
    //---  饼图 ---
    var myPieChart = ec.init(document.getElementById('mainPieW'), "macarons");

    //图表显示提示信息

    myPieChart.showLoading({

        text: "图表数据正在努力加载..."

    });
    myPieChart.hideLoading();
    //加载图表数据
    myPieChart.setOption({

        //标题，每个图表最多仅有一个标题控件
        //title: {
        //    text: '最近一周商品销量统计',
        //    x: 'center'
        //},
        //提示框，鼠标悬浮交互时的信息提示
        tooltip: {
            trigger: 'item',
            //formatter: "{b} : {c} ({d}%)"
            formatter: function (a) {
                var relVal = "", name = "";
                $.each(tipvalue, function (n, v) {
                    name = v.name;
                    if (a[1] == cutString(name, 15)) {
                        relVal = v.name;
                        relVal += "<br/>";
                        relVal += "数量：";
                        relVal += a[2] + "(" + a[3] + "%)";
                    }
                });
                return relVal;
            }
        },
        //图例，每个图表最多仅有一个图例，混搭图表共享
        legend: {
            orient: 'vertical',
            x: 'left',
            data: Pielegend
        },
        //工具箱，每个图表最多仅有一个工具箱
        toolbox: {
            show: true,
        },
        //是否启用拖拽重计算特性，默认关闭
        calculable: true,

        //图表数据加载和图表类型设置
        series: [
            {
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: value
            }
        ]
    });
}
//创建ECharts饼图图表 当月商品销量统计
function DrawPieEChartM(ec, Pielegend, value, tipvalue) {
    //---  饼图 ---
    var myPieChart = ec.init(document.getElementById('mainPieM'), "macarons");

    //图表显示提示信息

    myPieChart.showLoading({

        text: "图表数据正在努力加载..."

    });
    myPieChart.hideLoading();
    //加载图表数据
    myPieChart.setOption({

        //标题，每个图表最多仅有一个标题控件
        //title: {
        //    text: '当月商品销量统计',
        //    x: 'center'
        //},
        //提示框，鼠标悬浮交互时的信息提示
        tooltip: {
            trigger: 'item',
            //formatter: "{b} : {c} ({d}%)"
            formatter: function (a) {
                var relVal = "", name = "";
                $.each(tipvalue, function (n, v) {
                    name = v.name;
                    if (a[1] == cutString(name, 15)) {
                        relVal = v.name;
                        relVal += "<br/>";
                        relVal += "数量：";
                        relVal += a[2] + "(" + a[3] + "%)";
                    }
                });
                return relVal;
            }
        },
        //图例，每个图表最多仅有一个图例，混搭图表共享
        legend: {
            orient: 'vertical',
            x: 'left',
            data: Pielegend
        },
        //工具箱，每个图表最多仅有一个工具箱
        toolbox: {
            show: true,
        },
        //是否启用拖拽重计算特性，默认关闭
        calculable: true,

        //图表数据加载和图表类型设置
        series: [
            {
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: value
            }
        ]
    });
}


var Home = {
    bindPie1: function (ec) {
        //获取当日商品销量
        var PieLegend1 = [], yPieValue1 = [],tooltip=[]; //当日商品销量
        $.ajax({
            url: "/seller/createChart/getSaleSKU",
            type: "Post",
            data: { "page": 1,
            	"size": 10, 
            	"type": 1 },
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    if (data.data.length > 0) {
                        for (var i = 0; i < data.data.length ; i++) {
                            PieLegend1.push(cutString(data.data[i].spuname, 15));
                            yPieValue1.push({ value: data.data[i].salecount, name: cutString(data.data[i].spuname, 15) });
                            tooltip.push({ value: data.data[i].salecount, name: data.data[i].spuname });
                        }
                    }
                    DrawPieEChartD(ec, PieLegend1, yPieValue1,tooltip);
                }
            }
        });
    },
    bindPie2: function (ec) {
        //获取最近一周商品销量
        var PieLegend2 = [], yPieValue2 = [], tooltip = []; //当日商品销量
        $.ajax({
            url: "/seller/createChart/getSaleSKU",
            type: "Post",
            data: { "page": 1,
            	"size": 10,
            	"type": 2 },
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    if (data.data.length > 0) {
                        for (var i = 0; i < data.data.length ; i++) {
                            PieLegend2.push(cutString(data.data[i].spuname, 15));
                            yPieValue2.push({ value: data.data[i].salecount, name: cutString(data.data[i].spuname, 15) });
                            tooltip.push({ value: data.data[i].salecount, name: data.data[i].spuname });
                        }
                    }
                    DrawPieEChartW(ec, PieLegend2, yPieValue2, tooltip);
                }
            }
        });
    },
    bindPie3: function (ec) {
        //获取当月商品销量
        var PieLegend3 = [], yPieValue3 = [], tooltip = []; //当日商品销量
        $.ajax({
            url: "/seller/createChart/getSaleSKU",
            type: "Post",
            data: { "page": 1,
            	"size": 10,
            	"type": 3 },
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    if (data.data.length > 0) {
                        for (var i = 0; i < data.data.length ; i++) {
                            //PieLegend3.push(data.Data[i].ProductName);
                            PieLegend3.push(cutString(data.data[i].spuname, 15));
                            yPieValue3.push({ value: data.data[i].salecount, name: cutString(data.data[i].spuname, 15) });
                            tooltip.push({ value: data.data[i].salecount, name: data.data[i].spuname });
                        }
                    }
                    DrawPieEChartM(ec, PieLegend3, yPieValue3, tooltip);
                }
            }
        });
    },
    bindLine: function (ec) {
        //获取店铺订单销售统计
        var yLineValue1 = [];//订单销量数
        var yLineValue2 = [];//订单销量总金额
        $.ajax({
            url: "/seller/createChart/getSaleOrder",
            type: "Post",
            data: {},
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    if (data.data.length > 0) {
                        for (var i = 0; i < data.data.length ; i++) {
                            yLineValue1.push(data.data[i].ordercount);
                            yLineValue2.push(data.data[i].orderacount);
                        }
                    }
                    //return yLineValue1 + "|" + yLineValue2;
                    DrawLineEChart(ec, yLineValue1, yLineValue2);
                }
            }
        });
    }
}
