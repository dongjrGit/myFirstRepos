<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/seller/js/DdGl/orderinvoice.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
     
    	list.getlist(1);
    })
</script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 订单管理 &gt; 订单发票管理
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>
                <span><label>订单编号：</label><input id="code" name="" type="text" class="text-inp"></span>
                <span>
                    <label>发票类型：</label>
                    <select id="type" class="sel-form">
                        <option value="" selected>全部</option>
                        <option value="1">普通发票</option>
                        <option value="2">增值税发票</option>
                    </select>
                </span>
                <span>
                    <label>打印状态：</label>
                    <select id="status" class="sel-form">
                        <option  value="" selected>全部</option>
                        <option value="0">未打印</option>
                        <option value="1">已打印</option>
                    </select>
                </span>
                <span class="marright0">
                    <label>下单日期：</label>
                    <input type="text" id="add_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'add_end\')}' })" />--
                    <input type="text" id="add_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'add_begin\')}' })" />
                </span>
                <span class="marright0">
                    <label>发货日期：</label>
                    <input type="text" id="Deliver_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'Deliver_end\')}' })" />--
                    <input type="text" id="Deliver_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'Deliver_begin\')}' })" />
                </span>

                <div class="clear"></div>

                <div class="submit-but">
                    <input name="search" type="button" value="查询" class="but-comm">
                    <input name="reset" type="button" value="清空" class="but-comm">
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="thgl">
            <table>
                <tr>
                    <th width="12%">订单号</th>
                    <th width="10%">发票类型</th>
                    <th width="8%">抬头类型</th>
                    <th width="30%">发票抬头</th>
                    <th width="10%">下单时间</th>
                    <th width="10%">打印时间</th>
                    <th width="10%">打印状态</th>
                    <th width="10%">操作</th>
                </tr>
                <tbody id="datalist">
                    <script id="invlist" type="text/html">
                        {{each list as inv i}}
                        <tr>
                            <td>{{inv.ordercode}}</td>
                            <td>
                                {{if inv.type==1}}普通发票
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
                                {{if inv.status==0}}<a href="javascript:void(0);" onclick="setStatus({{inv.id}})"><span class="bjxx">标记打印</span></a>
                                {{else}}<span>已打印</span>
                                {{/if}}
                            </td>
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

