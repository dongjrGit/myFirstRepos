//找回密码

//初始化
var Init = {
    bind: function () {
        //刷新验证码
        $(".refleshverification").bind("click", refleshverification);

        //获取短信验证码倒计时
        var stinxasss;
        var curTImr = 60;
        $(".mobilecode").click(function tim() {
            $("#hidden_mobilecodetoken").attr("value", "");
            $("#text_mobilecode").attr("disabled", false);
            $(".mobilecode").unbind("click");
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
                    $(".mobilecode").bind("click", tim);
                    clearInterval(stinxasss);
                }

            }, 1000)
            var mobilestr = $("#strAccount").val();
            var imgcode = $("#text_imgcode").val();
            $.ajax({
                type: "post",
                url: "/pc/user/send",
                dataType: "json",
                data: { "ph": mobilestr,
        		    "smsType":2,
        		    "ch": 1,
        		    "imgcode": imgcode},
                success: function (rsl) {
                    if (rsl.code == 0) {
                        var jsonObj = rsl.data;
                       // alert(jsonObj.Token);
                        //$("#hidden_mobilecodetoken").attr("value", jsonObj.Token);
                        
                    }
                    else {
                        alert(rsl.desc);
                    }
                },
                error: function (e) {

                }
            });
        })
        //获取email验证码倒计时
        var e_stinxasss;
        $(".emailcode").click(function tim() {
            $(".emailcode").unbind("click");
            $(this).find("b").show();
            $(this).find("font").html("秒后重新获取");
            $(this).css("color", "#999");
            curTImr = 60;
            var thisOBJ = $(this);
            e_stinxasss = setInterval(function () {
                curTImr--;
                thisOBJ.find("b").html(curTImr);
                if (curTImr == 0) {
                    thisOBJ.find("b").hide();
                    thisOBJ.find("font").html("获取短信验证码")
                    thisOBJ.css("color", "#000");
                    $(".emailcode").bind("click", tim);
                    clearInterval(e_stinxasss);
                }

            }, 1000)
            var emailstr = $("#strAccount").val();
            if (emailstr.indexOf(";") > 0) {
                emailstr = emailstr.split(";")[1];
            }
            $.ajax(({
                type: "post",
                url: "/pc/user/sendemail",
                dataType: "json",
                data: { "eamil": emailstr,
                	"ch":1},
                success: function (rsl) {
                    if (rsl.code == 0) {
                        var jsonObj = rsl.data;
                       // $("#hidden_emailcodetoken").attr("value", jsonObj.Token);
                      
                    }
                    else {
                        alert(rsl.desc);
                    }
                },
                error: function (e) {

                }
            }));
        })

        //校验提交
        $("#checksubmit").bind("click", function () {
            $("#td_error").find(".error").remove();
            var type = $("#select_check option:selected").val();
            if (type == 1) {
                if (!Check_Mobile().form()) {
                    return;
                }
                //手机提交
                Submit_Mobile();
            } else {
                $("#td_error").find(".error").remove();
                if (!Check_Email().form()) {
                    return;
                }
                //邮箱提交
                Submit_Email();
            }

        });
        //验证方式下拉框变化事件
        $("#select_check").change(function () {
            var type = $("#select_check option:selected").val()
            if (type == 1) {
                $("#mtitle").removeAttr("style");
                $("#mcheck").removeAttr("style");
                $("#etitle").attr("style", "display:none");
                $("#echeck").attr("style", "display:none");
            } else {
                if (type == 2) {
                    $("#mtitle").attr("style", "display:none");
                    $("#mcheck").attr("style", "display:none");
                    $("#etitle").removeAttr("style");
                    $("#echeck").removeAttr("style");

                }
            }
        });

        //账户校验
        $("#accsubmit").click(function () {
            $("#td_error").find(".error").remove();
            if (!Check_Account().form()) {
                return;
            }
            //账户校验提交
            Submit_Account();
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
        var setup = GetQueryStringByName("setup");
        if (setup == "four") {
            returnLogin();
        }
    }
}
//刷新验证码
function refleshverification() {
    $(".refleshverification_img").attr("src", "/VerifyCodeServlet?t=" + Math.random() * 100000);
}

//自动返回登录页面
function returnLogin() {
    var curTImr = 5;
    $("#backsecond").show();
    $("#backsecond").css("color", "#999");
        var thisOBJ = $("#backsecond");
        stinxasss = setInterval(function () {
            curTImr--;
            thisOBJ.html(curTImr);
            if (curTImr == 0) {
                thisOBJ.hide();
                thisOBJ.css("color", "#000");
                clearInterval(stinxasss);
                window.location = "/member/user/showlogin";
            }
        }, 1000)
}
//手机验证校验
function Check_Mobile() {
    return $("#checkForm").validate({
        rules: {
            text_mobilecode: {
                required: true
            }
        },
        messages: {
            text_mobilecode: {
                required: "请输入手机验证码",
            }
        },
        errorPlacement: function (error, element) {
            $("#td_error").find(".error").remove();
            error.appendTo($("#td_error"));
        }
    })
}

//手机提交
function Submit_Mobile() {
    var mobilecode = $("#text_mobilecode").val();
   // var mobilecodetoken = $("#hidden_mobilecodetoken").val();
    var mobilestr = $("#strAccount").val();
    var fk = GetQueryStringByName("userid");
    $.ajax({
        type: "post",
        url: "/pc/user/checkMobileCode",
        dataType: "json",
        data: { "MobileCode": mobilecode,
        	"mobile":mobilestr
        	},
        success: function (rsl) {
            if (rsl.code == 0) {
                window.location.href = "/member/userInfo/findPwd.html?setup=3&type=0&userid=" + fk;
            }
            else {
                var html = '<label id="data_error" class="error" for="data_error">' + rsl.desc + '</label>';
                $("#td_error").append(html);
            }
        },
        error: function (e) {
        }
    });
}

//账号验证校验
function Check_Account() {
    return $("#checkAccount").validate({
        rules: {
            text_account: {
                required: true
            },
            text_imgcode: {
                required: true
            }
        },
        messages: {
            text_account: {
                required: "请输入账户",
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

//账户提交
function Submit_Account() {
    var acName = $("#text_account").val();
    var imgcode = $("#text_imgcode").val();
    $.ajax({
        type: "post",
        url: "/pc/user/checkAccount",
        dataType: "json",
        data: { "accName": acName,
        	"ImgCode": imgcode,
        	"userType":2,
        	"ch":1},
        success: function (rsl) {
            if (rsl.code == 0) {
            	
                window.location.href = "/member/userInfo/findPwd.html?setup=2&type=0&userid=" + rsl.data;
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
    });
}

//邮箱验证校验
function Check_Email() {
    return $("#checkForm").validate({
        rules: {
            text_emailcode: {
                required: true
            }
        },
        messages: {
            text_emailcode: {
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
    var emailcode = $("#text_emailcode").val();
   // var emailecodetoken = $("#hidden_emailcodetoken").val();
    var emailstr = $("#strAccount").val();
    var fk = GetQueryStringByName("userid");
    $.ajax({
        type: "post",
        url: "/pc/user/checkEmailCode",
        dataType: "json",
        data: { "EmailCode": emailcode,
        	"emails": emailstr },
        success: function (rsl) {
            if (rsl.code == 0) {
                window.location.href = "/member/userInfo/findPwd.html?setup=3&type=0&userid="+fk;
            }
            else {
                var html = '<label id="data_error" class="error" for="data_error">' + rsl.Desc + '</label>';
                $("#td_error").append(html);
            }
        },
        error: function (e) {
        }
    });
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
        }
    })
}

//设置新密码提交
function Submit_SetNewPwd() {
    var newPwd = $("#password_newpwd").val();
    var newPwdAgain = $("#password_pwdagain").val();
    var fk = GetQueryStringByName("userid");
   
    $.ajax(({
        type: "post",
        url: "/pc/user/findPwd",
        dataType: "json",
        data: {userid:fk, 
        	NewPwd: newPwd, 
        	NewPwdAgain: newPwdAgain },
        success: function (rsl) {
            if (rsl.code == 0) {
                window.location.href = "/member/userInfo/findPwd.html?setup=4&type=0";
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
