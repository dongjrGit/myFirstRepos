var pcount, pindex, psize = size_reports;
var sid;
var sslist = {
    bind: function (index,sellerid) {
        $.ajax({
            url: "/Order_TJ/GetSalesSku",
            type: "Post",
            data: { "page": index, "size": psize, "sellerid": sellerid },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                    var listdata = {

                        list: data.Data
                    }

                    var html = template('spxllist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#spxl_title").parent().children().each(function () {
                        if ($(this).attr('id') != "spxl_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    //html 填充
                    $("#spxl_title").after(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = data.MaxRow;
                    pindex = data.PageIndex;
                    sid = sellerid;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                }
            },
            error: function () {

            }
        });
    }
}

function pagelist(index)
{
    sslist.bind(index, sid);
}