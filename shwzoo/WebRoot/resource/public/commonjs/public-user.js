//用户公共
var user = {
    IsLogin: function () {
        var result = false;
        $.ajax(({
            type: "post",
            url: "/UserInfo/IsLogin",
            dataType: "json",
            data: {},
            async: false,
            success: function (rsl) {
                if (rsl.Code == 0) {
                    result = rsl.Data;
                }
            },
            error: function (e) {
                //alert(e.statusText);
            }
        }));
        return result;
    }
}