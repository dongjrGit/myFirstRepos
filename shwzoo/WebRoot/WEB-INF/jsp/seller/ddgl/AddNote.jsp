

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<link href="${ctx }/resource/public/commoncss/jQuery.Validate.css" rel="stylesheet" />
    <script src="${ctx }/resource/public/commonjs/jquery.validate.js"></script>
    <script src="${ctx }/resource/public/commonjs/messages_cn.js"></script>
 <script src="${ctx }/resource/public/seller/js/DdGl/editNote.js"></script>
<%-- <script src="${ctx }/sv/js/MyShop/ShopNoticeEdit.js?v=1.01"></script> --%>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：订单管理 &gt; 订单管理管理 &gt; 退款退货备注
        </div><!--所在位置信息  结束 -->
        <div class="dpztbg">
            <form id="noticeEdit">
                <table>
               
                    <tr>
                        <td style="vertical-align:top">内容：</td>
                        <td><textarea name="content" id="content" class="form-area" required></textarea></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td style="padding-top:10px;">
                            <input name="" type="button" id="savebtn" value="保存" class="big-but">
                            <input name="" type="button" id="backbtn" value="返回" class="big-but-huise">
                        </td>
                    </tr>
                </table>
            </form>
        </div><!--dpztbg  stop-->
    </div><!--主要内容 右边结束 -->
</div>
