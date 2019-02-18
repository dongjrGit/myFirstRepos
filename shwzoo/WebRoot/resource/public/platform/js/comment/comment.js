//会员评论列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;

var CommentList = {
    bind: function (index) {
        var shopname = $("#select_shopname").val();
        var proname = $("#select_proname").val();
        var ordernum = $("#select_ordernum").val();
        var buyername = $("#select_buyername").val();
        var begin = $("#select_begin").val();
        var end = $("#select_end").val();
        $.ajax(({
            type: "post",
            url: "/platform/membercommen/queryMemberComment",
            dataType: "json",
            data: { pageindex: index, pagesize: psize, shopname: shopname, proname: proname, ordernum: ordernum, buyername: buyername, starttime: begin, endtime: end,status:0 },
            success: function (rsl) {
                if (rsl.code == 0) {
                    var listdata = {
                        list: rsl.data
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
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = rsl.maxRow;
                    pindex = rsl.pageIndex;
                    //alert(pindex);
                    $("#pager").html(pagination(pcount, pindex, psize, "comment_pagelist"));

                    //查看评论明细
                    $(".a_commentdetail").bind("click", commentdetailshow);
                    //删除会员评论
                    $(".a_commentdelete").bind("click", function () {
                        var commentID = $(this).parent().find("#hidden_commentid").val();
                        ConfirmShow("确定要删除吗？", membercommentdel, commentID);
                    });
                    
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
    $(".a_commentdetail").attr('href', "/platform/member/showMemberCommentDetail?type=2&id=" + commentID);
};

//删除会员评论
function membercommentdel(commentID) {
    $.ajax(({
        type: "post",
        url: "/platform/membercommen/delComment",
        dataType: "json",
        data: { commentid: commentID },
        success: function (rsl) {
            if (rsl.code == 0) {
               
                window.location.href = "/platform/member/showMemberComment_list";
                CommentList.bind(indexlog);
            }
            else {
                Dalert(rsl.desc);
            }
        },
        error: function (es) {

        }
    }));
};

//评价审核
function comcheck(comid,star,reason) {
    var contentHTML = "";
    contentHTML += "<div class='l_plshtop'>评论星级：";
    for(var i =0;i < star; i++){
    	contentHTML += "<span class='star-lvs'></span>";
    }
    var huise = 5 - star;
    if(huise > 0){
    	for(var i =0;i < huise; i++){
        	contentHTML += "<span class='star-heis'></span>";
        }
    }
    contentHTML += "</div>";
    contentHTML += "<div class='l_plshtop' style='margin-top:15px;'>评论内容：</div>";
    contentHTML += "<div><textarea name='commstar' cols='' rows='6' class='l_ckxqarea_big'>"+reason+"</textarea></div>";
    d = dialog({
        title: '评价审核',
        content: contentHTML,
        width: 500,
        height: 180,
        button: [
    {
        value: '审核通过',
        callback: function () {
        	$.ajax({
                url: "/platform/membercommen/comCheck",
                type: "Post",
                data: { "comid": comid ,"comstatus":1},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Dalert(data.desc, 3000, function(){ location.reload();});
                    } else {
                        Dalert(data.desc);
                    }
                },
                error:function(){
                    
                }
            });
        },
        autofocus: true
    },
      {
    	value:'审核不通过',
    		callback:function () {
    			if ($("textarea[name=commstar]").val() == "" || $("textarea[name=commstar]").val() == undefined) {
                    Dalert("请填写评论内容，谢谢！");
                    return false;
                }
    			else{
    				$.ajax({
    		            url: "/platform/membercommen/comCheck",
    		            type: "Post",
    		            data: { "comid": comid ,"comstatus":2},
    		            dataType: "json",
    		            success: function (data) {
    		                if (data.code == 0) {
    		                    Dalert(data.desc, 3000, function(){ location.reload();});
    		                } else {
    		                    Dalert(data.desc);
    		                }
    		            },
    		            error:function(){
    		            }
    		      });
    		 }
    	}
      
      },
      {
    	  value:'关闭'
      }]
      						 
   });
   d.show();
}
    	


