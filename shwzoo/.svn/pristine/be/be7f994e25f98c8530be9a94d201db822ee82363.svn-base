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
})
</script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 订单管理 &gt; 订单详情
        </div><!--所在位置信息  结束 -->
        <div class="ddhxq-top">
            <h3>
                订单号：${order.code}　　　状态：<span id="status" style="color:#14A115;"></span>

                <span style="margin-right:40px;"></span><span class="ddcz"></span>
                <input type="hidden" id="reason" value="${order.cancelreason}"  />
                <span style="float:right;"><a href="javascript:history.go(-1);"><input type="button" value="返回列表" class="but-comm"></a></span>
            </h3>
        </div><!--ddhxq-top   stop -->
        <!--n_ddxq  begin -->
        <div class="n_ddxq">
            <div class="n_ddxqtitle">订单详情</div>
            <div class="n_ddxqmk" style="margin-top:20px;">
                <div class="n_ddxqmktop">收货人信息</div>
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">收货人：</div>
                    <div class="n_ddxqtdright">${receive.consignee}</div>
                </div><!--n_ddxqtr  stop -->
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">收货地址：</div>
                    <div class="n_ddxqtdright">${receive.address}</div>
                </div><!--n_ddxqtr  stop -->
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">手机号码：</div>
                    <div class="n_ddxqtdright">${receive.telphone}</div>
                </div><!--n_ddxqtr  stop -->
                <div class="clear"></div>
            </div><!--n_ddxqmk  stop -->
            <div class="n_ddxqmk">
                <div class="n_ddxqmktop">支付及配送方式</div>
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">支付方式：</div>
                    <div class="n_ddxqtdright">
                       <c:choose>
							<c:when test="${order.paytype== 0}">
								<span>在线支付</span>
							</c:when>
							<c:when test="${order.paytype== 0}">
								<span>余额支付</span>
							</c:when>
							<c:when test="${order.paytype== 0}">
								<span>优惠券支付</span>
							</c:when>
							<c:when test="${order.paytype== 0}">
								<span>混合支付</span>
							</c:when>
						</c:choose>
                    </div>
                </div><!--n_ddxqtr  stop -->
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">运费：</div>
                    <div class="n_ddxqtdright">￥：<fmt:formatNumber value="${order.freight}" pattern="0.00" /></div>
                </div><!--n_ddxqtr  stop -->
                <div class="clear"></div>
            </div><!--n_ddxqmk  stop -->
            <div class="n_ddxqmk">
                <div class="n_ddxqmktop">发票信息</div>
                <div class="n_ddxqtr">
                   <c:choose>
						<c:when test="${order.isinvoice==0}">
							<div class="n_ddxqtdleft">发票类型：</div>
							<div class="n_ddxqtdright">不需要发票</div>
						</c:when>
						<c:when test="${order.isinvoice==1}">
						 <div class="n_ddxqtdleft">发票类型：</div>
							<c:choose>
								<c:when test="${invoice.type==0 }">
									<div class="n_ddxqtdright">普通发票</div>
								</c:when>
								<c:when test="${invoice.type==1 }">
									<div class="n_ddxqtdright">电子发票</div>
								</c:when>
								<c:otherwise>
									<div class="n_ddxqtdright">增值税发票</div>
								</c:otherwise>
							</c:choose>
							<div class="n_ddxqtdleft">发票抬头：</div>
							<div class="n_ddxqtdright">${invoice.title}</div>
							<div class="n_ddxqtdleft">发票明细：</div>
							<div class="n_ddxqtdright">${invoice.content}</div>
						</c:when>
					</c:choose>
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
                            <td width="40%">商品名称</td>
                            <td width="15%">价格（元）</td>
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
								<td>${detail.productcount }</td>

							</tr>
						</c:forEach>
                    </table>
                </div><!--n_spqd  stop -->
                <div class="clear"></div>
            </div><!--n_ddxqmk  stop -->
            <div class="n_ddxqbottom">
                <table>
                    <tr>
                        <td>总商品金额：</td>
                        <td class="text-left">￥<fmt:formatNumber
								value="${order.price }" pattern="0.00" /></td>
                    </tr>
                    <tr>
                        <td>-优惠金额：</td>
                        <td class="text-left">￥<fmt:formatNumber
								value="${order.discount }" pattern="0.00" /></td>
                    </tr>
                    <tr style="border-bottom:1px solid #CACACA;">
                        <td>+运费：</td>
                        <td class="text-left">￥<fmt:formatNumber
								value="${order.freight }" pattern="0.00" /></td>
                    </tr>
                    <tr class="n_ddxqyfze">
                        <td>应付总额：</td>
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
