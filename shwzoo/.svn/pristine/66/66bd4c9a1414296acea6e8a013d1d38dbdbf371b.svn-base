<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="/resource/public/seller/js/cdgl/cdOrder.js"></script>
<input type="hidden" id='isread' value="false"/>
<div class="position">
            您所在的位置：订单管理 &gt; 催单列表 &gt; 催单列表
        </div>
 <form id="form" style="margin-top: 30px">
    <div class="clear"></div>
    <div class="mar35"></div>
    <div id="con">
                <ul id="tags">
                    <li id="wsl"   class="selectTag"><a href="javascript:void(0);">未受理</a><span class="sj-img"></span></li>
                    <li id="ysl" ><a href="javascript:void(0);">已处理</a><span class="sj-img"></span></li>
                </ul>
    </div>  
	<div class="clear"></div>
        <div >
        	<div class="thgl">
        	 <table >
        	    <tr class="blank-tr"></tr>
                <tr id="dd_title">
       			   <td align="center">订单编号</td>
                   <td align="center">下单时间</td>
                   <td align="center">催单时间</td>
                   <td align="center">商户编号 </td>
                   <td align="center">商户名称</td>
                  
                   <td  id="td"  align="center">订单操作</td>
                </tr>
                 
                <tbody id="data_list">
			
				</tbody>
                	<script id="noticelist" type="text/html">
                    {{each list as st i}}
                    <tr>
                        <td align="center">{{st.ordernum}}</td>
						<td align="center">{{st.ordertime | toDateTime}}</td>
						<td align="center">{{st.createtime | toDateTime}}</td>
                        <td align="center">{{st.buyername}}</td>
						<td align="center">{{st.name}}</td>
						{{if st.isread==0}}
						<td id="td{{i}}" name=td2 align="center">  
						<a href="ddgl_OrderDetail?id={{st.orderid}}&ch=8" ><span class="shenlan">查看订单</span></a>&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="cd.sl({{st.id}})"><span class="shenlan">处理</span></a>  </td>
		 				{{else}}
							{{/if}}
                    </tr>
                    {{/each}}
                </script>
                
            </table>
            	 
                          
       
    </div>
     	<div class="clear"></div>
        <div id="pager" class="page">

        </div>
            </div> 
     </form>
<script type="text/javascript">
	$(function (){
		cd.bind();
		cd.getlist(1);
		
	})
	
	$("#wsl").bind("click", function() {
		$("#ysl").removeClass("selectTag");
		$("#wsl").addClass("selectTag");
		$("#isread").val("false");
		$("#td").show();
		cd.getlist(1);
	});
	$("#ysl").bind("click", function() {
		$("#wsl").removeClass("selectTag");
		$("#ysl").addClass("selectTag");
		$("#isread").val("true");
		$("#td").hide();
		cd.getlist(1);
	});
	
</script>
 
