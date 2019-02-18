//店铺订单统计
var pcount, pindex, psize = size_reports;
var at;
var solist = {
    //获取售后订单数据
    bindsh: function (index, aftertype) {
        at = aftertype;
        var stype = $("#select_type").val();
        var timef, timet,isSearch=true;
        switch (stype) {
            case "1": timef = $("#time").val(); timet = $("#time1").val(); if ((timef == undefined || timef == "") && (timet == undefined || timet == "")) { isSearch = false; alert("请选择日期"); } break;
            case "2":; break;
            case "3": timef = $("#time2").val(); if (timef == undefined || timef == "") { isSearch = false; alert("请选择月份"); } break;
            case "4": timef = $("#time3").val(); if (timef == undefined || timef == "") { isSearch = false; alert("请选择季度"); } break;
            case "5": timef = $("#time4").val(); if (timef == undefined || timef == "") { isSearch = false; alert("请选择年度"); } break;
            default: break;
        }
        if (isSearch) {
            if (stype == 4) { timef = timef.substring(0, 6); }
            $.ajax({
                url: "/Order_Seller/GetAfterOrderList",
                type: "Post",
                data: { "page": index, "size": psize, "type": stype, "datef": timef, "datet": timet, "aftertype": aftertype },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        Dalert(data.Desc);
                    } else {
                        var listdata = {
                            list: data.Data
                        }

                        var html = template('ddlist', listdata);

                        //html 填充
                        $("#datalist").html(html);
                        pcount = data.MaxRow;
                        pindex = data.PageIndex;

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
            case "2": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").hide(); $("#divQuarter").hide(); $("#divYear").hide(); solist.bindsh(1, 2); break;
            case "3": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").show(); $("#divQuarter").hide(); $("#divYear").hide(); break;
            case "4": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").hide(); $("#divQuarter").show(); $("#divYear").hide(); break;
            case "5": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").hide(); $("#divQuarter").hide(); $("#divYear").show(); break;
            default: break;
        }
    }
}
function pagelist(index) {
    solist.bindsh(index,at);
}
//查询
function aftersearch(aftertype) {
    solist.bindsh(1, aftertype)
}