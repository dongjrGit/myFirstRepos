<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="${pageContext.request.contextPath }/resource/public/seller/js/spgl/warnSku_list.js"></script>
	
<!--主要内容开始 -->
<div id="container">
	<!--主要内容 左边导航结束 -->
	<div class="allcon">
		<div class="position">您所在的位置：商品管理 &gt; 标准商品管理 &gt; 库存预警列表</div>
		<!--所在位置信息  结束 -->
		<div class="clear"></div>
		<div class="thgl">
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
						<th>预警数量</th>
						<th>操作</th>
					</tr>
				</thead>
			<tbody id="list_title">
				<script id="skulist" type="text/html">
                        {{each list as sku i}}
                        <tr>
                            <td>{{sku.name}}</td>
                            <td>{{sku.num}}</td>
                            <td>{{sku.oldprice}}</td>
                            <td>{{sku.price}}</td>
                            <td>{{sku.appprice}}</td>
                            <td>{{sku.wapprice}}</td>
                            <td>{{sku.wechatprice}}</td>
                            <td>{{sku.stock}}</td>
                            <td>{{sku.warnnum}}</td>
							<td>
								<a href="/web/products/proinfo.html?spuid={{sku.spuId}}" target="_blank"><span class="shenlan">详情</span></a>
							</td>
                        </tr>
                        {{/each}}
                    </script>
			</tbody>
		</table>
		<div class="clear"></div>
		<div id="pager" class="page"></div>
		<!--page stop -->

		</div>
	</div>
	<!--主要内容 右边结束 -->
</div>
<!--主要内容结束 -->
<script type="text/javascript">
	$(document).ready(function() {
		SKU.getWarnSKUList(1);
	})
</script>
