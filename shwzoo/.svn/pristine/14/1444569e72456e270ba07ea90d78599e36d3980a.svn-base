/*商圈*/
$(document).ready(function() {
	Template.bind(1);
	// 删除信息
	$(".delete").each(function(){
		var id = $(this).parent().find("input").val();
		$(this).bind("click", function(){
			ConfirmShow("确认删除吗？", del, id);
		});
		
	})
//	$(".delete").live("click", function() {
//		var id = $(this).parent().find("input").val();
//		ConfirmShow("确认删除吗？", del, id);
//
//	});
	// 编辑
	$("body").on("click",".bjxx", function() {
		var id = $(this).parent().find("input").val();

		location.href = "/platform/shop/showBusinessDistrictAdd?id=" + id;
	});
	// 添加跳转
	$("#addbtn").click(function() {
		location.href = "/platform/shop/showBusinessDistrictAdd";
	});
	$("#searchBtn").click(function() {
		Template.bind(1);
	});
});
var pindex;
var pcount;
var psize = 10;
var Template = {
	bind : function(index) {
		var contactsname = $("#contactsname").val();
		var mobile = $("#mobile").val();
		var shopname = $("#shopname").val();
		$.ajax({
			url : "/platform/shop/queryApplyShopList",
			type : "post",
			data : {
				"page" : index,
				"size" : psize,
				"contactsname" : contactsname,
				"mobile" : mobile,
				"shopname" : shopname,
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					// alert(data.Desc);
					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#trlist").parent().children().each(function() {
						if ($(this).attr('id') != "trlist") {
							this.parentNode.removeChild(this);
						}
					})
				} else {

					var listdata = {
						list : data.data
					}
					var html = template('datalist', listdata);
					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#trlist").parent().children().each(function() {
						if ($(this).attr('id') != "trlist") {
							this.parentNode.removeChild(this);
						}
					})
					$("#trlist").after(html);
					// 分页
					pcount = data.maxRow;
					pindex = data.pageIndex;

					$("#pager").html(
							pagination(pcount, pindex, psize, "pagelist"));
				}
			},
			error : function() {
			}
		});
	}
}
function pagelist(index) {
	Template.bind(index);
}

function del(id) {
	$.ajax({
		type : "post",
		url : "/platform/shop/delApplyShop",
		dataType : "json",
		data : {
			id : id
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", refresh);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}
//修改账户冻结状态
function setStatus(id) {
	$
			.ajax({
				url : "/platform/shop/updateApplyShop",
				type : "Post",
				data : {
					'id' : id,
				},
				dataType : "json",
				success : function(data) {
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						Template.bind(1);
					}
				},
				error : function() {
					Dalert("修改状态失败");
				}
			});
}
function refresh() {
	location.reload();
}
function setDef(id) {
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/setDefaultSendTemplate",
		dataType : "json",
		data : {
			id : id
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, 1, refresh);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}

function backhref() {
	location.href = "/platform/controlpanel/showBusinessDistrictList";
}
