<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/news/news_class.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/news/mapnewsimg.js"></script>
<div class="clear"></div>
<div class="mainright">
	<div class="clear"></div>
	<div class="dotted mar35"></div>
	<div class="account-form">
		<span>标题：<input type="text" id="name_select" class="inp-seller" /></span>
		<span>新闻分类：</span>
        <input type="hidden" value="" id="fid" />
        <input type="hidden" value="" id="sid" />
        <input type="hidden" value="" id="tid" />
        <select class="the-form-select-one" name="firstID" id="firstID" onchange="Class.firstChange()">
            <option value="0" id="defaultfc" selected>无</option>
            <script id="flist" type="text/html">
                {{each list as fclass i}}
                <option value="{{fclass.id}}">{{fclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" name="secondID" id="secondID" onchange="Class.secondChange()">
            <option value="0" id="defaultsc" selected>无</option>
            <script id="slist" type="text/html">
                {{each list as sclass i}}
                <option value="{{sclass.id}}">{{sclass.name}}</option>
                {{/each}}
            </script>
        </select>-->
        <select class="the-form-select-one" name="thirdID" id="thirdID">
            <option value="0" id="defaulttc" selected>无</option>
            <script id="tlist" type="text/html">
                {{each list as tclass i}}
                <option value="{{tclass.id}}">{{tclass.name}}</option>
                {{/each}}
            </script>
        </select>
        <td class="text-right">所在地：</td>
			<select id="select_province" name="select_province"
				class="the-form-select-one">
				<option value="-1">请选择</option>
				<script id="proviceselect" type="text/html">
                {{each list as pro index}}
                <option value="{{pro.code}}">{{pro.name}}</option>
                {{/each}}
                </script>
			</select> --><select id="select_city" name="select_city" class="the-form-select-one">
				<option value="-1">请选择</option>
				<script id="cityselect" type="text/html">
                {{each list as pro index}}
                <option value="{{pro.code}}">{{pro.name}}</option>
                {{/each}}
                </script>
			</select> --><select id="select_area" name="select_area" class="the-form-select-one">
				<option value="-1">请选择</option>
				<script id="areaselect" type="text/html">
                {{each list as pro index}}
                <option value="{{pro.code}}">{{pro.name}}</option>
                {{/each}}
                </script>
			</select>
        <br />
        <span>
			发布状态： <select class="the-form-select-one" id="type_select">
				<option value="">全部</option>
				<option value="0">发布</option>
				<option value="1">未发布</option>
		</select>
		</span> 
        <span>
          <span>发布日期：</span>
          <input type="text" id="select_begin" class="Wdate" onfocus="WdatePicker({ maxDate: '#F{$dp.$D(\'select_end\')}' })"  />
          - <input type="text" id="select_end" class="Wdate" onfocus="WdatePicker({ minDate: '#F{$dp.$D(\'select_begin\')}' })"  />
      	</span>
      	<span class="marrig35"></span><input type="hidden" id="type" name="type" value=${type } type="text">
      	<input type="hidden" id="ctype" name="ctype" value="${ctype }" type="text">
		<span class="marrig10"></span> <input class="chaxun"
			name="select_button" type="button" value="查询"
			onclick="Brand.getAll(1)"> <span class="marrig10"></span> <input
			class="chaxun" name="" onclick="add();" type="button" value="+添加图片地图新闻">
	</div>
	<!--notice stop -->
	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="list_title">
				<th>标题</th>
				<th width="10%">图片</th>
				<th width="10%">发布状态</th>
				<th width="30%">操作</th>
			</tr>
			<script id="brandlist" type="text/html">
                {{each list as brand i}}
                <tr>
                    <td>{{brand.title}}</td>
					<td class="ppimg"><img src="<%=path %>{{brand.imgurl}}" width="50px" height="25px"></td>
					{{if brand.state==0}}
                    <td id="td_{{brand.id}}">
                        <span class="lvs"><a data-id="{{brand.id}}" data-status="1" onclick="setstate({{brand.id}},1)" class="set" href="javascript:void(0);">发布</a></span>
                    </td>
                    {{else }}
                    <td id="td_{{brand.id}}">
                        <span class="lvs"><a data-id="{{brand.id}}" data-status="0" onclick="setstate({{brand.id}},0)" class="set" href="javascript:void(0);">未发布</a></span>
                    </td>
                    {{/if}}
                    <td>
						{{if brand.ishead==0}}
                        	<span class="lvs" id="tt_{{brand.id}}"><a data-id="{{brand.id}}" data-status="1" onclick="sethead({{brand.id}},1)" class="set" href="javascript:void(0);">取消头条</a></span>
                    	{{else }}
                        	<span class="lvs" id="tt_{{brand.id}}"><a data-id="{{brand.id}}" data-status="0" onclick="sethead({{brand.id}},0)" class="set" href="javascript:void(0);">设置头条</a></span>
                   		{{/if}}
						{{if brand.isindex==0}}
                        	<span class="lvs" id="sy_{{brand.id}}"><a data-id="{{brand.id}}" data-status="1" onclick="setindex({{brand.id}},1)" class="set" href="javascript:void(0);">取消首页</a></span>
                    	{{else }}
                        	<span class="lvs" id="sy_{{brand.id}}"><a data-id="{{brand.id}}" data-status="0" onclick="setindex({{brand.id}},0)" class="set" href="javascript:void(0);">设置首页</a></span>
                   		{{/if}}
						{{if brand.isrecommend==0}}
                        	<span class="lvs" id="tj_{{brand.id}}"><a data-id="{{brand.id}}" data-status="1" onclick="setrecommend({{brand.id}},1)" class="set" href="javascript:void(0);">取消推荐</a></span>
                    	{{else }}
                        	<span class="lvs" id="tj_{{brand.id}}"><a data-id="{{brand.id}}" data-status="0" onclick="setrecommend({{brand.id}},0)" class="set" href="javascript:void(0);">设置推荐</a></span>
                   		{{/if}}
						{{if brand.istop==0}}
                        	<span class="lvs" id="zd_{{brand.id}}"><a data-id="{{brand.id}}" data-status="1" onclick="settop({{brand.id}},1)" class="set" href="javascript:void(0);">取消置顶</a></span>
                    	{{else }}
                        	<span class="lvs" id="zd_{{brand.id}}"><a data-id="{{brand.id}}" data-status="0" onclick="settop({{brand.id}},0)" class="set" href="javascript:void(0);">设置置顶</a></span>
                   		{{/if}}
						<a href="${ctx }/platform/news/mapimgedit?id={{brand.id}}&type={{brand.ctype}}&ctype=${ctype}" title="修改"><span class="shenlan">修改</span></a>
                        <a href="javascript:void(0);" onclick="Brand.del({{brand.id}})"><span class="shenlan" title="删除">删除</span></a>
                    </td>
                </tr>
                {{/each}}
            </script>
		</table>
		
		<div class="clear"></div>
		<div id="pager" class="page"></div>
		<!--page stop -->
	</div>
	<!--table-con  stop -->

</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {
		getFirstClass();
		BindRegion();
		Brand.getAll(1);
		function getFirstClass(){
			var ctype = $("#ctype").val();
			$.ajax({
				url : "/platform/news/queryClassSub",
				type : "post",
				data : {
					'pid' : 0,
					"ctype" : ctype
				},
				dataType : "json",
				success : function(data) {
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						var listdata = {
							list : data.data
						}
						var html = template('flist', listdata);
						$("#defaultfc").after(html);
					}
				},
				error : function() {

				}
			});
		}
	})
</script>