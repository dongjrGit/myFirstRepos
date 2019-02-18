<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<div class="mainright">

    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">新增店铺品牌</a><span class="sj-img"></span></li>

            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx">
            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" action="/Shop/Save" method="post">
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>店铺名称：</label></div>
                            <div class="tjcpxx-con-form">
                                <input class="tjcpxx-fprm-inp" type="text" value="${shop.name }" disabled>
                                <input type="hidden" id="sbid" value="${shopBrand.id }" />
                                <input type="hidden" id="shopid" value="${shop.id }" />
                                <input type="hidden" id="brandid" value="${shopBrand.brandid }" />
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>品牌：</label></div>
                            <div class="tjcpxx-con-form">
                                <select id="select_brand">
                                <c:if test="${list != null and list.size()>0 }">
                                   <c:forEach items="${list }" var="brand">
                                        <option value="${brand.id }">${brand.name }</option>
                                   </c:forEach>
                                   </c:if>
                                </select>
                            </div>
                        </div><!--tjcpxx-con-mk stop -->

                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" id="sub" name="" type="button" value="保存" onclick="formSubmit()">
                                <input class="preserve-inp_hs" name="" type="button" value="取消" onclick="backhref()">
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
        $("#select_brand").val($("#brandid").val());
    })
    function formSubmit() {
    	$("#sub").hide();
        var shopid = $("#shopid").val();
        var brandid = $("#select_brand").val();
        var sbid = $("#sbid").val();
        $.ajax({
            url: "/platform/shopbrand/save",
            type: "Post",
            data: { "shopid": shopid, "brandid": brandid, "id": sbid },
            dataType: "json",
            success: function (data) {
                Dalert(data.desc, 2000, function () {
                	$("#sub").show();
                    if (data.code == 0) {
                        location.href = "/platform/shop/showShopBranList?shopid=" + shopid;
                    }
                });
            }
        })
    }
    function backhref() {
        location.href = "/platform/shop/showShopBranList?shopid=" + $("#shopid").val();
    }
</script>
