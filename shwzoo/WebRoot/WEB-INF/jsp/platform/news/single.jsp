<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/decorators/getFileUrl.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<div class="mainright">
<script type="text/javascript" src="${ctx}/resource/public/platform/js/news/single.js"></script>
<script src="${ctx}/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
<script src="${ctx}/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>'
<script type="text/javascript">
var editor;
/*var basepath=getRootPath();  */
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
			uploadJson :"/zoo/image/upload?relationtype=1&iskdr=1"
	});
});
</script>
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">${name}</a><span class="sj-img"></span></li>
			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con">
				<form id="form">
					<span class="marrig35"></span><input type="hidden" id="ctype" name="ctype" value=${type } type="text">
					<div class="tjcpxx-con-con">
						<%-- <input type="hidden" name="id" value="${data.id }" /> <input
							type="hidden" id="groupid" name="groupid" value="${data.groupbyid }" /> --%>
							<input type="hidden" value="${vo.id }" name="id" id="id"/>
							 <div class="tjcpxx-con-mk">
			                 <div class="tjcpxx-con-form-title">标题：</div>
			                 <div class="tjcpxx-con-form">
			                <input class="tjcpxx-fprm-inp"  name="title" type="text" value="${vo.title }">
			                 </div>
						  </div>
                        
                        <!-- <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title1"><label>内容：</label></div>
                            <div class="tjcpxx-con-form1">
                                <textarea class="tjcpxx-con-form1-text" name="description" value="">${vo.content }</textarea>
                            </div>
                        </div>
                         -->
                        
                        <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>内容：</label>
							</div>
							<div class="tjcpxx-con-form">
								<textarea id="content1" name=""
									style="width: 450px; height: 200px; visibility: hidden;">${vo.content }</textarea>
								<input class="tjcpxx-fprm-inp" name="content" type="hidden">
							</div>
						</div>
			                   
					   	<br/>
					   
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp"  type="button" value="保存" /> 
									 <input id="action" type="hidden" value="advertAdd" />
									<!-- <input class="preserve-inp_hs" name="" type="button" value="取消"
									onclick="javascript:location.href='/platform/websitesConfigView/index.html'"> -->
							</div>
						</div>
					</form>
				</div>
			</div>
			<!--tjcpxx-con stop -->
		</div>
		<!--tjcpxx stop -->
	</div>
</div>