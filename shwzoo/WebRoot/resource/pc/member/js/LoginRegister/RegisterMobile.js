//初始化
var Init = {
    bind: function () {
        //刷新验证码
        $(".refleshverification").bind("click", refleshverification);

        //获取短信验证码倒计时
        var stinxasss;
        var curTImr = 60;
        $(".g_hqyzm").click(function tim() {
            if (!SendCheck().form()) {
                return;
            }
            $("#text_mobilecode").attr("disabled", false);
            $(".g_hqyzm").unbind("click");
            $(this).find("b").show();
            $(this).find("font").html("秒后重新获取");
            $(this).css("color", "#999");
            curTImr = 60;
            var thisOBJ = $(this);
            stinxasss = setInterval(function () {
                curTImr--;
                thisOBJ.find("b").html(curTImr);
                if (curTImr == 0) {
                    thisOBJ.find("b").hide();
                    thisOBJ.find("font").html("获取短信验证码")
                    thisOBJ.css("color", "#000");
                    $("#text_mobilecode").attr("disabled", true);
                    $(".g_hqyzm").bind("click", tim);
                    clearInterval(stinxasss);
                }

            }, 1000)

            var strregistermobile = $("#text_mobile").val();
            $.ajax(({
                type: "post",
                url: "/Register/B_SendMobileCode",
                dataType: "json",
                data: { Mobile: strregistermobile },
                async: false,
                success: function (rsl) {
                    if (rsl.Code == 0) {
                    }
                    else {
                        Dalert(rsl.Desc);
                    }

                },
                error: function (e) {

                }
            }));

        })

        //校验
        $(".uerregister").bind("click", function () {
            if (!Check().form()) {
                return;
            }
            //注册
            register();
        });

    }
}

//刷新验证码
function refleshverification() {
    $(".refleshverification_img").attr("src", "/verification?t=" + Math.random() * 100000);
}

//校验
function Check() {
    return $("#signupForm").validate({
        rules: {
            text_mobile: {
                required: true,
                isMobile: true,
                checkDataRepeat: [2]
            },
            text_mobilecode: {
                required: true
            },
            password_pwd: {
                required: true,
                byteRangeLength: [6, 16]
            },
            password_pwdagain: {
                required: true,
                equalTo: "#password_pwd"
            },
            text_verification: {
                required: true,
                checkImgCode: true
            }
        },
        messages: {
            text_mobile: {
                required: "请输入手机号",
                isMobile: "手机号格式不正确",
                checkDataRepeat: "该手机号已被注册"
            },
            text_mobilecode: {
                required: "请输入手机验证码"
            },
            password_pwd: {
                required: "请输入密码",
                byteRangeLength: "密码长度不正确，请重新设置(6-16位)"
            },
            password_pwdagain: {
                required: "请输入确认密码",
                equalTo: "两次密码不一致"
            },
            text_verification: {
                required: "请输入验证码",
                checkImgCode: "验证码错误"
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        debug: true
    })
}

//发送短信校验
function SendCheck() {
    return $("#signupForm").validate({
        rules: {
            text_mobile: {
                required: true,
                isMobile: true,
                checkDataRepeat: [2]
            }
        },
        messages: {
            text_mobile: {
                required: "请输入手机号",
                isMobile: "手机号格式不正确",
                checkDataRepeat: "该手机号已被注册"
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        debug: true
    })
}


//注册
function register() {
    var data = { autype: 1 };
    data.username = $("#text_mobile").val();
    data.pwd = $("#password_pwd").val();
    data.rpwd = $("#password_pwdagain").val();
    data.pcode = $("#text_mobilecode").val();
    //data.typevalue = $("#text_mobile").val();

    $.ajax(({
        type: "post",
        url: "/Register/B_Register",
        dataType: "json",
        data: data,
        success: function (rsl) {
            if (rsl.Code == 0) {
                Dalert("注册成功！", "", backhref);
            }
            else if (rsl.Code == -103 || rsl.Code == -104) {
                $("#text_mobilecode-error").remove();
                var html = '<label id="text_mobilecode-error" class="error" for="text_mobilecode">' + rsl.Desc + '</label>';
                $(".g_hqyzm").after(html);
                refleshverification();
                $("#text_verification").attr('value', "");
            }
            else {
                Dalert(rsl.Desc);
            }
        },
        error: function (e) {
            refleshverification();
        }
    }));
}
function backhref() {
    window.location.href = "login";
}