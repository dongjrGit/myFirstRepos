//首页-每日精彩
var pcount = "", pindex = "", p_page;
var Mrjc = {
    bind: function (index) {
        var datahtml = "",hyhhtml="";
        $.ajax({
            url: "/sy_mrjc/W_GetList",
            type: "Post",
            data: { "index": index, "size": 10 },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    alert(data.Desc);
                } else {
                    var len = data.Data.length;
                    if (len > 0) {
                        for (var i = 0; i < len; i++) {
                            datahtml += "<li>";
                            datahtml += "<a href='/Web/Goods/pro_detail?sid=" + data.Data[i].SPUID + "&cid=" + data.Data[i].ClassID + "'><img src='" + data.Data[i].ImgUrl + "' width='215px' height='140px' /></a>";
                            datahtml += "<h3><a href='#'>" + data.Data[i].SpuName + "</a></h3>";
                            datahtml += "<p>￥";
                            if (data.Data[i].Price == undefined || data.Data[i].Price == null) {
                                datahtml += "0.00";
                            }
                            else {
                                price = changeTwoDecimal_f(data.Data[i].Price);
                                datahtml += price;
                            }
                            datahtml += "</p>";
                            datahtml += "</li>";
                        }
                        datahtml += "<div class='clear'></div>";
                        pcount = data.MaxRow;
                        pindex = data.PageIndex;
                        if ((pcount % 10) == 0) p_page = pcount / 10;
                        else {
                            p_page = (pcount / 10) + 1;
                        }
                        if ((pindex + 1) > p_page) {
                            pindex = 0;
                        }
                        hyhhtml = "<p>每日精彩</p>";
                        hyhhtml += "<a onclick='Mrjc.bind(" + (pindex + 1) + ")'>换一换</a>";
                        hyhhtml += "<div class='clear'></div>";
                    }
                    $(".g_zksplist ul").html(datahtml);
                    $(".g_zktoub").html(hyhhtml);
                }
            },
            error: function () {
                
            }
        });
    }
}

function changeTwoDecimal_f(x) {
    //  debugger;
    var f_x = parseFloat(x);
    var f_x = Math.round(x * 100) / 100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    return s_x;
}

