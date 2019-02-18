<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"
	src="/resource/ajaxfileupload.js"></script>
<script src="/resource/public/platform/js/shop/shopimg.js"></script>
<script type="text/javascript">
    function backhref() {
        window.location.href = "showImageList?shopid=" + $("#shopid").val();
    }
</script> 

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">编辑店铺图片信息</a><span
					class="sj-img"></span></li>

			</ul>
		</div>
		<!--l_wzmbtop   stop -->

		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form">
						<input type="hidden" name="id" value="${data.id }" /> 
							<input
							type="hidden" id="shopid" name="shopid" value="${shopid }" />
							<input type="hidden" id="imgsrc" value="<%=path %>" />
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>店铺图片：</label>
							</div>
							<div class="tjcpxx-con-form">				
                                <div class="tjcpxx-con-form-upimg">
                                <c:choose>
								<c:when test="${data.id>0 }">
								<img id="loadimg" width="120px" height="115px"
										src="<%=path %>${data.imgurl}" />
								</c:when>
								<c:otherwise>
									<img id="loadimg" width="120px" height="115px"
										src="" />
								</c:otherwise>
								</c:choose>
                                </div>
                                <input type="hidden" name="img" value="${data.imgurl}" />
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
								<input class="tjcpxx-fprm-inp" id="sort" name="sort" type="text"
									value="${data.sort }">
							</div>
						</div>
						<%-- <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>链接地址：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="url" name="url" type="text"
									value="${data.url }">
							</div>
						</div> --%>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>状态：</label>
							</div>
							<div class="tjcpxx-con-form">
									<c:choose>
									<c:when test="${data.status==null }">
									    <input name="status" checked type='radio' value='0'>
										<span>启用</span>
										<span class='marrig35'></span>
										<input name='status' type='radio' value='1'>
										<span>禁用</span>
									</c:when>
									<c:when test="${data.status==0 }">
										<input name="status" checked type='radio' value='0'>
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
								<input class="preserve-inp" name="Save" type="button" value="保存" /> 
									 <input id="action" type="hidden" value="${action}" />
									<input class="preserve-inp_hs" name="" type="button" value="取消"
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
					relationtype : 1
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
                    else
                        $("#loadimg").attr("src", "");
                    //TODO 结束正在加载中
                },
                error:function(e){
                	 alert(JSON.stringify(e));
                }
            });
        });
        if ($("#sort").val() == "" || $("#sort").val() == undefined) {
            $("#sort").val(1);
        }
    })
</script>