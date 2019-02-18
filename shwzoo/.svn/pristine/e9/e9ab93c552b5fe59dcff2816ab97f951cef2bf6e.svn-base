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
        <span>订单状态:</span>
        <select id="orderstatus" name="orderstatus" class="the-form-select-one">
            <option value="" selected>全部订单</option>
            <option value="0">待付款</option>
            <option value="1">待发货</option>
            <option value="2">待收货</option>
            <option value="3">已确认收货</option>
            <option value="4">未付款取消申请</option>
            <option value="5">未付款已取消</option>
            <option value="6">已付款取消申请</option>
            <option value="7">已付款已取消</option>
            <option value="8">已完成</option>
            <option value="9">申请退款退货中</option>
            <option value="10">已退款</option>
        </select>
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
    </div><!--account-form stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="l_gulitab">
        <table class="data_list">
            <tr id="dd_title">
                <th width='30%'>订单详情</th>
                <th width='8%'>商品单价</th>
                <th width='8%'>商品数量</th>
                <th width='10%'>买家信息</th>
                <th width='12%'>订单状态</th>
                <th width='12%'>下单时间</th>
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
                                <td colspan="4" class="l_toptd">
                                    <span>订单号：{{order.code}}</span> <span>订单金额：{{order.price | toFixed}}</span><span>运费：{{order.freight | toFixed}}</span>
                                    <span>优惠金额：{{order.discount | toFixed}}</span>
                                    <span>实际支付金额：{{order.actualPay | toFixed}}</span>
                                </td>
                                <td style="text-align:center" class="l_toptd">
                                    {{if order.status==0}}待付款
                                    {{else if order.status==1}}待发货
                                    {{else if order.status==2}}待收货
                                    {{else if order.status==3}}已确认收货
                                    {{else if order.status==4}}未付款取消申请
                                    {{else if order.status==5}}未付款已取消
                                    {{else if order.status==6}}已付款取消申请
                                    {{else if order.status==7}}已付款已取消
                                    {{else if order.status==8}}已完成
									{{else if order.status==9}}申请退款退货中
                                    {{else if order.status==10}}已退款
                                    {{/if}}                               
                                </td>
                                <td class="l_toptd"></td>
                                <td class="l_toptd" style="text-align:center">
                                    <a href='ddgl_OrderDetail?id={{order.id}}'><span class='shenlan'>查看详情</span></a>
                                    {{if order.status==0}}
                                    {{else if order.status==1}}
                                    <a href='javascript:void(0);' data-status="{{order.status}}" data-oid="{{order.id}}"><span class='shenlan'>发货</span></a>
                                    {{else if order.status==4 || order.status==6 }}
                                    <a href='javascript:void(0);' data-status="{{order.status}}" data-cancel="{{order.cancelReason}}" data-oid="{{order.id}}"><span class='shenlan'>查看原因</span></a>
                                    {{else if order.status==9 }}
                                    <a href='javascript:void(0);' data-status="{{order.status}}" data-oid="{{order.id}}"><span class='shenlan'>查看原因</span></a>
                                    {{/if}}
                                    <a href='javascript:void(0);' onclick="order.del({{order.id}})"><span class='shenlan'>删除</span></a>
                                     {{if (order.status>1 && order.status<4) || order.status>7}}
                                    <a href='${pageContext.request.contextPath }/platform/track/showTrackquery?orderid={{order.id}}&isowned=1' ><span class='shenlan'>查看物流</span></a>
                                {{/if}}
                                </td>
                            </tr>
                            {{each order.children as detail j}}
                            <tr>
                                <td width='30%'>{{detail.productname}}</td>
                                <td width='8%'>{{detail.productprice | toFixed}}</td>
                                <td width='8%'>{{detail.productcount}}</td>
                                <td width='10%'>{{order.buyerName}}</td>
                                <td width='12%'>
                                    <div class="pos_rela">
                                        {{if detail.status==50}}申请退货退款
                                        {{else if detail.status==51}}<span class="l_xsztdj" data-id="{{detail.id}}">退货申请被拒绝</span>
                                        {{else if detail.status==52}}<span class="l_xsztdj" data-id="{{detail.id}}">退货商家待收货</span>
                                        {{else if detail.status==59}}退货退款成功
                                        {{else if detail.status==20 }}<span class="l_xsztdj" data-id="{{detail.id}}">申请退款</span>
                                        {{else if detail.status==29 }}退款成功
                                        {{else if detail.status==30 }}<span class="l_xsztdj" data-id="{{detail.id}}">申请换货</span>
                                        {{else if detail.status==31 }}<span class="l_xsztdj" data-id="{{detail.id}}">换货申请被拒绝</span>
                                        {{else if detail.status==32 }}<span class="l_xsztdj" data-id="{{detail.id}}">换货商家待收货</span>
                                        {{else if detail.status==33 }}<span class="l_xsztdj" data-id="{{detail.id}}">换货商家待发货</span>
                                        {{else if detail.status==34 }}<span class="l_xsztdj" data-id="{{detail.id}}">换货买家待收货</span>
                                        {{else if detail.status==39 }}换货成功
                                        {{else if detail.status==40 }}<span class="l_xsztdj" data-id="{{detail.id}}">申请维修</span>
                                        {{else if detail.status==41 }}<span class="l_xsztdj" data-id="{{detail.id}}">维修申请被拒绝</span>
                                        {{else if detail.status==42 }}<span class="l_xsztdj" data-id="{{detail.id}}">维修商家待收货</span>
                                        {{else if detail.status==43 }}<span class="l_xsztdj" data-id="{{detail.id}}">维修商家待发货</span>
                                        {{else if detail.status==44}}<span class="l_xsztdj" data-id="{{detail.id}}">维修买家待收货</span>
                                        {{else if detail.status==49}}维修成功										
                                        {{/if}}
                                        <div id="divrecord_{{order.id}}" class="l_xsztcon" style="display:none;">
                                            <div class="l_xtb_xszt"></div>
                                            <div class="l_xszt">
                                                <ul></ul>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td width='12%'>{{order.addOrderDatestr}}</td>
                                <td>
                                    {{if detail.status==50}}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>查看原因</span></a>
                                    {{else if detail.status==52}}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>收货</span></a>
                                    {{else if detail.status==20 }}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>查看原因</span></a>
                                    {{else if detail.status==30 }}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>查看原因</span></a>
                                    {{else if detail.status==32 }}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>收货</span></a>
                                    {{else if detail.status==33 }}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>发货</span></a>
                                    {{else if detail.status==40 }}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>查看原因</span></a>
                                    {{else if detail.status==42 }}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>收货</span></a>
                                    {{else if detail.status==43 }}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>发货</span></a>
                                    {{else if detail.status==31||detail.status==41||detail.status==51 }}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>售后纠纷处理</span></a>
                                    {{else if detail.status==39||detail.status==49||detail.status==59 }}
                                    <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>售后详情</span></a>
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
        list.bind(1);
        reUrl.load(2);
        list.getlist(1, orderzy);
    })
</script>
