//订单跟踪
var logisticInfo = {
    getlogisticInfo: function () {
        var shipperCode = GetQueryStringByName("shipperCode");
        var logisticCode = GetQueryStringByName("logisticCode");
        $.ajax (({
            type: "post",
            url: "/AppKuaiDi/GetOrderTracesApp",
            dataType: "json",
            data: { shipperCode: shipperCode, logisticCode: logisticCode },
            success: function (fh)
            {
                if (fh.Code == 0) {
                    var logisticInfo = {
                        list: JSON.parse(fh.Data).Traces,
                        orderCode: JSON.parse(fh.Data).EBusinessID,
                        shopperCode: JSON.parse(fh.Data).ShipperCode
                    }
                    var html = template('logisticInfo', logisticInfo);
                    $(".container").html(html)
                    $("#orderCode").html(GetQueryStringByName("ordercode"));
                    $("#paytype").html(GetQueryStringByName("paytype"));
                }

            },
            error: function (e) {
               // alert(e.statusText);
            }
        }))
        
    }
}