//首页底部新闻链接
$(document).ready(function () {
    Article.bind();
})

var Article = {
    bind: function () {
        $.ajax(({
            type: "post",
            url: "/SyBottom/W_GetArtList",
            dataType: "json",
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
                    }
                    var html = template('artTitle', listdata);

                    $("#datalist").html(html);
                }
                else {
                    //alert(rsl.Desc);
                }
            },
            error: function (e) {
                //alert(e.statusText);
            }
        }));
    }
}