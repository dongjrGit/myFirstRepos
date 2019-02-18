//会员主页
var ch=3;
var MemberHomepage={
		exit: function (){
			$.ajax(({
				type:"post",
				url:"/api/wap/userinfo/exit",
			    dataType:"json",
			    data:{ch:ch},
			  success: function (rsl) {
	                if (rsl.code == 0) {
	                	location.href = "/wap/userinfo/homepage?ch=3";
	                	window.location.reload();
	                    }
	                else{alert(rsl.desc);}

	                },
			    error:function(e){
			    	
			    }

			}))
		},

		
}