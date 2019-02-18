<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="${ctx }/resource/public/platform/js/MessageManagement/sendmessage.js"></script>
<div class="mainright">


    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>
            账号类型：<select id="userType" name="userType" class="the-form-select-one">
                <option selected="" value="-1">请选择</option>
                <option value="2">买家账户</option>
                <option value="5">卖家账户</option>
            </select>
        </span>
        <span class="marrig10"></span>
        <span>
            选择时间：<input type="text" id="stime" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd' })" readonly class="Wdate" />
            至 <input type="text" id="etime" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'stime\')}' })" readonly class="Wdate" />
        </span>
        <span class="marrig10"></span>
        <span>收件人：<input id="rName" type="text" class="inp-seller"></span>
        <span class="marrig10"></span>
        <span>电话号码：<input id="telnum" type="tel" class="inp-seller"></span>
        <span class="marrig10"></span>
        <input class="chaxun" name="select_button" id="searchBtn" type="button" value="查询">
         <!-- <span class="dc-bg" style="margin-right:0px;"><input class="inquire" name="" type="button" value="导出Excel表"></span> -->

    </div><!--account-form stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="member-xz">
        <span><input id="SelectAll" name="chk_All" type="checkbox" value="">全选</span>
        <span><input class="member-inp" name="" id="sendMesList" type="button" value="批量发送信息"></span>

    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="memberlist_title">
                <th>选择</th>
                <th>收件人</th>
                <th>手机</th>
                <th>性别</th>
                <th>省份</th>
                <th>加入日期</th>
                <th>操作</th>
            </tr>
            <script id="memberlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td><input name="chk_list" type="checkbox" value="{{pro.id}}"></td>
                    <td>{{pro.username}}</td>
                    <td id="td_membermobile">{{pro.mobile}}</td>
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

                    
                    <td class="lvs"><a class="sendmes" href="javascript:void(0);"><span class="shenlan">点击发送</span></a><input type="hidden" id="hidden_memberid" value="{{pro.id}}" /></td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--table-con  stop -->
    <div class="clear"></div>
    <div class="member-xz" style="margin-top:10px;">
       <!--  <span><input class="SelectAll"  name="chk_list" type="checkbox" value="">全选</span>
            <span><input class="member-inp" name="" type="button" value="批量发送短信"></span> -->
    </div>
    <div class="page" id="pager">
        <a href="javascript:void(0);">&lt;</a>
        <a href="javascript:void(0);">1</a>
        ...
        <span class="page-hover"><a href="javascript:void(0);">&gt;</a></span>
        <span class="page-style">共1页,</span>
        到第<input name="" type="text" class="page-inp">页
        <input name="" type="button" value="确认" class="page-but">

    </div><!--page stop -->


</div><!--mainright stop -->
