
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="/resource/public/platform/js/member/feedBack.js"></script>

<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
   <span>反馈人：<input class="inp-seller" id="select_buyername" type="text"></span>
   <span class="marrig10"></span>
   <span>
       选择时间：
       <input type="text" id="select_begin" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })" class="text_time" /> 至
       <input type="text" id="select_end" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })" class="text_time" />
   </span>
   <span class="marrig10"></span>
        <input class="chaxun" name="select_button" type="button" value="查询" onclick="GoodConsultList.bind(1)">
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="goodconsultlist_title">
                <th>反馈人</th>
                <th>手机号</th>
                <th>反馈类型</th>
                <th>反馈内容</th>
                <th>反馈时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <script id="goodconsultlist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td>{{pro.username}}</td>
					<td>{{pro.phone}}</td>
					{{if pro.type==1 }}
                    <td>产品建议</td>
                    {{else if pro.type==2 }}
                    <td>程序错误</td>
                    {{else if pro.type==3}}
                    <td>其他</td>
					{{else}}
                    <td>---</td>
                    {{/if}}
                    <td>
                        <div title="{{pro.content}}">
                            {{if pro.content.length>15}}
                         {{pro.content.substring(0,15)}}...
                            {{else}}
                         {{pro.content}}
                            {{/if}}
                        </div>
                    </td>
					<td>{{pro.createtimetr}}</td>
                    {{if pro.status==1 }}
                    <td>已读</td>
                    {{else if pro.status==0 }}
                    <td>未读</td> 
                    {{/if}}
                   <td>
                        <input type="hidden" id="hidden_feedBackid" value="{{pro.id}}" />
						<input type="hidden" id="hidden_feedBackstatus" value="{{pro.status}}" />
                        <a href="javascript:void(0);" class="a_feedBackdetail"><span class="bjxx shenlan">明细</span></a>
                        <a href="javascript:void(0);" class="a_feedBackdelete"><span class="delete shenlan">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>


        </table>

    </div><!--table-con  stop -->

    <div class="clear"></div>
    <div id="pager" class="page">
    </div><!--page stop -->


</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        //列表以及分页数据绑定
        GoodConsultList.bind(1);
    })
</script>
