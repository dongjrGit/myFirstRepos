/**
 * 商品详情：介绍-规格-售后
 */
$(document).ready(function () {
    //标签切换
    $("#prodtap ul li").click(function () {
        $("#prodtap ul li").each(function () {
            $(this).removeClass("red");
        })
        $(this).addClass("red");
        var tag = $(this).attr("tagdata");
        if (tag == "0") {
            $("#spuInfo").attr("style", "display:block");
            $("#spuspecs").attr("style", "display:none");
            $("#spupacking").attr("style", "display:none");
        } else {
            if (tag == "1") {
                $("#spuInfo").attr("style", "display:none");
                $("#spuspecs").attr("style", "display:block");
                $("#spupacking").attr("style", "display:none");
            } else {
                $("#spuInfo").attr("style", "display:none");
                $("#spuspecs").attr("style", "display:none");
                $("#spupacking").attr("style", "display:block");
            }
        }
    })
//    var spuid = GetQueryStringByName("spuid");
//    if(spuid!=""&&spuid!=undefined)
  
//    var skuid = GetQueryStringByName("skuid");
//    if (skuid != "" && skuid != undefined)
  
   
})
//根据spuID获取Sku数据
function GetSPU(spuid) {
    $.ajax({
        url: "/api/wap/products/getproddesc",
        type: "Post",
        data: { "ch":3,"sid": spuid },
        dataType: "json",
        success: function (res) {
            if (res.code == 0) {
                var data = res.data;
                if (data != undefined && data != null) {
                    $("#spuInfo").html(data.SpuInfo);   
                }
            }
        }
    })
}
//根据skuID获取商品规格数据
function GetSkuSpecs(skuid) {
    $.ajax({
        url: "/api/wap/products/getprodspecinfo",
        type: "Post",
        data: { "ch":3,"kid": skuid,},
        dataType: "json",
        success: function (res) {
            if (res.code == 0) {
                var data = res.data;
                if (data != undefined && data != null) {
                    var listdata = {
                        list: data
                    }
                    var html = template('skuspecs', listdata);
                    $("#dataspecs").html(html);
                }
            }
        }
    })
}
//获取售后服务
function GetAfterServices(sid) {
    $.ajax({
        url: "/api/wap/products/getprodass",
        type: "Post",
        data: { "ch":3,"sid": sid,},
        dataType: "json",
        success: function (res) {
            if (res.code == 0) {
                var data = res.data;
                if (data != undefined && data != null) {                   
                    $("#spupacking").html(data);
                }
            }
        }
    })
}
