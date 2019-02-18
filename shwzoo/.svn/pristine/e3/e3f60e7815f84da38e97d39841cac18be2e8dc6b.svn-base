/**
 * 分配优惠劵
 */
//优惠卷编辑
var cid;
var couponassign = {
    getSPU: function (callback, event) {
        cid = $("#thirdID").val();
        //根据分类ID获取商品列表
        if (cid > 0) {
            if ($("#select_spu").val() != "" && $("#select_spu").val() != undefined) {
                $("#select_spu").val("");
                $("#usetypeid").val("");               
            }
            autoxl.bind("select_spu", couponassign.GetSpuList,true);
        }
    },
    GetSpuList: function (callback, event) {
        var name = $("#select_spu").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/platform/couponnew/getSpuStartWithName",
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
    /*
    callback 成功 有数据时 调的方法 
    event 事件
    */
    getShopStartwithName: function (callback, event) {
        var name = $("#select_shop").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/platform/couponnew/getShopStartWithName",
            type: "Post",
            data: { "name": name },
            dataType: "json",
            success: function (data) {

                if (data.code == 0) {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('select_shoplist', listdata);

                    if (callback) {
                        callback(html);
                    }
                } else {
                    Dalert(data.desc);
                }
            }
        });
    },
    Type2Change: function () {
        var usetype = $("#usetype").val();
        if (usetype == 0) {
            $("#divusetype1").show();
            $("#divusetype2").show();
            $("#divusetype3").hide();
        }
        if (usetype == 1) {
            $("#divusetype1").show();
            $("#divusetype2").hide();
            $("#divusetype3").hide();
        }
        if (usetype == 2) {
            $("#divusetype1").hide();
            $("#divusetype2").hide();
            $("#divusetype3").show();
            autoxl.bind("select_shop", couponassign.getShopStartwithName, true);
        }
        if (usetype == 3) {
            $("#divusetype1").hide();
            $("#divusetype2").hide();
            $("#divusetype3").hide();
        }
    }
}
//按钮事件绑定
$(function () {
    $("input[name=Save]").bind("click", Save);
    autoxl.bind("select_spu", couponassign.GetSpuList, true);
})
//保存
function Save() {
    var action = $("#action").val();
    if (checkform()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/platform/couponnew/" + action,
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

                    Dalert("保存成功！", "", function () { window.location.href = 'yxgl_AssignCoupon'; });

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
            count: {
                required: true,
                digits: true,
                max: Number($("#lastcount").val())
            }
        },
        message: {
            count: {
                required: "数量不能为空",
                digits: "必须输入整数",
                max:"不能大于可分配数量"
            }
        }
    });
}
//保存前参数验证
function checkform() {
    if (check().form()) {
        //判断是否选择商品
        var ret = true;
        if ($("#usetype").val() == "0") {
            if ($("#select_spu").attr("data") == "" || $("#select_spu").attr("data") == undefined) {
                if ($("#usetypeid").val() == "" || $("#usetypeid").val() == undefined) {
                    Dalert("请选择商品");
                    $("#select_spu").focus();
                    ret = false;
                }
            } else {
                $("#usetypeid").val($("#select_spu").attr("data"));
            }
            
        }
        else if ($("#usetype").val() == "2") {
            if ($("#select_shop").attr("data") == "" || $("#select_shop").attr("data") == undefined) {
                    Dalert("请选择店铺");
                    $("#select_shop").focus();
                    ret = false;
            } else {
                $("#usetypeid").val($("#select_shop").attr("data"));
            }
            
        }
        //判断是否选择分类
        else if ($("#usetype").val() == "1") {
            if ($("#thirdID").val() != "0") {
                $("#usetypeid").val($("#thirdID").val());
            }
            else {
                if ($("#secondID").val() != "0") {
                    $("#usetypeid").val($("#secondID").val());
                }
                else {
                    if ($("#firstID").val() != "0") {
                        $("#usetypeid").val($("#firstID").val());
                    }
                    else {
                        Dalert("请选择商品分类");
                        $("#firstID").focus();
                        ret = false;
                    }
                }
            }

        }
        return ret;
    }
}