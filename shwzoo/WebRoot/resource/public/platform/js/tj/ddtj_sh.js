//店铺订单统计
var pcount, pindex, psize = size_reports;
var at;
var solist = {
    //获取售后订单数据
    bindsh: function (index, aftertype) {
        at = aftertype;
        var stype = $("#select_type").val();
        var sid = $("#select_shop").attr("data");
        var timef, timet,isSearch=true;
        switch (stype) {
            case "1": timef = $("#time").val(); timet = $("#time1").val(); if ((timef == undefined || timef == "") && (timet == undefined || timet == "")) { isSearch = false; Dalert("请选择日期"); } break;
            case "2":; break;
            case "3": timef = $("#time2").val(); if (timef == undefined || timef == "") { isSearch = false; Dalert("请选择月份"); } break;
            case "4": timef = $("#time3").val(); if (timef == undefined || timef == "") { isSearch = false; Dalert("请选择季度"); } break;
            case "5": timef = $("#time4").val(); if (timef == undefined || timef == "") { isSearch = false; Dalert("请选择年度"); } break;
            default: break;
        }
        if (isSearch) {
            if (stype == 4) { timef = timef.substring(0, 6); }
            $.ajax({
                url: "/platform/dptj/getAfterOrderList",
                type: "Post",
                data: { "page": index, "size": psize, "shopid": sid, "type": stype, "datef": timef, "datet": timet, "aftertype": $("#aft").val() },
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
                        $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                    }
                },
                error: function () {

                }
            });
        }
    },
    typeChange: function () {
        var stype = $("#select_type").val();
        switch (stype) {
            case "1": $("#divDay").show(); $("#thday").show(); $("#divMonth").hide(); $("#divQuarter").hide(); $("#divYear").hide(); break;
            case "2": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").hide(); $("#divQuarter").hide(); $("#divYear").hide(); solist.bindsh(1, 2);break;
            case "3": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").show(); $("#divQuarter").hide(); $("#divYear").hide(); break;
            case "4": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").hide(); $("#divQuarter").show(); $("#divYear").hide(); break;
            case "5": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").hide(); $("#divQuarter").hide(); $("#divYear").show(); break;
            default: break;
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
function pagelist(index) {
    solist.bindsh(index,at);
}
$(function () { autoxl.bind("select_shop", solist.getShopStartwithName); })
//查询
function aftersearch(aftertype) {
    solist.bindsh(1, aftertype);
}