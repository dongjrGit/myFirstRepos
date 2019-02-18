<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<style type="text/css">
/* 半透明的遮罩层 */
#overlay {
    background: #000;
    filter: alpha(opacity=50); /* IE的透明度 */
    opacity: 0.5;  /* 透明度 */
    display: none;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    height: 100%;
    z-index: 100; /* 此处的图层要大于页面 */
    display:none;
}
</style>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/scenic/test.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/scenic/uploadZip.js"></script>
<div id="overlay"></div>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">添加导航地图</a>
					<span class="sj-img"></span>
				</li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form" method="post"  action="/zoo/zooMap/save">
						<input type="hidden" name="path">
						<input type="hidden" name="url">
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>名称：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="name"  maxlength="20" type="text">
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1" id="div_image">
								<label><span class="red marrig5">*</span>地图ZIP包：</label>
							</div>
							<div class="tjcpxx-con-form1">
									<input  class="tjcpxx-fprm-inp" id="zipFile"  type="file"/>
							</div>
						</div>
						<span class="marrig35"></span>
						<input type="hidden" name="type" value="${type}">
						<input type="hidden" name="state" value="${state}">
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" type="button" id="saveButton" value="保存">
								<span class="marrig35"></span> 
								<input class="preserve-inp_hs" type="button" value="取消" id="formCancel">
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
$(document).ready(function(){
	var msg = '${msg}';
	if(msg){
		alert(msg);
	}

	//jQuery提交  
	$("#saveButton").click(function(){  
		if ($("input[name=name]").val().trim() == "") {
			Dalert("名称不能为空！");
			return false;
		}
		// 判断景点图片是否上传
		if ($("#zipFile").val() == '') {
			Dalert("请上传ZIP包！");
			return false;
		}else{
			var fileName = $("#zipFile").val();
			var ext  = fileName.substring(fileName.lastIndexOf(".")+1);
			if(ext !="zip"){
				Dalert("文件格式不正确，请上传ZIP包！");
				return;
			}
			zooOver.showOverlay();
			uploadZip.upload({
				relationtype:50,
                fileElementId: 'zipFile',
                success: function(result) {
                	if (result.code == 0) {
                        $("input[name='url']").val(result.data);
                        $("input[name='path']").val(result.ossObject);
                        $("#form").submit();  
                    }else{
                    	Dalert(result.desc);
                    }
                },
                complete: function() {
                	zooOver.showOverlay();
                }
            })
		}
	});
    $("#formCancel").click(function(){
    	window.location.href = "/zoo/zooMap/list?type=" + '${type}' + "&state=" + '${state}';
    });
});



</script>
