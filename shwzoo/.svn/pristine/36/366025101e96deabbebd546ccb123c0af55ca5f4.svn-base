<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    .data_list_cs {
        width: 50px;
        height: 20px;
        background: #F3F5F4;
        border: 1px solid #ccc;
        padding-left: 5px;
    }
</style>
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt; 满赠活动 &gt; 满赠活动-赠品列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <span>活动编号：<span class="red">${data.actnum }</span></span>
            <span class="marrig10"></span>
            <span>活动名称：<span class="red">${data.actname }</span>
                <input type="hidden" id="activityid" value="${data.id }" />
            </span>
            <span class="marrig10"></span>
             <input class="but-comm chaxun" name="btnadd" type="button" value="+添加赠品">
            <a href="yxgl_FullgiftList" target="_self"><input class="but-comm" name="" type="button" value="返回满赠活动列表"></a>
        </div>
        <div class="clear"></div>
        <div class="thgl" style="position:relative;">
            <table class="data_list">
                <tr id="manzeng_title">
                    <th width="8%">赠品类型</th>
                    <th width="12%">赠品编号</th>
                    <th width="30%">赠品名称</th>
                    <th width="10%">价格/面值</th>
                    <th width="15%">数量</th>
                    <th>操作</th>
                </tr>
                <tbody id="datalist">
                    <script id="zengpinlist" type="text/html">
                        {{each list as fgift i}}
                        <tr>
                            {{if fgift.giftType==2}}
                            <td>积分</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input class="data_list_cs" id='po_{{fgift.id}}' name="" type="text" value='{{fgift.points}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setPoint({{fgift.id}},'po_{{fgift.id}}')">保存</a></span></td>
                            {{else}}
                            <td>
                                {{if fgift.giftType==0}}
                                优惠劵
                                {{else}}
                                库存商品
                                {{/if}}
                            </td>
                            <td>{{fgift.num}}</td>
                            <td>{{fgift.name}}</td>
                            <td>{{fgift.price | toFixed}}</td>                          
                            <td><input class="data_list_cs" id='ob_{{fgift.id}}' name="" type="text" value='{{fgift.count}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setCount({{fgift.id}},'ob_{{fgift.id}}')">保存</a></span></td>                        
                            {{/if}}
                            <td>
                                <a href="javascript:void(0);" class="del" data="{{fgift.id}}"><span class="shenlan">删除</span></a>
                            </td>
                        </tr>
                        {{/each}}
                    </script>
                </tbody>
            </table>
        </div>
        <div id="divadd" class="xgyfxxgz" style="height:260px;width:600px;margin-top: 45px;display:none;position:relative;">
            <div class="xgyfxxgz-top" style="background: #DDDDDD;">选择赠品<span class="xgyfxxgz-close">X</span></div><!-- xgyfxxgz-top  stop-->
            <div class="xgyfxxgz-con" style="height:250px;width:550px;padding: 20px 20px 20px 20px;">
                <form id="form" method="post">
                    <div class="zhgl-con-con" style="left:-100px;">
                        <table>
                            <%-- <tr>
                                <td class="xjdpzzh-left" style="width:100px;"><span class="red">*</span>赠送类型：</td>
                                <td>
                                <c:choose>
                                <c:when test="${gifttype==0 }">
                                  <select name="givetype" id="givetype" class="the-form-select">
                                            <option value="0">优惠劵</option>
                                        </select>
                                </c:when>
                                <c:when test="${gifttype==1 }">
                                 <select name="givetype" id="givetype" class="the-form-select">
                                            <option value="1">库存商品</option>
                                        </select>
                                </c:when>
                                <c:otherwise>
                                <select name="givetype" id="givetype" class="sel-form" onchange="zengpin.TypeChange()">
                                            <option value="0">优惠劵</option>
                                            <option value="1">库存商品</option>
                                        </select>
                                </c:otherwise>
                                </c:choose>
                                </td>
                            </tr>
                            <tr id="givec">
                                <td class="xjdpzzh-left" style="width:100px;"><span class="red">*</span>赠品：</td>
                                <td>
                                    <div class="tjcpxx-con-form1">
                                        <input id="select_givec" type="text" class="text-inp-big" value="" />
                                    </div>
                                    <div>
                                        <ul>
                                            <script id="select_giveclist" type="text/html">
                                                {{each list as give i}}
                                                <li data="{{give.id}}">{{give.couponname}}</li>
                                                {{/each}}
                                            </script>
                                        </ul>
                                    </div>
                                </td>
                            </tr> --%>
                            <tr id="gives">
                                <td class="xjdpzzh-left" style="width:100px;"><span class="red">*</span>赠品：</td>
                                <td>
                                    <input type="hidden" name="actid" id="actid" value="${data.id }" />
                                    <input id="giveid" type="hidden" name="giveid" value="" />
                                    <input id="givetype" type="hidden" name="givetype" value="1" />
                                    <div class="tjcpxx-con-form1">
                                        <input id="select_gives" type="text" class="text-inp-big" value="" />
                                    </div>
                                    <div>
                                        <ul>
                                            <script id="select_giveslist" type="text/html">
                                                {{each list as give i}}
                                                <li data="{{give.id}}">{{give.name}}</li>
                                                {{/each}}
                                            </script>
                                        </ul>
                                    </div>
                                </td>
                            </tr>

                            <tr id="zpcount">
                                <td class="xjdpzzh-left" style="width:100px;">赠品数量：</td>
                                <td>
                                    <input id="givecount" class="text-inp-big" style="width:150px;" name="givecount" type="text" value="">&nbsp;件
                                </td>
                            </tr>

                            <tr class="trtop">
                                <td class="xjdpzzh-left" style="width:100px;"></td>
                                <td>
                                    <input name="Save" type="button" value="保存" class="big-but">
                                    <input name="cancel" type="button" value="取消" class="big-but-huise">
                                </td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath }/resource/public/seller/js/yxgl/zengpinList.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        zengpin.bind();
        /* var gt=${gifttype};
        if (gt == 0) {
            autoxl.bind("select_givec", zengpin.getCoupon, true);
            $("#givetype").val(0);
        } else if (gt == 1)  {
            autoxl.bind("select_gives", zengpin.getSku, true);
            $("#givetype").val(1);
        } */

    })

</script>
