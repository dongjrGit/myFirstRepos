<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/resource/public/platform/js/hotcity/hotcity.js" type="text/javascript"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<td class="text-right">热门城市：</td>
			<select id="select_province" name="select_province"
				class="the-form-select-one">
				<option value="-1">请选择</option>
				<script id="proviceselect" type="text/html">
                {{each list as pro index}}
                <option value="{{pro.code}}">{{pro.name}}</option>
                {{/each}}
                </script>
			</select> --><select id="select_city" name="select_city" class="the-form-select-one">
				<option value="-1">请选择</option>
				<script id="cityselect" type="text/html">
                {{each list as pro index}}
                <option value="{{pro.code}}">{{pro.name}}</option>
                {{/each}}
                </script>
			</select>
			<span class="marrig10"></span>
		 <input class="inquire" name="btnsearch" type="button" value="查询"> 
		 <input class="inquire add" name="btnadd" type="button" value="+添加热门城市">
	</div>

	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="buy_title">
				<th>城市名称</th>
				<th width="25%">操作</th>
			</tr>
			<tbody id="datalist">
				<script id="tdatalist" type="text/html">
                    {{each list as pro i}}
                    <tr>
                        <td>{{pro.name}}</td> 
                        <td>
                            <a href="/platform/rmcs/edit?id={{pro.id}}" title="修改"><span class="shenlan">修改</span></a>
							<a href="javascript:void(0);" class="del" onclick="del({{pro.id}})" title="删除"><span class="shenlan">删除</span></a>
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
<!--mainright stop -->

<script type="text/javascript">
$(document).ready(
		function() {
			data.bind(1);	
			BindRegion();
			$(".add").bind("click",function(){
				window.location.href = "/platform/rmcs/edit";
			});
			$(".inquire").bind("click",function(){
				data.bind(1);
			});
			
		});
		function del(id){
			if(!confirm("确定删除吗?")){
				return;
			}
			$.ajax({
				url:"/platform/hotcity/delById",
				type:"post",
				data:{"id":id},
				dataType:"json",
				success:function(data){
					if(data.code<0){
						Dalert(data.desc);
					}else{
						Dalert("删除成功！");
						refresh();
					}
				}
			});
		}
</script>
