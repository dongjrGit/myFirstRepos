var classhtml = "";
//获取商品分类
var syclass = {
    bind: function () {
        $.ajax({
            url: "/Class/W_GetSystemClassTree",
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    alert(data.Desc);
                } else {
                    var len = data.Data.length;
                    var Fitem = "<div class='item bo'>";
                    if (len > 0) {
                        classhtml += Fitem;
                        for (var i = 0; i < len; i++) {
                            if (i > 0) { classhtml += "<div class='item'> "; }
                            classhtml += "<h3><span>·</span><a href='#'>" + data.Data[i].Name + "</a></h3> ";
                            classhtml += "<div class='item-list clearfix' style='z-index:999999;height:458px;'> ";
                            classhtml += "<div class='subitem' style='z-index:999999;height:458px;'> ";
                            var len2=data.Data[i].Children.length;
                            for (var j = 0; j < len2; j++) {
                                classhtml += "<dl class='fore1'> ";
                                classhtml += "<dt><a href='#'>" + data.Data[i].Children[j].Name + "</a></dt> ";
                                classhtml += "<dd>";
                                var len3 = data.Data[i].Children[j].Children.length;
                                for (var k = 0; k < len3; k++) {
                                        classhtml += "<em><a href='/Web/Goods/GoodsList?classid=" + data.Data[i].Children[j].Children[k].ID + "'>" + data.Data[i].Children[j].Children[k].Name + "</a></em>";                            
                                }
                                classhtml += "</dd>";
                                classhtml += "</dl>";
                            }
                            classhtml += "</div> ";
                            classhtml += "</div> ";
                            classhtml += "</div> ";
                        }
                        $(".all-sort-list").html(classhtml);
                        hover();
                    }
                    
                }
            },
            error: function () {

            }
        })
    }
}

function hover() {
    $('.all-sort-list > .item').hover(function () {
        var eq = $('.all-sort-list > .item').index(this),				//获取当前滑过是第几个元素
            h = $('.all-sort-list').offset().top,						//获取当前下拉菜单距离窗口多少像素
            s = $(window).scrollTop(),									//获取游览器滚动了多少高度
            i = $(this).offset().top,									//当前元素滑过距离窗口多少像素
            item = $(this).children('.item-list').height(),				//下拉菜单子类内容容器的高度
            sort = $('.all-sort-list').height();						//父类分类列表容器的高度

        if (item < sort) {												//如果子类的高度小于父类的高度
            if (eq == 0) {
                $(this).children('.item-list').css('top', (i - h));
            } else {
                $(this).children('.item-list').css('top', (i - h) + 1);
            }
        } else {
            if (s > h) {												//判断子类的显示位置，如果滚动的高度大于所有分类列表容器的高度
                if (i - s > 0) {											//则 继续判断当前滑过容器的位置 是否有一半超出窗口一半在窗口内显示的Bug,
                    $(this).children('.item-list').css('top', (s - h) + 2);
                } else {
                    $(this).children('.item-list').css('top', (s - h) - (-(i - s)) + 2);
                }
            } else {
                $(this).children('.item-list').css('top', 0);
            }
        }

        $(this).addClass('hover');
        $(this).children('.item-list').css('display', 'block');
    }, function () {
        $(this).removeClass('hover');
        $(this).children('.item-list').css('display', 'none');
    });

    $('.item > .item-list > .close').click(function () {
        $(this).parent().parent().removeClass('hover');
        $(this).parent().hide();
    });
}