<!-- {
    ViewBag.Title = "会员管理&gt;会员咨询评论&gt;评论列表";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <script type="text/javascript" src="/resource/public/seller/js/spgl/evaluation_list.js"></script>
 <script type="text/javascript" >
 $(document).ready(function () {
     //列表以及分页数据绑定
     CommentList.bind(1);
 })
 
 </script>
 
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：商品管理&gt; 商品管理 &gt; 商品评价列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form >
               <!-- 此处判断用于查询对账订单 -->
                     <span><label>商品名称：</label><input id="spuname" name="spuname" type="text" class="text-inp"></span>
                    <label>评价审核状态：</label>
                    <select class="sel-form" id="status_select">
                        <option value="-1" selected>请选择</option>
                        <option value="0">审核中</option>
                        <option value="1">审核通过</option>
                        <option value="2">审核未通过</option>
                    </select>
                     <span><label>订单编号：</label><input id="ordercode" name="ordercode" type="text" class="text-inp"></span>
					<input type="button" value="查询" name="search" onclick="CommentList.bind(1);" class="but-comm">
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="thgl">
            <table>
				<thead>
                <tr id="commentlist_title">
                <th>商品名称</th>
                <th>评论人</th>
                <th>评论内容</th>
                <th>评论日期</th>
                <th>订单编号</th>
                <th>审核状态</th>
                <th>操作</th>
                </tr>
				</thead>
            </table>
            <div class="l_ddgl">
                <table id="datalist">
                    <script id="commentlist" type="text/html">
                     {{each list as pro i}}
                <tr>
                    <td>{{pro.spuname}}</td>
                    <td>{{pro.buyername}}</td>
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
                    <td>{{pro.ordercode}}</td>
                    <td id="td_status">
							{{if pro.status==0}}<span class="lvs">审核中</span>
                            {{else if pro.status==1}}<span class="lvs">审核通过</span>
                            {{else  pro.status==2}}<span class="red">审核不通过</span>
                            {{/if}}
					</td>
                    <td>
                        	<input type="hidden" id="hidden_commentid" value="{{pro.id}}" />
                        	<a href="javascript:void(0);" class="a_commentdetail"><span class="bjxx shenlan">明细</span></a>
							{{if pro.status ==0}}
                            <a href="javascript:void(0);" name="changestate1"   onclick="ChangeStatus1({{pro.id}})" data-id="{{pro.id}}" data-state="1"><span class="shenlan span_status">审核通过</span></a>
                            {{else if pro.status ==1}}
                            <a href="javascript:void(0);" name="changestate2"   onclick="ChangeStatus2({{pro.id}})" data-id="{{pro.id}}" data-state="2"><span class="shenlan span_status">审核不通过</span></a>
                            {{else if pro.status ==2}}
                            <a href="javascript:void(0);" name="changestate3"   onclick="ChangeStatus3({{pro.id}})" data-id="{{pro.id}}" data-state="0"><span class="shenlan span_status">重新审核</span></a>
                            {{/if}}
							
					</td>
                </tr>
                {{/each}}
                </script>
                </table>
            </div><!--thgl 表格部分结束 -->


            <div class="clear"></div>
            <div id="pager" class="page">

            </div>
        </div><!--主要内容 右边结束 -->
    </div>
</div>
