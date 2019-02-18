//库存预警列表
var pindex=1;
var psize=20,pcount;
var SKU={
	getWarnSKUList:function(index){
		$.ajax({
            url: "/seller/shopproduct/getWarnSKUList",
            type: "Post",
            data: {"page": index, "size": psize},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('skulist', listdata);
                    $("#list_title").html(html);
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                    if (callback) {
                        callback();
                    }
                }
            }
        })
	}

}

//分页回调加载数据
function pagelist(index) {
    SKU.getSKUList(index);
}

