<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="/resource/public/seller/js/Zhgl/ChangeLoginPwd.js"></script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：账号管理 &gt; 个人中心 &gt; 密码修改
        </div><!--所在位置信息  结束 -->
        <div class="clear"></div>
        <div class="zhgl-con-con">
            <form id="changeLoginPwdForm" action="#" method="post" onsubmit="return check();">
                 <input type="hidden" id="hidden_userid" value="${userid }">
                <table>
                    <tr>
                        <td class="xjdpzzh-left"><span class="red">*</span>原密码：</td>
                        <td><input id="pwd_oldpwd" name="pwd_oldpwd" type="password" class="text-inp-big"></td>
                    </tr>
                    <tr>
                        <td class="xjdpzzh-left"><span class="red">*</span>新密码：</td>
                        <td><input id="pwd_newpwd" name="pwd_newpwd" type="password" class="text-inp-big"></td>
                    </tr>
                    <tr>
                        <td class="xjdpzzh-left"><span class="red">*</span>确认新密码：</td>
                        <td><input id="pwd_surepwd" name="pwd_surepwd" type="password" class="text-inp-big"></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td style="padding-top:10px;">
                            <input name="submit_ok" type="submit" value="提交申请" id="submit_ok" class="big-but">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div><!--主要内容 右边结束 -->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //初始化
        Init.bind();
        //表单验证
        Vaildate.bind();
    })
</script>
