$(function () {

    var id = GetQueryStringByName("id");
    var sta = GetQueryStringByName("sta");

    if (sta == 1) {
        $("#confirm").hide();
    }
    //取消返回
    $("input[name=bback]").click(function () {
        formCancel();
    });
    //获取信息
    if (parseInt(id) > 0) {
        GetInfo(id);
    }
    $("#confirm").click(function () {
        if (parseInt(id) > 0) {
            saveInfo(id);
        }
    })
});
function formCancel() {
    var sta = GetQueryStringByName("sta");
    if (parseInt(sta) != 0 && parseInt(sta) != 1) {
        sta = 0;
    }
    location.href = "/seller/ddtj/billlist?status=" + sta;
}
function check() {
    return $("#form").validate({
        rules: {
            name: {
                required: true,
                maxlength: 200
            },
            //selectimgs: {required:true},
            spuPrice: {
                required: true
            },
            display: {
                required: true
            }
        },
        message: {
            name: { required: "商品名不可为空", maxlength: "最多输入50个汉子" },

            //selectimgs: {
            //    required: "必填"
            //},
            spuPrice: {
                required: "商品价格不可为空"
            },
            display: {
                digtal: "最多输入50个汉子"
            }
        }, errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        }
    });
}
//保存信息
function saveInfo(id) {
    var ttype = $("#remark").val();
    var tTime = $("#tTime").val();
    var remark = $("#remark").val();
    var tmoney=$("#tmoney").val();
    $.ajax({
        url: "/seller/shoporder/editBusinessBill",
        type: "Post",
        data: { "bID": id, "remitType": ttype, "transferDate": tTime, "remark": remark, "transferMoney": tmoney },
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                Dalert(data.desc, "", formCancel);
            } else {
                $("input[name=confirm]").show();
                Dalert(data.desc);
            }
        }
    });
}

//获取信息
function GetInfo(id) {
    $.ajax({
        url: "/seller/shoporder/billByID",
        type: "Post",
        data: { "id": id },
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                var res = data.data;
                var bt = new Date(res.begindate).Format("yyyy-MM-dd");
                var et = new Date(res.enddate).Format("yyyy-MM-dd");
                $("#beTime").val(bt + "--" + et);
                $("#shopname").val(res.shopname);
                $("#trunover").val(res.turnover);
                $("#bankusername").val(res.bankusername);
                $("#commission").val(res.commission);
                $("#banknum").val(res.banknum);
                $("#tTime").val(new Date(res.transferdate).Format("yyyy-MM-dd"));
                $("#bankname").val(res.bankname);
                $("#tmoney").val(res.settlement);
                $("#ttype").val(res.remitType);
                $("#opertor").val(res.operator);
                $("#remark").val(res.remark);
            } else {
                Dalert(data.desc);
            }
        }
    });
}
