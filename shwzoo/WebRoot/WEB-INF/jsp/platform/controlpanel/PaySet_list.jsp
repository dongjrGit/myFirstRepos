<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/PaySetList.js"></script> 
 


<div class="mainright">

    <div class="clear"></div>
    <div class="dotted mar35"></div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="member-xz">
        <span><input class="but-comm" name="" id="addbtn" type="button" value="添加支付方式"/></span>
    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="trlist">
                <th>支付方式名称</th>
                <th>交易货币</th>
                <th>手续费</th>
                <th width="12%">是否支持在线支付</th>
                <th width="10%">接口类型</th>
                <th width="18%">详情</th>
                <th width="10%">操作</th>
            </tr>
            <script type="text/html" id="datalist">
                {{each list as art i}}
                <tr>
                    <td>{{art.payname}}</td>
                    <td>{{art.moneyname}}</td>
                    <td>{{art.poundage}}</td>
                    {{if art.isonline==true}}
                    <td>是</td>
                    {{else}}
                    <td>否</td>
                    {{/if}}
                    {{if art.interfacetype==0}}
                    <td>电脑版</td>
                    {{else}}
                    {{if art.interfacetype==1}}
                    <td>手机网页版</td>
                    {{else}}
                    {{if art.interfacetype==2}}
                    <td>手机App版</td>
                    {{/if}}
                    {{/if}}
                    {{/if}}
                    <td>{{art.discription}}</td>
                    <td class="zdgl-wzlbbj"><input type="hidden" value="{{art.id}}" /><span class="bjxx shenlan"><a>编辑</a></span> <span class="delete shenlan"><a>删除</a></span> <span class="lvs"></td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--table-con  stop -->




</div><!--mainright stop -->
