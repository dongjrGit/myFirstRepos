<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/manzengList.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        manzeng.bind();
        manzeng.getChecklist(1);
    })
</script>

<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>活动编号：<input type="text" id="num" class="inp-seller" /></span>
        <span class="marrig10"></span>
        <span>活动名称：<input type="text" id="name" class="inp-seller" /></span>
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
            活动类型：
            <select class="the-form-select-one" id="type_select">
                <option value="">全部</option>
                <option value="0">针对金额</option>
                <option value="1">针对商品</option>
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
            <tr id="manzeng_title">
                <th width="5%"><input name="chkall" id="selectAll" type="checkbox" value=""></th>
                <th width="8%">编号</th>
                <th width="12%">名称</th>
                <th width="8%">类型</th>
                <th width="10%">满足条件</th>
                <th width="8%">数量</th>
                <th width="10%">开始时间</th>
                <th width="10%">结束时间</th>
                <th width="8%">状态</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="checklist" type="text/html">
                    {{each list as manzeng i}}
                    <tr>
                        <td><input id="chk_{{manzeng.id}}" name="chksel" type="checkbox" value="{{manzeng.id}}"></td>
                        <td>{{manzeng.actnum}}</td>
                        <td>{{manzeng.actname}}</td>
                        <td>
                            {{if manzeng.usetype==0}}
                            针对金额
                            {{else}}
                            针对商品
                            {{/if}}
                        </td>
                        <td>
                            {{if manzeng.usetype==0}}
                            {{manzeng.fullvalue | toFixed}}元
                            {{else }}
                            商品：{{manzeng.name}}，{{manzeng.count}}件
                            {{/if}}
                        </td>
                        <td>{{manzeng.stock}}</td>
                        <td>{{manzeng.starttimestr}}</td>
                        <td>{{manzeng.endtimestr}}</td>
                        <td id="td_{{manzeng.id}}">
                            {{if manzeng.status==0}}
                            <span class="lvs"><a id="a_{{manzeng.id}}" href="javascript:void(0);" onclick="setStatus({{manzeng.id}},1)">启用</a></span>
                            {{else }}
                            <span class="lvs"><a id="a_{{manzeng.id}}" href="javascript:void(0);" onclick="setStatus({{manzeng.id}},0)">禁用</a></span>
                            {{/if}}
                        </td>
                        <td>
                            <a href="yxgl_GiftList?id={{manzeng.id}}&type=1"><span class="shenlan">赠品列表</span></a>
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" class="check" data-id="{{manzeng.id}}"><span class="shenlan">审核</span></a>
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