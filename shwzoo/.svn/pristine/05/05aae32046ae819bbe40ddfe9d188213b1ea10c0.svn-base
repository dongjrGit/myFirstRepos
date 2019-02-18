
//楼层管理
$(function() {
	// 上传图片
	$(".h_scimgbut1").click(
			function() {
				$.ajaxFileUpload({
					url : "/app/api/img/upload",
					secureuri : false,
					fileElementId : 'singlefile',
					dataType : "json",
					// ftype:上传文件类型（图片文件=1，其他文件=2）
					// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
					data : {
						relationtype : 6
					},
					type : 'POST',
					success : function(result) {
						
						$("input[name='img']").val($("#imgsrc").val() +result.data[0]);
						if (result.code == 0){
							Dalert("上传成功");
							$("#loadimg").attr("src",
									$("#imgsrc").val() + result.data[0]);
						}
						else
							$("#loadimg").attr("src", "");
						// TODO 结束正在加载中
					}
				});
			});

	// 获取信息
	// 保存
	$("#submit_ok").click(function() {
		var id = GetQueryStringByName("id");
		if(id==""){
			save();
		}else{
			update(id);
		}
		

	});
	// 返回
	$("#backBtn").click(function() {
		backhref();
	});
});
// 页面返回
function backhref() {
	var topicid = $("#topicid").val();
	if(topicid==""){
		var topicid = $("#fid").val();
	}
	
	//var spmark = $("#spmark").val();
	
	window.location.href = '/platform/topic/sublist?topicid=' + topicid;
	
	//window.location.href = '/platform/topic/sublist';
}

function getInfo(){
	var id=$("#id").val();
	$.ajax({
		url:"/platform/topic/getSpecialProtype",
		type:"post",
		data:{"id":id},
		dataType:"json",
		success:function(data){
			if(data.code==0){
				var res = data.data;
				if (res != null && res != "") {
					$("#topicid").val(res.specialid);
					$("#spname").val(res.name);
					$("#showname").val(res.showname);
					$("#loadimg").attr("src", $("#imgsrc").val()+res.img);
					$("#spmark").val(res.mark);
					$("#sort").val(res.orderby);
				}
			}else{
				Dalert(data.desc);
			}
		},
		error:function(){
			
		}
	})
}



// 保存
function save() {
	//var topicid = GetQueryStringByName("topicid");
	$("#submit_ok").hide();
	var name = $("#spname").val();
	var img = $("#saveimg").val();
	if (img == "") {
		img = $("#loadimg").val();
	}
	var showname=$("#showname").val();
	var sort=$("#sort").val();
	var specialid=$("#topicid1").val();
	var mark=$("#spmark").val();
	//防止重复提交 点击保存后隐藏按钮
	$("#submit_ok").hide();
	$.ajax({
		url : "/platform/topic/insertSpecialProtype",
		type : "Post",
		data : {
			
			"name" : name,
			"img" : img,
			"showname":showname,
			"orderby":sort,
			"specialid":specialid,
			"mark":mark
		},
		dataType : "json",
		success : function(data) {
			
			if (data.code == 0) {
				//Dalert(data.Desc, "", backhref);
				Dalert(data.desc);
				$("#spname").attr("value", "");
				$("#sptype").attr("value", "");
				$("#spmark").attr("value", "");
				$("#subspurl").attr("value", "");
				$("#spfloor").attr("value", "");
				$("#sort").attr("value","");
				$("#spstatus").attr("value","");
				$("#spdesc").attr("value", "");
				$("#loadimg").attr("src","");
				$("#saveimg").attr("value", "");
				//Dalert(data.desc, "", function () { window.location.href = '/platform/topic/showProList?topicid='+topicid+'spmark'+1; });
				Dalert(data.desc, "", function () { window.location.href = '/platform/topic/showSpeProList?topicid=' + specialid; });
			} else {
				$("#submit_ok").show();
				Dalert(data.desc);
			}
		},
		error : function() {

		}
	});
	
}

function update(id) {
	var topicid = $("#topicid").val();
	
	$("#submit_ok").hide();
	var id = $("#id").val();
	var name = $("#spname").val();
	var sort=$("#sort").val();
	var img = $("#saveimg").val();
	if (img=="") {
		img = $("#loadimg").attr('src');
	}
	var showname=$("#showname").val();
	var mark=$("#spmark").val();
	//防止重复提交 点击保存后隐藏按钮
	$("#submit_ok").hide();
	$.ajax({
		url : "/platform/topic/updateSpecialProtype",
		type : "Post",
		data : {
			"id" : id,
			"name" : name,
			"img" : img,
			"showname":showname,
			"orderby":sort,
			"mark":mark
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				
				Dalert(data.desc, "", function () { window.location.href = '/platform/topic/showSpeProList?topicid=' + topicid; });
				//Dalert(data.desc);
				/*$("#spname").attr("value", "");
				$("#sptype").attr("value", "");
				$("#spmark").attr("value", "");
				$("#subspurl").attr("value", "");
				$("#spfloor").attr("value", "");
				$("#spdesc").attr("value", "");
				$("#loadimg").attr("src","");
				$("#saveimg").attr("value", "");*/
				// Dalert("保存成功！", "", function () { window.location.href = '/platform/controlpanel/Control_Menulist'; });
			} else {
				$("#submit_ok").show();
				Dalert(data.desc);
			}
		},
		error : function() {

		}
	});
}

// 表单验证
function check() {
	return $("#floorForm").validate({
		rules : {
			spmark : {
				min : 1
			}
		},
		// 设置提示信息
		messages : {
			spmark : {
				min : "请选择奖项类型"
			}
		},
		// 设置错误信息存放标签
		errorElement : "label",
		debug : true,// 只验证不提交
		// 设置验证触发事件
		focusInvalid : true

	})
}
