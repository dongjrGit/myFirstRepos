var pcount, pindex, psize = 10;
var Img = {
    bind: function () {
        Img.getImgList(1, Img.unit);
    },
    getImgList: function (index, callback) {
        var skuid = $("#inp_skuid").val();
        $.ajax({
            url: "/ProductImgs/G_GetListBySKUID",
            type: "Post",
            data: { "skuid": skuid, "page": index, "size": psize },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    alert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('imglist', listdata);
                    $("#list_title").html(html);
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "Img.pagelist"));
                    if (callback) {
                        callback();
                    }
                }
            },
            error: function () {

            }
        })
    },
    del: function () {
        if (ConfirmShow("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/ProductImgs/S_Del",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    alert(data.Desc);
                    if (data.Code == 0) {
                        location.reload();
                    }
                }
            })
        }
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", Img.del);
        });
    },
    pagelist: function (index) {
        Img.getImgList(index, Img.unit);
    }
}