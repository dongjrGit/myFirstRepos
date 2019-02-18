<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>

<html>
<head>
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
    <link href="/resource/public/commoncss/jQuery.Validate.css" rel="stylesheet" />
    <script src="/resource/public/commonjs/jquery.validate.js"></script>
    <script src="/resource/public/commonjs/messages_cn.js"></script>
    <script src="/resource/public/commonjs/jquery.validate-methods.js"></script>
    
     <script src="/resource/public/commonjs/baseRoot.js"></script>
    <script src="/resource/public/seller/js/LoginRegister/RegisterCompany.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=v66Wo0IkRp2uWVi5RNsaj656"></script>
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
                /* background: url(/images/bj-all.jpg) no-repeat; */
            }
            
            .l_zhxxmk-right{
            width: 115px;
            }
            .clear{
               padding-left: 465px;color: red;
            }
    </style>
    
</head>


<body>
    <!--top  begin -->
    <div id="top">
        <div class="wrapper">
            <div class="logos" style="margin-top: 10px;">
				<a href="/index.html"><img src="/resource/pc/web/images/logo.png" width="202"
					height="80" /></a>
			</div>
            <div class="top-right">您好，欢迎光临中绿生活！<span class="lvse"><a href="${ctx }/seller/login">登录</a></span></div>
        </div><!--wrapper  stop -->
    </div>
    <!--top  stop -->
    <!--main  begin -->
    <div id="main">
        <div class="wrapper">
            <div class="yxzc">
               <%--  <span class="yxzc-wz" style="border-top-color:#BFBFBF;margin:0;">
                    <a href="${ctx }/seller/registers" style="color:#009AF1;">会员注册</a>
                </span> --%>
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
                                <li>1、为商家量身定制入驻培训</li>
                                <li>2、线下一对一专业入驻指导</li>
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
                        <input type="hidden" id="imgsrc" value="<%=path %>" />
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
                          <div class="zhxxmk fix" style="height:auto;">
                                <div class="zhxxmk-left"><span class="red">*</span>店铺简介：</div>
                                <div class="l_zhxxmk-right">
                                <textarea name="text_description" id="text_description"  class="" style="width: 500px;height: 150px"></textarea>
                                </div>
                            </div>
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>客服电话：</div>
                                <div class="zhxxmk-right"><input name="text_supporttel" id="text_supporttel" type="text" class="inp-yz"></div>
                            </div>
                            
                          <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>店铺图片：</div>
                                <div class="l_zhxxmk-right" id="div_shopimage">
                                    <input type="file" name="pic" id="file_shopimage" hidden />
                                      <img src="/resource/public/seller/images/djsc.png" width="107" height="100" id="img_shopimage" name="img_shopimage">
                                      <input type="hidden" name="shopimage" value="${shop.imgurl }" id="shopimage" />
                                </div>
                                <div class="clear"></div>
                            </div>
                            
                           <%--  <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left">店铺分类：</div>
                                <div class="zhxxmk-right-szbm">
                                <div class="selcon">
                                    <select id="ci_select" name="ci_select" class="n_inpqyzc ci_select">
                                        <option value="-1">请选择</option>
                                         <c:forEach items="${categorys }" var="category">
                                         <option value="${category.id }">${category.name }</option>
                                         </c:forEach>
                                    </select>
                                </div>
                                </div>
                                <div class="clear"></div>
                            </div> --%>
                        
                        <div class="zhxxmk" id="div_scope">
                            <div class="zhxxmk-left"><span class="red">*</span>经营范围：</div>
                            <div class="zhxxmk-right-szbm">
                                <div class="selcon">
                                    <select id="fc_select" name="fc_select" class="n_inpqyzc fc_select">
                                        <option value="-1">请选择</option>
                                        <script id="flist" type="text/html">
                                            {{each list as fclass i}}
                                            <option value="{{fclass.id}}" ffullpath-data="{{fclass.fullpath}}">{{fclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>
                                </div><!--selcon   stop -->
                                <div class="selcon">
                                    <select id="sc_select" name="sc_select" class="n_inpqyzc sc_select">
                                        <option value="-1">请选择</option>
                                        <script id="slist" type="text/html">
												<option value="-1">全部</option>
                                            {{each list as sclass i}}
                                            <option value="{{sclass.id}}" sfullpath-data="{{sclass.fullpath}}">{{sclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>
                                </div><!--selcon   stop -->
                                <div class="selcon">
                                    <select id="tc_select" name="tc_select" class="n_inpqyzc tc_select">
                                        <option value="-1">请选择</option>
                                        <script id="tlist" type="text/html">
												<option value="-1">全部</option>
                                            {{each list as tclass i}}
                                            <option value="{{tclass.id}}" tfullpath-data="{{tclass.fullpath}}">{{tclass.name}}</option>
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
                                <div class="zhxxmk-left"><span class="red"></span>联系人邮箱：</div>
                                <div class="zhxxmk-right"><input name="text_principalemail" id="text_principalemail" type="text" class="inp-yz"></div>
                            </div><!--zhxxmk  stop -->

                            <div class="clear"></div>
                        </div><!--zhxxform   stop -->
                        <div class="clear"></div>
                    </div>
                    
                    
                     <!-- <div class="zhxx-qyzc">
                        <div class="zhxx-qyzctop">开户行信息</div>zhxx-qyzctop  stop
                        <div class="zhxxform">
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red"></span>持卡人姓名：</div>
                                <div class="zhxxmk-right"><input name="text_hodername" id="text_hodername" type="text" class="inp-yz"></div>
                            </div>zhxxmk  stop
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red"></span>银行卡卡号：</div>
                                <div class="zhxxmk-right"><input name="text_bankcardno" id="text_bankcardno" type="text" class="inp-yz"></div>
                            </div>zhxxmk  stop
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red"></span>开户行名称：</div>
                                <div class="zhxxmk-right"><input name="text_bankname" id="text_bankname" type="text" class="inp-yz"></div>
                            </div>zhxxmk  stop
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red"></span>联行号：</div>
                                <div class="zhxxmk-right"><input name="text_lineno" id="text_lineno" type="text" class="inp-yz"></div>
                            </div>zhxxmk  stop
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red"></span>银行类别编码：</div>
                                <div class="zhxxmk-right"><input name="text_banktype" id="text_banktype" type="text" class="inp-yz"></div>
                            </div>zhxxmk  stop
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red"></span>银行账户类型：</div>
                                <div class="zhxxmk-right-szbm">
                                    <div class="selcon">
                                        <select id="select_accounttype" name="select_accounttype" class="n_inpqyzc">
                                            <option value="-1">请选择</option>
                                            <option value="0">对私账户</option>
										    <option value="1">对公账户</option>
                                        </select>
                                    </div>selcon   stop
                                </div>
                            </div>
                            
                            <div class="clear"></div>
                        </div>zhxxform   stop
                        <div class="clear"></div>
                    </div> -->
                    
                    
                    <!--zhxx-qyzc 联系人信息   stop -->
                    <div class="clear"></div>
                    <!--zhxx-qyzc 公司信息  begin -->
                    <div class="zhxx-qyzc">
                        <div class="zhxx-qyzctop">公司信息</div><!--zhxx-qyzctop  stop -->
                        <div class="zhxxform">
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>公司名称：</div>
                                <div class="zhxxmk-right"><input name="text_companyname" id="text_companyname" type="text" class="inp-yz"></div>
                            </div>
                            <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>营业执照、组织机构代码证、税务登记证：</div>
                                <div class="l_zhxxmk-right" id="div_license">
                                    <input type="file" name="pic" id="file_license" hidden />
                                      <img src="/resource/public/seller/images/djsc.png" width="107" height="100" id="img_license" name="img_license">
                                      <input type="hidden" name="license" value="${shopAuthentication.license }" id="license" />
                                </div>
                                 <div class="l_zhxxmk-right" id="div_organization">
                                    <input type="file" name="file_organization" id="file_organization" hidden />
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" width="107" height="100" id="img_organization" name="img_organization">
                                    <input type="hidden" name="organization" value="${shopAuthentication.organization }" id="organization" />
                                </div>
                                <div class="l_zhxxmk-right" id="div_tax">
                                    <input type="file" name="file_tax" id="file_tax" hidden />
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" width="107" height="100" id="img_tax" name="img_tax">
                                    <input type="hidden" name="tax" value="${shopAuthentication.tax }" id="tax" />
                               	 </div>
                                <div class="clear"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>开户银行许可证：</div>
                                <div class="l_zhxxmk-right" id="div_bank">
                                    <input type="file" name="file_bank" id="file_bank" hidden />
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" width="107" height="100" id="img_bank" name="img_bank">
                                     <input type="hidden" name="bank" value="${shopAuthentication.bank }" id="bank" />
                                </div>
                                <div class="clear"></div>
                            </div><!--zhxxmk  stop --> 
                            <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>三品一标证书：</div>
                                <input type="file" name="file_spy" id="file_spy" hidden />
                                <div class="l_zhxxmk-right" id="div_bank">
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" width="107" height="100" class="img_add">
                                     <input type="hidden" name="spy" value="${shopAuthentication.bank }" id="spy" />
                                </div>
                                <div class="clear" id="spy_msg"></div>
                            </div><!--zhxxmk  stop --> 
                            
                             <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>生产许可证或流通许可证：</div>
                                <input type="file" name="file_scxkz" id="file_scxkz" hidden />
                                <div class="l_zhxxmk-right">
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" width="107" height="100" class="img_add">
                                    <input type="hidden" name="scxkz" value="${shopAuthentication.tax }" id="scxkz" />
                                </div>
                                 <div class="l_zhxxmk-right">
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" width="107" height="100"  class="img_add">
                                    <input type="hidden" name="scxkz" value="${shopAuthentication.tax }" id="scxkz" />
                                </div>
                                <div class="clear" id="scxkz_msg"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red">*</span>近期产品检测报告：</div>
                               	<input type="file" name="file_jcbg" id="file_jcbg" hidden />
                                <div class="l_zhxxmk-right">
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" class="img_add" width="107" height="100" >
                                    <input type="hidden" name="jcbg" value="${shopAuthentication.tax }" id="jcbg" />
                                </div>
                                 <div class="l_zhxxmk-right">
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" class="img_add" width="107" height="100"  >
                                    <input type="hidden" name="jcbg" value="${shopAuthentication.tax }" id="jcbg" />
                                </div>
                                 <div class="l_zhxxmk-right" >
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" class="img_add" width="107" height="100" >
                                    <input type="hidden" name="jcbg" value="${shopAuthentication.tax }" id="jcbg" />
                                </div>
                                <div class="clear" id="jcbg_msg"></div>
                            </div><!--zhxxmk  stop -->
                            <div class="l_zhxxmk">
                                <div class="l_zhxxmk-left"><span class="red"></span>其它资质：</div>
                                <input type="file"   name="file_certificate" id="file_certificate" hidden />
                                    
                                <div class="l_zhxxmk-right" id="div_certificate">
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" width="107" height="100" class="img_add" id="img_certificate_1" name="img_certificate">
                                    <input type="hidden" name="certificate" id="certificate" />
                                </div>
                                
                                 <div class="l_zhxxmk-right" id="div_certificate">
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" width="107" height="100" class="img_add" id="img_certificate_2" name="img_certificate">
                                    <input type="hidden" name="certificate"  id="certificate" />
                                </div>
                                
                                 <div class="l_zhxxmk-right" id="div_certificate">
                                    <img src="${ctx }/resource/public/seller/images/djsc.png" width="107" height="100" class="img_add" id="img_certificate_3" name="img_certificate">
                                    <input type="hidden" name="certificate" id="certificate" />
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
                                                <option value="{{Pro.code}}">{{Pro.name}}</option>
                                                {{/each}}
                                            </script>
                                        </select>
                                    </div><!--selcon   stop -->
                                    <div class="selcon">
                                        <select id="select_city" name="select_city" class="n_inpqyzc">
                                            <option value="-1">请选择</option>
                                            <script id="cityselect" type="text/html">
                                                {{each list as Pro index}}
                                                <option value="{{Pro.code}}">{{Pro.name}}</option>
                                                {{/each}}
                                            </script>
                                        </select>
                                    </div><!--selcon   stop -->
                                    <div class="selcon">
                                        <select id="select_area" name="select_area" class="n_inpqyzc">
                                            <option value="-1">请选择</option>
                                            <script id="areaselect" type="text/html">
                                                {{each list as Pro index}}
                                                <option value="{{Pro.code}}">{{Pro.name}}</option>
                                                {{/each}}
                                            </script>
                                        </select>
                                    </div><!--selcon   stop -->
                                </div>
                            </div><!--zhxxmk  stop -->
                            
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>公司详细地址：</div>
                                <div class="zhxxmk-right_big">
                                <input name="text_companyadress" id="text_companyadress" type="text" class="l_inp-yz" style="color: rgb(0, 0, 0);">
                               	
                                </div>
                                <input type="button" id="button"  value="查询" style="float:left; margin-left:10px; background:#0080ff; border:1px solid #ccc; height:45px; line-height:45px; padding:0px 10px;" />
                            </div>
                            
                            <div class="zhxxmk" style="height: 300px;">
                            <div class="zhxxmk-left"></div>
                            <div class="zhxxmk-right_big" style="background:none;">
                               <div id="allmap" style="width: 300px;height: 300px;"></div>
                             </div>     
                             </div>

                            
                            <div class="zhxxmk">
                                <div class="zhxxmk-left">邮政编码：</div>
                                <div class="zhxxmk-right"><input name="text_postcode" id="text_postcode" type="text" class="inp-yz"></div>
                            </div>                           
                            <div class="zhxxmk">
                                <div class="zhxxmk-left">固定电话：</div>
                                <div class="zhxxmk-right"><input name="text_companytel" id="text_companytel" type="text" class="inp-yz"></div>
                            </div>
                            
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>结算类型：</div>
                                <div class="zhxxmk-right">
                                	<select id="settlementType" name="settlementType" style="width:273px" class="n_inpqyzc">
                                            <option value="-1">请选择</option>
											<option value="1" >月</option>
											<option value="3" >季度</option>
											<option value="0" >年</option>
                                	 </select>
                                </div>
                            </div>
                            
                            <!--zhxxmk  stop -->
                             <div class="zhxxmk">
                                <div class="zhxxmk-left">传真：</div>
                                <div class="zhxxmk-right"><input name="text_companyfox" id="text_companyfox" type="text" class="inp-yz"></div>
                            </div>
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
                                    </div>
                                </div>
                            </div>
                            <div class="zhxxmk">
                                <div class="zhxxmk-left">公司官网：</div>
                                <div class="zhxxmk-right_big"><input name="text_companyweb" id="text_companyweb" type="text" class="l_inp-yz" style="color: rgb(0, 0, 0);"></div>
                            </div>
                             <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red"></span>公司年销售额：</div>
                                <div class="zhxxmk-right"><input name="text_companysales" id="text_companysales" type="text" class="l_inp-yz" /></div>

                            </div> 
                            
                            <div class="zhxxmk">
                                <div class="zhxxmk-left"><span class="red">*</span>验证码：</div>
                                <div class="zhxxmk-right-yzm" style="width:600px;">
                                    <input name="text_verification" id="text_verification" type="text" class="inp-yz-yzm">
                                    <span class="verification-yzm">
                                        <img src="/VerifyCodeServlet" class="refleshverification_img" width="83" height="45" alt="验证码" />
                                    </span>
                                    <span class="lvse">
                                        <a href="javascript:void(0)" class="refleshverification">换一张</a>
                                    </span>

                                </div>
                            </div><!--zhxxmk  stop -->
                            <div class="zhxxmk" style="height: 30px">
                             <div  class="zhxxmk-right-bottom" style="width: 350px;margin-left: 470px">点击注册，表示同意中绿生活<span class="lvse"><a href="/seller/user/fwxy">《服务协议》</a></span></div>
                             </div>
                              <div class="zhxxmk">
                             <div style="width: 350px;margin-left: 470px">  <a id="a_submit" href="javascript:void(0);"><img src="${ctx }/resource/public/seller/images/zc.png" width="276" height="46" alt="注册"></a></div>
                            </div>
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
        /* $("#allmap").hide();  */
        //初始化
        Init.bind();
        
        $("#button").click(function(){
        	searchByStationName();
        });
        var stringBuilder = [];
        
        var map = new BMap.Map("allmap");
        
        var address = document.getElementById("text_companyadress").value;
        var provincename = $('#select_province option:selected').text();
        var cityname = $('#select_city option:selected').text();
    	var areaname = $('#select_area option:selected').text();
    	if(provincename != "请选择"){
   		stringBuilder.push(provincename);
   	     } 
       	if(cityname != "请选择") {
   		stringBuilder.push(cityname);
   	    }
   	    if(areaname != "请选择") {
   		stringBuilder.push(areaname);
   	     }
    	
    	address = stringBuilder.join("") + address;
    	if(address=="") {
   		 map.centerAndZoom("北京", 12);
   	} else {
   		 map.centerAndZoom(address, 12);
    		var lng = $("#longitude").val();
    		var lat = $("#latitude").val();
    		var marker = new BMap.Marker(new BMap.Point(lng,lat)); // 创建标注，为要查询的地方对应的经纬度
    		map.addOverlay(marker);
   	}
        map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
        map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

        map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
        map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
        map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开

        var localSearch = new BMap.LocalSearch(map);
        localSearch.enableAutoViewport(); //允许自动调节窗体大小
        function searchByStationName() {
        	var stringBuilder2 = [];
        	var provincename = $('#select_province option:selected').text();
        	var cityname = $('#select_city option:selected').text();
        	var areaname = $('#select_area option:selected').text();
        	if(provincename != "请选择"){
           		stringBuilder2.push(provincename);
           	     } 
               	if(cityname != "请选择") {
           		stringBuilder2.push(cityname);
           	    }
           	    if(areaname != "请选择") {
           		stringBuilder2.push(areaname);
           	     }
        	 map.clearOverlays();// 清空原来的标注
        	var address = document.getElementById("text_companyadress").value;
        	address =  stringBuilder2.join("")+ address;
        	
        	localSearch.setSearchCompleteCallback(function(searchResult) {
        		var poi = searchResult.getPoi(0);
        		
/*         		$("#longitude").val(poi.point.lng);
        		$("#latitude").val(poi.point.lat); */
        		map.centerAndZoom(poi.point, 13);
        		var marker = new BMap.Marker(new BMap.Point(poi.point.lng,
        				poi.point.lat)); // 创建标注，为要查询的地方对应的经纬度
        		map.addOverlay(marker);
        		var content = address
        				+ "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;
        		var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>"
        				+ content + "</p>");
        		marker.addEventListener("click", function() {
        			this.openInfoWindow(infoWindow);
        		});
        		// marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        	});
        	localSearch.search(address);
        }
    });
</script>

