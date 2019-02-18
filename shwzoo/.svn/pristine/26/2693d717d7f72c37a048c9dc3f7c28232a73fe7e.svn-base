//全部订单
var size =10;
var orderList = {
    //获取用户的全部订单列表
    getlist: function (index)
    {
        $.ajax(({
            type: "post",
            url: "/AppOrder/GetOrderPageList",
            dataType: "json",
            data: { token: "", index: index, size: size, status: "", searchStr: "", begin: "", end: "" },
            success: function (fh)
            {
                if (fh.Code == 0) {
                    var orderlist =
                        {
                            list:fh.Data
                        }
                    var html = template('orderslist', orderlist);
                    $("#l_ddzcgm").bind("click", AppriseOrder);
                    $("#l_djzhifu").bind("click", Payorder);
                    $("#l_querenshouhuo").bind("click", Comfrimgoods);
                    $("#l_qbddtop").after(html);
          
                }
            },
            error: function (e) {

        }
        }))
    },
    //删除订单
    //del: function (id)
    //{
    //    $.ajax(({
    //        type: "post",
    //        url: "",
    //        dataType: "json",
    //        data: {},
    //    }))
    //}
}
//点击评价晒单
function AppriseOrder()
{
    var orderid = $("#l_ddzcgm").attr("orderid-val");
    window.location.href = "" + orderid;
}
//点击支付
function Payorder()
{
    var orderid = $("#l_djzhifu").attr("orderid-val");
    window.location.href = "" + orderid;
}
//点击确认收货
function Comfrimgoods()
{
    var orderId = $("#l_querenshouhuo").attr("orderid-val");
    $.ajax(({
        type: "post",
        url: "/AppOrder/ConfirmOrder",
        dataType: "json",
        data: { token:"", orderid: orderId },
        success: function (fh) {
            if (fh.Code == 0) {
                window.location.reload();
            }
            else {
                Dalert(fh.Desc);
            }
        },
        error: function (e) {

        }
    }));
}
