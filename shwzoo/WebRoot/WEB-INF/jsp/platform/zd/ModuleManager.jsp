<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/SiteManagement/ModuleManager.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <a href="javascript:void(0);" target="_self"><input class="chaxun addnew_button" type="button" value="+添加模块"></a>
    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <form id="forms" method="post">
            <table class="data_list">
                <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="40%">名称</th>
                        <th width="30%">添加时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="list_title">
                    <script id="searchlist" type="text/html">
                        {{each list as keyws i}}
                        	<tr>
								<td>{{i+1}}</td>
								<td>{{keyws.name}}</td>
								<td>{{keyws.createtimetr}}</td>
								<td>
									<a href="javascript:void(0);" class="del" data="{{keyws.id}}"><span class="shenlan">删除</span></a>
								</td>
							</tr>
                        {{/each}}
                    </script>
                </tbody>
                
                <tr id="addnew_tr" style="display:none">
                	<td class="red">+</td>
                	<td><input type="text" id="addnew_name" name="addnew_name"/></td>
                	<td></td>
                	<td>
                		<a href="javascript:void(0);" id="addnew_submit"><span class="shenlan">保存</span></a>
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




