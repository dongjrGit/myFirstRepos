<!-- @{
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
    ViewBag.Title = "发送信息模板列表";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="${ctx }/resource/public/platform/js/ControlPanel/sendtemplate.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="member-xz">
        <!-- <span><input id="SelectAll" name="chk_list" type="checkbox" value="">全选</span>
        <span><input class="member-inp" name="" id="delete_all" type="button" value="批量删除"></span> -->
        <span>
            模板类型：
            <select name="" id="stType" class="the-form-select-one">
                <option value="" selected>请选择</option>
                <option value="0">邮件</option>
                <option value="1">短信</option>
                <option value="2">系统短信</option>
                <option value="3">系统推送</option>
            </select>
        </span>
        <span class="marrig10"></span>
        <span>
            内容类型：
            <select name="" id="cType" class="the-form-select-one">
                <option value="" selected>请选择</option>
                <option value="0">订单</option>
		        <option value="1">促销</option>
            </select>
        </span>
        <span class="marrig10"></span>   
             <input class="inquire" name="" type="button" id="searchBtn" value="搜索">
        <span><input class="inquire" name="" id="addbtn" type="button" value="添加信息模板"></span>
    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="trlist">
                <!-- <th width="80px">选择</th> -->
                <th width="20%">模板类型</th>
                <th width="40%">模板内容</th>
                <th width="10%">内容类型</th>
                <th width="10%">模板标识</th>
                <th>操作</th>
            </tr>
            <script type="text/html" id="datalist">
                {{each list as tem i}}
                <tr>                    
                    <td>{{if tem.type==0}}邮件
{{else if tem.type == 1}}短信
{{else if tem.type == 2}}系统短信
{{else}}系统推送
{{/if}}</td>
                    <td>{{tem.content}}</td>
<td>{{if tem.ctype==0}}订单
    {{else if tem.ctype==1}}促销
{{/if}}</td>
                    <td>{{if tem.tag==0}}下单成功
                        {{else if tem.tag==1}}支付成功
                        {{else if tem.tag==2}}支付失败
                        {{else if tem.tag==3}}卖家发货
                        {{else if tem.tag==4}}买家收货
                        {{else if tem.tag==5}}退换货申请
                        {{else if tem.tag==6}}卖家同意
                        {{else if tem.tag==7}}促销
                        {{else}} 
                        {{/if}}
                    </td>
                    <td class="zdgl-wzlbbj">
                        <input type="hidden" value="{{tem.id}}" />
                        {{if tem.isdefault==0}}
                        <span class="setDef shenlan"><a href="javascript:void(0);">设为默认模板</a></span>
                        {{else}}
                        <span class="setDef shenlan"><a href="javascript:void(0);">默认模板</a></span>
                        {{/if}}
                        <span class="bjxx shenlan"><a href="javascript:void(0);">编辑</a></span>
                        <span class="delete shenlan"><a href="javascript:void(0);">删除</a></span>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--table-con  stop -->

    <div class="page" id="pager">

    </div><!--page stop -->


</div><!--mainright stop -->
