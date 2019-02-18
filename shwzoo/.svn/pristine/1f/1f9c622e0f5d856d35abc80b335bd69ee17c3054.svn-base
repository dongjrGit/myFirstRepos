<!-- @{
    ViewBag.Title = "楼层列表";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script src="${ctx }/resource/public/commonjs/showImage.js"></script>
<script src="${ctx }/resource/public/platform/js/SY/floors.js"></script>
<div class="mainright">
    <div class="account-form">
        <span>楼层名称：<input type="text" id="flname" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>
            楼层类型：
            <select id="fltype" class="the-form-select-one">
                <option value="">所有</option>
                <option value="0">A层</option>
                <option value="1">B层</option>
            </select>
        </span>
       <!--  <span class="marrig10"></span>
        <span>显示位置：<input type="text" id="fldisplay" class="inp-seller" /></span> -->
        <span class="marrig10"></span>       
        <span>
            页面标识：
            <select id="flmark" class="the-form-select-one">
                <option value="">所有</option>
                <option value="0">首页经彩专题</option>
                  
            </select>
        </span>
        <!--   <option value="1">专题页</option>
                <option value="2">店铺页</option>
                <option value="3">商品列表页</option>
                <option value="4">购物车成功页</option>
                <option value="5">购物车页</option>
                <option value="6">支付成功页</option>
                <option value="7">商品详情页</option>
                <option value="8">优惠卷领取页</option>
                <option value="9">订单支付成功页</option>
                <option value="10">订单支付失败页</option>
                <option value="11">店铺商品列表页</option>   -->
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="chaxun" name="select_button" type="button" value="查询"></span>
        <a href="/platform/floor/showadd" target="_self"><input class="chaxun" name="add" type="button" value="+添加楼层信息"></a>
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="list_title">
                <th>楼层名称</th>
                <th>楼层类型</th>               
                <th>页面标识</th>
               <!--  <th>位置</th> -->
                <th>排序</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
            <script id="floorlist" type="text/html">
                {{each list as fl i}}
                <tr>
                    <td>{{fl.name}}</td>
                    <td>{{if fl.type==0}}A层{{else if fl.type==1}}B层{{/if}}</td>
                    <td>{{if fl.pagetag==0}}首页{{else if fl.pagetag==1}}专题页 {{else if fl.pagetag==2}}店铺页{{else if fl.pagetag==3}}商品列表页 {{else if fl.pagetag==4}}购物车成功页{{else if fl.pagetag==5}}购物车页{{else if fl.pagetag==6}}支付成功页{{else if fl.pagetag==7}}商品详情页{{else if fl.pagetag==8}}优惠卷领取页{{else if fl.pagetag==9}}订单支付成功页{{else if fl.pagetag==10}}订单支付失败页{{else if fl.pagetag==11}}店铺商品列表页{{/if}}</td>                   
                    <td><input class="data_list_cs" id='fl_order_{{fl.id}}' name="" type="text" value='{{fl.orderby}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setFloorValue({{fl.id}},'fl_order_{{fl.id}}',1)">保存</a></span></td>
                    <td>{{fl.description}}</td>                    
                    <td><a href="/platform/floor/showProList?flid={{fl.id}}" title="楼层管理"><span class="shenlan">楼层管理</span></a>                                                 
                        <a href="/platform/floor/showEdit?id={{fl.id}}"><span class="shenlan" title="修改">修改</span></a>
                        <a href="javascript:void(0);" onclick="Floor.del({{fl.id}})"><span class="shenlan" title="删除">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>

    </div><!--page stop -->
    <div class="clear"></div>
    <div id="pager" class="page">

    </div>
    <div class="clear"></div>
    <div class="mar35"></div>

</div><!--mainright stop -->
