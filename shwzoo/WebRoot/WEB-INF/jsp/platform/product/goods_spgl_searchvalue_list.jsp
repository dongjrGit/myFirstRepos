<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/public/platform/js/product/spgl_searchvaluelb.js"></script>



<div class="clear"></div>
<div class="mainright">
	<div class="clear"></div>
	<div class="dotted mar35"></div>
	<div class="notice-fenlei">
		<div style="float: left; padding-right: 20px;">
			属性名称：<span>${searchattr.name } </span> <input type="hidden"
				value="${searchattr.id }" id="attrid" /> <input type="hidden"
				value='${pageContext.request.queryString}'
				id="h_attrtype" />
		</div>
		<!-- <input class="chaxun" name="select_button" type="button" value="查询" onclick="S_Value.bind() "> -->
		<a href="javascript:void(0);" target="_self"><input
			class="chaxun addnew_button" type="button" value="+添加搜索属性值"></a>
		<a
			href="${pageContext.request.contextPath }/platform/product/showSearchAttr"
			target="_self"><input class="inquire" type="button"
			value="返回搜索属性"></a>
	</div>
	<!--notice stop -->
	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<thead>
				<tr>
					<th>值类型</th>
					<th>属性值</th>
					<th>值单位</th>
					<th>排序</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="list_title">
				<script id="svlist" type="text/html">
                    {{each list as attr i}}
                    <tr>
                        {{if attr.type == 0}}
                        <td>固定值</td>
                        {{else}}
                        <td>区间值</td>
                        {{/if}}
                        
                        <td>{{attr.value}}</td>
                        <td>{{attr.unit}}</td>
                        <td>{{attr.sort}}</td>
                        <td>
                           
                            <a href="javascript:void(0);" class="edit" data="{{attr.id}} "><span class="shenlan">编辑</span></a>
                            <a href="javascript:void(0);" class="del" data="{{attr.id}}"><span class="shenlan">删除</span></a>
                        </td>
                    </tr>
                    {{/each}}
                </script>
			</tbody>
			<tr id="addnew_tr" style="display: none;">
				<td><input type="hidden" id="addnew_id" /> <select
					id="addnew_type">
						<option value="0" selected>固定值</option>
						<option value="1">区间值</option>
				</select></td>
				<td id="addnew_td"><input type="text" id="addnew_value" /></td>
				<td><input type="text" id="addnew_unit" /></td>
				<td><input type="text" id="addnew_sort" /></td>
				<td><a href="javascript:void(0);" id="addnew_submit"><span
						class="shenlan">保存</span></a></td>
			</tr>
		</table>
		<div class="clear"></div>
		<div id="pager" class="page"></div>
		<!--page stop -->
		<div class="clear"></div>
		<div class="mar35"></div>
		<div class="sctp-con">
			
			<div class="clear"></div>
			<div class="preserve">
				<input type="hidden" id="specsid" value="${specsid }" /> <input
					class="preserve-inp" name="" type="button" value="保存">
			</div>
			<!--preserve stop -->
			<div class="close">X</div>
		</div>
		<!-- sctp-con stop-->
	</div>
	<!--table-con  stop -->

</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {
		S_Value.bind();
	})
</script>
