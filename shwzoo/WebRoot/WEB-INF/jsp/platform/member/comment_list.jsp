<!-- {
    ViewBag.Title = "会员管理&gt;会员咨询评论&gt;评论列表";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script type="text/javascript" src="/resource/public/platform/js/comment/comment.js"></script>
<div class="mainright">

    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>店铺名称：<input class="inp-seller" id="select_shopname" type="text"></span>
        <span class="marrig10"></span>
        <span>商品名称：<input class="inp-seller" id="select_proname" type="text"></span>
        <span class="marrig10"></span>
        <span>订单编号：<input class="inp-seller" id="select_ordernum" type="text"></span>
        <span class="marrig10"></span>
        <span>评论人：<input class="inp-seller" id="select_buyername" type="text"></span>
        <span class="marrig10"></span>
        <span>
            选择时间：
            <input type="text" id="select_begin" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })" class="text_time" /> 至
            <input type="text" id="select_end" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })" class="text_time" />
        </span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="inquire" name="" type="button" value="查询" onclick=" CommentList.bind(1);"></span>
    </div><!--the-form stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="commentlist_title">
                <th>评论人</th>
                <th>评论类型</th>
                <th>评论内容</th>
                <th>评论日期</th>
                <th>是否匿名</th>
                <th>订单编号</th>
                <th>商品名称</th>
                <th>店铺</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <script id="commentlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td>{{pro.buyername}}</td>
                    {{if pro.type==2}}
                    <td>晒单</td>
                    {{else if pro.type==3}}
                    <td>追评</td>
                    {{else}}
                    <td>评论</td>
                    {{/if}}
                    <td>
                        <div>星数：{{pro.star}}</div>
                        <div title="{{pro.content}}">
                            {{if pro.content.length>10}}
                            内容：{{pro.content.substring(0,10)}}...
                            {{else}}
                            内容：{{pro.content}}
                            {{/if}}
                        </div>
                    </td>
                    <td>{{pro.createdate}}</td>
                    {{if pro.showname==1}}
                    <td>是</td>
                    {{else}}
                    <td>否</td>
                    {{/if}}
                    <td>{{pro.ordercode}}</td>
                    <td>{{pro.spuname}}</td>
                    <td>{{pro.shopname}}</td>
                    {{if pro.status==1}}
                    <td class="lvs">审核通过</td>
					{{else if pro.status==2}}
					<td class="red">审核不通过</td>
					{{else}}
					<td>审核中</td>
                    {{/if}}
                    <td>
                        <input type="hidden" id="hidden_commentid" value="{{pro.id}}" />
                        <a href="javascript:void(0);" class="a_commentdetail"><span class="bjxx shenlan">明细</span></a>
                        <a href="javascript:void(0);" class="a_commentdelete"><span class="delete shenlan">删除</span></a>
                        <a href="javascript:void(0);"onclick="comcheck('{{pro.id}}','{{pro.star}}','{{pro.content}}')"><span class="shenlan">审核</span></a>
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
        CommentList.bind(1);
    })
</script>
