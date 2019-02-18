//库存商品列表

var pcount, pindex, psize = size_product;
var SKU = {
    bind: function () {
        $(".chaxun").first().bind("click", search);
    },
    getSKUList: function (index) {
        var shopid = $("#select_shop").attr("data");
        var spuid = $("#select_spuid").val();
        $.ajax({
            url: "/Product/GetSKUListBySPU",
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
function sptj(skuname,skuid) {
    d = dialog({
        title: skuname + '的调价',
        url: 'goods_setPrice?skuid=' + skuid,
        width: 520,
        height: 240,
        //fixed: true
        //modal: true, //蒙层
    });
    d.show();
}
function closeDialog() { d.close(); }

//$(function () {
//    autoxl.bind("select_seller", getSellerStartwithName);
//    autoxl.bind("select_shop", getShopStartwithName);
//})

/*
callback 成功 有数据时 调的方法 
event 事件
*/
function getSellerStartwithName(callback, event) {
    var name = $("#select_seller").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/Account/GetAllUserStartwithName",
        type: "Post",
        data: { "UserNameLike": name, "UserType": 5 },
        dataType: "json",
        success: function (data) {
            if (data.Code == 0) {
                var listdata = {
                    list: data.Data
                }
                var html = template('select_sellerlist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.Data);
            }
        }
    });
}
/*
callback 成功 有数据时 调的方法 
event 事件
*/
function getShopStartwithName(callback, event) {
    var name = $("#select_shop").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/Shop/GetListStartwithName",
        type: "Post",
        data: { "name": name },
        dataType: "json",
        success: function (data) {

            if (data.Code == 0) {
                var listdata = {
                    list: data.Data
                }
                var html = template('select_shoplist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.Data);
            }
        }
    });
}
