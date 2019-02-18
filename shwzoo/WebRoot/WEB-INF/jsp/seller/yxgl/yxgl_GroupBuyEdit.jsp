<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="container">
    <div class="allcon">
        <div class="position">
            您所在的位置：我的店铺 &gt; 团购 &gt; 编辑团购
        </div><!--所在位置信息  结束 -->
        <div class="zhgl-con">
            <div class="zhgl-con-top">
                <div class="zhgl-con-top-title">编辑团购</div>
            </div><!--zhgl-con-top  stop -->
            <form id="form" method="post">
                <div class="zhgl-con-con">
                    <table>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>标题：</td>
                            <td>
                                <input name="title" type="text" class="text-inp-big" value="${data.title}">
                                <input name="id" type="hidden" class="text-inp-big" value="${data.id}">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>现价：</td>
                            <td>
                                <input name="cprice" type="text" class="text-inp-big" style="width:155px;" 
                                value="<fmt:formatNumber value="${data.cprice}" pattern="0.00"/>">&nbsp;元
                            </td>
                        </tr>
                         <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>原价：</td>
                            <td>
                                <input name="oprice" type="text" class="text-inp-big" style="width:155px;"
                                 value="<fmt:formatNumber value="${data.oprice}" pattern="0.00"/>">&nbsp;元
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>数量：</td>
                            <td>
                                <input name="stock" type="text" class="text-inp-big" style="width:155px;" 
                                value="${data.stock}">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>类型：</td>
                            <td>
                                <select id="type" name="type" class="sel-form" >
                                   <option value="0">代金券</option>
                                    <option value="1">打折卡</option>
                                    <option value="2">活动优惠</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>开始时间：</td>
                            <td>
                                <input type="text" name="starttime" id="starttime" class="Wdate" 
                                onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endtime\')}' })" 
                                value="${data.startstr}" readonly="readonly" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"><span class="red">*</span>过期时间：</td>
                            <td>
                                <input type="text" name="endtime" id="endtime" class="Wdate" 
                                onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'starttime\')}' })" 
                                value="${data.endstr}" readonly="readonly" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">详细页简介：</td>
                            <td>
                                <input name="detaildesc" type="text" class="text-inp-big" 
                                value="${data.detaildesc}">
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">列表页简介：</td>
                            <td>
                                <input name="listdesc" type="text" class="text-inp-big" 
                                value="${data.listdesc}">
                            </td>
                        </tr>
                        <tr>
							<td class="xjdpzzh-left"><label>内容详情：</label></td>
							<td><textarea id="content1" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="text-inp-big" name="content" type="hidden" value="${data.content}">
							</td>
						</tr>
						<tr>
							<td class="xjdpzzh-left"><label>购买须知：</label></td>
							<td><textarea id="content2" name=""
									style="width: 450px; height: 200px; visibility: hidden;"></textarea>
								<input class="text-inp-big" name="buynotes" type="hidden" value="${data.buynotes}">
							</td>
						</tr>
						 <tr>
                            <td class="xjdpzzh-left">随时退：</td>
                            <td>
                              <c:choose>
									<c:when test="${data.isanytime==true}">
										<input name='isanytime' checked type='radio' value='true'>
										<span>是</span>
										<span class='marrig35'></span>
										<input name='isanytime' type='radio' value='false'>
										<span>否</span>
									</c:when>
									<c:otherwise>
										<input name='isanytime' type='radio' value='true'>
										<span>是</span>
										<span class='marrig35'></span>
										<input name='isanytime' checked type='radio' value='false'>
										<span>否</span>
									</c:otherwise>
								</c:choose>
                         
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">支持预约：</td>
                            <td>
                             <c:choose>
									<c:when test="${data.isbook==true}">
										<input name='isbook' checked type='radio' value='true'>
										<span>是</span>
										<span class='marrig35'></span>
										<input name='isbook' type='radio' value='false'>
										<span>否</span>
									</c:when>
									<c:otherwise>
										<input name='isbook' type='radio' value='true'>
										<span>是</span>
										<span class='marrig35'></span>
										<input name='isbook' checked type='radio' value='false'>
										<span>否</span>
									</c:otherwise>
								</c:choose>
                             
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left">过期退：</td>
                            <td>
                             <c:choose>
									<c:when test="${data.isexpired==true}">
										<input name='isexpired' checked type='radio' value='true'>
										<span>是</span>
										<span class='marrig35'></span>
										<input name='isexpired' type='radio' value='false'>
										<span>否</span>
									</c:when>
									<c:otherwise>
										<input name='isexpired' type='radio' value='true'>
										<span>是</span>
										<span class='marrig35'></span>
										<input name='isexpired' checked type='radio' value='false'>
										<span>否</span>
									</c:otherwise>
								</c:choose>
                            </td>
                        </tr>
                        
                        <tr>
                        	<td class="xjdpzzh-left" ><span class="red">*</span>使用平台：</td>
                        	<td>
                        		  <c:choose>
                            <c:when test="${data.usesite != null}">
										<%-- <c:choose>
											<c:when test="${fn:indexOf(data.usesite,'1')>=0}">
												<input name="useplatform" type="checkbox" checked value="1">
												<span>pc端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="1">
												<span>pc端</span>
											</c:otherwise>
										</c:choose> --%>
										<c:choose>
											<c:when test="${fn:indexOf(data.usesite,'2')>=0}">
												<input name="useplatform" type="checkbox" checked value="2">
												<span>app端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="2">
												<span>app端</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(data.usesite,'3')>=0}">
												<input name="useplatform" type="checkbox" checked value="3">
												<span>wap端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="3">
												<span>wap端</span>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${fn:indexOf(data.usesite,'4')>=0}">
												<input name="useplatform" type="checkbox" checked value="4">
												<span>微信端</span>
											</c:when>
											<c:otherwise>
												<input name="useplatform" type="checkbox" value="4">
												<span>微信端</span>
											</c:otherwise>
										</c:choose>

									</c:when>
                            <c:otherwise>
                              <!--  <input name='useplatform' type='checkbox' value='1'><span>pc端</span>
                                <span class='marrig35'></span> -->
                                <input name='useplatform' type='checkbox' value='2'><span>app端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='3'><span>wap端</span>
                                <span class='marrig35'></span>
                                <input name='useplatform' type='checkbox' value='4'><span>微信端</span>
                            </c:otherwise>
                            </c:choose>
                        	</td>
         				</tr>
                        <tr>
                            <td class="xjdpzzh-left">&nbsp;</td>
                            <td>
                             <c:choose>
									<c:when test="${data.status==0}">
									  <input name='status' checked type='radio' value='0'><span>启用</span>
                                <span class='marrig35'></span>
                                <input name='status' type='radio' value='1'><span>禁用</span>
									</c:when>
									<c:otherwise>
										  <input name='status'  type='radio' value='0'><span>启用</span>
                                <span class='marrig35'></span>
                                <input name='status' checked type='radio' value='1'><span>禁用</span>
									</c:otherwise>
								</c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="xjdpzzh-left"></td>
                            <td>
                                <input name="Save" type="button" value="保存" class="big-but">
                                <input id="action" type="hidden" value="update">
                                <input name="" type="button" value="取消" onclick="formCancel()" class="big-but-huise">
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>
<script
	src="${pageContext.request.contextPath }/resource/public/common.js/kindeditor-4.1.10/kindeditor-min.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script src="${pageContext.request.contextPath }/resource/public/seller/js/yxgl/groupbuySave.js"></script>
<script type="text/javascript">
    function formCancel() {
        location.href = "yxgl_GroupBuyList";
    }
    $(document).ready(function() {
		var ut = ${data.type};
		$("#type").val(ut);
	});
    var editor, editor1;
	KindEditor.ready(function(K) {
		//K.create('#content1');
		//取消功能 打印，插入模板，插入代码，插入flash，插入视频，插入表情，锚点
		editor = K.create('#content1', {
			items : [ 'source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
					'paste', 'plainpaste', 'wordpaste', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', 'fullscreen', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', 'image', 'multiimage', 'insertfile',
					'table', 'hr', 'baidumap', 'pagebreak', 'link', 'unlink' ],
				uploadJson :"/app/api/img/upload?relationtype=1&iskdr=1"
		});
		editor1 = K.create('#content2', {
			items : [ 'source', 'undo', 'redo', 'preview', 'cut', 'cpoy',
					'paste', 'plainpaste', 'wordpaste', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', 'subscript', 'superscript', 'clearhtml',
					'quickformat', 'selectall', 'fullscreen', 'formatblock',
					'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', 'image', 'multiimage', 'insertfile',
					'table', 'hr', 'baidumap', 'pagebreak', 'link', 'unlink' ],
					uploadJson :"/app/api/img/upload?relationtype=1&iskdr=1"
		});
		if ($("input[name=content]").val() != ""
			&& $("input[name=content]").val() != undefined) {
		editor.html($("input[name=content]").val());
	}
	if ($("input[name=buynotes]").val() != ""
			&& $("input[name=buynotes]").val() != undefined) {
		editor1.html($("input[name=buynotes]").val());
	}
	});
</script>