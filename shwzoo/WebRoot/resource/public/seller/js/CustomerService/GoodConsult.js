//会员列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var GoodConsultList = {
    bind: function (index) {
        $.ajax(({
            type: "post",
            url: "/GoodConsult/S_GetList",
            dataType: "json",
            data: { PageIndex: index, PageSize: psize },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
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
                    pcount = rsl.MaxRow;
                    pindex = rsl.PageIndex;
                    //alert(pindex);
                    $("#pager").html(pagination(pcount, pindex, psize, "goodconsult_pagelist"));

                    //回复商品咨询
                    $(".a_goodconsultreply").bind("click", goodconsultreply);
                }
                else {
                    Dalert(rsl.Desc);
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
//回复商品咨询
function goodconsultreply() {
    var goodconsultID = $(this).parent().find("#hidden_goodconsultid").val();
    $(".a_goodconsultreply").attr('href', "GoodConsult_Reply?goodconsultID=" + goodconsultID);
};