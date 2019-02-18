<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/platform/js/order/ddgl_dddetail.js"></script>
<script src="/resource/public/platform/js/order/ddgl_ddstatus.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        if('${order.cancelReason == null ? 0 : 1}'==0){
            GetStatus('${order.id}','${order.status}');
        }else{
            GetStatus('${order.id}','${order.status}');
        }

    })
    //根据状态显示操作按钮
function GetStatus(id, ss) {
	var zt = "", cz = "";
	switch (parseInt(ss)) {
	case 0:
		zt = "待付款";
		cz = "";
		break;
	case 1:
		zt = "待发货";
		cz = "<input name='' type='button' value='发货' onclick=order.fh(" + id
				+ "," + ss + ") class='inquire' />";
		break;
	case 2:
		zt = "待收货";
		cz = "";
		break;
	case 3:
		zt = "已确认收货";
		cz = "";
		break;
	case 4:
		zt = "未付款取消申请中";
		cz = "<input name='' type='button' value='查看原因' onclick=order.qxqr("
				+ id + ",'" + $("#reason").val() + "',4) class='inquire' /> ";
		break;
	case 5:
		zt = "未付款已取消";
		cz = "";
		$("#span_reason").show();
		break;
	case 6:
		zt = "已付款取消申请中";
		cz = "<input name='' type='button' value='查看原因' onclick=order.qxqr("
				+ id + ",'" + $("#reason").val() + "',6) class='inquire' />";
		break;
	case 7:
		zt = "已付款已取消";
		cz = "";
		$("#span_reason").show();
		break;
	case 8:
		zt = "已完成";
		cz = "";
		break;
	case 9:
		zt = "申请退款退货中";
		cz = "<input name='' type='button' value='查看原因' onclick=order.thhsh("
				+ id + ",9,2) class='inquire' />";
		break;
	case 10:
		zt = "已退款";
		cz = "";
		$("#span_reason").show();
		break;
	}
	$("#status").html(zt);
	$("span[name=ddcz]").html(cz);
}
</script>

<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">订单详情</a><span class="sj-img"></span></li>

            </ul>
            <span class="l_fhddlb"><input class="inquire" name="pback" type="button" onclick="javasciprt:location.href='/platform/businessbills/orderlist?status=${status}&shopId=${shopId }&dateb=${dateb }&datee=${datee }'" value="返回订单列表"></span>
        </div><!--l_wzmbtop   stop -->
        <!--n_ddxq  begin -->
        <div class="n_ddxq">
            <div class="l_ddhbj">
                <span>订单号：${order.code}</span>
                <span>状态：<span id="status" class="red">待发货</span></span>
                <span class="l_fhddlb" name="ddcz" style="margin-right:0px;"></span>
            </div><!--l_ddhbj   stop -->
            <div class="n_ddxqmk" style="margin-top:20px;">
                <div class="n_ddxqmktop">收货人信息</div>
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">收货人：</div>
                    <div class="n_ddxqtdright">${order.consignee }</div>
                </div><!--n_ddxqtr  stop -->
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">收货地址：</div>
                    <div class="n_ddxqtdright">${order.address }</div>
                </div><!--n_ddxqtr  stop -->
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">手机号码：</div>
                    <div class="n_ddxqtdright">${order.telphone } </div>
                </div><!--n_ddxqtr  stop -->
                <div class="clear"></div>
            </div><!--n_ddxqmk  stop -->
            <div class="n_ddxqmk">
                <div class="n_ddxqmktop">支付及配送方式</div>
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">支付方式：</div>
                    <div class="n_ddxqtdright">
                    <c:choose>
                    <c:when test="${order.payType == 0 }">
                    <span>账户余额支付</span>
                    </c:when>
                    <c:otherwise>
                    
                    <c:choose>
                    <c:when test="${order.payType == 1 }">
                    <span>网上支付</span>
                    </c:when>
                    <c:otherwise>
                    
                    </c:otherwise>
                    </c:choose>
                                <span>货到付款</span>
                    </c:otherwise>
                    </c:choose>

                    </div>
                </div><!--n_ddxqtr  stop -->
                <div class="n_ddxqtr">
                    <div class="n_ddxqtdleft">运费：</div>
                    <div class="n_ddxqtdright">￥：${order.freight }</div>
                </div><!--n_ddxqtr  stop -->
                <div class="clear"></div>
            </div><!--n_ddxqmk  stop -->
            <div class="n_ddxqmk">
                <div class="n_ddxqmktop">发票信息</div>
                <div class="n_ddxqtr">
                <c:choose>
                <c:when test="${order.isInvoice == 0 }">
                
                        <div class="n_ddxqtdleft">发票类型：</div>
                        <div class="n_ddxqtdright">不需要发票</div>
                
                </c:when>
                <c:when test="${order.isInvoice == 1 }">
                
                        <div class="n_ddxqtdleft">发票类型：</div>
                        <div class="n_ddxqtdright">普通发票</div>
                        <div class="n_ddxqtdleft">发票抬头：</div>
                        <div class="n_ddxqtdright">个人</div>
                        <div class="n_ddxqtdleft">发票明细：</div>
                         if (@ViewBag.Invoice == null)
                        {
                            <div class="n_ddxqtdright">明细</div>
                        }
                        else
                        {
                            if (@ViewBag.Invoice.Content == "0")
                            {
                                <div class="n_ddxqtdright">明细</div>
                            }
                            else
                            {
                                <div class="n_ddxqtdright">办公用品</div>
                            }
                        }
                </c:when>
                <c:when test="${order.isInvoice ==2 }">
                 <div class="n_ddxqtdleft">发票类型：</div>
                        <div class="n_ddxqtdright">电子发票</div>
                        <div class="n_ddxqtdleft">发票抬头：</div>
                        <div class="n_ddxqtdright">@ViewBag.Invoice.Title</div>
                        <div class="n_ddxqtdleft">发票明细：</div>
                        if (@ViewBag.Invoice == null)
                        {
                            <div class="n_ddxqtdright">明细</div>
                        }
                        else
                        {
                            if (@ViewBag.Invoice.content == "0")
                            {
                                <div class="n_ddxqtdright">明细</div>
                            }
                            else
                            {
                                <div class="n_ddxqtdright">办公用品</div>
                            }
                        }
                </c:when>
                <c:otherwise>
                
                <div class="n_ddxqtdleft">发票类型：</div>
                        <div class="n_ddxqtdright">增值税发票</div>
                        <div class="n_ddxqtdleft">发票抬头：</div>
                        <div class="n_ddxqtdright">@ViewBag.Invoice.Title</div>
                        <div class="n_ddxqtdleft">发票明细：</div>
                        if (@ViewBag.Invoice == null)
                        {
                            <div class="n_ddxqtdright">明细</div>
                        }
                        else
                        {
                            if (@ViewBag.Invoice.content == "0")
                            {
                                <div class="n_ddxqtdright">明细</div>
                            }
                            else
                            {
                                <div class="n_ddxqtdright">办公用品</div>
                            }
                        }
                </c:otherwise>
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
                        <c:forEach var="detail" items="${order.children }">
                         <tr>
                                <td>${detail.productnum }</td>
                                <td><img src="${detail.productimg }"></td>
                                <td class="text-left"><a href="javascript:void(0);">${detail.productname }</a></td>
                                <td>${detail.productprice }</td>
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
                        <td class="text-left">￥${order.price }</td>
                    </tr>
                    <tr>
                        <td>-优惠金额：</td>
                        <td class="text-left">￥${order.discount }</td>
                    </tr>
                    <tr style="border-bottom:1px solid #CACACA;">
                        <td>+运费：</td>
                        <td class="text-left">￥${order.freight }</td>
                    </tr>
                    <tr class="n_ddxqyfze">
                        <td class="bolder">实际总额：</td>
                        <td class="text-left bolder red">￥${order.price-order.discount+order.freight }</td>
                    </tr>
                </table>

                <div class="l_fhbot"><span name="ddcz" style="margin-right:0px;"></span></div>

            </div><!-- n_ddxqbottom  stop-->
        </div>
        <!--n_ddxq  stop -->
    </div><!--主要内容 右边结束 -->
</div>