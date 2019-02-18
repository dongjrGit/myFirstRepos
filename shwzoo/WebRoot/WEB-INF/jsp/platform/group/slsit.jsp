<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="/resource/public/platform/js/group/slsit.js"
	type="text/javascript"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<span>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：<input
			type="text" id="title" class="inp-seller" />
		</span><span class="marrig10"></span> <span>所属店铺：<input type="text"
			id="sname" class="inp-seller" />
		</span> <span class="marrig10"></span> <span>分类：<select
			id="text_class" name="text_class" class="tjcpxx-fprm-inp"
			style="width: 120px">
				<option value="-1">全部</option>
				<c:forEach var="cls" items="${clslist}">
					<option value="${cls.id}">${cls.name}</option>
				</c:forEach>
		</select>
		</span> <span> 状态： <select class="the-form-select-one"
			id="type_select" style="width: 80px">
				<option value="">全部</option>
				<option value="1">下架</option>
				<option value="0">上架</option>
		</select>
		</span> <span class="marrig10"></span><span> 审核状态： <select
			class="the-form-select-one" id="aud_select">
				<option value="">全部</option>
				<option value="0">通过</option>
				<option value="1">未审核</option>
				<option value="2">未通过</option>
		</select> </span> <br/>
		<span>开始时间：</span> <input type="text" id="starts" class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'starte\')}' })"
			value="" readonly="readonly" />- <input type="text" id="starte"
			class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'starts\')}' })"
			value="" readonly="readonly" /> <span class="marrig10"></span> <span
			class="marrig10"></span> <span>结束时间：</span> <input type="text" id="ends" class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'ende\')}' })"
			value="" readonly="readonly" />- <input type="text" id="ende"
			class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'ends\')}' })"
			value="" readonly="readonly" /> <span class="marrig10"></span> <input
			class="inquire" name="btnsearch" type="button" value="查询">
	</div>

	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="buy_title">
				<th width="35%">标题</th>
				<th width="6%">所属店铺</th>
				<th width="8%">原价</th>
				<th width="8%">现价</th>
				<th width="6%">参与人数</th>
				<th width="6%">已售数量</th>
				<th width="6%">付款数量</th>
				<th width="4%">状态</th>
				<th width="4%">审核</th>
				<th>操作</th>
			</tr>
			<tbody id="datalist">
				<script id="tdatalist" type="text/html">
                    {{each list as pro i}}
                    <tr>
                        <td>{{pro.title}}</td> 
						<td>{{pro.sname }}</td>
                        <td>{{pro.yprice | toFixed}}</td>
                        <td>{{pro.price | toFixed}}</td>
                        <td>{{pro.num }}</td>  
  						<td>{{pro.ynum }}</td>
  						<td>{{pro.pnum }}</td>
  					   {{if pro.state==0}}
                    <td id="td_{{pro.id}}">
                        <span>上架</span>
                    </td>
                    {{else }}
                    <td id="td_{{pro.id}}">
                        <span>下架</span>
                    </td>
                    {{/if}}

					{{if pro.auditing==0}}
                    <td id="td_{{pro.id}}">
                        <span>通过</span>
                    </td>
                    {{else if pro.auditing==1}}
                    <td id="td_{{pro.id}}">
                        <span>未审核</span>
                    </td>
					{{else}}
					<td id="td_{{pro.id}}">
                        <span>未通过</span>
                    </td>
                    {{/if}}
                        <td>
                                <a href="/platform/group/slsitedit?id={{pro.id}}" title="查看"><span class="shenlan">查看</span></a>
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
	$(document).ready(function() {
		data.bind(1);
		$(".inquire").bind("click", function() {
			data.bind(1);
		});
	});
	function del(id) {
		if (!confirm("确定删除吗?")) {
			return;
		}
		$.ajax({
			url : "/platform/dgroup/del",
			type : "post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					Dalert("删除成功！");
					refresh();
				}
			}
		});
	}
</script>
