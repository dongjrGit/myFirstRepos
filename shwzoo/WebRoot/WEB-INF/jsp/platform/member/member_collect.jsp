<!--  @{
    ViewBag.Title = "会员管理&gt;会员管理&gt;会员收藏";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
}-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="/resource/public/platform/js/member/membercollect.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>标题：</span><input class="inp-seller" id="select_title" type="text">
        <span class="marrig10"></span>
    <!--     <span>产品编号：</span><input class="inp-seller" id="select_pronum" type="text"> -->
        <span class="marrig10"></span>
        <input class="chaxun" name="select_button" type="button" value="查询" onclick="CollectList.bind(1)">
    <!--     @*<span id="screening"><a href="javascript:void(0);">更多筛选条件 &or;</a></span>*@--> 
        <div class="clear"></div>

    </div><!--the-form stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="collectlist_title">
                <th>用户名</th>
                <th>收藏类型</th>
                <th>店铺名称</th>
                <th>收藏商品</th>
                <th>收藏时间</th>
                <th>操作</th>
            </tr>
            <script type="text/html" id="collectlist">
                {{each list as pro i}}
                <tr>
                    <td>{{pro.username}}</td>
                    {{if pro.type==1}}
                    <td>收藏店铺</td>
                    <td>{{pro.shopname}}</td>
                    <td>-----</td>
                    <td>{{pro.createtimestr}}</td>
                    {{else}}
                    <td>收藏商品</td>
                    <td>{{pro.shopnamespu}}</td>
                    <td>
                        {{pro.spuname}}
                        <img height="150" width="150" src="{{pro.spuimgurl}}" />
                    </td>
                    <td>{{pro.createtimestr}}</td>
                    {{/if}}
                    <td>
                        <input type="hidden" id="hidden_collectid" value="{{pro.id}}" />
                        <a href="javascript:void(0);" class="a_collectdelete"><span class="bjxx shenlan">删除</span></a>
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
        CollectList.bind(1);
    })
</script>
