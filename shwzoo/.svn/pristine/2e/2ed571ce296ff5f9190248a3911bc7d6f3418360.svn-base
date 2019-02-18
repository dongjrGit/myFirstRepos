
$(function() {
	// 上传图片
	$(".tjcpxx-con-form-upthis").click(
			function() {
				$.ajaxFileUpload({
					url : "/app/api/img/upload",
					secureuri : false,
					fileElementId : 'selectimg',
					dataType : "json",
					// ftype:上传文件类型（图片文件=1，其他文件=2）
					// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
					data : {
						relationtype : 6
					},
					type : 'POST',
					success : function(result) {
						
						$("input[name='img']").val(result.data[0]);
						if (result.code == 0){
							Dalert("上传成功");
							$("#loadimg").attr("src",result.data[0]);
						
						}else{
							$("#loadimg").attr("src", "");
							Dalert(result.desc);
						}
						// TODO 结束正在加载中
					}
				});
			});

	// 获取信息
	getInfo();
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
	location.href = "SpecialList";
}
// 获取信息
function getInfo() {
	var id = GetQueryStringByName("id");
	if (id != "-1") {
		$.ajax({
			url : "/platform/topic/queryById",
			type : "Post",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(data) {		
				if (data.code == 0) {
					var res = data.data;
					if (res != null && res != "") {
						$("#spname").val(res.title);
						$("#sptype").val(res.type);
						$("#spmark").val(res.mark);
						$("#spstatus").val(res.status);				
						$("#spdesc").val(res.description);
						$("#loadimg").attr("src", res.imgurl);
						$("#saveimg").val(res.imgurl);
						$("#pagetag").val(res.pagetag);
						$("input[name=useplatform]").val(res.webset);

					}
				} else {
					Dalert(data.desc);
				}
			},
			error : function() {

			}
		});
	}
}
// 保存
function save() {
	if (check().form()) {
		//防止重复提交 点击保存后隐藏按钮
		$("#submit_ok").hide();
		
		var name = $("#spname").val();
		var type = $("#sptype").val();
		var mark = $("#spmark").val();
	
		var status=$("#spstatus").val();
		var desc = $("#spdesc").val();
		var pagetag=$("#pagetag option:selected").val();
		var webset = $("input[name=useplatform]").val();
		var img = $("#saveimg").val();
		if (img == "")
			img = $("#loadimg").val();
		
		$.ajax({
			url : "/platform/topic/addTopic",
			type : "Post",
			data : {
				
				"name" : name,
				"type" : type,
				"mark" : mark,
				"img" : img,
				"status":status,				
				"desc" : desc,
				"webset":webset,
				"pagetag" : pagetag
			},
			dataType : "json",
			success : function(data) {				
				if (data.code == 0) {
					Dalert(data.desc, "", function () { returnhref() });
				} else {
					$("#submit_ok").show();
					Dalert(data.desc);
				}
			},
			error : function() {

			}
		});
	}
	
}

function update(id) {
	$("#submit_ok").hide();
	
	var id = GetQueryStringByName("id");
	var name = $("#spname").val();
	var type = $("#sptype").val();
	var mark = $("#spmark").val();
	var status=$("#spstatus").val();
	var desc = $("#spdesc").val();
	var pagetag = $("#pagetag option:selected").val();
	var webset = $("input[name=useplatform]").val();
	var img = $("#saveimg").val();
	if (img == "") {
		img = $("#loadimg").val();
	}
	//防止重复提交 点击保存后隐藏按钮
	$("#submit_ok").hide();
	$.ajax({
		url : "/platform/topic/updTopic",
		type : "Post",
		data : {
			"id" : id,
			"name" : name,
			"type" : type,
			"mark" : mark,
			"img" : img,
			"status":status,
			"desc" : desc,
			"webset":webset,
			"pagetag" : pagetag
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				Dalert(data.desc, "", function () { returnhref(); });
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
			img : {
				required : true
			}
		},
		message : {
			img : {
				required : "图片不能为空",
			}
		}
	});
}

function returnhref(){
	window.location.href = "/platform/topic/list";
}
