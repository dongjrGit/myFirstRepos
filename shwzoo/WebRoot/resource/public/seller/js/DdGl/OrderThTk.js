/*返修与退换货*/
$(document).ready(function () {
    Service.bind(1);

    //搜索
    $("#searchBtn").click(function () {
        var timef = $("#add_begin").val();
        var timet = $("#add_end").val();
        var code = $("#search").val();
        var shopid = $("#shopid").val();
        Service.search(code,shopid,timef,timet,1);
    });

});
var usrid = 0;
var timef = "";
var timet ="";
var code = "";
var Service = {
    //sid：店铺id
    bind: function (index) {
    	var shopid = $("#shopid").val();
        $.ajax({
            url: "/seller/sh/getReturnTradeList",
            type: "post",
            data: { 
            	order:code,
            	shopid:shopid,
            	timef: timef,
            	timet:timet,
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
                    var html = template('packlist', listdata);
                    //html 填充
                    $("#datalist").html(html);
                /*    $("#ddlist").siblings().remove();
                    $("#ddlist").after(html);*/
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
    
    //sID：店铺id，code：订单编号，ti：时间
    search: function (code,shopid,timef,timet,index) {
        $.ajax({
            url: "/seller/sh/getReturnTradeList",
            type: "post",
            data: {
            	order:code,
            	shopid:shopid,
            	timef: timef,
            	timet:timet,
            	page: index,
            	size: 10  },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    //alert(data.Desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('packlist', listdata);
                    $("#datalist").html(html);
                 /*   $("#ddlist").siblings().remove();
                    $("#ddlist").after(html);*/
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
    Service.bind(index);
}
function pageSeach(index) {
    Service.search(code,shopid,timef,timet,index)
}