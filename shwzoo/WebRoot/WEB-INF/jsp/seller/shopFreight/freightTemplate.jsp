<!-- @{
    ViewBag.Title = "仓库配送 &gt; 配送管理 &gt; 店铺运费模版";
    Layout = "~/Areas/Seller/Views/Shared/_Layout_Seller_Center.cshtml";
}
 -->
 
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="/resource/public/seller/js/CkPs/FreightTemplate.js"></script>
<script type="text/javascript">

$(function(){
 is(0);
 $(".a").css("display","none");
 $(".b").css("display","none");
$(".c").css("display","none");
	
});
var bubaoyou='<tr class="bubaoyou shanchu" ><td class="left-yfmbtab">计价方式：</td><td><select class="sel-form" style=" margin-left: 0px;" id="AdpModel" onchange="change()"><option value="0">按件数</option><option value="1">按重量</option></select></td></tr>';
var baoyou='<tr class="baoyou shanchu" ><td class="left-yfmbtab">计价方式：</td><td><select class="sel-form" style=" margin-left: 0px;" id="AdpModel" onchange="change()"><option value="0">按件数</option> <option value="1">按重量</option> <option value="2">按满额</option></select><span class="num">数值： <input type="text" class="text-inp" style="width: 70px; color: rgb(0, 0, 0);" id="Adsz"></span> <span class="a" > 件</span><span class="b"> 千克</span><span class="c" > 元</span></td></tr>';
function is(value){
	$(".shanchu").remove();
	if (value==0) {
		$(".num").css("display","none");
		$("#shifou").after(bubaoyou);
		change();
	}else{
		$(".num").attr("style",null);
		$("#shifou").after(baoyou);
		change();
	}
};
function change(){
	 var AdpModel=$("#AdpModel").val();
    if(AdpModel==0){
    	$(".a").attr("style",null);
    	$(".b").css("display","none");
    	$(".c").css("display","none");	
    }else  if(AdpModel==1){
    	$(".b").attr("style",null);
    	$(".a").css("display","none");
    	$(".c").css("display","none");
    }else if(AdpModel==2){
    	$(".c").attr("style",null);
    	$(".b").css("display","none");
    	$(".a").css("display","none");
    }
	
	/* if (value==0) {
		$(".num").css("display","none");
	}else{
		$(".num").attr("style",null);
	} */
}
</script>

<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：仓库配送 &gt; 配送管理 &gt; 店铺运费模板
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <div class="submit-but">
                <input type="hidden" id="userID" value="${userid }" />
                <input name="" id="addTemplate" type="button" value="新建运费模板" class="big-but">
                <!-- <input name="" id="addTemplateInfo" type="button" value="新建模板详细" class="but-comm"> -->
            </div><!--submit-but  stop -->
        </div><!--表单部分结束 -->
        <div class="mryfmb">
            请选择店铺：
            <select class="sel-form" id="shopName">
                <script type="text/html" id="sNamelist">
                    {{each list as shop i}}
                    <option value="{{shop.id}}">{{shop.name}}</option>
                    {{/each}}
                </script>
            </select>

        </div><!--mryfmb  stop -->
        <div class="clear"></div>
        <div class="freight-tab">
            <table>
                <tr id="freightList">
                    <th width="285px">模版名称</th>
                    <th>运送方式</th>
                    <th>计价方式</th>
                    <th>是否包邮</th>
                    <th>操作</th>
                </tr>
                <script type="text/html" id="datalist">
                    {{each list as ft i}}
                    <tr>
                        <td width="315px">{{ft.name}}</td>
                        <td>
                            {{if ft.transportmode==0}}快递
                            {{else}}{{if ft.transportmode==1}}EMS
                            {{else ft.transportmode==2}}平邮
                            {{/if}}
                            {{/if}}
                        </td>
                        <td>
                            {{if ft.pricingmode==0}}按件数
                            {{else if ft.pricingmode==1}}按重量
                            {{else}}按金额
                            {{/if}}
                        </td>
                        <td>{{if ft.isexemptionpostage==1}}包邮{{else}}不包邮{{/if}}</td>
                        <td>
                            <input type="hidden" value="{{ft.id}}" />
                            <p class="lvse">
                                <a class="setDefault">{{if ft.status==1}}默认模版{{else}}设为默认{{/if}}</a>
                                <span class="yfmb-xiugai"><a>修改</a></span>
                                <a class="delete">删除</a>
                                <a class="AddFreightAtrr">添加模板详细</a>
								<a href="/seller/freight/subFreight_Manager_list?ftid={{ft.id}}" title="子模板列表"><span class="shenlan">子模板列表</span></a>
                            </p>
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </table>
            <!--xgyfxxgz 修改运费详细规则  begin -->
            <div class="xgyfxxgz" id="editfreightshow">
                <div class="xgyfxxgz-top">修改运费详细规则<span class="xgyfxxgz-close">X</span></div><!-- xgyfxxgz-top  stop-->
                <div class="xgyfxxgz-con">
                    <table class="yfmb-tab">
                        <tr>
                            <td class="left-yfmbtab">模板名称：</td>
                            <td><input name="tName" id="tName" type="text" class="text-inp"></td>
                        </tr>
                         <tr>
                             <td class="left-yfmbtab">模板描述：</td>
                             <td> <textarea name="description" id="description" rows="10" class="inp-seller"></textarea></td>
                          </tr>
                          
                        <tr>
                            <td class="left-yfmbtab">计价方式：</td>
                            <td>
                                <select class="sel-form" style=" margin-left: 0px;" id="pModel" name="pModel" onchange="change2()">
                                    <option value="0">按件数</option>
                                    <option value="1">按重量</option>
                                  	<option value="2">按重量</option>
                                </select>
                                <span class="num1">   数值：<input name='sz' id='sz' type='text' class='text-inp' style="width: 70px; color: rgb(0, 0, 0);"></span>
                                 <span class="a" > 件</span><span class="b" > 千克</span><span class="c" > 元</span>
                            </td>
                        </tr>
						<tr class="num1" id="bygz">
                                <td class="left-yfmbtab">包邮规则：</td>
                                <td>
                                    <div class="yfmk-top">
                                        <input name="IsCondition" type="radio" value="0" >大于等于
                                            <input name="IsCondition" type="radio" value="1" >小于等于
                                    </div>
                                </td>
                            </tr>
                        <tr id="ysfs">
                            <td class="left-yfmbtab">运送方式：</td>
                            <td>
                                <div class="yfmk-top">
                                    <input name="ftype" type="radio" value="0" >快递
                                    <input name="ftype" type="radio" value="1" >EMS
                                    <input name="ftype" type="radio" value="2" >平邮
                                </div>
                            </td>
                        </tr>
                       
                      <tr id="falist">
                            <td class="left-yfmbtab">是否包邮：</td>
                            <td>
                                 <input name="IsPostage"  id="IsPostage" disabled type="radio" value="0" >否
                                    <input name="IsPostage"  id="IsPostage" disabled type="radio" value="1">是
                            </td>
                        </tr>
                        <tbody id="tbodyarea">
                        <script type="text/html" id="ftAttr">
                            {{each list as fa j}}
                            
                            <tr class="edittr" id="edittr{{j}}">
                                <td class="left-yfmbtab" style="vertical-align:top">地址：</td>
                                <td>
                                    <div style="position:relative">
                                        <div class="sel-yfmbdz" id="{{j}}"><input type="text" class="text-inp" value="{{fa.areas}}" id="AtAreas{{j}}" name="areas" /></div>
                                        <!--yfmb-qycon  begin -->
                                        <div class="yfmb-qycon" id="yfmb-qycon">
                                            <div class="xzqy-yfmb">
                                                <div class="xzqy-yfmbtop">
                                                    选择区域
                                                    <span class="xzqy-yfmbtop-close" id="{{j}}">关闭</span>
                                                </div><!-- xzqy-yfmbtop  stop-->
                                                <div class="xzqy-yfmbcon">
                                                    <table id="tabArea{{j}}">
                                                        <tr>
                                                            <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="">华东</td>
                                                            <td class="xzqy-tabright">
                                                                <ul>
                                                                   
                                                                      <li><input name="Atareas" type="checkbox" value="上海">上海</li>
                                                                     
                                                                     
                                                                     <li><input name="Atareas" type="checkbox" value="江苏">江苏</li>
                                                                     
                                                                    
                                                                     <li><input name="Atareas" type="checkbox" value="浙江">浙江</li>
                                                                     
                                                                    
                                                                      <li><input name="Atareas" type="checkbox" value="安徽">安徽</li>
                                                                  
                                                                     
                                                                     <li><input name="Atareas" type="checkbox" value="江西">江西</li>
                                                                     
                                                                </ul>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="华北">华北</td>
                                                            <td class="xzqy-tabright">
                                                                <ul> 
                                                                     
                                                                      <li><input name="Atareas" type="checkbox" value="北京">北京</li>
                                                                    
                                                                     
                                                                     <li><input name="Atareas" type="checkbox" value="天津">天津</li>
                                                                     
                                                                    
                                                                    <li><input name="Atareas" type="checkbox" value="山西">山西</li>
                                                                     
                                                                     
                                                                      <li><input name="Atareas" type="checkbox" value="山东">山东</li>
                                                                     
                                                                     
                                                                      <li><input name="Atareas" type="checkbox" value="河北">河北</li>
                                                                   
                                                                     
                                                                     <li><input name="Atareas" type="checkbox" value="内蒙古">内蒙古</li>
                                                                     
                                                                    
                                                                </ul>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="华中">华中</td>
                                                            <td class="xzqy-tabright">
                                                                <ul>
                                                                    <li><input name="Atareas" type="checkbox" value="湖南">湖南</li>
                                                                    <li><input name="Atareas" type="checkbox" value="湖北">湖北</li>
                                                                    <li><input name="Atareas" type="checkbox" value="河南">河南</li>
                                                                </ul>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="">华南</td>
                                                            <td class="xzqy-tabright">
                                                                <ul>
                                                                    <li><input name="Atareas" type="checkbox" value="广东">广东</li>
                                                                    <li><input name="Atareas" type="checkbox" value="广西">广西</li>
                                                                    <li><input name="Atareas" type="checkbox" value="福建">福建</li>
                                                                    <li><input name="Atareas" type="checkbox" value="海南">海南</li>
                                                                </ul>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="">东北</td>
                                                            <td class="xzqy-tabright">
                                                                <ul>
                                                                    <li><input name="Atareas" type="checkbox" value="辽宁">辽宁</li>
                                                                    <li><input name="Atareas" type="checkbox" value="吉林">吉林</li>
                                                                    <li><input name="Atareas" type="checkbox" value="黑龙江">黑龙江</li>
                                                                </ul>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="">西北</td>
                                                            <td class="xzqy-tabright">
                                                                <ul>
                                                                    <li><input name="Atareas" type="checkbox" value="陕西">陕西</li>
                                                                    <li><input name="Atareas" type="checkbox" value="新疆">新疆</li>
                                                                    <li><input name="Atareas" type="checkbox" value="甘肃">甘肃</li>
                                                                    <li><input name="Atareas" type="checkbox" value="宁夏">宁夏</li>
                                                                    <li><input name="Atareas" type="checkbox" value="青海">青海</li>
                                                                </ul>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="">西南</td>
                                                            <td class="xzqy-tabright">
                                                                <ul>
                                                                    <li><input name="Atareas" type="checkbox" value="重庆">重庆</li>
                                                                    <li><input name="Atareas" type="checkbox" value="云南">云南</li>
                                                                    <li><input name="Atareas" type="checkbox" value="贵州">贵州</li>
                                                                    <li><input name="Atareas" type="checkbox" value="四川">四川</li>
                                                                    <li><input name="Atareas" type="checkbox" value="西藏">西藏</li>
                                                                </ul>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="港澳台">港澳台</td>
                                                            <td class="xzqy-tabright">
                                                                <ul>
                                                                    <li><input name="Atareas" type="checkbox" value="香港">香港</li>
                                                                    <li><input name="Atareas" type="checkbox" value="澳门">澳门</li>
                                                                    <li><input name="Atareas" type="checkbox" value="台湾">台湾</li>
                                                                </ul>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="海外">海外</td>
                                                            <td class="xzqy-tabright">
                                                                <ul>
                                                                    <li><input name="Atareas" type="checkbox" value="海外">海外</li>
                                                                </ul>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div><!--xzqy-yfmbcon  stop -->
                                                <div class="xzqy-yfmbbottom">
                                                    <input name="{{j}}" type="button" value="确定" class="areBut big-but">
                                                    <input name="{{j}}" type="button" value="取消" class="areBut big-but-huise">
                                                </div><!--xzqy-yfmbbottom  stop -->
                                                <div class="clear"></div>
                                            </div>
                                            <!--yfmb-qycon  stop -->
                                        </div><!--xzqy-yfmb  stop -->
                                    </div>
                                    <div class="yfmk">

                                        <div class="yfmk-con">
                                            <div class="deleteAttr" style="width:30px; height:13px; position:absolute; top:-15px; right:0px; text-align:center; line-height:13px; cursor:pointer; color:#41A8ED;">
                                                删除<input type="hidden" value="{{fa.id}}">
                                            </div>
                                            <div class="yfmk-contop">
                                                默认运费：<input name="FirstCount" type="text" value="{{fa.firstcount}}" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" class="mryf-inp">
								<span class="aa">件/</span><span class="ff">千克内 ,</span>
                                                <input name="FirstPrice" onBlur="checkmoney(this)" type="text" class="mryf-inpred" value="{{fa.firstprice}}">元 <span class="dd">,
                                                每增加<input name="ElseCount" type="text" value="{{fa.elsecount}}" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" class="mryf-inp">
								<span class="gg">件 /</span><span class="kk"> 千克,</span>
                                                增加运费<input name="ElsePrice" type="text" onBlur="checkmoney(this)" class="mryf-inpred" value="{{fa.elseprice}}">元</span>
                                                <div class="clear"></div>
                                                <div class="sfsrsz"><span class="red bolder">X</span>首费应输入0.00至999.99之间</div><!--sfsrsz  stop -->
                                                <span class="cc"><div class="sfsrsz"><span class="red bolder">X</span>首件应输入1至9之间</div><!--sfsrsz  stop --></span>
                                                <div class="clear"></div><input type="hidden" value="{{fa.id}}" name="faID" />
                                            </div><!-- yfmk-contop  stop-->
                                            <div class="yfmk-conbottom lvse">为指定地区城市设置运费</div><!--yfmk-conbottom  stop -->
                                        </div><!--yfmk-con  stop -->
                                    </div><!--yfmk  stop -->
                                </td>
                            </tr>
                            {{/each}}
                        </script>
                        </tbody>
                        <tr>
                            <td class="left-yfmbtab">&nbsp;</td>
                            <td class="bottom-tryfmb">
                                <input type="hidden" id="editfID" />
                                <a href="javascript:void(0);"><input type="button" value="保存" class="big-but" id="editFreight"></a>
                                <a href="javascript:void(0);"><input type="button" value="返回" class="cancelbtn big-but-huise"></a>
                            </td>
                        </tr>
                    </table>
                    <div class="clear"></div>
                </div><!--xgyfxxgz-con  stop -->
                <div class="clear"></div>
            </div>
            <!--xgyfxxgz 修改运费详细规则  stop -->
            <!--xgyfxxgz 添加运费模版  begin -->
            <div class="xgyfxxgz" id="addfreight">
                <div class="xgyfxxgz-top">添加运费模版<span class="xgyfxxgz-close">X</span></div><!-- xgyfxxgz-top  stop-->
                <div class="xgyfxxgz-con">
                    <table class="yfmb-tab">
                        <tr>
                            <td class="left-yfmbtab">模板名称：</td>
                            <td><input name="" id="AdtName" type="text" class="text-inp"></td>
                        </tr>
                        <tr>
                             <td class="left-yfmbtab">模板描述：</td>
                             <td> <textarea name="adddescription" id="adddescription" rows="10" class="inp-seller"></textarea></td>
                        </tr>
                        <tr id="shifou">
                            <td class="left-yfmbtab">是否包邮：</td>
                            <td>
                                <div class="yfmk-top">
                                    <input name="AdIsPostage" onclick="is(0)" type="radio" value="0" checked>否
                                    <input name="AdIsPostage" onclick="is(1)" type="radio" value="1">是
                                </div>
                            </td>
                        </tr>
                          <tr  class="num">
                                    <td class="left-yfmbtab">包邮规则：</td>
                                    <td>
                                        <input name="IsCondition" type="radio" value="0" checked>大于等于	
                                        <input name="IsCondition" type="radio" value="1">小于等于
                                    </td>
                                </tr>

                        <tr id="falist">
                            <td class="left-yfmbtab">运送方式：</td>
                            <td>
                                <div class="yfmk-top">
                                    <input name="Adftype" type="radio" value="0" checked>快递
                                    <input name="Adftype" type="radio" value="1">EMS
                                    <input name="Adftype" type="radio" value="2">平邮
                                </div>
                            </td>
                        </tr>
                      
                        <tr>
                            <td class="left-yfmbtab">&nbsp;</td>
                            <td class="bottom-tryfmb">
                                <a href="javascript:void(0);"><input type="button" value="保存" class="big-but" id="Adtempleat"></a>
                                <a href="javascript:void(0);"><input type="button" value="返回" class="cancelbtn big-but-huise"></a>
                            </td>
                        </tr>
                    </table>
                    <div class="clear"></div>
                </div><!--xgyfxxgz-con  stop -->
                <div class="clear"></div>
            </div>
            <!--xgyfxxgz 添加运费模版  stop -->
            <!--xgyfxxgz 添加运费模版详细  begin -->
            <div class="xgyfxxgz" id="addfreightInfo">
                <div class="xgyfxxgz-top">添加运费详细规则<span class="xgyfxxgz-close">X</span></div><!-- xgyfxxgz-top  stop-->
                <div class="xgyfxxgz-con">
                    <table class="yfmb-tab">
                        <tr>
                            <td class="left-yfmbtab">模板名称：</td>
                            <td>
                                <input type="text" class="text-inp" id="AttName" readonly />
                                <input type="hidden" id="AttID" />
                                <!-- <select class="sel-form" id="AttName">
                                        <script type="text/html" id="namelist">
                                            {{each list as at i}}
                                            <option value="{{at.ID}}">{{at.Name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>
                                    <span class="lvse"><a href="javascript:void(0);">请选择运费模版</a></span> -->
                            </td>
                        </tr>
                        <tr>
                            <td class="left-yfmbtab">计价方式：</td>
                            <td>
                                <select class="sel-form" style=" margin-left: 0px;" id="AtpModel" disabled >
                                    <option value="0">按件数</option>
                                    <option value="1">按重量</option>
                                    <option value="2">按满额</option>
                                </select>
                           <span class="num1">      数值： <input type="text" class="text-inp" style="width: 80px; color: rgb(0, 0, 0);" name="Atsz" id="Atsz" readonly></span>
                             <span class="a" > 件</span><span class="b" > 千克</span><span class="c" > 元</span>
                            </td>
                        </tr>
                        
                        <tr class="num1">
                            <td class="left-yfmbtab">包邮规则：</td>
                            <td>
                                <div class="yfmk-top">
                                    <input name="AtInNum" type="radio" value="0" checked disabled>大于等于
                                    <input name="AtInNum" type="radio" value="1" disabled>小于等于
                                </div>
                            </td>
                        </tr>

                        <tr id="falist">
                            <td class="left-yfmbtab">运送方式：</td>
                            <td>
                                <div class="yfmk-top">
                                    <input name="Atftype" type="radio" value="0" disabled>快递
                                    <input name="Atftype" type="radio" value="1" disabled>EMS
                                    <input name="Atftype" type="radio" value="2" disabled>平邮
                                </div>
                            </td>
                        </tr>
                        <tr >
                            <td class="left-yfmbtab">是否包邮：</td>
                            <td>
                                <div class="yfmk-top">
                                   <input name="AtIsPostage" type="radio" value="0" disabled>否
                                    <input name="AtIsPostage" type="radio" value="1" disabled>是
                                </div>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="left-yfmbtab" style="vertical-align:top">地址：</td>
                            <td>
                                <div style="position:relative">
                                    <div class="sel-yfmbdz"><input type="text" id="AreasAtrr" class="text-inp" readonly="readonly" /></div>
                                    <!--yfmb-qycon  begin -->
                                    <div id="yfmb-qycon" class="yfmb-qycon">
                                        <div class="xzqy-yfmb">
                                            <div class="xzqy-yfmbtop">
                                                选择区域
                                                <span class="xzqy-yfmbtop-close">关闭</span>
                                            </div><!-- xzqy-yfmbtop  stop-->
                                            <div class="xzqy-yfmbcon">
                                                <table>
                                                    <tr>
                                                        <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="华东">华东</td>
                                                        <td class="xzqy-tabright">
                                                            <ul>
                                                                <li><input name="AreasAtrr" type="checkbox" value="上海">上海</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="江苏">江苏</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="浙江">浙江</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="安徽">安徽</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="江西">江西</li>
                                                            </ul>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="华北">华北</td>
                                                        <td class="xzqy-tabright">
                                                            <ul>
                                                                <li><input name="AreasAtrr" type="checkbox" value="北京">北京</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="天津">天津</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="山西">山西</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="山东">山东</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="河北">河北</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="内蒙古">内蒙古</li>
                                                            </ul>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="华中">华中</td>
                                                        <td class="xzqy-tabright">
                                                            <ul>
                                                                <li><input name="AreasAtrr" type="checkbox" value="湖南">湖南</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="湖北">湖北</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="河南">河南</li>
                                                            </ul>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="华南">华南</td>
                                                        <td class="xzqy-tabright">
                                                            <ul>
                                                                <li><input name="AreasAtrr" type="checkbox" value="广东">广东</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="广西">广西</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="福建">福建</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="海南">海南</li>
                                                            </ul>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="东北">东北</td>
                                                        <td class="xzqy-tabright">
                                                            <ul>
                                                                <li><input name="AreasAtrr" type="checkbox" value="辽宁">辽宁</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="吉林">吉林</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="黑龙江">黑龙江</li>
                                                            </ul>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="西北">西北</td>
                                                        <td class="xzqy-tabright">
                                                            <ul>
                                                                <li><input name="AreasAtrr" type="checkbox" value="陕西">陕西</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="新疆">新疆</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="甘肃">甘肃</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="宁夏">宁夏</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="青海">青海</li>
                                                            </ul>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="西南">西南</td>
                                                        <td class="xzqy-tabright">
                                                            <ul>
                                                                <li><input name="AreasAtrr" type="checkbox" value="重庆">重庆</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="云南">云南</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="贵州">贵州</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="四川">四川</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="西藏">西藏</li>
                                                            </ul>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="港澳台">港澳台</td>
                                                        <td class="xzqy-tabright">
                                                            <ul>
                                                                <li><input name="AreasAtrr" type="checkbox" value="香港">香港</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="澳门">澳门</li>
                                                                <li><input name="AreasAtrr" type="checkbox" value="台湾">台湾</li>
                                                            </ul>

                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="xzqy-tableft"><input name="AreasSelect" type="checkbox" value="海外">海外</td>
                                                        <td class="xzqy-tabright">
                                                            <ul>
                                                                <li><input name="AreasAtrr" type="checkbox" value="海外">海外</li>
                                                            </ul>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div><!--xzqy-yfmbcon  stop -->
                                            <div class="xzqy-yfmbbottom">
                                                <input name="" type="button" value="确定" class="areBut big-but">
                                                <input name="" type="button" value="取消" class="areBut big-but-huise">
                                            </div><!--xzqy-yfmbbottom  stop -->
                                            <div class="clear"></div>
                                        </div>
                                        <!--yfmb-qycon  stop -->
                                    </div><!--xzqy-yfmb  stop -->
                                </div>
                                <div class="yfmk">

                                    <div class="yfmk-con">
                                        <div class="yfmk-contop">
                                            默认运费：<input name="" type="text" class="mryf-inp" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" id="AtFirstCount">
                                         	<span class="aa">件/</span><span class="ff">千克内 ,</span>
                                            <input name="" type="text" class="mryf-inpred" onBlur="checkmoney(this)" id="AtFirstPrice">元<span class="dd"> ,
                                            每增加<input name="" type="text" class="mryf-inp" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" id="AtElseCount">
                                            <span class="gg">件/</span><span class="kk"> 千克,</span>
                                            增加运费<input name="" type="text" class="mryf-inpred" onBlur="checkmoney(this)" id="AtElsePrice">元</span>
                                            <div class="clear"></div>
                                            <div class="sfsrsz"><span class="red bolder">X</span>首费应输入0.00至999.99之间</div><!--sfsrsz  stop -->
                                           <span class="cc"> <div class="sfsrsz"><span class="red bolder">X</span>首件应输入1至9之间</div><!--sfsrsz  stop --></span>
                                            <div class="clear"></div>
                                        </div><!-- yfmk-contop  stop-->
                                        <div class="yfmk-conbottom lvse">为指定地区城市设置运费</div><!--yfmk-conbottom  stop -->
                                    </div><!--yfmk-con  stop -->
                                </div><!--yfmk  stop -->
                            </td>
                        </tr>
                        <tr>
                            <td class="left-yfmbtab">&nbsp;</td>
                            <td class="bottom-tryfmb">
                                <a href="javascript:void(0);"><input type="button" value="保存" id="addFreightAtrr" class="big-but"></a>
                                <a href="javascript:void(0);"><input type="button" value="返回" class="cancelbtn big-but-huise" height="32"></a>
                            </td>
                        </tr>
                    </table>
                    <div class="clear"></div>
                </div><!--xgyfxxgz-con  stop -->
                <div class="clear"></div>
            </div>
            <!--xgyfxxgz 添加运费模版详细  stop -->
        </div><!--freight-tab  stop -->
    </div><!--主要内容 右边结束 -->
    <script type="text/javascript">
function checkmoney(htmldom){
	var $dom = $(htmldom);
	var reg = /^[1-9]\d*(\.\d{1,2})?$|^[0]\.\d{1,2}$/g;
	if(!reg.test($dom.val()))
		$dom.val("0.00");
}
</script>
</div>

