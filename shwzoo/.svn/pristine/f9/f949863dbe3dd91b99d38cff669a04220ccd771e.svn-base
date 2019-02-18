//商品评论
$(document).ready(function() {
	 var lng="${lng}";
	var lat="${lat}";
    
    callback = upAjax;//上拉加载数据

 
    //根据spuID获取商品评论数据
    function upAjax() {
    	 $.ajax({
	            url : "/wap/zhuye/queryscopshop",
	            type : "Post",
	           data:{"ch":3,
	        	   "longitude":lng,
	        	   "latitude":lat,
	        	   "page":1,
	        	   "size":6},
	            dataType : "json",
	            success : function(res) {
	                if (res.code == 0) {
	                    var data = res.data;
	                    if (data != undefined && data != null && data.list.length > 0) {
	                        var listdata = {
	                            list : data.list
	                        }
	                       var html = template('comlist', listdata);
	                        if (isclick) {
	                            $("#list").html(html);
	                        } else {
	                            $("#list").append(html);
	                        }
	                        hasdata = true;//是否有数据iscroll_list中定义，没有数据index将不再增加页数
	                        myScroll.refresh();//刷新加载
	                    } else {
	                        hasdata = false;
	                        if (isclick) {
	                            //$('#list').html("<li><font style='font-size:0.16rem;'>暂无数据</font></li>");
	                        }
	                    }
	                } else {
	                    if (isclick) {
	                        //$('#list').html("<li><font style='font-size:0.16rem;'>暂无数据</font></li>");
	                    }
	                }
	                isclick = false;
	            },
	            error : function() {
	                $('#list').html("<li><font style='font-size:0.16rem;'>数据请求失败，请重新刷新</font><li>");
	            }
	        }); 
    }

})

