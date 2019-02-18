<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${pageContext.request.contextPath }/resource/dialog-min.js"></script>
<script src="${pageContext.request.contextPath }/resource/public/seller/js/spgl/spgl_sptj.js"></script>

<div id="container" style="width:350px;">
    <div class="zhgl-con" style="width:350px;">
        <div class="clear"></div>
        <div class="zhgl-con-con" style="width:350px;">
            <table>
                <tr>
                    <td style="width:120px;"><span class="red">*</span>调价类型：</td>
                    <td>
                        <select id="priceType" name="priceType" onchange="selectchange()" class="sel-form">
			               	<script id="flist" type="text/html">
                                {{each list as ad i}}
                                 <option value="{{ad.code}}">{{ad.name}}</option>
                                {{/each}}
                            </script>
                        </select>
                    </td>
                </tr>
        <%--         <tr id="spyj">
                    <td style="width:120px;"><span class="red">*</span>商品原价：</td>
                    <td><input name="" type="text" class="text-inp" value="<fmt:formatNumber value="${sku.oldprice}" pattern="0.00"/>"></td>
                </tr> --%>
                <tr id="spsj">
                    <td style="width:120px;"><span class="red">*</span>商品pc售价：</td>
                    <td><input name="" id="pcprice" type="text" class="text-inp" value="<fmt:formatNumber value="${sku.price}" pattern="0.00"/>"></td>
                </tr>
                <tr id="spappsj">
                    <td style="width:120px;"><span class="red">*</span>商品App售价：</td>
                    <td><input name="" id="appprice" type="text" class="text-inp" value="<fmt:formatNumber value="${sku.appprice}" pattern="0.00"/>"></td>
                </tr>
                  <tr id="spwapsj">
                    <td style="width:120px;"><span class="red">*</span>商品wap售价：</td>
                    <td><input name="" id="wapprice" type="text" class="text-inp" value="<fmt:formatNumber value="${sku.wapprice}" pattern="0.00"/>"></td>
                </tr>
                <tr id="spxisj">
                    <td style="width:120px;"><span class="red">*</span>商品微信售价：</td>
                    <td><input name="" id="wxprice" type="text" class="text-inp" value="<fmt:formatNumber value="${sku.wechatprice}" pattern="0.00"/>"></td>
                </tr>
           
                <tr>
                    <td style="width:120px;"><span class="red">*</span>新价格：</td>
                    <td><input id="price" name="price" class="text-inp" type="text" value=""></td>
                </tr>
            </table>
        </div>

        <div class="clear"></div>
        <div style="margin-top: 35px;"></div>
        <div style="text-align:right;width:350px;">
            <input name="sptjsave" type="button" value="确定" class="but-comm">
            <span style="margin-top: 15px;"></span>
            <input name="" type="button" onclick="Xclose()" value="关闭" class="but-comm">
            <input id="spid" type="hidden" name="" value="${sku.id}" />
        </div>
    </div>
</div>
    <script type="text/javascript">
        function Xclose() {
            parent.window.closeDialog();
        }
    </script>