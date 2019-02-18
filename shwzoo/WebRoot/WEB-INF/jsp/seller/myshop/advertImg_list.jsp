<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<script type="text/javascript"	src="/resource/public/seller/js/MyShop/advert_list.js"></script>
<!--主要内容开始 -->
<div id="container">
	<div class="allcon">
		<div class="position"> 您所在的位置：我的店铺 &gt; 店铺管理 &gt; 广告管理</div>
		<!--所在位置信息  结束 -->
		<div class="the-form">
			<div class="members-form">
				<form>
				<input type="hidden" id="id" name="id" />
					<div style="float: left; padding-right: 20px;"></div>
					<input type="hidden" name="mark" id="mark" value=""> <input
						type="hidden" name="position" id="position" value=""> <span>页面标识：
						<select class="the-form-select-one" name="firstID" id="firstID" onchange="Ad.firstChange()">
							<option value="" id="defaultfc" selected>无</option>
							<option value="2">店铺页</option>
						</select>
					</span> <!-- <span>显示位置：<input class="text-inp" id="secondID"
						type="text" value=""></span> --> <span>状态：<select
						class="the-form-select-one" name="status" id="status">
							<option value="">无</option>
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
					<span><input class="but-comm" id="searchtitle" type="button"
						value="搜索"></span> <span><a
						href="/seller/shop/showAdvertImgAdd" target="_self"><input
							class="but-comm" name="" type="button" value="+添加广告"></a></span>
				</form>
			</div>
			<!--account-form stop -->
			<div class="member-xz" style="margin-top: 10px;">
				<!-- <span><input id="SelectAll" name="check" type="checkbox" value="">全选</span> -->
				<!-- <span><input class="inquire" id="delete_all" type="button" value="批量删除"></span>
                <span><input class="inquire" type="button" id="updAll" value="更新排序"></span> -->
			</div>
			<div class="thgl" id="divshow">
				<table>
					<tr id="Advertdata">
						<!-- <th><input id="SelectAll" name="ch_All" type="checkbox" value=""></th> -->
						<th>页面标识</th>
						<th>标题</th>
						<th>图片</th>
						<th>状态</th>
						<th>显示位置</th>
						<th>站点标识</th>
						<th>操作</th>
					</tr>
					<script id="Advertlist" type="text/html">
 						{{each list as advert i}}
                        <tr name="dataTr">
                           <input name="chk_list" type="hidden" value='{{advert.id}}'>
							{{if advert.pagemark==0}}
							<td>首页</td>
							{{else if advert.pagemark==1}}
							<td>专题页</td>
							{{else if advert.pagemark==2}}
							<td>店铺页</td>
							{{else if advert.pagemark==3}}
							<td>商品列表页</td>
							{{else if advert.pagemark==4}}
							<td>购物车成功页</td>
							{{else if advert.pagemark==5}}
							<td>购物车页</td>
							{{else if advert.pagemark==6}}
							<td>支付成功页</td>
							{{else if advert.pagemark==7}}
							<td>商品详情页</td>
							{{else if advert.pagemark==8}}
							<td>优惠卷领取页</td>
							{{else if advert.pagemark==10}}
							<td>支付失败页</td>
							{{else if advert.pagemark==11}}
							<td>店铺商品列表页</td>
                             {{else if advert.pagemark==12}}
                            <td>抢购活动</td>
							{{else if advert.pagemark==13}}
                            <td>团购活动</td>
							{{else if advert.pagemark==14}}
                            <td>绿色中国首页</td>
							{{else if advert.pagemark==15}}
                            <td>绿色中国地方官首页</td>
                            {{else if advert.pagemark==16}}
                            <td>发现首页</td>
                            {{else if advert.pagemark==17}}
                            <td>中绿味道</td>
                            {{else if advert.pagemark==18}}
                            <td>包邮直送</td>
							{{else if advert.pagemark==19}} 
 							<td>每日鲜</td>
							{{else if advert.pagemark==20}} 
 							<td>秒杀页</td>
							{{else if advert.pagemark==21}} 
 							<td>闪购页</td>
							{{else if advert.pagemark==22}} 
 							<td>新品尝鲜</td>
							{{else if advert.pagemark==23}} 
 							<td>值得购</td>
							{{else if advert.pagemark==24}} 
 							<td>独家品牌</td>
							{{else if advert.pagemark==25}} 
 							<td>每日鲜PC</td>
							{{else if advert.pagemark==26}} 
 							<td>包邮直送PC</td>
							{{else if advert.pagemark==27}} 
 							<td>优惠券PC</td>
							{{else if advert.pagemark==28}} 
							<td>APP分类广告</td>
							{{else if advert.pagemark==29}} 
							<td>发现好店</td>
							{{else if advert.pagemark==30}} 
							<td>店招</td>
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
                            <td>{{advert.display}}</td>
                            <td>{{if advert.webSet==1}}pc{{else if advert.webSet==2}}app{{else if advert.webSet==3}}wap{{else if advert.webSet==4}}wechat{{/if}}</td>
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
