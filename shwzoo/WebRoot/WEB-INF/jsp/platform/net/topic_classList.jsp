<!-- @{
    ViewBag.Title = "专题管理";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script src="${ctx }/resource/public/commonjs/showImage.js"></script>
<script src="${ctx }/resource/public/platform/js/SY/SpecialProType.js"></script>
<div class="mainright">
<input type="hidden" id="imgsrc" value="<%=path %>" />
    <div class="account-form">
        <span>分类名称：<input type="text" id="spname" class="inp-seller" /></span>
        <span class="marrig10"></span>
       <!--  <span>显示位置：<input type="text" id="spdisplay" class="inp-seller" /></span>
        <span class="marrig10"></span> -->
      <!--   <span>
            页面标识：
            <select id="spmark" class="the-form-select-one">
                <option value="0">所有</option>
                <option value="1">精彩专题</option>
                <option value="2">发现好店</option>
                <option value="3">新品上市</option>   
                <option value="4">发现好货</option>        
            </select>
        </span> -->
        <input type="hidden" id="topicid" value="${topicid}">
         
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="chaxun" name="select_button" type="button" value="查询"></span>
        <a href="/platform/topic/showSpeProEdit?special=${topicid}" target="_self"><input class="chaxun" name="add" type="button" value="+添加分类"></a>
       <!--  <a href="javascript:;" target="_self"><input class="chaxun" name="add" type="button" value="+添加关联专题信息"></a> -->
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="list_title">
                <th>分类名称</th>
                <th>显示名称</th>
                <th>分类图片</th>
                <th>排序</th>
                <th>操作</th>
            </tr>
            <script id="speciallist" type="text/html">
                {{each list as sp i}}
                <tr>
                    <td>{{sp.name}}</td>
 					<td>{{sp.showname}}</td>
                    <td class="ppimg"><img src="<%=path %>{{sp.img}}" width="100px" height="80px"></td>
					<td>{{sp.orderby}}</td>
                    <td>                       
                        <a href="/platform/topic/showSpeProEdit?id={{sp.id}}"><span class="shenlan" title="修改">修改</span></a>
                        <a href="javascript:void(0);" onclick="Special.del({{sp.id}})"><span class="shenlan" title="删除">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>

    </div><!--page stop -->
    <div class="clear"></div>
    <div id="bigimg" class="l_lbimg" style="display:none;">
        <img alt="" />
        <div class="l_close">X</div>
    </div>
    <div class="clear"></div>
    <div id="pager" class="page">

    </div>
    <div class="clear"></div>
    <div class="mar35"></div>

</div><!--mainright stop -->

