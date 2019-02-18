<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript"
	src="/resource/public/platform/js/shop/spgl_dpgl.js"></script>
<div class="mainright">
	<div class="clear"></div>
	<div class="account-form">
		<div class="account-form">
			<span>所属用户：<input id="select_user" type="text"
				class="inp-seller" /></span>
			<div>
				<ul>
					<script id="select_userlist" type="text/html">
                        {{each list as users i}}
                        <li data="{{users.id}}">{{users.username}}</li>
                        {{/each}}
                    </script>
				</ul>
			</div>
			<span class="marrig10"></span> <span>店铺名称：<input
				id="select_shopName" type="text" class="inp-seller" /></span>
			<div>
				<ul>
					<script id="select_shoplist" type="text/html">
                        {{each list as shop i}}
                        <li data="{{shop.id}}">{{shop.name}}</li>
                        {{/each}}
                    </script>
				</ul>
			</div>
			<span class="marrig10"></span> <span>店铺类型：
				<select id="shoptype" name="shoptype" class="the-form-select-one">
				                        <option value="-1" selected >全部</option>
				                        <option value="0">门票</option>
										<option value="1">马戏团票</option>
										<option value="2">游乐票</option>
										<option value="3">餐饮票</option>
										<option value="4">动物互动</option>
										<option value="5">主题商品</option>
										<!-- <option value="5">礼品卡票</option> -->
				</select>
				</span>
			<span class="marrig10"></span> <span>店铺编号：<input
				id="select_shopNum" type="text" class="inp-seller" /></span> <span
				class="marrig10"></span> <span class="notice-fenlei-mk2"><input
				class="chaxun" type="button" value="查询" id="select_input" /></span> <span
				class="marrig10"></span> <a href="${ctx }/platform/shop/shopEdit"
				target="_self"> <input class="chaxun" name="" type="button"
				value="+新增店铺">
			</a>
		</div>
	</div>

	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list" id="list_title">
			<thead>
				<tr>
					<th>店铺名称</th>
					<th>店铺编号</th>
					<th>店铺地址</th>
					<th>店铺类型</th>
					<th>联系人</th>
					<th>联系人手机</th>
					<th>客服电话</th>
					<th>状态</th>
					<th>添加时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<script id="slist" type="text/html">
                    {{each list as shop i}}
                    <tr>
                        <td>{{shop.name}}</td>
                        <td>{{shop.shopnum}}</td>
                        <td>{{shop.shopaddress}}</td>
                        <td>
                            {{if shop.shoptype==0}}<span>门票</span>
                            {{else if shop.shoptype==1}}<span>马戏团票</span>
                            {{else if shop.shoptype==2}}<span>游乐票</span>
                            {{else if shop.shoptype==3}}<span>餐饮票</span>
                            {{else if shop.shoptype==4}}<span>动物互动</span>
                            {{else if shop.shoptype==5}}<span>主题商品</span>
                            {{else}}
                            {{/if}}
                        </td>
                        <td>{{shop.contactname}}</td>
                        <td>{{shop.contactmobile}}</td>
                        <td>{{shop.supporttel}}</td>
                        <td id="td_status">
                            {{if shop.status==0}}<span class="lvs">审核中</span>
                            {{else if shop.status==1}}<span class="lvs">审核通过</span>
                            {{else if shop.status==2}}<span class="red">审核不通过</span>
                            {{else if shop.status==3}}<span class="red">违规</span>
                            {{else if shop.status==4}}<span class="lvs">营业中</span>
                            {{else}}<span class="red">打烊</span>
                            {{/if}}
                        </td>
                        <td>{{shop.creattime | toDateTime}}</td>
                        <td>
                            <a href="/platform/shop/shopEdit?shopid={{shop.id}}"><span class="shenlan">编辑</span></a>
                            {{if shop.status ==1}}
                            <a href="javascript:void(0);" name="changestate" data-id="{{shop.id}}" data-state="4"><span class="shenlan span_status">开张</span></a>
                            {{else if shop.status ==5}}
                            <a href="javascript:void(0);" name="changestate" data-id="{{shop.id}}" data-state="4"><span class="shenlan span_status">开张</span></a>
                            {{else if shop.status ==4}}
                            <a href="javascript:void(0);" name="changestate" data-id="{{shop.id}}" data-state="5"><span class="shenlan span_status">关闭</span></a>
                            {{/if}}
                            <a href="/platform/shop/showPwdUpdate?shopid={{shop.id}}"><span class="shenlan">修改密码</span></a>
                            <a href="/platform/shop/showImageList?shopid={{shop.id}}"><span class="shenlan">店内图片管理</span></a>
                            <a href="javascript:void(0);" onclick="Shop.del({{shop.id}})" ><span class="shenlan">删除</span></a>
                        </td>
                    </tr>
                    {{/each}}
                </script>
			</tbody>
		</table>
	</div>
	<!--table-con  stop -->

	<div class="clear"></div>
	<div class="page" id="pager"></div>
	<!--page stop -->


</div>
<!--mainright stop -->
<script type="text/javascript">
	//初始化
	$(function() {
		Shop.bind();
		Shop.getShopList(1, Shop.unit);

	});
	function SyncUp(id) {
		$.ajax(({
			type : "post",
			url : "/platform/shop/SyncUp",
			dataType : "json",
			async : false,
			data : {
				id : id
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert(rsl.desc);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {
				//Dalert(rsl.desc);
			}
		}));
	}
</script>