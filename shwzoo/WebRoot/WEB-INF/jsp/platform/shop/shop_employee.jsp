<!-- @{
    ViewBag.Title = "店铺管理&gt;店铺管理&gt;店员列表";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="${ctx }/resource/public/platform/js/shop/shopemployee.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>所属店铺：<input id="select_shop" type="text" class="inp-seller" /></span>
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
        <span>员工姓名：<input class="inp-seller" id="select_name" type="text"></span>
        <span class="marrig10"></span>
        <span>登录用户名：<input class="inp-seller" id="select_username" type="text"></span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="inquire" name="" type="button" value="查询" onclick="ShopEmployeeList.getEmployeeList(1)"></span>
        <span class="marrig10"></span>
        <span><a href="${ctx }/platform/shop/showShopEmployeeAdd" target="_self"><input class="inquire" name="" type="button" value="+添加店铺员工"></a></span>
    </div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="shopemployeelist_title">
                <th>所属店铺</th>
                <th>员工编号</th>
                <th>员工姓名</th>
                <th>用户名</th>
                <!-- <th>密码</th> -->
                <th>联系方式</th>
                <th>店铺角色</th>
                <th>操作</th>
            </tr>
            <script id="shopemployeelist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td>{{pro.shopname}}</td>
                    <td>{{pro.empnum}}</td>
                    <td>{{pro.realname}}</td>
                    <td>{{pro.username}}</td>
                    <!-- <td>{{pro.Password}}</td> -->
                    <td>{{pro.mobile}}</td>
                    <td>{{pro.rolename}}</td>
                    <td>
                        <input type="hidden" class="hidden_employeeid" value="{{pro.id}}" />
                        <input type="hidden" class="hidden_shopid" value="{{pro.shopid}}" />
                        <input type="hidden" class="hidden_shopname" value="{{pro.shopname}}" />
                        <input type="hidden" class="hidden_empnum" value="{{pro.empnum}}" />
                        <input type="hidden" class="hidden_empname" value="{{pro.realname}}" />
                        <input type="hidden" class="hidden_emplogionname" value="{{pro.username}}" />
                        <input type="hidden" class="hidden_emploginpwd" value="{{pro.password}}" />
                        <input type="hidden" class="hidden_empmobile" value="{{pro.mobile}}" />
                        <input type="hidden" class="hidden_emproleid" value="{{pro.roleid}}" />
                        <a href="javascript:void(0);" class="a_shopemployeeupdate"><span class="shenlan">编辑</span></a>
                        {{if pro.islock==true}}
                        <input type="hidden" class="hidden_employeelock" value="true">
                        <a href="javascript:void(0);" class="a_lockshopemployee"><span class="shenlan">解锁</span></a>
                        {{else}}
                        <input type="hidden" class="hidden_employeelock" value="false">
                        <a href="javascript:void(0);" class="a_lockshopemployee"><span class="shenlan">锁定</span></a>
                        {{/if}}
                        <a href="javascript:void(0);" class="a_shopemployeepwdupdate"><span class="shenlan">修改密码</span></a>
                        <a href="javascript:void(0);" class="a_shopemployeedelete"><span class="shenlan">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>

        </table>
    </div><!--table-con  stop -->
    <div class="clear"></div>
    <div class="page" id="pager">

    </div><!--page stop -->


</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //列表以及分页数据绑定
        ShopEmployeeList.bind();
        ShopEmployeeList.getEmployeeList(1);
    })
</script>
