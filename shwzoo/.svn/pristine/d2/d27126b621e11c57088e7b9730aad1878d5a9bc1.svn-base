<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath}/resource/public/seller/js/yxgl/packagesave.js"></script>
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt; 组合商品 &gt; 编辑组合商品
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">编辑组合商品</div>
            </div><!--zhgl-con-top  stop -->
            <form id="form" method="post">
                <div class="zhgl-con-con">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>套餐编号：</td>
                            <td>
                                <input id="num" class="text-inp-big" readonly name="num" type="text" value="${data.num }">
                                <input name="id" type="hidden" value="${data.id }" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>套餐名称：</td>
                            <td>
                                <input id="name" class="text-inp-big" name="name" type="text" value="${data.name }">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>数量：</td>
                            <td>
                                <input id="count" class="text-inp-big" style="width:155px;" name="count" type="text" value="${data.count }">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>开始时间：</td>
                            <td>
                                <input type="text" name="start" id="start" class="Wdate" 
                                onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'end\')}' })" 
                                value="${data.starttimestr }" readonly="readonly" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>结束时间：</td>
                            <td>
                                <input type="text" name="end" id="end" class="Wdate" 
                                onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'start\')}' })" 
                                value="${data.endtimestr }" readonly="readonly" />
                            </td>
                        </tr>
                       
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>排序：</td>
                            <td>
                                <input id="orderby" class="text-inp-big" style="width:155px;" name="orderby" 
                                type="text" value="${data.orderby }">
                            </td>
                        </tr>
                        
                        	<tr>
                        	<td class="xjdpzzh-left" ><span class="red">*</span>使用平台：</td>
                        	<td>
                        		  <c:choose>
                            <c:when test="${data.usesite != null}">
										<c:choose>
											<c:when test="${fn:indexOf(data.usesite,'1')>=0}">
												<input name="useplatform" type="checkbox" checked value="1">
												<span>pc端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="1">
												<span>pc端</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(data.usesite,'2')>=0}">
												<input name="useplatform" type="checkbox" checked value="2">
												<span>app端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="2">
												<span>app端</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(data.usesite,'3')>=0}">
												<input name="useplatform" type="checkbox" checked value="3">
												<span>wap端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="3">
												<span>wap端</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(data.usesite,'4')>=0}">
												<input name="useplatform" type="checkbox" checked value="4">
												<span>微信端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="4">
												<span>微信端</span>
											</c:otherwise>
										</c:choose>

									</c:when>
                            <c:otherwise>
                               <input name='useplatform' type='checkbox' value='1'><span>pc端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='2'><span>app端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='3'><span>wap端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='4'><span>微信端</span>
                            </c:otherwise>
                            </c:choose>
                        	</td>
         				</tr>
                        <tr>
                            <td class="xjdpzzh-left">&nbsp;</td>
                            <td>
                            <c:choose>
                            <c:when test="${data.status==0 }">
                              <input name='status' checked type='radio' value='0'><span>启用</span>
                                    <span class='marrig35'></span>
                                    <input name='status' type='radio' value='1'><span>禁用</span>
                            </c:when>
                            <c:otherwise>
                             <input name='status' type='radio' value='0'><span>启用</span>
                                    <span class='marrig35'></span>
                                    <input name='status' checked type='radio' value='1'><span>禁用</span>
                            </c:otherwise>
                            </c:choose>
                             
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input name="Save" type="button" value="保存" class="big-but">
                                <input id="action" type="hidden" value="updatePackage">
                                <input name="" type="button" value="取消" onclick="formCancel()" class="big-but-huise">
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function formCancel() {
        location.href = "yxgl_PackageList";
    }
</script>