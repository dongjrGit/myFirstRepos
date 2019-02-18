<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt; 秒杀活动 &gt; 活动列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>
                <span>活动编号：<input type="text" id="num" class="text-inp" /></span>
                <span>
                    活动类型：
                    <select class="sel-form" id="type_select">
                        <option value="">全部</option>
                        <option value="0">秒杀</option>
                        <option value="1">闪购</option>
				<option value="4">团购</option>
                    </select>
                </span>
                <span>
                    开始时间：
                    <input type="text" id="starts" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', maxDate: '#F{$dp.$D(\'starte\')}' })" value="" readonly="readonly" />-
                    <input type="text" id="starte" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', minDate: '#F{$dp.$D(\'starts\')}' })" value="" readonly="readonly" />
                </span>
                <span>
                    结束时间：
                    <input type="text" id="ends" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', maxDate: '#F{$dp.$D(\'ende\')}' })" value="" readonly="readonly" />-
                    <input type="text" id="ende" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', minDate: '#F{$dp.$D(\'ends\')}' })" value="" readonly="readonly" />
                </span>
                <input class="but-comm" name="btnsearch" type="button" value="查询">
            </form>
        </div>

        <div class="clear"></div>
        <div class="thgl">
            <table class="data_list">
                <tr id="spike_title">
                    <th width="10%">编号</th>
                    <th width="20%">名称</th>
                    <th width="10%">类型</th>
                    <th width="12%">开始时间</th>
                    <th width="12%">结束时间</th>
                    <th width="10%">申请状态</th>
                    <th>操作</th>
                </tr>
                <tbody id="datalist">
                    <script id="spikelist" type="text/html">
                        {{each list as spike i}}
                        <tr>
                            <td>{{spike.spikenum}}</td>
                            <td>{{spike.spikename}}</td>
                            <td>
                                {{if spike.spiketype==0}}
                                                                  秒杀
                                {{else if spike.spiketype==1}}
                                                                  闪购
                                {{/if}}
                            </td>
                            <td>{{spike.starttimeStr}}</td>
                            <td>{{spike.endtimeStr}}</td>
                            <td>
                                {{if spike.spikeshopstatus==0}}
                                未申请
                                {{else if spike.spikeshopstatus==1}}
                                提交申请中
                                {{else if spike.spikeshopstatus==2}}
                                审核通过
                                {{else if spike.spikeshopstatus==3}}
                                审核不通过
                                {{/if}}
                            </td>
                            <td>
                                {{if spike.isout==1}}
                                    {{if spike.spikeshopstatus==0}}
                                <a href="javascript:void(0);" class="apply" data-id="{{spike.id}}"><span class="shenlan">申请活动</span></a>
                                    {{else}}
                                        {{if spike.spikeshopstatus==1}}
                                            <span>提交申请中</span>
                                        {{else if spike.spikeshopstatus==2}}
                                            <a href="yxgl_SpikeSpuList?id={{spike.id}}"><span class="shenlan">商品列表</span></a>
                                            <span style="margin-left:10px;"></span>
                                            <a href="javascript:void(0);" class="out" data-id="{{spike.spikeshopid}}"><span class="shenlan">退出</span></a>
                                        {{else if spike.spikeshopstatus==3}}
                                            <a href="javascript:void(0);" class="applyagain" data-id="{{spike.spikeshopid}}"><span class="shenlan">申请活动</span></a>
                                        {{/if}}
                                    {{/if}}
                                {{else}}
                                    {{if spike.spikeshopstatus==null}}
                                        <span class="huise">申请活动</span>
                                    {{else}}
                                        {{if spike.spikeshopstatus==0}}
                                            <span>提交申请中</span>
                                        {{else if spike.spikeshopstatus==1}}
                                            <a href="yxgl_SpikeSpuList?id={{spike.id}}"><span class="shenlan">商品列表</span></a>
                                            <span style="margin-left:10px;"></span>
                                        {{else if spike.spikeshopstatus==2}}
                                            <span class="huise">申请活动</span>
                                        {{/if}}
                                    {{/if}}
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
</div>
<script src="/resource/public/seller/js/yxgl/spikelist.js"></script>
<script type="text/javascript">
    $(document).ready(function () { spike.bind();spike.getlist(1); })
</script>