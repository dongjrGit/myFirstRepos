<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/packagelist.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        pack.bind();
        pack.getChecklist(1);
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
            组合商品类型：
            <select id="packtype" name="packtype" class="the-form-select">
                <option value="">全部</option>
                <option value="0">限时抢购</option>
                <option value="1">组合商品</option>
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
        <input class="inquire" name="btncheck" type="button" value="查询">
        <span class="marrig35"></span>
        <input class="inquire" name="checks" type="button" value="批量审核">
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="pack_title">
                <th width="5%"><input name="chkall" type="checkbox" value=""></th>
                <th width="10%">编号</th>
                <th width="12%">名称</th>
                <th width="8%">价格</th>
                <th width="10%">开始时间</th>
                <th width="10%">结束时间</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="checklist" type="text/html">
                    {{each list as pack i}}
                    <tr>
                        <td><input id="chk_{{pack.id}}" name="chksel" type="checkbox" value="{{pack.id}}"></td>
                        <td>{{pack.num}}</td>
                        <td>{{pack.name}}</td>
                        <td>{{pack.price | toFixed}}</td>
                        <td>{{pack.starttimestr}}</td>
                        <td>{{pack.endtimestr}}</td>
                        <td>
                            <a href="javascript:void(0);" class="check" data-id="{{pack.id}}"><span class="shenlan">同意</span></a>
                            <span class="marrig35"></span>
                            <a href="yxgl_PackageSku?id={{pack.id}}&type=1"><span class="shenlan">查看关联商品</span></a>
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