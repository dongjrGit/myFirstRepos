var sid = "";
//商品详情页
var Detail = {
    bind : function() {//规格点击绑定
        $(".aconmk").each(function() {
            $(this).bind("click", Detail.selectSKU);
        })
    },
    //规格
    selectSKU : function() {//选取规格       
        var sc = $(this).attr("class");
        var sids = "", liname = "";
        if (sc != "aconmk selected") {//同一规格类型操作
            $(this).parent().find("div").each(function() {
                $(this).removeClass("selected");
            })
            $(this).addClass("selected");
            sids = $(this).find("i").attr("data");
            liname = $(this).parent().attr("name");
        }
        var tempid="0";//两个规格确定skuid  
        if (sids != "" ) {//&& !isNaN(sids)
            $(".value_div").each(function() {//不同规格类型操作
                if (liname != $(this).attr("name")) {                                       
                  var tempsele=false; 
                   $(this).find('div').each(function() {
                       if ($(this).attr("class") == "aconmk selected") {//原规格已被选中 
                            var thissid = $(this).find("i").attr('data');
                            if (sids.indexOf(";") > 0) {
                                if (thissid.indexOf(";") > 0) {
                                    var sidss = sids.split(';');
                                    var thissids = thissid.split(';');
                                    for (var si = 0; si < sidss.length; si++) {
                                        if(!tempsele){
	                                        for (var s = 0; s < thissids.length; s++) {
	                                           if(parseInt(tempid)>0){
	                                           	    if(parseInt(tempid)==thissids[s]){
	                                            		tempsele = true;
		                                                sid = thissids[s];		                                                
		                                                break;
		                                            }
	                                            }else{
	                                            	if (sidss[si] == thissids[s]) {
	                                            		tempsele = true;
		                                                sid = thissids[s];		                                                
		                                                break;
		                                               }
	                                            }
	                                        }
                                        }
                                    }
                                } else {
                                    var sidss = sids.split(';');
                                    for (var si =0; si < sidss.length; si++) {
                                           if(parseInt(tempid)>0){
	                                    		if (parseInt(tempid)==thissid){
		                                           	tempsele = true;
		                                            sid = thissid;		                                           
		                                            break;
	                                           }
                                    	   }
                                           else{
                                           	if (sidss[si] == thissid) {
	                                           	tempsele = true;
	                                            sid = thissid;	                                           
	                                            break;
                                           }
                                         }
                                    }
                                }
                            } else {
                                if (thissid.indexOf(";") > 0) {
                                    var sidlist = thissid.split(';');
                                    for (var s=0;s<sidlist.length;s++) {
                                        if (sids == sidlist[s]) {
                                           if (parseInt(tempid)>0){
                                        		if(parseInt(tempid)==sidlist[s]){
                                        			tempsele = true;
		                                            sid = sidlist[s];		                                            
		                                            break;
                                        		}
                                           }else{
                                           	    tempsele = true;
	                                            sid = sidlist[s];	                                            
	                                            break;
                                           }
                                        }
                                    }
                                } else {
                                    if (sids == thissid) {
                                    	if (parseInt(tempid)>0){
                                    		if(parseInt(tempid)==thissid){
                                    			tempsele = true;
	                                            sid = sids;	                         
                                    		}
	                                    }else{
	                                    	tempsele = true;
	                                        sid = sids;	                  
	                                    }
                                    }
                                }        
                          }
                         } 
                       if(!tempsele){
                	       $(this).removeClass('selected');
                	   }
                    });
                    if(!tempsele){                    	
                    	var tcon=0;
                    	var isselect=false;
                    	$(this).find('div').each(function() {
	                       if (tcon == 0) {
	                        var thissid = $(this).find("i").attr('data');
                            if (sids.indexOf(";") > 0) {
                                if (thissid.indexOf(";") > 0) {
                                    var sidss = sids.split(';');
                                    var thissids = thissid.split(';');
                                    for (var si = 0; si < sidss.length; si++) {
                                        if(!isselect){
	                                        for (var s = 0; s < thissids.length; s++) {
	                                           if(parseInt(tempid)>0){
	                                           	    if(parseInt(tempid)==thissids[s]){
	                                            		isselect = true;
		                                                sid = thissids[s];	
		                                                tcon++;	                                                
		                                                break;
		                                            }
	                                            }else{
	                                            	if (sidss[si] == thissids[s]) {
	                                            		isselect = true;
		                                                sid = thissids[s];
		                                                tcon++;		                                                
		                                                break;
		                                               }
	                                            }
	                                        }
                                        }
                                    }
                                } else {
                                    var sidss = sids.split(';');
                                    for (var si =0; si < sidss.length; si++) {
                                           if(parseInt(tempid)>0){
	                                    		if (parseInt(tempid)==thissid){
		                                           	isselect = true;
		                                            sid = thissid;	
		                                            tcon++;	                                           
		                                            break;
	                                           }
                                    	   }
                                           else{
                                           	if (sidss[si] == thissid) {
	                                           	isselect = true;
	                                            sid = thissid;	
	                                            tcon++;                                           
	                                            break;
                                           }
                                         }
                                    }
                                }
                            } else {
                                if (thissid.indexOf(";") > 0) {
                                    var sidlist = thissid.split(';');
                                    for (var s=0;s<sidlist.length;s++) {
                                        if (sids == sidlist[s]) {
                                           if (parseInt(tempid)>0){
                                        		if(parseInt(tempid)==parseInt(sidlist[s])){
                                        			isselect = true;
		                                            sid = sidlist[s];
		                                            tcon++;		                                            
		                                            break;
                                        		}
                                           }else{
                                           	    isselect = true;
	                                            sid = sidlist[s];	   
	                                            tcon++;                                         
	                                            break;
                                           }
                                        }
                                    }
                                } else {
                                    if (sids == thissid) {
                                    	if (parseInt(tempid)>0){
                                    		if(parseInt(tempid)==thissid){
                                    			isselect = true;
	                                            sid = sids;	  
	                                            tcon++; 	                                                                                    
                                    		}
	                                    }else{
	                                    	isselect = true;
	                                        sid = sids;	    
	                                        tcon++;	                                                                         
	                                    }
                                    }
                                }        
                              }
                              if(isselect){
                              	$(this).addClass('selected');
                              }
	                       }		                        
	                    });
                    }else
                    {
                    	tempid=sid;                    	
                    }
            
                }
             })
			if($(".value_div").length==1){
				sid=sids;
			}
            $("#h_skuid").val(sid);
            GetSKU(sid);
           
            GetSpecs(sid, 2);
            GetSpecs(sid, 3);
            GetgroupSKU(sid);
        }
    },
    //添加购物车
    addToCart : function() {

        var count = $(".m02").val();
        //购买数量验证
        count = parseInt(count);
        if (isNaN(count)) {
            count = 1;
            $(".m02").val(1);
        }
        var shopid = $("#h_shopid").val();
        var skuid = $("#h_skuid").val();
        //获取秒杀活动ID
        var spikeid = "0";
        if ($("#issg").val() == "1" || $("#issg").val() == "2") {
            spikeid = $("#spike_id").val();
        }
        var type = $("#h_protype").val();
        // 添加 callback:回调函数 shopid:店铺ID goodsid:组合商品ID或商品SKUID goodscount:商品数量
        // ispack:商品类型（0普通商品 1组合商品 2秒杀 3闪购） spuid:商品SPUID(组合商品时为包含的商品种类数量)
        // spikeid:闪购或秒杀ID
        if(type==1){
            type=2;
        }else{if(type==2){
            type=3;
        }}
        cart.add(cart.addcallback, shopid, skuid, count, type, spikeid);

    },
    //直接购买
    addBuyGoods : function(callback, shopid, goodsid, goodscount, type, spikeid) {
        if (WebLogin.isLogin()) {
            cart.buynow(callback, shopid, goodsid, goodscount, type, spikeid);
        } else {
            showlogindiv();
        }

    },
    //添加商品关注
    addConcernt : function(skuid, type) {
        $.ajax({
            type : "post",
            url : "/pc/products/collectSpu",
            dataType : "json",
            data : {
                "skuid" : skuid,
                "ch" : 0
            },
            success : function(rsl) {
                if (rsl.code == 0) {
                    alert(rsl.desc);
                } else {
                    alert(rsl.desc);
                }
            },
            error : function(e) {
                //alert(e.statusText);
            }
        });
    }
}
Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}
$(document).ready(function(e) {

    Detail.bind();

    //购买数量加减
    $(".m01").click(function() {
        var num = $(".m02").val();
        num = parseInt(num);
        if (isNaN(num)) {
            num = 1;
        }
        var text = $(this).text();
        if (text == "+") {
            num++;
        } else {
            if (num >= 2) {
                num--;
            }
        }
        $(".m02").val(num);
    });
    //购买数量验证
    $(".m02").blur(function() {
        var tvalue = $(this).val();
        tvalue = parseInt(tvalue);
        if (isNaN(tvalue)) {
            tvalue = 1;
            $(".m02").val(1);
        }
    })
    var kucun=$("#h_kucun").val();
    //加入购物车
    $("#sellnow").click(function() {
        var bclass=$("#sellnow").attr("class");        
        if(parseInt(kucun)>0&&bclass.length<=7){
            var num = $(".m02").val();
            if(parseInt(kucun)>=parseInt(num)){
                Detail.addToCart();
            }else{
                alert("库存不足");
            }
           
        }  
    });

    //立刻购买
    $("#BuyNow").click(function() {
        var bclass=$("#BuyNow").attr("class");
        if(parseInt(kucun)>0&&bclass.length<=7){
        var count = $(".m02").val();
        //购买数量验证
        count = parseInt(count);
        if (isNaN(count)) {
            count = 1;
            $(".m02").val(1);
        }
        var shopid = $("#h_shopid").val();
        var skuid = $("#h_skuid").val();
        var spikeid = "0";
        if ($("#issg").val() == "1" || $("#issg").val() == "2") {
            spikeid = $("#spike_id").val();
        }
        var type = $("#h_protype").val();
        if(type==1){
            type=2;
        }else{if(type==2){
            type=3;
        }}
        //立即购买接口调用 回调函数 店铺ID，库存商品ID，购买数量，商品类型 0-普通商品 2-秒杀 3-闪购，活动ID
        if(parseInt(kucun)>=parseInt(count)){
        Detail.addBuyGoods(cart.buynowcallback, shopid, skuid, count, type, spikeid);
        }else{
            alert("库存不足");
        }
        }
    });
    //点击关注
    $("#addconcernt").click(function() {
        var skuid = $("#nowPrice").attr("data_id");

        if (WebLogin.isLogin()) {
            Detail.addConcernt(skuid, 0);
        } else {
            showlogindiv();
        }
    });
    //组合套餐加入购物车
    $("#package_addcar").click(function() {
    	if($(this).attr("disable",true)){
    		var packageID = 0;
            $("#pack_count a").each(function() {
                if ($(this).attr("class") == "red") {
                    packageID = $(this).attr("data");
                }
            })
            var shopid = $("#h_shopid").val();
            var spucon = "0";//组合商品数量
            $(".l_tjdpcenter").find("ul").each(function(){
                if($(this).attr("class")=="fix"){
                   spucon= $(this).find("li").length;
                }
            })        
            cart.add(cart.addcallback, shopid, packageID, 1, 1, spucon);
    	}
    });

    //商品组合套餐点击
    $("#pack_count").on("click","a", function() {
        $("#pack_count").find("a").removeClass("red");
        $(this).addClass("red");
        var packid = "", packprice = "";
        packid = $(this).attr("data");//套餐id        
        packprice = $(this).attr("dpri");//套餐价格
        packcon=$(this).attr("pkcon");//套餐数量
        if(!isNaN(parseInt(packcon))||parseInt(packcon)==0){
            $("#package_addcar").unbind("click");
            $("#package_addcar").addClass('disabeld',true);
        }else{
            $("#package_addcar").bind("click");
            $("#package_addcar").removeClass('disabeld');
        }
        $("div[name=package_group]").each(function() {
            $("div[name=package_group]").addclass("none");           
            var pid = $(this).attr("data");
            if (pid == packid) {
                 $(this).removeClass('none');                
            }
        });
        $("#packprice").text("￥ " + parseFloat(packprice).toFixed(2));

    });

    //Consult.bind();

    //添加浏览记录
    // BrowseHistory.add();

    //GetShopInfo();//获取店铺信息
    //GetSpuCouponts();

    /*
    kn: "",  //关键字 1
    ot: "",  //页面或按钮标识 1
    step: 0, //步骤 1
    type: 0, //类型，0页面访问  1按钮点击 1
    */
    // setTimeout(function () {
    // var kn = "load/buy";
    // var isacc = infoc.getCookie("_isAcced");
    // if (isacc == undefined || isacc == null || isacc == "") {
    // kn += "/uv";
    // infoc.setCookie("_isAcced", 1, 24 - new Date().getHours());
    // }
    // infoc.add(kn, "/Web/Goods/pro_detail", 31, 0);
    // }, 1);
    //页面离开事件
    // window.onbeforeunload = function (event) {
    // infoc.add("load", "/Web/Goods/pro_detail", 32, 0);
    // }

    //秒杀倒计时
    if ($("#issg").val() == "1" || $("#issg").val() == "2") {
    showTime();
    }

    $.fn.smartFloat = function() {
        var position = function(element) {
            var top = element.position().top, pos = element.css("position");
            $(window).scroll(function() {
                var scrolls = $(this).scrollTop();
                if (scrolls > top) {
                    if (window.XMLHttpRequest) {
                        element.css({
                            position : "fixed",
                            top : 0
                        });
                    } else {
                        element.css({
                            top : scrolls
                        });
                    }
                } else {
                    element.css({
                        position : pos,
                        top : top
                    });
                }
            });
        };
        return $(this).each(function() {
            position($(this));
        });
    };
    $("#fixed").smartFloat();
});

function MethodForShop(allcount, goodcount) {
    var all = parseInt(allcount);
    //评论总数
    var good = parseInt(goodcount);
    //好评总数

    var goodp = 100.00;
    if (all > 0) {
        goodp = parseFloat((good / all) * 100).toFixed(2);
        //好评百分比
    }
    return goodp;
}

//店铺收藏
function shopCollect(sid) {

    if (WebLogin.isLogin()) {

        $.ajax({
            type : "post",
            url : "/pc/products/collectShop",
            dataType : "json",
            data : {
                "shopId" : sid,
                "ch" : 1
            },
            success : function(rsl) {
                if (rsl.code == 0) {
                    alert(rsl.desc);
                } else {
                    alert(rsl.desc);
                }
            },
            error : function(e) {
            }
        });
    } else {
        showlogindiv();
    }
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
//根据skuID获取Sku数据
function GetSKU(skuid) {
    $.ajax({
        url : "/pc/products/getskubyid",
        type : "Post",
        data : {
            "skuid" : skuid
        },
        dataType : "json",
        success : function(res) {
            if (res.code == 0) {
                if ($("#issg").val() == "0") {
                    $("#oldPrice").text("￥ " + res.data.oldprice.toFixed(2));//原价
                     var type = $("#h_protype").val();
                    if(type==0){
                    $("#nowPrice").text("￥ " + res.data.price.toFixed(2));//售价
                    }
                    $("#kucun").text("件（库存" + res.data.stock + " 件）");
                    $("#h_kucun").val(res.data.stock);
                    $(".goodname").text(res.data.name);
                    $("#h_skuimg").val(res.data.imgurl);
                }
                
                //$("#g_m_price").text("￥ " + res.Data.Price.toFixed(2));//组合显示售价
                //$("#kucun").text("件（库存" +res.Data.Stock + " 件）");
                // $("#nowPrice").attr("data_id", res.Data.ID);
                //$("#g_m_price").attr("data_id", res.Data.ID);
                // $("#g_allPrice").text("￥ " + res.Data.Price.toFixed(2));
                //组合显示默认总售价
                // $("#g_allPrice").attr("data", res.Data.Price);
                // $(".goodname").text(res.Data.Name);
                // var shopid = $("#s_id").attr("data_id");
                // var spuid = $("#s_act").attr("data_pid");
                // setTimeout(function() {
                // infoc.on();
                // infoc(infoc.params = {
                // tn : 1,
                // shopid : shopid,
                // spuid : spuid,
                // skuid : res.Data.ID
                // })
                // infoc.off();
                // }, 1);
              GetSKUImgs(skuid);
            }
        }
    })
}

//根据skuID获取Sku图片数据
function GetSKUImgs(skuid) {
    $.ajax({
        url : "/pc/products/getimgsbyskuid",
        type : "post",
        data : {
            skuid : skuid
        },
        dataType : "json",
        success : function(res) {
            if (res.code == 0) {

                var imgs = res.data;
                var jqzoom = "";
                var li = "";
                if (imgs != "" && imgs != undefined) {
                    

                    for (var i = 0; i < imgs.length; i++) {
                        li += "<li><img alt='商品图片' bimg='" + imgs[i].imgurl + "' src='" + imgs[i].imgurl + "' onmousemove='preview(this);'></li>";
                    }
                    jqzoom = "<img jqimg='" + imgs[0].imgurl + "' src='" + imgs[0].imgurl + "' style='width:332px;height:402px' />";
                    $("#skuimgs").html(li);
                    $(".jqzoom").html(jqzoom);
                    $("#g_m_img").attr("src", imgs[0].imgurl);
                }else{
                	var img=$("#h_skuimg").val();
                	if(img!=""&&img!=null){
                		li = "<li><img alt='商品图片' bimg='" + img + "' src='" + img + "' onmousemove='preview(this);'></li>";
                	    jqzoom = "<img jqimg='" + img + "' src='" + img + "' style='width:332px;height:402px' />";
                	    $("#skuimgs").html(li);
                        $(".jqzoom").html(jqzoom);
                        $("#g_m_img").attr("src", img);
                	}
                }
            }

        }
    })
}

//根据skuID获取商品组合数据
function GetgroupSKU(skuid) {
    $.ajax({
        url : "/pc/products/selectPackage",
        type : "Post",
        data : {
            "skuid" : skuid
        },
        dataType : "json",
        success : function(res) {
            if (res.code == 0) {
                //var listdata = {
                //    list: res.Data
                //}
                //var html = template('g_skulist', listdata);

                //$("#groupskus").html(html);
                var data = res.data;
                if (data.length > 0) {
                    $("#group_tap").removeClass("none");
                    var tap = "", group = "";
                    for (var i = 0; i < data.length; i++) {
                        if (i == 0) {
                            tap += " <a href='javascript:;' data='" + data[0].packageID+"' dpri='" + data[0].skuPackPrice + "' pkcon='" + data[0].Count + "' class='red'>优惠套餐1</a><span></span>";
                            group+="<ul class='fix' name='package_group' data='" + data[0].packageID+"'>";
                            for (var j = 0; j < data[0].skus.length; j++) {
                                if (skuid != data[0].skus[j].skuID) {
                                    if (j == data[i].skus.length - 1) {
                                        group += "<li tag='" + data[0].packageID + "'><div class='l_tjdpmk'><a href='javascript:;'><img src='" + data[0].skus[j].imgUrl + "' width='131' height='131' alt='" + data[0].skus[j].skuName + "'></a>" + "<p><a href='javascript:;'>" + data[0].skus[j].skuName + "</a></p>" + "</div></li>";
                                        //去掉后面加号
                                    } else {
                                        group += "<li tag='" + data[0].packageID + "'><div class='l_tjdpmk'><a href='javascript:;'><img src='" + data[0].skus[j].imgUrl + "' width='131' height='131' alt='" + data[0].skus[j].skuName + "'></a>" + "<p><a href='javascript:;'>" + data[0].skus[j].skuName + "</a></p>" + "</div><div class='l_xjh'><i></i></div></li>";
                                    }
                                }
                            }
                            group=+"</ul>";
                        } else {
                            tap += " <a href='javascript:;' data='" + data[i].packageID + "' dpri='" + data[i].SkuPackPrice + "' pkcon='" + data[i].Count + "'>优惠套餐" + (i + 1) + "</a><span></span>";
                             group+="<ul class='fix none' name='package_group' data='" + data[i].PackageID+"'>";
                            for (var j = 0; j < data[i].skus.length; j++) {
                                if (skuid != data[i].skus[j].SkuID) {
                                    if (j == data[i].skus.length - 1) {
                                        group += "<li tag='" + data[i].packageID + "' style='display:none'><div class='l_tjdpmk'><a href='javascript:;'><img src='" + data[i].skus[j].imgUrl + "' width='131' height='131' alt='" + data[i].skus[j].skuName + "'></a>" + "<p><a href='javascript:;'>" + data[i].skus[j].skuName + "</a></p>" + "</div></li>";
                                        //去掉后面加号

                                    } else {
                                        group += "<li tag='" + data[i].packageID + "' style='display:none'><div class='l_tjdpmk'><a href='javascript:;'><img src='" + data[i].skus[j].imgUrl + "' width='131' height='131' alt='" + data[i].skus[j].skuName + "'></a>" + "<p><a href='javascript:;'>" + data[i].skus[j].skuName + "</a></p>" + "</div><div class='l_xjh'><i></i></div></li>";
                                    }
                                }
                            }
                            group=+"</ul>";
                        }
                    }

                    tap = tap.substr(0, tap.length - 13);
                    $("#pack_count").html(tap);
                    $("#packprice").text("￥ " + parseFloat(data[0].skuPackPrice).toFixed(2));
                    $(".package_group").html(group);
                    //重新定位浮动条位置（index.js）
                    $("#fixed").smartFloat();
                } else {
                    $("#group_tap").addClass("none");
                }
            }
        }
    })
}

//根据spuID获取spu满减优惠信息
function GetSpuCouponts(sid) {
    var spuid = $("#s_act").attr("data_pid");
    //spuid
    $.ajax({
        url : "/Activity/W_GetBySpu",
        type : "Post",
        data : {
            spuid : spuid
        },
        dataType : "json",
        success : function(res) {

            if (res.Code == 0) {

                var acts = res.Data;
                if (acts != "" && acts != undefined && acts.length > 0) {
                    var span = " <span class='l_01'>店铺活动</span><span class='kqs'>";
                    var cont = "", acid = "";

                    for (var i = 0; i < acts.length; i++) {
                        cont += acts[i].ActName + ";";
                        acid += acts[i].ID + ",";
                    }
                    $("#s_act").html(span + cont + "</span>");
                    $("#s_act").attr("data_acid", acid);
                }
            }

        }
    })
}

