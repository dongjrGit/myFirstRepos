/**
 * 购物车信息
 */
var cart = {
	// 添加 callback:回调函数 shopid:店铺ID goodsid:组合商品ID或商品SKUID goodscount:商品数量
	// type:商品类型（0普通商品 1组合商品 2秒杀 3闪购）spikeid:闪购或秒杀ID(组合商品时为包含的商品种类数量)
	add : function(callback, shopid, goodsid, goodscount, type, spikeid) {
		if (WebLogin.isLogin()) {
			// 已登录
			$.ajax({
				type : "post",
				url : "/pc/cart/addCart",
				dataType : "json",
				data : {
					"sid" : shopid,
					"proid" : goodsid,
					"procount" : goodscount,
					"type" : type,
					"spikeid" : spikeid,
					"ch" : 1
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						if (callback) {
							callback(goodsid, type);
						}
					}
				},
				error : function(e) {
				}
			});
		} else {
			// 未登录 添加到cookie
			var strCookie = GetCookie("cartstr");
			var dic = [];
			if (strCookie != null)
				dic = JSON.parse(strCookie);
			var queryResult = Enumerable.From(dic).Where(function(x) {
				return x.proid == goodsid && x.type == type;
			}).ToArray();
			if (queryResult == null || queryResult.length == 0) {
				dic.push({
					sid : shopid,
					proid : goodsid,
					procount : goodscount,
					type : type,
					spikeid : spikeid,
					issel : "1"
				});
			} else {
				var val = queryResult[0];
				for (var i = 0; i < dic.length; i++) {
					if (dic[i].proid == val.proid) {
						dic.splice(i, 1);
						// 从下标为i的元素开始，连续删除1个元素
						break;
					}
				}
				var valarr = val.procount;
				dic.push({
					sid : shopid,
					proid : goodsid,
					procount : (parseInt(valarr) + parseInt(goodscount)),
					type : type,
					spikeid : spikeid,
					issel : "1"
				});
			}
			SetCookie("cartstr", JSON.stringify(dic));
			if (callback) {
				callback(goodsid, type);
			}
		}
	},
	// 添加回调 goodsid:组合商品ID或商品SKUID ispack:是否组合商品（组合商品为1 非组合商品未0）
	addcallback : function(goodsid,type) {
		// 返回url
		var backUrl = encodeURIComponent(window.location.href);
		self.location.href = "/web/addcartsuccess.html?cartid="+goodsid+"_"+type+"&backurl=" + backUrl;
	},
	// 立即购买 同add备注
	buynow : function(callback, shopid, goodsid, goodscount, type, spikeid) {
		if (WebLogin.isLogin()) {
			var strCookie = GetCookie("buynowstr");
			var dic = [];
			dic.push({
				sid : shopid,
				proid : goodsid,
				procount : goodscount,
				type : type,
				spikeid : spikeid,
				issel : "1"
			});
			SetCookie("buynowstr", JSON.stringify(dic));
			if (callback) {
				callback(goodsid, type);
			}
		}
	},
	// 立即购买回调
	buynowcallback : function(goodsid, type) {
		// to do
		var cartID = goodsid + '_' + type;
		self.location.href = "/web/orderbuynow.html?cartid=" + cartID;
	},
	// Cookie数据同步到用户数据库购物车
	cookietodb : function() {
		var strCookie = GetCookie("cartstr");
		if (strCookie == null) {
			// TODO 购物车为空 未处理
			return;
		}
		$.ajax({
			type : "post",
			url : "/pc/cart/addCarts",
			async : false,
			dataType : "json",
			data : {
				"cartstr" : strCookie,
				"ch" : 1
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					// 清除Cookie中的购物车数据
					DelCookie("cartstr");
				}
			},
			error : function(e) {
			}
		});
	},
	// 购物车列表
	getlist : function(callback) {
		var cartStr = "";
		if (!WebLogin.isLogin()) {
			var strCookie = GetCookie("cartstr");
			if (strCookie == null || strCookie=='[]'|| strCookie==undefined) {
				$(".l_nogwc").show();
				$("#div_cart").html("");
				return true;
			}
			$.ajax({
				type : "post",
				url : "/pc/cart/getCookieCarts",
				dataType : "json",
				data : {
					"cartstr" : strCookie,
					"ch" : "1"
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						var listdata = {
							cart : rsl.data
						}
						var html = template('cartlist', listdata);
						if (html == "") {
							$(".l_nogwc").show();
							$("#div_cart").html("");
						} else {
							$("#div_cart").html(html);
							cartinit();
						}
						
					}
				},
				error : function(e) {
				}
			});
		} else {
			$.ajax({
				type : "post",
				url : "/pc/cart/getCartList",
				dataType : "json",
				data : {
					"ch" : "1"
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						var listdata = {
								cart : rsl.data
						}
						var html = template('cartlist', listdata);
						$("#div_cart").html(html);
						cartinit();
					}
				},
				error : function(e) {
				}
			});
		}
	},
	/*
	 * // 购物车列表回调 getlistcallback : function() { cartinit(); },
	 */
	changgecount : function(id, count, goodsid, type) {
		if (!WebLogin.isLogin()) {
			var strCookie = GetCookie("cartstr");
			var dic = [];
			if (strCookie != null)
				dic = JSON.parse(strCookie);
			var queryResult = Enumerable.From(dic).Where(function(x) {
				return x.proid == goodsid && x.type == type;
			}).ToArray();
			if (queryResult == null || queryResult.length == 0) {
				//alert("cookie中没有商品信息");
			} else {
				var val = queryResult[0];
				for (var i = 0; i < dic.length; i++) {
					if (dic[i].proid == val.proid) {
						dic.splice(i, 1);
						// 从下标为i的元素开始，连续删除1个元素
						break;
					}
				}
				if (count > 0) {
					dic.push({
						sid : val.sid,
						proid : goodsid,
						procount : count,
						type : val.type,
						spikeid : val.spikeid,
						issel : "1"
					});
				}
			}
			SetCookie("cartstr", JSON.stringify(dic));
			cart.getlist(cart.getlistcallback);
		} else {
			$.ajax({
				type : "post",
				url : "/pc/cart/changeCount",
				dataType : "json",
				data : {
					"id" : id,
					"count" : count,
					"ch" : "1"
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						cart.getlist(cart.getlistcallback);
					}
				},
				error : function(e) {
				}
			});
		}

	},
	changesel : function(id, sel, goodsid, type) {
		if (!WebLogin.isLogin()) {
			var strCookie = GetCookie("cartstr");
			var dic = [];
			if (strCookie != null)
				dic = JSON.parse(strCookie);
			var queryResult = Enumerable.From(dic).Where(function(x) {
				return x.proid == goodsid && x.type == type;
			}).ToArray();
			if (queryResult == null || queryResult.length == 0) {
//				alert("cookie中没有商品信息");
			} else {
				var val = queryResult[0];
				for (var i = 0; i < dic.length; i++) {
					if (dic[i].proid == val.proid) {
						dic.splice(i, 1);
						// 从下标为i的元素开始，连续删除1个元素
						break;
					}
				}
				dic.push({
					sid : val.sid,
					proid : goodsid,
					procount : count,
					type : val.type,
					spikeid : val.spikeid,
					issel : sel
				});
			}
			SetCookie("cartstr", JSON.stringify(dic));
			cart.getlist(cart.getlistcallback);
		} else {
			$.ajax({
				type : "post",
				url : "/pc/cart/selCart",
				dataType : "json",
				data : {
					"id" : id,
					"sel" : sel,
					"ch" : "1"
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						cart.getlist(cart.getlistcallback);
					}
				},
				error : function(e) {
				}
			});
		}
	},
	updateshopchecked:function(shopids,sel){
		if (!WebLogin.isLogin()) {
			 var shopidarry = shopids.split(',');
	            if (shopidarry.length > 0) {
	                for (var i = 0; i < shopidarry.length; i++) {
	                    var shopidstr = shopidarry[i];
	                    var strCookie = GetCookie("cartstr");
	                    var dic = [];
	                    if (strCookie != null)
	                        dic = JSON.parse(strCookie);
	                    var queryResult = Enumerable.From(dic)
	                                     .Where(function (x) {
	                                         return x.sid == shopidstr;
	                                     })
	                                     .ToArray();
	                    if (queryResult == null || queryResult.length == 0) {
//	        				alert("cookie中没有商品信息");
	        			} else {
	        				var val = queryResult[0];
	        				for (var i = 0; i < dic.length; i++) {
	        					if (dic[i].proid == val.proid) {
	        						dic.splice(i, 1);
	        						// 从下标为i的元素开始，连续删除1个元素
	        						break;
	        					}
	        				}
	        				dic.push({
	        					sid : val.sid,
	        					procount : val.procount,
	        					proid:val.proid,
	        					type : val.type,
	        					spikeid : val.spikeid,
	        					issel : val.issel
	        				});
	        			}
	                    SetCookie("cartstr", JSON.stringify(dic));
	                    cart.getlist(cart.getlistcallback);
	                }
	            }
		} else {
			$.ajax({
				type : "post",
				url : "/pc/cart/selShopCart",
				dataType : "json",
				data : {
					"shopids" : shopids,
					"sel" : sel,
					"ch" : "1"
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						cart.getlist(cart.getlistcallback);
					}
				},
				error : function(e) {
				}
			});
		}
	},
	del:function(id,goodsid,type){
		if (!WebLogin.isLogin()) {
			var strCookie = GetCookie("cartstr");
			var dic = [];
			if (strCookie != null)
				dic = JSON.parse(strCookie);
			var queryResult = Enumerable.From(dic).Where(function(x) {
				return x.proid == goodsid && x.type == type;
			}).ToArray();
			if (queryResult == null || queryResult.length == 0) {
//				alert("cookie中没有商品信息");
			} else {
				var val = queryResult[0];
				for (var i = 0; i < dic.length; i++) {
					if (dic[i].proid == val.proid) {
						dic.splice(i, 1);
						// 从下标为i的元素开始，连续删除1个元素
						break;
					}
				}
				SetCookie("cartstr", JSON.stringify(dic));
				cart.getlist(cart.getlistcallback);
			}
		}else{
			$.ajax({
				type : "post",
				url : "/pc/cart/deleCartSpu",
				dataType : "json",
				data : {
					"id" : id,
					"ch" : "1"
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						cart.getlist(cart.getlistcallback);
					}
				},
				error : function(e) {
				}
			});
		}
	},
	dellist:function(idstr,cartids){
		if (!WebLogin.isLogin()) {
			var strCookie = GetCookie("cartstr");
			var dic = [];
			if (strCookie != null)
				dic = JSON.parse(strCookie);
			 for (var i = 0; i < cartids.length; i++) {
				 var queryResult = Enumerable.From(dic).Where(function(x) {
						return x.proid == cartids[i].id && x.type == cartids[i].type;
					}).ToArray();
				 if (queryResult == null || queryResult.length == 0) {
//						alert("cookie中没有商品信息");
					} else {
						var val = queryResult[0];
						for (var j = 0; j < dic.length; j++) {
							if (dic[j].proid == val.proid) {
								dic.splice(j, 1);
								// 从下标为i的元素开始，连续删除1个元素
								break;
							}
						}
						SetCookie("cartstr", JSON.stringify(dic));						
					}
			 }
			 cart.getlist(cart.getlistcallback);
		}else{
               $.ajax({
                   type : "post",
                   url : "/pc/cart/deleCartSpuList",
                   dataType : "json",
                   data : {
                   "ids" : idstr,
                   "ch" : "1"
                   },
                   success : function(rsl) {
                       if (rsl.code == 0) {
                    	   
                           cart.getlist(cart.getlistcallback);
                           location.reload(); 
                       }
                   },
                   error : function(e) {
                   }
               });
		}
	},
	 // 关注
    addconcern: function (goodsid) {
        if (WebLogin.isLogin()) {
            $.ajax(({
                type: "post",
                url: "/pc/products/collectSpu",
                dataType: "json",
                data: { "skuid": goodsid,"ch":1 },
                success: function (rsl) {
                    if (rsl.code == 0) {
                        alert("关注成功");
                    }
                    else {
                        alert(rsl.desc);
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
    // 批量关注
    addbatchconcern: function (goodsids) {
        if (WebLogin.isLogin()) {
            $.ajax(({
                type: "post",
                url: "/pc/products/collectMoreSpu",
                dataType: "json",
                data: { "skuids": goodsids,"ch":1 },
                success: function (rsl) {
                    if (rsl.code == 0) {
                        alert("关注成功");
                    }
                    else {
                        alert(rsl.desc);
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
    getprocount: function () {
    	var goodscount=0;
        if (WebLogin.isLogin()) {
            $.ajax(({
                type: "post",
                url: "/pc/cart/getCount",
                dataType: "json",
                data: {ch:1},
                success: function (rsl) {
                    if (rsl.code == 0) {
                    	 goodscount = parseInt(rsl.sum);
                    }
                    else {
                        //alert(rsl.desc);
                    }
                    $("#span_shoppingcartgoodscount").html(goodscount);
                    $(".div_shoppingcartgoodscountsum").html(goodscount);
                },
                error: function (e) {
                }
            }));
        } else {
        	var strCookie = GetCookie("cartstr");
        	var dic = [];
			if (strCookie != null)
				dic = JSON.parse(strCookie);
        	for (var i = 0; i < dic.length; i++) {
                var type = parseInt(dic[i].type);
                var count = parseInt(dic[i].procount);
                //组合商品 包含商品数量
                var spikeid = parseInt(dic[i].spikeid);
                if (type == 0) {
                    //非组合商品
                    goodscount += count;
                }
                else if (type == 1) {
                    goodscount += count * spikeid;
                }
            }
        	 $("#span_shoppingcartgoodscount").html(goodscount);
             $(".div_shoppingcartgoodscountsum").html(goodscount);
        }
       
    }
}

function cartinit() {
	
	// 优惠券相关
    // 显示和隐藏优惠券列表 未登录时弹出登录
    $(".span_couponlist").bind("click", function () {
        if (!WebLogin.isLogin()) {
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
    // 领取优惠券
    $(".a_getcoupon").bind("click", function () {
        var objt = $(this);
        var couponid = parseInt(objt.attr("couponid-val"));
        $.ajax(({
            type: "post",
            url: "/pc/coupon/takeCoupon",
            dataType: "json",
            data: { "couponid": couponid, "ch": 1 },
            success: function (rsl) {
                if (rsl.code == 0) {
                    objt.parent().addClass("ahs");
                    objt.parent().prev().prev().addClass("ahs");
                    objt.parent().text("已领取");
                }
            },
            error: function (e) {
            }
        }));
    })

	// 数量变更相关
	// 商品数量文本框得到、失去焦点事件
	var goodscountLog;
	$(".text_goodscount").focus(function() {
		goodscountLog = parseInt($(this).val());
	})
	$(".text_goodscount").blur(function() {
		if (isNaN($(this).val())) {
			alert("输入数量有误");
			$(this).val(goodscountLog);
			return;
		}
		var goodscountNew = parseInt($(this).val());
		var goodsstock = parseInt($(this).attr("stock-val"));
		if (goodscountNew > goodsstock) {
			alert("商品数量超现");
			$(this).val(goodscountLog);
			return;
		}
		if (goodscountNew < 1) {
			alert("商品数量必须大于0");
			$(this).val(goodscountLog);
			return;
		}
		// 变更数据库数量
		var goodsid = $(this).parent().attr('skuid-val');
		var scpid = $(this).parent().attr('shopcartproid');
		var skutype=$(this).parent().find('input').attr('skutype');
		cart.changgecount(scpid, goodscountNew,goodsid,skutype);
	})
	// 增减事件
	$(".span_down").bind("click", function() {
		var count = parseInt($(this).parent().find(".text_goodscount").val());
		if (count < 2) {
			return;
		}
		// 变更数据库或cookie数量
		var goodsid = $(this).parent().attr('skuid-val');
		var scpid = $(this).parent().attr('shopcartproid');
		var skutype=$(this).parent().find('input').attr('skutype');
		cart.changgecount(scpid, count - 1,goodsid,skutype);
	});
	$(".span_up").bind("click",function() {
				var count = parseInt($(this).parent().find(".text_goodscount")
						.val());
				var stock = parseInt($(this).parent()
						.find(".hidden_goodsstock").val());
				if (count > stock - 1) {
					return;
				}
				// 变更数据库或cookie数量
				var goodsid = $(this).parent().attr('skuid-val');
				var scpid = $(this).parent().attr('shopcartproid');
				var skutype=$(this).parent().find('input').attr('skutype');
				cart.changgecount(scpid, count + 1,goodsid,skutype);
			});
	// 组合商品数量文本框得到、失去焦点事件
	var packagecountLog;
	$(".text_packagecount").focus(function() {
		packagecountLog = parseInt($(this).val());
	})
	$(".text_packagecount").blur(function() {
		if (isNaN($(this).val())) {
			alert("输入数量有误");
			$(this).val(packagecountLog);
			return;
		}
		var packagecountNew = parseInt($(this).val());
		var packagestock = parseInt($(this).attr("stock-val"));
		if (packagecountNew > packagestock) {
			alert("商品数量超现");
			$(this).val(packagecountLog);
			return;
		}
		if (packagecountNew < 1) {
			alert("商品数量必须大于0");
			$(this).val(packagecountLog);
			return;
		}
		// 变更数据库数量
		var goodsid = $(this).parent().attr('skuid-val');
		var scpid = $(this).parent().attr('shopcartproid');
		cart.changgecount(scpid, packagecountNew,goodsid,1);
	})
	// 增减事件
	$(".span_packdown").bind(
			"click",
			function() {
				var count = parseInt($(this).parent()
						.find(".text_packagecount").val());
				if (count < 2) {
					return;
				}
				// 变更数据库或cookie数量
				var goodsid = $(this).parent().attr('skuid-val');
				var scpid = $(this).parent().attr('shopcartproid');				
				cart.changgecount(scpid, count - 1,goodsid,1);
			});
	$(".span_packup").bind(
			"click",
			function() {
				var count = parseInt($(this).parent()
						.find(".text_packagecount").val());
				var stock = parseInt($(this).parent()
						.find(".text_packagecount").attr("stock-val"));
				if (count > stock - 1) {
					return;
				}
				// 变更数据库或cookie数量
				var goodsid = $(this).parent().attr('skuid-val');
				var scpid = $(this).parent().attr('shopcartproid');
				cart.changgecount(scpid,count + 1,goodsid,1);
			});

	// 是否选中相关
	// 商品选择
	$(".checkbox_goods").change(function() {
		var goodsid = $(this).val();
		var scpid = $(this).attr("data-id");
		var ischecked = '';
		if ($(this).prop('checked')) {
			ischecked = '1';
		} else {
			ischecked = '0';
		}
		cart.changesel(scpid,ischecked,goodsid, 0);
	});
	$(".checkbox_package").change(function() {
		var goodsid = $(this).val();
		var scpid = $(this).attr("data-id");
		var ischecked = '';
		if ($(this).prop('checked')) {
			ischecked = '1';
		} else {
			ischecked = '0';
		}
		cart.changesel(scpid,ischecked, goodsid, 1);
	});
	// 店铺选择
	$(".checkbox_shopall").change(function() {
		var shopid = $(this).val();
		var ischecked = '';
		if ($(this).prop('checked')) {
			ischecked = '1';
		} else {
			ischecked = '0';
		}
		cart.updateshopchecked(shopid, ischecked);
	});
	// 购物车全选
	$(".checkbox_shoppingcartall").change(function() {
		var shopids = '';
		$("#div_cart").find(".checkbox_shopall").each(function() {
			shopids += $(this).val() + ',';
		})
		if (shopids.length > 1)
			shopids = shopids.substring(0, shopids.length - 1).toString();
		var ischecked = '';
		if ($(this).prop('checked')) {
			ischecked = '1';
		} else {
			ischecked = '0';
		}
		cart.updateshopchecked(shopids, ischecked);
	});

	// 删除相关
	// 普通商品
	$(".a_delshoppingcartgoods").bind("click", function() {
		var goodsid = $(this).attr('goodsid-val');
		var scpid = $(this).attr('shopcartproid');
		if (confirm("确认删除吗?")) {
			cart.del(scpid, goodsid, 0);
		}
	});
	// 组合商品
	$(".a_delpackagegoods").bind("click", function() {
		var goodsid = $(this).attr('goodsid-val');
		var scpid = $(this).attr('shopcartproid');
		if (confirm("确认删除吗?")) {
			cart.del(scpid, goodsid, 1);
		}
	});
// // 组合商品中单个商品
// $(".a_delpackagesingle").bind("click", function() {
// var packid = $(this).attr('packid-val');
// var delskuid = $(this).attr('skuid-val');
// if (confirm("确认删除吗?")) {
// Cart.delpacksingle(Cart.delcallback, packid, delskuid);
// }
// });
	// 删除选中的商品
	$(".a_delselectedgoods").bind("click",function() {
				var delstr = [];
				var idstr="";
				$(".checkbox_goods").each(function(e) {
							if ($(this).prop('checked')) {
								delstr.push({"id":parseInt($(this).parents(".div_sku")
										.find(".a_delshoppingcartgoods").attr(
										'goodsid-val')),"type":0});
								idstr+=parseInt($(this).parents(".div_sku")
										.find(".a_delshoppingcartgoods").attr(
										'shopcartproid'))+",";
							}
						})
				$(".checkbox_package").each(function(e) {
							if ($(this).prop('checked')) {
								delstr.push({"id":parseInt($(this).parents(
										".div_packages").find(
										".a_delpackagegoods").attr(
										'goodsid-val')),"type":1});
								idstr+=parseInt($(this).parents(".div_packages")
										.find(".a_delpackagegoods").attr(
										'shopcartproid'))+",";
							}
						})
				if (idstr.length > 1) {
					idstr = idstr.substring(0, idstr.length - 1).toString();
				}
				if (idstr != null && idstr != "") {
					if (confirm("确认删除吗?")) {
						cart.dellist(idstr, delstr);
					}
				}

			});

	// 关注相关
	$(".a_concernshoppingcartgoods").bind("click", function() {
		var goodsid = $(this).attr('goodsid-val');
		cart.addconcern(goodsid);
	});
	// 关注选中的商品
	$(".a_concernselectedgoods").bind(
			"click",
			function() {
				var goodsids = "";
				$(".checkbox_goods").each(
						function(e) {
							if ($(this).prop('checked')) {
								goodsids += parseInt($(this)
										.parents(".div_sku").find(
												".a_concernshoppingcartgoods")
										.attr('goodsid-val'))
										+ ",";
							}
						})
				$(".checkbox_package").each(
						function(e) {
							if ($(this).prop('checked')) {
								$(this).parents(".div_packages").find(
										".a_concernshoppingcartgoods").each(
										function() {
											goodsids += parseInt($(this).attr(
													'goodsid-val'))
													+ ",";
										})
							}
						})
				if (goodsids.length > 1) {
					goodsids = goodsids.substring(0, goodsids.length - 1)
							.toString();
				}
				if (goodsids != null && goodsids != "")
					cart.addbatchconcern(goodsids);
			});
	
	// 去结算
    $(".div_goclearing").bind("click", function () {
        if (!WebLogin.isLogin()) {
            showlogindiv();
            return;
        }

        var bool = false;
        $(".checkbox_goods").each(function (e) {
            if ($(this).prop('checked')) {
                bool = true;
                return;
            }
        })
        if (!bool) {
            $(".checkbox_package").each(function (e) {
                if ($(this).prop('checked')) {
                    bool = true;
                    return;
                }
            })
        }
        if (!bool) {
            alert("请选择至少一样商品");
            return;
        }
        // 新版结算
        var clearing_params=[];
        $("#ul_cart").find(".li_shop").each(function () {
            var isenter = false;
            $(this).find(".checkbox_goods").each(function () {
                if ($(this).prop('checked')) {
                    isenter = true;
                    return;
                }

            })
            if (!isenter) {
                $(this).find(".checkbox_package").each(function () {
                    if ($(this).prop('checked')) {
                        isenter = true;
                        return;
                    }
                })
            }
           
            if (isenter) {
            	// 组合商品信息
            	var pack_params=[];           	
            	$(this).find(".div_packages").each(function () {
            	    var nohaspk=$(this).find("input[name=nohaspk]").val();
            	    if(nohaspk){
            	        alert("库存不足");
            	        bool=false;
            	        return;
            	    }
            		var packsku_param=[];
                    if ($(this).find(".checkbox_package").prop('checked')) {
                    	// 组合商品 库存商品信息
                        $(this).find(".hidden_packskuinfo").each(function () {
                            var $this = $(this);
                            packsku_param.push({
                            	"id":$this.attr("skuid"),
                            	"name":$this.attr("skuname"),
                            	"price":$this.attr("skuprice"),
                            	"img":$this.attr("skuimg"),
                            	"count":$this.attr("skucount"),
                            	"stock":$this.attr("skustock"),
                            	"specsinfo":$this.attr("skuspecs"),
                            	"protype":1,
                            	"spikeid":0,
                            	"shopcartproid":0,
                            	"spuid":$this.attr("spuid")
                            })
                        });
                        
                        var packinfo = $(this).find(".hidden_packinfo");
                    	pack_params.push({
                    		"id":packinfo.attr("packid"),
                    		"name":packinfo.attr("packname"),
                    		"count":packinfo.attr("packcount"),
                    		"price":packinfo.attr("packprice"),
                    		"skuscd":packsku_param,
                    		"shopcartproid":packinfo.attr("packscpid")
                    	})
                    }
                })
                
            	// 普通商品信息
            	var sputotal_params=[];
            	 $(this).find(".div_spus").each(function () {
            		 var spusku_params=[];
            		 var spuact_params ={id:null,name:null,type:null,typename:null,desc:"",delmoney:null,skuscd:null,gifscd:null};
            		//SPU活动
                     var spuactinfo = $(this).find(".div_spuact").find(".hidden_spuact");
                     var isspuact = false;//是否有活动
                     if (spuactinfo.attr('actid') != null && spuactinfo.attr('actid') != 'undefined') {
                    	var isspuact = true;
                    	//活动类型
                     	var spuacttype=NulltoStr(spuactinfo.attr("acttype"));
                     	//活动赠品信息
                         var spuact_skuscd_params = [];//库存商品
                         var spuact_gifscd_params = [];//优惠券或积分
                         var ishavagif = false;//是否有赠品
                         //满赠活动处理
                         if (parseInt(spuacttype) == 1) {
                         	   $(this).find(".div_spuactgif ul li").each(function () {
                         		   //如果赠品是商品
                         		   if ($(this).is('.li_skugif')) {
                         			   var $objhidden = $(this).find('.hidden_skugifinfo');
                         			  spuact_skuscd_params.push({
                         				   "id":NulltoStr($objhidden.attr("skuid")),
                         				   "name":NulltoStr($objhidden.attr("skuname")),
                         				   "price":NulltoStr($objhidden.attr("skuprice")),
                         				   "img":NulltoStr($objhidden.attr("skuimg")),
                         				   "count":NulltoStr($objhidden.attr("skucount")),
                         				   "stock":NulltoStr($objhidden.attr("skustock")),
                         				   "specsinfo":NulltoStr($objhidden.attr("skuspecs")),
                         				   "protype":NulltoStr($objhidden.attr("skutype")),
                         				   "spikeid":0,
                         				   "shopcartproid":0
                         			   });
                         		   }
                         		   //如果赠品是优惠劵或者积分
                         		   if ($(this).is('.li_othergif')) {
                         			   var $objhidden = $(this).find('.hidden_othergifinfo');
                         			  spuact_gifscd_params.push({
                         				   "id":NulltoStr($objhidden.attr("otherid")),
                         				   "facevalue":NulltoStr($objhidden.attr("otherfacevalue")),
                         				   "type":NulltoStr($objhidden.attr("othertype")),
                         				   "count":NulltoStr($objhidden.attr("othercount"))
                         			   });
                         		   }
                         	   })
                         	   if(spuact_skuscd_params.length>0 || spuact_gifscd_params.length>0)
                         		   ishavagif = true;
                         }
                         //商品活动信息
                         spuact_params.id=NulltoStr(spuactinfo.attr("actid"));
                         spuact_params.name=NulltoStr(spuactinfo.attr("actname"));
                         spuact_params.type=NulltoStr(spuactinfo.attr("acttype"));
                         spuact_params.typename=NulltoStr(spuactinfo.attr("acttypename"));
                         spuact_params.delmoney=NulltoStr(spuactinfo.attr("actdelmoney"));
                         spuact_params.skuscd=spuact_skuscd_params;
                         spuact_params.gifscd=spuact_gifscd_params;
                         
                         if (parseInt(spuacttype) == 1 && ishavagif == false) //满赠活动赠品不存在时处理
                        	 spuact_params = null;
                     }
                     if (isspuact == false) spuact_params=null;
                     // SKU列表
                     $(this).find(".div_sku").each(function () {
                         var spuskuobj = $(this);
                         var spusku = spuskuobj.find(".hidden_spuskuinfo");
                         if (spuskuobj.find(".checkbox_goods").prop('checked')) {
                        	 spusku_params.push({
                        		"id":spusku.attr("skuid"),
                             	"name":spusku.attr("skuname"),
                             	"price":spusku.attr("skuprice"),
                             	"img":spusku.attr("skuimg"),
                             	"count":spusku.attr("skucount"),
                             	"stock":spusku.attr("skustock"),
                             	"specsinfo":spusku.attr("skuspecs"),
                             	"protype":spusku.attr("skutype"),
                             	"spikeid":spusku.attr("skuspikeid"),
                             	"shopcartproid":spusku.attr("skuscpid")
                        	 })
                         }
                     })
                     
                     var spuinfo = $(this).find(".hidden_spuinfo");
                     sputotal_params.push({
                    	 "id":spuinfo.attr("spuid"),
                    	 "totalmoney":spuinfo.attr("sputotalmoney"),
                    	 "delmoney":spuinfo.attr("spudelmoney"),
                    	 "skuscd":spusku_params,
                    	 "spuasd":spuact_params
                     })
                 })
            	// 店铺信息
                var shopinfo = $(this).find(".hidden_shop");   
                // 店铺活动信息
            	var shopactinfo = $(this).find(".hidden_shopact");
               
                
                //店铺活动
            	var shopact_params ={id:null,name:null,type:null,typename:null,desc:"",delmoney:null,skuscd:null,gifscd:null};
            	var isact = false;//是否有活动
                if (shopactinfo.attr("actid") != null && shopactinfo.attr("actid") != 'undefined') {
                	isact=true;
                	//活动类型
                	var shopacttype=NulltoStr(shopactinfo.attr("acttype"));
                	//活动赠品信息
                    var shopact_skuscd_params = [];//库存商品
                    var shopact_gifscd_params = [];//优惠券或积分
                    var ishavagif = false;//是否有赠品
                    //满赠活动处理
                    if (parseInt(shopacttype) == 1) {
                    	   $(this).find(".div_shopactgif ul li").each(function () {
                    		   //如果赠品是商品
                    		   if ($(this).is('.li_skugif')) {
                    			   var $objhidden = $(this).find('.hidden_skugifinfo');
                    			   shopact_skuscd_params.push({
                    				   "id":NulltoStr($objhidden.attr("skuid")),
                    				   "name":NulltoStr($objhidden.attr("skuname")),
                    				   "price":NulltoStr($objhidden.attr("skuprice")),
                    				   "img":NulltoStr($objhidden.attr("skuimg")),
                    				   "count":NulltoStr($objhidden.attr("skucount")),
                    				   "stock":NulltoStr($objhidden.attr("skustock")),
                    				   "specsinfo":NulltoStr($objhidden.attr("skuspecs")),
                    				   "protype":NulltoStr($objhidden.attr("skutype")),
                    				   "spikeid":0,
                    				   "shopcartproid":0
                    			   });
                    		   }
                    		   //如果赠品是优惠劵或者积分
                    		   if ($(this).is('.li_othergif')) {
                    			   var $objhidden = $(this).find('.hidden_othergifinfo');
                    			   shopact_gifscd_params.push({
                    				   "id":NulltoStr($objhidden.attr("otherid")),
                    				   "facevalue":NulltoStr($objhidden.attr("otherfacevalue")),
                    				   "type":NulltoStr($objhidden.attr("othertype")),
                    				   "count":NulltoStr($objhidden.attr("othercount"))
                    			   });
                    		   }
                    	   })
                    	   if(shopact_skuscd_params.length>0 || shopact_gifscd_params.length>0)
                    		   ishavagif = true;
                    }
                    
                    shopact_params.id=NulltoStr(shopactinfo.attr("actid"));
                    shopact_params.name=NulltoStr(shopactinfo.attr("actname"));
                    shopact_params.type=NulltoStr(shopactinfo.attr("acttype"));
                    shopact_params.typename=NulltoStr(shopactinfo.attr("acttypename"));
                    shopact_params.delmoney=NulltoStr(shopactinfo.attr("actdelmoney"));
                    shopact_params.skuscd=shopact_skuscd_params;
                    shopact_params.gifscd=shopact_gifscd_params;
                    if (parseInt(shopacttype) == 1 && ishavagif == false) //满赠活动赠品不存在时处理
                        shopact_params = null;
                }
                if(isact == false)shopact_params = null;
                clearing_params.push({
           	     // 店铺ID
           		"shopid":shopinfo.attr('shopid'),     
           		// 店铺名称
           		"shopname":shopinfo.attr('shopname'),
           		// 店铺总金额
           		"totalmoney":shopinfo.attr('shoptotalmoney'),
           		// 店铺减免金额
           		"delmoney":shopinfo.attr('shopdelmoney'),
           		"shopasd":shopact_params,
           		"packscd":pack_params,
           		"spuscd":sputotal_params
               });
            }
        });
        if(bool){
        // 要结算的购物车信息
//        var cart_params = [];
        var cart_paramse={delmoney:null,totalmoney:null,totalcount:null,paymoney:null,shops:null};
        cart_paramse.delmoney=$("#hidden_cartinfo").attr('delmoney');
        cart_paramse.totalmoney=$("#hidden_cartinfo").attr('totalmoney')
        cart_paramse.totalcount=$("#hidden_cartinfo").attr('selectedcount')
        cart_paramse.paymoney=$("#hidden_cartinfo").attr('paymoney')
        cart_paramse.shops=clearing_params;

        var param = JSON.stringify(cart_paramse);
        $("#cart_paramstr").val(param);
        $("#suborder").submit();
        }
    });
}
function NulltoStr(str) {
    if (str == null)
        return "";
    return str;

}
