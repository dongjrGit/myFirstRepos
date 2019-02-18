<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/OperatorSave.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        oper.unit();
    })

</script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">编辑操作管理员</a><span class="sj-img"></span></li>

            </ul>
        </div>
        <div class="tjcpxx">
            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" method="post">
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>用户名：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="loginname" type="text" value="${data.loginname }">
                                <input name="id" type="hidden" value="${data.id }" />
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>用户昵称：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="nickname" type="text" value="${data.nickname }">
                            </div>
                        </div>
                      <c:if test="${data.id==null }">
                      <div class="tjcpxx-con-mk1">
                                <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>密码：</label></div>
                                <div class="tjcpxx-con-form1">
                                    <input class="tjcpxx-fprm-inp" id="password" name="password" value="" type="password">
                                </div>
                            </div>
                            <div class="tjcpxx-con-mk1">
                                <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>确认密码：</label></div>
                                <div class="tjcpxx-con-form1">
                                    <input class="tjcpxx-fprm-inp" name="compassword" value="" type="password">
                                </div>
                            </div>
                      </c:if>
                            
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>所属部门：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input type="hidden" value="${fid}"id="fid" />
                                <input type="hidden" value="${sid}" id="sid" /> 
                                <input type="hidden" value="${tid}" id="tid" />
                                <input type="hidden" value="${data.departmentid}" id="departid" name="departid"/>
								<select name="firstID"
									id="firstID" onchange="oper.firstChange(oper.callback, 'fc')">
									<option value="0" id="defaultfc" selected>无</option>
									<script id="flist" type="text/html">
                                            {{each list as fclass i}}
                                            <option value="{{fclass.id}}">{{fclass.name}}</option>
                                            {{/each}}
                                        </script>
								</select>--> 
								<select name="secondID" id="secondID"
									onchange="oper.firstChange(oper.callback, 'sc')">
									<option value="0" id="defaultsc" selected>无</option>
									<script id="slist" type="text/html">
                                            {{each list as sclass i}}
                                             <option value="{{sclass.id}}">{{sclass.name}}</option>
                                            {{/each}}
                                        </script>
								</select>--> 
								<select name="thirdID" id="thirdID" >
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
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>关联角色：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="select_role" type="text" class="tjcpxx-fprm-inp" value="${data.rolename }" data="${data.roleid }" />
                                <input id="roleid" type="hidden" name="roleid" value="${data.roleid }" />
                            </div>
                            <div style="margin-top:25px;margin-left:15px;left: 218px;">
                                <ul>
                                    <script id="select_rolelist" type="text/html">
                                        {{each list as role i}}
                                        <li data="{{role.id}}">{{role.name}}</li>
                                        {{/each}}
                                    </script>
                                </ul>
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>真实姓名：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="realname" type="text" value="${data.realname }">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>邮箱：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="email" type="text" value="${data.email }">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" name="Save" type="button" value="保存">
                                <input id="oper_action" type="hidden" value="${action }">
                                <span class="marrig35"></span>
                                <input class="preserve-inp_hs" name="" type="button" value="取消" onclick="formCancel()">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function formCancel() {
        location.href = "operator_list";
    }
</script>
