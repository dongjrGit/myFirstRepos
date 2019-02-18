<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<link href="${ctx }/resource/public/platform/css/index.css" rel="stylesheet" />
<script src="/resource/artTemplate.js"></script>
<script src="/resource/public/platform/js/jquery.min.js"></script>
<script src="/resource/public/commonjs/ECharts/asset/js/esl/esl.js"></script>
<script src="/resource/public/commonjs/ECharts/www/js/echarts.js"></script>
<script src="/resource/public.js"></script>
<script src="/resource/public/platform/js/tj/sy_CreateCharts.js"></script>
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
    </head>
  
<div class="mainright">
    <div class="clear"></div>
    <!--l_xyhdd   begin -->
    <div class="l_xyhdd" style="margin-top:0px;">
        <div class="l_xyhddmk">
            <div class="l_xyhddmkleft"></div>
            <div class="l_xyhddmkright">
                <p class="l_lett">新用户</p>
                <p class="l_bol">${accounts}</p>
            </div>
        </div>
        <div class="l_xyhddmk_1">
            <div class="l_xyhddmkleft_1"></div>
            <div class="l_xyhddmkright">
                <p class="l_lett">新订单</p>
                <p class="l_bol">${orderCount}</p>
            </div>
        </div>
        <div class="l_xyhddmk_2" style=" margin-right:0px;">
            <div class="l_xyhddmkleft_2"></div>
            <div class="l_xyhddmkright">
                <p class="l_lett">新收入</p>
                <p class="l_bol">${orderprice}</p>
            </div>
        </div>
       <!--  <div class="l_xyhddmk_3" style=" margin-right:0px;">
                <div class="l_xyhddmkleft_3"></div>
                <div class="l_xyhddmkright">
                    <p class="l_lett">新访客</p>
                    <p class="l_bol">387</p>
                </div>
            </div> -->
    </div>
    <!--l_xyhdd  stop -->
    <div class="clear"></div>
    <!--商品销量   begin -->
    <div class="spxltj">
        <div class="spxltj-title"><span class="xtb-spxltj"></span>商品销量统计</div><!-- spxltj-title  stop-->
        <div class="spxltjcon">
           <!--  <div id="mainPieD" class="spxltjcon-mk" style="min-height:320px;">

            </div> -->
            <div id="mainPieW" class="spxltjcon-mk" style="min-height:320px;">

            </div><!--spxltjcon-mk  stop -->
            <div id="mainPieM" class="spxltjcon-mk" style="margin-right:0px;min-height:320px;">

            </div>
        </div><!--spxltjcon   stop -->
    </div>

    <div class="clear"></div>
    <!--订单销量   begin -->
    <div class="spxltj">
        <div class="spxltj-title"><span class="xtb-spxltj"></span>店铺销量统计</div><!-- spxltj-title  stop-->
        <div class="spxltjcon">
           <!--  <div id="main" class="spxltjcon-mk" style="min-height:320px;">

            </div> -->
            <div id="mainOrderW" class="spxltjcon-mk" style="min-height:320px;">

            </div><!--spxltjcon-mk  stop -->
            <div id="mainOrderM" class="spxltjcon-mk" style="margin-right:0px;min-height:320px;">

            </div>
        </div><!--spxltjcon   stop -->
    </div>
 
</div><!--mainright stop -->