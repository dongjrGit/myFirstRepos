<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/seller/js/DdGl/orderSearch.js"></script>
<script src="/resource/public/seller/js/DdGl/orderstatus.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
     
    	list.gethhList(1);
    })
</script>
<!--主要内容开始 -->
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 订单管理 &gt; 换货管理
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>
                <span><label>订单编号：</label><input id="code" name="" type="text" class="text-inp"></span>
                <span><label>配送编号：</label><input id="logisticscode" name="" type="text" class="text-inp"></span>
                <span class="marright0">
                    <label>下单日期：</label>
                    <input type="text" id="add_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'add_end\')}' })" />--
                    <input type="text" id="add_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'add_begin\')}' })" />
                </span>
                <span>
                    <label>订单状态：</label>
                    <select id="status" class="sel-form">
                        <option selected>全部</option>
                        <option value="30">申请换货</option>
                        <option value="31">换货买家待发货</option>
                        <option value="32">换货商家待收货</option>
                        <option value="33">换货商家待发货</option>
                        <option value="34">换货买家待收货</option>
                        <option value="39">换货成功</option>
                    </select>
                </span>
                <span class="marright0">
                    <label>发货日期：</label>
                    <input type="text" id="Deliver_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'Deliver_end\')}' })" />--
                    <input type="text" id="Deliver_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'Deliver_begin\')}' })" />
                </span>

                <div class="clear"></div>

                <div class="submit-but">
                    <input name="searchHH" type="button" value="查询" class="but-comm">
                    <input name="reset" type="button" value="清空" class="but-comm">
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="l_gulitab">
            <table>
                <tr>
                    <th width="20%">订单详情</th>
                    <th width="12%">商品单价</th>
                    <th width="10%">商品数量</th>
                    <th width="15%">买家信息</th>
                    <th width="12%">订单状态</th>
                    <th width="15%">下单时间</th>
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
                                        {{/if}}
                                    </td>
                                    <td class="l_toptd"></td>
                                    <td class="l_toptd">
                                        <a href='ddgl_OrderDetail?id={{order.id}}&ch=4'><span class='shenlan'>查看详情</span></a>
                                    </td>
                                </tr>
                                {{each order.children as detail j}}
                                <tr>
                                    <td width='20%'>{{detail.productname}}</td>
                                    <td width='12%'>{{detail.productprice | toFixed}}</td>
                                    <td width='10%'>{{detail.productcount}}</td>
                                    <td width='15%'>{{order.buyerName}}</td>
                                    <td width='15%'>                                 
                                            {{if detail.status==30 }}<span class="l_xsztdj" data-id="{{detail.id}}">申请换货</span>
                                            {{else if detail.status==31 }}<span class="l_xsztdj" data-id="{{detail.id}}">换货买家待发货</span>
                                            {{else if detail.status==32 }}<span class="l_xsztdj" data-id="{{detail.id}}">换货商家待收货</span>
                                            {{else if detail.status==33 }}<span class="l_xsztdj" data-id="{{detail.id}}">换货商家待发货</span>
                                            {{else if detail.status==34 }}<span class="l_xsztdj" data-id="{{detail.id}}">换货买家待收货</span>
                                            {{else if detail.status==39 }}换货成功
                                            {{else if detail.status==40 }}<span class="l_xsztdj" data-id="{{detail.id}}">申请维修</span>
                                            {{else if detail.status==41 }}<span class="l_xsztdj" data-id="{{detail.id}}">维修买家待发货</span>
                                            {{else if detail.status==42 }}<span class="l_xsztdj" data-id="{{detail.id}}">维修商家待收货</span>
                                            {{else if detail.status==43 }}<span class="l_xsztdj" data-id="{{detail.id}}">维修商家待发货</span>
                                            {{else if detail.status==44}}<span class="l_xsztdj" data-id="{{detail.id}}">维修买家待收货</span>
                                            {{else if detail.status==49}}维修成功
                                            {{/if}}
                                    </td>
                                    <td width='15%'>{{order.addOrderDatestr}}</td>
                                    <td>
                                        {{if detail.status==30 }}
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
                                        {{/if}}
                                    </td>
                                </tr>
                                {{/each}}
                            </table>
                        </div>
                        {{/each}}
                    </script>
                </table>
            </div><!--thgl 表格部分结束 -->
            <div class="clear"></div>
            <div id="pager" class="page">

            </div>
        </div><!--主要内容 右边结束 -->
    </div>
</div>