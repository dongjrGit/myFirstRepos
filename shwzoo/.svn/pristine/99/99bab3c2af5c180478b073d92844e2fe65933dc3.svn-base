/*控制面板--物流公司（暂时无用）*/
$(document).ready(function () {
    LogisticsCom.bind();

    //编辑
    $("body").on("click",".bjxx", function () {
        var id = $(this).parent().parent().find("input").val();
        location.href = "Updatelogistics?isEdOrAd=0&&logID="+id;//isEdOrAd设定编辑还是添加：0为编辑，1为添加

    })
    //取消
    $("#editcasel").on("click",null, function () {
        clearForm();
        document.getElementById("form").reset();
        $(".sctp-con").css("display", "none")
    })
    //添加
    $(".chaxun").click(function () {
        location.href = "Updatelogistics?isEdOrAd=1";
    })
    //删除物流公司
    $("body").on("click",".delete", function () {
        if (confirm("确认要删除物流公司吗？")) {
            var id = $(this).parent().parent().find("input").val();
            var userID = $("#userid").val();
            $.ajax({
                type: "post",
                url: "/LogisticsCom/DeleteLogistics",
                dataType: "json",
                data: {
                    id: id, userID: userID
                },
                success: function (rsl) {
                    if (rsl.Code == 0) {
                        Dalert(rsl.Desc,"",refresh);
                        //window.location.reload();
                    }
                    else {
                        Dalert(rsl.Desc, "", refresh);
                        //window.location.reload();
                    }
                },
                error: function (e) {
                    //Dalert(e.statusText);
                }
            })
        }
    })

});

var LogisticsCom = {
    bind: function () {
        $.ajax({
            url: "/LogisticsCom/GetList",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    //alert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('lgtlist', listdata);
                    $("#list_title").siblings().remove();
                    $("#list_title").after(html);

                }
            },
            error: function () {
            }
        });
    }
}
function refresh() {
    window.location.reload();
}
