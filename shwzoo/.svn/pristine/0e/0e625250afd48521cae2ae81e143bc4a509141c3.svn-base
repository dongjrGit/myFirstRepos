<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/decorators/getFileUrl.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=v66Wo0IkRp2uWVi5RNsaj656"></script>
 <script type="text/javascript" src="${ctx }/resource/ajaxfileupload.js"></script> 
<script type="text/javascript" src="${ctx }/resource/public/platform/js/news/news_class.js"></script>
<script src="${ctx}/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
<script src="${ctx}/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/news/mapimgedit.js"></script>
<script type="text/javascript">
var editor;
/*var basepath=getRootPath();  */
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
			uploadJson :"/app/api/img/upload?relationtype=30&iskdr=1"
	});
});
</script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">添加图片地图新闻</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<div class="tjcpxx-con-con">
					<form id="form" method="post">
					<div class="tjcpxx-con-mk1">
						<div class="tjcpxx-con-form-title">
							<label><span class="red marrig5">*</span>标题：</label>
						</div>
						<div class="tjcpxx-con-form1">
							<input class="tjcpxx-fprm-inp" name="name" type="text" value="${vo.title }">
						</div>
					</div>
					<div class="tjcpxx-con-mk1">
						<div class="tjcpxx-con-form-title"><label>副标题：</label></div>
						<div class="tjcpxx-con-form1">
							<input class="tjcpxx-fprm-inp" name="subtitle" type="text" value="${vo.subtitle }">
						</div>
					</div>
					<div class="tjcpxx-con-mk1">
						<div class="tjcpxx-con-form-title"><label>摘要：</label></div>
						<div class="tjcpxx-con-form1">
							<textarea rows="4" cols="43" name="tabloid">${vo.tabloid }</textarea>
						</div>
					</div>
					</br>
					
					<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>新闻分类：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="ClassID" name="classid" type="hidden" value="0" /> <input
									type="hidden" value="${fid}" id="fid" /> <input type="hidden"
									value="${sid}" id="sid" /> <input type="hidden" value="${tid}"
									id="tid" /> <input type="hidden" value="${vo.id }" id="id"
									name="id" /> <select name="firstID" id="firstID" class="the-form-select-one"
									style="width: 92px" onchange="Class.firstChange()">

									<option value="0" id="defaultfc">无</option>
									<c:forEach var="s" items="${fslist}">
										<c:choose>
											<c:when test="${s.id == fid}">
												<option value="${s.id}" selected="selected">${s.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${s.id}">${s.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<script id="flist" type="text/html">
                                            {{each list as fclass i}}
                                            <option value="{{fclass.id}}">{{fclass.name}}</option>
                                            {{/each}}
                                        </script>
								</select>--> <select name="secondID" id="secondID" style="width: 92px"
									onchange="Class.secondChange()" class="the-form-select-one">
									<option value="0" id="defaultsc">无</option>
									<c:forEach var="s" items="${sslist}">
										<c:choose>
											<c:when test="${s.id == sid}">
												<option value="${s.id}" selected="selected">${s.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${s.id}">${s.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>

								</select>--> <select name="thirdID" id="thirdID" style="width: 92px" class="the-form-select-one">
									<option value="0" id="defaulttc">无</option>
									<c:forEach var="s" items="${tslist}">
										<c:choose>
											<c:when test="${s.id==tid}">
												<option value="${s.id}" selected="selected">${s.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${s.id}">${s.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>

								</select>
							</div>
						</div>
					<div class="tjcpxx-con-mk1">
						<div class="tjcpxx-con-form-title"><label>链接地址：</label></div>
						<div class="tjcpxx-con-form1">
							<input class="tjcpxx-fprm-inp" name="url" type="text" value="${vo.url }">
						</div>
					</div>
		                <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label>图片：</label></div>
                            <div class="tjcpxx-con-form1">
                                <div class="tjcpxx-con-form-upimg">
                                    <img id="loadimg" width="120px" height="115px" src="<%=path %>${vo.imgurl}" />
                                </div>
                                <input type="hidden" name="img" value="${vo.imgurl }" />
                                    <div
							style="width: 200px; float: left; position: relative; padding-left: 30px;">
							<input type="button" value="选择图片" class="h_scimgbut" /> <input
								type="file" id="singlefile" name="pics" 
								class="filemhbut" 
								style="top: 10px; left: 27px;" />
							<div>
								<input type="button" value="本地上传" class="h_scimgbut h_scimgbut1" />
							</div>
						</div>
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"></div>
                            <div class="tjcpxx-con-form1" style="position:relative;">
                                <label for="selectimg" class="error"></label>
                                <span class="beizhu-mc-upimg">上传图片要小于500kb</span>
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>内容：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content1" name=""
									style="width: 450px; height: 200px; visibility: hidden;">${vo.content }</textarea>
								<input class="tjcpxx-fprm-inp" name="content" type="hidden">
							</div>
						</div>
						</br>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title"><label>标签：</label></div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="tag" type="text" value="${vo.tag }">
								<span class="huise">请用“，”隔开！</span>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title"><label>关键字：</label></div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="keyword" type="text" value="${vo.keyword }">
								<span class="huise">请用“，”隔开！</span>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title"><label>作者：</label></div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="author" type="text" value="${vo.author }">
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>所在地：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="RegionID" name="regionid" type="hidden" value="0" /> <input
									type="hidden" value="${vo.province}" id="province" /> <input type="hidden"
									value="${vo.city}" id="city" /> <input type="hidden" value="${vo.area}"
									id="area" />
									<select id="select_province" name="select_province" style="width: 92px"
										class="the-form-select-one">
										<option value="-1">请选择</option>
										<script id="proviceselect" type="text/html">
               								{{each list as pro index}}
                							<option value="{{pro.code}}">{{pro.name}}</option>
                							{{/each}}
                						</script>
									</select> --><select id="select_city" name="select_city" style="width: 92px" class="the-form-select-one">
										<option value="-1">请选择</option>
										<script id="cityselect" type="text/html">
                							{{each list as pro index}}
                							<option value="{{pro.code}}">{{pro.name}}</option>
                							{{/each}}
                						</script>
									</select> --><select id="select_area" name="select_area" style="width: 92px" class="the-form-select-one">
										<option value="-1">请选择</option>
										<script id="areaselect" type="text/html">
                							{{each list as pro index}}
                							<option value="{{pro.code}}">{{pro.name}}</option>
                							{{/each}}
                						</script>
									</select>
									<label  class="error" id="dterror"></label>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>详细地址：</label></div>
							<div class="tjcpxx-con-form1">
								<input name="text_companyadress" id="text_companyadress"
								type="text" class="tjcpxx-fprm-inp" value="${vo.ex4 }"> 
								<input type="button" id="button" value="查询" style="height: 24px;" />
								<label  class="error" id="dterror"></label>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title"><label>地图：</label></div>
							<div class="tjcpxx-con-form1">
								<div id="allmap" style="width: 300px; height: 300px;" class="l_dpxxinp"></div>
							</div>
						</div>
						</br>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>经纬度：</label></div>
							<div class="tjcpxx-con-form1">
								经度：<input class="tjcpxx-fprm-inp" type="text" id="longitude" style="width:110px;" name="longitude"value="${vo.ex5 }" /> 
								纬度：<input class="tjcpxx-fprm-inp" type="text" id="latitude" style="width:110px;" name="latitude" value="${vo.ex6 }" />
							<label  class="error" id="dterror"></label>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title"><label>排序：</label></div>
							<div class="tjcpxx-con-form1">
								<c:choose>
									<c:when test="${vo.sort>0 }">
										<input class="tjcpxx-fprm-inp" name="sort" type="text" value="${vo.sort}">
									</c:when>
									<c:otherwise>
										<input class="tjcpxx-fprm-inp" name="sort" type="text" value="0">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>发布时间：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input type="text" id="updatetime" name="updatetime" class="Wdate"
								 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${vo.updatetimetr }"/>
							</div>
						</div>
						
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>新闻图片：</label>
								<input type="hidden" name='imgs' id="imgs"/>
							</div>
							<div class="h_imgcon fix">
    							<ul class="fix" name="fix">
    								<!--<li><img src="images/bj-all_02.jpg"/></li>
    								<li><img src="images/img3.png"/></li>
						    		<li><img src="images/img3.png"/></li>
						    		<li><img src="images/img3.png"/></li>
						    		<li><img src="images/img3.png"/></li>
						    		<li><img src="images/img3.png"/></li>
						    		<li><img src="images/img3.png"/></li>
						    		<li><img src="images/img3.png"/></li>-->
						    		<c:forEach var="s" items="${imglist}">
										<li><img src="${s.img }"/><i onclick="delimg(this)">X</i></li>
									</c:forEach>
    							</ul>
	    						<div class="h_scimg">	    							
	    							<input type="button" value="本地上传" class="h_scimgbut" />	    						
	    							<input type="file" id="filemhbut" name="filemhbut" value="上传图片" class="filemhbut" multiple="multiple"/>
	    						</div>
    						</div>
						</div>
						<div class="tjcpxx-con-mk1">
	                           <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>功能操作：</label></div>
	                           <div class="tjcpxx-con-form1">
	                           <c:choose>
		                           <c:when test="${vo.ishead==0 }">
		                            	<input name="ishead" type="checkbox" checked value="0"><span>头条</span>
		                           </c:when>
		                           <c:otherwise>
		                           		<input name="ishead" type="checkbox" value="0"><span>头条</span>
		                           </c:otherwise>
	                           </c:choose>
	                            <span class='marrig35'></span>
	                            
	                            <c:choose>
		                           <c:when test="${vo.isindex==0 }">
		                            	<input name="isindex" type="checkbox" checked value="0"><span>首页</span>
		                           </c:when>
		                           <c:otherwise>
		                           		<input name="isindex" type="checkbox" value="0"><span>首页</span>
		                           </c:otherwise>
	                           </c:choose>
	                            <span class='marrig35'></span>
	                            <c:choose>
		                           <c:when test="${vo.isrecommend==0 }">
		                            	<input name="isrecommend" type="checkbox" checked value="0"><span>推荐</span>
		                           </c:when>
		                           <c:otherwise>
		                           		<input name="isrecommend" type="checkbox" value="0"><span>推荐</span>
		                           </c:otherwise>
	                           </c:choose>
	                            <span class='marrig35'></span>
	                            <c:choose>
		                           <c:when test="${vo.istop==0 }">
		                            	<input name="istop" type="checkbox" checked value="0"><span>置顶</span>
		                           </c:when>
		                           <c:otherwise>
		                           		<input name="istop" type="checkbox" value="0"><span>置顶</span>
		                           </c:otherwise>
	                           </c:choose>
	                           <label  class="error" id="dterror"></label>
	                           </div>
	                     </div>
                        
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>发布状态：</label></div>
                            <div id="divStatus" class="tjcpxx-con-form huise">
	                            <span class='marrig35'></span>
	                            <c:choose>
		                           <c:when test="${vo.state==0 }">
	                            		<input name='state' checked type='radio' value='0'><span>发布</span>
	                            		<input name='state' type='radio' value='1'><span>未发布</span>
		                           </c:when>
		                           <c:otherwise>
		                           		<input name='state' type='radio' value='0'><span>发布</span>
	                            		<input name='state' checked type='radio' value='1'><span>未发布</span>
		                           </c:otherwise>
	                           </c:choose>
                            </div>
                        </div>
						<span class="marrig35"></span><input type="hidden" id="type" name="type" value=${type } type="text">
						<input type="hidden" id="ctype" name="ctype" value="${ctype }" type="text">
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp"  name="Save" type="button" value="保存">
								<input id="type_action" type="hidden" value="add">
								<span class="marrig35"></span> <input class="preserve-inp_hs"
									name="" type="button" value="取消" onclick="formCancel()">
							</div>
						</div>
						<!--tjcpxx-con-mk stop -->


					</form>
				</div>
			</div>
		</div>
		<!--tjcpxx-con stop -->
	</div>
	<script id="slist" type="text/html">
  {{each list as sclass i}}
   <option value="{{sclass.id}}">{{sclass.name}}</option>
  {{/each}}
   </script>
	<script id="tlist" type="text/html">
  {{each list as tclass i}}
   <option value="{{tclass.id}}">{{tclass.name}}</option>
   {{/each}}
    </script>
</div>

<script type="text/javascript">
function delimg(obj)
{
	$(obj).parent().hide();
}
    $(document).ready(function () {
    	BindRegion();
    	$("#singlefile").change(x);
    	function x(){
    		var objUrl = getObjectURL(this.files[0]);
    		console.log("objUrl = " + objUrl);
    		if (objUrl) {
    			$("#loadimg").attr("src", objUrl);
    		} else {
    			$("#loadimg").attr("src", "");
    		}
    	};
    	$("#filemhbut").change(y);
    	
    	function y(){
    		var objUrl = getObjectURL(this.files[0]) ;
    		console.log("objUrl = "+objUrl) ;    		
    		if (objUrl) {    			
    			 $.ajaxFileUpload({
    	            	url : "/app/api/img/upload",
    					secureuri : false,
    					fileElementId : "filemhbut",
    					dataType : "json",
    					//relationtype: 模块（品牌( 11)）
    					data : {
    						relationtype : 30
    					},
    	                type: 'POST',
    	                success: function (result) {
    	                    if (result.code == 0){
    	                    	Dalert("上传成功");    	                    	
    	                    	$(".h_imgcon ul").append('<li><img src="'+result.data[0]+'" /><i onclick="delimg(this)">X</i></li>');
    	                    }else{
    	                   	 	Dalert("上传图片失败");
    	                   	}
    	                    $("#filemhbut").change(y);
    	                    //TODO 结束正在加载中
    	                },
    	                error:function(e){
    	                	 alert(JSON.stringify(e));
    	                	 $("#filemhbut").change(y);
    	                }
    	            });
    			
    		}
    	}
    	//建立一个可存取到该file的url
    	function getObjectURL(file) {	
    		var url = null;
    		if (window.createObjectURL != undefined) { // basic
    			url = window.createObjectURL(file);
    		} else if (window.URL != undefined) { // mozilla(firefox)
    			url = window.URL.createObjectURL(file);
    		} else if (window.webkitURL != undefined) { // webkit or chrome
    			url = window.webkitURL.createObjectURL(file);
    		}
    		return url;
    	}
    	var id = $("#id").val();
    	if(id!=""){
    		Class.unit(Class.callback);
    	}else{
    		Class.unit();
    	}
    	//地图
    	$("#button").click(function() {
			searchByStationName();
		});
		var stringBuilder = [];

		var map = new BMap.Map("allmap");
		var address = document
				.getElementById("text_companyadress").value;
		var provincename = $('#select_province option:selected')
				.text();
		var cityname = $('#select_city option:selected').text();
		var areaname = $('#select_area option:selected').text();
		if (provincename != "请选择") {
			stringBuilder.push(provincename);
		}
		if (cityname != "请选择") {
			stringBuilder.push(cityname);
		}
		if (areaname != "请选择") {
			stringBuilder.push(areaname);
		}

		address = stringBuilder.join("") + address;
		if (address == "") {
			map.centerAndZoom("北京", 12);
		} else {
			map.centerAndZoom(address, 12);
			var lng = $("#longitude").val();
			var lat = $("#latitude").val();
			var marker = new BMap.Marker(new BMap.Point(lng,
					lat)); // 创建标注，为要查询的地方对应的经纬度
			map.addOverlay(marker);
		}

		map.enableScrollWheelZoom(); //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom(); //启用地图惯性拖拽，默认禁用

		map.addControl(new BMap.NavigationControl()); //添加默认缩放平移控件
		map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
		map.addControl(new BMap.OverviewMapControl({
			isOpen : true,
			anchor : BMAP_ANCHOR_BOTTOM_RIGHT
		})); //右下角，打开

		var localSearch = new BMap.LocalSearch(map);
		localSearch.enableAutoViewport(); //允许自动调节窗体大小
		function searchByStationName() {
			var stringBuilder2 = [];
			var provincename = $(
					'#select_province option:selected').text();
			var cityname = $('#select_city option:selected')
					.text();
			var areaname = $('#select_area option:selected')
					.text();
			if (provincename != "请选择") {
				stringBuilder2.push(provincename);
			}
			if (cityname != "请选择") {
				stringBuilder2.push(cityname);
			}
			if (areaname != "请选择") {
				stringBuilder2.push(areaname);
			}
			map.clearOverlays();// 清空原来的标注
			var address = document
					.getElementById("text_companyadress").value;
			address = stringBuilder2.join("") + address;
			localSearch.setSearchCompleteCallback(function(
					searchResult) {
				var poi = searchResult.getPoi(0);

				$("#longitude").val(poi.point.lng);
				$("#latitude").val(poi.point.lat);
				map.centerAndZoom(poi.point, 13);
				var marker = new BMap.Marker(new BMap.Point(
						poi.point.lng, poi.point.lat)); // 创建标注，为要查询的地方对应的经纬度
				map.addOverlay(marker);
				var content = address + "<br/><br/>经度："
						+ poi.point.lng + "<br/>纬度："
						+ poi.point.lat;
				var infoWindow = new BMap.InfoWindow(
						"<p style='font-size:14px;'>" + content
								+ "</p>");
				marker.addEventListener("click", function() {
					this.openInfoWindow(infoWindow);
				});
				// marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			});
			localSearch.search(address);
		}
        $(".h_scimgbut1").click(function () {
            $.ajaxFileUpload({
            	url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'singlefile',
				dataType : "json",
				//relationtype: 模块（品牌( 11)）
				data : {
					relationtype : 30
				},
                type: 'POST',
                success: function (result) {
                    // alert(JSON.stringify(result));
                    //$(".url1").html(JSON.stringify(result));
                    $("input[name='img']").val(result.data); 
                    if (result.code == 0)
                        $("#loadimg").attr("src", $("#imgsrc").val()+result.data[0]);
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
    function formCancel() {
    	var type=$("#type").val();
    	var ctype=$("#ctype").val();
		location.href = "/platform/news/mapnewsimg?type="+type+"&ctype="+ctype;
	}
</script>
