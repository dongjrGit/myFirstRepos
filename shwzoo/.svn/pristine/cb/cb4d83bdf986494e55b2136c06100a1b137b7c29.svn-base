<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/SiteManagement/classify_artcle.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<div class="clear"></div>
<div class="mainright">
<input type="hidden" id="valueId" value="${id }"/>
    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="account-form">
        <a href="javascript:void(0);" target="_self"><input class="chaxun addnew_button" type="button" value="+添加关联文章"></a>
    </div><!--notice stop -->

    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <thead>
                <tr>
                    <th>序号</th>
                    <th>文章名称</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="list_title">
                <script id="searchlist" type="text/html">
                    {{each list as attr i}}
                    <tr>
                        <td>{{i+1}}</td>
						<td> {{attr.articleTitle}}</td>
                        <td>
                            <a href="javascript:void(0);" class="del" ><span class="shenlan"  onclick="util.del({{attr.id}})">删除</span></a>
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
            <tr id="addnew_tr" style="display:none;">
                <td class="red">+</td>
                <td>
                <input value="${id }" type="hidden" name="classfyId" id="classfyId"/>
                <input value="${name }" type="hidden"  name="classfyName" id="classfyName"/>
                 <select class="select2" name="article" id="article" style="width: 50%">
                 <c:forEach var="vo" items="${wz }">
					<option value="${vo.id }">${vo.title }</option>                 
                 </c:forEach>
                 </select>	
                </td>
                <td>
                    <a href="javascript:void(0);" id="addnew_submit"><span >保存</span></a>
                </td>
            </tr>
        </table>
        <div class="clear"></div>
        <div id="pager" class="page">

        </div><!--page stop -->
        <div class="clear"></div>
        <div class="mar35"></div>
        <div class="sctp-con">
           
            <div class="clear"></div>
            <div class="preserve">
                <input class="preserve-inp" name="" type="button" value="保存">
            </div><!--preserve stop -->
            <div class="close">X</div>
        </div><!-- sctp-con stop-->
    </div><!--table-con  stop -->

</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
        Search.bind(Search.callback);
    })
</script>
