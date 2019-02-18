
//闪购页
var claid = "";
var list = {
    getlist: function (index) {
        var pcount;
        var pindex;
        var psize = 20;
        var datahtml = "";
        $.ajax({
            url: "/pc/activity/spike",
            type: "Post",
            data: { "ch":1,
        		    "page": index,
        		    "rows": psize },
        		dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    alert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('pro_listfs', listdata);
                    $("#productlist").html(html);
                    var html = template('pro_spike', listdata);
                    $("#boxtab").html(html);
                	pcount = data.maxRow;
					pindex = data.pageIndex;
					list.initul();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "list.getlist"));
                }
            },
            error: function () {
            	alert("错误信息");
            }
        });
    },
    initul:function(){

    	 var oUl = document.getElementById("boxtab");
         var aLi = oUl.getElementsByTagName("li");
         var i = 0;
         for (i = 0; i < aLi.length; i++) {
             if ($(aLi[i]).attr("data-type") == "0") {
                 $(aLi[i]).addClass('active');
                 $("#productlist_" + $(aLi[i]).attr("id")).addClass('current');
                 break;
             }
             $("#productlist_" + $(aLi[0]).attr("id")).addClass('current');
         }
         for (i = 0; i < aLi.length; i++) {
             aLi[i].index = i;
             aLi[i].onmouseover = function () {
                 for (i = 0; i < aLi.length; i++) {
                     $(aLi[i]).removeClass('active');
                     $("#productlist_" + $(aLi[i]).attr("id")).removeClass('current');
                 }
                 $(this).addClass('active');
                 $("#productlist_" + $(this).attr("id")).addClass('current');
             };
         }
    }
}
