<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/packageSku.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		pack.bind();
	})
</script>
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<input type="hidden" id="packageid" value="${data.id}" /> <span>组合商品编号：<span
			class="red">${data.num}</span></span> <span class="marrig10"></span> <span>组合商品名称：<span
			class="red">${data.name}</span></span> <span class="marrig10"></span>
		<c:choose>
			<c:when test="${data.price==null }">
				<span> 组合商品价格： <span class="red"> 0.00 </span>
				</span>
			</c:when>
			<c:otherwise>
				<span> 组合商品价格： <span class="red"> <fmt:formatNumber
							value="${data.price}" pattern="0.00" />
				</span>
				</span>
			</c:otherwise>
		</c:choose>
		<span class="marrig10"></span>
		<c:if test="${data.shopid==shopid}">
		<input
			class="inquire chaxun" name="btnadd" type="button" value="+添加商品">
		</c:if>
		<c:choose>
		<c:when test="${type==1}">
		<a href="yxgl_PackageCheck" target="_self"><input class="inquire"
			name="" type="button" value="返回组合商品审核列表"></a>
		</c:when>
		<c:otherwise>
		<a href="yxgl_PackageList" target="_self"><input class="inquire"
			name="" type="button" value="返回组合商品列表"></a>
		</c:otherwise>
		</c:choose>
		
	</div>

	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="pack_title">
				<th width="12%">库存商品编号</th>
				<th width="30%">库存商品名称</th>
				<th width="12%">库存商品原价</th>
				<th width="12%">优惠价格</th>
				<th>操作</th>
			</tr>
			<tbody id="datalist">
				<script id="packlist" type="text/html">
                    {{each list as sku i}}
                    <tr>
                        <td>{{sku.skuNum}}</td>
                        <td>{{sku.skuName}}</td>
                        <td>{{sku.skuPrice | toFixed}}</td>
                        <td>{{sku.skuPackPrice | toFixed}}</td>
                        <td>
{{if ${data.shopid==shopid} }}
                            <a href="javascript:void(0);" class="del" data="{{sku.id}}"><span class="shenlan">删除</span></a>
                       {{/if}} 
</td>
                    </tr>
                    {{/each}}
                </script>
			</tbody>
		</table>
	</div>
	<div class="clear"></div>
	<div id="the-package" style="margin-top: 30px; display: none">
		<form id="form" method="post">
			<div class="tjcpxx-con-mk1">
				<label><span class="red marrig5">*</span>组合商品：</label>
				<div class="tjcpxx-con-form1" style="width: 430px;">
					<input id="select_sku" type="text" class="tjcpxx-fprm-inp" value="" />
					<input id="skuid" type="hidden" name="skuid" value="" />
					<input type="hidden" name="packageid" value="${data.id}" />
				</div>
				<div style="margin-top: 25px; margin-left: 15px;">
					<ul>
						<script id="select_skulist" type="text/html">
                            {{each list as sku i}}
                            <li data="{{sku.id}}">{{sku.name}}</li>
                            {{/each}}
                        </script>
					</ul>
				</div>
			</div>
			<div class="tjcpxx-con-mk1">
				<label><span class="red marrig5">*</span>优惠价格：</label>
				<div class="tjcpxx-con-form1" style="width: 430px;">
					<input id="price" name="price" type="text" class="tjcpxx-fprm-inp"
						value="" />&nbsp;元
				</div>
			</div>
			<input name="Save" type="button" value="保存" class="inquire">
			<input name="btncancel" type="button" value="取消" class="inquire">
		</form>
	</div>

</div>