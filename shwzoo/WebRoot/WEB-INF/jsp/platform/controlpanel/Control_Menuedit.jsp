<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="${pageContext.request.contextPath }/resource/public/platform/js/ControlPanel/menusave.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        Menu.unit(Menu.callback);
    })
</script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">修改菜单</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx">
            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" method="post">

                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>菜单名称：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="name" type="text" value="${data.name }">
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>菜单路径：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input type="hidden" value="${data.id }" name="id" />
                                <input type="hidden" value="${fid }" id="fid" />
                                <input type="hidden" value="${sid }" id="sid" />
                                <input type="hidden" value="${tid }" id="tid" />
                                <select name="firstID" id="firstID" onchange="Menu.firstChange(Menu.callback, 'fm')">
                                    <option value="0" id="defaultfc" selected>无</option>
                                    <script id="flist" type="text/html">
                                        {{each list as fmenu i}}
                                         <option value="{{fmenu.id}}">{{fmenu.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>-->
                                <select name="secondID" id="secondID" onchange="Menu.firstChange(Menu.callback, 'sm')">
                                    <option value="0" id="defaultsc" selected>无</option>
                                    <script id="slist" type="text/html">
                                        {{each list as smenu i}}
                                         <option value="{{smenu.id}}">{{smenu.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>-->
                                <select name="thirdID" id="thirdID">
                                    <option value="0" id="defaulttc" selected>无</option>
                                    <script id="tlist" type="text/html">
                                        {{each list as tmenu i}}
                                         <option value="{{tmenu.id}}">{{tmenu.name}}</option>
                                        {{/each}}
                                    </script>
                                </select>
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>菜单链接：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input class="tjcpxx-fprm-inp" name="menuurl" type="text" value="${data.menuurl }">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>菜单来源：</label></div>
                            <div class="tjcpxx-con-form huise">
                            <c:choose>
                            <c:when test="${data.menutype==0}">
                              <input name='menutype' checked type='radio' value='0'><span>平台</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' type='radio' value='1'><span>卖家</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' type='radio' value='2'><span>买家</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' type='radio' value='3'><span>前台</span>
                            </c:when>
                            <c:when test="${data.menutype==1}">
                              <input name='menutype' type='radio' value='0'><span>平台</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' checked type='radio' value='1'><span>卖家</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' type='radio' value='2'><span>买家</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' type='radio' value='3'><span>前台</span>
                            </c:when>
                             <c:when test="${data.menutype==2}">
                               <input name='menutype' type='radio' value='0'><span>平台</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' type='radio' value='1'><span>卖家</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' checked type='radio' value='2'><span>买家</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' type='radio' value='3'><span>前台</span>
                            </c:when>
                            <c:otherwise>
                            <input name='menutype' type='radio' value='0'><span>平台</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' type='radio' value='1'><span>卖家</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' type='radio' value='2'><span>买家</span>
                                    <span class='marrig35'></span>
                                    <input name='menutype' checked type='radio' value='3'><span>前台</span>
                            
                            </c:otherwise>
                            </c:choose>
                            </div>
                        </div><!--tjcpxx-con-mk stop -->
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>类型：</label></div>
                            <div class="tjcpxx-con-form huise">
                            <c:choose>
                            <c:when test="${data.type==0}">
                             <input name='type' checked type='radio' value='0'><span>菜单</span>
                                    <span class='marrig35'></span>
                                    <input name='type' type='radio' value='1'><span>按钮</span>
                            </c:when>
                            <c:otherwise>
                             <input name='type' type='radio' value='0'><span>菜单</span>
                                    <span class='marrig35'></span>
                                    <input name='type' checked type='radio' value='1'><span>按钮</span>
                            </c:otherwise>
                            </c:choose>
                              
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>是否有子级：</label></div>
                            <div class="tjcpxx-con-form huise">
                             <c:choose>
                            <c:when test="${data.haschild==true}">
                            <input name='haschild' checked type='radio' value='true'><span>有</span>
                                    <span class='marrig35'></span>
                                    <input name='haschild' type='radio' value='false'><span>没有</span>
                            </c:when>
                            <c:otherwise>
                              <input name='haschild' type='radio' value='true'><span>有</span>
                                    <span class='marrig35'></span>
                                    <input name='haschild' type='radio' checked value='false'><span>没有</span>
                            </c:otherwise>
                            </c:choose>                              
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1" style="display:none;">
                            <div class="tjcpxx-con-form-title">菜单状态：</div>
                            <div class="tjcpxx-con-form huise">
                            <c:choose>
                            <c:when test="${data.status==0}">
                             <input name='status' checked type='radio' value='0'>启用
                                <span class='marrig35'></span>
                                <input name='status' type='radio' value='1'>禁用
                            </c:when>
                            <c:otherwise>
                             <input name='status' type='radio' value='0'>启用
                                <span class='marrig35'></span>
                                <input name='status' checked type='radio' value='1'>禁用
                            </c:otherwise>
                            </c:choose>
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" name="Save" type="button" value="保存">
                                <input id="action" type="hidden" value="updatemenu">
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
        location.href = "/platform/controlpanel/Control_Menulist";
    }
</script>