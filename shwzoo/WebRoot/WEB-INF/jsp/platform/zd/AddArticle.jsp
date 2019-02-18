<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<style>
    form {
        margin: 0;
    }

    textarea {
        display: block;
    }
</style>
<link href="${ctx }/resource/public/commonjs/kindeditor-4.1.10/themes/default/default.css" rel="stylesheet" />

<script src="${ctx }/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
<script src="${ctx }/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script src="/resource/public/platform/js/SiteManagement/AddArticle.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script>
$(function(){
	$(".select2").select2();
});
</script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="contitle">添加文章</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
            <div class="tjcpxx-con-con">
                <form id="ArticleForm">
                  <input name="partid" id="partid" type="hidden" value="${partid}">
                  <input name="id" id="id" type="hidden" value="${art.id}">
                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"><span class="red marrig5">*</span><label>标题：</label></div>
                        <input class="tjcpxx-fprm-inp" name="title" id="title" type="text" value=${art.title }>                  
                    </div><!--tjcpxx-con-mk stop -->
                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"><label>摘要：</label></div>

                        <input class="tjcpxx-fprm-inp" name="digest" id="digest" type="text" value=${art.digest }>

                    </div>
                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"><label>副标题：</label></div>

                        <input class="tjcpxx-fprm-inp" name="bytitle" id="bytitle" type="text"  value=${art.bytitle }>

                    </div>
                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"><span class="red marrig5">*</span><label>内容：</label></div>

                        <textarea name="content1" id="content1" style="width:750px;height:400px;visibility:hidden;" required></textarea>
                        <input name="content" id="content" type="hidden" value="${art.artcontent}">
                        <label for="content1" class="error"></label>

                    </div><!--tjcpxx-con-mk stop -->
                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"><label>图片链接：</label></div>
                        <div class="tjcpxx-con-form1">
                            <div class="tjcpxx-con-form-upimg"><img width="120px" height="115px" src="${art.imgurl}" /></div>
                            <div style=" width:200px; float:left;margin-left:20px;">
                                <input type="file" id="file_imgUrl" name="file_imgUrl" /><input type="hidden" name="imgurl" value="${art.imgurl}" />
                                <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a>
                            </div>
                        </div>

                    </div>
                    
                    
                    

				
                <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"></div>
                        <div class="tjcpxx-con-form1 huise">
                            <input class="preserve-inp marrig35 mar35" name="" id="savebtn" type="button" value="保存">
                             <input name="action" id="action" type="hidden" value="${action}">
                            <span class="mar35 marrig35"></span>
                            <input class="preserve-inp_hs" name="" onclick="javascript:location.href='/platform/zd/wzzx';" id="backbtn" type="button" value="返回">
                        </div>
                 </div><!--tjcpxx-con-mk stop -->

				

                </form>
            </div>
    </div><!--tjcpxx stop -->



</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
    	
        $(".tjcpxx-con-form-upthis").each(function () {
            $(this).bind("click", function (e) {
                var $imgurl = $(this).parent().parent().find("input").last();
                var $imgsrc = $(this).parent().parent().parent().find("img").first();
                var elementid = $(this).parent().parent().find("input").first().attr("id");
                $.ajaxFileUpload({
                    url: "/app/api/img/upload",
                    secureuri: false,
                    fileElementId: elementid,
                    dataType: "json",
                    //ftype:上传文件类型（图片文件=1，其他文件=2）
                    //module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
                    data: { relationtype : 6 },
                    type: 'POST',
                    success: function (result) {
                        //  alert(JSON.stringify(result));
                        if (result.code == 0) {
                        	Dalert("上传成功");
                            $imgurl.val(result.data[0]);
                            $imgsrc.attr("src", result.data[0]);
                        }
                        else {
                            $imgsrc.attr("src", "");
                            alert(result.desc);
                        }
                        //TODO 结束正在加载中
                    },
                    error: function (e) {
                        alert(JSON.stringify(e));
                        //TODO 结束正在加载中
                    }
                });
            });
        })       
    })
</script>