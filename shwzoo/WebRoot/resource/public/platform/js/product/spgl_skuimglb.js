//库存商品图片列表
var pcount, pindex, psize = size_product;
var Img = {
    bind: function () {
        Img.getImgList(1,Img.unit);
    },
    getImgList: function (index,callback) {
        var skuid = $("#inp_skuid").val();
        $.ajax({
            url: "/ProductImgs/G_GetListBySKUID",
            type: "Post",
            data: { "skuid": skuid, "page": index, "size": psize },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('imglist', listdata);
                    $("#list_title").html(html);
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                    if (callback) {
                        callback();
                    }
                    //绑定点击图片查看大图
                    $(".skuimg img").each(function () {
                        $(this).click(function () {
                            if ($(this).attr("src") != "" && $(this).attr("src") != undefined)
                                Show("bigimg", $(this).attr("src"));
                        });
                    });
                }
            },
            error: function () {

            }
        })
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/ProductImgs/P_Del",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    //Dalert(data.Desc);
                    if (data.Code == 0) {
                        //location.reload();
                        Dalert(data.Desc, "", refresh);
                    } else {
                        Dalert(data.Desc);
                    }
                }
            })
        }
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", Img.del);
        });
    }
}
function pagelist(index) {
    Img.getImgList(1,Img.unit);
}
function refresh() {
location.reload();
}