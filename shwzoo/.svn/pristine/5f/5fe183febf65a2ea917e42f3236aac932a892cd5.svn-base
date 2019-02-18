<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="${ctx }/resource/public/platform/js/ControlPanel/lottery.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">中奖名单</a><span class="sj-img"></span></li>

			</ul>
		</div>
    <div class="table-con">
        <table class="data_list">
            <tr id="trlist">
                <th width="20%">中奖人</th>
                <th width="20%">手机号</th>
                <th width="20%">描述</th>
                <th width="20%">时间</th>
                <th width="30%">操作</th>
            </tr>
            <script type="text/html" id="datalist">
                {{each list as tem i}}
                <tr>                    
                    <td>{{tem.username}}</td>
                    <td>{{tem.mobile}}</td>
                    <td>{{tem.description}}</td>
                    <td>{{tem.createtimetr}}</td>
                    <td class="zdgl-wzlbbj">
                        <span class="delete shenlan"><a href="javascript:void(0);">删除</a></span>
						<input name=""id"" type="hidden" id="id" value="{{tem.id}}">
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>
    </div><!--table-con  stop -->

    <div class="page" id="pager">

    </div><!--page stop -->


</div><!--mainright stop -->
