//图形封装 报表ID，标题，图例，X轴数据，数据源

function DrawEChart(ec, chartid, charttitle, chartlegend, chartxAxis, chartvalue) {
    var value = [];
    for (var i = 0; i < chartvalue.length; i++) {
        value.push({ name: chartlegend[i], type: 'line', data: chartvalue[i] });
    }
    var myChart = ec.init(document.getElementById(chartid), "macarons"); //图表主题固定 macarons
    myChart.setOption({
        //标题，每个图表最多仅有一个标题控件
        title: {
            text: charttitle//主标题内容
        },
        //提示框，鼠标悬浮交互时的信息提示
        tooltip: {
            trigger: 'axis'
        },
        //图例，每个图表最多仅有一个图例，混搭图表共享
        legend: {
            data: chartlegend
        },
        //工具箱，每个图表最多仅有一个工具箱
        toolbox: {
            show: true,
        },
        //是否启用拖拽重计算特性，默认关闭
        calculable: true,
        //图表X轴数据加载
        xAxis: [
            {
                type: 'category',
                //data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'] 直接赋值
                data: chartxAxis
            },
        ],
        //图表Y轴样式
        yAxis: [
            {
                type: 'value',
                splitArea: { show: true }
            }
        ],
        //图表Y轴数据加载和图表类型设置
        series: [
            value
        ]
    });
}