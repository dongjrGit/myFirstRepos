<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/product/spgl_spsh.js"></script>

<div class="clear"></div>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
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
        <span>商品名称：<input type="text" id="name_select" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>商品编号：<input type="text" id="num_select" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <input class="inquire" name="search" type="button" value="查询">
        <span class="marrig35"></span>
        <input class="inquire" name="spshs" type="button" value="批量审核">
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th><input name="chkall" type="checkbox" value=""></th>
                    <th>商品名称</th>
                    <th>商品编码</th>
                    <th>所属店铺</th>
                    <th>商品短标题</th>
                    <th>商品状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="list_title">
                <script id="spulist" type="text/html">
                    {{each list as spu i}}
                    <tr>
                        <td><input id="chk_{{spu.id}}" name="chksel" type="checkbox" value="{{spu.id}}"></td>
                        <td>{{spu.name}}</td>
                        <td>{{spu.proNum}}</td>
                        <td>{{spu.shopName}}</td>
                        <td>{{spu.titleShort}}</td>
                        <td>
                            {{if spu.status==0}}未审核
                            {{else if spu.status==1}}提交审核中
                            {{else if spu.status==2 || spu.status==4}}已下架
                            {{else if spu.status==3}}已上架
                            {{else if spu.status==5}}已冻结
                            {{else if spu.status==6}}解冻申请中
                            {{/if}}
                        </td>
                        <td><a href="javascript:void(0);" data-spuid="{{spu.id}}"><span class="shenlan">审核</span></a></td>
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
        list.bind();
        search();
    })
</script>