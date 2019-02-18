<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/subFreightManager.js"></script> 


<div class="mainright">

    <div class="clear"></div>
   <div class="tjcpxx">
       
        <div class="tjcpxx-con">
            <!-- -->
            
                <div class="the-form">
                    <div class="submit-but">
                     <input type="hidden" id="ftid" value="${ftid }" />
                      <%--   <input type="hidden" id="ftid" value="${ftid }" />
                        <input type="hidden" id="shopName" value="${shop.name }" />
                        <input type="hidden" id="userType" value="${accouts.usertype }" />
                        <input name="" id="addTemplate" type="button" value="新建运费模板" class="but-comm"> --%>
                        <!--@*  <input name="" id="addTemplateInfo" type="button" value="新建运费模板详细" class="but-comm">*@-->
                        <a href="javascript:;" target="_self"><input class="chaxun" name="back" type="button" value="返回"></a>
                    </div><!--submit-but  stop -->
                </div><!--表单部分结束 -->               

                <div class="clear"></div>
                <div class="freight-tab">
                    <table>
                        
                        
                        <tr id="freightList">
                            <th width="315px">序号</th>
                            <th>地区</th>
                            <th>首件数量</th>
                             <th>首件价格</th>
                            <th>续建数量</th>
                             <th>续建价格</th>

                        </tr>
                        <script type="text/html" id="datalist">
                            {{each list as ft i}}
							
                            <tr>
                                <td width="315px">{{i+1}}</td>
                                <td> {{ft.areas}} </td>
                                <td>{{ft.firstcount}}</td>
  								<td>{{ft.firstprice}}</td>
                                <td>{{ft.elsecount}}</td>
								<td>{{ft.elseprice}}</td>
                            </tr>
                            {{/each}}
                        </script>
                    </table>
                   
</div><!--mainright stop -->
        </div><!--freight-tab  stop -->
            </div>
            <!-- -->
        </div><!--tjcpxx-con stop -->

