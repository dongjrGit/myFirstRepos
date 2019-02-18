//订单驳回列表
var pcount, pindex, psize = size_order;
var orderzy = "";
//页面加载，直营或店铺 0-店铺 1-直营
var listbh = {
    bind: function (zy) {
        orderzy = zy;
       
    },
    //获取驳回订单列表
    getlist: function (index, orderzy) {
        var datahtml = "";
        var sUrl = "/Order_Platform/GetDPBHList";
        if (orderzy == 1) {
            sUrl = "/Order_Platform/GetZYBHList";
        }
        var status = $("#orderstatus").val();
        var ordercode = $("#select_ordercode").val();
        //var sellerid = $("#select_seller").attr("data");
        var sid = $("#select_shop").attr("data");
        var buyerid = $("#select_buyer").attr("data");
        var begin = $("#select_begin").val();
        var end = $("#select_end").val();
        $.ajax({
            url: sUrl,
            type: "Post",
            data: {
                "page": index, "size": psize, "status": status, "ordercode": ordercode, "shopid": sid,
                "buyerid": buyerid, "begin": begin, "end": end
            },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }

                    var html = template('ddlist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
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
}
//分页回调函数
function pagelist(index) {
    listbh.getlist(index, orderzy);
}
//按钮事件绑定
$(function () {
    $("input[name=search]").bind("click", search);
    //autoxl.bind("select_seller", getSellerStartwithName);
    autoxl.bind("select_shop", list.getShopStartwithName);
    autoxl.bind("select_buyer", getBuyerStartwithName);
})
//查询
function search() {
    listbh.getlist(1, orderzy);
}

///*
//callback 成功 有数据时 调的方法 
//event 事件
//*/
////卖家下拉框绑定
//function getSellerStartwithName(callback, event) {
//    var name = $("#select_seller").val();
//    if (event)
//        name += String.fromCharCode(event.keyCode);
//    $.ajax({
//        url: "/Account/GetAllUserStartwithName",
//        type: "Post",
//        data: { "UserNameLike": name, "UserType": 5 },
//        dataType: "json",
//        success: function (data) {
//            if (data.Code == 0) {
//                var listdata = {
//                    list: data.Data
//                }
//                var html = template('select_sellerlist', listdata);

//                if (callback) {
//                    callback(html);
//                }
//            } else {
//                Dalert(data.Data);
//            }
//        }
//    });
//}

//买家下拉框绑定
function getBuyerStartwithName(callback, event) {
    var name = $("#select_buyer").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/Account/GetAllUserStartwithName",
        type: "Post",
        data: { "UserNameLike": name, "UserType": 2 },
        dataType: "json",
        success: function (data) {
            if (data.Code == 0) {
                var listdata = {
                    list: data.Data
                }
                var html = template('select_buyerlist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.Data);
            }
        }
    });
}