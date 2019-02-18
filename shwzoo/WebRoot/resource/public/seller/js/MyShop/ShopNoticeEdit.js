/*店铺公告编辑/添加 author：rw*/
$(document)
		.ready(
				function() {
					var id = GetQueryStringByName("id");// 获取地址栏参数信息
					// js/GetQueryString.js
					var edituser = GetQueryStringByName("editUser");
					var shopid = GetQueryStringByName("shopid");
					// alert(shopid);

					GetByID(id);// 根据公告id获取公告信息
					if (id == "" || id < 0) {
						$(".position")
								.html("您所在的位置：我的店铺 &gt; 店铺管理 &gt; 店铺公告添加")
					}
					// 表单验证
					function check() {
						return $("#noticeEdit").validate({
							rules : {
								title : {
									required : true,
									maxlength : 50
								},
								content : {
									required : true,
									maxlength : 200
								}
							},
							messages : {
								title : {
									required : "请输入标题",
									maxlenght : "最多输入50个字符"
								},
								content : {
									required : "请输入公告内容",
									maxlenght : "最多输入200个字符"
								}
							},
							errorPlacement : function(error, element) {
								error.appendTo(element.parent());
							}
						})
					}
					// 保存/编辑
					function SaveOrAdd() {
						if (id > 0) {
							save(id, shopid);
						} else {
							Add(shopid, edituser)
						}
					}
					// 返回
					$("#backbtn")
							.click(
									function() {
										location.href = "showShopNotice?cur=ssc&dcur=dsnc&shopid="
												+ shopid
												+ "&editUser="
												+ edituser;
									})
					$("#savebtn").click(function() {
						if (check().form()) {
							SaveOrAdd();
						}
					})
				});
// 编辑公告 id：公告id
function save(id) {
	var ti = $("#title").val();
	var co = $("#content").val();
	$.ajax({
		type : "post",
		url : "/seller/shop/editShopNotice",
		dataType : "json",
		data : {
			id : id,
			title : ti,
			content : co
		},
		success : function(data) {
			if (data.code < 0) {
				Dalert(data.desc);
			} else {
				var edituser = GetQueryStringByName("editUser");
				var shopid = GetQueryStringByName("shopid");
				Dalert(data.desc);
				location.href = "/seller/shop/showShopNotice?cur=ssc&dcur=dsnc&shopid=" + shopid
			+ "&editUser=" + edituser;
			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}
// 添加公告 sid：店铺id，ad：添加用户id
function Add(sid, ad) {
	$("#savebtn").hide();
	var ti = $("#title").val();
	var co = $("#content").val();
	$.ajax({
		type : "post",
		url : "/seller/shop/addShopNotice",
		dataType : "json",
		data : {
			shopid : sid,
			title : ti,
			content : co,
		},
		success : function(data) {
			if (data.code < 0) {
				Dalert(data.desc);
				$("#savebtn").show();
			} else {
				var edituser = GetQueryStringByName("editUser");
				var shopid = GetQueryStringByName("shopid");
				Dalert(data.desc);
				location.href = "/seller/shop/showShopNotice?cur=ssc&dcur=dsnc&shopid=" + shopid
			+ "&editUser=" + edituser;
			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}
// 根据公告id获取公告信息 id：公告id
function GetByID(id) {
	if (id > 0)
		$.ajax({
			url : "/seller/shop/queryNoticById",
			type : "post",
			data : {
				id : id
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					// Dalert(data.Desc);
				} else {
					var af = data.data;
					$("#title").attr("value", af.title);
					$("#content").attr("value", af.content);
				}

			}
		});
}
// 返回列表页
function backhref() {
	location.href = "showShopNotice?cur=ssc&dcur=dsnc&shopid=" + shopid
			+ "&editUser=" + edituser;
}