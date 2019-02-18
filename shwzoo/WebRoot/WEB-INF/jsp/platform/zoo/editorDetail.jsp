<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="${ctx}/resource/public/platform/js/zoo/kindeditor-4.1.11/kindeditor-all-min.js"></script>
<script src="${ctx}/resource/public/platform/js/zoo/kindeditor-4.1.11/lang/zh-CN.js"></script>
<script src="${ctx}/resource/public/platform/js/zoo/editor/editorDetail.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/zooCommon.js"></script>
<script type="text/javascript">
var  contEditor;
var flag = true;
KindEditor.ready(function(K) {
	contEditor = K.create('#content', {
		items : ['source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
					'paste', 'plainpaste', 'wordpaste', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', 'fullscreen', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', 'image', 'multiimage','table', 'hr'
				], 
		minHeight : '300', minWidth : '800',
		//allowImageRemote:false,
		//uploadJson :"/app/api/img/upload?relationtype=50&iskdr=1"
		uploadJson:"/zoo/image/upload?relationtype=50&iskdr=1"
	})
});
</script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">图文信息</a><span class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form" method="post">
						<input type="hidden" value="${type}" id="type" />
						<input type="hidden" value="${bean.id }" id="id" name="id" /> 
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>标题：</label>
							</div>
							<div class="tjcpxx-con-form1">
							
								<input class="tjcpxx-fprm-inp" style="width:365px;" id="title" maxlength="30" onblur="value=value.trim()" type="text" value="<c:out value='${bean.title}'></c:out>">
							</div>
						</div>
						
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>详情：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<textarea name="content" id="content" style="height: 300px; visibility: hidden">${bean.content }</textarea>
							</div>
						</div>
						<div class="tjcpxx-con-mk1" style="height: 15px;">
						</div>
						
						<span class="marrig35"></span>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="Save" type="button" value="保存">
								<span class="marrig35"></span> 
								<input class="preserve-inp_hs" name="" type="button" value="取消" onclick="formCancel()">
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
$(document).ready(function() {
	
});

function formCancel() {
	var type = $("#type").val();
    location.href = "/zoo/editor/list?type="+type;
}
</script>
