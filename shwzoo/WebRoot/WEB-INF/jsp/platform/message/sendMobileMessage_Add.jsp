<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="/resource/public/platform/js/MessageManagement/sendmobilemessageadd.js"></script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0)">发送手机短信</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="sendmobilemessageForm" action="#" method="post" onsubmit="return check();">

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>手机号：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_mobilenum" id="text_mobilenum" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                 <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>标题：</label></div>
                        <div class="tjcpxx-con-form1" style="position:relative;">
                            <input class="tjcpxx-fprm-inp" name="text_title" id="text_title" type="text">
                        </div>
                    </div> <!--tjcpxx-con-mk stop -->
                    
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>信息：</label></div>
                    <div class="tjcpxx-con-form1">
                        <textarea class="tjcpxx-con-form1-text" name="text_mobilemessage" id="text_mobilemessage" cols="" rows=""></textarea>
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
        //点击返回按钮
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/message/showSendMobileMessage";
        });
        //初始化
        Init.bind();
        //表单验证
        Vaildate.bind();
    })
</script>
