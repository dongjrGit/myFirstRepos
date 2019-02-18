<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath }/resource/public/seller/js/Zhgl/zhgl_role.js"></script>

<script src="${pageContext.request.contextPath }/resource/public/seller/js/Zhgl/zTree_permission.js"></script>

<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：账号管理 &gt; 账号管理 &gt; 角色维护
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>

                <span>
                    <label>角色名称：</label>
                    <input id="rolename" name="" type="text" class="text-inp">
                </span>
                <div class="clear"></div>

                <div class="submit-but">
                    <input name="rolesearch" type="button" value="查询" class="but-comm">
                    <a href="zhgl_RoleEdit"><input name="" type="button" value="新建角色" class="but-comm"></a>
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="thgl">
            <table>
                <tr id="role_title">
                    <th width="10%">序号</th>
                    <th width="20%">角色名称</th>
                    <th width="25%">角色描述</th>
                    <th width="20%">角色状态</th>
                    <th width="25%">操作</th>
                </tr>
                <tbody id="datalist">
                <script id="rolelist" type="text/html">
                    {{each list as pro i}}
                    <tr>
                        <td>{{i+1}}</td>
                        <td>{{pro.name}}</td>
                        <td>{{pro.description}}</td>
                        <td id="td_status">
							{{if pro.status==0}}<span class="lvs">已启用</span>
                            {{else if pro.status==1}}<span class="lvs">已禁用</span>
                            {{/if}}</td>
                        <td>
                            <a href="zhgl_RoleEdit?id={{pro.id}}"><span class="bjxx">编辑</span></a>
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" onclick="del({{pro.id}})"><span class="delete">删除</span></a>
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" onclick="getperm('{{pro.name}}',{{pro.id}},0)"><span class="bjxx">编辑权限</span></a>
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" onclick="getperm('{{pro.name}}',{{pro.id}},1)"><span class="bjxx">查看权限</span></a>
                        </td>
                    </tr>
                    {{/each}}
                </script>
                </tbody>
            </table>
        </div><!--thgl 表格部分结束 -->
        <div class="clear"></div>
        <div id="pager" class="page">

        </div>
    </div><!--主要内容 右边结束 -->
</div>
<script type="text/javascript">
	$(document).ready(function(){
		role.bind(1);
	})
</script>