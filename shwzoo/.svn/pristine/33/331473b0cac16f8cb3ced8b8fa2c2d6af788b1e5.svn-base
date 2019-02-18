$(function(){
//	fx-footer 底部点击切换
	$('.fx-footer a').click(function(){
		$(this).addClass('current').siblings().removeClass('current');
	});
	$('.sep-byzsq li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	});
	
	$('.sep-dqxz li').click(function(){
		$(this).toggleClass('active').siblings().removeClass('active');
	})
	
	
	
	
})
//  绝对定位占据原来的位置
function fixed(obj){
	var allHeight=$(obj).outerHeight();
	$(obj).after('<div style="height:'+ allHeight+'px;">'+'&nbsp;</div>');
}
function screenHeight(obj){
	var screenHeight=window.screen.availHeight;
	$(obj).css('height',screenHeight+'px');
}

