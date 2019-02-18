<!-- @{
    ViewBag.Title = "商品楼层列表";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script src="${ctx }/resource/public/commonjs/showImage.js"></script>
<script src="${ctx }/resource/public/platform/js/SY/profloors.js"></script>
<div class="mainright">
<input type="hidden" id="imgsrc" value="<%=path %>" />
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
       <!--  <span>显示位置：<input type="text" id="fldisplay" class="inp-seller" /></span> -->
        <span class="marrig10"></span>
        <span>
            类型：
            <select id="profltype" class="the-form-select-one">
                <option value="">所有</option>
                <option value="0">商品</option>
                <option value="1">品牌</option>
                <option value="2">分类</option>
                <option value="3">专题</option>
            </select>
        </span>
        <span class="marrig10"></span>
        <input class="chaxun" name="select_button" id="searchpro" type="button" value="查询" onclick="">
        <span class="marrig10"></span>
        <a href="javascript:;" target="_self"><input class="chaxun" name="add" type="button" value="+添加楼层商品"></a>
        <a href="javascript:;" target="_self"><input class="chaxun" name="back" type="button" value="返回"></a>

        <input type="hidden" value="0" id="marktp" />
    </div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="list_title">
            	
                <th>图片</th>
                <th>排序</th>
                <th>类型</th>
                <th>地址链接</th>
                <th>显示位置</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
            <script id="ProFloorlist" type="text/html">
                {{each list as pf i}}
                <tr>
					
                    <td class="ppimg"><img src="<%=path %>{{pf.img}}" width="100px" height="80px"></td>
                    <td><input class="data_list_cs" id='ob_{{pf.id}}' name="" type="text" value='{{pf.orderby}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setValue({{pf.id}},'ob_{{pf.id}}',1)">保存</a></span></td>
					<td>{{if pf.protype==0}}商品{{else if pf.protype==1}}品牌 {{else if pf.protype==2}}分类{{else if pf.protype==3}}专题 {{/if}}</td>
                    <td>{{pf.url}}</td>
                    <td>{{pf.display}}</td>
					<td>{{pf.description}}</td>
                    <td>
                        <a href="/platform/floor/showProEdit?flid={{pf.floorid}}&id={{pf.id}}"><span  class="shenlan" title="修改">修改</span></a>
                        <a href="javascript:void(0);" onclick="Del({{pf.id}})"><span class="shenlan" title="删除">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
        <div id="bigimg" class="l_lbimg" style="display:none;">
            <img alt="" />
            <div class="l_close">X</div>
        </div>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div><!--page stop -->
        <div class="clear"></div>
        <div class="mar35"></div>
    </div><!--table-con  stop -->

</div><!--mainright stop -->
