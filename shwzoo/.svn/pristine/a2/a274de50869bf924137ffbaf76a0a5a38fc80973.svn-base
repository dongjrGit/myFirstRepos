<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/lottery_param_edit.js"></script> 
<div class="mainright">
    <!--l_wzmb  开始 -->
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">奖项管理</a><span class="sj-img"></span></li>                
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="payForm"  method="post">
               <input type="hidden" id="input_type"  value="${record.type}">
               <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><span class="red">*</span><label>奖项类型：</label></div>
                    <div class="tjcpxx-con-form1">
                        <select id="select_type" onChange="changeSelect();" name="type" >
                         <option value="0">请选择</option>
                         <c:forEach items="${dictionarys }" var="dictionary">
                         <option value="${dictionary.mark }">${dictionary.name }</option>
                        </c:forEach>
                        </select>
                    </div>

               </div>
               
                <div id="name1" class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><span class="red">*</span><label>奖项名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="name" name="name" type="text" value="${record.name }" >
                        <input class="tjcpxx-fprm-inp" type="hidden" id="hidden_name" name="hidden_name" type="text" value="${record.groupid }" >
                    </div>
               </div>
               
               
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>奖项描述：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="description" name="description" type="text" value="${record.description }" >
                    </div>

               </div>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>奖项的值：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="value" name="value" type="text" value="${record.value }" >
                    </div>

               </div>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                        <input class="preserve-inp marrig35 mar35" name="savebtn" id="savebtn" type="button" value="保存">
                        <input class="preserve-inp_hs" name="backbtn" id="backbtn" type="button" value="返回">
                    </div>
                </div>

            </form>
        </div>
    </div>
    <!--l_wzmb  结束 -->
</div><!--mainright stop -->
