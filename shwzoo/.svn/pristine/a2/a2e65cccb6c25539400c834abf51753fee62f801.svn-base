<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">编辑商品</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form" method="post">
						<input id="id" name="spikeid" type="hidden"
							value="${data.spikeid }" /> <input name="id" type="hidden"
							value="${data.id }" /> <input id="spuid" name="spuid"
							type="hidden" value="${data.spuid }" />
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="select_spu" type="text" disabled
									class="tjcpxx-fprm-inp" value="${spuname }"
									data="${data.spuid }" />
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>优惠价格：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="price" class="tjcpxx-fprm-inp" name="price"
									type="text"
									value="<fmt:formatNumber value="${data.price}" pattern="0.00"/>">&nbsp;元
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品数量：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="count" class="tjcpxx-fprm-inp" name="count"
									type="text" value="${data.spucount}">
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>排序：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="orderby" class="tjcpxx-fprm-inp" name="orderby"
									type="text" value="${data.orderby}">
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>手机专享：</label>
							</div>
							<div id="divStatus" class="tjcpxx-con-form huise">
								<c:choose>
									<c:when test="${data.isphone==0 }">
										<input name='isphone' checked type='radio' value='0'>
										<span>否</span>
										<span class='marrig35'></span>
										<input name='isphone' type='radio' value='1'>
										<span>是</span>
									</c:when>
									<c:otherwise>
										<input name='isphone' type='radio' value='0'>
										<span>否</span>
										<span class='marrig35'></span>
										<input name='isphone' checked type='radio' value='1'>
										<span>是</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="Save" type="button" value="保存">
								<input id="action" type="hidden" value="updateSpikeSpu">
								<span class="marrig35"></span> <input class="preserve-inp_hs"
									name="" type="button" value="取消" onclick="formCancel()">
							</div>
						</div>


					</form>
				</div>
			</div>
		</div>
		<!--tjcpxx-con stop -->
	</div>
</div>
<script
	src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/spikeskuSave.js"></script>
<script>
	function formCancel() {
		location.href = "yxgl_SpikeSpuList?id=" + $("#id").val();
	}
</script>