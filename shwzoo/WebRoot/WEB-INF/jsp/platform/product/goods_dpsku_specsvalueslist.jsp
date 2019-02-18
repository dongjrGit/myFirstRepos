<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="notice-fenlei">
        <div style="float:left; padding-right:20px;">
           <input id="spu_id" type="hidden" value="${sku.spuId }" />
        </div>
        <div style="float:left; padding-right:20px;">
            所属库存商品：
            <span class="red">${sku.name }<input id="sku_id" type="hidden" value="${sku.id }" /></span>
        </div>
        <input class="inquire" name="backPage" type="button" value="返回库存商品列表">
    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th>规格名称</th>
                    <th>规格值</th>
                </tr>
            </thead>
            <tbody id="list_title">
                <c:forEach var="sv" items="${svlist }">
                    <tr>
                        <td>${sv.specsname }</td>
                        <td>${sv.value }</td>
                    </tr>
                </c:forEach>
               <!--  @for (int i = 0; i < ViewBag.Data.Count; i+=2)
                {
                    <tr>
                        <td>@ViewBag.Data[i].Name</td>
                        <td>@ViewBag.Data[i].Value</td>
                        @if (i + 1 < ViewBag.Data.Count - 1)
	                    {
                            <td>@ViewBag.Data[i+1].Name</td>
                            <td>@ViewBag.Data[i+1].Value</td>
                        }
                        @if (i == ViewBag.Data.Count - 1 && ViewBag.Data.Count % 2 == 1)
                        { 
                            <td></td><td></td>
                        }
                    </tr>
                } -->
            </tbody>
           
            </table>
            <div class="clear"></div>

        </div><!--table-con  stop -->

</div><!--mainright stop -->
    <script type="text/javascript">
    $(document).ready(function () {
        $("input[name=backPage]").bind("click", function () { location.href = "goods_dpSkulist?spuid=" + $('#spu_id').val(); })
    })
    </script>