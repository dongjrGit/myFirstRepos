$(function(){
	$(".preserve-inp").bind("click",edit);
})


function edit(){
	var type=$("#ctype").val();
	editor.sync();
	$("input[name=content]").val(editor.html());
	$.ajax({
		url:"/platform/news/editAboutUs",
		type:"post",
		data:$("#form").serialize(),
		dataType:"json",
		success:function(rsl){
			if(rsl.code==0){
				Dalert("保存成功","",function(){window.location.href="/platform/news/single?type="+type});
			}else{
				Dalert(rsl.desc);
			}
		},
		error:function(){
			
		}
	});
}




