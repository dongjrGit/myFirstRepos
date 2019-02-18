<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/platform/js/order/ddgl_invoice.js"></script>
<script>
    $(document).ready(function () {
        list.bind(1);
    })
</script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>订单号:</span>
        <input id="code" type="text" class="inp-seller" value="">
        <span class="marrig10"></span>
        <span>发票类型：</span>
        <select id="type" class="the-form-select-one">
            <option value="" selected>全部</option>
            <option value="1">普通发票</option>
            <option value="2">电子发票</option>
            <option value="3">增值税发票</option>
        </select>
        <span class="marrig10"></span>
        <span>打印状态：</span>
        <select id="status" name="" class="the-form-select-one">
            <option value="" selected>全部</option>
            <option value="0">未打印</option>
            <option value="1">已打印</option>
        </select>
        <span class="marrig10"></span>
        <span>订单日期:</span>
        <input type="text" id="select_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })" />-->
        <input type="text" id="select_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })" />
        <input class="inquire" name="search" type="button" value="查询">

    </div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr>
                <th width="10%">订单号</th>
                <th width="10%">发票类型</th>
                <th width="10%">抬头类型</th>
                <th width="25%">发票抬头</th>
                <th width="10%">下单时间</th>
                <th width="10%">打印时间</th> 
                <th width="10%">打印状态</th>
                <th width="15%">发票操作</th>
            </tr>

            <tbody id="datalist">
                <script id="invlist" type="text/html">
                    {{each list as inv i}}
                    <tr>
                        <td>{{inv.ordercode}}</td>
                        <td>
                            {{if inv.type==1}}普通发票
                            {{else if inv.type=2}}电子发票
							{{else}}增值税发票
                            {{/if}}
                        </td>
                        <td>
                            {{if inv.titletype==0}}个人
                            {{else}}公司
                            {{/if}}
                        </td>
                        <td>{{inv.title}}</td>
                        <td>{{inv.addorderdate}}</td>
                        <td>{{inv.printdate}}</td>
                        <td id="tds_{{inv.id}}">
                            {{if inv.status==0}}未打印
                            {{else}}已打印
                            {{/if}}
                        </td>
                        <td id="td_{{inv.id}}">
                            {{if inv.status==0}}<a href="javascript:void(0);" onclick="setStatus({{inv.id}})"><span class="shenlan">标记打印</span></a>
                            {{else}}<span class="huise">已打印</span>
                            {{/if}}
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div>
    </div><!--table-con  stop -->
</div>
