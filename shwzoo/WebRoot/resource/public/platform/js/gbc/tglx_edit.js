$(function(){
	$("#savebtn").click(function(){
		if(check().form()){
			tglx.addTglx();
		}
	});
	$("#backbtn").click(function () {
		window.location.href = '/platform/tglx/tglx_list'; });
});

var tglx={
	addTglx:function(){
		var id=$("input[name=id]").val();
		var name=$("#name").val();
		var sort=$("#sort").val();
		$.ajax({
			url:"/platform/tgroup/save",
			type:"post",
			data:{"id":id,"name":name,"sort":sort},
			dataType:"json",
			success:function(data){
				if(data.code<0){
					Dalert(data.desc);
				}else{
					Dalert(data.desc, "",function () { window.location.href = '/platform/tglx/tglx_list'; });
				}
			},
			error:function(){}
		});
	}
}

//表单验证
function check(){
	return $("#form").validate({
		rules:{
			name:{
				required:true
			},
			sort:{
				digits: true
			}
		},
		messages:{
			name:{
				required:"请输入团购类型"
			},
			sort:{
				digits: "请输入整数"
			}
		},
	});
}

