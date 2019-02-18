//商品咨询ID
var goodconsultID;
//初始化
var Init = {
    bind: function () {
        //获取商品咨询ID
        goodconsultID = GetQueryStringByName("goodconsultID");
        //清空按钮点击
        $("#btn_empty").bind("click", function () {
            $("#text_replygoodconsult").attr("value", "");
        });
    }
}
//表单验证
var Vaildate = {
    bind: function () {
        $("#replygoodconsultForm").validate({
            rules: {
                text_replygoodconsult: {
                    required: true
                }
            },
            messages: {
                text_replygoodconsult: {
                    required: "请输入回复内容"
                }
            },
            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
            },
            debug: true,
            submitHandler: function (form) {
                $(form).ajaxSubmit(Submit.bind());
            }
        })
    }
}
//表单提交
var Submit = {
    bind: function () {
        var replygoodconsult = $("#text_replygoodconsult").val();
        $.ajax(({
            type: "post",
            url: "/GoodConsult/S_ReplyGoodConsult",
            dataType: "json",
            data: { GoodConsultID: goodconsultID, ReplyContent: replygoodconsult },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    Dalert("商品咨询回复成功");
                    $("#text_replygoodconsult").attr("value", "");
                }
                else {
                    Dalert(rsl.Desc);
                }
            },
            error: function (e) {

            }
        }));
    }
}