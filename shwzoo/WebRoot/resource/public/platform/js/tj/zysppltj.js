var pcount, pindex, psize = size_reports;
var sid, pid, stype;
var solist = {
    //获取订单日数据
    bind1: function (index) {
        sid = "";
        var timef = $("#time").val();
        var timet = $("#time1").val();
        if ((timef != undefined && timef != "") || (timet != undefined && timet != "")) {
            $.ajax({
                url: "/Product_TJ/P_GetSPUCommentList",
                type: "Post",
                data: { "page": index, "size": psize, "sellerid": sid, "spuid": pid, "type": 1, "zy": 1, "datef": timef, "datet": timet },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        Dalert(data.Desc);
                    } else {
                        var listdata = {

                            list: data.Data
                        }

                        var html = template('ddlist', listdata);

                        //翻页时删除表头以外的所有节点，避免after()方法重复加载
                        $("#dd_title").parent().children().each(function () {
                            if ($(this).attr('id') != "dd_title") {
                                this.parentNode.removeChild(this);
                            }
                        })
                        //html 填充
                        $("#dd_title").after(html);
                        //加载table样式：改变偶数行背景色，鼠标移动时背景色
                        init();
                        pcount = data.MaxRow;
                        pindex = data.PageIndex;
                        //分页
                        $("#pager").html(pagination(pcount, pindex, psize, "pagelist1"));
                    }
                },
                error: function () {

                }
            });
        }
    },
    //获取订单最近一周数据
    bind2: function (index) {
        sid = "";
        $.ajax({
            url: "/Product_TJ/P_GetSPUCommentList",
            type: "Post",
            data: { "page": index, "size": psize, "sellerid": sid, "spuid": pid, "type": 2, "zy": 1 },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }

                    var html = template('ddlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#dd_title").parent().children().each(function () {
                        if ($(this).attr('id') != "dd_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#dd_title").after(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
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
        sid = ""; stype = type
        var timef = $("#time").val();
        if (timef != undefined && timef != "") {
            if (type == 4) { timef = timef.substring(0, 6) }
            $.ajax({
                url: "/Product_TJ/P_GetSPUCommentList",
                type: "Post",
                data: { "page": index, "size": psize, "sellerid": sid, "spuid": pid, "zy": 1, "type": type, "datef": timef },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        Dalert(data.Desc);
                    } else {
                        var listdata = {

                            list: data.Data
                        }

                        var html = template('ddlist', listdata);

                        //翻页时删除表头以外的所有节点，避免after()方法重复加载
                        $("#dd_title").parent().children().each(function () {
                            if ($(this).attr('id') != "dd_title") {
                                this.parentNode.removeChild(this);
                            }
                        })
                        //html 填充
                        $("#dd_title").after(html);
                        //加载table样式：改变偶数行背景色，鼠标移动时背景色
                        init();
                        pcount = data.MaxRow;
                        pindex = data.PageIndex;

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

function pagelist1(index) {
    solist.bind1(index);
}
function pagelist2(index) {
    solist.bind2(index);
}
function pagelist3(index) {
    solist.bind3(index, stype);
}
$(function () {
    $("input[name=searchD]").click(searchD);
    $("input[name=searchW]").click(searchW);
    $("input[name=search]").click(search);
    autoxl.bind("select_spu", getSPUStartwithName);
})

function searchD() {
    pid = $("#select_spu").attr("data");
    solist.bind1(1);
}
function searchW() {
    pid = $("#select_spu").attr("data");
    solist.bind2(1);
}
function search() {
    pid = $("#select_spu").attr("data");
    solist.bind3(1, stype);
}

/*
callback 成功 有数据时 调的方法 
event 事件
*/
function getSPUStartwithName(callback, event) {
    var name = $("#select_spu").val();
    var sellerid = 0;
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/Product_TJ/P_GetSPULikes",
        type: "Post",
        data: { "SPUNameLike": name, "UserID": sellerid },
        dataType: "json",
        success: function (data) {
            if (data.Code == 0) {
                var listdata = {
                    list: data.Data
                }
                var html = template('select_spulist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.Data);
            }
        }
    });
}