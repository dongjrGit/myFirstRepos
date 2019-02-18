<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/manzengList.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        manzeng.bind();
        manzeng.getlist(1);
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
        <span class="marrig10"></span><br/>
        <span>结束时间：</span>
        <input type="text" id="ends" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'ende\')}' })" value="" readonly="readonly" />-
        <input type="text" id="ende" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'ends\')}' })" value="" readonly="readonly" />
        <span class="marrig10"></span>
        <input class="inquire" name="btnsearch" type="button" value="查询">
        <input class="inquire chaxun" name="btnadd" type="button" value="+添加满赠活动">
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="manzeng_title">
                <th width="8%">编号</th>
                <th width="12%">名称</th>
                <th width="8%">类型</th>
                <th width="12%">所属店铺</th>
                <th width="7%">满足条件</th>
                <th width="7%">数量</th>
                <th width="8%">审核状态</th>
                <th width="10%">开始时间</th>
                <th width="10%">结束时间</th>
                <th width="8%">状态</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="manzenglist" type="text/html">
                    {{each list as manzeng i}}
                    <tr>
                        <td>{{manzeng.actnum}}</td>
                        <td>{{manzeng.actname}}</td>
                        <td>
                            {{if manzeng.usetype==0}}
                            针对金额
                            {{else}}
                            针对商品
                            {{/if}}
                        </td>
                        <td>{{manzeng.shopname}}</td>
                        <td>
                            {{if manzeng.usetype==0}}
                            {{manzeng.fullvalue | toFixed}}元
                            {{else }}
                            {{manzeng.count}}件
                            {{/if}}
                        </td>
                        <td>{{manzeng.stock}}</td>
                        <td id="check_{{manzeng.id}}">
                            {{if manzeng.ischeck==false}}
                            <span>未审核</span>
                            {{else}}
                            <span>已审核</span>
                            {{/if}}
                        </td>
                        <td>{{manzeng.starttimestr}}</td>
                        <td>{{manzeng.endtimestr}}</td>
                        <td id="td_{{manzeng.id}}">
                            {{if manzeng.shopid== ${shopid} }}
                            {{if manzeng.status==0}}
                            <span class="lvs"><a id="a_{{manzeng.id}}" href="javascript:void(0);" onclick="setStatus({{manzeng.id}},1)">启用</a></span>
                            {{else }}
                            <span class="lvs"><a id="a_{{manzeng.id}}" href="javascript:void(0);" onclick="setStatus({{manzeng.id}},0)">禁用</a></span>
                            {{/if}}
                            {{else }}
                            {{if manzeng.status==0}}
                            <span>启用</span>
                            {{else }}
                            <span>禁用</span>
                            {{/if}}
                            {{/if}}
                        </td>
                        <td id="cz_{{manzeng.id}}">

                            <a href="yxgl_GiftList?id={{manzeng.id}}&type=0"><span class="shenlan">赠品列表</span></a>
                            <span class="marrig35"></span>
                             {{if manzeng.shopid== ${shopid} }}
                            <a href="yxgl_FullgiftDetail?id={{manzeng.id}}"><span class="shenlan">查看</span></a>
                            <span class="marrig35"></span>
                            <a href="yxgl_FullgiftEdit?id={{manzeng.id}}"><span class="shenlan">编辑</span></a>
                            <span class="marrig35"></span>
                            {{if manzeng.ischeck==false}}
                            <a href="javascript:void(0);" class="ischeck" data="{{manzeng.id}}"><span class="shenlan">审核</span></a>
                            <span class="marrig35"></span>
                            {{/if}}
                            {{else }}
                            <a href="yxgl_FullgiftDetail?id={{manzeng.id}}"><span class="shenlan">查看</span></a>
                            {{/if}}

                            <a href="javascript:void(0);" class="del" data="{{manzeng.id}}"><span class="shenlan">删除</span></a>
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
