
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/shop/shopviolationadd.js"></script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0)">添加违规店铺</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->

        <div class="tjcpxx-con-con">
            <form id="addshopviolationForm" action="#" method="post">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>店铺名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <!--<input class="tjcpxx-fprm-inp" name="text_shopname" id="text_shopname" type="text">-->
                        <ad><input id="select_shop" name="select_shop" type="text" class=" tjcpxx-fprm-inp" /></ad>
                        <div>
                            <ul>
                                <script id="select_shoplist" type="text/html">
                                    {{each list as shop i}}
                                    <li data="{{shop.id}}">{{shop.name}}</li>
                                    {{/each}}
                                </script>
                            </ul>
                        </div>
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>违规类型：</label></div>
                    <div class="tjcpxx-con-form1">
                        <select name="select_violation" id="select_violation" class="mar-djmc">
                           <!-- <script id="violationselect" type="text/html">
                                <option value="-1" selected>请选择</option>
                                {{each list as VillationDTo dto}}
                                <option value="{{dto.index}}">{{dto.value}}</option>
                                {{/each}}
                            </script> -->
                             
				               <option value="0">请选择</option>
				               <option value="1">轻度违规</option>
				               <option value="2">一般违规</option>
				               <option value="3">严重违规</option>
           
                        </select>
                    </div>
                </div><!--tjcpxx-con-mk stop -->

                <div class="tjcpxx-con-mk1" style="height:230px;">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>违规描述：</label></div>
                    <div class="tjcpxx-con-form1">
                        <textarea class="dpwg-form1-text" name="text_violationdescribe" id="text_violationdescribe" cols="" rows=""></textarea>
                    </div>
                </div><!--tjcpxx-con-mk stop -->

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>违规处理：</label></div>
                    <div class="tjcpxx-con-form1">
                        <textarea class="dpwg-form1-text" name="text_violationresult" id="text_violationresult" cols="" rows=""></textarea>
                    </div>

                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                        <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="button" value="保存">
                        <input class="preserve-inp_hs" name="btn_goback" id="btn_goback" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->


            </form>
        </div><!--tjcpxx-con stop -->
    </div><!--tjcpxx stop -->



</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //返回按钮点击
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/shop/showViolationShop";
        });
        //初始化
        Init.bind();
        //表单验证
        Vaildate.bind();
    })
</script>

