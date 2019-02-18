//商品规格值管理
var vid, vaction;
var pcount;
var pindex;
var psize = size_product;
// 添加
function show() {
	$("#the-form-value").show();
}

function cancel() {
	$("#the-form-value").hide();
}
// 页面数据加载
function bind(index) {
	var specsid = $("#specsid").val();
	if(specsid>0){
		$.ajax({
			url : "/platform/commodity/querySpecsValueBySpecsId",
			type : "Post",
			data : {
				'specsid' : specsid,
				'page' : index,
				'size' : psize
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {

						list : data.data
					}

					var html = template('valuelist', listdata);
					// html 填充
					$("#datalist").html(html);

					if (data.data.length == 0) {
						show();
					}
					// 加载table样式：改变偶数行背景色，鼠标移动时背景色
					init();
					pcount = data.maxRow;
					pindex = data.pageIndex;

					// 分页
					$("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
				}
			},
			error : function() {
				Dalert("数据加载失败");
			}
		});
	}else{
		Dalert("规格ID为空，请刷新重试。");
	}
	
}
// 分页回调加载数据
function pagelist(index) {
	bind(index);
}
// 添加
function addvalue() {
	var value = $("#txtvalue").val();
	if (isNull(value) == "") {
		Dalert("属性值为必填项，不能为空");
	} else {
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
			// 商品规格表添加属性值
			$.ajax({
				url : "/platform/commodity/insertSpecsValue",
				type : "Post",
				data : {
					'value' : value,
					'specsid' : $("#specsid").val()
				},
				dataType : "json",
				success : function(data) {
					$("input[name='Save']").show();
					Dalert(data.desc, "", refresh);
					// location.reload();
				},
				error : function() {
					Dalert("添加属性值失败");
				}
			});
		}
}
// 删除
function delvalue(valueid) {
		// 商品规格表添加属性值
		$.ajax({
			url : "/platform/commodity/deleteSpecsValueById",
			type : "Post",
			data : {
				'id' : valueid
			},
			dataType : "json",
			success : function(data) {
				Dalert(data.desc, "", refresh);// location.reload();
			},
			error : function() {
				Dalert("删除属性值失败");
			}
		});
}
function isNull(data) {
	return (data == "" || data == undefined || data == null) ? "" : data;
}
// 更改状态
function setStatus(id, ss) {
		$.ajax({
					url : "/platform/commodity/updateValueStatus",
					type : "Post",
					data : {
						'id' : id,
						'status' : ss
					},
					dataType : "json",
					success : function(data) {

						if (data.code < 0) {
							Dalert(data.desc);
						} else {
							var td_html = "";
							if (ss == 0) {
								td_html = "<span class='lvs'><a href='#' onclick=setStatus("
										+ id + ",1)>启用</a></span>";
							} else {
								td_html = "<span class='lvs'><a href='#' onclick=setStatus("
										+ id + ",0)>禁用</a></span>";
							}
							$("#td_" + id).html(td_html);
						}
					},
					error : function() {
						Dalert("修改规格值状态失败");
					}
				});
}
// 刷新
function refresh() {
	location.reload();
}