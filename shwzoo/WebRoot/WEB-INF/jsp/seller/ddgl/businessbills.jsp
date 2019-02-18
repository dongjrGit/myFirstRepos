<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/seller/js/DdGl/businessbills.js"></script>
<script>
    $(document).ready(function () {
        list.getlist(1);
    })
</script>
<!--主要内容开始 -->
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 对账单管理 &gt; ${status==0?"未处理佣金列表":"已处理佣金列表" }
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
        <input name="shopid" id="shopid" value=${shopid} type="hidden"></input>
            <form>
                <span>
	            <span>对账日期：</span>
		            <input type="text" id="select_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })">--&gt;
		            <input type="text" id="select_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })">
		        </span>
                <div class="clear"></div>
                <div class="submit-but">
                    <input name="chaxun" type="button" value="查询" class="but-comm">
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="thgl">
            <table>
            <thead>
                <tr>
                   <th width="20%">供应商名称</th>
                    <th width="15%">订单日期</th>
                    <th width="10%">订单金额</th>
                    <th width="10%">状态</th>
                    <th width="15%">生成对账日期</th>
                    <th >操作</th>
                </tr>
                </thead>
                 <tbody id="list_bbill"></tbody>
            </table>
            <div class="l_ddgl">
                <table id="list_bbillf">
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
					{{if ${status} == 0}}
                    <a class="chuli" onclick="chuli(this)" href="javascript:void(0)" bedit="{{bbs.id}}" bstu="{{bbs.status}}"><span class="shenlan">处理</span></a>
                   	{{/if}}
					<a class="chakan" onclick="chakan(this)" href="javascript:void(0)" dateb="{{bbs.orderdate | toDateTimes}}"  bedit="{{bbs.shopid}}" bedis="{{bbs.status}}"><span class="shenlan">查看</span></a>
                </td>
            </tr>
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
