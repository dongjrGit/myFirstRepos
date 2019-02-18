$(function () {
    $("input[name=searchD]").click(searchD);
    $("input[name=search]").click(search);
    $("input[name=searchW]").click(searchW);
});

//订单日数据分页回调函数
function pagelist1(index) {
    solist.bind1(index);
}
//订单最近一周数据分页回调函数
function pagelist2(index) {
    solist.bind2(index);
}
//订单月，季度，年度数据分页回调函数
function pagelist3(index) {
    solist.bind3(index, stype);
}

//订单日统计查询
function searchD() {
    solist.bind1(1);
}
function searchW() {
    solist.bind2(1);
}
function search() {
    solist.bind3(1, stype);
}

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
                url: "/seller/tj/getddtjDay",
                type: "Post",
                data: { "page": index,
                	"size": psize, 
                	"sellerid": sid,
                	"type": 1,
                	"zy": 0,
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
    	 var timef = $("#time").val();
         var timet = $("#time1").val();
         if ((timef != undefined && timef != "") || (timet != undefined && timet != "")) {
        $.ajax({
            url: "/seller/tj/getddtjWeek",
            type: "Post",
            data: { "page": index, 
            	"size": psize,
            	"sellerid": sid, 
            	"type": 2,
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

                    pcount = data.maxRow;
					pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist2"));
                }
            },
            error: function () {

            }
        });
         } else {
             alert("请选择日期");
         }
    },
    //按月,季度,年获取订单
    bind3: function (index, type) {
    	sid =  $("#userid").val();
    	stype = type;
        var timef = $("#time").val();
        if (timef != undefined && timef != "") {
            if (type == 4) { timef = timef.substring(0, 6) }
            $.ajax({
                url: "/seller/tj/getddtj",
                type: "Post",
                data: { "page": index,
                	"size": psize, 
                	"sellerid": sid, 
                	"zy": 0, 
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
