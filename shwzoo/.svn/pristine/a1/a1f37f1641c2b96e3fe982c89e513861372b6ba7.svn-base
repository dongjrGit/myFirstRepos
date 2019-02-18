<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/seller/js/DdGl/bbillsOrder.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    	list.getlist(1);
    })
</script>
<!--主要内容开始 -->
<div id="container">
    <div class="allcon">
        <div class="position">
           您所在的位置：订单管理 &gt; 对账单管理 &gt; 对账订单列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
               <input type="hidden" value=${shopid } id="shopid"/>
				<input type="hidden" value=${orderdate } id="orderdate"/>
				<input type="hidden" value=${status } id="status"/>
                <div class="submit-but">
                    <input type="button" value="返回" onclick="formCancel()" class="but-comm">
                </div>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="thgl">
             <table>
                  <tr id="dd_title">
                    <th >主订单号</th>
	                <th >订单金额</th>
	                <th >支付方式</th>
	                <th >商户信息</th>
	                <th >订单状态</th>
	                <th >下单时间</th>
                </tr>
                <tbody id="datalist">
                    <script id="ddlist" type="text/html">
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
            </div><!--thgl 表格部分结束 -->


            <div class="clear"></div>
            <div id="pager" class="page">

            </div>
        </div><!--主要内容 右边结束 -->
    </div>
</div>