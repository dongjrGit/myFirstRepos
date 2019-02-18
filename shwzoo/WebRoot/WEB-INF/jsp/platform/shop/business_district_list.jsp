<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="${ctx }/resource/public/platform/js/shop/business_district_list.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="member-xz">
        <span class="marrig10"></span>
        	商圈名称:<input  type="text" id="name" value="" />
             <input class="inquire" name="" type="button" id="searchBtn" value="搜索">
             <span><input class="inquire" name="" id="addbtn" type="button" value="添加商圈"></span>
    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="trlist">
                <th width="20%">商圈</th>
                <th width="20%">创建时间</th>
                <th width="30%">操作</th>
            </tr>
            <script type="text/html" id="datalist">
                {{each list as tem i}}
                <tr>                    
                    <td>{{tem.name}}</td>
                    <td>{{tem.createtimeStr}}</td>
                    <td class="zdgl-wzlbbj">
                        <span class="bjxx shenlan"><a href="javascript:void(0);">编辑</a></span>
                        <span class="delete shenlan"><a href="javascript:void(0);">删除</a></span>
						<input name="id" type="hidden" id="id" value="{{tem.id}}">
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--table-con  stop -->

    <div class="page" id="pager">

    </div><!--page stop -->


</div><!--mainright stop -->
