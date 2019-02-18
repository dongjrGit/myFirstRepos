<!-- 直营商品列表 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript" src="/resource/public/platform/js/product/spgl_spulb.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <span>商品名称：<input type="text" id="name_select" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>商品编号：<input type="text" id="num_select" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>
                商品状态：
            <select id="status_select" class="the-form-select-one">
                <option value="">所有</option>
                <option value="4">已下架</option>
                <option value="3">已上架</option>
            </select>
        </span>
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
        <input type="hidden" value="${isoffer }" id="isoffer"/>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="chaxun" name="select_button" type="button" value="查询"></span>
        <c:if test="${isoffer != 1 }">
	        <a href="/platform/product/goods_dpaddspu" target="_self"><input class="chaxun" name="" type="button" value="+添加商品"></a>
        </c:if>
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>商品名称</th>
                    <th width="10%">所属店铺</th>
                    <th width="10%">商品编码</th>
                    <th width="10%">商品短标题</th>
                    <th width="10%">商品价格</th>
                    <th width="5%">商品状态</th>
                    <th width="30%">操作</th>
                </tr>
            </thead>
            <tbody id="list_title">
                <script id="spulist" type="text/html">
                    {{each list as spu i}}
                    <tr>
                        <td>{{i+1}}</td>
                        <td>{{spu.name}}</td>
                        <td>{{spu.shopName}}</td>
                        <td>{{spu.proNum}}</td>
                        <td>{{spu.titleShort}}</td>
                        <td>{{spu.price}}</td>
                        <td>
                            <div class="pos_rela">
                                {{if spu.status==0}}未审核
                                {{else if spu.status==1}}<span class="l_xsztdj" data-id="{{spu.id}}">提交审核中</span>
                                {{else if spu.status==2 || spu.status==4}}<span class="l_xsztdj" data-id="{{spu.id}}">已下架</span>
                                {{else if spu.status==3}}<span class="l_xsztdj" data-id="{{spu.id}}">已上架</span>
                                {{else if spu.status==5}}<span class="l_xsztdj" data-id="{{spu.id}}">已冻结</span>
                                {{else if spu.status==6}}<span class="l_xsztdj" data-id="{{spu.id}}">解冻申请中</span>
                                {{/if}}
                                <div id="divrecord_{{spu.id}}" class="l_xsztcon" style="display:none;">
                                    <div class="l_xtb_xszt"></div>
                                    <div class="l_xszt">
                                        <ul></ul>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td>
                             <a href="/platform/product/goods_editspu?spuid={{spu.id}}"><span class="shenlan">编辑</span></a>
                            <a href="javascript:void(0);" class="del" data="{{spu.id}}"><span class="shenlan">删除</span></a>
                            <a href="/platform/product/showSpuImg?spuid={{spu.id}}"><span class="shenlan">图片管理</span></a>                            
               
                            {{if spu.status==3}}
                            <a href="javascript:void(0);" data-stauts="4" data-spuid="{{spu.id}}"><span class="shenlan">下架</span></a>
                            {{else if spu.status==4}}
                            <a href="javascript:void(0);" data-stauts="3" data-spuid="{{spu.id}}"><span class="shenlan">上架</span></a>
                            {{/if}}
							<a href="/platform/product/showSpuStock?spuid={{spu.id}}"><span class="shenlan">库存管理</span></a>
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
    	SPU.bind();
    	SPU.getSPUList(1, SPU.unit);
    })
</script>

