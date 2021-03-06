<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/seller/js/DdGl/orderdetail.js"></script>
<script src="/resource/public/seller/js/DdGl/orderstatus.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	var id = ${order.id};
	var status = ${order.status};
	GetStatus(id, status);
});
function formCancel() {
		var ch = ${ch};
		 switch (parseInt(ch)) {
         case 1: location.href = "/seller/shopdd/ddgl_OrderList"; break;
         case 2: location.href = "/seller/shopdd/ddgl_DfkOrderList"; break;
         case 3: location.href = "/seller/shopdd/ddgl_DsyOrderList"; break;
         case 4: location.href = "/seller/shopdd/ddgl_ShzOrderList"; break;
         case 5: location.href = "/seller/shopdd/ddgl_YqxOrderList"; break;
         case 6: location.href = "/seller/shopdd/ddgl_YwjOrderList"; break;
         default:break;
     }
	}
</script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 订单管理 &gt; 订单详情
        </div><!--所在位置信息  结束 -->
        <div class="ddhxq-top">
            <h3>
                主订单号：${order.groupcode}　　　状态：<span id="status" style="color:#14A115;"></span>

                <span style="margin-right:40px;"></span><span class="ddcz"></span>
                <input type="hidden" id="reason" value="${order.cancelreason}"  />
                <span style="float:right;"><input type="button" value="返回列表" onclick="formCancel()" class="but-comm"></span>
            </h3>
        </div><!--ddhxq-top   stop -->
        <!--n_ddxq  begin -->
        <div class="n_ddxq">
            <div class="n_ddxqtitle">订单详情</div>
            <div class="n_ddxqmk" style="margin-top:20px;">
                <div class="n_ddxqmktop">身份信息</div>
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">姓名：</div>
                    <div class="n_ddxqtdright">${idcardinfo.name}</div>
                </div><!--n_ddxqtr  stop -->
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">手机号：</div>
                    <div class="n_ddxqtdright">${idcardinfo.phone}</div>
                </div><!--n_ddxqtr  stop -->
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">身份证号：</div>
                    <div class="n_ddxqtdright">${idcardinfo.card}</div>
                </div><!--n_ddxqtr  stop -->
                <div class="clear"></div>
            </div><!--n_ddxqmk  stop -->
            <div class="n_ddxqmk">
                <div class="n_ddxqmktop">支付方式</div>
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">支付方式：</div>
                    <div class="n_ddxqtdright">
						<c:choose>
							<c:when test="${order.paytype==6 }">
								<span>支付宝支付</span>
							</c:when>
							<c:when test="${order.paytype==7 }">
								<span>微信支付</span>
							</c:when>
							<c:when test="${order.paytype==3 }">
								<span>优惠券支付</span>
							</c:when>
							<c:otherwise><span>未支付</span></c:otherwise>
						</c:choose>
                    </div>
                </div><!--n_ddxqtr  stop -->
                <div class="clear"></div>
            </div><!--n_ddxqmk  stop -->
            <div class="n_ddxqmk" style="border-bottom:none;">
                <div class="n_ddxqmktop">商品清单</div>
                <div class="n_spqd">
                    <table>
                        <tr>
                            <td width="15%">商品编号</td>
                            <td width="20%">商品图片</td>
                            <td width="35%">商品名称</td>
                            <td width="10%">价格（元）</td>
                            <td width="10%">使用时间</td>
                            <td width="10%">数量</td>
                        </tr>
                       <c:forEach var="detail" items="${details}">
							<tr>
								<td>${detail.productnum }</td>
								<c:choose>
									<c:when test="${detail.productimg==null}">
										<td><img src=""></td>
									</c:when>
									<c:otherwise>
										<td><img src="<%=path %>${detail.productimg} "></td>
									</c:otherwise>
								</c:choose>
								<td class="text-left"><a href="javascript:void(0);">${detail.productname }</a></td>
								<td><fmt:formatNumber value="${detail.productprice }"
										pattern="0.00" /></td>
								<td><fmt:formatDate value="${detail.usetime }" pattern="yyyy-MM-dd"/></td>
								<td>${detail.productcount }</td>

							</tr>
						</c:forEach>
                    </table>
                </div><!--n_spqd  stop -->
                <div class="clear"></div>
            </div><!--n_ddxqmk  stop -->
            <div class="n_ddxqbottom">
                <table>
                  <tr class="n_ddxqyfze">
                        <td>订单金额：</td>
                        <td class="text-left bolder red">￥<fmt:formatNumber
								value="${order.price }" pattern="0.00" /></td>
                    </tr>
                    <tr class="n_ddxqyfze">
                        <td>-优惠金额：</td>
                        <td class="text-left bolder red">￥<fmt:formatNumber
								value="${order.discount }" pattern="0.00" /></td>
                    </tr>
                    <tr class="n_ddxqyfze">
                        <td>总商品金额：</td>
                        <td class="text-left bolder red">￥<fmt:formatNumber
								value="${totalmoney }" pattern="0.00" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td class="text-left"><span class="ddcz"></span></td>
                    </tr>
                </table>
            </div><!-- n_ddxqbottom  stop-->
        </div>
        <!--n_ddxq  stop -->
    </div><!--主要内容 右边结束 -->
</div>
