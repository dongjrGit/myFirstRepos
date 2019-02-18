//初始化
var Init = {
    bind: function () {
        //登录按钮点击
      //$("#a_submit").bind("click", Login);
      
    }
}

//登录方法
function Login() {
    var userName = $("#userName").val();
    var userPwd = $("#userPwd").val();
    if (userName == "") {
        $("#userName").focus();
        $("#div_errormessage").html("请输入账号!");
        return false;
    }
    //alert("dkdkd");
    if (userPwd == "") {
        $("#userPwd").focus();
        $("#div_errormessage").html("请输入密码!");
        return false;
    }
    $.ajax(({
        type: "post",
        url: "/platform/user/toLogin",
        dataType: "json",
        data: {name: userName, pwd: userPwd,ChannelType: 0, LoginType: 1},
        success: function (rsl) {
            if (rsl.code == 0) {
            	var data=rsl.data;
            	var info=eval("(" + data + ")");
                SetCookie("_u_token", info.Token);
                SetCookie("_u_channel",info.Channel);
                SetCookie("login", true);
                window.location.href = "/platform/index";
            }
            else {
                $("#userName").focus();
                $("#div_errormessage").text(rsl.desc);
            }
        },
        error: function (e) {
        	$("#div_errormessage").text("登录名或密码错误");
        }
    }));
}

//回车事件
function EnterPress() {
    if (event.keyCode == 13) {
        $("#a_submit").click();
    }
}
