<!-- @{
    ViewBag.Title = "商品销售概况";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="${ctx }/resource/public/commonjs/MrYangUtil.js"></script>
<!-- <script src="~/js/ECharts/www/js/echarts.js"></script>
<script src="~/js/ECharts/ECharts.js"></script> -->
<div class="mainright">
    <!--l_wzmb  开始 -->
    <div class="l_wzmb fix">
        <div class="l_wzmbtop fix">
            <ul>
                <li class="sj_hover"><a href="#">商品销售概况</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <!-------------pad10  begin --------------->
        <div class="pad10 fix">
            <div class="l_khqyfb FastSelect">
                <p>快速选择： <a href="javascript:void(0);" data-val="0" class=" CurrentDate">本周</a><a href="javascript:void(0);" class="PreviousWeek" data-val="1">上周</a><a href="javascript:void(0);" data-val="2" class="CurrentMonth">本月</a><a href="javascript:void(0);" class=" PreviousMonth " data-val="3">上月</a></p>
                <div style="margin-top:10px;"></div>
                <div class="mt15">
                    <span>所属店铺：</span><span><input id="select_shop" type="text" class="inp-seller" style="margin-top:0px;" /></span>
                    <div style="margin-left:12px;">
                        <ul>
                            <script id="select_shoplist" type="text/html">
                                {{each list as shop i}}
                                <li data="{{shop.id}}">{{shop.name}}</li>
                                {{/each}}
                            </script>
                        </ul>
                    </div>
                    选择日期：<input name="" type="text" class="inp-seller Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'endtime\')}' })" value="" readonly="readonly" id="starttime"> - <input name="" type="text" id="endtime" class="inp-seller mr10 Wdate" onclick="    WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'starttime\')}' })"><input class="chaxun" id="search" type="button" value="查看">
                </div>
            </div>
            <div class="l_zlb datasumtime ">数据汇总（2015-12-06至2015-12-06）</div>
            <div class="l_sjhzxsmx fix">
                <ul id="datasumtimedisplay">
                    <script id="datasumtime" type="text/html">
                        <li>下单量：<span class="red">{{list.ordercount}}</span></li>
                        <li>下单客户数：<span class="red">{{list.buyercount}}</span></li>
                        <li>下单商品件数：<span class="red">{{list.skucount}}</span></li>
                        <li>下单金额：<span class="red">{{list.ordermoney | toFixed}}</span></li>
                        <li>取消订单量：<span class="red">{{list.cancelcount}}</span></li>
                        <li>取消订单金额：<span class="red">{{list.cancelmoney | toFixed}}</span></li>
                        {{if list.skucount==0}}
                        <li>取消率：<span class="red">{{ list.CancelCount | toFixed}}%</span></li>
                        {{else}}
                        <li>取消率：<span class="red">{{ list.cancelpercent | toFixed}}%</span></li>
                        {{/if}}
                       
                        {{if list.buyercount==0}}
                        <li>客单价：<span class="red">0</span></li>
                        {{else}}
                        <li>客单价：<span class="red">{{list.buyerbymoney | toFixed}}</span></li>
                        {{/if}}
                        {{if list.buyercount==0}}
                        <li>客单量：<span class="red">0</span></li>
                        {{else}}
                        <li>客单量：<span class="red">{{list.buyerbycount | toFixed}}</span></li>
                        {{/if}}
                       
                    </script>
                </ul>

            </div><!--l_sjhzxsmx  stop -->
         <!--    <div class="l_zlb"><ad class="salesdatatime">销售数据报表（2015-12-06至2015-12-06）</ad>@*<div class="l_zlbright lvs"><a href="#"><i></i>下载</a></div>*@</div>
            <div class="l_sjhzxsmx fix">
                @*<div class="l_xszbcon fix" style="padding-left:0px;" id="salesearch">
                        <span><input name="" type="checkbox" value="-1" checked>全部</span>
                        <span><input name="" type="checkbox" value="1" checked>下单量</span>
                        <span><input name="" type="checkbox" value="3" checked>下单客户数</span>
                        <span><input name="" type="checkbox" value="0" checked>下单商品件数</span>
                        <span><input name="" type="checkbox" value="2" checked>下单金额</span>
                    </div>*@l_xszbcon  stop
                <div class="mt15" style="min-height:300px" id="SaleChart"></div>

            </div>l_sjhzxsmx  stop -->
            <div class="l_zlb"><ad class="commoditytop20time">商品TOP20（2015-12-06至2015-12-06）</ad><!-- @*<div class="l_zlbright lvs"><a href="#"><i></i>下载</a></div>*@ --></div>
            <div class="l_zlbcon fix l_jymxbtab">
                <!--box  begin -->
                <div id="box">
                    <ul>
                        <li class="active">下单量</li>
                        <li>下单客户数</li>
                        <li>下单金额</li>
                    </ul>
                    <div id="box_con">
                        <div class="show">
                            <table>
                                <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>商品编号</th>
                                        <th>商品名称</th>
                                        <th>商品单价</th>
                                        <th>下单量</th>
                                    </tr>
                                </thead>
                                <tbody id="ordercountdispaly">
                                    <script id="ordercount" type="text/html">
                                        {{each list as ordercount i}}
                                        <tr>
                                            <td>{{i+1}}</td>
                                            <td>{{ordercount.skunum}}</td>
                                            <td>{{ordercount.skuname}}</td>
                                            <td>{{ordercount.skuprice | toFixed}} </td>
                                            <td>{{ordercount.ordercount}}</td>
                                        </tr>
                                        {{/each}}
                                    </script>
                                </tbody>
                            </table>
                        </div><!-- -->
                        <div>
                            <table>
                                <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>商品编号</th>
                                        <th>商品名称</th>
                                        <th>商品单价</th>
                                        <th>客户数</th>
                                    </tr>
                                </thead>
                                <tbody id="orderuserdispaly">
                                    <script id="orderuser" type="text/html">
                                        {{each list as order i}}
                                        <tr>
                                            <td>{{i+1}}</td>
                                            <td>{{order.skunum}}</td>
                                            <td>{{order.skuname}}</td>
                                            <td>{{order.skuprice | toFixed}} </td>
                                            <td>{{order.buyercount }}</td>
                                        </tr>
                                        {{/each}}
                                    </script>
                                </tbody>
                            </table>
                        </div>
                        <div>
                            <table>
                                <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>商品编号</th>
                                        <th>商品名称</th>
                                        <th>商品单价</th>
                                        <th>下单金额</th>
                                    </tr>
                                </thead>
                                <tbody id="ordermoneydispaly">
                                    <script id="ordermoney" type="text/html">
                                        {{each list as order i}}
                                        <tr>
                                            <td>{{i+1}}</td>
                                            <td>{{order.skunum}}</td>
                                            <td>{{order.skuname}}</td>
                                            <td>{{order.skuprice | toFixed}} </td>
                                            <td>{{order.ordermoney | toFixed}}</td>
                                        </tr>
                                        {{/each}}
                                    </script>
                                </tbody>

                            </table>
                        </div>
                    </div>
                </div>

                <!--box  stop -->
            </div><!--l_zlbcon  stop -->
        </div>
        <!-------------pad10  stop --------------->
    </div>
    <!--l_wzmb  结束 -->
</div><!--mainright stop -->

<script type="text/javascript">
    $(function () {
        var oUl = document.getElementById("box").getElementsByTagName("ul")[0];
        var aLi = oUl.getElementsByTagName("li");
        var aDiv = document.getElementById("box_con").getElementsByTagName("div");
        var i = 0;
        for (i = 0; i < aLi.length; i++) {
            aLi[i].index = i;
            aLi[i].onmouseover = function () {
                for (i = 0; i < aLi.length; i++) {
                    aLi[i].className = "";
                    aDiv[i].className = ""
                }
                this.className = "active";
                aDiv[this.index].className = "show";
            };
        }
    });
    var SaleChart;
    var xdata;
    var salelistdata;
    var legendData;
    $(function () {
        var date = new Date();
        //  SaleChart = CreateEChart_Lines("SaleChart")
        $("#starttime").val(date.Format());
        $("#endtime").val(date.Format());
        legendData = new Array();
        legendData.push("商品件数");
        legendData.push("下单量");
        legendData.push("下单金额");
        legendData.push("客户数");
        getdatalist();
        $(".FastSelect").children().find("a").each(function (i) {
            var $obj = $(this);
            $obj.bind("click", function () { FastSelect($obj.attr("data-val")) });
        });
        $("#search").bind("click", function () { getdatalist(); });
        autoxl.bind("select_shop", getShopStartwithName, true);

    });

    function getdatalist() {
        var timef = $("#starttime").val();
        var timet = $("#endtime").val();
        if (timef == undefined || timef == "" || timet == undefined || timet == "") {
            alert("请选择时间段！");
        } else {
            $.ajax({
                url: "/platform/analysis/getSalesAnalysis",
                type: "Post",
                data: { "starttime": $("#starttime").val(), 
                	"endtime": $("#endtime").val(), 
                	"shopid": $("#select_shop").attr("data") },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        var listdata = {
                            list: data.data
                        }
                        //数据汇总
                        var html = template('datasumtime', listdata);
                        $("#datasumtimedisplay").html(html);
                        //下单量
                        listdata = {
                            list: data.data.skubyorder
                        }
                        var html = template('ordercount', listdata);
                        $("#ordercountdispaly").html(html);
                        //客户数
                        listdata = {

                            list: data.data.skubybuyer
                        }
                        var html = template('orderuser', listdata);
                        $("#orderuserdispaly").html(html);
                        //下单金额
                        listdata = {

                            list: data.data.skubymoney
                        }
                        var html = template('ordermoney', listdata);
                        $("#ordermoneydispaly").html(html);

                        setdatasumtime();
                       /*  salelistdata = new Array();
                        salelistdata.push(chartdata('商品件数', data.data.skucount));
                        salelistdata.push(chartdata('下单量', data.data.skubyorder));
                        salelistdata.push(chartdata('下单金额', data.data.skubymoney));
                        salelistdata.push(chartdata('客户数', data.data.skubybuyer));
                        xdata = data.data.time;
                        ShowChart(legendData, salelistdata, xdata); */
                    }
                },
                error: function (e) {
                    Dalert(JSON.stringify(e));
                }
            });
        }
    }

    function ShowChart(legenddata, saledata, time) {
        if (SaleChart == null) {
            CreateEChart_Lines("SaleChart", function (x) {
                SaleChart = x;
            }, "销售数据报表", "销售数据报表", legenddata, time, saledata);
        }
        else {

            SaleChart.setOption(CreateEChart_Lines_option("销售数据报表", "销售数据报表", legenddata, time, saledata), true);
        }
    }

    function chartdata(name, data) {
        return { name: name, type: 'line', data: data };
    }

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

    function setdatasumtime() {
        $(".datasumtime").html("数据汇总（" + $("#starttime").val() + "至" + $("#endtime").val() + ")");
        $(".commoditytop20time").html("商品TOP20（" + $("#starttime").val() + "至" + $("#endtime").val() + ")");
        /* $(".salesdatatime").html("销售数据报表（" + $("#starttime").val() + "至" + $("#endtime").val() + ")");  */
    }
    /*
callback 成功 有数据时 调的方法
event 事件
*/
    function getShopStartwithName(callback, event) {
        var name = $("#select_shop").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/platform/shop/queryShopByLikeName",
            type: "Post",
            data: { "name": name },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('select_shoplist', listdata);

                    if (callback) {
                        callback(html);
                    }
                } else {
                    Dalert(data.desc);
                }
            },
            error: function () {

            }
        });
    }
</script>
