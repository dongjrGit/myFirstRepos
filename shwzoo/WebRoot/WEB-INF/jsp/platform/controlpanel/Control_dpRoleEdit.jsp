<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/manager_dprole.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        autoxl.bind("select_shop", getShopStartwithName, true);
    })
</script>
<div class="mainright">
    <div class="l_wzmb">
        <!--l_wzmbtop   begin -->
        <div class="l_wzmbtop">
            <ul>
                <c:choose>
                 <c:when test="${data.id == null }">
                  <li class="sj_hover"><a href="javascript:void(0);">店铺角色添加</a><span class="sj-img"></span></li>
                 </c:when>
                 <c:otherwise>
                 <li class="sj_hover"><a href="javascript:void(0);">店铺角色编辑</a><span class="sj-img"></span></li>
                 </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <!--l_wzmbtop   stop -->
        <div class="tjcpxx">

            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" method="post">

                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>角色名称：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="name" type="text" value="${data.name }">
                                <input name="id" type="hidden" value="${data.id }" />
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>关联店铺：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="select_shop" type="text" class="tjcpxx-fprm-inp" value="${shopname }" />
                                <input id="shopid" type="hidden" name="shopid" value="${data.shopid }" />
                            </div>
                            <c:if test="${data.id ==null}">
                             <div style="margin-top:25px;margin-left:15px;left: 218px;">
                                <ul>
                                    <script id="select_shoplist" type="text/html">
                                        {{each list as shop i}}
                                        <li data="{{shop.id}}">{{shop.name}}</li>
                                        {{/each}}
                                    </script>
                                </ul>
                            </div>
                            </c:if>
                            </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>角色描述：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="desc" type="text" value="${data.description}">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"></div>
                            <div id="divStatus" class="tjcpxx-con-form huise">
                                <c:choose>
									<c:when test="${data.status==null}">
										<input name='status' checked type='radio' value='0'>启用
                                            <span class='marrig35'></span>
										<input name='status' type='radio' value='1'>禁用
						        	</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${data.status==0}">
												<input name='status' checked type='radio' value='0'>启用
                                                <span class='marrig35'></span>
												<input name='status' type='radio' value='1'>禁用
                                            </c:when>
											<c:otherwise>
												<input name='status' type='radio' value='0'>启用
                                                <span class='marrig35'></span>
												<input name='status' type='radio' checked value='1'>禁用
                                            </c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" name="Save" type="button" value="保存">
                                <input id="role_action" type="hidden" value="${roleaction }">
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
<script>
    function formCancel() {
        location.href = "control_dpRoleList";
    }
</script>