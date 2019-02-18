//初始化
var Init = {
    bind : function() {
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
        $("#div_errormessage").html("请输入用户名!");
        return false;
    }
    //alert("dkdkd");
    if (userPwd == "") {
        $("#userPwd").focus();
        $("#div_errormessage").html("请输入密码!");
        return false;
    }
    $.ajax(( {
        type : "post",
        url : "/api/wap/userinfo/login",
        dataType : "json",
        data : {
            name : userName,
            pwd : userPwd,
            ch : 0,
            code : 1
        },
        success : function(rsl) {
            if (rsl.code == 0) {
                var data = rsl.data;
                var info = eval("(" + data + ")");
                SetCookie("_u_token", info.Token);
                SetCookie("_u_channel", info.Channel);
                SetCookie("_uid", info.UserID);
                /*SetCookie("_un", info.UserName + "," + info.Mobile + ","
                 + info.Email);*/
                SetCookie("_u_name", info.UserName);
                SetCookie("_u_mobile", info.Mobile);
                SetCookie("_u_email", info.Email);
                window.location.href = "/wap/index.html";

                //cookie数据同步到数据库
                Cart.cookietodb(Cart.getgoodscount,Cart.getcountcallback);                
            } else {
                $("#userName").focus();
                $("#div_errormessage").text(rsl.desc);
            }
        },
        error : function(e) {
            alert("登录异常");
        }
    }));
}

//回车事件
function EnterPress() {
    if (event.keyCode == 13) {
        $("#a_submit").click();
    }
}