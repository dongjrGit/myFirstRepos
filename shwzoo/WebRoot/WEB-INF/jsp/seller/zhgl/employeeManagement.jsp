<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="${ctx }/resource/public/seller/js/Zhgl/EmployeeManagement.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	ShopEmployeeList.bind(1);
})
</script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：账号管理 &gt; 账号管理 &gt; 子账号维护
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>
              <input type="hidden" id="shopid" value="${shop.id }">
                <span>
                    <label>用户名：</label>
                    <input type="text" id="username" value="" class="text-inp" />
                </span>
                <span>
                    <label>员工姓名：</label>
                    <input type="text" id="realname" value="" class="text-inp" />
                </span>
                <span>
                    <label>所属角色：</label>
                    <input id="select_role" type="text" class="sel-form" />
                </span>
                <div>
                    <ul>
                        <script id="select_rolelist" type="text/html">
                            {{each list as role i}}
                            <li data="{{role.id}}">{{role.name}}</li>
                            {{/each}}
                        </script>
                    </ul>
                </div>

                <div class="clear"></div>

                <div class="submit-but">
                    <input name="search" type="button" value="查询" class="but-comm">
                    <a href="${ctx }/seller/zhglshop/showEmployeeManagementAdd?shopid=${shop.id }"><input name="" type="button" value="新建子账号" class="but-comm"></a>
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="thgl">
            <table>
               <!--  <tr class="blank-tr"><td colspan="16"><div style="height:10px;"></div></td></tr> -->
                <tr id="shopemployeelist_title">
                    <th>员工编号</th>
                    <th>员工姓名</th>
                    <th>用户名</th>
                    <th>联系方式</th>
                    <th>店铺角色</th>
                    <th>操作</th>
                </tr>
                <script id="shopemployeelist" type="text/html">
                    {{each list as pro i}}
                    <tr>
                        <td>{{pro.empnum}}</td>
                        <td>{{pro.realname}}</td>
                        <td>{{pro.username}}</td>
                        <td>{{pro.mobile}}</td>
                        <td>{{pro.rolename}}</td>
                        <td>
                            <input type="hidden" class="hidden_employeeid" value="{{pro.id}}" />
                            <input type="hidden" class="hidden_employeename" value="{{pro.username}}" />
                            <a href="javascript:void(0);" class="a_shopemployeeupdate"><span class="bjxx">编辑</span></a>
                            <a href="javascript:void(0);" class="a_shopemployeepassword"><span class="bjxx">修改密码</span></a>
                            {{if pro.islock==true}}
                            <input type="hidden" class="hidden_employeelock" value="true">
                            <a href="javascript:void(0);" class="a_lockshopemployee"><span class="bjxx">解锁</span></a>
                            {{else}}
                            <input type="hidden" class="hidden_employeelock" value="false">
                            <a href="javascript:void(0);" class="a_lockshopemployee"><span class="bjxx">锁定</span></a>
                            {{/if}}
                            <a href="javascript:void(0);" class="a_shopemployeedelete"><span class="delete">删除</span></a>
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </table>
        </div><!--thgl 表格部分结束 -->
        <div id="pager" class="page">
        </div><!--page stop -->
    </div><!--主要内容 右边结束 -->
</div>
