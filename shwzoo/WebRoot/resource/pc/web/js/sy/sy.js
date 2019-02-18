var tips=0;
$(document).ready(function () {

    //专题加载
    //showSpecials(1);
    //专题切换
    $(".l_sjrigtop ul li").click(function () {
        $(".l_sjrigtop ul li").removeClass("l_czhover");
        $(".l_sjrigtop ul li").children('span').remove();
        $(this).addClass("l_czhover");
        $(this).find("a").after("<span class='l_czrow'></span>");
        var dis = $(this).attr("data");
        tips=dis;
       // showSpecials(dis);
        showTops(dis);
    })
    //进入用户中心
    // $(".div_membercenter").bind("click", function () {
        // if (user.IsLogin()) {
            // window.location.href = '/Member/Member/MemberSY';
        // } else {
            // //TODO 登陆
            // showlogindiv();
        // }
    // })
    //首页屏蔽菜单隐藏
    $("#navigation").mouseleave(function () {
        $("div[name=div_menu]").attr("style", "display:block");
    });
    //首页菜单显示
    $("div[name=div_menu]").show();

})
//专题加载 display:显示位置
function showSpecials(display) {
    $.ajax({
        type: "post",
        url: "/pc/index/gettopic",
        dataType: "json",
        data: {"pagetag":0,"mark":display,size:5},
        success: function (rsl) {
            if (rsl.code == 0) {
                var data = rsl.data;
                var div = "", lift = "";
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                           
                        var pro = data[i];
                        if (i == 0) {
                            lift += "<a href='/web/products/proinfo.html?spuid=" + pro.spuid + "' target='_blank'>";
                            lift += "<img src=" + pro.img + " width='274' height='330'></a>";
                            //lift += " <div class='l_sjlecon'><p class='fon18'>" + pro.ProName + "</p><div class='l_borsmall'></div><p class='fon22 lett2'>优惠促销</p></div></a>";
                        } else {
                            div += " <div class='l_sjmk'> <div class='l_sjmkimg'>";
                            div += " <a href='/web/products/proinfo.html?spuid=" + pro.spuid + "' target='_blank'><img src=" + pro.img + " width='214px' height='220px'></a></div>";
                            div += "  <div class='l_sjmkcon'> <h3><a href='/web/products/proinfo.html?spuid=" + pro.spuid + "' target='_blank'>" + pro.name + "</a></h3>";

                            div += " <p>价格：￥" + pro.price.toFixed(2) + "</p>";
                            div += "  </div></div>";
                        }

                    }
                    $("#splift"+tips).html(lift);
                    $("#spright"+tips).html(div);
                }
            }
            else {

            }
        },
        error: function (e) {
            //alert(e.statusText);
        }
    });
}
function showTops(data){
	switch(parseInt(data)){
	case 0:
		$("#splift").show();$("#spright").show();
	    $("#splift1").hide();$("#spright1").hide();
	    $("#splift2").hide();$("#spright2").hide();
	    $("#splift3").hide();$("#spright3").hide();
	    break;
	case 1:
		$("#splift").hide();$("#spright").hide();
	    $("#splift1").show();$("#spright1").show();
	    $("#splift2").hide();$("#spright2").hide();
	    $("#splift3").hide();$("#spright3").hide();
	    break;
	case 2:
		$("#splift").hide();$("#spright").hide();
	    $("#splift1").hide();$("#spright1").hide();
	    $("#splift2").show();$("#spright2").show();
	    $("#splift3").hide();$("#spright3").hide();
	    break;
	case 3:
		$("#splift").hide();$("#spright").hide();
	    $("#splift1").hide();$("#spright1").hide();
	    $("#splift2").hide();$("#spright2").hide();
	    $("#splift3").show();$("#spright3").show();
	    break;
	}
}




