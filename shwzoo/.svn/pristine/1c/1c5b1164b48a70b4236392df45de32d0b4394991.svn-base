//会员登录
var MemberLogin = {
    login: function () {
        var userName = $("#userName").val();
        var userPwd = $("#userPwd").val();
        if (userName == "") {
            $("#userName").focus();
            $("#p_errmessage").text("请输入账号");
            return;
        }
        if (userPwd == "") {
            $("#userPwd").focus();
            $("#p_errmessage").text("请输入密码");
            return;
        }
        $.ajax(({
            type: "post",
            url: "/pc/user/login",
            dataType: "json",
            data: {"ch": 1,
            	"name": userName,
            	"pwd": userPwd,
            	"code": 0 },
            success: function (rsl) {
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
                     //cookie数据同步到数据库
                     cart.cookietodb();
                     cart.getprocount();
                     
                     window.location.href = "/member/homepage/homepages.html";
                     
                     
                }
                else {
                    $("#userName").focus();
                    $("#p_errmessage").text(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));
    },
    enterpress: function () {
        if (event.keyCode == 13) {
            $("#a_submit").click();
        }
    }
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