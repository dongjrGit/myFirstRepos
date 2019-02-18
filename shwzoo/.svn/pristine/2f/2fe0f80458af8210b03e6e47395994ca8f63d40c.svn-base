
var login = {    
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
};