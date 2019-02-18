//获取商品列表
var claid = "";
var list = {
    bind: function () {
        claid = $("#cid").val();
      
        $("span[name='search_values']").each(function () {
            $(this).bind("click", list.checkValueSelect);
        });
    },
    getlist: function (index) {
        //[{attrType:1,typeID:1,ischeckbox:0,values:[{type:1,value:1-5},{type:0,value:2.5}]},
        // {attrType:1,typeID:1,ischeckbox:0,values:[{type:1,value:1-5},{type:0,value:2.5}]}]
        var searchStr = "[";
        $("#search_table").find("div").each(function () {
            var $attrdiv = $(this);
            var selected = $attrdiv.find("span[data_tag='1']").length;
            if (selected > 0) {
                searchStr += "{attrType:" + $attrdiv.attr("data_attrType");
                searchStr += ",typeID:" + $attrdiv.attr("data_typeID");
                searchStr += ",ischeckbox:" + $attrdiv.attr("data") + ",values:[";
                $attrdiv.find("span[data_tag='1']").each(function () {
                    $valuetd = $(this);
                    searchStr += "{type:" + $valuetd.attr("data_type");
                    //searchStr += ",value:'" + $valuetd.html() + "'},"
                    searchStr += ",value:'" + $valuetd.attr("data_value") + "'},"
                })
                searchStr = searchStr.substr(0, searchStr.length - 1);
                searchStr += "]},"
            }
        })
        if (searchStr != "[")
            searchStr = searchStr.substr(0, searchStr.length - 1);
        searchStr += "]";

        var pcount;
        var pindex;
        var psize = 20;
        var datahtml = "";
        if (claid > 0) {
            $.ajax({
                url: "/SearchProducts/W_GetProListByQuery",
                type: "Post",
                data: { cid: claid, page: index, size: psize, searchStr: searchStr },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        alert(data.Desc);
                    } else {
                        var len = data.Data.length;
                        for (var i = 0; i < len; i++) {
                            datahtml += " <li>";
                            datahtml += " <div class='img'><a href='pro_detail?sid="+data.Data[i].SPUID+"'><img src='" + data.Data[i].ImgUrl + "' /></a></div>";
                            datahtml += " <div class='h'>";
                            datahtml += " <a href='pro_detail?sid=" + data.Data[i].SPUID + "'>" + data.Data[i].Name + " " + data.Data[i].ProNum + " " + data.Data[i].Tag + "</a>";
                            datahtml += " </div>";
                            //datahtml += " <div class='jg'>" + data.Data[i].Price + "</div>";
                            datahtml += " <div class='jg'> ";
                            if (data.Data[i].Price == undefined || data.Data[i].Price == null) {
                                datahtml += "0.00";
                            }
                            else {
                                price = changeTwoDecimal_f(data.Data[i].Price);
                                datahtml += price;
                            }
                            datahtml += " </div> ";
                            datahtml += " <div class='pj'><a href='#'>";
                            if (data.Data[i].CommentCount == null) {
                                datahtml += 0;
                                                    }
                                                        else {
                                datahtml += data.Data[i].CommentCount;
                                                        }
                            datahtml += " 个评价</a></div>";
                            datahtml += " <div class='btn'>";
                            //datahtml += " <a href='#' class='lk'>放入购物车</a>";
                            //datahtml += " <a href='#' class='lk'>一键购买</a>";
                            datahtml += " <a href='#' class='gz'>关注</a>";
                            datahtml += " </div>";
                            datahtml += " </li>";
                        }
                        $(".y_mainPorR_list").html(datahtml);
                        pcount = data.MaxRow;
                        pindex = data.PageIndex;

                        //分页
                        $("#pager").html(pagination(pcount, pindex, psize, "list.getlist"));
                    }
                },
                error: function () {
                }
            });
        }
    },
    getClass: function () {
        var childclass = "";
        var datahtml = "";
        $.ajax({
            url: "/Class/W_GetFirstClass",
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    alert(data.Desc);
                } else {
                    var len = data.Data.length;
                    for (var i = 0; i < len; i++) {

                        datahtml += " <li>";
                        datahtml += " <a href='GoodsList?classid=" + data.Data[i].ID + "' class='g_lbmc'>" + data.Data[i].Name + "</a>";
                        getChildClass(data.Data[i].ID);
                        datahtml += childclass;

                        datahtml += " </li>";
                    }
                    $(".g_zcsxq ul").html(datahtml);
                }
            },
            error: function () {
            }
        });
    },
    checkValueSelect: function () {
        var $selectspan = $(this);
        var id = $selectspan.attr("data_tag");
        if (id == "0") {
            if ($selectspan.parent().attr("data") == "0") {
                $selectspan.parent().children("span").each(function () {
                    if ($selectspan != $(this)) {
                        $(this).attr("data_tag", "0");
                        $(this).removeAttr("style");
                    }
                })
            }
            $selectspan.attr("data_tag", "1");
            $selectspan.css("color", "red");
        } else {
            $selectspan.attr("data_tag", "0");
            $selectspan.removeAttr("style");
        }
        list.getlist(1);
    }
}


function search() {
    var strDiv = $("#searchDiv").val();

}

function getChildClass(cid) {
    var datahtml = "";
    $.ajax({
        url: "/Class/W_GetChildrenByFatherID",
        type: "Post",
        data: { fatherid: cid },
        dataType: "json",
        async: false,
        success: function (data) {
            if (data.Code < 0) {
                alert(data.Desc);
            } else {
                var len = data.Data.length;
                datahtml += " <div class='g_lbsp'>";
                for (var i = 0; i < len; i++) {
                    datahtml += " <a href='GoodsList?classid=" + data.Data[i].ID + "'>" + data.Data[i].Name + "</a>";
                }
                datahtml += " </div>";
                datahtml += " <div class='g_clxk'></div>";

                childclass = datahtml;
            }
        },
        error: function () {
        }
    });
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