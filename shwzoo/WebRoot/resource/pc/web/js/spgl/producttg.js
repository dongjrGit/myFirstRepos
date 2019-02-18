
//闪购页
var claid = "";
var list = {
    bind: function () {
        list.getlist(1);
    },
    getlist: function (index) {
        var pcount;
        var pindex;
        var psize = 20;
        var datahtml = "";
        $.ajax({
            url: "/pc/activity/gettglist",
            type: "Post",
            data: { "ch":1,
        		    "page": index,
        		    "size": psize },
        		dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    alert(data.desc);
                } else {
                	
                    var listdata = {
                        list: data.data
                    }
                    var html = template('pro_list', listdata);
                    $("#productlist").html(html);
                	pcount = data.maxRow;
					pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "list.getlist"));
                }
            },
            error: function () {
            }
        });
    },
}


function addConcernt(spuid){
	 if (WebLogin.isLogin()) {
		 $.ajax({
	            type : "post",
	            url : "/pc/products/collectbuySpu",
	            dataType : "json",
	            data : {
	                "spuid" : spuid,
	                "ch" : 0
	            },
	            success : function(rsl) {
	                if (rsl.code == 0) {
	                    alert(rsl.desc);
	                } else {
	                    alert(rsl.desc);
	                }
	            },
	            error : function(e) {
	                //alert(e.statusText);
	            }
	        });
	} else {
	    showlogindiv();
	} 
}
