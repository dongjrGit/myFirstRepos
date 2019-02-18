<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/decorators/getFileUrl.jsp"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0);">添加积分商品</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx">
            <div class="tjcpxx-con">
                <div class="tjcpxx-con-con">
                    <form id="form" method="post">
                    <input type="hidden" value="${vo.id }" id="id" name="id" />
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label><span class="red marrig5">*</span>商品名称：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="name" class="tjcpxx-fprm-inp" name="name" type="text" value="${vo.name }">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>短标题：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="sname" class="tjcpxx-fprm-inp" name="sname" type="text" value="${vo.sname }">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>原价格：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="price" class="tjcpxx-fprm-inp" name="price" type="text" value="${vo.price }">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>所需积分：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="integral" class="tjcpxx-fprm-inp" name="integral" type="text" value="${vo.integral }">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                    	<div class="tjcpxx-con-form-title"><label>会员等级：</label></div>
                    	<div class="tjcpxx-con-form1">
	                        <select name="select_level" id="select_level" class="the-form-select-one">
	                            <option value="-1">请选择</option>
								<c:forEach var="s" items="${list}">
										<c:choose>
											<c:when test="${s.id == vo.userlevel}">
												<option value="${s.id}" selected="selected">${s.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${s.id}">${s.name}</option>
											</c:otherwise>
										</c:choose>
								</c:forEach>                       
	                        </select>
                    	</div>
                		</div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>包装：</label></div>
                            <div class="tjcpxx-con-form1">
                                <textarea rows="4" cols="43" id="packing" name="packing">${vo.packing }</textarea>
                            </div>
                        </div>
                        </br>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>库存：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="stock" class="tjcpxx-fprm-inp" name="stock" type="text" value="${vo.stock }">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>兑换次数：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="count" class="tjcpxx-fprm-inp" name="count" type="text" value="${vo.count }">
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><label>兑换数量：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input id="count1" class="tjcpxx-fprm-inp" name="count1" type="text" value="${vo.count1 }">
                            </div>
                        </div>
                        <div id="divdetail" class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"><label>描述：</label></div>
                            <div class="tjcpxx-con-form">
                                <div class="tjcpxx-con-form">
								<textarea id="content1" name=""
									style="width: 450px; height: 200px; visibility: hidden;">${vo.content }</textarea>
								<input class="tjcpxx-fprm-inp" name="detaildesc" type="hidden">
							</div>
                            </div>
                        </div>
                        </br>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span><label>开始时间：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input type="text" name="start" id="start" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', maxDate: '#F{$dp.$D(\'end\')}' })" value="${vo.endtimetr }" readonly="readonly" />
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk1">
                            <div class="tjcpxx-con-form-title"><span class="red marrig5">*</span><label>结束时间：</label></div>
                            <div class="tjcpxx-con-form1">
                                <input type="text" name="end" id="end" class="Wdate" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd HH:mm:00', minDate: '#F{$dp.$D(\'start\')}' })" value="${vo.starttimetr }" readonly="readonly" />
                            </div>
                        </div>
                        <div class="tjcpxx-con-mk">
                            <div class="tjcpxx-con-form-title"></div>
                            <div class="tjcpxx-con-form huise">
                                <input class="preserve-inp" name="Save" type="button" value="保存">
                                <input id="action" type="hidden" value="add">
                                <span class="marrig35"></span>
                                <input class="preserve-inp_hs" name="" type="button" value="取消" onclick="formCancel()">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div><!--tjcpxx-con stop -->
    </div>
</div>
<script src="${pageContext.request.contextPath }/resource/public/platform/js/integraproduct/edit.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/kindeditor-min.js"></script>
<script
	src="${pageContext.request.contextPath }/resource/public/commonjs/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script>
var editor;
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
