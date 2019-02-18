<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="${ctx }/resource/public/platform/js/shop/shopviolationhistory.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="mar35">
        <span>店铺名称：<input id="select_shop" type="text" class="inp-seller" /></span>
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
        <span>
            违规类型：
            <select class="the-form-select-one" id="select_type" type="text">
                <option value="0">请选择</option>
                <option value="1">轻度违规</option>
                <option value="2">一般违规</option>
                <option value="3">严重违规</option>
            </select>
        </span>
        <span class="marrig10"></span>
        <span><input class="inquire" name="select_button" type="button" value="查询" onclick="ShopViolationList.getShopViolationList(1)"></span>
        <!-- <input class="inquire" name="" type="button" value="导出违规处理"> -->
        <span class="marrig35"></span>
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="shopviolationlist_title">
                <th>商户</th>
                <th>店铺名称</th>
                <th>违规类型</th>
                <th>违规描述</th>
                <th>处理结果</th>
                <th>处理时间</th>
                <!-- <th>操作</th> -->
            </tr>
            <script id="shopviolationlist" type="text/html">
                {{each list as pro i}}
                <tr>
                   <td>{{pro.username}}</td>
                    <td>{{pro.shopname}}</td>
                    {{if pro.type==2}}
                    <td>一般违规</td>
                    {{else if pro.type==3}}
                    <td>严重违规</td>
                    {{else}}
                    <td>轻度违规</td>
                    {{/if}}
                    <td title="{{pro.description}}">
                        {{if pro.description.length>20}}
                        {{pro.description.substring(0,20)}}...
                        {{else}}
                        {{pro.description}}
                        {{/if}}
                    </td>
                    <td title="{{pro.result}}">
                        {{if pro.result.length>20}}
                        {{pro.result.substring(0,20)}}...
                        {{else}}
                        {{pro.result}}
                        {{/if}}
                    </td>
                    <td>{{pro.createtimetr}}</td>
                    
                </tr>
                {{/each}}
            </script>
        </table>
        <!-- <td>
                            <input type="hidden" id="hidden_violationid" value="{{pro.id}}" />
                            <input type="hidden" id="hidden_userid" value="{{pro.userid}}" />
                            <a href="javascript:void(0);" class="a_shopviolationdelete"><span class="delete">删除</span></a>
                        </td> -->
    </div><!--table-con  stop -->
    <div class="clear"></div>
    <div id="pager" class="page">

    </div><!--page stop -->

</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //列表以及分页数据绑定
        ShopViolationList.bind();
    })
</script>
