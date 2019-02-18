<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/seller/js/yxgl/couponlist.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        coupon.bind();
        coupon.getlist(1);
    })
</script>

<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt; 优惠劵 &gt; 优惠劵列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>

                <span><label>优惠劵名称：</label><input type="text" id="name" class="text-inp" /></span>
                <span>
                    <label>
                        使用对象：
                    </label>
                    <select class="sel-form" id="use_select">
                        <option value="">所有</option>
                        <option value="0">商品</option>
                        <option value="2">全场通用</option>
                    </select>
                </span>
                <span>
                    <label>
                        优惠劵类型：
                    </label>
                    <select class="sel-form" id="type_select">
                        <option value="">所有</option>
                        <option value="0">通用</option>
                        <option value="1">金额限制</option>
                    </select>
                </span>
                <span>
                    <label>开始时间：</label>
                    <input type="text" id="starts" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'starte\')}' })" value="" readonly="readonly" />-
                    <input type="text" id="starte" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'starts\')}' })" value="" readonly="readonly" />
                </span>
                <span>
                    <label>结束时间：</label>
                    <input type="text" id="ends" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'ende\')}' })" value="" readonly="readonly" />-
                    <input type="text" id="ende" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'ends\')}' })" value="" readonly="readonly" />
                </span>
                <div class="clear"></div>
                <input class="but-comm" name="btnsearch" type="button" value="查询">
                <input class="but-comm" name="btnadd" type="button" value="+添加优惠劵">
            </form>
        </div>

        <div class="clear"></div>
        <div class="mar35"></div>
        <div class="thgl">
            <table class="data_list">
                <tr id="buy_title">
                    <th width="8%">优惠劵编号</th>
                    <th width="10%">优惠劵名称</th>
                    <th width="8%">面值</th>
                    <th width="8%">剩余数量</th>
                     <th width="8%">使用对象</th>
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
                            {{else}}
                                                          全场通用
                            {{/if}}
                        </td>
                        <td>
                            {{if coupon.gettype==0}}
                                                           会员领取
                            {{else if coupon.gettype==1 }}
                                                           满返促销
                            {{else if coupon.gettype==2 }}
                                                          系统赠送
                             {{else if coupon.gettype==3 }}
                                                          生日赠送
                            {{else}}
                                                          抽奖专用
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
                                <a href="yxgl_CouponEdit?id={{coupon.id}}"><span class="shenlan">编辑</span></a>
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
</div>