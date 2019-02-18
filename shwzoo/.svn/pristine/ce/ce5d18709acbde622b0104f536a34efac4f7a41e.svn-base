/*支付方式管理*/
$(document).ready(function () {
    Message.bind();
 
    //删除信息
    $("body").on("click",".delete", function () {
        var id = $(this).parent().find("input").val();
        var d = dialog({
            title: '提示',
            content: '确认要删除支付方式吗？',
            okValue: '确定',
            ok: function () {
                $.ajax({
                    type: "post",
                    url: "/platform/payset/deletePaySetById",
                    dataType: "json",
                    data: {
                        id: id
                    },
                    success: function (rsl) {
                        if (rsl.code == 0) {
                            Dalert(rsl.desc); Message.bind();
                        }
                        else {
                            Dalert(rsl.desc);
                        }
                    },
                    error: function (e) {
                       // Dalert(e.statusText);
                    }
                });
            },
            cancelValue: '取消',
            cancel: function () { }
        }).show();
    });
    //编辑跳转
    $("body").on("click",".bjxx", function () {
        var id = $(this).parent().find("input").val();
       // $(".bjxx").attr('href', "PaySet_add?id=" + id);
        location.href = "/platform/controlpanel/paySet_add?id=" + id;
    });
    //添加 跳转
    $("#addbtn").click( function () {
        location.href = "/platform/controlpanel/paySet_add";
    })
});
var Message = {
    bind: function () {
        $.ajax({
            url: "/platform/payset/queryPaySetList",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    //Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('datalist', listdata);
                    $("#trlist").siblings().remove();
                    $("#trlist").after(html);
                }
            },
            error: function () {
            }
        });
    }
}
