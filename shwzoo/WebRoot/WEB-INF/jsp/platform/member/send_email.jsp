<!--  @{
    ViewBag.Title = "会员管理&gt;会员管理&gt;会员列表&gt;发送邮件";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
}-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/member/sendemail.js"></script>
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
                    	<input type="hidden" id="action" value="" />
                        <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="submit" value="发送">
                        <input class="preserve-inp_hs" name="btn_goback" id="btn_goback" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->

            </form>
        </div>
        <!--tjcpxx-con stop -->
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //点击返回按钮
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/member/showMemberList";
        });
        //初始化
        Init.bind();
        //表单验证
        Vaildate.bind();
    })
</script>
