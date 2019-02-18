var loginContent="body";
var csscon=0;
var login={
		open:function(){	
			$("#loginDiv").remove();
			var css='<link rel="stylesheet" href="/resource/wap/css/lanrenzhijia.css" media="all">';
	    	var a='<div class="theme-popover" id="loginDiv">';
				a+='<div class="theme-poptit">';
				a+='<a href="javascript:;" title="关闭" class="close" onclick="login.close()">×</a>';
				a+='<h3>中绿登录</h3>';
				a+='</div>';
				a+='<div class="theme-popbod dform">';
				a+='<form class="theme-signin" name="loginform" id="loginform"  method="post">';
				a+='<ol>';
				a+='<li><strong>用户名：</strong><input class="ipt" type="text" name="log" id="userName"  size="20" /></li>';
				a+=' <li><strong>密码：</strong><input class="ipt" type="password" id="userPwd" name="pwd"  size="20" /></li>';
				a+=' <li id="div_errormessage" class="cwtsxx"></li>'
				a+='<li><div><input class="btn btn-primary" type="button" name="sure"  value=" 登 录 " onclick="login.initLogin()"/> </div></li>';
				//<input class="btn btn-primary"  type="button" name="cancel" value="取消" onclick="login.close()"/>';
				a+=' </ol>';
				a+='</form>';
				a+=' </div>';
				a+='</div>';
				if(csscon==0){
				    $(loginContent).append(css);
				}
				csscon=1;
				$(loginContent).append(a);
				$("#loginDiv").show();
				$('.theme-popover-mask').fadeIn(100);
				$('.theme-popover').slideDown(200);
		},close:function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		},initLogin:function(){
			var userName = $("#userName").val();
	 	    var userPwd = $("#userPwd").val();
	 	    if (userName == "") {
	 	        $("#userName").focus();
	 	        $("#div_errormessage").html("请输入用户名!");
	 	        return false;
	 	    }
	 	   
	 	    if (userPwd == "") {
	 	        $("#userPwd").focus();
	 	        $("#div_errormessage").html("请输入密码!");
	 	        return false;
	 	    }
	 	    $.ajax(({
	 	        type: "post",
	 	        url: "/api/wap/userinfo/login",
	 	        dataType: "json",
	 	        data: {name: userName, 
	 	        	pwd: userPwd,
	 	        	ch: 0,
	 	        	code: 1},
	 	        success: function (rsl) {
	 	            if (rsl.code == 0) {
	 	            	var data=JSON.parse(rsl.data) ;  
	 	            	SetCookie("_u_token", data.Token);
	 					SetCookie("_u_channel", data.Channel);
	 					SetCookie("_uid", data.UserID);
	 					$('.theme-popover-mask').fadeOut(100);
	 					$('.theme-popover').slideUp(200);
	 					//cookie数据同步到数据库
                        Cart.cookietodb(Cart.getlist,Cart.getlistcallback);
	 					loginSuccess();
	 	            }else {
	 	                $("#userName").focus();
	 	                $("#div_errormessage").text(rsl.desc);
	 	            }
	 	        },
	 	        error: function (e) {
	 	        	alert("登录异常");
	 	        }
	 	    }));
		},isLogin:function(){
			var returnVal;
			$.ajax({
				type:'POST', //请求类型
				dataType: "text",
				url:'/api/wap/qiandao/isLogins',//请求位置 时间轴
				cache: false,//true，dataType 为 script 和 jsonp 时默认为 false。设置为 false 将不缓存此页面。
				async: false,//同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
				timeout:30000,//设置请求超时时间（毫秒）。此设置将覆盖全局设置。
				success: function(data){ //成功
					returnVal=data;
				},error:function(){
					alert("错误");
				}
			});
			if(parseInt(returnVal)==-401){
				return false;
			}else{
				return true;
			}
		}
		
		
};
 function loginSuccess(){
	 
 }
 