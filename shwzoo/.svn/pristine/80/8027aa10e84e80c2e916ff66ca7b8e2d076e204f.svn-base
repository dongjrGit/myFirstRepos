<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>优惠劵代码：${data.code }</span>
        <span class="marrig10"></span>
        <span>剩余数量：${data.lastcount }</span>
        <a href="yxgl_AssignCoupon" target="_self"><input class="inquire" name="" type="button" value="返回优惠劵分配列表"></a>
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="buy_title">
                <th width="20%">优惠劵名称</th>
                <th width="10%">面值（元）</th>
                <th width="12%">使用对象类型</th>
                <th >使用对象名称</th>
                <th width="8%">分配数量</th>
                <th width="8%">领取方式</th>
                <th width="12%">开始时间</th>
                <th width="12%">过期时间</th>
            </tr>
            <tbody id="datalist">
            <c:forEach var="coupon" items="${couponlist }">
                   <tr>
                        <td>${coupon.couponname }</td>
                        <td>
                        <fmt:formatNumber value="${coupon.facevalue}" pattern="0.00"/>
                        </td>                     
                        <td>
                        <c:choose>
                        <c:when test="${coupon.couponusetype==0 }">
                           <span>商品</span>
                        </c:when>
                         <c:when test="${coupon.couponusetype==1 }">
                          <span>分类</span>
                        </c:when>                 
                         <c:when test="${coupon.couponusetype==2 }">
                         <span>店铺</span>
                        </c:when>
                        <c:otherwise><span>全场通用</span></c:otherwise>
                        </c:choose>
                        </td>
                        <td>${coupon.shopname }</td>
                        <td>${coupon.conponcount }</td>
                         <td>
                        <c:choose>
                        <c:when test="${coupon.gettype==0 }">
                           <span>会员领取</span>
                        </c:when>
                        <c:otherwise><span>抽奖专用</span></c:otherwise>
                        </c:choose>
                        </td>
                        <td>${coupon.providetimestr }</td>
                        <td>${coupon.endtimestr }</td>
                    </tr>
          </c:forEach>
            </tbody>
        </table>
    </div>
</div>