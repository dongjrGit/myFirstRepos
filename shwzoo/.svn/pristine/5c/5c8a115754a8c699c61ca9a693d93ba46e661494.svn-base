<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="/resource/public/seller/js/spgl/spgl_spuedit.js"></script>
<script src="/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
<script src="/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">

	var editor;
	KindEditor.ready(function(K) {
		//K.create('#content1');
		//取消功能 打印，插入模板，插入代码，插入flash，插入视频，插入表情，锚点
		editor = K.create('#content1', {
			items : [ 'source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
					'paste', 'plainpaste', 'wordpaste', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', 'fullscreen', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', 'image', 'multiimage', 'insertfile',
					'table', 'hr', 'baidumap', 'pagebreak', 'link', 'unlink' ],
					uploadJson :"/zoo/image/upload?relationtype=1&iskdr=1"
		});
	});
</script>
<style>
    .tjcpxx-con-form-upimg {
        display: table-cell;
        width: 120px;
        height: 115px;
        background: #f4f4f4;
        border: 1px solid #D9D9D9;
        padding: 1px;
        text-align: center;
        line-height: 115px;
        float: left;
        margin: 0px 0px 10px 0px;
    }

    .tjcpxx-con-form-upthis {
        width: 105px;
        height: 35px;
        float: left;
        background: #FFE5E6;
        border: 1px solid #D9D9D9;
        margin: 30px auto auto 5px;
        text-align: center;
        line-height: 35px;
    }
</style>
<div id="container">
	<div class="allcon">
		<div class="position">您所在的位置：商品管理 &gt; 标准商品管理 &gt; 商品信息添加</div>
		<!--所在位置信息  结束 -->
		<div class="zhgl-con">
			<div class="zhgl-con-con">
				<form id="form">
					<div class="zhgl-con-top">
						<div class="zhgl-con-top-title">商品信息添加</div>
					</div>
					<table>
						<tr>
							<td class="xjdpzzh-left"><label><span
									class="red marrig5">*</span>商品名称：</label></td>
							<td><input class="text-inp-big" name="name" type="text">
								<span class="huise"> 商品名称不能为空，长度限制在100个字符以内 </span>
								<input type="hidden" value="0" id="select_id" name="id" />
								</td>
						</tr>						
						<!--xjdpzzh-left stop -->
                         <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>商品图片：</label></td>
                            <td>
                                <input type="hidden" id="imgsrc" value="<%=path %>" />
                                <div class="tjcpxx-con-form">
                                    <div class="tjcpxx-con-form-upimg"><img id="loadimg" width="120px" height="115px" src="" /></div>
                                    <div style=" width:200px;margin-left:10px; float:left;">
                                        <input type="file" id="singlefile" name="pics" />
                                        <input type="hidden" name="imgurlApp" value="" />
                                        <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                       <!--xjdpzzh-left stop -->
						<tr>
							<td class="xjdpzzh-left"><label><span
									class="red marrig5">*</span>商品原价：</label></td>
							<td><input class="text-inp-big" name="price" onkeyup="clearNoNum(this)" type="text">
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left"><label><span
									class="red marrig5">*</span>商品ID：</label></td>
							<td><input class="text-inp-big" name="tnum" type="text">
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left"><label><span
									class="red marrig5">*</span>商品售价：</label></td>
							<td><input class="text-inp-big" name="tprice" onkeyup="clearNoNum(this)" type="text">
							</td>
						</tr>
						<c:choose>
						<c:when test="${stype==0 }">
						<tr>
							<td class="xjdpzzh-left"><label></label></td>
							<td><input id="protype" name="protype" type="checkbox" /> <span>年卡</span> 
							<input type="hidden" name="isnk" value="0" />
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left"><label><span
									class="red marrig5">*</span>商品ID(当天票)：</label></td>
							<td><input class="text-inp-big" name="tdnum" type="text">
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left"><label><span
									class="red marrig5">*</span>商品售价(当天票)：</label></td>
							<td><input class="text-inp-big" name="tdprice" onkeyup="clearNoNum(this)" type="text">
							</td>
						</tr>
						</c:when>
						<c:when test="${stype==1 }">
						<tr>
							<td class="xjdpzzh-left"><label><span class="red marrig5">*</span>表演时间：</label></td>
							<td><select name="showy" id="showy" class="sel_allmost" >
                                        <option value="2017">2017年</option>
										<option value="2018">2018年</option>
										<option value="2019">2019年</option>
										<option value="2020">2020年</option>
										<option value="2021">2021年</option>
										<option value="2022">2022年</option>
										<option value="2023">2023年</option>
										<option value="2024">2024年</option>
										<option value="2025">2025年</option>
										<option value="2026">2026年</option>
										<option value="2027">2027年</option>
										<option value="2028">2028年</option>
										<option value="2029">2029年</option>
										<option value="2030">2030年</option>
                                 </select>
                                 <select name="showm" id="showm" class="sel_allmost" >
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
							<input class="text-inp-big" id="showdays" name="showdays" type="text">
							<span class="huise">日期之间用英文逗号分隔，例1,2,3 </span>
							</td>
						</tr>
						</c:when>
						</c:choose>
						<tr>
							<td class="xjdpzzh-left"><label>商品短标题：</label></td>
							<td><input class="text-inp-big" name="titleShort"
								type="text"></td>
						</tr>
						<!--xjdpzzh-left stop -->
						<tr>
							<td class="xjdpzzh-left"><label>商品标签：</label></td>
							<td><input class="text-inp-big" name="tag" type="text">
							</td>
						</tr>
                        <!--xjdpzzh-left stop -->
						<tr>
							<td class="xjdpzzh-left"><label>商品详情：</label></td>
							<td><textarea id="content1" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="text-inp-big" name="description" type="hidden">
							</td>
						</tr>
						
						<tr>
							<td class="xjdpzzh-left"><label></label></td>
							<td><input id="iszk" name="iszk" type="checkbox" /> <span>折扣</span> 
							<input type="hidden" name="isoffer" value="0" />
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left"></td>
							<td>
							<input type="hidden" id="skuparam" name="skuparam" />
							<input type="hidden" value="${stype }" id="stype" />
							<input class="big-but" name="commit" type="button" value="保存" onclick="formSubmit()"> 
							<input class="big-but-huise" name="" type="button" value="返回"
								onclick="backhref()"></td>
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
        $(".tjcpxx-con-form-upthis").click(function () {
            $.ajaxFileUpload({
            	url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//relationtype: 产品 ( 2) 可以自由增加
				data : {
					"relationtype" : 2,
					//"type" : 0
				},
                type: 'POST',
                success: function (result) {
                    $("input[name='imgurlApp']").val(result.data); 
                    if (result.code == 0){
                    	Dalert("上传成功");
                        $("#loadimg").attr("src",result.data[0]);
                    }
                    else
                    {
                   	 $("#loadimg").attr("src", "");
                   	 Dalert("上传图片失败");
                   }
                    //TODO 结束正在加载中
                },
                error:function(e){
                	 alert(JSON.stringify(e));
                }
            });
        });
	})
	
function clearNoNum(obj){  
  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符   
  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的   
  obj.value = obj.value.replace(/^\./g, ""); 
  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
  obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数   
  if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额  
   obj.value= parseFloat(obj.value);  
  }  
}  
</script>
