//商品评论
var index = 1, hasdata = true;
var myScroll;
var upIcon = $("#up-icon");
var downIcon = $("#down-icon");
var distance = 10;//滑动距离
var callback;
$(document).ready(function() {

    myScroll = new IScroll('#wrapper', {    
        tap:true,    
        click:true,
        probeType : 3,
        mouseWheel : true
    });
    $("#scroller-pullUp").hide();
    /*样式处理 页面滚动 上下div样式*/
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
    $("#scroller-pullUp").show();
    return "";
    } else if (maxY < distance && maxY >= 0) {
    upHasClass && upIcon.removeClass("reverse_icon");
    return "";
    }
    });

    // 下拉刷新事件
    myScroll.on("slideDown", function() {
        if (this.y > distance) {
            index = index + 1;
            if (callback) {
                callback(index);
                upIcon.removeClass("reverse_icon")
            }
        }
    });
    // 上拉滑动事件
    myScroll.on("slideUp", function() {        
        if (this.maxScrollY - this.y > distance) {
            if (hasdata) {
                index = index + 1;
            }
            if (callback) {
                callback(index);
                upIcon.removeClass("reverse_icon");
            }
        }
    }); 
})

