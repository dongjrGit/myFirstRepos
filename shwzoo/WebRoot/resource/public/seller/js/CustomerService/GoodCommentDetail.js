//评论ID
var commentID;
//初始化
var Init = {
    bind: function () {
        //获取评论ID
        commentID = GetQueryStringByName("id");
        //评论数据绑定
        BindComment();
    }
}

//评论数据绑定
function BindComment() {
    $.ajax(({
        type: "post",
        url: "/Comment/S_GetCommentDetail",
        dataType: "json",
        data: { CommentID: commentID },
        success: function (rsl) {
            if (rsl.Code == 0) {
                var listdata = {
                    list: rsl.Data
                }
                var html = '<tr class="dpplb-title"><td colspan="2">基础信息</td></tr>' + template('commentdetail', listdata);
                $("#table_commentdetail").html(html);
                //星级绑定
                $(".star_val").each(function (e) {
                    var val = parseInt($(this).attr("data-val"));
                    var html1 = "";
                    for (var i = 0; i < val; i++) {
                        html1 += ' <span class="star-lvs"></span>';
                    }
                    for (var i = 0; i < 5 - val; i++) {
                        html1 += ' <span class="star-heis"></span>';
                    }
                    $(this).html(html1);

                });
                //回复弹出文本框
                $(".reply-but").click(function () {
                    $(this).parent().siblings(".reply-con").css("display", "block");
                });
                $(".reply_ok").bind("click", ReplySubmit);
                $(".reply_canle").click(function () {
                    $(this).parent().find(".reply_text").attr("value", "");
                    $(this).parent().hide();
                });

            }
            else {
                Dalert(rsl.Desc);
            }
        },
        error: function (es) {
          
        }
    }));
}

//提交回复
function ReplySubmit() {
    var replycontent = $(this).parent().find(".reply_text").val();
    if (replycontent == "") {
        $(this).parent().hide();
    }
    else {
        var parentid = $(this).parent().parent().find(".hidden_replyid").val();
        var byreplyuserid = $(this).parent().parent().find(".hidden_byreplyuserid").val();
        var content = $(this).parent().find(".reply_text").val();
        var commentID = $(this).parent().parent().find(".hidden_commentid").val();
        $.ajax(({
            type: "post",
            url: "/Comment/S_AddReply",
            dataType: "json",
            data: { CommentID: commentID, ReplyType: 2, ParentID: parentid, ByReplyUserID: byreplyuserid, Content: content },
            success: function (rsl) {
                if (rsl.Code == 0) {
                    window.location.href = window.location.href;
                }
                else {
                    Dalert(rsl.Desc);
                }
            },
            error: function (es) {
              
            }
        }));
    }
}