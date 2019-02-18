<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="clear"></div>
<div class="mainright">
	<div class="clear"></div>
	<div class="dotted mar35"></div>
	<div class="notice-fenlei">
		<div style="float: left; padding-right: 20px;">
			<h1>
				<span>活动名称：${data.spikename}</span>
			</h1>
			<input type="hidden" id="spikeid" value="${data.id}" />
		</div>
		 <a href="yxgl_ExcitingList"><input class="inquire" name="backPage"
			type="button" value="返回活动列表"></a>

	</div>
	<!--notice stop -->
	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<thead>
				<tr>
					<th width="15%">用户名</th>
					<th width="10%">手机号</th>
					<th width="15%">过期时间</th>
					<th width="8%">是否使用</th>
					<th width="15%">使用时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="datalist">
                <script id="spikelist" type="text/html">
                    {{each list as spike i}}
                    <tr>
                        <td>{{spike.username}}</td>
                        <td>{{spike.phone}}</td>
                        <td>{{spike.outtime}}</td>
                        <td>
                            {{if spike.isuse==0}}
                                                        否
                            {{else}}
                                                        是
                            {{/if}}
                        </td>
                        <td>{{spike.usetime}}</td>
                        <td id="cz_{{spike.id}}">
                         {{if spike.isout==1 && spike.isuse==0}}
                            <a href="javascript:void(0);" class="usecode" data="{{spike.id}}"><span class="shenlan">使用验证码</span></a>
                         {{/if}}
                        </td>
                    </tr>
                    {{/each}}
                </script>
            </tbody>
		</table>
		<div class="clear"></div>
		<div class="page" id="pager"></div>
		<!--page stop -->
	</div>
	<!--table-con  stop -->

</div>
<script
	src="${pageContext.request.contextPath }/resource/public/platform/js/yxgl/excitinglist.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		spike.bind();
	})
</script>