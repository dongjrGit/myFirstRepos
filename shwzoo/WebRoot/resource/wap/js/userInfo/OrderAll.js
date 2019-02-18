var ch = 3;
var psize = 10;
var Order = {
	//shanchu
	deleteorder : function(id,status) {
		$.ajax(({
			type : "post",
			url : "/api/wap/order/delOrder",
			dataType : "json",
			data : {
				id : id,
				ch : ch
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					alert("删除订单成功");
					location.href = "/wap/order/getorder?status=" + status+"&ch=3";
				}

				else {
					alert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	},	
	confirmationgood : function(orderid) {
		$.ajax({
			type : "post",
			url : "/api/wap/order/comfirmreceive",
			dataType : "json",
			data : {
				orderid : orderid,
				ch : ch
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					alert("确认收货成功成功");
				window.location.reload();
				}

				else {
					alert(rsl.desc);
				}
			},
			error : function(e) {

			}
		});
	},
	cancel : function(status, orderid, reason) {
		$.ajax(({
			type : "post",
			url : "/api/wap/order/cancel",
			dataType : "json",
			data : {
				status : status,
				ch : ch,
				reason : reason,
				orderid : orderid
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					alert("订单取消成功");
					window.location.reload();
				}
				else {
					alert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	}
}