<!-- @{
    ViewBag.Title = "专题管理";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/decorators/getFileUrl.jsp"%>

<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script src="/resource/public/platform/js/SY/SpecialProTypeEdit.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="floortitle">专题商品分类管理</a><span class="sj-img"></span></li>
            </ul>
        </div>
			
        <div class="tjcpxx-con-con">
            <form id="floorForm">
            <input type="hidden" id="topicid" value="${specialProtype.specialid }">
            <input type="hidden" id="topicid1" value="${special}">
            <input type="hidden" id="id" value="${id}">
            <input type="hidden" id="imgsrc" value="<%=path %>" />
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>分类名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="spname" name="spname" type="text" value="">
                    </div>
                </div>   
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>显示名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="showname" name="showname" type="text"  value="">
                    </div>
                </div>   
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>分类图片：</label></div>
                    <div class="tjcpxx-con-form1">
                        <div class="tjcpxx-con-form-upimg">
                            <img id="loadimg" width="120px" height="115px" src="" />
                        </div>
                        <input type="hidden" id="saveimg" name="img" />
                        <div style="width: 200px; float: left;position: relative;left: 30px;">
						<input type="button" value="选择图片" class="h_scimgbut"> 
						<input type="file" name="pics" id="singlefile"  class="filemhbut" style="top: 10px;">
					    <div>
						     <input type="button" value="本地上传" class="h_scimgbut h_scimgbut1">
						     
					    </div>
						</div>
                        <!-- <div style=" width:200px;margin-left:10px; float:left;">
                            <input type="file" id="selectimg" name="selectimgs" />
                            <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a>
                        </div> -->
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1" style="position:relative;">
                        <label for="selectimg" class="error"></label>
                        <span class="beizhu-mc-upimg">上传图片要小于500kb</span>
                    </div>
                </div>
               
                 <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>排序</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="sort" name="sort" type="text"  value="">
                    </div>
                </div>

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise">
                        <input type="hidden" id="hidden_memberid" value="" />
                        <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="button" value="保存">
                        <input class="preserve-inp_hs" name="backBtn" id="backBtn" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->

            </form>
        </div>

    </div><!--tjcpxx stop -->
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
    	getInfo();
    	var topicid = $("#topicid").val();
    	var topicid1 = $("#topicid1").val();
    	//返回按钮点击
        $("#backBtn").bind("click", function () {
        	
        	//var spmark = $("#spmark").val();
        	
        	if(topicid==""){
        		window.location.href = '/platform/topic/showSpeProList?topicid=' + topicid1;
        	}else{
        		window.location.href = '/platform/topic/showSpeProList?topicid=' + topicid;
        	}
        });

    })
</script>
