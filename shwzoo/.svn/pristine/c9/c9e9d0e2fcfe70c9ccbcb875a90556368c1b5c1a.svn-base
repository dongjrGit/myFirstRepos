<%@ page language="java"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/platform/js/giftcard/gidtcard.js"></script>
<script src="http://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        gift.getlist(1);
    })
    
    function input(){
    	$("#form").ajaxSubmit(function() { 
    		refresh();
    	});
    	  	
    	
    }
</script>
<div class="mainright">
    <div class="clear"></div>
    <div class="account-form">
        <span>礼品卡编号：<input type="text" id="cardcode" class="inp-seller" /></span>
        <span class="marrig10"></span><span>卡类型：<input type="text" id="cardtype" class="inp-seller" /></span>
        <span class="marrig10"></span><span>卡名：<input type="text" id="cardname" class="inp-seller" /></span>
        <span class="marrig10"></span><span>面值：<input id="cardface" type="text" class="inp-seller" /></span>
        <span class="marrig10"></span><span>批次号：<input id="cardpc" type="text" class="inp-seller" /></span>
        <span class="marrig10"></span><span> 是否使用：
        	<select id="checkss" name="checkss" class="the-form-select">
                <option value="">全部</option>
                <option value="0">否</option>
                <option value="1">是</option>
            </select>
        </span>
        <span class="marrig10"></span><span>充值时间：</span>
        <input type="text" id="starts" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'starte\')}' })" value="" readonly="readonly" />-
        <input type="text" id="starte" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'starts\')}' })" value="" readonly="readonly" />
        <span class="marrig10"></span>
        <input class="inquire" name="btnsearch" onclick="gift.getlist(1)" type="button" value="查询">
        <input class="inquire chaxun"onclick="gift.inputExcel()" name="btnadd" type="button" value="导入礼品卡">
    </div>

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="pack_title">
                <th width="12%">礼品卡名称</th>
                <th width="12%">礼品卡编号</th>
                <th width="12%">礼品卡卡密</th>
                <th width="12%">礼品卡描述</th>
                <th width="8%">礼品卡类型</th>
                <th width="8%">礼品卡面值</th>
                <th width="9%">批次号</th>
                <th>操作</th>
            </tr>
            <tbody id="datalist">
                <script id="packlist" type="text/html">
                    {{each list as card i}}
                    <tr>
                        <td>{{card.name}}</td>
                        <td>{{card.code}}</td>  
                        <td>{{card.password}}</td>
                        <td>{{card.remark}}</td>
                        <td>{{card.type}}</td>
                        <td>{{card.facevalue }} </td>
                        <td>{{card.batchnum}}</td>
						<td>
							<a href="javascript:void(0);" name="del"><span class="shenlan" onclick="gift.del({{card.id}})">删除</span></a>
							<a href="/platform/market/giftcard_edit?id={{card.id}}" name="del"><span class="shenlan" >修改</span></a>
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