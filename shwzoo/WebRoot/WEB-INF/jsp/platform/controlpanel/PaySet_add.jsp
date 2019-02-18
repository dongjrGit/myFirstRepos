<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/AddPaySet.js"></script> 

<div class="mainright">
    <!--l_wzmb  开始 -->
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">支付方式管理</a><span class="sj-img"></span></li>                
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="payForms"  method="post">
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>支付方式名称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="PayName" name="PayName" type="text" >
                    </div>

                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>支持交易货币：</label></div>
                    <div class="tjcpxx-con-form1">
                        <select id="MoneyName" name="MoneyName" class="select-spfl">
                            <option value="人民币" selected>人民币</option>
                            <!-- <script id="moneyselect" type="text/html">
                                <option value="" selected>请选择</option>
                                {{each list as Pro index}}
                                <option value="{{Pro.ID}}">{{Pro.Name}}</option>
                                {{/each}}
                            </script> -->
                        </select>
                    </div>

                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1" style="height:0px;">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1" style="position:relative;">

                    </div>

                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>手续费：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="Poundage" id="Poundage" type="text" value="0.00" onfocus="if (value =='0.00'){value =''}" onblur="if (value ==''){value='0.00'}">
                        <input type="checkbox" id="IsPersent" checked>百分比
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>是否支持在线支付：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input name="isOnline" type="radio" value="true" checked>是
                        <input name="isOnline" type="radio" value="false">否
                    </div>
                </div><!--tjcpxx-con-mk stop -->
               <!--  <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>状态：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input name="status" type="radio" value="" checked>显示
                        <input name="status" type="radio" value="">不显示
                    </div>
                </div>tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>接口类型：</label></div>
                    <div class="tjcpxx-con-form1">
                        <select id="InterfaceType" class="select-spfl" name="InterfaceType">
                            <option value="">选择接口类型</option>
                            <option value="0">电脑版</option>
                            <option value="1">手机网页版</option>
                            <option value="2">手机App版</option>
                        </select>
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>显示顺序：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" id="Display" name="Display" type="text" value="1" onfocus="if (value =='1'){value =''}" onblur="if (value ==''){value='1'}">
                    </div>

                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>详细描述：</label></div>
                    <div class="tjcpxx-con-form1">
                        <textarea class="tjcpxx-con-form1-text" id="Discription" name="Discription"></textarea>
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                        <input class="preserve-inp marrig35 mar35" name="savebtn" id="savebtn" type="button" value="保存">
                        <input class="preserve-inp_hs" name="backbtn" id="backbtn" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->

            </form>
        </div><!--tjcpxx-con-con stop -->
    </div>
    <!--l_wzmb  结束 -->
</div><!--mainright stop -->
