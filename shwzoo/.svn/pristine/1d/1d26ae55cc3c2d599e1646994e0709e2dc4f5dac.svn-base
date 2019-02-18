<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/resource/public/platform/js/Highspecialty/hispe.js" type="text/javascript"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<span>特产名称：<input type="text" id="name" class="inp-seller" /></span> 
		<span>
			状态： <select class="the-form-select-one" id="type_select">
				<option value="">全部</option>
				<option value="0">普通</option>
				<option value="1">实时推荐</option>
		</select>
		</span> 
		<span class="marrig10"></span>
		 <input class="inquire" name="btnsearch" type="button" value="查询"> 
		 <input class="inquire add" name="btnadd" type="button" value="+添加优质特产">
	</div>

	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="buy_title">
				<th>特产名称</th>
				<th width="8%">售价</th>
				<th width="8%">APP售价</th>
				<th width="8%">状态</th>
				<th width="25%">操作</th>
			</tr>
			<tbody id="datalist">
				<script id="tdatalist" type="text/html">
                    {{each list as pro i}}
                    <tr>
                        <td>{{pro.name}}</td> 
                        <td>{{pro.price | toFixed}}</td>
                        <td>{{pro.appprice | toFixed}}</td>
  					   {{if pro.type==0}}
                    <td id="td_{{pro.id}}">
                        <span class="lvs"><a data-id="{{pro.id}}" data-status="1" onclick="setstate({{pro.id}},1)" class="set" href="javascript:void(0);">普通</a></span>
                    </td>
                    {{else }}
                    <td id="td_{{pro.id}}">
                        <span class="lvs"><a data-id="{{pro.id}}" data-status="0" onclick="setstate({{pro.id}},0)" class="set" href="javascript:void(0);">实时推荐</a></span>
                    </td>
                    {{/if}}
                        <td>
                                <a href="/platform/yztc/edit?id={{pro.id}}" title="修改"><span class="shenlan">修改</span></a>
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
			check();data.bind(1);		
			$(".add").bind("click",function(){
				window.location.href = "/platform/yztc/edit";
			});
			$(".inquire").bind("click",function(){
				data.bind(1);
			});
			
		});
		function setstate(id,val)
		{
			$.ajax({
				url:"/platform/hispe/setstate",
				type:"post",
				data:{"id":id,"state":val},
				dataType:"json",
				success:function(data){
					if(data.code<0){
						Dalert(data.desc);
					}else{
						Dalert("保存成功！");
						if(val==0){
							$("#td_"+id).html( ' <span class="lvs"><a data-id="'+id+'" data-status="1" onclick="setstate('+id+',1)" class="set" href="javascript:void(0);">普通</a></span>');
						}else {
							$("#td_"+id).html( ' <span class="lvs"><a data-id="'+id+'" data-status="1" onclick="setstate('+id+',0)" class="set" href="javascript:void(0);">实时推荐</a></span>');
						}
					}
				 }
				});
		}
		function del(id){
			if(!confirm("确定删除吗?")){
				return;
			}
			$.ajax({
				url:"/platform/hispe/delById",
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
