<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script
	src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/packagesave.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if ($("#orderby").val() == "" || $("#orderby").val() == undefined) {
			$("#orderby").val(1);
		}
	})
	function formCancel() {
		location.href = "yxgl_PackageList";
	}
</script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">编辑组合商品</a><span
					class="sj-img"></span></li>

			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">

			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form" method="post">
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>套餐名称：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input id="num" class="tjcpxx-fprm-inp" name="num" type="text"
									value="${data.num }"> <input type="hidden" name="id"
									value="${data.id }" />
							</div>
						</div>
						<!-- @*名称*@ -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>套餐名称：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input id="name" class="tjcpxx-fprm-inp" name="name" type="text"
									value="${data.name }">
							</div>
						</div>
						<!--  @*数量*@ -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>套餐数量：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input id="count" class="tjcpxx-fprm-inp" style="width: 150px;"
									name="count" type="text" value="${data.count }">
							</div>
						</div>

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>开始时间：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input type="text" name="starttime" id="start" class="Wdate2"
									onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'end\')}' })"
									value="${data.starttimestr }" readonly="readonly" />
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>结束时间：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input type="text" name="endtime" id="end" class="Wdate2"
									onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'start\')}' })"
									value="${data.endtimestr }" readonly="readonly" />
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>排序：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input id="orderby" class="tjcpxx-fprm-inp"
									style="width: 150px;" name="orderby" type="text"
									value="${data.orderby }">
							</div>
						</div>
						<div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><span class="red">*</span>使用平台：</div>
                            <div class="tjcpxx-con-form">
                              <c:choose>
                            <c:when test="${data.usesite != null}">
										<c:choose>
											<c:when test="${fn:indexOf(data.usesite,'1')>=0}">
												<input name="useplatform" type="checkbox" checked value="1">
												<span>pc端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="1">
												<span>pc端</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(data.usesite,'2')>=0}">
												<input name="useplatform" type="checkbox" checked value="2">
												<span>app端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="2">
												<span>app端</span>
											</c:otherwise>
										</c:choose>

									</c:when>
                            <c:otherwise>
                               <input name='useplatform' type='checkbox' value='1'><span>pc端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='2'><span>app端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='3'><span>wap端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='4'><span>微信端</span>
                            </c:otherwise>
                            </c:choose>
                              
                            </div>
                        </div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div id="divStatus" class="tjcpxx-con-form huise">
								<c:choose>
									<c:when test="${data.status==0 }">
										<input name='status' checked type='radio' value='0'>
										<span>启用</span>
										<span class='marrig35'></span>
										<input name='status' type='radio' value='1'>
										<span>禁用</span>
									</c:when>
									<c:otherwise>
										<input name='status' type='radio' value='0'>
										<span>启用</span>
										<span class='marrig35'></span>
										<input name='status' checked type='radio' value='1'>
										<span>禁用</span>
									</c:otherwise>
								</c:choose>

							</div>
						</div>

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="Save" type="button" value="保存">
								<input id="action" type="hidden" value="updatePackage">
								<span class="marrig35"></span> <input class="preserve-inp_hs"
									name="" type="button" value="取消" onclick="formCancel()">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
					</form>
				</div>
			</div>
		</div>
		<!--tjcpxx-con stop -->
	</div>
</div>