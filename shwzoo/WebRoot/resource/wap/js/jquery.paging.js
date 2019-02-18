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

;(function($,windows,document){
	$.fn.extend({
		paging:function(options){
			var defaults={
				page:1,//默认第一页
				size:10,//默认十条数据
				total:-1,//总页默认-1 -1代表未获取到总页
				ajaxData:{page:1,size:10},//请求接口参数 page,size 默认必须初始化参数
				url:"",//请求数据地址
				templateID:"",//artTemplate模版ID
				beforeSend:function(){},//请求数据前操作
				complete:function(){},//请求数据成功后操作
				dataType:"json",//返回类型
				styleinit:function(){},
				submitStyle:"ajax",//请求类型
				isfristload:true,//是否首次自动加载
				
			};
			 //将默认的参数对象和传进来的参数对象合并在一起
            var opts = $.extend(defaults, options);
            if(VarUtil.isNull(opts.ajaxData.page)){
            	opts.ajaxData.page=opts.page;
			}
			if(VarUtil.isNull(opts.ajaxData.size)){
				opts.ajaxData.size=opts.size;
			}
            //目标元素命名
            var $this = this;
            //全局API对象
            PageAPI = {};
          //请求分页数据 用于做分页处理 和首次加载
            PageAPI.AjaxPage=function(){
            	if(opts.total==-1||(opts.total>=opts.page)){
	            	if(opts.submitStyle=="ajax"){
	            		 $.ajax({
	 	     				url:opts.url,
	 	     				data:opts.ajaxData,
	 	     				dataType:"json",
	 	     				success:function(data){
	 	     					 var listdata = {
	 	     		                    list: data.data
	 	     		                }
	 	     					 var html = template(opts.templateID, listdata);
	 	     					 opts.total=data.page;//总页数
	 	     					 $this.append(html);
	 	     					 opts.page++;//当前页+1
	 	     					 opts.ajaxData.page++;//当前页+1
	 	     					 
	 	     					 opts.styleinit();
	 	     				},
	 	     				beforeSend:opts.beforeSend,
	 	     				complete:opts.complete
	 	     			});
	            	}
            	}
            }
            //判断是否首次主动加载数据
            if(opts.isfristload){
            	PageAPI.AjaxPage();
            }
            //自动下来加载方法
            PageAPI.SelfLoad=function(){
            	$(window).scroll(function(){
    				var scrollTop=$(this).scrollTop();
    				var scrollHeight=$(document).height();
    				var windowHeight=$(this).height();
    				//放小匹配值 防止无法加载问题
    				if((scrollTop+windowHeight)>=(scrollHeight-1)){
    					PageAPI.AjaxPage();
    				}
    			});
            }
            //API 向ajaxData增加或替换参数,传入json对象 
            PageAPI.AddAjaxData = function (json) {
                opts.submitStyle == "ajax" && (opts.ajaxData = $.extend(false, opts.ajaxData, json));
            }
            
            //API 主动执行请求
            PageAPI.AjaxSubmit = function () {
                if (opts.submitStyle == "ajax") {
                	//主动请求清除页数
                	opts.total = -1;
                	opts.page = 1;
                	opts.ajaxData.page=1;
                	if(opts.total==-1||(opts.total>=opts.page)){
	                	 $.ajax({
		 	     				url:opts.url,
		 	     				data:opts.ajaxData,
		 	     				dataType:"json",
		 	     				success:function(data){
		 	     					 var listdata = {
		 	     		                    list: data.data
		 	     		                }
		 	     					 var html = template(opts.templateID, listdata);
		 	     					 opts.total=data.page;//总页数
		 	     					 $this.html(html);
		 	     					 opts.page++;//当前页+1
		 	     					 opts.ajaxData.page++;//当前页+1
		 	     					 opts.styleinit();
		 	     				},
		 	     				beforeSend:opts.beforeSend,
		 	     				complete:opts.complete
		 	     			});
                	}
                }
            }
           
            return $this;
		}
	});
})(jQuery, window, document);