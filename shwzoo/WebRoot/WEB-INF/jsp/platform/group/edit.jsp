<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript"
	src="/resource/public/platform/js/group/group.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	var editor;
	/*var basepath=getRootPath();  */
	KindEditor.ready(function(K) {
		//K.create('#content1');
		//取消功能 打印，插入模板，插入代码，插入flash，插入视频，插入表情，锚点
		editor = K.create('#depict', {
			items : [ 'source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
					'paste', 'plainpaste', 'wordpaste', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', 'fullscreen', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', 'image', 'multiimage', 'insertfile',
					'table', 'hr', 'baidumap', 'pagebreak', 'link', 'unlink' ],
			uploadJson : "/app/api/img/upload?relationtype=5&iskdr=1"
		});
	});
</script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0)">编辑团购信息</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx-con-con">
			<form id="form1" action="#" method="post">
				<input id="id" type="hidden" value="${gbinfo.id}">
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label><span class="red marrig5">*</span>商品名称：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" name="text_title"
							value="${gbinfo.title}" id="text_title" type="text">
					</div>
				</div>

				<!-- 	<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label><span class="red marrig5">*</span>期号：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" name="text_title" id="text_title"
							type="text">
					</div>
				</div> -->
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label>图片：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<div class="tjcpxx-con-form-upimg">
							<img id="loadimg" width="120px" height="115px"
								src="${gbinfo.imgurl}" />
						</div>
						<input type="hidden" id="imgsrc" value="<%=path%>" /> <input
							type="hidden" name="imgurl" id="imgurl" value="${gbinfo.imgurl}" />
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
				<!--tjcpxx-con-mk stop -->
				<br />
				<div class="tjcpxx-con-mk1">
					<div class="tjcpxx-con-form-title1">
						<label>分类：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<select id="text_class" name="text_class" class="tjcpxx-fprm-inp"
							style="width: 168px">
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
				<div class="tjcpxx-con-mk">
					<div class="tjcpxx-con-form-title1">
						<label>原价格：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" style="width: 155px"
							name="text_yprice" id="text_yprice" type="text"
							value="${gbinfo.yprice}">
					</div>
				</div>
				<div class="tjcpxx-con-mk">
					<div class="tjcpxx-con-form-title1">
						<label>团购价格：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" style="width: 155px"
							name="text_price" id="text_price" type="text"
							value="${gbinfo.price}">
					</div>
				</div>
				<div class="tjcpxx-con-mk">
					<div class="tjcpxx-con-form-title1">
						<label>参与 人数：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" style="width: 155px"
							name="text_num" id="text_num" type="text" value="${gbinfo.num}">
					</div>
				</div>
				<!--tjcpxx-con-mk stop -->
				<div class="tjcpxx-con-mk">
					<div class="tjcpxx-con-form-title1">
						<label>开始时间：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp Wdate" style="margin-left: 1px"
							name="text_stime" id="text_stime" type="text"
							value="<fmt:formatDate value="${gbinfo.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
					</div>
				</div>
				<!--tjcpxx-con-mk stop -->
				<div class="tjcpxx-con-mk">
					<div class="tjcpxx-con-form-title1">
						<label>结束时间：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp Wdate" style="margin-left: 1px"
							name="text_etime" id="text_etime" type="text"
							value="<fmt:formatDate value="${gbinfo.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
					</div>
				</div>
				<!--tjcpxx-con-mk stop -->

				<div class="tjcpxx-con-mk">
					<div class="tjcpxx-con-form-title1">
						<label>描述：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<textarea class="tjcpxx-fprm-inp" name="depict" id="depict">${gbinfo.depict}</textarea>
					</div>
				</div>
				<div class="tjcpxx-con-mk">
					<div class="tjcpxx-con-form-title1">
						<label>状态：</label>
					</div>
					<div class="">
						<c:choose>
							<c:when test="${gbinfo.state==0}">
								<input class="" name="state" id="state1" checked type="radio"
									value="0">上架 <input class="" name="state" id="state1"
									type="radio" value="1">下架
							</c:when>
							<c:otherwise>
								<input class="" name="state" id="state1" type="radio" value="0">上架 <input
									class="" name="state" checked id="state1" type="radio"
									value="1">下架
							</c:otherwise>
						</c:choose>
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

	$(function() {
		// 返回按钮点击

		$("#btn_goback").bind("click", function() {
			window.location.href = "/platform/group/list";
		});

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
