var Order = {
    bind: function () {
        $.ajax({
            url: "/Order/GetPageList",
            type: "Post",
            data: { userID: 1, page: 1, size: 10 },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {
                        list: data.Data
                    }
                    var html = template('orderlist', listdata);
                    $("#list_title").after(html);
                }
            },
            error: function () {

            }
        });
    }
}