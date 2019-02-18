
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/platform/js/order/ddgl_dddetail.js"></script>
<script src="/resource/public/platform/js/order/ddgl_ddstatus.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var id = ${order.id};
		var status = ${order.status};
		GetStatus(id, status);
	})
	function formCancel() {
		var isowned = ${isowned};
		if (isowned == 0) {
			location.href = "/platform/ddgl/ddgl_dpOrderList";
		} else {
			location.href = "/platform/ddgl/ddgl_zyOrderList";
		}
	}
</script>

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">订单详情</a><span
					class="sj-img"></span></li>

			</ul>
			<span class="l_fhddlb"><a href="javascript:history.go(-1);"><input class="inquire"
				type="button" value="返回订单列表"></span>
		</div>
		<!--l_wzmbtop   stop onclick="formCancel()"  -->

		<!--n_ddxq  begin -->
		<div class="n_ddxq">
			<div class="l_ddhbj">
				<span>订单号：${order.code}</span> <span>状态：<span id="status"
					class="red"></span></span> <span class="l_fhddlb" name="ddcz"
					style="margin-right: 0px;"></span>
					 <input type="hidden" id="reason" value="${order.cancelreason}"  />
			</div>
			<!--l_ddhbj   stop -->
			<div class="n_ddxqmk" style="margin-top: 20px;">
				<div class="n_ddxqmktop">收货人信息</div>
				<div class="n_ddxqtr">
					<div class="n_ddxqtdleft">收货人：</div>
					<div class="n_ddxqtdright">${receive.consignee}</div>
				</div>
				<!--n_ddxqtr  stop -->
				<div class="n_ddxqtr">
					<div class="n_ddxqtdleft">收货地址：</div>
					<div class="n_ddxqtdright">${receive.provincename} ${receive.cityname} ${receive.areaname} ${receive.address}</div>
				</div>
				<!--n_ddxqtr  stop -->
				<div class="n_ddxqtr">
					<div class="n_ddxqtdleft">手机号码：</div>
					<div class="n_ddxqtdright">${receive.telphone}</div>
				</div>
				<!--n_ddxqtr  stop -->
				<div class="clear"></div>
			</div>
			<!--n_ddxqmk  stop -->
			<div class="n_ddxqmk">
				<div class="n_ddxqmktop">支付及配送方式</div>
				<div class="n_ddxqtr">
					<div class="n_ddxqtdleft">支付方式：</div>
					<div class="n_ddxqtdright">
						<c:choose>
							<c:when test="${order.paytype== 1}">
								<span>在线支付</span>
							</c:when>
							<c:when test="${order.paytype== 2}">
								<span>余额支付</span>
							</c:when>
							<c:when test="${order.paytype== 3}">
								<span>优惠券支付</span>
							</c:when>
							<c:when test="${order.paytype== 4}">
								<span>混合支付</span>
							</c:when>
						</c:choose>
					</div>
				</div>
				<!--n_ddxqtr  stop -->
				<div class="n_ddxqtr">
					<div class="n_ddxqtdleft">运费：</div>
					<div class="n_ddxqtdright">
						￥：
						<fmt:formatNumber value="${order.freight}" pattern="0.00" />
					</div>
				</div>
				<!--n_ddxqtr  stop -->
				<div class="clear"></div>
			</div>
			<!--n_ddxqmk  stop -->
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

				</div>
				<!--n_ddxqtr  stop -->
				<div class="clear"></div>
			</div>
			<!--n_ddxqmk  stop -->
			<div class="n_ddxqmk" style="border-bottom: none;">
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
				</div>
				<!--n_spqd  stop -->
				<div class="clear"></div>
			</div>
			<!--n_ddxqmk  stop -->
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
					<tr style="border-bottom: 1px solid #CACACA;">
						<td>+运费：</td>
						<td class="text-left">￥<fmt:formatNumber
								value="${order.freight }" pattern="0.00" /></td>
					</tr>
					<tr class="n_ddxqyfze">
						<td class="bolder">实际总额：</td>
						<td class="text-left bolder red">￥<fmt:formatNumber
								value="${totalmoney }" pattern="0.00" /></td>
					</tr>
				</table>

				<div class="l_fhbot">
					<span name="ddcz" style="margin-right: 0px;"></span>
				</div>

			</div>
			<!-- n_ddxqbottom  stop-->

			<!--Tab2 申请返修退换货开始 -->
			<!-- @*<div class="clear"></div>
                 <div class="ddhxq-img"><img src="~/web/images/img07.png" width="649" height="108"></div>ddhxq-img  stop
                <div id="Tab2">
                     <div class="Menubox">
                         <ul>
                             <li id="two1" onclick="setTab('two',1,4)" class="hover">订单跟踪</li>
                             <li id="two2" onclick="setTab('two',2,4)">付款信息</li>
                         </ul>
                     </div>
                     <div class="Contentbox">
                         con_two_1 选项卡1对应内容开始
                         <div id="con_two_1">
                             <div class="n_ddgz">
                                 <table>
                                     <tr class="n_ddgztr">
                                         <td>订单跟踪</td>
                                         <td>处理信息</td>
                                         <td>操作人</td>
                                     </tr>
                                     <tr>
                                         <td style="padding-top:20px;">2015-09-06 11:21:31</td>
                                         <td style="padding-top:20px;">您提交了订单，请等待系统确认</td>
                                         <td style="padding-top:20px;">客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                     <tr>
                                         <td>2015-09-06 11:21:31</td>
                                         <td>您提交了订单，请等待系统确认</td>
                                         <td>客户</td>
                                     </tr>
                                 </table>
                                 <div class="n_ddgz-bottom"><span>送货方式：普通快递</span> <span>承运人：中通快递</span> <span>货运单号：7633521566555555555555</span> <span class="lvse"><a href="javascript:void(0);">点击查询</a></span></div>
                             </div>n_ddgz  stop

                         </div>
                         con_two_1 选项卡1对应内容结束
                         con_two_2 选项卡2对应内容开始
                         <div id="con_two_2" style="display:none">
                             <div class="n_fkxx">
                                 <table>
                                     <tr>
                                         <td class="text-right">付款方式：</td>
                                         <td colspan="5">在线支付</td>
                                     </tr>
                                     <tr>
                                         <td class="text-right">商品金额：</td>
                                         <td>￥45.00</td>
                                         <td class="text-right">运费金额：</td>
                                         <td>￥45.00</td>
                                         <td class="text-right">优惠券：</td>
                                         <td>￥45.00</td>
                                     </tr>
                                     <tr>
                                         <td class="text-right">商品金额：</td>
                                         <td>￥45.00</td>
                                         <td class="text-right">运费金额：</td>
                                         <td>￥45.00</td>
                                         <td class="text-right">优惠券：</td>
                                         <td>￥45.00</td>
                                     </tr>
                                     <tr>
                                         <td class="text-right">付款时间：</td>
                                         <td colspan="5">2015-09-08 11:23:45</td>
                                     </tr>
                                 </table>
                             </div>n_fkxx  stop
                         </div>con_two_2 选项卡2对应内容结束
                     </div>
                 </div>*@ -->
			<!--Tab2 申请返修退换货结束 -->
		</div>
		<!--n_ddxq  stop -->
	</div>
	<!--主要内容 右边结束 -->
</div>