<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>活动编号：<input type="text" id="num" class="inp-seller" /></span>
      <span>
            活动类型：
            <select class="the-form-select-one" id="type_select">
                <option value="">全部</option>
                <option value="0">秒杀</option>
                <option value="1">闪购</option>
            </select>
        </span> 
        <span class="marrig10"></span>
        <span>开始时间：</span>
        <input type="text" id="starts" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', maxDate: '#F{$dp.$D(\'starte\')}' })" value="" readonly="readonly" />-
        <input type="text" id="starte" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', minDate: '#F{$dp.$D(\'starts\')}' })" value="" readonly="readonly" />
        <span class="marrig10"></span>
        <span>结束时间：</span>
        <input type="text" id="ends" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', maxDate: '#F{$dp.$D(\'ende\')}' })" value="" readonly="readonly" />-
        <input type="text" id="ende" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', minDate: '#F{$dp.$D(\'ends\')}' })" value="" readonly="readonly" />
        <span class="marrig10"></span>
        <input class="inquire" name="btnsearch" type="button" value="查询">
        <span class="marrig35"></span>
        <input class="inquire" name="checks" type="button" value="批量审核">
        <span class="marrig10"></span>
        <input class="inquire" name="checkbhs" type="button" value="批量驳回">
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="spike_title">
                <th width="5%"><input name="chkall" type="checkbox" value=""></th>
                <th width="10%">编号</th>
                <th width="15%">名称</th>
                <th width="8%">类型</th>
                <th width="12%">开始时间</th>
                <th width="12%">结束时间</th>
                <th width="10%">申请店铺</th>
                <th width="10%">申请时间</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="spikelist" type="text/html">
                    {{each list as spike i}}
                    <tr>
                        <td><input id="chk_{{spike.spikeshopid}}" name="chksel" type="checkbox" value="{{spike.spikeshopid}}"></td>
                        <td>{{spike.spikenum}}</td>
                        <td>{{spike.spikename}}</td>
                        <td>
                            {{if spike.spiketype==0}}
                            秒杀
                            {{else if spike.spiketype==1}}
                                                          闪购
                            {{else if spike.spiketype==2}}
                                                          促销
                            {{/if}}
                        </td>
                        <td>{{spike.starttimestr}}</td>
                        <td>{{spike.endtimestr}}</td>
                        <td>{{spike.shopname}}</td>
                        <td>{{spike.sscreatetimestr}}</td>
                        <td>
                            {{if spike.spikeshopstatus==1}}
                            <a href="javascript:void(0);" class="check" data-id="{{spike.spikeshopid}}" data-type="2"><span class="shenlan">同意</span></a>
                            <span class="marrig35"></span>
                            <a href="javascript:void(0);" class="check" data-id="{{spike.spikeshopid}}" data-type="3"><span class="shenlan">不同意</span></a>
                            {{/if}}
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
<script src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/spikeShop.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		/* spike.bind(); */
		spikeshop.getChecklist(1);
	})
</script>