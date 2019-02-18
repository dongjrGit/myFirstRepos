<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="${ctx }/resource/public/platform/js/MessageManagement/sendedmessage.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>
            选择时间：<input type="text" id="stime" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd' })" readonly class="Wdate" />
            至 <input type="text" id="etime" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'stime\')}' })" readonly class="Wdate" />
        </span>
        <span class="marrig10"></span>
        <span>收件人：<input class="members-form-inp" id="rName" type="text"></span>
        <span class="marrig10"></span>
        <input class="chaxun" name="select_button" id="searchBtn" type="button" value="查询">
        <!-- <span class="dc-bg" style="margin-right:0px;"><input class="inquire" name="" type="button" value="导出Excel表"></span> -->

    </div><!--account-form stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="member-xz">
        <span><input id="SelectAll" name="chk_All" type="checkbox" value="">全选</span>
        <span><input class="member-inp" name="" type="button" id="delAll" value="批量删除信息"></span>

    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="memberlist_title">
                <th>选择</th>
                <th>收件人</th>
                <th>标题</th>
                <th>发件日期</th>
                <th>操作</th>
            </tr>
            <script id="memberlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td><input name="chk_list" type="checkbox" value="{{pro.id}}"></td>
                    <td>{{pro.username}}</td>
                    <td>{{pro.title}}</td>
                    <td>{{pro.sendtimestr}}</td>
                    <td class="lvs">
                        <a class="sendmes"><span class="shenlan">查看</span></a>
                        <a class="deleteMes"><span class="shenlan">删除</span></a>
                        <input type="hidden" id="hidden_memberid" value="{{pro.id}}" />
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--table-con  stop -->
    <div class="clear"></div>
    <div class="member-xz" style="margin-top:10px;">
        <!-- <span><input class="SelectAll"  name="chk_list" type="checkbox" value="">全选</span>
            <span><input class="member-inp" name="" type="button" value="批量发送短信"></span> -->
    </div>
    <div class="page" id="pager">


    </div><!--page stop -->


</div><!--mainright stop -->
