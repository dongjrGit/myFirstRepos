//我的订单---完成
var size = 10;
/*var orderDetail = {
    getlist: function ()
    {
        var orderid = GetQueryStringByName("orderid");
        $.ajax(({
            type: "post",
            url: "/wap/order/getorder",
            dataType: "json",
            data: { token: "", orderid: orderid},
            success: function (fh)
            {
                if (fh.Code == 0) {
                    var orderlist =
                        {
                            list: fh.Data,
                            children: fh.Data.Children
                        }
                    var html = template('orderlist', orderlist);
                    $("#orderdetail").html(html);
                }
            },
            error: function (e) {

            }
        }))

    }
}*/
//评价晒单
function AppriseOrder() {
    var orderid = $("#l_ddzcgm").attr("orderid-val");
    window.location.href = "/Member/DdZx/CommentList?orderID=" + orderid;
}