<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
	var editor, editor1, editor2, editor3;
	/* 	var basepath=getRootPath(); */
	KindEditor.ready(function(K) {
		//K.create('#content1');
		//取消功能 打印，插入模板，插入代码，插入flash，插入视频，插入表情，锚点
		editor = K.create('#content1', {
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
			uploadJson : "/app/api/img/upload?relationtype=1&iskdr=1"
		});
		editor1 = K.create('#content2', {
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
			uploadJson : "/app/api/img/upload?relationtype=1&iskdr=1"
		});
		editor2 = K.create('#content3', {
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
			uploadJson : "/app/api/img/upload?relationtype=1&iskdr=1"
		});
		editor3 = K.create('#content4', {
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
			uploadJson : "/app/api/img/upload?relationtype=1&iskdr=1"
		});
		if ($("input[name=description]").val() != ""
				&& $("input[name=description]").val() != undefined) {
			editor.html($("input[name=description]").val());
		}
		if ($("input[name=spuinfo]").val() != ""
				&& $("input[name=spuinfo]").val() != undefined) {
			editor1.html($("input[name=spuinfo]").val());
		}
		if ($("input[name=packinglist]").val() != ""
				&& $("input[name=packinglist]").val() != undefined) {
			editor2.html($("input[name=packinglist]").val());
		}
		if ($("input[name=afterservice]").val() != ""
				&& $("input[name=afterservice]").val() != undefined) {
			editor3.html($("input[name=afterservice]").val());
		}
	});

	function formSubmit() {
		$("input[name='commit']").hide();
		$("#brandID").val($("#select_brand").attr("data"));
		editor.sync();
		$("input[name=description]").val(editor.html());
		$("input[name=spuinfo]").val(editor1.html());
		$("input[name=packinglist]").val(editor2.html());
		$("input[name=afterservice]").val(editor3.html());
		$.ajax({
			url : "/platform/spu/updateDirect",
			data : $("#form").serialize(),
			type : "Post",
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("修改成功！", 2000, function() {
						window.location.href = "/platform/product/showDirect";
					});

				} else {
					$("input[name='commit']").show();
					Dalert(data.desc);
				}
			},
			error : function(e) {
				alert(JSON.parse(e.responseText).desc);
			}
		});
	}
	function formCancel() {
		var ht ="/platform/product/showDirect?";
			ht+="fc=${fc}";
			ht+='&sc=${sc}&tc=${tc}';
			ht+='&name=${name}&num=${num}&status='+GetQueryStringByName("status");
		window.location.href = ht;
	}
</script>

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">商品基本信息</a><span
					class="sj-img"></span></li>

			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con-con">
				<form id="form">
					<div class="tjcpxx-con">
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品名称：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="name" type="text"
									value="${spu.name }"> <input type="hidden"
									value="${spu.id }" id="select_id" name="id" /> <input
									type="hidden" value="${spu.shopid }" name="shopid" /> <input
									type="hidden" id="imgsrc" value="<%=path%>" /> <span
									class="huise">商品名称不能为空，长度限制在100个字符以内</span>
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品分类：</label>
							</div>
							<div class="tjcpxx-con-form">
								<span>${category.name }</span>
							</div>
							<input type="hidden" name="classid" value="${category.id }">
						</div>
						<!--tjcpxx-con-mk stop -->

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品品牌：</label>
							</div>
							<div class="tjcpxx-con-form">
								<span>${brand.name }</span>
							</div>
							<input type="hidden" name="brandid" value="${brand.id }">
						</div>

						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品短标题：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="titleShort" type="text"
									value="${spu.titleShort }">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品标签：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="tag" type="text"
									value="${spu.tag }">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品重量：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="weight" type="text"
									value="${spu.weight }">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品产地：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="producer" type="text"
									value="${spu.producer }">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品包装：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="pack" type="text"
									value="${spu.pack }">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>保质期(天)：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="shelflife" type="text"
									value="${spu.shelflife }">
							</div>
						</div>

						<c:choose>
							<c:when
								test="${spu.ispostage!=null&&spu.ispostage=='true' }">
								<input type="hidden" id="ispostage" name="ispostage" value="true">
								<div class="tjcpxx-con-mk">
									<div class="tjcpxx-con-form-title">是否包邮：</div>
									<div class="tjcpxx-con-form huise">
										<input name="r_ispostage" type="radio" value="0"><span>不包邮</span>
										<span class="marrig35"></span>
										<input name="r_ispostage" type="radio" checked="checked" value="1"><span>包邮</span>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<input type="hidden" id="ispostage" name="ispostage" value="false">
								<div class="tjcpxx-con-mk">
									<div class="tjcpxx-con-form-title">是否包邮：</div>
									<div class="tjcpxx-con-form huise">
										<input name="r_ispostage" type="radio" checked="checked" value="0"><span>不包邮</span> 
											<span class="marrig35"></span>
										<input name="r_ispostage" type="radio" value="1"><span>包邮</span>
									</div>
								</div>
							</c:otherwise>
						</c:choose>

						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>商品图片：</label>
							</div>
							<div class="tjcpxx-con-form">
								<c:choose>
									<c:when test="${spu.imgurlApp==null || spu.imgurlApp=='' }">
										<div class="tjcpxx-con-form-upimg">
											<img id="loadimg" width="120px" height="115px"
												src="${spu.imgurlApp }" />
										</div>
									</c:when>
									<c:otherwise>
										<div class="tjcpxx-con-form-upimg">
											<img id="loadimg" width="120px" height="115px"
												src="<%=path %>${spu.imgurlApp }" />
										</div>
									</c:otherwise>
								</c:choose>

								<input type="hidden" name="imgurlApp" value="${spu.imgurlApp }" />
								<div style="width: 200px; margin-left: 10px; float: left;">
									<input type="file" id="singlefile" name="pics" /> <a
										href="javascript:void(0);" style="color: #000"><div
											class="tjcpxx-con-form-upthis">本地上传</div></a>
								</div>
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>商品详情：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content2" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="tjcpxx-fprm-inp" name="spuinfo" type="hidden"
									value="${spu.spuinfo }">
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>手机端商品详情：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content1" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="tjcpxx-fprm-inp" name="description" type="hidden"
									value="${spu.description}" />

							</div>
						</div>

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>包装清单：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content3" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="tjcpxx-fprm-inp" name="packinglist" type="hidden"
									value="${spu.packinglist }">
							</div>
						</div>
						<span class="marrig10"></span>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>售后服务：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content4" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="tjcpxx-fprm-inp" name="afterservice" type="hidden"
									value="${spu.afterservice }">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->

					</div>

					<div class="clear"></div>
					<div class="dotted mar35"></div>
					<div class="mar35"></div>

					<div class="tjcpxx-con-mk">
						<div class="tjcpxx-con-form-title"></div>
						<div class="tjcpxx-con-form huise">
							<input class="preserve-inp" name="commit" type="button"
								value="保存" onclick="formSubmit()"> <input
								class="preserve-inp_hs" name="" type="button" value="取消"
								onclick="formCancel()">
						</div>
					</div>
					<!--tjcpxx-con-mk stop -->
					<div class="clear"></div>
					<div class="dotted mar35"></div>

				</form>
			</div>
			<!--tjcpxx-con stop -->
		</div>
		<!--tjcpxx stop -->
	</div>
	<!--mainright stop -->
</div>
<script type="text/javascript">
	$(document).ready(function() {
				$("#select_freight").val($("#input_freight").val());
				$(".tjcpxx-con-form-upthis").click(function() {

							$.ajaxFileUpload({
								url : "/app/api/img/upload",
								secureuri : false,
								fileElementId : 'singlefile',
								dataType : "json",
								//relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他 (20);） 可以自由增加
								data : {
									"relationtype" : 2,
									"type" : 0
								},
								type : 'POST',
								success : function(result) {
									$("input[name='imgurlApp']").val(
											result.data);
									if (result.code == 0){
										Dalert("上传成功");										
										$("#loadimg").attr(
												"src",
												$("#imgsrc").val()
														+ result.data[0]);
									}
									else {
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
				$("input[name=r_ispostage]").click(function() {
					var v = $(this).val();
					if (v == 0) {
						$("#ispostage").val(false);
					} else {
						$("#ispostage").val(true);
					}

				});
			})
</script>
