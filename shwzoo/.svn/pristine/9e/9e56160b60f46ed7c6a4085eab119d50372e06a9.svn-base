<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/platform/js/product/spgl_warnsku.js"></script>
<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="notice-fenlei">
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
        <span class="marrig10"></span>        
        <span>商品名称：<input type="text" id="select_gname" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>预警数量：<input type="text" style="width:60px" id="warnbnum" class="inp-seller" />-
        <input type="text" style="width:60px" id="warnenum" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>库存数量：<input type="text" style="width:60px" id="stockbnum" class="inp-seller" />-
        <input type="text" style="width:60px" id="stockenum" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <input class="chaxun" name="select_button" type="button" value="查询" onclick="SKU.getSKUList(1)">        
    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th>商品名称</th>
                    <th>商品编码</th>
                    <th>商品原价</th>
                    <th>pc售价</th>
                    <th>App售价</th>
                    <th>wap售价</th>
                    <th>微信售价</th>
                    <th>商品库存</th>
                    <th>预警数量</th>
                    <th>操作</th>
                </tr>
            </thead>
          <tbody id="list_title">
				<script id="skulist" type="textml">
                        {{each list as sku i}}
                        <tr>
                            <td>{{sku.name}}</td>
                            <td>{{sku.num}}</td>
                            <td>{{sku.oldprice | toFixed}}</td>
                            <td>{{sku.price | toFixed}}</td>
                            <td>{{sku.appprice | toFixed}}</td>
                            <td>{{sku.wapprice | toFixed}}</td>
                            <td>{{sku.wechatprice | toFixed}}</td>
                            <td>{{sku.stock}}</td>
                            <td>{{sku.warnnum}}</td>
							<td>
								  <a href="/web/products/proinfo.html?spuid={{sku.spuId}}" target="_blank"><span class="shenlan">详情</span></a>
							     <a href="javascript:void(0)" data="{{sku.id}}" class="editstock"><span class="shenlan">编辑库存</span></a>
                            </td>
                        </tr>
                        {{/each}}
                    </script>
				</tbody>
        </table>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div><!--page stop -->
       
    </div><!--table-con  stop -->

</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //列表以及分页数据绑定
        SKU.getSKUList(1)
        $("#list_title").on("click",".editstock",function(){
        	var skuid=$(this).attr("data");
        	ConfigShow(skuid);
        })
    })
</script>