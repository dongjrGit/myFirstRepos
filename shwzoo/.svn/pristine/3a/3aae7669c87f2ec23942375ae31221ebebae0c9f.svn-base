//每日鲜列表
var pcount, pindex, psize =10;
var mrx = {
	getChecklist: function (index,orderby,sort) {
	var mark=$("#mark").val();
    $.ajax({
        url: "/pc/index/getpcTopicList",
        type: "Post",
        data: { 
        	"index":index,
        	"size":psize,
        	"mark":mark,
        	"orderby":orderby,
        	"sort":sort},
        dataType: "json",
        success: function (data) {
            if (data.code < 0) {
                Dalert(data.desc);
            } else {
                var listdata = {

                    list: data.data
                }

                var html = template('grouplist', listdata);
                //html 填充
                $("#datalist").html(html);
               var initializeRowsTotal=data.maxRow;
			var initializePage=data.pageIndex;
			$("#pager").html(pagination(initializeRowsTotal, initializePage, psize,'mrx.getChecklist'));
            }
        },
        error: function () {

        }
    });
    } 
    
   }