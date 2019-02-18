/*返修与退换货*/
$(document).ready(function () {
    var shopID = $("#shopID").val();
    Service.bind(1);

    //搜索
    $("#searchBtn").click(function () {
        var time = $("#time").val();
        var code = $("#search").val();
        Service.search(code,time,1);
    });

});
var usrid = 0;
var time = "";
var code = "";
var Service = {
    //sid：店铺id
    bind: function (index) {
        
        $.ajax({
            url: "/platform/sh/getReturnTradeList",
            type: "post",
            data: { 
            	order:code,
            	time: time,
            	page: index,
            	size: 10 },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    //alert(data.Desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('datalist', listdata);
                    $("#trdata").siblings().remove();
                    $("#trdata").after(html);
                    //分页
                    //usrid = sID;
                    pcount = data.maxRow;
					pindex = data.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, 10, "pagelist"));
                }
            },
            error: function () {
            }
        });
    },
    
    del: function (id) {
        if (confirm('确定将此记录删除?')) {
            $.ajax({
                url: "/platform/sh/delReturnTrade",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                    	Dalert(data.desc, "", refresh);
                    }
                },
                error: function () {

                }
            });
        }
    },
    
    rollbackMony: function (id) {
        if (confirm('确定退款吗?')) {
            $.ajax({
                url: "/platform/sh/updatestatus",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                    	Dalert(data.desc, "", refresh);
                    }
                },
                error: function () {

                }
            });
        }
    },
    
    //sID：店铺id，code：订单编号，ti：时间
    search: function (code, ti, index) {
        $.ajax({
            url: "/platform/sh/getReturnTradeList",
            type: "post",
            data: { status:1,
            	order:code,
            	time: ti,
            	page: index,
            	size: 10 },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    //alert(data.Desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('datalist', listdata);
                    $("#trdata").siblings().remove();
                    $("#trdata").after(html);
                    //分页
                    //usrid = sID;
                    time = ti;
                    code = code;
                    pcount = data.maxRow;
					pindex = data.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, 10, "pageSeach"));
                }
            },
            error: function () {
            }
        });
    }
}

function refresh() {
	location.reload();
}

function pagelist(index) {
    Service.bind(usrid, index);
}
function pageSeach(index) {
    Service.search(usrid,code, time, index)
}