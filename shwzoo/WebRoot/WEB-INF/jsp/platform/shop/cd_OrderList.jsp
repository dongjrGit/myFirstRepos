<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="/resource/public/platform/js/order/cdOrder.js"></script>
 <form id="form" style="margin-top: 30px">
 <input type="hidden" id='isread' value="false"/>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div id="con">
     <span class="marrig10"></span>
    		<span>催单状态：<span class="red"></span> </span>
    		<select id="cdstate">
    		<option value="1">未受理</option>
    		<option value="2">已受理</option>
    		</select>
         <input class="inquire" name="" type="button" id="searchBtn" value="搜索">
             <!--    <ul id="tags">
                    <li id="wsl"   class="selectTag"><a href="javascript:void(0);">未受理</a><span class="sj-img"></span></li>
                    <li id="ysl" ><a href="javascript:void(0);">已处理</a><span class="sj-img"></span></li>
                </ul> -->
    </div>  
        	  <div class="table-con">
        		<table class="data_list" id="list_title">
           		<tbody>
           		<tr>
                	<td align="center">订单编号</td>
                   <td align="center">下单时间</td>
                   <td align="center">催单时间</td>
                   <td align="center">商户编号 </td>
                   <td align="center">商户名称</td>
                   <td  id="td"  align="center">订单操作</td>
            	</tr>
            	</tbody>
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
						<a href="/platform/ddgl/ddgl_OrderDetail?id={{st.orderid}}&ch=1" ><span class="shenlan">查看订单</span></a>&nbsp;&nbsp;
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
     </form>
<script type="text/javascript">
	$(function (){
		cd.bind();
		cd.getlist(1);
		
	});
	$("#searchBtn").bind("click", function() {
		var cds=$('#cdstate option:selected').val();
		if(cds==1){
			$("#td").show();
			$("#isread").val("false");
			cd.getlist(1);
		}else{
			$("#td").hide();
			$("#isread").val("true");
			cd.getlist(1);
		}
		
	});
	
	
	 
	
</script>
 
