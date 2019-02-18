<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="/resource/public/platform/css/ddgl_ddlb.css" />
<script src="/resource/public/platform/js/order/ddgl_list.js"></script>
<script src="/resource/public/platform/js/order/ddgl_ddstatus.js"></script>

<div class="mainright">

    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>订单号：</span><input class="inp-seller" id="select_ordercode" type="text" value="">
        <span class="marrig10"></span>
        <span>所属店铺：<input id="select_shop" type="text" class="inp-seller" /></span>
        <div>
            <ul>
                <script id="select_shoplist" type="text/html">
                    {{each list as shop i}}
                    <li data="{{shop.id}}">{{shop.name}}</li>
                    {{/each}}
                </script>
            </ul>
        </div>
        <span class="marrig10"></span>
        <span>买家名称：<input id="select_buyer" type="text" class="inp-seller" /></span>
        <div>
            <ul>
                <script id="select_buyerlist" type="text/html">
                    {{each list as buyer i}}
                    <li data="{{buyer.userid}}">{{buyer.loginname}}</li>
                    {{/each}}
                </script>
            </ul>
        </div>
        <span class="marrig10"></span>
        <span>
            <span>订单日期：</span>
            <input type="text" id="select_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })" />-->
            <input type="text" id="select_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })" />
        </span>
        <span class="marrig10"></span>
        <input class="inquire" name="search" type="button" value="查询">
        <input id="state" type="hidden" value="4">
    </div><!--account-form stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="l_gulitab">
        <table class="data_list">
            <tr id="dd_title">
                <th width='30%'>订单详情</th>
                <th width='8%'>商品单价</th>
                <th width='8%'>商品数量</th>
                <th width='10%'>所属店铺</th>
                <th width='10%'>买家信息</th>
                <th width='10%'>订单状态</th>
                <th width='10%'>订单时间</th>
                <th>订单操作</th>
            </tr>
        </table>
        <div class="l_ddgl">
            <table id="datalist">
                <script id="ddlist" type="text/html">
                    {{each list as order i}}
                    <div class="l_ddgl">
                        <table>
                            <tr>
                                <td colspan="5" class="l_toptd">
                                    <span>主订单号：{{order.groupCode}}</span> <span>订单金额：{{order.price | toFixed}}</span>
                                    <span>实际支付金额：{{order.actualPay | toFixed}}</span>
									{{if order.status>0 && order.payType!=null}}
                                         <span>支付方式：
 										    {{if order.payType==6}}支付宝支付
 										    {{else if order.payType==7}}微信支付
  {{else if order.payType==3}}优惠券支付
											{{else if order.payType==0}}待付款
                                            {{/if}}
                                         </span>
                                       {{/if}}
                                </td>
                                <td style="text-align:center;padding-left:0px" class="l_toptd">
                                    {{if order.status==0}}待付款
                                    {{else if order.status==1}}出票中
                                    {{else if order.status==2}}待使用
                                    {{else if order.status==3}}审核中
                                    {{else if order.status==4}}已取消
									{{else if order.status==9}}已完结
                                    {{/if}}                               
                                </td>
                                <td class="l_toptd" >{{order.addOrderDatestr}}</td>
                                <td class="l_toptd">
                                    <a href='ddgl_OrderDetail?id={{order.id}}&type=3'><span class='shenlan'>查看详情</span></a>
                                    {{if order.status==0}}
                                    {{else if order.status==3}}
                                    	<!--<a href='javascript:void(0);' onclick="order.zhuddtk({{order.groupCode}})"><span class='shenlan'>退款</span></a>-->
                                    {{/if}}
                                    <a href='javascript:void(0);' onclick="order.del({{order.id}})"><span class='shenlan'>删除</span></a>
                               </td>
                            </tr>
                            {{each order.children as detail j}}
                            <tr>
                                <td width='30%'>{{detail.productname}}</td>
                                <td width='8%'>{{detail.productprice | toFixed}}</td>
                                <td width='8%'>{{detail.productcount}}</td>
                                <td width='10%'>{{order.shopName}}</td>
                                <td width='10%'>{{order.buyerName}}</td>
                                <td width='10%'>
                                    <div class="pos_rela"> 
										{{if detail.status==0}}<!--订单未完成-->
										{{else if detail.status==10}}<!--待使用-->
										{{else if detail.status==19}}已使用
										{{else if detail.status==20}}<span class="l_xsztdj" data-id="{{detail.id}}">申请退款</span>
										{{else if detail.status==21}}<span class="l_xsztdj" data-id="{{detail.id}}">退款失败</span>
										{{else if detail.status==22}}<span class="l_xsztdj" data-id="{{detail.id}}">审核中</span>
										{{else if detail.status==29 }}退款成功
										{{else if detail.status==99}}订单完成
                                        {{/if}}
                                        <div id="divrecord_{{order.id}}" class="l_xsztcon" style="display:none;">
                                            <div class="l_xtb_xszt"></div>
                                            <div class="l_xszt">
                                                <ul></ul>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td width='10%'>{{detail.usetime | toDateTimes}}</td>
                                <td>
                                    {{if detail.status==22}}
                                    	<a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>查看原因</span></a>
										<!--<a href='javascript:void(0);' onclick="order.ziddtk({{detail.id}})"><span class='shenlan'>退款</span></a>-->
                                    {{/if}}
                                </td>
                            </tr>
                            {{/each}}
                        </table>
                    </div>
                    {{/each}}
                </script>
            </table>
        </div><!--table-con  stop -->
        <div class="clear"></div>
        <div id="pager" class="page">

        </div>

    </div><!--mainright stop -->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        list.bind(0);
        reUrl.load(1);
        autoxl.bind("select_shop", list.getShopStartwithName);
        list.getlist(1, orderzy);
    })
</script>
