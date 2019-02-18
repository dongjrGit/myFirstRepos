<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/seller/js/yxgl/manjianList.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        manjian.bind();
        manjian.getlist(1);
    })
</script>
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt; 满减活动 &gt; 满减活动列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>
                <span>活动编号：<input type="text" id="num" class="text-inp" /></span>
                <span class="marrig10"></span>
                <span>活动名称：<input type="text" id="name" class="text-inp" /></span>
                <span class="marrig10"></span>
                <span>
                    <label>审核状态：</label>
                    <select name="checkstatus" id="checkstatus" class="sel-form">
                        <option value="">全部</option>
                        <option value="0">未审核</option>
                        <option value="1">已审核</option>
                    </select>
                </span>
                <span class="marrig10"></span>
                <span>
                    活动类型：
                    <select class="sel-form" id="type_select">
                        <option value="">全部</option>
                        <option value="0">针对金额</option>
                        <option value="1">针对商品</option>
                    </select>
                </span>
                <span class="marrig10"></span>
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
                
                <div class="submit-but">
                    <input type="button" value="查询" name="btnsearch" class="but-comm">
                    <input class="but-comm" name="btnadd" type="button" value="+添加满减活动">
                </div>
            </form>
        </div>
        <div class="clear"></div>
        <div class="thgl">
            <table class="data_list">
                <tr id="manjian_title">
                    <th width="8%">编号</th>
                    <th width="12%">名称</th>
                    <th width="8%">类型</th>
                    <th width="10%">满足条件</th>
                    <th width="10%">减免金额(元)</th>
                    <th width="8%">数量</th>
                    <th width="10%">开始时间</th>
                    <th width="10%">结束时间</th>
                    <th width="8%">状态</th>
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
                            <td>
                                {{if manjian.usetype==0}}
                                {{manjian.fullvalue | toFixed}}元
                                {{else }}
                                {{manjian.count}}件
                                {{/if}}
                            </td>
                            <td>{{manjian.subvalue | toFixed}}</td>
                            <td>{{manjian.stock}}</td>
                            <td>{{manjian.starttimestr}}</td>
                            <td>{{manjian.endtimestr}}</td>
                            <td id="check_{{manjian.id}}">
                                {{if manjian.ischeck==false}}
                                <span>未审核</span>
                                {{else}}
                                <span>已审核</span>
                                {{/if}}
                            </td>
                            <td id="cz_{{manjian.id}}">
                                {{if manjian.ischeck==false}}
                                <a href="javascript:void(0);" class="ischeck" data="{{manjian.id}}"><span class="shenlan">审核</span></a>
                                <span class="marrig35"></span>
                                {{/if}}
                                <a href="yxgl_FullcutEdit?id={{manjian.id}}"><span class="shenlan">编辑</span></a>
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
</div>