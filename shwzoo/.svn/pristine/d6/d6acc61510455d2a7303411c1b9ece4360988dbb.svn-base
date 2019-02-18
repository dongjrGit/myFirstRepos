//商品评论
$(document).ready(function() {
	
    callback = upAjax;//上拉加载数据
    
  
    function upAjax() {
    	  index=1;
        $.ajax({
            url : "/app/api/groupby/list",
            type : "Post",
            data : {
            	 "ch" : 3,
                 "page" : index,//iscroll_list已定义好直接引用
                 "size" : 5,
            },
            dataType : "json",
            success : function(res) {
                if (res.code == 0) {
                	alert(res.data);
                    var data = res.data;
                    
                    if (data != undefined && data != null && data.list.length > 0) {
                    	 var listdata = {
                                 list : data
                             }
                    	var html = template('comlist', listdata);
                        $("#list").append(html);
                      
                        hasdata = true;//是否有数据iscroll_list中定义，没有数据index将不再增加页数
                        myScroll.refresh();//刷新加载
                    } else {
                        hasdata = false;
               
                    }
                } else {
                                   }
               
            },
            error : function() {
                $('#list').html("<li><font style='font-size:0.16rem;'>数据请求失败，请重新刷新</font><li>");
            }
        });        
    }

})

