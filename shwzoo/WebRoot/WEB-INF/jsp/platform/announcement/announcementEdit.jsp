<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/announcement/announcement_add.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/commonjs/jquery.form.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=v66Wo0IkRp2uWVi5RNsaj656"></script>

<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0)">编辑通告</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="addannouncementForm" action="#" method="post">
			
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>标题：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_title" id="text_title" type="text" value="${announcement.title }">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
             <!--     @*<div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"></div>
                        <div class="tjcpxx-con-form1" style="position:relative;">
                            <span class="beizhu-mc">用户名不能为空 长度限制在0-20个字符之间！</span>
                        </div>
                    </div>*@--><!--tjcpxx-con-mk stop -->
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>内容：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_content" id="text_content" type="text" value="${announcement.content }">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
              
				<div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>排序：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_sort" id="text_sort" type="text" value="${announcement.sort }">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                    <input type="hidden" id="hidden_announid" value="${announid }" />
                        <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="submit" value="保存">
                        <input class="preserve-inp_hs" name="btn_goback" id="btn_goback" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
            </form>
        </div>
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //表单验证
        Vaildate.bind();
        //返回按钮点击
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/announcement/showannouncement";
        });
    })
</script>