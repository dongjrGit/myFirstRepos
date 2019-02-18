<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/menulist.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>菜单名称：</span>
        <input id="name" name="" type="text" class="inp-seller" value="" />
        <span class="marrig10"></span>
        <span>菜单来源：</span>
        <select id="seltype" name="" class="the-form-select-one">
            <option value="">全部</option>
            <option value="0">平台</option>
            <option value="1">卖家</option>
            <option value="2">买家</option>
            <option value="3">前台</option>
        </select>
        <span>菜单类型：</span>
        <select id="selmtype" name="" class="the-form-select-one">
            <option value="">全部</option>
            <option value="0">菜单</option>
            <option value="1">按钮</option>
        </select>
        <span class="marrig10"></span>
        <input class="inquire" name="search" type="button" value="查询">
        <span class="marrig10"></span>
        <input class="inquire" name="add" type="button" value="+添加菜单">

    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="perm_title">
                <th width="15%">菜单名称</th>
                <th width="10%">菜单等级</th>
                <th width="10%">菜单来源</th>
                <th width="10%">是否有子级</th>
                <th width="35%">菜单链接</th>
                <th width="20%">操作</th>
            </tr>
            <tbody id="datalist">
                <script id="menulist" type="text/html">
                    {{each list as menu i}}
                    <tr>
                        <td>{{menu.name}}</td>
                        <td>{{menu.level}}</td>

                        {{if menu.menutype==0}}
                        <td>平台</td>
                        {{else if menu.menutype==1}}
                        <td>卖家</td>
                        {{else if menu.menutype==2}}
                        <td>买家</td>
                        {{else}}
                        <td>前台</td>
                        {{/if}}
                        {{if menu.haschild==true}}
                        <td>是</td>
                        {{else}}
                        <td>否</td>
                        {{/if}}
                        <td>{{menu.menuurl}}</td>
                        <td>
                            <a href="Control_Menuedit?id={{menu.id}}"><span class="shenlan">编辑</span></a>

                            <a href="javascript:void(0);" onclick="del({{menu.id}})"><span class="shenlan">删除</span></a>
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
$(document).ready(function () {
	Menu.getlist(1);
});

</script>