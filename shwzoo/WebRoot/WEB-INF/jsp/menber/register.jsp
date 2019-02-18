<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>会员注册</title>
    <meta name="title" content="" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link type="text/css" rel="stylesheet" href="${ctx }/css/mv/common.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/css/mv/top-footer.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/css/mv/index.css">
    <script type="text/javascript" src="${ctx }/js/jquery-1.8.2.min.js"></script>
    <script language="javascript" type="text/javascript" src="${ctx }/mv/index.js"></script>

    <script src="${ctx }/js/Guid.js"></script>
    <script src="${ctx }/js/cookie.js"></script>
    <script type="text/javascript" src="${ctx }/js/artTemplate.js"></script>
    <script src="${ctx }/js/AjaxFileUploaderV2.1/ajaxfileupload.js"></script>
    <!--弹出框-->
    <link href="${ctx }/css/ui-dialog.css" rel="stylesheet" />
    <script src="${ctx }/js/dialog-min.js"></script>
    <script src="${ctx }/js/dialogShow.js"></script> 
       <!--表单验证css&&js-->
    <link href="${ctx }/css/jQuery.Validate.css" rel="stylesheet" />
    <script src="${ctx }/scripts/jquery.validate.js"></script>
    <script src="${ctx }/scripts/messages_cn.js"></script>
    <script src="${ctx }/scripts/jquery.validate-methods.js"></script>

    
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=v66Wo0IkRp2uWVi5RNsaj656"></script>
    <script src="${ctx }/sv/js/LoginRegister/Register.js"></script>
</head>

<body bgcolor="#F5F5F5">
    <!--dl_top  begin -->
    <div class="dl_top">
        <div class="w1100">
            <div class="dl_top01"><a href="/web/sy/Sypt"><img src="${ctx }/sv/images/logo.png"></a></div>
            <div class="dl_top02">欢迎注册</div>
        </div>
    </div>
    <!--dl_top  stop -->
    <div class="dl_nr fix">
        <div class="w1100 zc_nr fix">
            <!--zc_nrleft  begin -->
            <div class="zc_nrleft">
                <div class="zc_nrtop">
                    <div class="zc_nrtopmk"><i></i>个人用户<div class="active"><i></i></div></div>
                    <div class="zc_nrtopmk nobor" onclick="location.href = '${ctx }/seller/register'"><i></i>企业用户</div>
                </div><!-- zc_nrtop  stop-->
                <div class="zx_nrcon fix">
                    <form id="signupForm">
                        <ul>
                            <li><label><span class="red">*</span>用户名：</label><input name="text_username" id="text_username" type="text" class="zc_inp"></li>
                            <li><label><span class="red">*</span>设置密码：</label><input name="password_pwd" id="password_pwd" type="password" class="zc_inp"></li>
                            <li><label><span class="red">*</span>请确认密码：</label><input name="password_pwdagain" id="password_pwdagain" type="password" class="zc_inp"></li>
                            <li><label><span class="red">*</span>验证手机：</label><input name="text_mobile" id="text_mobile" type="text" class="zc_inp"><span class="yscmh ml10">或</span><span class="red ml5"><a href="#">验证邮箱</a></span></li>
                            <li>
                                <label><span class="red">*</span>验证码：</label>
                                <input name="text_verification" id="text_verification" type="text" class="zc_inp small">
                                <span class="ml10">
                                	<img src="/VerifyCodeServlet" class="refleshverification_img" width="102" height="38" alt="验证码" />
                                </span>
                                <span class="ml10 yscmh">看不清？</span>
                                <span class="red">
                                	<a href="javascript:void(0);" class="refleshverification" onclick="MemberRegister.refleshverification();">换一张</a>
                                </span>
                            </li>
                            <li>
                                <label><span class="red">*</span>短信验证码：</label>
                                <input name="text_mobilecode" id="text_mobilecode" type="text" class="zc_inp small">
                                <span class="ml10 zc_hqdzyzm">
                                    
                                    <a href="javascript:void(0);" class="g_hqyzm" style="color: rgb(0, 0, 0);"><span class="hqyzm-sj"><b style="display: none;">60</b><font>获取短信验证码</font></span></a>
                                </span>
                                <!-- <div class="zhxxmk"> -->
                                
                            <!-- </div>zhxxmk  stop -->
                            </li>
                        </ul>
                        <div class="zc_ydty fix">
                            <div><input name="" type="checkbox" value="" checked>我已阅读并同意<span class="red"><a href="javascript:void(0);">《用户注册协议》</a></span></div>
                            <div class="dl_lja mt30 uerregister" style="width:274px;"><a href="javascript:void(0);">立即注册</a></div>
                        </div><!--zc_ydty  stop -->
                    </form>
                </div><!--zx_nrcon  stop -->
            </div>
            <!--zc_nrleft  stop -->
            <div class="zc_nrright">
                <div class="zc_nrrigcon">
                    <img src="${ctx }/sv/images/tp_23.png">
                    <p class="fon16 mt15">我已经注册，现在就</p>
                    <div class="dl_lja dl_ybdl"><a href="Login">登录</a></div>
                </div>
            </div>
            <!--zc_nrright   stop -->
        </div>
    </div>
    <!--footer  底部开始 -->
    <div id="footer" class="fix">
        <!--footcon  begin -->
        <div class="footcon">
            <!--l_foottop  begin -->
            <div class="l_foottop">
                <div class="l_sex">
                    <div class="l_sexcircle">正</div>
                    <div class="l_sexwzjs">
                        <h3>正品保障</h3>
                        <p>正品行货 放心选购</p>
                    </div>
                </div><!--l_sex  stop -->
                <div class="l_sex">
                    <div class="l_sexcircle">满</div>
                    <div class="l_sexwzjs">
                        <h3>满68包邮</h3>
                        <p>满68元 免运费</p>
                    </div>
                </div><!--l_sex  stop -->
                <div class="l_sex">
                    <div class="l_sexcircle">七</div>
                    <div class="l_sexwzjs">
                        <h3>售后无忧</h3>
                        <p>7天无理由退货</p>
                    </div>
                </div><!--l_sex  stop -->
                <div class="l_sex">
                    <div class="l_sexcircle">准</div>
                    <div class="l_sexwzjs">
                        <h3>准时送达</h3>
                        <p>收获时间由你做主</p>
                    </div>
                </div><!--l_sex  stop -->
                <div class="clear"></div>
            </div>
            <!--l_foottop  stop -->
            <!--l_process  begin -->
            <div class="l_process">
                <!--l_procon  begin -->
                <div class="l_procon">
                    <div class="l_proleft">
                        <div class="l_promk">
                            <p>购物流程</p>
                            <ul>
                                <li><a href="#">购物流程</a></li>
                                <li><a href="#">会员介绍</a></li>
                                <li><a href="#">团购</a></li>
                                <li><a href="#">常见问题</a></li>
                                <li><a href="#">家电</a></li>
                                <li><a href="#">联系客服</a></li>
                            </ul>
                        </div><!--l_promk  stop -->
                        <div class="l_promk">
                            <p>配送方式</p>
                            <ul>
                                <li><a href="#">上门自提</a></li>
                                <li><a href="#">配送服务查询</a></li>
                                <li><a href="#">配送费收取标准</a></li>
                            </ul>
                        </div><!--l_promk  stop -->
                        <div class="l_promk">
                            <p>支付方式</p>
                            <ul>
                                <li><a href="#">货到付款</a></li>
                                <li><a href="#">在线支付</a></li>
                                <li><a href="#">分期付款</a></li>
                                <li><a href="#">邮局汇款</a></li>
                                <li><a href="#">公司转账</a></li>
                            </ul>
                        </div><!--l_promk  stop -->
                        <div class="l_promk">
                            <p>售后服务</p>
                            <ul>
                                <li><a href="#">售后政策</a></li>
                                <li><a href="#">价格保护</a></li>
                                <li><a href="#">退款说明</a></li>
                                <li><a href="#">返修/退换货</a></li>
                                <li><a href="#">取消订单</a></li>
                            </ul>
                        </div><!--l_promk  stop -->
                        <div class="clear"></div>
                    </div><!-- l_proleft  stop-->
                    <div class="l_proright">
                        <div class="l_weibo">
                            <p>中绿微博</p>
                            <div class="l_wbbs"><img src="${ctx }/sv/images/weibo.png" width="102" height="102"></div>
                        </div><!--l_weibo  stop -->
                        <div class="l_weixin">
                            <p>中绿微信</p>
                            <div class="l_wbbs"><img src="${ctx }/sv/images/weixin.png" width="102" height="102"></div>
                        </div><!--l_weibo  stop -->
                    </div><!--l_proright  stop -->
                    <div class="clear"></div>
                </div>
                <!--l_procon  stop -->
            </div>
            <!--l_process  stop -->
            <!--l_gywm 关于我们开始 -->
            <div class="l_gywm">
                <p class="links">
                    <a href="#">关于我们</a>|
                    <a href="#">帮助中心</a>|
                    <a href="#">友情链接</a>|
                    <a href="#">诚征英才</a>|
                    <a href="#">商家登录</a>|
                    <a href="#">供应商登录</a>|
                    <a href="#">联系我们</a>|
                    <a href="#">网站合作</a>|
                    <a href="#">开放平台</a>|
                    <a href="#">法律声明</a>
                </p>
                <p class="l_xkz">
                    京ICP备00000000号<span>|</span>
                    <a href="#">营业执照</a><span>|</span>
                    <a href="#">网络文化经营许可证</a><span>|</span>
                    <a href="#">互联网药品交易服务资格证</a>
                </p>
                <p>Copyright&copy;&amp; 中绿生活 2014-2018，All Rights Reserved</p>
            </div>
            <!--l_gywm 关于我们结束 -->
            <!--l_kxwz  begin -->
            <div class="l_kxwz">
                <a href="#"><img src="/web_new/images/54b8871eNa9a7067e.png" width="103" height="32" alt="经营性网站备案中心" title="经营性网站备案中心"></a>
                <a href="#"><img src="/web_new/images/54b8871eNa9a7067e4444.png" width="103" height="32" alt="可信网站信用评价" title="可信网站信用评价"></a>
                <a href="#"><img src="/web_new/images/54b8874bN694454a5.png" width="103" height="32"></a>
                <a href="#"><img src="/web_new/images/54b8875fNad1e0c4c.png" width="103" height="32"></a>
                <a href="#"><img src="/web_new/images/54b8863dN8d2c61ec.png" width="185" height="32"></a>
            </div>
            <!--l_kxwz  stop -->
        </div>
        <!--footcon  stop -->
    </div>
    <!--footer  底部结束 -->
</body>
</html>
<script type="text/javascript">
    $(document).ready(function (e) {
        //样式添加
        $("input[type=text]").focus(function () {
            $(this).css("color", "#000")
        });
        $("input[type=password]").focus(function () {
            $(this).css("color", "#000")
        });
        /* $("#allmap").hide();  */
        //初始化
        Init.bind();
        
    });
</script>


