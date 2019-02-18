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
               /*alert("ajaxPost请求异常");*/
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



function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
