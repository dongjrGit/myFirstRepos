<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>活动编号：<span class="red">${data.actnum }</span></span>
        <span class="marrig10"></span>
        <span>
            活动名称：<span class="red">${data.actname }</span>
            <input type="hidden" id="activityid" value="${data.id }" />
        </span>
        <span class="marrig10"></span>
        <c:if test="${data.shopid== shopid}">
         <input class="inquire chaxun" name="btnadd" type="button" value="+添加赠品">
        </c:if>
        <c:choose>
        <c:when test="${urltype==0 }">
        <a href="yxgl_FullgiftList" target="_self"><input class="inquire" name="" type="button" value="返回满赠活动列表"></a>
        </c:when>
        <c:otherwise>
        <a href="yxgl_FullgiftCheck" target="_self"><input class="inquire" name="" type="button" value="返回满赠活动审核列表"></a>
        </c:otherwise>
        </c:choose>
        
        
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
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
                            {{if ${data.shopid== shopid}}}
                                <a href="javascript:void(0);" class="del" data="{{fgift.id}}"><span class="shenlan">删除</span></a>
                            {{else}}
                           
                            {{/if}}
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
        <div id="divadd" class="xgyfxxgz" style="height:260px;width:600px;margin-left: 450px;display:none;position:relative;">
            <div class="xgyfxxgz-top" style="background: #1680B2;">选择赠品<span class="xgyfxxgz-close">X</span></div><!-- xgyfxxgz-top  stop-->
            <div class="xgyfxxgz-con" style="height:250px;width:550px;">
                <form id="form" method="post">
                    <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>赠送类型：</label></div>
                        <div class="tjcpxx-con-form1">
                        <c:choose>
                        <c:when test="${gifttype==0}">
                         <select name="givetype" id="givetype" class="the-form-select">
                                    <option value="0">优惠劵</option>
                                </select>
                        </c:when>
                         <c:when test="${gifttype==1}">
                         <select name="givetype" id="givetype" class="the-form-select">
                                    <option value="1">库存商品</option>
                                </select>
                         </c:when>
                         <c:when test="${gifttype==2}">
                         <select name="givetype" id="givetype" class="the-form-select">
                                    <option value="2">积分</option>
                                </select>
                         </c:when>
                         <c:otherwise>
                          <select name="givetype" id="givetype" class="the-form-select" onchange="zengpin.TypeChange()">
                                    <option value="0">优惠卷</option>
                                    <option value="1">库存商品</option>
                                    <option value="2">积分</option>
                                </select>
                         </c:otherwise>
                        </c:choose>
                            <input type="hidden" name="actid" id="actid" value="${data.id }" />
                        </div>
                    </div>
                    <div id="givec" class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>赠送品：</label></div>
                        <div class="tjcpxx-con-form1">
                            <input id="select_givec" type="text" class="tjcpxx-fprm-inp" />
                        </div>
                        <div style="margin-top:25px;margin-left:-275px;">
                            <ul>
                                <script id="select_giveclist" type="text/html">
                                    {{each list as give i}}
                                    <li data="{{give.id}}">{{give.couponname}}</li>
                                    {{/each}}
                                </script>
                            </ul>
                        </div>
                    </div>
                    <div id="gives" class="tjcpxx-con-mk1" style="display:none;">
                        <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>赠送品：</label></div>
                        <div class="tjcpxx-con-form1">
                            <input id="select_gives" type="text" class="tjcpxx-fprm-inp" />
                        </div>
                        <div style="margin-top:25px;margin-left:-275px;">
                            <ul>
                                <script id="select_giveslist" type="text/html">
                                    {{each list as give i}}
                                    <li data="{{give.id}}">{{give.name}}</li>
                                    {{/each}}
                                </script>
                            </ul>
                        </div>
                        <input id="giveid" type="hidden" name="giveid" />
                    </div>
                    <div id="givep" class="tjcpxx-con-mk1" style="display:none;">
                        <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>积分：</label></div>
                        <div class="tjcpxx-con-form1">
                            <input id="points" class="tjcpxx-fprm-inp" style="width:150px;" name="points" type="text" value="">
                        </div>
                    </div>
                    <div id="zpcount" class="tjcpxx-con-mk1" style="display:none;">
                        <div class="tjcpxx-con-form-title"><label>赠品数量：</label></div>
                        <div class="tjcpxx-con-form1">
                            <input id="givecount" class="tjcpxx-fprm-inp" style="width:150px;" name="givecount" type="text" value="">&nbsp;件
                        </div>
                    </div>
                    <div class="tjcpxx-con-mk">
                        <div class="tjcpxx-con-form-title"></div>
                        <div class="tjcpxx-con-form huise">
                            <input class="preserve-inp" name="Save" type="button" value="保存">
                            <span class="marrig35"></span>
                            <input class="preserve-inp_hs" name="cancel" type="button" value="取消">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/zengpinList.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        zengpin.bind();
        var gt=${gifttype};
        if (gt == 0) {
            autoxl.bind("select_givec", zengpin.getCoupon, true);
            $("#givetype").val(0);
        } else if (gt == 1)  {
            autoxl.bind("select_gives", zengpin.getSku, true);
            $("#givetype").val(1);
        }
        else if (gt == 2)  {
            $("#givetype").val(2);
        }

    })

</script>