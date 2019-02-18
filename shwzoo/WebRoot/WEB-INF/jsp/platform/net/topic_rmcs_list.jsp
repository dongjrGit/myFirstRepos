
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script src="${ctx }/resource/public/commonjs/showImage.js"></script>
<script src="${ctx }/resource/public/platform/js/SY/Specials.js"></script>
<div class="mainright">
<input type="hidden" id="imgsrc" value="<%=path %>" />
<!-- 是否是系统专题 0否 1是  -->
<input type="hidden" name="issys" id="issys" value="1"/>
    <div class="account-form">
        <span>标题：<input type="text" id="spname" class="inp-seller" /></span>
        <span class="marrig10"></span>
       <!--  <span>显示位置：<input type="text" id="spdisplay" class="inp-seller" /></span>
        <span class="marrig10"></span> -->
        <span>
            <input type="hidden" id="spmark" value="${topicmark }"/>
        </span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="chaxun" name="select_button" type="button" value="查询"></span>
        <a href="/platform/topic/rmcsAdd" target="_self"><input class="chaxun" name="add" type="button" value="+添加地方馆"></a>
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="list_title">
                <th>标题</th>
                <th>图片</th>
                <th>状态</th>
                <th>描述</th>                
                <th>操作</th>
            </tr>       
            <script id="speciallist" type="text/html">
                {{each list as sp i}}
                <tr>
                    <td>{{sp.title}}</td>
                    <td class="ppimg"><img src="<%=path %>{{sp.imgurl}}" width="100px" height="80px"></td>
					<td>{{if sp.status==1}}禁用{{else if sp.status==0}}启用 {{/if}}</td>                   
					<td>{{sp.description}}</td>                    
                    <td>
						<a href="/platform/topic/rmcsProList?topicid={{sp.id}}&spmark={{sp.mark}}&type={{sp.type}}" title="专题管理"><span class="shenlan">商品管理</span></a>
                        <a href="/platform/topic/rmcsEdit?id={{sp.id}}"><span class="shenlan" title="修改">修改</span></a>
                        <a href="javascript:void(0);" onclick="Special.del({{sp.id}})"><span class="shenlan" title="删除">删除</span></a>
						<a href="javascript:void(0);" onclick="Special.edit({{sp.id}})"><span class="shenlan" title="启用禁用">{{if sp.status==1}}设为启用{{else if sp.status==0}}设为禁用{{/if}}</span></a>	
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

