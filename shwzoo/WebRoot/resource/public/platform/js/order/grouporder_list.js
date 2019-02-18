$(document).ready(function () {
        list.getlist(1);
    })  
var pcount, pindex, psize = size_order;

//页面加载
var list = {
    //订单列表
    getlist: function (index) {
 
        var datahtml = "";
        var status = $("#orderstatus option:selected").val();
        var ordercode = $("#select_ordercode").val();
        var sid = $("#select_shop").attr("data");
        var buyerid = $("#select_buyer").attr("data");
        var begin = $("#select_begin").val();
        var end = $("#select_end").val();
        $.ajax({
            url : "/platform/grouporder/getGroupOrderList",
			type : "Get",
			dataType : "json",
			data: {
                "page": index, "size": psize, "status": status, "num": ordercode, "shopid": sid,
                "buyerid": buyerid, "start": begin, "end": end
            },
			success : function(data) {
				if (data.code < 0) {
					 Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('Grouplist', listdata);
					$("#datalist").html(html);
					// 分页
					pcount = data.maxRow;
					pindex = data.pageIndex;
					$("#pager").html(pagination(pcount, pindex, 10, "pagelist"));
				}
			},
            error: function () {
            	
            }
        });
    },
    //订单删除
    del: function (id) {
        if (confirm('确定将此记录删除?')) {
            $.ajax({
                url: "/platform/grouporder/delGroupOrder",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        Dalert(data.desc);
                        list.getlist(pindex);
                    }
                },
                error: function () {

                }
            });
        }
    },
    //使用团购劵
    use: function (id) {
    	var code=$(this).attr("data-code");
        if (confirm('确定要使用团购劵吗?')) {
            $.ajax({
                url: "/platform/grouporder/useGroupOrder",
                type: "Post",
                data: { "id": id, "code": code},
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        Dalert(data.desc);
                        list.getlist(pindex);
                    }
                },
                error: function () {

                }
            });
        }
    },
    //审核退款
    thsh: function (id) {
        if (confirm('确定要审核退款吗?')) {
            $.ajax({
                url: "/platform/grouporder/confirmReturnOrder",
                type: "Post",
                data: { "id": id},
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        Dalert(data.desc);
                        list.getlist(pindex);
                    }
                },
                error: function () {

                }
            });
        }
    },
    /*
callback 成功 有数据时 调的方法 
event 事件
*/
    getShopStartwithName: function (callback, event) {
        var name = $("#select_shop").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/platform/shop/getShopStartWithName",
            type: "Post",
            data: { "name": name },
            dataType: "json",
            success: function (data) {

                if (data.code == 0) {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('select_shoplist', listdata);

                    if (callback) {
                        callback(html);
                    }
                } else {
                    Dalert(data.desc);
                }
            }
        });
    }
}
//分页回调函数
function pagelist(index) {
    list.getlist(index);
}
//按钮事件绑定
$(function () {
    $("input[name=search]").bind("click", search);
    autoxl.bind("select_buyer", getBuyerStartwithName);
    autoxl.bind("select_shop", list.getShopStartwithName);
})
//查询
function search() {
    list.getlist(1);
}

function getBuyerStartwithName(callback, event) {
    var name = $("#select_buyer").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/accounts/getUserStartWithName",
        type: "Post",
        data: { "name": name, "usertype": 2 },
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('select_buyerlist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.desc);
            }
        }
    });
}