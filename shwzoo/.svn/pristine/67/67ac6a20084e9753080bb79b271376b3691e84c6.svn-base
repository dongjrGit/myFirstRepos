<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/seller/js/DdGl/grouporder.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
     
    	gorder.getlist(1);
    })
</script>
<!--主要内容开始 -->
<div id="container">
	<div class="allcon">
		<div class="position">您所在的位置：订单管理 &gt; 订单管理 &gt; 团购订单列表</div>
		<!--所在位置信息  结束 -->
		<div class="the-form">
			<form>
				<span><label>订单编号：</label><input id="ordercode" name=""
					type="text" class="text-inp"></span> 
                   <span
					class="marright0"> <label>下单日期：</label> <input type="text"
					id="add_begin" class="Wdate"
					onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'add_end\')}' })" />--
					<input type="text" id="add_end" class="Wdate"
					onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'add_begin\')}' })" />
				</span> <span> <label>订单状态：</label> <select id="status"
					class="sel-form">
						<option value="" selected>全部订单</option>
						<option value="0">待付款</option>
						<option value="2">已取消</option>
						<option value="1">已完成</option>
				</select>
				</span>
				<div class="clear"></div>
				<div class="submit-but">
					<input name="search" type="button" value="查询" class="but-comm">
				</div>
			</form>
		</div>
		<!--表单部分结束 -->
		<div class="clear"></div>
		<div class="l_gulitab">
	     <table class="data_list">
            <tr id="dd_title">
                <th width='40%'>订单详情</th>
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
                                <td width='40%' class="l_toptd">
                                    <span>订单号：{{order.ordercode}}</span> <span>订单金额：{{order.orderprice | toFixed}}</span>
                                    <span>实际支付金额：{{order.payprice | toFixed}}</span>
                                </td>
                                <td width='10%' class="l_toptd">{{order.buyname}}</td>
                                <td width='10%' class="l_toptd">{{order.starttime}}</td>
                                <td width='10%' class="l_toptd">{{order.endtime}}</td>
                                <td width='10%' style="text-align:center;" class="l_toptd">
                                    {{if order.status==0}}待付款
                                    {{else if order.status==1}}已完成
                                    {{else if order.status==2}}已取消
                                    {{/if}}                               
                                </td>
                                <td width='10%' class="l_toptd" >{{order.createtime}}</td>
                                <td class="l_toptd">
                                    <a href='javascript:void(0);' onclick="gorder.del({{order.id}})"><span class='shenlan'>删除</span></a>
                                </td>
                            </tr>
                            {{each order.detaillist as detail j}}
                            <tr>
                                <td width='40%'>{{order.title}}</td>
                                <td width='10%'>团购码</td>
                                <td width='20%' colspan="2">{{detail.code}}</td>
                                <td width='10%'>
                                        {{if detail.status==10}}未使用
                                        {{else if detail.status==11}}<span class="l_xsztdj" data-id="{{detail.id}}">已退款</span>
                                        {{else if detail.status==12}}<span class="l_xsztdj" data-id="{{detail.id}}">已消费</span>
                                        {{else if detail.status==13}}已评价
                                        {{/if}}
                                </td>
                                <td width='10%'>
                               {{if detail.status==12}}
                                 <span>{{detail.usetimestr}}</span>
                               {{/if}}
                                </td>
                                <td>
                                    {{if detail.status==10}}
                                     <a href='javascript:void(0);' data-code="{{detail.code}}" onclick="gorder.use({{detail.id}})"><span class='shenlan'>买家消费</span></a>
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
			<!--thgl 表格部分结束 -->
			<div class="clear"></div>
			<div id="pager" class="page"></div>
		</div>
		<!--主要内容 右边结束 -->
	</div>
</div>