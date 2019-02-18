var changebool = false;
var strmobile = "";
var strmobiletoken = "";
$(document).ready(function() {
	
	MemberRegister.init();
})

// 注册
var MemberRegister = {
	// 初始化
	init : function() {
		// 获取短信验证码倒计时
		var stinxasss;
		var curTImr = 60;
		$(".g_hqyzm").click(function tim() {
			if (!MemberRegister.check().element(text_mobile)) {
				return;
			}
			strmobiletoken = "";
			$("#text_mobilecode").attr("disabled", false);
			$(".g_hqyzm").unbind("click");
			$(this).find("b").show();
			$(this).find("font").html("秒后重新获取");
			$(this).css("color", "#999");
			curTImr = 60;
			var thisOBJ = $(this);
			stinxasss = setInterval(function() {
				curTImr--;
				thisOBJ.find("b").html(curTImr);
				if (curTImr == 0) {
					thisOBJ.find("b").hide();
					thisOBJ.find("font").html("获取短信验证码")
					thisOBJ.css("color", "#000");
					$(".g_hqyzm").bind("click", tim);
					clearInterval(stinxasss);
				}

			}, 1000)

			var strregistermobile = $("#text_mobile").val();
			var sdfds = "";
			if (!changebool) {
				if (strregistermobile == "") {
					Dalert("请输入手机号！");
					return;
				}
				sdfds = strregistermobile;
			} else {
				sdfds = strmobile;
			}
			$.ajax(({
				type : "post",
				url : "/seller/user/send",
				dataType : "json",
				data : {
					phone : sdfds,
					type : 0
				},
				async : false,
				success : function(rsl) {
					if (rsl.code == 0) {
						var jsonObj = rsl.data;
						strmobiletoken = jsonObj.token;
					} else {
						Dalert(rsl.esc);
					}

				},
				error : function(e) {

				}
			}));

		})

		// 注册校验
		$(".uerregister").bind("click", function() {
			if (!MemberRegister.check().form()) {
				return;
			}
			// 注册
			MemberRegister.register();
		});
	},
	// 注册
	register : function() {
		var data = {
			autype : 1
		};
		data.typevalue = $("#text_mobile").val();  //手机
		data.username = $("#text_username").val(); //用户名
		data.pwd = $("#password_pwd").val();      //密码
		data.rpwd = $("#password_pwdagain").val();  //确认密码
		data.pcode = $("#text_mobilecode").val();    //短信验证码

		$
				.ajax(({
					type : "post",
					url : "/seller/user/toUserRegister",
					dataType : "json",
					data : data,
					success : function(rsl) {
						if (rsl.code == 0) {
							Dalert("注册成功！", "", MemberRegister.backhref);
						} else if (rsl.code == -103 || rsl.code == -104) {
							$("#text_mobilecode-error").remove();
							var html = '<label id="text_mobilecode-error" class="error" for="text_mobilecode">'
									+ rsl.desc + '</label>';
							$(".g_hqyzm").after(html);
							MemberRegister.refleshverification();
							$("#text_verification").attr('value', "");
						} else {
							Dalert(rsl.desc);
						}
					},
					error : function(e) {
						MemberRegister.refleshverification();
					}
				}));
	},
	// 注册回调
	backhref : function() {
		window.location.href = "login";
	},
	// 刷新验证码
	refleshverification : function() {
		$(".refleshverification_img").attr("src",
				"/VerifyCodeServlet?t=" + Math.random() * 100000);
	},
	// 注册校验
	check : function() {
		return $("#signupForm").validate({
			rules : {
				text_username : {
					required : true
				},
				password_pwd : {
					required : true,
					byteRangeLength : [ 6, 16 ]
				},
				password_pwdagain : {
					required : true,
					equalTo : "#password_pwd"
				},
				text_mobile : {
					required : true,
					isMobile : true,
					//checkDataRepeat : [ 2 ]
				},
				text_verification : {
					required : true,
					checkImgCode : true
				},
				text_mobilecode : {
					required : true
				}
			},
			messages : {
				text_username : {
					required : "请输入用户名"
				},
				password_pwd : {
					required : "请输入密码",
					byteRangeLength : "密码长度不正确，请重新设置(6-16位)"
				},
				password_pwdagain : {
					required : "请输入确认密码",
					equalTo : "两次密码不一致"
				},
				text_mobile : {
					required : "请输入验证手机号",
					isMobile : "手机号格式不正确",
						
				},
				text_verification : {
					required : "请输入验证码",
					checkImgCode : "验证码错误"
				},
				text_mobilecode : {
					required : "请输入短信验证码"
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			},
			debug : true
		})
	}
}