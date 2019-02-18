<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">添加活动</a><span
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
								<label><span class="red marrig5">*</span>活动名称：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="sname" class="tjcpxx-fprm-inp" name="sname"
									type="text" value=""> <input type="hidden" id="imgsrc"
									value="<%=path%>" />
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>秒杀类型：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<select name="stype" id="stype" class="sel-form"
									onchange="typeChange()">
								   <option value="0" selected>秒杀</option> 
									<option value="1">闪购</option>
									<!-- <option value="4">团购</option> -->
								</select>
							</div>
						</div>
						<div id="divclass" class="tjcpxx-con-mk" style="display: none;">
							<div class="tjcpxx-con-form-title">
								<label>团购分类：</label>
							</div>
							<div class="tjcpxx-con-form">
								<select id="tgclass" name="tgclass" class="sel-form">
									<option value="-1">全部</option>
									<c:forEach var="cls" items="${clslist}">
										<c:choose>
											<c:when test="${cls.id == gbinfo.cid}">
												<option selected="selected" value="${cls.id}">${cls.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${cls.id}">${cls.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="divimg" class="tjcpxx-con-mk" style="display: none;">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>促销活动图片：</label>
							</div>
							<div class="tjcpxx-con-form">
								<div class="tjcpxx-con-form-upimg">
									<img id="loadimg" width="120px" height="115px" src="" />
								</div>
								<input type="hidden" name="img" value="" />
								<div style="width: 200px; float: left;">
									<input type="file" name="pics" id="singlefile" /> <a
										href="javascript:void(0);" style="color: #000"> <span
										class="tjcpxx-con-form-upthis">本地上传</span>
									</a>
								</div>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>开始时间：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input type="text" name="start" id="start" class="Wdate2"
									onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', maxDate: '#F{$dp.$D(\'end\')}' })"
									value="" readonly="readonly" />
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>结束时间：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input type="text" name="end" id="end" class="Wdate2"
									onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', minDate: '#F{$dp.$D(\'start\')}' })"
									value="" readonly="readonly" />
							</div>
						</div>

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<span class="red">*</span>使用平台：
							</div>
							<div class="tjcpxx-con-form">
								<input name='useplatform' checked type='checkbox' value='1'><span>pc端</span>
								<span class='marrig35'></span> <input name='useplatform'
									type='checkbox' value='2'><span>app端</span> <span
									class='marrig35'></span> 
							</div>
						</div>

						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title"></div>
							<div id="divStatus" class="tjcpxx-con-form huise">
								<input name='status' checked type='radio' value='0'><span>启用</span>
								<span class='marrig35'></span> <input name='status' type='radio'
									value='1'><span>禁用</span>

							</div>
						</div>

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="Save" type="button" value="保存">
								<input id="action" type="hidden" value="add"> <span
									class="marrig35"></span> <input class="preserve-inp_hs" name=""
									type="button" value="取消" onclick="formCancel()">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--tjcpxx-con stop -->
	</div>
</div>
<script src="/resource/public/platform/js/yxgl/spikesave.js"></script>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script>
	$(document).ready(
			function() {
				$(".tjcpxx-con-form-upthis").click(
						function() {
							$.ajaxFileUpload({
								url : "/app/api/img/upload",
								secureuri : false,
								fileElementId : 'singlefile',
								dataType : "json",
								//relationtype: 活动 (4)
								data : {
									relationtype : 2
								},
								type : 'post',
								success : function(result) {
									$("input[name='img']").val(result.data);
									if (result.code == 0){
										Dalert("上传成功");										
										$("#loadimg").attr(
												"src",
												$("#imgsrc").val()
														+ result.data[0]);
									}
									else
										$("#loadimg").attr("src", "");
									//TODO 结束正在加载中
								},
								error : function(e) {
									alert(JSON.stringify(e));
								}
							});
						});
			})
	function formCancel() {
		location.href = "yxgl_SpikeList";
	}
</script>
