var btnhtml = "";
//首页广告
var sygg = {
    init: function () {
        var gghtml = "", spanhtml = "";;
        $.ajax({
            url: "/Advertising/W_GetList",
            type: "Post",
            data: { "index": 1, "size": 5 },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    alert(data.Desc);
                } else {
                    var len = data.Data.length;
                    if (len > 0) {
                        gghtml += "<ul> ";
                        for (var i = 0; i<len;i++){
                            gghtml += "<li><a href='" + data.Data[i].Url + "'><img src='" + data.Data[i].Imag + "' /></a></li>";
                            spanhtml += "<span></span>";
                        }
                    }
                    //g_tpzdgd
                    gghtml += "</ul>";
                    gghtml += "<img src='/web/imgs/g_08.png' class='g_djxzan' />";
                    gghtml += "<img src='/web/imgs/g_09.png' class='g_djxyan' />";
                    gghtml += "<div class='g_szbf'>";
                    gghtml += spanhtml;
                    gghtml += "<a href='javascript:void(0)'></a></div>";
                    $(".g_tpzdgd").html(gghtml);
                    /*调用*/
                    fade_banneras(".g_tpzdgd ul li", ".g_szbf span", ".g_djxzan", ".g_djxyan", "g_sbdqys", 5000)
                }
            },
            error: function () {
              
            }
        });
    },
    init2: function () {
        var gghtml = "";
        $.ajax({
            url: "/Advertising/W_GetList",
            type: "Post",
            data: { "index": 1, "size": 8 },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    alert(data.Desc);
                } else {
                    var len = data.Data.length;
                    if (len > 0) {
                        for (var i = 0; i < len; i++) {
                            if (i == 0) gghtml += "<a href='" + data.Data[i].Url + "'><img id='adImage" + i + "' src='" + data.Data[i].Imag + "' class='jd_ad_img' /></a>";
                            else
                                gghtml += "<a href='" + data.Data[i].Url + "'><img id='adImage" + i + "' src='" + data.Data[i].Imag + "' data-src='" + data.Data[i].Imag + "'class='jd_ad_img' /></a>";
                            btnhtml += "<a href='javascript:' class='jd_ad_btn_a' data-rel='adImage" + i + "'>" + (i + 1) + "</a>";
                        }
                        gghtml += " <div id='jdAdBtn' class='jd_ad_btn'> ";
                        //gghtml += btnhtml;
                        gghtml += "</div> ";
                    }
                   
                    $("#jdAdSlide").html(gghtml);
                    adbtn();
                }
            },
            error: function () {

            }
        });
    }
}

function adbtn() {
    $("#jdAdBtn").html(btnhtml).find("a").powerSwitch({
        eventType: "hover",
        classAdd: "active",
        animation: "fade",
        autoTime: 5000,
        onSwitch: function (image) {
            if (!image.attr("src")) {
                image.attr("src", image.attr("data-src"));
            }
        }
    }).eq(0).trigger("mouseover");
    btnhtml = "";
}
