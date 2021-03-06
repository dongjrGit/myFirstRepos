﻿<!-- @{
    ViewBag.Title = "专题管理";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/decorators/getFileUrl.jsp"%>

<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script src="/resource/public/platform/js/SY/SpecialsEdit.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
    <input type="hidden" name="sptype" id="sptype" value="3"/>  <input type="hidden" name="spmark" id="spmark" value="12"/>
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="floortitle">专题管理</a><span class="sj-img"></span></li>
            </ul>
        </div>

        <div class="tjcpxx-con-con">
            <form id="floorForm">
            <input type="hidden" id="imgsrc" value="<%=path %>" />
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>专题名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="spname" name="spname" type="text">
                    </div>
                </div>   
                
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
                
              <!--   <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>子主题链接：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="subspurl" name="subspurl" type="text">
                    </div>
                </div>  --> 
                
               <!-- <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>楼层id</label></div>
                    <div class="tjcpxx-con-form1">
                        <select name="spfloor" id="spfloor" class="the-form-select-one">
                            <option value="0" selected>请选择</option>
                            <script id="floorselect" type="text/html">
                                {{each list as value index}}
                                <option value="{{value.code}}">{{value.name}}</option>
                                {{/each}}
                            </script>                         
                        </select>
                    </div>
                </div>--><!--tjcpxx-con-mk stop -->
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>专题类型</label></div>
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
                 <div class="tjcpxx-con-mk">
                <div class="tjcpxx-con-form-title"><span class="red">*</span>所属省：</div>
                <div class="tjcpxx-con-form">
                	 <select name="provinceid" id="provinceid" class="the-form-select-one">
                	 <c:forEach items="${provincelist }" var="vo">
                	 
                	 <option value="${vo.id }">${vo.name }</option>
                	 </c:forEach>
                	 	  
                	 	  
                	 </select>
                </div>
                </div>
                <div class="tjcpxx-con-mk1" style="display: none;">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>页面标识</label></div>
                    <div class="tjcpxx-con-form1">
                  
                        <select name="pagetag" id="pagetag" class="the-form-select-one">
                            <option value="-1">请选择</option>
                            <option value="0">首页</option>
                            <option value="1" selected="selected">专题页</option>
                            <option value="2">店铺页</option>
                            <option value="3">商品列表页</option>
                            <option value="4">购物车成功页</option> 
                             <option value="5">购物车页</option> 
                             <option value="6">支付成功页</option>
                            <option value="7">商品详情页</option> 
                             <option value="8">优惠卷领取页</option>
                             <option value="9">订单支付成功页 </option> 
                             <option value="10">支付失败页</option>
                            <option value="11">店铺商品列表页</option> 
                             <option value="12">抢购活动</option>
                             <option value="13">团购活动</option>                      
                        </select>
                    </div>
                </div>  
                
                
                <div class="tjcpxx-con-mk">
                <div class="tjcpxx-con-form-title"><span class="red">*</span>是否热门：</div>
                <div class="tjcpxx-con-form">
                	 	<input name='hotis' type="radio" value='1' checked><span>是</span>
                        <span class='marrig35'></span>
                        <input name='hotis' type='radio' value='0'><span>否</span>
                        <span class='marrig35'></span>
                </div>
                
                </div>
                <div class="tjcpxx-con-mk">
                    <div class="tjcpxx-con-form-title"><span class="red">*</span>使用平台：</div>
                    <div class="tjcpxx-con-form">
                        <input name='useplatform' type='checkbox' value='1'><span>pc端</span>
                        <span class='marrig35'></span>
                        <input name='useplatform' type='checkbox' value='2'><span>app端</span>
                        <span class='marrig35'></span>
                        <input name='useplatform' type='checkbox' value='3'><span>wap端</span>
                        <span class='marrig35'></span>
                        <input name='useplatform' type='checkbox' value='4'><span>微信端</span>
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
        //返回按钮点击
        $("#backBtn").bind("click", function () {
        	returnhref();
        });

        //初始化
       //Init.bind();

        //表单验证
        //Vaildate.bind();
    })
    
   	function returnhref(){
    	window.location.href = "/platform/topic/rmcslist";
    }
</script>
