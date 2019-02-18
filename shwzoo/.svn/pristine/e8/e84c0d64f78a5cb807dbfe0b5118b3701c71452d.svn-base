$(document).ready(function() {
	$("div[name='payType']").bind("click", function(){
		var tag = $(this).attr("tag");
		var url = "";
		if(tag == "1")
			url = "/api/wap/alipay/grouppaybygroupnum";
		else if (tag == "2") {
			url = "/api/wap/IpsWap/groupipspay";
		}else if (tag == "3") {
			url = "/api/wap/activity/payOrder";
		}else{
			alert("快捷支付暂未开通!");
			return;
		}
		$("#payform").attr("action", url);
		$("#payform").submit();
	})
})