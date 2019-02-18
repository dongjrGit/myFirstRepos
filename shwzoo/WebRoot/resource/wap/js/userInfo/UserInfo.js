 var ch=3;
var UserInfo={
		upuserssmessage: function (imgUrl,name,age,birthday,hometown,location){
			$.ajax(({
				type:"post",
				url:"/api/wap/userinfo/updUserInfo",
			    dataType:"json",
			    data:{imgUrl:imgUrl,name:name,age:age,birthday:birthday,hometown:hometown,location:location,ch:ch},
			  success: function (rsl) {
	                if (rsl.code == 0) {
	                	alert("修改成功！");
	                	//location.href = "/wap/userinfo/selectInfo?ch=3";
	                	window.location.href = "/wap/userinfo/selectInfo?ch=3";
	                    }
	                else{alert(rsl.desc);}

	                },
			    error:function(e){
			    	
			    }

			}))
		},
		//图片
		 fimage:function(index) {
			$.ajaxFileUpload({
						url : "/app/api/img/upload",
						secureuri : false,
						fileElementId : 'file'+index,
						dataType : "json",
						// ftype:上传文件类型（图片文件=1，其他文件=2）
						// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
						data : {
							relationtype : 5
						},
						type : 'POST',
						success : function(result) {
							if (result.code == 0) {
								$("#img1").attr("src",result.data[0]);
								$("#img").attr("src",result.data[0]);
								$("#imasrc3").val(result.data[0]);
								
							} 
						},
						error : function(e) {
							// alert(JSON.stringify(e));

						}
					});
		 }
}