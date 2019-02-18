<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .tr1{line-height:35px;}
    .td1{width:100px;text-align:left;}
    .td2{text-align:left;background-color:#D2ECFB}
</style>
<div id="container">
<input type="hidden" id="userid" value="${userid}">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 订单统计 &gt; 全部订单
        </div><!--所在位置信息  结束 -->
        <div class="clear"></div>
        <div class="l_ddhz">
            <table>
                <tr>
                    <td colspan="4" class="l_qbddtop">全部订单</td>
                </tr>
                <tr class="l_center">
                    <td class="l_qbdd">订单总数</td>
                    <td>${order.totalCount}</td>
                    <td class="l_qbdd">订单总金额</td>
                    <td>${order.totalMoney}</td>
                </tr>
            </table>
        </div>
        <div class="l_ddhz">
            <table>
                <tr>
                    <td colspan="8" class="l_qbddtop">正常订单</td>
                </tr>
                <tr class="l_center">
                    <td class="l_qbdd">待付款数</td>
                    <td>${order.count_DFK}</td>
                    <td class="l_qbdd">待付款金额</td>
                    <td>${order.money_DFK}</td>
                    <td class="l_qbdd">已完结数</td>
                    <td>${order.count_YWJ}</td>
                    <td class="l_qbdd">已完结金额</td>
                    <td>${order.money_YWJ}</td>
                </tr>
                <tr class="l_center">
                    <td class="l_qbdd">出票中数</td>
                    <td>${order.count_CPZ}</td>
                    <td class="l_qbdd">出票中金额</td>
                    <td>${order.money_CPZ}</td>
                    <td class="l_qbdd">退款中数</td>
                    <td>${order.count_SHZ}</td>
                    <td class="l_qbdd">退款中金额</td>
                    <td>${order.money_SHZ}</td>
                </tr>
            </table>
        </div>
        <div class="l_ddhz">
            <table>
                <tr>
                    <td colspan="8" class="l_qbddtop">取消订单</td>
                </tr>
                <tr class="l_center">
                    <td class="l_qbdd">已取消数</td>
                    <td>${order.count_QX}</td>
                    <td class="l_qbdd">已取消金额</td>
                    <td>${order.money_QX}</td>
                </tr>
            </table>
        </div>
       <div class="l_ddhz">
            <table>
                <tr>
                    <td colspan="8" class="l_qbddtop">退款退货订单</td>
                </tr>
                <tr class="l_center">
                    <td class="l_qbdd">退款申请数</td>
                    <td>${order.count_TKSQ}</td>
                    <td class="l_qbdd">退款申请金额</td>
                    <td>${order.money_TKSQ}</td>
                    <td class="l_qbdd">退款失败数</td>
                    <td>${order.count_TKSB}</td>
                    <td class="l_qbdd">退款失败金额</td>
                    <td>${order.money_TKSB}</td>
                </tr>
                <tr class="l_center">
                   <td class="l_qbdd">退货中数</td>
                    <td>${order.count_TKSH}</td>
                    <td class="l_qbdd">退货中金额</td>
                    <td>${order.money_TKSH}</td>
                    <td class="l_qbdd">已退款数</td>
                    <td>${order.count_YTK}</td>
                    <td class="l_qbdd">已退款金额</td>
                    <td>${order.money_YTK}</td>
                </tr>
                
            </table>
        </div>
        <!--主要内容 右边结束 -->
    </div>
</div>
