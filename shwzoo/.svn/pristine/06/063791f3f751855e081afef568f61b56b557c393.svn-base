<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/platform/js/order/businessbills.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>
            <span>对账日期：</span>
            <input type="text" id="select_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })">--&gt;
            <input type="text" id="select_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })">
        </span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="chaxun" name="search" type="button" value="查询"></span>

    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th width="20%">供应商名称</th>
                    <th width="15%">订单日期</th>
                    <th width="10%">订单金额</th>
                    <th width="10%">状态</th>
                    <th width="15%">生成对账日期</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="list_bbill"></tbody>
        </table>
        <script id="bblist" type="text/html">
            {{each list as bbs i}}
            <tr>
                <td>{{bbs.shopname}}</td>
                <td>{{bbs.orderdate | toDateTimes}}</td>
                <td>{{bbs.orderprice | toFixed}}</td>
                <td>{{if bbs.status==0}}未处理{{else if bbs.status==1}}已处理{{/if}}</td>
                <td>{{bbs.createdate | toDateTimes}}</td>
               <td>
					<!--判断未处理-->
					{{if ${status} == 0 }}
                    <a class="chuli" onclick="chuli(this)" href="javascript:void(0)" bedit="{{bbs.id}}" bstu="{{bbs.status}}"><span class="shenlan">处理</span></a>
					{{/if}}
					<a class="chakan" onclick="chakan(this)" href="javascript:void(0)" dateb="{{bbs.orderdate | toDateTimes}}" bedit="{{bbs.shopid}}"  bedis="{{bbs.status}}"><span class="shenlan">明细</span></a>
                </td>
            </tr>
            {{/each}}
        </script>

        <div class="clear"></div>
        <div id="pager" class="page">

        </div><!--page stop -->
        <div class="clear"></div>
    </div><!--table-con  stop -->

</div><!--mainright stop -->
<script>
    $(document).ready(function () {
        list.getlist(1);
    })
</script>