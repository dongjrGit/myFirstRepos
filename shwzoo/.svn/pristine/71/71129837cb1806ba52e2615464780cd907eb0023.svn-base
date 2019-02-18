
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/commonjs/showImage.js"></script>
<style>
.servicexx-tabbottom{ width:100%; border-collapse:collapse; background:#fff;}
.servicexx-tabbottom tr td{ border:1px solid #E5E5E5; padding:15px 10px 15px 15px;}
.servicexx-tabbottom tr td.left-sertabbottom{ width:85px; }
</style>
<div id="bigimg" class="l_imgbig" style="display:none;">
    <img alt="" />
    <div class="l_close">X</div>
</div>
<div class="l_ckxqcon_con">
    
     <table class="servicexx-tabbottom">         
          <c:forEach var="apply" items="${applys}" varStatus="status">           
               <tr>
                  <td class="left-sertabbottom">
                   <c:choose>
                   <c:when test="${apply.datatype==0}">
                    	问题描述
                   </c:when>
                   <c:when test="${apply.datatype==1}">
                    	卖家反馈
                   </c:when>
                   <c:otherwise>
                     	平台反馈
                   </c:otherwise>
                   </c:choose>
                  </td>
                  <td>
                      <p>${apply.content}</p>
                      <div class="l_fxwtimg">
                      <c:if test="${apply.imgurl!=null}">
                      	    <c:choose>
							<c:when test="${fn:contains(apply.imgurl,',')==false}">
							 <img src="${apply.imgurl}" style="width:80px;height:80px"> 
							</c:when>
							<c:otherwise>
							  <c:set value="${fn:split(apply.imgurl, ',')}" var="imgs" />
							  <c:forEach items="${imgs}" var="img">
								<img src="${img}" style="width:80px;height:80px">
							  </c:forEach>
							</c:otherwise>
							</c:choose>                          
                      </c:if>
                                                             
                      </div>
                  </td>
              </tr>              
               </c:forEach>
          </table>
          <!-- 点击查看大图 -->
		<div id="bigimg" class="l_lbimg" style="display: none;">
			<img alt="" />
			<div class="l_close">X</div>
		</div>
 </div>

<script type="text/javascript">
    $(document).ready(function () {
    		
        $(".l_fxwtimg img").each(function () {
            //为img绑定单机查看大图事件
            $(this).click(function () {
                if ($(this).attr("src") != "" && $(this).attr("src") != undefined)
                    //公用方法 查看大图
                    Show("bigimg", $(this).attr("src"));
            });
        });
        //关闭弹出层事件
        $(".l_close").bind("click", function () { $("#bigimg").hide(); });
        $("#bigimg img").bind("click", Jump);
    })
   //点击大图跳转方法
	function Jump() {
		  var imgurl = $("#bigimg img").attr("src");
	        imgJump(imgurl);
	}
    function Xclose() {
        parent.window.closeDialog();
    }
</script>
