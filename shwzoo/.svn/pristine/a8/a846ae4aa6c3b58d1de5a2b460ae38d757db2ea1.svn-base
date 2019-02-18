//楼层管理
$(function () {
    //获取信息
    getInfo();
    //保存
   
    $("#submit_ok").click(function () {
        var id = GetQueryStringByName("id");
        save(id);
    });
    //返回
    $("#backBtn").click(function () {
        backhref();
    });
    
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
 							$("#loadimg").attr("src", result.data[0]);
 						}
 						else
 							$("#loadimg").attr("src", "");
 						// TODO 结束正在加载中
 					}
 				});
 			});
});
//页面返回
function backhref() {
    location.href = "Floors";
}
//获取信息
function getInfo() {
    var id = GetQueryStringByName("id");
    if (id != "-1") {
        $.ajax({
            url: "/platform/floor/queryById",
            type: "Post",
            data: { "fid": id },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    var res = data.data;
                    if (res != null && res != "") {
                        $("#flname").val(res.name);
                        $("#fltype").val(res.type);
                       // $("#fldisplay").val(res.display);
                        //$("#flprotype").val(res.ProType);
                        $("#flmark").val(res.pagetag);
                        $("#florderby").val(res.orderby);
                        $("#fldesc").val(res.desc);
                        $("#saveimg").val(res.imgurl);
                        $("#loadimg").attr("src",res.imgurl);
                        var ws=res.webSet;
                        if(ws){
                            if(ws.indexOf(',')>0){
                                ws=ws.split(',');
                                for (var i=0; i < ws.length; i++) {
                                  $("input[name=useplatform]").each(function(index) {
                                  if(ws[i]==$(this).val()){
                                      $(this).attr("checked","checked");
                                  }
                                });
                                  
                                };
                            }else{
                                $("input[name=useplatform]").each(function(index) {
                                  if(ws==$(this).val()){
                                      $(this).attr("checked","checked");
                                  }
                                });
                            }
                        }
                    }
                }
                else { Dalert(data.desc); }
            },
            error: function () {

            }
        });
    }
}
//保存
function save(id) {
	
    $("#submit_ok").hide();
    var name = $("#flname").val();
    var type = $("#fltype").val();
    //var proType = $("#flprotype").val();
    var pagetag = $("#flmark").val();
    //var display = $("#fldisplay").val();
    var orderby = $("#florderby").val();
    var desc = $("#fldesc").val();    
    var webset = "";
    $("input[name=useplatform]:checked").each(function(index) {
      webset=webset+$(this).val()+",";
    });
    if(webset!=""){
        webset=webset.substring(0,webset.length-1);
    }
    var imgurl=$("#saveimg").val();
    $.ajax({
        url: "/platform/floor/editfloor",
        type: "Post",
        data: { "id": id,
        	    "name": name,
        	    "type": type, 
        	    "pagetag": pagetag,
        	   // "display": display,
        	    "orderby": orderby,
        	    "desc": desc,
        	    "webset":webset,
        	    "imgurl":imgurl
        	},
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
            	Dalert(data.desc, "", function () {  window.location.href = "/platform/floor/list";});
            } else {
                $("#submit_ok").show();
                Dalert(data.desc);
            }
        },
        error: function () {

        }
    });
}
//表单验证
function check() {
    return $("#floorForm").validate({
        rules: {
            SetName: {
                maxlength: 10,
                required: true
            },
            SetOrderBy: {
                required: true,
                digits: true
            }
        },
        //设置提示信息
        messages: {
            SetName: {
                maxlength: "长度限制在10个字符以内",
                required: "请输入配置名称"
            },
            SetOrderBy: {
                required: "请输入配置值",
                digits: "请输入整数"
            }
        },
        //设置错误信息存放标签
        errorElement: "label",
        //设置验证触发事件
        focusInvalid: true

    })
}
