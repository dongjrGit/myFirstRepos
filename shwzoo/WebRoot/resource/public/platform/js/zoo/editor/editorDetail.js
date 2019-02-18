$(function() {
	$("input[name=Save]").bind("click", save);
});

function save() {
	if (formSubmit()) {
		var data={};
		var type = $('#type').val();
		data.id=$('#id').val();
		data.type=type;
		data.title=$('#title').val();
		data.content=contEditor.html();
		console.log(data);
		var jsonData = JSON.stringify(data); 
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		$.ajax({
			url : "/zoo/editor/editlist",
			type : "Post",
			data : {"data":jsonData},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", function() {
						window.location.href = "/zoo/editor/list?type="+type;
					});
				} else {
					$("input[name='Save']").show();
					Dalert(data.desc);
				}
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
	}
}

function formSubmit() {
	var title = $('#title').val();
	var id = $('#id').val();
	if(!title){
		Dalert("请填写标题！");
		return false;
	}else{
		var type = $('#type').val();
		var flag = false;
		$.ajax({
			url : "/zoo/editor/checkTitle",
			type : "Post",
			data : {"title":title,"id":id,"type":type},
			dataType : "json",
			async:false,
			success : function(data) {
				if (data.code == 0) {
					if(data.data==false){
						Dalert("该标题已存在！");
					}else{
						flag=true;
					}
				} else {
					Dalert(data.desc);
				}
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
		if(!flag){
			return false;
		}
	}
	if(contEditor.isEmpty()){
		Dalert("请填写详情！");
		return false;
	}
	return true;

}