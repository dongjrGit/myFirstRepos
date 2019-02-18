<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="/resource/public/platform/js/baner/list.js"></script>
<div class="clear"></div>
<div class="mainright">
	<div class="clear"></div>
	<div class="dotted mar35"></div>
	<div class="account-form">
	<input type="hidden" id="site" value=${site}  />
		<span>标题：<input type="text" id="name_select" class="inp-seller" /></span>
		<span>使用平台：
			<select class="the-form-select-one" id="type_select">
				<option value="">全部</option>
				<option value="2">APP</option>
				<option value="3">WAP</option>
			</select>
		</span> 
      	<span class="marrig35"></span><input type="hidden" id="type" name="type" value=${type } type="text">
		<span class="marrig10"></span> <input class="chaxun"
			name="select_button" type="button" value="查询"onclick="Brand.getAll(1)">
		<span class="marrig10"></span> 
		<input class="chaxun" name="" onclick="back();" type="button" value="+添加发现baner">
	</div>
	<!--notice stop -->
	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="list_title">
				<th>标题</th>
				<th width="10%">图片</th>
				<th width="30%">操作</th>
			</tr>
			<script id="brandlist" type="text/html">
                {{each list as brand i}}
                <tr>
                    <td>{{brand.title}}</td>
                    <td class="ppimg"><img src="<%=path %>{{brand.img}}" width="50px" height="25px"></td>
                    <td>
                        <a href="${ctx }/platform/baner/edit?id={{brand.id}}&type={{brand.type}}" title="修改"><span class="shenlan">修改</span></a>
                        <a href="javascript:void(0);" onclick="Brand.del({{brand.id}})"><span class="shenlan" title="删除">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
		</table>
		
		<div class="clear"></div>
		<div id="pager" class="page"></div>
		<!--page stop -->
	</div>
	<!--table-con  stop -->

</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {
		Brand.getAll(1);
	})
</script>