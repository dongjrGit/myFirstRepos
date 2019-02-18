var AjaxType={POST:"POST",GET:"GET"};
var AjaxDataType={JSON:"JSON"};


var AjaxUtil={
   Post:function(ajaxUrl,data,dataType){
       if(VarUtil.isNull(dataType))
       dataType=AjaxDataType.JSON;
       var returnVal;
       $.ajax({
           type:AjaxType.POST,
           dataType:dataType,
           url:ajaxUrl,
           cache:false,
           async:false,
           data:data,
           timeout:30000,
           error: function () {
               alert("ajaxPost请求异常");
           },
           success:function(data){
        	   returnVal= data;
           }

       });
       return returnVal;
   },
    Get:function(ajaxUrl,data,dataType,beforeSend,complete){
        if(VarUtil.isNull(dataType))
            dataType=AjaxDataType.JSON;
        
        var returnVal;
        $.ajax({
            type:AjaxType.GET,
            dataType:dataType,
            url:ajaxUrl,
            cache:false,
            async:false,
            data:data,
            timeout:30000,
            beforeSend:beforeSend,
            complete:complete,
            error: function () {
               // alert("ajaxPost请求异常");
            },
            success:function(data){
            	returnVal=data;
            }
        });
        return returnVal;
    }


};




var VarUtil={
    isNull:function(value){
        if(value==null||value==""){
            return true;
        }else{
            return false;
        }
    },isNotNull:function(value){
        return !VarUtil.isNull(value);
    }
};

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};

var pageIndex=1;
var pageTotal=1;
var pageData={page:1,size:1};
var pageUrl="";
var content="";
var scripthtml="pagehtml";

function initPage(){
	pageData.page=pageIndex;
	//当前页小于或者等于总页数时
	if(pageIndex <= pageTotal){
		var nohtml = '';
		var data=AjaxUtil.Get(pageUrl, pageData, AjaxDataType.JSON);
		if(data.code==0){
			var listdata = {
                    list: data.data
                }
			 var html = template(scripthtml, listdata);
			pageTotal=data.page;
			$(content).append(html);
			pageIndex++;
			load();
		}
	}
}

function clearPage(){
	pageIndex=1;
	$(content).html("");
}

$(function(){
	$(window).scroll(function(){
		var scrollTop=$(this).scrollTop();
		var scrollHeight=$(document).height();
		var windowHeight=$(this).height();
		if(scrollTop+windowHeight==scrollHeight){
			initPage();
		}
	});
});

function load(){}
