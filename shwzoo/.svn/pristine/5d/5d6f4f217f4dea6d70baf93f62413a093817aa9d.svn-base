
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=v66Wo0IkRp2uWVi5RNsaj656"></script>
<script src="/resource/public/platform/js/shop/shopadd.js"></script>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="/resource/public/platform/js/upload_img.js"></script>
<script type="text/javascript">
	$(function(){
		$("#div_jqcp").upload({
			id:"examinereport",
			paramname:"examinereport",
			imgmax:3,
			uploaddata:{
				// ftype:上传文件类型（图片文件=1，其他文件=2）
        		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
				relationtype:1
			},
		});
		
		$("#div_certificate").upload({
			id:"certificate",
			paramname:"certificate",
			imgmax:3,
			uploaddata:{
				// ftype:上传文件类型（图片文件=1，其他文件=2）
        		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
				relationtype:1
			}
		});
		
		$("#div_producelicence").upload({
			id:"producelicence",
			paramname:"producelicence",
			imgmax:2,
			uploaddata:{
				// ftype:上传文件类型（图片文件=1，其他文件=2）
        		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
				relationtype:1
			}
		});
		
		$("#div_adoctrinecredential").upload({
			id:"adoctrinecredential",
			paramname:"adoctrinecredential",
			imgmax:1,
			uploaddata:{
				// ftype:上传文件类型（图片文件=1，其他文件=2）
        		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
				relationtype:1
			}
		});
		//初始化数据
		 UploadAPI.InitData("examinereport","${shopAuthentication.examinereport}");
		 UploadAPI.InitData("producelicence","${shopAuthentication.producelicence}");
		 UploadAPI.InitData("adoctrinecredential","${shopAuthentication.adoctrinecredential}");
		 UploadAPI.InitData("certificate","${shopAuthentication.certificate}");
	});

</script>
<div class="mainright">
	<input type="hidden" value="${shop.id }" id="hidden_shopid" name="id" />
	<input type="hidden" value="${shop.userid }" id="hidden_userid" name="userid" /> 
	<input type="hidden" value="${shopclassfullpath}" id="hidden_shopclass" name="hidden_shopclass" /> 
	<input type="hidden" value="${shopAuthentication.companyprovince }" id="hidden_province" name="companyprovince" /> 
	<input type="hidden" value="${shopAuthentication.companycity }" id="hidden_city" name="companycity" /> 
	<input type="hidden" value="${shopAuthentication.companyarea }" id="hidden_area" name="companyarea" /> 
	<input type="hidden" value="${shopAuthentication.companytype }" id="hidden_companytype" name="companytype" /> 
	<input type="hidden" value="${shopAuthentication.id }" id="hidden_shopAuthenticationid" name="shopAuthenticationid" /> 
	<input type="hidden" value="${accounts.id }" id="hidden_accountsid" name="accountsid" /> 
	<div class="l_wzmb">

		<c:choose>
			<c:when test="${shop.id == null }">
				<div class="l_wzmbtop">
					<ul>
						<li class="sj_hover"><a href="javascript:void(0)">添加新店铺</a><span
							class="sj-img"></span></li>
					</ul>
				</div>
				<!--l_wzmbtop   stop -->
			</c:when>

			<c:otherwise>
				<div class="l_wzmbtop">
					<ul>
						<li class="sj_hover"><a href="javascript:void(0)">编辑店铺信息</a><span
							class="sj-img"></span></li>
					</ul>
				</div>
				<!--l_wzmbtop   stop -->
			</c:otherwise>
		</c:choose>

		<div class="l_dpxx">
			<form id="signupForm">
				<div class="l_dpxxmk">
					<div class="l_dpxxtitle">账户信息</div>
					<div class="l_dpxxcon">
						<table>
							<tr>
								<td class="text-right"><span class="red">*</span>用户名：</td>
								<td><input name="text_username" id="text_username" type="text"
									class="l_dpxxinp" value="${accounts.loginname }"></td>
							</tr>
							<!-- <tr>
                                 <td class="text-right"><span class="red">*</span>验证码：</td>
                                 <td>
                                     <input name="text_imgcode" id="text_imgcode" type="text" class="inp-big">
                                     <img src="/VerifyCodeServlet" class="refleshverification_img" width="126" height="34" alt="验证码" />
                                     看不清？ <span class="lvse"><a href="javascript:void(0)" class="refleshverification">换一张</a></span>
                                 </td>
                            </tr> -->
							<tr>
								<td class="text-right"><span class="red">*</span>验证手机：</td>
								<td><input name="text_phone" maxlength="11" id="text_phone" type="text"
									class="l_dpxxinp" onkeyup="this.value=this.value.replace(/\D/g,'')" 
									onafterpaste="this.value=this.value.replace(/\D/g,'')" value="${accounts.phone }">
								</td>
								<%-- <td><input name="text_supporttel" id="text_supporttel"
									type="text" class="l_dpxxinp" onkeyup="this.value=this.value.replace(/\D/g,'')"  
									onafterpaste="this.value=this.value.replace(/\D/g,'')"  value="${shop.supporttel }">
								</td> --%>
							</tr>
						</table>
					</div>
					<!--l_dpxxcon  stop -->
				</div>
				<!--l_dpxxmk  stop -->
				<div class="l_dpxxmk">
					<div class="l_dpxxtitle">店铺信息</div>
					<div class="l_dpxxcon">
						<table>
							 <tr>
								<td class="text-right"><span class="red">*</span>店铺名：</td>
								<td><input name="text_shopname" id="text_shopname" type="text"
									class="l_dpxxinp" value="${shop.name }"></td>
							</tr> 
							<%-- <c:choose>
								<c:when test="${shop.id == null }">
									<tr>
													<td class="text-right"><span class="red">*</span>店铺名：</td>
													<td><input name="text_shopname" id="text_shopname" type="text"
														class="l_dpxxinp" value="${shop.name }"></td>
												</tr>
									<!--l_wzmbtop   stop -->
								</c:when>
					
								<c:otherwise>
									<tr>
													<td class="text-right"><span class="red">*</span>店铺名：</td>
													<td><input name="text_shopname" id="text_shopname" type="text"
														class="l_dpxxinp" value="${shop.name }" readonly></td>
												</tr>
									<!--l_wzmbtop   stop -->
								</c:otherwise>
							</c:choose> --%>
							<tr>
								<td class="text-right"><span class="red">*</span>店铺简介：</td>
								<td><input name="text_description" id="text_description"
									type="text" class="l_dpxxinp" value="${shop.description }">
								</td>
							</tr>
							
							<tr>
								<td class="text-right"><span class="red">*</span>店铺图片：</td>
								<td>
									<div class="l_zhxxmk-right" id="div_shopimage">
										<input type="file" name="file_shopimage" id="file_shopimage" hidden />
										<c:if test="${shop.imgurl  == null }">
											<img src="${ctx }/resource/public/seller/images/djsc.png" id="img_shopimage"
												width="107" height="100" name="img_shopimage">
										</c:if>
										<c:if test="${shop.imgurl != null }">
											<img src="<%=path %>${shop.imgurl }" id="img_shopimage"
												width="107" height="100" name="img_shopimage">
										</c:if>
										<input type="hidden" name="shopimage" value="${shop.imgurl }"
											id="shopimage" />

									</div>
								</td>
							</tr>

							<tr>
								<td class="text-right"><span class="red">*</span>客服电话：</td>
								<td><input name="text_supporttel" id="text_supporttel"
									type="text" class="l_dpxxinp" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"  value="${shop.supporttel }">
								</td>
							</tr>
							<tr>
								<td class="text-right"><span class="red">*</span>店铺类型：</td>
								<td>
								<select id="shoptype" name="shoptype" class="l_dpxxsel">
								<c:choose>
								<c:when test="${shop.shoptype == 0 }">
								        <option value="0" selected >门票</option>
										<option value="1" >马戏团票</option>
										<option value="2">游乐票</option>
										<option value="3">餐饮票</option>
										<option value="4">动物互动</option>
										<option value="5">主题商品</option>
								</c:when>
								<c:when test="${shop.shoptype == 1 }">
								        <option value="0"  >门票</option>
										<option value="1" selected >马戏团票</option>
										<option value="2" >游乐票</option>
										<option value="3">餐饮票</option>
										<option value="4">动物互动</option>
										<option value="5">主题商品</option>
								</c:when>
								<c:when test="${shop.shoptype == 2 }">
								        <option value="0"  >门票</option>
										<option value="1">马戏团票</option>
										<option value="2" selected>游乐票</option>
										<option value="3" >餐饮票</option>
										<option value="4">动物互动</option>
										<option value="5">主题商品</option>
								</c:when>
								<c:when test="${shop.shoptype == 3 }">
								        <option value="0"  >门票</option>
										<option value="1">马戏团票</option>
										<option value="2">游乐票</option>
										<option value="3" selected>餐饮票</option>
										<option value="4">动物互动</option>
										<option value="5">主题商品</option>
								</c:when>
								<c:when test="${shop.shoptype == 4 }">
								        <option value="0"  >门票</option>
										<option value="1">马戏团票</option>
										<option value="2">游乐票</option>
										<option value="3" >餐饮票</option>
										<option value="4" selected>动物互动</option>
										<option value="5">主题商品</option>
								</c:when>
								<c:when test="${shop.shoptype == 5 }">
								        <option value="0"  >门票</option>
										<option value="1">马戏团票</option>
										<option value="2">游乐票</option>
										<option value="3" >餐饮票</option>
										<option value="4">动物互动</option>
										<option value="5" selected>主题商品</option>
								</c:when>				
								<c:otherwise>
							            <option value="0" selected >门票</option>
										<option value="1">马戏团票</option>
										<option value="2">游乐票</option>
										<option value="3">餐饮票</option>
										<option value="4">动物互动</option>
										<option value="5">主题商品</option>
										<!-- <option value="5">礼品卡票</option> -->
								</c:otherwise>
							</c:choose>
										
								</select>
								</td>
							</tr>
							<tr>
								<td class="text-right"><span class="red"></span>店铺分类：</td>
								<td><select id="ci_select" name="ci_select"
									class="l_dpxxsel ci_select" select-val="0">
										<option value=" -1">请选择</option>
										<c:if test="${clist != null and clist.size() > 0 }">
											<c:forEach items="${clist }" var="list">
												<c:choose>
													<c:when test="${list.id ==shop.classid}">
														<option value="${list.id }" selected="selected">${list.name }</option>
													</c:when>
													<c:otherwise>
														<option value="${list.id }">${list.name }</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:if>
								</select></td>
							</tr>
							<tr>
								<td class="text-right">经营范围：</td>
								<td>
									<input name="text_marketingscope" id="text_marketingscope"
									type="text" class="l_dpxxinp" value="${shopAuthentication.marketingscope }">
								</td>
							</tr>
							<tr>
								<td class="text-right">描述：</td>
								<td>
									<textarea style="margin: 0px; width: 452px; height: 70px;"
									name="text_bewrite" id="text_bewrite">${shopAuthentication.bewrite }</textarea>
								</td>
							</tr>
						</table>
					</div>
					<!--l_dpxxcon  stop -->
				</div>
				<!--l_dpxxmk  stop -->
				<div class="l_dpxxmk">
					<div class="l_dpxxtitle">联系人信息</div>
					<div class="l_dpxxcon">
						<table>
							<tr>
								<td class="text-right"><span class="red">*</span>联系人姓名：</td>
								<td><input name="text_principalname" id="text_principalname"
									type="text" class="l_dpxxinp"
									value="${shopAuthentication.principalname }"></td>
							</tr>
							<tr>
								<td class="text-right"><span class="red">*</span>手机：</td>
								<td><input name="text_principalmobile" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"  id="text_principalmobile"
									type="text" class="l_dpxxinp"
									value="${shopAuthentication.principalmobile }"></td>
							</tr>
							<!-- <tr>
                                <td class="text-right"><span class="red">*</span>短信验证码：</td>
                                <td>
                                    <input name="text_mobilecode" id="text_mobilecode" type="text" class="l_dpxxinp" value="">
                                    <a href="javascript:void(0)" id="g_hqyzm fl" class="g_hqyzm fl" style="color: rgb(0, 0, 0);"><span class="hqyzm-sj-zhxxmk"><b style="display: none;">60</b><font>获取验证码</font></span></a>
                                </td>
                            </tr> -->

							<tr>
								<td class="text-right"><span class="red"></span>联系人邮箱：</td>
								<td><input name="text_principalemail" id="text_principalemail"
									type="text" class="l_dpxxinp"
									value="${shopAuthentication.principalemail }"></td>
							</tr>
						</table>
					</div>
					<!--l_dpxxcon  stop -->
				</div>
				<div class="l_dpxxmk">
					<div class="l_dpxxtitle">公司信息</div>
					<div class="l_dpxxcon">
						<table>
							<tr>
								<td class="text-right"><span class="red"></span>公司名称：</td>
								<td><input name="text_companyname" id="text_companyname"
									type="text" class="l_dpxxinp"
									value="${shopAuthentication.companyname }"></td>
							</tr>
							<%-- <tr>
								<td class="text-right"><span class="red"></span>营业执照号码：</td>
								<td><input name="licenseNum" id="licenseNum"
									type="text" class="l_dpxxinp"
									value="${shopAuthentication.licenseNum }"></td>
									</tr> --%>
							<tr>
								<td class="text-right"><span class="red">*</span>营业执照、组织机构代码证、税务登记证：</td>
								<td>
									<div class="l_dpxxtj" id="div_license">
										<input type="file" name="pic" id="file_license"  hidden />
										<c:if test="${shopAuthentication.license  == null }">
											<img src="/resource/public/seller/images/djsc.png" id="img_license"
												width="107" height="100" name="img_license">
										</c:if>
										<c:if test="${shopAuthentication.license != null }">
											<img src="<%=path %>${shopAuthentication.license }"
												id="img_license" width="107" height="100" name="license">
										</c:if>
										<input type="hidden" name="license"
											value="${shopAuthentication.license }" id="license" />

									</div>
									<div class="l_dpxxtj" id="div_organization">
										<input type="file" name="pic" id="file_organization" hidden />
										<c:if test="${shopAuthentication.id  == null }">
											<img src="${ctx }/resource/public/seller/images/djsc.png" width="107"
												height="100" id="img_organization" name="img_organization">
										</c:if>
										<c:if test="${shopAuthentication.organization != null }">
											<img src="<%=path %>${shopAuthentication.organization }"
												width="107" height="100" id="img_organization"
												name="organization">
										</c:if>
										<input type="hidden" name="organization"
											value="${shopAuthentication.organization }" id="organization" />

									</div>
									<div class="l_dpxxtj" id="div_tax">
										<input type="file" name="pic" id="file_tax" hidden />
										<c:if test="${shopAuthentication.id  == null }">
											<img src="${ctx }/resource/public/seller/images/djsc.png" width="107"
												height="100" id="img_tax" name="img_tax">
										</c:if>
										<c:if test="${shopAuthentication.tax != null }">
											<img src="<%=path %>${shopAuthentication.tax }" width="107"
												height="100" id="img_tax" name="tax">
										</c:if>
										<input type="hidden" name="tax"
											value="${shopAuthentication.tax }" id="tax" />
									</div>
								</td>
							</tr>
							<tr>
								<td class="text-right"><span class="red">*</span>公司所在地：</td>
								<td>
									<div class="l_dpxxselcon">
										<select id="select_province" name="select_province"
											class="l_dpxxsel">
											<option value="-1">请选择</option>
											<script id="proviceselect" type="text/html">
                                                {{each list as pro index}}
                                                <option value="{{pro.code}}">{{pro.name}}</option>
                                                {{/each}}
                                            </script>
										</select> <select id="select_city" name="select_city" class="l_dpxxsel">
											<option value="-1">请选择</option>
											<script id="cityselect" type="text/html">
                                                {{each list as pro index}}
                                                <option value="{{pro.code}}">{{pro.name}}</option>
                                                {{/each}}
                                            </script>
										</select> <select id="select_area" name="select_area" class="l_dpxxsel">
											<option value="-1">请选择</option>
											<script id="areaselect" type="text/html">
                                                {{each list as pro index}}
                                                <option value="{{pro.code}}">{{pro.name}}</option>
                                                {{/each}}
                                            </script>
										</select>
									</div>
								</td>
							</tr>

							<tr>
								<td class="text-right"><span class="red">*</span>公司详细地址：</td>
								<td><input name="text_companyadress" id="text_companyadress"
									type="text" class="l_dpxxinp"
									value="${shopAuthentication.companyadress }"> <input
									type="button" id="button" value="查询" style="height: 36px;" />

								</td>
							</tr>
							<tr>
								<td class="text-right">地图：</td>
								<td>
									<div id="allmap" style="width: 300px; height: 300px;"
										class="l_dpxxinp"></div>
								</td>
							</tr>
							

							<tr>
								<td class="text-right">邮政编码：</td>
								<td><input name="text_postcode" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"   id="text_postcode" type="text"
									class="l_dpxxinp" value="${shopAuthentication.postcode }">
								</td>
							</tr>
							<tr>
								<td class="text-right">固定电话：</td>
								<td><input name="text_companytel" id="text_companytel"
									type="text" class="l_dpxxinp" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" 
									value="${shopAuthentication.companytel }"></td>
							</tr>
							 <tr>
								<td class="text-right">传真：</td>
								<td><input name="companyfox" id="text_companyfox"
									type="text" class="l_dpxxinp"
									value="${shopAuthentication.companyfox }"></td>
							</tr>
							<tr>
								<td class="text-right">&nbsp;</td>
								<td style="padding-top: 10px;"><c:choose>
										<c:when test="${shop.id == null }">
											<input class="preserve-inp" name="a_submit" id="a_submit"
												type="button" value="保存" />
										</c:when>
										<c:otherwise>
											<input class="preserve-inp" name="a_submitedit"
												id="a_submitedit" type="button" value="保存" />
										</c:otherwise>
									</c:choose> <input class="preserve-inp_hs" name="btn_goback"
									id="btn_goback" type="button" value="返回"></td>
							</tr>
						</table>
					</div>
					<!--l_dpxxcon  stop -->
				</div>
				<!--l_dpxxmk  stop -->
			</form>
		</div>
		<!-- l_dpxx   stop-->
	</div>
	<!--tjcpxx stop -->



</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {

						//点击返回按钮
						$("#btn_goback").bind("click", function() {
							window.location.href = "/platform/shop/list";
						});
						//初始化
						Init.bind();
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

					})
</script>
