<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="${ctx }/resource/public/platform/js/MessageManagement/sendMesInfo.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="mesHead">已发站内信</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->

        <div class="tjcpxx-con-con">
            <form id="sendForm" action="#" method="post">
            	<div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>类型：</label></div>
                    <div class="tjcpxx-con-form1">
                        <span>
		            		<select id="types" class="the-form-select-one">
					            <c:forEach items="${messageslist }" var="messages">
					                <option value="${messages.code }">${messages.name }</option>
					             </c:forEach>       
					            </select>
					        </span>
                    </div>
                </div><!--tjcpxx-con-mk stop -->

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5"></span>标题：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_title" id="text_title" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <!-- <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"></div>
                        <div class="tjcpxx-con-form1" style="position:relative;">
                            <span class="beizhu-mc">邮件主题不能为空 长度限制在0-20个字符之间！</span>
                        </div>
                    </div> --><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5"></span>内容：</label></div>
                    <div class="tjcpxx-con-form1">
                        <textarea class="tjcpxx-con-form1-text" name="text_content" id="text_content" cols="" rows=""></textarea>
                    </div>
                </div><!--tjcpxx-con-mk stop -->

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                        <input class="preserve-inp marrig35 mar35" id="saveBtn" type="button" value="发送" style="display:none">
                        <input class="preserve-inp_hs" id="backBtn" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->

            </form>
        </div>
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->

