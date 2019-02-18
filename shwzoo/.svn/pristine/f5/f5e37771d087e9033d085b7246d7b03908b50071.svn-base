//关注的店铺
$(document).ready(function () {
    var myScroll,
 upIcon = $("#up-icon"),
 downIcon = $("#down-icon"),
 distance = 10; //滑动距离

    myScroll = new IScroll('#wrapper', { probeType: 3, mouseWheel: true });

    myScroll.on("scroll", function () {
        var y = this.y,
            maxY = this.maxScrollY - y,
            downHasClass = downIcon.hasClass("reverse_icon"),
            upHasClass = upIcon.hasClass("reverse_icon");

        if (y >= distance) {
            !downHasClass && downIcon.addClass("reverse_icon");
            return "";
        } else if (y < distance && y > 0) {
            downHasClass && downIcon.removeClass("reverse_icon");
            return "";
        }

        if (maxY >= distance) {
            !upHasClass && upIcon.addClass("reverse_icon");
            return "";
        } else if (maxY < distance && maxY >= 0) {
            upHasClass && upIcon.removeClass("reverse_icon");
            return "";
        }
    });

    // 下拉刷新事件
    myScroll.on("slideDown", function () {
        if (this.y > distance) {
            index = index + 1;
            //downAjax();
            upIcon.removeClass("reverse_icon")
        }
    });
    // 上拉滑动事件
    myScroll.on("slideUp", function () {
        if (this.maxScrollY - this.y > distance) {
            index = index + 1;
            var attribu = $(document).find("a").attr("data-type");
            if (attribu == 2) {
                concernShop.getconsernshoplist(GetQueryStringByName("page"), false);
            } else {
                concernShop.getconcedrnprolist(GetQueryStringByName("page"), "", false);
            }
            upIcon.removeClass("reverse_icon")
        }
    });
    concernShop.getconsernshoplist(GetQueryStringByName("page"), true);
});
var size = 10;
var concernShop = {
    //获取关注的店铺列表
    getconsernshoplist: function (page)
    {
        $("#concernshophuoqu").show();
        $("#concernpro").hide();
        $.ajax(({
            type: "post",
            url: "/AppConcern/GetShopConcerns",
            dataType: "json",
            data: { page: page, size: size, token: "" },
            success: function (fh)
            {
                if (fh.Code == 0) {
                    if (fh.Data != "") {
                        var concernshoplist = {
                            list: fh.Data
                        }
                        var html = template('concernshop', concernshoplist);                       
                            $("#concernshophuoqu").html(html);
                      
                    }

                    else { $("#concernshophuoqu").html("<div class='l_dpqjdmk'><i></i>您还没有关注任何店铺</div>"); }
                }
                else { $("#concernshophuoqu").html("<div class='l_dpqjdmk'><i></i>您还没有关注任何店铺</div>"); }
            },
            error:function(){{ $("#concernshophuoqu").html("<div class='l_dpqjdmk'><i></i>数据请求失败</div>"); }}
        }))
    },
    getconcedrnprolist: function (page, ordertype)
    {
        $("#concernpro").show();
        $("#concernshophuoqu").hide();
        $.ajax(({
            type: "post",
            url: "/AppConcern/GetSkuConcern",
            dataType:"json",
            data: { page: page, size: size, ordertype: "", token: "" },
            success: function (fh)
            {
                if (fh.Code == 0) {
                    if (fh.Data != "") {
                        var concernprolist =
                            {
                                list: fh.Data
                            }
                        var html = template('concereprolist', concernprolist);
                      
                            $("#concernpro").html(html);
                       
                        $("#defualt").bind("click", Default);
                        $("#promotion").bind("click", Promotion)
                    }
                    else { $("#concernpro").html("<div class='l_mslist'><i></i>您还没有关注任何商品</div>"); }

                }
                else { $("#concernpro").html("<div class='l_mslist'><i></i>您还没有关注任何商品</div>"); }
            },
            error: function () { $("#concernpro").html("<div class='l_mslist'><i></i>数据请求失败</div>"); }
        }))
    }
}
//点击默认
function Default()
{
        concernShop.getconcedrnprolist(1, "",true);
}
//点击促销优先
function Promotion()
{
    concernShop.getconcedrnprolist(1, 1,true);
}