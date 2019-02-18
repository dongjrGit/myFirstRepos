
//广告列表
$(function () {
    $(".chaxun").bind("click", add);
})

var pcount;
var pindex;
var psize = 10;
var gglist = {
    bind: function (index) {
        $.ajax({
            url: "/Advertising/P_GetList",
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

                    var html = template('gglist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#gg_title").parent().children().each(function () {
                        if ($(this).attr('id') != "gg_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#gg_title").after(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "gglist.bind"));
                }
            },
            error: function () {
                Dalert("数据加载失败");
            }
        });
    }
}


//添加
function add() {
    self.location = "AdvertisingSave";
}
//删除
function Del(ggid) {

    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/Advertising/P_Del",
            type: "Post",
            data: { id: ggid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                }
                else {
                    gglist.bind(pindex);
                }
            },
            error: function () {
                Dalert("删除失败");
            }
        });
    }

}
//修改排序
function setOrder(id, ob) {
    var obtext = $("#" + ob).val();
    $.ajax({
        url: "/Advertising/P_ChangeOrder",
        type: "Post",
        data: { "id": id, "orderby": obtext },
        dataType: "json",
        success: function (data) {
            Dalert(data.Desc);
        },
        error: function () {
            Dalert("修改排序失败");
        }
    });
}








