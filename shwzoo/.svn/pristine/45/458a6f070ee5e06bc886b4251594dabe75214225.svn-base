
//优惠卷列表
var pcount, pindex, psize = size_shop;
var coupon = {
    bind: function () {
        autoxl.bind("select_shop", coupon.getShopStartwithName, true);
        $("input[name=btnsearch]").bind("click", function () { coupon.getlist(1); });
        $("input[name=btnadd]").bind("click", function () { location.href = "yxgl_CouponAdd"; });
    },
    getlist: function (index) {
        //分类路径 商品名称 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var sid = "",type1,type2, name = "", s1, e1, s2, e2;
        var sid = $("#select_shop").attr("data");
        type1 = $("#issue_select").val();
        type2 = $("#use_select").val();
        type3 = $("#type_select").val();
        name = $("#name").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/platform/coupon/getCouponList",
            type: "Post",
            data: { "page": index, "size": psize, "startf": s1, "startt": e1, "endf": s2, "endt": e2,
                "usetype": type2, "ctype": type3, "shopid": sid,"name":name,"itype":type1
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('couponlist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    coupon.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "coupon.getlist"));
                }
            },
            error: function () {

            }
        });
    },
    getuserlist: function (index) {
        $("input[name=add]").bind("click", function () { $("#adduser").show(); });
        var couponid = $("#couponid").val();
        $.ajax({
            url: "/platform/coupon/getByCouponID",
            type: "Post",
            data: {
                "page": index, "size": psize, "id": couponid
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('couponlist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //绑定删除事件
                    coupon.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "coupon.getuserlist"));
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", coupon.del);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/platform/coupon/deleteCoupon",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                    	Dalert(data.desc, "", refresh);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
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

function refresh() {
	location.reload();
}

//公开 不公开
function setStatus(id, ss) {
    $.ajax({
        url: "/platform/coupon/updateStatus",
        type: "Post",
        data: { "id": id, "status": ss },
        dataType: "json",
        success: function (data) {
            if (data.code < 0) { Dalert(data.desc); }
            else {
                var td_html = "";
                if (ss == 0) {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + id + ",1)>公开</a></span>";
                }
                else {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + id + ",0)>不公开</a></span>";
                }
                $("#td_" + id).html(td_html);
            }
        },
        error: function () {
            Dalert("修改状态失败");
        }
    });
}