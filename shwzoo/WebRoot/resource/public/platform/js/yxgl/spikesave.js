//按钮事件绑定
$(function () {
    $("input[name=Save]").bind("click", Save);
})
function typeChange(){
	var stype=$("#stype").val();
	if(stype==2){
		$("#divimg").show();
		$("#divlist").hide();
		$("#divdetail").hide();
	}else if (stype==3){
		$("#divimg").show();
		$("#divlist").show();
		$("#divdetail").show();
	}else if (stype==4){
		$("#divclass").show();
	}else{
		$("#divimg").hide();
		$("#divclass").hide();
		$("#divlist").hide();
		$("#divdetail").hide();
	}
}
//保存
function Save() {
    var action = $("#action").val();
   
    if (check().form()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();

//        editor.sync();
//        $("input[name='detaildesc']").val(editor.html());


        $.ajax({
            url: "/platform/SpikeActivity/" + action,
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
            	
                if (data.code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.desc);
                }
                else {
                    Dalert("保存成功！", "", backhref);

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
            sname: "required",
            start: "required",
            end: "required"
        },
        message: {
            sname: { required: "活动名称不能为空" },
            start: { required: "开始时间不能为空" },
            end: {
                required: "结束时间不能为空"
            }
        }
    });
}

function formCancel() {
	window.location.href = 'yxgl_ExcitingList';
}
//刷新
function backhref() {
	if($("#stype").val()==3){
		 window.location.href = 'yxgl_ExcitingList';
	}else{
		 window.location.href = 'yxgl_SpikeList';
	}
}