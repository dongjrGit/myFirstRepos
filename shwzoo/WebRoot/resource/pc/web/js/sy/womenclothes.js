//女装首页
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

});



