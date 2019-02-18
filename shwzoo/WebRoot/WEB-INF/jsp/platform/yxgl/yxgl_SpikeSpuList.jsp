<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>活动编号：<span class="red">${data.spikenum }</span></span>
        <span class="marrig10"></span>
        <span>活动名称：<span class="red">${data.spikename }</span></span>
        <input type="hidden" id="spikeid" value="${data.id }" />
        <input class="inquire chaxun" name="btnadd" type="button" value="+添加商品">
        <span class="marrig10"></span>
        <a href="yxgl_SpikeList" target="_self"><input class="inquire" name="" type="button" value="返回列表"></a>
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="spikesku_title">
                <th width="10%">商品编号</th>
                <th width="20%">商品名称</th>
                <th width="20%">商品图片</th>
                <th width="10%">商品价格</th>
                <th width="10%">优惠价格</th>
                <th width="10%">数量</th>
                <th width="10%">手机专享</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="spikeskulist" type="text/html">
                    {{each list as spikespu i}}
                    <tr>
                        <td>{{spikespu.num}}</td>
                        <td>{{spikespu.name}}</td>
                        <td>
                            <img style="height:80px;width:100px;" alt="" src="{{spikespu.imgUrl}}" />
                        </td>
                        <td>{{spikespu.price}}</td>
                        <td>{{spikespu.discountPrice}}</td>
                        <td>{{spikespu.spuCount}}</td>
                        <td id="td_{{spikespu.id}}">
                            {{if spikespu.isPhone==0}}
                            <span class="lvs"><a id="a_{{spikespu.id}}" href="javascript:void(0);" onclick="setIsPhone({{spikespu.id}},1)">否</a></span>
                            {{else }}
                            <span class="lvs"><a id="a_{{spikespu.id}}" href="javascript:void(0);" onclick="setIsPhone({{spikespu.id}},0)">是</a></span>
                            {{/if}}
                        </td>
                        <td>
                            <a href="yxgl_SpikeSpuEdit?id={{spikespu.id}}"><span class="shenlan">编辑</span></a>
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" class="del" data-id="{{spikespu.id}}"><span class="shenlan">删除</span></a>
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
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/spikeSku.js"></script>
<script type="text/javascript">
    $(document).ready(function () { spikesku.bind(); })
</script>