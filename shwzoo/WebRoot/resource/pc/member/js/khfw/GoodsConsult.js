//商品咨询列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var GoodConsultList = {
    bind: function (index) {
        $.ajax(({
            type: "post",
            url: "/pc/khFwmemb/getGoodConsult",
            dataType: "json",
            data: { "page": index, 
            	   "size": psize,
            	   "ch":1},
            success: function (rsl) {
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
                    }
                   
                    var html = template('goodconsultlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#goodconsultlist_title").parent().children().each(function () {
                        if ($(this).attr('id') != "goodconsultlist_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#goodconsultlist_title").after(html);

                    indexlog = index;
                    pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
                    //alert(pindex);
                    $("#pager").html(pagination(pcount, pindex, psize, "goodconsult_pagelist"));
                }
                else {
                    Dalert(rsl.desc);
                }
            },
            error: function (e) {
               
            }
        }));
    }
}
//分页回调
function goodconsult_pagelist(index) {
    if (indexlog != index) {
        GoodConsultList.bind(index);
    }
}