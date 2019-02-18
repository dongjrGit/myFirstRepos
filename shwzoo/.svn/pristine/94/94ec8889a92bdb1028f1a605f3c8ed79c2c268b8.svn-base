<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="${ctx }/resource/public/platform/js/MessageManagement/sendmobilemessagecomplete.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>收信人：<input class="inp-seller" id="select_name" type="text"></span>
        <span class="marrig10"></span>
        <!-- <span>发送人：<input class="inp-seller" id="select_sendname" type="text"></span>
        <span class="marrig10"></span> -->
        <span>手机号：<input class="inp-seller" id="select_mobile" type="text"></span>
        <span class="marrig10"></span>
        <span>
            选择时间：
            <input type="text" id="select_begin" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })" class="text_time" /> 至
            <input type="text" id="select_end" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })" class="text_time" />
        </span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="inquire" name="" type="button" value="查询" onclick="MessageList.bind(1)"></span>
    </div><!--account-form stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="member-xz">
    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="messagelist_title">
                <th>收信人</th>
                <th>手机号</th>
                <th>内容</th>
                <th>发送时间</th>
                <th>发送人</th>
            </tr>
            <script id="messagelist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td>{{pro.username}}</td>
                    <td>{{pro.mobile}}</td>
                    <td>{{pro.content}}</td>
                    <td>{{pro.sendtimestr}}</td>
                    <td>{{pro.sendname}}</td>
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
        MessageList.bind(1);
    })
</script>
