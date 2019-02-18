var Init = {
	bind : function() {
		 autoxl.bind("spuName", getSpuStartwithName,true);
		// getNamelist();
		$("#upload").bind(
				"click",
				function() {
					// 上传图片
					$.ajaxFileUpload({
						url : "/app/api/img/upload",
						secureuri : false,
						fileElementId : 'selectimg',
						dataType : "json",
						// ftype:上传文件类型（图片文件=1，其他文件=2）
						// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
						data : {
							relationtype : 1
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

				})
	}
}

// 名称下拉框
function getNamelist() {

	var topicid = $("#topicid").val();
	$.ajax({
		url : "/platform/topic/getName",
		type : "post",
		data : {
			"id" : topicid
		},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {
				Dalert(data.desc);
			} else {
				var listdata = {
					list : data.data
				}
				var html = template('select_brand', listdata);
				$("#spuname").html(html);
			}
		}
	});
}

$(function() {

	// 保存
	$("#submit_ok").click(function() {
		var findid = $("#findid").val();
		var type = $("#type").val();
		saveInfo(findid,type);

	});

	// 返回
	$("#backBtn").click(function() {
		backhref();
	});

	// 品牌加载
	// getNamelist();

});
//获取spu列表
function getSpuStartwithName(callback, event) {
	var findid = $("#findid").val();
    var name = $("#spuName").val();
    var sname = $("#name").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/find/getName",
        type: "Get",
        data: { "id" : findid,
        		"name":name
        },
        dataType: "json",
        success: function (data) {

            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
             /*   autoxl.bind("spuName", getSpuStartwithName);
        		
        		$("#spuName").val(sname);*/
                var html = template('select_spulist', listdata);
                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.data);
            }
        }
    });
}
// 页面返回
function backhref() {
	var findid = $("#findid").val();
	var id = $("#id").val();
	var type = $("#type").val();
	window.location.href = "/platform/find/showFindRelateList?findid=" + findid
			+"&type=" + type;
}

function check() {
	return $("#form").validate({
		rules : {
			
			spuname : {
				required : true
			}
		},
		message : {
			
			spuname : {
				required : "名称不能为空",

			}
		}
	});
}

// 保存信息
function saveInfo(findid, type) {
	if (check().form()) {
		$("submit_ok").hide();
		var id = GetQueryStringByName("id");
		 var relatedid = $("#spuName").attr("data");
		var orderby=$("#orderby").val();
		// 防止重复提交 点击保存后隐藏按钮
		$("#submit_ok").hide();
		$
				.ajax({
					url : "/platform/find/saveFindRelate",
					type : "Post",
					// topicid,String relateid,String imgurl
					data : {
						"findid" : findid,
						"relateid" : relatedid,
						"orderby" : orderby,
						"id" : id
						
					},
					dataType : "json",
					success : function(data) {
						if (data.code == 0) {
							Dalert(data.desc);
							$("#spuname").attr("value", "0");
							$("#orderby").attr("orderby", "");
							$("#loadimg").attr("src", $("#imgsrc").val() + "");
							$("#saveimg").attr("value", "");
							// window.location.href =
							// "/platform/topic/showProList?topicid="+topicid;
							
							/*window.location.href = "/platform/topic/showProList?topicid="
									+ topicid + "&spmark=" + spmark+"&type=" + type;*/
							
							//window.location.href = "/platform/topic/showProList?topicid="+topicid+"&spmark="+spmark+"&type="+type;
							window.location.href = "/platform/find/showFindRelateList?findid=" + findid
							+"&type=" + type;
						} else {
							$("#submit_ok").show();
							Dalert(data.desc);
						}
					}
				});
	}

}

function selectChange() {
	if ($("#Type").val() == "0") {
		$("#url").val("/Web/Goods/pro_detail");
	} else {
		$("#url").val("");
	}
}