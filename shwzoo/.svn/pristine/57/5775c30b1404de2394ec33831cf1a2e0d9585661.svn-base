//图形封装 报表ID，标题，图例，数据源
function DrawEChart(ec, chartid, charttitle, chartlegend, chartvalue) {

    var myChart = ec.init(document.getElementById(chartid), "macarons");//图表主题固定 macarons
    myChart.setOption({
        //标题，每个图表最多仅有一个标题控件
        title: {
            text: charttitle,
            x: 'center'
        },
        //提示框，鼠标悬浮交互时的信息提示
        tooltip: {
            trigger: 'item',
            formatter: "{a}<br/>{b} : {c} ({d}%)"
        },
        //图例，每个图表最多仅有一个图例，混搭图表共享
        legend: {
            orient: 'vertical',
            x: 'left',
            data: chartlegend
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
    name: '访问来源',
    type: 'pie',
    radius: '55%',
    center: ['50%', '60%'],
    data: chartvalue
}
        ]
    });
}