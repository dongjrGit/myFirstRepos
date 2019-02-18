//店铺信息 author:rw
$(document).ready(function() {
	var s = $("#shopID").val();
	var u = $("#userID").val();
	$(".rightside").find("input[type=text]").each(function() {
		$(this).addClass("border")
	});
	// 编辑按钮
	$("#editone").click(function() {

		$(this).attr("style", "display:none");
		$("#imgdiv").attr("style", "display:block");
		$("#im").attr("style", "display:none");
		$("#saveone").attr("style", "display:block");
		$("#conEdit").find("input[type=text]").each(function() {
			$(this).removeAttr("readonly")
			$(this).removeClass("border")
		});
	})
	// 保存
	$("#saveone").click(function() {
		$(this).attr("style", "display:none");
		$("#conEdit").find("input[type=text]").each(function() {
			$(this).attr("readonly", "readonly")
			$(this).addClass("border")
		});
		$("#im").attr("style", "display:block");
		$("#imgdiv").attr("style", "display:none");
		var xm = $("#xm").val();
		var sjhm = $("#sjhm").val();
		var yx = $("#dzyx").val();
		var shoplogo=$("#shoplogo").val();
		$.ajax({
			type : "post",
			url : "/seller/shop/saveUser",
			dataType : "json",
			data : {
				userid : u,
				name : xm,
				mobile : sjhm,
				email : yx,
				shoplogo:shoplogo
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert(rsl.desc);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {
				// Dalert(e.statusText);
			}
		});
		$("#editone").attr("style", "display:block");

	})
	// 获取店铺信息 s：店铺id
	GetShopInfo(s);
	// 获取联系人信息 u：用户id
	GetContacts(u)
})
function GetShopInfo(sid) {
	$.ajax(({
		type : "post",
		url : "/seller/shop/queryShopById",
		dataType : "json",
		data : {
			id : sid
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var com = rsl.data;
				$("#dpmc").attr("value", com.name);
				$("#imgurl").attr("src", com.imgurl);
				$("#loadimg").attr("src", com.imgurl);
				// $("#dpdz").attr("value", com.ShopAddress);
				// $("#kfdh").attr("value", com.SupportTel);
				// if (com.IsOwned == 1) {
				// $("#sfzyd").attr("value", "是");
				// } else {
				// $("#sfzyd").attr("value", "否");
				// }
				if (com.isflagship) {
					$("#sfqjd").attr("value", "是");
				} else {
					$("#sfqjd").attr("value", "否");
				}
			} else {

			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	}));
}
// 获取联系人信 uid：用户id
function GetContacts(uid) {
	$.ajax({
		type : "post",
		url : "/seller/shop/queryUserById",
		dataType : "json",
		data : {
			userid : uid
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var com = rsl.data;
				if (com != "" && com != null) {
					$("#xm").attr("value", com.realname);
					$("#sjhm").attr("value", com.mobile);
					$("#dzyx").attr("value", com.email);
				}
			} else {

			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}
