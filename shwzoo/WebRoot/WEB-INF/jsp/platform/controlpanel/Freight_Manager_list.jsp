<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <script type="text/javascript" src="/resource/public/platform/js/ControlPanel/FreightManager.js"></script> 
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
	}else{
		$(".num").attr("style","block");
		$("#shifou").after(baoyou);
	}
	change();
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
	
}
</script>

<div class="mainright">

    <div class="clear"></div>
   <div class="tjcpxx">
       
        <div class="tjcpxx-con">
            <!-- -->
            
                <div class="the-form">
                    <div class="submit-but">
                        <input type="hidden" id="userID" value="${accouts.userid }" />
                        <input type="hidden" id="shopName" value="${shop.name }" />
                        <input type="hidden" id="userType" value="${accouts.usertype }" />
                        <input name="" id="addTemplate" type="button" value="新建运费模板" class="but-comm">
                        <!--@*  <input name="" id="addTemplateInfo" type="button" value="新建运费模板详细" class="but-comm">*@-->
                    </div><!--submit-but  stop -->
                </div><!--表单部分结束 -->               

                <div class="clear"></div>
                <div class="freight-tab">
                    <table>
                        <tr id="freightList">
                            <th width="315px">模版名称</th>
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
                                    {{else if ft.transportmode==1}}EMS
                                    {{else if ft.transportmode==2}}平邮
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
                                        <a href="javascript:void(0);" class="setDefault">{{if ft.status==1}}默认模版{{else}}设为默认{{/if}}</a>
                                    <span class="yfmb-xiugai"><a class="edit shenlan">修改</a></span>
                                        <a class="delTemplate shenlan">删除</a>
                                        <a class="addFreightAtrr shenlan">添加模板详细</a> 
										<a href="/platform/controlpanel/subFreight_Manager_list?ftid={{ft.id}}" title="子模板列表"><span class="shenlan">子模板列表</span></a>                                      
                                    </p>                                    
                                </td>
                            </tr>
                            {{/each}}
                        </script>
                    </table>
                    <!--xgyfxxgz 修改运费详细规则  begin -->
                    <div class="xgyfxxgz" id="editfreightshow" style="">
                        <div class="xgyfxxgz-top">修改运费详细规则<span class="xgyfxxgz-close">X</span></div><!-- xgyfxxgz-top  stop-->
                        <div class="xgyfxxgz-con">
                            <table class="yfmb-tab">
                                <tr>
                                    <td class="left-yfmbtab">模板名称：</td>
                                    <td><input name="tName" id="tName" type="text" class="inp-seller"></td>
                                </tr>
                                  <tr>
                                    <td class="left-yfmbtab">模板描述：</td>
                                    <td> <textarea name="description" id="description" rows="10" ></textarea></td>
                                </tr>
                                <tr id="jjfs">
                                    <td class="left-yfmbtab">计价方式：</td>
                                    <td>
                                        <select class="the-form-select-one" id="pModel" name="pModel" onchange="change2()">
                                            <option value="0">按件数</option>
                                            <option value="1">按重量</option>
                                            <option value="2">按金额</option>
                                        </select>
                                        <span class="num1">
                                    <span>数值：<input name='sz' id='sz' type='text' class='inp-xd' value=''></span>
                                    <span class="a" > 件</span><span class="b" > 千克</span><span class="c" > 元</span>
                                    </span>
                                    </td>
                                </tr>
								  <tr id="ispost" class="num1">
                                    <td class="left-yfmbtab">包邮规则：</td>
                                    <td>
                                    <p>
                                            <input name="IsConditionEdit" type="radio" value="0" checked>大于等于
                                            <input name="IsConditionEdit" type="radio" value="1">小于等于</p> 
                                    </td>
                                </tr>
                                <tr >
                                    <td class="left-yfmbtab">运送方式：</td>
                                    <td>
                                        <div class="yfmk-top">
                                            <input name="ftype" type="radio" value="0" checked>快递
                                            <input name="ftype" type="radio" value="1" checked>EMS
                                            <input name="ftype" type="radio" value="2" checked>平邮
                                        </div>
                                    </td>
                                </tr>
                                <tr id ="falist">
                                    <td class="left-yfmbtab">是否包邮：</td>
                                    <td>
                                      <!--   <select class="sel-form" id="IsPostage" name="IsPostage">
                                            <option value="0">否</option>
                                            <option value="1">是</option>
                                        </select> -->
                                        
                                    <input name="IsPostage"  id="IsPostage" type="radio" value="0" disabled>否
                                    <input name="IsPostage"  id="IsPostage" type="radio" value="1" disabled>是
                                    </td>
                                </tr>
                              <tbody id="tbodyarea">
                                <script type="text/html" id="ftAttr">
                                    {{each list as fa j}}
                                    <tr id="edittr{{j}}">
                                        <td class="left-yfmbtab">地址：</td>
                                        <td>
                                            <div style="position:relative">
                                                <div class="sel-yfmbdz" id="{{j}}"><input type="text" value="{{fa.areas}}" id="AtAreas{{j}}" name="areas" class="inp-seller" /></div>
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
                                                                    <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="" />华东</td>
                                                                    <td class="xzqy-tabright">
                                                                        <ul>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="上海" />上海</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="江苏" />江苏</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="浙江" />浙江</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="安徽" />安徽</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="江西" />江西</li>
                                                                        </ul>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="华北" />华北</td>
                                                                    <td class="xzqy-tabright">
                                                                        <ul>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="北京" />北京</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="天津" />天津</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="山西" />山西</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="山东" />山东</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="河北" />河北</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="内蒙古" />内蒙古</li>
                                                                        </ul>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="华中" />华中</td>
                                                                    <td class="xzqy-tabright">
                                                                        <ul>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="湖南" />湖南</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="湖北" />湖北</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="河南" />河南</li>
                                                                        </ul>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="" />华南</td>
                                                                    <td class="xzqy-tabright">
                                                                        <ul>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="广东" />广东</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="广西" />广西</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="福建" />福建</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="海南" />海南</li>
                                                                        </ul>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="" />东北</td>
                                                                    <td class="xzqy-tabright">
                                                                        <ul>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="辽宁" />辽宁</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="吉林" />吉林</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="黑龙江" />黑龙江</li>
                                                                        </ul>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="" />西北</td>
                                                                    <td class="xzqy-tabright">
                                                                        <ul>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="陕西" />陕西</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="新疆" />新疆</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="甘肃" />甘肃</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="宁夏" />宁夏</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="青海" />青海</li>
                                                                        </ul>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="xzqy-tableft"><input name="AreasSelect"  class="AreasSelect" type="checkbox" value="" />西南</td>
                                                                    <td class="xzqy-tabright">
                                                                        <ul>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="重庆" />重庆</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="云南" />云南</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="贵州" />贵州</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="四川" />四川</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="西藏" />西藏</li>
                                                                        </ul>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="港澳台" />港澳台</td>
                                                                    <td class="xzqy-tabright">
                                                                        <ul>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="香港" />香港</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="澳门" />澳门</li>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="台湾" />台湾</li>
                                                                        </ul>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="xzqy-tableft"><input name="HaiWai" class="AreasSelect" type="checkbox" value="海外" />海外</td>
                                                                    <td class="xzqy-tabright">
                                                                        <ul>
                                                                            <li><input name="Atareas" class="Atareas" type="checkbox" value="海外" />海外</li>
                                                                        </ul>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </div><!--xzqy-yfmbcon  stop -->
                                                        <div class="xzqy-yfmbbottom">
                                                            <input name="{{j}}" type="button" value="确定" class="areBut text-inp">
                                                            <input name="{{j}}" type="button" value="取消" class="areBut text-inp">
                                                        </div><!--xzqy-yfmbbottom  stop -->
                                                        <div class="clear"></div>
                                                    </div>
                                                    <!--yfmb-qycon  stop -->
                                                </div><!--xzqy-yfmb  stop -->
                                            </div>
                                            <div class="yfmk">

                                                <div class="yfmk-con">
                                                    <div class="deleteAttr" style="width:30px; height:13px; position:absolute; top:-15px; right:0px; text-align:center; line-height:13px; cursor:pointer; color:#41A8ED;">
                                                        删除<input type="hidden" value="{{fa.id}}"></div>
                                                    <div class="yfmk-contop">
                                                        默认运费：<input name="FirstCount" type="text" value="{{fa.firstcount}}" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" class="mryf-inp">
						<span class="aa">件/</span><span class="ff">千克内,</span>
                                                    <input name="FirstPrice" type="text" class="mryf-inpred" onBlur="checkmoney(this)" value="{{fa.firstprice}}">元 <span class="dd">,
                                                        每增加<input name="ElseCount" type="text" value="{{fa.elseCount}}" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" class="mryf-inp">
							<span class="gg">件/</span><span class="kk">千克,</span>
                                                        增加运费<input name="ElsePrice" type="text" class="mryf-inpred" value="{{fa.elseprice}}" onBlur="checkmoney(this)" />元</span>
                                                        <div class="clear"></div>
                                                        <div class="sfsrsz"><span class="red bolder">X</span>首费应输入0.00至999.99之间</div><!--sfsrsz  stop -->
                                                      <span class="cc">	<div class="sfsrsz"><span class="red bolder">X</span>首件应输入1至9之间</div><!--sfsrsz  stop --></span>
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
                                        <a href="javascript:void(0);"><input type="button" class="preserve-inp marrig35 mar35" id="editFreight"  value="保存"></a>
                                        <a href="javascript:void(0);"><input type="button" class="cancelbtn preserve-inp_hs" value="返回"></a>
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
                                    <td><input name="" id="AdtName" type="text" class="inp-seller">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-yfmbtab">模板描述：</td>
                                    <td> <textarea name="adddescription" id="adddescription" rows="10" ></textarea></td>
                                </tr>
                                 <tr id="shifou">
                                    <td class="left-yfmbtab">是否包邮：</td>
                                    <td>
                                        <div class="yfmk-top">
                                            <input name="AdIsPostage"  onclick="is(0)" type="radio" value="0" checked>否
                                            <input name="AdIsPostage" onclick="is(1)" type="radio" value="1">是
                                        </div>
                                    </td>
                                </tr>
                             
								 <tr  class="num">
                                    <td class="left-yfmbtab">包邮规则：</td>
                                    <td>
                                    <p>
                                        <input name="IsCondition" type="radio" value="0" checked>大于等于
                                        <input name="IsCondition" type="radio" value="1">小于等于</p> 
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
                                        <a href="javascript:void(0);"><input type="button" value="保存" class="preserve-inp marrig35 mar35" id="Adtempleat"></a>
                                        <a href="javascript:void(0);"><input type="button" value="返回" class="cancelbtn preserve-inp_hs" ></a>
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
                                    <td><input type="text" id="AttName" class="inp-seller" readonly />
                                    <input type="hidden" id="AttID" />
                                     <!--     @*<select class="sel-form" id="AttName">
                                            <script type="text/html" id="namelist">
                                                {{each list as at i}}
                                                <option value="{{at.ID}}">{{at.Name}}</option>
                                                {{/each}}
                                            </script>
                                        </select>
                                        <span class="lvse"><a href="javascript:void(0);">请选择运费模版</a></span>*@-->
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-yfmbtab">计价方式：</td>
                                    <td>
                                        <select class="the-form-select-one" id="AtpModel" disabled>
                                            <option value="0">按件数</option>
                                            <option value="1">按重量</option>
                                            <option value="2">按金额</option>
                                        </select>
                                      <span class="num1">  数值：<input name='Atsz' id='Atsz' type='text' class='inp-xd' readonly></span>
                                       <span class="a" > 件</span><span class="b" > 千克</span><span class="c" > 元</span>
                                    </td>
                                </tr>
								  <tr  class="num1">
                                    <td class="left-yfmbtab">包邮规则：</td>
                                    <td>
                                            <input name="AtInNum" type="radio" value="0" checked disabled>大于等于
                                            <input name="AtInNum" type="radio" value="1" disabled>小于等于
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
                                <tr>
                                    <td class="left-yfmbtab">是否包邮：</td>
                                    <td>
                                        <div class="yfmk-top">
                                            <input name="AtIsPostage" type="radio" value="0" disabled>否
                                            <input name="AtIsPostage" type="radio" value="1" disabled>是
                                        </div>
                                    </td>
                                </tr>
                              
                                <tr>
                                    <td class="left-yfmbtab">地址：</td>
                                    <td>
                                        <div style="position:relative">
                                            <div class="sel-yfmbdz"><input type="text" class="inp-seller" id="AreasAtrr" readonly="readonly" /></div>
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
                                                                <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="华东" />华东</td>
                                                                <td class="xzqy-tabright">
                                                                    <ul>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="上海" />上海</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="江苏" />江苏</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="浙江" />浙江</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="安徽" />安徽</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="江西" />江西</li>
                                                                    </ul>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="xzqy-tableft"><input name="AreasSelect"class="AreasSelect"  type="checkbox" value="华北" />华北</td>
                                                                <td class="xzqy-tabright">
                                                                    <ul>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="北京" />北京</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="天津" />天津</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="山西" />山西</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="山东" />山东</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="河北" />河北</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="内蒙古" />内蒙古</li>
                                                                    </ul>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="华中" />华中</td>
                                                                <td class="xzqy-tabright">
                                                                    <ul>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="湖南" />湖南</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="湖北" />湖北</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="河南" />河南</li>
                                                                    </ul>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="华南" />华南</td>
                                                                <td class="xzqy-tabright">
                                                                    <ul>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="广东" />广东</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="广西" />广西</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="福建" />福建</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="海南" />海南</li>
                                                                    </ul>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="东北" />东北</td>
                                                                <td class="xzqy-tabright">
                                                                    <ul>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="辽宁" />辽宁</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="吉林" />吉林</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="黑龙江" />黑龙江</li>
                                                                    </ul>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="西北" />西北</td>
                                                                <td class="xzqy-tabright">
                                                                    <ul>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="陕西" />陕西</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="新疆" />新疆</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="甘肃" />甘肃</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="宁夏" />宁夏</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="青海" />青海</li>
                                                                    </ul>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="西南">西南</td>
                                                                <td class="xzqy-tabright">
                                                                    <ul>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="重庆" />重庆</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="云南" />云南</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="贵州" />贵州</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="四川" />四川</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="西藏" />西藏</li>
                                                                    </ul>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="港澳台" />港澳台</td>
                                                                <td class="xzqy-tabright">
                                                                    <ul>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="香港" />香港</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="澳门" />澳门</li>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="台湾" />台湾</li>
                                                                    </ul>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="xzqy-tableft"><input name="AreasSelect" class="AreasSelect" type="checkbox" value="海外" />海外</td>
                                                                <td class="xzqy-tabright">
                                                                    <ul>
                                                                        <li><input name="AreasAtrr" class="AreasAtrr" type="checkbox" value="海外" />海外</li>
                                                                    </ul>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div><!--xzqy-yfmbcon  stop -->
                                                    <div class="xzqy-yfmbbottom">
                                                        <input name="" type="button" value="确定" class="areBut text-inp">
                                                        <input name="" type="button" value="取消" class="areBut text-inp">
                                                    </div><!--xzqy-yfmbbottom  stop -->
                                                    <div class="clear"></div>
                                                </div>
                                                <!--yfmb-qycon  stop -->
                                            </div><!--xzqy-yfmb  stop -->
                                        </div>
                                        <div class="yfmk">

                                            <div class="yfmk-con">
                                              <!--    @*<div class="aDAttr" style="width:30px; height:13px; position:absolute; top:-15px; right:0px; text-align:center; line-height:13px; cursor:pointer; color:#41A8ED;">*@
                                                   @* 添加<input type="hidden" value="">
                                                </div>*@-->
                                                <div class="yfmk-contop">
                                                    默认运费：<input name="AtFirstCount" type="text" class="mryf-inp" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" id="AtFirstCount">
                                                    <span class="aa">件/</span><span class="ff">千克内,</span>
                                                    <input name="AtFirstPrice" type="text" class="mryf-inpred" onBlur="checkmoney(this)" id="AtFirstPrice">元 <span class="dd">,
                                                    每增加<input name="AtElseCount" type="text" class="mryf-inp" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" id="AtElseCount">
													<span class="gg">件/</span><span class="kk"> 千克,</span>
                                                    增加运费<input name="AtElsePrice" type="text" class="mryf-inpred" id="AtElsePrice" onBlur="checkmoney(this)" />元</span>
                                                    <div class="clear"></div>
                                                    <div class="sfsrsz"><span class="red bolder">X</span>费用应输入0.00至999.99之间</div><!--sfsrsz  stop -->
                                                   <span class="cc"> <div class="sfsrsz"><span class="red bolder">X</span>件数应输入0至999之间</div><!--sfsrsz  stop --></span>
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
                                        <a href="javascript:void(0);"><input type="button" class="preserve-inp marrig35 mar35" id="addFreightAtrr" value="保存"></a>
                                        <a href="javascript:void(0);"><input type="button" class="cancelbtn preserve-inp_hs" value="返回"></a>
                                    </td>
                                </tr>
                            </table>
                            <div class="clear"></div>
                        </div><!--xgyfxxgz-con  stop -->
                        <div class="clear"></div>
                    </div>
                    <!--xgyfxxgz 添加运费模版详细  stop -->

                </div><!--freight-tab  stop -->
            </div>
            <!-- -->
        </div><!--tjcpxx-con stop -->
<script type="text/javascript">
function checkmoney(htmldom){
	var $dom = $(htmldom);
	var reg = /^[1-9]\d*(\.\d{1,2})?$|^[0]\.\d{1,2}$/g;
	if(!reg.test($dom.val()))
		$dom.val("0.00");
}
</script>

</div><!--mainright stop -->

