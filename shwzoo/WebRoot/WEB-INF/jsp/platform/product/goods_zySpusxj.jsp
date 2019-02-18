<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/product/spgl_zyspsxj.js"></script>
<script src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_class.js"></script>
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
        <span>
            商品状态：
            <select id="status_select" class="the-form-select-one">
                <option value="">所有</option>
                <option value="2">已下架</option>
                <option value="3">已上架</option>
            </select>
        </span>
        <span class="marrig10"></span>
        <span>商品名称：<input type="text" id="name_select" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>商品编号：<input type="text" id="num_select" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="chaxun" name="select_button" type="button" value="查询"></span>
        <input class="inquire" name="spsj" type="button" value="批量上架">
        <input class="inquire" name="spxj" type="button" value="批量下架">

    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th width="10%"><input name="chkall" type="checkbox" value=""></th>
                    <th>商品名称</th>
                    <th>商品编码</th>
                    <th>商品短标题</th>
                    <th>所属分类</th>
                    <th>商品状态</th>
                    <th width="15%">操作</th>
                </tr>
            </thead>
            <tbody id="list_title">
                <script id="spulist" type="text/html">
                    {{each list as spu i}}
                    <tr>
                        <td><input id="chk_{{spu.id}}" name="chksel" type="checkbox" value="{{spu.id}}_{{spu.status}}"></td>
                        <td>{{spu.name}}</td>
                        <td>{{spu.proNum}}</td>
                        <td>{{spu.titleShort}}</td>
                        <td>{{spu.fullPathName}}</td>
                        <td>
                            <div class="pos_rela">
                                {{if spu.status==2 || spu.status==4}}<span class="l_xsztdj" data-id="{{spu.id}}"><a class="red">已下架</a></span>
                                {{else if spu.status==3}}<span class="l_xsztdj" data-id="{{spu.id}}"><a class="lanse">已上架</a></span>
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
                            {{if spu.status==2 || spu.status==4}}<a href="javascript:void(0);" data-type="3" data-spuid="{{spu.id}}"><span class="shenlan">上架</span></a>
                            {{else if spu.status==3}}<a href="javascript:void(0);" data-type="4" data-spuid="{{spu.id}}"><span class="shenlan">下架</span></a>
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
    	SPU.getSPUList(1, SPU.unit);
    })
</script>