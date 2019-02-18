// JavaScript Document

$(document).ready(function () {
    //首页顶部 网站导航 鼠标放上去 弹出内容
    $("#l_wzdh").mouseenter(function () {
        $(".l_wzdhcon").addClass("l_seenter");
        $(this).addClass("l_bjbs");
        $(this).find(".l_toprow").addClass("l_toprow01");
    });
    $("#l_wzdh").mouseleave(function () {
        $(".l_wzdhcon").removeClass("l_seenter");
        $(this).removeClass("l_bjbs");
        $(this).find(".l_toprow").removeClass("l_toprow01");
    });
    //首页顶部 客户服务 鼠标放上去 弹出内容
    $("#l_khfw").mouseenter(function () {
        $(".l_khfwcon").addClass("l_seenter");
        $(this).addClass("l_bjbs");
        $(this).find(".l_toprow").addClass("l_toprow01");
    });
    $("#l_khfw").mouseleave(function () {
        $(".l_khfwcon").removeClass("l_seenter");
        $(this).removeClass("l_bjbs");
        $(this).find(".l_toprow").removeClass("l_toprow01");
    });

    //通用头部搜索切换
    $('#search-hd .search-input').on('input propertychange', function () {
        var val = $(this).val();
        if (val.length > 0) {
            $('#search-hd .pholder').hide(0);
        } else {
            var index = $('#search-bd li.selected').index();
            $('#search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
        }
    })
    $('#search-bd li').click(function () {
        var index = $(this).index();
        $('#search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
        $('#search-hd .search-input').eq(index).show().siblings('.search-input').hide(0);
        $(this).addClass('selected').siblings().removeClass('selected');
        $('#search-hd .search-input').val('');
    });

    //商品列表页图片效果
    $(".l_xdimg").mouseenter(function () {
        $(this).siblings("div").css("display", "block")
    });
    $(".l_xdimg").mouseleave(function () {
        $(this).siblings("div").css("display", "none")
    });



    //我的订单  右边高度与左边高度相等
    $(".l_ddmkcon").each(function () {
        var rheight = $(this).find(".l_ddmk01con").height();
        $(this).find(".l_ddmk01con").siblings(".l_ddmk02, .l_ddmk03, .l_ddmk04").css("height", rheight - 30 + "px");
    });

    //优惠券关闭按钮
    $(".y_lqts .close").mouseleave(function () {
        $(".y_lqts").css("display", "none")
    });

    //未登录弹出登录框的下面遮罩层   获取高度
    var hheight = document.body.clientHeight;
    $(".r_loginhui").css("height", hheight + "px");

    //所有文本框获取焦点时  字体深色
    $("input[type=text]").focus(function () {
        $(this).css("color", "#595959")
    });

    //店铺的首页导航部分  点击全部分类  显示内容
    //$(".l_qbfldp").mouseenter(function () {
    //    $(".l_qbflconall").css("display", "block")
    //});
    //$(".l_qbfldp").mouseleave(function () {
    //    $(".l_qbflconall").css("display", "none")
    //});
    var shopid = parseInt($("#shopid").val());
    if (shopid > 0) {
        getShop(shopid);
    }
    //添加店铺关注
    $("#adcon").click(function () {
        var sid = parseInt($("#shopid").val());
        var ishas = $("#sName").html();
        if (sid > 0 && ishas != "") {
            if (user.IsLogin()) {
                AddShopCon(sid);
            }
            else {
                //TODO 登陆
                showlogindiv();
            }
        }
    });

    //领取优惠券
    $(".l_yhqdp03").click(function () {
        if (user.IsLogin()) {
            var cid = $(this).attr("data");
            if (cid != undefined && cid != "") {
                GetUserCoupon(cid);
            }
        }
        else {
            //TODO 登陆
            showlogindiv();
        }
    });
    //进入用户中心
    $("#membercenter").click(function () {
        if (user.IsLogin()) {
            window.location.href = '/Member/zhsz/PersonalInformation';
        } else {
            //TODO 登陆
            showlogindiv();
        }
    })
});

//店铺首页轮番
$(function () {
    //首页 轮播
    if ($('#index-bxslider').length > 0) {
        $('#index-bxslider').bxSlider({
            auto: true,
            pagerCustom: '#bx-pager',
            nextText: ' ',
            prevText: ' ',
            speed: 600,
            pause: 3000
        });
    };
});
//获取店铺信息
function getShop(id) {
    $.ajax({
        type: "post",
        url: "/Shop/GetShopByID",
        dataType: "json",
        data: { "id": id },
        success: function (rsl) {
            if (rsl.Code == 0) {
                var data = rsl.Data;
                if (data != null && data != "" && data != undefined) {
                    $("#sName").html(data.Name);
                }
            }
        },
        error: function (e) {
        }
    });
}
//添加店铺关注
function AddShopCon(id) {
    $.ajax({
        type: "post",
        url: "/Concern/W_AddConcern",
        dataType: "json",
        data: { "productID": id, "type": 1 },
        success: function (rsl) {
            if (rsl.Code == 0) {
                alert(rsl.Desc);
            } else {
                alert(rsl.Desc);
            }
        },
        error: function (e) {
        }
    });

}
//用领取优惠券
function GetUserCoupon(id) {
    $.ajax({
        type: "post",
        url: "/Coupon/M_GetConpon",
        dataType: "json",
        data: { "couponid": id, "count": 1 },
        success: function (rsl) {
            alert(rsl.Desc);
        },
        error: function (e) {
        }
    });

}