<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/public/platform/js/product/spgl_flgl_tjfl.js">
	</script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">编辑商品分类</a><span
					class="sj-img"></span></li>

			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form" method="post">
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1">
								<label><span class="red marrig5">*</span>分类名称：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="name" value="${data.name}"
									type="text">
							</div>
							<input type="hidden" name="fatherid" value="${fid}" /> <input
								type="hidden" name="secondid" value="${sid}" /> <input
								type="hidden" name="id" value="${data.id}" />
								<input type="hidden" id="imgsrc" value="<%=path %>" />
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1">
								<label><span class="red marrig5">*</span>所属类别：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input type="hidden" value="${fid}" id="fid" /> <input
									type="hidden" value="${sid}" id="sid" /> <select
									name="firstID" id="firstID" disabled>
									<option value="0" id="defaultfc" selected>无</option>
									<script id="flist" type="text/html">
                                            {{each list as fclass i}}
                                            <option value="{{fclass.id}}">{{fclass.name}}</option>
                                            {{/each}}
                                        </script>
								</select><span>--></span> <select name="secondID" id="secondID" disabled>
									<option value="0" id="defaultsc" selected>无</option>
									<script id="slist" type="text/html">
                                            {{each list as sclass i}}
                                            <option value="{{sclass.id}}">{{sclass.name}}</option>
                                            {{/each}}
                                        </script>
								</select>

							</div>
						</div>
						<%-- <c:if test="${sid>0}"> --%>
						 <div class="tjcpxx-con-mk1" >
                            <div class="tjcpxx-con-form-title1"><label>分类图片：</label></div>
                            <div class="tjcpxx-con-form1">
                                <div class="tjcpxx-con-form-upimg">
                                    <img id="loadimg" width="120px" height="115px" src="<%=path %>${data.imageurl}" />
                                </div>
                                <input type="hidden" name="img" value="${data.imageurl}" />
                                    <div style=" width:200px;margin-left:10px; float:left;">
                                        <input type="file" id="singlefile" name="pics" />
                                        <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a> 
                                    </div>
                            </div>
                        </div>
					<%-- 	</c:if> --%>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1">
								<label><span class="red marrig5">*</span>预警数量：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="warnnum" type="text"
									value="${data.warnnum}">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1">
								<label><span class="red marrig5">*</span>排序：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="sort" type="text"
									value="${data.sort}" />
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1">
								<label><span class="red marrig5">*</span>虚拟商品：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<c:choose>
									<c:when test="${data.isvirtual==true}">
									 
										<input type="radio" name="isvirtual" value="false" />
										<span>否</span>
										  <input type="radio" name="isvirtual" value="true"
											checked="checked" />
										<span>是</span>
									</c:when>
									<c:otherwise>
										<input type="radio" name="isvirtual" value="false"
											checked="checked" />
										<span>否</span>
										<input type="radio" name="isvirtual" value="true" />
										<span>是</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1">
								<label><span class="red marrig5">*</span>可编辑：</label>
							</div>
						
											
							<div class="tjcpxx-con-form1">
								<c:choose>
									<c:when test="${data.iseditable==true}">
									    
										<input type="radio" name="iseditable" value="false" />
										<span>否</span>
										<input type="radio" name="iseditable" value="true" checked="checked" />
										<span>是</span>
									</c:when>
									<c:otherwise>
									<input type="radio" name="iseditable" value="false"
											checked="checked" />
										<span>否</span>
										<input type="radio" name="iseditable" value="true" />
										<span>是</span>

									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1"></div>
							<div class="tjcpxx-con-form1 huise">
								<input class="preserve-inp" name="commit" type="button"
									value="保存" onclick="formSubmit('updateCategory')"> <input
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
	$(document).ready(function() {
				Class.unit(Class.callback);
				if ($("input[name=order]").val() == ""|| $("input[name=order]").val() == undefined) {
					$("input[name=order]").val(1);
				}
		        $(".tjcpxx-con-form-upthis").click(function () {
		            $.ajaxFileUpload({
		            	url : "/app/api/img/upload",
						secureuri : false,
						fileElementId : 'singlefile',
						dataType : "json",
						//relationtype: 模块(分类(3))
						data : {
							relationtype : 3
						},
		                type: 'POST',
		                success: function (result) {
		                    $("input[name='img']").val(result.data); 
		                    if (result.code == 0){
		                    	Dalert("上传成功");		                    	
		                        $("#loadimg").attr("src", $("#imgsrc").val()+result.data[0]);
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
			});
</script>