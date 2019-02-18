//库存商品关联规格值

var SSList = {
    bind: function () {
        $(".addnew_button").first().bind("click", function () {
            SSList.specsChange();
            $("#addnew_tr").show();
        });
        $(".del").each(function () {
            $(this).bind("click", SSList.del);
        });
        $("#addnew_submit").bind("click", SSList.save);
        $("#addnew_specs").bind("change", SSList.specsChange);
    },
    specsChange: function (selectid) {
        var specsid = $("#addnew_specs").val();
        $.ajax({
            url: "/SpecsValue/GetListBySpecsID",
            type: "Post",
            data: { "specsID": specsid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('specs_values', listdata);
                    $("#addnew_values").html(html);
                    if(selectid)
                        $("#addnew_values").val(selectid);
                }
            }
        });
    },
    del: function () {
        if (confirm("确定要删除吗？")) {
            var skuid = $("#sku_id").val();
            var valueid = $(this).attr("data");
            $.ajax({
                url: "/Product/DelSKU_Specs",
                type: "Post",
                data: { "skuID": skuid, "specsValueID": valueid },
                dataType: "json",
                success: function (data) {
                    //Dalert(data.Desc);
                    if (data.Code == 0) {
                        //location.reload();
                        Dalert(data.Desc, "", refresh);
                    } else {
                        Dalert(data.Desc);
                    }
                }
            })
        }
    },
    save: function () {
        var skuid = $("#sku_id").val();
        var valueid = $("#addnew_values").val();
        var specsid = $("#addnew_specs").val();
        $.ajax({
            url: "/Product/SaveSSV",
            type: "Post",
            data: { "skuid": skuid, "valueid": valueid, "specsid": specsid },
            dataType: "json",
            success: function (data) {
                //Dalert(data.Desc);
                if (data.Code == 0) {
                    //location.reload();
                    Dalert(data.Desc, "", refresh);
                } else {
                    Dalert(data.Desc);
                }
            }
        })
    }
}
function refresh() {
    location.reload();
}