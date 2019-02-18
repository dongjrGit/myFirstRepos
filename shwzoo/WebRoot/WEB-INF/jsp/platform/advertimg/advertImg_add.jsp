<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/commonjs/jquery.form.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script src="/resource/public/platform/js/advert/advert_edit.js"></script>
<script type="text/javascript">
    function backhref() {
    	var sid=$("#annouid").val();
        window.location.href = "showAdvertImg?id="+sid;
    }
</script>

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">添加广告信息</a><span
					class="sj-img"></span></li>

			</ul>
		</div>
		<!--l_wzmbtop   stop -->

		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form">
						<%-- <input type="hidden" name="id" value="${data.id }" /> <input
							type="hidden" id="groupid" name="groupid" value="${data.groupbyid }" /> --%>
							<input type="hidden" name="mark" id="mark" value="${advert.type }">
							<input type="hidden" name="position" id="position" value="${advert.position }">
							 <div class="tjcpxx-con-mk">
			                 <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span>页面标识：</div>
			                 <div class="tjcpxx-con-form">
			                 <select name="pagemark" id="pagemark" onchange="Ad.pagemarkChange()">
			                        <script id="flist1" type="text/html">
                                        {{each list as ad i}}
                                        <option value="{{ad.code}}">{{ad.name}}</option>
                                        {{/each}}
                                    </script>
			                   </select>
			                  </div>
						  </div>
			              
			               <div id="divsp" style="display:none">
                            <div class="tjcpxx-con-mk1">
                                <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品分类：</label></div>
                                <div class="tjcpxx-con-form1">
                                    <input type="hidden" value="" id="fid" />
                                    <select name="firstID" id="firstID" >
                                        <option value="0" id="defaultfc" selected>无</option>
                                        <script id="flist" type="text/html">
                                            {{each list as fclass i}}
                                            <option value="{{fclass.id}}">{{fclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>
                                </div>
                            </div>  
			                 </div>  
			                  
			                <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>标题：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="title" name="title" type="text"
									value="${advert.title }">
							</div>
						    </div>
						  
							<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>广告图片：</label>
							</div>
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
								<input type="button" value="选择图片" class="h_scimgbut"> 
								<input type="file" name="pics" id="singlefile"  class="filemhbut" style="top: 10px;">
							    <div>
								     <input type="button" value="本地上传" class="h_scimgbut h_scimgbut1">
								     
							    </div>
								</div>
                                     <!-- <div style=" width:200px; float:right;">
                                        <input type="file" name="pics" id="singlefile" />
                                        <a href="javascript:void(0);" style="color:#000"><div class="tjcpxx-con-form-upthis">本地上传</div></a> 
                                    </div> -->
                             </div>
					   </div>
					   	<br/>
					   	
					   	 <div class="tjcpxx-con-mk">
			                 <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span>跳转类型：</div>
			                 <div class="tjcpxx-con-form">
			                 <select name="type" id="type" onchange="Ad.TypeChange()">
			                      <option value="" >请选择</option>
			                      <!-- <option value="0" id="defaultfc1" >添加链接</option> -->
			                      	<script id="flist2" type="text/html">
                                        {{each list as ad i}}
                                        <option value="{{ad.code}}">{{ad.name}}</option>
                                        {{/each}}
                                    </script>
			                   </select>
			                  </div>
						  </div>
						  
						   <div id="divspu" class="tjcpxx-con-mk" style='position:relative;display:none'>
                             <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span>商品名称：</div>
                             <div style="width:auto;display: table-cell;">
                             	<div ><input id="select_spu" type="text" class="inp-seller" /></div>
	                        	 <div class="tjcpxx-con-form" style='left:0;'>
	                        		<ul>
	                                    <script id="select_spulist" type="text/html">
                                        {{each list as sp i}}
                                        <li data="{{sp.id}}">{{sp.name}}</li>
                                        {{/each}}
                                    </script>
	                                </ul>
	                        	</div>
                             </div>
                        	
                        </div>
                        
                          <div id="divshop" class="tjcpxx-con-mk" style='position:relative;display:none'>
                             <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span>店铺名称：</div>
                        	 <div style="width:auto;display: table-cell;">
                        	 	<div><input id="select_shop" type="text" class="inp-seller" /></div>
                        	 	<div class="tjcpxx-con-form">
                        		<ul>
                                    <script id="select_shoplist" type="text/html">
                                        {{each list as shop i}}
                    						<li data="{{shop.id}}">{{shop.name}}</li>
                   						{{/each}}
                                    </script>
                                </ul>
                        	</div>
                        	 </div>
                        	 
                        </div>
                        
                          <div id="divtopic" class="tjcpxx-con-mk" style='position:relative;display:none'>
                             <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span>专题名称：</div>
                        	 <div style="width:auto;display: table-cell;">
                        	 	<div><input id="select_topic" type="text" class="inp-seller" /></div>
                        	 	<div class="tjcpxx-con-form">
                        		<ul>
                                    <script id="select_topiclist" type="text/html">
                                        {{each list as topic i}}
                                        <li data="{{topic.id}}">{{topic.title}}</li>
                                        {{/each}}
                                    </script>
                                </ul>
                        		</div>
                        	 </div>
                        </div>
                        
					   
                        <div id="divurl" class="tjcpxx-con-mk" style="display:none">
							<div class="tjcpxx-con-form-title">
								<label>链接：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="url" name="url" type="text"
									value="${advert.url }">
							</div>
						</div>
						
						  <div class="tjcpxx-con-mk" style="display: none;">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>显示位置：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="display" name="display" type="hidden"
									value="1">
							</div>
						  </div>
						  <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5"></span>图片排序：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="orderby" name="orderby" type="text"
									value="">
							</div>
						  </div>
						  <div class="tjcpxx-con-mk" style="display: none;">
			                 <div class="tjcpxx-con-form-title">站点标识：</div>
			                 <div class="tjcpxx-con-form">			                 
			                 <select name="webset" id="webset" disabled="true">			                  																		
			                      <!--  <option value="1" >pc</option> -->
			                       <option value="2" selected="selected" >app</option>  
			                  </select>            
			                  </div>
						    </div> 
						<!--tjcpxx-con-mk stop -->
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>状态：</label>
							</div>
							<div class="tjcpxx-con-form">
									<c:choose>
									<c:when test="${advert.status==1}">
                    			<input type="radio" id="radio_status" name="radio_status" value="1"  checked/>启用 
                    			<input type="radio"
									name="radio_status"  id="radio_status"  value="0"  />禁用
                    		</c:when>
                    		<c:otherwise>
                    			<input type="radio" id="radio_status" name="radio_status" value="1" checked="ischecked"/>启用 
                    			<input type="radio"
									name="radio_status"  id="radio_status"  value="0"  />禁用
                    		</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input type="hidden" id="hidden_advertid" value="${advert.id }" />
								<input class="preserve-inp" name="Save" type="button" value="保存" /> 
									 <input id="action" type="hidden" value="advertAdd" />
									<input class="preserve-inp_hs" name="" type="button" value="取消"
									onclick="backhref()">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->
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
                /* success: function (result) {
                     alert(JSON.stringify(result+"ddddddddddddd"));
                    //$(".url1").html(JSON.stringify(result));
                    $("#img").val(result.data[0]); 
                    if (result.code == 0)
                    	$("#loadimg").attr("src",$("#imgsrc").val()+result.data[0]);
                    else
                        $("#loadimg").attr("src", "");
                    //TODO 结束正在加载中
                },
                error:function(e){
                	 alert(JSON.stringify(e)+"d----------------------------");
                } */
                success: function (result) {        //后台ajax返回的数据 此处Imgurl
                	$("#img").val(result.data[0]); 
                    if (result.code == 0){
                    	Dalert("上传成功");
                    	$("#loadimg").attr("src",result.data[0]);
                    }else{
                        $("#loadimg").attr("src", "");
                    }
                    //TODO 结束正在加载中
                  } ,error:function(e){  
                       alert('上传失败！');  
                 }  
            });
        });
        if ($("#sort").val() == "" || $("#sort").val() == undefined) {
            $("#sort").val(1);
        }
    })
</script>