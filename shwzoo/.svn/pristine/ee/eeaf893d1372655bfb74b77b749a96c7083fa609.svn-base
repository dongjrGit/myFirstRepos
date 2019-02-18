//商品详情
$(document).ready(function() {

    Cart.getgoodscount(Cart.getcountcallback);
    // 添加/取消关注
    $("#spucon").click(function() {
        if (login.isLogin()) {
            var cclass = $("#spucon").attr("class");
            if (cclass == "foot_focus") {
                addspuconcern($("#h_spuid").val());
            } else {
                cancelspuconcern($("#h_spuid").val());
            }
        } else {
            login.open();
        }
    });
    // 添加购物车按钮
    $("#addtocart").click(function() {
    	if($(this).attr("class")!="footshopcart_r disabled"){
    	var type = $("#h_type").val();
        var stock = $("#h_stock").val();
        if (!stock) {
            stock = GetCookie("prostock");
        }
       
        if (parseInt(stock) > 0) {
            var ispk = $("#h_ispk").val();
            var goodsid = $("#h_skuid").val();
            var gcount = $("#gcount").val();
            var shopid = $("#h_shopid").val();
            var skiid = $("#h_skiid").val();
            var spuid = $("#h_spuid").val();  
            if(ispk){
                var pkid=$("input:radio:checked").val();
                var cont=$("input:radio:checked").parent().parent().find("ul").find("li").length;
                Cart.add(shopid, pkid, 0, 1, 1, 0, 1, Cart.addcallback);  
            }else{
            //shopid, goodsid, goodscount, type, spikeid, ischecked, callback
          
            
            if(!skiid){
                skiid=GetCookie("skiid");
            }
            if(!type){
                type=GetCookie("type");
            } 
            if(!gcount){
                gcount=GetCookie("gcount");
            }
            if(!shopid){
                shopid=GetCookie("shopid");
            }   
            if (!skiid) {
                skiid = 0;
            }
            if (!type) {
                type = 0;
            } else {
                if (type == 1) {
                    type = 2;
                } else {
                    if (type == 2) {
                        type = 3;
                    }
                }
            }
            Cart.add(shopid, goodsid, spuid, gcount, type, skiid, 1, Cart.addcallback);
        }}
        }
    });
    setparam();
    function setparam(){
        var skiid = $("#h_skiid").val();
        var type = $("#h_type").val();
        var gcount = $("#gcount").val();
        var shopid = $("#h_shopid").val();
        if(skiid){
            SetCookie("skiid",skiid);
        }
        if(type){
            SetCookie("type",type);
        }  
        if(gcount){
            SetCookie("gcount",gcount);
        }
        if(shopid){
            SetCookie("shopid",shopid);
        }       
    }
})
// 添加关注
function addspuconcern(spuid) {
    $.ajax({
        url : "/api/wap/concern/addspuconcern",
        type : "Post",
        data : {
            "spuId" : spuid,
            "ch" : 3
        },
        dataType : "json",
        success : function(res) {
            if (res.code == 0) {
                $("#spucon").addClass('active');
                $("#spuconfont").html("已关注");
            }
        },
        error : function(res) {

        }
    });
}

// 取消关注
function cancelspuconcern(spuid) {
    $.ajax({
        url : "/api/wap/concern/cancelspuconcern",
        type : "Post",
        data : {
            "spuId" : spuid,
            "ch" : 3
        },
        dataType : "json",
        success : function(res) {
            if (res.code == 0) {
                $("#spucon").removeClass('active');
                $("#spuconfont").html("关注");
            }
        }
    });
}

// 绝对定位占据原来的位置
function fixed(obj) {
    var allHeight = $(obj).outerHeight();
    $(obj).after('<div style="height:' + allHeight + 'px;">' + '&nbsp;</div>');
}

