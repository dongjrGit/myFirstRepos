<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>商户号：</span>
        <input id="partner" name="" type="text" class="inp-seller" value="" />
        <span class="marrig10"></span>
        <span>支付类型：</span>
        <select id="paytype" name="" class="the-form-select-one">
            <option value="">全部</option>
            <option value="1">支付宝</option>
            <option value="2">环迅支付</option>
        </select>
        <span class="marrig10"></span>
        <input class="inquire" name="search" type="button" value="查询">
        <span class="marrig10"></span>
        <input class="inquire" name="add" type="button" value="+添加支付配置">
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="role_title">
                <th width="10%">商户号</th>
                <th width="10%">收款账号</th>
                <th width="10%">支付类型</th>
                <th width="10%">站点</th>
                <th width="15%">添加时间</th>
                <th width="10%">状态</th>
                <th width="35%">操作</th>
            </tr>
            <tbody id="datalist">
                <script id="paylist" type="text/html">
                    {{each list as payconfig i}}
                    <tr>
                        <td>{{payconfig.partner}}</td>
                        <td>{{payconfig.sellerid}}</td>
                        <td>
                        {{if payconfig.paytype==1}}
                            <span>支付宝</span>
                        {{else }}
                            <span>环迅支付</span>                      
                        {{/if}}
                        </td>
                        <td>
                        {{if payconfig.sites==1}}
                            <span>PC</span>
                        {{else if payconfig.sites==2}}
                            <span>APP</span>  
                        {{else if payconfig.sites==3}}
                            <span>WAP</span>  
                        {{else if payconfig.sites==4}}
                            <span>WeChat</span>                      
                        {{/if}}
                        </td>
                        <td>{{payconfig.createtimestr}}</td>
                        <td>
                        {{if payconfig.status==0}}
                            <span>启用</span>
                        {{else }}
                            <span>禁用</span>                      
                        {{/if}}
                        </td>
                        <td>
                            <a href="payConfig_edit?id={{payconfig.id}}"><span class="shenlan">编辑</span></a>
                            <a href="javascript:void(0);" onclick="del({{payconfig.id}})"><span class="shenlan">删除</span></a>
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
<script type="text/javascript" src="/resource/public/platform/js/ControlPanel/pay_config.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    bind.getlist();
})
</script>

