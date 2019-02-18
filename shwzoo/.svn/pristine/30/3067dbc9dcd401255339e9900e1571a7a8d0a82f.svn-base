<!-- @{
    ViewBag.Title = "文章分类";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script src="${ctx }/resource/public/platform/js/SiteManagement/NavClass.js"></script>

<div class="mainright">


    <div class="clear"></div>
    <div class="dotted mar35"></div>
    <div class="notice-fenlei">
        <div class="notice-fenlei-mk3"><span class="unwind"></span><a href="javascript:void(0);">全部展开</a></div>
        <div class="notice-fenlei-mk4"><span class="unwind-one"></span><a href="javascript:void(0);">全部收缩</a></div>
        <span class="notice-fenlei-mk2"><input class="inquire" id="addbtn" name="" type="button" value="添加"></span>
    </div><!--notice stop -->
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="list_title">
                <th width="40%">分类名称</th>
                <th width="15%">所属页面</th>
                <th width="15%">使用站点</th>
                <th width="15%">排序</th>
                <th width="20%">操作</th>
            </tr>
            <!--分类一展开   开始 -->
            <script id="classlist" type="text/html">
                {{each list as nclass i}}
                <tr>
                    <td class="li-he45 text-left"><span class="unwind-two"></span>{{nclass.classname}}</td>
                    <td width="15%">{{nclass.pageTypeName}}</td>
                    <td width="15%">{{nclass.webSetName}}</td>
					<td width="15%">{{nclass.sort}}</td>
                    <td>
                        <a href="javascript:void(0);"><span class="sctp shenlan">编辑</span></a>
                        <span class="marrig35"></span><input type="hidden" value="{{nclass.id}}">
						 <a href="javascript:void(0);"><span class="glwz shenlan"  onclick="javascript:location.href='/platform/zd/classifyAndarticle?id={{nclass.id}}&name={{nclass.classname}}'">关联文章</span></a>
                        <a href="javascript:void(0);"><span class="delete shenlan">删除</span></a>
                    </td>
                </tr>

                <!--小的一级栏目 开始 -->
                <tr style="border:none;display:none">
                    <td colspan="6" style="border:none">
                        <div class="tab-scale">
                            <table width="100%" style="width:100%">
                                {{each nclass.list as child j}}
                                <tr class="table-con-mk"  style="border:1px solid #ccc;">
                                    <td class="text-left padleft100" width="40%"><span class="unwind-two" style="margin-top:0px;margin-left:0px;"></span>{{child.classname}}</td>
                                    <td width="15%">{{child.pageTypeName}}</td>
                                    <td width="15%">{{child.webSetName}}</td>
									<td width="15%">{{child.sort}}</td>
                                    <td width="20%">
                                        <a href="javascript:void(0);"><span class="sctp shenlan">编辑</span></a>
                                        <span class="marrig35"></span><input type="hidden" value="{{child.id}}">
<a href="javascript:void(0);"><span class="glwz shenlan"  onclick="javascript:location.href='/platform/zd/classifyAndarticle?id={{child.id}}&name={{child.classname}}'">关联文章</span></a>
                                                                
<a href="javascript:void(0);"><span class="delete shenlan">删除</span></a>
                                    </td>
                                </tr>

								<tr class="table-con-mk-con" style="display: none;">
                                    <td colspan="5" style="border:none">
                                        <div class="table-con-mk">
                                            <table width="100%" style="width:100%;height:100%">
								{{each child.list as childe f}}
 								<tr >
                                    <td class="text-left padleft130" width="40%"><span class="unwind-two" style="margin-top:0px;margin-left:0px;"></span>{{childe.classname}}</td>
                                    <td width="15%">{{childe.pageTypeName}}</td>
                                    <td width="15%">{{childe.webSetName}}</td>
									<td width="15%">{{childe.sort}}</td>	
                                    <td >
                                        <a href="javascript:void(0);"><span class="sctp shenlan">编辑</span></a>
                                        <span class="marrig35"></span><input type="hidden" value="{{childe.id}}">
<a href="javascript:void(0);"><span class="glwz shenlan"  onclick="javascript:location.href='/platform/zd/classifyAndarticle?id={{childe.id}}&name={{childe.classname}}'">关联文章</span></a>
                                          
<a href="javascript:void(0);"><span class="delete shenlan">删除</span></a>
                                    </td>
                                </tr>
								{{/each}}
 									</table>
                                        </div>
                                    </td>
                                </tr>
                                {{/each}}
								

                            </table>
                        </div>
                   		 </td>
                		</tr>
                <!--分类一展开  结束 -->
                {{/each}}
            </script>
        </table>
        <div class="clear"></div>
        <div class="mar35"></div>
    </div><!--table-con  stop -->

</div><!--mainright stop -->
