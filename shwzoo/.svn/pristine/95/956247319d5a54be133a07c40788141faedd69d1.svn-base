<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- <script	src="/resource/public/seller/js/spgl/spgl_sptj.js"></script> -->

<!--主要内容开始 -->
<div id="container">
	<!--主要内容 左边导航结束 -->
	<div class="allcon">
		<div class="position">您所在的位置：商品管理 &gt; 标准商品管理 &gt; 库存商品列表</div>
		<!--所在位置信息  结束 -->
		<div class="the-form">
			<form>
				<span> <label>所属标准商品：</label> <span class="red"
					data="${spu.id }" id="select_spuid">${spu.name }</span>
				</span>

				<div class="clear"></div>
				<a href="spgl_skuAdd?spuid=${spu.id }" target="_self"><input
					class="but-comm" type="button" value="+添加商品及规格信息"></a> <a
					href="spgl_spulist" target="_self"><input class="but-comm"
					type="button" value="返回商品列表"></a>
			</form>
		</div>
		<!--表单部分结束 -->
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
						<th>商品销量</th>
						<th>预警数量</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="list_title">
					<c:forEach var="sku" items="${skulist}">
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
							<td><a href="spgl_skuImglist?skuid=${sku.id }"><span>图片管理</span></a>
								<a href="spgl_skuEdit?id=${sku.id }"><span>商品及规格编辑</span></a>
								<a href="javascript:void(0);"
								onclick="sptj('${sku.name }',${sku.id })"><span>调价</span></a>
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
	<!--主要内容 右边结束 -->
	<script type="text/javascript">
		function sptj(skuname, skuid) {
		    d = dialog({
		        title: skuname + '的调价',
		        url: '/seller/productshop/spgl_skutj?skuid=' + skuid,
		        width: 400,
		        height: 240,
		        //fixed: true
		        //modal: true, //蒙层
		    });
		    d.show();
		}
	</script>
</div>
<!--主要内容结束 -->
