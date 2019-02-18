//订单操作 发货 收货 取消退换货申请审核 删除
var d = [];
var order = {
    fh: function (id, ss,tag) {
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
                    url: "/seller/shoporder/sendProForAfter",
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
    sh: function (id,ss) {
        //收货
        var $obj=$(this);
        $.ajax({
            url: "/seller/shoporder/afterReceivePro",
            type: "Post",
            data: { "id": id ,"status": ss },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    Dalert(data.desc);
                    ChangeStatus(ss, $obj);
                }
            },
            error: function () {

            }
        });
    },
    qxqr: function (id,Reason,status) {
        //取消确认
        var $obj=$(this);
        var reason = Reason;
        if (reason == "undefined") { reason = ""; }

        //var contentHTML = "<div>";
        //contentHTML += "<span>买家取消原因：</span><input type='text' name='' style='width:320px;' value='" + reason + "' /><br/>";
        //contentHTML += "<span>卖家确认原因：</span><input type='text' name='reason' style='width:320px;' value='' />";
        //contentHTML += "</div>";
        var contentHTML = "";
        contentHTML += "<div class='l_qxyytop'>买家取消原因：</div>";
        contentHTML += "<div class='l_mjthyy_big'>" +reason + "</div>";
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
                    url: "/seller/shoporder/confirmCancelOrder",
                    type: "Post",
                    data: { "orderid": id, "isagree": 1, "status":status,"imgUrl":$("input[name=imgUrl]").val(),"reason": $("textarea[name=reason]").val()
                },
                    dataType: "json",
                    success: function (data) {
                        if (data.code < 0) {
                            Dalert(data.desc);
                        } else {
//                            Dalert(data.desc);
//                            ChangeStatus(5, $obj);
                        	 Dalert(data.desc,"",refresh);
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
                if($("textarea[name=reason]").val() == "" || $("textarea[name=reason]").val() == undefined) {
                    Dalert("请填写买家不能取消订单的原因，谢谢！");
                    $("input[name=reason]").focus();
                    return false;
                 }
                 else {
                $.ajax({
                    url: "/seller/shoporder/confirmCancelOrder",
                    type: "Post",
                    data: { "orderid": id, "isagree": 0, "status":status,"reason": $("textarea[name=reason]").val()
                },
                    dataType: "json",
                    success: function (data) {
                        if (data.code < 0) {
                            Dalert(data.dsc);
                        } else {
//                            Dalert(data.desc);
//                            ChangeStatus(12, $obj);
                        	Dalert(data.desc,"",refresh);
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
        });
        d.show();
    },
    thhsh: function (id,ss,flag) {
        //退换货审核
        //var thh = "退货";
        //if (flag == 1) { thh = "换货"; }
    	//申请退款
        var thh;
        switch (flag) {
          /*  case 1: thh = "退货退款"; break;
            case 2: thh = "退款"; break;
            case 3: thh = "换货"; break;
            case 4: thh = "维修"; break;*/
        	case 2: thh = "退款"; break;
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
    thhtk: function () {
        //退换货退款
    },
    zhuddtk : function(groupnum){
    	if(confirm("确定要执行该操作吗?")){
    		$.ajax({
    			url: "/seller/shoporder/updateOrderTK",
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
    			url: "/seller/shoporder/updatePartTK",
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
    }
}

function closeDialog() { d.close(); }

function closeDiv() { document.getElementById("dd_qxqr").style.display = "none"; }

function refresh() {
    location.reload();
}

function ChangeStatus(ss,$obj){
    var $prev = $obj.parent().prev().prev();
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
            $obj.html(""); //取消申请驳回
            $obj.attr("data-status", false);
            $prev.find("span").first().html("待发货");
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
    location.reload();
}
