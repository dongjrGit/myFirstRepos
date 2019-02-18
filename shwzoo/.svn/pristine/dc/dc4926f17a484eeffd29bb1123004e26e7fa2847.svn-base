var type;
var state;
var ZooMap = {
	getAll : function(param1, param2) {
		type = param1;
		state = param2;
		$.ajax({
			url : "/zoo/zooMap/querylist",
			type : "post",
			data : {
				"type" : type,
				"state":state
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('zooMaplist', listdata); //
					$("#list_title").after(html); // 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#list_title").parent().children().each(function() {
						if ($(this).attr('id') != "list_title") {
							this.parentNode.removeChild(this);
						}
					}) // html 填充
					$("#list_title").after(html); // 加载table样式：改变偶数行背景色，鼠标移动时背景色
				}
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
	},
	add:function(flag){
		if(flag==1){
			window.location.href="/zoo/zooMap/editView?type="+type+"&state="+state;	
		}
		if(flag==2){
			window.location.href="/zoo/zooMap/listedit?type="+type+"&state="+state;	
		}
		
	},
	del:function(id){
		var data={"id":id,"srcState":1,"tgtState":0};
		$.ajax({
			url : "/zoo/zooMap/dellist",
			type : "post",
			data :data,
			dataType : "json",
			success : function(data) {
				Dalert(data.desc, "", ZooMap.refresh);
			},
			error : function(xhr, status, error) {
				// alert(XMLHttpRequest.status);
				alert("删除失败 \r" + xhr.responseText);
			}
		});
	},
	
	// 选择是否删除
	delCheck:function(id) {
		ConfirmShow("确认删除吗？", ZooMap.del, id, "是否删除");
	},
	
	refresh:function () {
		ZooMap.getAll(type,state);
	}
}