//说明 所有的元素以ul li ul li ul li的循环格式嵌套 如果没有下级分类 就用li a结束嵌套
$(document).ready(function () {
    //$("#nav ul li").children("ul").hide();
    $("#nav").find("li").not(":has(ul)").children("a").css({ textDecoration: "none", color: "#fff", background: "none" })
   
    $("#nav").find("li:has(ul)").children("a").css({ background: "url(images/statu_close.gif) no-repeat left top;" })
    .click(function () {
        if ($(this).next("ul").is(":hidden")) {
            $(this).next("ul").slideDown();
            if ($(this).parent("li").siblings("li").children("ul").is(":visible")) {
                $(this).parent("li").siblings("li").find("ul").slideUp();
                $(this).parent("li").siblings("li:has(ul)").children("a").css({ background: "url(images/statu_close.gif) no-repeat left top;" })
                .end().find("li:has(ul)").children("a").css({ background: "url(images/statu_close.gif) no-repeat left top;" });
            }
            $(this).css({ background: "url(images/statu_open.gif) no-repeat left top;" });
            return false;
        } else {
            $(this).next("ul").slideUp();
            //不用toggle()的原因是为了在收缩菜单的时候同时也将该菜单的下级菜单以后的所有元素都隐藏
            $(this).css({ background: "url(images/statu_close.gif) no-repeat left top;" });
            $(this).next("ul").children("li").find("ul").fadeOut();
            $(this).next("ul").find("li:has(ul)").children("a").css({ background: "url(images/statu_close.gif) no-repeat left top;" });
            return false;
        }
    });
});