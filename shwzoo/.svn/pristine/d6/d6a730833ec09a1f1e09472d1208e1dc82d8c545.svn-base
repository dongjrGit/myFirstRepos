<!-- @{
    ViewBag.Title = "添加类别";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="/resource/public/platform/js/SiteManagement/AddNavClass.js"></script>
<script type="text/javascript">

var relevance="${vo.relevance}";

$(function(){
	$.each($("#parentID1 option"),function(){
		if(relevance.indexOf(this.value)>=0){
			$(this).attr("selected","selected");
			flselect(this)
		}
	});
	
});
function flselect(obj){
	if(obj.value!=0){
	 $.ajax({
         url: "/platform/page/queryClassSub",
         type: "Post",
         data: {id:obj.value},
         dataType: "json",
         success: function (data) {
             if (data.code < 0) {
                 Dalert(data.desc);
             } else {
            	 var fldata=data.data;
					
            	 if(fldata.length<=0){
            		 return;
            	 }
            	 var htmls="";
                 htmls+='<select name="parentId" class="the-form-select-one" id="list_fl" onchange="flselect(this)">';
                 htmls+='<option value="0">无</option>';
                 $.each(fldata,function(index){
                     htmls+='<option value="'+this.id+'">'+this.classname+'</option>';
                 });
              	 htmls+='</select>';
              	 $(obj).nextAll().remove();
              	 $("#flbox").append(htmls);
                 
                 $.each($("#list_fl option"),function(){
             		if(relevance.indexOf(this.value)>=0){
             			$(this).attr("selected","selected");
             			$(this).onclick();
             		}
             	});
             }
         },
         error: function () {

         }
     });
	}else{
		 $(obj).nextAll().remove();
	}
}

</script>
<div class="clear"></div>
<div class="mainright">

    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);" id="contitle">添加文章分类</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="form">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>分类名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="ClassName" name="className" value="${vo.classname }" type="text">
                    </div>
                    <input type="hidden" value="${vo.id }" name="id"/>
                </div>
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>所属页面：</label></div>
                    <div class="tjcpxx-con-form1">
                        <select name="pageType" id="pageType" class="the-form-select-one">
	                        <c:forEach items="${list }" var="data">
	                        	<option <c:if test="${vo.pageType == data.value }">selected="selected"</c:if>  value="${data.value }">${data.name }</option>
	                        </c:forEach>
                        </select>
                    </div>
                    
                </div>
                <div class="tjcpxx-con-mk">
                    <div class="tjcpxx-con-form-title"><span class="red">*</span>使用站点：</div>
                    <div class="tjcpxx-con-form">
                        <input name="webSet"  <c:if test="${fn:indexOf(vo.webSet,'1')>=0}">checked="checked"</c:if> type="checkbox" value="1"><span>pc端</span>
                        <span class="marrig35"></span>
                        <input name="webSet"  <c:if test="${fn:indexOf(vo.webSet,'2')>=0}">checked="checked"</c:if> type="checkbox" value="2"><span>app端</span>
                        <span class="marrig35"></span>
                        <input name="webSet"  <c:if test="${fn:indexOf(vo.webSet,'3')>=0}">checked="checked"</c:if> type="checkbox" value="3"><span>wap端</span>
                        <span class="marrig35"></span>
                        <input name="webSet"  <c:if test="${fn:indexOf(vo.webSet,'4')>=0}">checked="checked"</c:if> type="checkbox" value="4"><span>微信端</span>
                    </div>
                </div>
                
                <div class="tjcpxx-con-mk" >
                    <div class="tjcpxx-con-form-title"><span class="red">*</span>所属分类：</div>
                    <div class="tjcpxx-con-form" id="flbox">
                     <select name="parentId" id="parentID1" class="the-form-select-one" onchange="flselect(this)">
                    <option value="0">无</option>
	                        <c:forEach items="${fl }" var="data">
	                        <option   value="${data.id }" >${data.classname }</option>
	                        </c:forEach>
                     </select>
                     
                    </div>
                </div>
              
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>排序：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" value="${vo.sort }" id="sort" name="sort" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise">
                        <input class="preserve-inp marrig35 mar35" name="" id="savebtn" type="button" value="保存">
                        <input class="preserve-inp_hs" name="" id="backbtn" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->

            </form>
        </div>
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->
