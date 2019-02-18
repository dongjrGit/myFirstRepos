var Init = {
	bind : function() {
		 autoxl.bind("spuName", getSpuStartwithName,true);
		 if(parseInt($("#type").val())==6){
			 autoxl.bind("classifyName", getclassifyStartwithName,true);
		 }
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

							$("input[name='img']").val(result.data[0]);
							if (result.code == 0){
								Dalert("上传成功");
								$("#loadimg").attr("src", result.data[0]);
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
		var topicid = $("#topicid").val();
		var spmark = $("#spmark").val();
		var type = $("#type").val();
		var fatherid = $("#fatherid").val();
		saveInfo(topicid, spmark,type,fatherid);

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
	var isby=$("#isby").val();
	var topicid = $("#topicid").val();
    var name = $("#spuName").val();
    var sname = $("#name").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/topic/getName",
        type: "Get",
        data: { "id" : topicid,
        		"isby":isby,
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
	var topicid = $("#topicid").val();
	var spmark = $("#spmark").val();
	var type = $("#type").val();
	var fatherid = $("#fatherid").val();
	window.location.href = "/platform/topic/showProList?topicid=" + topicid
			+ "&spmark=" + spmark+"&type=" + type+"&fatherid="+fatherid;
}

function check() {
	return $("#form").validate({
		rules : {
			img : {
				required : true
			},
			spuname : {
				required : true
			}
		},
		message : {
			img : {
				required : "图片不能为空",
			},
			spuname : {
				required : "名称不能为空",

			}
		}
	});
}

// 保存信息
function saveInfo(topicid, spmark,type,fatherid) {
	if (check().form()) {
		$("submit_ok").hide();
		// var spmark=GetQueryStringByName("spmark");
		
		
		var id = GetQueryStringByName("id");
		 var relatedid = $("#spuName").attr("data");
		/*var relatedid = $("#spuname").val();*/
		 var classifyid=$("#classifyName").attr("data");
		 var classifyName=$("#classifyName").val();
		var orderby=$("#orderby").val();
		var img = $("#saveimg").val();
		if (img == "") {
			img = $("#loadimg").val();
		}
		// 防止重复提交 点击保存后隐藏按钮
		$("#submit_ok").hide();
		$.ajax({
					url : "/platform/topic/saveTopicRelate",
					type : "Post",
					// topicid,String relateid,String imgurl
					data : {
						"topicid" : topicid,
						"relateid" : relatedid,
						"orderby" : orderby,
						"id" : id,
						"imgurl" : img,
						"classifyid":classifyid,
						"classifyName":classifyName
					},
					dataType : "json",
					success : function(data) {
						if (data.code == 0) {
							Dalert(data.desc,2000,function(){
								$("#spuname").attr("value", "0");
								$("#orderby").attr("orderby", "");
								$("#loadimg").attr("src", $("#imgsrc").val() + "");
								$("#saveimg").attr("value", "");
								// window.location.href =
								// "/platform/topic/showProList?topicid="+topicid;
								
								/*window.location.href = "/platform/topic/showProList?topicid="
										+ topicid + "&spmark=" + spmark+"&type=" + type;*/
								
								//window.location.href = "/platform/topic/showProList?topicid="+topicid+"&spmark="+spmark+"&type="+type;
								returnhref(topicid,spmark,type,fatherid);
							});
							
						} else {
							$("#submit_ok").show();
							Dalert(data.desc);
						}
					}
				});
	}

}
function returnhref(topicid,spmark,type,fatherid){
	location.href = "/platform/topic/showProList?topicid=" + topicid
	+ "&spmark=" + spmark+"&type=" + type+"&fatherid="+fatherid;
}

function selectChange() {
	if ($("#Type").val() == "0") {
		$("#url").val("/Web/Goods/pro_detail");
	} else {
		$("#url").val("");
	}
}



//获取spu列表
function getclassifyStartwithName(callback, event) {
	var topicid = $("#topicid").val();
  var name = $("#classifyName").val();
  var sname = $("#name").val();
  if (event)
      name += String.fromCharCode(event.keyCode);
  $.ajax({
      url: "/platform/topic/getClassify",
      type: "Get",
      data: { "id" : topicid,
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