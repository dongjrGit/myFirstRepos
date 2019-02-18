<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
 <div class="mainright">
 <script type="text/javascript" src="/resource/public/platform/js/websitesConfig.js">
 </script>
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <a href="javascript:void(0);" target="_self"><input class="chaxun addnew_button" onclick="javascript:location.href='${ctx}/platform/websitesConfigView/modify'" type="button" value="+添加网络配置"></a>
    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <form id="form" method="post">
            <table class="data_list">
                <thead>
                    <tr>
                        <th width="10%">公司名称</th>
                        <th width="35%">公司网站</th>
                        <th width="25%">联系电话</th>
                        <th width="20%">关键字</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="list_title">
                    <script id="searchlist" type="text/html">
                        {{each list as keyws i}}
					<tr>
						<td>{{keyws.companyName}}</td>
						<td>{{keyws.companyUrl}}</td>
						<td>{{keyws.companyTel}}</td>
						<td>{{keyws.companyKey}}</td>
						<td><a href="javascript:void(0);" onclick="initView.del({{keyws.id}})" ><span class="shenlan">删除</span></a>

<a href="javascript:void(0);" onclick="javascript:location.href='/platform/websitesConfigView/modify?id={{keyws.id}}'" ><span class="shenlan">修改</span></a>
</td>
					</tr>
                        {{/each}}
                    </script>
                </tbody>
                
                <tr id="addnew_tr" style="display:none;">
                    <td class="red">+</td>
                    <td><input type="text" id="addnew_keys" name="addnew_keys" /></td>
                    <td><input type="text" id="addnew_order" name="addnew_order" /></td>
                     <td><select id="addnew_useSites" name="addnew_useSites">
                     <option value="1">pc</option>
                     <option value="2">app</option>
                     <option value="3">wap</option>
                     </select></td>
                    <td>
                        <a href="javascript:void(0);" id="addnew_submit"><span class="shenlan" >保存</span></a>
                        <a href="javascript:void(0);" id="addnew_cancel"><span class="shenlan">取消</span></a>
                    </td>
                </tr>
            </table>
        </form>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div><!--page stop -->
    </div><!--table-con  stop -->

</div><!--mainright stop -->

<script type="text/javascript">
    $(document).ready(function () {
        Search.bind();
    })
</script>