<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="${ctx }/resource/public/platform/js/shop/spgl_dpgl_ppgl.js"></script>
<script>
function addshopBrand(obj){
	location.href="${ctx }/platform/shop/showShopBrandEdit?shopid="+obj
}
</script>
<div class="mainright">

    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="clear"></div>
    <div class="mar35">
        <span>所属店铺：<span class="red">${shop.name }</span></span>
        <span class="marrig10"></span>
      <%--   <a href="${ctx }/platform/shop/showShopBrandEdit?shopid=${shop.id}" target="_self"></a> --%>
        <input style="cursor: pointer;"  onclick="addshopBrand('${shop.id}')" class="chaxun" value="新增店铺关联品牌" />
        <span class="marrig10"></span>
        <a href="${ctx }/platform/shop/list" target="_self">
            <input type="button" class="inquire" value="返回店铺品牌管理" />
        </a>
            <input type="hidden" id="sid" value="${shop.id }" />
</div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list" id="list_title">
            <thead>
                <tr>
                    <th>店铺名称</th>
                    <th>品牌名称</th>
                    <th>审核状态</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <script id="sblist" type="text/html">
                    {{each list as item i}}
                    <tr>
                        <td>{{item.shopname}}</td>
                        <td>{{item.brandname}}</td>
                        <td>
                            {{if item.checkstatus==0}}未审核
                            {{else if item.checkstatus==1}}已审核
                            {{else }}审核不通过
                            {{/if}}
                        </td>
                        <td>{{item.creattimestr }}</td>
                        <td>
                            <a href="${ctx }/platform/shop/showShopBrandEdit?id={{item.id}}&shopid=${shop.id}"><span class="shenlan">编辑</span></a>
                            <a class="delete" data="{{item.id}}"><span class="shenlan">删除</span></a>
                            {{if item.checkstatus==0}}
                            <a href="javascript:void(0);" class="check" val="1" data="{{item.id}}"><span class="shenlan">审核通过</span></a>
                            <a href="javascript:void(0);" class="check" val="2" data="{{item.id}}"><span class="shenlan">审核驳回</span></a>
                            {{/if}}
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>  
          <%--   @*@foreach (Shop_Brand item in ViewBag.Data)
            {
                <tr>
                    <td>@ViewBag.Shop.Name</td>
                    <td>${item.id }</td>
                    @if (item.checkstatus == 0)
                    {
                        <td>未审核</td>
                    }
                    else if (item.checkstatus == 1)
                    {
                        <td>已审核</td>
                    }
                    else
                    {
                        <td>审核不通过</td>
                    }
                    <td>@item.createtime</td>
                    <td>
                        <a href="spgl_dpgl_brandEdit?id=@item.ID&sid=@ViewBag.Shop.ID">编辑</a>
                        <a class="delete" data="@item.ID">删除</a>
                        @if (item.CheckStatus == 0)
                        {
                            <a class="check" val="1" data="@item.ID">审核通过</a>
                            <a class="check" val="-1" data="@item.ID">审核驳回</a>
                        }
                    </td>
                </tr>
            }*@ --%>
        </table>
    </div><!--table-con  stop -->

    <div class="clear"></div>
    <div class="page" id="pager">

    </div><!--page stop -->


</div><!--mainright stop -->
<script type="text/javascript">
    $(function () {
        Shop.bind();
    });
</script>