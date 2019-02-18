<!-- @{
    ViewBag.Title = "店铺订单管理-最近一周统计";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="/resource/public/platform/js/tj/ddtj.js"></script>
<div class="mainright">
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
        <span>订单日期：</span>
        <input type="text" id="time" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'time1\')}' })" value="" readonly="readonly" />-
        <input type="text" id="time1" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'time\')}' })" value="" readonly="readonly" />
        <span class="marrig10"></span>
        <input class="inquire" name="searchW" type="button" value="查询">
          <span class="marrig10"></span>
		<span class="notice-fenlei-mk2"><input class="chaxun" name="exportuser" type="button" value="导出"></span>
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="dd_title">
                <th width="10%">店铺名称</th>
                <th width="10%">商品名称</th>
                <th width="10%">订单日期	</th>
                <th width="8%">单价（元）</th>
                <th width="8%">数量</th>
                <th width="9%">已使用（数量/金额）</th>
                <th width="9%">未使用（数量/金额）</th>
                <th width="9%">已退款（数量/金额）</th>
                <th width="9%">待付款（数量/金额）</th>
                <th width="9%">退款中（数量/金额）</th>
                <th width="9%">出票中（数量/金额）</th>
            </tr>
            <tbody id="datalist">
                <script id="ddlist" type="text/html">
                    {{each list as saleorder i}}
                    <tr>
                       <td>{{saleorder.name}}</td>
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
    </div>
    <div class="clear"></div>
    <div id="pager" class="page">

    </div>
</div>
<script type="text/javascript">
$(function(){
	 $("input[name=exportuser]").click(function(){
			daochu();
		});
})
function daochu(){
	if(confirm("确认要导出吗?")){
		  var timef = $("#time").val();
	        var timet = $("#time1").val();
	        if ((timef != undefined && timef != "") || (timet != undefined && timet != "")) {
		var sid = $("#select_shop").attr("data");
		location.href="/platform/tj/exportddtjdetail?shopid="+sid+"&datef="+timef
				+"&datet="+timet;
	        }else{
	        	 alert("请选择日期");
	        }
	}
}
</script>