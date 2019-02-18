<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resource/ajaxfileupload.js"></script>
<script type="text/javascript" src="/resource/public/platform/js/member/memberadd.js"></script>
 
<script type="text/javascript" src="/resource/public/commonjs/Birthday-picker.js"></script>
<div class="mainright">
    <div class="l_wzmb">
        <div class="l_wzmbtop">
            <ul>
                <li class="sj_hover"><a href="javascript:void(0)">添加新会员</a><span class="sj-img"></span></li>
            </ul>
        </div><!--l_wzmbtop   stop -->
        <div class="tjcpxx-con-con">
            <form id="addmemberForm" action="#" method="post">
            	

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>用户名：</label></div>
                    <div class="tjcpxx-con-form1" >
                        <input class="tjcpxx-fprm-inp" name="text_username" id="text_username" type="text">  用户名不能为空 长度限制在3-16个字符之间！
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <!-- <div class="tjcpxx-con-mk1">
                        <div class="tjcpxx-con-form-title1"></div>
                        <div class="tjcpxx-con-form1" style="position:relative;">
                            <span class="beizhu-mc">用户名不能为空 长度限制在3-16个字符之间！</span>
                        </div>
                 </div> --><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>密码：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_loginpwd" id="text_loginpwd" type="password">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>确认密码：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_loginpwdagain" id="text_loginpwdagain" type="password">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label><span class="red marrig5">*</span>手机号：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_mobile" id="text_mobile" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>会员等级：</label></div>
                    <div class="tjcpxx-con-form1">
                        <select name="select_level" id="select_level" class="the-form-select-one">
                            <option value="-1">请选择</option>
							<c:forEach items="${list }" var="level">
								<option value="${level.id }">${level.name}</option>
							</c:forEach>                            
                         <!--    @foreach (UserLevelDto level in Level)
                            {
                                <option value="@level.ID">@level.Name</option>
                            }--> 
                        </select>
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1">
								<label>头像：</label>
							</div>
							<div class="tjcpxx-con-form1">
								<div class="tjcpxx-con-form-upimg">
									<img id="loadimg" width="120px" height="115px"
										src="${vo.imgurl}" />
								</div>
								<input type="hidden" name="img" value="${vo.imgurl}" />
								<div
							style="width: 200px; float: left; position: relative; padding-left: 30px;">
							<input type="button" value="选择图片" class="h_scimgbut" /> <input
								type="file" id="singlefile" name="pics" 
								class="filemhbut" 
								style="top: 10px; left: 27px;" />
							<div>
								<input type="button" value="本地上传" class="h_scimgbut h_scimgbut1 h_scimgbut2" />
							</div>
						</div>
							</div>
						</div>
						<div class="tjcpxx-con-mk1">
							<div class="tjcpxx-con-form-title1"></div>
							<div class="tjcpxx-con-form1" style="position: relative;">
								<label for="selectimg" class="error"></label> <span
									class="beizhu-mc-upimg">上传图片要小于500kb</span>
							</div>
						</div>
               <!--  <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>昵称：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_nickname" id="text_nickname" type="text">
                    </div>
                    </div>tjcpxx-con-mk stop -->
                      <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>身份证号：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_idcard" id="text_idcard" type="text">
                    </div>
                </div>
                
                
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>真实姓名：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_relname" id="text_relname" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
               
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>邮箱：</label></div>
                    <div class="tjcpxx-con-form1">
                        <input class="tjcpxx-fprm-inp" name="text_email" id="text_email" type="text">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>性别：</label></div>
                    <div class="tjcpxx-con-form1">
                        <select name="" id="select_sex" class="the-form-select-one">
                            <option value="-1" selected>请选择</option>
                            <script id="sexselect" type="text/html">
                                {{each list as value index}}
                                <option value="{{value.code}}">{{value.name}}</option>
                                {{/each}}
                            </script>
                        </select>
                    </div>
                </div><!--tjcpxx-con-mk stop -->

                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>出生日期：</label></div>
                    <!-- <div class="tjcpxx-con-form1 div_birthday">
                    </div> -->
                    <div><input type="text" id="select_date" onfocus="WdatePicker({  })" class="text_time" /></div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"><label>所在地区：</label></div>
                    <div class="tjcpxx-con-form1">
                        <select id="select_provice" class="the-form-select-one">
                            <option value="-1" selected>请选择</option>
                            <script id="proviceselect" type="text/html">
                                {{each list as Pro index}}
                                <option value="{{Pro.code}}">{{Pro.name}}</option>
                                {{/each}}
                            </script>
                        </select>
                        <select id="select_city" class="the-form-select-one">
                            <option value="-1" selected>请选择</option>
                            <script id="cityselect" type="text/html">
                                {{each list as Pro index}}
                                <option value="{{Pro.code}}">{{Pro.name}}</option>
                                {{/each}}
                            </script>
                        </select>
                        <select id="select_area" class="the-form-select-one">
                            <option value="-1" selected>请选择</option>
                            <script id="areaselect" type="text/html">
                                {{each list as Pro index}}
                                <option value="{{Pro.code}}">{{Pro.name}}</option>
                                {{/each}}
                            </script>
                        </select>
                    </div>
                </div><!--tjcpxx-con-mk stop -->
                <div class="tjcpxx-con-mk1">
                    <div class="tjcpxx-con-form-title1"></div>
                    <div class="tjcpxx-con-form1 huise" style="padding-top:20px;">
                        <input class="preserve-inp marrig35 mar35" name="submit_ok" id="submit_ok" type="submit" value="保存">
                        <input class="preserve-inp_hs" name="btn_goback" id="btn_goback" type="button" value="返回">
                    </div>
                </div><!--tjcpxx-con-mk stop -->
            </form>
        </div>
    </div><!--tjcpxx stop -->
</div><!--mainright stop -->
<script type="text/javascript">
    $(document).ready(function () {
    	//性别绑定
    	GetSelectData();
    	//地区绑定
    	BindRegion();
        //表单验证
        Vaildate.bind();
        //返回按钮点击
        $("#btn_goback").bind("click", function () {
            window.location.href = "/platform/member/showMemberList";
        });
    })
        
    $(".h_scimgbut2").click(
			function() {
				$.ajaxFileUpload({
					url : "/app/api/img/upload",
					secureuri : false,
					fileElementId : 'singlefile',
					dataType : "json",
					data : {
						relationtype : 0
					},
					type : 'POST',
					success : function(result) {
						$("input[name='img']").val(result.data);
						if (result.code == 0){
							Dalert("上传头像成功");
							$("#loadimg").attr("src",result.data[0]);
						}else {
							$("#loadimg").attr("src", "");
							Dalert(result.desc);
						}
						//TODO 结束正在加载中
					},
					error : function(e) {
						alert(JSON.stringify(e));
					}
				});
			});
</script>
