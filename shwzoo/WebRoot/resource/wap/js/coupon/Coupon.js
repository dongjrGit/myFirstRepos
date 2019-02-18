//优惠券
var ch=3;
var AddCoupon = {
    //领取用户优惠券列表
    add: function (couponid)
    {
        $.ajax(({
            type:"post",
             url: "/api/wap/coupon/takeCoupon",
            datatype: "json",
            data: { couponid:couponid,ch:ch},
            success: function (fh)
            {
            	var coupon=JSON.parse(fh);
                if (coupon.code==0) {
                	alert("领取优惠券成功");
                }else{
                	alert(coupon.desc);
                }
               
            },
            error: function (e) {
            	alert(coupon.desc);
            }
            
        }));
    }
}