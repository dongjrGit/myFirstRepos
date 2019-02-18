/*添加支付方式*/
$(document).ready(function () {
    Init.bind();
    var id = GetQueryStringByName("id");
    GetForm(id)
    //验证表单
    function check() {
        return $("#payForms").validate({
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
        location.href = "/platform/controlpanel/paySet_list";
    });
});
//初始化货币类型
var Init = {
    bind: function () {
        $.ajax(({
            type: "post",
            url: "/ConfigSet/GetMoneyTypeList",
            dataType: "json",
            success: function (rsl) {
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
//添加支付设置
function add() {
	
    $("#savebtn").hide();
    var pn = $("#PayName").val();
    var mn = $("#MoneyName").find("option:selected").text();
    var pd = $("#Poundage").val();
    var ip = $("#IsPersent").is(":checked");

    var io = $("input[name='isOnline']:checked").val();
    var it = $("#InterfaceType").val();
    var dl = $("#Display").val();
    var dp = $("#Discription").val();
    $.ajax({
        type: "post",
        url: "/platform/payset/addPaySet",
        dataType: "json",
        data: {
            payName: pn, 
            moneyName: mn,
            poundage: pd, 
            isPersent: ip, 
            isOnline: io, 
            interfaceType: it, 
            display: dl, 
            discription: dp
        },
        success: function (rsl) {
            if (rsl.code == 0) {
                Dalert(rsl.desc, "", backhref);
            } else {
                Dalert(rsl.desc); $("#savebtn").show();
            }
        },
        error: function (e) {
            // Dalert(e.statusText);
        }
    });
}
//编辑支付设置
function edit(id) {
    var pn = $("#PayName").val();
    var mn = $("#MoneyName").find("option:selected").text();
    var pd = $("#Poundage").val();
    var ip = $("#IsPersent").is(":checked");

    var io = $("input[name='isOnline']:checked").val();
    var it = $("#InterfaceType").val();
    var dl = $("#Display").val();
    var dp = $("#Discription").val();
    $.ajax({
        type: "post",
        url: "/platform/payset/updatPaySetById",
        dataType: "json",
        data: {
            id: id, payName: pn, moneyName: mn, poundage: pd, isPersent: ip, isOnline: io, interfaceType: it, display: dl, discription: dp
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
//返回支付设置列表
function backhref() {
    location.href = "/platform/controlpanel/paySet_list";
}