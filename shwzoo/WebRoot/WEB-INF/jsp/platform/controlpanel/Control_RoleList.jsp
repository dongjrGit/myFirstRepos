<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/manager_role.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>角色名称：</span>
        <input id="name" name="" type="text" class="inp-seller" value="" />
        <span class="marrig10"></span>
        <input class="inquire" name="rolesearch" type="button" value="查询">
        <span class="marrig10"></span>
        <input class="inquire" name="roleadd" type="button" value="+添加角色">
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="role_title">
                <th width="10%">角色名称</th>
                <th width="10%">状态</th>
                <th width="25%">角色描述</th>
                <th width="15%">添加时间</th>
                <th width="40%">操作</th>
            </tr>
            <tbody id="datalist">
                <script id="rolelist" type="text/html">
                    {{each list as role i}}
                    <tr>
                        <td>{{role.name}}</td>
                        <td id="td_{{role.id}}">
                        {{if role.status==0}}
                            <span class="lvs"><a href="javascript:void(0);" onclick="setStatus({{role.id}},1)">启用</a></span>
                        {{else }}
                            <span class="lvs"><a href="javascript:void(0);" onclick="setStatus({{role.id}},0)">禁用</a></span>                      
                        {{/if}}
                        </td>
                        <td>{{role.description}}</td>
                        <td>{{role.creattimestr}}</td>
                        <td>
                            <a href="control_RoleEdit?id={{role.id}}"><span class="shenlan">编辑</span></a>

                            <a href="javascript:void(0);" onclick="del({{role.id}})"><span class="shenlan">删除</span></a>

                            <a href="javascript:void(0);" onclick="getperm('{{role.name}}',{{role.id}},0,1)"><span class="shenlan">编辑权限</span></a>

                            <a href="javascript:void(0);" onclick="getperm('{{role.name}}',{{role.id}},1,1)"><span class="shenlan">查看权限</span></a>
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
    </div>
    <div class="clear"></div>
    <div id="pager" class="page">

    </div>
</div>
<script type="text/javascript">
	rolesearch();
</script>