<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>


<script src="${ctx }/resource/public/platform/js/MessageManagement/sendemail.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>用户名：<input class="inp-seller" id="select_name" type="text"></span>
        <span class="marrig10"></span>
        <span>邮箱：<input class="inp-seller" id="select_email" type="text"></span>
        <span class="marrig10"></span>
        <span>
            选择时间：
            <input type="text" id="select_begin" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })" class="text_time" /> 至
            <input type="text" id="select_end" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })" class="text_time" />
        </span>
        <span class="marrig10"></span>
        <span><input class="inquire" name="" type="button" value="查询" onclick="MemberList.bind(1)"></span>
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="member-xz" style="margin-top:10px;">
        <span><input id="selectall" name="chk_All" type="checkbox" value="">全选</span>
        <span><input id="btn_sendall" class="member-inp" name="" type="button" value="批量发送邮件"></span>

    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="memberlist_title">
                <th>选择</th>
                <th>用户名</th>
                <th>邮箱</th>
                <th>性别</th>
                <th>省份</th>
                <th>加入日期</th>
                <th>操作</th>
            </tr>
            <script id="memberlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td><input name="chk_list" class="member_check_list" type="checkbox" value="{{pro.email}}"></td>
                    <td>{{pro.username}}</td>
                    <td id="td_memberemail">{{pro.email}}</td>
                    {{if pro.sex==1 }}
                    <td>男</td>
                    {{else if pro.sex==2 }}
                    <td>女</td>
                    {{else if pro.sex==0}}
                    <td>保密</td>
					{{else}}
                    <td>---</td>
                    {{/if}}
                    <td>{{pro.provincename}}</td>
                    <td>{{pro.createtimestr}}</td>
                    <td class="lvs">
                        <a class="a_memberemail" href="javascript:void(0);"><span class="shenlan">点击发送</span></a>
                        <input type="hidden" id="hidden_memberid" value="{{pro.id}}" />
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--table-con  stop -->
    <div class="clear"></div>
    <div class="member-xz" style="margin-top:10px;">
    </div>
    <div class="page" id="pager">

    </div><!--page stop -->


</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //列表以及分页数据绑定
        MemberList.bind(1);
        //全选或者取消全选
        SelectAllorNoall.bind();
        //全选按钮
        $("#btn_sendall").bind("click", SendAll);
    })

    //批量发邮件
    function SendAll() {
        var stremail = "";
        $('input[name="chk_list"]:checked').each(function () {
            var email = $(this).val();
            stremail += email + ",";
        });
        if (stremail.length > 1) {
            stremail = stremail.substring(0, stremail.length - 1).toString();
            window.location.href = "/platform/message/showSendEmailAdd?memberemailstr=" + stremail;
        }
    }
</script>
