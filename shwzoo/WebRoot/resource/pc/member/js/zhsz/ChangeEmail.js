//初始化
var Init = {
    bind: function () {
        //刷新验证码
        $(".refleshverification").bind("click", refleshverification);

        //获取短信验证码倒计时
        var stinxasss;
        var curTImr = 60;
        $(".g_hqyzm").click(function tim() {
            if (!Check().element(text_email)) {
                return;
            }
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
                    $(".g_hqyzm").bind("click", tim);
                    clearInterval(stinxasss);
                }

            }, 1000)
            var emailstr = $("#text_email").val();
            $.ajax(({
                type: "post",
                url: "/pc/user/sendemail",
                dataType: "json",
                data: { "eamil": emailstr,
                	"ch":1},
                async: false,
                success: function (rsl) {
                    if (rsl.code == 0) {
                        $("#hidden_mail").attr("value", emailstr);
                    }
                    else {
                        Dalert(rsl.desc);
                    }
                },
                error: function (e) {

                }
            }));
        })

        //校验
        $("#a_emailsubmit").bind("click", function () {
            if (!Check().form()) {
                return;
            }
            //提交
            MailSubmit();
        });
    }
}

//刷新验证码
function refleshverification() {
    $(".refleshverification_img").attr("src", "/VerifyCodeServlet?t=" + Math.random() * 100000);
}

//校验
function Check() {
    return $("#emailForm").validate({
        rules: {
            text_email: {
                required: true,
                email: true,
                checkDataRepeat: [2]

            },
            text_emailcode: {
                required: true
            },
            text_imgcode: {
                required: true,
                checkImgCode: true
            }
        },
        messages: {
            text_email: {
                required: "请输入新邮箱",
                email: "邮箱格式错误",
                checkDataRepeat: "该邮箱已存在"
            },
            text_emailcode: {
                required: "请输入邮箱校验码"
            },
            text_imgcode: {
                required: "请输入验证码",
                checkImgCode: "验证码错误"
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent().next());
        },
        debug: true
    })
}

//提交
function MailSubmit() {
    //手机号
    var mail = $("#hidden_mail").val();
    if (mail == "" || mail == null || mail == undefined)
        mail = $("#text_email").val();
    //手机验证码
    var mailcode = $("#text_emailcode").val();
    //图片验证码
    var imgcode = $("#text_imgcode").val();

    $.ajax(({
        type: "post",
        url: "/pc/user/bindEmail",
        dataType: "json",
        data: {"email": mail,
        	"mailcode": mailcode, 
        	"imgcode": imgcode,
        	"ch":1 },
        success: function (rsl) {
            if (rsl.code == 0) {
                $("#hidden_mail").attr("value", "");
                window.location.href = "/member/userInfo/changeEmail.html?setup=2";
            }
            else {
                var html = '<label id="data_error" class="error" for="data_error">' + rsl.desc + '</label>';
                $("#td_error").append(html);
                refleshverification();
                $("#text_imgcode").attr("value", "");
            }
        },
        error: function (e) {
            refleshverification();
            $("#text_imgcode").attr("value", "");
        }
    }));
}
