<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<style type="text/css">
/* 半透明的遮罩层 */
#overlay {
    background: #000;
    filter: alpha(opacity=50); /* IE的透明度 */
    opacity: 0.5;  /* 透明度 */
    display: none;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    height: 100%;
    z-index: 100; /* 此处的图层要大于页面 */
    display:none;
}
</style>
<script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script> 
	<script type="text/javascript" src="/resource/public/platform/js/upload_img_ext.js"></script>
	<script src="${ctx}/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
	<script src="${ctx}/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>
	<script type="text/javascript" src="/resource/public/platform/js/zoo/scenic/scenic_type.js"></script>
	<script type="text/javascript" src="/resource/public/platform/js/zoo/scenic/scenicedit.js"></script>
	<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/zooCommon.js"></script>
	<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/scenic/test.js"></script>
<script type="text/javascript">
	var timeEditor,contEditor;
	var flag = true;
	KindEditor.ready(function(K) {
		timeEditor = K.create('#openTime', {
			items : ['source','undo', 'redo', 'preview', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent','clearhtml','quickformat', 'selectall', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat','table', 'hr'],
		    minHeight:'240',
		    minWidth:'500'
		});
		/* $(timeEditor.edit.doc).bind("keydown", function(e) {
			if(e.which==32){
				timeEditor.insertHtml('&emsp;');
				return false;				
			}
		}); */
		
		contEditor = K.create('#content', {
			items : ['undo', 'redo', 'preview', 'justifyleft',
						'justifycenter', 'justifyright', 'justifyfull',
						'insertorderedlist', 'insertunorderedlist', 'indent',
						'outdent','clearhtml','quickformat', 'selectall', 'formatblock',
						'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
						'italic', 'underline', 'strikethrough', 'lineheight',
						'removeformat','table', 'hr'],
			minHeight:'240',
			minWidth:'570'
		});
	});



	
		$(function() {
			Init.bind();
			$("#div_scenicImgs").upload({
				id : "scenicImgs",
				paramname : "scenicImgs",
				imgmax : 5,
				//uploadurl:"/aaa/aaa",
				uploaddata : {
					// ftype:上传文件类型（图片文件=1，其他文件=2）
					// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
					relationtype : 1
				},
			});
			//初始化数据
			UploadAPI.InitData("scenicImgs", '${imagePaths}');
		});
	</script>
	<div class="mainright">
	<div id="overlay"></div>
		<div class="l_wzmb">
			<div class="l_wzmbtop">
				<ul>
					<li class="sj_hover"><a href="javascript:void(0);">添加景点设施</a><span class="sj-img"></span></li>
				</ul>
			</div>
			<!--l_wzmbtop   stop -->
			<div class="tjcpxx">
				<div class="tjcpxx-con">
					<div class="tjcpxx-con-con">
						<form id="form" method="post">
							<input type="hidden" value="${bean.id }" id="id" name="id" />
							<input type="hidden" value="${bean.imageid }" id="imageid"/>
							<input type="hidden" value="${bean.createtimestr }" id="createtimestr"/>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>景点设施名称：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<input class="tjcpxx-fprm-inp" name="scenicName" id="scenicName" onblur="value=value.trim()"  type="text" value="${bean.scenicname}">
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>景点类别：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<select class="the-form-select-one" name="scenicType"  id="scenicType"  onchange="Init.typeChange()">
										<option value="" id="defaultType" selected>请选择</option>
										<script id="typeList" type="text/html">
                            	{{each list as fclass i}}
                					<option value="{{fclass.code}}">{{fclass.name}}</option>
                            	{{/each}}
                            	</script>
									</select>
								</div>
							</div>
							<%-- <div class="tjcpxx-con-mk1" id="propertyDiv" style="display:none">
								<div class="tjcpxx-con-form-title">
									<label>属性：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<c:choose>
										<c:when test="${bean.property==1 || bean.property==4 || bean.property==6 || bean.property==9}">
											<input name="interaction" id="interaction" type="checkbox" checked value="1" ><span>动物互动</span>
										</c:when>
										<c:otherwise>
											<input name="interaction" id="interaction" type="checkbox" value="1" ><span>动物互动</span>
										</c:otherwise>
									</c:choose>
									<span class='marrig35'></span> 
									<c:choose>
										<c:when test="${bean.property==3 || bean.property==4 || bean.property==8 || bean.property==9}">
											<input name="explain" id="explain" type="checkbox" checked value="3"><span>科普讲解</span>
										</c:when>
										<c:otherwise>
											<input name="explain" id="explain" type="checkbox" value="3"><span>科普讲解</span>
										</c:otherwise>
									</c:choose> 
									<span class='marrig35'></span> 
									<c:choose>
										<c:when test="${bean.property==5 || bean.property==6 || bean.property==8 || bean.property==9}">
											<input name="actionShow" id="actionShow" type="checkbox" checked value="5"><span>行为展示</span>
										</c:when>
										<c:otherwise>
											<input name="actionShow" id="actionShow" type="checkbox"  value="5"><span>行为展示</span>
										</c:otherwise>
									</c:choose>
								</div>
							</div> --%>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title1" id="div_image">
									<label><span class="red marrig5">*</span>景点图片：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<div class="tjcpxx-con-form-upimg">
										<img id="loadimg" width="120px"  height="115px" src="" alt="请上传图片" />
									</div>
									<input type="hidden" name="img" value="" />
									<div style="width: 200px; float: left; position: relative; padding-left: 30px;">
										<input type="button" value="选择图片" class="h_scimgbut" id="buttonid" /> 
										<input type="file" id="singlefile" name="pics" onchange="changeFile(this)" class="filemhbut" style="top: 10px; left: 27px;" />
										<div>
											<input type="button" value="本地上传" class="h_scimgbut h_scimgbut1" />
										</div>
									</div>
								</div>
							</div>
							<!--tjcpxx-con-mk stop -->
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title1"></div>
								<div class="tjcpxx-con-form1" style="position: relative;">
									<label for="selectimg" class="error"></label> <span class="beizhu-mc-upimg">上传图片要小于500kb</span>
								</div>
							</div>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>详情展示：</label>
								</div>
								<div class="l_dpxxtj" id="div_scenicImgs"></div>
							</div>
							</br>
							<div class="tjcpxx-con-mk">
								<div class="tjcpxx-con-form-title">
									<label>开放时间：</label>
								</div>
								<div class="tjcpxx-con-form" >
									<textarea  name="openTime" id="openTime"
										style="height: 300px; visibility: hidden;" >${bean.opentime}</textarea>
								</div>
								<div class="tjcpxx-con-form-title" style="width: 100px;">
									<label>例子：</label>
								</div>
								<div class="tjcpxx-con-form1" style="width: 200px;">
									<textarea rows="17" cols="25" id="demo" readonly="readonly" style="resize:none">投喂时间：
周一 ~ 周五    10:30—15:30
周六 ~ 周日    10:00—16:00

骑乘时间：
周一 ~ 周五    11:30—14:30
周六 ~ 周日    11:30—15:30

科普讲解时间：
10:30    14:30
行为展示-大象运木
10:30    14:30</textarea>
								</div>
							</div>
							</br>
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>地点：</label>
								</div>
								<div class="tjcpxx-con-form1" style="width: 800px">
									<select class="the-form-select-one" id="scenicArea" name="scenicArea">
										<option value="">请选择</option>
										<option value="0">步行区</option>
										<option value="1">车入区</option>
									</select><span>详细地址：</span> <input class="tjcpxx-fprm-inp" id="address" style="width: 290px" name="address" type="text" value="${bean.address }" maxlength="20" /> 
									<span>经度：</span> <input id="longitude" name="longitude" style="width: 100px" type="text"  value="${bean.longitude }"/>
									<span>纬度：</span> <input id="latitude" name="latitude" style="width: 100px" type="text" value="${bean.latitude }"/>
								</div>
							</div>
							<div class="tjcpxx-con-mk1" id="isChargeDiv" style="display:none">
								<div class="tjcpxx-con-form-title">
									<label>是否收费：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<select class="the-form-select-one" id="isCharge">
										<option value="">请选择</option>
										<option value="0">免费</option>
										<option value="1">收费</option>
									</select>
								</div>
							</div>
							
							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label><span class="red marrig5">*</span>详情：</label>
								</div>
								<div class="tjcpxx-con-form1">
									<textarea  name="content" id="content" style="height: 300px;visibility: hidden">${bean.content }</textarea>
								</div>
							</div>
							</br>
							<div class="tjcpxx-con-mk1" id="shopDiv" style="display:none">
								<div class="tjcpxx-con-form-title1">
									<label>店铺：</label>
								</div>
								<ad>
									<input class="tjcpxx-fprm-inp" data="${bean.shopid}" value="${bean.shopname}" type="text" name="shopId" id="shopId" />
								</ad>
								<div name="spu_name_select" style="margin-left: 12px;">
									<ul>
										<script id="select_shoplist" type="text/html">
                                        	{{each list as sp i}}
                                        	<li data="{{sp.id}}">{{sp.name}}</li>
                                        	{{/each}}
                                    	</script>
									</ul>
								</div>
							</div>

							<div class="tjcpxx-con-mk1" id="likeDiv" style="display:none">
								<div class="tjcpxx-con-form-title1" >
									<label>猜你喜欢：</label>
								</div>
								<ad>
									<input class="tjcpxx-fprm-inp" data="${bean.topicid}" type="text"  value="${bean.topicname}"  name="guessLike" id="guessLike"/>
								</ad>
								<div name="spu_name_select" style="margin-left: 12px;">
									<ul>
										<script id="select_spulist" type="text/html">
                                        	{{each list as sp i}}
                                        	<li data="{{sp.id}}">{{sp.title}}</li>
                                        	{{/each}}
                                    	</script>
									</ul>
								</div>
							</div>

							<div class="tjcpxx-con-mk1">
								<div class="tjcpxx-con-form-title">
									<label>发布状态：</label>
								</div>
								<div id="divStatus" class="tjcpxx-con-form huise">
									<span class='marrig35'></span> 
									<c:choose>
									    <c:when test="${bean.state==1 }">
		                            		<input name='state' checked type='radio' value='1'><span>发布</span>
		                            		<input name='state' type='radio' value='0'><span>未发布</span>
			                            </c:when>
			                            <c:otherwise>
			                           		<input name='state' type='radio' value='1'><span>发布</span> 
											<input name='state' checked type='radio' value='0'><span>未发布</span>
			                            </c:otherwise>
		                            </c:choose>
								</div>
							</div>
							<span class="marrig35"></span><input type="hidden" id="type" name="type" value=2 type="text"> <input type="hidden" id="ctype" name="ctype" value="1" type="text">
							<div class="tjcpxx-con-mk">
								<div class="tjcpxx-con-form-title"></div>
								<div class="tjcpxx-con-form huise">
									<input class="preserve-inp" name="Save" type="button" value="保存"> 
									<span class="marrig35"></span> 
									<input class="preserve-inp_hs" name="" type="button" value="取消" onclick="formCancel()">
								</div>
							</div>
							<!--tjcpxx-con-mk stop -->
						</form>
					</div>
				</div>
			</div>
			<!--tjcpxx-con stop -->
		</div>
		<script id="slist" type="text/html">
  {{each list as sclass i}}
   <option value="{{sclass.id}}">{{sclass.name}}</option>
  {{/each}}
   </script>
		<script id="tlist" type="text/html">
  {{each list as tclass i}}
   <option value="{{tclass.id}}">{{tclass.name}}</option>
   {{/each}}
    </script>
	</div>
<script type="text/javascript">
$(document).ready(function() {
    // 获取景点类别
    getScenicType();
    if ('' != '${bean.id}') {
        $("#scenicType").val('${bean.type}');
        $("#scenicType").change();
        $("#scenicType").attr("disabled", "disabled");
        $("#scenicArea").val('${bean.scenicarea}');
        $("#loadimg").attr("src", '${bean.img}');
        $("input[name='img']").val('${bean.img}');
        $("#isCharge").val('${bean.ischarge}');
    }
    function getScenicType() {
        $.ajax({
            url: "/zoo/scenic/queryScenicType",
            type: "post",
            data: {},
            dataType: "json",
            async: false,
            success: function(data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('typeList', listdata);
                    $("#defaultType").after(html);
                }
            },
            error: function() {}
        });
    }
    $(".h_scimgbut1").click(function() {
        if ($("#singlefile").val()) {
        	zooOver.showOverlay();
            $.ajaxFileUpload({
                url: "/app/api/img/upload",
                secureuri: false,
                fileElementId: 'singlefile',
                dataType: "json",
                data: {
                    relationtype: 50,
                    'zoo-upload': 'true'
                },
                type: 'POST',
                success: function(result) {
                	if (result.code == 0) {
                        $("input[name='img']").val(result.data);
                        $("#loadimg").attr("src", result.data[0]);
                        Dalert("上传成功");
                    } else {
                        $("#loadimg").attr("src", "");
                        $("input[name='img']").val("");
                        if (result.code == 700) {
                            clearFile();
                            Dalert("图片过大");
                        } else {
                            Dalert(result.desc);
                        }
                    }
                },
                error: function(e) {
                    alert(JSON.stringify(e));
                },
                complete: function() {
                	zooOver.hideOverlay();
                }
            });
            $("#singlefile").remove();
            var input = '<input type="file" id="singlefile" name="pics" class="filemhbut"  onchange="changeFile(this);"  style="top: 10px; left: 27px;" />';
            $("#buttonid").after(input);
        }
    });
}) 
function changeFile(object) {
    var agent = navigator.userAgent.toLowerCase();
    if (agent.indexOf("msie") > 0) {
        var version = agent.match(/msie [\d.]+;/gi)[0];
        if (version == 'msie 9.0;') {
            object.select();
            // object.blur();
            $('#div_image').focus();
            var nfile = document.selection.createRange().text;
            // alert("当前选择的文件完整路径是:"+nfile);
            document.selection.empty();
            document.getElementById("loadimg").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + nfile + "')";
        } else {
            if (version == 'msie 10.0;') {
                if (object.files.item(0).size > 500 * 1024) {
                    clearFile();
                    Dalert("图片大小不能超过500kb");
                    return;
                }
                var objUrl = getObjectURL(object.files.item(0));
                if (objUrl) {
                    $("#loadimg").attr("src", objUrl);
                } else {
                    $("#loadimg").attr("src", "");
                }
            }
        }
    } else {
        if (object.files.item(0).size > 500 * 1024) {
            clearFile();
            Dalert("图片大小不能超过500kb");
            return;
        }
        var objUrl = getObjectURL(object.files.item(0));
        if (objUrl) {
            $("#loadimg").attr("src", objUrl);
        } else {
            $("#loadimg").attr("src", "");
        }
    }
}
// 建立一个可存取到该file的url
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

function clearFile() {
    $("#singlefile").remove();
    var input = '<input type="file" id="singlefile" name="pics" class="filemhbut"  onchange="changeFile(this);"  style="top: 10px; left: 27px;" />';
    $("#buttonid").after(input);
    $("#loadimg").attr("src", "");
}

function formCancel() {
    location.href = "/zoo/scenic/list";
}
</script>
