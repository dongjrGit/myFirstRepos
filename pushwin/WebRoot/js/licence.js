// JavaScript Document
//页面加载后显示
	function load(){
		var loginType = document.getElementById("loginType").value;
		var mac = document.getElementById("mac");
		var dtm = document.getElementById("dtm");
		var yzm = document.getElementById("yzm");
		var yhm = document.getElementById("yhm");
		var pwd = document.getElementById("pwd");
		var phone = document.getElementById("phone");
		var ts  = document.getElementById("ts");
		if(loginType == "1")
		{
			 dtm.style.display = "block";
			 phone.style.display = "block";
			 ts.style.display = "block";
			 
		}else (loginType == "0")
		{
			 yzm.style.display = "block"; 
			 yhm.style.display = "block"; 
			 pwd.style.display = "block";
		}
	}
	
	//校验加密 
	//首页加密校验
	//暂时关闭
	function md5()
	{
		var licence = document.getElementById("t4ui_endText").innerText;
		jQuery.ajax({
            url: "userlicenceMd5.do",
            data: { licence:licence },
            type: "post",
            success: function (text) {
            	if(text == "")
            	{
            		alert("页面被篡改,请联系服务商"); 
            		window.location.href = "tamper_error.html";
            	}
            }
        });
	}

	window.onload=function(){load();}//md5();
	
	//动态码
	var wait=60;
	function time(o) {	
		//发送前校验手机号是否在系统中存储
		if (wait == 0) {
			o.removeAttribute("disabled");						
			o.value="获取验证码";			
			wait = 60;	
		} else {			
			o.setAttribute("disabled", true);			
			o.value="重新发送(" + wait + ")";			
			wait--;			
			setTimeout(function() {				
				time(o);			
			},			
			1000);		
			}
				
		}
	function send(){
		var iphone = document.getElementById("iphone").value;
		if(iphone == "")
		{
			alert("请输入要发送的手机号");
			return ;
		}
			jQuery.ajax({
	                url: "useriphoneCode.do",
	                data: { iphone:iphone },
	                success: function (text) {
	                	if(text == "")
	                	{
	                		alert("此号码不存在"); 
	                		return ;
	                	}else
	                	{
	                		alert("尊敬的天畅科技用户,您的本次登陆验证码为："+text);
	                		var obj= document.getElementById("btn");
							time(obj);
	                		return;
	                	}
	                }
	            });
	}