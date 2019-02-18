//订单发票列表
var pcount, pindex, psize = size_order;
var orderzy = "";
//页面加载，直营或店铺 0-店铺 1-直营
var list = {
    bind: function (zy) {
        orderzy = zy;
    },
    //获取发票列表
    getlist: function (index) {
        var datahtml = "";
        var code = $("#code").val();
        var type = $("#type").val();
        var add_begin = $("#select_begin").val();
        var add_end = $("#select_end").val();
        var status = $("#status").val();
        var sUrl = "/platform/order/getdpInvoiceList";
        if (orderzy == 1) { sUrl = "/platform/order/getzyInvoiceList"; }
        $.ajax({
            url: sUrl,
            type: "Post",
            data: {"page": index, "size": psize, "code": code, "type": type, "start": add_begin,"end": add_end, "status": status},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }
                    var html = template('invlist', listdata);

                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "list.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    /*
    callback 成功 有数据时 调的方法 
    event 事件
    */
        getShopStartwithName: function (callback, event) {
            var name = $("#select_shop").val();
            if (event)
                name += String.fromCharCode(event.keyCode);
            $.ajax({
                url: "/platform/shop/getShopStartWithName",
                type: "Post",
                data: { "name": name },
                dataType: "json",
                success: function (data) {

                    if (data.code == 0) {
                        var listdata = {
                            list: data.data
                        }
                        var html = template('select_shoplist', listdata);

                        if (callback) {
                            callback(html);
                        }
                    } else {
                        Dalert(data.desc);
                    }
                }
            });
        }
}
//按钮事件绑定
$(function () {
    $("input[name=search]").bind("click", function () { list.getlist(1); });
})
//更改打印状态 标记打印
function setStatus(id) {
    $.ajax({
        url: "/platform/order/changeInvStatus",
        type: "Post",
        data: { "id": id },
        dataType: "json",
        success: function (data) {
            if (data.code < 0) { Dalert(data.desc); }
            else {
                Dalert(data.desc);
                var td_html = "<span class='huise'>已打印</span>";

                $("#td_" + id).html(td_html);
                $("#tds_" + id).html(td_html);
            }
        },
        error: function () {
        }
    });
}