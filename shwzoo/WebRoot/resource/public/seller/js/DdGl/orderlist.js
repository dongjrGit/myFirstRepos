//订单列表
var pcount, pindex, psize = size_order;
function getlistByStatus(index) {
    var datahtml = "";
    var code = $("#code").val();
    var wlcode = $("#logisticscode").val();
    var add_begin = $("#add_begin").val();
    var add_end = $("#add_end").val();
    $.ajax({
        url: "/seller/shoporder/getsendOrderList",
        type: "Post",
        data: {
            "page": index, "size": psize, "num": code, "logisticscode": wlcode, "strat": add_begin,
            "end": add_end
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
                $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                //根据订单状态绑定事件
                var status;
                $("#datalist").find("a").each(function (e) {
                    var $obj = $(this);
                    if ($obj.attr("data-status") != undefined) {
                        status = $obj.attr("data-status");
                        switch (parseInt(status)) {
                            case 1: $obj.bind("click", function () { order.fh($obj.attr("data-oid"), $obj.attr("data-status"), 1); }); break;
                        }
                    }

                });
//                recordbind();
            }
        },
        error: function () {

        }
    });
}

function pagelist(index) {
    list.getlistByStatus(index);
}
//清空查询
function reset() {
    $("#code").val("");
    $("#logisticscode").val("");
    $("#add_begin").val("");
    $("#add_end").val("");
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