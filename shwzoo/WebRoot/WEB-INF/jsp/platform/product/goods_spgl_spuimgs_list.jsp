<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/resource/public/commonjs/showImage.js"></script>
<script>
	//返回
	function fh(){
		var ht ="/platform/product/goods_dpspgl?";
		ht+="fc="+GetQueryStringByName("fc");
		ht+='&sc='+GetQueryStringByName("sc")+'&tc='+GetQueryStringByName("tc");
		ht+='&name='+GetQueryStringByName("name")+'&num='+GetQueryStringByName("num")+'&status='+GetQueryStringByName("status");
		window.location.href = ht;
	}

</script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="clear"></div>
<div class="mainright">
	<div class="clear"></div>
	<div class="dotted mar35"></div>
	<div class="notice-fenlei">
		<div style="float: left; padding-right: 20px;">
			<h1>
				<span>所属商品：${spu.name }</span>
			</h1>
		</div>
		<a href="showSpuImgEditor?spuid=${spu.id }" target="_self"><input
			class="inquire" name="" type="button" value="+添加图片"></a> <a href="javascript:fh()">
			<input class="inquire" name="backPage" type="button" value="返回商品列表"></a>

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
					<th width="10%">排序</th>
					<th width="20%">操作</th>
				</tr>
			</thead>
			<tbody id="list_title">
				<c:if test="${imglist != null and imglist.size()>0 }">
					<c:forEach items="${imglist }" var="img" varStatus="i">
						<tr>
							<td>${i.index }</td>
							<td class="skuimg"><img width="300px" height="120px"
								src="<%=path %>${img.imgurl }" /></td>
							<td>${img.orderby }</td>
							<td><a
								href="/platform/product/showSpuImgEditor?id=${img.id }&spuid=${spu.id}"><span
									class="shenlan">编辑</span></a> <a href="javascript:void(0);"
								class="del" data="${img.id }"><span class="shenlan">删除</span></a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
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
					url : "/platform/spu/delSpuImage",
					type : "Post",
					data : {
						'id' : id
					},
					dataType : "json",
					success : function(data) {
						if (data.Code < 0) {
							Dalert(data.Desc);
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
</script>
