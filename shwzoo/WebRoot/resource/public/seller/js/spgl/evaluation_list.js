//会员评论列表 分页数据绑定
 var pcount;
 var pindex;
 var psize = 10;
 var indexlog;
 var CommentList = {
     bind: function (index) {
         var spuname = $("#spuname").val();
         var status = $("#status_select").val();
         var ordernum = $("#ordercode").val();
         $.ajax(({
             type: "post",
             url:  "/seller/shopproduct/queryEvalutionList",
             dataType: "json",
             data: { pageindex: index, pagesize: psize, spuname: spuname, status: status, ordernum: ordernum},
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
                     pcount = rsl.maxRow;
                     pindex = rsl.pageIndex;
                     $("#pager").html(pagination(pcount, pindex, psize, "comment_pagelist"));
                     //查看评论明细
                     $(".a_commentdetail").bind("click", commentdetailshow);
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
     $(".a_commentdetail").attr('href', "/seller/productshop/showMemberCommentDetail?id=" + commentID);
 };

	function ChangeStatus1(obj) {
		var status = $("a[name='changestate1']").attr("data-state");
		$.ajax({
			url : "/seller/shopproduct/changeShopStatus",
			type : "Post",
			data : {
				"id" : obj,
				"status" : status
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					Dalert(data.desc);
				}
				 location.reload();
			}
		});
	}

	function ChangeStatus2(obj) {
		var status = $("a[name='changestate2']").attr("data-state");
		$.ajax({
			url : "/seller/shopproduct/changeShopStatus",
			type : "Post",
			data : {
				"id" : obj,
				"status" : status
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					Dalert(data.desc);
				}
				 location.reload();
			}
		});
	}

	function ChangeStatus3(obj) {
		var status = $("a[name='changestate3']").attr("data-state");
		$.ajax({
			url : "/seller/shopproduct/changeShopStatus",
			type : "Post",
			data : {
				"id" : obj,
				"status" : status
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					Dalert(data.desc);
				}
				 location.reload();
			}
		});
	}