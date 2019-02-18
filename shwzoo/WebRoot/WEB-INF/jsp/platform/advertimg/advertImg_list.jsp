<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"	src="/resource/public/platform/js/advert/advert_list.js"></script>
<div class="mainright">
	<!--l_wzmb  开始 -->
	<div>
		<!-- class="l_wzmb" -->
		<input type="hidden" id="id" name="id" />
		<div style="margin-top: 20px; padding: 10px;">
			<div class="members-form">
				<form>
					<div style="float: left; padding-right: 20px;"></div>
					<input type="hidden" name="mark" id="mark" value=""> <input
						type="hidden" name="position" id="position" value=""> 
						<span>页面标识：<select class="the-form-select-one" name="firstID" id="firstID" onchange="Ad.firstChange()"> 
							<option value="" id="defaultfc" selected>请选择</option>
							<script id="flist" type="text/html">
                            	{{each list as ad i}}
                                <option value="{{ad.code}}">{{ad.name}}</option>
                                {{/each}}
                            </script>
						</select>
					</span> 
					<!-- <span>显示位置：<input class="members-form-inp" id="secondID" type="text" value=""></span>  -->
						<span>状态：<select class="the-form-select-one" name="status" id="status">
							<option value="">请选择</option>
							<option value=1>启用</option>
							<option value=0>禁用</option>
								 </select></span>

					<!-- <span>页面：<select name="stype" id="stype">
                   		<option value="">无</option>
                   		<option value=1>首页</option>
                   		<option value=2>店铺</option>
                   		<option value=3>团购</option>
                   		<option value=4>分类</option>
                   </select></span>
                   <span>状态：<select name="status" id="status">
                   		<option value="">无</option>
                   		<option value=1>启用</option>
                   		<option value=0>禁用</option>
                   </select></span>
                   <span>显示位置：<input class="members-form-inp" id="position" name="position" type="text" value=""></span> -->
					<span><input class="inquire" id="searchtitle" type="button"
						value="搜索"></span> <span><a
						href="/platform/advertimg/showAdvertImgAdd" target="_self"><input
							class="inquire" name="" type="button" value="+添加广告"></a></span>
				</form>
			</div>
			<!--account-form stop -->
			<div class="member-xz" style="margin-top: 10px;">
				<!-- <span><input id="SelectAll" name="check" type="checkbox" value="">全选</span> -->
				<!-- <span><input class="inquire" id="delete_all" type="button" value="批量删除"></span>
                <span><input class="inquire" type="button" id="updAll" value="更新排序"></span> -->
			</div>
			<div class="clear"></div>
			<div class="table-con" id="divshow">
				<table class="data_list">
					<tr id="Advertdata">
						<th>页面标识</th>
						<th>标题</th>
						<th>图片</th>
						<th>状态</th>
						<th>跳转类型</th>
						<!-- <th>站点标识</th> -->
						<th>操作</th>
					</tr>
					<tbody id="datalist">
					<script id="Advertlist" type="text/html">
 						{{each list as advert i}}
                        <tr name="dataTr">
                           <input name="chk_list" type="hidden" value='{{advert.id}}'>
							{{if advert.pagemark==0}}
							<td>首页</td>
							{{else if advert.pagemark==3}}
							<td>商品列表页</td>
							{{else}}
                            <td></td>
							{{/if}}
							<td>{{advert.title}}</td>
                           <td><img src="{{advert.imag}}" style="width:80px;heigth:80px;"/></td>
							{{if advert.status==1}}
                   		 	<td>
								启用
                   			</td>
                    		{{else }}
                   			 <td>
                        	禁用
                   			 </td>
                    		{{/if}}
                            <td>{{if advert.type==1}}店铺{{else if advert.type==2}}商品{{/if}}</td>
							<td class="member-operate">
                                <span class="cxtt shenlan">编辑</span>
                                <span class="delete shenlan">删除</span>
                              {{if advert.status==1}}
                               <span class="lvs shenlan" id="span_{{advert.id}}"><a data-id="{{advert.id}}" data-status="0" class="set"  href="javascript:void(0);">禁用</a></span>
                              {{else }} 
                              <span class="lvs shenlan" id="span_{{advert.id}}"><a data-id="{{advert.id}}" data-status="1" class="set"  href="javascript:void(0);">启用</a></span>     
                               {{/if}} 
                            </td>
                        </tr>
                        {{/each}}
                    </script>
                    </tbody>
				</table>
			</div>
			<!--table-con  stop    <td>{{advert.sort}}</td>-->
			<div class="clear"></div>

			<div class="page" id="pager"></div>
			<!--page stop -->
		</div>
	</div>
	<!--l_wzmb  结束 -->
</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {
		//初始化数据
		advert.bind(1);
	})
</script>
