<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/platform/js/yxgl/couponnewlist.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        couponnew.bind(); 
    })
</script>

<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>优惠劵名称：<input type="text" id="name" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>所属店铺：<input id="select_shop" type="text" class="inp-seller" /></span>
        <div>
            <ul>
                <script id="select_shoplist" type="text/html">
                    {{each list as shop i}}
                    <li data="{{shop.id}}">{{shop.name}}</li>
                    {{/each}}
                </script>
            </ul>
        </div>
       <!--  <span class="marrig10"></span>
        <span>
           优惠劵来源：
            <select class="the-form-select-one" id="from_select">
                <option value="-1">所有</option>
                <option value="0">平台</option>
                <option value="1">外部</option>
            </select>
        </span> -->
        <span class="marrig10"></span>
        <span>
            使用对象：
            <select class="the-form-select-one" id="use_select">
                <option value="-1">所有</option>
                <option value="0">商品</option>
             <!--    <option value="1">分类</option> -->
                <option value="2">店铺</option>
                <option value="3">全场通用</option>
            </select>
        </span>
        <span class="marrig10"></span>
        <span>
            优惠劵类型：
            <select class="the-form-select-one" id="type_select">
                <option value="-1">所有</option>
                <option value="0">通用</option>
                <option value="1">金额限制</option>
            </select>
        </span>
        <span class="marrig10"></span>
        <span>开始时间：</span>
        <input type="text" id="starts" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'starte\')}' })" value="" readonly="readonly" />-
        <input type="text" id="starte" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'starts\')}' })" value="" readonly="readonly" />
        <span class="marrig10"></span>
        <span>结束时间：</span>
        <input type="text" id="ends" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'ende\')}' })" value="" readonly="readonly" />-
        <input type="text" id="ende" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'ends\')}' })" value="" readonly="readonly" />
        <span class="marrig10"></span>
        <input class="inquire" name="btnsearch" type="button" value="查询">
        <input class="inquire chaxun" name="btnadd" type="button" value="+添加优惠劵">
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="buy_title">
                <th width="8%">优惠劵编号</th>
                <th width="10%">优惠劵名称</th>
                <th width="8%">面值</th>
                <th width="8%">剩余数量</th>
                <th width="6%">使用对象</th>
                <th width="6%">所属店铺</th>
                <th width="6%">领用方式</th>
                <th width="10%">开始时间</th>
                <th width="10%">结束时间</th>
                <th width="6%">公开领劵</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="couponlist" type="text/html">
                    {{each list as coupon i}}
                    <tr>
                        <td>{{coupon.couponnum}}</td>
                        <td>{{coupon.couponname}}</td>
                        <td>{{coupon.facevalue | toFixed}}</td>
                        <td>{{coupon.conponcount}}</td>
                        <td>
                            {{if coupon.couponusetype==0}}
                                                          商品
                            {{else if coupon.couponusetype==1 }}
                                                          分类
                            {{else if coupon.couponusetype==2 }}
                                                           店铺
                            {{else}}
                                                          全场通用
                            {{/if}}
                        </td>
                        <td>{{coupon.shopname}}</td>
                        <td>
                            {{if coupon.gettype==0}}
                                                           会员领取
                            {{else if coupon.gettype==1 }}
                                                           满返促销
                            {{else if coupon.gettype==2 }}
                                                          系统赠送
                            {{else if coupon.gettype==3 }}
                                                          生日赠送
                            {{else if coupon.gettype==4 }}
                                                         抽奖专用
                            {{else if coupon.gettype==5 }}
                                                         注册赠送                            
                            {{/if}}
                        </td>
                        <td>{{coupon.providetimestr}}</td>
                        <td>{{coupon.endtimestr}}</td>
                        <td id="td_{{coupon.id}}">
                            {{if coupon.status==0}}
                            <span class="lvs"><a id="a_{{coupon.id}}" href="javascript:void(0);" onclick="setStatus({{coupon.id}},1)">公开</a></span>
                            {{else }}
                            <span class="lvs"><a id="a_{{coupon.id}}" href="javascript:void(0);" onclick="setStatus({{coupon.id}},0)">不公开</a></span>
                            {{/if}}
                        </td>
                        <td>
                           {{if coupon.fromtype==0}}
                            <a href="yxgl_CouponNewEdit?id={{coupon.id}}"><span class="shenlan">编辑</span></a>        
                            <span class="marrig35"></span>                  
                            {{/if}}
                            <a href="javascript:void(0);" class="del" data="{{coupon.id}}"><span class="shenlan">删除</span></a>
                            <span class="marrig35"></span>
                            {{if coupon.status==0 && coupon.gettype==2 }}
                            <a href="yxgl_CouponUserAdd?id={{coupon.id}}"><span class="shenlan">添加用户</span></a>
                            {{else }}
                            <a href="yxgl_CouponUserList?id={{coupon.id}}"><span class="shenlan">领取用户</span></a>
                            {{/if}}
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
    </div>
    <div class="clear"></div>
    <div id="pager" class="page">

    </div>
</div>
