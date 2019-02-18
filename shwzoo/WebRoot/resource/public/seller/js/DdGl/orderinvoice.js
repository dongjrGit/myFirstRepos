//订单发票列表
var pcount, pindex, psize = size_order;
var list = {
    getlist: function (index) {
        var datahtml = "";
        var code = $("#code").val();
        var type = $("#type").val();
        var add_begin = $("#add_begin").val();
        var add_end = $("#add_end").val();
        var d_begin = $("#Deliver_begin").val();
        var d_end = $("#Deliver_end").val();
        var status = $("#status").val();
        $.ajax({
            url: "/seller/shoporder/getInvoiceList",
            type: "Post",
            data: {
                "page": index, "size": psize, "code": code, "type": type, "start": add_begin,
                "end": add_end, "sendstart": d_begin, "sendend": d_end, "status": status
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('invlist', listdata);

                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "list.getlist"));
                }
            },
            error: function () {

            }
        });
    }
}

$(function () {
    $("input[name=search]").bind("click", function () { list.getlist(1); });
    $("input[name=reset]").bind("click", reset);
})
//清空
function reset() {
    $("#code").val("");
    $("#add_begin").val("");
    $("#add_end").val("");
    $("#Deliver_begin").val("");
    $("#Deliver_end").val("");
    $("#status").val("");
    $("#type").val("");
}

//标记打印
function setStatus(id) {
    $.ajax({
        url: "/seller/shoporder/changeInvStatus",
        type: "Post",
        data: { "id": id },
        dataType: "json",
        success: function (data) {
            if (data.code < 0) { Dalert(data.desc); }
            else {
                Dalert(data.desc);
                var td_html = "<span>已打印</span>";
 
                $("#td_" + id).html(td_html);
                $("#tds_" + id).html(td_html);
            }
        },
        error: function () {
        }
    });
}