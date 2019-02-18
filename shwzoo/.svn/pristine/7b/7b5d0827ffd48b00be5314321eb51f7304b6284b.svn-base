<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="/resource/artTemplate.js"></script>

<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：账号管理 &gt; 个人中心 &gt; 账户充值
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">账户充值</div>
            </div><!--zhgl-con-top  stop -->
            <div class="zhgl-con-con">
                <form id="submitForm">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left">账户余额：</td>
                            <td>
                            <fmt:formatNumber value="${usercapital.balance}" pattern="0.00"/></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">最低保证金：</td>
                            <td><fmt:formatNumber value="${lowband}" pattern="0.00"/></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">保证金余额：</td>
                            <td><fmt:formatNumber value="${usercapital.bond }" pattern="0.00"/></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">冻结余额：</td>
                            <td><fmt:formatNumber value="${usercapital.freezemoney }" pattern="0.00"/></td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">充值类型：</td>
                            <td>
                                <input name="radio_vouchertype" type="radio" value="0" checked>账户余额<span style="margin-right:8px;"></span>
                                <input name="radio_vouchertype" type="radio" value="1">保证金
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">充值金额（元）：</td>
                            <td>
                                <input name="text_vouchernum" id="text_vouchernum" type="text" class="text-inp" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><input name="btn_pay" id="btn_pay" type="button" value="充值" class="reply-but"></td>
                            <td>
                            	<input name="btn_back" id="btn_back" type="button" value="返回" class="reply-but">
                            </td>
                           
                        </tr>
                    </table>
                    <input type="hidden" name="id" id="id" value="${usercapital.id }"/>
                    <input type="hidden" name="userid" id="userid" value="${userid }"/>
                </form>
                
                
            </div><!--zhgl-con-con  stop -->
        </div><!--zhgl-con  stop -->
    </div><!--主要内容 右边结束 -->
</div><!--主要内容 右边结束 -->
<script type="text/javascript" src="/resource/validate.js">
</script>
<script type="text/javascript">
    $(document).ready(function () {
        //充值按钮点击
        $("#btn_pay").bind("click", function () {
            if (!Check().form()) {
                return;
            }
            Submit();
        });
        
        $("#btn_back").bind("click", function () {
            
            Back();
        });
    })
    jQuery.validator.addMethod("isMoney", function(value, element) {
		var length = value.length;
var mobile = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
return mobile.test(value);
},"请输入正确金额");
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
                    required: "请输入充值金额"
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
        var type = $("input[name='radio_vouchertype']:checked").val();
        var voucher = parseFloat($("#text_vouchernum").val());
        var id = $("#id").val();
        var userid = $("#userid").val();
       // window.location.href = "/seller/message/recharge?userid="+ userid +"&money=" + voucher + "&recharge_type=" + type;
     if(type==0){
       location.href="/pay/paybygroupnum?price="+voucher;
     }else{
    	 $.ajax({
    		 url:"/pay/balanceTurnMargin?price="+voucher,
    		 type:"Post",
    		 dataType:"json",
    		 success:function(data){
				 alert(data.desc)
    				 if(data.code==0){
    					 Back();
    				 }
    		 }
    	 });
     }
       /* $.ajax({
        	url:"/seller/message/recharge",
        	type:"Post",
        	data:{
        		"userid":userid,
        		"recharge_type":type,
        		"money":voucher
        	},
        	dataType:"json",
        	success:function(data){
        		
        		if(data.code==0){
        			
        			/* window.location.href = '/seller/home'; */
        	/* 		Dalert(data.desc, "", function () { window.location.href = '/seller/home';});
        		}else{
        			
        			Dalert(data.desc);
        		}
        	}
        }); */ 
    }
    
    //提交
    function Back() {
    	window.location.href = '/seller/home';
    }
</script>
