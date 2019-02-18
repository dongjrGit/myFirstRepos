var ch = 3;
var changproduct = {
	chang : function(orderdetailid, type, reason, images) {
		var seron = $("#seron").val();
		// var orderdetailid=85;
		var ch = "3";
		$.ajax(({
			type : "post",
			url : "/api/wap/order/applyafter",
			dataType : "json",
			data : {
				ch : ch,
				orderdetailid : orderdetailid,
				applytype : type,
				reason : reason,
				images : images
			},
			success : function(fh) {
				if (fh.code == 0) {
					alert("申请成功");
					window.location.href="/wap/userinfo/homepage?ch=3";
				} else {
					alert(fh.desc);
				}
			}
		}))
	},
	// 图片
	fimage : function(index) {
		$.ajaxFileUpload({
			url : "/app/api/img/upload",
			secureuri : false,
			fileElementId : 'file' + index,
			dataType : "json",
			// ftype:上传文件类型（图片文件=1，其他文件=2）
			// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
			data : {
				relationtype : 5
			},
			type : 'POST',
			success : function(result) {
				if (result.code == 0) {
					$("#img" + index).attr("src", result.data[0]);
					$("#imasrc" + index).val(result.data[0]);
					$("#li" + index).addClass("youtu");
				}
			},
			error : function(e) {
				// alert(JSON.stringify(e));

			}
		});
	}
}