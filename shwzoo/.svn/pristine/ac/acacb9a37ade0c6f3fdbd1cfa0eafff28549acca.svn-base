<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<style type="text/css">
</style>

<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/rcmdScenic/rcmdScenic_list.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/zooCommon.js"></script>

<div class="clear"></div>
<div id="overlay"></div>
<div class="mainright">
	<div class="clear"></div>
	<div class="dotted mar35"></div>
	<div class="account-form">
	
	<c:choose>
		<c:when test="${type==1}">
			<span class="marrig10"></span> <input style="cursor: pointer;" class="chaxun"  onclick="add();" type="button" value="+添加剧场">
		</c:when>
		<c:when test="${type==2}">
			<span class="marrig10"></span> <input style="cursor: pointer;" class="chaxun"  onclick="add();" type="button" value="+添加景点">
		</c:when>
		<c:when test="${type==3}">
			<span class="marrig10"></span> <input style="cursor: pointer;" class="chaxun"  onclick="add();" type="button" value="+添加设施">
		</c:when>
		<c:when test="${type==4}">
			<span class="marrig10"></span> <input style="cursor: pointer;" class="chaxun"  onclick="add();" type="button" value="+添加动物互动">
		</c:when>
	</c:choose>
	</div>
	<input type="hidden" id="type" value="${type}"/>
	<input type="hidden" id="count" />
	<!--notice stop -->
	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="list_title">
			<th width="5%">序号</th>
			<c:choose>
				<c:when test="${type==1}">
					<th width="15%">剧场名称</th>
				</c:when>
				<c:when test="${type==2}">
					<th width="15%">景点名称</th>
				</c:when>
				<c:when test="${type==3}">
					<th width="15%">设施名称</th>
				</c:when>
				<c:when test="${type==4}">
					<th width="15%">动物互动名称</th>
				</c:when>
			</c:choose>
				<th width="10%">发布时间</th>
				<th width="5%">发布人</th>
				<th width="15%">操作</th>
			</tr>
			<script id="sceniclist" type="text/html">
                {{each list as bean i}}
                <tr>
					<td>{{i + 1}}</td>	
                    <td>{{bean.scenicname}}</td>
					<td>{{bean.updatetimestr}}</td>
                    <td>{{bean.operator}}</td>
                    <td>
						<a href="${ctx }/zoo/rcmdScenic/listedit?id={{bean.id}}" title="修改"><span class="shenlan">修改</span></a>					
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
		//获取列表信息，默认查询第一页
	    RcmdScenic.getAll();
	});
</script>
