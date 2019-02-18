<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0)">会员充值</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="submitForm" action="#" method="post" onsubmit="return check();">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>账户余额：</label></div>
                    <div class="tjcpxx-con-form1">
                        ${usercapital.balances}
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>冻结余额：</label></div>
                    <div class="tjcpxx-con-form1">
                       ${usercapital.freezemoneys}
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>充值金额（元）：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_vouchernum" id="text_vouchernum" type="text" value="">
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
    //会员ID
    var memberID;
    $(document).ready(function () {
        //获取会员ID
        memberID = GetQueryStringByName("memberid");
        //返回按钮点击
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/member/showMemberList";
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
                text_vouchernum: {
                    required: true,
                    isMoney: true
                }
            },
            messages: {
                text_vouchernum: {
                    required: "请输入充值金额",
                    isMoney: "输入金额有误"
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
        var voucher = parseFloat($("#text_vouchernum").val());
        $.ajax(({
            type: "post",
            url: "/platform/membermanagement/rechargeMember",
            dataType: "json",
            data: { memberid: memberID, money1: voucher, type: 0 },
            success: function (rsl) {
                if (rsl.code == 0) {
                    Dalert("保存成功", "", refresh);
                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));
    }
    function refresh() {
        location.reload();
    }
</script>
