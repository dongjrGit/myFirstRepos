<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/decorators/getFileUrl.jsp"%>
 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/navigation/navigation_list.js"></script>

<div class="mainright">
    <!--l_wzmb  开始 -->
    <div ><!-- class="l_wzmb" --><input type="hidden"  id="id" name="id" />
        <div style="margin-top:20px; padding:10px;">
            <div class="members-form">
                <form>
                	<div style="float: left; padding-right: 20px;">
				   <input type="hidden" name="imgsrc" id="imgsrc" value="<%=path %>" />				
					</div>
					<span>标题：<input type="text" name="title" id="title" value="" />
					</span>
			                   <span>状态：<select class="the-form-select-one" name="status" id="status">
                   			<option value="-1">无</option>
                   			<option value=1>启用</option>
                   			<option value=0>禁用</option>
                   			</select></span>
			      
                    <span><input class="inquire" id="searchtitle" type="button" value="搜索"></span>
                    <span><a href="/platform/navigation/showNavigationEdit" target="_self"><input class="inquire" name="" type="button" value="+添加导航"></a></span>
                </form>
            </div><!--account-form stop -->
            <div class="member-xz" style="margin-top:10px;">
            </div>
            <div class="clear"></div>
            <div class="table-con" id="divshow">
                <table class="data_list">
                    <tr id="navigationdata">
                        <!-- <th><input id="SelectAll" name="ch_All" type="checkbox" value=""></th> -->
                        <th>标题</th>
                        <th>图片</th>
                        <th>状态</th>
                        <th>排序</th>
                        <th>操作</th>
                    </tr>  <!-- id="td_{{nav.id}}" -->
                    <tbody id="datalist">
                    <script id="navigationlist" type="text/html">
 						{{each list as nav i}}
                        <tr name="dataTr">
                           <input name="chk_list" type="hidden" value='{{nav.id}}'>
							<td>{{nav.title}}</td>
                           <td><img src="<%=path %>{{nav.img}}" style="width:150px;heigth:150px;"/></td>
                            <td id="status_{{nav.id}}">
                            {{if nav.status==1}}
                            <span class="lvs">启用</span>
                            {{else }}
                            <span class="lvs">禁用</span>
                           {{/if}}
                           </td>
							<td>{{nav.sort}}</td>
                           
							<td class="member-operate">
                                <span class="cxtt shenlan">编辑</span>
                                <span class="delete shenlan">删除</span>
                               {{if nav.status==1}}
                               <span class="lvs" id="span_{{nav.id}}"><a data-id="{{nav.id}}" data-status="0" class="set"  href="javascript:void(0);">禁用</a></span>
                              {{else }} 
                              <span class="lvs" id="span_{{nav.id}}"><a data-id="{{nav.id}}" data-status="1" class="set"  href="javascript:void(0);">启用</a></span>     
                               {{/if}}         
                         </td>

                          
                        </tr>
                        {{/each}}
                    </script>
                  </tbody>
                </table>
            </div><!--table-con  stop -->
            <div class="clear"></div>

            <div class="page" id="pager">


            </div><!--page stop -->
        </div>
    </div>
    <!--l_wzmb  结束 -->
</div><!--mainright stop -->
