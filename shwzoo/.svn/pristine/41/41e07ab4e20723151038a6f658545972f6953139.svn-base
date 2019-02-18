<!-- {
    ViewBag.Title = "会员管理&gt;会员管理&gt;会员浏览记录";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/member/memberbrowserhistory.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>用户名：<input class="inp-seller" name="" id="uName" type="text"></span>
        <span class="marrig10"></span>
        <input class="chaxun" name="select_button" id="searchMem" type="button" value="查询">
       <!-- <span id="screening"><a href="javascript:void(0);">更多筛选条件 &or;</a></span>

            <div class="clear"></div>
            <div id="screening-con">
                <span>
                    选择范围：<select name="" class="the-form-select-one">
                        <option selected>本周</option>
                        <option>分类一</option>
                        <option>分类二</option>
                        <option>分类三</option>
                        <option>分类四</option>
                    </select>
                </span>
                <span>选择时间：<input type="text" id="time" onclick="return Calendar('time');" class="text_time" /> 至 <input type="text" id="time-one" onclick="return Calendar('time-one');" class="text_time" /></span>
            </div> -->

    </div><!--account-form stop -->
    <div class="clear"></div>
    <div class="mar35"></div>

    <div class="table-con">
        <table class="data_list">
            <tr id="memberbrowserlist_title">
                <th>用户名</th>
                <th>通道类型</th>
                <th>浏览日期</th>
                <th>操作</th>
            </tr>
            <script id="memberbrowserlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td>{{pro.username}}</td>
                    {{if pro.channeltype==1 }}
                    <td>安卓</td>
                    {{else if pro.channeltype==2 }}
                    <td>IOS</td>
                    {{else}}
                    <td>微软PC</td>
                    {{/if}}
                    <td>{{pro.createtimestr}}</td>
                    <td class="glance-over">
                        <input type="hidden" id="hidden_memberid" value="{{pro.userid}}" />
                        <a href="/platform/member/showMember_BrowserHisDetail?memberid={{pro.userid}}"><span class="browse-red shenlan">查看浏览列表</span></a>
                        <a href="javascript:void(0);" class="a_memberbrowserdelete"><span class="delete shenlan">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
        <div class="glance-over-con" id="div_browsedetail">
            <table width="100%">
                <tr id="browserdetaillist_title">
                    <td>浏览产品</td>
                    <td>浏览日期</td>
                </tr>
                <script id="browserdetaillist" type="text/html">
                    {{each list as pro i}}
                    <tr>
                        <td>{{pro.spuname}}<img width="100" height="100" src="{{pro.imgurl}}" /></td>
                        <td>{{pro.createtimestr}}</td>
                    </tr>
                    {{/each}}
                </script>
               <!-- <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr> -->
            </table>
            <p><input name="" type="button" value="返回" class="go-back"></p>
        </div>
    </div><!--table-con  stop -->
    <div class="clear"></div>

    <div id="pager" class="page">
    </div><!--page stop -->


</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //列表以及分页数据绑定
        MemberBrowserList.bind(1);
    })
</script>