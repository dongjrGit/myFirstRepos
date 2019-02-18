<!-- @{
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
    ViewBag.Title = "品牌店铺列表";
} -->

 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script type="text/javascript" src="${ctx }/resource/public/platform/js/product/sppz_ppdpgl.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="notice-fenlei">
        <span>
            品牌：
            <select id="select_brand" class="the-form-select-one">
               <c:forEach items="${brands }" var="brand">
                    <option value="${brand.id }" <c:if test=""></c:if> data="">${brand.name }</option>
               </c:forEach>
            </select>
            <input type="hidden" id="selectid" value="" />
        </span>

        <a href="javascript:void(0);" target="_self"><input class="chaxun" id="addnew" type="button" value="+添加品牌店铺"></a>
        <a href="${ctx }/platform/product/showBrand" target="_self"><input class="inquire" type="button" value="返回商品品牌"></a>
    </div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th width="40%">店铺</th>
                    <th width="30%">状态</th>
                    <th width="10%">是否为旗舰店</th>
                    <th width="30%">操作</th>
                </tr>
            </thead>
            <tbody id="list_title">
                <script id="shopbrandlist" type="text/html">
                    {{each list as ub i}}
                    <tr>
                        <td> {{ub.shopname}}</td>
                        <td>
                            {{if ub.checkstatus==0}}待审核
                            {{else if ub.checkstatus == 1}}审核通过
                            {{else}}审核未通过
                            {{/if}}
                        </td>
                        <td>
                            {{if ub.flag== false}}否
                            {{else if ub.checkstatus == 1}}是                           
                            {{/if}}
                        </td>
                        <td>
                            
                            {{if ub.checkstatus==1&&ub.flag== false}}
                            <a href="javascript:void(0);" name="set" class="set" data="{{ub.shopid}}"><span class="shenlan">设为旗舰店</span></a>
                            {{/if}}
                            <a href="javascript:void(0);" name="del"><span class="shenlan" onclick="ShopBrand.del({{ub.id}})">删除</span></a>
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
        </table>
        <div id="addDiv" style="display:none;">
           <!--  <div style=" float:left; margin-right:20px;">
                    <span>品牌：<input id="newbid" class="inp-seller" type="text" /></span>
                    <div>
                        <ul>
                            <script id="select_brandlist" type="text/html">
                                {{each list as brand i}}
                                <li data="{{brand.id}}">{{brand.name}}</li>
                                {{/each}}
                            </script>
                        </ul>
                    </div>
                </div> -->
            <div style=" float:left; margin-right:50px;">
                <span>店铺：<input id="newsid" class="inp-seller" type="text" /></span>
                <div>
                    <ul>
                        <script id="select_shoplist" type="text/html">
                            {{each list as shop i}}
                            <li data="{{shop.id}}">{{shop.name}}</li>
                            {{/each}}
                        </script>
                    </ul>
                </div>
            </div>
            <div class="preserve">
                <input class="preserve-inp" id="savenew" type="button" value="保存">
                <input class="preserve-inp_hs" id="hidenew" type="button" value="取消">
            </div><!--preserve stop -->
        </div>
        <div class="clear"></div>
        <div class="mar35"></div>
        <div class="sctp-con">
            <ul>
                <li>
                    <img src="/pv10/images/img3.png" width="130" height="130">
                    <div class="sctp-con-dele"><a href="javascript:void(0);"><img src="/pv10/images/dele.png" width="19" height="25"></a></div>
                </li>
                <li>
                    <img src="/pv10/images/img3.png" width="130" height="130">
                    <div class="sctp-con-dele"><a href="javascript:void(0);"><img src="/pv10/images/dele.png" width="19" height="25"></a></div>
                </li>
                <li>
                    <img src="/pv10/images/img3.png" width="130" height="130">
                    <div class="sctp-con-dele"><a href="javascript:void(0);"><img src="/pv10/images/dele.png" width="19" height="25"></a></div>
                </li>
                <li>
                    <img src="/pv10/images/img3.png" width="130" height="130">
                    <div class="sctp-con-dele"><a href="javascript:void(0);"><img src="/pv10/images/dele.png" width="19" height="25"></a></div>
                </li>
                <li>
                    <img src="/pv10/images/img3.png" width="130" height="130">
                    <div class="sctp-con-dele"><a href="javascript:void(0);"><img src="/pv10/images/dele.png" width="19" height="25"></a></div>
                </li>
                <li>
                    <img src="/pv10/images/img3.png" width="130" height="130">
                    <div class="sctp-con-dele"><a href="javascript:void(0);"><img src="/pv10/images/dele.png" width="19" height="25"></a></div>
                </li>
                <li>
                    <img src="/pv10/images/img3.png" width="130" height="130">
                    <div class="sctp-con-dele"><a href="javascript:void(0);"><img src="/pv10/images/dele.png" width="19" height="25"></a></div>
                </li>
                <li class="sctp-con-add">
                    <a href="javascript:void(0);"><img src="/pv10/images/add.png" width="45" height="44"></a>
                </li>
                <li class="sctp-con-add">
                    <a href="javascript:void(0);"><img src="/pv10/images/add.png" width="45" height="44"></a>
                </li>
                <li class="sctp-con-add">
                    <a href="javascript:void(0);"><img src="/pv10/images/add.png" width="45" height="44"></a>
                </li>

            </ul>
            <div class="clear"></div>
            <div class="preserve">
                <input class="preserve-inp" name="" type="button" value="保存">
            </div><!--preserve stop -->
            <div class="close">X</div>
        </div><!-- sctp-con stop-->
    </div><!--table-con  stop -->

</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        ShopBrand.bind();
       var brandid = GetQueryStringByName("id");
       $("#select_brand").val(brandid);
    })
</script>