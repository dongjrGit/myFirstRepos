<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/public/platform/js/baner/edit.js"></script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">添加发现baner</a><span
					class="sj-img"></span></li>

			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form" method="post">
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>标题：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="title" type="text"
									value="${vo.title }">
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>链接：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="url" type="text"
									value="${vo.url }">
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1">
								<label>图片：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<div class="tjcpxx-con-form-upimg">
									<img id="loadimg" width="120px" height="115px"
										src="<%=path %>${vo.img}" />
								</div>
								<input type="hidden" name="img" value="${vo.img }" />
								<div
							style="width: 200px; float: left; position: relative; padding-left: 30px;">
							<input type="button" value="选择图片" class="h_scimgbut" /> <input
								type="file" id="singlefile" name="pics" 
								class="filemhbut" 
								style="top: 10px; left: 27px;" />
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
								<label>排序：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="sort" type="text"
									value="${vo.sort }">
							</div>
						</div>
						
						<div class="tjcpxx-con-mk1">
	                           <div class="tjcpxx-con-form-title"><label>使用平台：</label></div>
	                           <div class="tjcpxx-con-form1">
	                            <c:choose>
		                           <c:when test="${ctype1==1 }">
		                            	<input name="ctype1" type="checkbox" checked value="1"><span>pc端</span>
		                           </c:when>
		                           <c:otherwise>
		                           		<input name="ctype1" type="checkbox" value="1"><span>pc端</span>
		                           </c:otherwise>
	                           </c:choose>
	                            <span class='marrig35'></span>
	                            <c:choose>
		                           <c:when test="${ctype2==1 }">
		                            	<input name="ctype2" type="checkbox" checked value="1"><span>app端</span>
		                           </c:when>
		                           <c:otherwise>
		                           		<input name="ctype2" type="checkbox" value="1"><span>app端</span>
		                           </c:otherwise>
	                           </c:choose>
	                            <span class='marrig35'></span>
	                            <c:choose>
		                           <c:when test="${ctype3==1 }">
		                            	<input name="ctype3" type="checkbox" checked value="1"><span>wap端</span>
		                           </c:when>
		                           <c:otherwise>
		                           		<input name="ctype3" type="checkbox" value="1"><span>wap端</span>
		                           </c:otherwise>
	                           </c:choose>
	                            
	                            
	                           </div>
	                     </div>
						
						<!--tjcpxx-con-mk stop -->
						<span class="marrig35"></span><input type="hidden" id="type"
							name="type" value=${type } type="text">
							<span class="marrig35"></span><input type="hidden" id="id"
							name="id" value=${vo.id } type="text">
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="Save" type="button" value="保存">
								<input id="type_action" type="hidden" value="add"> <span
									class="marrig35"></span> <input class="preserve-inp_hs" name=""
									type="button" value="取消" onclick="formCancel()">
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
		$("#singlefile").change(x);
    	function x(){
    		var objUrl = getObjectURL(this.files[0]);
    		console.log("objUrl = " + objUrl);
    		if (objUrl) {
    			$("#loadimg").attr("src", objUrl);
    		} else {
    			$("#loadimg").attr("src", "");
    		}
    	};
    	//建立一个可存取到该file的url
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
				$(".h_scimgbut1").click(
						function() {
							$.ajaxFileUpload({
								url : "/app/api/img/upload",
								secureuri : false,
								fileElementId : 'singlefile',
								dataType : "json",
								//relationtype: 模块（品牌( 11)）
								data : {
									relationtype : 11
								},
								type : 'POST',
								success : function(result) {
									//  alert(JSON.stringify(result));
									//$(".url1").html(JSON.stringify(result));
									$("input[name='img']").val(result.data);
									if (result.code == 0){
										Dalert("上传成功");
										$("#loadimg").attr(
												"src",
												$("#imgsrc").val()
														+ result.data[0]);
									}else {
										$("#loadimg").attr("src", "");
										Dalert("上传图片失败");
									}
									//TODO 结束正在加载中
								},
								error : function(e) {
									alert(JSON.stringify(e));
								}
							});
						});
			})
	function formCancel() {
		var type = $("#type").val();
		location.href = "/platform/baner/list?type=" + type;
	}
</script>
