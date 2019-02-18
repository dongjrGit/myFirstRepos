//满赠活动-赠品列表

var zengpin = {
    bind: function () {
//        $("input[name=btnadd]").bind("click", function () { $("#divadd").show(); zengpin.TypeChange(); });
    	autoxl.bind("select_gives", zengpin.getSku, true);
    	$("input[name=btnadd]").bind("click", function () { $("#divadd").show();});
        $("input[name=Save]").bind("click", Save);
        $("input[name=cancel]").bind("click", function () { $("#divadd").hide(); });
        $("body").on("click",".xgyfxxgz-close", function () {
            $("#divadd").css("display", "none");
        });
        zengpin.getlist();
    },
    getlist: function () {
        var actid = $("#activityid").val();
        $.ajax({
            url: "/seller/ShopFullgift/getGiftList",
            type: "Post",
            data: { "actid": actid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('zengpinlist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    //绑定删除事件
                    zengpin.unit();
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", zengpin.del);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/seller/ShopFullgift/deleteGift",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        location.reload();
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    },
//    TypeChange: function () {
//        var givetype = $("#givetype").val();
//        if (givetype == 0) {
//            $("#givec").show();
//            $("#gives").hide();
//            $("#givep").hide();
//            autoxl.bind("select_givec", zengpin.getCoupon, true);
//            $("#zpcount").show();
//        }
//        else if (givetype == 1) {
//            $("#gives").show();
//            $("#givec").hide();
//            $("#givep").hide();
//            autoxl.bind("select_gives", zengpin.getSku, true);
//            $("#zpcount").show();
//        }
//    },
    getSku: function (callback, event) {
        var name = $("#select_gives").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/seller/shopsku/getactskubyname",
            type: "Post",
            data: { "name": name },
            dataType: "json",
            success: function (data) {

                if (data.code == 0) {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('select_giveslist', listdata);

                    if (callback) {
                        callback(html);
                    }
                } else {
                    Dalert(data.desc);
                }
            }
        });
    },
    getCoupon: function (callback, event) {
        var name = $("#select_givec").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/seller/ShopCoupon/getCouponStartwithName",
            type: "Post",
            data: { "name": name },
            dataType: "json",
            success: function (data) {

                if (data.code == 0) {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('select_giveclist', listdata);

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
//保存
function Save() {
    if (checkform()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/seller/ShopFullgift/addGift",
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.desc);
                }
                else {
                    Dalert("保存成功！");
                    $("#divadd").hide();
                    location.reload();
                }
            },
            error: function () {
            }
        });
    }
}
//保存前参数验证
function checkform() {
    //判断是否选择赠品
    var ret = true;
//    if ($("#givetype").val() == "1") {
        giveid = $("#select_gives").attr("data");
        if (giveid == "" || giveid == undefined) {
            Dalert("请选择赠品");
            $("#select_gives").focus();
            ret = false;
        } else {
            $("#giveid").val(giveid);
        }

//    }
//    else if ($("#givetype").val() == "0") {
//        giveid = $("#select_givec").attr("data");
//        if (giveid == "" || giveid == undefined) {
//            Dalert("请选择赠品");
//            $("#select_givec").focus();
//            ret = false;
//        } else {
//            $("#giveid").val(giveid);
//        }
//
//    } else {
//        if ($("#points").val() == "" || $("#points").val() == undefined) {
//        Dalert("请填写积分");
//        $("#points").focus();
//    }
//}

    return ret;
}
//修改数量
function setCount(id, ob) {
    var count = $("#" + ob).val();
    $.ajax({
        url: "/seller/ShopFullgift/changeCount",
        type: "Post",
        data: { "id": id, "count": count },
        dataType: "json",
        success: function (data) {
            Dalert(data.desc);
        },
        error: function () {
            Dalert("修改数量失败");
        }
    });
}

//修改积分
function setPoint(id, po) {
    var count = $("#" + po).val();
    $.ajax({
        url: "/seller/ShopFullgift/changePoint",
        type: "Post",
        data: { "id": id, "points": count },
        dataType: "json",
        success: function (data) {
            Dalert(data.desc);
        },
        error: function () {
            Dalert("修改积分失败");
        }
    });
}