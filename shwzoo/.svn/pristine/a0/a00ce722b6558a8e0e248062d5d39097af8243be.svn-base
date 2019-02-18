//初始化
var Init = {
    bind: function () {
        //刷新验证码
        $(".refleshverification").bind("click", refleshverification);

        //获取短信验证码倒计时
        var stinxasss;
        var curTImr = 60;
        $(".g_hqyzm").click(function tim() {
            $("#hidden_mobilecodetoken").attr("value", "");
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
            var mobilestr = $("#hidden_mobile").val();
            var imgcode = $("#text_imgcode").val();
            $.ajax(({
                type: "post",
                url: "/pc/user/send",
                dataType: "json",
                data: { "ph": mobilestr,
        		    	"smsType":1,
        		    	"ch": 1,
        		    	"imgcode": imgcode},
                async: false,
                success: function (rsl) {
                	
                    if (rsl.code == 0) {
                        var jsonObj = rsl.data;
                        $("#hidden_mobilecodetoken").attr("value", jsonObj.content);
                    }
                    else {
                        Dalert(rsl.desc);
                    }
                },
                error: function (e) {
                  
                }
            }));
        })

        //手机校验
        $("#a_mobilesubmit").bind("click", function () {
            $("#td_error").find(".error").remove();
            if (!Check_Mobile().form()) {
                return;
            }
            //手机提交
            Submit_Mobile();
        });

        //邮箱校验
        $("#a_emailsubmit").bind("click", function () {
            $("#td_error").find(".error").remove();
            if (!Check_Email().form()) {
                return;
            }
            //邮箱提交
            Submit_Email();
        });

        //支付密码校验
        $("#a_paypwdsubmit").bind("click", function () {
            $("#td_error").find(".error").remove();
            if (!Check_PayPwd().form()) {
                return;
            }
            //支付密码提交
            Submit_PayPwd();
        });

        //设置新密码校验
        $("#a_setnewpwdsubmit").bind("click", function () {
            $("#td_error").find(".error").remove();
            if (!Check_SetNewPwd().form()) {
                return;
            }
            //设置新密码提交
            Submit_SetNewPwd();
        });
    }
}

//刷新验证码
function refleshverification() {
    $(".refleshverification_img").attr("src", "/VerifyCodeServlet?t=" + Math.random() * 100000);
}

//手机验证校验
function Check_Mobile() {
    return $("#mobileForm").validate({
        rules: {
            text_mobilecode: {
                required: true
            },
            text_imgcode: {
                required: true
            }
        },
        messages: {
            text_mobilecode: {
                required: "请输入手机验证码",
            },
            text_imgcode: {
                required: "请输入验证码",
            }
        },
        errorPlacement: function (error, element) {
            $("#td_error").find(".error").remove();
            error.appendTo($("#td_error"));
        },
        debug: true
    })
}

//手机提交
function Submit_Mobile() {
    var mobilecode = $("#text_mobilecode").val();
    var imgcode = $("#text_imgcode").val();
    //var mobilecodetoken = $("#hidden_mobilecodetoken").val();
    var mobilestr = $("#hidden_mobile").val();

    $.ajax(({
        type: "post",
        url: "/pc/user/mobilestr",
        dataType: "json",
        data: { "MobileCode": mobilecode,
        		"ImgCode": imgcode,
        		"mobile": mobilestr,
        		"ch":1},
        success: function (rsl) {
            if (rsl.code == 0) {
                window.location.href = "/member/userInfo/changeLoginPwd.html?type=3&setup=2";
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




//邮箱验证校验
function Check_Email() {
    return $("#emailForm").validate({
        rules: {
            text_imgcode: {
                required: true
            }
        },
        messages: {
            text_imgcode: {
                required: "请输入验证码"
            }
        },
        errorPlacement: function (error, element) {
            $("#td_error").find(".error").remove();
            error.appendTo($("#td_error"));
        },
        debug: true
    })
}

//邮箱提交
function Submit_Email() {
    var imgcode = $("#text_imgcode").val();
    //校验
    $.ajax(({
        type: "post",
        url: "/pc/user/sendEmailForPwd",
        dataType: "json",
        data: { "imgCode": imgcode, 
        	   "SkipUrl": window.location.href ,
        	   "ch":1},
        success: function (rsl) {
        	
            if (rsl.code == 0) {
                window.location.href = "/member/userInfo/changeLoginPwd.html?type=1&setup=4";
            }
            else {
                var html = '<label id="data_error" class="error" for="data_error">' + rsl.Desc + '</label>';
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





//支付密码验证校验
function Check_PayPwd() {
    return $("#paypwdForm").validate({
        rules: {
            password_paypwd: {
                required: true
            },
            text_imgcode: {
                required: true
            }
        },
        messages: {
            password_paypwd: {
                required: "请输入支付密码"
            },
            text_imgcode: {
                required: "请输入验证码"
            }
        },
        errorPlacement: function (error, element) {
            $("#td_error").find(".error").remove();
            error.appendTo($("#td_error"));
        },
        debug: true
    })
}

//支付密码提交
function Submit_PayPwd() {
    var paypwd = $("#password_paypwd").val();
    var imgcode = $("#text_imgcode").val();
    //校验
    $.ajax(({
        type: "post",
        url: "/pc/user/checkPassPwd",
        dataType: "json",
        data: { "ImgCode": imgcode,
        	"PayPwd": paypwd },
        success: function (rsl) {
            if (rsl.code == 0) {
                window.location.href = "/member/userInfo/changeLoginPwd.html?type=2&setup=3";
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







//设置新密码验证
function Check_SetNewPwd() {
    return $("#setnewpwdForm").validate({
        rules: {
            password_newpwd: {
                required: true,
                byteRangeLength: [6, 16]
            },
            password_pwdagain: {
                required: true,
                equalTo: "#password_newpwd"
            }
        },
        messages: {
            password_newpwd: {
                required: "请输入新密码",
                byteRangeLength: "密码长度不正确，请重新设置(6-16位)"
            },
            password_pwdagain: {
                required: "请输入确认新密码",
                equalTo: "两次密码不一致"
            }
        },
        errorPlacement: function (error, element) {
            $("#td_error").find(".error").remove();
            error.appendTo($("#td_error"));
        },
        debug: true
    })
}

//设置新密码提交
function Submit_SetNewPwd() {
    var newPwd = $("#password_newpwd").val();
    var newPwdAgain = $("#password_pwdagain").val();

    $.ajax(({
        type: "post",
        url: "/pc/user/updPwd",
        dataType: "json",
        data: { "NewPwd": newPwd,
        	"NewPwdAgain": newPwdAgain },
        success: function (rsl) {
        	
            if (rsl.code == 0) {
                window.location.href = "/member/userInfo/changeLoginPwd.html?type=1&setup=3";
            }
            else {
                var html = '<label id="data_error" class="error" for="data_error">' + rsl.desc + '</label>';
                $("#td_error").append(html);
            }
        },
        error: function (e) {
         
            refleshverification();
        }
    }));
}