//订单操作 发货 收货 取消退换货申请审核 删除

//返回绑定
var d = []; var sUrl;
var reUrl = {
    load: function (sType) {
        sUrl = sType;
    }
}
var order = {
    //发货
    fh: function (id, ss) {
        //发货
    	 d = dialog({
             title: "订单发货",
             url: "wlcompany?id=" + id ,
             width: 400,
             //modal: true, //蒙层
         });
        d.show();
    },
    afterfh: function (id, status) {
        //发货
        d = dialog({
            title: '售后发货',
            content: "<span>物流单号：</span><input type='text' name='logcode' style='width:220px;' /><p style='height: 10px;'></p><span>物流公司：</span><input type='text' name='logname' style='width:220px;' />",
            width: 300,
            height: 100,
            okValue: '确认',
            ok: function () {
                $.ajax({
                    url: "/platform/order/sendProForAfter",
                    type: "Post",
                    data: { "orderdetailid": id, "logisticscode": $("input[name=logcode]").val(), "logisticsname": $("input[name=logname]").val(), "status": status },
                    dataType: "json",
                    success: function (data) {
                        if (data.code < 0) {
                            Dalert(data.desc);
                        } else {
                            Dalert(data.desc);
                            order.backPage();
                            closeDialog();
                        }
                    },
                    error: function () {

                    }
                });
                return false;
            },
            cancelValue: '取消',
            cancel: function () { }
            //modal: true, //蒙层
        });
        d.show();
    },

    //收货
    sh: function (id, ss) {
        if (confirm("确认要执行此操作吗？")) {
            $.ajax({
                url: "/platform/order/afterReceivePro",
                type: "Post",
                data: { "id": id, "status": ss },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        Dalert(data.desc);
                        order.backPage();
                    }
                },
                error: function () {

                }
            });
        }
    },
    //取消确认
    qxqr: function (id, Reason,status) {

        var reason = Reason;
        if (reason == "undefined") { reason = ""; }
        var contentHTML = "";
        contentHTML += "<div class='l_qxyytop'>买家取消原因：</div>";
        contentHTML += "<div class='l_mjthyy_big'>" + reason + "</div>";
        contentHTML += "<div class='l_qxyytop' style='margin-top:15px;'>卖家确认原因：</div>";
        contentHTML += "<div><textarea name='reason' cols='' rows='6' class='l_ckxqarea_big'></textarea></div>";

        d = dialog({
            title: '订单取消确认',
            content: contentHTML,
            width: 500,
            height: 180,
            button: [
        {
            value: '同意',
            callback: function () {
                $.ajax({
                    url: "/platform/order/confirmCancelOrder",
                    type: "Post",
                    data: { "orderid": id, "isagree": 1, "status":status,"reason": $("textarea[name=reason]").val() },
                    dataType: "json",
                    success: function (data) {
                        if (data.code < 0) {
                            Dalert(data.desc);
                        } else {
                            Dalert(data.desc);
                            order.backPage();
                            closeDialog();
                        }
                    },
                    error: function () {

                    }
                });
            },
            autofocus: true
        },
        {
            value: '不同意',
            callback: function () {
                if ($("textarea[name=reason]").val() == "" || $("textarea[name=reason]").val() == undefined) {
                    Dalert("请填写买家不能取消订单的原因，谢谢！");
                    return false;
                }
                else {
                    $.ajax({
                        url: "/platform/order/confirmCancelOrder",
                        type: "Post",
                        data: { "orderid": id, "isagree": 0,"status":status,"reason": $("textarea[name=reason]").val() },
                        dataType: "json",
                        success: function (data) {
                            if (data.code < 0) {
                                Dalert(data.desc);
                            } else {
                                Dalert(data.desc);
                                order.backPage();
                            }
                        },
                        error: function () {

                        }
                    });
                }

            }
        },
        {
            value: '关闭'
        }
            ]
            //modal: true, //蒙层
        });
        d.show();
    },
    //退换货审核
    thhsh: function (id, ss, flag) {

        //var thh = "退货";
        //if (flag == 1) { thh = "换货"; }
        var thh;
        switch (flag) {
            case 1: thh = "退货退款"; break;
            case 2: thh = "退款"; break;
            case 3: thh = "换货"; break;
            case 4: thh = "维修"; break;
            default: break;
        }
        d = dialog({
            title: "订单" + thh + "审核",
            url: "ddgl_thh?id=" + id + "&ss=" + ss + "&aftertype=" + flag,
            width: 600,
            //modal: true, //蒙层
        });
        d.show();
    },
    //售后订单处理
    shddcl: function (id, ss, flag) {
        var thh;
        switch (flag) {
            case 1: thh = "退货退款"; break;
            case 2: thh = "退款"; break;
            case 3: thh = "换货"; break;
            case 4: thh = "维修"; break;
            default: break;
        }
        d = dialog({
            title: "订单" + thh + "处理",
            id:"jfcl",
            url: "ddgl_shcl?id=" + id + "&ss=" + ss + "&aftertype=" + flag,
            width: 600,
            //modal: true, //蒙层
        });
        d.show();
    },
    //售后处理详情
    ddshclxq: function (id, ss, flag) {
        d = dialog({
            title: "订单售后处理详情",
            id:"ddjfcl",
            url: "ddgl_shxq?id=" + id ,
            width: 600,
            //modal: true, //蒙层
        });
        d.show();
    },
    //订单删除
    del: function (id) {
        if (confirm('确定将此记录删除?')) {
            $.ajax({
                url: "/platform/order/delOrder",
                type: "Post",
                data: { "orderid": id },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        Dalert(data.desc);
                        order.backPage();
                    }
                },
                error: function () {

                }
            });
        }
    },
    zhuddtk : function(groupnum){
    	if(confirm("确定要执行该操作吗?")){
    		$.ajax({
    			url: "/platform/order/updateOrderTK",
    			type: "Post",
    			data: { "groupnum" : groupnum },
    			dataType: "json",
    			success: function (data) {
    				if (data.code < 0) {
    					Dalert(data.desc);
    				} else {
    					Dalert(data.desc,"",function(){location.reload();});
    				}
    			}
    		});
    	}
    },
    ziddtk : function(orderdetailid){
    	if(confirm("确定要执行该操作吗?")){
    		$.ajax({
    			url: "/platform/order/updatePartTK",
    			type: "Post",
    			data: { "orderdetailid" : orderdetailid },
    			dataType: "json",
    			success: function (data) {
    				if (data.code < 0) {
    					Dalert(data.desc);
    				} else {
    					Dalert(data.desc,"",function(){location.reload();});
    				}
    			}
    		});
    	}
    },
    /*//退换货退款
    thhtk: function (id, flag) {

        $.ajax({
            url: "/platform/order/confirmOrderApply",
            type: "Post",
            data: { "orderid": id, "isagree": 1, "type": flag },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    Dalert(data.desc);
                }
            }
        });
    },
    //取消驳回订单原因查看
    cancel: function (id) {
        d = dialog({
            title: "订单取消原因",
            url: "ddgl_bhcancel?id=" + id,
            width: 450,
            //modal: true, //蒙层
        });
        d.show();
    },
    //订单取消-平台操作
    platform_cancel: function (id, flag) {

        $.ajax({
            url: "/Order_Platform/CancelOrder",
            type: "Post",
            data: {
                "id": id, "isAgree": flag
            },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    Dalert(data.Desc);
                    order.backPage();
                }
            },
            error: function () {

            }
        });
    },
    //退货驳回订单原因查看
    platform_th: function (id, flag) {
        //退货-平台操作
        $.ajax({
            url: "/Order_Platform/THOrder",
            type: "Post",
            data: {
                "id": id, "isAgree": flag
            },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    Dalert(data.Desc);
                    order.backPage();
                }
            },
            error: function () {

            }
        });
    },
    //换货驳回订单原因查看
    platform_hh: function (id, flag) {

        $.ajax({
            url: "/Order_Platform/HHOrder",
            type: "Post",
            data: {
                "id": id, "isAgree": flag
            },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    Dalert(data.Desc, "", refresh);
                    order.backPage();
                }
            },
            error: function () {

            }
        });
    },
    //退换货审核
    bhthh: function (id, otag) {

        var thh = "退货";
        if (otag == 1) { thh = "换货"; }
        d = dialog({
            title: "订单" + thh + "原因",
            url: "ddgl_bhthh?id=" + id,
            width: 600,
            //modal: true, //蒙层
        });
        d.show();
    },*/
    //返回刷新
    backPage: function () {
        switch (sUrl) {
            case 1: list.getlist(1, 0); break;
            case 2: list.getlist(1, 1); break;
            case 3: listbh.getlist(1, 0); break;
            case 4: listbh.getlist(1, 1); break;
            default: break;
        }
    }
}
//关闭弹出框
function closeDialog() { d.close(); }
function refresh() {
    location.reload();
}

function ChangeStatus(ss, $obj) {
    var $prev = $obj.parent().prev();
    switch (parseInt(ss)) {
        case 1:
            $obj.html(""); //发货
            $obj.attr("data-status", false);
            $prev.find("span").first().html("待收货");
            break;
        case 5:
            $obj.html(""); //取消申请
            $obj.attr("data-status", false);
            $prev.find("span").first().html("已取消");
            break;
        case 12:
            $obj.html(""); //取消申请
            $obj.attr("data-status", false);
            $prev.find("span").first().html("取消驳回");
            break;
        case 7:
            $obj.html(""); //退货收货
            $obj.attr("data-status", false);
            $prev.find("span").first().html("已退货");
            break;
        case 11:
            $obj.html("换货待发货"); //换货收货
            $obj.attr("data-status", 13);
            $prev.find("span").first().html("换货待发货");
            break;
        case 13:
            $obj.html(""); //换货待收货
            $obj.attr("data-status", false);
            $prev.find("span").first().html("换货待收货");
            break;
    }
}