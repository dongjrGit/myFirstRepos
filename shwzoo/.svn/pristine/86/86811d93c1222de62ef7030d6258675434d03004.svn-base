<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/wap/css/Yform.css">
<link rel="stylesheet" type="text/css" href="/wap/css/css.css">
<link href="/wap/css/public.css" rel="stylesheet" />
<link href="/wap/css/index.css" rel="stylesheet" />


<script src="/js/jquery-1.8.2.min.js"></script>
<script src="/wap/js/public.js"></script>
<script src="/js/artTemplate.js"></script>
<script src="/wap/js/products/proInfo.js"></script>

<title>商品介绍</title>
</head>
<body>
	<!--container  begin-->
	<div class="container fix">
		<!--l_alltop  begin -->
		<div class="l_alltop fix l_bjlsyz" style="position: relative;">
			<span><img src="/wap/images/tp10.png"
				class="img-responsive l_maxwid"></span> 
				<i><a href="/wap/products/showProDetail">商品</a></i>
				<i class="red">详情</i><i>
				<a href="/wap/products/showProCommen">评价</a></i>
			<div class="floatright l_llzj">
				<a href="#"><img src="/wap/images/tp27.png"
					class="img-responsive"></a>
			</div>
			<div class="floatright l_fxtbks">
				<a href="#"><img src="/wap/images/tp26.png"
					class="img-responsive"></a>
			</div>
			<!--<div class=" l_fxjtxm"><img src="/wap/images/tp29.png" class="img-responsive"></div> -->
			<!--l_fxtbkscon  begin -->
			<div class="l_fxtbkscon fix" style="display: none;">
				<ul class="fix">
					<li><a href="#"> <span><img
								src="/wap/images/fx01.png" class="img-responsive"></span>
							<div>分享</div>
					</a></li>
					<li><a href="#"> <span><img
								src="/wap/images/fx02.png" class="img-responsive"></span>
							<div>搜索</div>
					</a></li>
					<li><a href="#"> <span><img
								src="/wap/images/fx03.png" class="img-responsive"></span>
							<div>首页</div>
					</a></li>
				</ul>
			</div>
			<!--l_fxtbkscon  stop -->
		</div>
		<!--l_alltop  stop -->
		<!--r_bjbs  begin -->
		<div class="r_bjbs fix">
			<!--r_bzshnr  begin-->
			<div class="r_bzshnr fix" id="prodtap">
				<ul>
					<li class="red" tagdata="0"><a href="javascript:void(0)">商品介绍</a></li>
					<li tagdata="1"><a href="javascript:void(0)">规格参数</a></li>
					<li class="borno" tagdata="2"><a href="javascript:void(0)">售后服务</a></li>
				</ul>
			</div>
			<!--r_bzshnr  stop-->
			<!--r_bzqdcon  begin -->
			<div class="r_spjsimg" id="spuInfo"></div>
			<div class="r_ggcsnr fix" id="spuspecs" style="display: none">
				<table id="dataspecs">
					<script type="text/html" id="skuspecs">
                        {{each list as spe i}} 
                        <tr>
                            <td>{{spe.name}}</td>
                            <td>{{spe.Value}}</td>
                        </tr>                        
                        {{/each}}
                    </script>
				</table>
			</div>

			<div class="r_bzqdcon fix" id="spupacking" style="display: none">

			</div>
			<!--r_bzqdcon  stop -->
		</div>
		<!--r_bjbs  stop -->
		<!--l_xmjr  begin -->
		<div class="l_xmjr fix">
			<div class="l_xmjr_l">
				<ul class="fix">
					<a href="#">
						<li class="red"><span><img
								src="/wap/images/spxq01.png" class="img-responsive" alt=""></span>
							<p>客服</p></li>
					</a>
					<a href="#">
						<li><span><img src="/wap/images/spxq02.png"
								class="img-responsive" alt=""></span>
							<p>店铺</p></li>
					</a>
					<a href="#">
						<li><span><img src="/wap/images/spxq03.png"
								class="img-responsive" alt=""></span>
							<p>关注</p></li>
					</a>
					<a href="#">
						<li><span><img src="/wap/images/spxq04.png"
								class="img-responsive" alt=""></span>
							<p>购物车</p>
							<div class="l_smxtb">30</div></li>
					</a>
				</ul>
			</div>
			<!--l_xmjr_l  stop -->
			<div class="l_xmjr_r">
				<a href="#">加入购物车</a>
			</div>
			<!--l_xmjr_r  stop -->
		</div>
		<script>
			var aHeight = $(".l_xmjr_l").height();
			var bHeight = $(".l_xmjr").height();
			$(".l_xmjr_r").css({
				"height" : aHeight + "px",
				"line-height" : aHeight + "px"
			});
			$(".l_xmjr").after("<div id='xmjrhmnr'></div>")
			$("#xmjrhmnr").css({
				"height" : bHeight + "px",
				"margin-top" : "3%",
			});
			var cWidth = $(".l_smxtb").width();
			$(".l_smxtb").css({
				"height" : cWidth + "px",
				"line-height" : cWidth + "px"
			});
		</script>
		<!--l_xmjr  stop -->
	</div>
	<!--container  stop -->
</body>
</html>