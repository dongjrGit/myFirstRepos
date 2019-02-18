<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script>
<script src="${ctx}/resource/public/platform/js/zoo/kindeditor-4.1.11/kindeditor-all-min.js"></script>
<script src="${ctx}/resource/public/platform/js/zoo/kindeditor-4.1.11/lang/zh-CN.js"></script>
<script src="${ctx}/resource/public/platform/js/zoo/news/newsedit.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/zooCommon.js"></script>
<style>
.h_scimgbut2 {
    background: #3180d2;
    color: #fff;
    }
</style>
<script type="text/javascript">
var  contEditor;
var flag = true;
KindEditor.ready(function(K) {
	contEditor = K.create('#content', {
		items : ['source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
					'paste', 'plainpaste', 'wordpaste', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', 'fullscreen', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', 'image', 'multiimage','table', 'hr'
				], 
		minHeight : '300', minWidth : '800',
		//allowImageRemote:false,
		//uploadJson :"/app/api/img/upload?relationtype=50&iskdr=1"
		uploadJson:"/zoo/image/upload?relationtype=50&iskdr=1"
	})
});
</script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">添加新闻</a><span class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form" method="post">
						<input type="hidden" value="${bean.id }" id="id" name="id" /> 
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>标题：</label>
							</div>
							<div class="tjcpxx-con-form1">
							
								<input class="tjcpxx-fprm-inp" style="width:365px;" id="title" maxlength="30" onblur="value=value.trim()" type="text" value="<c:out value='${bean.title}'></c:out>">
							</div>
						</div>

						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1" id="div_image2">
								<label><span class="red marrig5">*</span>标题图片：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<div class="tjcpxx-con-form-upimg">
									<img id="loadimgTitle" width="120px" height="115px" src="" alt="请上传图片" />
								</div>
								<input type="hidden" name="titlePic" value="" />
								<div style="width: 200px; float: left; position: relative; padding-left: 30px;">
									<input type="button" value="选择图片" class="h_scimgbut" id="buttonid2" /> 
									<input type="file" id="singlefileTitle" name="pics" onchange="changeTitleFile(this)" class="filemhbut"
										style="top: 10px; left: 27px;" />
									<div>
										<input type="button" value="本地上传" class="h_scimgbut h_scimgbut2" />
									</div>
								</div>
							</div>
						</div>
						<br>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1" id="div_image">
								<label><span class="red marrig5">*</span>主题图片：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<div class="tjcpxx-con-form-upimg">
									<img id="loadimg" width="120px" height="115px" src="" alt="请上传图片" />
								</div>
								<input type="hidden" name="img" value="" />
								<div style="width: 200px; float: left; position: relative; padding-left: 30px;">
									<input type="button" value="选择图片" class="h_scimgbut" id="buttonid" /> 
									<input type="file" id="singlefile" name="pics" onchange="changeFile(this)" class="filemhbut"
										style="top: 10px; left: 27px;" />
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
								<label><span class="red marrig5">*</span>详情：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<textarea name="content" id="content" style="height: 300px; visibility: hidden">${bean.content }</textarea>
							</div>
						</div>
						<div class="tjcpxx-con-mk1" style="height: 15px;">
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title" >
								<label>发布状态：</label>
							</div>
							<div id="divStatus" class="tjcpxx-con-form huise">
								<span class='marrig35'></span>
								<c:choose>
									<c:when test="${bean.state==1 }">
										<input name='state' checked type='radio' value='1'>
										<span>发布</span>
										<input name='state' type='radio' value='0'>
										<span>未发布</span>
									</c:when>
									<c:otherwise>
										<input name='state' type='radio' value='1'>
										<span>发布</span>
										<input name='state' checked type='radio' value='0'>
										<span>未发布</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<span class="marrig35"></span>
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
</div>
<script type="text/javascript">
$(document).ready(function() {
    if ('' != '${bean.id}') {
        $("#loadimg").attr("src", '${bean.img}');
        $("input[name='img']").val('${bean.img}');
        $("#loadimgTitle").attr("src", '${bean.titlePic}');
        $("input[name='titlePic']").val('${bean.titlePic}');
    }

    $(".h_scimgbut1").click(function() {
        if ($("#singlefile").val()) {
            $.ajaxFileUpload({
                url: "/app/api/img/upload",
                secureuri: false,
                fileElementId: 'singlefile',
                dataType: "json",
                data: {
                    relationtype: 50
                },
                type: 'POST',
                success: function(result) {
                    if (parseInt(result.desc) >= 512000) {
                        Dalert("图片大小不能超过500kb");
                    } else {
                        if (result.data != "") {
                            if (result.code == 0) {
                            	Dalert("上传成功");
                                $("input[name='img']").val(result.data);
                                $("#loadimg").attr("src", result.data[0]);
                            } else {
                                $("#loadimg").attr("src", "");
                                Dalert("上传图片失败");
                            }
                        }
                    }
                },
                error: function(e) {
                    alert(JSON.stringify(e));
                },
                complete: function() {}
            });
            $("#singlefile").remove();
            var input = '<input type="file" id="singlefile" name="pics" class="filemhbut"  onchange="changeFile(this);"  style="top: 10px; left: 27px;" />';
            $("#buttonid").after(input);
        }
    });
    //上传标题图标
    $(".h_scimgbut2").click(function() {
        if ($("#singlefileTitle").val()) {
            $.ajaxFileUpload({
                url: "/app/api/img/upload",
                secureuri: false,
                fileElementId: 'singlefileTitle',
                dataType: "json",
                data: {
                    relationtype: 50
                },
                type: 'POST',
                success: function(result) {
                    if (parseInt(result.desc) >= 512000) {
                        Dalert("图片大小不能超过500kb");
                    } else {
                        if (result.data != "") {
                            if (result.code == 0) {
                            	Dalert("上传成功");
                                $("input[name='titlePic']").val(result.data);
                                $("#loadimgTitle").attr("src", result.data[0]);
                            } else {
                                $("#loadimgTitle").attr("src", "");
                                Dalert("上传图片失败");
                            }
                        }
                    }
                },
                error: function(e) {
                    alert(JSON.stringify(e));
                },
                complete: function() {}
            });
            $("#singlefileTitle").remove();
            var input = '<input type="file" id="singlefileTitle" name="pics" class="filemhbut"  onchange="changeTitleFile(this);"  style="top: 10px; left: 27px;" />';
            $("#buttonid2").after(input);
        }
    });
});
    
   

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
            var objUrl = getObjectURL(object.files.item(0));
            if (objUrl) {
                $("#loadimg").attr("src", objUrl);
            } else {
                $("#loadimg").attr("src", "");
            }
        }
    } else {
        var objUrl = getObjectURL(object.files.item(0));
        if (objUrl) {
            $("#loadimg").attr("src", objUrl);
        } else {
            $("#loadimg").attr("src", "");
        }
    }
}
//
function changeTitleFile(object) {
    var agent = navigator.userAgent.toLowerCase();
    if (agent.indexOf("msie") > 0) {
        var version = agent.match(/msie [\d.]+;/gi)[0];
        if (version == 'msie 9.0;') {
            object.select();
            // object.blur();
            $('#div_image2').focus();
            var nfile = document.selection.createRange().text;
            // alert("当前选择的文件完整路径是:"+nfile);
            document.selection.empty();
            document.getElementById("loadimgTitle").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + nfile + "')";
        } else {
            var objUrl = getObjectURL(object.files.item(0));
            if (objUrl) {
                $("#loadimgTitle").attr("src", objUrl);
            } else {
                $("#loadimgTitle").attr("src", "");
            }
        }
    } else {
        var objUrl = getObjectURL(object.files.item(0));
        if (objUrl) {
            $("#loadimgTitle").attr("src", objUrl);
        } else {
            $("#loadimgTitle").attr("src", "");
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
function formCancel() {
    location.href = "/zoo/news/list";
}
</script>
