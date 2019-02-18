<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript"
	src="/resource/public/platform/js/hotcity/hotcity.js"></script>
	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0)">编辑热门城市</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx-con-con">
			<form id="form1" action="#" method="post">
				<input id="id" type="hidden" value="${gbinfo.id}">
				<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<span class="red marrig5">*</span><label>热门城市：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="RegionID" name="regionid" type="hidden" value="0" /> <input
									type="hidden" value="${province}" id="province" /> <input type="hidden"
									value="${city}" id="city" />
									<select id="select_province" name="select_province"  style="width: 92px"
										class="the-form-select-one">
										<option value="-1" >请选择</option>
										<script id="proviceselect" type="text/html">
                							{{each list as pro index}}
                							<option value="{{pro.code}}">{{pro.name}}</option>
                							{{/each}}
                						</script>
									</select> --><select id="select_city" name="select_city" style="width: 92px" class="the-form-select-one">
										<option value="-1">请选择</option>
										<script id="cityselect" type="text/html">
                							{{each list as pro index}}
                							<option value="{{pro.code}}">{{pro.name}}</option>
                							{{/each}}
                						</script>
									</select>
							</div>
						</div>
						
						<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label>图片：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<div class="tjcpxx-con-form-upimg">
						 <img id="loadimg" width="120px" height="115px"
								src="${gbinfo.imgurl}" />
						</div>
						<input type="hidden" id="imgsrc" value="<%=path%>" />
				 		 <input type="hidden" name="imgurl" id="imgurl" value="${gbinfo.imgurl}" />
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
					   <br/>
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label>排序：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" name="sort"
							value="${gbinfo.sort}" id="sort" type="text">
					</div>
				</div>
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1"></div>
					<div class="tjcpxx-con-form1 huise" style="padding-top: 20px;">
						<input class="preserve-inp marrig35 mar35" name="submit_ok"
							id="submit_ok" onclick="save()" type="button" value="保存">
						<input class="preserve-inp_hs" name="btn_goback" id="btn_goback"
							type="button" value="返回">
					</div>
				</div>
				<!--tjcpxx-con-mk stop -->
			</form>
		</div>
	</div>
	<!--tjcpxx stop -->
</div>
<!--mainright stop -->
<script type="text/javascript">
	$(function() {
		BindRegion();
		// 返回按钮点击
		$("#btn_goback").bind("click", function() {
			window.location.href = "/platform/rmcs/list";
		});
		if($("#id").val()>0){
			var province=$("#province").val();
			var city=$("#city").val();
			GetRegionData(0,"");
			$("#select_province").val(province);
			GetRegionData(1, province);
			$("#select_city").val(city);
		}
		
		
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
						// relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他
						// (20);）
						data : {
							"relationtype" : 5,
							"type" : 0
						},
						type : 'post',
						success : function(result) {
							$("#singlefile").change(x);
							 if (result.code == 0) {	
								 Dalert("上传成功");
								$("input[name='imgurl']").val(result.data[0]);
								$("#loadimg").attr("src",
										$("#imgsrc").val() + result.data[0]);
							} else {
								$("#loadimg").attr("src", "");
								Dalert("上传图片失败");
							} 
							// TODO 结束正在加载中
						},
						error : function(e) {
							alert(JSON.stringify(e));
						}
					});
				});
	});
</script>
