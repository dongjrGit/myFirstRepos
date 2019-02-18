//商品抢购类型列表

var pcount, pindex;
var psize = size_product;
//绑定事件
var BuyType = {
    bind: function () {
        $("#addnew_submit").bind("click", BuyType.save);
        $("#cancel_button").bind("click", BuyType.cancel);
        BuyType.getList(1);
    },
    //获取商品抢购类型列表
    getList: function (index) {
        $.ajax({
            url: "/ProductBuyType/P_GetList",
            type: "Post",
            data: { "index": index, "size": psize },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('typelist', listdata);
                    $("#list_title").html(html);
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    BuyType.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "BuyType.getList"));
                }
            },
            error: function () {

            }
        })
    },
    //绑定列表事件
    unit: function () {
        $(".del").each(function () {
            $(this).bind("click", BuyType.del);
        });
        $(".addnew_button").first().bind("click", function () {
            $("#addnew_order").val("1");
            $("#addnew_id").val("0");
            $("input[name='addnew_ischeckbox']").first().attr("checked", "checked");
            $("#addnew_tr").show();
        });
        $(".edit").each(function () {
            $(this).bind("click", BuyType.unit_shownew);
        });
    },
    unit_shownew: function () {
        var name = $(this).attr("data_name");
        var id = $(this).attr("data_id");
        var order = $(this).attr("data_order");
        var ischeckbox = $(this).attr("data_status");
        $("#addnew_name").val(name);
        $("#addnew_order").val(order);
        $("#addnew_id").val(id);
        $("input[name='addnew_ischeckbox']").each(function () {
            if ($(this).val() == ischeckbox)
                $(this).attr("checked", "checked");
        })
        $("#addnew_tr").show();
    },
    cancel: function () {
        $(this).bind("click", function () { $("#addnew_tr").hide(); });
    },
    //删除
    del: function () {
        if (confirm("确定要删除吗？")) {
            var id = $(this).attr("data");
            $.ajax({
                url: "/ProductBuyType/P_Delete",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    Dalert(data.Desc);
                    if (data.Code == 0) {
                        BuyType.getList(1);
                    }
                }
            })
        }
    },
    //添加搜索属性
    save: function () {
        var id = $("#addnew_id").val();
        var name = $("#addnew_name").val();
        var order = $("#addnew_order").val();
        var ischeckbox = $("input[name='addnew_ischeckbox']:checked").val();
        var sUrl = "/ProductBuyType/P_Update";
        if (id == "0") {
            sUrl = "/ProductBuyType/P_Add";
        }
        $.ajax({
            url: sUrl,
            type: "Post",
            data: { "id": id, "name": name, "status": ischeckbox, "orderby": order },
            dataType: "json",
            success: function (data) {
                Dalert(data.Desc);
                if (data.Code == 0) {
                    location.reload();
                }
            }
        })
    }
}