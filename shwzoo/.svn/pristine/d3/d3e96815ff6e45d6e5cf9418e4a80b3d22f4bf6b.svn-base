<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/couponlist.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        coupon.getuserlist(1);
    })
</script>

<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>优惠劵编号：${data.couponnum }</span>
        <span class="marrig10"></span>
        <span>优惠劵名称：${data.couponname }</span>
        <input type="hidden" id="couponid" value="${data.id}" />
        <a href="yxgl_CouponList" target="_self"><input class="inquire" name="" type="button" value="返回优惠劵列表"></a>
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr>
                <th width="10%">用户名称</th>
                <th width="15%">邮箱</th>
                <th width="10%">手机号</th>
                <th width="15%">领用时间</th>
                <th width="8%">是否使用</th>
                <th width="15%">使用时间</th>
                <th>使用说明</th>
            </tr>
            <tbody id="datalist">
                <script id="couponlist" type="text/html">
                    {{each list as coupon i}}
                    <tr>
                        <td>{{coupon.username}}</td>
                        <td>{{coupon.email}}</td>
                        <td>{{coupon.mobile}}</td>
                        <td>{{coupon.gettimestr}}</td>
                        <td>
                            {{if coupon.isuser==0}}
                            未使用
                            {{else }}
                            已使用
                            {{/if}}
                        </td>
                        <td>{{coupon.usetimestr}}</td>
                        <td>{{coupon.userdesc}}</td>
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
