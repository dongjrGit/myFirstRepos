<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/seller/js/DdGl/orderSearch.js"></script>
<script src="/resource/public/seller/js/DdGl/orderstatus.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
     
    	list.getthList(1);
    })
</script>
<!--主要内容开始 -->
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 订单管理 &gt; 退货管理
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
                        <option value="-1" selected>全部</option>
                        <option value="50">申请退货退款</option>
                        <option value="51">退货买家待发货</option>
                        <option value="52">退货商家待收货</option>
                        <option value="59">退货退款成功</option>
                   <!--      <option value="20">申请退款</option>
                        <option value="29">退款成功</option> -->
                    </select>
                </span>
                <span class="marright0">
                    <label>发货日期：</label>
                    <input type="text" id="Deliver_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'Deliver_end\')}' })" />--
                    <input type="text" id="Deliver_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'Deliver_begin\')}' })" />
                </span>

                <div class="clear"></div>

                <div class="submit-but">
                    <input name="searchTH" type="button" value="查询" class="but-comm">
                    <input name="reset" type="button" value="清空" class="but-comm">
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="l_gulitab">
            <table>
                <tr>
                    <th width="20%">订单详情</th>
                    <th width="8%">商品单价</th>
                    <th width="8%">商品数量</th>
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
                                    {{else if order.status==1}}出票中
                                    {{else if order.status==2}}待使用
                                    {{else if order.status==3}}审核中
                                    {{else if order.status==4}}已取消
									{{else if order.status==9}}已完结
                                    {{/if}}   
                                    </td>
                                    <td class="l_toptd"></td>
                                    <td class="l_toptd">
                                        <a href='ddgl_OrderDetail?id={{order.id}}&ch=3'><span class='shenlan'>查看详情</span></a>
                                    </td>
                                </tr>
                                {{each order.children as detail j}}
                                <tr>
                                    <td width='20%'>{{detail.productname}}</td>
                                    <td width='12%'>{{detail.productprice | toFixed}}</td>
                                    <td width='10%'>{{detail.productcount}}</td>
                                    <td width='15%'>{{order.buyerName}}</td>
                                    <td width='15%' style="position: relative;">
                                        <div class="pos_rela">
                                            {{if detail.status==50}}<span class="l_xsztdj" data-id="{{detail.id}}">申请退货退款</span>
                                            {{else if detail.status==51}}<span class="l_xsztdj" data-id="{{detail.id}}">退货买家待发货</span>
                                            {{else if detail.status==52}}<span class="l_xsztdj" data-id="{{detail.id}}">退货商家待收货</span>
                                            {{else if detail.status==59}}退货退款成功
                                            {{else if detail.status==20 }}<span class="l_xsztdj" data-id="{{detail.id}}">申请退款</span>
                                            {{else if detail.status==29 }}退款成功
                                            {{/if}}
                                        </div>
                                    </td>
                                    <td width='15%'>{{order.addOrderDatestr}}</td>
                                    <td>
                                        {{if detail.status==50}}
                                        <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>查看原因</span></a>
                                        {{else if detail.status==52}}
                                        <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>收货</span></a>
                                        {{else if detail.status==20 }}
                                        <a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>查看原因</span></a>
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
