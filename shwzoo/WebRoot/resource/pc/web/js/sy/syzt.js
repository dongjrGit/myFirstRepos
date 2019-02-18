var jphtml = "", classhtml = "", sphtml = "";
var arr = [];
//获取首页专题
var syzt = {
    getzt: function () {
        var htmlzt = "", ztclass = "", g_xuanfu="";
        $.ajax({
            url: "/ProductTheme/W_GetList",
            type: "Post",
            data: {pageindex:1,pagesize:5},
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    alert(data.Desc);
                }
                else {
                    var len = data.Data.length;
                    if (len>0) {
                        for (var i = 0; i < len; i++) {
                            switch (i) {
                                case 0: ztclass = "<div class='g_ylnr'>"; break;
                                case 1: ztclass = "<div class='g_ylnr g_flno2'>"; break;
                                case 2: ztclass = "<div class='g_ylnr g_flno3'>"; break;
                                case 3: ztclass = "<div class='g_ylnr g_flno4'>"; break;
                                default: break;
                            }
                            //debugger;
                            htmlzt += ztclass;
                            if (i <= 3) {
                                htmlzt += "<div class=g_zcdhq'>";
                                htmlzt += "<a href='#' name='" + data.Data[i].Name + "'></a>";
                                htmlzt += " <p class='g_flbt'>" + data.Data [i].Name+ "</p>";
                                htmlzt += "</div>";
                               
                                htmlzt += "<div class='g_ycqhq'>";
                                //获取 精品推荐 和分类
                                syzt.getclass(data.Data[i].ID);
                                htmlzt += classhtml;
                                //获取 商品列表
                                syzt.getspuByzt(data.Data[i].ID);
                                htmlzt += jphtml;
                                htmlzt += sphtml;
                                //重置
                                classhtml = ""; jphtml = ""; sphtml = "";
                                htmlzt += "</div>";
                                htmlzt += "<div class='clear'></div>";
                                htmlzt += "</div>";
                                g_xuanfu += " <li><a href='#" + data.Data[i].Name + "'>" + data.Data[i].Name + "</a></li>";
                            }
                            else { break;}
                        }
                        $(".g_flspnr").html(htmlzt);
                        $(".g_xuanfu ul").html(g_xuanfu);
                        init();
                    }
                  //  debugger;
                   
                }
            },
            error: function () {

            }
        })
    },
    getclass: function (proTid) {
        $.ajax({
            url: "/ProductTheme/W_GetProClassList",
            type: "Post",
            data: { "pageindex": 1, "pagesize": 5, "proTId": proTid },
            dataType: "json",
            async:false,
            success: function (data) {
                if (data.Code < 0) {
                    alert(data.Desc);
                }
                else {
                    var len = data.Data.length;
                    classhtml += "<div class='g_flqhtou'>";
                    classhtml += "<div class='g_flqhdjdx'>";
                    classhtml += "<a href='javascript:void(0)' class='g_flqhdqys'>精品推荐</a>";
                    arr = [];
                    if (len > 0) {
                        for (var i = 0; i < len; i++) {
                            arr.push(data.Data[i].Cid);
                            classhtml += "<a href='javascript:void(0)'>" + data.Data [i].Cname + "</a>";
                        }
                    }
                    classhtml += "</div>";
                    //classhtml += "<a href='#' class='g_more'>更多>></a>";
                    classhtml += "<div class='clear'></div>";
                    classhtml += "</div>";
                   
                }
            },
            error: function () {

            }
        })
        syzt.getspuByclass(proTid, arr);
    },
    getspuByzt: function (proTid) {
        $.ajax({
            url: "/ProductTheme/W_GetProSPUList",
            type: "Post",
            data: { pageindex: 1, pagesize: 10, proTId: proTid },
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.Code < 0) {
                    alert(data.Desc);
                }
                else {
                    var len = data.Data.length;
                    jphtml += "<div class='g_flqhshen'> ";
                    jphtml += "<div class='g_fllist g_dyfl'> ";
                    if (len > 0) {
                        jphtml += "<ul>";
                        for (var i = 0; i < len; i++) {
                            jphtml += "<li>";
                            jphtml += "<a href='/Web/Goods/pro_detail?sid=" + data.Data[i].SPUID + "&cid=" + data.Data[i].ClassID + "'>";
                            jphtml += "<img src='" + data.Data[i].ImgUrl + "' />";
                            jphtml += "<span class='g_zkspm'>" + data.Data[i].SPname + "</span>";
                            //jphtml += "<span class='g_zkjbj'>￥" + data.Data[i].Price + "</span>";
                            jphtml += "<span class='g_zkjbj'>￥";
                            if (data.Data[i].Price == undefined || data.Data[i].Price == null) {
                                jphtml += "0.00";
                            }
                            else {
                                price = changeTwoDecimal_f(data.Data[i].Price);
                                jphtml += price;
                            }
                            jphtml += "</span>";
                            jphtml += "</a>";
                            jphtml += "</li>";
                        }
                        jphtml += "</ul>";
                    }
                    jphtml += "<div class='clear'></div>";
                    jphtml += "</div>";
                    jphtml += "</div>";
                }
            },
            error: function () {

            }
        })
    },
    getspuByclass: function (proTid,Cid) {
        //GetSPUByProClass
        for (var i = 0; i < Cid.length;i++) {
            $.ajax({
                url: "/ProductTheme/W_GetSPUByProClass",
                type: "Post",
                data: { pageindex: 1, pagesize: 10, ClassID: Cid[i], proTId: proTid },
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.Code < 0) {
                        alert(data.Desc);
                    }
                    else {
                        var len = data.Data.length;
                        var price = "";
                        sphtml += "<div class='g_flqhshen'> ";
                        sphtml += "<div class='g_fllist'> ";
                        if (len > 0) {
                            sphtml += "<ul>";
                            for (var i = 0; i < len; i++) {
                                sphtml += "<li>";
                                sphtml += "<a href='/Web/Goods/pro_detail?sid=" + data.Data[i].ID + "&cid=" + data.Data[i].ClassID + "'>";
                                sphtml += "<img src='" + data.Data[i].ImgUrl + "' />";
                                sphtml += "<span class='g_zkspm'>" + data.Data[i].Name + "</span>";
                                sphtml += "<span class='g_zkjbj'>￥";
                                if (data.Data[i].Price == undefined || data.Data[i].Price == null) {
                                    sphtml += "0.00";
                                }
                                else {
                                    price = changeTwoDecimal_f(data.Data[i].Price);
                                    sphtml += price;
                                }
                                sphtml += "</span>";
                                sphtml += "</a>";
                                sphtml += "</li>";
                            }
                            sphtml += "</ul>";
                        }
                        sphtml += "<div class='clear'></div>";
                        sphtml += "</div>";
                        sphtml += "</div>";
                    }
                },
                error: function () {

                }
            })
        }
      
    }
}
//获取首页每日精彩
var pcount = "", pindex = "", p_page;
var Mrjc = {
    bind: function (index) {
        var datahtml = "", hyhhtml = "";
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
