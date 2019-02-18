<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/platform/js/tj/tgtj_sh.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="account-form">
         <span>统计类型：</span>
        <select id="select_type" name="select_type" class="the-form-select-one" onclick="typeChange()">
            <option selected value="1">按天统计</option>
            <option value="2">最近一周</option>
            <option value="3">按月统计</option>
            <option value="4">按季度统计</option>
            <option value="5">按年统计</option>
        </select>
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
        <span style="width:450px;" id="divDay">
            <span class="marrig10"></span>
            <span>团购日期：</span>
            <input type="text" id="time" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'time1\')}' })" value="" readonly="readonly" />-
            <input type="text" id="time1" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'time\')}' })" value="" readonly="readonly" />
        </span>
        <span id="divMonth" style="width:280px;display:none;">
            <span class="marrig10"></span>
            <span>选择月份：</span>
            <input type="text" id="time2" onclick="WdatePicker({ dateFmt: 'yyyy-MM' })" value="" readonly="readonly" class="Wdate" />
        </span>
        <span id="divQuarter" style="width:280px;display:none;">
            <span class="marrig10"></span>
            <span>团购订单季度：</span>
            <input type="text" id="time3" onclick="WdatePicker({dateFmt:'yyyy-M季度', isQuarter:true,disabledDates:['....-0[5-9]-..','....-1[0-2]-..'], startDate:'%y-01-01' })" value="" readonly="readonly" class="Wdate" />
        </span>
        <span id="divYear" style="width:280px;display:none;">
            <span class="marrig10"></span>
            <span>团购订单年度：</span>
            <input type="text" id="time4" onclick="WdatePicker({dateFmt:'yyyy' })" value="" readonly="readonly" class="Wdate" />
        </span>
        <span class="marrig10"></span>
        <input class="inquire" name="search" type="button" id="searchtitle" searchtitle value="查询">
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con" id="divshow">
        <table class="data_list">
            <tr id="dd_title">
                <th width="10%">店铺</th>
                <th width="10%">总量</th>
                <th width="15%">总额（元）</th>
                <th width="10%">待付款（数/金额）</th>
                <th width="10%">待消费（数/金额）</th>
                <th width="10%">已完成（数/金额）</th>
                <th width="10%">已取消（数/金额）</th>
            </tr>
            <tbody id="datalist">
                <script id="Grouplist" type="text/html">
                    {{each list as group i}}
                        <tr name="dataTr">
							<td>{{group.shopname}}</td>
							<td>{{group.total}}</td>
							<td>{{group.totalprice | toFixed}}</td>
							<td>{{group.daicount}}/{{group.daiprice | toFixed}}</td>
							<td>{{group.daixiaocount}}/{{group.daixiaoprice | toFixed}}</td>
							<td>{{group.endcount}}/{{group.endprice | toFixed}}</td>
							<td>{{group.qucount}}/{{group.quprice | toFixed}}</td>
						</tr>
					{{/each}}
                </script>
            </tbody>
        </table>
    </div>
    <div class="clear"></div>
    <div id="pager" class="page">

    </div>
</div>
