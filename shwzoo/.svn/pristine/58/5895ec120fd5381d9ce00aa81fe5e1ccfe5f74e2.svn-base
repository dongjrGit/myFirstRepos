//初始化
var Init = {
    bind: function () {
        //刷新验证码
        $(".refleshverification").bind("click", refleshverification);

        //获取短信验证码倒计时
        var stinxasss;
        var curTImr = 60;
        $(".g_hqyzm").click(function tim() {
            if (!Check().element(text_mobile)) {
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
            
            var mobilestr = $("#text_mobile").val();
           var imgcode = $("#text_imgcode").val();
            $.ajax(({
                type: "post",
                url: "/pc/user/send",
                dataType: "json",
                data: { "ph": mobilestr,
            		    "smsType":2,
            		    "ch": 1,
            		    "imgcode": imgcode},
                async: false,
                success: function (rsl) {
                    if (rsl.code == 0) {
                        $("#hidden_mobile").attr("value", mobilestr);
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
        $("#a_mobilesubmit").bind("click", function () {
            if (!Check().form()) {
                return;
            }
            //提交
            MobileSubmit();
        });
    }
}

//刷新验证码
function refleshverification() {
    $(".refleshverification_img").attr("src", "/VerifyCodeServlet?t=" + Math.random() * 100000);
}

//校验
function Check() {
    return $("#mobileForm").validate({
        rules: {
            text_mobile: {
                required: true,
                isMobile: true,
                checkDataRepeat: [2]

            },
            text_mobilecode: {
                required: true
            },
            text_imgcode: {
                required: true/*,
                checkImgCode: true*/
            }
        },
        messages: {
            text_mobile: {
                required: "请输入新手机号",
                isMobile: "手机号格式错误",
                checkDataRepeat: "该手机号已存在"
            },
            text_mobilecode: {
                required: "请输入手机校验码"
            },
            text_imgcode: {
                required: "请输入验证码"/*,
                checkImgCode: "验证码错误"*/
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent().next());
        },
        debug: true
    })
}

//提交
function MobileSubmit() {
    //手机号
    var mobile = $("#hidden_mobile").val();
    if (mobile == "" || mobile == null || mobile == undefined)
        mobile = $("#text_mobile").val();
    //手机验证码
    var mobilecode = $("#text_mobilecode").val();
    //图片验证码
    var imgcode = $("#text_imgcode").val();

    $.ajax(({
        type: "post",
        url: "/pc/user/updPhone",
        dataType: "json",
        data: { "Mobile": mobile, 
        	    "MobileCode": mobilecode,
        	    "ImgCode": imgcode,
        	    "ch":1},
        success: function (rsl) {
        	
            if (rsl.code == 0) {
                $("#hidden_mobile").attr("value", "");
                window.location.href = "/member/userInfo/changeMobile.html?setup=2";
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
