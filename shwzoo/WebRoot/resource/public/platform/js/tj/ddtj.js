//查询事件绑定
$(function () {
    $("input[name=searchD]").click(searchD);
    $("input[name=searchW]").click(searchW);
    $("input[name=search]").click(search);
    //autoxl.bind("select_seller", getSellerStartwithName);
    autoxl.bind("select_shop", solist.getShopStartwithName);
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
function searchW() {
    solist.bind2(1);
}
function search() {
    solist.bind3(1,stype);
}

//店铺订单统计
var pcount, pindex, psize = 10;
var sid,stype;
var solist = {
    bind:function(tjtype){
        stype=tjtype;
    },
    //获取订单日数据
    bind1: function (index) {
    	var sid = $("#select_shop").attr("data");
        var timef = $("#time").val();
        var timet = $("#time1").val();
        if ((timef != undefined && timef != "") || (timet != undefined && timet != "")) {
            $.ajax({
                url: "/platform/dptj/getddtjDay",
                type: "Post",
                data: { "page": index,
                	"size": psize, 
                	"shopid": sid, 
                	"type": 1,
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
    	var sid = $("#select_shop").attr("data");
    	var timef = $("#time").val();
        var timet = $("#time1").val();
       if ((timef != undefined && timef != "") || (timet != undefined && timet != "")) {
        $.ajax({
            url: "/platform/dptj/getddtjWeek",
            type: "Post",
            data: { "page": index, 
            	"size": psize,
            	"shopid": sid, 
            	"type": 2,
            	"datef": timef, 
            	"datet": timet  },
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
    bind3: function (index,type) {
    	var sid = $("#select_shop").attr("data");
        stype = type;
        var timef = $("#time").val();
        if (timef != undefined && timef != "") {
            if (type == 4) { timef = timef.substring(0,6)}
            $.ajax({
                url: "/platform/dptj/getddtj",
                type: "Post",
                data: { "page": index,
                	"size": psize, 
                	"shopid": sid,
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
                        //加载table样式：改变偶数行背景色，鼠标移动时背景色
                        init();
                        pcount = data.maxRow;
    					pindex = data.pageIndex;
                       
                        //分页
                        $("#pager").html(pagination(pcount, pindex, psize, "pagelist3"));
                    }
                },
                error: function () {

                }
            });
        }else{
        	  alert("请选择日期");
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
            url: "/platform/shop/queryShopByLikeName",
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
                    Dalert(data.Data);
                }
            }
        });
    }
}


/*
callback 成功 有数据时 调的方法 
event 事件
*/
function getSellerStartwithName(callback, event) {
    var name = $("#select_seller").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/Account/GetAllUserStartwithName",
        type: "Post",
        data: { "UserNameLike": name, "UserType": 5 },
        dataType: "json",
        success: function (data) {
            if (data.Code == 0) {
                var listdata = {
                    list: data.Data
                }
                var html = template('select_sellerlist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.Data);
            }
        }
    });
}