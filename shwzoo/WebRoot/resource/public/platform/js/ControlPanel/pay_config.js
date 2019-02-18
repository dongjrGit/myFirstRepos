/**
 * 支付设置
 */
var bind = {
    getlist: function (index) {
        var partner = $("#partner").val();
        var paytype = $("#paytype").val();
        $.ajax({
            url: "/platform/paycongif/getlist",
            type: "Post",
            data: { "partner": paytype, "paytype": paytype},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    //js template数据加载
                    var listdata = {
                        list: data.data
                    }

                    var html = template('paylist', listdata);
                    //html 填充
                    $("#datalist").html(html);
                }
            },
            error: function () {
                Dalert("数据加载失败");
            }
        });
    }
}
//按钮事件绑定
$(function() {
	$("input[name=add]").bind("click", function () { location.href = "payConfig_edit"; });
	$("input[name=Save]").bind("click", Save);
	if($("input[name=id]").val()>0){   
		   $("#paytype").val($("input[name=datapaytype]").val());	  
		   $("#sites").val($("input[name=datasites]").val());
		   $("#iscredit").val($("input[name=dataiscredit]").val());
		   $("#producttype").val($("input[name=dataproducttype]").val());
		   $("#signtype").val($("input[name=datasigntype]").val());
		   TypeChange();
		   creditChange();
		   siteChange();
	}
})
function TypeChange(){
	if($("#paytype").val()==1){
		$("#divips").hide();
		$("#divalipay").show();
	}
    if($("#paytype").val()==2){
    	$("#divips").show();
		$("#divalipay").hide();
	}
}
function creditChange(){
	if($("#iscredit").val()==1){
		$("#creditdiv").show();
	}else{
		$("#creditdiv").hide();
	}
}
function siteChange(){
	if($("#paytype").val()==1 && $("#sites").val()==2){
		$("#divaliapp").show();
	}else{
		$("#divaliapp").hide();
	}
}
function Save() {
    var action = $("#action").val();
    if (check().form()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/platform/paycongif/" + action,
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Dalert("保存成功！","",backhref);
                } else {
                    $("input[name='Save']").show();
                    Dalert(data.desc);
                }
            },
            error: function () {
            }
        });
    }
}
//保存前参数验证
function check() {
    return $("#form").validate({
        rules: {
        	partner: {
                required: true,
            },
            sellerid: {
                required: true
            }
        },
        message: {
        	partner: {
                required: "不可为空"
            },
            sellerid: {
                required: "不能为空"
            }
        }
    });
}
//返回刷新
function backhref() {
    window.location.href = '/platform/controlpanel/payConfig_list';
}
