<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>卖家管理</title>
    <meta name="title" content="商品管理——到货通知" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    
     <link rel="shortcut icon" type="image/x-icon" href="${ctx }/resource/public/seller/images/favicon_shortcut.ico" />
    <link href="${ctx }/resource/ui-dialog.css" rel="stylesheet" />
     <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/seller/css/common.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/seller/css/login.css">
<script type="text/javascript" src="${ctx }/resource/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx }/resource/public/seller/js/common.js"></script>
 
   
   <script src="${ctx }/resource/public/commonjs/Guid.js"></script>
    <script src="${ctx }/resource/cookie.js"></script>
    <script type="text/javascript" src="${ctx }/resource/artTemplate.js"></script>
    <script src="${ctx }/resource/ajaxfileupload.js"></script>
    <script src="${ctx }/resource/dialog-min.js"></script>
    <script src="${ctx }/resource/dialogShow.js"></script> <!--弹出框-->
     <!--表单验证css&&js-->
    <link href="${ctx }/resource/public/commoncss/jQuery.Validate.css" rel="stylesheet" />
    <script src="${ctx }/resource/public/commonjs/jquery.validate.js"></script>
    <script src="${ctx }/resource/public/commonjs/messages_cn.js"></script>
    <script src="${ctx }/resource/public/commonjs/jquery.validate-methods.js"></script>
    
     <script src="${ctx }/resource/public/commonjs/baseRoot.js"></script>
     <script src="/resource/public/seller/js/LoginRegister/findpwd.js"></script>
     <script type="text/javascript">
    $(document).ready(function () {
        //初始化
        Init.bind();
    });
</script>
</head>
<body>
<div id="top">
	<div class="wrapper">
    	<div class="logo"></div>
        <div class="top-right">您好，欢迎光临中绿生活！<span class="lvse"><a href="/seller/login">登录</a></span></div>
    </div><!--wrapper  stop -->
</div>
<!--top  stop -->
<div class="wjmmcon-nr">
     <div class="zhaq fix">
            <div class="wddd-title">找回登录密码</div>
            <div class="xgmm-con">

<c:choose>
<c:when test="${setup==1}">
	<div class="l_xgmmtop fix">
                            <ul>
                                <li class="active">填写账号名<span>1</span></li>
                                <li>验证身份<span>2</span></li>
                                <li>设置新密码<span>3</span></li>
                                <li>完成<span>4</span></li>
                            </ul>
                        </div>
                        <div class="xgmm-maincon">
                            <form id="checkAccount">
                                <table>
                                    <tr>
                                        <td class="xgmm-tableft">账户名：</td>
                                        <td>
                                            <input name="text_account" id="text_account" type="text" class="inp-big" placeholder="请输入您的用户名/已验证邮箱或手机">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="xgmm-tableft">验证码：</td>
                                        <td>
                                            <input name="text_imgcode" id="text_imgcode" type="text" class="inp-big">
                                            <img src="/VerifyCodeServlet" class="refleshverification_img" width="126" height="34" alt="验证码" />
                                            看不清？ <span class="lvse"><a href="javascript:void(0)" class="refleshverification">换一张</a></span><span class="red marrig5">区分大小写</span>
                                        </td>
                                        
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td id="td_error"></td>
                                    </tr>
                                    <tr>
                                        <td class="xgmm-tableft">&nbsp;</td>
                                        <td>
                                            <a href="javascript:void(0);" id="accsubmit" class="l_tjfxthh">提交</a>
                                        </td>
                                    </tr>
                                </table>
                            </form>

                        </div>
</c:when>
<c:when test="${setup==2}">
 				<div class="l_xgmmtop fix">
                        <ul>
                            <li class="active">填写账号名<span>1</span></li>
                            <li class="active">验证身份<span>2</span></li>
                            <li>设置新密码<span>3</span></li>
                            <li>完成<span>4</span></li>
                        </ul>
                    </div>
                    <div class="xgmm-maincon">
                        <input type="hidden" value="${emails}" id="cType" />
                        <input type="hidden" value="${phone}" id="strAccount">
                        <form id="checkForm">
                            <table>
                                <tr>
                                    <td class="xgmm-tableft">请选择验证方式：</td>
                                    <td>
                                        <select id="select_check">
                                         <c:if test="${type==1}">
                                           <option value="1" selected>手机验证</option>
                                         </c:if> 
                                         <c:if test="${type==2}">
                                           <option value="2" selected>邮箱验证</option>
                                         </c:if>
                                        <c:if test="${type==0}">
                                             <option value="1" selected>手机验证</option>
                                             <option value="2">邮箱验证</option>
                                         </c:if>
                                        </select>
                                    </td>
                                </tr>
	                                     <c:if test="${type==1}">
	                                         <tr>
                                        <td class="xgmm-tableft">已验证手机：</td>
                                        <td>${phone}</td>
                                    </tr>

                                    <tr>
                                        <td class="xgmm-tableft">请填写手机效验码：</td>
                                        <td>
                                            <input name="text_mobilecode" id="text_mobilecode" type="text" class="inp-big" disabled>
                                            <input type="hidden" value="" id="hidden_mobilecodetoken" name="hidden_mobilecodetoken" />
                                            <a href="javascript:void(0)" class="mobilecode g_hqyzm fl" style="color: rgb(0, 0, 0);">
                                                <span class="zc_hqdzyzm"><b style="display: none;">60</b><font>获取验证码</font></span>
                                                <input type="hidden" value="" id="hidden_mobile" name="hidden_mobile" />
                                            </a>
                                        </td>
                                    </tr>
	                                      </c:if> 
	                                      
	                                      <c:if test="${type==2}">
	                                        <tr>
                                            <td class="xgmm-tableft">已验证邮箱：</td>
                                            <td>${emails}</td>
                                        </tr>
                                        <tr>
                                            <td class="xgmm-tableft">请填写邮箱效验码：</td>
                                            <td>
                                                <input name="text_emailcode" id="text_emailcode" type="text" class="inp-big">
                                                <a href="javascript:void(0)" class="emailcode g_hqyzm fl" style="color: rgb(0, 0, 0);">
                                                    <span class="zc_hqdzyzm"><b style="display: none;">60</b><font>获取验证码</font></span>
                                                </a>
                                            </td>
                                        </tr>
	                                      </c:if>
	                                     <c:if test="${type==0}">
	                                          <tr id="mtitle">
                                            <td class="xgmm-tableft">已验证手机：</td>
                                            <td>${phone}</td>
                                        </tr>

                                        <tr id="mcheck">
                                            <td class="xgmm-tableft">请填写手机效验码：</td>
                                            <td>
                                                <input name="text_mobilecode" id="text_mobilecode" type="text" class="inp-big" disabled>
                                                <input type="hidden" value="" id="hidden_mobilecodetoken" name="hidden_mobilecodetoken" />
                                                <a href="javascript:void(0)" class="mobilecode g_hqyzm fl" style="color: rgb(0, 0, 0);">
                                                    <span class="zc_hqdzyzm"><b style="display: none;">60</b><font>获取验证码</font></span>
                                                    <input type="hidden" value="" id="hidden_mobile" name="hidden_mobile" />
                                                </a>
                                            </td>
                                        </tr>
                                        <tr id="etitle" style="display:none">
                                            <td class="xgmm-tableft">已验证邮箱：</td>
                                            <td>${emails}</td>
                                        </tr>
                                        <tr id="echeck" style="display:none">
                                            <td class="xgmm-tableft">请填写邮箱效验码：</td>
                                            <td>
                                                <input name="text_emailcode" id="text_emailcode" type="text" class="inp-big">
                                                <input type="hidden" value="" id="hidden_emailcodetoken" name="hidden_emailcodetoken" />
                                                <a href="javascript:void(0)" class="emailcode g_hqyzm fl" style="color: rgb(0, 0, 0);">
                                                    <span class="zc_hqdzyzm"><b style="display: none;">60</b><font>获取验证码</font></span>
                                                </a>
                                            </td>
                                        </tr>
	                                      </c:if>
                               
                                <tr>
                                    <td></td>
                                    <td id="td_error"></td>
                                </tr>
                                <tr>
                                    <td class="xgmm-tableft">&nbsp;</td>
                                    <td style="height:80px;">
                                        <a href="javascript:void(0);" id="checksubmit" class="l_tjfxthh">提交</a>
                                    </td>
                                </tr>
                            </table>
                        </form>

                    </div>
</c:when>
<c:when test="${setup==3}">
	 <div class="l_xgmmtop fix">
                        <ul>
                            <li class="active">填写账号名<span>1</span></li>
                            <li class="active">验证身份<span>2</span></li>
                            <li class="active">设置新密码<span>3</span></li>
                            <li>完成<span>4</span></li>
                        </ul>
                    </div>
                    <div class="xgmm-maincon">
                        <form id="setnewpwdForm">
                            <table>
                                <tr>
                                    <td class="xgmm-tableft">新密码：</td>
                                    <td><input id="password_newpwd" name="password_newpwd" type="password" class="inp-big"></td>
                                </tr>
                                <tr>
                                    <td class="xgmm-tableft">确认新密码：</td>
                                    <td><input id="password_pwdagain" name="password_pwdagain" type="password" class="inp-big"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td id="td_error"></td>
                                </tr>
                                <tr>
                                    <td class="xgmm-tableft">&nbsp;</td>
                                    <td>
                                        <a href="javascript:void(0);" id="a_setnewpwdsubmit" class="l_tjfxthh">提交</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
</c:when>
<c:when test="${setup==4}">
	      <div class="l_xgmmtop fix">
                        <ul>
                            <li class="active">填写账号名<span>1</span></li>
                            <li class="active">验证身份<span>2</span></li>
                            <li class="active">设置新密码<span>3</span></li>
                            <li class="active">完成<span>4</span></li>
                        </ul>
                    </div>
                    <div class="xgmm-maincon-complete">
                        <img src="/resource/pc/member/images/duigou.png" width="43" height="30">
                        密码找回成功。请记住新密码！
                        <span class="fon14">
                            <b id="backsecond" style="display: none;">10</b>
                            <font>返回<a class="lvse" href="/seller/login">登录</a>页</font>
                        </span>

                    </div>
</c:when>
</c:choose>
</body>
</html>