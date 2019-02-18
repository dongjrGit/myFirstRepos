function Map() {     
    /** 存放键的数组(遍历用到) */    
    this.keys = new Array();     
    /** 存放数据 */    
    this.data = new Object();     
         
    /**   
     * 放入一个键值对   
     * @param {String} key   
     * @param {Object} value   
     */    
    this.put = function(key, value) {     
        if(this.data[key] == null){     
            this.keys.push(key);     
        }     
        this.data[key] = value;     
    };     
         
    /**   
     * 获取某键对应的值   
     * @param {String} key   
     * @return {Object} value   
     */    
    this.get = function(key) {     
        return this.data[key];     
    };     
         
    /**   
     * 删除一个键值对   
     * @param {String} key   
     */    
    this.remove = function(key) {     
        this.keys.remove(key);     
        this.data[key] = null;     
    };     
         
    /**   
     * 遍历Map,执行处理函数   
     *    
     * @param {Function} 回调函数 function(key,value,index){..}   
     */    
    this.each = function(fn){     
        if(typeof fn != 'function'){     
            return;     
        }     
        var len = this.keys.length;     
        for(var i=0;i<len;i++){     
            var k = this.keys[i];     
            fn(k,this.data[k],i);     
        }     
    };     
         
    /**   
     * 获取键值数组(类似<a href="http://lib.csdn.net/base/javase" class='replace_word' title="Java SE知识库" target='_blank' style='color:#df3434; font-weight:bold;'>Java</a>的entrySet())   
     * @return 键值对象{key,value}的数组   
     */    
    this.entrys = function() {     
        var len = this.keys.length;     
        var entrys = new Array(len);     
        for (var i = 0; i < len; i++) {     
            entrys[i] = {     
                key : this.keys[i],     
                value : this.data[i]     
            };     
        }     
        return entrys;     
    };     
         
    /**   
     * 判断Map是否为空   
     */    
    this.isEmpty = function() {     
        return this.keys.length == 0;     
    };     
         
    /**   
     * 获取键值对数量   
     */    
    this.size = function(){     
        return this.keys.length;     
    };     
         
    /**   
     * 重写toString    
     */    
    this.toString = function(){     
        var s = "{";     
        for(var i=0;i<this.keys.length;i++,s+=','){     
            var k = this.keys[i];     
            s += k+"="+this.data[k];     
        }     
        s+="}";     
        return s;     
    };     
}     


var uploadoptions=new Map();


;(function($,windows,document){
	
	$.fn.extend({
		upload:function(options){
			 //目标元素命名
            var $this = this;
            var valthis=$(this); 
			var defaults={
				id:"1",//上传ID
				imgmax:3,//上传图片数量 -1则不限制 达到上传数量责隐藏上传按钮
				// ftype:上传文件类型（图片文件=1，其他文件=2）
        		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
				relationtype:0,
				paramformat:"string",//array数组类型 string 字符串类型
				paramname:"ufx",//参数名称
				uploadurl:"/app/api/img/upload",//上传位置url
				uploaddata:{},
				addimg:"/resource/public/seller/images/djsc.png",//上传图片背景图片
				delimg:"/resource/public/platform/images/img_del.png",//删除图片图
			};
			 //将默认的参数对象和传进来的参数对象合并在一起
            var opts = $.extend(defaults, options);
            uploadoptions.put(opts.id,opts);
            //全局API对象
            UploadAPI = {};
            //图片删除
            UploadAPI.ImgDel=function(obj,id){
            	var vals=$("#"+uploadoptions.get(id).paramname).val();
            	var array = vals.split(',');
            	var image=$(obj).parent().find('img').attr("src");
            	array.splice($.inArray(image,array),1);
            	$("#"+uploadoptions.get(id).paramname).val(array.join(','));
            	$(obj).parent().remove();
            	var filecount=parseInt($("#imgadd_upload_"+id).attr("data-max"));
				if (filecount!=-1) {
    				if (filecount==0) {
    					$("#imgadd_upload_"+id).attr("data-max","1");
    					$("#imgadd_upload_"+id).css("display","");
					}else{
						$("#imgadd_upload_"+id).attr("data-max",filecount+1);
					}
				}
            }
            /**
             * 初始化数据
             * 
             * 参数ID,数据字符串,分割符号
             */
            UploadAPI.InitData=function(id,str,format){
            	if (str==""||str==null) {
					return;
				}
            	var array=str.split(format==null?",":format);//判断分隔符号是否为空 非空默认逗号分割
            	$.each(array,function(index,val){
            		var htmls='<div style="float:left;position: relative;margin-right:5px;">';
    	            htmls+='<img src="'+val+'" width="107" height="100">';
    	            if (uploadoptions.get(id).paramformat=="array") {
    	            	 htmls+='<input type="hidden" name="'+uploadoptions.get(id).paramname+'" value="'+val+'">';
					}else{
						var vals=$("#"+uploadoptions.get(id).paramname).val();
   					 	if (vals==null||vals=="") {
   						 vals=val;
						}else{
							vals+=","+val;
						}
   					 	$("#"+uploadoptions.get(id).paramname).val(vals);
					}
    	           
    	            htmls+='<span style="width: 15px;height: 15px;background: #CCCCCC;border: 1px solid #999;display:block;position: absolute;top: 5px;right: 5px;text-align: center;line-height: 15px;cursor: pointer;" onclick="UploadAPI.ImgDel(this,\''+uploadoptions.get(id).id+'\')">X</span>';
    	            htmls+='</div>';
    	            $("#imgadd_upload_"+id).parent().parent().before(htmls);
    	            if (uploadoptions.get(id).imgmax == index) {
            			return false;
					}
            	});
            	if (opts.imgmax!=-1) {
            		var sum=uploadoptions.get(id).imgmax-array.length;
            		if (sum<=0) {
            			$("#imgadd_upload_"+id).css("display","none");
            			$("#imgadd_upload_"+id).attr("data-max",sum);
					}else{
						$("#imgadd_upload_"+id).attr("data-max",sum);
					}
				}
            };
            //图片上传
            UploadAPI.UploadAjax=function(id){
            	$.ajaxFileUpload({
            		url : opts.uploadurl,
            		secureuri : false,
            		fileElementId : 'file_upload_'+id,
            		dataType : "json",
            		// ftype:上传文件类型（图片文件=1，其他文件=2）
            		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
            		data :  uploadoptions.get(id).uploaddata,
            		type : 'POST',
            		success : function(result) {
            			if (result.code == 0) {
            				var htmls='<div style="float:left;position: relative;margin-right:5px;">';
            				htmls+='<img src="'+result.data[0]+'" width="107" height="100">';
            				 if (uploadoptions.get(id).paramformat=="array") {
            					 htmls+='<input type="hidden" name="'+uploadoptions.get(id).paramname+'" value="'+result.data[0]+'">';
            				 }else{
            					 var vals=$("#"+uploadoptions.get(id).paramname).val();
            					 if (vals==null||vals=="") {
            						 vals=result.data[0];
								}else{
									vals+=","+result.data[0];
								}
            					 $("#"+uploadoptions.get(id).paramname).val(vals);
            				 }
            				htmls+='<span style="width: 15px;height: 15px;background: #CCCCCC;border: 1px solid #999;display:block;position: absolute;top: 5px;right: 5px;text-align: center;line-height: 15px;cursor: pointer;" onclick="UploadAPI.ImgDel(this,\''+id+'\')">X</span>';
            	            htmls+='</div>';
            	            $("#imgadd_upload_"+id).parent().parent().before(htmls);
	            			
	            			var filecount=parseInt($("#imgadd_upload_"+id).attr("data-max"));
            				if (filecount!=-1) {
                				filecount=filecount-1;
                				$("#imgadd_upload_"+id).attr("data-max",filecount);
                				if (filecount==0) {
                					$("#imgadd_upload_"+id).css("display","none");
    							}
            				}
            				$("#file_upload_"+id).val("");
            			} else {
            				$("#img_certificate-error").remove();
            				var html4 = '<label id="img_certificate-error" class="error" for="img_certificate">' + result.desc + '</label>';
            				$("#div_certificate").after(html4);
            			}
            			// TODO 结束正在加载中
            		},
            		error : function(e) {
            			// Dalert(JSON.stringify(e));
            			
            			// TODO 结束正在加载中
            		},
            		complete:function(){
            			
            		}
            	});
            }
            
            $this.css("float","left");
            $this.css("position","relative");
            $this.css("margin-right","5px");
            
            var htmls='<div style="float:left;position: relative;margin-right:5px;">';
            	htmls+='<input type="file" name="file_upload_'+opts.id+'" id="file_upload_'+opts.id+'" onchange="UploadAPI.UploadAjax(\''+opts.id+'\')" hidden />';
		        if (opts.paramformat=="string") {
		        	htmls+='<input type="text" name="'+opts.paramname+'" id="'+opts.paramname+'" value=""  hidden />';
				}
            	htmls+='<img src="'+opts.addimg+'" width="107" height="100"  data-max="'+opts.imgmax+'" id="imgadd_upload_'+opts.id+'" onclick="$(\'#file_upload_'+opts.id+'\').click()" name="imgadd_upload_'+opts.id+'">';
	            htmls+='</div>';
            
			$this.append(htmls);
			return $this;
		}
	});
})(jQuery, window, document);;