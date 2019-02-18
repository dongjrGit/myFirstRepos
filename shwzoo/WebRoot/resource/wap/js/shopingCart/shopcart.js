var islogin=$("#h_islogin").val();
//购物车
var Cart = {
    //添加 callback:回调函数 shopid:店铺ID, goodsid:组合商品ID或商品SKUID, spuid:spuid, goodscount:商品数量  type:商品类型（0普通商品 1组合商品 2秒杀 3闪购）  ischecked:是否选中(选中为1 未选中为0)  spikeid:闪购或秒杀ID
    add : function(shopid, goodsid, spuid, goodscount, type, spikeid, ischecked, callback) {
        if (!login.isLogin()) {
            //未登录 添加到cookie
            var strCookie = GetCookie("cartstr");
            var dic = [];
            if (strCookie != null) {
                dic = JSON.parse(strCookie);
            }
            var queryResult = Enumerable.From(dic).Where(function(x) {
                return x.proid == (goodsid + "_" + spuid);
            }).ToArray();
            if (queryResult == null || queryResult.length == 0) {
                dic.push({
                    sid : shopid,
                    proid : goodsid + "_" + spuid,
                    procount : goodscount,
                    type : type,
                    spikeid : spikeid,
                    issel : ischecked
                });
            } else {
                var val = queryResult[0];
                for (var i = 0; i < dic.length; i++) {
                    if (dic[i].proid == val.proid) {
                        dic.splice(i, 1);
                        //从下标为i的元素开始，连续删除1个元素
                        break;
                    }
                }

                var valarr = val.procount;
                dic.push({
                    sid : shopid,
                    proid : goodsid + "_" + spuid,
                    procount : (parseInt(valarr) + parseInt(goodscount)),
                    type : type,
                    spikeid : spikeid,
                    issel : ischecked
                });
            }
            SetCookie("cartstr", JSON.stringify(dic));
            if (callback) {
                callback(goodscount);
            }

        } else {
            //已登录
            $.ajax({
                type : "post",
                url : "/api/wap/shopcart/addshopCart",
                dataType : "json",
                data : {
                    sid : shopid,
                    proid : goodsid,
                    procount : goodscount,
                    type : type,
                    spikeid : spikeid,
                    ch : "3"
                },
                success : function(rsl) {
                    if (rsl.code == 0) {
                        if (callback) {
                            callback(goodscount);
                        }
                    }
                },
                error : function(e) {
                }
            });
        }
    },
    //
    addcallback : function(gcount) {
        Cart.getgoodscount(Cart.getcountcallback);
    },
    //Cookie数据同步到用户数据库购物车
    cookietodb : function(callback,value) {
        var strCookie = GetCookie("cartstr");
        if (strCookie == null) {
            //TODO 购物车为空 
            return;
        }
        var dic = [];
        if (strCookie != null) {
            dic = JSON.parse(strCookie);
            for (var i = 0; i < dic.length; i++) {
                var goodid = dic[i].proid.split('_')[0];
                dic[i].proid = parseInt(goodid);
            }
            $.ajax({
                type : "post",
                url : "/api/wap/shopcart/addcookieCarts",
                //async: false,
                dataType : "json",
                data : {
                    "ch" : "3",
                    "cartstr" : JSON.stringify(dic)
                },
                success : function(rsl) {
                    if (rsl.code == 0) {
                        
                        //清除Cookie中的购物车数据
                        DelCookie("cartstr");
                        if(callback){
                            callback(value);
                        }
                    }
                },
                error : function(e) {
                }
            });
        }
    },
    //获取购物车商品总数
    getgoodscount : function(callback) {
        var goodscount = 0;
        if (login.isLogin()) {
            //已登录
            $.ajax({
                type : "post",
                url : "/api/wap/shopcart/getCarGoodCount",
                dataType : "json",
                data : {
                    "ch" : "3"
                },
                //async : false,
                success : function(rsl) {
                    if (rsl.code == 0) {
                        var jsonData = rsl.sum;
                        goodscount = parseInt(jsonData);
                        if (callback) {
                            callback(goodscount);
                        }
                    }
                },
                error : function(e) {
                }
            });
        } else {
            //未登录
            var strCookie = GetCookie("cartstr");
            if (strCookie != null) {
                var dic = [];
                dic = JSON.parse(strCookie);
                for (var i = 0; i < dic.length; i++) {
                    var count = parseInt(dic[i].procount);
                    var spuid = parseInt(dic[i].proid.split("_")[1]);
                    goodscount += count;
                }                
            }
            if (callback) {
                 callback(goodscount);
          }
        }

    },
    //获取购物车商品总数回调
    getcountcallback : function(goodscount) {
        if (goodscount > 0) {
            $("#g_count").addClass("fx-footer_num");
            $("#g_count").html(goodscount);
            fxFooter('.fx-footer_num');
        } else {
            $("#g_count").removeClass("fx-footer_num");
            $("#g_count").html("");
        }
    },
    //购物车列表
    getlist : function(callback) {
        var cartStr = "";
        if (!login.isLogin()) {
            //未登录 SpuId，ShopId，GoodsID GoodsCount CartType IsSelected ShopActId SpuActId SpikeID shopcartproid
            // sid : proid : goodsid + "_" + spuid,procount :type : spikeid :issel : 
            var strCookie = GetCookie("cartstr");
            
            if (strCookie == null) {
                $("#div_cart").html("<style>#div_cart{text-align: center;padding: 10% 0px;}#div_cart img{width: 1.5rem;margin-bottom: 3%;}</style><img src='/resource/wap/images/gwc.png'/><div class='huise'>购物车是空的噢</div>");
                if(callback){
                    callback();
                }
                return true;
            }
            var dic = [];
            if (strCookie != null) {
                dic = JSON.parse(strCookie);
            }
            var vals = [];
            for (var i = 0; i < dic.length; i++) {
                vals.push({
                    SpuId : parseInt(dic[i].proid.split('_')[1]),
                    ShopId : parseInt(dic[i].sid),
                    GoodsID : parseInt(dic[i].proid.split('_')[0]),
                    GoodsCount : parseInt(dic[i].procount),
                    CartType : parseInt(dic[i].type),
                    IsSelected : dic[i].issel == 0 ? false : true,
                    ShopActId : 0,
                    SpuActId : 0,
                    SpikeID : parseInt(dic[i].spikeid),
                    shopcartproid : 0
                })

            }

            var cars = JSON.stringify(vals);
            $.ajax({
                type : "post",
                url : "/api/wap/shopcart/getCookieCarts",
                dataType : "json",
                data : {
                    "cartstr" : cars,
                    "ch" : "3"
                },
                success : function(rsl) {
                    if (rsl.code == 0) {
                        var listdata = {
                            list : rsl.data
                        }
                        var html = template('cookiecartlist', listdata);
                        if (html == "") {
                            $("#div_cart").html("<style>#div_cart{text-align: center;padding: 10% 0px;}#div_cart img{width: 1.5rem;margin-bottom: 3%;}</style><img src='/resource/wap/images/gwc.png'/><div class='huise'>购物车是空的噢</div>");
                        } else {
                            $("#div_cart").html(html);
                        }

                        if (callback) {
                            callback();
                        }
                    }
                },
                error : function(e) {
                }
            });

        } else {
            $.ajax({
                type : "post",
                url : "/api/wap/shopcart/getCartList",
                dataType : "json",
                data : {
                    "ch" : "3"
                },
                success : function(rsl) {
                    if (rsl.code == 0) {
                        var listdata = {
                            list : rsl.data
                        }
                        if (rsl.data.shops.length == 0) {
                            $("#div_cart").html("<style>#div_cart{text-align: center;padding: 10% 0px;}#div_cart img{width: 1.5rem;margin-bottom: 3%;}</style><img src='/resource/wap/images/gwc.png'/><div class='huise'>购物车是空的噢</div>");
                        } else {
                            var html = template('cookiecartlist', listdata);
                            $("#div_cart").html(html);
                        }

                        if (callback) {
                            callback();
                        }
                    }
                },
                error : function(e) {
                }
            });
        }
    },
    //购物车列表回调
    getlistcallback : function(data) {
        $(".shopchart_checkall").nextAll().remove();
        $(".fx-footer").nextAll().remove()
        fixedbot('.fx-footer', '.shopchart_checkall');
        fxFooter('.fx-footer_num');
        fixed('.fx-footer');
        
        //商品数量绑定
        $('.spinner').find("button").click(function() {
            var id = $(this).parent().find('input').attr("data");
            var count = $(this).parent().find('input').val();
            Cart.updatecount(id, count, Cart.updatacountcb);
        })
        //购物车数量显示
        var count = parseInt($("#h_carcount").val());
        var paymoney = Number($("#h_proallpay").val());
        var sele = $("#h_selected").val();
        Cart.getgoodscount(Cart.getcountcallback);
        
        if (!(isNaN(paymoney))) {
             $("#proallpay").html("￥ " + paymoney.toFixed(2));
         }else{
            $("#proallpay").html("￥  0");
        }
        if (sele == "true") {
            $("#car_checkeall").html('<input type="checkbox" name="" id="shopchart_checkall" checked="checked"  />');
            //是否全选
        } else {
            $("#car_checkeall").html('<input type="checkbox" name="" id="shopchart_checkall"/>');
            //是否全选
        }
    },
    //更新商品数量
    updatecount : function(id, count, cookiecarid, callback) {

        if (login.isLogin()) {
            $.ajax({
                type : "post",
                url : "/api/wap/shopcart/changeCount",
                dataType : "json",
                data : {
                    "id" : id,
                    "count" : count,
                    "ch" : "3"
                },
                success : function(rsl) {
                    if (rsl.code == 0) {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error : function(e) {
                }
            });
        } else {

            var strCookie = GetCookie("cartstr");
            var dic = [];
            if (strCookie != null) {
                dic = JSON.parse(strCookie);
            }
            var queryResult = Enumerable.From(dic).Where(function(x) {
                return x.proid == cookiecarid;
            }).ToArray();

            if (queryResult == null || queryResult.length == 0) {
                //alert("为获取商品信息");
            } else {
                var val = queryResult[0];
                for (var i = 0; i < dic.length; i++) {
                    if (dic[i].proid == val.proid) {
                        dic.splice(i, 1);
                        //从下标为i的元素开始，连续删除1个元素
                        break;
                    }
                }

                var valarr = val.procount;
                dic.push({
                    sid : val.sid,
                    proid : val.proid,
                    procount : count,
                    type : val.type,
                    spikeid : val.spikeid,
                    issel : val.issel
                });

            }
            SetCookie("cartstr", JSON.stringify(dic));
            if (callback) {
                callback();
            }
        }
    },
    updatecountcb : function() {
        // var proallcount = 0;
        // $(".spinner").find("input").each(function(index) {
            // var proc = $(this).val();
            // proallcount = parseInt(proallcount) + parseInt(proc);
        // });
        // $("#g_count").html(proallcount);
        Cart.getlist(Cart.getlistcallback);
    },
    //更新商品勾选
    updateSelect : function(id, isSelect, cookiecarid, callback) {
        if (login.isLogin()) {//已登录
            $.ajax({
                type : "post",
                url : "/api/wap/shopcart/selectCart",
                dataType : "json",
                data : {
                    "id" : id,
                    "sel" : isSelect,
                    "ch" : "3"
                },
                success : function(rsl) {
                    if (rsl.code == 0) {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error : function(e) {
                }
            });
        } else {

            var strCookie = GetCookie("cartstr");
            var dic = [];
            if (strCookie != null) {
                dic = JSON.parse(strCookie);
            }

            var queryResult = Enumerable.From(dic).Where(function(x) {
                return x.proid == cookiecarid;
            }).ToArray();

            if (queryResult == null || queryResult.length == 0) {
                //alert("为获取商品信息");
            } else {
                var val = queryResult[0];
                for (var i = 0; i < dic.length; i++) {
                    if (dic[i].proid == val.proid) {
                        dic.splice(i, 1);
                        //从下标为i的元素开始，连续删除1个元素
                        break;
                    }
                }
                if (isSelect == 0) {
                    isSelect = false;
                } else {
                    isSelect = true;
                }
                var valarr = val.procount;
                dic.push({
                    sid : val.sid,
                    proid : val.proid,
                    procount : val.procount,
                    type : val.type,
                    spikeid : val.spikeid,
                    issel : isSelect
                });

            }
            SetCookie("cartstr", JSON.stringify(dic));

            if (callback) {
                callback();
            }
        }
    },
    //更新多个商品勾选
    updateSelectList : function(ids, isSelects, cookiecarid, callback) {
        if (login.isLogin()) {
            $.ajax({
                type : "post",
                url : "/api/wap/shopcart/selectCartList",
                dataType : "json",
                data : {
                    "ids" : ids,
                    "sels" : isSelects,
                    "ch" : "3"
                },
                success : function(rsl) {
                    if (rsl.code == 0) {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error : function(e) {
                }
            });
        } else {

            var strCookie = GetCookie("cartstr");
            var dic = [];
            if (strCookie != null) {
                dic = JSON.parse(strCookie);
            }
            if (cookiecarid.indexOf(',') > 0) {
                isSelects = isSelects.split(',');
                cookiecarid = cookiecarid.split(',');
                var sel = false;
                //批量处理选择时选择状态相同（这是点击店铺全选或者全选触发）
                if (isSelects[0] == "1") {
                    sel = true;
                }
                for (var k = 0; k < cookiecarid.length; k++) {

                    var queryResult = Enumerable.From(dic).Where(function(x) {
                        return x.proid == cookiecarid[k];
                    }).ToArray();

                    if (queryResult == null || queryResult.length == 0) {
                        return true;
                    } else {
                        var val = queryResult[0];
                        for (var i = 0; i < dic.length; i++) {
                            if (dic[i].proid == val.proid) {
                                dic.splice(i, 1);
                                //从下标为i的元素开始，连续删除1个元素
                                break;
                            }
                        }

                        var valarr = val.procount;
                        dic.push({
                            sid : val.sid,
                            proid : val.proid,
                            procount : val.procount,
                            type : val.type,
                            spikeid : val.spikeid,
                            issel : sel
                        });

                    }
                    SetCookie("cartstr", JSON.stringify(dic));
                }
            } else {
                Cart.updateSelect(ids, isSelects, cookiecarid, Cart.selectcallback);
            }
            if (callback) {
                callback();
            }
        }
    },
    selectcallback : function() {
        Cart.getlist(Cart.getlistcallback);
    },
    //删除 callback:回调函数 carproid:购物车子表id
    del : function(carproid, callback) {
        if (login.isLogin()) {
            $.ajax({
                type : "post",
                url : "/api/wap/shopcart/deleCartSpu",
                dataType : "json",
                data : {
                    "id" : carproid,
                    "ch" : "3"
                },
                success : function(rsl) {
                    if (rsl.code == 0) {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error : function(e) {
                }
            });
        } else {
            $("input[name=ck_sku]").each(function(index) {
                if ($(this).is(':checked')) {
                    var cartid = $(this).attr("data");
                    var strCookie = GetCookie("cartstr");
                    var dic = [];
                    if (strCookie != null) {
                        dic = JSON.parse(strCookie);
                    }
                    var queryResult = Enumerable.From(dic).Where(function(x) {
                        return x.proid == cartid;
                    }).ToArray();
                    if (queryResult == null || queryResult.length == 0) {
                        return true;
                    } else {
                        var val = queryResult[0];
                        for (var i = 0; i < dic.length; i++) {
                            if (dic[i].proid == val.proid) {
                                dic.splice(i, 1);
                                //从下标为i的元素开始，连续删除1个元素
                                break;
                            }
                        }
                        SetCookie("cartstr", JSON.stringify(dic));
                    }
                }
            });
            if (callback) {
                callback();
            }

        }
    },
    //删除回调
    delcartback : function() {
        //刷新数据列表
        Cart.getlist(Cart.getlistcallback);
    },
    //批量删除 callback:回调函数  ids: 购物车子表id集合（用,隔开）
    delbatch : function(ids, callback) {
        if (login.isLogin()) {
            $.ajax(( {
                type : "post",
                url : "/api/wap/shopcart/deleCartSpuList",
                dataType : "json",
                data : {
                    "ids" : ids,
                    "ch" : "3"
                },
                success : function(rsl) {
                    if (rsl.code == 0) {
                        if (callback) {
                            callback();
                        }
                    }
                },
                error : function(e) {
                }
            }));
        } else {
            $("input[name=ck_sku]").each(function(index) {
                if ($(this).is(':checked')) {
                    var cartid = $(this).attr("data");
                    var strCookie = GetCookie("cartstr");
                    var dic = [];
                    if (strCookie != null) {
                        dic = JSON.parse(strCookie);
                    }
                    var queryResult = Enumerable.From(dic).Where(function(x) {
                        return x.proid == cartid;
                    }).ToArray();
                    if (queryResult == null || queryResult.length == 0) {
                        return true;
                    } else {
                        var val = queryResult[0];
                        for (var i = 0; i < dic.length; i++) {
                            if (dic[i].proid == val.proid) {
                                dic.splice(i, 1);
                                //从下标为i的元素开始，连续删除1个元素
                                break;
                            }
                        }
                        SetCookie("cartstr", JSON.stringify(dic));
                    }
                }
            });
            if (callback) {
                callback();
            }
        }
    },
    //删除所有
    delAll : function(callback) {
        if (login.isLogin()) {
            $.ajax({
                type : "post",
                url : "/api/wap/shopcart/clearCart",
                dataType : "json",
                data : {
                    "ch" : "3"
                },
                success : function(rsl) {
                    if (rsl.code == 0) {                        
                        if (callback) {
                            callback();
                        }
                    }
                },
                error : function(e) {
                }
            });
        } else {
            //清除Cookie中的购物车数据
            DelCookie("cartstr");
            if (callback) {
                callback();
            }
        }
    },
}

function NulltoStr(str) {
    if (str == null)
        return "";
    return str;

}

$(function() {
    //全选勾选
    $("#car_checkeall").on("click", "input", function() {
        var ids = "", coids = "";
        var seles = "";
        if ($(this).is(':checked')) {
            $("input[type=checkbox]").attr("checked", "checked");
            $("input[name=ck_sku]").each(function(index) {
                ids = ids + $(this).val() + ",";
                coids = coids + $(this).attr("data") + ",";
                seles = seles + "1" + ",";
            });
        } else {
            $("input[type=checkbox]").removeAttr('checked');
            $("input[name=ck_sku]").each(function(index) {
                ids = ids + $(this).val() + ",";
                coids = coids + $(this).attr("data") + ",";
                seles = seles + "0" + ",";
            });
        }
        if (ids != "" && coids != "") {//更新商品选取状态
            ids = ids.substring(0, ids.length - 1);
            coids = coids.substring(0, coids.length - 1);
            seles = seles.substring(0, seles.length - 1);
            Cart.updateSelectList(ids, seles, coids, Cart.selectcallback);
        }
    });
    //店铺勾选
    $("#div_cart").on('click', "input[name=ck_shop]", function(event) {
        var allselect = true;
        var isseles = "0";
        var ids = "", coids = "";
        var seles = "";
        if ($(this).is(':checked')) {
            $(this).parent().parent().parent().find("input[name=ck_sku]").each(function(index) {
                $(this).attr("checked", "checked");
                ids = ids + $(this).val() + ",";
                coids = coids + $(this).attr("data") + ",";
                seles = seles + "1" + ",";
            });
        } else {
            $(this).parent().parent().parent().find("input[name=ck_sku]").each(function(index) {
                $(this).removeAttr('checked');
                ids = ids + $(this).val() + ",";
                coids = coids + $(this).attr("data") + ",";
                seles = seles + "0" + ",";
            });
            $("#shopchart_checkall").removeAttr('checked');
        }
        $("#div_cart").find("input[type=checkbox]").each(function() {
            if (!$(this).is(":checked")) {
                allselect = false;
            }
        })
        if (allselect) {
            $("#car_checkeall").html('<input type="checkbox" name="" id="shopchart_checkall" checked="checked"  />');
        }
        if (ids != "" && coids != "") {//更新商品选取状态
            ids = ids.substring(0, ids.length - 1);
            coids = coids.substring(0, coids.length - 1);
            seles = seles.substring(0, seles.length - 1);
            Cart.updateSelectList(ids, seles, coids, Cart.selectcallback);
        }
    });
    //商品勾选
    $("#div_cart").on("change", "input[name=ck_sku]", function() {
        var issele = 0;
        if (!$(this).is(":checked")) {
            $(this).parent().parent().parent().find("input[name=ck_shop]").removeAttr('checked');
            $("#shopchart_checkall").removeAttr('checked');
            issele = 0;
        } else {
            issele = 1;
            var allselect = true;
            var shopselect = true;
            //$(this).parent().find("input").attr("data");
            $(this).parent().parent().find("input[name=ck_sku]").each(function() {
                if (!$(this).is(":checked")) {
                    shopselect = false;
                }
            })
            if (shopselect) {
                $(this).parent().parent().parent().find("input[name=ck_shop]").attr('checked', 'checked');
            }
            $("#div_cart").find("input[type=checkbox]").each(function() {
                if (!$(this).is(":checked")) {
                    allselect = false;
                }
            })
            if (allselect) {
                $("#car_checkeall").html('<input type="checkbox" name="" id="shopchart_checkall" checked="checked"  />');
            }
        }
        //更新商品选取状态
        var id = $(this).val();
        var coid = $(this).attr("data");
        Cart.updateSelect(id, issele, coid, Cart.selectcallback);
    })
})
