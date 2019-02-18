//优惠券
var pcount;
var pindex;
var psize = 5;
var pstatus;
var porderby;
var Coupon = {
    //列表
    getList: function (type, sort, page,size) {
        $.ajax(({
            type: "post",
            url: "/pc/coupon/showcoupons",
            dataType: "json",
            data: { type:type,sort:sort,page: page, size: size, },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }

                    var html = template('couponlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#div_title").parent().children().each(function () {
                        if ($(this).attr('id') != "div_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#div_title").after(html);

                    pcount = rsl.maxRow;
                    pindex = rsl.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "coupon_pagelist"));
                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function (e) {

            }
        }));
    }
}

//分页回调
function coupon_pagelist(index) {
	 var status = $("#select_coupontype").val();
    Coupon.getList(status,null,index,5);
}

//改变优惠券类型
function ChangeCouponType() {
    var status = $("#select_coupontype").val();
    Coupon.getList(status,null,1,5);
}

//排序改变
function OrderChange(obj) {
    var ordertype = $(obj).attr("order-val");
    Coupon.getList(1, pstatus, ordertype);
    $(".a_order").parent().attr('class', 'normal');
    $(obj).parent().attr('class', 'red');
}

//优惠券删除
function DelCoupon(obj) {
	if(confirm("删除该优惠券吗？"))
	{
		var id = $(obj).attr("id-val");
	    $.ajax(({
	        type: "post",
	        url: "/pc/coupon/deleteUserCoupon",
	        dataType: "json",
	        data: { id: id },
	        success: function (rsl) {
	                Dalert(rsl.Desc,"",Coupon.getList(1,null,1,5));
	        },
	        error: function (e) {

	        }
	    }));
	}
    
}

//优惠券恢复使用
function RecoverCoupon(obj) {
    var id = $(obj).attr("id-val");
    $.ajax(({
        type: "post",
        url: "/Coupon/M_CancelUserDelCoupon",
        dataType: "json",
        data: { id: id },
        success: function (rsl) {
            if (rsl.code == 0) {
                Coupon.getList(1, pstatus, porderby);
            }
            else {
                Dalert(rsl.Desc);
            }
        },
        error: function (e) {

        }
    }));
}