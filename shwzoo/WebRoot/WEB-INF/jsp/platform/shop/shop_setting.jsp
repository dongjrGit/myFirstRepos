<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0)">店铺参数设定</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="submitForm" action="#" method="post" onsubmit="return check();">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>最大员工人数：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_employeenum" id="text_employeenum" type="text" value="${shop.maxEmployee }">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>最大角色个数：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_rolenum" id="text_rolenum" type="text" value="${shop.maxRole }">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>保证金：</label></div>
                    <div class="tjcpxx-con-form1">
                      
                            <input class="tjcpxx-fprm-inp" name="text_margin" id="text_margin" type="text" value="${shop.margin }">
                        
                           <!--  <input class="tjcpxx-fprm-inp" name="text_margin" id="text_margin" type="text" value=""> -->
                       
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
    //店铺ID
    var shopID;
    $(document).ready(function () {
        //获取店铺ID
        shopID = GetQueryStringByName("shopid");
        //返回按钮点击
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/shop/list";
        });
        //保存按钮点击
        $("#submit_ok").bind("click", function () {
            if (!Check().form()) {
                return;
            }
            Submit();
        });
    })
    //校验
    function Check() {
        return $("#submitForm").validate({
            rules: {
                text_employeenum: {
                	digits:true
                },
                text_rolenum: {
                	digits:true
                },
                text_margin: {
                    isMoney: true
                }
            },
            messages: {
                text_employeenum: {
                	digits: "输入人数有误"
                },
                text_rolenum: {
                	digits: "输入个数有误"
                },
                text_margin: {
                    isMoney: "输入保证金有误"
                }
            },
            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
            },
            debug: true
        })
    }

    //提交
    function Submit() {
        var employeenum = $("#text_employeenum").val();
        var rolenum = $("#text_rolenum").val();
        var margin = $("#text_margin").val();

        $.ajax(({
            type: "post",
            url: "/platform/shop/updateParam",
            dataType: "json",
            data: { shopid: shopID, maxEmployee: employeenum, maxRole: rolenum, margin: margin },
            success: function (rsl) {
                if (rsl.code == 0) {
                    Dalert("保存成功", "", function () { window.location.href = '/platform/shop/list'; });
                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));
    }
    function reload() {
        location.reload();
    }
</script>
