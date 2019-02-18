<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
	<div class="allcon">
		<div class="position">您所在的位置：营销管理 &gt; 闪购列表 &gt; 商品列表</div>
		<!--所在位置信息  结束 -->
		<div class="the-form">
			<span>活动编号：<span class="red">${data.spikenum }</span></span> 
			<span>活动名称：<span class="red">${data.spikename }</span></span> 
			<input type="hidden" id="spikeid" value="${data.id }" /> 
			<span>
			<if test="${isout==1}">
			   <input class="but-comm chaxun" name="btnadd" type="button" value="+添加商品">
			</if>
            <a href="yxgl_SpikeList" target="_self"><input class="but-comm" name="" type="button" value="返回列表"></a>
			</span>
		</div>
		<div class="clear"></div>
		<div class="thgl">
			<table class="data_list">
				<tr id="spikesku_title">
					<th width="10%">商品编号</th>
					<th width="20%">商品名称</th>
					<th width="20%">商品图片</th>
					<th width="10%">商品价格</th>
					<th width="10%">优惠价格</th>
					<th width="10%">数量</th>
					<th width="10%">手机专享</th>
					<th>操作</th>
				</tr>
				<tbody id="datalist">
					<script id="spikeskulist" type="text/html">
                        {{each list as spikesku i}}
                        <tr>
                            <td>{{spikesku.num}}</td>
                            <td>{{spikesku.name}}</td>
                            <td><img style="height:80px;width:100px;" alt="" src="{{spikesku.imgUrl}}" /></td>
                            <td>{{spikesku.price}}</td>
                            <td>{{spikesku.discountPrice}}</td>
                            <td>{{spikesku.spuCount}}</td>
                            <td id="td_{{spikesku.id}}">
                                {{if spikesku.isPhone==0}}
                                <span class="lvs"><a id="a_{{spikesku.id}}" href="javascript:void(0);" onclick="setIsPhone({{spikesku.id}},1)">否</a></span>
                                {{else}}
                                <span class="lvs"><a id="a_{{spikesku.id}}" href="javascript:void(0);" onclick="setIsPhone({{spikesku.id}},0)">是</a></span>
                                {{/if}}
                            </td>
                            <td>
                                {{if spikesku.isout==0}}
                                <span class="huise">编辑</span>
                                <span class="marrig35"></span>
                                <span class="huise">删除</span>
                                {{else}}
                                <a href="yxgl_SpikeSpuEdit?id={{spikesku.id}}"><span class="shenlan">编辑</span></a>
                                <span class="marrig35"></span>
                                <a href="javascript:void(0);" class="del" data-id="{{spikesku.id}}"><span class="shenlan">删除</span></a>
                                {{/if}}
                                
                            </td>
                        </tr>
                        {{/each}}
                    </script>
				</tbody>
			</table>
		</div>
		<div class="clear"></div>
		<div id="pager" class="page"></div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/resource/public/seller/js/yxgl/spikeSku.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		spikesku.bind();
	})
</script>