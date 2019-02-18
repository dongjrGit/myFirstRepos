/*控制面板--支付货币设置*/
$(document).ready(function () {
    MoneySet.bind();

    //编辑
    $("body").on("click",".bjxx", function () {
        var id = $(this).parent().parent().find("input").val();
        var name = $(this).parents("tr")[0].cells[0].innerText;

        var d = dialog({
            title: '编辑',
            content: "<label>货币名称:</label><input id='msName' value='" + name + "' />",
            cancelValue: "取消",
            okValue: "确定",
            cancel: function () { },
            ok: function () {
                var edName = $("#msName").val();
                $.ajax({
                    type: "post",
                    url: "/KZ/Edit",
                    dataType: "json",
                    data: {
                        ID: id, name: edName
                    },
                    success: function (rsl) {
                        if (rsl.Code == 0) {
                            Dalert(rsl.Desc, "", refresh);
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
        });
        d.show();
    })
    //添加
    $("#addmoney").click( function () {
        var d = dialog({
            title: '编辑',
            content: "<label>货币名称:</label><input id='msName' value='' />",
            cancelValue: "取消",
            okValue: "确定",
            cancel: function () { },
            ok: function () {
                var edName = $("#msName").val();
                if (edName == "" || edName == undefined) {
                    Dalert("不能为空");
                    return false;
                } else {
                    $.ajax({
                        type: "post",
                        url: "/KZ/Add",
                        dataType: "json",
                        data: {
                            name: edName
                        },
                        success: function (rsl) {
                            if (rsl.Code == 0) {
                                Dalert(rsl.Desc, "", refresh);
                            }
                            else {
                                Dalert(rsl.Desc);
                            }
                        },
                        error: function (e) {
                           // alert(e.statusText);
                        }
                    })
                }
            }
        });
        d.show();
    })
    //删除
    $("body").on("click",".delete", function () {
        var id = $(this).parent().parent().find("input").val();
        var d = dialog({
            title: '删除',
            content: "确认要删除吗？",
            cancelValue: "取消",
            okValue: "确定",
            cancel: function () { },
            ok: function () {
                $.ajax({
                    type: "post",
                    url: "/KZ/DeleteMoney",
                    dataType: "json",
                    data: {
                        mID: id
                    },
                    success: function (rsl) {
                        if (rsl.Code == 0) {
                            Dalert(rsl.Desc, "", refresh);
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
        });
        d.show();
    })

});

var MoneySet = {
    bind: function () {
        $.ajax({
            url: "/KZ/GetMoneyTypeList",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    //alert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('mslist', listdata);
                    $("#datalist").siblings().remove();
                    $("#datalist").after(html);

                }
            },
            error: function () {
            }
        });
    }
}

function refresh() {
    // window.location.reload();
    MoneySet.bind();
}