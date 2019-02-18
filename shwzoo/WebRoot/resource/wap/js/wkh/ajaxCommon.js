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
            },
            success:function(data){
            	returnVal=data;
            }
        });
        return returnVal;
    }


};

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
