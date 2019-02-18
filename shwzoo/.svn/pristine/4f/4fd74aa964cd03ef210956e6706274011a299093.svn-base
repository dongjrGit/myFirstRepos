<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/lottery_rule_add.js"></script>



<div class="mainright">
	<!--l_wzmb  开始 -->
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">抽奖规则管理</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx-con-con">
			<form id="payForm" method="post">

				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label>抽奖规则名称：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" id="name" name="name" type="text"
							value="${lotteryDictionary.name }">
					</div>

				</div>
				<!--tjcpxx-con-mk stop -->
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label>抽奖规则描述：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" id="description" name="description"
							type="text" value="${lotteryDictionary.description }">
					</div>

				</div>
				<!--tjcpxx-con-mk stop -->
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label>抽奖概率：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" id="probability" name="probability"
							type="text" value="${lotteryDictionary.probability }">
					</div>

				</div>
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label>类型：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input type="hidden" id="h_mark" value="${lotteryDictionary.mark}" />
						<select id="mark" name="mark">
							<option value="1">代金卷奖</option>
							<option value="2">商品奖</option>
							<option value="3">积分奖</option>
							<option value="10">谢谢参与</option>
						</select>
					</div>
				</div>
				<!--tjcpxx-con-mk stop -->
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label>规则值：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" id="id_value" name="name_value"
							type="text" value="${lotteryDictionary.value }">
					</div>

				</div>
				<!--tjcpxx-con-mk stop -->

				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1"></div>
					<div class="tjcpxx-con-form1 huise" style="padding-top: 20px;">
						<input class="preserve-inp marrig35 mar35" name="savebtn"
							id="savebtn" type="button" value="保存"> <input
							class="preserve-inp_hs" name="backbtn" id="backbtn" type="button"
							value="返回">
					</div>
				</div>
				<!--tjcpxx-con-mk stop -->
			</form>
		</div>
		<!--tjcpxx-con-con stop -->
	</div>
	<!--l_wzmb  结束 -->
</div>
<!--mainright stop -->
<script type="text/javascript">
<!--
	$(function() {
		$("#mark").val($("#h_mark").val());
	});
//-->
</script>
