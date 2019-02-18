//两个参数，一个是cookie的名子，一个是值
function SetCookie(name, value) {

    document.cookie = name + "=" + escape(value) + ";path=/";//expires=" + exp.toGMTString() 
}
//两个参数，一个是cookie的名子，一个是值
function SetCookieTime(name, value, days) {
    var exp = new Date();
    var seconds = days * 60 * 60 * 24;
    exp.setTime(exp.getTime() + seconds * 1000);
    document.cookie = name + "=" + escape(value) + ";path=/;expires=" + exp.toGMTString()
}

//取cookies函数
function GetCookie(name) {
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]); return null;
}

//删除cookie
function DelCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = GetCookie(name);
    if (cval != null) document.cookie = name + "=" + cval + ";path=/;expires=" + exp.toGMTString();
}

$(function () {
    var ctl = GetCookie("yltzsw_tcl");
    if (ctl == null || ctl == "") {
        try {
            SetCookieTime("yltzsw_tcl", Guid.NewGuid().ToString(), 200);
        }
        catch (e) {
        }
    }
   
    setInterval(function () {
        $.ajax({
            url: "/api/app/userinfo/delayheartbeat",
            type: "Post",
            dataType: "json",
            success: function (data) {
            },
            error: function () {
            }
        });
    }, 2 * 60 * 1000);
}
);