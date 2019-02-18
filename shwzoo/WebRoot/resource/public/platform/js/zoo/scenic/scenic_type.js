//公告分类公共类
var Class = {
	unit : function(callback) {
		
	},	
	firstChange : function() { // 根据父ID获取分类
		var pid = $("#firstID").val();
		var ctype = $("#ctype").val();
		if (pid <= 0) {
			var html = '<option value="0" id="defaultsc" selected>无</option>';
			$("#thirdID").html(html);
			$("#secondID").html(html);
			return;
		}
		this.callback("secondID", pid, ctype);

	},
	secondChange : function() {
		var pid = $("#secondID").val();
		if (pid <= 0) {
			var html = '<option value="0" id="defaultsc" selected>无</option>';
			$("#thirdID").html(html);
			return;
		}
		var ctype = $("#ctype").val();
		this.callback("thirdID", pid, ctype)
	},
	callback : function(id, pid, type) {
		$.ajax({
			url : "/platform/news/queryClassSub",
			type : "post",
			data : {
				'pid' : pid,
				"ctype" : type
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}
					var html = '<option value="0" id="defaultsc" selected>无</option>'
							+ template('slist', listdata);
					$("#" + id).html(html);
				}
			},
			error : function() {

			}
		});
	}

}
