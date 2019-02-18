//直营商品-库存管理
var pcount, pindex, psize = size_product;
var SKU = {
    bind: function () {
        $(".chaxun").first().bind("click", search);
    },
    getSKUList: function (index) {
        var shopid = $("#select_shop").attr("data");
        var spuid = $("#select_spuid").val();
        $.ajax({
            url: "/Product/GetZYSKUListBySPU",
            type: "Post",
            data: { "spuid": spuid, "shopid": shopid, "page": index, "size": psize },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('skulist', listdata);
                    $("#list_title").html(html);

                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                }
            },
            error: function () {

            }
        })
    }
}
function pagelist(index) {
    SKU.getSKUList(index);
}
function search() {
    SKU.getSKUList(1);
}
var d = [];
function sptj(skuname, skuid) {
    d = dialog({
        title: skuname + '的调价',
        url: 'goods_spgl_sptj?skuid=' + skuid,
        width: 400,
        height: 240,
        //fixed: true
        //modal: true, //蒙层
    });
    d.show();
}
function closeDialog() { d.close(); }




