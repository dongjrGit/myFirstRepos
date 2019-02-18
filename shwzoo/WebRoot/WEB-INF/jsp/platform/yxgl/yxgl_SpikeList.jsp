<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<span>活动编号：<input type="text" id="num" class="inp-seller" /></span> <span>
			活动类型： <select class="the-form-select-one" id="type_select">
				<option value="">全部</option>
				<option value="0">秒杀</option>
				<option value="1">闪购</option>
				<!--<option value="4">团购</option>  -->
		</select>
		</span> <span class="marrig10"></span> <span>开始时间：</span> <input type="text"
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
			class="inquire" name="btnsearch" type="button" value="查询"> <input
			class="inquire chaxun" name="btnadd" type="button" value="+添加活动">
	</div>

	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="spike_title">
				<th width="10%">编号</th>
				<th width="15%">名称</th>
				<th width="8%">类型</th>
				<th width="12%">开始时间</th>
				<th width="12%">结束时间</th>
				<th width="10%">状态</th>
				<th>操作</th>
			</tr>
			<tbody id="datalist">
				<script id="spikelist" type="text/html">
                    {{each list as spike i}}
                    <tr>
                        <td>{{spike.spikenum}}</td>
                        <td>{{spike.spikename}}</td>
                        <td>
                            {{if spike.spiketype==0}}
                                                          秒杀
                            {{else if spike.spiketype==1}}
                                                          闪购
                            {{/if}}
                        </td>
                        <td>{{spike.starttimestr}}</td>
                        <td>{{spike.endtimestr}}</td>
                        <td id="td_{{spike.id}}">
                            {{if spike.status==0}}
                            <span class="lvs"><a id="a_{{spike.id}}" href="javascript:void(0);" onclick="setStatus({{spike.id}},1)">启用</a></span>
                            {{else }}
                            <span class="lvs"><a id="a_{{spike.id}}" href="javascript:void(0);" onclick="setStatus({{spike.id}},0)">禁用</a></span>
                            {{/if}}
                        </td>
                        <td>
                            <a href="yxgl_SpikeEdit?id={{spike.id}}"><span class="shenlan">编辑</span></a>
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" class="del" data="{{spike.id}}"><span class="shenlan">删除</span></a>
                            <span class="marrig35"></span>
                            <a href="yxgl_SpikeSpuList?id={{spike.id}}"><span class="shenlan">商品列表</span></a>
                            <span class="marrig35"></span>
                            <a href="yxgl_SpikeShopList?id={{spike.id}}"><span class="shenlan">店铺申请列表</span></a>  


                                                                              
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
	src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/spikelist.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		spike.bind();
		spike.getlist(1);
	})
</script>