<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function formCancel() {
	
	var isowned = $("#isowned").val();
	if (isowned == 1) {
		location.href = "/platform/ddgl/ddgl_zyOrderList";
	} else {
		location.href = "/platform/ddgl/ddgl_dpOrderList";
	}
}	
</script>

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">物流详情</a><span
					class="sj-img"></span></li>

			</ul>
			<span class="l_fhddlb"><input class="inquire"
				onclick="formCancel()" type="button" value="返回订单列表"></span>
				<input type="hidden" id="isowned" value="${isowned }" />
		</div>
		<!--l_wzmbtop   stop -->

		<!--n_ddxq  begin -->
		<div class="n_ddxq">
			<div class="l_ddhbj">
				 <span>状态：
				 <c:choose>
				  <c:when test="${data.state == 3 }">
				  <span id="status" class="red">您的包裹已被签收</span>
				  </c:when>
				  <c:otherwise>
				    <span id="status" class="red">您的包裹正在派送中...</span>
				  </c:otherwise>
				 </c:choose>
				 </span> <span class="l_fhddlb" name="ddcz"
					style="margin-right: 0px;"></span>
					 <!-- <input type="hidden" id="reason" value=""  /> -->
			</div>
			<!--l_ddhbj   stop -->
			<div class="n_ddxqmk" style="margin-top: 20px;">
				<div class="n_ddxqmktop">物流的状态变更详情</div>
				<div class="n_ddxqtr">
				 
					<div class="n_ddxqtdleft"></div>
					<div class="n_ddxqtdright"></div>
					<table>
                                   <tr class="n_ddgztr">
                                         <td>变更的时间</td>
                                         <td>变更的信息</td>
                                     </tr>
                                     <c:forEach items="${data.traces }" var="trace"> 
                                     <tr>
                                         <td>${trace.accepttime }</td>
                                          <td>   </td>
                                         <td>${trace.acceptstation }</td>
                                     </tr>
                                    </c:forEach>
                                    
                                 </table>
				</div>
				
				<div class="clear"></div>
			</div>
		
			<div class="n_ddxqmk">
				<div class="n_ddxqmktop">物流公司信息</div>
				<div class="n_ddxqtr">
					<div class="n_ddxqtdleft">物流单号：</div>
					<div class="n_ddxqtdright">
						<span id="expno">${data.logisticcode }</span>
					</div>
				</div>
				<!--n_ddxqtr  stop -->
				<div class="n_ddxqtr">
					<div class="n_ddxqtdleft">物流公司：</div>
					<div class="n_ddxqtdright">
						<span id="expcode">${data.name }</span>
					</div>
				</div>
				<!--n_ddxqtr  stop -->
				<div class="clear"></div>
			</div>

		</div>
		<!--n_ddxq  stop -->
	</div>
	<!--主要内容 右边结束 -->
</div>