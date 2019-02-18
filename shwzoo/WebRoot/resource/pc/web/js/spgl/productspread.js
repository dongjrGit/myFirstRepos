//商品左侧推广
var Zctg = {
    bind: function (claid) {
        var datahtml = "";
        $.ajax({
            url: "/ProductSpread/W_GetList",
            type: "Post",
            data: { "index": 1, "size": 8, "cid": claid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    alert(data.Desc);
                } else {
                    var len = data.Data.length;
                    if (len > 0) {
                        for (var i = 0; i < len; i++) {
                            datahtml += "<li>";
                            datahtml += "<a href='pro_detail?sid=" + data.Data[i].SPUID + "&cid=" + data.Data[i].ClassID + "'><img class='mg' src='" + data.Data[i].ImgUrl + "' /></a>";
                            datahtml += "<h3><a href='#'>" + data.Data[i].SpuName + " " + data.Data[i].ProNum + " " + data.Data[i].Tag + " " + data.Data[i].Pack + "</a></h3>";

                            datahtml += "<div class='jg'>￥";
                            if (data.Data[i].Price == undefined || data.Data[i].Price == null) {
                                datahtml += "0.00";
                            }
                            else {
                                price = changeTwoDecimal_f(data.Data[i].Price);
                                datahtml += price;
                            }
                            datahtml += "</div>";
                            //datahtml += "<div class='pj'><a href='#'>" + data.Data[i].CommentCount + "</a></div>";
                            datahtml += " <div class='pj'><a href='#'>";
                            if (data.Data[i].CommentCount == null) {
                                datahtml += 0;
                            }
                            else {
                                datahtml += data.Data[i].CommentCount;
                            }
                            datahtml += " </a>个评价</div>";
                            datahtml += "</li>";
                        }
                        $(".y_tuigChnap").html(datahtml);
                    }
                }
            },
            error: function () {
                //alert("数据加载失败");
            }
        });
    }
}
function changeTwoDecimal_f(x) {
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