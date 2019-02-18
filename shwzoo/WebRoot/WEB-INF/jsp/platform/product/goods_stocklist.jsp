<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resource/public/platform/js/product/spgl_stocklist.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <div class="martop8">
        	<div style="float: left; padding-right: 20px;">
			<h1>
				<span style="color:red">所属商品：${spu.name }</span>
			</h1>
			<input type="hidden" id="spuid" name="spuid" value="${spu.id}" />
			</div>
			
			<span>日期：
			  <select id="showy" name="showy" class="the-form-select-one" onchange="BindDate()" >
                    <option value="" selected>请选择</option>
                    <c:if test="${yearlist != null and yearlist.size() > 0 }">
						<c:forEach items="${yearlist }" var="year">
							<option value="${year}">${year}年</option>
						</c:forEach>
					</c:if>
				</select>
		        <select id="showm" name="showm" class="the-form-select-one" onchange="BindDate()" >
					<option value="" selected>请选择</option>
					<option value="1">1月</option>
					<option value="2">2月</option>
					<option value="3">3月</option>
					<option value="4">4月</option>
					<option value="5">5月</option>
					<option value="6">6月</option>
					<option value="7">7月</option>
					<option value="8">8月</option>
					<option value="9">9月</option>
					<option value="10">10月</option>
					<option value="11">11月</option>
					<option value="12">12月</option>
				</select>
		        <select id="showd" name="showd" class="the-form-select-one">
	                 <option value="" id="ruletr1" selected>请选择</option>
	                 
		        </select>
			</span>
			  <span class="marrig10"></span>
			<input class="chaxun" name="select_button" type="button" value="查询">
			
            <input class="inquire" type="button" id="delAll" value="批量删除">
           
             <a href="javascript:void(0);" target="_self"><input class="chaxun" id="addnew_button" type="button" value="+按天添加库存售价"></a>
             
             <a href="javascript:void(0);" target="_self"><input class="chaxun" id="addnew_buttonMonth" type="button" value="+按月添加库存售价"></a>
        </div>
    </div>

<div class="clear"></div>
<div class="mar35"></div>
<div class="table-con">
    <table class="data_list">
        <thead>
	        <tr id="type_title">
	            <th width="10%"><input type="checkbox" name="ch_All" id="ch_All" value="" /></th>
	            <th>日期</th>
	            <th>库存</th>
	            <th>售价(元)</th>
	            <th>操作</th>
	        </tr>
        </thead>
        <tbody id="datalist">
            <script id="typelist" type="text/html">
                {{each list as pro i}}
                <tr>
                    <td><input type="checkbox" name="ck_list" value="{{pro.id}}" /></td>
                    <td>{{pro.dateStr}}</td>
					<td>{{pro.stock }}</td>
					<td>{{pro.price }}</td>
                    <td>
						<a href="javascript:void(0);" class="edit" data_datestr="{{pro.dateStr}}"  data_stock="{{pro.stock}}" data_price="{{pro.price}}" data_id="{{pro.id}}" ><span class="shenlan">编辑</span></a>
                        <span class="marrig35"></span>
                        <a href="javascript:void(0);" data-id="{{pro.id}}" class="del"><span class="shenlan">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
        </tbody>
        <tr id="addnew_tr" style="display:none;">
                <td class="red">+</td>
                <td><input type="hidden" id="addnew_id" /><input type="text" id="addnew_datestr" disabled/></td>
                <td><input type="text" id="addnew_stock" /></td>
                <td><input type="text" id="addnew_price" /></td>
                <td>
                    <a href="javascript:void(0);" id="sumbit_save" name="sumbit_save"><span class="shenlan">保存</span></a>
                </td>
        </tr>
    </table>
    <!--  -->
</div>
<div class="clear"></div>
<div id="pager" class="page">

</div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        type.bind(1);
        
    	$("input[name='select_button']").first().bind("click", function() {
    		type.bind(1);
		});
        
        $("#addnew_button").click(function() {
        	timestock.add(); 
    	});
    	
    	$("#addnew_buttonMonth").click(function() {
        	timestock.addMonth(); 
    	})
    	
    })
    
    //日期数据绑定
	function BindDate() {
		$(".addr").remove();
		// 月份改变
		var month = $("#showm").val();
		var year=$("#showy").val();
		
		var daycount=30;
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
			daycount=31;
		}else if(year%4==0 && month==2){
			
			daycount=29;
		}else if(year%4!=0 && month==2){
			
			daycount=28;
		}
		for (var i=daycount;i>=1;i--)
		{
			var addhtml="<option value='"+i+"' class='addr'>"+i+"日</option>";
			$("#ruletr1").after(addhtml);
		} 
	}
</script>