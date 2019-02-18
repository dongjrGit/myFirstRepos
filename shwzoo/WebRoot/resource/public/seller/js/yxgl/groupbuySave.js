/**
 * 团购编辑
 */
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
		editor1.sync();
		$("input[name=content]").val(editor.html());
		$("input[name=buynotes]").val(editor1.html());
        $.ajax({
            url: "/seller/shopgroupbuy/" + action,
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.desc);
                }
                else {
                    Dalert("保存成功！", "", function () { window.location.href = 'yxgl_GroupBuyList'; });
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
            title: {
                required: true
            },
            type: {
                digits: true
            },
            cprice: {
                required: true,
                number: true
            },
            oprice: {
                required: true,
                number: true
            },
            starttime: {
                required: true
            },
            endtime: {
                required: true
            }

        },
        message: {
        	title: {
                required: "标题不能为空",
            },
            cprice: {
                required: "现价不能为空",
                number: true
            },
            oprice: {
                required: "原价不能为空",
                number: true
            },
            starttime: {
                required: "发放时间不能为空",
            },
            endtime: {
                required: "过期时间不能为空",
            }
        }
    });
}