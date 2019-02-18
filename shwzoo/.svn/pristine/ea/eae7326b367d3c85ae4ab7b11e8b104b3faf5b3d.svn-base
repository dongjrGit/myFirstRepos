<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script src="/resource/artTemplate.js"></script>
<script src="/resource/public/commonjs/showImage.js"></script>
<script src="/resource/public/platform/js/SY/ProSpecialList.js"></script>

<script src="/resource/public/platform/js/jquery.min.js"></script>

<script src="/resource/public/commonjs/ECharts/asset/js/esl/esl.js"></script>
<script src="/resource/public/commonjs/ECharts/www/js/echarts.js"></script>
<script src="/resource/public.js"></script>

<script src="/resource/public/seller/js/mjsy/CreateChart.js"></script>
 <script type="text/javascript" language="javascript">
            // Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
            require.config({
                paths: {
                    echarts: '/resource/public/commonjs/ECharts/www/js'

                }
            });
            // Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
            require(
            [
            'echarts',
            'echarts/chart/pie', //按需加载图表关于饼图的部分
            'echarts/chart/line' //按需加载图表关于线性图的部分
            ],
            //创建ECharts图表
            DrawCharts
            );


        </script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#a_balance").bind("click", function () {
            $("#a_bond").parent().attr("class", "l_shux");
            $("#a_freezemoney").parent().attr("class", "l_shux");
            $(this).parent().attr("class", "l_hoverbal");

            $("#san_show").text($("#hidden_balance").val());
        });

        $("#a_bond").bind("click", function () {
            $("#a_balance").parent().attr("class", "l_shux");
            $("#a_freezemoney").parent().attr("class", "l_shux");
            $(this).parent().attr("class", "l_hoverbal");

            $("#san_show").text($("#hidden_bond").val());
        });

        $("#a_freezemoney").bind("click", function () {
            $("#a_balance").parent().attr("class", "l_shux");
            $("#a_bond").parent().attr("class", "l_shux");
            $(this).parent().attr("class", "l_hoverbal");

            $("#san_show").text($("#hidden_freezemoney").val());
        });
       
       var userid=$("#userid").val();
       $("#btn_govoucher").bind("click", function () {
            window.location.href = "/seller/zhglshop/showAccountVoucher?userid="+userid;
        });
       
       $("#je li").click(function(){
    	   if(this.id=="dj"){
    		   $("#cz").css("display","none");
    	   }else{
    		   $("#cz").css("display","");
    	   }
       });
       
       /* //充值
    	$("input[name=chongzhi]").click(function() {
    		 window.location.href = "/platform/floor/list";
    	}); */
    }) 
</script>

</head>
<body>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：商家后台管理系统 &gt; 首页
        </div><!--所在位置信息  结束 -->
        <div class="clear"></div>
        <input type="hidden" id="userid" value="${userid}" />
        <!--l_balance   begin -->
        <div class="l_balance">
            <div class="l_balanceleft">
                <div class="l_tximg"><img src="${img}"  width="120px" height="115px" ></div><!--l_tximg  stop -->
                <div class="l_tximgcon">
                    <h3>${shopname}</h3>
                    <!--<p>用户级别：普通会员</p>
                    <p>上次登录：2015-10-19 16:28:30</p>-->
                </div><!--l_tximgcon  stop -->
                <div class="clear"></div>
                <!-- <div class="l_zhaq">
                    <span class="lvse">账户安全：</span>
                    <span class="l_hui"><span class="l_lv"></span></span>
                    <span class="l_jddj">一般</span>
                </div><!-- l_zhaq  stop-->
            </div><!--l_balanceleft  stop -->
            <c:if test="${shopowned==true}">
            <div class="l_balanceright">
                <ul id="je">
                    <li class="l_hoverbal">
                        <a href="javascript:void(0)" id="a_balance">账户余额</a>
                    </li>
                    <li class="l_shux">|</li>
                    <li>
                        <a href="javascript:void(0)" id="a_bond">保证金</a>
                    </li>
                    <li class="l_shux">|</li>
                    <li id="dj">
                        <a href="javascript:void(0)" id="a_freezemoney">冻结金额</a>
                    </li>
                </ul>
                <div class="l_yemm">
                    <input type="hidden" id="hidden_balance" value="${balance}" />
                    <input type="hidden" id="hidden_bond" value="${bond}" />
                    <input type="hidden" id="hidden_freezemoney" value="${freezeMonkey}" />
                    <span class="l_mmxh" id="san_show">${balance}</span>
                    <span class="l_xsmm"></span>
                </div><!--l_yemm  stop -->
                <div class="l_cztx" id="cz">
                    <a href="javascript:;" target="_self"><input name="chongzhi" type="button" value="充值" id="btn_govoucher" class="l_czbut"></a>
                    <!-- <a href="javascript:;" target="_self"><input name="tixian" type="button" value="提现" class="l_txbut"></a> -->
                </div><!--l_cztx  stop -->
            </div>
            </c:if>
            <!--l_balanceright  stop -->
            <div class="clear"></div>
        </div>
        
       
        <!--l_balance   stop -->
        <div class="clear"></div>
        <div class="sales-notice">
            <!--店铺订单销量   begin -->
            <div class="sales-notice-left">
                <div class="store-market-title">
                    店铺销售情况
                    <div class="red-xtb"></div>
                </div>
                <div id="main" class="spxltjcon-mk"></div>
            </div>
            <div class="clear"></div>
            <!--商品销量   begin -->
            <div class="l_xstb">
            <!--     <div class="l_xstbmk">
                        <div class="store-market-title">
                            日销量统计
                            <div class="red-xtb"></div>
                        </div>
                        <div id="mainPieD" class="l_xstbcon"></div>
                    </div> -->
                <div class="l_xstbmk">
                    <div class="store-market-title">
                        周销量统计
                        <div class="red-xtb"></div>
                    </div>
                    <div id="mainPieW" class="l_xstbcon"></div>
                </div>
                <div class="l_xstbmk" style="margin-right:0px;">
                    <div class="store-market-title">
                        月销量统计
                        <div class="red-xtb"></div>
                    </div>
                    <div id="mainPieM" class="l_xstbcon"></div>
                </div>
            </div>

        </div>
       

    </div><!--主要内容 右边结束 -->
</div>
</body>