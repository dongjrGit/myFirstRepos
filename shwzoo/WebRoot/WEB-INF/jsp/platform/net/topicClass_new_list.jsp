<!-- @{
    ViewBag.Title = "专题管理";
    Layout = "~/Areas/Platform/Views/Shared/_Layout_iframe_conent_v11.cshtml";
} -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script src="${ctx }/resource/public/commonjs/showImage.js"></script>
<script src="${ctx }/resource/public/platform/js/SY/SubSpecials_new.js"></script>
<div class="mainright">
<input type="hidden" id="imgsrc" value="<%=path %>" />
    <div class="account-form">
        <span>专题名称：<input type="text" id="spname" class="inp-seller" /></span>
        <span class="marrig10"></span>
       <!--  <span>显示位置：<input type="text" id="spdisplay" class="inp-seller" /></span>
        <span class="marrig10"></span> -->
      <!--   <span>
            页面标识：
            <select id="spmark" class="the-form-select-one">
                <option value="0">所有</option>
                <option value="1">精彩专题</option>
                <option value="2">发现好店</option>
                <option value="3">新品上市</option>   
                <option value="4">发现好货</option>        
            </select>
        </span> -->
        <input type="hidden" id="topicid" value="${topicid}">
         <input type="hidden" id="type" value="${type}"> 
         
        <span class="marrig10"></span>
        <span class="notice-fenlei-mk2"><input class="chaxun" name="select_button" type="button" value="查询"></span>
        <a href="/platform/topic/showSonAdd?topicid=${topicid}&href=${href}" target="_self"><input class="chaxun" name="add" type="button" value="+添加子专题信息"></a>
       <!--  <a href="javascript:;" target="_self"><input class="chaxun" name="add" type="button" value="+添加关联专题信息"></a> -->
        <a href="javascript:;" target="_self"><input class="chaxun" name="back" type="button" value="返回"></a>
    </div>
    <div class="clear"></div>
    <div class="mar35"></div>
    <div class="table-con">
        <table class="data_list">
            <tr id="list_title">
                <th>专题类型</th>
                <th>专题标题</th>
                <th>图片</th>
                <th>楼层</th>
                <th>状态</th>
                <th>排序</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
               
           	 
            <script id="speciallist" type="text/html">
                {{each list as sp i}}
                <tr>
                     <td>{{if sp.type==1}}子专题
							{{else}} {{if sp.type==2}}店铺
							{{else}} {{if sp.type==3}}商品
									{{else}} {{if sp.type==4}}品牌
										{{else}} {{if sp.type==5}}分类
												{{/if}}
											{{/if}}
									{{/if}}
							{{/if}}
						{{/if}}
					</td>
                    <td>{{sp.title}}</td>
                    <td class="ppimg"><img src="<%=path %>{{sp.imgurl}}" width="100px" height="80px"></td>
					<td>{{sp.floorname}}</td>
					<td>{{if sp.status==1}}禁用{{else if sp.status==0}}启用 {{/if}}</td> 
                    <td><input class="data_list_cs" id='ob_{{sp.id}}' name="" type="text" value='{{sp.sort}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setValue({{sp.id}},'ob_{{sp.id}}',1)">保存</a></span></td>
                    <td>{{sp.description}}</td>
                    <td>                       
                        <a href="/platform/topic/showProList?topicid={{sp.id}}&spmark={{sp.mark}}&fatherid={{sp.fatherid}}&type={{sp.type}}" title="专题管理"><span class="shenlan">专题管理</span></a>                       
                        <a href="/platform/topic/showSubEdit?id={{sp.id}}&fid={{sp.fatherid}}"><span class="shenlan" title="修改">修改</span></a>
                        <a href="javascript:void(0);" onclick="Special.del({{sp.id}})"><span class="shenlan" title="删除">删除</span></a>
						<a href="javascript:void(0);" onclick="Special.edit({{sp.id}})"><span class="shenlan" title="启用禁用">{{if sp.status==1}}设为启用{{else if sp.status==0}}设为禁用{{/if}}</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
        </table>

    </div><!--page stop -->
    <div class="clear"></div>
    <div id="bigimg" class="l_lbimg" style="display:none;">
        <img alt="" />
        <div class="l_close">X</div>
    </div>
    <div class="clear"></div>
    <div id="pager" class="page">

    </div>
    <div class="clear"></div>
    <div class="mar35"></div>

</div><!--mainright stop -->

