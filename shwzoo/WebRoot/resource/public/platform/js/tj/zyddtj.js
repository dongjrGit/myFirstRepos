$(function () {
    $("input[name=searchD]").click(searchD);
    $("input[name=search]").click(search);
});

//订单日数据分页回调函数
function pagelist1(index) {
    solist.bind1(index);
}
//订单最近一周数据分页回调函数
function pagelist2(index) {
    solist.bind2(index);
}
//按月,季度,年获取订单，分页回调函数
function pagelist3(index) {
    solist.bind3(index, stype);
}

function searchD() {
    solist.bind1(1);
}
function search() {
    solist.bind3(1, stype);
}

//直营订单统计
var pcount, pindex, psize = 10;
var sid, stype;
var solist = {
    bind: function (tjtype) {
        stype = tjtype;
    },
    //获取订单日数据
    bind1: function (index) {
        sid =  $("#userid").val();
        var timef = $("#time").val();
        var timet = $("#time1").val();
        if ((timef != undefined && timef != "") || (timet != undefined && timet != "")) {
            $.ajax({
                url: "/platform/tj/getzyddtjDay",
                type: "Post",
                data: { "page": index,
                	"size": psize, 
                	"sellerid": sid,
                	"type": 1, 
                	"zy": 1, 
                	"datef": timef, 
                	"datet": timet },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        var listdata = {

                            list: data.data
                        }

                        var html = template('ddlist', listdata);

                        //html 填充
                        $("#datalist").html(html);
                        //加载table样式：改变偶数行背景色，鼠标移动时背景色
                        init();
                        psize = psize;
                        pcount = data.maxRow;
						pindex = data.pageIndex;
                        //分页
                        $("#pager").html(pagination(pcount, pindex, psize, "pagelist1"));
                    }
                },
                error: function () {

                }
            });
        } else {
            alert("请选择日期");
        }
    },
    //获取订单最近一周数据
    bind2: function (index) {
    	 sid =  $("#userid").val();
        $.ajax({
            url: "/platform/tj/getzyddtjWeek",
            type: "Post",
            data: { "page": index, "size": psize, "sellerid": sid, "zy": 1, "type": 2 },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('ddlist', listdata);
                    //html 填充
                    $("#datalist").html(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    psize = psize;
                    pcount = data.maxRow;
					pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist2"));
                }
            },
            error: function () {

            }
        });
    },
    //按月,季度,年获取订单
    bind3: function (index, type) {
    	 sid =  $("#userid").val();
    	 stype = type;
        var timef = $("#time").val();
        if (timef != undefined && timef != "") {
            if (type == 4) { timef = timef.substring(0, 6) }
            $.ajax({
                url: "/platform/tj/getzyddtj",
                type: "Post",
                data: { "page": index, 
                	"size": psize, 
                	"sellerid": sid,
                	"zy": 1,
                	"type": type, 
                	"datef": timef },
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        var listdata = {

                            list: data.data
                        }

                        var html = template('ddlist', listdata);

                        //html 填充
                        $("#datalist").html(html);
                        //加载table样式：改变偶数行背景色，鼠标移动时背景色
                        init();
                        psize = psize;
                        pcount = data.maxRow;
						pindex = data.pageIndex;
                        //分页
                        $("#pager").html(pagination(pcount, pindex, psize, "pagelist3"));
                    }
                },
                error: function () {

                }
            });
        }
    }
}
