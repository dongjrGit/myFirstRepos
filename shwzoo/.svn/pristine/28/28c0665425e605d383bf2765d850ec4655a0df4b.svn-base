<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="/resource/public/seller/js/spgl/spgl_stockadd.js"></script>
<style type="text/css">
#ruletable{border-collapse :collapse ;}
#ruletable tr, #ruletable td,#ruletable th { border :1px solid #ccc;font-size:12px; text-align :center;}
#ruletable th{padding: 6px 0px;}
.xztjsp-xjgg{height:20px;line-height:20px;float: none;position: static;top: 0;left: 0;}
.xztjsp-inpdivnodw{position: relative;}
.xztjspmk.xztjsp-gg .xztjsp-inpdivnodw{margin-left: 115px;position: relative;border: 1px solid #DDDDDD;padding: 15px;}
.xztjsp-xjgg span{ display:inline-block;width: 20px;height: 20px;text-align: center;background: #409DEB;font-size: 20px;}
.xztjsp-inpdivnodw h3{font-weight: bold;margin: 10px 0px 0px 0px;color: #434343;}
.xztjsp-inpdivnodw table{ width: 150%;border-collapse: collapse;margin: 10px 0px 0px 0px;}
.xztjsp-inpdivnodw table td, .xztjspmk.xztjsp-gg .xztjsp-inpdivnodw table th{border: 1px solid #DDDDDD;text-align: left;padding:5px 5px 5px 15px;font-size: 14px;}
.xztjsp-inpdivnodw table th{color: #656565; font-weight: bold;line-height: 100%;}
.xztjsp-inpdivnodw table td input[type="text"]{border: 1px solid #DDDDDD;background: #fff;height: 23px;color: #43A5F9;padding: 0 5px;width: 90%;}
.xzspsx{width: 50%;position: relative;margin-top: 10px;}
.xzspsx span{display: inline-block;vertical-align: middle;line-height: 25px;min-width: 113px;text-align: right;}
.xzspsx input{ height: 25px;line-height: 25px;border: 1px solid #DDDDDD; background: #fff;padding: 0 5px;width: 100%;box-sizing:border-box;-moz-box-sizing:border-box; -webkit-box-sizing:border-box;-ms-box-sizing:border-box;-o-box-sizing:border-box;}
.xzspsx div{position: absolute;top: 0;left: 115px;right: 0px;}
.xzspsx select{height:25px;line-height:25px;border:1px solid #DDD;background:#fff;padding:0 5px;width:100%;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;-ms-box-sizing:border-box;-o-box-sizing:border-box;}
.xzthsp-radio{ vertical-align: middle;margin-top: 12.5px;width: 15px;height: 15px;}
.xztjsp-inpdivnodw.nobor{margin-left: 115px;position: relative;border: none;padding:0;}
.xztjsp-inpdivnodw.nobor img{}
.xztjspmk.mt20{ margin-top: 20px;}
.xztjspmk.mt20 input:nth-child(2){ margin-left: 8px;}
.xztjsp-inpdivnodw table td input[type="checkbox"]{ vertical-align: middle;}
.xztjsp-inpdivnodw table td select{border: 1px solid #DDDDDD;background: #fff;height: 25px;padding: 0 5px;width: 90%;}
.xztjsp-sel:nth-child(5){ margin: 0 5%;}
.h_scimg{ margin: 0;}
.h_scimg input[type="file"]{opacity: 0;}
.xztjsp-xjgg{ padding-right: 0;}
.xztjsp-inpdivnodw table{margin-top: 0;width: 80%;}
.xztjsp-xjgg span{ margin-right: 0;}
.xztjsp-ulist li{position: relative;}
.xztjsp-ulist li i{ display: block;font-style: normal;position: absolute;top: 1px;right: 0px;background: #efefef;width: 17px;height: 17px;line-height: 17px;text-align: center;border: 1px solid #DDDDDD;cursor: pointer;}
</style>
<div id="container">
	<div class="allcon">
		<div class="position">您所在的位置：商品管理 &gt; 标准商品管理 &gt; 商品库存添加</div>
		<!--所在位置信息  结束 -->
		<div class="zhgl-con">
			<div class="zhgl-con-con">
				<form id="form">
					<!-- <div class="zhgl-con-top">
						<div class="zhgl-con-top-title">商品库存添加</div>
					</div> -->
					<table>
						<tr>
							<td class="xjdpzzh-left"><label><span class="red marrig5">*</span>日期：</label></td>
							<td><select style="width:150px;margin-right:8px;" name="showy" id="showy" class="sel_allmost" onchange="BindDate()" >
                                       <c:if test="${yearlist != null and yearlist.size() > 0 }" >
											<c:forEach items="${yearlist }" var="year">
												<option value="${year}">${year}年</option>
											</c:forEach>
										</c:if>
                                 </select>
                                 <select style="width:150px;margin-right:8px;"   name="showm" id="showm" class="sel_allmost" onchange="BindDate()">
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
							</td>
						</tr>
						
						<tr>
							<td class="xjdpzzh-left"><label><span class="red marrig5"></span>库存价格：</label></td>
							<td>
							<div class="xztjsp-inpdivnodw fix">
								<table border="" cellspacing="" cellpadding="" id="ruletable">
									<tbody><tr id="ruletr1">
									<th>商品库存<span class="red"></span></th>
									<th>商品售价<span class="red"></span></th>
									</tr>
									<tr class='addr'>
									<td><input type='text' name='stock' id='' value='' />
									</td><td><input type='text' name='money' id='' value='' /></td>
									</tr>
								</tbody></table>
							</div>
							</td>
						</tr>
						
						<tr>
							<td class="xjdpzzh-left"></td>
							<td>
							<input type="hidden" id="spuid" name="spuid"  value="${spu.id}"/>
							<input type="hidden" id="skuid" name="skuid" value="${sku.id}"/>
							
							<input class="big-but" name="commit" type="button" value="保存" onclick="formSubmit('addTimeStock')"> 
							<input class="big-but-huise" name="" type="button" value="返回"
								onclick="refresh()"></td>
						</tr>
					</table>

				</form>
			</div>
			<!--tjcpxx-con stop -->
		</div>
		<!--tjcpxx stop -->
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	
	var year = "${year}";
	var month = "${month}";
	$("#showy").val(year);
	$("#showm").val(month);
	
})

function formSubmit() {
	// 防止重复提交 点击保存后隐藏按钮
	$("input[name='commit']").hide();
	
	$.ajax({
		url : "/seller/shopproduct/addTimeStockByMonth",
		data : $("#form").serialize(),
		type : "Post",
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				Dalert("保存成功！", "3000", refresh);
			} else {
				$("input[name='commit']").show();
				Dalert(data.desc,10000);
			}
		},
		error : function(e) {

		}
	});
}


function refresh() {
    parent.location.reload();
}

function Xclose() {
    parent.window.closeDialog();
}

</script>
