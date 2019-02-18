$(function(){
	
//	search获取焦点以及失去焦点
	$('.top_searchinput').focus(function(){
//			$('.top_searchico').css({'display':'none',});
			$(this).css({'color':'#000'});
	});
	$('.top_searchinput').blur(function(){
//			$('.top_searchico').css({'display':'inline-block',});
			$(this).css({'color':'#999999'});
	});
	
	$('select, input, textarea').focus(function(){
			$(this).css({'color':'#000'});
	});
	
//	分类
	$('.l_flcon_l ul li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		// var index=$('.l_flcon_l ul li').index(this);
		// $('.l_flcon_r .l_flconmk').eq(index).addClass('active').siblings().removeClass('active');
	});
	
	$('.ranksort_smart').click(function(){
		var $Hasi=$(this).find('i');
		if($Hasi.hasClass("active")){
			$Hasi.removeClass('active');
			$('.ranksort_smartcon').hide();
			$(this).find('span').removeClass('red');
		}else{
			$Hasi.addClass('active');
			$('.ranksort_smartcon').show();
			$(this).find('span').addClass('red');
		};
	});
	$('.ranksort_classify').click(function(){
		var $Hasi=$(this).find('i');
		if($Hasi.hasClass("active")){
			$Hasi.removeClass('active');
			$('.ranksort_classifycon').hide();
			$(this).find('span').removeClass('red');
		}else{
			$Hasi.addClass('active');
			$('.ranksort_classifycon').show();
			$(this).find('span').addClass('red');
		};
	});
	
	$('.top_menu').click(function(){
		$('.top_menucon').toggle();
	});
	
	$('.l_sjqdnrtop03').click(function(){
		$(this).addClass('huise');
		$(this).html('<a href="javascript:;">已签到</a>');
		var objHeight=$('.l_sjqdnrtop03.huise a').outerHeight();
		$('.l_sjqdnrtop03.huise a').css('border-radius',objHeight+'px');
	});


});	

//	半椭圆函数
function borderRadius(obj){
	var objHeight=$(obj).outerHeight();
	$(obj).css('border-radius',objHeight+'px')
};

function Actidraw(obj,obj01){
	var WHeight=$(obj).outerHeight();
	$(obj01).css({'height':(WHeight-3)/2+'px'});
}

function themeStreet(obj,obj01){
	var WHeight=$(obj).outerHeight();
	$(obj01).css({'height':WHeight/2+'px'});
}

function newArrival(obj){
	var objWidth=$(obj).outerWidth();
	$(obj).css('height',objWidth+'px')
}

function goodslist(obj){
	var objWidth=$(obj).outerWidth()*0.7595;
	$(obj).css('height',objWidth+'px');
}

function themestreetL(obj){
	var objWidth=$(obj).outerWidth()*1.215;
	$(obj).css('height',objWidth+'px');
}

function actidrawtopL(obj){
	var objWidth=$(obj).outerWidth()*1.1677;
	$(obj).css('height',objWidth+'px');
}

function listActivities(obj){
	var objWidth=$(obj).outerWidth()*0.5065;
	$(obj).css('height',objWidth+'px');
}


//	获取一组数据中的最大值
function maxArry(obj){
	var hArr=[];
	$(obj).each(function(index){
		var h=$(obj).eq(index).outerHeight();
		hArr[index]=h;
		var maxH=Math.max.apply(null,hArr);
		$(obj).css('height',maxH+'px');		
	})
}

//  绝对定位占据原来的位置
function fixed(obj){
	var allHeight=$(obj).outerHeight();
	$(obj).after('<div style="height:'+ allHeight+'px;">'+'&nbsp;</div>');
}

function fixedbot(obj,obj01){
	var allHeight=$(obj).outerHeight();
	var allHeight01=$(obj01).outerHeight();
	$(obj01).css('bottom',allHeight+'px');	
	$(obj01).after('<div style="height:'+ allHeight01+'px;">'+'&nbsp;</div>');
}

function lynFdpdttop01(obj){
	var objWidth=$(obj).outerWidth()*0.3262;
	$(obj).css('height',objWidth+'px');
}


function fxTime(obj,obj01){
	var WHeight=$(obj).outerHeight();
	$(obj01).css({'line-height':WHeight+'px'});
}
