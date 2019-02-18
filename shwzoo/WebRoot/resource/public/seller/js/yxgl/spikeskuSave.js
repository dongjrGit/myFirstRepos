//按钮事件绑定
$(function () {
    $("input[name=Save]").bind("click", Save);
    autoxl.bind("select_spu", getSpuStartwithName, true);
})
//保存
function Save() {
    var action = $("#action").val();
    if (check().form()) {
        if ($("#select_spu").attr("data") == "" || $("#select_spu").attr("data") == undefined) { alert("请选择商品"); $("#select_spu").focus(); }
        else {
            $("#spuid").val($("#select_spu").attr("data"));
            //防止重复提交 点击保存后隐藏按钮
            $("input[name='Save']").hide();
            $.ajax({
                url: "/seller/shopspike/" + action,
                type: "Post",
                data: $("#form").serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        $("input[name='Save']").show();
                        Dalert(data.desc);
                    }
                    else {

                        Dalert("保存成功！", "", function () { window.location.href = 'yxgl_SpikeSpuList?id=' + $("#id").val(); });

                    }
                },
                error: function () {
                }
            });
        }

    }
}
//保存前参数验证
function check() {
    return $("#form").validate({
        rules: {
            select_sku: "required",
            price: {
                required: true,
                number: true
            },
            count: {
                required: true,
                digits: true
            },
            orderby: {
                required: true,
                digits: true
            },
        },
        message: {
            select_sku: { required: "商品名称不能为空" },
            price: { required: "价格不能为空", number: true },
            count: {
                required: "数量不能为空",
                digits: true
            },
            orderby: {
                required: "排序不能为空",
                digits: true
            }
        }
    });
}
function getSpuStartwithName(callback, event) {
    var spikeid = $("#id").val();
    var name = $("#select_spu").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/seller/ShopSpike/getSpuStartwithName",
        type: "Post",
        data: { "name": name, "spikeid": spikeid },
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
}
function getSpuPriceStartwithName(){
	var spikeid = $("#id").val();
    var name = $("#select_spu").val();
    $.ajax({
        url: "/seller/ShopSpike/getSpuPriceStartwithName",
        type: "Post",
        data: { "name": name, "spikeid": spikeid },
        dataType: "json",
        success: function (rel) {
            if (rel.code == 0) {
                $("#presentPrice").val(rel.data);
            } else {
                Dalert(data.desc);
            }
        }
    });
}



