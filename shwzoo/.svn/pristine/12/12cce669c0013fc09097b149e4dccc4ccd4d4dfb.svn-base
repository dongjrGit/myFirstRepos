<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath }/resource/public/seller/js/spgl/spgl_skuimglb.js"></script>
<!--主要内容开始 -->
<div id="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：商品管理 &gt; 库存商品管理 &gt; 商品图片列表
        </div><!--所在位置信息  结束 -->
        <div class="the-form">
            <form>
                <span>
                    <label>所属库存商品：</label><input value="${sku.name }" disabled type="text" class="text-inp">
                    <input type="hidden" value="${sku.id }" id="inp_skuid" />
                </span>
                <div class="clear"></div>
                <div class="submit-but">
                    <a href="spgl_skuImgAdd?skuid=${sku.id }"><input type="button" value="添加图片" class="but-comm"></a>
                    <a href="spgl_skulist?spuid=${sku.spuId }"><input type="button" value="返回库存列表" class="but-comm"></a>
                </div>
            </form>
        </div><!--表单部分结束 -->
        <div class="clear"></div>
        <div class="thgl">
            <table class="data_list">
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>图片</th>
                        <th>排序</th>
                        <th>图片使用站点</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="list_title">
                <c:forEach var="img" items="${imglist}" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
						<td class="skuimg"><img width="300px" height="120px"
							src="<%=path %>${img.imgurl }" /></td>
						<td>${img.orderby }</td>
						<td>
						<c:if test="${img.imgsite.indexOf('1')>=0 }">
						<span>pc,</span>
						</c:if>
						<c:if test="${img.imgsite.indexOf('2')>=0 }">
						<span>app,</span>
						</c:if>
						<c:if test="${img.imgsite.indexOf('3')>=0 }">
						<span>wap</span>
						</c:if>
						</td>
						<td><a href="spgl_skuImgEdit?id=${img.id }"><span class="shenlan">编辑</span></a> 
						<a href="javascript:void(0);" class="del" data="${img.id }"><span class="shenlan">删除</span></a>
						</td>
					</tr>
				</c:forEach>
                </tbody>
            </table>
             <!-- 点击查看大图 -->
            <div id="bigimg" class="l_lbimg" style="display:none;">
                <img alt="" />
                <div class="l_close">X</div>
            </div>
            <div class="clear"></div>
            <div id="pager" class="page">

            </div><!--page stop -->
        </div>
    </div><!--主要内容 右边结束 -->
</div>
<!--主要内容结束 -->
<script type="text/javascript">
	$(document).ready(function() {
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
					url : "/seller/shopsku/delSkuImage",
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
</script>