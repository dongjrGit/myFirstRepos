<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="/resource/public/seller/js/spgl/spgl_splb.js"></script>

<!--主要内容开始 -->
<div id="container">
	<div class="allcon">
		<div class="position">您所在的位置：商品管理 &gt; 标准商品管理 &gt; 商品列表</div>
		<!--所在位置信息  结束 -->
		<div class="the-form">
			<form>
				 <span><label>商品编号：</label><input id="select_pronum"
					type="text" class="text-inp"></span> <span><label>商品名称：</label><input
					id="select_proname" type="text" class="text-inp"></span> <span>
					<label>商品状态：</label> <select class="sel-form" id="status_select">
						<option value="">所有</option>
						<option value="3">已上架</option>
						<option value="4">已下架</option>
				</select>
				</span>
				<div class="clear"></div>
				<div class="submit-but">
					<input type="button" value="查询" id="select_button" class="but-comm">
					<a href="spgl_spuAdd"><input name="" type="button"
						value="添加商品" class="but-comm"></a>
				</div>
			</form>
		</div>
		<!--表单部分结束 -->
		<div class="clear"></div>
		<div class="thgl">
			<table class="data_list">
				<thead>
					<tr>
						<th>序号</th>
						<th>商品名称</th>
						<th>商品编码</th>
						<th>商品短标题</th>
						<th>商品状态</th>
						<th>价格（元）</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="list_title">
					<script id="spulist" type="text/html">
                        {{each list as spu i}}
                        <tr>
                            <td>{{i+1}}</td>
                            <td>{{spu.name}}</td>
                            <td>{{spu.proNum}}</td>
                            <td>{{spu.titleShort}}</td>                      
                            <td>
                                <div class="pos_rela">
                                    {{if spu.status==0}}未审核
                                    {{else if spu.status==1}}<span class="l_xsztdj" data-id="{{spu.id}}">提交审核中</span>
                                    {{else if spu.status==2 || spu.status==4}}<span class="l_xsztdj" data-id="{{spu.id}}">下架</span>
                                    {{else if spu.status==3}}<span class="l_xsztdj" data-id="{{spu.id}}">上架</span>
                                    {{else if spu.status==5}}<span class="l_xsztdj" data-id="{{spu.id}}">冻结</span>
                                    {{else if spu.status==6}}<span class="l_xsztdj" data-id="{{spu.id}}">解冻申请中</span>
                                    {{/if}}
                                    <div id="divrecord_{{spu.id}}" class="l_xsztcon" style="display:none;">
                                        <div class="l_xtb_xszt"></div>
                                        <div class="l_xszt">
                                            <ul></ul>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td>{{spu.price}}
                            </td>
                            <td>{{spu.createTime | toDateTime}}</td>    
                            <td>
                                <a href="spgl_spuImglist?spuid={{spu.id}}"><span>图片管理</span></a>
                                <a href="spgl_spuEdit?id={{spu.id}}"><span>编辑</span></a>
                                <a href="javascript:void(0);" class="del" data="{{spu.id}}"><span>删除</span></a>
								<a href="showSpuStock?spuid={{spu.id}}"><span>库存管理</span></a>
                            </td>
                        </tr>
                        {{/each}}
                    </script>
				</tbody>
			</table>
			<div class="clear"></div>
			<div id="pager" class="page"></div>
			<!--page stop -->
		</div>
	</div>
	<!--主要内容 右边结束 -->
</div>
<!--主要内容结束 -->
<script type="text/javascript">
	$(document).ready(function() {
		SPU.bind();
		SPU.getSPUList(1, SPU.unit);
	})
</script>