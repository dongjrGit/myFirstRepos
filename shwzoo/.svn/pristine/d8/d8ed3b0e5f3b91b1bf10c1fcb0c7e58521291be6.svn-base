<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/manjianList.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        manjian.bind();
        manjian.getlist(1);
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
        <span class="marrig10"></span>
        <span>结束时间：</span>
        <input type="text" id="ends" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'ende\')}' })" value="" readonly="readonly" />-
        <input type="text" id="ende" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'ends\')}' })" value="" readonly="readonly" />
        <span class="marrig10"></span>
        <input class="inquire" name="btnsearch" type="button" value="查询">
        <input class="inquire chaxun" name="btnadd" type="button" value="+添加满减活动">
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="manjian_title">
                <th width="8%">编号</th>
                <th width="12%">名称</th>
                <th width="6%">类型</th>
                <th width="12%">所属店铺</th>
                <th width="7%">满足条件</th>
                <th width="7%">减免金额（元）</th>
                <th width="6%">数量</th>
                <th width="6%">审核状态</th>
                <th width="10%">开始时间</th>
                <th width="10%">结束时间</th>
                <th width="6%">状态</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="manjianlist" type="text/html">
                    {{each list as manjian i}}
                    <tr>
                        <td>{{manjian.actnum}}</td>
                        <td>{{manjian.actname}}</td>
                        <td>
                            {{if manjian.usetype==0}}
                            针对金额
                            {{else}}
                            针对商品
                            {{/if}}
                        </td>
                        <td>{{manjian.shopname}}</td>
                        <td>
                            {{if manjian.usetype==0}}
                            {{manjian.fullvalue | toFixed}}元
                            {{else }}
                            {{manjian.count}}件
                            {{/if}}
                        </td>
                        <td>{{manjian.subvalue | toFixed}}</td>
                        <td>{{manjian.stock}}</td>
                        <td id="check_{{manjian.id}}">
                            {{if manjian.ischeck==0}}
                            <span>未审核</span>
                            {{else}}
                            <span>已审核</span>
                            {{/if}}
                        </td>
                        <td>{{manjian.starttimestr}}</td>
                        <td>{{manjian.endtimestr}}</td>
                        <td id="td_{{manjian.id}}">
                            {{if manjian.shopid== ${shopid}}}
                            {{if manjian.status==0}}
                            <span class="lvs"><a id="a_{{manjian.id}}" href="javascript:void(0);" onclick="setStatus({{manjian.id}},1)">启用</a></span>
                            {{else }}
                            <span class="lvs"><a id="a_{{manjian.id}}" href="javascript:void(0);" onclick="setStatus({{manjian.id}},0)">禁用</a></span>
                            {{/if}}
                            {{else }}
                            {{if manjian.status==0}}
                            <span>启用</span>
                            {{else }}
                            <span>禁用</span>
                            {{/if}}
                            {{/if}}
                        </td>
                        <td id="cz_{{manjian.id}}">
                            {{if manjian.shopid== ${shopid} }}

                            <a href="yxgl_FullcutDetail?id={{manjian.id}}"><span class="shenlan">查看</span></a>
                            <span class="marrig35"></span>
                            <a href="yxgl_FullcutEdit?id={{manjian.id}}"><span class="shenlan">编辑</span></a>
                            {{if manjian.ischeck==false}}
                            <a href="javascript:void(0);" class="ischeck" data="{{manjian.id}}"><span class="shenlan">审核</span></a>
                            <span class="marrig35"></span>
                            {{/if}}
                            {{else }}
                            <a href="yxgl_FullcutDetail?id={{manjian.id}}"><span class="shenlan">查看</span></a>
                            {{/if}}
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" class="del" data="{{manjian.id}}"><span class="shenlan">删除</span></a>
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