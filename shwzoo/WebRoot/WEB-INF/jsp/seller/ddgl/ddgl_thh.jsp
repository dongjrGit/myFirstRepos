<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"	src="/resource/ajaxfileupload.js"></script>
<script src="/resource/public/seller/js/DdGl/orderthh.js"></script>
<script src="/resource/public/commonjs/showImage.js"></script>
<style>
    .tjcpxx-con-form-upimg {
        display: table-cell;
        width: 120px;
        height: 115px;
        background: #f4f4f4;
        border: 1px solid #D9D9D9;
        padding: 1px;
        text-align: center;
        line-height: 115px;
        float: left;
    }
    .tjcpxx-con-form-upthis{ width:105px; height:35px; float:left; background:#FFE5E6; border:1px solid #D9D9D9; margin:30px auto auto 5px; text-align:center; line-height:35px;}
    .mar35 {
        margin-top: 35px;
    }

    .mar10 {
        margin-top: 10px;
    }
</style>
<div id="bigimg" class="l_imgbig" style="display:none;">
    <img alt="" />
    <div class="l_close">X</div>
</div>
<div class="l_ckxqcon_con">
    <table>
        <tr>
            <td class="text-right">问题描述：</td>
            <td width="365px">
                <div class="l_mjthyy">${apply.content }</div>
                <input type="hidden" id="imgsrc" value="<%=path %>" />
                <input name="Id" type="hidden" value="${detail.id }" />
                <input name="Status" type="hidden" value="${detail.status }" />
            </td>
        </tr>
        <%-- <tr>
            <td class="text-right">买家提供图片：</td>
            <td>
                 <c:choose>
            <c:when test="${imglist==null}">
            <span>买家未提供图片</span>
            </c:when>
            <c:otherwise>
             <ul class="l_ckxqul">
            <c:forEach var="img" items="${imglist}">
               <li class="tjcpxx-con-form-upimg" style="width:100px;">
               <img id="${img.id }" height="80px" width="100px" alt="" src="<%=path %>${img.imgurl }" />
               </li>
            </c:forEach>
             </ul>
            </c:otherwise>
            </c:choose>

            </td>
        </tr> --%>
        <!-- <tr>
            <td class="text-right">审核回复：</td>
            <td width="365px">
                <textarea name="reason" cols="" rows="" class="l_ckxqarea"></textarea>
            </td>
        </tr> -->
        <tr>
            <td class="text-right"></td>
            <td width="365px">
                <!-- <div class="tjcpxx-con-form-upimg"><img id="loadimg" width="120px" height="115px" /></div>
                <div style=" width:200px; float:right;">
                    <input type="hidden" name="imgUrl" value="" />
                     <input type="file" name="pics" id="singlefile" />
                    <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a>
                </div> -->
                <div class="clear"></div>
                <div class="l_ckxqbotm">
                    <!-- <a href="javascript:void(0);" name="ty"><span class="l_ty">同意</span></a>
                    <a href="javascript:void(0);" name="bty"><span class="l_bty">不同意</span></a> -->
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
                     $("input[name='imgUrl']").val(result.data[0]); 
                     if (result.code == 0){
                    	 Dalert("上传成功");                    	 
                     	$("#loadimg").attr("src",$("#imgsrc").val()+result.data[0]);
                     }
                     else
                         $("#loadimg").attr("src", "");
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