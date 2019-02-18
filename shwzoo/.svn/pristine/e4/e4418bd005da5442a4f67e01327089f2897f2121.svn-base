<!-- @{
    ViewBag.Title = "商品楼层管理";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script>
<script type="text/javascript">
$(function(){

	 autoxl.bind("spuName", getSpuStartwithName,true);
	
	$("#submit_ok").click(function(){
		
		 $.ajax({
		        url: "/platform/news/saveNewsSpu",
		        type: "post",
		        data: { 
		        		"spuid":$("#spuName").attr("data"),
		 				"spuname":$("#spuName").val(),
		 				"newsid":$("#newsid").val(),
		 				"orderby":$("#orderby").val()
		        },
		        dataType: "json",
		        success: function (data) {
		            if (data.code == 0) {
		            	 Dalert(data.desc);
		            	 location.href="/platform/news/xgsc?newsid="+$("#newsid").val();
		            } else {
		                Dalert(data.desc);
		            }
		        }
		    });
		
	});
});

function getSpuStartwithName(callback, event) {
    var name = $("#spuName").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/news/getSpuName",
        type: "Get",
        data: { 
        		"name":name
        },
        dataType: "json",
        success: function (data) {

            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
             /*   autoxl.bind("spuName", getSpuStartwithName);
        		
        		$("#spuName").val(sname);*/
                var html = template('select_spulist', listdata);
                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.data);
            }
        }
    });
}

</script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">商品专题管理</a><span class="sj-img"></span></li>

            </ul>
        </div>
        <div class="tjcpxx">
            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form">
                    	<input type="hidden" id="newsid" value="${newsid}">
                       <%--  <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>名称：</label></div>
                            <div class="tjcpxx-con-form1">
                                 <select name="subname" id="subname" class="the-form-select-one">
                                    <option value="0" id="0">请选择</option>
                                    <!--  <script type="text/html" id="select_brand">
                                        {{each list as br i}}
                                        <option value="{{br.id}}">{{br.name}}</option>
                                        {{/each}}
                                    </script> -->
                                    <c:forEach items="${list }" var="br">
                                     <option value="${br.id}" <c:if test='${data.relatedid==br.id}'> selected='true'</c:if>>${br.name}</option>
                                     </c:forEach>
                                </select>
                            </div>
                        </div> --%>
                        <span class="marrig10"></span>
                        <div class="tjcpxx-con-mk1" name="fltype" id="spname" style="">
                            <div class="tjcpxx-con-form-title1"><label>名称：</label></div>
                         <ad>
                                <input class="inp-seller" data="${relateid}" type="text" name="spuName" id="spuName" value="${name }" />
                          </ad> 
                       
                            <div name="spu_name_select" >
                                <ul>
                                    <script id="select_spulist" type="text/html">
                                        {{each list as sp i}}
                                        <li data="{{sp.id}}">{{sp.name}}</li>
                                        {{/each}}
                                    </script>
                                </ul>
                            </div>
                        </div>
                
                     <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label>排序：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="orderby" id="orderby" type="text" value="${data.sort}">
                            </div>
                        </div>  
                        
                        
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"></div>
                            <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                                <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="button" value="确定">
                       			<input class="preserve-inp_hs" name="backBtn" id="backBtn" type="button" value="取消">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->

                    </form>
                </div>
            </div><!--tjcpxx-con stop -->
        </div><!--tjcpxx stop -->
    </div>
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //返回按钮点击
        var topicid = $("#topicid").val();
        var spmark=$("#spmark").val();
        var type=$("#type").val();
       var fatherid = $("#fatherid").val();
        $("#backBtn").bind("click", function () {
            window.location.href = "/platform/news/xgsc?newsid="+$("#newsid").val(); 
        });

        //初始化
        Init.bind();

        //表单验证
        //Vaildate.bind();
    })
</script>
