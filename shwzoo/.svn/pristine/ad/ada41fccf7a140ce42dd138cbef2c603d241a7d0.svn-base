//组合商品列表
var pcount, pindex, psize = size_product;
var pack = {
    bind: function () {
        autoxl.bind("select_sku", pack.getSkuStartwithName, true);
        $("input[name=btnadd]").bind("click", function () { $("#the-package").show(); });
        $("input[name=btncancel]").bind("click", function () { $("#the-package").hide(); });
        $("input[name=Save]").bind("click", pack.Save);
        pack.getlist();
    },
    getlist: function () {
        var packid = $("#packageid").val();
        $.ajax({
            url: "/platform/package/getSkuByPackageID",
            type: "Post",
            data: { "packageid": packid},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('packlist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    //绑定删除事件
                    pack.unit();
                }
            },
            error: function () {

            }
        });
    },
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", pack.del);
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/platform/package/deleteSkuPackage",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 1000);
                        location.reload();
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    },
    getSkuStartwithName: function (callback, event) {
        var packid = $("#packageid").val();
        var name = $("#select_sku").val();
        if (event)
            name += String.fromCharCode(event.keyCode);
        $.ajax({
            url: "/platform/package/getSkuStartwithName",
            type: "Post",
            data: { "name": name, "packageid": packid },
            dataType: "json",
            success: function (data) {

                if (data.code == 0) {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('select_skulist', listdata);
                    if (callback) {
                        callback(html);
                    }
                } else {
                    Dalert(data.desc);
                }
            }
        });
    },
    //保存
    Save: function () {
        var skuid = $("#select_sku").attr("data");
        if (skuid == "" || skuid == undefined) {
            $("#select_sku").focus();
        } else {
        	  $("#skuid").val(skuid);
            //防止重复提交 点击保存后隐藏按钮
            $("input[name='Save']").hide();
            $.ajax({
                url: "/platform/package/addSkuPackage",
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
                        $("input[name='Save']").show();
                        $("#select_sku").val("");
                        $("#price").val("");
                        Dalert("保存成功！");
                        location.reload();

                    }
                },
                error: function () {
                }
            });
        }
    }
}