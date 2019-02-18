var ajaxUtil={
		Get:function(url,parsems,dataType,beforeSend,complete){
			if (varUtil.isNull(dataType)) {
				dataType="JSON";
			}
			var returnVal;
			$.ajax({
				type:'GET', //请求类型
				dataType: dataType,
				url:url,//请求位置 时间轴
				cache: false,//true，dataType 为 script 和 jsonp 时默认为 false。设置为 false 将不缓存此页面。
				async: false,//同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
				data:parsems,
				timeout:30000,//设置请求超时时间（毫秒）。此设置将覆盖全局设置。
				beforeSend:beforeSend, //加载中
				complete: complete, //加载结束
				error: function(){ //错误
					alert("异常")
				},
				success: function(data){ //成功
					returnVal=data;
				}
			});
			return returnVal;
		},
		Post:function(url,params,dataType){
			if (varUtil.isNull(dataType)) {
				dataType="JSON";
			}

			var returnVal;
			$.ajax({
				type:'POST', //请求类型
				dataType: dataType,
				url:url,//请求位置 时间轴
				cache: false,//true，dataType 为 script 和 jsonp 时默认为 false。设置为 false 将不缓存此页面。
				async: false,//同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
				data:params,
				timeout:30000,//设置请求超时时间（毫秒）。此设置将覆盖全局设置。
				beforeSend:function(){ //加载中
				},
				complete: function(){ //加载结束
				},
				error: function(){ //错误
				},
				success: function(data){ //成功
					returnVal=data;
				}
			});
			return returnVal;
		}
};


var varUtil={
		isNull:function(value){
			if (value==null||value=="") {
				return true;
			}else{
				return false;
			}
		}
}


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
