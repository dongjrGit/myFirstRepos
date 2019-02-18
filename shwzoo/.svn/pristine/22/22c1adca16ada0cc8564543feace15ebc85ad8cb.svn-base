<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script type="text/javascript" src="/resource/public/platform/js/Findrecord/Findrecordadd.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/commonjs/jquery.form.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=v66Wo0IkRp2uWVi5RNsaj656"></script>
<script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script>

<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0)">添加新图片</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="addFindForm" action="#" method="post">

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>标题：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_title" id="text_title" type="text" value="${findrecord.title }">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                
              <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>发现类型</label></div>
                    <div class="tjcpxx-con-form1" >
                        <select name="text_type" id="text_type" class="the-form-select-one">
                        	<option value="0" >请选择</option>
                            <option value="1"  <c:if test="${findrecord.type==1 }">selected="selected"</c:if> >店铺动态</option>
                            <option value="2"  <c:if test="${findrecord.type==2 }">selected="selected"</c:if> >专题</option>
                            <option value="3"  <c:if test="${findrecord.type==3 }">selected="selected"</c:if> >资讯文章</option>
                            <option value="4"  <c:if test="${findrecord.type==4 }">selected="selected"</c:if> >外部链接</option>   
                        </select>
                    </div>
                </div><!--tjcpxx-con-mk stop  -->
                 <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>外链url：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_url" id="text_url" type="text" value="${findrecord.url}">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>上传图片：</label></div>
                           <div class="l_dpxxtj" id="div_findimage">
                           <input type="hidden" id="imgsrc" value="<%=path %>" />
                                        <input type="file" name="pic" id="file_findimage" hidden />
                                            <c:if test="${findrecord.imgurl  == null }">
                                            <img src="/resource/public/seller/images/djsc.png" id="img_findimage" width="107" height="100" name="img_findimage">
                                           </c:if> 
                                           <c:if test="${findrecord.imgurl != null }">
                                            <img src="<%=path %>${findrecord.imgurl }" id="img_findimage" width="107" height="100" name="img_findimage">
                                            </c:if>
                                            <input type="hidden" name="findimage" value="${findrecord.imgurl }" id="findimage" />
                                       
                                    </div>
                                
                </div><!--tjcpxx-con-mk stop -->
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>状态：</label></div>
                    <div class="tjcpxx-con-form1">
                    	<c:choose>
                    		<c:when test="${ findrecord.status==0}">
                    			<input type="radio" id="radio_status" name="radio_status" value="1"/>启用 
                    			<input type="radio"
									name="radio_status"  id="radio_status"  value="0" checked />禁用
                    		</c:when>
                    		<c:otherwise>
                    			<input type="radio" id="radio_status" name="radio_status" value="1" checked="ischecked"/>启用 
                    			<input type="radio"
									name="radio_status"  id="radio_status"  value="0"  />禁用
                    		</c:otherwise>
                    	</c:choose>
                       
                       

                    </div>
                </div><!--tjcpxx-con-mk stop -->
				<div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>排序：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_sort" id="text_sort" type="text" value="${findrecord.sort}">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                    <input type="hidden" id="hidden_findid" value="${findid }" />
                        <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="submit" value="保存">
                        <input class="preserve-inp_hs" name="btn_goback" id="btn_goback" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
            </form>
        </div>
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
    	init.bind();
        //表单验证
        Vaildate.bind();
        //返回按钮点击
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/find/showFindRecord";
        });
    })
</script>
