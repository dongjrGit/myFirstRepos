// JavaScript Document

/* 导航条 账户设置 鼠标放上去 弹出下拉框 开始*/
$(document).ready(function(){
	$(".account-set").mouseenter(function(){
		$(".zhsz-con").css("display","block")
	});
	$(".account-set").mouseleave(function(){
		$(".zhsz-con").css("display","none")
	});
});
/* 导航条 账户设置 鼠标放上去 弹出下拉框 结束*/

/*选项卡  更换显示样式*/
function setTab(name,cursel,n){
for(i=1;i<=n;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById("con_"+name+"_"+i);
menu.className=i==cursel?"hover":"";
con.style.display=i==cursel?"block":"none";
}
}


$(document).ready(function(){
	//返修退换货第一步  第二步 提交成功
	$(".sqthh-tbp").click(function(){
		$(".sqfx-secstep").css("display","block");
		$(".sqfx-onestep").css("display","none");
	});
	$(".tjsq-img").click(function(){
		$(".success-sqfx").css("display","block");
		$(".sqfx-secstep").css("display","none");
		$(".sqfx-onestep").css("display","none");
	});
	
	//账户设置 个人信息 兴趣爱好  点击 背景变色
	$(".perinfor-xqah ul li").click(function(){
		$(this).css("backgroundColor","#ccc");
	});
	
	//账户设置 分享绑定 点击立刻设置  弹出框
	$(".lksz-fxbd").click(function(){
		$(this).siblings(".lksz-con").css("display","block")
	});
	$(".lksz-con .lksz-con-top .xzshdz-tab-close").click(function(){
		$(".lksz-con").hide();
	});
	
	//账户设置 快捷支付 点击添加  新的内容 覆盖当前内容
	$(".kjzfcon-mk-tjtb p").click(function(){
		$(".kjzf-form").css("display","block");
		$(".kjzf").css("display","none");
	});
	
	//账户设置  账号绑定  点击绑定
	$(".bd-right").click(function(){
		$(this).siblings(".zhbd-sscon").css("display","block");
	});
	
	$(".shrdetial-rm,.zffs-shop").click(function(){
		$(this).css({"borderColor":"#E4393C","borderWidth":"2px"})	
	});
	
	$(".xzshdz-shop").click(function(){
		$(".xzdzcon").css("display","block");
	});
	
	//企业注册  下拉框
	$(".selcon-right").click(function(){
		$(this).siblings(".selcon-main").css("display","block");
	});
});