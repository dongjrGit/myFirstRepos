var price=0;
$(document).ready(function() {
    price=$("#h_price").val();
	$("div[name='payType']").bind("click", function(){
		var tag = $(this).attr("tag");
		var url = "";
		if(tag == "1")
			url = "/api/wap/alipay/paybygroupnum";
		else if (tag == "2") {
			url = "/api/wap/IpsWap/ipspay";
		}else if (tag == "3") {
			url = "/api/wap/order/pay";
		}else{
			alert("快捷支付暂未开通!");
			return;
		}
		$("#payform").attr("action", url);
		
		var groupnum=$("#groupnum").val();
		
		if(tag=="3"){
		    orderpay (url,groupnum,3);
		}else{
		    $("#payform").submit();
		}
	})
	$(".top_return").click(function(){
	    if(confirm("确认要离开中绿收银台吗？")){
	        window.location.href="/wap/order/getorder?ch=3&&iscomment=null";
	    }       
    })
})
function orderpay(url,groupnum,paytype) {
    $.ajax({
        type : "post",
        url : url,
        dataType : "json",
        data : {
            "groupnum" : groupnum,
            "ch" : "3"
        },
        success : function(rsl) {
            if (rsl.code == 0) {
              window.location.href="/wap/order/paysuccess.html?price="+price+"&paytype="+paytype+"ispay="+true;
            }else{
                window.location.href="/wap/order/paysuccess.html?ispay="+false+"&reason="+rsl.desc;
            }
        },
        error : function(e) {
        }
    });
        
}