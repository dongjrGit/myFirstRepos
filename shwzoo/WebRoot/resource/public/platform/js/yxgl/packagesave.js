//按钮事件绑定
$(function () {
    $("input[name=Save]").bind("click", Save);
});
//保存
function Save() {
    var action = $("#action").val();
    if (check().form()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/platform/package/" + action,
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.desc);
                }
                else {
                    //Dalert(data.Desc);

                    Dalert("保存成功！", "", function () { window.location.href = 'yxgl_PackageList'; });

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
            name: {
                required: true
            },
            orderby: {
                digits: true
            },
            count: {
                required: true,
                digits: true
            },
            price: {
                required: true,
                number: true
            },
            start: {
                required: true
            },
            end: {
                required: true
            }

        },
        message: {
            name: {
                required: "名称不能为空",
            },
            orderby: {
                required: "不能为空",
                digits: "必须输入整数"
            },
            count: {
                required: "数量不能为空",
                digits: "必须输入整数"
            },
            price: {
                required: "价格不能为空",
                number: true
            },
            start: {
                required: "开始时间不能为空",
            },
            end: {
                required: "结束时间不能为空",
            }
        }
    });
}