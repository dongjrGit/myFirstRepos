var cid;
var probuy = {
    getSPU: function () {
        cid = $("#thirdID").val();
        //根据分类ID获取商品列表
        if (cid > 0) {
            $.ajax({
                url: "/Product/P_GetSPUListByQuery",
                type: "Post",
                data: { "page": 1, "size": 100, "cid": cid },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        Dalert(data.Desc);
                    } else {
                        var listdata = {
                            list: data.Data
                        }
                        var html = '<option value="0" id="defaultspu" selected>无</option>' + template('spulist', listdata);
                        $("#spuid").html(html);

                        //var spuid = $("#spuid").val();
                        //if (spuid > 0) {
                        //    $("#spuid option").each(function () {
                        //        if ($(this).val() == Spuid) {
                        //            $(this).attr("selected", "selected");
                        //        } else {
                        //            $(this).removeAttr("selected");

                        //        }

                        //    });
                        //    probuy.getSKU();
                        //}
                    }
                },
                error: function () {
                    //alert("数据加载失败");
                }
            });
        }
        else {

            $("#spuid").html('<option value="" id="defaultspu" selected>无</option>');
            $("#skuid").html('<option value="" id="defaultsku" selected>无</option>');

        }
    },
    getSKU: function () {
        var spuid = $("#spuid").val();
        //根据分类ID获取商品列表
        if (spuid > 0) {
            $.ajax({
                url: "/Product/P_GetSKUBySpu",
                type: "Post",
                data: { "SpuID": spuid },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        Dalert(data.Desc);
                    } else {
                        var listdata = {
                            list: data.Data
                        }
                        var html = '<option value="0" id="defaultsku" selected>无</option>' + template('skulist', listdata);
                        $("#skuid").html(html);
                        var skuid = $("#SkuID").val();
                        if (skuid > 0) {
                            $("#skuid option").each(function () {
                                if ($(this).val() == skuid) {
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
    },
    Init: function () {
        cid = $("#ClassID").val();
        //根据分类ID获取商品列表
        if (cid > 0) {
            $.ajax({
                url: "/Product/P_GetSPUListByQuery",
                type: "Post",
                data: { "page": 1, "size": 100, "cid": cid },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        Dalert(data.Desc);
                    } else {
                        var listdata = {
                            list: data.Data
                        }
                        var html = '<option value="0" id="defaultspu" selected>无</option>' + template('spulist', listdata);
                        $("#spuid").html(html);
                        var spuid = $("#SpuID").val();
                        if (spuid > 0) {
                            $("#spuid option").each(function () {
                                if ($(this).val() == spuid) {
                                    $(this).attr("selected", "selected");
                                } else {
                                    $(this).removeAttr("selected");

                                }

                            });
                            probuy.getSKU();
                        }
                    }
                },
                error: function () {
                    //alert("数据加载失败");
                }
            });
        }
        probuy.getType();
    },
    getType: function () {
        var typeid = $("#buytypeid").val();
        $.ajax({
            url: "/ProductBuyType/G_SysBuyType",
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
                    var html = '<option value="0" id="defaulttype" selected>无</option>' + template('typelist', listdata);
                    $("#typeid").html(html);
                    if (typeid > 0) {
                        $("#typeid option").each(function () {
                            if ($(this).val() == typeid) {
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
})
//保存
function Save() {
    var action = $("#action").val();
    if (checkform()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/ProductBuying/" + action,
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

                    Dalert("保存成功！", "", function () { window.location.href = '/Platform/gg/goods_BuyList'; });

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
            orderby: {
                required: true,
                digits: true
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
            orderby: {
                required: "排序不能为空",
                digits: "必须输入整数"
            },
            stock: {
                required: "库存不能为空",
                digits: "必须输入整数"
            },
            start: {
                required: "开始时间不能为空",
            },
            end: {
                required: "结束时间不能为空",
            }
        }
    });
}
//保存前参数验证
function checkform() {
    if (check().form()) {
        if ($("#thirdID").val() != "0") {
            $("#ClassID").val($("#thirdID").val());
            return true;
        }
        else {

            Dalert("请选择商品分类到第三级");
            return false;

        }
        if ($("#spuid").val() == "0") {
            Dalert("请选择标准商品");
            $("#spuid").focus();
            return false;
        }
    }
}