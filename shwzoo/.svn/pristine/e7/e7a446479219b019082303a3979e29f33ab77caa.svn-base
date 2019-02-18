<!-- {
    ViewBag.Title = "物流公司&gt;物流公司列表";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/public/platform/js/logistics/logistics.js"></script>

<div class="mainright">
	<div class="clear"></div>
	<div class="dotted mar35"></div>
	<div class="account-form">
		<span>名称：</span><input class="inp-seller" id="select_title"
			type="text"> <span class="marrig10"></span> <span>编号：</span><input
			class="inp-seller" id="select_pronum" type="text"> <span
			class="marrig10"></span> <input class="chaxun" name="select_button"
			type="button" value="查询" onclick=""> <input class="chaxun"
			name="btn_add" id="btn_add" type="button" value="添加" onclick="">
		<!--the-form stop -->

		<div class="clear"></div>
		<div class="mar35"></div>
		<div class="table-con">
			<table class="data_list">
				<thead>
					<tr id="goodconsultlist_title">
						<th>名称</th>
						<th>编号</th>
						<th>排序</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
				<script id="list" type="text/html">
                {{each list as pro i}}
                <tr> <td>{{pro.name}}</td>
                    <td>{{pro.code}}</td>                   
                     <td>{{pro.sort}}</td>
                    <td>                           <a href="edit?id={{pro.id}}"><span class="bjxx">修改</span></a>
                        <a href="javascript:void(0);" onclick="deletebyid({{pro.id}})"><span class="delete">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
			</table>

		</div>
		<!--table-con  stop -->

		<div class="clear"></div>
		<div id="pager" class="page"></div>
		<!--page stop -->


	</div>
</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {
		//列表以及分页数据绑定
		List.bind(1);
		$(".chaxun").bind("click", function() {
			List.bind(1);
		});
		$("#btn_add").bind("click", function() {
			window.location.href = "/platform/logistics/edit";
		});
	});
</script>