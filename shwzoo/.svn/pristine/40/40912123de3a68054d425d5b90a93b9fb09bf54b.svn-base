/**
 * 获取分配优惠卷列表
 */
var pcount, pindex, psize = size_shop;
var assignc = {
    bind: function () {
        $("input[name=btnsearch]").bind("click", function () { assignc.getlist(1); });
        assignc.getlist(1); 
    },
    getlist: function (index) {
        //分类路径 商品名称 开始时间-开始 开始时间-结束  结束时间-开始  结束时间-结束
        var code = "",s1, e1;
        code = $("#code").val();
        s1 = $("#ends").val();
        e1 = $("#ende").val();
        $.ajax({
            url: "/platform/couponnew/getVoucherList",
            type: "Post",
            data: { "page": index, "size": psize, "endf": s1, "endt": e1,"code":code
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('assignlist', listdata);

                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "assignc.getlist"));
                }
            },
            error: function () {

            }
        });
    }
}