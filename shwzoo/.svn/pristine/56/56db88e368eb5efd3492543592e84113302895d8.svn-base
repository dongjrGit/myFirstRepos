//评论订单列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var BuyerOrderList = {
    bind: function (index) {
        //获取订单列表
        $.ajax(({
            type: "post",
            url: "/Order_Member/B_GetOrderList",
            dataType: "json",
            data: { PageIndex: index, PageSize: psize },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
                    }
                    var html = '<tr class="tab-title"><td width="420px">宝贝</td><td width="90px">数量</td><td width="130px">价格</td><td width="180px">订单状态</td><td>操作</td></tr>' + template('orderslist', listdata);
                    $("#orderslist_table").html(html);
                    pcount = rsl.MaxRow;
                    pindex = rsl.PageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "orders_pagelist"));
                    //产品页面跳转
                    $(".a_spuhref").bind("click", Goods_Href);
                    //拆单详情
                    $(".a_orderdisconnect").bind("click", Order_Disconnect);
                    //订单详情
                    $(".a_orderdetailinfo").bind("click", Order_DetailInfo);
                    //评价晒单
                    $(".a_comment").bind("click", Order_Comment);
                    //取消订单
                    $(".a_ordercancel").bind("click", Order_Cancel);
                    //去付款
                    $(".payment-but").bind("click", GoPayment);
                    //申请售后
                    $(".a_afterservice").bind("click", Order_Afterservice);
                    //确认收货
                    $(".a_confirmgoods").bind("click", Order_ConfirmGoods);
                    //换货待收货确认
                    $(".a_confirmreturngoods").bind("click", Order_ConfirmReturnGoods);
                }
                else {
                    Dalert(rsl.Desc);
                }
            },
            error: function (e) {

            }
        }));
    },
    getlistByStatus: function (index, status, seachstr) {
        $.ajax(({
            type: "post",
            url: "/Order_Member/B_GetOrderList",
            dataType: "json",
            data: { PageIndex: index, PageSize: psize, OrderStatus: status, SearchStr: seachstr },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
                    }
                    var html = '<tr class="tab-title"><td width="420px">宝贝</td><td width="90px">数量</td><td width="130px">价格</td><td width="180px">订单状态</td><td>操作</td></tr>' + template('orderslist', listdata);
                    $("#orderslist_table").html(html);
                    pcount = rsl.MaxRow;
                    pindex = rsl.PageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "orders_pagelist"));
                    //产品页面跳转
                    $(".a_spuhref").bind("click", Goods_Href);
                    //拆单详情
                    $(".a_orderdisconnect").bind("click", Order_Disconnect);
                    //订单详情
                    $(".a_orderdetailinfo").bind("click", Order_DetailInfo);
                    //评价晒单
                    $(".a_comment").bind("click", Order_Comment);
                    //取消订单
                    $(".a_ordercancel").bind("click", Order_Cancel);
                    //去付款
                    $(".payment-but").bind("click", GoPayment);
                    //申请售后
                    $(".a_afterservice").bind("click", Order_Afterservice);
                    //确认收货
                    $(".a_confirmgoods").bind("click", Order_ConfirmGoods);
                    //换货待收货确认
                    $(".a_confirmreturngoods").bind("click", Order_ConfirmReturnGoods);
                }
                else {
                    Dalert(rsl.Desc);
                }
            },
            error: function (e) {

            }
        }));
    },
    getCancellist: function (index) {
        $.ajax(({
            type: "post",
            url: "/Order_Member/B_GetOrderList",
            dataType: "json",
            data: { PageIndex: index, PageSize: psize, OrderStatus: 5 },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
                    }

                    //数据列表为空样式加载
                    $("#div_nolist").remove();
                    if (listdata.list.length <= 0) {
                        var htmno = '<div class="l_wplsp" id="div_nolist"><img src="/web_new/images/plno.png"><p>当前暂无取消的订单~</p></div>';
                        $("#orderslist_table").after(htmno);
                        return;
                    }

                    var html = '<tr class="tab-title" id="orderslist_table"><td width="100px">订单编号</td><td width="120px">订单商品</td><td>提交时间</td><td>支付明细</td><td>退款状态</td><td>操作</td></tr>' + template('orderslist', listdata);
                    $("#orderslist_table").html(html);
                    pcount = rsl.MaxRow;
                    pindex = rsl.PageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "order_pagetcancellist"));
                    //产品页面跳转
                    $(".a_spuhref").bind("click", Goods_Href);
                    //拆单详情
                    $(".a_orderdisconnect").bind("click", Order_Disconnect);
                    //订单详情
                    $(".a_orderdetailinfo").bind("click", Order_DetailInfo);
                    //评价晒单
                    $(".a_comment").bind("click", Order_Comment);
                    //取消订单
                    $(".a_ordercancel").bind("click", Order_Cancel);
                    //去付款
                    $(".payment-but").bind("click", GoPayment);
                    //申请售后
                    $(".a_afterservice").bind("click", Order_Afterservice);
                    //确认收货
                    $(".a_confirmgoods").bind("click", Order_ConfirmGoods);
                    //换货待收货确认
                    $(".a_confirmreturngoods").bind("click", Order_ConfirmReturnGoods);
                }
                else {
                    Dalert(rsl.Desc);
                }
            },
            error: function (e) {

            }
        }));
    },
}

//分页回调
function orders_pagelist(index) {
    BuyerOrderList.bind(index);
}
//取消订单分页回调
function order_pagetcancellist(index) {
    BuyerOrderList.getCancellist(index);
}


//产品页面跳转
function Goods_Href() {
    var skuid = parseInt($(this).attr("sku-val"));
    $.ajax(({
        type: "post",
        url: "/Order_Member/B_GetSpuID",
        dataType: "json",
        data: { SkuID: skuid },
        success: function (rsl) {
            if (rsl.Code == 0) {
                var spuid = parseInt(rsl.Data);
                if (spuid > 0) {
                    window.location.href = "/Web/Goods/pro_detail?sid=" + spuid;
                }
            }
            else {
                Dalert(rsl.Desc);
            }
        },
        error: function (e) {

        }
    }));
}

//拆单详情
function Order_Disconnect() {
    var orderID = $(this).parent().find(".hidden_orderid").val();
    window.location.href = "Url?orderID=" + orderID;
}

//订单详情
function Order_DetailInfo() {
    var orderID = $(this).parent().parent().find(".hidden_orderid").val();
    var orderdetailID = $(this).parent().parent().find(".hidden_orderdetailid").val();
    window.location.href = "/Member/DdZx/OrderDetail?orderID=" + orderID;
}

//评价晒单
function Order_Comment() {
    var orderID = $(this).parent().parent().find(".hidden_orderid").val();
    var orderdetailID = $(this).parent().parent().find(".hidden_orderdetailid").val();
    window.location.href = "/Member/KhFwmemb/GoodsComment?orderdetailID=" + orderdetailID;
}

//订单取消
function Order_Cancel() {

    var orderID = $(this).parent().parent().find(".hidden_orderid").val();
    var orderdetailID = $(this).parent().parent().find(".hidden_orderdetailid").val();

    var d = dialog({
        title: '提示',
        content: '取消原因：<select id="select_reason" name="" class="n_inpqyzc"><option value="-1">请选择</option><option value="1">现在不想购买</option><option value="2">重复下单</option><option value="3">无法支付订单</option><option value="4">其它</option></select>',
        okValue: '确定',
        ok: function () {
            var reasonval = parseInt($("#select_reason").val());
            var reasontext = $('#select_reason option:selected').text()
            if (reasonval < 0) {
                Dalert("请选择取消原因");
                return false;
            }

            this.title('提交中…');
            $.ajax(({
                type: "post",
                url: "/Order_Member/B_CancelOrder",
                dataType: "json",
                data: { orderID: orderID, reason: reasontext },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        window.location.reload();
                    }
                    else {
                        Dalert(rsl.Desc);
                    }
                },
                error: function (e) {

                }
            }));
        },
        cancelValue: '取消',
        cancel: function () { }
    });
    d.width(480);
    d.height(240);
    d.show();
}

//去付款
function GoPayment() {
    var orderID = $(this).parent().parent().find(".hidden_orderid").val();
    var orderdetailID = $(this).parent().parent().find(".hidden_orderdetailid").val();
    var ordergroupcode = $(this).parent().parent().find(".hidden_ordergroupcode").val();
    window.location.href = "/Web/Orders/order_pay?gc=" + ordergroupcode;
}

//申请售后
function Order_Afterservice() {
    var orderID = $(this).parent().parent().find(".hidden_orderid").val();
    var orderdetailID = $(this).parent().parent().find(".hidden_orderdetailid").val();
    window.location.href = "/Member/KhFwmemb/RepairOrReturn?orderID=" + orderID;
}

//确认收货
function Order_ConfirmGoods() {
    var orderID = $(this).parent().parent().find(".hidden_orderid").val();
    var orderdetailID = $(this).parent().parent().find(".hidden_orderdetailid").val();
    $.ajax(({
        type: "post",
        url: "/Order_Member/B_ConfirmReceivePro",
        dataType: "json",
        data: { orderID: orderID },
        success: function (rsl) {
            if (rsl.Code == 0) {
                window.location.reload();
            }
            else {
                Dalert(rsl.Desc);
            }
        },
        error: function (e) {

        }
    }));
}

//换货待收货确认
function Order_ConfirmReturnGoods() {
    var orderID = $(this).parent().parent().find(".hidden_orderid").val();
    var orderdetailID = $(this).parent().parent().find(".hidden_orderdetailid").val();
    $.ajax(({
        type: "post",
        url: "/Order_Member/B_ConfirmReceivePro",
        dataType: "json",
        data: { orderID: orderID },
        success: function (rsl) {
            if (rsl.Code == 0) {
                window.location.reload();
            }
            else {
                Dalert(rsl.Desc);
            }
        },
        error: function (e) {

        }
    }));
}