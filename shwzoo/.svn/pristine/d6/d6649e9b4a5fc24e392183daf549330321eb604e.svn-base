<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="/resource/public/platform/js/member/memberPoints.js"></script>
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<div class="account-form">
			 <span>会员名称：<input id="select_buyer" type="text" class="inp-seller" /></span>
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
            类型：<select name="" id="select_type" class="the-form-select-one">
              <option value="-1" selected>请选择</option>
              <option value="0">收入</option>
              <option value="1">支出</option>
        </select>
        </span>
        <span class="marrig10"></span>
        
   <span>
       选择时间：
       <input type="text" id="select_begin" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })" class="text_time" /> 至
       <input type="text" id="select_end" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })" class="text_time" />
   </span>
   <span class="marrig10"></span>
        <input class="chaxun" name="select_button" type="button" value="查询" onclick="PointsList.bind(1)">
        
    	</div>
	</div>
    
        
	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		 <table class="data_list">
            <tr id="goodconsultlist_title">
                <th>会员名称</th>
                <th>类型</th>
                <th>收入/支出</th>
                <th>详细说明</th>
                <th>日期</th>
               
            </tr>
            <script id="goodconsultlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td>{{pro.username}}</td>
					
                    {{if pro.type==1 }}
                    <td>支出</td>
                    {{else if pro.type==0 }}
                    <td>收入</td> 
                    {{/if}}
					<td>{{pro.points}}</td>	
					<td>
                     {{if pro.fromtype==0}}
                                    支付订单
                     {{else if pro.fromtype==1}}
                                    订单评价
                     {{else if pro.fromtype==2}}
                                    签到
                     {{else if pro.fromtype==3}}
                                    活动增送
                     {{else if pro.fromtype==4}}
                                    订单消费
                     {{else if pro.fromtype==5}}
                                    活动消费
                     {{/if}}
                    </td>
					<td>{{pro.createtimetr}}</td>	
                </tr>
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
    })
</script>