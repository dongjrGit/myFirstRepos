var psize = 10;
var sc = {
     
    bind: function (index) {
    	
    	 $.ajax({
             type: "post",
             url: "/pc/concern/usernewList",
             dataType: "json",
             data: { "page": index,"size": psize },
             success: function (rsl) {
             	
                 if (rsl.code == 0) {
                     var listdata = {
                         list: rsl.data
                     }
                     var html = template('concernlist', listdata);
                     $("#scnews").html(html);
                     pcount = rsl.maxRow;
 					 pindex = rsl.pageIndex;
                     $("#pager").html(pagination(pcount, pindex, psize, "sc.bind()"));

                 }
                 else {
                 	
                     $(".wdys").html("<div class='l_mygzddp'><i></i>您还没有收藏任何菜谱</div>");

                 }
             },
             error: function (e) {
                 //alert(e.statusText);
             }
         });
    }
}
function xqsc(obj){
	
	if(confirm("确认取消收藏？")){
		 $.ajax({
	         type: "post",
	         url: "/pc/concern/delusernew",
	         dataType: "json",
	         data: { "id": obj},
	         success: function (rsl) {
	             if (rsl.code == 0) {
	            	 Dalert("取消成功！", "", backhref);
	             }
	             else {

	             }
	         },
	         error: function (e) {
	         }
	     });
	}
	 
}
function backhref(){
	location.href="/member/pcconcern/cpsc.html";
}