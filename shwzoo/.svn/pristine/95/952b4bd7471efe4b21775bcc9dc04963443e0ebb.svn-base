<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="${ctx }/resource/public/seller/js/Zhgl/EmployeeManagenmentAdd.js"></script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：账号管理 &gt; 账号管理 &gt; 角色维护 &gt; 新建子账号
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">新建子账号</div>
            </div><!--zhgl-con-top  stop -->
            <div class="zhgl-con-con">
                <input type="hidden" id="hidden_shopid" value="${shop.id }" />
                <form id="addshopEmployeeForm" action="#" method="post">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>用户名：</td>
                            <td><input id="text_loginname" name="text_loginname" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>密码：</td>
                            <td><input id="text_loginpwd" name="text_loginpwd" type="password" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>确认密码：</td>
                            <td><input id="text_loginpwd2" name="text_loginpwd2" type="password" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>员工编号：</td>
                            <td><input id="text_employeenum" name="text_employeenum" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>员工姓名：</td>
                            <td><input id="text_employeename" name="text_employeename" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>手机：</td>
                            <td><input id="text_employeemobile" name="text_employeemobile" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left" style="vertical-align:top;"><span class="red">*</span>角色：</td>
                            <td>
                                <select id="select_employeerole" name="select_employeerole" class="sel-form">
                                    <option value="-1">请选择</option>
                                     <c:forEach items="${roles }" var="role">
                                        <option value="${role.id }">${role.name }</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">邮件地址：</td>
                            <td><input id="text_employeeemail" name="text_employeeemail" type="text" class="text-inp-big"></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">电话：</td>
                            <td><input id="text_tel" name="text_tel" type="text" class="text-inp-big"></td>
                        </tr>
                        <!-- <tr>
                                <td class="xjdpzzh-left">用户是否使用：</td>
                                <td>
                                    <input name="radio_isuserd" type="radio" value="" checked>是
                                    <input name="radio_isuserd" type="radio" value="">否
                                </td>
                            </tr> -->
                        <tr>
                            <td class="xjdpzzh-left" style="vertical-align:top;">备注：</td>
                            <td>
                                <textarea id="text_mark" name="text_mark" cols="" rows="" class="form-area"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td style="padding-top:30px;">
                                <input name="submit_ok" type="button" value="保存" id="submit_ok" class="big-but">
                                <input name="btn_empty" id="btn_empty" type="button" value="返回" onclick="formCancel()" class="big-but-huise">
                            </td>
                        </tr>
                    </table>
                </form>
            </div><!--zhgl-con-con  stop -->
        </div><!--zhgl-con  stop -->
    </div><!--主要内容 右边结束 -->
</div><!--主要内容 右边结束 -->
<script type="text/javascript">
    $(document).ready(function () {
        //初始化
        Init.bind();
    })
    function formCancel() {
    	location.href = "/seller/zhglshop/showEmployeeManagement";
    }
</script>
