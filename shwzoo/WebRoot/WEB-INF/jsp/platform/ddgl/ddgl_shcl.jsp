
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script src="/resource/public/commonjs/showImage.js"></script>
<div id="bigimg" class="l_imgbig" style="display:none;">
    <img alt="" />
    <div class="l_close">X</div>
</div>
<div class="l_ckxqcon_con">
    <table>
        <tr>
            <td class="text-right">买家原因：</td>
            <td width="365px">
                <c:forEach var="apply" items="${applys}">
                 <c:if test="${apply.datatype==0}">
                  <div class="l_mjthyy">${apply.content }</div>
                 </c:if>
                </c:forEach>
                <input type="hidden" id="imgsrc" value="<%=path %>" />
                <input name="Id" type="hidden" value="${detail.id }" />
                <input name="Status" type="hidden" value="${detail.status }" />
            </td>
        </tr>
        
        <tr>
            <td class="text-right" style="width:125px;">买家提供图片：</td>
            <td>
            <c:forEach var="apply" items="${applys}">
              <c:if test="${apply.datatype==0}">
	            <c:choose>             
	            <c:when test="${apply.imgurl==null}">
	            <span>买家未提供图片</span>
	            </c:when>
	            <c:otherwise>
	            <ul class="l_ckxqul">	             
	                 <c:choose>
						<c:when test="${fn:contains(apply.imgurl,',')==false}">
						  <li class="tjcpxx-con-form-upimg" style="width:100px;">
						  <img src="${apply.imgurl}" style="height:80px;width:100px" > 
						  </li>
						</c:when>
						<c:otherwise>
						  <c:set value="${fn:split(apply.imgurl,',')}" var="imgs" />
						  <c:forEach items="${imgs}" var="img">
						    <li class="tjcpxx-con-form-upimg" style="width:100px;">
							<img src="${img}" style="height:80px;width:100px">
							</li>
						  </c:forEach>
						</c:otherwise>
					</c:choose> 	             
	              </ul>    
	             </c:otherwise>
	             </c:choose>
             </c:if>
            </c:forEach>
            </td>
        </tr>
       
        <tr>
            <td class="text-right">卖家确认原因：</td>
            <td width="365px">
               <c:forEach var="apply" items="${applys}">
                 <c:if test="${apply.datatype==1}">
                  <div class="l_mjthyy">${apply.content }</div>
                 </c:if>
                </c:forEach>
            </td>
        </tr>
       
        <tr>
        <td class="text-right" style="width:125px;">卖家提供图片：</td>
            <td>
            <c:forEach var="apply" items="${applys}">
             <c:if test="${apply.datatype==1}">
	            <c:choose>
	            <c:when test="${apply.imgurl==null}">
	            <span>卖家未提供图片</span>
	            </c:when>
	            <c:otherwise>
	             <ul class="l_ckxqul">
                   <li class="tjcpxx-con-form-upimg" style="width:100px;">                   
                   <img src="${apply.imgurl}" style="height:80px;width:100px">
                   </li>   
	             </ul>
	            </c:otherwise>
	            </c:choose>
	           </c:if>
            </c:forEach>
            </td>
        </tr>
                
        <tr>
            <td class="text-right">平台确认原因：</td>
            <td width="365px">
                <textarea name="reason" cols="" rows="" class="l_ckxqarea"></textarea>
            </td>
        </tr>
        
        <tr>
            <td class="text-right">平台提供图片：</td>
            <td width="365px">
                <div class="tjcpxx-con-form-upimg"><img id="loadimg" width="120px" height="115px" /></div>
                <div style=" width:200px; float:right;">
                    <input type="hidden" name="imgUrl" value="" />
                     <input type="file" name="pics" id="singlefile" />
                    <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a>
                </div>
                <div class="clear"></div>
                <div class="l_ckxqbotm">
                    <a href="javascript:void(0);" id="dealbtn"><span class="l_ty">同意</span></a>                   
                    <a href="javascript:void(0);" onclick="Xclose()"><span class="l_bty">关闭</span></a>
                </div>

            </td>
        </tr>        
       
    </table>
 </div>

<script type="text/javascript">
    $(document).ready(function () {
        $(".tjcpxx-con-form-upthis").click(function () {
            $.ajaxFileUpload({
            	url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//relationtype: 模块（订单 (5);）
				data : {
					relationtype : 5
				},
                type: 'POST',
                success: function (result) {                    
                    if (result.code == 0){ 
                    	Dalert("上传成功");
                    	$("input[name='imgUrl']").val(result.data[0]);
                    	$("#loadimg").attr("src",$("#imgsrc").val()+result.data[0]);
                    }else{
                        $("#loadimg").attr("src", "");
                    }
                    //TODO 结束正在加载中
                },
                error:function(e){
                	 alert(JSON.stringify(e));
                }
            });
        });
    		
        $(".l_ckxqul img").each(function () {
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
        //确认按钮
        $("#dealbtn").click(function(){
        	var id = $("input[name=Id]").val();
       	    var type = $("input[name=Status]").val();
       	    var reason = $("textarea[name=reason]").val(), img = $("input[name=imgUrl]").val();
       	    $.ajax({
       	        url: "/platform/order/dealafterorder",
       	        type: "Post",        	        
       	        data: { "id": id, "status": type, "reason": reason, "img": img},
       	        dataType: "json",
       	        success: function (data) {
       	            if (data.code < 0) {
       	                Dalert(data.desc);
       	            } else {
       	                Dalert(data.desc,"",refresh);        	                
       	            }
       	        }
       	    });
        })
    })
   //点击大图跳转方法
	function Jump() {
		  var imgurl = $("#bigimg img").attr("src");
	        imgJump(imgurl);
	}
    function Xclose() {
        parent.window.closeDialog();
    }
    //刷新
    function refresh() {
    parent.location.reload();
    }
</script>
