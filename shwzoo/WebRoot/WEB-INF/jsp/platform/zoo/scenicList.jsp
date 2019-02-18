<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<style type="text/css">
/* 半透明的遮罩层 */
#overlay {
    background: #000;
    filter: alpha(opacity=50); /* IE的透明度 */
    opacity: 0.5;  /* 透明度 */
    display: none;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    height: 100%;
    z-index: 100; /* 此处的图层要大于页面 */
    display:none;
}
</style>

<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/scenic/scenic_list.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/scenic/scenic_type.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/scenic/test.js"></script>
<script type="text/javascript" src="${ctx }/resource/public/platform/js/zoo/zooCommon.js"></script>

<div class="clear"></div>
<div id="overlay"></div>
<div class="mainright">
	<div class="clear"></div>
	<div class="dotted mar35"></div>
	<div class="account-form">
		<span>景点设施名称：<input type="text" id="scenicName" class="inp-seller" /></span>
		<span>景点设施类别：</span>
        <select class="the-form-select-one" name="scenicType" id="scenicType">
            <option value="" id="defaultType" selected>请选择</option>
            <script id="typeList" type="text/html">
                {{each list as fclass i}}
                <option value="{{fclass.code}}">{{fclass.name}}</option>
                {{/each}}
            </script>
        </select>
        <span>
			区域： <select class="the-form-select-one" id="scenicArea">
				<option value="">请选择</option>
				<option value="0">步行去</option>
				<option value="1">车入区</option>
		</select>
		</span> 
      	<span class="marrig35"></span><input type="hidden" id="type" name="type" value=2 type="text">
		<span class="marrig10"></span> <input class="chaxun"
			name="select_button" type="button" value="查询" style="cursor: pointer;"
			onclick="Scenic.getAll(1)"> <span class="marrig10"></span> <input style="cursor: pointer;"
			class="chaxun" name="" onclick="add();" type="button" value="+添加景点">
	</div>
	<!--notice stop -->
	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<tr id="list_title">
				<th width="15%">景点设施名称</th>
				<th width="10%">景点设施类别</th>
				<th width="5%">区域</th>
				<th width="5%">发布状态</th>
				<th width="15%">操作</th>
			</tr>
			<script id="sceniclist" type="text/html">
                {{each list as scenic i}}
                <tr>
                    <td>{{scenic.scenicname}}</td>
					<td>{{scenic.typeName}}</td>

					{{if scenic.scenicarea==0}}
                        <td>步行区</td>
                    {{/if}}
					{{if scenic.scenicarea==1}}
                        <td>车入区</td>
                    {{/if}}					

					{{if scenic.state==0}}
                        <td>未发布</td>
                    {{else }}
                        <td>发布</td>
                    {{/if}}					
                    <td>
						{{if scenic.state==0}}
							<a href="javascript:void(0);" onclick="Scenic.updateCheck({{scenic.id}},1)" title="发布"><span class="shenlan">发布</span></a>
							<a href="${ctx }/zoo/scenic/listedit?id={{scenic.id}}" title="修改"><span class="shenlan">修改</span></a>
							<a href="javascript:void(0);" onclick="Scenic.delCheck({{scenic.id}},'{{scenic.imageid}}')"><span class="shenlan" title="删除">删除</span></a>
                    	{{else }}
							<a href="javascript:void(0);" onclick="Scenic.updateCheck({{scenic.id}},0)" title="撤销发布"><span class="shenlan">撤销发布</span></a>
                    	{{/if}}						
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
		//获取景点类别
		getScenicType();
		//获取列表信息，默认查询第一页
		Scenic.getAll(1);
		function getScenicType(){
			$.ajax({
				url : "/zoo/scenic/queryScenicType",
				type : "post",
				data : {},
				dataType : "json",
				success : function(data) {
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						var listdata = {
							list : data.data
						}
						var html = template('typeList', listdata);
						$("#defaultType").after(html);
					}
				},
				error : function() {

				}
			}); 
		}
	});
</script>
