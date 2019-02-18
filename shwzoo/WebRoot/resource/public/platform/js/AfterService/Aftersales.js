/*售后申请表*/
$(document).ready(function () {
    var shopID = $("#shopID").val();
    Service.bind(shopID);
    //删除
    $("body").on("click",".delete", function () {
        var id = $(this).parent().parent().find("input").val();
        ConfirmShow("确认要删除吗？", delAfSer, id);//confirm弹出框 js/dialogShow.js

    });
    //搜索
    $("#search").click(function () {
        var time = $("#time").val();
        Service.search(shopID, time);
    });
    //编辑
    $("body").on("click",".bjxx", function () {
        var id = $(this).parent().parent().find("input").val();
        var tr = $(this).parent().parent().parent();
        var orderCode = tr.children('td').eq(0).html();

        location.href = "ConfirmAftersales?id=" + id + "&code=" + orderCode;
    })
});
//删除售后服务 id:服务id
function delAfSer(id) {
    $.ajax({
        type: "post",
        url: "/AfterService/DeleAfter",
        dataType: "json",
        data: {
            id: id
        },
        success: function (rsl) {
            if (rsl.Code == 0) {
                Dalert(rsl.Desc,"",refresh);
               // window.location.reload();
            }
            else {
                Dalert(rsl.Desc);
            }
        },
        error: function (e) {
            //Dalert(e.statusText);
        }
    })
}
var usrid = 0;
var time = "";
var Service = {
    //sid：店铺id
    bind: function (sID, index) {
        $.ajax({
            url: "/AfterService_Platform/GetPageList",
            type: "post",
            data: { status: "", order: "", page: index, size: 10 },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    //alert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('datalist', listdata);
                    $("#trdata").siblings().remove();
                    $("#trdata").after(html);
                    //分页
                    usrid = sID;
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    $("#pager").html(pagination(pcount, pindex, 10, "pagelist"));
                }
            },
            error: function () {
            }
        });
    },//sid：店铺id，ti：时间
    search: function (sID, ti, index) {
        $.ajax({
            url: "/AfterService_Platform/GetPageList",
            type: "post",
            data: { status:"",order:"", time: ti, page: index, size: 10 },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    //alert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('datalist', listdata);
                    $("#trdata").siblings().remove();
                    $("#trdata").after(html);
                    //分页
                    usrid = sID;
                    time = ti;
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    $("#pager").html(pagination(pcount, pindex, 10, "pageSeach"));
                }
            },
            error: function () {
            }
        });
    }
}
function pagelist(index) {
    Service.bind(usrid, index);
}
function pageSeach(index) {
    Service.search(usrid, time, index)
}
//刷新
function refresh() {
    location.reload();
}