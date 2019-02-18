//商品详情
$(document).ready(function() {
    var sid = "";
    // 规格值标签切换
    $(".specsSelect li span").click(function() {
        var select = false, con = 0;
        var sc = $(this).attr("class");
        var sids = "", liname = "";
        if (sc != "spanbc") {//同一规格类型操作
            $(this).parent().find("span").each(function() {
                $(this).removeClass("spanbc");
            })
            $(this).addClass("spanbc");
            sids = $(this).attr("data");
            liname = $(this).parent().attr("name");
        }

        if (sids != "" && !isNaN(sids)) {
            $(".specsSelect li").each(function() {//不同规格类型操作
                if (liname != $(this).attr("name")) {
                    con = 0;
                     $(this).find('span').each(function() {$(this).removeClass('spanbc');});
                    $(this).find('span').each(function() {
                        if (con == 0) {
                            var thissid = $(this).attr('data');
                            if (sids.indexOf(";") > 0) {
                                if (thissid.indexOf(";") > 0) {
                                    var sidss = sids.split(';');
                                    var thissids = thissid.split(';');
                                    for (var si in sidss) {
                                        for (var s in thissids) {
                                            if (sidss[si] == thissids[s]) {
                                                select = true;
                                                sid = thissids[s]; con++;
                                            }
                                        }
                                    }
                                } else {
                                    var sidss = sids.split(';');
                                    for (var si in sidss) {
                                        if (sidss[si] == thissid) {
                                            select = true;
                                            sid = thissid; ++con;
                                        }
                                    }
                                }
                            } else {
                                if (thissid.indexOf(";") > 0) {
                                    var thissids = thissid.split(';');
                                    for (var s in thissids) {
                                        if (sids == thissids[s]) {
                                            select = true;
                                            sid = thissids[s]; ++con;
                                        }
                                    }
                                } else {
                                    if (sids == thissid) {
                                        select = true;
                                        sid = sids; ++con;
                                    }
                                }
                            }
                            if (select) {                                
                                if ($(this).attr("class") != "spanbc") {
                                    $(this).addClass('spanbc');
                                }
                            } else {
                                if ($(this).attr("class") == "spanbc") {
                                    $(this).removeClass('spanbc');
                                }
                            }
                        }
                    });
                }
            })
            GetSkuId(sid, trunimgs);            
             $("#h_skuid").val(sid);
        }
    })
    
})

// 根据规格值id获取商品
function GetSkuId(skuid, callback) {
    $.ajax({
        url : "/api/wap/products/getSkuByID",
        type : "Post",
        data : {
            "ch" : 3,
            "skuid" : skuid
        },
        dataType : "json",
        success : function(res) {
            if (res.code == 0) {
                var data = res.data;
                if (data != undefined && data != null) {
                    $("#pordct_H").html(data.name);
                    $("#pordct_Hp").html(data.subtitle);
                    var protype=$("#protype").val();
                    if(protype==0){
                      $("#appprice").html(data.price.toFixed(2));   
                    }                    
                    $("#askuinfo").attr("href", "/wap/products/showProInfo?skuid=" + skuid + "&spuid=" + data.spuId);
                    if(!isNaN(parseInt(data.stock))){
                        if(parseInt(data.stock)<=0){
                            $("#addtocart").addClass('disabled');
                        }else{
                            $("#addtocart").removeClass('disabled');
                        }
                        $("#h_stock").val(data.stock);
                    }
                }
            }
        },
        error:function(e){
            
        }
    });
    $.ajax({
        url : "/api/wap/products/getSkuImgsByID",
        type : "Post",
        data : {
            "ch" : 3,
            "skuid" : skuid
        },
        dataType : "json",
        success : function(res) {
            if (res.code == 0) {
                var data = res.data;
                if (data != undefined && data != null) {
                    var listdata = {
                        list : data
                    }
                    var html = template('bannerimgs', listdata);
                    $(".pordct_banner").html(html);
                    if (callback) {
                        callback();
                    }

                }
            }
        },
        error:function(e){
            
        }
    });
}

function trunimgs() {
    ////banner/////
    Yeffect.mobileBanner(".pordct_banner", ".pordct_banner li", ".pordct_banner p i", "", "", "current", 5000, 500);
    var pordct_banner = $(".pordct_banner");
    Yeffect.resizeHtW(pordct_banner, pordct_banner, 640, 600);
    ///热销产品///
    Yeffect.click_hxk(".pordct_cptab nav a", ".pordct_cptab ul", "current");
    Yeffect.resizeHtWstr(".pordct_cptab ul li:visible", ".pordct_cptab ul li img", 209, 159);
}
function showTime() {
    var dt1 = new Date($("#endtime").val());
    //获取页面隐藏输入框的截至时间
    var nowdt = new Date($("#servicetime").val());
    //获取服务器当前时间
    //var nowdt = new Date(); //获取当前时间（这个是客户端的时间）

    timer(dt1.getTime() - nowdt.getTime());   
}
//intdiff 为毫秒
function timer(intDiff){
    intDiff=intDiff/1000;
    window.setInterval(function(){
    var day=0,
        hour=0,
        minute=0,
        second=0;//时间默认值        
    if(intDiff > 0){        
        day = Math.floor(intDiff / (60 * 60 * 24));
        hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
        minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
        second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
    }
    if (minute <= 9) minute = '0' + minute;
    if (second <= 9) second = '0' + second;
    if (intDiff > 0){//将剩余时间显示在页面上
        $("#lasttime").html("剩余" + day + "天" + hour + "小时" + minute + "分" + second + "秒");
    }else{
        $("#lasttime").html("活动已结束");
        $("#sellnow").addClass("disabeld");
        $("#BuyNow").addClass("disabeld");
        }
        
    intDiff--;
    }, 1000);
} 
$(function(){
        //秒杀倒计时
    if ($("#protype").val() == "1" || $("#protype").val() == "2") {
    showTime();
    }
    
})
