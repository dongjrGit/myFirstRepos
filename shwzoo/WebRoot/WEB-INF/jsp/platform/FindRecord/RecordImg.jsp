<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/decorators/getFileUrl.jsp"%>
 <script type="text/javascript" src="${pageContext.request.contextPath }/resource/public/platform/js/Findrecord/Findrecord.js"></script>

<div class="mainright">
    <!--l_wzmb  开始 -->
    <div ><!-- class="l_wzmb" --><input type="hidden"  id="id" name="id" />
        <div style="margin-top:20px; padding:10px;">
            <div class="members-form">
                <form>
                    <span>标题：<input class="members-form-inp" id="stitle" name="stitle" type="text" value=""></span>
                    <span><input class="inquire" id="searchtitle" type="button" value="搜索"></span>
                    <span><a href="/platform/find/showFindRecordAdd" target="_self"><input class="inquire" name="" type="button" value="+添加图片"></a></span>
                </form>
            </div><!--account-form stop -->
            <div class="member-xz" style="margin-top:10px;">
                <!-- <span><input id="SelectAll" name="check" type="checkbox" value="">全选</span> -->
                <span><input class="inquire" id="delete_all" type="button" value="批量删除"></span>
                <span><input class="inquire" type="button" id="updAll" value="更新排序"></span>
            </div>
            <div class="clear"></div>
            <div class="table-con" id="divshow">
                <table class="data_list">
                    <tr id="Finddata">
                        <th><input id="SelectAll" name="ch_All" type="checkbox" value=""></th>
                        <th>标题</th>
                        <th>图片</th>
                        <th>类型</th>
                        <th>状态</th>
                        <th>排序</th>
                        <th>操作</th>
                    </tr>
                    <script id="Findlist" type="text/html">
                        {{each list as find i}}
                        <tr name="dataTr">
                            <td><input name="chk_list" type="checkbox" value='{{find.id}}'></td>
                            <td>{{find.title}}</td>
                            <td><a href="{{find.url}}"><img src="<%=path %>{{find.imgurl}}" style="width:100px;heigth:80px;"/></a></td>
							<td>{{if find.type==1}}
								店铺动态
							{{else if find.type==2}}
								专题
							{{else if find.type==3}}
								资讯文章
							{{else if find.type==4}}
								外部链接
							{{/if}}</td>
							<td>{{if find.status==0}}禁用{{else if find.status==1}}启用 {{/if}}</td>
							<td><input class="data_list_cs" id='ob_{{find.id}}' name="" type="text" value='{{find.sort}}' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> <span class="lvs"><a href="javascript:void(0);" onclick="setOrder({{find.id}},'ob_{{find.id}}')">保存</a></span></td>
							<td>
                                <span class="cxtt shenlan">编辑</span>
                                <span class="delete shenlan">删除</span>
								<a href="javascript:void(0);" onclick="Find.edit({{find.id}})"><span class="shenlan" title="启用禁用">{{if find.status==1}}设为禁用{{else if find.status==0}}设为启用{{/if}}</span></a>
								
								{{if find.type>=1 && find.type <=3}}
									<a href="/platform/find/showFindRelateList?findid={{find.id}}&type={{find.type}}" title="发现管理"><span class="shenlan">发现管理</span></a>
								{{/if}}        
                            </td>
                        </tr>
                        {{/each}}
                    </script>
                </table>
                
                <!--  {{if find.status==1}}
                   		 	<td id="td_{{find.id}}">
                        	<span class="lvs"><a data-id="{{find.id}}" data-status="0" class="set" href="javascript:void(0);">启用</a></span>
                   			 </td>
                    		{{else }}
                   			 <td id="td_{{find.id}}">
                        	<span class="lvs"><a data-id="{{find.id}}" data-status="1" class="set" href="javascript:void(0);">禁用</a></span>
                   			 </td>
                    		{{/if}}        class="member-operate"-->
            </div><!--table-con  stop -->
            <div class="clear"></div>

            <div class="page" id="pager">


            </div><!--page stop -->
        </div>
    </div>
    <!--l_wzmb  结束 -->
</div><!--mainright stop -->
