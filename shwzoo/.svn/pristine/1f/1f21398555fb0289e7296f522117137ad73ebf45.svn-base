

var Init = {
	bind : function() {
		
		// 获取楼层id数据
		GetSelectData();
		
	}
}

function GetSelectData() {
	// 楼层数据绑定
	
	$.ajax(({
		type : "post",
		url : "/platform/floor/queryall",
		dataType : "json",
		async : false,
		data : {},
		success : function(item) {
			if (item.code == 0) {
				var listdata = {
					list : item.data
				}
				var html = template('floorselect', listdata);
				$("#spfloor").append(html);
			} else {
				Dalert(item.desc);
			}
		},
		error : function(es) {

		}
	}));
}

//楼层管理
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
	var topicid = $("#topicid").val();
	if(topicid==""){
		var topicid = $("#fid").val();
	}
	//var spmark = $("#spmark").val();
	window.location.href = '/platform/topic/showSonTopic?topicid='+topicid+"&type=${type}";
	//window.location.href = '/platform/topic/sublist';
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
						$("#subspurl").val(res.url);
						$("#spfloor").val(res.floorappid);
						$("#sort").val(res.sort);
						$("#spstatus").val(res.status);
						$("#spdesc").val(res.description);
						$("#loadimg").attr("src", $("#imgsrc").val()+res.imgurl);
						$("#saveimg").val(res.imgurl);
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
	//var topicid = GetQueryStringByName("topicid");
	var topicid = $("#topicid").val();
	
	if (check().form()) {
		$("#submit_ok").hide();
		var name = $("#spname").val();
		var type = $("#sptype").val();
		var mark = $("#spmark").val();
		var subspurl = $("#subspurl").val();
		var spfloor = $("#spfloor").val();
		var sort=$("#sort").val();
		var status=$("#spstatus").val();
		var desc = $("#spdesc").val();
		var img = $("#saveimg").val();
		if (img == "") {
			img = $("#loadimg").val();
		}
		//防止重复提交 点击保存后隐藏按钮
		$("#submit_ok").hide();
		$.ajax({
			url : "/platform/topic/addSubTopic",
			type : "Post",
			data : {
				
				"name" : name,
				"type" : type,
				"mark" : mark,
				"img" : img,
				"subspurl" : subspurl,
				"spfloor" : spfloor,
				"sort":sort,
				"status":status,
				"desc" : desc,
				"fatherid":topicid
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
					Dalert(data.desc, "", function () { window.location.href = '/platform/topic/sublist?topicid=' + topicid; });
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
	var topicid = $("#fid").val();
	
	$("#submit_ok").hide();
	var id = GetQueryStringByName("id");
	var name = $("#spname").val();
	var type = $("#sptype").val();
	var mark = $("#spmark").val();
	var subspurl = $("#subspurl").val();
	var spfloor = $("#spfloor").val();
	var sort=$("#sort").val();
	var status=$("#spstatus").val();
	var desc = $("#spdesc").val();
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
			"subspurl" : subspurl,
			"spfloor" : spfloor,
			"sort": sort,
			"status":status,
			"desc" : desc,
			"fatherid":topicid
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				
				Dalert(data.desc, "", function () { window.location.href = '/platform/topic/sublist?topicid=' + topicid; });
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
