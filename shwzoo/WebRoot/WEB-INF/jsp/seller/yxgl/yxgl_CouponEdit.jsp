<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath }/resource/public/seller/js/yxgl/goods_class.js"></script>
<script src="${pageContext.request.contextPath }/resource/public/seller/js/yxgl/couponSave.js"></script>
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
    })
    function formCancel() {
        location.href = "yxgl_CouponList";
    }
</script>
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：营销管理 &gt; 优惠劵 &gt; 编辑优惠劵
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">编辑优惠劵</div>
            </div><!--zhgl-con-top  stop -->
            <form id="form" method="post">
                <div class="zhgl-con-con">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left">编号：</td>
                            <td>
                                <input name="num" type="text" class="text-inp-big" readonly value="${data.couponnum }">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>名称：</td>
                            <td>
                                <input name="name" type="text" class="text-inp-big" value="${data.couponname }">
                                <input id="id" name="id" type="hidden" value="${data.id }">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>面值：</td>
                            <td>
                                <input name="facevalue" type="text" class="text-inp-big" style="width:155px;"
                                 value="<fmt:formatNumber value="${data.facevalue}" pattern="0.00"/>">&nbsp;元
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>数量：</td>
                            <td>
                                <input name="count" type="text" class="text-inp-big" style="width:155px;" value="${data.conponcount }">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>优惠劵类型：</td>
                            <td>
                                <select id="coupontype" name="coupontype" class="sel-form" onchange="coupon.TypeChange()">
                                    <option value="0">通用</option>
                                    <option value="1">金额限制</option>
                                </select>
                            </td>
                        </tr>
                        <tr id="divtype" style="display:none;">
                            <td class="xjdpzzh-left"><span class="red">*</span>金额限制：</td>
                            <td>
                                <input id="mjprice" name="mjprice" type="text" class="text-inp-big" 
                                style="width:155px;" value="<fmt:formatNumber value="${data.fullreductionvalue}" pattern="0.00"/>">&nbsp;元
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>使用对象：</td>
                            <td>
                                <select id="usetype" name="usetype" class="sel-form" onchange="coupon.Type2Change()">
                                    <option value="0">商品</option>
                                    <option value="2">全场通用</option>
                                </select>
                            </td>
                        </tr>
                        <tr id="divusetype1">
                            <td class="xjdpzzh-left"><span class="red">*</span>商品分类：</td>
                            <td>
                                <div class="tjcpxx-con-form1">
                                    <input id="usetypeid" name="usetypeid" type="hidden" value="${data.usetypeid}" />
                                    <input type="hidden" value="${fid }" id="fid" />
                                    <input type="hidden" value="${sid }" id="sid" />
                                    <input type="hidden" value="${tid }" id="tid" />

                                    <select name="firstID" id="firstID" onchange="Class.firstChange(Class.callback, 'fc')" class="sel_allmost">
                                        <option value="0" id="defaultfc" selected>无</option>
                                        <script id="flist" type="text/html">
                                            {{each list as fclass i}}
                                            <option value="{{fclass.id}}">{{fclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select><span>--></span>
                                    <select name="secondID" id="secondID" onchange="Class.firstChange(Class.callback, 'sc')" class="sel_allmost">
                                        <option value="0" id="defaultsc" selected>无</option>
                                        <script id="slist" type="text/html">
                                            {{each list as sclass i}}
                                            <option value="{{sclass.id}}">{{sclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select><span>--></span>
                                    <select name="thirdID" id="thirdID" class="sel_allmost" onchange="coupon.getSPU()">
                                        <option value="0" id="defaulttc" selected>无</option>
                                        <script id="tlist" type="text/html">
                                            {{each list as tclass i}}
                                            <option value="{{tclass.id}}">{{tclass.name}}</option>
                                            {{/each}}
                                        </script>
                                    </select>

                                </div>
                            </td>
                        </tr>
                        <tr id="divusetype2">
                            <td class="xjdpzzh-left"><span class="red">*</span>商品：</td>
                            <td>
                                <div class="tjcpxx-con-form1">
                                    <input id="select_spu" type="text" class="text-inp-big" value="${spuname }" data="${spuid }"/>
                                </div>
                                <div style="margin-left:13px;">
                                    <ul>
                                        <script id="select_spulist" type="text/html">
                                            {{each list as spu i}}
                                            <li data="{{spu.id}}">{{spu.name}}</li>
                                            {{/each}}
                                        </script>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>领用方式：</td>
                            <td>
                                <select id="gettype" name="gettype" class="sel-form">
                                    <option value="0">会员领取</option>
                                    <option value="1">满返促销</option>
                                    <option value="2">系统赠送</option>
                                    <option value="3">生日赠送</option>
                                    <option value="4">抽奖专用</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>发放时间：</td>
                            <td>
                                <input type="text" name="starttime" id="starttime" class="Wdate" 
                                onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endtime\')}' })" 
                                value="${data.providetimestr }" readonly="readonly" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>过期时间：</td>
                            <td>
                                <input type="text" name="endtime" id="endtime" class="Wdate" 
                                onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'starttime\')}' })" 
                                value="${data.endtimestr }" readonly="readonly" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>每人限领：</td>
                            <td>
                                <input name="getcount" type="text" class="text-inp-big" style="width:155px;" value="${data.getcount}">&nbsp;张
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">有效期：</td>
                            <td>
                                <input name="endday" type="text" class="text-inp-big" style="width:155px;" value="${data.endday}">&nbsp;天
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">用户等级限制：</td>
                            <td>
                                <select id="userlevel" name="userlevel" class="sel-form">
                                    <option value="0" id="defaultlevel" selected>无</option>
                                    <script id="levellist" type="text/html">
                                        {{each list as level i}}
                                        <option value="{{level.level>{{level.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">公开领劵</td>
                            <td>
                            <c:choose>
                            <c:when test="${data.status==0 }">
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
                            </td>
                        </tr>
                        
                        <tr>
                        	<td class="xjdpzzh-left" ><span class="red">*</span>使用平台：</td>
                        	<td>
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
										<c:choose>
											<c:when test="${fn:indexOf(data.useplatform,'3')>=0}">
												<input name="useplatform" type="checkbox" checked value="3">
												<span>wap端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="3">
												<span>wap端</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(data.useplatform,'4')>=0}">
												<input name="useplatform" type="checkbox" checked value="4">
												<span>微信端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="4">
												<span>微信端</span>
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
                        	</td>
         				</tr>
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input name="Save" type="button" value="保存" class="big-but">
                                <input id="action" type="hidden" value="updateCoupon">
                                <input name="" type="button" value="取消" onclick="formCancel()" class="big-but-huise">
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>