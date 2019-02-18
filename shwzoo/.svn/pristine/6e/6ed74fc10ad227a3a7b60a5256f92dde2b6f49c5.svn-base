<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script
	src="${pageContext.request.contextPath }/resource/public/seller/js/yxgl/couponlist.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/seller/js/yxgl/couponuser.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		coupon.getuserlist(1);
		couponuser.bind();
	})
</script>

<div class="container">
	<div class="allcon">
		<div class="position">您所在的位置：营销管理 &gt; 优惠劵&gt; 优惠劵用户列表</div>
		<div class="the-form">
			<label>优惠劵编号：<span class="red">${data.couponnum }</span></label> <label>优惠劵名称：<span
				class="red">${data.couponname }</span></label> <input type="hidden"
				id="couponid" value="${data.id }" /> <input class="but-comm"
				name="add" type="button" value="添加用户"> <a
				href="yxgl_CouponList" target="_self"><input class="but-comm"
				name="" type="button" value="返回优惠劵列表"></a>
		</div>

		<div class="clear"></div>
		<div class="mar35"></div>
		<div class="thgl" style="position: relative;">
			<table class="data_list">
				<tr>
					<th width="10%">用户名称</th>
					<th width="15%">邮箱</th>
					<th width="10%">手机号</th>
					<th width="15%">领用时间</th>
					<th width="8%">是否使用</th>
					<th width="15%">使用时间</th>
					<th>使用说明</th>
				</tr>
				<tbody id="datalist">
					<script id="couponlist" type="text/html">
                        {{each list as coupon i}}
                        <tr>
                           <td>{{coupon.username}}</td>
                        <td>{{coupon.email}}</td>
                        <td>{{coupon.mobile}}</td>
                        <td>{{coupon.gettimestr}}</td>
                        <td>
                            {{if coupon.isuser==0}}
                            未使用
                            {{else }}
                            已使用
                            {{/if}}
                        </td>
                        <td>{{coupon.usetimestr}}</td>
                        <td>{{coupon.userdesc}}</td>
                        </tr>
                        {{/each}}
                    </script>
				</tbody>
			</table>
			<div id="adduser" class="xgyfxxgz"
				style="height: 580px; display: none; top: 45px;">
				<div class="xgyfxxgz-top" style="background: #DDDDDD;">
					选择用户<span class="xgyfxxgz-close">X</span>
				</div>
				<!-- xgyfxxgz-top  stop-->
				<div class="xgyfxxgz-con" style="padding: 10px 30px 20px 30px;">
					<div class="the-form">
						<span>用户名：<input type="text" id="name" class="text-inp" /></span>
						<span class="marrig10"></span> 
						<input type="hidden" id="couponid" value="${data.id }" /> 
						<input class="but-comm search" name="" type="button" value="查询"> 
						<input class="but-comm add" name="" type="button" value="添加">
					</div>

					<div class="clear"></div>
					<div class="mar35"></div>
					<div class="thgl">
						<table class="data_list">
							<tr id="memberlist_title">
								<th width="10%"><input name="chkall" type="checkbox"
									value=""></th>
								<th width="20%">用户名</th>
								<th width="20%">邮箱</th>
								<th width="15%">手机</th>
								<th>出生日期</th>
							</tr>
							<tbody id="memberlist">
								<script id="userlist" type="text/html">
                                    {{each list as user i}}
                                    <tr>
                                       <td><input id="chk_{{user.id}}" name="chksel" type="checkbox" value="{{user.id}}"></td>
                                    <td>{{user.username}}</td>
                                    <td>{{user.email}}</td>
                                    <td>{{user.mobile}}</td>
                                    <td>{{user.birthdaystr}}</td>
                                    </tr>
                                    {{/each}}
                                </script>
							</tbody>
						</table>
					</div>
					<div class="clear"></div>
					<div id="pager1" class="page"></div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
		<div id="pager" class="page"></div>
	</div>
</div>
