<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/zooMap/zooMapedit.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/zooCommon.js"></script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">添加</a>
					<span class="sj-img"></span>
				</li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form" method="post">
						<input type="hidden" id="ossObject">
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>名称：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" id="name" maxlength="20" type="text" value="${bean.remark}">
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1" id="div_image">
								<label><span class="red marrig5">*</span>图片：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<div class="tjcpxx-con-form-upimg">
									<img id="loadimg" width="120px" height="115px"
										src="<%=path %>${vo.img}" alt="请上传图片" />
								</div>
								<input type="hidden" name="img"  />
								<div style="width: 200px; float: left; position: relative; padding-left: 30px;">
									<input type="button" value="选择图片" class="h_scimgbut" id="buttonid" />
									<input type="file" id="singlefile" name="pics" class="filemhbut" onchange="ZooMapEdit.changeFile(this);" style="top: 10px; left: 27px;" />
									<div>
										<input type="button" value="本地上传" class="h_scimgbut h_scimgbut1" />
									</div>
								</div>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1"></div>
							<div class="tjcpxx-con-form1" style="position: relative;">
								<label for="selectimg" class="error"></label> <span
									class="beizhu-mc-upimg">上传图片要小于500kb</span>
							</div>
						</div>
						<%-- <div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>排序：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" id="sort"  type="number" min="0" value="${bean.sort}">
							</div>
						</div> --%>
						<!--tjcpxx-con-mk stop -->
						<span class="marrig35"></span>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="Save" type="button" value="保存">
								<span class="marrig35"></span> 
								<input class="preserve-inp_hs" type="button" value="取消" onclick="ZooMapEdit.formCancel()">
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


<script type="text/javascript">
	var type='${type}';;
	var state='${state}';
	var id='${bean.id}';
</script>
