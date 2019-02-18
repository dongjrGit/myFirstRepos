<!-- @{
    Layout = "~/Areas/Seller/Views/Shared/_Layout_Seller_Center.cshtml";
    ViewBag.Title = "我的店铺 &gt; 店铺管理 &gt; 店铺状态变更";
    var shopID = ViewData["shopID"];
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<link href="${ctx }/resource/public/commoncss/jQuery.Validate.css" rel="stylesheet" />
<script src="${ctx }/resource/public/commonjs/jquery.validate.js"></script>
<script src="${ctx }/resource/public/commonjs/messages_cn.js"></script>
<script src="/resource/public/seller/js/MyShop/ShopStauChange.js?v=1.01"></script>
<div id="container">
    <div class="allcon">
        <input type="hidden" id="shopID" value="${shopid }" />
        <div class="position">
            您所在的位置：我的店铺 &gt; 店铺管理 &gt; 店铺状态
        </div><!--所在位置信息  结束 -->
        <div class="dpztbg">
            <form id="stachange">
                <table>
                    <tr>
                        <td width="120px">当前店铺状态：</td>
                        <td><div id="staNow"></div></td>
                    </tr>
                    <tr>
                        <td>店铺状态变更为：</td>
                        <td>
                            <select name="selectsta" class="sel-form-dpztbg" id="selectsta" required>
                                <option value="">请选择</option>
                                <option value="5">打烊</option>
                                <option value="4">营业</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="vertical-align:top">原因：</td>
                        <td><textarea name="reason" id="reason" class="form-area"></textarea></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td style="padding-top:10px;">
                            <input name="" type="button" id="savebtn" value="保存" class="big-but">
                            <input name="" type="button" id="clearbtn" value="清空" class="big-but-huise">
                        </td>
                    </tr>
                </table>
            </form>
        </div><!--dpztbg  stop-->
    </div><!--主要内容 右边结束 -->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#stachange").validate();
    });
</script>
