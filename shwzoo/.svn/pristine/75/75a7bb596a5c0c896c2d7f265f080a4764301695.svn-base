<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="/resource/public/seller/js/spgl/goods_statistics.js"></script>

<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：商品管理管理 &gt; 商品销量统计 &gt; pc端统计
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
        <span>
        <label>统计类型：</label>
         <select id="select_type" name="select_type" class="sel-form" onclick="glist.typeChange()">
            <option selected value="1">按天统计</option>
            <option value="2">最近一周</option>
            <option value="3">按月统计</option>
            <option value="4">按季度统计</option>
            <option value="5">按年统计</option>
        </select>
        </span>
        <span style="width:450px;" id="divDay">
            <label>订单日期：</label>
            <input type="text" id="time" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'time1\')}' })" value="" readonly="readonly" />-
            <input type="text" id="time1" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'time\')}' })" value="" readonly="readonly" />
        </span>
        <span id="divMonth" style="width:280px;display:none;">
            <label>选择月份：</label>
            <input type="text" id="time2" onclick="WdatePicker({ dateFmt: 'yyyy-MM' })" value="" readonly="readonly" class="Wdate" />
        </span>
        <span id="divQuarter" style="width:280px;display:none;">
            <label>订单季度：</label>
            <input type="text" id="time3" onclick="WdatePicker({dateFmt:'yyyy-M季度', isQuarter:true,disabledDates:['....-0[5-9]-..','....-1[0-2]-..'], startDate:'%y-01-01' })" value="" readonly="readonly" class="Wdate" />
        </span>
        <span id="divYear" style="width:280px;display:none;">
            <label>订单年度：</label>
            <input type="text" id="time4" onclick="WdatePicker({dateFmt:'yyyy' })" value="" readonly="readonly" class="Wdate" />
        </span>     
          <input type="hidden" id="select_site" name="select_site" value="1">
       
        <span><label>商品名称：</label><input type="text" class="text-inp" id="proname" /></span>
        <span><label>商品编号：</label><input type="text" class="text-inp" id="pronum" /></span>
        <span><label>销售数量：</label>
        <input type="text" class="text-inp" id="countf" />-
        <input type="text" class="text-inp" id="countt" />
        </span>
        
        <input class="but-comm" name="search" type="button" id="search" value="查询">

        </div>
        <div class="clear"></div>
        <div class="thgl">
            <table>
                <tr id="dd_title">
                <th width="6%">序号</th>
                <th width="*">商品名称</th>
                  <th width="12%">商品编号</th>
                <th width="10%">商品售价</th>
                <th width="10%">销售数量</th>
                <th id="thday" width="15%">下单时间</th>
                </tr>
                <tbody id="datalist">
                    <script id="gdlist" type="text/html">
                        {{each list as gd i}}
                        <tr>
                             <td>{{i+1}}</td>
                    <td>{{gd.productname}}</td>
                    <td>{{gd.productnum}}</td>
                    <td>{{gd.productprice}}</td>
                    <td>{{gd.totalcount}}</td>
                    {{if gd.orderdate!=null }}
                    <td>{{gd.orderdateStr | toDateDay}}</td>
                    {{/if}}
                        </tr>
                        {{/each}}
                    </script>
                </tbody>
            </table>
        </div><!--thgl 表格部分结束 -->
        <div class="clear"></div>
        <div id="pager" class="page">

        </div>
    </div><!--主要内容 右边结束 -->
</div>