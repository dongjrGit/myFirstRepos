//订单列表

var pcount, pindex, psize = size_order;
var orderzy = "";
//页面加载，直营或店铺 0-店铺 1-直营
var list = {
    bind: function (zy) {
        orderzy = zy;
    },
    //订单列表
    getlist: function (index, orderzy) {
        var datahtml = "";
        var sUrl = "/platform/order/getShopOrderList";
        if (orderzy == 1) {
            sUrl = "/platform/order/getZyOrderList";
        }
        var status = $("#orderstatus").val();
        var ordercode = $("#select_ordercode").val();
        var sid = $("#select_shop").attr("data");
        var buyerid = $("#select_buyer").attr("data");
        var begin = $("#select_begin").val();
        var end = $("#select_end").val();
        var state = $("#state").val();
        $.ajax({
            url: sUrl,
            type: "Post",
            data: {
                "page": index, "size": psize, "status": status, "num": ordercode, "shopid": sid,
                "buyerid": buyerid, "start": begin, "end": end, "state": state
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('ddlist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));

                    //根据订单状态绑定事件
                    var status;
                    $("#datalist").find("a").each(function (e) {
                        var $obj = $(this);
                        if ($obj.attr("data-status") != undefined) {
                            status = $obj.attr("data-status");
                            switch (parseInt(status)) {
                                    //发货
                                case 1: $obj.bind("click", function () { order.fh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                                    //取消申请
                                case 4:
                                case 6: $obj.bind("click", function () { order.qxqr($obj.attr("data-oid"), $obj.attr("data-cancel"), $obj.attr("data-status")); }); break;
                                case 22: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 2); }); break;
                            }
                        }

                    });
//                    recordbind();//绑定订单状态变更记录
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
//分页回调函数
function pagelist(index) {
    list.getlist(index, orderzy);
}
//按钮事件绑定
$(function () {
    $("input[name=search]").bind("click", search);
    autoxl.bind("select_buyer", getBuyerStartwithName);
})
//查询
function search() {
    list.getlist(1, orderzy);
}

function getBuyerStartwithName(callback, event) {
    var name = $("#select_buyer").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/accounts/getUserStartWithName",
        type: "Post",
        data: { "name": name, "usertype": 2 },
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('select_buyerlist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.desc);
            }
        }
    });
}

//绑定订单状态变更记录
function recordbind() {
    //状态记录绑定
    $(".l_xsztdj").bind("click", function (e) {
        //鼠标点击上一个div没有关闭
        if ($('.l_xsztcon').css('display', 'block')) {
            $('.l_xsztcon').css('display', 'none');
        }
        var orderid = $(this).attr("data-id");
        $.ajax({
            url: "/Order_Platform/GetStatusRecords",
            type: "Post",
            data: { "orderid": orderid, "index": 1, "size": size_ordstatus },
            dataType: "json",
            success: function (data) {
                Dalert(data.Desc);
                if (data.Code == 0) {
                    var rehtml = "";
                    for (var i = 0; i < data.Data.length ; i++) {
                        rehtml += "<li>";
                        rehtml += "订单状态：" + data.Data[i].NewValue + "</li>";
                        rehtml += "<li>操作时间：" + data.Data[i].CreateTime + "</li>";
                        rehtml += "<li>操作人：" + data.Data[i].CreateUserName;
                        rehtml += "</li>";
                    }
                    $(".l_xszt ul").html(rehtml);
                    if (data.Data.length > 0)
                        $("#divrecord_" + orderid).show();
                }
            }
        })
        stopPropagation(e);
    });
    $(document).bind('click', function () {
        $('.l_xsztcon').css('display', 'none');
    });
}

//对div的click事件绑定事件处理程序，阻止事件冒泡，防止其冒泡到document，而调用document的onclick方法隐藏了该div。
function stopPropagation(e) {
    if (e.stopPropagation)
        e.stopPropagation();
    else
        e.cancelBubble = true;
}
