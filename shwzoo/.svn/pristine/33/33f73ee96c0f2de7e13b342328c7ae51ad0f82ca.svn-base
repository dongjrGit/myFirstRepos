<!-- @{
    ViewBag.Title = "店铺管理&gt;店铺管理&gt;店员列表&gt;添加店铺员工";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="/resource/public/platform/js/shop/shopemployeeadd.js"></script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0)">添加店铺员工</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="addshopEmployeeForm" action="#" method="post" onsubmit="return check();">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>店铺：</label></div>
                    <div class="tjcpxx-con-form1">
                        <!--<input class="tjcpxx-fprm-inp" name="text_shopname" id="text_shopname" type="text" onchange="sadsads();">-->
                        <ad><input id="select_shop" name="select_shop" type="text" class=" tjcpxx-fprm-inp" /></ad>
                        <div>
                            <ul>
                                <script id="select_shoplist" type="text/html">
                                    {{each list as shop i}}
                                    <li data="{{shop.id}}">{{shop.name}}</li>
                                    {{/each}}
                                </script>
                            </ul>
                        </div>
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk">
                    <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>员工角色：</label></div>
                    <div class="tjcpxx-con-form" style="position:relative;">
                        <select name="select_employeerole" id="select_employeerole" class="select-spfl">
                            <option value="-1" selected>请选择</option>
                            <script id="shoproleselect" type="text/html">
                                {{each list as value index}}
                                <option value="{{value.id}}">{{value.name}}</option>
                                {{/each}}
                            </script>
                        </select>
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>用户名：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_loginname" id="text_loginname" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>密码：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_loginpwd" id="text_loginpwd" type="password">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>确认密码：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_againpwd" id="text_againpwd" type="password">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>员工编号：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_employeenum" id="text_employeenum" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>员工姓名：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_employeename" id="text_employeename" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>手机号：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_employeemobile" id="text_employeemobile" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                        <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="submit" value="保存">
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
            window.location.href = "/platform/shop/showShopEmployeeList";
        });
        //初始化
        Init.bind();
        //表单验证
        Vaildate.bind();
    })

</script>