<!-- @{
    Layout = "~/Areas/Seller/Views/Shared/_Layout_Seller_Center.cshtml";
    ViewBag.Title = "我的店铺 &gt; 店铺管理 &gt; 店铺公告管理";
} -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<link href="${ctx }/resource/public/seller/css/pager2.css" rel="stylesheet" />
<script src="${ctx }/resource/public/seller/js/pager2.js"></script>
<script src="${ctx }/resource/dialogShow.js"></script>
<script src="${ctx }/resource/public/seller/js/MyShop/ShopNotice.js?v=1.01"></script>
<div id="container">
    <div class="allcon">
        <input type="hidden" id="shopID" value="${shopid }" />
        <input type="hidden" id="editUser" value="${editUser }" />
        <div class="position">
            您所在的位置：我的店铺 &gt; 店铺管理 &gt; 店铺公告管理
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>
                <div class="clear"></div>

                <div class="submit-but">
                    <!--  <span></span> -->
                    <label>公告标题：</label><input name="" type="text" id="notTitle" class="text-inp">

                    <input name="" type="button" value="查询" id="searNotice" class="but-comm">
                    <input name="" type="button" value="清空" id="clearSearch" class="but-comm">
                    <input id="addNotice" type="button" value="新建" class="but-comm">
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="thgl">
            <table>
                <!-- <tr class="blank-tr"><td colspan="7"><div style="height:10px;"></div></td></tr> -->
                <tr id="datalist">
                    <th>标号</th>
                    <th>标题</th>
                    <th>内容</th>
                    <th>修改人</th>
                    <th>公告发布时间</th>
                    <th width="80px">操作</th>
                </tr>
                <script type="text/html" id="noticelist">
                    {{each list as n i}}
                    <tr>
                        <td>{{i+1}}</td>
                        <td>{{n.title}}</td>
                        <td>{{n.content}}</td>
                        <td>{{n.edituser}}</td>
                        <td>{{n.sendtimeStr}}</td>
                        <td class="xxbj">
                            <a href="javascript:void(0);" class="edit_notice"><input type="hidden" value="{{n.id}}" />修改</a>
                            <a href="javascript:void(0);" class="del_notice"><input type="hidden" value="{{n.id}}" />删除</a>
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </table>
        </div><!--thgl 表格部分结束 -->
        <div class="page" id="pager">

        </div><!--page stop -->

    </div><!--主要内容 右边结束 -->
</div>
<script type="text/javascript">
$(document).ready(function(){
	var s = $("#shopID").val();
	var nt = $("#notTitle").val();
	Notics.bind(s, 1, nt);
})
</script>
