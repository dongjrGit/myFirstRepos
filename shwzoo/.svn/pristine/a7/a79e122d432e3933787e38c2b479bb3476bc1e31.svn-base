//订单列表
$(document).ready(function () {
    //返回
    $(".backbill").click(function () {
        var sta = GetQueryStringByName("sta");
        location.href = "/platform/businessbills/index?status=" + sta;
    })
})
function chakxq(obj){
	var sid = GetQueryStringByName("shopId");
    var id = $(obj).attr("data-bor");
    location.href = "/platform/businessbills/billddgldetail?shopId=" + sid + "&id=" + id+"&status="+GetQueryStringByName("status")+"&dateb="+GetQueryStringByName("dateb")+"&datee="+GetQueryStringByName("datee");
}
var pcount, pindex, psize = size_order;
var orderzy = "";
//页面加载，直营或店铺 0-店铺 1-直营
var list = {
    bind: function (index) {
        list.getlist(index);
    },
    //订单列表
    getlist: function (index) {
        var datahtml = "";

        var ordercode = $("#select_ordercode").val();
        var sid = GetQueryStringByName("shopId");
        var begin = $("#select_begin").val();
        var end = $("#select_end").val();
        $.ajax({
            url: "/platform/businessbillsAjax/orderindex",
            type: "Post",
            data: {
                "page": index, "size": psize, "ordercode": ordercode, "shopId": sid,"begintime": begin, "endtime": end
            },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
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
                                case 4: $obj.bind("click", function () { order.qxqr($obj.attr("data-oid"), $obj.attr("data-cancel")); }); break;
                                    //退货退款申请
                                case 10: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 1); }); break;
                                    //退货收货
                                case 12: $obj.bind("click", function () { order.sh($obj.attr("data-oid"), 1); }); break;
                                    //退款申请
                                case 20: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 2); }); break;
                                    //换货申请
                                case 30: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 3); }); break;
                                    //换货收货
                                case 32: $obj.bind("click", function () { order.sh($obj.attr("data-oid"), 2); }); break;
                                    //换货发货
                                case 33: $obj.bind("click", function () { order.afterfh($obj.attr("data-oid"), 2); }); break;
                                    //维修申请
                                case 40: $obj.bind("click", function () { order.thhsh($obj.attr("data-oid"), $obj.attr("data-status"), 4); }); break;
                                    //维修卖家收货
                                case 42: $obj.bind("click", function () { order.sh($obj.attr("data-oid"), 3); }); break;
                                    //维修卖家发货
                                case 43: $obj.bind("click", function () { order.afterfh($obj.attr("data-oid"), 3); }); break;
                            }
                        }

                    });
                    recordbind();//绑定订单状态变更记录
                }
            },
            error: function () {
            }
        });
    }
}
//分页回调函数
function pagelist(index) {
    list.getlist(index);
}
//按钮事件绑定
$(function () {
    $("input[name=search]").bind("click", search);

    var bt = GetQueryStringByName("dateb");
    var et = GetQueryStringByName("datee");
    $("#select_begin").val(bt);
    $("#select_end").val(et);
})
//查询
function search() {
    list.getlist(1);
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
            url: "/platform/businessbillsAjax/order",
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
