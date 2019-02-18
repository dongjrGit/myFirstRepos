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
                alert("ajaxPost请求异常");
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


var params={page:1,size:10};
var contentid="list";
var scriptId="scriptlist";
var pages=1;
var page=1;
var pageurl;

function initWapPage(){
	params.page=page;
	if(page<=pages){
		var ajaxdata=AjaxUtil.Get(pageurl, params,AjaxDataType.JSON);
		if (ajaxdata) {
				pages=ajaxdata.page;
			 var listdata = {
                     list: ajaxdata.data
                 };
			 var html = template(scriptId, listdata);
			 $(contentid).append(html);
			 page++;
		}
	}
}














