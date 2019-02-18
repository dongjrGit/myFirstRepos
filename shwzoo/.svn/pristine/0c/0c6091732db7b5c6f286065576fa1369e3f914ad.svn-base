//广告编辑

$(function () {
    $("input[name=Save]").bind("click", Save);  //绑定保存按钮事件 
    //文件上传
    $(".tjcpxx-con-form-upthis").each(function () {
        $(this).bind("click", function (e) {
            var $imgurl = $(this).parent().parent().find("input").last();
            var $imgsrc = $(this).parent().parent().parent().find("img").first();
            var elementid = $(this).parent().parent().find("input").first().attr("id");
            $.ajaxFileUpload({
                url: "/upLoad/UploadFile",
                secureuri: false,
                fileElementId: elementid,
                dataType: "json",
                //ftype:上传文件类型（图片文件=1，其他文件=2）
                //module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
                data: { ftype: 1, module: 2 },
                type: 'POST',
                success: function (result) {
                    //  alert(JSON.stringify(result));
                    if (result.Code == 0) {
                    	Dalert("上传成功");
                        $imgurl.val(result.Data[0]);
                        $imgsrc.attr("src", result.Data[0]);
                    }
                    else {
                        $imgsrc.attr("src", "");
                        Dalert(result.Desc);
                    }
                    //TODO 结束正在加载中
                },
                error: function (e) {
                    Dalert(JSON.stringify(e));
                    //TODO 结束正在加载中
                }
            });
        });
    });
})

function Save() {
    if (check().form()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/Advertising/P_Save",
            type: "Post",
            data: {
                "Id": $("input[name=Id]").val(), "Title": $("input[name=Title]").val(), "OrderBy": $("input[name=OrderBy]").val(),
                "Url": $("input[name=Url]").val(), "Image": $("input[name=image]").val(), "type": $("#Type").val(),
                "content": $("input[name=Content]").val(), "display": $("input[name=Display]").val(),
            },
            dataType: "json",
            success: function (data) {
                if (data.Code == 0) {
                    Dalert("保存成功！", "", backhref);
                } else {
                    $("input[name='Save']").show();
                    Dalert(data.Desc);
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
            Image: "required",
            Url: "required",
            Title: {
                required: true,
                stringCheck: true,
                rangelength: [1, 50]
            },
            OrderBy: {
                required: true,
                digits: true
            },
            Display: {
                required: true,
                digits: true
            }
        },
        message: {
            Image: { required: "图片不可为空" },
            Url: { required: "图片链接不可为空" },
            Title: {
                required: "标题不可为空"
            },
            OrderBy: {
                required: "排序不能为空",
                digits: "必须输入整数"
            },
            Display: {
                required: "位置不能为空",
                digits: "必须输入整数"
            }
        }
    });
}
//刷新
function backhref() {
    window.location.href = '/Platform/gg/AdvertisingList';
}