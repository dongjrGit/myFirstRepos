<!-- @{
    ViewBag.Title = "闪购统计";
    Layout = "~/Areas/Seller/Views/Shared/_Layout_Seller_Center.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="${ctx }/resource/public/commonjs/MrYangUtil.js"></script>
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：数据助手 &gt; 促销分析 &gt; 闪购数据分析
        </div><!--所在位置信息  结束 -->
          <input type="hidden" id="shopid" value="${shopid}">
        <div class="l_khqyfb FastSelect">
            <p>快速选择： <a href="javascript:void(0);" data-val="0" class=" CurrentDate">本周</a><a href="javascript:void(0);" class="PreviousWeek" data-val="1">上周</a><a href="javascript:void(0);" data-val="2" class="CurrentMonth">本月</a><a href="javascript:void(0);" class=" PreviousMonth " data-val="3">上月</a></p>
            <div style="margin-top:10px;"></div>
            <div class="mt15">
                活动编号：<input id="num" type="text" class="text-inp" />
                选择日期：<input name="" type="text" class="text-inp Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'endtime\')}' })" value="" readonly="readonly" id="starttime"> - <input name="" type="text" id="endtime" class="text-inp mr10 Wdate" onclick="    WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'starttime\')}' })"><input class="but-comm" id="search" type="button" value="查看">
            </div>
        </div>
        <div class="l_zlb datasumtime ">数据汇总</div>
        <div class="l_sjhzxsmx fix">
            <ul id="datasumtimedisplay">
                <script id="datasumtime" type="text/html">
                    <li>活动数：<span class="red">{{list.actCount}}</span></li>
                    <li>涉及商品数量：<span class="red">{{list.skuCount}}</span></li>
                    <li>涉及订单量：<span class="red">{{list.orderCount}}</span></li>
                    <li>涉及订单金额：<span class="red">{{list.orderMoney | toFixed}}</span></li>
                    <li>优惠金额：<span class="red">{{list.yhMoney | toFixed}}</span></li>
                </script>
            </ul>

        </div><!--l_sjhzxsmx  stop -->
      <!--   <div class="l_zlb"><ad class="counttop10">涉及订单量TOP10</ad></div>
        <div class="l_sjhzxsmx fix" id="CountChart">

        </div>l_sjhzxsmx  stop
        <div class="l_zlb"><ad class="moneytop10">涉及订单金额TOP10</ad></div>
        <div class="l_sjhzxsmx fix" id="MoneyChart">

        </div>l_sjhzxsmx  stop -->
        <div class="l_zlb"><ad class="commoditytime">活动明细</ad></div>
        <div class="l_zlbcon fix l_jymxbtab">
            <table>
                <tr>
                    <th>序号</th>
                    <th>编号</th>
                    <th>名称</th>
                   
                    <th>涉及订单数</th>
                    <th>涉及订单金额</th>
                    <th>优惠金额</th>
                </tr>
                <tbody id="datalist">
                    <script id="detaillist" type="text/html">
                        {{each list as detail i}}
                        <tr>
                            <td>{{i+1}}</td>
                            <td>{{detail.activityNum}}</td>
                            <td>{{detail.activityName}}</td>
                        
                            <td>{{detail.orderCount}}</td>
                            <td>{{detail.orderMoney | toFixed}}</td>
                            <td>{{detail.yhMoney | toFixed}} </td>
                        </tr>
                        {{/each}}
                    </script>
                </tbody>
            </table>
        </div><!--l_zlbcon  stop -->
        <div class="clear"></div>
        <div id="pager" class="page">

        </div>
    </div>
</div><!--mainright stop -->
<!-- <script src="~/js/MrYangUtil.js"></script>
@*引用图表js*@
<script src="~/js/ECharts/www/js/echarts.js"></script>
<script src="~/js/ECharts/ECharts.js"></script> -->

<script type="text/javascript">
    var SaleChart;
    var xdata;
    var salelistdata;
    var legendData;
    $(function () {
        var date = new Date();
        $("#starttime").val(date.Format());
        $("#endtime").val(date.Format());
        getdatalist(1);
        $(".FastSelect").children().find("a").each(function (i) {
            var $obj = $(this);
            $obj.bind("click", function () { FastSelect($obj.attr("data-val")) });
        });
        $("#search").bind("click", function () { getdatalist(1); });

    });
    var charts, chartsm;
    //定义图例和数据数组
    var clistdata, mlistdata;
    var clegendData, mlegendData;
    function getdatalist(index) {
        var pcount, pindex, psize = 10;
        var timef = $("#starttime").val();
        var timet = $("#endtime").val();
        var shopid = $("#shopid").val();
        if (timef == undefined || timef == "" || timet == undefined || timet == "") {
            alert("请选择时间段！");
        } else {
            $.ajax({
                url: "/seller/analysis/getspikeSG",
                type: "Post",
                data: { "page": index,
                	"size": psize, 
                	"starttime": timef,
                	"endtime": timet,
                	"shopid":shopid,
                	"num": $("#num").val() },
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

                        //活动明细
                        listdata = {

                            list: data.data.packageList
                        }
                        var html = template('detaillist', listdata);
                        $("#datalist").html(html);
                        pcount = data.maxRow;
                        pindex = data.pageIndex;
                        //分页
                        $("#pager").html(pagination(pcount, pindex, psize, "getdatalist"));
/* 
                        if (listdata.list.length > 0) {
                            clistdata = new Array(); mlistdata = new Array();
                            clegendData = new Array(); mlegendData = new Array();
                            //涉及订单数量TOP10
                            var OrderByCount = {
                                list: data.Data.OrderByCount
                            }
                            if (OrderByCount.list.length > 0) {
                                for (var i = 0; i < OrderByCount.list.length; i++) {
                                    clegendData.push(OrderByCount.list[i].ActivityNum);
                                    clistdata.push({ name: OrderByCount.list[i].ActivityNum, value: OrderByCount.list[i].OrderCount });
                                }
                                //创建饼图
                                $("#CountChart").css("min-height", "300px");
                                EChart_Pie("CountChart", "", "", clegendData, "涉及订单数", clistdata, OrderByCount.list[0].UserCount, callback, charts);
                            }
                            else {
                                $("#CountChart").css("max-height", "40px");
                            }
                            if (OrderByCount.list.length > 0) {
                                //涉及订单金额TOP10
                                var OrderByMoney = {
                                    list: data.Data.OrderByMoney
                                }
                                for (var i = 0; i < OrderByMoney.list.length; i++) {
                                    mlegendData.push(OrderByMoney.list[i].ActivityNum);
                                    mlistdata.push({ name: OrderByMoney.list[i].ActivityNum, value: OrderByMoney.list[i].OrderMoney });
                                }
                                //创建饼图
                                $("#MoneyChart").css("min-height", "300px");
                                EChart_Pie("MoneyChart", "", "", mlegendData, "涉及订单金额", mlistdata, OrderByMoney.list[0].UserCount, callbackm, chartsm);
                            }
                            else {
                                $("#MoneyChart").css("max-height", "40px");
                            }
                        } else {
                            $("#CountChart").css("max-height", "40px");
                            $("#MoneyChart").css("max-height", "40px");
                        } */
                        setdatasumtime();
                    }
                },
                error: function (e) {
                    //Dalert(JSON.stringify(e));
                }
            });
        }
    }
    function callback(ec) {
        charts = ec;
    }
    function callbackm(ec) {
        chartsm = ec;
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
        $(".counttop10").html("涉及订单量TOP10（" + $("#starttime").val() + "至" + $("#endtime").val() + ")");
        $(".moneytop10").html("涉及订单金额TOP10（" + $("#starttime").val() + "至" + $("#endtime").val() + ")");
        $(".commoditytime").html("活动明细（" + $("#starttime").val() + "至" + $("#endtime").val() + ")");
    }
</script>

