var pcount;
var pindex;
var psize = 10;
var spuid;
var ptype = 0;
//商品评论
var SPUComment = {
    bind: function () {
        //分页绑定
        pcount = $("#comment_count").val();
        pindex = $("#comment_index").val();
        $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
    },
    //商品评论列表
    getList: function (index, star) {
        spuid = $("#spuid").val();
        
        $.ajax(({
            type: "post",
            url: "/pc/products/procomlist",
            dataType: "json",
            data: { "sid": spuid, "page": index, "size": psize, "star": star },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data.list
                    }
                    var pagehtml=template('commentlist', listdata);
                    $("#ul_commentlist").html(pagehtml);

                    //星级绑定
                    $(".div_star").each(function (e) {
                        var val = parseInt($(this).attr("star-data"));
                        var html1 = "";
                        for (var i = 0; i < val; i++) {
                            html1 += '<span class="xing-yellow"></span>';
                        }
                        for (var i = 0; i < 5 - val; i++) {
                            html1 += '<span class="xing-huise"></span>';
                        }
                        $(this).html(html1);
                    });

                    //评论回复绑定
                    $(".p_reply").click(function () {
                        $(this).parents(".l_hfnrall").find(".l_hfnrcon").toggle();
                    });
                    $(".btn_reply").click(function () {
                        // var content = $(this).parents(".l_hfnrall").find(".textarea_reply").val();
                        // if (content == "") {
                            // alert("请输入回复内容");
                            // return;
                        // }
// 
                        // if (!user.IsLogin())
                        // {
                            // //显示登录弹出层
                            // showlogindiv();
                            // return;
                        // }

                        // var parentid = $(this).attr("parentid-val");
                        // var byreplyuserid = $(this).attr("byreplyuserid-val");
                        // var commentID = $(this).attr("commentid-val");
                        // SPUComment.replyComment(commentID, parentid, byreplyuserid, content)
// 
                        // $(this).parents(".l_hfnrall").find(".l_hfnrcon").toggle();
                        // $(this).parents(".l_hfnrall").find(".textarea_reply").val("");
                        // var obj = $(this).parents(".l_hfnrall").find(".a_replycount");
                        // var cout = parseInt(obj.attr("replycount-val")) + 1;
                        // obj.attr("replycount-val", cout);
                        // obj.text("回复（" + cout + "）");         
                               });

                    //分页绑定
                    pcount = rsl.maxRow;
                    pindex = rsl.pageIndex;
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));

                }
                else {
                    alert(rsl.desc);
                }
            },
            error: function (e) {
                alert(e.statusText);
            }
        }));
    },
    //评论回复
    replyComment: function (commentID, parentid, byreplyuserid, content) {
        $.ajax(({
            type: "post",
            url: "/Comment/B_AddReply",
            dataType: "json",
            data: { CommentID: commentID, ReplyType: 2, ParentID: parentid, ByReplyUserID: byreplyuserid, Content: content },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    return true;
                }
                else {
                    return false;
                }
            },
            error: function (es) {
                return false;
            }
        }));
    }
}

function pagelist(index) {
    SPUComment.getList(index, ptype);
}