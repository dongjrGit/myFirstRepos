<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/commonjs/jquery.form.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script src="/resource/public/seller/js/MyShop/advert_edit.js"></script>
<script type="text/javascript">
    function backhref() {
    	var sid=$("#annouid").val();
        window.location.href = "showAdvertImg?id="+sid;
    }
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
    <!--主要内容 左边导航结束 -->
    <div class="allcon">
        <div class="position">
            您所在的位置：我的店铺 &gt; 店铺管理 &gt; 广告添加
         </div>
		 <div class="zhgl-con">
            <div class="zhgl-con-top">
				<div class="zhgl-con-top-title">添加广告信息</div>
			</div>
		<!--l_wzmbtop   stop -->

			<div class="zhgl-con-con">
					<form id="form">
					<%-- <input type="hidden" name="id" value="${data.id }" /> <input
							type="hidden" id="groupid" name="groupid" value="${data.groupbyid }" /> --%>
							<input type="hidden" name="mark" id="mark" value="${advert.type }">
							<input type="hidden" name="position" id="position" value="${advert.position }">
							<input type="hidden" id="imgsrc" value="<%=path %>" />
					<table>
						 <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>页面标识：</label></td>
                            <td>
                                 <select class="sel-form" name="pagemark" id="pagemark" onchange="Ad.pagemarkChange()">
<!-- 			                                    <option value="0" id="defaultfc1" selected>无</option>
 -->									<option value="2">店铺页</option>
					              	<option value="30">店招</option>
					               </select>
                            </td>
                        </tr>
                        
                        
                        <tr>
                            <td class="xjdpzzh-left"><label>标题：</label></td>
                            <td>
                                  <input class="text-inp-big" id="title" name="title" type="text"
									value="${advert.title }">
                            </td>
                        </tr>
					
						<tr>
                            <td class="xjdpzzh-left"><label>广告图片：</label></td>
                            <td>
                            <div class="tjcpxx-con-form">
                                 <div class="tjcpxx-con-form-upimg">
                                <c:choose>
								<c:when test="${advert.id>0 }">
								<img id="loadimg" width="120px" height="115px"
										src="<%=path %>${advert.imgurl}" />
								</c:when>
								<c:otherwise>
									<img id="loadimg" width="120px" height="115px"
										src="" />
								</c:otherwise>
								</c:choose>
                                </div>
                                <input type="hidden" name="img" id="img" value="${advert.imgurl }" />
                                <div style="width: 200px; float: left;position: relative;left: 30px;">
								<input type="file" name="pics" id="singlefile"  class="filemhbut" style="top: 10px;">
							    <div>
								     <input type="button" value="本地上传" class="tjcpxx-con-form-upthis h_scimgbut1">
								     
							    </div>
								</div>
								</div>
                            </td>
                        </tr>
						
						
						<tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>跳转类型：</label></td>
                            <td>
                                  <input type="hidden" value="" id="fid" />
                                    <select name="type" id="type" onchange="Ad.TypeChange()" class="sel-form">
			                                   <!--  <option value="0" id="defaultfc1" selected>无</option> -->
											<option value="0">外部链接</option>
											<option value="2">商品</option>
			                   </select>
                            </td>
                        </tr>
                        
                        <tr  id="divspu" style='display:none'>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>商品名称：</label></td>
                            <td>
									<div ><input id="select_spu" type="text" class="text-inp-big" /></div>
	                        	 <div class="tjcpxx-con-form" style='left:0;'>
	                        		<ul>
	                                    <script id="select_spulist" type="text/html">
                                        {{each list as sp i}}
                                        <li data="{{sp.id}}">{{sp.name}}</li>
                                        {{/each}}
                                    </script>
	                                </ul>
	                        	</div>
                            </td>
                        </tr>
                        
                        <tr   id="divurl">
                            <td class="xjdpzzh-left"><label>连接：</label></td>
                            <td>
								<input class="text-inp-big" id="url" name="url" type="text"
									value="${advert.url }">
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="xjdpzzh-left"><label><span class="red marrig5">*</span>站点标识：</label></td>
                            <td>
								<select name="webset" id="webset" class="sel-form">			                  																		
			                       <option value="1" >pc</option>
			                       <option value="2" >app</option>  
			                       <option value="3" >wap</option>  
			                       <option value="4" >wechat</option>                    		                   
			                  </select>  
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="xjdpzzh-left">状态：</td>
                            <td>
								<c:choose>
									<c:when test="${advert.status==1}">
                    			<input type="radio" id="radio_status" name="radio_status" value="1"  checked/>启用 
                    			<input type="radio"
									name="radio_status"  id="radio_status"  value="0"  />禁用
                    		</c:when>
                    		<c:otherwise>
                    			<input type="radio" id="radio_status" name="radio_status" value="0" checked="ischecked"/>启用 
                    			<input type="radio"
									name="radio_status"  id="radio_status"  value="0"  />禁用
                    		</c:otherwise>
								</c:choose>
                            </td>
                        </tr>
					
						<tr>
							<td class="xjdpzzh-left"></td>
							<td>
								<input type="hidden" id="hidden_advertid" value="${advert.id }" />
								<input class="big-but" name="Save" type="button" value="保存" /> 
									 <input id="action" type="hidden" value="advertAdd" />
									<input class="big-but-huise" name="" type="button" value="取消"
									onclick="backhref()">
								</td>
						</tr>
					</table>
					</form>
				</div>
			</div>
			<!--tjcpxx-con stop -->
		</div>
		<!--tjcpxx stop -->
	</div>
</div>
<!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {  	  
        $.ajax({
            url: "/platform/commodity/GetClassByFatherID",
            type: "post",
            data: { 'fatherid': 0 },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('flist', listdata);
                    $("#defaultfc").after(html);
                }
            },
            error: function () {

            }
        });
    	Ad.unit();
    	/*var id = $("#id").val();*/
        $(".h_scimgbut1").click(function () {
            $.ajaxFileUpload({
            	url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//relationtype: 模块（广告( 9),其他 (20);） 可以自由增加
				data : {
					relationtype : 9
				},
                type: 'POST',
                success: function (result) {
                    //  alert(JSON.stringify(result));
                    //$(".url1").html(JSON.stringify(result));
                    $("#img").val(result.data[0]); 
                    if (result.code == 0){
                    	Dalert("上传成功");                    	
                    	$("#loadimg").attr("src",$("#imgsrc").val()+result.data[0]);
                    }
                    else
                        $("#loadimg").attr("src", "");
                    //TODO 结束正在加载中
                },
                error:function(e){
                	 alert(JSON.stringify(e));
                }
            });
        });
        if ($("#sort").val() == "" || $("#sort").val() == undefined) {
            $("#sort").val(1);
        }
    })
</script>