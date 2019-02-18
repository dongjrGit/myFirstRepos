//优惠券
var size = 10;
var Couponlist = {
    //获取用户优惠券列表
    getList: function (index, status, orderby)
    {
        $.ajax(({
            type:"post",
            url: "/AppCoupon/AppGetByUser",
            datatype: "json",
            data: { index: index, size: size, status: status, orderby: orderby, token: "" },
            success: function (fh)
            {
                var data = JSON.parse(fh);
                if (data.Code==0) {
                    var couponlists =
                        {
                            list: data.Data,
                        }
                    var html = template('couponlist', couponlists);
                    $("#coupon").html(html);
                }
                else { alert(data.Desc); }
            }
            
        }));
    }
}