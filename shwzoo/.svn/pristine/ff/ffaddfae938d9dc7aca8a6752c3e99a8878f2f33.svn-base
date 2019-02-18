<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/platform/js/tj/ddtj_sh.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="account-form">
        <span>统计类型：</span>
        <input type="hidden" id="aft" value="2" />
        <select id="select_type" name="select_type" class="the-form-select-one" onclick="solist.typeChange()">
            <option selected value="1">按天统计</option>
            <option value="2">最近一周</option>
            <option value="3">按月统计</option>
            <option value="4">按季度统计</option>
            <option value="5">按年统计</option>
        </select>
        <span class="marrig10"></span>
        <span>所属店铺：<input id="select_shop" type="text" class="inp-seller" /></span>
        <div>
            <ul>
                <script id="select_shoplist" type="text/html">
                    {{each list as shop i}}
                    <li data="{{shop.userid}}">{{shop.name}}</li>
                    {{/each}}
                </script>
            </ul>
        </div>
        <span style="width:450px;" id="divDay">
            <span class="marrig10"></span>
            <span>订单日期：</span>
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
            <span>订单季度：</span>
            <input type="text" id="time3" onclick="WdatePicker({dateFmt:'yyyy-M季度', isQuarter:true,disabledDates:['....-0[5-9]-..','....-1[0-2]-..'], startDate:'%y-01-01' })" value="" readonly="readonly" class="Wdate" />
        </span>
        <span id="divYear" style="width:280px;display:none;">
            <span class="marrig10"></span>
            <span>订单年度：</span>
            <input type="text" id="time4" onclick="WdatePicker({dateFmt:'yyyy' })" value="" readonly="readonly" class="Wdate" />
        </span>
        <span class="marrig10"></span>
        <input class="inquire" name="search" type="button" onclick="aftersearch(2)" value="查询">
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="dd_title">
                <th width="*">店铺</th>
                <th width="8%">总数量</th>
                <th width="10%">总金额(元)</th>
                <th id="thday" width="12%">订单日期</th>
                <th width="10%">换货申请</th>
                <th width="10%">换货买家发货</th>
                <th width="10%">换货卖家收货</th>
                <th width="10%">换货卖家发货</th>
                <th width="10%">换货买家收货</th>
                <th width="8%">换货成功</th>
            </tr>
            <tbody id="datalist">
                <script id="ddlist" type="text/html">
                    {{each list as saleorder i}}
                    <tr>
                        <td>{{saleorder.name}}</td>
                        <td>{{saleorder.totalcount}}</td>
                        <td>{{saleorder.totalmoney | toFixed}}</td>
                        {{if saleorder.orderdate!=null}}
                        <td>
                            {{saleorder.orderdateStr | toDateDay}}
                        </td>
                        {{/if}}
                        <td>{{saleorder.count_HHSQ}}/{{saleorder.money_HHSQ | toFixed}}</td>
                        <td>{{saleorder.count_BuyFH}}/{{saleorder.money_BuyFH | toFixed}}</td>
                        <td>{{saleorder.count_MJSH}}/{{saleorder.money_MJSH | toFixed}}</td>
                        <td>{{saleorder.count_MJFH}}/{{saleorder.money_MJFH | toFixed}}</td>
                        <td>{{saleorder.count_BuySH}}/{{saleorder.money_BuySH | toFixed}}</td>
                        <td>{{saleorder.count_HH}}/{{saleorder.money_HH | toFixed}}</td>
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
