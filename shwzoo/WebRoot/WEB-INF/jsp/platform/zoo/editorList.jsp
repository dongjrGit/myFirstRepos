<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/resource/public/platform/js/zoo/editor/editor_list.js"></script>
<script type="text/javascript" src="/resource/public/platform/js/zoo/zooCommon.js"></script>
<div class="mainright">
	<!--l_wzmb  开始 -->
	<div>
		<div class="clear"></div>
		<div class="dotted mar35"></div>
			<div class="members-form">
				<form>
					<input type="hidden" id="type" value=${type}>
					<div style="float: left; padding-right: 20px;"></div>
					<span>标题：<input type="text" id="title" class="inp-seller" /></span>
					</span> <span><input class="inquire" id="searchtitle" type="button" value="搜索"></span> 
					<span>
							<input class="inquire" onclick="ZooEditor.add();" type="button" value="+添加">
					</span>
				</form>
			</div>
			<!--account-form stop -->
			<div class="clear"></div>
			<div class="table-con" id="divshow">
				<table class="data_list">
					<tr id="Advertdata">
						<th width="20%">标题</th>
						<th width="10%">创建日期</th>
						<th width="15%">创建人</th>
						<th width="20%">操作</th>
					</tr>
					<tbody id="datalist">
						<script id="bannerList" type="text/html">
 						{{each list as bean i}}
                        <tr name="dataTr">
							<td>{{bean.title}}</td>
							<td>{{bean.createTimeStr}}</td>
							<td>{{bean.creator}}</td>
							<td class="member-operate">
							  <span class="cxtt shenlan" data-id="{{bean.id}}">编辑</span>
                              <span class="delete shenlan" data-id="{{bean.id}}">删除</span>
                            </td>
                        </tr>
                        {{/each}}
                    </script>
					</tbody>
				</table>
			</div>
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
		//查询
		$("#searchtitle").bind("click",function(){
			ZooEditor.getAll(1);
		});
			
		// 删除
	    $("body").on("click", ".delete", function() {
	        var id = $(this).attr("data-id");
	        ZooEditor.delCheck(id);
	    });

	    // 编辑
	    $("body").on("click", ".cxtt", function() {
	    	var id = $(this).attr("data-id");
	        location.href = "/zoo/editor/listedit?id=" + id;
	    });
		//初始化数据
		ZooEditor.getAll(1)
	})
</script>
