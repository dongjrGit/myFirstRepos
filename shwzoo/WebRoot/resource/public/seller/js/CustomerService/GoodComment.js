//会员评论列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;

var CommentList = {
    bind: function (index) {
        $.ajax(({
            type: "post",
            url: "/Comment/S_GetList",
            dataType: "json",
            data: { PageIndex: index, PageSize: psize },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    var listdata = {
                        list: rsl.Data
                    }
                    var html = template('commentlist', listdata);

                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
                    $("#commentlist_title").parent().children().each(function () {
                        if ($(this).attr('id') != "commentlist_title") {
                            this.parentNode.removeChild(this);
                        }
                    })
                    $("#commentlist_title").after(html);

                    indexlog = index;
                    pcount = rsl.MaxRow;
                    pindex = rsl.PageIndex;
                    //alert(pindex);
                    $("#pager").html(pagination(pcount, pindex, psize, "comment_pagelist"));

                    //查看评论明细
                    $(".a_commentdetail").bind("click", commentdetailshow);
                    //删除会员评论
                    //$(".a_commentdelete").bind("click", membercommentdel);
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
function comment_pagelist(index) {
    if (indexlog != index) {
        CommentList.bind(index);
    }
}

//查看评论明细
function commentdetailshow() {
    var commentID = $(this).parent().find("#hidden_commentid").val();
    $(".a_commentdetail").attr('href', "GoodComment_Detail?id=" + commentID);
};

//删除会员评论
function membercommentdel() {
    var commentID = $(this).parent().find("#hidden_commentid").val();
    $.ajax(({
        type: "post",
        url: "/Comment/S_DelComment",
        dataType: "json",
        data: { CommentID: commentID },
        success: function (rsl) {
            if (rsl.Code == 0) {
                CommentList.bind(indexlog);
            }
            else {
                Dalert(rsl.Desc);
            }
        },
        error: function (es) {
           
        }
    }));
};

