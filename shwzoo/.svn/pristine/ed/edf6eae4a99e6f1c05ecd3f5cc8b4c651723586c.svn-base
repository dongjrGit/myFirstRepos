<!-- @{
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
    ViewBag.Title = "添加信息模板";
} -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>


<script src="${ctx }/resource/public/platform/js/ControlPanel/sendtemplateedit.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="contitle">添加信息模板</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="templateForm">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><span class="red">*</span><label>模板类型：</label></div>
                    <input id="st1" name="sttype" type="radio" value="0">邮件
                    <input id="st2" name="sttype" type="radio" value="1">短信
                    <input id="st3" name="sttype" type="radio" value="2" checked>系统短信
                    <input id="st4" name="sttype" type="radio" value="3">系统推送
                </div>
                 <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>模板内容类型：</label></div>
                    <input name="ctype" type="radio" value="0" checked>订单
                    <input name="ctype" type="radio" value="1">促销
                </div>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>模板标识：</label></div>
                    
                    <select id="stag" name="stag" class="the-form-select-one">
                        <option value="0">下单成功</option>
                        <option value="1">支付成功</option>
                        <option value="2">支付失败</option>
                        <option value="3">卖家发货</option>
                        <option value="4">买家收货</option>
                        <option value="5">退换货申请</option>
                        <option value="6">卖家同意</option>
                    </select>
                    
                </div>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>排序：</label></div>

                    <input class="tjcpxx-fprm-inp" name="orderby" id="orderby" type="text">

                </div>
                <style>
                    .ke-container.ke-container-default {float:left; margin-right:30px;
                    }
                </style>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><span class="red">*</span><label>模板内容：</label></div>
                    <textarea name="content" id="content" style="width:700px;height:400px;" required></textarea><label for="content" id="areaerr" class="error"></label>
                    <div class="lanse" style="float:right;">                  
                            <p><a><font>[username]</font>:用户名</a></p>
                            <p><a><font>[ordernum]</font>:订单单号</a></p>
                            <p><a><font>[ordertime]</font>:订单时间</a></p>
                            <p><a><font>[logisticsname]</font>:物流公司</a></p>
                            <p><a><font>[logisticscode]</font>:物流单号</a></p>
                    </div>
                </div>
                <span class="mar35 marrig35"></span>
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise">
                        <input class="preserve-inp marrig35 mar35" name="" id="savebtn" type="button" value="保存">
                        <span class="mar35 marrig35"></span>
                        <input class="preserve-inp_hs" name="" id="backbtn" type="button" value="返回">
                    </div>
                </div>

            </form>
        </div>
    </div><!--tjcpxx stop -->



</div><!--mainright stop -->
