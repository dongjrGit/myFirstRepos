<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath}/resource/public/platform/js/product/spgl_skulb.js"></script>
<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="notice-fenlei">
        <div style="float:left; padding-right:20px;">
            所属标准商品：
            <span class="red">${spu.name}</span>
            
        </div>
        <a href="/platform/product/goods_dpspgl" target="_self"><input class="inquire" name="" type="button" value="返回商品列表"></a>

    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th>商品名称</th>
                    <th>商品编码</th>
                    <th>商品原价</th>
                    <th>pc售价</th>
                    <th>App售价</th>
                    <th>wap售价</th>
                    <th>微信售价</th>
                    <th>商品库存</th>
                    <th>商品销量</th>
                    <th>预警数量</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="list_title">
            <c:forEach var="sku" items="${skulist }">
                   <tr>
                        <td>${sku.name }</td>
                        <td>${sku.num }</td>
                         <td>
                        <fmt:formatNumber value="${sku.oldprice}" pattern="0.00"/>
                        </td>  
                        <td>
                        <fmt:formatNumber value="${sku.price}" pattern="0.00"/>
                        </td>                     
                        <td>
                        <fmt:formatNumber value="${sku.appprice}" pattern="0.00"/>
                        </td>
                        <td>
                        <fmt:formatNumber value="${sku.wapprice}" pattern="0.00"/>
                        </td>
                        <td>
                        <fmt:formatNumber value="${sku.wechatprice}" pattern="0.00"/>
                        </td>
                        <td>${sku.stock }</td>
                        <td>${sku.salescount }</td>
                        <td>${sku.warnnum }</td>
                        <td>
                            <a href="goods_dpSkuImg_list?skuid=${sku.id}"><span class="shenlan">图片管理</span></a>
                            <a href="goods_dpsku_specsvalueslist?skuid=${sku.id}"><span class="shenlan">规格值列表</span></a>
                            <a href="javascript:void(0);" onclick="sptj('${sku.name}',${sku.id})"><span class="shenlan">调价</span></a>
                        </td>
                    </tr>
          </c:forEach>
            </tbody>
        </table>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div><!--page stop -->
       
    </div><!--table-con  stop -->

</div><!--mainright stop -->