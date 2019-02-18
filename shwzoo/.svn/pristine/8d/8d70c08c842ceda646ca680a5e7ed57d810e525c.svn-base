<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/resource/public/platform/js/group/group.js" type="text/javascript"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<span>标题：<input type="text" id="" class="inp-seller" />
		</span> 
		<span			class="marrig10"></span> 
			<span>分类：<select id="text_class" name="text_class" class="tjcpxx-fprm-inp"
						
							style="width: 168px">
							<option value="-1">全部</option>
							<c:forEach var="cls" items="${clslist}">
										<option value="${cls.id}">${cls.name}</option>
							</c:forEach>
						</select>
		</span> 
		<span>
			状态： <select class="the-form-select-one" id="type_select">
				<option value="">全部</option>
				<option value="0">下架</option>
				<option value="1">上架</option>
		</select>
		</span> 
		<span			class="marrig10"></span>   
			<span>开始时间：</span>
			 <input type="text"
			id="starts" class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'starte\')}' })"
			value="" readonly="readonly" />- <input type="text" id="starte"
			class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'starts\')}' })"
			value="" readonly="readonly" /> <span class="marrig10"></span></br> <span>结束时间：</span>
		<input type="text" id="ends" class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'ende\')}' })"
			value="" readonly="readonly" />- <input type="text" id="ende"
			class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'ends\')}' })"
			value="" readonly="readonly" /> <span class="marrig10"></span> <input
			class="inquire" name="btnsearch" type="button" value="查询"> <input
			class="inquire add" name="btnadd" type="button" value="+添加团购">
	</div>

	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="buy_title">
				<th width="35%">标题</th>
				<th width="8%">原价</th>
				<th width="8%">现价</th>
				<th width="6%">参与人数</th>
				<th width="6%">已售数量</th>
				<th width="6%">付款数量</th>
				<th width="4%">状态</th>
				<th>操作</th>
			</tr>
			<tbody id="datalist">
				<script id="tdatalist" type="text/html">
                    {{each list as pro i}}
                    <tr>
                        <td>{{pro.title}}</td> 
                        <td>{{pro.yprice | toFixed}}</td>
                        <td>{{pro.price | toFixed}}</td>
                        <td>{{pro.num }}</td>  
  						<td>{{pro.ynum }}</td>
  						<td>{{pro.pnum }}</td>
  					   {{if pro.state==0}}
                    <td id="td_{{pro.id}}">
                        <span class="lvs"><a data-id="{{pro.id}}" data-status="1" onclick="setstate({{pro.id}},1)" class="set" href="javascript:void(0);">上架</a></span>
                    </td>
                    {{else }}
                    <td id="td_{{pro.id}}">
                        <span class="lvs"><a data-id="{{pro.id}}" data-status="0" onclick="setstate({{pro.id}},0)" class="set" href="javascript:void(0);">下架</a></span>
                    </td>
                    {{/if}}
                        <td>
                                <a href="/platform/group/edit?id={{pro.id}}" title="修改"><span class="shenlan">修改</span></a>
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
				window.location.href = "/platform/group/edit";
			});
			$(".inquire").bind("click",function(){
				data.bind(1);
			});
			
		});
		function setstate(id,val)
		{
			$.ajax({
				url:"/platform/dgroup/cstate",
				type:"post",
				data:{"id":id,"state":val},
				dataType:"json",
				success:function(data){
					if(data.code<0){
						Dalert(data.desc);
					}else{
						Dalert("保存成功！");
						if(val==0){
							$("#td_"+id).html( ' <span class="lvs"><a data-id="'+id+'" data-status="1" onclick="setstate('+id+',1)" class="set" href="javascript:void(0);">上架</a></span>');
						}else {
							$("#td_"+id).html( ' <span class="lvs"><a data-id="'+id+'" data-status="1" onclick="setstate('+id+',0)" class="set" href="javascript:void(0);">下架</a></span>');
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
				url:"/platform/dgroup/del",
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
