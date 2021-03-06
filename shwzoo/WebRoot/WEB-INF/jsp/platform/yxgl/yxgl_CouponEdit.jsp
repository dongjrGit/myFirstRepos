<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/platform/js/goods/goods_class.js"></script>
<script src="/resource/public/platform/js/yxgl/couponSave.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        Class.unit(Class.callback);
        var ut=${data.couponusetype };
        $("#usetype").val(ut);
        var ct=${data.coupontype}
        $("#coupontype").val(ct);
        var gt=${data.gettype}
        $("#gettype").val(gt);
        coupon.Init();
    });
    function formCancel() {
        location.href = "yxgl_CouponList";
    }
</script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">编辑优惠劵</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx">
            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" method="post">
                        <!--  @*编号*@ -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>编号：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="num" class="tjcpxx-fprm-inp" name="num" type="text" readonly value="${data.couponnum }">
                            </div>
                        </div>
                        <!-- @*名称*@ -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>名称：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="name" class="tjcpxx-fprm-inp" name="name" type="text" value="${data.couponname }">
                                 <input id="id" name="id" type="hidden" value="${data.id }">
                            </div>
                        </div>
                       <!--  @*面值*@ -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>面值：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="facevalue" class="tjcpxx-fprm-inp" style="width:150px;" 
                                name="facevalue" type="text" value="<fmt:formatNumber value="${data.facevalue}" pattern="0.00"/>">&nbsp;元
                            </div>
                        </div>
                      <!--   @*数量*@ -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>数量：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="count" class="tjcpxx-fprm-inp" style="width:150px;" 
                                name="count" type="text" value="${data.conponcount }">
                            </div>
                        </div>
                      <!--   @*优惠卷类型*@ -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>优惠劵类型：</label></div>
                            <div class="tjcpxx-con-form1">
                                <select id="coupontype" name="coupontype" class="the-form-select" onchange="coupon.TypeChange()">
                                    <option value="0">通用</option>
                                    <option value="1">金额限制</option>
                                </select>
                                <!-- <input name="issustype" type="hidden" value="1"> -->
                            </div>
                        </div>
                        <div id="divtype" class="tjcpxx-con-mk1" style="display:none;">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>金额限制：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="mjprice" class="tjcpxx-fprm-inp" style="width:150px;" name="mjprice" 
                                type="text" value="<fmt:formatNumber value="${data.fullreductionvalue}" pattern="0.00"/>">&nbsp;元
                            </div>
                        </div>
                      <!--   @*使用对象*@ -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>使用对象：</label></div>
                            <div class="tjcpxx-con-form1">
                                <select id="usetype" name="usetype" class="the-form-select" onchange="coupon.Type2Change()">
                                    <option value="0">商品</option>
                                    <option value="2">店铺</option>
                                    <option value="3">全场通用</option>
                                </select>
                            </div>
                        </div>
                        <div id="divusetype1" class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品分类：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="usetypeid" name="usetypeid" type="hidden" value="${data.usetypeid }" />
                                <input type="hidden" value="${fid }" id="fid" />
                                <input type="hidden" value="${sid }" id="sid" />
                                <input type="hidden" value="${tid }" id="tid" />
                                <select name="firstID" id="firstID" onchange="Class.firstChange(Class.callback, 'fc')">
                                    <option value="0" id="defaultfc" selected>无</option>
                                    <script id="flist" type="text/html">
                                        {{each list as fclass i}}
                                        <option value="{{fclass.id}}">{{fclass.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>-->
                                <select name="secondID" id="secondID" onchange="Class.firstChange(Class.callback, 'sc')">
                                    <option value="0" id="defaultsc" selected>无</option>
                                    <script id="slist" type="text/html">
                                        {{each list as sclass i}}
                                        <option value="{{sclass.id}}">{{sclass.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>-->
                                <select name="thirdID" id="thirdID" onchange="coupon.getSPU()">
                                    <option value=" 0" id="defaulttc" selected>无</option>
                                    <script id="tlist" type="text/html">
                                        {{each list as tclass i}}
                                        <option value="{{tclass.id}}">{{tclass.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </div>
                        </div>
                        <div id="divusetype2" class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="select_spu" type="text" class="tjcpxx-fprm-inp" value="${spuname }" data="${spuid }" />
                            </div>
                            <div style="margin-top:25px;margin-left:13px;">
                                <ul>
                                    <script id="select_spulist" type="text/html">
                                        {{each list as spu i}}
                                        <li data="{{spu.id}}">{{spu.name}}</li>
                                        {{/each}}
                                    </script>
                                </ul>
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>领用方式：</label></div>
                            <div class="tjcpxx-con-form">
                                <select id="gettype" name="gettype" class="the-form-select">
                                    <option value="0">会员领取</option>
                                    <option value="1">满返促销</option>
                                    <option value="2">系统赠送</option>
                                    <option value="3">生日赠送</option>
                                     <option value="4">抽奖专用</option>
                                </select>
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>发放时间：</label></div>
                            <div class="tjcpxx-con-form">
                                <input type="text" name="starttime" id="starttime" class="Wdate2" 
                                onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endtime\')}' })" 
                                value="${data.providetimestr }" readonly="readonly" />
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>过期时间：</label></div>
                            <div class="tjcpxx-con-form">
                                <input type="text" name="endtime" id="endtime" class="Wdate2" 
                                onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'starttime\')}' })" 
                                value="${data.endtimestr }" readonly="readonly" />
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>每人限领：</label></div>
                            <div class="tjcpxx-con-form">
                                <input id="endday" class="tjcpxx-fprm-inp" style="width:150px;" 
                                name="getcount" type="text" value="${data.getcount }">&nbsp;张
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label>有效期：</label></div>
                            <div class="tjcpxx-con-form">
                                <input id="endday" class="tjcpxx-fprm-inp" style="width:150px;" 
                                name="endday" type="text" value="${data.endday }">&nbsp;天
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label>会员等级限制：</label></div>
                            <div class="tjcpxx-con-form">
                            <input type="hidden" id="getuserlevel" value="${data.getuserlevel }" />
                                <select id="userlevel" name="userlevel" class="the-form-select">
                                    <option value="0" id="defaultlevel" selected>无</option>
                                    <script id="levellist" type="text/html">
                                        {{each list as level i}}
                                        <option value="{{level.level}}">{{level.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title">公开领劵：</div>
                            <div id="divStatus" class="tjcpxx-con-form huise">
                            <c:choose>
                            <c:when test="${data.status==0}">
                             <input name='status' checked type='radio' value='0'><span>公开</span>
                                <span class='marrig35'></span>
                                <input name='status' type='radio' value='1'><span>不公开</span>
                            </c:when>
                            <c:otherwise>
                             <input name='status' type='radio' value='0'><span>公开</span>
                                <span class='marrig35'></span>
                                <input name='status' checked type='radio' value='1'><span>不公开</span>
                            </c:otherwise>
                            </c:choose>
                               
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label><span class="red marrig5">*</span>优惠券图片：</label>
							</div>
							<div class="tjcpxx-con-form">
								<div class="tjcpxx-con-form-upimg">
									<img id="loadimg" width="120px" height="115px" src="${data.imgurl}" />
								</div>
								<input type="hidden" name="img" value="${data.imgurl}" />
								<div style="width: 200px; float: left;">
									<input type="file" name="pics" id="singlefile" /> <a
										href="javascript:void(0);" style="color: #000"> <span
										class="tjcpxx-con-form-upthis">本地上传</span>
									</a>
								</div>
							</div>
						</div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><span class="red">*</span>使用平台：</div>
                            <div class="tjcpxx-con-form">
                              <c:choose>
                            <c:when test="${data.useplatform != null}">
										<c:choose>
											<c:when test="${fn:indexOf(data.useplatform,'1')>=0}">
												<input name="useplatform" type="checkbox" checked value="1">
												<span>pc端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="1">
												<span>pc端</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(data.useplatform,'2')>=0}">
												<input name="useplatform" type="checkbox" checked value="2">
												<span>app端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="2">
												<span>app端</span>
											</c:otherwise>
										</c:choose>
									</c:when>
                            <c:otherwise>
                               <input name='useplatform' type='checkbox' value='1'><span>pc端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='2'><span>app端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='3'><span>wap端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='4'><span>微信端</span>
                            </c:otherwise>
                            </c:choose>
                              
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" name="Save" type="button" value="保存">
                                <input id="action" type="hidden" value="updateCoupon">
                                <span class="marrig35"></span>
                                <input class="preserve-inp_hs" name="" type="button" value="取消" onclick="formCancel()">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->


                    </form>
                </div>
            </div>
        </div><!--tjcpxx-con stop -->
    </div>
</div>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script>
	$(document).ready(
			function() {
				$(".tjcpxx-con-form-upthis").click(
						function() {
							$.ajaxFileUpload({
								url : "/app/api/img/upload",
								secureuri : false,
								fileElementId : 'singlefile',
								dataType : "json",
								//relationtype: 活动 (4)
								data : {
									relationtype : 2
								},
								type : 'post',
								success : function(result) {
									$("input[name='img']").val($("input[name='img']").val()+","+result.data[0]);
									var img=$("input[name='img']").val();
									if (result.code == 0){
										Dalert("上传成功");										
										$("#loadimg").attr( "src",result.data[0]);
									}
									else
										$("#loadimg").attr("src", "");
									//TODO 结束正在加载中
								},
								error : function(e) {
									alert(JSON.stringify(e));
								}
							});
						});
			})
	 
</script>
