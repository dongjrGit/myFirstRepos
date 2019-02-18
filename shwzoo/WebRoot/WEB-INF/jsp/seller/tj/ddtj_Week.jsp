<!-- @{
    ViewBag.Title = "卖家中心-最近一周订单";
    Layout = "~/Areas/Seller/Views/Shared/_Layout_Seller_Center.cshtml";
} -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="/resource/public/seller/js/DdTj/ddtj.js"></script>
<div id="container">
<input type="hidden" id="userid" value="${userid}">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 订单统计 &gt; 详细订单统计
        </div><!--所在位置信息  结束 -->
       <div class="the-form">
            <span>
                <label>订单日期：</label>
                <input type="text" id="time" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'time1\')}' })" value="" readonly="readonly" class="Wdate" />-
                <input type="text" id="time1" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'time\')}' })" value="" readonly="readonly" class="Wdate" />
            </span>
            <input class="but-comm" name="searchW" type="button" value="查询">
        </div>
        <div class="clear"></div>
        <div class="thgl">
            <table>
               <tr class="blank-tr"><td colspan="16"><div style="height:10px;"></div></td></tr>
                <tr id="dd_title">
                <th width="10%">商品名称</th>
                <th width="10%">订单日期	</th>
                <th width="10%">单价（元）</th>
                <th width="10%">数量</th>
                <th width="10%">已使用（数量/金额）</th>
                <th width="10%">未使用（数量/金额）</th>
                <th width="10%">已退款（数量/金额）</th>
                <th width="10%">待付款（数量/金额）</th>
                <th width="10%">退款中（数量/金额）</th>
                <th width="10%">出票中（数量/金额）</th>
            </tr>
            <tbody id="datalist">
                <script id="ddlist" type="text/html">
                    {{each list as saleorder i}}
                    <tr>
                        <td>{{saleorder.proname}}</td>
                        <td>
                           {{saleorder._orderDate}}
                        </td>
                        <td>{{saleorder.proprice | toFixed}}</td>
                        <td>{{saleorder.procount}}</td>
                        <td>{{saleorder.count_YSY}}/{{saleorder.money_YSY | toFixed}}</td>
                        <td>{{saleorder.count_DSY}}/{{saleorder.money_DSY | toFixed}}</td>
                        <td>{{saleorder.count_YTK}}/{{saleorder.money_YTK | toFixed}}</td>
                        <td>{{saleorder.count_DFK}}/{{saleorder.money_DFK | toFixed}}</td>
                        <td>{{saleorder.count_TKSH}}/{{saleorder.money_TKSH | toFixed}}</td>
                        <td>{{saleorder.count_CPZ}}/{{saleorder.money_CPZ | toFixed}}</td>
                        </tr>
                        {{/each}}
                    </script>
                </tbody>
            </table>
        </div><!--thgl 表格部分结束 -->
        <div class="clear"></div>
        <div id="pager" class="page">

        </div>
    </div><!--主要内容 右边结束 -->
</div>
