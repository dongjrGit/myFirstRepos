
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script src="${ctx }/resource/public/commonjs/showImage.js"></script>
<script src="/resource/public/platform/js/common_platform.js"></script>
<script>

	$(function(){
		initPlatform.Page("/platform/news/listnewsspu","newsspulist","#listpro","#page",{newsid:"${newsid}"});
	});
	
	function del(id){
		initPlatform.Delete("/platform/news/delnewsspu",id);
	}

</script>
<div class="mainright">
<input type="hidden" id="imgsrc" value="<%=path %>" />
<!-- 是否是系统专题 0否 1是  -->
<input type="hidden" name="issys" id="issys" value="0"/>
    <div class="account-form">
       <!--  <span class="notice-fenlei-mk2"><input class="chaxun" name="select_button" type="button" value="查询"></span>
        --> <a href="/platform/news/xgscedit?newsid=${newsid }" target="_self"><input class="chaxun" name="add" type="button" value="+添加商品"></a>
    </div>
    <script id="newsspulist" type="text/html">
                {{each list as spu i}}
			<tr>
                <td>{{spu.spuname}}</td>
                <td><img src="{{spu.spuimg}}" width="50px" height="25px"/></td>
                <td><span class="lvs" id="tt_115"><a onclick="del({{spu.id}})" class="set" href="javascript:void(0);">删除</a></span></td>
            </tr> 
                {{/each}}
            </script>  
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
        	<thead>
            <tr id="list_title">
                <th>商品名称</th>
                <th>图片</th>
                <th>操作</th>
            </tr>  
            </thead>
            <tbody id="listpro">
            
            </tbody>   
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

