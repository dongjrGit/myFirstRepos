// JavaScript Document
$(document).ready(function(){
	$('.topimg i').click(function(){
		$('.topimg').css('overflow','hidden');
		$('.topimg, .topimg .w1200').animate({height: '0px'}, "slow");
		$(this).hide();
	})
	//首页顶部 网站导航 鼠标放上去 弹出内容
	$("#l_wzdh").mouseenter(function(){
		$(".l_wzdhcon").addClass("l_seenter");
		$(this).addClass("l_bjbs");
		$(this).find(".l_toprow").addClass("l_toprow01");	
	});
	$("#l_wzdh").mouseleave(function(){
		$(".l_wzdhcon").removeClass("l_seenter");
		$(this).removeClass("l_bjbs");
		$(this).find(".l_toprow").removeClass("l_toprow01");
	});
	//首页顶部 客户服务 鼠标放上去 弹出内容
	$("#l_khfw").mouseenter(function(){
		$(".l_khfwcon").addClass("l_seenter");
		$(this).addClass("l_bjbs");
		$(this).find(".l_toprow").addClass("l_toprow01");	
	});
	$("#l_khfw").mouseleave(function(){
		$(".l_khfwcon").removeClass("l_seenter");
		$(this).removeClass("l_bjbs");
		$(this).find(".l_toprow").removeClass("l_toprow01");
	});	
	
	//通用头部搜索切换
	$('#search-hd .search-input').on('input propertychange',function(){
		var val = $(this).val();
		if(val.length > 0){
			$('#search-hd .pholder').hide(0);
		}else{
			var index = $('#search-bd li.selected').index();
			$('#search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
		}
	})
	$('#search-bd li').click(function(){
		var index = $(this).index();
		$('#search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
		$('#search-hd .search-input').eq(index).show().siblings('.search-input').hide(0);
		$(this).addClass('selected').siblings().removeClass('selected');
		$('#search-hd .search-input').val('');
	});
		
	//平台首页鼠标移到右边的小图片上面有一个白色透明的遮罩层效果
	$(".l_jdtrigmk, .l_ghmzmk").mouseenter(function(){
		$(this).find(".m_hover").css("display","block")	
	});
	$(".l_jdtrigmk, .l_ghmzmk").mouseleave(function(){
		$(this).find(".m_hover").css("display","none")	
	});
	
	//商品列表页图片效果
	$(".l_xdimg").mouseenter(function(){
		$(this).siblings("div").show();
	});
	$(".l_xdimg").mouseleave(function(){
		$(this).siblings("div").hide();	
	});
	
	//商品详情页  更多优惠
	$(".l_hdmc ul li .l_03").click(function(){
		$(".l_hdmc ul li p").toggle();
	});
	
	//商品详情页 左边宝贝分类 点击隐藏显示
	$(".l_bbfl ul li h3 span").click(function(){
		$(this).toggleClass("l_yzk01");
		$(this).parents("li").find(".l_bbflmk").toggle();
	});
	
	//商品详情页  下面评论的回复  显示隐藏
	$(".l_hfdj").click(function(){
		$(this).parents(".l_hfnrall").find(".l_hfnrcon").toggle();
	});
	
	//订单提交页  新增收货地址
	$(".l_xzshdzrig span, .l_xzshdzrig a").click(function(){
		$(".l_xzszall").css("display","block")
	});
	$(".l_gban").click(function(){
		$(".l_xzszall").css("display","none")
	});
	
	//订单提交页 发票信息修改
	$(".l_xzdwfp").click(function(){
		$(".l_fpttnone").css("display","block")
	});
	$(".l_rhmdj").click(function(){
		$(".l_srrhm").css("display","block")
	});
	$(".l_gban").click(function(){
		$(".l_xgfpnr").css("display","none")
	});
	$(".rxqone").click(function(){
		$(".r_shxqcon").toggleClass("r_block");
	});
	
	//支付页面
	$(".r_qtpt").click(function(){
		$(".r_qtzfpt").css("display","block")
		$(this).css("display","none")
		$(this).siblings("p").css("display","none")
	});
	$(".r_sqwz").click(function(){
		$(".r_qtzfpt").css("display","none")
		$(this).parents(".r_zffs").find("p, span").css("display","block")
	});
	$(".r_zf03").click(function(){
		$(".r_cxkall").css("display","block")
	});
	$(".r_zf01").click(function(){
		$(this).toggleClass("active");
		$(this).parents(".r_zffs").toggleClass("active");
	});
	
	/* 导航条 账户设置 鼠标放上去 弹出下拉框 开始*/
	$(".account-set").mouseenter(function(){
		$(".zhsz-con").css("display","block")
	});
	$(".account-set").mouseleave(function(){
		$(".zhsz-con").css("display","none")
	});
	
	/* 导航条 账户设置 鼠标放上去 弹出下拉框 结束*/
	
	//我的订单  右边高度与左边高度相等
	$(".l_ddmkcon").each(function () {
		var rheight = $(this).height();
		$(this).find(".l_ddmk01con").css("height", rheight + "px");
		$(this).find(".l_ddmk02, .l_ddmk03, .l_ddmk04").css("height", rheight - 30 + "px");
	});
	
	//优惠券关闭按钮
	$(".y_lqts .close").mouseleave(function(){
		$(".y_lqts").css("display","none")
	});
	
	//未登录弹出登录框的下面遮罩层   获取高度
//	var hheight=document.body.clientHeight;
//$(".r_loginhui").css("height",hheight+"px");
    
	//所有文本框获取焦点时  字体深色
	$("input[type=text]").focus(function(){
	  $(this).css("color","#000")
	});
	
//	未登录弹出框点击关闭按钮,遮罩层消失
	$('.shop_close').click(function(){
		$('.r_loginhui').hide();
	})
	
	//店铺的首页导航部分  点击全部分类  显示内容
	$(".l_qbfldp").mouseenter(function(){
		$(".l_qbflconall").css("display","block")
	});
	$(".l_qbfldp").mouseleave(function(){
		$(".l_qbflconall").css("display","none")
	});
	
	//发表咨询  点击发表咨询  弹出下面内容
	$(".l_xqyzx").click(function(){
		$(".l_fbzxcon").toggle();
	});
	
	//发表咨询  点击发表咨询  弹出下面内容
	$(".l_ggfhxx").click(function(){
		$(".servicexx-top").css("display","block");
	});
	
	//会员中心---设置-个人信息  选择兴趣爱好
	$(".perinfor-xqah ul li").click(function(){
		$(this).toggleClass("active");
	});
	
	//账户设置  账号绑定  点击绑定
	$(".bd-right").click(function(){
		$(this).siblings(".zhbd-sscon").css("display","block");
	});
	
	//账户设置 分享绑定 点击立刻设置  弹出框
	$(".lksz-fxbd").click(function(){
		$(this).siblings(".lksz-con").css("display","block")
	});
	$(".lksz-con .lksz-con-top .xzshdz-tab-close").click(function(){
		$(".lksz-con").hide();
	});
	
	//会员中心---设置-增值发票  点击添加增票资质
	$(".l_tjzpzzan").click(function(){
		$(".l_txzpzz").css("display","block");
		$(this).parent().css("display","none");
	});
	
	//	掌上购买本商品
	$('#h_appbuy').hover(function(){
		$('.h_appbuycon').show(300);
	},function(){
		$('.h_appbuycon').hide(300);
	})
	
//	列表页排序
	$('.l_spsort01 li').click(function(){
		$('.l_spsort01 li').each(function(){
			$(this).removeClass('l_zhss');
		})
		$(this).toggleClass('l_zhss active');
	})
//	绿色中国地方馆左边导航部分
	$('.xzplace-l ul li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	})
//	绿色中国地方馆  右边展开收起
	$('.xzplacemk-zk').toggle(
	  function () {
	    $(this).addClass("active").text("收起");
	    $(this).parents('.xzplacemk').addClass("active");
	  },
	  function () {
	    $(this).removeClass("active").text("展开");
	    $(this).parents('.xzplacemk').removeClass("active");
	  }
	);
	
	//商品列表页筛选里面  点击多选事件
	$('.l_spsxdx').toggle(
	  function () {
	   $(this).parent().siblings("ul").find("li").prepend('<input name="" type="checkbox" value="">');
			$(this).parent().siblings("ul").find(".l_qdqx").css("display","block");
			$(this).parent().siblings("ul").css({'max-height':'1000px','overflow':'auto'});
	  },
	  function () {
	    $(this).parent().siblings("ul").find('input[type="checkbox"]').remove();
			$(this).parent().siblings("ul").find(".l_qdqx").css("display","none");
			$(this).parent().siblings("ul").css({'max-height':'52px','overflow':'hidden'})
	  }
	);
	$('.l_spsxmore').toggle(
	  function () {
	    $(this).parent().siblings("ul").find('input[type="checkbox"]').remove();
			$(this).parent().siblings("ul").css("display","block").css({'max-height':'1000px','overflow':'auto'});
			$(this).parent().siblings("ul").find(".l_qdqx").css("display","none");
	  },
	  function () {
	    $(this).parent().siblings("ul").find('input[type="checkbox"]').remove();
			$(this).parent().siblings("ul").css("display","block").css({'max-height':'52px','overflow':'hidden'});
			$(this).parent().siblings("ul").find(".l_qdqx").css("display","none");
	  }
	);
	
	$('.zlzq-xmnrul li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	})
	
	$('.sbldq-zffs ul li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	})
	
});

//商品详情页 右边商品详情遇顶固定不动
$(document).ready(function(){
	$.fn.smartFloat = function() {
 var position = function(element) {
  var top = element.position().top, pos = element.css("position");
  $(window).scroll(function() {
   var scrolls = $(this).scrollTop();
   if (scrolls > top) {
	if (window.XMLHttpRequest) {
	 element.css({
	  position: "fixed",
	  top: 0
	 }); 
	} else {
	 element.css({
	  top: scrolls
	 }); 
	}
   }else {
	element.css({
	 position: pos,
	 top: top
	}); 
   }
  });
 };
 return $(this).each(function() {
  position($(this));      
 });
};
$("#fixed").smartFloat();   
});

//商品详情页  推荐搭配 优惠套餐 选项卡和滑动门事件
function nTabs(thisObj,Num){
    if(thisObj.className == "active")return;
    var tabObj = thisObj.parentNode.id;
    var tabList = document.getElementById(tabObj).getElementsByTagName("li");
    for(i=0; i <tabList.length; i++)
    {
      if (i == Num)
      {
       thisObj.className = "active"; 
          document.getElementById(tabObj+"_Content"+i).style.display = "block";
      }else{
       tabList[i].className = "normal"; 
       document.getElementById(tabObj+"_Content"+i).style.display = "none";
      }
    } 
    }

//购物车下面  猜你喜欢 我的关注 浏览历史  选项卡事件 以及图片滚动事件
$(function(){
	$(".yScrollListTitle h1").click(function  () {
		var index=$(this).index(".yScrollListTitle h1");
		$(this).addClass("yth1click").siblings().removeClass("yth1click");
		$($(".yScrollListInList")[index]).show().siblings().hide();
	})
	$(".yScrollListInList1 ul").css({width:$(".yScrollListInList1 ul li").length*(170+50)+"px"});
	$(".yScrollListInList2 ul").css({width:$(".yScrollListInList2 ul li").length*(170+50)+"px"});
	var numwidth=(170+50)*5;
	$(".yScrollListInList .yScrollListbtnl").click(function(){
		var obj=$(this).parent(".yScrollListInList").find("ul");
		if (!(obj.is(":animated"))) {
			var lefts=parseInt(obj.css("left").slice(0,-2));
			if(lefts<30){
				obj.animate({left:lefts+numwidth},1000);
			}
		}
	})
	$(".yScrollListInList .yScrollListbtnr").click(function(){
		var obj=$(this).parent(".yScrollListInList").find("ul");
		var objcds=-(30+(Math.ceil(obj.find("li").length/5)-2)*numwidth);
		if (!(obj.is(":animated"))) {
			var lefts=parseInt(obj.css("left").slice(0,-2));
			if(lefts>objcds){
				obj.animate({left:lefts-numwidth},1000);
			}
		}
	})
})

//店铺首页轮番
$(function(){
	//首页 轮播
	if($('#index-bxslider').length>0){
		$('#index-bxslider').bxSlider({
			auto: true,
			pagerCustom: '#bx-pager',
			nextText: ' ',
			prevText: ' ',
			speed: 600,
			pause: 3000
		});
	};
});
