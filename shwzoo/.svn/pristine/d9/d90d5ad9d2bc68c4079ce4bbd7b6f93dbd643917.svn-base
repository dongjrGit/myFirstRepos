<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="${ctx }/resource/public/platform/js/gbc/tglx_list.js"></script>
<div class="mainright">
    <div class="account-form">
        <span>团购分类：<input type="text" id="spname" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="chaxun" name="select_button" type="button" value="查询"></span>
        <a href="/platform/tglx/tglx_edit" target="_self"><input class="chaxun" name="add" type="button" value="+添加团购分类"></a>
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="list_title">
                <th>团购分类</th>
                <th>排序</th>                
                <th>操作</th>
            </tr>       
            <script id="speciallist" type="text/html">
                {{each list as sp i}}
                <tr>
                    <td>{{sp.name}}</td>
					<td>{{sp.sort}}</td>
                    <td>
                        <a href="/platform/tglx/tglx_edit?id={{sp.id}}"><span class="shenlan" title="修改">修改</span></a>
                        <a href="javascript:void(0);" onclick="Tglx.del({{sp.id}})"><span class="shenlan" title="删除">删除</span></a>
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

