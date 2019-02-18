/// <reference path="linq.min.js" />
//购物车新版
var Cart = {
    //添加 callback:回调函数 shopid:店铺ID  goodsid:组合商品ID或商品SKUID  goodscount:商品数量  ispack:商品类型（0普通商品 1组合商品 2秒杀 3闪购） spuid:商品SPUID(组合商品时为包含的商品种类数量) ischecked:是否选中(选中为1 未选中为0)  spikeid:闪购或秒杀ID
    add: function (callback, shopid, goodsid, goodscount, ispack, spuid, ischecked, spikeid) {
        if (user.IsLogin()) {
            //已登录
            $.ajax(({
                type: "post",
                url: "/ShopCart/B_AddCart",
                dataType: "json",
                data: { shopid: shopid, goodsid: goodsid, goodscount: goodscount, ispack: ispack, spuid: spuid, spikeid: spikeid },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        if (callback) {
                            callback(goodsid, ispack);
                        }
                    }
                },
                error: function (e) {
                }
            }));
        } else {
            //未登录 添加到cookie
            var strCookie = GetCookie("CartStr");
            var dic = [];
            if (strCookie != null)
                dic = JSON.parse(strCookie);
            var queryResult = Enumerable.From(dic)
               .Where(function (x) {
                   return x.cartID == goodsid + '_' + ispack;
               })
               .ToArray();
            if (queryResult == null || queryResult.length == 0) {
                dic.push({ cartID: goodsid + '_' + ispack, val: spuid + '_' + shopid + '_' + goodsid + '_' + goodscount + '_' + ispack + '_' + ischecked + '_' + spikeid });
            }
            else {
                var val = queryResult[0];
                dic.remove(val);
                var valarr = val.val.split('_')
                dic.push({ cartID: goodsid + '_' + ispack, val: spuid + '_' + shopid + '_' + goodsid + '_' + (parseInt(valarr[3]) + parseInt(goodscount)) + '_' + ispack + '_' + ischecked + '_' + spikeid });
            }
            SetCookie("CartStr", JSON.stringify(dic));
            if (callback) {
                callback(goodsid, ispack);
            }
        }
    },
    //添加回调 goodsid:组合商品ID或商品SKUID  ispack:是否组合商品（组合商品为1 非组合商品未0）
    addcallback: function (goodsid, ispack) {
        //返回url
        var backUrl = encodeURIComponent(window.location.href);
        var cartID = goodsid + '_' + ispack;
        self.location.href = "/Web/Cart/Cart_AddSucceed?cartid=" + cartID + "&backurl=" + backUrl;
    },
    //立即购买 同add备注
    buynow: function (callback, shopid, goodsid, goodscount, ispack, spuid, ischecked, spikeid) {
        if (user.IsLogin()) {
            //添加到数据库
            $.ajax(({
                type: "post",
                url: "/ShopCart/B_BuyNow",
                dataType: "json",
                data: { shopid: shopid, goodsid: goodsid, goodscount: goodscount, ispack: ispack, spuid: spuid, spikeid: spikeid },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        if (callback) {
                            callback(goodsid, ispack);
                        }
                    }
                },
                error: function (e) {
                }
            }));
        }
    },
    //立即购买回调
    buynowcallback: function (goodsid, ispack) {
        //to do
        var cartID = goodsid + '_' + ispack;
        self.location.href = "/Web/Cart/Cart_List?cartid=" + cartID + "&isbuynow=" + 1;
    },


    //购物车列表
    getlist: function (callback) {
        var cartStr = "";
        var GoodsID = "";
        var GoodsType = "";
        if (!user.IsLogin()) {
            //未登录
            var strCookie = GetCookie("CartStr");
            if (strCookie == null) {
                //购物车无商品显示样式
                $(".l_nogwc").show();
                return;
            }
            var dic = [];
            if (strCookie != null)
                dic = JSON.parse(strCookie);
            var vals = [];
            for (var i = 0; i < dic.length; i++) {
                var val = dic[i].val;

                while (val.indexOf("_") != -1) {
                    val = val.replace('_', ',');
                }
                vals.push(val);
            }
            cartStr = vals.join("|");
        }

        //立即购买
        var isbuynow = GetQueryStringByName("isbuynow");
        var cartid = GetQueryStringByName("cartid");
        var goodsid = cartid.split('_')[0];
        var goodstype = cartid.split('_')[1];
        if (isbuynow == 1 && goodsid > 0) {
            GoodsID = goodsid;
            GoodsType = goodstype
        }
        $.ajax(({
            type: "post",
            url: "/ShopCart/B_GetCartList",
            dataType: "json",
            data: { CartStr: cartStr, GoodsID: GoodsID, GoodsType: GoodsType },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        cart: rsl.Data
                    }

                    $("#div_cart").html(template('cart', listdata));

                    if (callback) {
                        callback(rsl.Data);
                    }
                }
            },
            error: function (e) {
            }
        }));

    },
    //购物车列表回调
    getlistcallback: function (data) {
        //产品页面跳转
        $(".a_spuhref").bind("click", Cart.goods_href);

        //购物车数量显示
        var showcount = $(".span_allcount").html();
        if (showcount == "" || showcount == null || showcount == 'undefined') {
            showcount = 0;

            //购物车无商品显示样式
            $(".l_nogwc").show();
            //是否显示登录按钮
            if (!user.IsLogin())
                $("#span_login").show();
            else
                $("#span_login").hide();
        }
        $("#span_shoppingcartgoodscount").html(showcount);

        //规格处理 
        $("#div_cart").find(".div_specs").each(function () {
            var specs_str = $(this).attr("specs-value");

            if (specs_str == "" || specs_str == null || specs_str == 'undefined') {
                specs_str = "[]";
            }
            var specs_json = JSON.parse(specs_str);
            var result = "";
            for (var i = 0; i < specs_json.length; i++) {
                result += '<p>' + specs_json[i].name + '：' + specs_json[i].value + '</p>';
            }
            $(this).html(result);
        })

        //活动处理
        $(".div_detailactivity").each(function (i) {
            var bol = false;
            var $ob = $(this);
            $ob.find(".radio_spuact").each(function (k) {
                if ($(this).attr("checked")) {
                    bol = true;
                    return;
                }
            });
            if (!bol) {
                $ob.find(".p_isjoin").find(".radio_spuact").attr("checked", true);
            }
        });

        //优惠券相关
        //显示和隐藏优惠券列表 未登录时弹出登录
        $(".span_couponlist").bind("click", function () {
            if (!user.IsLogin()) {
                $(".div_login").show();
                return;
            }
            var node = $(this).parent().find(".div_couponcontent");
            if (node.is(':hidden')) {
                node.show();
            } else {
                node.hide();
            }
        })
        //领取优惠券
        $(".a_getcoupon").bind("click", function () {
            var objt = $(this);
            var couponid = parseInt(objt.attr("couponid-val"));
            $.ajax(({
                type: "post",
                url: "/Coupon/M_GetConpon",
                dataType: "json",
                data: { couponid: couponid, count: 1 },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        objt.parent().addClass("ahs");
                        objt.parent().prev().prev().addClass("ahs");
                        objt.parent().text("已领取");
                    }
                },
                error: function (e) {
                }
            }));
        })

        //活动相关
        //显示和隐藏活动列表
        $(".span_activity").bind("click", function () {
            var node = $(this).parent().next();
            if (node.is(':hidden')) {
                node.show();
            } else {
                node.hide();
            }
        })
        //取消按钮 隐藏活动列表
        $(".a_close_actdiv").bind("click", function () {
            var obj = $(this).parents(".div_detailactivity");
            obj.hide();
        })
        //确认按钮 
        $(".a_ok_actdiv").bind("click", function () {
            var obj = $(this).parents(".div_detailactivity");
            var actid = obj.find("input[class='radio_spuact']:checked").val();
            if (actid != null && actid != undefined) {
                //更新商品活动
                var goodsid = obj.attr('skuid-val');
                Cart.update(Cart.updatecallback, goodsid, 0, null, null, actid);
            }
            obj.hide();
        })

        //数量变更相关
        //商品数量文本框得到、失去焦点事件
        var goodscountLog;
        $(".text_goodscount").focus(function () {
            goodscountLog = parseInt($(this).val());
        })
        $(".text_goodscount").blur(function () {
            if (isNaN($(this).val())) {
                alert("输入数量有误");
                $(this).attr("value", goodscountLog);
                return;
            }
            var goodscountNew = parseInt($(this).val());
            var goodsstock = parseInt($(this).attr("stock-val"));
            if (goodscountNew > goodsstock) {
                alert("商品数量超现");
                $(this).attr("value", goodscountLog);
                return;
            }
            if (goodscountNew < 1) {
                alert("商品数量必须大于0");
                $(this).attr("value", goodscountLog);
                return;
            }
            //变更数据库数量
            var goodsid = $(this).parent().attr('skuid-val');
            Cart.update(Cart.updatecallback, goodsid, 0, goodscountNew, null);
        })
        //增减事件
        $(".span_down").bind("click", function () {
            var count = parseInt($(this).parent().find(".text_goodscount").val());
            if (count < 2) {
                return;
            }
            //变更数据库或cookie数量
            var goodsid = $(this).parent().attr('skuid-val');
            Cart.update(Cart.updatecallback, goodsid, 0, count - 1, null);
        });
        $(".span_up").bind("click", function () {
            var count = parseInt($(this).parent().find(".text_goodscount").val());
            var stock = parseInt($(this).parent().find(".hidden_goodsstock").val());
            if (count > stock - 1) {
                return;
            }
            //变更数据库或cookie数量
            var goodsid = $(this).parent().attr('skuid-val');
            Cart.update(Cart.updatecallback, goodsid, 0, count + 1, null);
        });
        //组合商品数量文本框得到、失去焦点事件
        var packagecountLog;
        $(".text_packagecount").focus(function () {
            packagecountLog = parseInt($(this).val());
        })
        $(".text_packagecount").blur(function () {
            if (isNaN($(this).val())) {
                alert("输入数量有误");
                $(this).attr("value", packagecountLog);
                return;
            }
            var packagecountNew = parseInt($(this).val());
            var packagestock = parseInt($(this).attr("stock-val"));
            if (packagecountNew > packagestock) {
                alert("商品数量超现");
                $(this).attr("value", packagecountLog);
                return;
            }
            if (packagecountNew < 1) {
                alert("商品数量必须大于0");
                $(this).attr("value", packagecountLog);
                return;
            }
            //变更数据库数量
            var goodsid = $(this).parent().attr('packid-val');
            Cart.update(Cart.updatecallback, goodsid, 1, packagecountNew, null);
        })
        //增减事件
        $(".span_packdown").bind("click", function () {
            var count = parseInt($(this).parent().find(".text_packagecount").val());
            if (count < 2) {
                return;
            }
            //变更数据库或cookie数量
            var goodsid = $(this).parent().attr('packid-val');
            Cart.update(Cart.updatecallback, goodsid, 1, count - 1, null);

        });
        $(".span_packup").bind("click", function () {
            var count = parseInt($(this).parent().find(".text_packagecount").val());
            var stock = parseInt($(this).parent().find(".text_packagecount").attr("stock-val"));
            if (count > stock - 1) {
                return;
            }
            //变更数据库或cookie数量
            var goodsid = $(this).parent().attr('packid-val');
            Cart.update(Cart.updatecallback, goodsid, 1, count + 1, null);
        });



        //是否选中相关
        //商品选择
        $(".checkbox_goods").change(function () {
            var goodsid = $(this).val();
            var ischecked = '';
            if ($(this).attr('checked')) {
                ischecked = '1';
            }
            else {
                ischecked = '0';
            }
            Cart.update(Cart.updatecallback, goodsid, 0, null, ischecked);
        });
        $(".checkbox_package").change(function () {
            var goodsid = $(this).val();
            var ischecked = '';
            if ($(this).attr('checked')) {
                ischecked = '1';
            }
            else {
                ischecked = '0';
            }
            Cart.update(Cart.updatecallback, goodsid, 1, null, ischecked);
        });
        //店铺选择
        $(".checkbox_shopall").change(function () {
            var shopid = $(this).val();
            var ischecked = '';
            if ($(this).attr('checked')) {
                ischecked = '1';
            }
            else {
                ischecked = '0';
            }
            Cart.updateshopchecked(Cart.updatecallback, shopid, ischecked);
        });
        //购物车全选
        $(".checkbox_shoppingcartall").change(function () {
            var shopids = '';
            $("#div_cart").find(".checkbox_shopall").each(function () {
                shopids += $(this).val() + ',';
            })
            if (shopids.length > 1)
                shopids = shopids.substring(0, shopids.length - 1).toString();
            var ischecked = '';
            if ($(this).attr('checked')) {
                ischecked = '1';
            }
            else {
                ischecked = '0';
            }
            Cart.updateshopchecked(Cart.updatecallback, shopids, ischecked);
        });

        //删除相关
        //普通商品
        $(".a_delshoppingcartgoods").bind("click", function () {
            var goodsid = $(this).attr('goodsid-val');
            if (confirm("确认删除吗?")) {
                Cart.del(Cart.delcallback, goodsid, 0);
            }       
        })
        //组合商品
        $(".a_delpackagegoods").bind("click", function () {
            var goodsid = $(this).attr('goodsid-val');
            if (confirm("确认删除吗?")) {
                Cart.del(Cart.delcallback, goodsid, 1);
            }          
        })
        //组合商品中单个商品
        $(".a_delpackagesingle").bind("click", function () {
            var packid = $(this).attr('packid-val');
            var delskuid = $(this).attr('skuid-val');
            if (confirm("确认删除吗?")) {
                Cart.delpacksingle(Cart.delcallback, packid, delskuid);
            }
        })
        //删除选中的商品
        $(".a_delselectedgoods").bind("click", function () {
            var delstr = "";
            $(".checkbox_goods").each(function (e) {
                if ($(this).attr('checked')) {
                    delstr += parseInt($(this).parents(".div_sku").find(".a_delshoppingcartgoods").attr('goodsid-val')) + "_0|";
                }
            })
            $(".checkbox_package").each(function (e) {
                if ($(this).attr('checked')) {
                    delstr += parseInt($(this).parents(".div_packages").find(".a_delpackagegoods").attr('goodsid-val')) + "_1|";
                }
            })
            if (delstr.length > 1) {
                delstr = delstr.substring(0, delstr.length - 1).toString();
            }
            if (delstr != null && delstr != "")
            {
                if (confirm("确认删除吗?")) {
                    Cart.delbatch(Cart.delcallback, delstr);
                }
            }
               
        })

        //关注相关 
        $(".a_concernshoppingcartgoods").bind("click", function () {
            var goodsid = $(this).attr('goodsid-val');
            Cart.addconcern(goodsid);
        })
        //关注选中的商品
        $(".a_concernselectedgoods").bind("click", function () {
            var goodsids = "";
            $(".checkbox_goods").each(function (e) {
                if ($(this).attr('checked')) {
                    goodsids += parseInt($(this).parents(".div_sku").find(".a_concernshoppingcartgoods").attr('goodsid-val')) + ",";
                }
            })
            $(".checkbox_package").each(function (e) {
                if ($(this).attr('checked')) {
                    $(this).parents(".div_packages").find(".a_concernshoppingcartgoods").each(function () {
                        goodsids += parseInt($(this).attr('goodsid-val')) + ",";
                    })
                }
            })
            if (goodsids.length > 1) {
                goodsids = goodsids.substring(0, goodsids.length - 1).toString();
            }
            if (goodsids != null && goodsids != "")
                Cart.addbatchconcern(goodsids);
        })
        //去结算
        $(".div_goclearing").bind("click", function () {
            if (!user.IsLogin()) {
                showlogindiv();
                return;
            }

            var bool = false;
            $(".checkbox_goods").each(function (e) {
                if ($(this).attr('checked')) {
                    bool = true;
                    return;
                }
            })
            if (!bool) {
                $(".checkbox_package").each(function (e) {
                    if ($(this).attr('checked')) {
                        bool = true;
                        return;
                    }
                })
            }
            if (!bool) {
                alert("请选择至少一样商品");
                return;
            }
            //新版结算  
            var clearing_params = "";
            $("#ul_cart").find(".li_shop").each(function () {
                var isenter = false;
                $(this).find(".checkbox_goods").each(function () {
                    if ($(this).attr('checked')) {
                        isenter = true;
                        return;
                    }

                })
                if (!isenter) {
                    $(this).find(".checkbox_package").each(function () {
                        if ($(this).attr('checked')) {
                            isenter = true;
                            return;
                        }
                    })
                }

                if (isenter) {
                    clearing_params += '{';
                    var shopinfo = $(this).find(".hidden_shop");
                    var shopactinfo = $(this).find(".hidden_shopact");
                    //店铺ID
                    var shopid_params = '"ShopId":"' + shopinfo.attr('shopid') + '"';
                    //店铺名称
                    var shopname_params = '"ShopName":"' + shopinfo.attr('shopname') + '"';
                    //店铺总金额
                    var shoptotalmoney_params = '"TotalMoney":"' + shopinfo.attr('shoptotalmoney') + '"';

                    //店铺减免金额
                    var shopdelmoney_params = '"DelMoney":"' + shopinfo.attr('shopdelmoney') + '"';

                    //店铺活动
                    var shopact_params = '';
                    if (shopactinfo.attr("actid") != null && shopactinfo.attr("actid") != 'undefined') {
                        var shopactid = NulltoStr(shopactinfo.attr("actid"));
                        var shopactname = NulltoStr(shopactinfo.attr("actname"));
                        var shopacttype = NulltoStr(shopactinfo.attr("acttype"));
                        var shopacttypename = NulltoStr(shopactinfo.attr("acttypename"));
                        var shopactdesc = "";
                        var shopactdelmoney = NulltoStr(shopactinfo.attr("actdelmoney"));
                        shopact_params = '"Id": "' + shopactid + '","Name": "' + shopactname + '","Type": "'
                               + shopacttype + '","TypeName": "' + shopacttypename + '", "Desc": "'
                               + shopactdesc + '","DelMoney": "' + shopactdelmoney + '",';

                        //活动赠品信息
                        var shopact_skuscd_params = '';//库存商品
                        var shopact_gifscd_params = '';//优惠券或积分
                        var ishavagif = false;//是否有赠品
                        if (parseInt(shopacttype) == 1) {
                            $(this).find(".div_shopactgif ul li").each(function () {
                                var $objgif = $(this);
                                if ($objgif.is('.li_skugif')) {
                                    //sku赠品
                                    shopact_skuscd_params += '{';
                                    var $objhidden = $objgif.find('.hidden_skugifinfo');
                                    var skuid = NulltoStr($objhidden.attr("skuid"));
                                    var skutype = NulltoStr($objhidden.attr("skutype"));
                                    var skuname = NulltoStr($objhidden.attr("skuname"));
                                    var skuprice = NulltoStr($objhidden.attr("skuprice"));
                                    var skuimg = NulltoStr($objhidden.attr("skuimg"));
                                    var skucount = NulltoStr($objhidden.attr("skucount"));
                                    var skustock = NulltoStr($objhidden.attr("skustock"));
                                    var skuspecsinfo = NulltoStr($objhidden.attr("skuspecs"));
                                    var skuspikeid = NulltoStr($objhidden.attr("skuspikeid"));
                                    shopact_skuscd_params += '"Id": "' + skuid + '", "ProType": "' + skutype + '", "Name": "' + skuname + '", "Price": "' + skuprice + '","Img": "'
                                                         + skuimg + '", "Count": "' + skucount + '", "Stock": "' + skustock + '", "SpecsInfo": "' + skuspecsinfo + '","SpikeID":"' + skuspikeid + '"';
                                    shopact_skuscd_params += '},';
                                }
                                if ($objgif.is('.li_othergif')) {
                                    //优惠券或积分赠品
                                    shopact_gifscd_params += '{';
                                    var $objhidden = $objgif.find('.hidden_othergifinfo');
                                    var gifid = NulltoStr($objhidden.attr("otherid"));
                                    var giffacevalue = NulltoStr($objhidden.attr("otherfacevalue"));
                                    var giftype = NulltoStr($objhidden.attr("othertype"));
                                    var gifcount = NulltoStr($objhidden.attr("othercount"));
                                    shopact_gifscd_params += '"Id":"' + gifid + '", "FaceValue":"' + giffacevalue + '", "Type":"' + giftype + '", "Count":"' + gifcount + '"';
                                    shopact_gifscd_params += '},';
                                }
                            })
                            if (shopact_skuscd_params.length > 1) {
                                ishavagif = true;
                                shopact_skuscd_params = shopact_skuscd_params.substring(0, shopact_skuscd_params.length - 1).toString();
                            }
                            if (shopact_gifscd_params.length > 1) {
                                ishavagif = true;
                                shopact_gifscd_params = shopact_gifscd_params.substring(0, shopact_gifscd_params.length - 1).toString();
                            }
                        }
                        shopact_skuscd_params = '"Skuscd":[' + shopact_skuscd_params + ']';
                        shopact_gifscd_params = '"Gifscd":[' + shopact_gifscd_params + ']';
                        shopact_params += shopact_skuscd_params + ',' + shopact_gifscd_params;
                        if (parseInt(shopacttype) == 1 && ishavagif == false) //满赠活动赠品不存在时处理
                            shopact_params = '';
                    }
                    shopact_params = '"Asd":{' + shopact_params + '}';
                    //组合商品
                    var pack_params = "";
                    $(this).find(".div_packages").each(function () {
                        if ($(this).find(".checkbox_package").attr('checked')) {
                            pack_params += "{";
                            var packinfo = $(this).find(".hidden_packinfo");
                            var pid = NulltoStr(packinfo.attr("packid"));      //套餐ID
                            var pname = NulltoStr(packinfo.attr("packname"));  //套餐名称
                            var pcount = NulltoStr(packinfo.attr("packcount")); //套餐数量
                            var pprice = NulltoStr(packinfo.attr("packprice")); //套餐总价
                            pack_params += '"Id": "' + pid + '","Name": "' + pname + '","Count": ' + pcount + ',"Price": "' + pprice + '",';

                            var packsku_param = "";
                            $(this).find(".hidden_packskuinfo").each(function () {
                                packsku_param += '{';
                                var $this = $(this);
                                var skuid = NulltoStr($this.attr("skuid"));
                                var skuname = NulltoStr($this.attr("skuname"));
                                var skuprice = NulltoStr($this.attr("skuprice"));
                                var skuimg = NulltoStr($this.attr("skuimg"));
                                var skucount = NulltoStr($this.attr("skucount"));
                                var skustock = NulltoStr($this.attr("skustock"));
                                var skuspecsinfo = NulltoStr($this.attr("skuspecs"));

                                packsku_param += '"Id": "' + skuid + '", "ProType": "1", "Name": "' + skuname + '", "Price": "' + skuprice + '","Img": "'
                                              + skuimg + '", "Count": "' + skucount + '", "Stock": "' + skustock + '", "SpecsInfo": "' + skuspecsinfo + '"';

                                packsku_param += '},';
                            })
                            if (packsku_param.length > 1)
                                packsku_param = packsku_param.substring(0, packsku_param.length - 1).toString();
                            packsku_param = '"Skuscd":[' + packsku_param + ']';
                            pack_params += packsku_param;
                            pack_params += "},";
                        }
                    })
                    if (pack_params.length > 1)
                        pack_params = pack_params.substring(0, pack_params.length - 1).toString();
                    pack_params = '"Packscd":[' + pack_params + ']';

                    //SPU商品
                    var sputotal_params = "";
                    $(this).find(".div_spus").each(function () {
                        sputotal_params += '{';
                        var spuinfo = $(this).find(".hidden_spuinfo");
                        sputotal_params += '"Id": "' + spuinfo.attr("spuid") + '","TotalMoney": "' + spuinfo.attr("sputotalmoney") + '","DelMoney": "' + spuinfo.attr("spudelmoney") + '",';
                        //SPU活动
                        var spuactinfo = $(this).find(".div_spuact").find(".hidden_spuact");
                        var spuact_params = '';
                        if (spuactinfo.attr('actid') != null && spuactinfo.attr('actid') != 'undefined') {
                            var spuactid = NulltoStr(spuactinfo.attr("actid"));
                            var spuactname = NulltoStr(spuactinfo.attr("actname"));
                            var spuacttype = NulltoStr(spuactinfo.attr("acttype"));
                            var spuacttypename = NulltoStr(spuactinfo.attr("acttypename"));
                            var spuactdesc = NulltoStr(spuactinfo.attr("actdesc"));
                            var spuactdelmoney = NulltoStr(spuactinfo.attr("actdelmoney"));
                            spuact_params = '"Id": "' + spuactid + '","Name": "' + spuactname + '","Type": "'
                                   + spuacttype + '","TypeName": "' + spuacttypename + '", "Desc": "'
                                   + spuactdesc + '","DelMoney": "' + spuactdelmoney + '",';

                            //活动赠品信息
                            var spuact_skuscd_params = '';//库存商品
                            var spuact_gifscd_params = '';//优惠券或积分
                            var ishavagif = false;//是否有赠品
                            if (parseInt(spuacttype) == 1) {
                                $(this).find(".div_spuactgif ul li").each(function () {
                                    var $objgif = $(this);
                                    if ($objgif.is('.li_skugif')) {
                                        //sku赠品
                                        spuact_skuscd_params += '{';
                                        var $objhidden = $objgif.find('.hidden_skugifinfo');
                                        var skuid = NulltoStr($objhidden.attr("skuid"));
                                        var skutype = NulltoStr($objhidden.attr("skutype"));
                                        var skuname = NulltoStr($objhidden.attr("skuname"));
                                        var skuprice = NulltoStr($objhidden.attr("skuprice"));
                                        var skuimg = NulltoStr($objhidden.attr("skuimg"));
                                        var skucount = NulltoStr($objhidden.attr("skucount"));
                                        var skustock = NulltoStr($objhidden.attr("skustock"));
                                        var skuspecsinfo = NulltoStr($objhidden.attr("skuspecs"));
                                        var skuspikeid = NulltoStr($objhidden.attr("skuspikeid"));
                                        spuact_skuscd_params += '"Id": "' + skuid + '", "ProType": "' + skutype + '", "Name": "' + skuname + '", "Price": "' + skuprice + '","Img": "'
                                                             + skuimg + '", "Count": "' + skucount + '", "Stock": "' + skustock + '", "SpecsInfo": "' + skuspecsinfo + '","SpikeID":"' + skuspikeid + '"';
                                        spuact_skuscd_params += '},';
                                    }
                                    if ($objgif.is('.li_othergif')) {
                                        //优惠券或积分赠品
                                        spuact_gifscd_params += '{';
                                        var $objhidden = $objgif.find('.hidden_othergifinfo');
                                        var gifid = NulltoStr($objhidden.attr("otherid"));
                                        var giffacevalue = NulltoStr($objhidden.attr("otherfacevalue"));
                                        var giftype = NulltoStr($objhidden.attr("othertype"));
                                        var gifcount = NulltoStr($objhidden.attr("othercount"));
                                        spuact_gifscd_params += '"Id":"' + gifid + '", "FaceValue":"' + giffacevalue + '", "Type":"' + giftype + '", "Count":"' + gifcount + '"';
                                        spuact_gifscd_params += '},';
                                    }
                                })
                                if (spuact_skuscd_params.length > 1) {
                                    ishavagif = true;
                                    spuact_skuscd_params = spuact_skuscd_params.substring(0, spuact_skuscd_params.length - 1).toString();
                                }
                                if (spuact_gifscd_params.length > 1) {
                                    ishavagif = true;
                                    spuact_gifscd_params = spuact_gifscd_params.substring(0, spuact_gifscd_params.length - 1).toString();
                                }
                            }
                            spuact_skuscd_params = '"Skuscd":[' + spuact_skuscd_params + ']';
                            spuact_gifscd_params = '"Gifscd":[' + spuact_gifscd_params + ']';
                            spuact_params += spuact_skuscd_params + ',' + spuact_gifscd_params;
                            if (parseInt(spuacttype) == 1 && ishavagif == false) //满赠活动赠品不存在时处理
                                spuact_params = '';
                        }
                        spuact_params = '"Asd":{' + spuact_params + '},';
                        sputotal_params += spuact_params;

                        //SKU列表
                        var spusku_params = "";
                        $(this).find(".div_sku").each(function () {
                            var spuskuobj = $(this);
                            if (spuskuobj.find(".checkbox_goods").attr('checked')) {
                                spusku_params += '{';

                                var spusku = spuskuobj.find(".hidden_spuskuinfo");
                                var skuid = NulltoStr(spusku.attr("skuid"));
                                var skutype = NulltoStr(spusku.attr("skutype"));
                                var skuname = NulltoStr(spusku.attr("skuname"));
                                var skuprice = NulltoStr(spusku.attr("skuprice"));
                                var skuimg = NulltoStr(spusku.attr("skuimg"));
                                var skucount = NulltoStr(spusku.attr("skucount"));
                                var skustock = NulltoStr(spusku.attr("skustock"));
                                var skuspecsinfo = NulltoStr(spusku.attr("skuspecs"));
                                var skuspikeid = NulltoStr(spusku.attr("skuspikeid"));

                                spusku_params += '"Id": "' + skuid + '", "ProType": "' + skutype + '", "Name": "' + skuname + '", "Price": "' + skuprice + '","Img": "'
                                              + skuimg + '", "Count": "' + skucount + '", "Stock": "' + skustock + '", "SpecsInfo": "' + skuspecsinfo + '","SpikeID":"' + skuspikeid + '"';
                                spusku_params += '},';
                            }
                        })
                        if (spusku_params.length > 1)
                            spusku_params = spusku_params.substring(0, spusku_params.length - 1).toString();
                        spusku_params = '"Skuscd":[' + spusku_params + ']';
                        sputotal_params += spusku_params;
                        sputotal_params += '},';
                    })
                    if (sputotal_params.length > 1)
                        sputotal_params = sputotal_params.substring(0, sputotal_params.length - 1).toString();
                    sputotal_params = '"Spuscd":[' + sputotal_params + ']';
                    clearing_params += shopid_params + ',' + shopname_params + ',' + shoptotalmoney_params + ',' + shopdelmoney_params + ',' + shopact_params + ',' + sputotal_params + ',' + pack_params;
                    clearing_params += '},';
                }
            });
            clearing_params = clearing_params.substring(0, clearing_params.length - 1).toString();
            clearing_params = '"Shops":[' + clearing_params + ']';

            var cart_params = '';
            cart_params += '{';
            cart_params += ' "DelMoney": "' + $("#hidden_cartinfo").attr('delmoney')
                         + '", "TotalMoney": "' + $("#hidden_cartinfo").attr('totalmoney')
                         + '", "TotalCount": "' + $("#hidden_cartinfo").attr('selectedcount')
                         + '","PayMoney": "' + $("#hidden_cartinfo").attr('paymoney') + '",';
            cart_params += clearing_params;
            cart_params += '}';
            var param = encodeURIComponent(cart_params);
            $("#cart_paramstr").val(param);
            $("#suborder").submit();
        })

        //立即购买直接跳转结算页
        var isbuynow = GetQueryStringByName("isbuynow");
        var cartid = GetQueryStringByName("cartid");
        var goodsid = cartid.split('_')[0];
        var goodstype = cartid.split('_')[1];
        if (isbuynow == 1 && goodsid > 0) {
            $(".div_goclearing").click();
        }
    },
    //添加购物车成功页列表
    getlistsucceed: function (callback, cartid) {
        var cartStr = "";
        var goodsid = "";
        var ispack = false;
        if (cartid != null && cartid != "") {
            goodsid = parseInt(cartid.split('_')[0]);
            ispack = parseInt(cartid.split('_')[1]) == 1 ? true : false;
        }
        if (!user.IsLogin()) {
            //未登录
            var strCookie = GetCookie("CartStr");
            if (strCookie == null) {
                //TODO 购物车为空 未处理
                return;
            }
            var dic = [];
            if (strCookie != null)
                dic = JSON.parse(strCookie);
            var vals = [];
            for (var i = 0; i < dic.length; i++) {
                var val = dic[i].val;

                while (val.indexOf("_") != -1) {
                    val = val.replace('_', ',');
                }
                vals.push(val);
            }
            cartStr = vals.join("|");
        }

        $.ajax(({
            type: "post",
            url: "/ShopCart/B_GetCartList",
            dataType: "json",
            data: { CartStr: cartStr, GoodsID: "", GoodsType: "" },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        cart: rsl.Data,
                        goodsid: goodsid,
                        ispack: ispack
                    }



                    if (callback) {
                        callback(listdata);
                    }
                }
            },
            error: function (e) {
            }
        }));

    },
    //添加购物车成功列表页回调
    getlistsucceedcallback: function (listdata) {
        $("#div_cart").html(template('cart', listdata));
        //产品页面跳转
        $(".a_spuhref").bind("click", Cart.goods_href);
    },
    //获取购物车商品总数
    getgoodscount: function (callback) {
        var goodscount = 0;
        if (user.IsLogin()) {
            //已登录
            $.ajax(({
                type: "post",
                url: "/ShopCart/B_GetGoodsCount",
                dataType: "json",
                data: {},
                async: false,
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        var jsonData = rsl.Data;
                        goodscount = parseInt(jsonData);
                    }
                },
                error: function (e) {
                }
            }));
        }
        else {
            //未登录
            var strCookie = GetCookie("CartStr");
            if (strCookie != null) {
                var dic = [];
                dic = JSON.parse(strCookie);
                for (var i = 0; i < dic.length; i++) {
                    var valarr = dic[i].val.split('_');
                    var ispack = parseInt(valarr[4]);
                    var count = parseInt(valarr[3]);
                    var spuid = parseInt(valarr[0]);
                    if (ispack == 0) {
                        //非组合商品
                        goodscount += count;
                    }
                    else if (ispack == 1) {
                        //组合商品 spuid为组合商品包含的商品种类数
                        goodscount += count * spuid;
                    }
                }
            }
        }

        if (callback) {
            callback(goodscount);
        }
    },
    //获取购物车商品总数回调
    getgoodscountcallback: function (goodscount) {
        $("#span_shoppingcartgoodscount").text(goodscount);
        $(".div_shoppingcartgoodscountsum").text(goodscount);
    },


    //更新 数量、是否选中、活动  goodsid:商品ID  ispack:是否组合商品  goodscount;更新数量  ischecked:是否选中(0:未选中 1:选中)
    update: function (callback, goodsid, ispack, goodscount, ischecked, skuactid) {
        if (user.IsLogin()) {
            $.ajax(({
                type: "post",
                url: "/ShopCart/B_UpdateCart",
                dataType: "json",
                data: { goodsid: goodsid, ispack: ispack, goodscount: goodscount, ischecked: ischecked, skuactid: skuactid },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error: function (e) {
                }
            }));
        } else {
            var cartid = goodsid + '_' + ispack;
            var strCookie = GetCookie("CartStr");
            var dic = [];
            if (strCookie != null)
                dic = JSON.parse(strCookie);
            var queryResult = Enumerable.From(dic)
               .Where(function (x) {
                   return x.cartID == cartid;
               })
               .ToArray();
            if (queryResult == null || queryResult.length == 0) {
                //alert("cookie中没有商品信息");
            }
            else {
                var val = queryResult[0];
                dic.remove(val);
                var valarr = val.val.split('_')

                var spuid = parseInt(valarr[0]);
                var shopid = parseInt(valarr[1]);
                var goodsid = parseInt(valarr[2]);
                var ispack = parseInt(valarr[4]);
                var goodscount_old = parseInt(valarr[3]);
                var ischecked_old = parseInt(valarr[5]);
                if (goodscount == null || goodscount == '' || goodscount == 'undefined')
                    goodscount = goodscount_old;
                if (ischecked == null || ischecked == '' || ischecked == 'undefined')
                    ischecked = ischecked_old;
                dic.push({ cartID: goodsid + '_' + ispack, val: spuid + '_' + shopid + '_' + goodsid + '_' + goodscount + '_' + ispack + '_' + ischecked });
            }
            SetCookie("CartStr", JSON.stringify(dic));
            if (callback) {
                callback();
            }
        }
    },
    //更新回调
    updatecallback: function () {
        //刷新数据列表
        Cart.getlist(Cart.getlistcallback);
    },
    //更新店铺所有商品
    updateshopchecked: function (callback, shopids, ischecked) {
        if (user.IsLogin()) {
            $.ajax(({
                type: "post",
                url: "/ShopCart/B_UpdateShopChecked",
                dataType: "json",
                data: { shopids: shopids, ischecked: ischecked },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error: function (e) {
                }
            }));
        } else {
            var shopidarry = shopids.split(',');
            if (shopidarry.length > 0) {
                for (var i = 0; i < shopidarry.length; i++) {
                    var shopidstr = shopidarry[i];
                    var strCookie = GetCookie("CartStr");
                    var dic = [];
                    if (strCookie != null)
                        dic = JSON.parse(strCookie);
                    var queryResult = Enumerable.From(dic)
                                     .Where(function (x) {
                                         return x.val.split('_')[1] == shopidstr;
                                     })
                                     .ToArray();
                    if (queryResult != null && queryResult.length > 0) {
                        for (var j = 0; j < queryResult.length; j++) {
                            var val = queryResult[j];
                            dic.remove(val);
                            var valarr = val.val.split('_')

                            var spuid = parseInt(valarr[0]);
                            var shopid = parseInt(valarr[1]);
                            var goodsid = parseInt(valarr[2]);
                            var goodscount = parseInt(valarr[3]);
                            var ispack = parseInt(valarr[4]);
                            var ischecked_old = parseInt(valarr[5]);
                            if (ischecked == null || ischecked == '' || ischecked == 'undefined')
                                ischecked = ischecked_old;
                            dic.push({ cartID: goodsid + '_' + ispack, val: spuid + '_' + shopid + '_' + goodsid + '_' + goodscount + '_' + ispack + '_' + ischecked });
                        }
                    }
                    SetCookie("CartStr", JSON.stringify(dic));
                }
            }
            if (callback) {
                callback();
            }
        }
    },


    //删除 callback:回调函数 goodsid:组合商品ID或商品SKUID  ispack:是否组合商品（组合商品为1 非组合商品未0）
    del: function (callback, goodsid, ispack) {
        if (user.IsLogin()) {
            $.ajax(({
                type: "post",
                url: "/ShopCart/B_DelCart",
                dataType: "json",
                data: { goodsid: goodsid, ispack: ispack },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error: function (e) {
                }
            }));
        } else {
            var cartid = goodsid + '_' + ispack;
            var strCookie = GetCookie("CartStr");
            var dic = [];
            if (strCookie != null)
                dic = JSON.parse(strCookie);
            var queryResult = Enumerable.From(dic)
               .Where(function (x) {
                   return x.cartID == cartid;
               })
               .ToArray();
            if (queryResult == null || queryResult.length == 0) {
                //alert("cookie中没有商品信息");
            }
            else {
                var val = queryResult[0];
                dic.remove(val);
            }
            SetCookie("CartStr", JSON.stringify(dic));
            if (callback) {
                callback();
            }
        }
    },
    //删除回调
    delcallback: function () {
        //刷新数据列表
        Cart.getlist(Cart.getlistcallback);
    },
    //组合商品单个商品删除（其它未删除商品保存）packid:组合商品ID skuid:组合商品删除的skuid
    delpacksingle: function (callback, packid, delskuid) {
        if (user.IsLogin()) {
            $.ajax(({
                type: "post",
                url: "/ShopCart/B_DelPackSingle",
                dataType: "json",
                data: { packid: packid, delskuid: delskuid },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error: function (e) {
                }
            }));
        } else {
            var cartid = packid + '_1';
            var strCookie = GetCookie("CartStr");
            var dic = [];
            if (strCookie != null)
                dic = JSON.parse(strCookie);
            var queryResult = Enumerable.From(dic)
               .Where(function (x) {
                   return x.cartID == cartid;
               })
               .ToArray();
            if (queryResult != null && queryResult.length > 0) {
                var val = queryResult[0];
                dic.remove(val);
                var valarr = val.val.split('_')
                var spuid = '';
                var shopid = parseInt(valarr[1]);
                var goodsid = '';
                var goodscount = parseInt(valarr[3]);
                var ispack = '0';
                var ischecked = parseInt(valarr[5]);
                $.ajax(({
                    type: "post",
                    url: "/ShopCart/W_GetPackInfo",
                    dataType: "json",
                    async: false,
                    data: { packid: packid, delskuid: delskuid },
                    success: function (rsl) {
                        if (rsl.Code == 0) {
                            for (var i = 0; i < rsl.Data.length; i++) {
                                goodsid = rsl.Data[i].skuid;
                                spuid = rsl.Data[i].spuid;

                                var qResult = Enumerable.From(dic)
                                                 .Where(function (x) {
                                                     return x.cartID == goodsid + '_0';
                                                 })
                                                 .ToArray();
                                if (qResult == null || qResult.length == 0) {
                                    dic.push({ cartID: goodsid + '_' + ispack, val: spuid + '_' + shopid + '_' + goodsid + '_' + goodscount + '_' + ispack + '_' + ischecked });
                                }
                                else {
                                    var valselect = qResult[0];
                                    dic.remove(valselect);
                                    var valselectarr = valselect.val.split('_')
                                    dic.push({ cartID: goodsid + '_' + ispack, val: spuid + '_' + shopid + '_' + goodsid + '_' + (parseInt(valselectarr[3]) + parseInt(goodscount)) + '_' + ispack + '_' + ischecked });
                                }
                            }
                        }
                    },
                    error: function (e) {
                    }
                }));
            }
            SetCookie("CartStr", JSON.stringify(dic));
            if (callback) {
                callback();
            }
        }
    },
    //批量删除 callback:回调函数  delstr: 删除字符（商品ID_是否组合商品|...)
    delbatch: function (callback, delstr) {
        if (user.IsLogin()) {
            $.ajax(({
                type: "post",
                url: "/ShopCart/B_BatchDelCart",
                dataType: "json",
                data: { delstr: delstr },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error: function (e) {
                }
            }));
        } else {
            var dels = delstr.split('|');
            for (var i = 0; i < dels.length; i++) {
                var cartid = dels[i];
                var strCookie = GetCookie("CartStr");
                var dic = [];
                if (strCookie != null)
                    dic = JSON.parse(strCookie);
                var queryResult = Enumerable.From(dic)
                   .Where(function (x) {
                       return x.cartID == cartid;
                   })
                   .ToArray();
                if (queryResult == null || queryResult.length == 0) {
                    //alert("cookie中没有商品信息");
                }
                else {
                    var val = queryResult[0];
                    dic.remove(val);
                }
                SetCookie("CartStr", JSON.stringify(dic));
            }
            if (callback) {
                callback();
            }
        }
    },

    //关注
    addconcern: function (goodsid) {
        if (user.IsLogin()) {
            $.ajax(({
                type: "post",
                url: "/ShopCart/B_AddConcern",
                dataType: "json",
                data: { goodsid: goodsid },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        alert("关注成功");
                    }
                    else {
                        alert(rsl.Desc);
                    }
                },
                error: function (e) {
                }
            }));
        } else {
            $(".div_login").show();
            return;
        }
    },
    //批量关注
    addbatchconcern: function (goodsids) {
        if (user.IsLogin()) {
            $.ajax(({
                type: "post",
                url: "/ShopCart/B_AddBatchConcern",
                dataType: "json",
                data: { goodsids: goodsids },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        alert("关注成功");
                    }
                    else {
                        alert(rsl.Desc);
                    }
                },
                error: function (e) {
                }
            }));
        } else {
            $(".div_login").show();
            return;
        }
    },

    //Cookie数据同步到用户数据库购物车
    cookietodb: function () {
        var strCookie = GetCookie("CartStr");
        if (strCookie == null) {
            //TODO 购物车为空 未处理
            return;
        }
        var dic = [];
        if (strCookie != null)
            dic = JSON.parse(strCookie);
        var vals = [];
        for (var i = 0; i < dic.length; i++) {
            var val = dic[i].val;
            while (val.indexOf("_") != -1) {
                val = val.replace('_', ',');
            }
            vals.push(val);
        }
        $.ajax(({
            type: "post",
            url: "/ShopCart/B_AddCartList",
            async: false,
            dataType: "json",
            data: { StrCookie: vals.join('|') },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    //清除Cookie中的购物车数据
                    DelCookie("CartStr");
                }
            },
            error: function (e) {
            }
        }));
    },

    //产品页面跳转
    goods_href: function () {
        var skuid = parseInt($(this).attr("skuid-val"));
        $.ajax(({
            type: "post",
            url: "/Order_Member/B_GetSpuID",
            dataType: "json",
            data: { SkuID: skuid },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var spuid = parseInt(rsl.Data);
                    if (spuid > 0) {
                        window.location.href = "/Web/Goods/pro_detail?sid=" + spuid;
                    }
                }
                else {
                    Dalert(rsl.Desc);
                }
            },
            error: function (e) {

            }
        }));
    }
}

function NulltoStr(str) {
    if (str == null)
        return "";
    return str;

}