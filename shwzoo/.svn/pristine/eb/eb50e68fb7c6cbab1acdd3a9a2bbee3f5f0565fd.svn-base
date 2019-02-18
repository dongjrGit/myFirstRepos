<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/commonjs/jquery.form.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script src="${pageContext.request.contextPath}/resource/public/platform/js/navigation/navigation_edit.js"></script>
<script type="text/javascript">
    function backhref() {
        window.location.href = "list?";
    }
</script>

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">添加导航信息</a><span
					class="sj-img"></span></li>

			</ul>
		</div>
		<!--l_wzmbtop   stop -->

		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form">
							<input type="hidden" id="imgsrc" value="<%=path %>" />
							 <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>标题：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="title" name="title" type="text"
									value="${navigation.title }">
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>广告图片：</label>
							</div>
							<div class="tjcpxx-con-form">				
                                <div class="tjcpxx-con-form-upimg">
                                <c:choose>
								<c:when test="${navigation.id>0 }">
								<img id="loadimg" width="120px" height="115px"
										src="<%=path %>${navigation.img}" />
								</c:when>
								<c:otherwise>
									<img id="loadimg" width="120px" height="115px"
										src="" />
								</c:otherwise>
								</c:choose>
                                </div>
                                <input type="hidden" name="img" id="img" value="${navigation.img }" />
                                     <div style=" width:200px; float:right;">
                                        <input type="file" name="pics" id="singlefile" />
                                        <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a> 
                                    </div>
                             </div>
					   </div>
                        <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>链接：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="url" name="url" type="text"
									value="${navigation.url }">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>排序：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="sort" name="sort" type="text"
									value="${navigation.sort }">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>状态：</label>
							</div>
							<div class="tjcpxx-con-form">
									<c:choose>
									<c:when test="${navigation.status==1}">
                    			<input type="radio" id="radio_status" name="radio_status" value="1"  checked/>启用 
                    			<input type="radio"
									name="radio_status"  id="radio_status"  value="0"  />禁用
                    		</c:when>
                    		<c:otherwise>
                    			<input type="radio" id="radio_status" name="radio_status" value="1" />启用 
                    			<input type="radio"
									name="radio_status"  id="radio_status" checked="ischecked" value="0"  />禁用
                    		</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input type="hidden" id="hidden_navigationid" value="${navigation.id }" />
								<input class="preserve-inp" name="Save" type="button" value="保存" /> 
									 <input id="action" type="hidden" value="advertAdd" />
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
                    $("#img").val(result.data[0]); 
                    if (result.code == 0){
                    	Dalert("上传成功");                    	
                    	$("#loadimg").attr("src",$("#imgsrc").val()+result.data[0]);
                    }else
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