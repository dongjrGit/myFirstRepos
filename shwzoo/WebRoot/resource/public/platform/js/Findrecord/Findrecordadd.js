var init={
	bind:function(){
		// 图片上传
		$("#img_findimage").bind("click", function() {
		
			$("#file_findimage").click();
		});
		$("#file_findimage").change(function() {
			findimage();
		});
	}
}


//表单验证
var Vaildate = {
	bind : function() {
		$("#addFindForm").validate({
			rules : {
				text_title : {
					required : true,
					//byteRangeLength : [ 4, 15 ]
				}
			},
			messages : {
				text_title : {
					required : "请输入标题"
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			},
			debug : true,
			submitHandler : function(form) {
				$(form).ajaxSubmit(Submit.bind());
			}
		})
	}
}

//表单提交
var Submit = {
	bind : function() {
		var title = $("#text_title").val();
		var type = $("#text_type option:selected").val();
		var url=$("#text_url").val();
		var sort=$("#text_sort").val();
		var status=$("input[name=radio_status]");
	    var statusVal="";
	    for(var i=0;i<status.length;i++){
	    	if(status[i].checked){
	    		statusVal=status[i].value;
	    	}
	    }
		var Oldid=$("#hidden_findid").val();
		var findimage=$("#findimage").val();
		if(Oldid!=null&&Oldid!=""){
			$.ajax(({
				type : "post",
				url : "/platform/find/editFindRecord",
				dataType : "json",
				data : {
					//type:type,
					title:title,
					status:statusVal,
					oldid:Oldid,
					imgurl:findimage,
					url:url,
					sort:sort
				},
				success : function(editrsl) {
					if (editrsl.code == 0) {
						Dalert("编辑成功");
						window.location.href = "/platform/find/showFindRecord";
					} else {
						Dalert(editrsl.desc);
					}
				},
				error : function(e) {

				}
			}));
		}else{
			$.ajax(({
			type : "post",
			url : "/platform/find/findRecordAdd",
			dataType : "json",
			data : {
				type:type,
				title:title,
				status:statusVal,
				imgurl:findimage,
				url:url,
				sort:sort
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert("添加成功");
					$("#text_title").attr("value", "");
				/*	$("#text_type").attr("value", "");*/
					$("#radio_status").attr("value", "0");
					window.location.href = "/platform/find/showFindRecord";
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {
			}
		}));
		}
	}
}

//店铺头像
function findimage() {
	$
			.ajaxFileUpload({
				url : "/app/api/img/upload",
				secureuri : false,
				fileElementId : 'file_findimage',
				dataType : "json",
				// ftype:上传文件类型（图片文件=1，其他文件=2）
				// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
				data : {
					relationtype : 8
				},
				type : 'POST',
				success : function(result) {
					if (result.code == 0) {
						$("#img_findimage").attr("src",
								$("#imgsrc").val() + result.data[0]);
						$("#findimage").val($("#imgsrc").val()+result.data[0]);
					} else {
						var html1 = '<label id="img_license-error" class="error" for="find_shopimage">'
								+ result.desc + '</label>';
						$("#div_findimage").append(html1);
					}

				},
				error : function(e) {
					// alert(JSON.stringify(e));

				}
			});

	$("#file_findimage").remove();
	var input = '<input type="file" name="file_findimage" id="file_findimage" onchange="findimage()" hidden />';
	$("#div_findimage").append(input);
}