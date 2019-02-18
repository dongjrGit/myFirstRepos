<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/commonjs/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="/resource/public/platform/js/zoo/banner/banneredit.js"></script>
<script type="text/javascript" src="/resource/public/platform/js/zoo/zooCommon.js"></script>

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">添加</a><span class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->

		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form">
						<input type="hidden" value="${bean.id }" id="id"> <input type="hidden" value="${bean.type }" id="srcType">
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>标题：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="title" name="title" type="text" value="${bean.title }">
							</div>
						</div>

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title" id="div_image">
								<label><span class="red marrig5">*</span>图片：</label>
							</div>
							<div class="tjcpxx-con-form">
								<div class="tjcpxx-con-form-upimg">
									<c:choose>
										<c:when test="${bean.id>0 }">
											<img id="loadimg" width="120px" height="115px" src="<%=path %>${bean.img}" />
										</c:when>
										<c:otherwise>
											<img id="loadimg" width="120px" height="115px" src="" alt="请上传图片"/>
										</c:otherwise>
									</c:choose>
								</div>
								<input type="hidden" name="img" id="img" value="${bean.img }" />
								<div style="width: 200px; float: left; position: relative; left: 30px;">
									<input type="button" value="选择图片" class="h_scimgbut" id="buttonid"> 
									<input type="file" name="pics" id="singlefile" class="filemhbut" onchange="changeFile(this);" style="top: 10px;">
									<div>
										<input type="button" value="本地上传" class="h_scimgbut h_scimgbut1">

									</div>
								</div>
							</div>
						</div>
						<br />

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<span class="red marrig5">*</span>跳转类型：
							</div>
							<div class="tjcpxx-con-form">
								<select name="type" id="type" onchange="BannerEdit.typeChange()">
									<option value="">请选择</option>
									<script id="typeList" type="text/html">
                                        {{each list as ad i}}
                                        <option value="{{ad.code}}"
											{{if '${bean.type}'!='' }}
											{{if ad.code == '${bean.type}' }} selected="selected" {{/if}}
											{{/if}}
										>{{ad.name}}</option>
                                        {{/each}}
                                    </script>
								</select>
							</div>
						</div>

						<div id="divspu" class="tjcpxx-con-mk" style='position: relative; display: none'>
							<div class="tjcpxx-con-form-title">
								<span class="red marrig5">*</span>商品名称：
							</div>
							<div style="width: auto; display: table-cell;">
								<div>
									<input id="select_spu" type="text" class="inp-seller" value="${spuname }" data="${bean.typeId }" />
								</div>
								<div style="margin-left: 11px;">
									<ul>
										<script id="select_spulist" type="text/html">
                                        {{each list as sp i}}
                                        <li data="{{sp.id}}">{{sp.name}}</li>
                                        {{/each}}
                                    </script>
									</ul>
								</div>
							</div>
						</div>

						<div id="divshop" class="tjcpxx-con-mk" style='position: relative; display: none'>
							<div class="tjcpxx-con-form-title">
								<span class="red marrig5">*</span>店铺名称：
							</div>
							<div style="width: auto; display: table-cell;">
								<div>
									<input id="select_shop" type="text" class="inp-seller" value="${shopname }" data="${bean.typeId }" />
								</div>
								<div style="margin-left: 11px;">
									<ul>
										<script id="select_shoplist" type="text/html">
                                        {{each list as shop i}}
                    						<li data="{{shop.id}}">{{shop.name}}</li>
                   						{{/each}}
                                    </script>
									</ul>
								</div>
							</div>
						</div>

						<div id="divScenic" class="tjcpxx-con-mk" style='position: relative; display: none'>
							<div class="tjcpxx-con-form-title">
								<span class="red marrig5">*</span>设施名称：
							</div>
							<div style="width: auto; display: table-cell;">
								<div>
									<input id="scenicid" type="text" class="inp-seller" value="${scenicname }" data="${bean.typeId }" />
								</div>
								<div  style="margin-left: 11px;">
									<ul>
										<script id="select_sceniclist" type="text/html">
                                        	{{each list as item i}}
                                        	<li data="{{item.id}}">{{item.scenicname}}</li>
                                        	{{/each}}
                                    </script>
									</ul>
								</div>
							</div>
						</div>
						
						
						<div id="divEditor" class="tjcpxx-con-mk" style='position: relative; display: none'>
							<div class="tjcpxx-con-form-title">
								<span class="red marrig5">*</span>图文标题：
							</div>
							<div style="width: auto; display: table-cell;">
								<div>
									<input id="editorId" type="text" class="inp-seller" value="${editorTitle }" data="${bean.id }" />
								</div>
								<div  style="margin-left: 11px;">
									<ul>
										<script id="select_editorlist" type="text/html">
                                        	{{each list as item i}}
                                        	<li data="{{item.id}}">{{item.title}}</li>
                                        	{{/each}}
                                    </script>
									</ul>
								</div>
							</div>
						</div>

						<div id="divurl" class="tjcpxx-con-mk" style="display: none">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>链接：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="url" name="url" type="text" value="${bean.url }">
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>图片排序：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="sort"  type="number" min="0"  value="${bean.sort==null?0:bean.sort}">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>状态：</label>
							</div>
							<div class="tjcpxx-con-form">
								<c:choose>
									<c:when test="${bean.status==1}">
										<input type="radio" id="radio_status" name="radio_status" value="1" checked />启用 
                    			<input type="radio" name="radio_status" id="radio_status" value="0" />禁用
                    		</c:when>
									<c:otherwise>
										<input type="radio" id="radio_status" name="radio_status" value="1" checked="ischecked" />启用 
                    			<input type="radio" name="radio_status" id="radio_status" value="0" />禁用
                    		</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="Save" type="button" value="保存" /> <input class="preserve-inp_hs" name="" type="button" value="取消" onclick="backhref()">
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
	$(document).ready(function(){
		BannerEdit.getBannerType();
		$(".h_scimgbut1").click(function() {
			if($("#singlefile").val()){
				$.ajaxFileUpload({
					url : "/app/api/img/upload",
					secureuri : false,
					fileElementId : 'singlefile',
					dataType : "json",
					data : {
						relationtype : 50
					},
					type : 'post',
					success : function(result) { //后台ajax返回的数据 此处Imgurl
						$("#img").val(result.data[0]);
						if (result.code == 0){
							Dalert("上传成功");
							$("#loadimg").attr("src", result.data[0]);
						}else{
							$("#loadimg").attr("src", "");
						}
					},
					error : function(e) {
						alert('上传失败！');
					},
					complete:function(){
            			
            		}
				});
				$("#singlefile").remove();
				var input = '<input type="file" name="pics" id="singlefile" class="filemhbut" onchange="changeFile(this);" style="top: 10px;">';
				$("#buttonid").after(input);
		    }
		});
	});
	function changeFile(object){
		var agent = navigator.userAgent.toLowerCase() ;
		if(agent.indexOf("msie") > 0){
			var version = agent.match(/msie [\d.]+;/gi)[0];
			if(version=='msie 9.0;'){
				$("#loadimg").attr("src","");
				 object.select();  
				 //$("#id").blur();  
				 $('#div_image').focus();
				 var nfile = document.selection.createRange().text;  
				 //alert("当前选择的文件完整路径是:"+nfile);  
				 document.selection.empty();  
				 document.getElementById("loadimg").style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='"+nfile+"')";  
			}else{
				var objUrl = getObjectURL(object.files.item(0));
				if (objUrl) {
					$("#loadimg").attr("src", objUrl);
				} else {
					$("#loadimg").attr("src", "");
				}
			}
		}else{
			var objUrl = getObjectURL(object.files.item(0));
			if (objUrl) {
				$("#loadimg").attr("src", objUrl);
			} else {
				$("#loadimg").attr("src", "");
			}
		}
	}
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
	function backhref() {
		window.location.href = "list";
	}
</script>