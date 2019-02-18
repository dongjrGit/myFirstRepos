<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script src="/resource/public/platform/js/jscolor/jscolor.js" type="text/javascript"></script>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script src="/resource/public/platform/js/SY/SpecialsEdit.js"></script>
<div class="mainright">

    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="floortitle">专题管理</a><span class="sj-img"></span></li>
            </ul>
        </div>

        <div class="tjcpxx-con-con">
            <form id="floorForm">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>专题名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="spname" name="spname" type="text">
                    </div>
                </div>   
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>页面标识</label></div>
                    <div class="tjcpxx-con-form1">
                        <select name="pagetag" id="pagetag" class="the-form-select-one">
                            <option value="-1">请选择</option>
                            <option value="0">首页</option>
                            <option value="31">资讯页</option>
                        </select>
                    </div>
                </div>              
                 <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>专题标识</label></div>
                    <div class="tjcpxx-con-form1">
                        <select name="spmark" id="spmark" class="the-form-select-one">
                            <option value="0">请选择</option>
                             <option value="9">猜你喜欢</option>
                             <option value="22">热门推荐</option>               
                             <option value="23">今日特价</option>    
                        </select>
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                           
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>图片：</label></div>
                    <div class="tjcpxx-con-form1">
                        <div class="tjcpxx-con-form-upimg">
                            <img id="loadimg" width="120px" height="115px" src="" />
                        </div>
                        <input type="hidden" id="saveimg" name="img" />
                        <div style=" width:200px;margin-left:10px; float:left;">
                            <input type="file" id="selectimg" name="selectimgs" />
                            <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a>
                        </div>
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
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>专题状态</label></div>
                    <div class="tjcpxx-con-form1">
                        <select name="spstatus" id="spstatus" class="the-form-select-one">
                        	<option value="0">启用</option>
                            <option value="1">禁用</option>                                
                        </select>
                    </div>
                </div><!--tjcpxx-con-mk stop  --> 
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>专题描述：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="spdesc" name="spdesc" type="text">
                    </div>
                </div>
              
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise">
                        <input type="hidden" id="hidden_memberid" value="" />
                        <input type="hidden" id="sptype" name="sptype"  value="3" />
                        <input type="hidden" id="useplatform" name="useplatform"  value="2" />
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
        //返回按钮点击
        $("#backBtn").bind("click", function () {
            window.location.href = "/platform/topic/list";
        });
    })
</script>
