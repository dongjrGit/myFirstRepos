/**
 * 新优惠卷编辑
 */
var couponnew = {
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
    getSpuStartwithName: function (callback, event) {
        var name = $("#select_spu").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/platform/spu/getSpuStartWithShopName",
            type: "Post",
            data: { "name": name,"shopid":$("#select_shop").attr("data") },
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
        if (usetype == 2) {
            $("#divusetype1").show();
            $("#divusetype2").hide();
        }
        if (usetype == 3) {
            $("#divusetype1").hide();
            $("#divusetype2").hide();
        }
    },
    Init: function () {
    	couponnew.TypeChange();
    	couponnew.Type2Change();
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
    ///couponnew.getUserLevel();
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

                    Dalert("保存成功！", "", function () { window.location.href = 'yxgl_CouponNewList'; });

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
        var usetype = $("#usetype").val();
        if (usetype == 0) {
        	if ($("#select_shop").attr("data") == "" || $("#select_shop").attr("data") == undefined) {
                if ($("#shopid").val() == "" || $("#shopid").val() == undefined) {
                    Dalert("请选择所属店铺");
                    $("#select_shop").focus();
                    ret = false;
                }
            } else {
                $("#shopid").val($("#select_shop").attr("data"));
            }
            if ($("#select_spu").attr("data") == "" || $("#select_spu").attr("data") == undefined) {
                if ($("#spuid").val() == "" || $("#spuid").val() == undefined) {
                    Dalert("请选择商品");
                    $("#select_spu").focus();
                    ret = false;
                }
            } else {
                $("#spuid").val($("#select_spu").attr("data"));
            }
        }
        if (usetype == 2) {
        	 if ($("#select_shop").attr("data") == "" || $("#select_shop").attr("data") == undefined) {
                 if ($("#shopid").val() == "" || $("#shopid").val() == undefined) {
                     Dalert("请选择所属店铺");
                     $("#select_shop").focus();
                     ret = false;
                 }
             } else {
                 $("#shopid").val($("#select_shop").attr("data"));
             }
        }
            
        return ret;
    }
}