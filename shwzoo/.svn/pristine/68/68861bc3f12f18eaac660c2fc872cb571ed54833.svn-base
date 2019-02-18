<!-- @{
    ViewBag.Title = "商品楼层管理";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script>
<script src="/resource/public/platform/js/SY/profloorsEdit.js"></script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">商品楼层管理</a><span class="sj-img"></span></li>

            </ul>
        </div>        
        <div class="tjcpxx">
            <div class="tjcpxx-con">
           
                <div class="tjcpxx-con-con">
                    <form id="form">
                    
                     <input type="hidden" id="imgsrc" value="<%=path%>" />
                     <input type="hidden" id="id" value="${id}" />
                     <input type="hidden" id="flid" value="${flid}" />
                     <input type="hidden" id="data" value="${data.id}" />
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>商品类型：</label></div>
                            <div class="tjcpxx-con-form1">
                                <select id="Type" onchange="selectChange()">
                                    <option value="0">商品</option>
                                    <option value="1">品牌</option>
                                    <option value="2">分类</option>
                                    <option value="3">专题</option>
                                </select>
                            </div>
                        </div>
                         
                        <div class="tjcpxx-con-mk1" name="fltype" id="spname" style="display:none">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>商品名称：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" type="text" name="spuName" id="spuName" />
                            </div>
                             <div name="spu_name_select" style="width: 500px;margin-top: 30px" >
                                <ul>
                                    <script id="select_spulist" type="text/html">
                                         {{each list as sp i}}
                                       		 <li data="{{sp.id}}">{{sp.name}}</li>
                                       	{{/each}}
                                    </script>
                                </ul>
                            </div>
                        </div>

                        <div class="tjcpxx-con-mk1" name="fltype" id="goodclass" style="display:none">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>商品分类：</label></div>
                            <input type="hidden" value="@ViewBag.Tid" id="tid" />
                            <input type="hidden" value="@ViewBag.Fid" id="fid" />
                            <input type="hidden" value="@ViewBag.Sid" id="sid" />

                            <select class="the-form-select-one" name="firstID" id="firstID" onchange="Class.firstChange(Class.callback, 'fc')">
                                <option value="0" id="defaultfc">无</option>
                                <script id="flist" type="text/html">
                                    {{each list as fclass i}}
                                    <option  value="{{fclass.id}}">{{fclass.name}}</option>
                                    {{/each}}
                                </script>
                            </select>
                            <select class="the-form-select-one" name="secondID" id="secondID" onchange="Class.firstChange(Class.callback, 'sc')">
                                <option value="0" id="defaultsc" selected>无</option>
                                <script id="slist" type="text/html">
                                    {{each list as sclass i}}
                                    <option value="{{sclass.id}}">{{sclass.name}}</option>
                                    {{/each}}
                                </script>
                            </select>
                            <select class="the-form-select-one" name="thirdID" id="thirdID">
                                <option value="0">无</option>
                                <script id="tlist" type="text/html">
                                    {{each list as tclass i}}
                                    <option value="{{tclass.id}}">{{tclass.name}}</option>
                                    {{/each}}
                                </script>
                            </select>
                        </div>
                        <div class="tjcpxx-con-mk1" name="fltype" id="brand" style="display:none">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>商品品牌：</label></div>
                            <div class="tjcpxx-con-form1">
                                <select id="brandlist">
                                    <script type="text/html" id="select_brand">
                                        {{each list as br i}}
                                        <option value="{{br.id}}">{{br.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </div><input class="tjcpxx-fprm-inp" name="spuPrice" type="hidden">
                        </div>
                        <div class="tjcpxx-con-mk1" name="fltype" id="spcials" style="display:none">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>商品专题：</label></div>
                            <div class="tjcpxx-con-form1">
                                <select id="speciallist">
                                    <script type="text/html" id="select_Specials">
                                        {{each list as br i}}
                                        <option value="{{br.id}}">{{br.title}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </div><input class="tjcpxx-fprm-inp" name="spuPrice" type="hidden">
                        </div>
                         <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>图片：</label></div>
                    <div class="tjcpxx-con-form1">
                        <div class="tjcpxx-con-form-upimg">
                        <c:choose>
                          <c:when test="${data != null }">
                          <img id="loadimg" width="120px" height="115px" src="<%=path %>${data.img }" />
                          </c:when>
                          <c:otherwise>
                          <img id="loadimg" width="120px" height="115px" src="" />
                          </c:otherwise>
                         </c:choose>
                        </div>
                        <input type="hidden" id="saveimg" name="img" value="${data.img }" />
                        <div style=" width:200px;margin-left:10px; float:left;">
                            <input type="file" id="selectimg" name="selectimgs" />
                            <a href="javascript:void(0);" style="color:#000"><div id="upload" class="tjcpxx-con-form-upthis">本地上传</div></a>
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
                        <!-- <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label>显示位置：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" id="display" name="display" value="" type="text">
                            </div>
                        </div> -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label>地址链接：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" id="url" name="url" value="" type="text">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>排序：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="orderby" id="orderby" type="text">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>显示位置：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="display" id="display" type="text">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>描述：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="desc" id="desc" type="text">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"></div>
                            <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                                 <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="button" value="保存">
                        		 <input class="preserve-inp_hs" name="backBtn" id="backBtn" type="button" value="返回">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->

                    </form>
                </div>
            </div><!--tjcpxx-con stop -->
        </div><!--tjcpxx stop -->
    </div>
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //返回按钮点击
        var flid=$("#flid").val();
        
        $("#backBtn").bind("click", function () {
            window.location.href = "/platform/floor/showProList?flid="+flid;
        });

        //初始化
        //Init.bind();

        //表单验证
        //Vaildate.bind();
    })
</script>
