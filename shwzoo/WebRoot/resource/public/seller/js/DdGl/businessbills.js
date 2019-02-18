//对账单列表

var pcount, pindex, psize = 10;
//页面加载，直营或店铺 0-店铺 1-直营
var list = {
    //对账单列表
    getlist: function (index) {
        var sta =  GetQueryStringByName("status");;
        var begin = $("#select_begin").val();
        var end = $("#select_end").val();
        $.ajax({
            url: "/seller/shoporder/bulllist",
            type: "Post",
            data: {
                "index": index,
                "size": psize,
                "status": sta,
                "begin": begin,
                "end": end,
                "shopId": $("#shopid").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc,10000);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('bblist', listdata);
                    //html 填充
                    $("#list_bbill").html(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));

               }
            },
            error: function () {
            }
        });
    },
}
//分页回调函数
function pagelist(index) {
    list.getlist(index);
}
//按钮事件绑定
$(function () {
    $("input[name=chaxun]").bind("click", search);
})

function chuli(obj){
	var id = $(obj).attr("bedit");
    if (confirm("确认要处理吗？")) {
    	$.ajax({
            url: "/seller/shoporder/setBbillsStatus",
            type: "Post",
            data: { "billId": id },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                	Dalert(data.desc,3000);
                	location.href = "/seller/shopdd/billlist?status=0";
                } else {
                    Dalert(data.desc,10000);
                }
            }
        });
    }
}

function chakan(obj){
	 var sid = $(obj).attr("bedit");
     var bt =$(obj).attr("dateb");
     var status=$(obj).attr("bedis");
     location.href = "/seller/shopdd/bbillsOrderList?shopid="+sid+"&orderdate="+bt+"&status="+status;
}
//查询
function search() {
    list.getlist(1);
}
