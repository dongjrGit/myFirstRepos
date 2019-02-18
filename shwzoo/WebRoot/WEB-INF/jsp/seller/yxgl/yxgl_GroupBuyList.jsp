<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/seller/js/yxgl/groupbuylist.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    	groupb.bind();
    	groupb.getlist(1);
    })
</script>
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt; 团购 &gt; 团购列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>

                <span><label>团购标题：</label><input type="text" id="title" class="text-inp" /></span>
                <span>
                    <label>
                                         类型：
                    </label>
                    <select class="sel-form" id="type_select">
                        <option value="-1">所有</option>
                        <option value="0">代金券</option>
                        <option value="1">打折卡</option>
                        <option value="2">活动优惠</option>
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
                <input class="but-comm" name="btnadd" type="button" value="+添加团购">
            </form>
        </div>

        <div class="clear"></div>
        <div class="mar35"></div>
        <div class="thgl">
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
                            {{if gb.status==0}}
                            <span class="lvs"><a id="a_{{gb.id}}" href="javascript:void(0);" onclick="setStatus({{gb.id}},1)">启用</a></span>
                            {{else }}
                            <span class="lvs"><a id="a_{{gb.id}}" href="javascript:void(0);" onclick="setStatus({{gb.id}},0)">禁用</a></span>
                            {{/if}}
                        </td>
                        <td>
                            <a href="yxgl_GroupBuyEdit?id={{gb.id}}"><span class="shenlan">编辑</span></a>
                            <span class="marrig35"></span>
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
</div>