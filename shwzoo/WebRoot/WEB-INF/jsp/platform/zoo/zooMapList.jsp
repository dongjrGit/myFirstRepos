<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<style type="text/css">
</style>

<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/zooMap/zooMap_list.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/zooCommon.js"></script>
<div class="clear"></div>
<div id="overlay"></div>
<div class="mainright">
	<div class="clear"></div>
	<div class="dotted mar35"></div>
	<div class="account-form">
	
	<c:choose>
		<c:when test="${type==1}">
			<span class="marrig10"></span> <input style="cursor: pointer;" class="chaxun"  onclick="ZooMap.add(1);" type="button" value="+添加地图">
		</c:when>
		<c:when test="${type==2}">
			<span class="marrig10"></span> <input style="cursor: pointer;" class="chaxun"  onclick="ZooMap.add(2);" type="button" value="+添加导游图">
		</c:when>
	</c:choose>
	</div>
	<!--notice stop -->
	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="list_title">
			<th width="5%">序号</th>
			<th width="15%">名称</th>
			<th width="10%">创建时间</th>
			<th width="5%">创建人</th>
			<th width="15%">操作</th>
			</tr>
			<script id="zooMaplist" type="text/html">
                {{each list as bean i}}
                <tr>
					<td>{{i + 1}}</td>	
                    <td>{{bean.name}}</td>
					<td>{{bean.createTimeStr}}</td>
                    <td>{{bean.operator}}</td>
                    <td>
						<a href="javascript:void(0);" onclick="ZooMap.delCheck({{bean.id}})" title="删除"><span class="shenlan">删除</span></a>					
						<a href="/zoo/zooMap/download?path={{bean.path}}"  title="下载"><span class="shenlan">下载</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
		</table>
		
		<div class="clear"></div>
		<!--page stop -->
	</div>
	<!--table-con  stop -->

</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {
	    ZooMap.getAll('${type}','${state}');
	});
</script>
