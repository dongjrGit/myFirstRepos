
//满减活动信息编辑
var cid;
var activity = {
    getSPU: function () {
        cid = $("#thirdID").val();
        //根据分类ID获取商品列表
        if (cid > 0) {
            if ($("#select_spu").val() != "" && $("#select_spu").val() != undefined) {
                $("#select_spu").val("");
                $("#spuid").val("");
            }
            autoxl.bind("select_spu", activity.GetSpuList, true);
        }
    },
    GetSpuList: function (callback, event) {
        var name = $("#select_spu").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/platform/spu/getSpuStartWithName",
            type: "Post",
            data: { "classid": cid, "name": name },
            dataType: "json",
            success: function (data) {

                if (data.code == 0) {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('select_spulist', listdata);

                    if (callback) {
                        callback(html);
                    }
                } else {
                    Dalert(data.desc);
                }
            }
        });
    },
    TypeChange: function () {
        var acttype = $("#acttype").val();
        if (acttype == 0) {
            $("#divje").show();
            $("#divsp").hide();
        }
        else {
            $("#divje").hide();
            $("#divsp").show();
        }
    },
    Init: function () {
        cid = $("#tid").val();
        //根据分类ID获取商品列表
        if (cid > 0) {
            autoxl.bind("select_spu", activity.GetSpuList, true);
        }
    }
}

//按钮事件绑定
$(function () {
    $("input[name=Save]").bind("click", Save);
})
//保存
function Save() {
    var action = $("#action").val();
    if (checkform()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/platform/Fullcut/" + action,
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

                    Dalert("保存成功！", "", function () { window.location.href = 'yxgl_FullCutList'; });

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
            count: {
                digits: true
            },
            fullprice: {
                number: true
            },
            subprice: {
                number: true
            },
            stock: {
                required: true,
                digits: true
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
            count: {
                digits: "必须输入整数"
            },
            fullprice: {
                number: true
            },
            subprice: {
                number: true
            },
            stock: {
                required: "库存不能为空",
                digits: "必须输入整数"
            },
            starttime: {
                required: "开始时间不能为空",
            },
            endtime: {
                required: "结束时间不能为空",
            }
        }
    });
}
//保存前参数验证
function checkform() {
    if (check().form()) {
        //判断是否选择商品
        var ret = true;
        if ($("#acttype").val() == "1") {
            if ($("#select_spu").attr("data") == "" || $("#select_spu").attr("data") == undefined) {
                if ($("input[name=spuid]").val() == "" || $("input[name=spuid]").val() == undefined) {
                    Dalert("请选择商品");
                    $("#select_spu").focus();
                    ret = false;
                }
            } else {
                $("input[name=spuid]").val($("#select_spu").attr("data"));
            }
        }
        return ret;
    }
}