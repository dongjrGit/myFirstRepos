var RcmdScenic = {
	getAll : function(index) {
		// 类别
		var type = $("#type").val();
		$.ajax({
			url : "/zoo/rcmdScenic/querylist",
			type : "post",
			data : {
				"type" : type
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = template('sceniclist', listdata); //
					$("#list_title").after(html); // 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#list_title").parent().children().each(function() {
						if ($(this).attr('id') != "list_title") {
							this.parentNode.removeChild(this);
						}
					}) // html 填充
					$("#list_title").after(html); // 加载table样式：改变偶数行背景色，鼠标移动时背景色
					pcount = data.maxRow;
					$('#count').val(pcount);
				}
			},
			error : function(xhr,status,error) {
				alert(xhr.responseText);
			}
		});

	}
}

function refresh() {
	location.reload();
}

function add() {
	var type = $("#type").val();
	if(checkAdd(type)){
		window.location.href="/zoo/rcmdScenic/listedit?type="+type;	
	}else{
		Dalert("数量不能超过"+typeCount[type]+"个");
	}
}

var typeCount={"1":5,"2":4,"3":4,"4":4};

function checkAdd(type){
	var count = $('#count').val();
	if(count< typeCount[type]){
		return true;
	}
	return false;
}

