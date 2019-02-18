<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script src="/resource/public/platform/js/advert/advert_act.js"></script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">首页活动</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->

		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form">
			                <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>标题：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="title" name="title" type="text"
									value="${advert.title }" />
							</div>
						    </div>
						  
							<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>活动图片：</label>
							</div>
							<div class="tjcpxx-con-form">				
                                <div class="tjcpxx-con-form-upimg">
                                <c:choose>
								<c:when test="${advert.id>0 }">
								<img id="loadimg" width="120px" height="115px"
										src="${advert.imag}" />
								</c:when>
								<c:otherwise>
									<img id="loadimg" width="120px" height="115px"
										src="" />
								</c:otherwise>
								</c:choose>
                                </div>
                                <input type="hidden" name="img" id="img" value="${advert.imag }" />
                                <div style="width: 200px; float: left;position: relative;left: 30px;">
								<input type="button" value="选择图片" class="h_scimgbut"> 
								<input type="file" name="pics" id="singlefile"  class="filemhbut" style="top: 10px;">
							    <div>
								     <input type="button" value="本地上传" class="h_scimgbut h_scimgbut1">
							    </div>
								</div>
                             </div>
					   </div>
					   <br />
					   <div class="tjcpxx-con-mk" >
			                 <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span>跳转类型：</div>
			                 <div class="tjcpxx-con-form">
			                 <select name="type" id="type" onchange="Ad.TypeChange()" >
                                    <option value="0" id="defaultfc1" selected>无</option>
                                    <script id="flist2" type="text/html">
                                        {{each list as ad i}}
                                        <option value="{{ad.code}}">{{ad.name}}</option>
                                        {{/each}}
                                    </script>
			                   </select>
			                  </div>
						  </div>
					    <div id="divspu" class="tjcpxx-con-mk" style="display: none;">
                             <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span>商品名称：</div>
                             <div style="width:auto;display: table-cell;">
                             	<div ><input id="select_spu" type="text" class="inp-seller" value="${spuname }"  data="${advert.typeid }" /></div>
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
                         <div id="divshop" class="tjcpxx-con-mk"  style="display: none;">
                             <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span>店铺名称：</div>
                        	 <div style="width:auto;display: table-cell;">
                        	 	<div><input id="select_shop" type="text" class="inp-seller"  value="${shopname }" data="${advert.typeid }" /></div>
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
                        
                          <div id="divtopic" class="tjcpxx-con-mk"  style="display: none;">
                             <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span>专题名称：</div>
                        	 <div style="width:auto;display: table-cell;">
                        	 	<div><input id="select_topic" type="text" class="inp-seller" value="${topicname }"  data="${advert.typeid }" /></div>
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
                        
                        <div id="divScenic" class="tjcpxx-con-mk" style='position: relative; display: none'>
							<div class="tjcpxx-con-form-title">
								<span class="red marrig5">*</span>设施名称：
							</div>
							<div style="width: auto; display: table-cell;">
								<div>
									<input id="scenicid" type="text" class="inp-seller" value="${scenicname }" data="${advert.typeid }" />
								</div>
								<div  style="margin-left: 11px;">
									<ul>
										<script id="select_sceniclist" type="text/html">
                                        	{{each list as item i}}
                                        	<li data="{{item.id}}">{{item.scenicname}}</li>
                                        	{{/each}}
                                    </script>
									</ul>
								</div>
							</div>
						</div>
						
						
						<div id="divEditor" class="tjcpxx-con-mk" style='position: relative; display: none'>
							<div class="tjcpxx-con-form-title">
								<span class="red marrig5">*</span>图文标题：
							</div>
							<div style="width: auto; display: table-cell;">
								<div>
									<input id="editorId" type="text" class="inp-seller" value="${editorTitle }" data="${advert.typeid }" />
								</div>
								<div  style="margin-left: 11px;">
									<ul>
										<script id="select_editorlist" type="text/html">
                                        	{{each list as item i}}
                                        	<li data="{{item.id}}">{{item.title}}</li>
                                        	{{/each}}
                                    </script>
									</ul>
								</div>
							</div>
						</div>
                        
                        
                        <div id="divurl" class="tjcpxx-con-mk" style="display:none;">
							<div class="tjcpxx-con-form-title">
								<label>链接：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" id="url" name="url" type="text"
									value="${advert.url }" />
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>状态：</label>
							</div>
							<div class="tjcpxx-con-form">
								<c:choose>
									<c:when test="${advert.status==1}">
										<input type="radio" id="radio_status" name="radio_status"
											value="1" checked />启用 
                    			        <input type="radio" name="radio_status"
											id="radio_status" value="0" />禁用
                    		        </c:when>
									<c:otherwise>
										<input type="radio" id="radio_status" name="radio_status"
											value="1" />启用 
                    			        <input type="radio" name="radio_status"
											id="radio_status" checked value="0" />禁用
                    		        </c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
						    	<input type="hidden" name="topictype" id="topictype" value="${advert.type }">
								<input type="hidden" name="id" id="hidden_advertid" value="${advert.id }" />
								<input class="preserve-inp" name="Save" type="button" value="保存" /> 
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
<script type="text/javascript">
$(function(){
	 var type = $("#topictype").val();
     if (type == 0) {
         $("#divurl").show();
         $("#divspu").hide();
         $("#divshop").hide();
         $("#divtopic").hide();
         $("#divScenic").hide();
         $("#divEditor").hide();
     }else if(type==1){
     	 $("#divurl").hide();
          $("#divspu").hide();
          $("#divshop").show();
          $("#divtopic").hide();
          $("#divScenic").hide();
          $("#divEditor").hide();
     }else if(type==2){
     	$("#divurl").hide();
         $("#divspu").show();
         $("#divshop").hide();
         $("#divtopic").hide();
         $("#divScenic").hide();
         $("#divEditor").hide();
     }else if(type==3){
     	$("#divurl").hide();
         $("#divspu").hide();
         $("#divshop").hide();
         $("#divtopic").show();
         $("#divScenic").hide();
         $("#divEditor").hide();
     }else if(type==4){
    	 $("#divurl").hide();
         $("#divspu").hide();
         $("#divshop").hide();
         $("#divtopic").hide();
         $("#divScenic").show();
         $("#divEditor").hide();
     }else if(type==5){
    	 $("#divurl").hide();
         $("#divspu").hide();
         $("#divshop").hide();
         $("#divtopic").hide();
         $("#divScenic").hide();
         $("#divEditor").show();
     }
})
$(".h_scimgbut1").click(function () {
    $.ajaxFileUpload({
    	url : "/app/api/img/upload",
		secureuri : false,
		fileElementId : 'singlefile',
		dataType : "json",
		//ftype:上传文件类型（图片文件=1，其他文件=2）
		//relationtype: 模块（会员头像( 0), 店铺(1), 产品 ( 2), 其他 (20);） 可以自由增加
		data : {
			relationtype : 9
		},
        type: 'POST',
        success: function (result) {
            $("#img").val(result.data[0]); 
            if (result.code == 0){
            	Dalert("上传成功");
            	$("#loadimg").attr("src",result.data[0]);
            	$("#img").val(result.data[0]);
            }else
                $("#loadimg").attr("src", "");
            //TODO 结束正在加载中
        },
        error:function(e){
        	 //alert(JSON.stringify(e));
        }
    });
});
</script>