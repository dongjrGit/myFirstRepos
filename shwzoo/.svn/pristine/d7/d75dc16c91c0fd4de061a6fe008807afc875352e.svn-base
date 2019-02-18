<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/seller/js/DdGl/orderSearch.js"></script>
<script src="/resource/public/seller/js/DdGl/orderstatus.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    $(document).ready(function () {
     
    	 list.getlist(1);
    })
    function outputExcel(){
    	var code = $("#code").val();
        var wlcode = $("#logisticscode").val();
        var c = $("#add_begin").val();
        var add_end = $("#add_end").val();
        var d_begin = $("#Deliver_begin").val();
        var d_end = $("#Deliver_end").val();
        var status = $("#status").val();
        var action="/seller/shoporder/outPutOrderExcel?1=1"
        if(code!=""){
        	action=action+"&num="+code;
        }
        if(wlcode!=""){
        	action=action+"&logisticscode="+wlcode;
        }
        if(add_end!=""){
        	action=action+"&start="+add_begin;
        }
        if(add_end!=""){
        	action=action+"&end="+add_end;
        }
        if(status!=""){
        	action=action+"&status="+status;
        }
        if(d_begin!=""){
        	action=action+"&sendstart="+d_begin;
        }
        if(d_end!=""){
        	action=action+"&sendend="+d_end;
        }
       	$("#form1").attr("action",action);
    	$("#form1").submit();
    }
    
      
</script>
<!--主要内容开始 -->
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 订单管理 &gt; 待使用订单列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form id="form1" method="post"  action="">
                <span><label>订单编号：</label><input id="code" name="code" type="text" class="text-inp"></span>
                <span class="marright0">
                    <label>下单日期：</label>
                    <input type="text" id="add_begin" name="add_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'add_end\')}' })" />--
                    <input type="text" id="add_end" name="add_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'add_begin\')}' })" />
                </span>
               

                <div class="clear"></div>
                <div class="submit-but">
                    <input name="search" type="button" value="查询" class="but-comm">
                    <input name="reset" type="button" value="清空" class="but-comm">
                    <!-- <input id="outExcel" onclick="outputExcel()" type="button" value="导出Excel" class="but-comm"> -->
                </div>
             <input id="state" type="hidden" value="2">
                
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="l_gulitab">
            <table>
                <tr>
                    <th width="20%">订单详情</th>
                    <th width="12%">商品单价</th>
                    <th width="10%">商品数量</th>
                    <th width="15%">买家信息</th>
                    <th width="12%">订单状态</th>
                    <th width="15%">订单时间</th>
                    <th>订单操作</th>
                </tr>
            </table>
            <div class="l_ddgl">
                <table id="datalist">
                    <script id="ddlist" type="text/html">
                    {{each list as order i}}
                    <div class="l_ddgl">
                        <table>
                            <tr>
                                <td colspan="4" class="l_toptd">
                                    <span>主订单号：{{order.groupCode }}</span> <span>订单金额：{{order.price | toFixed}}</span>
                                    <span>实际支付金额：{{order.actualPay | toFixed}}</span>
									{{if order.status>0 && order.payType!=null}}
                                         <span>支付方式：
 										    {{if order.payType==6}}支付宝支付
 										    {{else if order.payType==7}}微信支付
 {{else if order.payType==3}}优惠券支付
											{{else if order.payType==0}}待付款
                                            {{/if}}
                                         </span>
                                       {{/if}}
                                </td>
                                <td style="text-align:center;padding-left:0px" class="l_toptd">
                                    {{if order.status==0}}待付款
                                    {{else if order.status==1}}出票中
                                    {{else if order.status==2}}待使用
                                    {{else if order.status==3}}审核中
                                    {{else if order.status==4}}已取消
									{{else if order.status==9}}已完结
                                    {{/if}}                         
                                </td>
                                <td class="l_toptd" >{{order.addOrderDatestr}}</td>
                                <td class="l_toptd">
									<a href='ddgl_OrderDetail?id={{order.id}}&ch=3'><span class='shenlan'>查看详情</span></a>
                                    {{if order.status==0}}
									{{else if order.status==3}}
                                    	<!--<a href='javascript:void(0);' onclick="order.zhuddtk({{order.groupCode}})"><span class='shenlan'>退款</span></a>-->
									{{/if}} 
                               </td>
                            </tr>
                            {{each order.children as detail j}}
                            <tr>
                                <td width='20%'>{{detail.productname}}</td>
                                <td width='12%'>{{detail.productprice | toFixed}}</td>
                                <td width='10%'>{{detail.productcount}}</td>
                                <td width='15%'>{{order.buyerName}}</td>
                                <td width='12%'>
                                    <div class="pos_rela">
                                    {{if detail.status==0}}<!--订单未完成-->
										{{else if detail.status==10}}<!--待使用-->
										{{else if detail.status==19}}已使用
										{{else if detail.status==20}}申请退款
										{{else if detail.status==21}}退款失败
										{{else if detail.status==22}}审核中
										{{else if detail.status==29 }}退款成功
										{{else if detail.status==99}}订单完成
                                        {{/if}}  
                                        <div id="divrecord_{{order.id}}" class="l_xsztcon" style="display:none;">
                                            <div class="l_xtb_xszt"></div>
                                            <div class="l_xszt">
                                                <ul></ul>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td width='15%'>{{detail.usetime | toDateTimes}}</td>
                                <td >
  									{{if detail.status==22}}
                                    	<a href='javascript:void(0);' data-status="{{detail.status}}" data-oid="{{detail.id}}"><span class='shenlan'>查看原因</span></a>
										<!--<a href='javascript:void(0);' onclick="order.ziddtk({{detail.id}})"><span class='shenlan'>退款</span></a>-->
                                    {{/if}}			
                                </td>
                            </tr>
                            {{/each}}
                        </table>
                    </div>
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