<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="${ctx }/resource/public/seller/js/Zhgl/financelist.js"></script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：账号管理 &gt; 个人中心 &gt; 财务记录
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <span>
                <label>订单编号：</label><input id="text_ordernum" name="" type="text" class="text-inp" style="color: rgb(0, 0, 0);">
            </span>
            <span>
                <label>支付单号：</label><input id="text_paynum" name="" type="text" class="text-inp" style="color: rgb(0, 0, 0);">
            </span>
            <span>
                <label>金额状态：</label>
                <select id="select_status" class="sel-form">
                    <option value="-1" selected>全部状态</option>
                    <option value="0">支出</option>
                    <option value="1">收入</option>
                    <option value="2">充值</option>
                    <option value="3">冻结</option>
                    <option value="4">解冻</option>
                    <option value="5">冻结金额增加</option>
                    <option value="6">冻结金额扣除</option>
                    <option value="7">余额转入保证金</option>
                    <option value="8">后台管理添加</option>
                </select>
            </span>
            <span>
                <label>发生日期：</label>
                <input type="text" id="time" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'time1\')}' })" value="" readonly="readonly" class="Wdate" />-
                <input type="text" id="time1" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'time\')}' })" value="" readonly="readonly" class="Wdate" />
            </span>
            <input class="but-comm" name="search" type="button" value="查询">
        </div>
        <div class="clear"></div>
        <div class="thgl">
            <table id="datalist">
                <tr class="blank-tr"><td colspan="16"><div style="height:10px;"></div></td></tr>
                <tr>
                    <th width="5%">用户余额</th>
                    <th width="5%">发生金额</th>
                    <th width="5%">金额类型</th>
                    <th width="8%">发生时间</th>
                    <th width="8%">订单号</th>
                    <th width="8%">支付单号</th>
                    <th width="8%">描述</th>
                </tr>
                <tbody id="dd_title">
                    <script id="ddlist" type="text/html">
                        {{each list as finance i}}
                        <tr>
                            <td>{{finance.balance | toFixed}}</td>
                            <td>{{finance.money | toFixed}}</td>
                            {{if finance.type==0}}
                            <td>支出</td>
                            {{else if finance.type==1}}
                            <td>收入</td>
                            {{else if finance.type==2}}
                            <td>充值</td>
                            {{else if finance.type==3}}
                            <td>冻结</td>
                            {{else if finance.type==4}}
                            <td>解冻</td>
                            {{else if finance.type==5}}
                            <td>冻结金额增加</td>
                            {{else if finance.type==6}}
                            <td>冻结金额扣除</td>
                            {{else if finance.type==7}}
                            <td>余额转入保证金</td>
                            {{else if finance.type==8}}
                            <td>后台管理添加</td>
                            {{else}}
                            {{/if}}
                            <td>{{finance.creattimestr}}</td>
                            <td>{{finance.number}}</td>
                            <td>{{finance.paynum}}</td>
                            <td>{{finance.description}}</td>
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
<script type="text/javascript">
    $(document).ready(function () {
        // solist.bind1(1);
    })
</script>
