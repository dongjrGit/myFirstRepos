<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><script type="text/javascript"
	src="/resource/public/seller/js/group/group.js"></script>

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
<style>
    .tjcpxx-con-form-upimg{display:table-cell; width:120px; height:115px; background:#f4f4f4; border:1px solid #D9D9D9; padding:1px; text-align:center; line-height:115px; float:left; margin:0px 0px 10px 0px;}
.tjcpxx-con-form-upthis{ width:105px; height:35px; float:left; background:#FFE5E6; border:1px solid #D9D9D9; margin:30px auto auto 5px; text-align:center; line-height:35px;}
</style>
<div class="container">
	<div class="allcon">
		<div class="position">您所在的位置：营销管理 &gt; 团购 &gt; 编辑团购信息</div>
		<!--所在位置信息  结束 -->
		<div class="zhgl-con">
			<div class="zhgl-con-con">
				<div class="zhgl-con-top">
					<div class="zhgl-con-top-title">编辑团购信息</div>
					<!--所在位置信息  结束 -->
				</div>
				<!--zhgl-con-top  stop -->
				<form id="form1" method="post">
					<table>
						<tr>
							<td class="xjdpzzh-left"><input id="id" type="hidden"
								value="${gbinfo.id}"> <span class="red">*</span>商品名称：</td>
							<td><input class="text-inp-big" name="text_title"
								value="${gbinfo.title}" id="text_title" type="text"></td>
						</tr>
						<tr>
							<td class="xjdpzzh-left">图片：</td>
							<td>

								<div class="tjcpxx-con-form">
									<div class="tjcpxx-con-form-upimg" >
										<img id="loadimg" width="120px" height="115px"
											src="${gbinfo.imgurl}" /> <input type="hidden" id="imgsrc"
											value="<%=path%>" /> <input type="hidden" name="imgurl"
											id="imgurl" value="${gbinfo.imgurl}" />
									</div>
									<div style="width: 200px; float: left; padding-left: 10px;">
										<input type="file" name="pics" id="singlefile" /> <a
											href="javascript:void(0);" style="color: #000"><div
												class="tjcpxx-con-form-upthis">本地上传</div></a>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left">分类：</td>
							<td><select id="text_class" name="text_class"
								class="sel-form" style="width: 160px">
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
							</select></td>
						</tr>
						<tr>
							<td class="xjdpzzh-left">原价格：</td>
							<td><input class="text-inp-big" style="width: 155px"
								name="text_yprice" id="text_yprice" type="text"
								value="${gbinfo.yprice}"></td>
						</tr>
						<tr>
							<td class="xjdpzzh-left">团购价格：</td>
							<td><input class="text-inp-big" style="width: 155px"
								name="text_price" id="text_price" type="text"
								value="${gbinfo.price}"></td>
						</tr>
						<tr>
							<td class="xjdpzzh-left">参与 人数：</td>
							<td><input class="text-inp-big" style="width: 155px"
								name="text_num" id="text_num" type="text" value="${gbinfo.num}"></td>
						</tr>
						<tr>
							<td class="xjdpzzh-left">开始时间：</td>
							<td><input class="text-inp-big Wdate"
								style="margin-left: 1px" name="text_stime" id="text_stime"
								type="text"
								value="<fmt:formatDate value="${gbinfo.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left">结束时间：</td>
							<td><input class="text-inp-big Wdate"
								style="margin-left: 1px" name="text_etime" id="text_etime"
								type="text"
								value="<fmt:formatDate value="${gbinfo.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left">描述：</td>
							<td><textarea class="tjcpxx-fprm-inp" name="depict"
									id="depict">${gbinfo.depict}</textarea></td>
						</tr>
						<tr>
							<td class="xjdpzzh-left">状态：</td>
							<td><c:choose>
									<c:when test="${gbinfo.state==0}">
										<input class="" name="state" id="state1" checked type="radio"
											value="0">
										<span>上架</span>
										<span class='marrig35'></span>
										<input class="" name="state" id="state1" type="radio"
											value="1">
										<span>下架</span>
									</c:when>
									<c:otherwise>
										<input class="" name="state" id="state1" type="radio"
											value="0">
										<span>上架</span>
										<span class='marrig35'></span>
										<input class="" name="state" checked id="state1" type="radio"
											value="1">
										<span>下架</span>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<td class="xjdpzzh-left">状态：</td>
							<td><input class="big-but marrig35 mar35" name="submit_ok"
								id="submit_ok" onclick="save()" type="button" value="保存">
								<input class="big-but-huise" name="btn_goback" id="btn_goback"
								type="button" value="返回"></td>
						</tr>
					</table>
			</div>
		</div>
		</form>
	</div>
</div>

<!--tjcpxx stop -->

<!--mainright stop -->
<script type="text/javascript">
	$(function() {
		// 返回按钮点击

		$("#btn_goback").bind("click", function() {
			window.location.href = "/seller/group/list";
		});

		$(".tjcpxx-con-form-upthis").click(
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
							debugger;
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