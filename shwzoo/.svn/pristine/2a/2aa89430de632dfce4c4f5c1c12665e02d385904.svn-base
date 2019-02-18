
//数据初始化
var Init = {
    bind: function () {
    	
        //店铺下拉数据绑定
        autoxl.bind("select_shop", getShopStartwithName);

        //店铺违规类型数据绑定
        $.ajax(({
            type: "post",
            url: "/platform/shop/getViolations",
            dataType: "json",
            data: {},
            success: function (rsll) {
                if (rsll.code == 0) {
                    var listdata = {
                        list: rsll.data
                    }
                    var html = template('violationselect', listdata);
                    $("#select_violation").append(html);
                }
                else {
                    Dalert(rsll.desc);
                }
            },
            error: function (es) {
              
            }
        }));

        //校验
        $("#submit_ok").bind("click", function () {
            if (!Check().form()) {
                return;
            }
            AddShopVoilation();
        })
    }
}

//校验
function Check() {
    return $("#addshopviolationForm").validate({
        rules: {
            select_shop: {
                required: true
            },
            select_violation: {
                selectRequired: true
            },
            text_violationdescribe: {
                required: true
            },
            text_violationresult: {
                required: true
            }
        },
        messages: {
            select_shop: {
                required: "请输入店铺名称"
            },
            select_violation: {
                selectRequired: "请选择违规类型"
            },
            text_violationdescribe: {
                required: "请输入违规描述"
            },
            text_violationresult: {
                required: "请输入违规处理"
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        debug: true
    })
}
//提交
function AddShopVoilation() {
    var shopid = $("#select_shop").attr("data");
    var violationtype = $("#select_violation").val();
    var violationdescribe = $("#text_violationdescribe").val();
    var violationresult = $("#text_violationresult").val();
    $.ajax(({
        type: "post",
        url: "/platform/shop/addVoilationShop",
        dataType: "json",
        data: { "shopid": shopid,
        		"violationtype": violationtype, 
        		"describe": violationdescribe, 
        		"result": violationresult },
        success: function (rsl) {
            if (rsl.code == 0) {
                Dalert("保存成功");
                $("#text_shopname").attr("value", "");
                $("#select_violation").attr("value", "-1");
                $("#text_violationdescribe").attr("value", "");
                $("#text_violationresult").attr("value", "");
                window.location.href = "/platform/shop/showViolationShop";
            }
            else {
                Dalert(rsl.desc);
            }
        },
        error: function (e) {

        }
    }));
}

//获取店铺下拉列表
function getShopStartwithName(callback, event) {
    var name = $("#select_shop").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/shop/conditionSelect",
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
                Dalert(data.data);
            }
        }
    });
}

