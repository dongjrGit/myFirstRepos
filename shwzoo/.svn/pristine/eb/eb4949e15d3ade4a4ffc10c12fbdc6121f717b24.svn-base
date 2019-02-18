/*添加店铺分类*/
$(document).ready(function () {
    Init.bind();
    var id = GetQueryStringByName("id");
    GetForm(id)
    //验证表单
    function check() {
        return $("#payForm").validate({
            rules: {
                PayName: {
                    rangelength: [2, 10],
                    required: true
                },
                MoneyName: {
                    required: true
                },
                Poundage: {
                    required: true
                },
                InterfaceType: {
                    required: true
                },
                Display: {
                    required: true
                },
                Discription: {
                	rangelength:[1, 500],
                    required: true
                }
            },
            //设置提示信息
            messages: {
                PayName: {
                    rangelength: "支付方式名称的长度限制在2-10个字符以内",
                    required: "支付方式名称不为空"
                },
                MoneyName: {
                    required: "请选择交易货币"
                },
                Poundage: {
                    required: "必填"
                },
                InterfaceType: {
                    required: "必填"
                },
                Display: {
                    required: "必填"
                },
                Discription: {
                	rangelength: "详细描述的长度限制在1-500个字符以内",
                    required: "必填"
                }
            },
            //设置错误信息存放标签
            errorElement: "label",
            debug: true,//只验证不提交
            //设置验证触发事件
            focusInvalid: true

        })
    }
    //添加/编辑
    function save() {
        if (id > 0) {
            edit(id)
        } else {
            add()
        }
    };
    //保存
    $("#savebtn").click(function () {
        if (check().form()) {
            save();
        }
    });
    //返回
    $("#backbtn").click(function () {
        location.href = "/platform/shop/showShopCategoryList";
    });
});
//初始化货币类型
var Init = {
    bind: function () {
        $.ajax(({
            type: "post",
            url: "/ConfigSet/GetMoneyTypeList",
            dataType: "json",
            success: function (rsl) {s
                if (rsl.code == 0) {
                    var listdata = { list: rsl.data }
                    var html = template('moneyselect', listdata);
                    $("#MoneyName").append(html);
                }
                else {
                    //Dalert(rsl.Desc);
                }
            },
            error: function (e) {
                //Dalert(e.statusText);
            }
        }));
    }
}
//根据id获取支付设置 id：支付设置表id
function GetForm(id) {
    if (id > 0) {
        $.ajax({
            type: "post",
            url: "/platform/payset/selectPaySetById",
            dataType: "json",
            data: { id: id },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var p = rsl.data;
                    $("#PayName").attr("value", p.payname);
                    $("#MoneyName option").each(function () {
                        if ($(this).text() == p.moneyname) {
                            $(this).attr("selected", true);
                            return false;
                        }
                    });
                    $("#Poundage").attr("value", p.poundage);
                    if (p.ispersent == true) {
                        $("#IsPersent").attr("checked", "true")
                    } else { $("#IsPersent").removeAttr("checked"); }
                    if (p.isonline == "1") {
                        $("input[name='isOnline']").first().attr("checked", "checked");
                        $("input[name='isOnline']").last().removeAttr("checked");
                    } else {
                        $("input[name='isOnline']").last().attr("checked", "checked");
                        $("input[name='isOnline']").first().removeAttr("checked");
                    }

                    $("#InterfaceType").attr("value", p.interfacetype);
                    $("#Display").attr("value", p.display);
                    $("#Discription").attr("value", p.discription);
                }
                else {
                    //Dalert(rsl.Desc);
                }
            },
            error: function (e) {
                //Dalert(e.statusText);
            }
        });
    }
}
//添加
function add() {
	var reg=/^[\u0391-\uFFE5A-Za-z0-9]+$/;
	var categoryName = $("#categoryName").val();
	if(!reg.test(categoryName)){
		Dalert("店铺分类格式错误");
		return false;
	}
    $.ajax({
        type: "post",
        url: "/platform/shop/addCategoryName",
        dataType: "json",
        data: {
            name : categoryName
        },
        success: function (rsl) {
            if (rsl.code == 0) {
                Dalert(rsl.desc, "", backhref);
            } else {
                Dalert(rsl.desc);
            }
        },
        error: function (e) {
            // Dalert(e.statusText);
        }
    });
}
//编辑
function edit(id) {
    var categoryName = $("#categoryName").val();
    $.ajax({
        type: "post",
        url: "/platform/shop/updatCategoryById",
        dataType: "json",
        data: {
            id: id, categoryName: categoryName
        },
        success: function (rsl) {
            if (rsl.code == 0) {
                Dalert(rsl.desc, "", backhref);
            } else {
                Dalert(rsl.desc);
            }
        },
        error: function (e) {
            //Dalert(e.statusText);
        }
    });
}
//返回列表
function backhref() {
    location.href = "/platform/shop/showShopCategoryList";
}