<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath }/resource/public/seller/js/spgl/spgl_spflsave.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        spfltj.unit(spfltj.callback);
    });
    function formCancel() {
        location.href = "spgl_customlist";
    }

</script>
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：商品管理 &gt; 商品管理 &gt; 商品分类管理 &gt; 编辑自定义分类
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">编辑自定义分类</div>
            </div><!--zhgl-con-top  stop -->
           <form id="form" method="post">
                <div class="zhgl-con-con">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>分类名称：</td>
                            <td>
                                <input name="name" type="text" class="text-inp-big" value="${data.name }">
                                <input name="id" type="hidden" value="${data.id }" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>关联父级分类：</td>
                            <td>
                                <div class="tjcpxx-con-form1">
                                    <input type="hidden" value="${fid }" id="fid" />
                                    <input type="hidden" value="${sid }" id="sid" />
                                    <c:choose>
                                    <c:when test="${data.id >0}">
                                    <select name="firstID" id="firstID" onchange="spfltj.firstChange(spfltj.callback,'fc')" class="sel_allmost" disabled>
                                            <option value="0" id="defaultfc" selected>无</option>
                                            <script id="flist" type="text/html">
                                                {{each list as fclass i}}
                                                <option value="{{fclass.id}}">{{fclass.name}}</option>
                                                {{/each}}
                                            </script>
                                        </select><span>--></span>
                                        <select name="secondID" id="secondID" onchange="spfltj.firstChange(spfltj.callback,'sc')" class="sel_allmost" disabled>
                                            <option value="0" id="defaultsc" selected>无</option>
                                            <script id="slist" type="text/html">
                                                {{each list as sclass i}}
                                                <option value="{{sclass.id}}">{{sclass.name}}</option>
                                                {{/each}}
                                            </script>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                    <select name="firstID" id="firstID" onchange="spfltj.firstChange(spfltj.callback, 'fc')" class="sel_allmost">
                                            <option value="0" id="defaultfc" selected>无</option>
                                            <script id="flist" type="text/html">
                                                {{each list as fclass i}}
                                                <option value="{{fclass.id}}">{{fclass.name}}</option>
                                                {{/each}}
                                            </script>
                                        </select><span>--></span>
                                        <select name="secondID" id="secondID" onchange="spfltj.firstChange(spfltj.callback, 'sc')" class="sel_allmost">
                                            <option value="0" id="defaultsc" selected>无</option>
                                            <script id="slist" type="text/html">
                                                {{each list as sclass i}}
                                                <option value="{{sclass.id}}">{{sclass.name}}</option>
                                                {{/each}}
                                            </script>
                                        </select>
                                    </c:otherwise>
                                    </c:choose>
                                </div>
                            </td>
                        </tr>
                         <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>分类排序：</td>
                            <td>
                                <input name="sort" type="text" class="text-inp-big" value="${data.sort }">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input name="Save" type="button" value="保存" class="big-but">
                                <input name="" type="button" value="取消" onclick="formCancel()" class="big-but-huise">
                            </td>
                        </tr>
                    </table>
                </div><!--zhgl-con-con  stop -->
            </form>
        </div><!--zhgl-con  stop -->
    </div><!--主要内容 右边结束 -->
</div>
