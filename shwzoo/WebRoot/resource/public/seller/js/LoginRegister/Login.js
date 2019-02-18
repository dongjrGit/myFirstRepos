//初始化
var Init = {
	bind : function() {
		// 登录按钮点击
		$("#a_submit").bind("click", Login);
	}
}

// 登录方法
function Login() {
	$("#div_errormessage").text("");
	var userName = $("#userName").val();
	var userPwd = $("#userPwd").val();
	if (userName == "" || userName == "手机号/会员名/邮箱") {
		$("#userName").focus();
		$("#div_errormessage").text("请输入账号");
		return;
	}
	if (userPwd == "") {
		$("#userPwd").focus();
		$("#div_errormessage").text("请输入密码");
		return;
	}

	$.ajax(({
		type : "post",
		url : "/seller/user/toLogin",
		dataType : "json",
		// async: false,
		data : {
			ChannelType : 0,
			UserName : userName,
			Pwd : userPwd,
			LoginType : 2
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var data = rsl.data;
				var info = eval("(" + data + ")");
				// alert(info.Token);
				SetCookie("_u_token", info.Token);
				SetCookie("_u_channel", info.Channel);
				SetCookie("_uid", info.UserID);
				SetCookie("_un", info.UserName + "," + info.Mobile + ","
						+ info.Email);
				// window.location.href = "/Seller/mjsy/HomePage?cur=hp";
				// alert(info.ShopStatus);
				if (info.ShopStatus != null) {
					var status = parseInt(info.ShopStatus);
					switch (status) {
					case 0:
					case 3:
						// ylsc/jsp/seller/wddp/Company.jsp
						window.location.href = "/seller/shop/showCompanyInfo";
						break;
					case 6:
						$("#div_errormessage").text("店铺已删除，不可登录");
						break;
					case 1:
					case 2:
					case 4:
					case 5:
						window.location.href = "/seller/index";
						break;
					default:
						$("#div_errormessage").text("店铺状态错误");
						break;
					}
				} else {
					$("#div_errormessage").text("店铺状态错误");
				}

			} else {
				$("#userName").focus();
				$("#div_errormessage").text(rsl.desc);
			}
		},
		error : function(e) {
			$("#div_errormessage").text("登录名或密码错误");
		}
	}));
}

// 回车事件
function EnterPress() {
	if (event.keyCode == 13) {
		$("#a_submit").click();
	}
}