
//创建饼状图(单次加载) 参数说明： chartid（图表页面显示dom的id），title（图表标题），subTitle（图表副标题），legendData（图例数据），seriesName(图表名称),seriesData（图表数据),maxValue(数据最大值)
function CreateEChart_Pie(chartid, title, subTitle, legendData, seriesName, seriesData, maxValue) {
    // 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
    require.config({
        paths: {
            echarts: '/js/ECharts/www/js'
        }
    });
    // 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
    require(
        [
            'echarts',
            'echarts/chart/pie',  //按需加载图表关于饼图的部分
            'echarts/chart/funnel'//按需加载图表关于漏斗图的部分
        ],
        //创建ECharts图表
        function (ec) {
            var chart = ec.init(document.getElementById(chartid));
            chart.setOption(option);
        }
    );
    var option = {
        title: {//标题
            text: title,                 //标题
            subtext: subTitle,           //副标题
            x: 'center'                  //水平安放位置，默认为左侧left，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
        },
        tooltip: {//提示框
            trigger: 'item',             //触发类型,默认数据触发item，可选为：'item' | 'axis'
            formatter: "{b} : {c} ({d}%)" //"{a} <br/>{b} : {c} ({d}%)"  内容格式器：{string}（Template） | {Function}
        },
        legend: {//图例
            orient: 'horizontal',          //布局方式，默认为水平布局horizontal，可选为：'horizontal' | 'vertical'
            x: 'left',                   //水平安放位置，默认为全图居中center，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
            data: legendData             //图例数据源，数组格式，例：['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        toolbox: {//工具箱
            show: true,                                       //显示策略，默认false，可选为：true（显示） | false（隐藏）
            feature: {                                        //启用功能
                mark: { show: true },                         //辅助线标志
                dataView: { show: true, readOnly: false },    //数据视图
                magicType: {                                  //动态类型切换
                    show: true,
                    type: ['pie', 'funnel'],                  //视图类型，数组 例：['line', 'bar', 'stack', 'tiled', 'force', 'chord', 'pie', 'funnel']
                    option: {                                 //可选，可传入切换是动态修改的配置，将复写series内的数组项
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: maxValue
                        }
                    }
                },
                restore: { show: true },                      //还原
                saveAsImage: { show: true }                   //保存图片（IE8-不支持）
            }
        },
        calculable: true,   //是否启用拖拽重计算特性，默认false
        series: [           //驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据
            {
                name: seriesName,                             //系列名称，如启用legend，该值将被legend.data索引相关
                type: 'pie',                                  //图表类型，必要重要参数！可选为：'line'（折线图） | 'bar'（柱状图） | 'scatter'（散点图） | 'k'（K线图）|
                //                                 'pie'（饼图） | 'radar'（雷达图） | 'chord'（和弦图） | 'force'（力导向布局图） | 'map'（地图）
                radius: '55%',                                //半径，默认[0, '75%']，支持绝对值（px）和百分比，百分比计算比，min(width, height) / 2 * 75%， 传数组实现环形图，[内半径，外半径]
                center: ['50%', '60%'],                       //圆心坐标，默认['50%', '50%']，支持绝对值（px）和百分比，百分比计算min(width, height) * 50%
                data: seriesData                              //数据内容数组，折线图以及柱状图时数组长度等于所使用类目轴文本标签数组axis.data的长度，并且他们间是一一对应的。
                //例： [
                //        { value: 335, name: '直接访问' },
                //        { value: 310, name: '邮件营销' },
                //        { value: 234, name: '联盟广告' },
                //        { value: 135, name: '视频广告' },
                //        { value: 1548, name: '搜索引擎' }
                //     ]

            }
        ]
    }
}

//创建饼状图(多次加载) 
//参数说明： chartid（图表页面显示dom的id），title（图表标题），subTitle（图表副标题），legendData（图例数据），seriesName(图表名称),seriesData（图表数据),maxValue(数据最大值),callback(回调函数，返回已加载的图表)，ec（图表实例，首次为null）
function EChart_Pie(chartid, title, subTitle, legendData, seriesName, seriesData, maxValue, callback, ec) {
    if (ec == null) {
        // 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
        require.config({
            paths: {
                echarts: '/js/ECharts/www/js'
            }
        });
        // 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
        require(
            [
                'echarts',
                'echarts/chart/pie',  //按需加载图表关于饼图的部分
                'echarts/chart/funnel'//按需加载图表关于漏斗图的部分
            ],
            //创建ECharts图表
            function (ec) {
                var chart = ec.init(document.getElementById(chartid));
                chart.setOption(getoption(title, subTitle, legendData, seriesName, seriesData, maxValue), true);
                callback(chart);
            });
    } else {
        ec.setOption(getoption(title, subTitle, legendData, seriesName, seriesData, maxValue), true);
        callback(ec);
    }

}

//创建饼状图(多次加载) 
//参数说明： chartid（图表页面显示dom的id），title（图表标题），subTitle（图表副标题），legendData（图例数据），seriesName(图表名称),seriesData（图表数据),maxValue(数据最大值),callback(回调函数，返回已加载的图表)，ec（图表实例，首次为null）
function EChart_PieEx(chartid, title, subTitle, legendData, seriesName, seriesData, maxValue, chart) {
    if (chart == null) {
        EChart_Pie(chartid, title, subTitle, legendData, seriesName, seriesData, maxValue, function (x) { chart = x; }, chart);
    }
    else {
        // 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
        chart.setOption(getoption(title, subTitle, legendData, seriesName, seriesData, maxValue), true);
    }
}


//获取饼图展示数据信息
var getoption = function (title, subTitle, legendData, seriesName, seriesData, maxValue) {
    var option = {
        title: {//标题
            text: title,                 //标题
            subtext: subTitle,           //副标题
            x: 'center'                  //水平安放位置，默认为左侧left，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
        },
        tooltip: {//提示框
            trigger: 'item',             //触发类型,默认数据触发item，可选为：'item' | 'axis'
            formatter: "{b} : {c} ({d}%)" //"{a} <br/>{b} : {c} ({d}%)"  内容格式器：{string}（Template） | {Function}
        },
        legend: {//图例
            orient: 'vertical',          //布局方式，默认为水平布局horizontal，可选为：'horizontal' | 'vertical'
            x: 'left',                   //水平安放位置，默认为全图居中center，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
            data: legendData             //图例数据源，数组格式，例：['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        toolbox: {//工具箱
            show: true,                                       //显示策略，默认false，可选为：true（显示） | false（隐藏）
            feature: {                                        //启用功能
                mark: { show: true },                         //辅助线标志
                dataView: { show: true, readOnly: false },    //数据视图
                magicType: {                                  //动态类型切换
                    show: true,
                    type: ['pie', 'funnel'],                  //视图类型，数组 例：['line', 'bar', 'stack', 'tiled', 'force', 'chord', 'pie', 'funnel']
                    option: {                                 //可选，可传入切换是动态修改的配置，将复写series内的数组项
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: maxValue
                        }
                    }
                },
                restore: { show: true },                      //还原
                saveAsImage: { show: true }                   //保存图片（IE8-不支持）
            }
        },
        calculable: true,   //是否启用拖拽重计算特性，默认false
        series: [           //驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据
            {
                name: seriesName,                             //系列名称，如启用legend，该值将被legend.data索引相关
                type: 'pie',                                  //图表类型，必要重要参数！可选为：'line'（折线图） | 'bar'（柱状图） | 'scatter'（散点图） | 'k'（K线图）|
                //                                 'pie'（饼图） | 'radar'（雷达图） | 'chord'（和弦图） | 'force'（力导向布局图） | 'map'（地图）
                radius: '55%',                                //半径，默认[0, '75%']，支持绝对值（px）和百分比，百分比计算比，min(width, height) / 2 * 75%， 传数组实现环形图，[内半径，外半径]
                center: ['50%', '60%'],                       //圆心坐标，默认['50%', '50%']，支持绝对值（px）和百分比，百分比计算min(width, height) * 50%
                data: seriesData                              //数据内容数组，折线图以及柱状图时数组长度等于所使用类目轴文本标签数组axis.data的长度，并且他们间是一一对应的。
            }
        ]
    }
    return option;
}


//创建漏斗图(多次加载) 
//参数说明： chartid（图表页面显示dom的id），title（图表标题），subTitle（图表副标题），legendData（图例数据），seriesName(图表名称),seriesData（图表数据),maxValue(数据最大值),callback(回调函数，返回已加载的图表)，ec（图表实例，首次为null）
function EChart_Funnel(chartid, title, subTitle, legendData, seriesName, seriesData, maxValue, callback, ec) {
    if (ec == null) {
        // 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
        require.config({
            paths: {
                echarts: '/js/ECharts/www/js'
            }
        });
        // 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
        require(
            [
                'echarts',
                'echarts/chart/pie',  //按需加载图表关于漏斗图的部分
                'echarts/chart/funnel'//按需加载图表关于饼图的部分
            ],
            //创建ECharts图表
            function (ec) {
                var chart = ec.init(document.getElementById(chartid));
                chart.setOption(get_funnel_option(title, subTitle, legendData, seriesName, seriesData, maxValue), true);
                callback(chart);
            });
    } else {
        ec.setOption(get_funnel_option(title, subTitle, legendData, seriesName, seriesData, maxValue), true);
        callback(ec);
    }

}
//创建漏斗图(多次加载) 
//参数说明： chartid（图表页面显示dom的id），title（图表标题），subTitle（图表副标题），legendData（图例数据），seriesName(图表名称),seriesData（图表数据),maxValue(数据最大值),callback(回调函数，返回已加载的图表)，ec（图表实例，首次为null）
function EChart_FunnelEx(chartid, title, subTitle, legendData, seriesName, seriesData, maxValue, chart) {
    if (chart == null) {
        EChart_Funnel(chartid, title, subTitle, legendData, seriesName, seriesData, maxValue, function (x) { chart = x; }, chart);
    }
    else {
        // 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
        chart.setOption(get_funnel_option(title, subTitle, legendData, seriesName, seriesData, maxValue), true);
    }
}


//获取漏斗图展示数据信息
var get_funnel_option = function (title, subTitle, legendData, seriesName, seriesData, maxValue) {
    var option = {
        title: {//标题
            text: title,                 //标题
            subtext: subTitle,           //副标题
            x: 'center'                  //水平安放位置，默认为左侧left，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
        },
        tooltip: {//提示框
            trigger: 'item',             //触发类型,默认数据触发item，可选为：'item' | 'axis'
            formatter: "{b} : {c}" //"{a} <br/>{b} : {c} ({d}%)"  内容格式器：{string}（Template） | {Function}
        },
        legend: {//图例
            orient: 'vertical',          //布局方式，默认为水平布局horizontal，可选为：'horizontal' | 'vertical'
            x: 'left',                   //水平安放位置，默认为全图居中center，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
            data: legendData             //图例数据源，数组格式，例：['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        toolbox: {//工具箱
            show: true,                                       //显示策略，默认false，可选为：true（显示） | false（隐藏）
            feature: {                                        //启用功能
                mark: { show: true },                         //辅助线标志
                dataView: { show: true, readOnly: false },    //数据视图
                magicType: {                                  //动态类型切换
                    show: true,
                    type: ['funnel', 'pie'],                  //视图类型，数组 例：['line', 'bar', 'stack', 'tiled', 'force', 'chord', 'pie', 'funnel']
                    option: {                                 //可选，可传入切换是动态修改的配置，将复写series内的数组项
                        funnel: {
                            x: '10%',
                            width: '70%',
                            funnelAlign: 'left',
                            max: maxValue
                        }
                    }
                },
                restore: { show: true },                      //还原
                saveAsImage: { show: true }                   //保存图片（IE8-不支持）
            }
        },
        calculable: true,   //是否启用拖拽重计算特性，默认false
        series: [           //驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据
            {
                name: seriesName,                             //系列名称，如启用legend，该值将被legend.data索引相关
                type: 'funnel',                               //图表类型，必要重要参数！可选为：'line'（折线图） | 'bar'（柱状图） | 'scatter'（散点图） | 'k'（K线图）|
                x: '10%',
                width: '70%',
                funnelAlign: 'left',

                radius: '55%',                                 //'pie'（饼图） | 'radar'（雷达图） | 'chord'（和弦图） | 'force'（力导向布局图） | 'map'（地图）
                center: ['50%', '60%'],                        //半径，默认[0, '75%']，支持绝对值（px）和百分比，百分比计算比，min(width, height) / 2 * 75%， 传数组实现环形图，[内半径，外半径]
                max: maxValue,                                 //圆心坐标，默认['50%', '50%']，支持绝对值（px）和百分比，百分比计算min(width, height) * 50%          
                data: seriesData                              //数据内容数组，折线图以及柱状图时数组长度等于所使用类目轴文本标签数组axis.data的长度，并且他们间是一一对应的。
            }
        ]
    }
    return option;
}


//创建折线表  chartid(图表页面显示dom的id),title(图表标题),subTitle(图表副标题),legendData(图例数据),xaxisData(横轴数据),seriesName(图表名称),seriesData（图表数据),pointData(标注图形数据),lineData(标线图形数据)
function CreateEChart_Line(chartid, title, subTitle, legendData, xaxisData, seriesName, seriesData, pointData, lineData) {
    // 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
    require.config({
        paths: {
            echarts: '/js/ECharts/www/js'
        }
    });
    // 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
    require(
        [
            'echarts',
            'echarts/chart/line',  //按需加载图表关于饼图的部分
            'echarts/chart/bar'    //按需加载图表关于漏斗图的部分
        ],
        //创建ECharts图表
        function (ec) {
            var chart = ec.init(document.getElementById(chartid));
            chart.setOption(option);
        }
    );
    var option = {
        title: {//标题
            text: title,                 //标题
            subtext: subTitle            //副标题
        },
        tooltip: {//提示框
            trigger: 'axis'              //触发类型,默认数据触发item，可选为：'item' | 'axis'
        },
        legend: {//图例
            data: legendData             //图例数据源，数组格式，例：['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        toolbox: {//工具箱
            show: true,                 //显示策略，默认false，可选为：true（显示） | false（隐藏）
            feature: {                  //启用功能
                mark: { show: true },     //辅助线标志
                dataView: { show: true, readOnly: false },         //数据视图
                magicType: { show: true, type: ['line', 'bar'] },  //动态类型切换
                restore: { show: true },                           //还原
                saveAsImage: { show: true }                        //保存图片（IE8-不支持）
            }
        },
        calculable: true,                              //是否启用拖拽重计算特性，默认false
        xAxis: [                                       //直角坐标系中横轴数组，数组中每一项代表一条横轴坐标轴，仅有一条时可省略数组。最多同时存在2条横轴
            {
                type: 'category',                      //坐标轴类型，横轴默认为类目型'category'，可选：'category' | 'value' | 'time' | 'log'
                boundaryGap: false,                    //类目起始和结束两端空白策略，默认为true留空，false则顶头
                data: xaxisData                        //类目列表，同时也是label内容，例：['周一','周二','周三','周四','周五','周六','周日']
            }
        ],
        yAxis: [                                       //直角坐标系中纵轴数组，数组中每一项代表一条纵轴坐标轴，仅有一条时可省略数组。最多同时存在2条纵轴
            {
                type: 'value',                         //坐标轴类型，纵轴默认为数值型'value'，可选：'category' | 'value' | 'time' | 'log'
                axisLabel: {                           //坐标轴文本标签选项
                    formatter: '{value} °C'            //间隔名称格式器：{string}（Template） | {Function}
                }
            }
        ],
        series: [                                      //驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据
            {
                name: seriesName,                       //系列名称，如启用legend，该值将被legend.data索引相关
                type: 'line',                           //图表类型，必要重要参数！可选为：'line'（折线图） | 'bar'（柱状图） | 'scatter'（散点图） | 'k'（K线图）|
                //                'pie'（饼图） | 'radar'（雷达图） | 'chord'（和弦图） | 'force'（力导向布局图） | 'map'（地图）
                data: seriesData,                       //数据内容数组，折线图以及柱状图时数组长度等于所使用类目轴文本标签数组axis.data的长度，并且他们间是一一对应的。例：[11, 11, 15, 13, 12, 13, 10]
                markPoint: {                           //系列中的数据标注内容
                    data: pointData                    //标注图形数据，例：[{type : 'max', name: '最大值'}, {type : 'min', name: '最小值'}]
                },
                markLine: {                            //系列中的数据标线内容
                    data: lineData                     //标线图形数据，例：[{type : 'average', name: '平均值'}]
                }
            }
        ]
    }
}



//创建折线表  chartid(图表页面显示dom的id),salechart(图例实例回调函数),title(图表标题),subTitle(图表副标题),legendData(图例数据),xaxisData(横轴数据),seriesName(图表名称),seriesData（图表数据),pointData(标注图形数据),lineData(标线图形数据)
function CreateEChart_Lines(chartid, salechart, title, subTitle, legendData, xaxisData, seriesData, unit) {

    // 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
    require.config({
        paths: {
            echarts: '/js/ECharts/www/js'
        }
    });
    // 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
    require(
        [
            'echarts',
            'echarts/chart/line',  //按需加载图表关于饼图的部分
            'echarts/chart/bar'    //按需加载图表关于漏斗图的部分
        ],
        //创建ECharts图表
        function (ec) {
            var chart = ec.init(document.getElementById(chartid));
            chart.setOption(CreateEChart_Lines_option(title, subTitle, legendData, xaxisData, seriesData, unit));
            if (salechart != null)
                salechart(chart);
        }
    );
}

var CreateEChart_Lines_option = function (title, subTitle, legendData, xaxisData, seriesData, unit) {
    if (unit == null)
        unit = '';
    var option = {
        title: {//标题
            text: title,                 //标题
            subtext: subTitle            //副标题
        },
        tooltip: {//提示框
            trigger: 'axis'              //触发类型,默认数据触发item，可选为：'item' | 'axis'
        },
        legend: {//图例
            data: legendData             //图例数据源，数组格式，例：['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        toolbox: {//工具箱
            show: true,                 //显示策略，默认false，可选为：true（显示） | false（隐藏）
            feature: {                  //启用功能
                mark: { show: true },     //辅助线标志
                dataView: { show: true, readOnly: false },         //数据视图
                magicType: { show: true, type: ['line', 'bar'] },  //动态类型切换
                restore: { show: true },                           //还原
                saveAsImage: { show: true }                        //保存图片（IE8-不支持）
            }
        },
        calculable: true,                              //是否启用拖拽重计算特性，默认false
        xAxis: [                                       //直角坐标系中横轴数组，数组中每一项代表一条横轴坐标轴，仅有一条时可省略数组。最多同时存在2条横轴
            {
                type: 'category',                      //坐标轴类型，横轴默认为类目型'category'，可选：'category' | 'value' | 'time' | 'log'
                boundaryGap: false,                    //类目起始和结束两端空白策略，默认为true留空，false则顶头
                data: xaxisData                        //类目列表，同时也是label内容，例：['周一','周二','周三','周四','周五','周六','周日']
            }
        ],
        yAxis: [                                       //直角坐标系中纵轴数组，数组中每一项代表一条纵轴坐标轴，仅有一条时可省略数组。最多同时存在2条纵轴
            {
                type: 'value',                         //坐标轴类型，纵轴默认为数值型'value'，可选：'category' | 'value' | 'time' | 'log'
                axisLabel: {                           //坐标轴文本标签选项
                    formatter: '{value} ' + unit            //间隔名称格式器：{string}（Template） | {Function}
                }
            }
        ],
        series: seriesData //[{ name: '下单量', type: 'line', data: [6, 3, 1, 9] }, { name: '下单客户数', type: 'line', data: [4, 7, 4, 9] }]
        //series: [                                      //驱动图表生成的数据内容数组，数组中每一项为一个系列的选项及数据
        //    {
        //        name: seriesName,                       //系列名称，如启用legend，该值将被legend.data索引相关
        //        type: 'line',                           //图表类型，必要重要参数！可选为：'line'（折线图） | 'bar'（柱状图） | 'scatter'（散点图） | 'k'（K线图）|
        //        //                'pie'（饼图） | 'radar'（雷达图） | 'chord'（和弦图） | 'force'（力导向布局图） | 'map'（地图）
        //        data: seriesData,                       //数据内容数组，折线图以及柱状图时数组长度等于所使用类目轴文本标签数组axis.data的长度，并且他们间是一一对应的。例：[11, 11, 15, 13, 12, 13, 10]
        //        markPoint: {                           //系列中的数据标注内容
        //            data: pointData                    //标注图形数据，例：[{type : 'max', name: '最大值'}, {type : 'min', name: '最小值'}]
        //        },
        //        markLine: {                            //系列中的数据标线内容
        //            data: lineData                     //标线图形数据，例：[{type : 'average', name: '平均值'}]
        //        }
        //    }
        //]
    }
    return option;
}