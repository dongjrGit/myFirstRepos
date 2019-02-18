<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>


<script type="text/javascript" src="${ctx }/resource/public/platform/js/MessageManagement/sendemailadd.js"></script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0)">发送邮件</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="sendemailForm" action="#" method="post" onsubmit="return check();">

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>收件人：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_emailrecipient" id="text_emailrecipient" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>邮件主题：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_emailtheme" id="text_emailtheme" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>正文：</label></div>
                    <div class="tjcpxx-con-form1">
                        <textarea class="tjcpxx-con-form1-text" name="text_emailbody" id="text_emailbody" cols="" rows=""></textarea>
                    </div>
                </div><!--tjcpxx-con-mk stop -->

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                        <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="submit" value="发送">
                        <input class="preserve-inp_hs" name="btn_goback" id="btn_goback" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->

            </form>
        </div><!--tjcpxx-con stop -->
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //返回按钮点击
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/message/showSendEmail";
        });
        //初始化
        Init.bind();
        //表单验证
        Vaildate.bind();
    })
</script>
