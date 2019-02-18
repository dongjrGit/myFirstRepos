<!-- @{
    ViewBag.Title = "店铺管理&gt;店铺管理&gt;店铺审核";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="${ctx }/resource/public/platform/js/shop/shopcheck.js"></script>
<div class="mainright">

    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>店铺名称：<input id="select_shop" type="text" class="inp-seller" /></span>
        <div>
            <ul>
                <script id="select_shoplist" type="text/html">
                    {{each list as shop i}}
                    <li data="{{shop.id}}">{{shop.name}}</li>
                    {{/each}}
                </script>
            </ul>
        </div>
        <span class="marrig10"></span>
        <span>公司名称：<input class="inp-seller" id="select_companyname" type="text"></span>
        <span class="marrig10"></span>
        <span><input class="inquire" name="" type="button" value="查询" onclick="ShopCheckList.getShopCheckList(1)"></span>
    </div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="shopchecklist_title">
                <th>联系人姓名</th>
                <th>联系人手机</th>
                <th>联系人邮箱</th>
                <th>店铺名称</th>
                <th>公司名称</th>
                <th>公司地址</th>
                <!-- <th>申请时间</th> -->
                <th>状态</th>
                <th>操作</th>
            </tr>
            <script id="shopchecklist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td>{{pro.principalname}}</td>
                    <td>{{pro.principalmobile}}</td>
                    <td>{{pro.principalemail}}</td>
                    <td>{{pro.shopname}}</td>
                    <td>{{pro.companyname}}</td>
                    <td>{{pro.companyadress}}</td>
                    {{if pro.status==1}}
                    <td class="lvs">审核通过</td>
                    {{else if pro.status==2}}
                    <td class="red">审核不通过</td>
                    {{else}}
                    <td>审核中</td>
                    {{/if}}
                    <td>
                        <input type="hidden" id="hidden_shopid" value="{{pro.shopid}}" />
                        <a href="javascript:void(0);" class="a_shopcheckdetail"><span class="shenlan">明细</span></a>
                        <a href="javascript:void(0);" class="a_shopcheckdelete"><span class="shenlan">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--table-con  stop -->
    <div class="clear"></div>
    <div id="pager" class="page">     
    </div><!--page stop -->

</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //列表以及分页数据绑定
        ShopCheckList.bind();
        ShopCheckList.getShopCheckList(1)
    })
</script>
