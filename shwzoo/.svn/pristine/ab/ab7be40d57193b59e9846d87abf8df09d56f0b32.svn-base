//品牌管理
$(document).ready(function () {
    //关闭弹出层事件
    $(".l_close").bind("click", function () { $("#bigimg").hide(); });
    $("#bigimg img").bind("click", Jump);
    Brand.getAll(1, 10);
    //修改
    $(document).on("change","span[name=editbrand]", function () {
        var type = GetQueryStringByName("tp");
        var id = $(this).attr("data_id");
        var reid = GetQueryStringByName("id");
        location.href = "BrandMarketEdit?type=" + type + "&id=" + id + "&reid=" + reid;
    });
    //添加
    $("input[name=add]").click(function () {
        var type = GetQueryStringByName("tp");
        var reid = GetQueryStringByName("id");

        location.href = "BrandMarketEdit?type=" + type + "&id=-1" + "&reid=" + reid;
    });
})
var pcount;
var pindex;
var psize;
var Brand = {
    getAll: function (index, psize) {
        var id = GetQueryStringByName("id");
        $.ajax({
            url: "/APP_Market/GetBrandMarket",
            type: "Get",
            data: { "index": index, "size": psize, "reID": id },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('brandlist', listdata);
                    //$("#list_title").after(html);
                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#list_title").parent().children().each(function () {
                        if ($(this).attr('id') != "list_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#list_title").after(html);
                    reid = id;
                    psize = psize;
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                    //点击图片查看大图
                    $(".ppimg img").each(function () {
                        $(this).click(function () {
                            if ($(this).attr("src") != "" && $(this).attr("src") != undefined)
                                Show("bigimg", $(this).attr("src"));
                        });
                    });
                }
            },
            error: function () {

            }
        });
    },
    //删除
    del: function (id) {
        $.ajax({
            url: "/APP_Market/DelBrandMarket",
            type: "Post",
            data: { "bID": id },
            dataType: "json",
            success: function (data) {
                Dalert(data.Desc, "", refresh);
                //location.reload();
            }
        });
    }
}
function Del(id) {
    ConfirmShow("确定要删除吗？", Brand.del, id, "");
}
function refresh() {
    location.reload();
}
function pagelist(index) {
    Brand.getAll(index, psize);
}
//点击大图跳转方法
function Jump() {
    var imgurl = $("#bigimg img").attr("src");
    var surl = "/Platform/img/ImageShow";
    ImageJump(imgurl, surl);
}
//修改排序
function setOrder(id, ob) {
    var obtext = $("#" + ob).val();
    $.ajax({
        url: "/APP_Market/SetBrandOrder",
        type: "Post",
        data: { "id": id, "orderby": obtext },
        dataType: "json",
        success: function (data) {
            Dalert(data.Desc);
        },
        error: function () {
            Dalert("修改排序失败");
        }
    });
}