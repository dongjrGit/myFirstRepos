<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="/resource/public/platform/css/ddgl_ddlb.css" />
<script src="/resource/public/platform/js/order/bbillsOrder.js"></script>

<div class="mainright">

    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <input type="hidden" value=${shopid } id="shopid"/>
        <input type="hidden" value=${orderdate } id="orderdate"/>
        <input type="hidden" value=${status } id="status"/>
        <input class="inquire"
				type="button" onclick="formCancel()" value="返回">
    </div><!--account-form stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con" id="divshow">
        <table class="data_list">
            <tr id="pack_title">
                <th >主订单号</th>
                <th >订单金额</th>
                <th >支付方式</th>
                <th >商户信息</th>
                <th >订单状态</th>
                <th >下单时间</th>
            </tr>
            <tbody id="datalist">
                <script id="packlist" type="text/html">
                    {{each list as order i}}
                    <tr>
                        <td>{{order.groupcode}}</td>
                        <td>{{order.price | toFixed}}</td>
						<td>
							{{if order.payType==1}}在线支付
                            {{else if order.payType==2}}余额支付
                            {{else if order.payType==3}}优惠券支付
                            {{else if order.payType==4}}混合支付
							{{else if order.payType==5}}货到付款
 							{{else if order.payType==6}}支付宝支付
 							{{else if order.payType==7}}微信支付
                            {{else if order.payType==8}}IPS支付
                            {{else if order.payType==9}}快捷支付
                            {{else if order.payType==10}}银联支付
                            {{else if order.payType==11}}浦发支付
							{{else if order.payType==12}}农行支付
                            {{else if order.payType==13}}农行贷记卡
							{{else if order.payType==0}}未支付
                            {{/if}}
						</td>
						<td>{{order.membername}}</td>
						<td >
                            {{if order.status==0}}订单未完成
                            {{else if order.status==10}}待使用
							{{else if order.status==19}}已使用
							{{else if order.status==20}}申请退款
							{{else if order.status==21}}退款失败
                            {{else if order.status==22}}审核中
                            {{else if order.status==29}}退款成功
                            {{else if order.status==99}}订单完成
                            {{/if}}                               
                        </td>
						<td>{{order.createtime | toDateTime}}</td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
    </div>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div>

    </div><!--mainright stop -->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        list.bind();
        list.getlist(1);
    })
</script>
