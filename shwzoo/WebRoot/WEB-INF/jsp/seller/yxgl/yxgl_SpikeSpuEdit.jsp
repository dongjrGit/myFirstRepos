<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt; 闪购列表 &gt; 修改商品
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">修改商品</div>
            </div><!--zhgl-con-top  stop -->
            <form id="form" method="post">
                <div class="zhgl-con-con">
                    <input id="id" name="spikeid" type="hidden" value="${data.spikeid }" />
                    <input name="id" type="hidden" value="${data.id }" />
                    <input id="spuid" name="spuid" type="hidden" value="${data.spuid }" />
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>商品：</td>
                            <td>
                                <input id="select_spu" type="text" disabled class="text-inp-big" value="${spuname }" data="${data.spuid }" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>优惠价格：</td>
                            <td>
                                <input id="price" class="text-inp-big" name="price" type="text" value="<fmt:formatNumber value="${data.price}" pattern="0.00"/>">&nbsp;元
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>商品数量：</td>
                            <td>
                                <input id="count" class="text-inp-big" name="count" type="text" value="${data.spucount}">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>排序：</td>
                            <td>
                                <input id="orderby" class="text-inp-big" name="orderby" type="text" value="${data.orderby }">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>手机专享：</td>
                            <td>
                            <c:choose>
                            <c:when test="${data.isphone==0 }">
                            <input name='isphone' checked type='radio' value='0'><span>否</span>
                                    <span class='marrig35'></span>
                                    <input name='isphone' type='radio' value='1'><span>是</span>
                            </c:when>
                            <c:otherwise>
                              <input name='isphone' type='radio' value='0'><span>否</span>
                                    <span class='marrig35'></span>
                                    <input name='isphone' checked type='radio' value='1'><span>是</span>
                            </c:otherwise>
                            </c:choose>
                             
                            </td>
                        </tr>
                        	<%-- <tr>
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
         				</tr> --%>
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input name="Save" type="button" value="保存" class="big-but">
                                <input id="action" type="hidden" value="updateSpikeSpu">
                                <input name="" type="button" value="取消" onclick="formCancel()" class="big-but-huise">
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resource/public/seller/js/yxgl/spikeskuSave.js"></script>
<script>
    function formCancel() {
        location.href = "yxgl_SpikeSpuList?id=" + $("#id").val();
    }
</script>