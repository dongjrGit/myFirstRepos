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
            $("#text_emailcode").attr("disabled", false);
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
                    $("#text_emailcode").attr("disabled", true);
                    $(".g_hqyzm").bind("click", tim);
                    clearInterval(stinxasss);
                }

            }, 1000)

            var strregisteremail = $("#text_email").val();
            $.ajax(({
                type: "post",
                url: "/Register/B_SendEmailCode",
                dataType: "json",
                data: { Email: strregisteremail },
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
            text_email: {
                required: true,
                email: true,
                checkDataRepeat: [2]
            },
            text_emailcode: {
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
            text_email: {
                required: "请输入邮箱地址",
                email: "邮箱格式不正确",
                checkDataRepeat: "该邮箱已被注册"
            },
            text_emailcode: {
                required: "请输入邮箱验证码"
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

//发送邮箱校验
function SendCheck() {
    return $("#signupForm").validate({
        rules: {
            text_email: {
                required: true,
                email: true,
                checkDataRepeat: [2]
            }
        },
        messages: {
            text_email: {
                required: "请输入邮箱地址",
                email: "邮箱格式不正确",
                checkDataRepeat: "该邮箱已被注册"
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
    var data = { autype: 0 };
    data.username = $("#text_email").val();
    data.pwd = $("#password_pwd").val();
    data.rpwd = $("#password_pwdagain").val();
    data.vcode = $("#text_emailcode").val();
    //data.typevalue = $("#text_email").val();

    $.ajax(({
        type: "post",
        url: "/Register/B_Register",
        dataType: "json",
        data: data,
        success: function (rsl) {
            if (rsl.Code == 0) {
                Dalert("注册成功！", "", backhref);
            }
            else if (rsl.Code == -101 || rsl.Code == -102) {
                $("#text_emailcode-error").remove();
                var html = '<label id="text_emailcode-error" class="error" for="text_emailcode">' + rsl.Desc + '</label>';
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