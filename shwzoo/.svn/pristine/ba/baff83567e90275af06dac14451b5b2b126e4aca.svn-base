//中绿基地
var pcount, pindex, psize = size_product;
var base = {
  
    getlist: function (index) {
        $.ajax({
            url: "/platform/zlbaseinfo/listpage",
            type: "Post",
            data: {
                "page": index, "size": psize
            },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                    	list: data.data
                    }

                    var html = template('baselist', listdata);
                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "gift.getlist"));
                }
            },
            error: function () {

            }
        });
    },
   
    del: function (id) {
        if (confirm("确定要删除吗？")) {
            $.ajax({
                url: "/platform/coupon/deletecard",
                type: "Post",
                data: { "id": id },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                    	Dalert(rsl.desc, "", refresh);
                    } else {
                        Dalert(data.desc);
                    }
                }
            })
        }
    } 
}
 
//页面刷新
function refresh() {
	location.href="/platform/market/giftcard_list";
}
