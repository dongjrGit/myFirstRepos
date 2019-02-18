<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/public/platform/js/logistics/logistics.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/public/commonjs/jquery.form.js"></script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0)">物流信息</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx-con-con">
			<form id="form1" action="#" method="post">
				<input class="tjcpxx-fprm-inp" name="id" id="text_id"
					value="${dto.id}" type="hidden">
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label><span class="red marrig5">*</span>名称：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" name="name" id="text_name"
							value="${dto.name}" type="text">
					</div>
				</div>
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label><span class="red marrig5">*</span>编号：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" name="code" id="text_code"
							value="${dto.code}" type="text">
					</div>
				</div>
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label>排序：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" name="sort" id="text_sort"
							value="${dto.sort}" type="text">
					</div>
				</div>

				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1"></div>
					<div class="tjcpxx-con-form1 huise" style="padding-top: 20px;">
						<input class="preserve-inp marrig35 mar35" name="submit_ok"
							id="submit_ok" type="button" value="保存"> <input
							class="preserve-inp_hs" name="btn_goback" id="btn_goback"
							type="button" value="返回">
					</div>
				</div>
				<!--tjcpxx-con-mk stop -->
			</form>
		</div>
	</div>
	<!--tjcpxx stop -->
</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {
		//表单验证
		$("#submit_ok").bind("click",save);
		//返回按钮点击
		$("#btn_goback").bind("click", function() {
			window.location.href = "/platform/logistics/list";
		});
		if ($("#text_sort").val() == "" || $("#text_sort").val() == undefined) {
            $("#text_sort").val(1);
        }
	})
</script>