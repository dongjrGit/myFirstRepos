<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<span>商品名称：<input type="text" id="num" class="inp-seller" />
		</span> 
		<span class="marrig10"></span> <span>开始时间：</span> <input type="text"
			id="starts" class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', maxDate: '#F{$dp.$D(\'starte\')}' })"
			value="" readonly="readonly" />- <input type="text" id="starte"
			class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', minDate: '#F{$dp.$D(\'starts\')}' })"
			value="" readonly="readonly" /> <span class="marrig10"></span> <span>结束时间：</span>
		<input type="text" id="ends" class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', maxDate: '#F{$dp.$D(\'ende\')}' })"
			value="" readonly="readonly" />- <input type="text" id="ende"
			class="Wdate"
			onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', minDate: '#F{$dp.$D(\'ends\')}' })"
			value="" readonly="readonly" /> <span class="marrig10"></span> <input
			class="inquire" name="btnsearch" type="button" value="查询"> 
			<input class="inquire chaxun" name="btnadd" type="button" value="+添加积分商品">
	</div>

	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="spike_title">
				<th>商品名称</th>
				<th width="10%">开始时间</th>
				<th width="10%">结束时间</th>
				<th width="25%">操作</th>
			</tr>
			<tbody id="datalist">
				<script id="spikelist" type="text/html">
                    {{each list as spike i}}
                    <tr>
                        <td>{{spike.name}}</td>
						<td>{{spike.endtimetr}}</td>
						<td>{{spike.starttimetr}}</td>
                        <td>
                            <a href="/platform/integra/editintepro?id={{spike.id}}"><span class="shenlan">编辑</span></a>
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" class="del" data="{{spike.id}}"><span class="shenlan">删除</span></a>
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
	src="${pageContext.request.contextPath }/resource/public/platform/js/integraproduct/list.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		spike.bind();
		spike.getlist(1);
	})
</script>