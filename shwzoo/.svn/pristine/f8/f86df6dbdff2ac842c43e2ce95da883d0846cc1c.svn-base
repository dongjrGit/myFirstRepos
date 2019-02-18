<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<span>活动编号：<span class="red">${data.spikenum }</span></span> <span
			class="marrig10"></span> <span>活动名称：<span class="red">${data.spikename }</span></span>
		<input type="hidden" id="spikeid" value="${data.id }" /> <a
			href="yxgl_SpikeList" target="_self"><input class="inquire" name=""
			type="button" value="返回列表"></a>
	</div>

	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="spikeshop_title">
				<th width="20%">店铺名称</th>
				<th width="15%">申请时间</th>
				<th width="10%">状态</th>
				<th>操作</th>
			</tr>
			<tbody id="datalist">
				<script id="spikeshoplist" type="text/html">
                    {{each list as spikeshop i}}
                    <tr>
                        <td>{{spikeshop.shopname}}</td>
                        <td>{{spikeshop.sscreatetimestr}}</td>
                        <td>
                            {{if spikeshop.spikeshopstatus==1}}
                                                         提交审核中
                            {{else if spikeshop.spikeshopstatus==2}}
                                                         审核通过
                            {{else}}
                                                         审核不通过
                            {{/if}}
                        </td>                      
                        <td>
                           {{if spikeshop.spikeshopstatus==1}}
                            <a href="javascript:void(0);" class="check" data-id="{{spikeshop.spikeshopid}}" data-type="2"><span class="shenlan">同意</span></a>
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" class="check" data-id="{{spikeshop.spikeshopid}}" data-type="3"><span class="shenlan">不同意</span></a>
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
<script
	src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/spikeShop.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		spikeshop.getlist(1);
	})
</script>