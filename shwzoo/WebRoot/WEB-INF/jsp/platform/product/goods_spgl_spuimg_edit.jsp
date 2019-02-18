<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>

<script type="text/javascript">
	function formSubmit(imgaction) {
		//var ss = $("#form").submit()
		 $("input[name=btnsave]").hide();
		$.ajax({
			url : "/platform/spu/" + imgaction,
			data : $("#form").serialize(),
			type : "post",
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", backhref);
				} else {
					 $("input[name=btnsave]").show();
					Dalert(data.desc);
				}
			}
		});
	}
	function backhref() {
		window.location.href = "showSpuImg?spuid=" + $("#spuid").val();
	}
</script>

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">编辑商品图片信息</a><span
					class="sj-img"></span></li>

			</ul>
		</div>
		<!--l_wzmbtop   stop -->

		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form">
						<input type="hidden" name="id" value="${img.id }" /> <input
							type="hidden" id="spuid" name="spuid" value="${img.spuId }" />
							<input type="hidden" id="imgsrc" value="<%=path %>" />
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品图片：</label>
							</div>
							<div class="tjcpxx-con-form">
								<div class="tjcpxx-con-form-upimg">
								<c:choose>
								<c:when test="${img.id>0 }">
								<img id="loadimg" width="120px" height="115px"
										src="<%=path %>${img.imgurl}" />
								</c:when>
								<c:otherwise>
									<img id="loadimg" width="120px" height="115px"
										src="" />
								</c:otherwise>
								</c:choose>
									
								</div>
								<input type="hidden" name="img" value="${img.imgurl}" />
								<div style="width: 200px; float: right;">
									<input type="file" name="pics" id="singlefile" /> <a
										href="javascript:void(0);" style="color: #000"> <span
										class="tjcpxx-con-form-upthis">本地上传</span>
									</a>
								</div>
							</div>
						</div>

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>排序：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="orderby" name="orderby"
									type="text" value="${img.orderby }" />
							</div>
						</div>

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="btnsave" type="button" value="保存"
									onclick="formSubmit('${imgaction}')"> <input
									class="preserve-inp_hs" name="" type="button" value="取消"
									onclick="backhref()">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {
		var id = $("#id").val();
		$(".tjcpxx-con-form-upthis").click(function() {
			$.ajaxFileUpload({
				url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//ftype:上传文件类型（图片文件=1，其他文件=2）
				//relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他 (20);） 可以自由增加
				data : {
					relationtype : 2
				},
				type : 'post',
				success : function(result) {
					//  alert(JSON.stringify(result));
					//$(".url1").html(JSON.stringify(result));
					$("input[name='img']").val(result.data);
					if (result.code == 0){
						Dalert("上传成功");						
						$("#loadimg").attr("src",$("#imgsrc").val()+result.data[0]);
					}
					else
					{
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
		if ($("#orderby").val() == "" || $("#orderby").val() == undefined) {
			$("#orderby").val(1);
		}
	})
</script>
