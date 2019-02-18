
jQuery(function() {

    //屏蔽页面错误
    // jQuery(window).error(function() {
        // return true;
    // });
    // jQuery("img").error(function() {
        // $(this).hide();
    // });
});

function GetQueryStringByName(name) {
    var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}

function GetQueryStringByIndex(index) {
    if (index == null) {
        return "";
    }
    var queryStringList = getQueryString();
    if (index >= queryStringList.length) {
        return "";
    }
    var result = queryStringList[index];
    var startIndex = result.indexOf("=") + 1;
    result = result.substring(startIndex);
    return result;
}

function GetQueryString() {
    var result = location.search.match(new RegExp("[\?\&][^\?\&]+=[^\?\&]+", "g"));
    for (var i = 0; i < result.length; i++) {
        result[i] = result[i].substring(1);
    }
    return result;
}

//用户公共
var user = {
    IsLogin : function() {
        var token=GetCookie("_u_token");
        var result = false;
        $.ajax({
            type : "post",
            url : "/wap/qiandao/IsLogin",
            dataType : "json",
            data : {
                "token" : token
            },
            async : false,
            success : function(rsl) {
                if (rsl.code == 0) {
                    result = true;
                }
            },
            error : function(e) {
                //alert(e.statusText);
            }
        });
        return result;
    }
}

