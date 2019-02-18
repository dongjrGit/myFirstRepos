//商品分类加载
var Class = {
    bind: function () {
        Class.getClassTree();
    },
    getClassTree: function(){
        $.ajax({
            url: "/platform/commodity/queryAllCategorys",
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('classlist', listdata);
                    $("#list_title").after(html);
                    init();
                }
            },
            error: function () {

            }
        });
    },
    //删除商品分类
    deleteClass: function (id) {
        if (confirm("是否确认删除？")) {
            $.ajax({
                url: "/platform/commodity/deleteCategory",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    Dalert(data.desc);
                    if (data.code >= 0) {
                        location.reload();
                    }
                },
                error: function () {

                }
            });
        }
    }
}