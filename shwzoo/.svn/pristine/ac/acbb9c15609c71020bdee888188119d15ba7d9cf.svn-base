<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/rcmdScenic/rcmdScenicedit.js"></script>
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
					<input type="hidden" value="${type}" id="type"/>
					<input type="hidden" value="${bean.id}" id="id"/>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<c:choose>
									<c:when test="${type==1}">
										<label><span class="red marrig5">*</span>剧场名称：</label>
									</c:when>
									<c:when test="${type==2}">
										<label><span class="red marrig5">*</span>景点名称：</label>
									</c:when>
									<c:when test="${type==3}">
										<label><span class="red marrig5">*</span>设施名称：</label>
									</c:when>
									<c:when test="${type==4}">
										<label><span class="red marrig5">*</span>动物互动名称：</label>
									</c:when>
								</c:choose>
							</div>
							<ad>
									<input class="inp-seller" data="${bean.scenicid}" type="text" value="${bean.scenicname}" id="scenicid" />
							</ad>
							<div  style="margin-left: 12px;">
								<ul>
									<script id="select_sceniclist" type="text/html">
                                        	{{each list as item i}}
                                        	<li data="{{item.id}}">{{item.scenicname}}</li>
                                        	{{/each}}
                                    </script>
								</ul>
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
									<input type="file" id="singlefile" name="pics" class="filemhbut" onchange="changeFile(this);" style="top: 10px; left: 27px;" />
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
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>备注：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" id="remark" maxlength="20" type="text" value=<c:out value="${bean.remark}"></c:out> >
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<span class="marrig35"></span>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="Save" type="button" value="保存">
								<span class="marrig35"></span> 
								<input class="preserve-inp_hs" type="button" value="取消" onclick="formCancel()">
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
	Init.bind();
	if ($('#id').val()) {
		$("#loadimg").attr("src", '${bean.img}');
		$("input[name='img']").val('${bean.img}');
	}
});
function formCancel() {
	var type = $("#type").val();
	location.href = "/zoo/rcmdScenic/list?type=" + type;
}

function changeFile(object) {
	var agent = navigator.userAgent.toLowerCase();
	if (agent.indexOf("msie") > 0) {
		var version = agent.match(/msie [\d.]+;/gi)[0];
		if (version == 'msie 9.0;') {
			$("#loadimg").attr("src", "");
			object.select();
			// $("#id").blur();
			$('#div_image').focus();
			var nfile = document.selection.createRange().text;
			// alert("当前选择的文件完整路径是:"+nfile);
			document.selection.empty();
			document.getElementById("loadimg").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + nfile + "')";
		} else {
			var objUrl = getObjectURL(object.files.item(0));
			if (objUrl) {
				$("#loadimg").attr("src", objUrl);
			} else {
				$("#loadimg").attr("src", "");
			}
		}
	} else {
		var objUrl = getObjectURL(object.files.item(0));
		if (objUrl) {
			$("#loadimg").attr("src", objUrl);
		} else {
			$("#loadimg").attr("src", "");
		}
	}
}

// 建立一个可存取到该file的url
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}
$(".h_scimgbut1").click(function() {
	if ($("#singlefile").val()) {
		$.ajaxFileUpload({
			url : "/app/api/img/upload",
			secureuri : false,
			fileElementId : 'singlefile',
			dataType : "json",
			// relationtype: 模块（品牌( 11)）
			data : {
				relationtype : 50
			},
			type : 'POST',
			success : function(result) {
				if (parseInt(result.desc) >= 512000) {
					Dalert("图片大小不能超过500kb");
				} else {
					if (result.data != "") {
						if (result.code == 0) {
							Dalert("上传成功");
							$("#loadimg").attr("src", result.data[0]);
							$("input[name='img']").val(result.data);
						} else {
							$("#loadimg").attr("src", "");
							Dalert("上传图片失败");
						}
					}
				}
			},
			error : function(e) {
				alert(JSON.stringify(e));
			},
			complete:function(){
    			
    		}
		});
		$("#singlefile").remove();
		var input = '<input type="file" id="singlefile" name="pics" class="filemhbut"  onchange="changeFile(this);"  style="top: 10px; left: 27px;" />';
		$("#buttonid").after(input);
	}
});
</script>
