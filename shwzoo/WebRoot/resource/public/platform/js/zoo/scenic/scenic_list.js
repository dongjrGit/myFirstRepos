//景点设施管理
var pcount;
var pindex;
var psize = 10;
var Scenic = {
	bind : function(callback) {
		$("input[name=select_button]").bind("click", Scenic.getAll(1));
	},
	getAll : function(index) {
		// 名称
		var name = $("#scenicName").val();
		// 类别
		var type = $("#scenicType").val();
		// 区域
		var area = $("#scenicArea").val();
		$.ajax({
			url : "/zoo/scenic/querylist",
			type : "post",
			data : {
				"pageindex" : index,
				"pagesize" : psize,
				"name" : name,
				"type" : type,
				"area" : area
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
					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#list_title").parent().children().each(function() {
						if ($(this).attr('id') != "list_title") {
							this.parentNode.removeChild(this);
						}
					}) // html 填充
					$("#list_title").after(html); // 加载table样式：改变偶数行背景色，鼠标移动时背景色
					// init();
					pcount = data.maxRow;
					pindex = data.pageIndex; // 分页
					$("#pager").html(pagination(pcount, pindex, psize, "Scenic.getAll"));
				}
			},
			error : function() {
			}
		});

	},
	// 选择是否删除
	delCheck:function(id,imageid) {
		var param = {"id":id,"imageid":imageid};
		ConfirmShow("确认删除这个景点设施吗？", del, param, "是否删除");
	},
	updateCheck:function(id,state){
		var param = {"id":id,"state":state};
		if(0==state){
			ConfirmShow("确认撤销发布吗？", update, param, "是否发布");
		}
		if(1==state){
			ConfirmShow("确认发布吗？", update, param, "是否发布");
		}
		
	}
}

function update(param){
	$.ajax({
		url : "/zoo/scenic/updateState",
		type : "post",
		data :param,
		dataType : "json",
		success : function(data) {
			if(data.code==1){
				okShow(data.desc, "",200);
				return;
			}
			Dalert(data.desc, "", refresh);
		},
		error : function(XMLHttpRequest, status, error) {
			// alert(XMLHttpRequest.status);
			alert("操作失败 \r" + XMLHttpRequest.responseText);
		}
	});
}

function del(param) {
	$.ajax({
		url : "/zoo/scenic/dellist",
		type : "Post",
		data :param,
		dataType : "json",
		success : function(data) {
			Dalert(data.desc, "", refresh);
		},
		error : function(XMLHttpRequest, status, error) {
			// alert(XMLHttpRequest.status);
			alert("删除失败 \r" + XMLHttpRequest.responseText);
		}
	});
}

function refresh() {
	location.reload();
}

function add() {
	/*var type = $("#type").val();
	var ctype = $("#ctype").val();
	window.location.href = "/platform/news/listedit?type=" + type + "&ctype="
			+ ctype;*/
	
	window.location.href="/zoo/scenic/listedit";
}

function setstate(id, val) {
	$.ajax({
				url : "/platform/news/setstate",
				type : "post",
				data : {
					"id" : id,
					"isstate" : val
				},
				dataType : "json",
				success : function(data) {
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						Dalert("保存成功！");
						if (val == 0) {
							$("#td_" + id)
									.html(
											' <span class="lvs"><a data-id="'
													+ id
													+ '" data-status="1" onclick="setstate('
													+ id
													+ ',1)" class="set" href="javascript:void(0);">发布</a></span>');
						} else {
							$("#td_" + id)
									.html(
											' <span class="lvs"><a data-id="'
													+ id
													+ '" data-status="1" onclick="setstate('
													+ id
													+ ',0)" class="set" href="javascript:void(0);">未发布</a></span>');
						}
					}
				}
			});
}



