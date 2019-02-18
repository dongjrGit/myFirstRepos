<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath}/resource/public/seller/js/yxgl/packagelist.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        pack.bind();
        pack.getlist(1);
    })
</script>

<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt; 组合商品 &gt; 组合商品列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>
                <span><label>组合编号：</label><input type="text" id="num" class="text-inp" /></span>
                <span><label>组合名称：</label><input type="text" id="name" class="text-inp" /></span>
                <span>
                    <label>
                        审核状态：
                    </label>
                    <select class="sel-form" id="checkss">
                        <option value="">全部</option>
                        <option value="0">未审核</option>
                        <option value="1">已审核</option>
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
                <input class="but-comm" name="btnadd" type="button" value="+添加组合商品">
            </form>
        </div>

        <div class="clear"></div>
        <div class="mar35"></div>
        <div class="thgl">
            <table class="data_list">
                <tr id="pack_title">
                    <th width="8%">编号</th>
                    <th width="12%">名称</th>
                    <th width="8%">价格</th>
                    <th width="10%">开始时间</th>
                    <th width="10%">结束时间</th>
                    <th width="8%">审核状态</th>
                    <th>操作</th>
                </tr>
                <tbody id="datalist">
                    <script id="packlist" type="text/html">
                        {{each list as pack i}}
                        <tr>
                            <td>{{pack.num}}</td>
                            <td>{{pack.name}}</td>
                            <td>{{pack.price | toFixed}}</td>
                           
                            <td>{{pack.starttime}}</td>
                            <td>{{pack.endtime}}</td>
                            <td id="check_{{pack.id}}">
                                {{if pack.ischeck==false}}
                                <span>未审核</span>
                                {{else}}
                                <span>已审核</span>
                                {{/if}}
                            </td>
                            <td id="cz_{{pack.id}}">
                                {{if pack.ischeck==false}}
                                <a href="javascript:void(0);" class="ischeck" data-cs="{{pack.ischeck}}" data="{{pack.id}}"><span class="shenlan">内部审核</span></a>
                                <span class="marrig35"></span>
                                {{/if}}
                                <a href="yxgl_PackageEdit?id={{pack.id}}"><span class="shenlan">编辑</span></a>
                                <span class="marrig35"></span>
                                <a href="javascript:void(0);" class="del" data="{{pack.id}}"><span class="shenlan">删除</span></a>
                                <span class="marrig35"></span>
                                <a href="yxgl_PackageSku?id={{pack.id}}"><span class="shenlan">关联商品</span></a>
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