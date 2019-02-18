<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_productspecs.js"></script>

<script
	src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_class.js"></script>

<script type="text/javascript">
document.ready=function () {
	if ($("#orderby").val() == "" || $("#orderby").val() == undefined) {
		$("#orderby").val(1);
	}
	//specs.bind(1);
	Class.unit(Class.callback);
	 var fid=$("#fid").val();
	 var sid=$("#sid").val();
	 var tid=$("#tid").val();
	 init(fid,sid,tid);
};
 
</script>
<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">编辑商品规格</a><span
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
								<label><span class="red marrig5">*</span>商品分类：</label>
							</div>
							<div class="tjcpxx-con-form1">
									<input id="ClassID" name="classid" type="hidden" value="${data.classid}" /> 
									<%-- <input type="hidden" value="${fid}" id="firstID" /> --%>
									<input type="hidden" value="${sid}" id="sID" /> 
									<input type="hidden" value="${tid}" id="tID" />
									
									<input type="hidden" value="${fid}" id="fid" /> 
									<input type="hidden" value="${sid}" id="sid" /> 
									<input type="hidden" value="${tid}" id="tid" /> 
									<input type="hidden" value="${data.id}" id="id" name="id" /> 
									<select name="firstID" id="firstID" onchange="Class.firstChange(Class.callback, 'fc');init();">
									<option value="0" id="defaultfc" selected>无</option>
									<script id="flist" type="text/html">
                                            {{each list as fclass i}}
                                            <option value="{{fclass.id}}">{{fclass.name}}</option>
                                            {{/each}}
                                        </script>
								</select>--> <select name="secondID" id="secondID"
									onchange="Class.firstChange(Class.callback, 'sc');init();">
									<option value="0" id="defaultsc" selected>无</option>
									<script id="slist" type="text/html">
                                            {{each list as sclass i}}
                                             <option value="{{sclass.id}}">{{sclass.name}}</option>
                                            {{/each}}
                                        </script>
								</select>--> <select name="thirdID" id="thirdID" onchange="initlist()">
									<option value="0" id="defaulttc" selected>无</option>
									<script id="tlist" type="text/html">
                                            {{each list as tclass i}}
                                             <option value="{{tclass.id}}">{{tclass.name}}</option>
                                            {{/each}}
                                        </script>
								</select>
							</div>
						</div>

						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>规格名称：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input class="tjcpxx-fprm-inp" name="name" type="text"
									value="${data.name}">
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label>规格类型：</label>
								<input type="hidden" value="${data.specstypeid}" id="typeid" name="typeid" />
							</div>
							<div class="tjcpxx-con-form1">
								<select id="TypeID" name="specstypeid" class="the-form-select">
									<script id="tlist" type="text/html">
                                            {{each list as tlist i}}
                                             <option value="{{tlist.id}}">{{tlist.name}}</option>
                                            {{/each}}
                                        </script>
								</select> 
								
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>排序：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<input id="orderby" class="tjcpxx-fprm-inp" name="orderby"
									type="text" value="${data.orderby}">
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>显示位置：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<c:choose>
									<c:when test="${data.displaylocation != null}">
										<c:choose>
											<c:when test="${fn:indexOf(data.displaylocation,'1')>=0}">
												<input name="displayL" type="checkbox" checked value="1">
												<span>详情介绍</span>
											</c:when>
											<c:otherwise>
												<input name="displayL" type="checkbox" value="1">
												<span>详情介绍</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(data.displaylocation,'2')>=0}">
												<input name="displayL" type="checkbox" checked value="2">
												<span>商品详情</span>
											</c:when>
											<c:otherwise>
												<input name="displayL" type="checkbox" value="2">
												<span>商品详情</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(data.displaylocation,'3')>=0}">
												<input name="displayL" type="checkbox" checked value="3">
												<span>规格参数</span>
											</c:when>
											<c:otherwise>
												<input name="displayL" type="checkbox" value="3">
												<span>规格参数</span>
											</c:otherwise>
										</c:choose>
										<%-- <c:choose>
											<c:when test="${fn:indexOf(data.displaylocation,'4')>=0}">
												<input name="displayL" type="checkbox" checked value="4">
												<span>购物车</span>
											</c:when>
											<c:otherwise>
												<input name="displayL" type="checkbox" value="4">
												<span>购物车</span>
											</c:otherwise>
										</c:choose>
 --%>
									</c:when>
									<c:otherwise>
										<input name="displayL" type="checkbox" value="1">
										<span>详情介绍</span>
										<input name="displayL" type="checkbox" value="2">
										<span>商品详情</span>
										<input name="displayL" type="checkbox" value="3">
										<span>规格参数</span>
										<input name="displayL" type="checkbox" value="4">
										<span>购物车</span>
									</c:otherwise>

								</c:choose>

								<span id="disloca" style="display: none;" class="red marrig5">显示位置不能为空</span>
								<input name="displaylocation" id="displaylocation" type="hidden"
									value="${data.displaylocation}">
							</div>

						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title"></div>
							<div id="divStatus" class="tjcpxx-con-form huise">

								<c:choose>
									<c:when test="${data.status==0}">
										<input name='status' checked type='radio' value='0'>
										<span>启用</span>
										<span class='marrig35'></span>
										<input name='status' type='radio' value='1'>
										<span>禁用</span>
									</c:when>
									<c:otherwise>
										<input name='status' type='radio' value='0'>
										<span>启用</span>
										<span class='marrig35'></span>
										<input name='status' checked type='radio' value='1'>
										<span>禁用</span>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title"></div>
							<div id="divIsEntry" class="tjcpxx-con-form huise">
								<c:choose>
									<c:when test="${data.isentry==true}">
										<input name='isentry' checked type='radio' value='true'>
										<span>可输入</span>
										<span class='marrig35'></span>
										<input name='isentry' type='radio' value='false'>
										<span>不可输入</span>

									</c:when>
									<c:otherwise>
										<input name='isentry' type='radio' value='true'>
										<span>可输入</span>
										<span class='marrig35'></span>
										<input name='isentry' checked type='radio' value='false'>
										<span>不可输入</span>

									</c:otherwise>
								</c:choose>

							</div>
						</div>

						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp" name="Save" type="button" value="保存">
								<input id="specs_action" type="hidden"
									value="updateProductSpecsById"> <span class="marrig35"></span>
								<input class="preserve-inp_hs" name="" type="button" value="取消"
									onclick="formCancel()">
							</div>
						</div>


					</form>
				</div>
			</div>
		</div>
		<!--tjcpxx-con stop -->
	</div>
</div>
<script>
	function formCancel() {

		location.href = "/platform/product/prospecs_list";

	}
	function init(fid,sid,tid){
		  	var strselect = "";
		    var selectvalue = $("#typeid").val();
		    var sids=$('#firstID option:selected').val()
		    if($('#firstID option:selected').val()!="0"){
		    	fid =$('#firstID option:selected').val();
		    	sid =$('#secondID option:selected').val();
		    	tid =$('#thirdID option:selected').val();
		    } 
		    if ( parseInt(fid) > 0) {
		        //获取商品规格类型
		        $.ajax({
		            url: "/platform/Specstype/getTypeByClassID",
		            type: "Post",
		            dataType: "json",
		            data: { "classid": fid,'classid2':sid,'classid3':tid },
		            success: function (data) {
		                if (data.code < 0) {
		                    Dalert(data.desc);
		                } else {
		                	 var listdata = {
		                             list: data.data
		                         }
		                	  var html = '<option value="0">请选择</option>' +template('tlist', listdata);
		                      $("#TypeID").html(html);
		                      $("#TypeID option").each(function () {
		                          if ($(this).val() == selectvalue) {
		                              $(this).attr("selected", "selected");
		                          } else {
		                              $(this).removeAttr("selected");
		                          }
		                      });
		                }
		            },
		            error: function () {

		            }
		        });
		    }
		    else {
		        $("#TypeID").html("<option value='0'></option>");
		    }
	}
</script>
