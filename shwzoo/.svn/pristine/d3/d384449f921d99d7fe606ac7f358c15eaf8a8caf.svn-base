 var beans=0;//结算用积分
//订单结算页
$(document).ready(function () {

    //获取发票
    GetInvoice();
    //发票修改点击
    $("#editshow").click(function () {
        $("#invoiceDiv").attr("style", "display:block");
        $("#hType_hide").val(0);//默认发票抬头是个人
    })
    //发票修改保存点击
    $("#editBtn").on("click", function () {
        EditInvoice();
    })
    //发票关闭
    $("#invoiceClose").on("click", function () {
        $("#invoiceDiv").attr("style", "display:none");
    })
    //发票抬头个人点击事件
    $("#hType").click(function () {
        $(this).toggleClass("active");
        if ($(this).attr("class") == "l_fpttmk active") {
            $("#hType_hide").val(0);
            $("div[name=comTitle]").attr("style", "display:none");
            $("#resMessge").html("");
            $("#iTitle").attr("value", "");
            $("#iTitle_hide").attr("value", "");
        } else {
            $("#hType_hide").val(1);
            $("div[name=comTitle]").attr("style", "display:block");
        }
    });

    //输入积分失去焦点事件
    $("#usepoints").blur(function() {
        var points=parseInt($("#usepoints").val());//用户输入积分
        var canUsePoints=parseInt($("#h_usepoints").val());//可使用积分
        if(canUsePoints>0){
            var paymoney=$("#h_pay").val();
            var frightmoney= parseFloat($("#total_freight").html());//总运费;
            paymoney=parseFloat(paymoney)+parseFloat(frightmoney);
            if(parseInt(points)>=100){            
                if(parseInt(points)>parseInt(canUsePoints)){
                    $("#usepoints").val(canUsePoints);
                    points=parseInt($("#usepoints").val());                
                }                
                var pointmoney=parseFloat(points/100);
                $("#pointsmoney").html("￥"+pointmoney);
                $("#span_paymoney").html("￥"+(paymoney-pointmoney));
                beans=points;
            }else{
                $("#pointsmoney").html("￥0.00");
                $("#span_paymoney").html("￥"+paymoney);
                $("#usepoints").val(0);
            }
        }
    });
    //点击新增公司发票
    $(".addComInv").click(function () {
        $("#hType").removeClass("active");
        $("#hType_hide").val(1);
        $("div[name=comTitle]").attr("style", "display:block");
    });

    //公司发票抬头保存
    $("#saveComTitle").click(function () {
        var comTitle = $("#iTitle").val();
        if (comTitle != "" && comTitle != "新增单位发票抬头") {
            $("#resMessge").html("保存成功！")
            $("#iTitle_hide").attr("value", comTitle);
        } else {
            $("#resMessge").html("请填入公司名称！");
            $("#iTitle_hide").attr("value", "");
        }
    });
    //是否需要发票
    $(document).on("click","input[name=isNeed]", function () {
        var isneed = $(this).val();
        if (isneed == 0) {
            $("#isneedinvoice").attr("style", "display:none");
            $("#inType_hide").val(0);
        }
        else {
            $("#isneedinvoice").attr("style", "display:block");
            $("#inType_hide").val(1);
        }
    });

    //发票信息修改显示
    $(".l_xzdwfp").click(function () {
        $(".l_fpttnone").css("display", "block")
    });
    //发票信息修改关闭
    $(".invoice").click(function () {
        $(".l_xgfpnr").css("display", "none")
    });
    //发票类型选择
    $("#inType").find("li").click(function () {
        var type = $(this).attr("data_tag");
        if (type != 3) {
            $("#inType").find("li").removeClass("active");
            $(this).addClass("active");
            $("#inType_hide").val(type); //改为点击保存时修改
        }
        if (type == 1 || type == 2) {
            $("div[name=fptt]").attr("style", "display:block");//发票抬头
            $("div[name=fpnr]").attr("style", "display:block");//发票内容
            $("div[name=fpbc]").attr("style", "display:block");//发票保存 
            $("div[name=zhsfp]").attr("style", "display:none");//增值税发票隐藏
            if (type == 1) {
                $("div[name=dzfpts]").attr("style", "display:none");//电子发票 说明隐藏
                $("ul[name=dzfpdhyx]").attr("style", "display:none");//电子发票 电话 邮箱隐藏
            } else {
                $("div[name=dzfpts]").attr("style", "display:block");//电子发票 说明显示
                $("ul[name=dzfpdhyx]").attr("style", "display:block");//电子发票 电话 邮箱显示
            }
        }
        if (type == 3) {
           /* $("div[name=fptt]").attr("style", "display:none");//发票抬头
            $("div[name=fpnr]").attr("style", "display:none");//发票内容
            $("div[name=dzfpts]").attr("style", "display:none");//电子发票 说明隐藏
            $("ul[name=dzfpdhyx]").attr("style", "display:none");//电子发票 电话 邮箱隐藏
            $("div[name=fpbc]").attr("style", "display:none");//发票保存 
            $("div[name=zhsfp]").attr("style", "display:block");//增值税发票隐藏
*/
        }
    });
    //发票内容选择
    $("#iContent").find("li").click(function () {
        $("#iContent").find("li").removeClass("active");
        $(this).addClass("active");
        $("#iContent_hide").val($(this).attr("data_tag"));
    });
    //-------------------------------以上为发票相关-----------------------------------------------------

    //优惠券/积分展开点击事件
    $("body").on("click",".span_expand", function () {
        if ($(this).text() == "+") {
            $(this).text("-");
            $(this).parent().parent().find("div[name=coupon_points]").attr("style", "display:block");
        } else {
            if ($(this).text() == "-") {
                $(this).text("+");
                $(this).parent().parent().find("div[name=coupon_points]").attr("style", "display:none");
            }
        }
    });
    //是否使用优惠券
    $(document).on("click","input[name=coupon_cb]", function () {
        var $coup_check = $(this);
        var c_all_price = $coup_check.val().split('_');//获取点击优惠券的满额值和面值
        var c_price = 0;
        if (c_all_price.length == 1) {
            c_price = c_all_price[0];
        } else {
            c_price = c_all_price[1];
        }
        var num = $("#coupon_num").text();
        var d_price = $("#coupon_price").text();
        var actdelmoney = parseFloat($("#span_alldelmoney").attr('actdelmoney'));//活动减免金额
        var totalprice = parseFloat($("#total_price").html());//商品总金额 未减免
        var totalfreight = parseFloat($("#total_freight").html());//总运费
        if ($coup_check.context.checked == true) {
            num++;
            $("#coupon_num").text(num);//使用优惠券数量
            $("#coupon_price").text(d_price - (-c_price));//优惠金额
        } else {
            num--;
            $("#coupon_num").text(num);//使用优惠券数量
            $("#coupon_price").text(d_price - c_price);//优惠金额        
        }
        $("#span_alldelmoney").html((parseFloat(actdelmoney) + parseFloat($("#coupon_price").html())).toFixed(2));//活动+优惠券减免金额
        var t_money = totalprice + totalfreight - parseFloat($("#span_alldelmoney").html()) > 0 ? totalprice + totalfreight - parseFloat($("#span_alldelmoney").html()) : 0;
        $("span[name=total_money]").html('￥' + t_money.toFixed(2));//应付金额

        var shopid = $coup_check.attr("data_sp");//shopid
        if ($coup_check.context.checked == true) {
            if (shopid == "null") {//通用优惠券勾选 不可使用其他优惠券
                $coup_check.parent("li").siblings("li").each(function () {
                    $(this).find("input").attr("disabled", "disabled");
                    $(this).find(".coupont_value").addClass("yscmh");
                });
            } else {//店铺优惠全勾选
                $coup_check.parent("li").siblings("li").each(function () {
                    // 不可使用通用优惠券
                    if ($(this).find("input").attr("data_sp") == "null") {
                        $(this).find("input").attr("disabled", "disabled");
                        $(this).find(".coupont_value").addClass("yscmh");
                    }
                    else {
                        //同一店铺的优惠券不可用
                        if ($(this).find("input").attr("data_sp") == shopid) {
                            $(this).find("input").attr("disabled", "disabled");
                            $(this).find(".coupont_value").addClass("yscmh");
                        }
                    }
                });
            }
        }
        else {
            if (shopid == "null") {//恢复所有优惠券
                $coup_check.parent("li").siblings("li").each(function () {
                    $(this).find("input").removeAttr("disabled");
                    $(this).find(".coupont_value").removeClass("yscmh");
                });
            } else {//恢复通用优惠券 当店铺所有优惠券未勾选时可恢复
                $coup_check.parent("li").siblings("li").each(function () {
                    var cb = false;
                    //查看其他店铺是否有勾选优惠券
                    $coup_check.parent("li").siblings("li").each(function () {
                        if ($(this).find("input[name=coupon_cb]:checked").length > 0) {
                            cb = true;
                        }
                    });
                    if ($(this).find("input").attr("data_sp") == "null") {
                        if (cb == false) {
                            $(this).find("input").removeAttr("disabled");
                            $(this).find(".coupont_value").removeClass("yscmh");
                        }
                    }
                    else {
                        //同一店铺的优惠券恢复
                        if ($(this).find("input").attr("data_sp") == shopid) {
                            $(this).find("input").removeAttr("disabled");
                            $(this).find(".coupont_value").removeClass("yscmh");
                        }
                    }
                });
            }
        }
    });

    $("#act_del_price").val(0);
    Order.bind();//订单加载 
    // loadactivity();//加载活动//放在页面最下面
    GetCoupon();//加载优惠卷

//    setTimeout(function () {
//        //页面埋点
//        var kn = "load/buy";
//        var isacc = infoc.getCookie("_isAcced");
//        if (isacc == undefined || isacc == null || isacc == "") {
//            kn += "/uv";
//            infoc.setCookie("_isAcced", 1, 24 - new Date().getHours());
//        }
//        infoc.add(kn, "/Web/Orders/order_jiesuan", 61, 0);
//    }, 1);
//    //页面离开事件
//    window.onbeforeunload = function (event) {
//        infoc.add("load", "/Web/Orders/order_jiesuan", 62, 0);
//    }
})
var Order = {
    bind: function () {
        $("#commit").bind("click", Order.addNewOrder);//订单提交按钮绑定
        //地址选择
        $("li[name='receiveAddress_list']").each(function () {
            var $div = $(this);
            $div.bind("click", Order.selectAddress);
            if ($div.attr("data_tag") == "1") {
                $div.attr("class", "active");
                Order.selectAddress(1);
            }
        });
    },
    //生成订单
    // orderParams=[格式]shopID:x,totalMoney:x,delMoney:x,freightMoney:x,couponID:x,activityid:x,isInvoice:x,remark:x,
    //  skuID:y-proCount:y-packageid:y-marketid:y-spuid:y|skuID:y-proCount:y-packageid:y-marketid:y-spuid:y

    addNewOrder: function () {
        var orderParams = "", count_coup = 0;
        var coupons = $("input[name='coupon_cb']:checked");

        var isneed = $("input[name='isNeed']:checked").first().val();

        var zyactibityid = 0, zycoupid = 0;//通用活动id 优惠券id

        var scpids="";
        $("div[name='shop_table']").each(function () {
            var $shop = $(this);
            var sid = $shop.attr("shopid");
            orderParams += "shopID:" + sid + ",";
            var totalMoney = 0;
            var totaldelmoney = 0;
            var prostr = "";
           
            $shop.find(".div_spu").each(function () {
                var $spu = $(this);
                var spuinfo = $spu.find(".hidden_spuinfo");
                totaldelmoney += parseFloat(spuinfo.attr('spudelmoney'));
                totalMoney += parseFloat(spuinfo.attr('sputotalmoney'));
                var spustr = "";
                $spu.find(".div_spusku").each(function () {
                    var $sku = $(this);
                    var skuinfo = $sku.find(".hidden_spuskuinfo");
                    spustr += "skuID:" + NulltoStr(skuinfo.attr('skuid')) + "-";
                    spustr += "proCount:" + NulltoStr(skuinfo.attr('skucount')) + "-";
                    spustr += "packageid:0-";
                    spustr += "marketid:" + NulltoStr(skuinfo.attr('spuact')) + "-";
                    spustr += "spuid:" + NulltoStr(skuinfo.attr('spuid')) + "-";
                    spustr += "spikeid:" + NulltoStr(skuinfo.attr('spikeid')) + "-";
                    spustr += "type:" + NulltoStr(skuinfo.attr('skutype')) + "|";
                    if(NulltoStr(skuinfo.attr('shopcartproid'))>0)
                    {
                    if(scpids==""){
                    	scpids=skuinfo.attr('shopcartproid');
                    }else{
                    	scpids+=","+skuinfo.attr('shopcartproid');
                    }
                    }
                    //店铺赠品信息
                    $sku.find(".div_shopskugif").each(function () {
                        var $skugif = $(this);
                        var gifinfo = $skugif.find(".hidden_shopskugifinfo");
                        spustr += "skuID:" + NulltoStr(gifinfo.attr('skuid')) + "-";
                        spustr += "proCount:" + NulltoStr(gifinfo.attr('skucount')) + "-";
                        spustr += "packageid:0-";
                        spustr += "marketid:0-";
                        spustr += "spuid:0-";
                        spustr += "spikeid:0-";
                        spustr += "type:" + NulltoStr(gifinfo.attr('skutype')) + "|";
                    })
                    //商品赠品信息
                    $sku.find(".div_spuskugif").each(function () {
                        var $skugif = $(this);
                        var gifinfo = $skugif.find(".hidden_spuskugifinfo");
                        spustr += "skuID:" + NulltoStr(gifinfo.attr('skuid')) + "-";
                        spustr += "proCount:" + NulltoStr(gifinfo.attr('skucount')) + "-";
                        spustr += "packageid:0-";
                        spustr += "marketid:0-";
                        spustr += "spuid:0-";
                        spustr += "spikeid:0-";
                        spustr += "type:" + NulltoStr(gifinfo.attr('skutype')) + "|";
                    })
                })
                prostr += spustr;
            })

            $shop.find(".div_pack").each(function () {
                var $pack = $(this);
                if ($pack.attr("data-count") <= 0) {
                    return;
                }
                var packinfo = $pack.find(".hidden_packinfo");
                if (packinfo == null)
                    return;
                totalMoney += parseFloat(packinfo.attr('packtotalmoney'));
                if(NulltoStr(packinfo.attr('shopcartproid'))>0){
                    if(scpids==""){
                	    scpids=packinfo.attr('shopcartproid');
                    }else{
                	    scpids+=","+packinfo.attr('shopcartproid');
                    }
                }
                var packstr = "";
                $pack.find(".div_packsku").each(function () {
//                    var $sku = $(this);
                    var skuinfo = $(this).find(".hidden_packskuinfo");
                    packstr += "skuID:" + NulltoStr(skuinfo.attr('skuid')) + "-";
                    packstr += "proCount:" + NulltoStr(skuinfo.attr('skucount')) + "-";
                    packstr += "packageid:" + NulltoStr(skuinfo.attr('packageid')) + "-";
                    packstr += "marketid:0-";
                    packstr += "spuid:0-";
                    packstr += "spikeid:0-";
                    packstr += "type:" + NulltoStr(skuinfo.attr('skutype')) + "|";
                });
                prostr += packstr;
            })
            if (prostr.length > 1)
                prostr = prostr.substr(0, prostr.length - 1) + ";";
            var c_id = 0, coup_price = 0;//cid优惠券id，coup_price 优惠券面值
            for (i = 0; i < coupons.length; i++) {
                var coup_sid = coupons[i].attributes[3].value;//data_sp 店铺id
                var coup_id = coupons[i].attributes[2].value;//data_cp 优惠券id
                var coup_type = coupons[i].attributes[1].value;//使用对象类型 0-商品 1-商品分类 2-店铺 3-通用
                var coup_ck_p = coupons[i].attributes[5].value.split('_')[1];//value 满额_面值
                if (coup_sid == sid) {//同一店铺下
                    c_id = coup_id;
                    coup_price = parseFloat(coup_ck_p);
                    
                }
//                else {//通用
//                    if (coup_sid == "null" && (totalMoney > coup_ck_p)) {
//                        c_id = coup_id;
//                        coup_price = parseFloat(coup_ck_p);
//                        count_coup++;
//                    }
//                }
                if (coup_type == "3") {
                    zycoupid = coup_id;
                    coup_price = parseFloat(coup_ck_p);
                }
            }
            totaldelmoney += parseFloat(coup_price);
            var ac_id = $shop.find("#p_shopact").attr("shopactid");//店铺活动id
            if (ac_id != null && ac_id != 'undefined' && ac_id != "" && parseInt(ac_id) > 0) {
                var shopactdel = $shop.find("#p_shopact").attr("shopactdel");
                if (shopactdel != null && shopactdel != "" && shopactdel != 'undefined')
                    totaldelmoney += parseFloat(shopactdel);
            }
            else {
                ac_id = 0;
            }

            orderParams += "totalMoney:" + totalMoney + ",";
            orderParams += "delMoney:" + totaldelmoney + ",";
            var freight = $shop.find("span[name='freight_span']").html();
            orderParams += "freightMoney:" + freight + ",";
            orderParams += "couponID:" + c_id + ",";

            orderParams += "activityid:" + ac_id + ",";
            orderParams += "isInvoice:" + isneed + ",";
            var remark = "无";
            orderParams += "remark:" + remark + ",";
            orderParams += prostr;
        })
        if (orderParams.length > 1)
            orderParams = orderParams.substr(0, orderParams.length - 1);

        var receiveAddrID = $("li[name='receiveAddress_list'][data_tag='1']").first().find("input").first().val();
        var dispType = "0";
        var dateType = "0";
        var timeType = "0";
        var invoiceType = "0";
        var titleType = "0";
        var title = "";
        var content = "";//checkbox
        var mobile = $("#InvMobile").val();
        var email = $("#InvEmail").val();
        
        if (parseInt(isneed) == 1) {
            invoiceType = $("#inType_hide").val();
            titleType = $("#hType_hide").val();
            var itl=$("#iTitle_hide").val();
            if(itl==""||itl==null){
            	$("#iTitle_hide").val("个人")
            }
            title = titleType == "0" ? "个人" : $("#iTitle_hide").val();
            content = $("#liContent")[0].innerHTML;
              
        }else{
        	//不需要发票
        	invoiceType = "-1";
        }
        //订单提交
        $.ajax({
            url: "/pc/order/add",
            type: "Post",
            data: {
                "orderparams": orderParams, "receiveaddrid": receiveAddrID, "disptype": dispType, 
                "datetype": dateType, "timetype": timeType,
                "invoicetype": invoiceType, "invoicetitletype": titleType, "invoicetitle": title, 
                "invoicecontent": content, "mobile": mobile, "email": email, 
                "zyactivityid": zyactibityid,"zyconponid": zycoupid,"beans":beans,
                "scids":scpids,"ch":"1"
            },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                	if(parseInt(data.sum)==0){
                		location.href = "/member/order/orderPaySuccess.html?out_trade_no="+data.data+"&total_fee=0.0";
                	}
                	else{
                		location.href = "/member/order/orderPay.html?gc=" + data.data;//跳转订单支付页
                	}
                    
                    //清除立即购买cookie数据
                    DelCookie("buynowstr");
                } else {
                    alert(data.desc);
                }
            }
        })
//        infoc.add("commit", "commitNewOrder", "", 1);
    },
    //选择收货地址
    selectAddress: function (isdefault) {
        var $selectdiv = isdefault == 1 ? $("li[name='receiveAddress_list'][data_tag='1']") : $(this);
        if ($selectdiv.attr("data_tag") == "0" || isdefault == 1) {
            $selectdiv.parent().find("li").each(function () {
                $(this).removeClass("active");
                $(this).attr("data_tag", "0");
            })
            $selectdiv.attr("data_tag", "1");
            $selectdiv.addClass("active");
            //获取运费参数整合 店铺id,店铺商品总数量，店铺商品总价格
            var shopfreight=[],spuids="";
            $("div[name='shop_table']").each(function () {
//                shoplist += $(this).attr("shopid") + ",";
                var count = 0,price=0.00;
                $(this).find("div[name='sku_count']").each(function () {
                    count += parseInt($(this).html());
                });
                
                $(this).find("div[name='totalPrice']").each(function () {
                	price += parseInt($(this).html());
                });
                //获取店铺spu信息
                $(this).find(".hidden_spuinfo").each(function () { 
                		spuids+=$(this).attr("spuid")+",";                        
                });
                shopfreight.push({
                	shopid:$(this).attr("shopid"),
                	procount:count,
                	proprice:price
                })                
            });
            spuids=spuids.substr(0,spuids.length-1);
            
            var province = $("li[name='receiveAddress_list'][data_tag='1']").first().find("input").first().attr("data");
            $.ajax({
                url: "/pc/freight/getFreightByShop",
                type: "Post",
                data: { "shopfreight": JSON.stringify(shopfreight), "province": province,"spuids":spuids,"ch":"1" },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        var total_price = 0;
                        $("div[name='shop_table']").each(function () {
                            var $shopdiv = $(this);
                            var shopid = $shopdiv.attr("shopid");
                            var fre = Enumerable.From(data.data)
                                 .Where(function (x) {
                                     return x.shopid == shopid;
                                 })
                                 .ToArray()[0];
                            $shopdiv.find("span[name='freight_span']").html(fre.price);
                            $shopdiv.attr("data_id", fre.id);
                            total_price+=fre.price;
                        })
                        $("#total_freight").html(total_price.toFixed(2));
                        var totalprice = parseFloat($("#total_price").html());//商品总金额 未减免
                        var totalfreight = parseFloat($("#total_freight").html());//总运费
                        var t_money = totalprice + totalfreight - parseFloat($("#span_alldelmoney").html()) > 0 ? totalprice + totalfreight - parseFloat($("#span_alldelmoney").html()) : 0;
                        $("span[name=total_money]").html('￥' + t_money.toFixed(2));//应付金额

                    } else {
                        alert("商家未设置该收货地区运费，请联系商家处理！")
                        //alert(data.Desc);
                    }
                }
            })
        }
    },
    //选择支付方式
    selectPayType: function () {
        var $payType = $(this);
        var tag = $payType.attr("data_tag");
        if (tag == "0") {
            $payType.parent().find("span").each(function () {
                $(this).removeAttr("style");
                $(this).attr("data_tag", "0");
            })
            $payType.attr("data_tag", "1");
            $payType.css({ "border-style": "solid", "borderColor": "#E4393C", "borderWidth": "2px" });
        }
    }
}
//发票类型
function GetInvoice(userid) {
    $("#linType").html("普通发票"); $("#lhType").html("个人"); $("#liContent").html("明细");
}
//发票明细 内容
function EditInvoice(userid) {
    $("#inType").find("li").each(function () {
        if ($(this).attr("class") == "active") {
            var t = $(this).attr("data_tag");
           
            if (t == 0) {
                $("#inType_hide").val(0);
            }
            if (t == 1) {
                $("#inType_hide").val(1);
            }
            if (t == 2) {
                $("#inType_hide").val(2);
            }
        }
    })

    var ity = $("#inType_hide").val();
    var tty = $("#hType_hide").val();
    var ti = $("#iTitle_hide").val();
    var co = $("#iContent_hide").val();
    var mob = $("#InvMobile").val();
    var ema = $("#InvEmail").val();
    if (ity != 3) {
        if (tty == 0) {
            ti = "个人"
        }
        if (ti == "") { $("#resMessge").html("请填入公司名称，并保存！"); return; }
        //$.ajax({
        //    url: "/Orders/EditInvoice",
        //    type: "Post",
        //    data: {
        //        userID: userid, itype: ity, titleType: tty, title: ti, content: co, mobile: "", email: ""
        //    },
        //    dataType: "json",
        //    success: function (data) {
        //        if (data.Code == 0) {
        //            alert(data.Desc);
        //            $("#invoiceDiv").attr("style", "display:none");
        //        } else {
        //            alert(data.Desc);
        //        }
        //    }
        //});
        if (ity == 1) {
            $("#linType").html("普通发票");
        } else {
            if (ity == 2) {
                $("#linType").html("电子发票");
            } else {
                if (ity == 3) {
                    $("#linType").html("增值税发票");
                }
            }
        }
        if (tty == 0) {
            $("#lhType").html("个人");
        } else {
            if (tty == 1) {
                $("#lhType").html(ti);
            }
        }
        if (co == 0) {
        	
            $("#liContent").html("明细");
        } else {
            if (co == 1) {
            	
                $("#liContent").html("办公用品");
            }
            if (co == 2) {
            	
                $("#liContent").html("电脑配件");
            }
            if (co == 3) {
            	
                $("#liContent").html("耗材");
                
            }
        }
        $("#invoiceDiv").attr("style", "display:none");
    }

   
}

//获取优惠券 内容
function GetCoupon() {
    //参数格式 [{shopID:0,money:0,spuIDs:[1,2,3]},{shopID:0,money:0,spuIDs:[1,2,3]}]
    var allmoney = 0;
    var shopparam=[];
    $("div[name='shop_table']").each(function () {
    	var spuparam=[];
        var $shop = $(this);
        $shop.find(".div_spu").each(function () {
         var spuinfo = $(this).find(".hidden_spuinfo");
        	spuparam.push({
        		proid:spuinfo.attr('spuid'),
        		promoney:spuinfo.attr('sputotalmoney')
        	})
            allmoney += parseFloat(spuinfo.attr('sputotalmoney'));
        });
        shopparam.push({
        	shopid:$shop.attr("shopid"),
        	money:allmoney,
        	prolist:spuparam
        })
    });
    var param = JSON.stringify(shopparam);
    $.ajax({
        url: "/pc/coupon/getOrderCoupon",
        type: "Post",
        data: {
            paramstr: param,ch:"1"
        },
        dataType: "json",
        success: function (data) {
            var cnum = 0;
            if (data.code == 0) {
                var coupons = data.data;
                if (coupons != "" && coupons.length > 0) {
                    var cp = "";
                    for (i = 0; i < coupons.length; i++) {
                        cp += "<li><span class='coupon_text yscmh'>" + coupons[i].couponname +
                              "</span><span class='coupont_time yscmh'>有效期至：" + coupons[i].outtime +
                              "</span><span class='coupont_value red'>" + coupons[i].facevalue +
                              "元</span><input type='checkbox' "
                              + "data_ci='" + coupons[i].couponusetype 
                              + "' data_cp='" + coupons[i].couponid 
                              + "' data_sp='" + coupons[i].shopid 
                              + "' name='coupon_cb' value='" 
                              + coupons[i].fullreductionvalue + "_" 
                              + coupons[i].facevalue + "' /></li>";
                    }
                    $("#cupon_list").html(cp);
                    $("#cnum").text("可用优惠券(" + coupons.length + ")");//可用优惠券数量
                }
            } else {

            }
        },
        error: function (es) {
        }
    })
}

//字符处理
function NulltoStr(str) {
    if (str == null || str=="" ||str==undefined)
        return "0";
    return str;

}

//orderParams +=  shopID:x,totalMoney:x,delMoney:x,freightMoney:x,couponID:x,activityid:x,isInvoice:x,remark:x,skuID:y-proCount:y-packageid:y-marketid:y|skuID:y-proCount:y-packageid:y-marketid:y-spuid:y;
//                shopID:x,totalMoney:x,delMoney:x,freightMoney:x,couponID:x,activityid:x,isInvoice:x,remark:x,skuID:y-proCount:y-packageid:y-marketid:y|skuID:y-proCount:y-packageid:y-marketid:y-spuid:y

//shopID:x,totalMoney:x,delMoney:x,freightMoney:x,couponID:x,activityid:x,isInvoice:x,remark:x,skuID:y-proCount:y-packageid:y-marketid:y-spuid:y-spikeid:y-type:y|skuID:y-proCount:y-packageid:y-marketid:y-spuid:y-spikeid:y-type:y;
//shopID:x,totalMoney:x,delMoney:x,freightMoney:x,couponID:x,activityid:x,isInvoice:x,remark:x,skuID:y-proCount:y-packageid:y-marketid:y|skuID:y-proCount:y-packageid:y-marketid:y-spuid:y
