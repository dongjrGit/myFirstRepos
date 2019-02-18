<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="/wap/css/index.css" rel="stylesheet" />
    <link href="/wap/css/public.css" rel="stylesheet" />
    <link href="/wap/css/iscroll.css" rel="stylesheet" />
    <script src="/js/jquery-1.8.2.min.js"></script>
    <script src="/js/artTemplate.js"></script>
    <script src="/wap/js/iscroll.js"></script>
   
<title>商品评论</title>
</head>
<body>
    <!--container  begin-->
    <div class="container fix">
        <!--l_alltop  begin -->
        <div class="l_alltop fix l_bjlsyz" style="position:relative;">
            <span><img src="/wap/images/tp10.png" class="img-responsive l_maxwid"></span>
            <i><a href="/wap/products/showProDetail">商品</a></i>
            <i><a href="/wap/products/showProInfo">详情</a></i>
            <i class="red">评价</i>
            <div class="floatright l_llzj"><a href="#"><img src="/wap/images/tp27.png" class="img-responsive"></a></div>
            <div class="floatright l_fxtbks"><a href="#"><img src="/wap/images/tp26.png" class="img-responsive"></a></div>
            <!--<div class=" l_fxjtxm"><img src="/images/tp29.png" class="img-responsive"></div> -->
            <!--l_fxtbkscon  begin -->
            <div class="l_fxtbkscon fix" style="display:none;">
                <ul class="fix">
                    <li>
                        <a href="javascript:void(0)">
                            <span><img src="/wap/images/fx01.png" class="img-responsive"></span>
                            <div>分享</div>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <span><img src="/wap/images/fx02.png" class="img-responsive"></span>
                            <div>搜索</div>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <span><img src="/wap/images/fx03.png" class="img-responsive"></span>
                            <div>首页</div>
                        </a>
                    </li>
                </ul>
            </div>
            <!--l_fxtbkscon  stop -->
        </div>
        <!--l_alltop  stop -->
        <!--l_pjnrw  begin -->
        <div class="l_pjnrw fix">
            <ul class="fix" id="commtype" style="height:200px">
                <li class="red">
                    <a href="javascript:void(0)" id="allcoms" datatype="-1">
                        <p>0</p>
                        <p>全部评价</p>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0)" id="goodcoms" datatype="0">
                        <p>0</p>
                        <p>好评</p>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0)" id="midcoms" datatype="1">
                        <p>0</p>
                        <p>中评</p>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0)" id="badcoms" datatype="2">
                        <p>0</p>
                        <p>差评</p>
                    </a>
                </li>               
            </ul>
        </div>
        <!--l_pjnrw stop -->
        <!--l_sppjnr 商品评价  begin -->
        <div class="l_sppjnr fix">
            <div id="wrapper">
                <div id="scroller-content">
                    <div id="scroller-pullDown">
                        <span id="down-icon" class="icon-double-angle-down pull-down-icon"></span>
                        <span id="pullDown-msg" class="pull-down-msg">下拉刷新</span>
                    </div>
                    <ul id="list"></ul>
                    <!--<div id="scroller-pullUp">
                        <span id="up-icon" class="icon-double-angle-up pull-up-icon"></span>
                        <span id="pullUp-msg" class="pull-up-msg">上拉刷新</span>
                    </div>-->
                </div>
            </div>

        </div>
        <script type="text/html" id="conlist">
            {{each list as comm i}}
            <li>
                <div class="l_bbpjmk l_bbyx fix">
                    <div class="l_bbpjmk01">
                        {{toHideStr(comm.UserName)}}  {{comm.CreateDate}}
                        <div class="l_xxcon">
                            {{if comm.Star==5}}
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            {{else if comm.Star==4}}
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star02.png" class="img-responsive"></span>
                            {{else if comm.Star==3}}
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star02.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star02.png" class="img-responsive"></span>
                            {{else if comm.Star==2}}
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star02.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star02.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star02.png" class="img-responsive"></span>
                            {{else if comm.Star==1}}
                            <span><img src="/wap/images/star01.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star02.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star02.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star02.png" class="img-responsive"></span>
                            <span><img src="/wap/images/star02.png" class="img-responsive"></span>
                            {{/if}}
                        </div>
                    </div>
                    <div class="l_bbpjmk02">
                        {{comm.Content}}
                    </div>
                    <div class="l_bbpjmk03 fix">
                        <ul>
                            {{each comm.ShowImgList as imgs j}}
                            <li><a href="javascript:void(0)"><img src="{{imgs.ImgUrl}}" alt="" class="img-responsive"></a></li>
                            {{/each}}
                        </ul>
                    </div>
                    <div class="huise l_bbpjmk04"><span>购买时间 {{comm.PayDate}}</span></div><!--<span>尺码 30</span><span>颜色分类 黄色</span>-->
                </div>
            </li>
            {{/each}}
        </script>

        <!--l_sppjnr 商品评价  stop -->
        <!--l_xmjr  begin -->
        <div class="l_xmjr fix">
            <div class="l_xmjr_l">
                <ul class="fix">
                    <a href="#">
                        <li class="red">
                            <span><img src="/wap/images/spxq01.png" class="img-responsive" alt=""></span>
                            <p>客服</p>
                        </li>
                    </a>
                    <a href="#">
                        <li>
                            <span><img src="/wap/images/spxq02.png" class="img-responsive" alt=""></span>
                            <p>店铺</p>
                        </li>
                    </a>
                    <a href="#">
                        <li>
                            <span><img src="/wap/images/spxq03.png" class="img-responsive" alt=""></span>
                            <p>关注</p>
                        </li>
                    </a>
                    <a href="#">
                        <li>
                            <span><img src="/wap/images/spxq04.png" class="img-responsive" alt=""></span>
                            <p>购物车</p>
                            <div class="l_smxtb">30</div>
                        </li>
                    </a>
                </ul>
            </div><!--l_xmjr_l  stop -->
            <div class="l_xmjr_r">
                <a href="#">加入购物车</a>
            </div><!--l_xmjr_r  stop -->
        </div>
        <script>
            var aHeight = $(".l_xmjr_l").height();
            var bHeight = $(".l_xmjr").height();
            $(".l_xmjr_r").css({ "height": aHeight + "px", "line-height": aHeight + "px" });
            $(".l_xmjr").after("<div id='xmjrhmnr'></div>")
            $("#xmjrhmnr").css({ "height": bHeight + "px", "margin-top": "3%", });
            var cWidth = $(".l_smxtb").width();
            $(".l_smxtb").css({ "height": cWidth + "px", "line-height": cWidth + "px" });
        </script>
        <!--l_xmjr  stop -->
    </div>
    <!--container  stop -->
    <!--l_xsh  回到顶部图标 begin -->
    <div class="l_xsh"><img src="/images/tp23.png" class="img-responsive"></div>
    <!--l_xsh  回到顶部图标 stop -->
</body>
</html>