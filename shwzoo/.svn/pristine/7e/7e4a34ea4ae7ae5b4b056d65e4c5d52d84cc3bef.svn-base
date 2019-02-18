<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/packagelist.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        pack.bind();
        pack.getlist(1);
    })
</script>
<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>组合商品编号：<input type="text" id="num" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>组合商品名称：<input type="text" id="name" class="inp-seller" /></span>
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
            审核状态：
            <select id="checkss" name="checkss" class="the-form-select">
                <option value="">全部</option>
                <option value="0">未审核</option>
                <option value="1">已审核</option>
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
        <input class="inquire chaxun" name="btnadd" type="button" value="+添加组合商品">
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="pack_title">
                <th width="10%">编号</th>
                <th width="12%">名称</th>
                <th width="8%">价格</th>
                <th width="10%">开始时间</th>
                <th width="10%">结束时间</th>
                <th width="8%">审核状态</th>
                <th width="6%">状态</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="packlist" type="text/html">
                    {{each list as pack i}}
                    <tr>
                        <td>{{pack.num}}</td>
                        <td>{{pack.name}}</td>  
                        <td>{{pack.price | toFixed}}</td>
                        <td>{{pack.starttimestr}}</td>
                        <td>{{pack.endtimestr}}</td>
                        <td id="check_{{pack.id}}">
                            {{if pack.ischeck==false}}
                            <span>未审核</span>
                            {{else}}
                            <span>已审核</span>
                            {{/if}}
                        </td>
                        <td id="td_{{pack.id}}">
                            {{if pack.shopid== ${shopid} }}
                            {{if pack.status==0}}
                            <span class="lvs"><a id="a_{{pack.id}}" href="javascript:void(0);" onclick="setStatus({{pack.id}},1)">启用</a></span>
                            {{else }}
                            <span class="lvs"><a id="a_{{pack.id}}" href="javascript:void(0);" onclick="setStatus({{pack.id}},0)">禁用</a></span>
                            {{/if}}
                            {{else }}
                            {{if pack.status==0}}
                            <span>启用</span>
                            {{else }}
                            <span>禁用</span>
                            {{/if}}

                            {{/if}}
                        </td>
                        <td id="cz_{{pack.id}}">
                            {{if pack.shopid== ${shopid} }}
                            <a href="yxgl_PackageDetail?id={{pack.id}}"><span class="shenlan">查看</span></a>
                            <span class="marrig35"></span>
                            <a href="yxgl_PackageEdit?id={{pack.id}}"><span class="shenlan">编辑</span></a>
                            {{if pack.ischeck==false}}
                            <a href="javascript:void(0);" class="ischeck" data="{{pack.id}}"><span class="shenlan">审核</span></a>
                            <span class="marrig35"></span>
                            {{/if}}
                            {{else }}
                            <a href="yxgl_PackageDetail?id={{pack.id}}"><span class="shenlan">查看</span></a>
                            {{/if}}
                            <span class="marrig35"></span>
                            <a href="yxgl_PackageSku?id={{pack.id}}&type=0"><span class="shenlan">关联商品</span></a>
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" class="del" data="{{pack.id}}"><span class="shenlan">删除</span></a>
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