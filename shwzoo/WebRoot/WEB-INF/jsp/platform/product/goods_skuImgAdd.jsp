<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>

<script type="text/javascript">
    function formSubmit(imgaction) {
        //var ss = $("#form").submit()
        $("input[name=btnsave]").hide();
        $.ajax({
            url: "/platform/sku/"+imgaction,
            data: $("#form").serialize(),
            type: "Post",
            dataType: "json",
            success: function (data) {
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
        window.location.href = "goods_skuImgList?skuid=" + $("#skuid").val();
    }
</script>

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">编辑库存商品图片信息</a><span
					class="sj-img"></span></li>

			</ul>
		</div>
		<!--l_wzmbtop   stop -->

		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form">
						<input type="hidden" name="id" value="${img.id }" /> <input
							type="hidden" id="skuid" name="skuid" value="${img.skuId }" />
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
                                     <div style=" width:200px; float:right;">
                                        <input type="file" name="pics" id="singlefile" />
                                        <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a> 
                                    </div>
                             </div>
					   </div>

						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>排序：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="orderby" name="orderby" type="text"
									value="${img.orderby }">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>图片使用站点：</label>
							</div>
							<div class="tjcpxx-con-form">
							<c:choose>
									<c:when test="${img.imgsite != null}">
										<c:choose>
											<c:when test="${fn:indexOf(img.imgsite,'1')>=0}">
												<input name="imgsite" type="checkbox" checked value="1">
												<span>pc</span>
											</c:when>
											<c:otherwise>
												<input name="imgsite" type="checkbox" value="1">
												<span>pc</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(img.imgsite,'2')>=0}">
												<input name="imgsite" type="checkbox" checked value="2">
												<span>app</span>
											</c:when>
											<c:otherwise>
												<input name="imgsite" type="checkbox" value="2">
												<span>app</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(img.imgsite,'3')>=0}">
												<input name="imgsite" type="checkbox" checked value="3">
												<span>wap</span>
											</c:when>
											<c:otherwise>
												<input name="imgsite" type="checkbox" value="3">
												<span>wap</span>
											</c:otherwise>
										</c:choose>									
									</c:when>
									<c:otherwise>
										<input name="imgsite" type="checkbox" value="1">
										<span>pc</span>
										<input name="imgsite" type="checkbox" value="2">
										<span>app</span>
										<input name="imgsite" type="checkbox" value="3">
										<span>wap</span>
									</c:otherwise>

								</c:choose>
									<%-- <c:choose>
									<c:when test="${img.isapp==1 }">
										<input name='isapp' checked type='radio' value='1'>
										<span>是</span>
										<span class='marrig35'></span>
										<input name='isapp' type='radio' value='0'>
										<span>否</span>
									</c:when>
									<c:otherwise>
										<input name='isapp' type='radio' value='1'>
										<span>是</span>
										<span class='marrig35'></span>
										<input name='isapp' checked type='radio' value='0'>
										<span>否</span>
									</c:otherwise>
								</c:choose> --%>
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk" style="display: none;">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>状态：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" type="text"
									value="${img.status }" disabled>
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->


						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="btnsave" type="button" value="保存"
									onclick="formSubmit('${imgaction}')"> <input
									class="preserve-inp_hs" name="" type="button" value="取消"
									onclick="backhref()">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
					</form>
				</div>
			</div>
			<!--tjcpxx-con stop -->
		</div>
		<!--tjcpxx stop -->
	</div>
</div>
<!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
    	var id = $("#id").val();
        $(".tjcpxx-con-form-upthis").click(function () {
            $.ajaxFileUpload({
            	url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//ftype:上传文件类型（图片文件=1，其他文件=2）
				//relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他 (20);） 可以自由增加
				data : {
					"relationtype" : 2,
					"type" : 0
				},
                type: 'POST',
                success: function (result) {
                    //  alert(JSON.stringify(result));
                    //$(".url1").html(JSON.stringify(result));
                    $("input[name='img']").val(result.data); 
                    if (result.code == 0){
                    	Dalert("上传成功");                    	
                    	$("#loadimg").attr("src",$("#imgsrc").val()+result.data[0]);
                    }
                    else{
                    	 $("#loadimg").attr("src", "");
                    	 Dalert("上传图片失败");
                    }
                       
                    //TODO 结束正在加载中
                },
                error:function(e){
                	 alert(JSON.stringify(e));
                }
            });
        });
        if ($("#orderby").val() == "" || $("#orderby").val() == undefined) {
            $("#orderby").val(1);
        }
    })
</script>