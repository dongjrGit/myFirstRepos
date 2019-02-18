<!-- @{
    ViewBag.Title = "销售分析-商品明细";
    Layout = "~/Areas/Seller/Views/Shared/_Layout_Seller_Center.cshtml";
}
 -->
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="${ctx }/resource/public/commonjs/MrYangUtil.js"></script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：数据助手 &gt; 销售分析 &gt; 销售明细
        </div><!--所在位置信息  结束 -->
        <input type="hidden" id="shopid" value="${shopid}">
        <div class="l_khqyfb FastSelect">
            <p>快速选择： <a href="javascript:void(0);" data-val="0" class=" CurrentDate">本周</a><a href="javascript:void(0);" class="PreviousWeek" data-val="1">上周</a><a href="javascript:void(0);" data-val="2" class="CurrentMonth">本月</a><a href="javascript:void(0);" class=" PreviousMonth " data-val="3">上月</a></p>
            <div class="mt15">选择日期：<input name="" type="text" class="inp-seller Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'endtime\')}' })" value="" readonly="readonly" id="starttime"> - <input name="" type="text" id="endtime" class="inp-seller mr10 Wdate" onclick="    WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'starttime\')}' })"><input class="but-comm" id="search" type="button" value="查看"></div>
        </div>
        <div class="l_zlb">数据汇总</div>
        <div class="l_sjhzxsmx fix">
            <ul id="sumcountdispaly">
                <script id="sumcount" type="text/html">
                    <li>下单量：<span class="red">{{list.ordercount}}</span></li>
                    <li>下单客户数：<span class="red">{{list.buyercount}}</span></li>
                    <li>下单商品件数：<span class="red">{{list.skucount}}</span></li>
                    <li>下单金额：<span class="red">{{list.ordermoney | toFixed}}</span></li>
                  
                </script>
            </ul>
        </div><!--l_sjhzxsmx  stop -->
        <div class="l_zlb">销售明细 </div>
        <div class="l_zlbcon fix l_jymxbtab">
            <table>
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>商品编号</th>
                        <th>商品名称</th>
                        <th>商品单价</th>
                      
                        <th>下单量</th>
                        <th>下单客户数</th>
                        <th>下单商品件数</th>
                        <th>下单金额</th>
                    </tr>
                </thead>
                <tbody id="alldata">
                    <script id="allstatus" type="text/html">
                        {{each list as ordercount i}}
                        <tr>
                            <td>{{i+1}}</td>
                            <td>{{ordercount.skunum}}</td>
                            <td>{{ordercount.skuname}}</td>
                            <td>{{ordercount.skuprice | toFixed}} </td>
                            <td>{{ordercount.ordercount}}</td>
                            <td>{{ordercount.buyercount }} </td>
                            <td>{{ordercount.skucount}}</td>
                            <td>{{ordercount.ordermoney | toFixed}}</td>
                        </tr>
                        {{/each}}
                    </script>
                </tbody>
            </table>
           </div> <div class="clear"></div><!--l_zlbcon  stop -->
            <div id="pager" class="page">

            </div><!--l_ysxsmx  stop -->
           
        </div>
    </div><!--主要内容 右边结束 -->
</div>
<!-- <script src="~/js/MrYangUtil.js"></script> 
<script src="${ctx }/js/jquery.min.js"></script>-->
<script>
    $(function () {
        var date = new Date();
        $("#starttime").val(date.Format());
        $("#endtime").val(date.Format());
        getlist(1);
        $(".FastSelect").children().find("a").each(function (i) {
            var $obj = $(this);
            $obj.bind("click", function () { FastSelect($obj.attr("data-val")) });
        });
        $("#search").bind("click", function () { getlist(1); });
    });

    function getlist(index) {
        var psize = 10;
        var timef = $("#starttime").val();
        var timet = $("#endtime").val();
        var shopid = $("#shopid").val();
        
        if (timef == undefined || timef == "" || timet == undefined || timet == "") {
        } else {
            $.ajax({
                url: "/seller/analysis/SalesDetails",
                type: "Post",
                data: { "starttime": timef, 
                	"endtime": timet,
                	"shopid":shopid,
                	"page": index,
                	"size": psize},
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        Dalert(data.desc);
                    } else {
                        var listdata = {
                            list: data.data.skuSalasDetails
                        }

                        var html = template('allstatus', listdata);
                        //html 填充
                        $("#alldata").html(html);
                        var listdata = {
                            list: data.data
                        }
                        html = template('sumcount', listdata);
                        //html 填充
                        $("#sumcountdispaly").html(html);
                        pcount = data.maxRow;
                        pindex = data.pageIndex;
                       //分页
                      $("#pager").html(pagination(pcount, pindex, psize, "getlist"));
                    }
                },
                error: function () {

                }
            });
        }
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
                $obj.removeClass("red");// active
            }
        });

    }
</script>   
