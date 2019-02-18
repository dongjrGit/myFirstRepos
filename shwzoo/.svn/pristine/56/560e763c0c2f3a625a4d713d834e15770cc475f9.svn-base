//web登录
var WebLogin = {
    //列表页登录
    login : function() {
        //用户名
        var userName = $("#text_username").val();
        //密码
        var userPwd = $("#password_loginpwd").val();

        $.ajax({
            type : "post",
            url : "/pc/user/login",
            dataType : "json",
            async : false,
            data : {
                "ch" : 1,
                "name" : userName,
                "pwd" : userPwd,
                "code" : 0
            },
            success : function(rsl) {
                if (rsl.code == 0) {
                    var data = rsl.data;
                    var info = eval("(" + data + ")");
                    SetCookie("_u_token", info.Token);
                    SetCookie("_u_channel", info.Channel);
                    SetCookie("_uid", info.UserID);
                    SetCookie("_u_name", info.UserName);
                    SetCookie("_u_mobile", info.Mobile);
                    SetCookie("_u_email", info.Email);

                    //cookie数据同步到数据库
                    cart.cookietodb();
                    cart.getprocount();

                    $(".div_login").hide();
                    $("#text_username").attr("value", "");
                    $("#password_loginpwd").attr("value", "");

                    location.reload();
                    /*  if (user.IsLogin()) {
                     //cookie数据同步到数据库
                     Cart.cookietodb()
                     //刷新页面
                     location.reload();
                     }*/
                } else {
                    $("#password_loginpwd-error").remove();
                    var htm = '<label id="password_loginpwd-error" class="error" for="password_loginpwd" width="210px;">' + rsl.desc + '</label>';
                    $("#p_pwd").after(htm);
                }
            },
            error : function(e) {
                //alert(e.statusText);
            }
        });
    },
    //回车事件
    enterpress : function() {
        if (event.keyCode == 13) {
            $("#submit_ok").click();
        }
    },
    //列表页登录校验
    check : function() {
        return $("#loginForm").validate({
            rules : {
                text_username : {
                    required : true
                },
                password_loginpwd : {
                    required : true
                }
            },
            messages : {
                text_username : {
                    required : "请输入用户名"
                },
                password_loginpwd : {
                    required : "请输入密码"
                }
            },
            errorPlacement : function(error, element) {
                //  error.appendTo(element.parent().next());
                $(element.parent()).after(error);
            },
            debug : true
        })
    },
    isLogin : function() {
        var returnVal;
        $.ajax({
            type : 'POST', //请求类型
            dataType : "text",
            url : '/pc/user/isLogins', //请求位置 时间轴
            cache : false, //true，dataType 为 script 和 jsonp 时默认为 false。设置为 false 将不缓存此页面。
            async : false, //同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
            timeout : 30000, //设置请求超时时间（毫秒）。此设置将覆盖全局设置。
            success : function(data) {//成功
                returnVal = data;
            },
            error : function() {
                //alert("错误");
            }
        });
        if (parseInt(returnVal) == -401) {
            return false;
        } else {
            return true;
        }
    }
}

function showlogindiv() {
    $(".div_cover").show();
    $(".div_login").show();
}

function hidelogindiv() {
    $(".div_cover").hide();
    $(".div_login").hide();
}

//退出登录
function signout() {
    DelCookie("_u_token");
    DelCookie("_u_channel");
    DelCookie("_uid");
    DelCookie("_u_name");
    DelCookie("_u_mobile");
    DelCookie("_u_email");
     window.location.href = "/index.html";
}


$(document).ready(function() {
    $("#submit_ok").click(function() {
        if (!WebLogin.check().form()) {
            return;
        }
        //登录
        WebLogin.login();
    });
})