<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript"
	src="/resource/public/platform/js/Highspecialty/hispe.js"></script>

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
				<li class="sj_hover"><a href="javascript:void(0)">编辑优质特产</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx-con-con">
			<form id="form1" action="#" method="post">
				<input id="id" type="hidden" value="${gbinfo.id}">
				<%-- <c:choose>
									<c:when test="${gbinfo.id >0}">
									<div class="tjcpxx-con-mk" >
					<div class="tjcpxx-con-form-title">	<label><span class="red marrig5">*</span>特产名称：</label></div>
						<div class="tjcpxx-con-form1">
										 <label id="select_sku" name="select_sku"  data="${gbinfo.sid}">${skuinfo.kname}[价格(<fmt:formatNumber value="${skuinfo.price}" pattern="##00.0#"/> 元))店铺(${skuinfo.sname})]</label>
								</div></div>	</c:when>
									<c:otherwise>
										<input type="hidden" id="sid" name="sid" /> --%>
				<div class="tjcpxx-con-mk">
					<div class="tjcpxx-con-form-title">
						<label><span class="red marrig5">*</span>特产名称：</label>
					</div>
					<ad> <input id="select_sku" name="select_sku" type="text"
						class=" tjcpxx-fprm-inp the-form-select-one" style="width: 310px"
						value="${gbinfo.name }" data="${gbinfo.kid }"/></ad>
					<div>
						<ul>

						</ul>
					</div>
								<script id="select_skulist" type="text/html">
                                        {{each list as sku i}}
                                        <li data="{{sku.id}}" data-val="{{sku.kname}}">{{sku.kname}}</li>
                                        {{/each}}
                                    </script>
							</div>
									<%-- </c:otherwise>
								</c:choose> --%>
				
				<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>所在地：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="RegionID" name="regionid" type="hidden" value="0" /> <input
									type="hidden" value="${vo.province}" id="province" /> <input type="hidden"
									value="${vo.city}" id="city" />
									<select id="select_province" name="select_province"  style="width: 150px"
										class="the-form-select-one" value="${vo.province}">
										<option value="-1" >请选择</option>
										<script id="proviceselect" type="text/html">
                							{{each list as pro index}}
                							<option value="{{pro.code}}">{{pro.name}}</option>
                							{{/each}}
                						</script>
									</select> --><select id="select_city" name="select_city" style="width: 150px" class="the-form-select-one"  value="${vo.city}">
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
								src="${gbinfo.himgurl}" />
						</div>
						<input type="hidden" id="imgsrc" value="<%=path%>" /> <input
							type="hidden" name="imgurl" id="imgurl" value="${gbinfo.himgurl}" />
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
						<label>排序：</label>
					</div>
					<div class="tjcpxx-con-form1">
						<input class="tjcpxx-fprm-inp" name="sort"
							value="${gbinfo.sort}" id="sort" type="text">
					</div>
				</div>
				<div class="tjcpxx-con-mk">
					<div class="tjcpxx-con-form-title1">
						<label>状态：</label>
					</div>
					<div class="">
						<c:choose>
							<c:when test="${gbinfo.type==1}">
								<input class="" name="state" id="state1" type="radio" value="0">普通 
								<input class="" name="state" id="state1" checked type="radio" value="1">实时推荐
							</c:when>
							<c:otherwise>
								<input class="" name="state" id="state1" checked type="radio" value="0">普通
								<input class="" name="state" id="state1" type="radio" value="1">实时推荐
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
$(document).ready(function(){
	BindRegion();
	autoxl.bind("select_sku", getskus, '400', 1);
	function getskus(callback, event) {
		var name = $("#select_sku").val();
		if (event)
			name += String.fromCharCode(event.keyCode);
		$.ajax({
			url : "/platform/spu/getBySkuName",
			type : "Post",
			data : {
				"name" : name
			},
			dataType : "json",
			success : function(data) {

				if (data.code == 0) {
					var listdata = {
						list : data.data
					}
					var html = template('select_skulist', listdata);

					if (callback) {
						// debugger;
						callback(html);
					}
				} else {
					Dalert(data.desc);
				}
			}
		});
	}
	;
})
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
			window.location.href = "/platform/yztc/list";
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
	$(function(){
		if($("#id").val()>0){
			var province=$("#province").val();
			var city=$("#city").val();
			GetRegionData(0,"");
			$("#select_province").val(province);
			GetRegionData(1, province);
			$("#select_city").val(city);
		}
	});
</script>
