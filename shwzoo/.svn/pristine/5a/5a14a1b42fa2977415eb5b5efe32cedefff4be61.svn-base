<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>企业注册</title>
    <meta name="title" content="商品管理——到货通知" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link rel="shortcut icon" type="image/x-icon" href="${ctx }/resource/public/seller/images/favicon_shortcut.ico" />
    <link href="${ctx }/resource/ui-dialog.css" rel="stylesheet" />
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/seller/css/common.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/resource/public/seller/css/login.css">
<script type="text/javascript" src="${ctx }/resource/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx }/resource/public/seller/js/common.js"></script>
    <script src="${ctx }/resource/public/commonjs/Guid.js"></script>
    <script src="${ctx }/resource/cookie.js"></script>
    <script type="text/javascript" src="${ctx }/resource/artTemplate.js"></script>
    <script src="${ctx }/resource/ajaxfileupload.js"></script>
    <script src="${ctx }/resource/dialog-min.js"></script>
    <script src="${ctx }/resource/dialogShow.js"></script> <!--弹出框-->
    <!--表单验证css&&js-->
    <link href="${ctx }/resource/public/commoncss/jQuery.Validate.css" rel="stylesheet" />
    <script src="${ctx }/resource/public/commonjs/jquery.validate.js"></script>
    <script src="${ctx }/resource/public/commonjs/messages_cn.js"></script>
    <script src="${ctx }/resource/public/commonjs/jquery.validate-methods.js"></script>

    <script src="${ctx }/resource/public/seller/js/LoginRegister/RegisterCompany.js"></script>

    <style type="text/css">
        html, body {
            height: auto;
            width: 100%;
            overflow: auto;
            margin: 0;
            padding: 0;
        }

        #top {
            width: 100%;
            height: 99px;
            background: #ffffff;
            border-bottom: 1px solid #EAEAEA;
        }

        #main {
            background: #FEF9F5;
            width: 100%;
            margin: 0;
            padding: 0;
        }

            #main .wrapper {
                width: 1200px;
                margin: 0 auto;
                background: url(images/bj-all.jpg) no-repeat;
            }
    </style>
</head>
<body>
<!--top  begin -->
    <div id="top">
        <div class="wrapper">
            <div class="logo"></div>
            <div class="top-right">您好，欢迎光临绿色生活！<span class="lvse"><a href="Login">登录</a></span></div>
        </div><!--wrapper  stop -->
    </div>
    <!--top  stop -->
    <!--main  begin -->
    <div id="main">
        <div class="wrapper">
            <div class="yxzc">
                <span class="yxzc-wz" style="border-top-color:#BFBFBF;margin:0;">
                    <a href="/ylsc/platform/member/showMemberAdd" style="color:#009AF1;">会员注册</a>
                </span>
                <span class="yxzc-wz" style="border-top-color:#0197EC;  margin:0px;">
                    <a href="javascript:void(0);" style="color:#009AF1;">企业注册</a>
                </span>
            </div><!--yxzc  stop -->
            <!--yxyzcon主要内容  开始 -->
            <div class="yxyzcon" style="padding:50px 0px 20px 0px; margin-top:1px;">
                <div class="hyzcqyyh">欢迎注册成为企业用户</div><!--hyzcqyyh  stop -->
                <!-- fwlbcon  begin-->
                <div class="fwlbcon">
                    <div class="fwlbcon-mk">
                        <div class="fwlbcon-mktitle">政策优惠</div>
                        <div class="fwlbcon-mkcon">
                            <ul>
                                <li>1、全免费政策，无成本入驻</li>
                                <li>2、精选优质商家，减少同质竞争</li>
                                <li>3、为商家提供免费品牌展示，广告推广</li>
                            </ul>
                        </div>
                    </div><!--fwlbcon-mk  stop -->
                    <div class="fwlbcon-mk" style="width:360px;">
                        <div class="fwlbcon-mktitle">定制培训</div>
                        <div class="fwlbcon-mkcon">
                            <ul>
                                <li>1、为商家量身定制培训课程</li>
                                <li>2、线下一对一专业运营指导</li>
                                <li>3、专业客服24小时在线</li>
                            </ul>
                        </div>
                    </div><!--fwlbcon-mk  stop -->
                    <div class="fwlbcon-mk" style="width:400px; margin-right:0px;">
                        <div class="fwlbcon-mktitle">服务须知</div>
                        <div class="fwlbcon-mkcon">
                            <ul>
                                <li>我们的审核时限为48小时（工作日），遇法定节假日顺延</li>
                                <li>如您对企业入驻有疑问，或有批量采购需求</li>
                                <li>请拨打企业专享热线：400-689-0088或留言咨询</li>

                            </ul>
                        </div>
                    </div><!--fwlbcon-mk  stop -->
                    <div class="clear"></div>
                </div>
                <!--fwlbcon  stop -->
                <div class="clear"></div>
                <form id="signupForm">
                    <!--zhxx-qyzc 账户信息  begin -->
                    <div class="zhxx-qyzc">
                        <div class="zhxx-qyzctop">账户信息</div><!--zhxx-qyzctop  stop -->
                        <div class="zhxxform">
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>用户名：</div>
                                <div class="zhxxmk-right"><input name="text_username" id="text_username" type="text" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>密码：</div>
                                <div class="zhxxmk-right"><input name="password_pwd" id="password_pwd" type="password" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>确认密码：</div>
                                <div class="zhxxmk-right"><input name="password_pwdagian" id="password_pwdagian" type="password" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="clear"></div>
                        </div><!--zhxxform   stop -->
                        <div class="clear"></div>
                    </div>
                    <!--zhxx-qyzc 账户信息   stop -->
                    <div class="clear"></div>
                    <!--zhxx-qyzc 店铺信息  begin -->
                    <div class="zhxx-qyzc">
                        <div class="zhxx-qyzctop">店铺信息</div><!--zhxx-qyzctop  stop -->
                        <div class="zhxxform">
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>店铺名：</div>
                                <div class="zhxxmk-right"><input name="text_shopname" id="text_shopname" type="text" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="clear"></div>
                        </div><!--zhxxform   stop -->
                        <div class="zhxxmk" id="div_scope">
                            <div class="zhxxmk-left"><span class="red">*</span>经营范围：</div>
                            <div class="zhxxmk-right-szbm">
                                <div class="selcon">
                                    <select id="fc_select" name="fc_select" class="n_inpqyzc fc_select" >
                                        <option value="-1">请选择</option>
                                        <script id="flist" type="text/html">
                                            {{each list as fclass i}}
                                            <option value="{{fclass.id}}" ffullpath-data="{{fclass.FullPath}}">{{fclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>
                                </div><!--selcon   stop -->
                                <div class="selcon">
                                    <select id="sc_select" name="sc_select" class="n_inpqyzc sc_select">
                                        <option value="-1">请选择</option>
                                        <script id="slist" type="text/html">
                                            {{each list as sclass i}}
                                            <option value="{{sclass.id}}" sfullpath-data="{{sclass.FullPath}}">{{sclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>
                                </div><!--selcon   stop -->
                                <div class="selcon">
                                    <select id="tc_select" name="tc_select" class="n_inpqyzc tc_select" >
                                        <option value="-1">请选择</option>
                                        <script id="tlist" type="text/html">
                                            {{each list as tclass i}}
                                            <option value="{{tclass.id}}" tfullpath-data="{{tclass.FullPath}}">{{tclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>
                                </div><!--selcon   stop -->
                            </div>
                        </div><!--zhxxmk  stop -->

                        <div class="clear"></div>
                    </div>
                    <!--zhxx-qyzc 账户信息   stop -->
                    <div class="clear"></div>
                    <!--zhxx-qyzc 联系人信息  begin -->
                    <div class="zhxx-qyzc">
                        <div class="zhxx-qyzctop">联系人信息</div><!--zhxx-qyzctop  stop -->
                        <div class="zhxxform">
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>联系人姓名：</div>
                                <div class="zhxxmk-right"><input name="text_principalname" id="text_principalname" type="text" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>验证手机：</div>
                                <div class="zhxxmk-right"><input name="text_principalmobile" id="text_principalmobile" type="text" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
					        	<div class="zhxxmk-left"><span class="red">*</span>验证码：</div>
					        	<div class="zhxxmk-right">
					        		<input type="text" name="text_imgcode" id="text_imgcode" value="" class="inp-big" style="width:324px;" />
					                <img src="/VerifyCodeServlet" class="refleshverification_img" width="126" height="34" alt="验证码" />
					                                            看不清？ <span class="lvse"><a href="javascript:void(0)" class="refleshverification">换一张</a></span>
					        	</div>
					        </div>
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>短信验证码：</div>
                                <div class="zhxxmk-right-yzm">
                                    <input name="text_mobilecode" id="text_mobilecode" type="text" class="inp-yz-yzm">
                                    <a href="javascript:void(0)" class="g_hqyzm fl" style="color: rgb(0, 0, 0);"><span class="hqyzm-sj-zhxxmk"><b style="display: none;">60</b><font>获取验证码</font></span></a><!--hqyzm-sj-zhxxmk-->
                                </div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left">联系人邮箱：</div>
                                <div class="zhxxmk-right"><input name="text_principalemail" id="text_principalemail" type="text" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->

                            <div class="clear"></div>
                        </div><!--zhxxform   stop -->
                        <div class="clear"></div>
                    </div>
                    <!--zhxx-qyzc 联系人信息   stop -->
                    <div class="clear"></div>
                    <!--zhxx-qyzc 公司信息  begin -->
                    <div class="zhxx-qyzc">
                        <div class="zhxx-qyzctop">公司信息</div><!--zhxx-qyzctop  stop -->
                        <div class="zhxxform">
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>公司名称：</div>
                                <div class="zhxxmk-right"><input name="text_companyname" id="text_companyname" type="text" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>营业执照扫描件：</div>
                                <div class="l_zhxxmk-right" id="div_license">
                                    <input type="file" name="file_license" id="file_license" hidden />
                                    <img src="${ctx }/sv/images/djsc.png" width="107" height="100" id="img_license" name="img_license">
                                </div>
                                <div class="clear"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>组织机构代码证扫描件：</div>
                                <div class="l_zhxxmk-right" id="div_organization">
                                    <input type="file" name="file_organization" id="file_organization" hidden />
                                    <img src="${ctx }/sv/images/djsc.png" width="107" height="100" id="img_organization" name="img_organization">
                                </div>
                                <div class="clear"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>税务登记证扫描件：</div>
                                <div class="l_zhxxmk-right" id="div_tax">
                                    <input type="file" name="file_tax" id="file_tax" hidden />
                                    <img src="${ctx }/sv/images/djsc.png" width="107" height="100" id="img_tax" name="img_tax">
                                </div>
                                <div class="clear"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>公司资质：</div>
                                <div class="l_zhxxmk-right" id="div_certificate">
                                    <input type="file" name="file_certificate" id="file_certificate" hidden />
                                    <img src="${ctx }/sv/images/djsc.png" width="107" height="100" id="img_certificate" name="img_certificate">
                                </div>
                                <div class="clear"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>开户银行许可证：</div>
                                <div class="l_zhxxmk-right" id="div_bank">
                                    <input type="file" name="file_bank" id="file_bank" hidden />
                                    <img src="${ctx }/sv/images/djsc.png" width="107" height="100" id="img_bank" name="img_bank">
                                </div>
                                <div class="clear"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>公司所在地：</div>
                                <div class="zhxxmk-right-szbm">
                                    <div class="selcon">
                                        <select id="select_province" name="select_province" class="n_inpqyzc">
                                            <option value="-1">请选择</option>
                                            <script id="proviceselect" type="text/html">
                                                {{each list as Pro index}}
                                                <option value="{{Pro.Code}}">{{Pro.Name}}</option>
                                                {{/each}}
                                            </script>
                                        </select>
                                    </div><!--selcon   stop -->
                                    <div class="selcon">
                                        <select id="select_city" name="select_city" class="n_inpqyzc">
                                            <option value="-1">请选择</option>
                                            <script id="cityselect" type="text/html">
                                                {{each list as Pro index}}
                                                <option value="{{Pro.Code}}">{{Pro.Name}}</option>
                                                {{/each}}
                                            </script>
                                        </select>
                                    </div><!--selcon   stop -->
                                    <div class="selcon">
                                        <select id="select_area" name="select_area" class="n_inpqyzc">
                                            <option value="-1">请选择</option>
                                            <script id="areaselect" type="text/html">
                                                {{each list as Pro index}}
                                                <option value="{{Pro.Code}}">{{Pro.Name}}</option>
                                                {{/each}}
                                            </script>
                                        </select>
                                    </div><!--selcon   stop -->
                                </div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>公司详细地址：</div>
                                <div class="zhxxmk-right_big"><input name="text_companyadress" id="text_companyadress" type="text" class="l_inp-yz" style="color: rgb(0, 0, 0);"></div>

                            </div>
                            <div class="zhxxmk">
                                <div class="zhxxmk-left">邮政编码：</div>
                                <div class="zhxxmk-right"><input name="text_postcode" id="text_postcode" type="text" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->
                            <!--<div class="zhxxmk" style="height:auto;">
                                <div class="zhxxmk-left" style="height:auto;"><span class="red">*</span>购买类型/用途：</div>
                                <div class="zhxxmk-right-szbm" style="height:auto;">
                                    <ul class="fxk-zhxxmk">
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                        <li><input name="" type="checkbox" value="">IT设备</li>
                                        <li><input name="" type="checkbox" value="">数码通讯</li>
                                    </ul>
                                    <div class="clear"></div>
                                </div>
                                <div class="clear"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left">固定电话：</div>
                                <div class="zhxxmk-right"><input name="text_companytel" id="text_companytel" type="text" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left">传真：</div>
                                <div class="zhxxmk-right"><input name="text_companyfox" id="text_companyfox" type="text" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>公司性质：</div>
                                <div class="zhxxmk-right-szbm">
                                    <div class="selcon">
                                        <select id="select_companytype" name="select_companytype" class="n_inpqyzc">
                                            <option value="-1">请选择</option>
                                            <option value="0">民营</option>
                                            <option value="1">国企</option>
                                            <option value="2">外企</option>
                                            <option value="3">其它</option>
                                        </select>
                                    </div><!--selcon   stop -->
                                </div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left">公司官网：</div>
                                <div class="zhxxmk-right_big"><input name="text_companyweb" id="text_companyweb" type="text" class="l_inp-yz" style="color: rgb(0, 0, 0);"></div>

                            </div>
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>验证码：</div>
                                <div class="zhxxmk-right-yzm" style="width:600px;">
                                    <input name="text_verification" id="text_verification" type="text" class="inp-yz-yzm">
                                    <span class="verification-yzm">
                                        <img src="/ylsc/VerifyCodeServlet" class="refleshverification_img" width="83" height="45" alt="验证码" />
                                    </span>
                                    <span class="lvse">
                                        <a href="javascript:void(0)" class="refleshverification" onclick="refleshverification()">换一张</a>
                                    </span>

                                </div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"></div>
                                <div class="zhxxmk-right"><a id="a_submit" href="javascript:void(0);"><img src="${ctx }/sv/images/zc.png" width="276" height="46" alt="注册"></a></div>
                            </div><!--yxyzmk  stop -->
                            <div class="zhxxmk" style="height:30px; margin:-15px 0px 40px 0px;">
                                <div class="zhxxmk-left" style="height:30px;"></div>
                                <div class="zhxxmk-right-bottom">点击注册，表示同意绿色生活<span class="lvse"><a href="javascript:void(0);">《服务协议》</a></span></div>
                            </div><!--yxyzmk  stop -->
                            <div class="clear"></div>
                        </div><!--zhxxform   stop -->
                        <div class="clear"></div>
                    </div>
                    <!--zhxx-qyzc 公司信息   stop -->
                </form>
            </div>
            <!--yxyzcon主要内容  结束 -->
            <div class="clear"></div>

            <div class="clear"></div>
        </div><!--wrapper  stop -->
    </div>
    <!--main  stop -->
    <div style="height:20px;"></div>
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

        //初始化
        Init.bind();
    });
</script>