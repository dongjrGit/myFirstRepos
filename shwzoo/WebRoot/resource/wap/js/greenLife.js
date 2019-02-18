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

//	分类
	$('.l_flcon_l ul li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		var index=$('.l_flcon_l ul li').index(this);
		$('.l_flcon_r .l_flconmk').eq(index).addClass('active').siblings().removeClass('active');
	});
	
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
//	半椭圆函数
function borderRadius(obj){
	var objHeight=$(obj).outerHeight();
	$(obj).css('border-radius',objHeight+'px')
};

