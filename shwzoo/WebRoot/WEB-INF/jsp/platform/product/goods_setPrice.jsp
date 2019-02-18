<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath}/resource/public/platform/js/product/spgl_sptj.js"></script>
<div id="mainright" style="width:480px;">
    <div class="the-form" style="width:480px;">
        <div class="clear"></div>
        <div class="tjcpxx-con-mk1">
            <div class="tjcpxx-con-form-title" style="width: 120px;"><label><span class="red marrig5">*</span>调价类型：</label></div>
            <div class="tjcpxx-con-form1" style="width: 320px;">
                <select id="priceType" name="priceType" onchange="selectchange()" class="sel-form">
			               	<script id="flist" type="text/html">
                                {{each list as ad i}}
                                 <option value="{{ad.code}}">{{ad.name}}</option>
                                {{/each}}
                            </script>
                </select>
            </div>
        </div>
   <%--      <div class="tjcpxx-con-mk1" id="spyj">
            <div class="tjcpxx-con-form-title" style="width: 120px;"><label><span class="red marrig5">*</span>原价：</label></div>
            <div class="tjcpxx-con-form1" style="width: 320px;">
                <input class="tjcpxx-fprm-inp" name="" type="text" value="<fmt:formatNumber value="${sku.oldprice}" pattern="0.00"/>">
            </div>
        </div> --%>
        <div class="tjcpxx-con-mk1" id="spsj">
            <div class="tjcpxx-con-form-title" style="width: 120px;"><label><span class="red marrig5">*</span>pc售价：</label></div>
            <div class="tjcpxx-con-form1" style="width: 320px;">
                <input class="tjcpxx-fprm-inp" id="pcprice" name="" type="text" readonly="readonly" value="<fmt:formatNumber value="${sku.price}" pattern="0.00"/>">
            </div>
        </div>
        <div class="tjcpxx-con-mk1" id="spappsj">
            <div class="tjcpxx-con-form-title" style="width: 120px;"><label><span class="red marrig5">*</span>App售价：</label></div>
            <div class="tjcpxx-con-form1" style="width: 320px;">
                <input class="tjcpxx-fprm-inp" id="appprice" name="" type="text" readonly="readonly" value="<fmt:formatNumber value="${sku.appprice}" pattern="0.00"/>">
            </div>
        </div>
         <div class="tjcpxx-con-mk1" id="spwapsj">
            <div class="tjcpxx-con-form-title" style="width: 120px;"><label><span class="red marrig5">*</span>wap售价：</label></div>
            <div class="tjcpxx-con-form1" style="width: 320px;">
                <input class="tjcpxx-fprm-inp" id="wapprice" name="" type="text" readonly="readonly" value="<fmt:formatNumber value="${sku.wapprice}" pattern="0.00"/>">
            </div>
        </div>
         <div class="tjcpxx-con-mk1" id="spxisj">
            <div class="tjcpxx-con-form-title" style="width: 120px;"><label><span class="red marrig5">*</span>微信售价：</label></div>
            <div class="tjcpxx-con-form1" style="width: 320px;">
                <input class="tjcpxx-fprm-inp" id="wxprice" name="" readonly="readonly" type="text" value="<fmt:formatNumber value="${sku.wechatprice}" pattern="0.00"/>">
            </div>
        </div>
        <div class="tjcpxx-con-mk1">
            <div class="tjcpxx-con-form-title" style="width: 120px;"><label><span class="red marrig5">*</span>新价格：</label></div>
            <div class="tjcpxx-con-form1" style="width: 320px;">
                <input id="price" name="price"  class="tjcpxx-fprm-inp" type="text" value="">
            </div>
        </div>
        <div class="clear"></div>
        <div style="text-align:right;">
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
         	 //初始化数据
        }
</script>