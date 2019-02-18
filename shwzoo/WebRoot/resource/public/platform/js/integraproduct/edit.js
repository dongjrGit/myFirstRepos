//按钮事件绑定
$(function () {
    $("input[name=Save]").bind("click", Save);
})

//保存
function Save() {
    var action = $("#action").val();
   
    if (check().form()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        editor.sync();
        $("input[name=detaildesc]").val(editor.html());
        $.ajax({
            url: "/platform/integra/editjfsp",
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
        	name: "required",
        },
        message: {
        	name: { required: "商品名称不能为空" },
        }
    });
}

function formCancel() {
	window.location.href = '/platform/integra/list';
}
//刷新
function backhref() {
	window.location.href = '/platform/integra/list';
}