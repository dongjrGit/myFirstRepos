//库存商品编辑

var SKU = {
    bind: function () {
        SKU.getSpecsList(SKU.getValuesBySKU);
    },
    //根据分类获取规格
    getSpecsList: function (callback) {
        var classID = $("#classid").val();
        $.ajax({
            url: "/seller/shopsku/getSpecsByClassID",
            type: "Post",
            data: { "classid": classID },
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('specslist', listdata);
                    $("#specslist_div").html(html);
                    if (callback)
                        callback();
                } else {
                    Dalert(data.desc);
                }
            }
        });
    },
    //根据规格获取规格值
    getValuesBySKU: function () {
        var skuid = $("#id").val();
        if (skuid > 0) {
            $.ajax({
                url: "/seller/shopsku/getValueBySkuID",
                type: "Post",
                data: { "skuid": skuid },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        if (data.data.length > 0) {
                        	 $("td[name='specs_div']").each(function () {
                                 var $div = $(this);
                                 var specsid = $div.attr("data");
                                 for (var i = 0; i < data.data.length; i++) {
                                     if (specsid == data.data[i].specsid) {
                                         var $value = $div.find("[name='specs_" + specsid + "']");
                                         if ($value.attr("type") == "text") {
                                             $value.val(data.data[i].value);
                                         } else {
                                             $value.val(data.data[i].valueid);
                                         }
                                     }
                                 }
                             })
                        }
                    } else {
                        Dalert(data.desc);
                    }
                }
            });
        }
    }
}