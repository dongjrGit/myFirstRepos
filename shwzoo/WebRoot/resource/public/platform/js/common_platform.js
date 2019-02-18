//后台公用js  包含分页 修改 添加 删除 等 操作.方便使用无需创建多个js 只需要初始化变量便可 实现功能 业务复杂是 请勿使用

/*
 * 可在不影响其它页面调用的情况下扩展
 * 
 * */

//ajax请求分页列表 url 可初始化
var ajaxPageUrl="";
//ajax请求添加修改Url 可初始化
var ajaxModifyUrl="";
//ajax请求删除Url 可初始化
var ajaxDelUrl="";

//页大小 默认大小 可初始化
var pageSize=10;
//当前页 默认当前页 可更具需求获取页
var pageIndex=1;
//总数据
var pageCount=0;
//分页参数
var pageparam={page:pageIndex,size:pageSize};
//删除参数
var delparam;

//后台参数 可初始化
var platform_param;

//内容存放位置ID
var pagecontentid="";
//scriptid
var pagescriptid="";
//分页按钮存放位置
var pagebtnpage="";


//后台调用接口
var initPlatform={
		/**
		 *  * 后台分页
		 * @param url 分页请求地址
		 * @param scriptid
		 * @param contentid
		 * @param btnpageid
		 * @param param 请求参数 
		 * @param formid 绑定的formID 如#form
		 */
		Page:function(url,scriptid,contentid,btnpageid,param,formid){
			try {
				//初始化全局变量
				ajaxPageUrl=url;
				pagecontentid=contentid;
				pagescriptid=scriptid;
				pagebtnpage=btnpageid;
				
				//判断是否传参数 无参数就绑定默认参数
				if (isNull(param)) {
					//设置默认分页参数
					param=pageparam;
					//初始化后台分页参数
					platform_param=param;
				}else{
					//判断page参数是否存在 不存在初始化page参数
					if (isNull(param.page)) {
						param.page=pageparam.page;
					}
					//判断size参数是否存在 不存在初始化size参数
					if (isNull(param.size)) {
						param.size=pageparam.size;
					}
					//初始化后台分页参数
					platform_param=param;
				}
				//判断是否绑定form表单
				if (isNotNull(formid)) {
					//form表单参数集成 param参数
					param=$.extend($(formid).serializeObject(),param);
					//初始化后台分页参数
					platform_param=param;
				}
				
				
				if (isNull(scriptid)) {
					console.log("scriptid未初始化");
					return;
				}
				if (isNull(contentid)) {
					console.log("contentid未初始化");
					return;
				}
				
				//请求后台获取数据
				var ajaxdata=AjaxUtil.Get(url, param, AjaxDataType.JSON);
				//判断code参数是否存在 存在代表请求成功 不存在请求失败 弹出 错误信息
				if (ajaxdata.code != null) {
					//判断code是否是0 不是0 责返回错误信息
					if (ajaxdata.code==0) {
						//初始化script参数
						var listdata = {
			                     list: ajaxdata.data
			            };
						 //初始化数据
						 var html = template(scriptid, listdata);
						 //数据覆盖
						 $(contentid).html(html);
						 pageCount = ajaxdata.maxRow;
						 pageIndex=ajaxdata.pageIndex;
						 //初始化分页按钮
						 $(btnpageid).html(pagination(pageCount, pageIndex, pageSize, "platfompage"));
						 
					}else{
						Dalert(ajaxdata.desc);
					}
				}else{
					Dalert("错误信息："+ajaxdata);
				}
			} catch (e) {
				console.log(e);
			}
		},
		Delete:function(url,param){
			try {
				if (isNull(url)) {
					console.log("删除URL未初始化");
				}else{
					ajaxDelUrl=url;
				}
				if (isNull(param)) {
					console.log("删除参数为空");
				}else{
					delparam=param;
				}

				/*if (typeof param == "object") {
				}else{*/
					var ajaxdata=AjaxUtil.Post(url, {id:param},AjaxDataType.JSON);
					if (ajaxdata.code != null) {
						//判断code是否是0 不是0 责返回错误信息
						if (ajaxdata.code==0) {
							Dalert("删除成功","",function(){
								platfompage(1);
							});
						}else{
							Dalert(ajaxdata.desc);
						}
					}else{
						Dalert("错误信息："+ajaxdata);
					}
				/*}*/
			} catch (e) {
				console.log(e);
			}
		},
		/**
		 * 模版覆盖
		 * @param url 请求地址
		 * @param scriptid scriptID
		 * @param contentid 内容ID
		 * @param data 参数
		 * @param is 是否附加
		 */
		AjaxTemplate:function(url,scriptid,contentid,data,is){
			try {
				var ajaxdata=AjaxUtil.Get(url, data, AjaxDataType.JSON);
				
				if (ajaxdata==0) {
					var listdata = {
							list : rsl.data
						}
					var html = template(scriptid, listdata);
					if (is) {
						$(contentid).append(html);
					}else{
						$(contentid).html(html);
					}
				}else{
					Dalert(ajaxdata.desc);
				}
				
			} catch (e) {
				console.log(e);
			}
		}
};

/*
 * 兼容后台分页按钮
 */
function platfompage(page){

	try {
		//初始化页参数
		platform_param.page=page;
		//请求后台获取数据
		var ajaxdata=AjaxUtil.Get(ajaxPageUrl, platform_param, AjaxDataType.JSON);
		//判断code参数是否存在 存在代表请求成功 不存在请求失败 弹出 错误信息
		if (ajaxdata.code != null) {
			//判断code是否是0 不是0 责返回错误信息
			if (ajaxdata.code==0) {
				//初始化script参数
				var listdata = {
	                     list: ajaxdata.data
	            };
				 //初始化数据
				 var html = template(pagescriptid, listdata);
				 //数据覆盖
				 $(pagecontentid).html(html);
				 pageCount = ajaxdata.maxRow;
				 pageIndex=ajaxdata.pageIndex;
				 //初始化分页按钮
				 $(pagebtnpage).html(pagination(pageCount, pageIndex, pageSize, "platfompage"));
			}else{
				Dalert(ajaxdata.desc);
			}
		}else{
			Dalert("错误信息："+ajaxdata);
		}
	} catch (e) {
		console.log(e);
	}
}






var AjaxType={POST:"POST",GET:"GET"};
var AjaxDataType={JSON:"JSON"};


var AjaxUtil={
		Get:function(url,parsems,dataType,beforeSend,complete){
			if (isNull(dataType)) {
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
				error: function(e){ //错误
					returnVal=e;
				},
				success: function(data){ //成功
					returnVal=data;
				}
			});
			return returnVal;
		},
		Post:function(url,params,dataType){
			if (isNull(dataType)) {
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
				error: function(e){ //错误
					returnVal=e;
					alert(e);
				},
				success: function(data){ //成功
					returnVal=data;
				}
			});
			return returnVal;
		}
};




function isNull(value){
	if (value==null||value=="") {
		return true;
	}else{
		return false;
	}
}
function isNotNull(value){
	if (value==null||value=="") {
		return false;
	}else{
		return true;
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


