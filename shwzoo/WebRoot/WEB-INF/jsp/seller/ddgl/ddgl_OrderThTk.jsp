<!-- @{
    ViewBag.Title = "售后维修管理";
    Layout = "~/Areas/Seller/Views/Shared/_Layout_Seller_Center.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="${ctx }/resource/public/seller/js/DdGl/OrderThTk.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    	list.getwxList(1);
    })
</script>
<!--主要内容开始 -->
<div id="container">
<input type="hidden" id="shopid" value="${shopid}">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 订单管理 &gt; 退货退款管理
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>
                <span><label>订单编号：</label><input id="code" name="" type="text" class="text-inp"></span>
                <span class="marright0">
                    <label>申请日期：</label>
                    <input type="text" id="add_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'add_end\')}' })" />--
                    <input type="text" id="add_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'add_begin\')}' })" />
                  <!--    <input type="text" id="add_begin" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'time1\')}' })" value="" readonly="readonly" class="Wdate" />-
                <input type="text" id="add_end" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'time\')}' })" value="" readonly="readonly" class="Wdate" /> -->
                </span>
               <!--  <span>
                    <label>订单状态：</label>
                    <select id="status" class="sel-form">
                        <option selected>全部</option>
                        <option value="40">申请维修</option>
                        <option value="41">维修买家待发货</option>
                        <option value="42">维修货商家待收货</option>
                        <option value="43">维修商家待发货</option>
                        <option value="44">维修买家待收货</option>
                        <option value="49">维修成功</option>
                    </select>
                </span> -->
             <!--    <span class="marright0">
                    <label>发货日期：</label>
                    <input type="text" id="Deliver_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'Deliver_end\')}' })" />--
                    <input type="text" id="Deliver_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'Deliver_begin\')}' })" />
                </span> -->

                <div class="clear"></div>

                <div class="submit-but">
                    <input name="searchWX"  id="searchBtn" type="button" value="查询" class="but-comm">
                   <!--  <input name="reset" type="button" value="清空" class="but-comm">
                    <input name="" type="button" value="导出订单" class="but-comm"> -->
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
         <div class="mar35"></div>
        <div class="thgl">
            <table  class="data_list">
                <tr id="pack_title">
                    <th width="18%">订单号</th>
                    <th width="5%">用户</th>
                    <th width="12%">联系电话</th>
                    <th width="12%">描述</th>
                    <th width="12%">申请时间</th>
                    <th width="15%">状态</th>
                    <th >操作</th>
                </tr>
             <tbody id="datalist">
             	<script id="packlist" type="text/html">
                         
					{{each list as re i}}        		
 					<tr>
                    <td>{{re.ordercode}}</td>
                    <td>{{re.name}}    </td>
                    <td>{{re.mobile}}</td>
                    <td>{{re.content}}</td>
                    <td>{{re._createtime}}</td>
                    {{if re.status==9}}
                    <td class="fxythh"><span class="red">申请退款退货中</span></td>
                    {{else}}
                    {{if re.status==10}}
                    <td class="fxythh"><span class="red">已退款</span></td>
 					{{/if}}
                    {{/if}}
					<td>
					   <a href='/seller/shopdd/ddgl_OrderDetailTh?id={{re.orderid}}'><span class='shenlan'>查看详情</span></a>
						  <a href='/seller/shopdd/editNote?id={{re.id}}'><span class='shenlan'>编辑备注</span></a>
					 <a href="javascript:void(0);" onclick="Service.del({{re.id}})"><span class="shenlan" title="删除">删除</span></a>
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
</div>
