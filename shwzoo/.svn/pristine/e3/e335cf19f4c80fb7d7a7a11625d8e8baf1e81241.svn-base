
var pcount, pindex;
var psize = 10;

var Search={
    bind:function(){
    	$("#addnew_submit").bind("click",Search.save);
    	$("#addnew_cancel").bind("click",function(){
    		$("#addnew_tr").hide();
    	});
    	$(".addnew_button").first().bind("click",function(){
    		$("#addnew_tr").show();
    	});
    	Search.getSearchList(1);
    },
    //查询
	getSearchList:function(index){
		$.ajax({
			url:"/platform/zd/index",
			type:"post",
			data:{"page":index,"size":psize},
			dataType:"json",
			success:function(data){
				if(data.code<0){
					Dalert(data.desc);
				}else{
					var listdata={
						list:data.data
					}
					var html=template('searchlist',listdata);
					$("#list_title").html(html);
					pcount=data.maxRow;
					pindex=data.pageIndex;
					//绑定删除事件
					Search.unit();
					//分页
					$("#pager").html(pagination(pcount,pindex,10,"Search.getSearchList"));
				}
			},
			error:function(){
				alert("查询出错!");
			}
		});
	},
	
	//删除绑定
	unit:function(){
		$(".del").each(function(){
			$(this).bind("click",Search.del);
		});
	},
	
    //删除
    del:function(){
    	if(confirm("确定要删除吗?")){
    		var id=$(this).attr("data");
    		$.ajax({
    			url:"/platform/zd/delete",
    			type:"post",
    			data:{"id":id},
    			dataType:"json",
    			success:function(data){
    				Dalert(data.desc);
    				if(data.code==0){
    					location.reload();
    				}
    			},
    			error:function(){
    				alert("模块删除出错!");
    			}
    		});
    	}
    },
	
	//保存
	save:function(){
		var name=$("#addnew_name").val().trim();
		if(check().form()){
			$.ajax({
				url:"/platform/zd/save",
				type:"post",
				data:{"name":name},
				dataType:"json",
				success:function(data){
					Dalert(data.desc);
					if(data.code==0){
						$("#forms")[0].reset();
						location.reload();
					}else{
						Dalert(data.desc);
					}
				},
				error:function(){
					alert("模块增加出错!");
				}
			});
		}
	}
    
}

function check(){
	return $("#forms").validate({
		rules:{
			addnew_name:{
				required:true,
				rangelength:[1,10]
			}
		},
		messages:{
			addnew_name:{
				required:"名称不可为空",
				rangelength:"名称长度在10个字符以内(一个汉字算两个字符)"
			}
		},
	});
}




