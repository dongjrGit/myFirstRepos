<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath }/resource/public/seller/js/Zhgl/zhgl_role.js"></script>

<script type="text/javascript">
    function formCancel() {
        location.href = "zhgl_RoleList";
    }
</script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：账号管理 &gt; 账号管理 &gt; 角色维护 &gt;角色编辑
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">角色编辑</div>
            </div><!--zhgl-con-top  stop -->
            <form id="form" method="post">
                <div class="zhgl-con-con">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>角色名：</td>
                            <td>
                                <input name="name" type="text" class="text-inp-big" value="${data.name }">
                                <input name="id" type="hidden" value="${data.id }" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left" style="vertical-align:top;">角色描述：</td>
                            <td>
                                <input name="desc" type="text" class="text-inp-big" value="${data.description}">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">角色状态：</td>
                            <td id="divStatus">
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
                            </td>
                        </tr>

                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input name="Save" type="button" value="保存" class="big-but">
                                <input id="role_action" type="hidden" value="${roleaction }">
                                <input name="" type="button" value="取消" onclick="formCancel()" class="big-but-huise">
                            </td>
                        </tr>
                    </table>
                </div><!--zhgl-con-con  stop -->
            </form>
        </div><!--zhgl-con  stop -->
    </div><!--主要内容 右边结束 -->
</div>
