<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/groupbuylist.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    	groupb.bind();
    	groupb.getlist(1);
    })
</script>

<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>团购标题：<input type="text" id="" class="inp-seller" /></span>
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
            类型：
            <select class="the-form-select-one" id="type_select">
                <option value="-1">所有</option>
                <option value="0">代金券</option>
                <option value="1">打折卡</option>
                <option value="2">活动优惠</option>
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
        <input class="inquire chaxun" name="btnadd" type="button" value="+添加团购">
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="buy_title">
                <th width="18%">标题</th>
                <th width="8%">现价</th>
                <th width="8%">原价</th>
                <th width="6%">类型</th>
                <th width="6%">剩余数量</th>
                <th width="10%">开始时间</th>
                <th width="10%">结束时间</th>
                <th width="6%">随时退</th>
                <th width="6%">过期退</th>
                <th width="6%">状态</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="grouplist" type="text/html">
                    {{each list as gb i}}
                    <tr>
                        <td>{{gb.title}}</td>
                        <td>{{gb.cprice | toFixed}}</td>
                        <td>{{gb.oprice | toFixed}}</td>
                        <td>
                            {{if gb.type==0}}
                                                           代金券
                            {{else if gb.type==1}}
                                                            打折卡
                            {{else if gb.type==2}}
                                                           活动优惠
                            {{/if}}
                        </td>
                        <td>{{gb.stock}}</td>
                        <td>{{gb.startstr}}</td>
                        <td>{{gb.endstr}}</td>
                        <td>
                            {{if gb.isanytime==true}}
                                                           是
                            {{else}}
                                                            否
                            {{/if}}
                        </td>
                        <td>
                            {{if gb.isexpired==true}}
                                                           是
                            {{else}}
                                                           否
                            {{/if}}
                        </td>
                         <td id="td_{{gb.id}}">
                            {{if gb.shopid== ${shopid} }}
                            {{if gb.status==0}}
                            <span class="lvs"><a id="a_{{gb.id}}" href="javascript:void(0);" onclick="setStatus({{gb.id}},1)">启用</a></span>
                            {{else }}
                            <span class="lvs"><a id="a_{{gb.id}}" href="javascript:void(0);" onclick="setStatus({{gb.id}},0)">禁用</a></span>
                            {{/if}}
                            {{else }}
                            {{if gb.status==0}}
                            <span>启用</span>
                            {{else }}
                            <span>禁用</span>
                            {{/if}}
                            {{/if}}
                        </td>
                        <td>
                           {{if gb.shopid== ${shopid} }}
                            <a href="yxgl_GroupBuyEdit?id={{gb.id}}"><span class="shenlan">编辑</span></a>
                            <span class="marrig35"></span>
                            <a href="yxgl_GroupBuyDetail?id={{gb.id}}"><span class="shenlan">查看</span></a>
                            <span class="marrig35"></span>
                           {{else }}
                           <a href="yxgl_GroupBuyDetail?id={{gb.id}}"><span class="shenlan">查看</span></a>
                           <span class="marrig35"></span>
                           {{/if}}
                            <a href="javascript:void(0);" class="del" data="{{gb.id}}"><span class="shenlan">删除</span></a>
                            <span class="marrig35"></span>
                            <a href="yxgl_GroupBuyImgList?id={{gb.id}}"><span class="shenlan">图片管理</span></a>
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