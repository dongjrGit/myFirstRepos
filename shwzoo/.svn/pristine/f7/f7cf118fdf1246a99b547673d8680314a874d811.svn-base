<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>

<script src="${ctx }/resource/public/platform/js/shop/shopApplyList.js"></script>
<div class="mainright">
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="member-xz">
        <span class="marrig10"></span>
        	联系人名字:<input  type="text" id="contactsname" value="" />
        	联系人电话:<input  type="text" id="mobile" value="" />
        	店铺名称:<input  type="text" id="shopname" value="" />
             <input class="inquire" name="" type="button" id="searchBtn" value="搜索">
             <!-- <span><input class="inquire" name="" id="addbtn" type="button" value="添加"></span> -->
    </div>
    <div class="table-con">
        <table class="data_list">
            <tr id="trlist">
                <th >联系人名字</th>
                <th >联系人电话</th>
                <th >店铺名称</th>
                <th >地址</th>
                <th >状态</th>
                <th >操作</th>
            </tr>
            <script type="text/html" id="datalist">
                {{each list as tem i}}
                <tr>                    
                    <td>{{tem.contactsname}}</td>
                    <td>{{tem.mobile}}</td>
                    <td>{{tem.shopname}}</td>
                    <td>{{tem.address}}</td>
					{{if tem.iscontact==0}}
							<td>未联系</td>
                    {{else }}
							<td>已联系</td>
                     {{/if}}
                    <td class="zdgl-wzlbbj">
                        <span class="delete shenlan"><a href="javascript:void(0);">删除</a></span>
						{{if tem.iscontact==0}}
                               <span class="shenlan"><a id="a_{{tem.id}}" href="javascript:void(0);" onclick="setStatus({{tem.id}})">已联系</a></span>
                        {{/if}}
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
