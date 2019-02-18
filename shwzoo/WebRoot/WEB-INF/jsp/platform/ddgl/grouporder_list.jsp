<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="/resource/public/platform/css/ddgl_ddlb.css" />
<script src="/resource/public/platform/js/order/grouporder_list.js"></script>

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
            <option value="1">待消费</option>
            <option value="2">已取消</option>
            <option value="3">申请退款</option>
            <option value="4">已退款</option>
            <option value="5">待评价</option>
            <option value="6">已评价</option>
        </select>
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
    </div><!--account-form stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="dd_title">
                <th width='30%'>订单详情</th>
                <th width='10%'>所属店铺</th>
                <th width='10%'>买家信息</th>
                <th width='10%'>团购开始时间</th>
                <th width='10%'>团购结束时间</th>
                <th width='10%'>订单状态</th>
                <th width='10%'>下单时间</th>
                <th>订单操作</th>
            </tr>
        </table>      
        <div class="l_ddgl">
            <table id="datalist">
                <script id="Grouplist" type="text/html">
                  {{each list as order i}}
					 <div class="l_ddgl">
                        <table>
                            <tr>
                                <td width='30%' class="l_toptd">
                                    <span>订单号：{{order.ordercode}}</span> <span>订单金额：{{order.orderprice | toFixed}}</span>
                                    <span>实际支付金额：{{order.payprice | toFixed}}</span>
                                </td>
                                <td width='10%' class="l_toptd" style="text-align:center;" >{{order.shopname}}</td>
                                <td width='10%' class="l_toptd">{{order.buyname}}</td>
                                <td width='10%' class="l_toptd">{{order.starttime}}</td>
                                <td width='10%' class="l_toptd">{{order.endtime}}</td>
                                <td width='10%' style="text-align:center;" class="l_toptd">
                                    {{if order.status==0}}待付款
                                    {{else if order.status==1}}待消费
                                    {{else if order.status==2}}已取消
{{else if order.status==3}}申请退款
{{else if order.status==4}}已退款
{{else if order.status==5}}待评价
{{else if order.status==6}}已评价
                                    {{/if}}                               
                                </td>
                                <td width='10%' class="l_toptd" >{{order.createtime}}</td>
                                <td class="l_toptd">
                                    <a href='javascript:void(0);' onclick="list.del({{order.id}})"><span class='shenlan'>删除</span></a>
                                </td>
                            </tr>
                            {{each order.detaillist as detail j}}
                            <tr>
                                <td width='30%'>{{order.title}}</td>
                                <td width='10%'>团购码</td>
                                <td width='10%'>{{detail.code}}</td>
                                <td colspan="2"></td>
                                <td width='10%'>
                                        {{if detail.status==0}}未使用
                                        {{else if detail.status==1}}<span class="l_xsztdj" data-id="{{detail.id}}">已退款</span>
                                        {{else if detail.status==2}}<span class="l_xsztdj" data-id="{{detail.id}}">已消费</span>
                                        {{else if detail.status==3}}申请退款
                                        {{/if}}
                                </td>
                                <td width='10%'>
  {{if detail.status==2}}
<span>{{detail.usetimestr}}</span>
  {{/if}}
</td>
                                <td>
                                    {{if detail.status==0}}
                                    <a href='javascript:void(0);' data-code="{{detail.code}}" onclick="list.use({{detail.id}})"><span class='shenlan'>买家消费</span></a>
                                    {{else if detail.status==3}}
                                    <a href='javascript:void(0);' onclick="list.thsh({{detail.id}})"><span class='shenlan'>退款审核</span></a>
{{/if}}
                                </td>
                            </tr>
                            {{/each}}
                        </table>
                    </div>
						
				  {{/each}}
                </script>
            </table>
            </div>
        </div><!--table-con  stop -->
        <div class="clear"></div>
        <div id="pager" class="page">

        </div>

    </div><!--mainright stop -->

