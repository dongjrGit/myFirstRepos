<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/public/seller/js/spgl/spgl_spfl.js"></script>

<!--主要内容开始 -->
<div id="container">
	<div class="allcon">
		<div class="position">您所在的位置：商品管理 &gt; 商品管理 &gt; 商品分类管理</div>
		<!--所在位置信息  结束 -->
		<div class="the-form">
			<span> <label>所属分类：</label> <select class="sel_allmost"
				id="fc_select" onchange="spfl.fatherChange('fc')">
					<script id="flist" type="text/html">
                        {{each list as fclass i}}
                        <option value="{{fclass.id}}">{{fclass.name}}</option>
                        {{/each}}
                    </script>
			</select>--> <select class="sel_allmost" id="sc_select"
				onchange="spfl.fatherChange('sc')">
					<script id="slist" type="text/html">
                        {{each list as sclass i}}
                        <option value="{{sclass.id}}">{{sclass.name}}</option>
                        {{/each}}
                    </script>
			</select>--> <select class="sel_allmost" id="tc_select">
					<script id="tlist" type="text/html">
                        {{each list as tclass i}}
                        <option value="{{tclass.id}}">{{tclass.name}}</option>
                        {{/each}}
                    </script>
			</select>
			</span> <span><label>名称：</label><input id="select_name" type="text"
				class="text-inp"></span> <span> <label>状态：</label> <select
				class="sel-form" id="status_select">
					<option value="-1">所有</option>
					<option value="0">未提交</option>
					<option value="1">审核中</option>
					<option value="2">审核通过</option>
					<option value="3">审核未通过</option>
			</select>
			</span>
			<div class="clear"></div>
			<div class="submit-but">
				<input type="button" value="查询" id="select_button" class="but-comm">
				<input name="spflAdd" type="button" value="商品分类添加" class="but-comm">
			</div>
			<!-- submit-but  stop-->
		</div>
		<div class="thgl">
			<table>
				<tr>
					<th width='30%'>分类名称</th>
					<th width='20%'>上级分类</th>
					<th width='20%'>状态</th>
					<th width='30%'>操作</th>
				</tr>
				<tbody id="datalist">
					<script id="customlist" type="text/html">
                {{each list as cus i}}
                <tr>
                    <td>{{cus.name}}</td>
                    <td>{{cus.fullname}}</td>
                    <td id="td_{{cus.id}}">
                    {{if cus.status==0}}<span>未提交</span>
                    {{else if cus.status==1}}<span>审核中</span>
                    {{else if cus.status==2}}<span>审核通过</span>
                    {{else if cus.status==3}}<span>审核不通过</span>
                    {{/if}}
                    </td>
                    <td id="cz_{{cus.id}}">
                       {{if cus.status==0}}
                       <a href="#" onclick="ChangeStatus({{cus.id}},1)"><span class="bjxx">提交</span></a>
                       <a href="spgl_customEdit?id={{cus.id}}"><span class="shenlan">编辑</span></a>
                       {{else if cus.status==1}}
                       <span class="bjxx">编辑</span> 
                       {{else}}
                       <a href="spgl_customEdit?id={{cus.id}}"><span class="shenlan">编辑</span></a>
                       {{/if}}                      
                        <span class="marrig35"></span>
                        <a href="javascript:void(0);" onclick="del({{cus.id}})"><span class="shenlan">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
				</tbody>
			</table>
		</div>
		<!--thgl 表格部分结束 -->

		<div class="clear"></div>
		<div id="pager" class="page"></div>
	</div>
	<!--主要内容 右边结束 -->
</div>
<!--主要内容结束 -->
<script>
	$(document).ready(function() {
		spfl.bind();
		spfl.getlist(1);
	})
</script>