
//闪购页
var claid = "";
var list = {
    bind: function () {
        list.getlist(1);
    },
    getlist: function (index) {
        var pcount;
        var pindex;
        var psize = 9;
        var datahtml = "";
        $.ajax({
            url: "/pc/activity/getSgList",
            type: "Post",
            data: { "ch":1,
        		    "page": index,
        		    "size": psize },
        		dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    // alert(data.desc);
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
                    
                    //倒计时
                    //time(data.data.length);
                    //window.setInterval(function(){time(data.data.length)}, 1000);
                    
                    var diff=new Date($('#time0').val().trim())-new Date();
                	var day = parseInt(diff / 86400000);  
                    if (day<=0) {
                    	timetext(data.data[0].time);
                    	setTimeout(function(){timetext(data.data[0].time)}, 1000);
					}else{
						 $(".timespu").html("距离结束还有"+day+"天");
					}
                    
                    //jQuery(".picScroll-left").slide({mainCell:".bd ul",autoPlay:true,effect:"left",vis:3,trigger:"click"});
                	//jQuery(".picScroll-left").slide({mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:false,vis:3,trigger:"click"});
                }
            },
            error: function () {
            }
        });
    },
}
function time(obj){
	for (var i = 0; i < obj; i++) {
		 var time=$('#time'+i).val();
		 var day1=Math.floor(time/(60*60*24)); 
		 var hour=Math.floor((time-day1*24*60*60)/3600); 
		 var minute=Math.floor((time-day1*24*60*60-hour*3600)/60); 
		 var second=Math.floor((time-day1*24*60*60-hour*3600-minute*60)); 
		 var time=$('#time'+i).val($('#time'+i).val()-1);
		var htmlstr="剩余"+day1+"天"+hour+"小时"+minute+"分钟"+second+"秒";
		$('#times'+i).html(htmlstr);
	}
};
function timetext(endtime){
	var diff=new Date(endtime)-new Date();
	var day = parseInt(diff / 86400000);                         //以天数为单位取整  
    var hour= parseInt(diff % 86400000 / 3600000);               //以小时为单位取整  
    var min = parseInt(diff % 86400000 % 3600000 / 60000);       //以分钟为单位取整  
    var seconds = parseInt(diff % 86400000 % 3600000 % 60000 / 1000);   //以秒为单位取整  
	// var time=$('#time'+i).val($('#time'+i).val()-1);.
	 if (day>0||hour>0||min>0||seconds>0) {
		 var htmlstr=hour+"小时"+min+"分钟"+seconds+"秒";
		 $('#time0').val($('#time0').val()-1);
		 $('.timespu').html(htmlstr);
		 setTimeout(function(){timetext(endtime)}, 1000);
	}else{
		$('.timespu').html("活动已结束");
	}
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
