<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="/resource/public/platform/js/ControlPanel/Operator.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        Operator.unit(Operator.callback);
        search();
    })
</script>
<div class="mainright">
    <div class="account-form">
        <span>登录账户：</span>
        <input id="name" name="" type="text" class="inp-seller" value="" />
        <span class="marrig10"></span>
        <span>所属部门：</span>
        <input type="hidden" id="fid" />
        <input type="hidden" id="sid" />
        <input type="hidden" id="tid" />
        <select class="the-form-select-one" name="firstID" id="firstID" onchange="Operator.firstChange(Operator.callback, 'fc')">
            <option value="0" id="defaultfc" selected>无</option>
            <script class="the-form-select-one" id="flist" type="text/html">
                {{each list as fclass i}}
                <option value="{{fclass.id}}">{{fclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" name="secondID" id="secondID" onchange="Operator.firstChange(Operator.callback, 'sc')">
            <option value="0" id="defaultsc" selected>无</option>
            <script id="slist" type="text/html">
                {{each list as sclass i}}
                <option value="{{sclass.id}}">{{sclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" name="thirdID" id="thirdID">
            <option value="0" id="defaulttc" selected>无</option>
            <script id="tlist" type="text/html">
                {{each list as tclass i}}
                <option value="{{tclass.id}}">{{tclass.name}}</option>
                {{/each}}
            </script>
        </select>
        <span class="marrig10"></span>
        <span>所属角色：<input id="select_role" type="text" class="inp-seller" /></span>
        <div>
            <ul>
                <script id="select_rolelist" type="text/html">
                    {{each list as role i}}
                    <li data="{{role.id}}">{{role.name}}</li>
                    {{/each}}
                </script>
            </ul>
        </div>
        <span class="marrig10"></span>
        <input class="inquire" name="search" type="button" value="查询">
        <span class="marrig10"></span>
        <input class="inquire" name="add" type="button" value="+添加管理员">
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr>
                <th width='15%'>登录账户</th>
                <th width='15%'>邮箱</th>
                <th width='20%'>部门</th>
                <th width='15%'>角色</th>
                <th width='*'>管理项</th>
            </tr>
            <tbody id="datalist">
                <script id="operlist" type="text/html">
                    {{each list as oper i}}
                    <tr>
                        <td>{{oper.loginname}}</td>
                        <td>{{oper.email}}</td>
                        <td>{{oper.departmentname}}</td>
                        <td>{{oper.rolename}}</td>
                        <td>
                            <a href='operator_edit?id={{oper.id}}'><span class='bjxx shenlan'>编辑</span></a>
                            <a href='javascript:void(0);' onclick='Operator.del({{oper.id}})'><span class='delete shenlan'>删除</span></a>
                            <a href='#' onclick="changePassword({{oper.id}},'{{oper.loginname}}')"><span class='bjxx shenlan'>修改密码</span></a>
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