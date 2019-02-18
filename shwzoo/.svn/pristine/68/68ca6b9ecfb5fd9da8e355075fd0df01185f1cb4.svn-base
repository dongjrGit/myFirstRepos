//订单列表
var pcount, pindex, psize = size_order;
var orderstatus = "";
var list = {
    bind: function () {
        //list.getlist(1);
    },
    getlist: function (index) {
        var datahtml = "";
        var code = $("#code").val();
        var wlcode = $("#logisticscode").val();
        var add_begin = $("#add_begin").val();
        var add_end = $("#add_end").val();
        var d_begin = $("#Deliver_begin").val();
        var d_end = $("#Deliver_end").val();
        var status = $("#status").val();
        var qrbegin=$("#qr_begin").val();
        var qrend=$("#qr_end").val();
        var state=$("#state").val();
        $.ajax({
            url: "/seller/shoporder/getOrderList",
            type: "Post",
            data: {
                "page": index, "size": psize, "num": code, "logisticscode": wlcode, "start": add_begin,
                "end": add_end,"status": status,"sendstart": d_begin,"sendend": d_end
                ,"qrbegin":qrbegin,"qrend":qrend,"state":state
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

                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "list.getlist"));
                    //根据订单状态绑定事件
                    var status;
                    $("#datalist").find("a").each(function (e) {
                        var $obj = $(this);
                        if ($obj.attr("data-status") != undefined) {
                            status = $obj.attr("data-status");
                            switch (parseInt(status)) {
                               /* //发货
                                case 1: $obj.bind("click", function () { order.fh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                                    //取消申请
                                case 4:
                                case 6: $obj.bind("click", function () { order.qxqr($obj.attr("data-oid"), $obj.attr("data-cancel"), $obj.attr("data-status")); }); break;
                                    //退货退款申请
                                case 50: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 1); }); break;
                                    //退货收货
                                case 52: $obj.bind("click", function () { order.sh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                                    //退款申请
                                case 9: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 2); }); break;
                                    //换货申请
                                case 30: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 3); }); break;
                                    //换货收货
                                case 32: $obj.bind("click", function () { order.sh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                                    //换货发货
                                case 33: $obj.bind("click", function () { order.afterfh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                                    //维修申请
                                case 40: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 4); }); break;
                                    //维修卖家收货
                                case 42: $obj.bind("click", function () { order.sh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                                    //维修卖家发货
                                case 43: $obj.bind("click", function () { order.afterfh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;*/
                            	//发货
                            case 1: $obj.bind("click", function () { order.fh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                                //退款申请
                            case 22: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 2); }); break;
                            }
                        }

                    });
//                    recordbind();
                }
            },
            error: function () {

            }
        });
    },
    gethistory: function (index) {
        var datahtml = "";
        var code = $("#code").val();
        var wlcode = $("#logisticscode").val();
        var add_begin = $("#add_begin").val();
        var add_end = $("#add_end").val();
        var d_begin = $("#Deliver_begin").val();
        var d_end = $("#Deliver_end").val();
        var status = $("#status").val();
        $.ajax({
            url: "/seller/shoporder/GetHistoryList",
            type: "Post",
            data: {
            	  "page": index, "size": psize, "num": code, "logisticscode": wlcode, "start": add_begin,
                  "end": add_end,"status": status,"sendstart": d_begin,"sendend": d_end
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
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "list.gethistory"));
//                    recordbind();
                }
            },
            error: function () {

            }
        });
    },
    getthList: function (index) {
        var datahtml = "";
        var code = $("#code").val();
        var wlcode = $("#logisticscode").val();
        var add_begin = $("#add_begin").val();
        var add_end = $("#add_end").val();
        var d_begin = $("#Deliver_begin").val();
        var d_end = $("#Deliver_end").val();
        var status = $("#status").val();
        $.ajax({
            url: "/seller/shoporder/getReturnList",
            type: "Post",
            data: {
            	 "page": index, "size": psize, "num": code, "logisticscode": wlcode, "start": add_begin,
                 "end": add_end,"status": status,"sendstart": d_begin,"sendend": d_end
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

                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "list.getthList"));
                    //根据订单状态绑定事件
                    var status;
                    $("#datalist").find("a").each(function (e) {
                        var $obj = $(this);
                        if ($obj.attr("data-status") != undefined) {
                            status = $obj.attr("data-status");
                            switch (parseInt(status)) {
                                /*//退货退款申请
                                case 50: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 1); }); break;
                                    //退货收货
                                case 52: $obj.bind("click", function () { order.sh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                                    //退款申请
                                case 20: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 2); }); break;*/
                            		//退款申请
                            	case 20: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 2); }); break;
                            }
                        }

                    });
//                    recordbind();
                }
            },
            error: function () {

            }
        });
    },
    gethhList: function (index) {
        var datahtml = "";
        var code = $("#code").val();
        var wlcode = $("#logisticscode").val();
        var add_begin = $("#add_begin").val();
        var add_end = $("#add_end").val();
        var d_begin = $("#Deliver_begin").val();
        var d_end = $("#Deliver_end").val();
        var status = $("#status").val();
        $.ajax({
            url: "/seller/shoporder/getExchangeList",
            type: "Post",
            data: {
            	"page": index, "size": psize, "num": code, "logisticscode": wlcode, "start": add_begin,
                "end": add_end,"status": status,"sendstart": d_begin,"sendend": d_end
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

                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "list.gethhList"));
                    //根据订单状态绑定事件
                    var status;
                    $("#datalist").find("a").each(function (e) {
                        var $obj = $(this);
                        if ($obj.attr("data-status") != undefined) {
                            status = $obj.attr("data-status");
                           /* switch (parseInt(status)) {
                                //换货申请
                                case 30: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 3); }); break;
                                    //换货收货
                                case 32: $obj.bind("click", function () { order.sh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                                    //换货发货
                                case 33: $obj.bind("click", function () { order.fh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                            }*/
                        }

                    });
//                    recordbind();
                }
            },
            error: function () {

            }
        });
    },
    getwxList: function (index) {
        var datahtml = "";
        var code = $("#code").val();
        var wlcode = $("#logisticscode").val();
        var add_begin = $("#add_begin").val();
        var add_end = $("#add_end").val();
        var d_begin = $("#Deliver_begin").val();
        var d_end = $("#Deliver_end").val();
        var status = $("#status").val();
        $.ajax({
            url: "/seller/shoporder/GetWXList",
            type: "Post",
            data: {
            	"page": index, "size": psize, "num": code, "logisticscode": wlcode, "start": add_begin,
                "end": add_end,"status": status,"sendstart": d_begin,"sendend": d_end
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

                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "list.gethhList"));
                    //根据订单状态绑定事件
                    var status;
                    $("#datalist").find("a").each(function (e) {
                        var $obj = $(this);
                        if ($obj.attr("data-status") != undefined) {
                            status = $obj.attr("data-status");
                           /* switch (parseInt(status)) {
                                //维修申请
                                case 40: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 4); }); break;
                                    //维修卖家收货
                                case 42: $obj.bind("click", function () { order.sh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                                    //维修卖家发货
                                case 43: $obj.bind("click", function () { order.fh($obj.attr("data-oid"), $obj.attr("data-status")); }); break;
                            }*/
                        }

                    });
//                    recordbind();
                }
            },
            error: function () {

            }
        });
    }
}

$(function () {
    $("input[name=search]").bind("click", function () { list.getlist(1); });
    $("input[name=searchHis]").bind("click", function () { list.gethistory(1); });
    $("input[name=searchTH]").bind("click", function () { list.getthList(1); });
    $("input[name=searchHH]").bind("click", function () { list.gethhList(1); });
    $("input[name=searchWX]").bind("click", function () { list.getwxList(1); });
    $("input[name=reset]").bind("click", reset);
})
function reset() {
    $("#code").val("");
    $("#logisticscode").val("");
    $("#add_begin").val("");
    $("#add_end").val("");
    $("#Deliver_begin").val("");
    $("#Deliver_end").val("");
    $("#status").val("");
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
            url: "/Order_Seller/GetStatusRecords",
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
