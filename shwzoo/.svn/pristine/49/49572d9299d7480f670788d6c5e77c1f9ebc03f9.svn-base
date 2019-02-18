
//优惠卷列表
var pcount, pindex, psize = 10;
var coupon = {
    bind: function () {
        $("input[name=btnsearch]").bind("click", function () { coupon.getlist(1); });
        $("input[name=btnadd]").bind("click",function(){location.href="yxgl_CouponAdd";});
    },
    getlist: function (index) {
        //分类路径 商品名称 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var sid = "",type1,type2, name = "", s1, e1, s2, e2;
        var sid = $("#select_shop").attr("data");;
        type1 = $("#use_select").val();
        type2 = $("#type_select").val();
        name = $("#name").val();
        s1 = $("#starts").val();
        e1 = $("#starte").val();
        s2 = $("#ends").val();
        e2 = $("#ende").val();
        $.ajax({
            url: "/seller/shopcoupon/getShopCouponList",
            type: "Post",
            data: { "page": index, "size": psize, "startf": s1, "startt": e1, "endf": s2, "endt": e2,
                "usetype": type1, "ctype": type2},
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
            url: "/seller/shopcoupon/getByCouponID",
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
                url: "/seller/shopcoupon/deleteCoupon",
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
    }
}

function refresh() {
	location.reload();
}

//公开 不公开
function setStatus(id, ss) {
    $.ajax({
        url: "/seller/shopcoupon/updateStatus",
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