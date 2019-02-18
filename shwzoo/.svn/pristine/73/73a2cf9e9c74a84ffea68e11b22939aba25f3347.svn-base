//订单退换货页面加载
$(function () {
    $("a[name=ty]").bind("click", ty);
    $("a[name=bty]").bind("click", bty);
    // $(".tjcpxx-con-form-upthis").each(function () {
        // $(this).bind("click", function (e) {
            // var $imgurl = $(this).parent().parent().find("input").last();
            // var $imgsrc = $(this).parent().parent().parent().find("img").first();
            // var elementid = $(this).parent().parent().find("input").first().attr("id");
            // $.ajaxFileUpload({
                // url: "/upLoad/UploadFile",
                // secureuri: false,
                // fileElementId: elementid,
                // dataType: "json",
                // //ftype:上传文件类型（图片文件=1，其他文件=2）
                // //module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
                // data: { ftype: 1, module: 2 },
                // type: 'POST',
                // success: function (result) {
                    // //  Dalert(JSON.stringify(result));
                    // if (result.Code == 0) {
                        // $imgurl.val(result.Data[0]);
                        // $imgsrc.attr("src", result.Data[0]);
                    // }
                    // else {
                        // $imgsrc.attr("src", "");
                        // Dalert(result.Desc);
                    // }
                    // //TODO 结束正在加载中
                // },
                // error: function (e) {
                    // Dalert(JSON.stringify(e));
                    // //TODO 结束正在加载中
                // }
            // });
        // });
    // });
})
//同意事件
function ty() {
    var id = $("input[name=Id]").val();
    var type = $("input[name=Status]").val();
    var reason = $("textarea[name=reason]").val()
    if (reason == "" || reason == undefined) {
        Dalert("请填写审核回复");
        $("input[name=reason]").focus();
        return false;
    }
    $.ajax({
        url: "/platform/order/checkAfterOrder",
        type: "Post",
        data: { "id": id, "isagree": 1, "status": type, "reason": reason},
        dataType: "json",
        success: function (data) {
            if (data.code < 0) {
                Dalert(data.desc);
            } else {
                Dalert(data.desc,"",refresh);
                //parent.location.reload();
            }
        }
    });
}
//不同意事件
function bty() {
    var reason = $("textarea[name=reason]").val(), img = $("input[name=imgUrl]").val();
    if (reason == "" || reason == undefined) {
        Dalert("请填写审核回复");
        $("input[name=reason]").focus();
    }
    else {
        var id = $("input[name=Id]").val();
        var type = $("input[name=Status]").val();        
        $.ajax({
            url: "/platform/order/checkAfterOrder",
            type: "Post",
            data: { "id": id, "isagree": 0, "status": type, "reason": reason, "img": img },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    Dalert(data.desc,"",refresh);
                    //parent.location.reload();
                }
            }
        });
    }
}
//刷新
function refresh() {
parent.location.reload();
}