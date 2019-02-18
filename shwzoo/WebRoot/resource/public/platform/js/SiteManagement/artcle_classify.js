$(function(){
	util.page(1);
	$(".select2").select2();
	$(".addnew_button").click(function(){
        $("#addnew_tr").show();
	});
	
	$("#addnew_submit").click(function(){
		util.save();
	});
	
	$(".shenlan").click(function(){
		util.del($(this).attr("data"));
	});
});



var util={
		save:function(){
			var articleId=$("#articleId").val();
			var articleTitle=$("#articleTitle").val();
			var classfyId=$("#classfy").val();
			var classfyName=$("#classfy").find('option:selected').text();
			$.ajax({
	            url: "/platform/page/articleAndClassfyAdd",
	            type: "Post",
	            data: {"articleId":articleId,"articleTitle":articleTitle,"classfyId":classfyId,"classfyName":classfyName},
	            dataType: "json",
	            success: function (data) {
	                Dalert(data.desc);
	                if (data.code == 0) {
	                    location.reload();
	                }
	            }
	        });
		},page:function(page){
			$.ajax({
	            url: "/platform/page/articleAndClassfy",
	            type: "Post",
	            data: { "articleId": $("#valueId").val(), "page": page, "size": 10},
	            dataType: "json",
	            success: function (data) {
	                if (data.code < 0) {
	                    Dalert(data.desc);
	                } else {
	                    var listdata = {
	                        list: data.data
	                    }
	                    var html = template('searchlist', listdata);
	                    $("#list_title").html(html);
	                    if (callback) {
	                        callback();
	                    }
	                    pcount = data.maxRow;
	                    pindex = data.pageIndex;

	                    //分页
	                    $("#pager").html(pagination(pcount, pindex, psize, "util.page"));
	                }
	            },
	            error: function () {

	            }
	        });
		},del:function(id){
			$.ajax({
	            url: "/platform/page/articleAndClassfyDel",
	            type: "Post",
	            data: {id:id},
	            dataType: "json",
	            success: function (data) {
	                Dalert(data.desc);
	                if (data.code == 0) {
	                    location.reload();
	                }
	            }
	        });
		}
		
};