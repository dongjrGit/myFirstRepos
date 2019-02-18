<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="/resource/public/platform/js/member/memberfinance.js"></script>
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<div class="account-form">
		<span>用户名：<input id="select_buyer" type="text" class="inp-seller" /></span>
        <span class="marrig10"></span>
		<!-- <span>商户名称：<input id="select_name" type="text" class="inp-seller" /></span>
		<span class="marrig10"></span> -->
	  <span>
            金额类型：<select name="" id="select_type" class="the-form-select-one">
              <option value="-1" selected>请选择</option>
              <option value="0">支出</option>
              <option value="1">收入</option>
              <option value="2">充值</option>
             <!--  <option value="3">冻结</option>
              <option value="4">解冻</option>
              <option value="5">冻结金额增加</option>
              <option value="6">冻结金额扣除</option>
              <option value="7">余额转入保证金</option> -->
              <option value="8">后台管理添加</option>
              <option value="11">退款返还</option>
             <!--  <option value="9">保证金充值</option> -->
        </select>
        </span>
        <span class="marrig10"></span>
        <span>订单编号：<input id="select_number" type="text" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>支付单号：<input id="select_paynum" type="text" class="inp-seller" /></span>
        <span class="marrig10"></span>
   <span>
       选择时间：
       <input type="text" id="select_begin" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })" class="text_time" /> 至
       <input type="text" id="select_end" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })" class="text_time" />
   </span>
   <br/>
   <span>
       金额：
       <input type="text" id="money_begin" class="inp-seller" /> ->
       <input type="text" id="money_end" class="inp-seller" />
   </span>
   <span class="marrig10"></span>
   <input class="chaxun" name="select_button" type="button" value="查询" onclick="PointsList.bind(1)">
        <span><input class="inquire" name="exportfinance" type="button" value="导出"></span>
   <br/>
   <span>
   支付类型：
   		支付宝支付<input name="checkbox_paytype" type="checkbox" value="6">
   		微信支付<input name="checkbox_paytype" type="checkbox" value="7">
   </span>
    	</div>
	</div>
    
        
	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		 <table class="data_list">
            <tr id="goodconsultlist_title">
                <th width="10%">用户名</th>
                <th width="10%">用户昵称</th>
                <th width="8%">金额类型</th>
                <th width="8%">发生金额</th>
                <th width="8%">支付类型</th>
                <th width="10%">订单编号</th>
                <th width="15%">支付单号</th>
                <th width="10%">日期</th>
                <th width="*">描述</th>
            </tr>
            <script id="goodconsultlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td>{{pro.username}}</td>
                    <td>{{pro.buyername}}</td>
                    {{if pro.type==0 }}
                    <td>支出</td>
                    {{else if pro.type==1 }}
                    <td>收入</td> 
					{{else if pro.type==2 }}
                    <td>充值</td> 
					{{else if pro.type==3 }}
                    <td>冻结</td>
					{{else if pro.type==4 }}
                    <td>解冻</td> 
					{{else if pro.type==5 }}
                    <td>冻结金额增加</td>
					{{else if pro.type==6 }}
                    <td>冻结金额扣除</td>
					{{else if pro.type==7 }}
                    <td>余额转入保证金</td>
					{{else if pro.type==8 }}
                    <td>后台管理添加</td>
					{{else if pro.type==9 }}
                    <td>保证金充值</td>  
                   {{else if pro.type==10 }}
                    <td>提现</td>
                   {{else if pro.type==11 }}
                    <td>退款返还</td>
                   {{else if pro.type==12 }}
                    <td>退款扣除</td>
                   {{else }}<td> </td>
                    {{/if}}
					<td>{{pro.money}}</td>
					{{if pro.paytype==1}}
						<td>在线支付</td>
					{{else if pro.paytype==2}}
						<td>余额支付</td>
					{{else if pro.paytype==3}}
						<td>优惠券支付</td>
					{{else if pro.paytype==4}}
						<td>混合支付</td>
					{{else if pro.paytype==5}}
						<td>货到付款</td>
					{{else if pro.paytype==6}}
						<td>支付宝支付</td>
					{{else if pro.paytype==7}}
						<td>微信支付</td>
					{{else if pro.paytype==8}}
						<td>IPS支付</td>
					{{else if pro.paytype==9}}
						<td>快捷支付</td>
					{{else if pro.paytype==10}}
						<td>银联支付</td>
					{{else if pro.paytype==11}}
						<td>浦发支付</td>
					{{else if pro.paytype==12}}
						<td>农行支付</td>
					{{else if pro.paytype==13}}
						<td>农行贷记卡</td>
					{{else if pro.paytype==0}}
						<td>未支付</td>
					{{else}}
						<td></td>
					{{/if}}
					<td>{{pro.number}}</td>	
					<td>{{pro.paynum}}</td>
					<td>{{pro.creattimestr}}</td>		
					<td>{{pro.description}}</td>
                {{/each}}
            </script>
        </table>
	</div>
	<!--table-con  stop -->

	<div class="clear"></div>
	<div class="page" id="pager"></div>
	<!--page stop -->


</div>
<!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //列表以及分页数据绑定
        PointsList.bind(1);
   	 //商户导出
   	 $("input[name='exportfinance']").first().bind("click", function() {
   		PointsList.daochu();
   		});
    })
</script>