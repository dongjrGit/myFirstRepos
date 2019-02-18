<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="${pageContext.request.contextPath}/resource/public/commonjs/showImage.js"></script>

<div class="clear"></div>
<div class="mainright">
	<div class="clear"></div>
	<div class="dotted mar35"></div>
	<div class="notice-fenlei">
		<div style="float: left; padding-right: 20px;">
			<h1>
				<span>活动名称：${spike.spikename}</span>
			</h1>
		</div>
		<a href="yxgl_SpikeImgEdit?id=${spike.id}" target="_self"><input
			class="inquire" name="" type="button" value="+添加图片"></a> <a
			href="yxgl_ExcitingList"><input class="inquire" name="backPage"
			type="button" value="返回活动列表"></a>

	</div>
	<!--notice stop -->
	<div class="clear"></div>
	<div class="mar35"></div>
	<div class="table-con">
		<table class="data_list">
			<thead>
				<tr>
					<th width="5%">序号</th>
					<th>图片</th>
					<th width="20%">排序</th>
					<th width="15%">操作</th>
				</tr>
			</thead>
			<tbody id="list_title">
				<c:forEach var="img" items="${imglist}" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
						<td class="skuimg"><img width="300px" height="120px"
							src="<%=path %>${img.imgurl }" /></td>
						<td><input class="data_list_cs" id='ob_${img.id }' name="" type="text" value='${img.sort }' onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}"> 
						<span class="lvs"><a href="javascript:void(0);" onclick="setOrder(${img.id },'ob_${img.id }')">保存</a></span>
						</td>
						<td>
						<a href="javascript:void(0);" class="del" data="${img.id }"><span class="shenlan">删除</span></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 点击查看大图 -->
		<div id="bigimg" class="l_lbimg" style="display: none;">
			<img alt="" />
			<div class="l_close">X</div>
		</div>
		<div class="clear"></div>
		<div class="page" id="pager"></div>
		<!--page stop -->
	</div>
	<!--table-con  stop -->

</div>
<!--mainright stop -->
<script type="text/javascript">
	$(document).ready(function() {
		 //点击图片查看大图
        $(".skuimg img").each(function () {
            $(this).click(function () {
                if ($(this).attr("src") != "" && $(this).attr("src") != undefined)
                    Show("bigimg", $(this).attr("src"));
            });
        });
		//关闭弹出层事件
		$(".l_close").bind("click", function() {
			$("#bigimg").hide();
		});
		$("#bigimg img").bind("click", Jump);
		$(".del").bind("click", function() {
			var $a = $(this);
			if (confirm("确认要执行此操作吗？")) {
				var id = $a.attr("data");
				$.ajax({
					url : "/platform/SpikeActivity/delimg",
					type : "Post",
					data : {
						'id' : id
					},
					dataType : "json",
					success : function(data) {
						if (data.code < 0) {
							Dalert(data.desc);
						} else {
							Dalert("删除成功！", 1000, function() {
								window.location.reload();
							})
						}
					}
				});
			}
		})
	})
	//点击大图跳转方法
	function Jump() {
		  var imgurl = $("#bigimg img").attr("src");
	        imgJump(imgurl);
	}
	//更改排序 ob-排序
	function setOrder(id, ob) {
	    var obtext = $("#" + ob).val();
	    $.ajax({
	        url: "/platform/SpikeActivity/updateImgSort",
	        type: "Post",
	        data: { 'id': id, 'sort': obtext },
	        dataType: "json",
	        success: function (data) {
	            Dalert(data.desc);
	        },
	        error: function () {
	            Dalert("修改规格排序失败");
	        }
	    });
	}
</script>