$(function () {
   /* var code = $("#orderlogcode").val();//物流公司代码
    var num = $("#orderlognum").val();//物流单号*/    
	var orderid = $("#orderid").val();//订单id
    GetLogistic(orderid);
})
//code:物流公司代码,num:物流单号
function GetLogistic(orderid) {
    $.ajax({
        type: "post",
        url: "/pc/order/querytrack",
        dataType: "json",
        data: { orderid: orderid },
        success: function (res) {
        	var data = JSON.parse(res);
            if (data.code == 0) {
                var js = data.data;
                if (js.success == "true") {
                    var listdata = {
                        list: js
                    }
                    var html = template('datalog', listdata);
                    $("#trlog").parent().children().each(function () {
                        if ($(this).attr('id') != "trlog") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#trlog").after(html);
                } else {
                    var reason = "<tr>"
                               + "<td style='padding-top:20px;' colspan='3'><div class='n_ddgz-bottom'><span>"
                               + "获取物流信息失败，请去对应物流公司官网查询"
                               + "</span> <span class='lvse'></span></div></td>"
                               + "</tr>";
                    $("#trlog").after(reason);
                }
            }
            else {
                $("#trlog").parent().children().each(function () {
                    if ($(this).attr('id') != "trlog") {
                        this.parentNode.removeChild(this);
                    }
                })
                var reason = "<tr>"
                               + "<td style='padding-top:20px;' colspan='3'><div class='n_ddgz-bottom'><span>"
                               + "暂无物流信息"
                               + "</span> <span class='lvse'></span></div></td>"
                               + "</tr>";
                $("#trlog").after(reason);
            }
        },
        error: function (e) {
        }
    });
}