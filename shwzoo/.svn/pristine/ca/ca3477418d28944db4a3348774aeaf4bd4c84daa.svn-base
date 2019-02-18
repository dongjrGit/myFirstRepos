<!-- @{
    ViewBag.Title = "店铺订单管理-按季度统计";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="/resource/public/platform/js/tj/ddtj.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="account-form">
        <span>所属店铺：<input id="select_shop" type="text" class="inp-seller" /></span>
        <div>
            <ul>
                <script id="select_shoplist" type="text/html">
                    {{each list as shop i}}
                    <li data="{{shop.id}}">{{shop.name}}</li>
                    {{/each}}
                </script>
            </ul>
        </div>
        <span class="marrig10"></span>
        <span>订单季度：</span>
        <input type="text" id="time" onclick="WdatePicker({dateFmt:'yyyy-M季度', isQuarter:true,disabledDates:['....-0[5-9]-..','....-1[0-2]-..'], startDate:'%y-01-01' })" value="" readonly="readonly" class="Wdate" />
        <span class="marrig10"></span>
        <input class="inquire" name="search" type="button" value="查询">
          <span class="marrig10"></span>
		<span class="notice-fenlei-mk2"><input class="chaxun" name="exportuser" type="button" value="导出"></span>
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="dd_title">
                <th width="10%">店铺名称</th>
                <th width="15%">商品名称</th>
                <th width="12%">年/季度</th>
                <th width="10%">单价（元）</th>
                <th width="8%">数量</th>
                <th width="15%">已使用（数量/金额）</th>
                <th width="15%">未使用（数量/金额）</th>
                <th width="15%">已退款（数量/金额）</th>
            </tr>
            <tbody id="datalist">
                <script id="ddlist" type="text/html">
                    {{each list as saleorder i}}
                    <tr>
                       	<td>{{saleorder.name}}</td>
                        <td>{{saleorder.proname}}</td>
                        <td>
                           {{saleorder.datestr}}
                        </td>
                        <td>{{saleorder.proprice | toFixed}}</td>
                        <td>{{saleorder.procount}}</td>
                        <td>{{saleorder.count_YSY}}/{{saleorder.money_YSY | toFixed}}</td>
                        <td>{{saleorder.count_DSY}}/{{saleorder.money_DSY | toFixed}}</td>
                        <td>{{saleorder.count_YTK}}/{{saleorder.money_YTK | toFixed}}</td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
    </div>
    <div class="clear"></div>
    <div id="pager" class="page">

    </div>
</div>
<script>
$(function(){
	solist.bind(4);
	 $("input[name=exportuser]").click(function(){
			daochu();
		});
})
function daochu(){
	if(confirm("确认要导出吗?")){
		 var timef = $("#time").val();
		 if (timef != undefined && timef != "") {
	        timef = timef.substring(0,6);
		var sid = $("#select_shop").attr("data");
		location.href="/platform/tj/exportddtjquarter?shopid="+sid+"&datef="+timef;
		}else{
			 alert("请选择季度");
		}
	}
}
</script>
