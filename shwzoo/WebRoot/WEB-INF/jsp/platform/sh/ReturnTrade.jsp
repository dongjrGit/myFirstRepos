<!-- @{
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
    ViewBag.Title = "返修与退换货";
    var shopID = ViewBag.ShopID;
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="${ctx }/resource/public/platform/js/AfterService/ReturnTrade.js"></script>
<div class="mainright">


    <div class="clear"><input type="hidden" id="shopID" value="@shopID" /></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>订单号：<input name="" type="text" id="search" class="inp-seller" value="请输入订单号" onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"></span>
        <span class="marrig10"></span>
        <span>申请时间：<input type="text" id="time" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd' })" class="text_time" /></span>
        <span class="marrig10"></span>
        <span><input class="inquire" name="" type="button" id="searchBtn" value="搜索"></span>

    </div><!--account-form stop -->
    <div class="clear"></div>
    <div class="mar35"></div>

    <div class="table-con">
        <table class="data_list">
            <tr id="trdata">
                <th>订单号</th>
                <th>用户名称</th>
                <th>联系电话</th>
                <th>描述</th>
                <th>申请时间</th>
                <th>状态</th>
				<th>操作</th>	
            </tr>
            <script type="text/html" id="datalist">
                {{each list as re i}}
                <tr>
                    <td>{{re.ordercode}}</td>
                    <td>{{re.name}}</td>
                    <td>{{re.mobile}}</td>
                    <td>{{re.content}}</td>
                    <td>{{re._createtime}}</td>
                    {{if re.status==9}}
                    <td class="fxythh"><span class="red">申请退款退货中</span></td>
                    {{else}}
                    {{if re.status==10}}
                    <td class="fxythh"><span class="red">已退款</span></td>
 					{{/if}}
                    {{/if}}
					<td>
					   <a href='/platform/ddgl/ddgl_OrderDetailTh?id={{re.orderid}}'><span class='shenlan'>查看详情</span></a>
					  <a href='/platform/sh/editNote?id={{re.id}}'><span class='shenlan'>编辑备注</span></a>
					   {{if re.status==9}}<a href="javascript:void(0);" onclick="Service.rollbackMony({{re.id}})"><span class="shenlan" title="退款">退款</span></a>{{/if}}
					 <a href="javascript:void(0);" onclick="Service.del({{re.id}})"><span class="shenlan" title="删除">删除</span></a>
					</td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--table-con  stop -->
    <div class="clear"></div>

    <div class="page" id="pager">
       <a href="javascript:void(0);">&lt;</a>
            <a href="javascript:void(0);">1</a>
            <span class="page-hover"><a href="javascript:void(0);">&gt;</a></span>
            <span class="page-style">共1页,</span>
            到第<input name="" type="text" class="page-inp">页
            <input name="" type="button" value="确认" class="page-but">

    </div><!--page stop -->


</div><!--mainright stop -->
