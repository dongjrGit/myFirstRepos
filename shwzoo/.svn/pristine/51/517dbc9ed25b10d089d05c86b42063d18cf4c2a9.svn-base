<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/decorators/getFileUrl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript"
	src="/resource/public/platform/js/group/group.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/resource/ajaxfileupload.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>

<div class="mainright">
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0)">查看团购商品</a><span
					class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div style="margin-top: 20px; padding: 10px;">
			<div class="table-con-detail">
				<table id="t1">
					<tbody>
						<input id="id" type="hidden" value="${gbinfo.id}">
						<tr>
							<td class="text-right" style="width:120px;">商品名称</td>
							<td>${gbinfo.title}</td>
						</tr>
						<tr>
							<td class="text-right">所属店铺</td>
							<td>${gbinfo.sname}</td>
						</tr>
						<tr>
							<td class="text-right">图片</td>
							<td><img id="loadimg" width="120px" height="115px"
								src="${gbinfo.imgurl}" /></td>
						</tr>
						<tr>
							<td class="text-right">分类</td>
							<td>${gbinfo.cname}</td>
						</tr>
						<tr>
							<td class="text-right">原价格</td>
							<td>${gbinfo.yprice}</td>
						</tr>
						<tr>
							<td class="text-right">团购价格</td>
							<td>${gbinfo.price}</td>
						</tr>
						<tr>
							<td class="text-right">参与人数</td>
							<td>${gbinfo.num}</td>
						</tr>
						<tr>
							<td class="text-right">开始时间</td>
							<td><fmt:formatDate value="${gbinfo.starttime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td class="text-right">结束时间</td>
							<td><fmt:formatDate value="${gbinfo.endtime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td class="text-right">描述</td>
							<td>${gbinfo.depict}</td>
						</tr>
						<tr>
							<td class="text-right">状态</td>
							<td><c:choose>
									<c:when test="${gbinfo.state==0}">
										<label>上架</label>
									</c:when>
									<c:otherwise>
										<label>下架</label>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<td class="text-right">审核</td>
							<td><c:choose>
									<c:when test="${gbinfo.auditing==0}">
										<label>通过</label>
									</c:when>
									<c:when test="${gbinfo.auditing==1}">
										<label>未审核</label>
									</c:when>
									<c:otherwise>
										<label>未通过</label>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<tr class="l_nobor">
							<td class="text-right">&nbsp;</td>
							<td><input class="preserve-inp_hs" name="btn_goback" id=""
								type="button" onclick="back();" value="返回"></td>
						</tr>
					</tbody>
				</table>
				<div class="table-con-detail-form"></div>
			</div>
			<!--table-con  stop -->
		</div>
	</div>
</div>
<!--tjcpxx stop -->
<script type="text/javascript">
	function back() {
		window.location.href = "/platform/group/slsit";
	}
</script>

