<!-- @{
    ViewBag.Title = "仓库配送 &gt; 配送管理 &gt; 店铺运费模版";
    Layout = "~/Areas/Seller/Views/Shared/_Layout_Seller_Center.cshtml";
}
 -->
 
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>


<script src="${ctx }/resource/public/seller/js/CkPs/subFreightTemplate.js"></script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：仓库配送 &gt; 配送管理 &gt; 店铺运费子模板
        </div><!--所在位置信息  结束 -->
     <div class="the-form">
            <div class="submit-but">
                <input type="hidden" id="ftid" value="${ftid }" />
                <!-- <input name="" id="addTemplate" type="button" value="新建运费模板" class="big-but"> -->
                <!-- <input name="" id="addTemplateInfo" type="button" value="新建模板详细" class="but-comm"> -->
                 <a href="javascript:;" target="_self"><input class="big-but" name="back" type="button" value="返回"> </a>
            </div><!--submit-but  stop -->
        </div><!--表单部分结束 -->
     
        <div class="clear"></div>
        <div class="freight-tab">
            <table>
                <tr id="freightList">
                     <th width="315px">序号</th>
                            <th>地区</th>
                            <th>首件数量</th>
                             <th>首件价格</th>
                            <th>续建数量</th>
                             <th>续建价格</th>
                </tr>
                <script type="text/html" id="datalist">
                    {{each list as ft i}}
                    <tr>
                        <td width="315px">{{i+1}}</td>
                        <td> {{ft.areas}} </td>
                        <td>{{ft.firstcount}}</td>
  						<td>{{ft.firstprice}}</td>
                        <td>{{ft.elsecount}}</td>
						<td>{{ft.elseprice}}</td>
                    </tr>
                    {{/each}}
                </script>
            </table>
            <!--xgyfxxgz 修改运费详细规则  begin -->
            
            </div>
            <!--xgyfxxgz 添加运费模版详细  stop -->
        </div><!--freight-tab  stop -->
    </div><!--主要内容 右边结束 -->