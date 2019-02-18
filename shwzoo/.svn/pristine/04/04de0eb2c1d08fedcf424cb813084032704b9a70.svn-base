//优惠卷编辑
var cid;
var coupon = {
    getSPU: function (callback, event) {
        cid = $("#thirdID").val();
        //根据分类ID获取商品列表
        if (cid > 0) {
            if ($("#select_spu").val() != "" && $("#select_spu").val() != undefined) {
                $("#select_spu").val("");
                $("#usetypeid").val("");               
            }
            autoxl.bind("select_spu", coupon.GetSpuList,true);
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
        var coupontype = $("#coupontype").val();
        if (coupontype == 0) {
            $("#divtype").hide();
        }
        else {
            $("#divtype").show();
        }
    },
    Type2Change: function () {
        var usetype = $("#usetype").val();
        if (usetype == 0) {
            $("#divusetype1").show();
            $("#divusetype2").show();
        }
        if (usetype == 1) {
            $("#divusetype1").show();
            $("#divusetype2").hide();
        }
        if (usetype == 2 || usetype == 3) {
            $("#divusetype1").hide();
            $("#divusetype2").hide();
        }
    },
    Init: function () {
        coupon.TypeChange();
        coupon.Type2Change();
        if ($("#usetype").val() == 0) {
            cid = $("#tid").val();
            //根据分类ID获取商品列表
            if (cid > 0) {
                autoxl.bind("select_spu", coupon.GetSpuList, true);
            }
        }
    },
    getUserLevel: function () {
        var userlevel = $("#getuserlevel").val();
        $.ajax({
            url: "/platform/memberlevel/getLevelList",
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
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
    }
}
//按钮事件绑定
$(function () {
    $("input[name=Save]").bind("click", Save);
    coupon.getUserLevel();
})
//保存
function Save() {
    var action = $("#action").val();
    if (checkform()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/platform/coupon/" + action,
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

                    Dalert("保存成功！", "", function () { window.location.href = 'yxgl_CouponList'; });

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
            endday: {
                digits: true
            },
            count: {
                required: true,
                digits: true
            },
            getcount: {
                required: true,
                digits: true
            },
            fackvalue: {
                required: true,
                number: true
            },
            mjprice: {
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
            name: {
                required: "名称不能为空",
            },
            endday: {
                digits: "必须输入整数"
            },
            count: {
                required: "数量不能为空",
                digits: "必须输入整数"
            },
            getcount: {
                required: "限领张数不能为空",
                digits: "必须输入整数"
            },
            fackvalue: {
                required: "数量不能为空",
                number: true
            },
            mjprice: {
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
        //判断是否选择分类
        if ($("#usetype").val() == "1") {
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