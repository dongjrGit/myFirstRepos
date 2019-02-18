<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="/resource/public/platform/js/product/spgl_spulb.js"></script>

<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        所属分类：
        <select class="the-form-select-one" id="fc_select" onchange="SPU.fatherChange('fc')">
            <script id="flist" type="text/html">
                {{each list as fclass i}}
                <option value="{{fclass.id}}">{{fclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" id="sc_select" onchange="SPU.fatherChange('sc')">
            <script id="slist" type="text/html">
                {{each list as sclass i}}
                <option value="{{sclass.id}}">{{sclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" id="tc_select">
            <script id="tlist" type="text/html">
                {{each list as tclass i}}
                <option value="{{tclass.id}}">{{tclass.name}}</option>
                {{/each}}
            </script>
        </select>
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
        <span class="marrig10"></span>
        <span>
            商品状态：
            <select class="the-form-select-one" id="status_select">
                <option value="-1">所有</option>
                <option value="0">未审核</option>
                <option value="1">审核中</option>
                <option value="2">已下架</option>
                <option value="3">已上架</option>
                <option value="5">已冻结</option>
                <option value="6">解冻申请中</option>
            </select>
        </span>
        <span class="marrig10"></span>
        <span>商品名称：<input type="text" id="name_select" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>商品编号：<input type="text" id="num_select" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="chaxun" name="select_button" type="button" value="查询"></span>

    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th width="5%">序号</th>
                    <th>商品名称</th>
                    <th>商品编码</th>
                    <th>所属店铺</th> 
                    <th>商品短标题</th>
                    <th width="20%">所属分类</th>
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
                        <td>{{spu.pronum}}</td>
                        <td>{{spu.shopname}}</td>
                        <td>{{spu.titleShort}}</td>
                        <td>{{spu.fullpath}}</td>
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
                            <a href="/web/products/proinfo.html?spuid={{spu.id}}" target="_blank"><span class="shenlan">详情</span></a>
                            <a href="javascript:void(0);" class="del" data="{{spu.id}}"><span class="shenlan">删除</span></a>
                            <a href="goods_spgl_dpspuimg?sid={{spu.id}}"><span class="shenlan">图片管理</span></a>
                            <a href="goods_spgl_skulb?spu_id={{spu.id}}"><span class="shenlan">库存商品管理</span></a>
                            {{if spu.status==5}}
                            <a href="javascript:void(0);" data-stauts="1" data-spuid="{{spu.id}}"><span class="shenlan">解冻</span></a>
                            {{else if spu.status==6}}
                            <a href="javascript:void(0);" data-stauts="1" data-spuid="{{spu.id}}"><span class="shenlan">解冻</span></a>
                            <a href="javascript:void(0);" data-stauts="0" data-spuid="{{spu.id}}"><span class="shenlan">继续冻结</span></a>
                            {{else if spu.status>1}}
                            <a href="javascript:void(0);" data-stauts="0" data-spuid="{{spu.id}}"><span class="shenlan">冻结</span></a>
                            {{/if}}
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
    })
</script>
