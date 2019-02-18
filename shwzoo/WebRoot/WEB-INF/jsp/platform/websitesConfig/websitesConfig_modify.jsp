<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<div class="mainright">
<script type="text/javascript" src="/resource/public/platform/js/websitesConfig.js"></script>
	<div class="l_wzmb">
		<div class="l_wzmbtop">
			<ul>
				<li class="sj_hover"><a href="javascript:void(0);">添加网站配置</a><span
					class="sj-img"></span></li>

			</ul>
		</div>
		<!--l_wzmbtop   stop -->
		<div class="tjcpxx">
			<div class="tjcpxx-con">
					<form id="form">
				<div class="tjcpxx-con-con">
						<%-- <input type="hidden" name="id" value="${data.id }" /> <input
							type="hidden" id="groupid" name="groupid" value="${data.groupbyid }" /> --%>
							<input type="hidden" value="${vo.id }" name="id"/>
							 <div class="tjcpxx-con-mk">
			                 <div class="tjcpxx-con-form-title">公司名称：</div>
			                 <div class="tjcpxx-con-form">
			                <input class="tjcpxx-fprm-inp"  name="name" type="text"value="${vo.companyName }">
			                 </div>
						  </div>
			                   
			                   
			                <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>公司网站：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp"  name="url" type="text"
									value="${vo.companyUrl }">
							</div>
						    </div>
						  
							<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>备案号：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp" name="forRecord" type="text"
									value="${vo.forRecord }">
							</div>
						    </div>
						    
						    <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>地址：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp"  name="address" type="text"
									value="${vo.companyAddr }">
							</div>
						    </div>
						    
						    <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>联系电话：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp"  name="tel" type="text"
									value="${vo.companyTel }">
							</div>
						    </div>
						    
						     <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>邮箱：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp"  name="email" type="text"
									value="${vo.companyEmail }">
							</div>
						    </div>
						    
						     
						    <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>公司关键字：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp"  name="key" type="text"
									value="${vo.companyKey }">
							</div>
						    </div>
						    
						    
						     <div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title">
								<label>描述：</label>
							</div>
							<div class="tjcpxx-con-form">
								<input class="tjcpxx-fprm-inp"  name="desc" type="text"
									value="${vo.desc }">
							</div>
						    </div>
						    
						    
					   	<br/>
					   
						</div>
						<div class="tjcpxx-con-mk">
							<div class="tjcpxx-con-form-title"></div>
							<div class="tjcpxx-con-form huise">
								<input class="preserve-inp"  type="button" value="保存" onclick="initView.Modify()" /> 
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