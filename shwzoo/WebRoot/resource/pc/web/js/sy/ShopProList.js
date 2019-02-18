$(document).ready(function () {
    GetProList(1, 10, "", "", "", "");
    //排序
    $(".l_dplbr_top ul li").live("click", function () {
        $(".l_dplbr_top ul li").removeClass("active");
        $(this).addClass("active");
        var orderby = $(this).attr("data");
        GetProList(1, 10, "", "", orderby, "");
    });
    //类别点击
    $(".l_dpflmk01").live("click", function () {
        var cid = $(this).attr("cid");
        if (cid != null && cid != undefined) {
            $("#classid").val(cid);
        } else {
            $("#classid").val("0");
        }
        var orderby = 0;
        $(".l_dplbr_top ul li").each(function () {
            if ($(this).attr("class") == "active") {
                orderby = $(this).attr("data");
            }
        })
        $("#spname").val("");
        GetProList(1, 10, "", "", orderby, "");
    });
    //搜索点击
    $("#searchsp").click(function () {

        var orderby = 0;
        $(".l_dplbr_top ul li").each(function () {
            if ($(this).attr("class") == "active") {
                orderby = $(this).attr("data");
            }
        })
        GetProList(1, 10, "", "", orderby, "");
    });
    //导航点击
    $(".l_dpflcon ul li").live("click", function () {
        var cid = $(this).attr("cid");
        if (cid != null && cid != undefined) {
            $("#classid").val(cid);

        } else {
            $("#classid").val("0");
        }
        var orderby = 0;
        $(".l_dplbr_top ul li").each(function () {
            if ($(this).attr("class") == "active") {
                orderby = $(this).attr("data");
            }
        })
        $("#spname").val("");
        GetProList(1, 10, "", "", orderby, "");
    });
    //商品关注点击
    $(".l_jgzdp").live("click", function () {
        var sid = $(this).attr("data");
        if (sid != null && sid != undefined) {
            if (user.IsLogin()) {
                addCon(sid);
            }
            else {
                //TODO 登陆
                showlogindiv();
            } 
        }
    });
    //商品小图点击
    $(".l_dplbxt ul li").live("click", function () {
        $(this).parent().find("li").removeClass("active");
        $(this).addClass("active");
        var imgsrc = $(this).find("img").attr("src");
        if (imgsrc != "" && imgsrc != undefined) {
            $(this).parent().parent().parent().find(".l_dpimg").find("img").attr("src", imgsrc);
        }
    });
   
});
var psize = 0, pshopid = 0, pcid = 0, pname = "", pminp = 0, pmaxp = 0, porder = 0, pordert = 0;
function GetProList(index, size, minPrice, maxPrice, orderby, orderType) {
    var shopid = $("#shopid").val();
    var customcid = $("#classid").val();
    if (customcid == 0) {
        customcid = "";
    }
    var proName = $("#spname").val();
    if (proName == undefined) {
        proName = "";
    }
    $.ajax({
        type: "post",
        url: "/HomeFloor/W_GetShopClassPro",
        dataType: "json",
        data: { "index": index, "size": size, "shopid": shopid, "customcid": customcid, "proName": proName, "minProce": minPrice, "maxPrice": maxPrice, "orderby": orderby, "orderType": orderType },
        success: function (rsl) {
            if (rsl.Code == 0) {
                var listdata = {
                    list: rsl.Data
                }
                var html = template('sprolist', listdata);

                $("#pro_Data").html(html);

                pcount = rsl.MaxRow;
                pindex = rsl.PageIndex;
                psize = size;
                pshopid = shopid;
                pcid = customcid;
                pname = proName;
                pminp = minPrice;
                pmaxp = maxPrice;
                porder = orderby;
                pordert = orderType;
                $("#pager").html(pagination(pcount, pindex, 10, "pagelist"));
            }
        },
        error: function (e) {
        }
    });
}
//分页回调
function pagelist(index) {
    GetProList(index, psize, pshopid, pcid, pname, pminp, pmaxp, porder, pordert);
}

function addCon(sid) {
    $.ajax({
        type: "post",
        url: "/Concern/W_AddConcern",
        dataType: "json",
        data: { "productID": sid, "type": 0 },
        success: function (rsl) {
            if (rsl.Code == 0) {
                alert(rsl.Desc);
            }
        },
        error: function (e) {
        }
    });
}