<!--  @{
    ViewBag.Title = "会员管理&gt;会员管理&gt;会员列表&gt;发送手机短信";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
}-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/resource/public/platform/js/member/sendmobilemessage.js"></script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0)">发送手机短信</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->

        <div class="tjcpxx-con-con">
            <form id="sendmobilemessageForm" action="#" method="post" >

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>手机号：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_mobilenum" id="text_mobilenum" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <!-- <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"></div>
                        <div class="tjcpxx-con-form1" style="position:relative;">
                            <span class="beizhu-mc">邮件主题不能为空 长度限制在0-20个字符之间！</span>
                        </div>
                    </div> --><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>信息：</label></div>
                    <div class="tjcpxx-con-form1">
                        <textarea class="tjcpxx-con-form1-text" name="text_mobilemessage" id="text_mobilemessage" cols="" rows=""></textarea>
                    </div>
                </div><!--tjcpxx-con-mk stop -->

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                    	<input type="hidden" id="action" value="" />
                    	<input type="hidden" id="text_username" value="" />
                        <input class="preserve-inp marrig35 mar35" onclick="submits()" type="button" value="发送">
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
        //返回按钮点击
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/member/showMemberList";
        });
        //初始化
        Init.bind();
        //表单验证
        Vaildate.bind();
        
    })
    
    function submits(){
    	Submit.bind();
    }
</script>