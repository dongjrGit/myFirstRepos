<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_costomcheck.js"></script>
<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        所属分类：
        <select class="the-form-select-one" id="fc_select" onchange="Custom.fatherChange('fc')">
            <script id="flist" type="text/html">
                {{each list as fclass i}}
                <option value="{{fclass.id}}">{{fclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" id="sc_select" onchange="Custom.fatherChange('sc')">
            <script id="slist" type="text/html">
                {{each list as sclass i}}
                <option value="{{sclass.id}}">{{sclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" id="tc_select">
            <script id="tlist" type="text/html">
                {{each list as tclass i}}
                <option value="{{tclass.id}}">{{tclass.name}}</option>
                {{/each}}
            </script>
        </select>
        <span class="marrig10"></span>
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
        <input class="chaxun" name="select_button" type="button" value="查询">
        <span class="marrig10"></span>
        <input class="inquire" name="customsh" type="button" value="批量审核">
        <span class="marrig10"></span>
        <input class="inquire" name="custombh" type="button" value="批量驳回">
    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th width="10%"><input name="chkall" type="checkbox" value=""></th>
                    <th width="20%">分类名称</th>
                    <th width="15%">所属店铺</th>
                    <th width="15%">状态</th>
                    <th width="30%">分类路径</th>
                    <th width="10%">操作</th>
                </tr>
            </thead>
            <tbody id="list_title">
                <script id="cuslist" type="text/html">
                    {{each list as cla i}}
                    <tr>
                        <td><input id="chk_{{cla.id}}" name="chksel" type="checkbox" value="{{cla.id}}"></td>
                        <td>{{cla.name}}</td>
                        <td>{{cla.shopname}}</td>
                        <td>
                            {{if cla.status==1}}提交审核中
                            {{/if}}
                        </td>
                        <td>{{cla.fullname}}</td>
                        <td>
                            <a href="javascript:void(0);" onclick="customcheck({{cla.id}},2)"><span class="shenlan">审核</span></a>

                            <a href="javascript:void(0);" onclick="customcheck({{cla.id}},3)"><span class="shenlan">驳回</span></a>
                        </td>

                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div><!--page stop -->
    </div><!--table-con  stop -->

</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        Custom.bind();
        Custom.getClassList(1);
    })
</script>