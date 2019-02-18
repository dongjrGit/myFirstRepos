///将画多个图表的进行函数封装
function DrawCharts(ec) {
    ////获取当日订单销量
    //Home.bindOrderD(ec);
    //获取最近一周订单销量
    Home.bindOrderW(ec);
    //获取当月订单销量
    Home.bindOrderM(ec);
    ////获取当日商品销量
    //Home.bindSKUD(ec);
    //获取最近一周商品销量
    Home.bindSKUW(ec);
    //获取当月商品销量
    Home.bindSKUM(ec);
}
//创建ECharts折线图图表 店铺销量统计
function DrawOrderEChartD(ec, Pielegend, value, tipvalue) {
    //--- 饼图 ---
    var myPieChart = ec.init(document.getElementById('main'), "macarons");

    //图表显示提示信息

    myPieChart.showLoading({

        text: "图表数据正在努力加载..."

    });
    myPieChart.hideLoading();
    //加载图表数据
    myPieChart.setOption({

        //标题，每个图表最多仅有一个标题控件
        title: {
            text: '当日订单销量统计',
            x: 'center'
        },
        //提示框，鼠标悬浮交互时的信息提示
        tooltip: {
            trigger: 'item',
            //气泡提示框 内容自定义
            formatter: function (a) {
                var relVal = "", name = "";
                $.each(tipvalue, function (n, v) {
                    name = v.name;
                    if (a[1] == cutString(name, 15)) {
                        relVal = v.name;
                        relVal += "<br/>";
                        relVal += "数量：";
                        relVal += a[2] + "(" + a[3] + "%)";
                        relVal += "<br/>";
                        relVal += "金额：" + v.money;
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
function DrawOrderEChartW(ec, Pielegend, value, tipvalue) {
    //--- 饼图 ---
    var myPieChart = ec.init(document.getElementById('mainOrderW'), "macarons");

    //图表显示提示信息

    myPieChart.showLoading({

        text: "图表数据正在努力加载..."

    });
    myPieChart.hideLoading();
    //加载图表数据
    myPieChart.setOption({

        //标题，每个图表最多仅有一个标题控件
        title: {
            text: '最近一周订单销量统计',
            x: 'center'
        },
        //提示框，鼠标悬浮交互时的信息提示
        tooltip: {
            trigger: 'item',
            //气泡提示框 内容自定义
            formatter: function (a) {
                var relVal = "", name = "";
                $.each(tipvalue, function (n, v) {
                    name = v.name;
                    if (a[1] == cutString(name, 15)) {
                        relVal = v.name;
                        relVal += "<br/>";
                        relVal += "数量：";
                        relVal += a[2] + "(" + a[3] + "%)";
                        relVal += "<br/>";
                        relVal += "金额：" + v.money;
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
function DrawOrderEChartM(ec, Pielegend, value, tipvalue) {
    //--- 饼图 ---
    var myPieChart = ec.init(document.getElementById('mainOrderM'), "macarons");

    //图表显示提示信息

    myPieChart.showLoading({

        text: "图表数据正在努力加载..."

    });
    myPieChart.hideLoading();
    //加载图表数据
    myPieChart.setOption({

        //标题，每个图表最多仅有一个标题控件
        title: {
            text: '当月订单销量统计',
            x: 'center'
        },
        //提示框，鼠标悬浮交互时的信息提示
        tooltip: {
            trigger: 'item',
            //气泡提示框 内容自定义
            formatter: function (a) {
                var relVal = "", name = "";
                $.each(tipvalue, function (n, v) {
                    name = v.name;
                   
                    if (a[1] == cutString(name, 15)) {
                        relVal = v.name;
                        relVal += "<br/>";
                        relVal += "数量：";
                        relVal += a[2] + "(" + a[3] + "%)";
                        relVal += "<br/>";
                        relVal += "金额：" + v.money;
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
//创建ECharts饼图图表 当日商品销量统计
function DrawPieEChartD(ec, Pielegend, value, tipvalue) {
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
        title: {
            text: '当日商品销量统计',
            x: 'center'
        },
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
                center: ['60%', '50%'],
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
        title: {
            text: '最近一周商品销量统计',
            x: 'center'
        },
        //提示框，鼠标悬浮交互时的信息提示
        tooltip: {
            trigger: 'item',
            //formatter: "{b} : {c} ({d}%)"
            formatter: function (a) {
                var relVal = "",name="";
                $.each(tipvalue, function (n, v) {
                    name = v.name;
                    if (a[1] == cutString(name, 15)) {
                        relVal = v.name;
                        relVal += "<br/>";
                        relVal += "数量：";
                        relVal += a[2]+"("+a[3]+"%)";
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
                center: ['60%', '50%'],
                data: value
            }
        ]
    });
}
//创建ECharts饼图图表 当月商品销量统计
function DrawPieEChartM(ec, Pielegend, value,tipvalue) {
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
        title: {
            text: '当月商品销量统计',
            x: 'center'
        },
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
                center: ['60%', '50%'],
                data: value
            }
        ]
    });
}


var Home = {
    bindSKUD: function (ec) {
        //获取当日商品销量
        var PieLegend1 = [], yPieValue1 = [], tooltip = []; //当日商品销量
        $.ajax({
            url: "/Product_TJ/GetSaleSKU",
            type: "Post",
            data: { "pageindex": 1, "pagesize": 10, "stype": 1 },
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    if (data.Data.length > 0) {
                        for (var i = 0; i < data.Data.length ; i++) {
                            //PieLegend1.push(data.Data[i].ProductName);
                            PieLegend1.push(cutString(data.Data[i].ProductName, 15));
                            yPieValue1.push({ value: data.Data[i].ProductCount, name: cutString(data.Data[i].ProductName, 15) });
                            tooltip.push({ value: data.Data[i].ProductCount, name: data.Data[i].ProductName });
                        }
                    }
                    DrawPieEChartD(ec, PieLegend1, yPieValue1, tooltip);
                }
            }
        });
    },
    bindSKUW: function (ec) {
        //获取最近一周商品销量
        var PieLegend2 = [], yPieValue2 = [], tooltip = []; //当日商品销量
        $.ajax({
            url: "/platform/syCreateCharts/getSaleSKUZ",
            type: "Post",
            data: { "pageindex": 1,
            	"pagesize": 10},
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    if (data.data.length> 0) {
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
    bindSKUM: function (ec) {
        //获取当月商品销量
        var PieLegend3 = [], yPieValue3 = [], tooltip = []; //当日商品销量
        $.ajax({
            url: "/platform/syCreateCharts/getSaleSKUM",
            type: "Post",
            data: { "pageindex": 1,
            	"pagesize": 10},
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    if (data.data.length > 0) {
                        for (var i = 0; i < data.data.length ; i++) {
                        	
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
    bindOrderD: function (ec) {
        //获取店铺订单销售统计
        var PieLegend = [];//图例 店铺名称
        var yPieValue = [], tooltip = [];;//订单销量总金额
        $.ajax({
            url: "/Order_Platform/GetSaleOrder",
            type: "Post",
            data: {"index":1, "size":10, "stype":1},
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    if (data.data.length > 0) {
                        for (var i = 0; i < data.data.length ; i++) {
                            //PieLegend.push(data.Data[i].Name);
                            PieLegend.push(cutString(data.data[i].Name, 15));
                            yPieValue.push({ value: data.data[i].TotalCount, name: cutString(data.data[i].Name, 15) });
                            tooltip.push({ money: parseFloat(data.data[i].TotalMoney).toFixed(2), name: cutString(data.data[i].Name, 15) });
                        }
                    }
                    DrawOrderEChartD(ec, PieLegend, yPieValue,tooltip);
                }
            }
        });
    },
    bindOrderW: function (ec) {
        //获取店铺订单销售统计
        var PieLegend1 = [];//图例 店铺名称
        var yPieValue1 = [], tooltip = [];;//订单销量总金额
        $.ajax({
            url: "/platform/syCreateCharts/getSaleOrderZ",
            type: "Post",
            data: { "index": 1,
            	"size": 10
            	 },
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    if (data.data.length > 0) {
                        for (var i = 0; i < data.data.length ; i++) {
                            PieLegend1.push(cutString(data.data[i].shopname, 15));
                            yPieValue1.push({ value: data.data[i].ordercount, name: cutString(data.data[i].shopname, 15) });
                            tooltip.push({ money: parseFloat(data.data[i].orderacount).toFixed(2), name: cutString(data.data[i].shopname, 15) });
                        }
                    }
                    DrawOrderEChartW(ec, PieLegend1, yPieValue1,tooltip);
                }
            }
        });
    },
    bindOrderM: function (ec) {
        //获取店铺订单销售统计
        var PieLegend1 = [];//图例 店铺名称
        var yPieValue1 = [], tooltip = [];;//订单销量总金额
        $.ajax({
            url: "/platform/syCreateCharts/getSaleOrderM",
            type: "Post",
            data: { "index": 1,
            	"size": 10},
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    if (data.data.length > 0) {
                        for (var i = 0; i < data.data.length; i++) {
                            PieLegend1.push(cutString(data.data[i].shopname, 15));
                            yPieValue1.push({ value: data.data[i].ordercount, name: cutString(data.data[i].shopname, 15) });
                            tooltip.push({ money: parseFloat(data.data[i].orderacount).toFixed(2), name: cutString(data.data[i].shopname, 15) });
                        }
                    }
                    DrawOrderEChartM(ec, PieLegend1, yPieValue1,tooltip);
                }
            }
        });
    }
}
