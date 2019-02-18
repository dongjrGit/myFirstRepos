//访问url
var initializeUrl;
//总记录数
var initializeRowsTotal;
//总页数
var initializePageTotal;
//行数
var initializePageSize=12;
//当前页
var initializePage=1;

//内容存放位置
var initializeContentId="#contentRows";

//scriptId名称
var initializeScriptId="rowslist";

//参数
var initializeParam=null;
//兼容提高 0自己1别人
var compatibility=0;

var pageUtil={
		initialize:function(url,param){
			initializeUrl=url;
			if (VarUtil.isNull(param)) {
				param={page:initializePage,pagesize:initializePageSize};
			}else{
				if(compatibility==0){
				if (VarUtil.isNull(param.page)) 
					param.page=initializePage;
				else
					initializePage=	param.page;
				if(VarUtil.isNull(param.pagesize))
					param.pagesize=initializePageSize;
				else
					initializePageSize=param.pagesize;
				}else{
					if (VarUtil.isNull(param.page)) 
						param.page=initializePage;
					else
						initializePage=	param.page;
					if(VarUtil.isNull(param.size))
						param.size=initializePageSize;
					else
						initializePageSize=param.size;
				}
				
			}
			initializeParam=param;
			var data=AjaxUtil.Get(initializeUrl, param, AjaxDataType.JSON)
			
			if (data.code<0) {
				alert(data.desc);
			}else{
				 var listdata = {
	                        list: data.data
	                    };
				 var html = template(initializeScriptId, listdata);
				 $(initializeContentId).html(html);
				 initializeRowsTotal=data.maxRow;
				 initializePage=data.pageIndex;
				 initializePageTotal=(initializeRowsTotal%initializePageSize==0)?initializeRowsTotal/initializePageSize:parseInt(initializeRowsTotal/initializePageSize)+1;
				 $("#pager").html(pagination(initializeRowsTotal, initializePage, initializePageSize,'pageUtil.getlist'));
			}
		},
		getlist:function(index){
			initializeParam.page=index;
			pageUtil.initialize(initializeUrl, initializeParam);
		}
};




