//积分商品编辑
var skuid;
var pointsku = {
    getUserLevel: function () {
        var userlevel = $("#level").val();
        $.ajax({
            url: "/Coupon/G_GetUsersLevel",
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = '<option value="0" id="defaultlevel" selected>无</option>' + template('levellist', listdata);
                    $("#userlevel").html(html);
                    if (userlevel > 0) {
                        $("#userlevel option").each(function () {
                            if ($(this).val() == userlevel) {
                                $(this).attr("selected", "selected");
                            } else {
                                $(this).removeAttr("selected");

                            }

                        });
                    }
                }
            },
            error: function () {
                //alert("数据加载失败");
            }
        });
    },
    getSku: function (callback, event) {
        var name = $("#select_sku").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/Product/P_GetSKULikes",
            type: "Post",
            data: { "NameLike": name },
            dataType: "json",
            success: function (data) {

                if (data.Code == 0) {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('select_skulist', listdata);

                    if (callback) {
                        callback(html);
                    }
                } else {
                    Dalert(data.Data);
                }
            }
        });
    }
}
//按钮事件绑定
$(function () {
    $("input[name=Save]").bind("click", Save);
    pointsku.getUserLevel();
})
//保存
function Save() {
    var action = $("#action").val();
    if (check().form()) {
        if ($("#select_sku").attr("data") != "" && $("#select_sku").attr("data") != undefined)
            $("#skuid").val($("#select_sku").attr("data"))
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/SkuPoints/" + action,
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.Desc);
                }
                else {
                    //Dalert(data.Desc);

                    Dalert("保存成功！", "", function () { window.location.href = '/Platform/yx/PointsSkuList'; });

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
            select_sku: {
                required: true
            },
            count: {
                required: true,
                digits: true
            },
            price: {
                number: true
            },
            points: {
                required: true,
                digits: true
            },
            orderby: {
                number: true
            }

        },
        message: {
            select_sku: {
                required: "名称不能为空",
            },
            count: {
                required: "数量不能为空",
                digits: "必须输入整数"
            },
            points: {
                required: "限领张数不能为空",
                digits: "必须输入整数"
            },
            orderby: {
                required: "数量不能为空",
                digits: "必须输入整数"
            }
        }
    });
}