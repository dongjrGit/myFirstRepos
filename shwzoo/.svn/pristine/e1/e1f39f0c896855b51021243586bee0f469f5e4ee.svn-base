//店铺员工列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var ShopEmployeeList = {
	bind : function(index) {
		var username = $("#username").val();
		var realname = $("#realname").val();
		var shopid = $("#shopid").val();
		var rid = $("#select_role").attr("data");
		$
				.ajax(({
					type : "post",
					url : "/seller/zhglshop/qeuryAccountsListByCriteria",
					dataType : "json",
					data : {
						page : index,
						size : psize,
						"username" : username,
						"realname" : realname,
						"roleid" : rid,
						"shopid" : shopid
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							var listdata = {
								list : rsl.data
							}
							var html = template('shopemployeelist', listdata);

							// 翻页时删除表头以外的所有节点，避免after()方法重复加载
							$("#shopemployeelist_title")
									.parent()
									.children()
									.each(
											function() {
												if ($(this).attr('id') != "shopemployeelist_title") {
													this.parentNode
															.removeChild(this);
												}
											})
							$("#shopemployeelist_title").after(html);

							indexlog = index;
							pcount = rsl.maxRow;
							pindex = rsl.pageIndex;
							// alert(pindex);
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"shopemployee_pagelist"));

							// 编辑店铺员工
							$(".a_shopemployeeupdate").bind("click",
									updateshopemployee);
							// 锁定或解锁店铺员工
							$(".a_lockshopemployee").bind("click",
									lockshopemployee);
							// 删除店铺员工
							$(".a_shopemployeedelete").bind("click",
									delshopemployee);
							// 修改店铺员工密码
							$(".a_shopemployeepassword").bind("click",
									updateshopemployeePW);
						} else {
							Dalert(rsl.desc);
						}
					},
					error : function(e) {

					}
				}));
	}
}
$(function() {
	$("input[name=search]").bind("click", function() {
		ShopEmployeeList.bind(1);
	});
	autoxl.bind("select_role", getRoleStartwithName);
})
// 分页回调
function shopemployee_pagelist(index) {
	if (indexlog != index) {
		ShopEmployeeList.bind(index);
	}
}

// 删除店铺员工
function delshopemployee() {
	var employeeID = $(this).parent().find(".hidden_employeeid").val();
	$.ajax(({
		type : "post",
		url : "/seller/zhglshop/deleteAccount",
		dataType : "json",
		data : {
			id : employeeID
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				ShopEmployeeList.bind(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	}));
}

// 锁定或解锁店铺员工
function lockshopemployee() {
	var employeeID = $(this).parent().find(".hidden_employeeid").val();
	var islock = $(this).parent().find(".hidden_employeelock").val();
	$.ajax(({
		type : "post",
		url : "/seller/zhglshop/updateEmployeeLock",
		dataType : "json",
		data : {
			id : employeeID,
			islock : islock
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				ShopEmployeeList.bind(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	}));
}

// 编辑店铺员工
function updateshopemployee() {
	var employeeID = $(this).parent().find(".hidden_employeeid").val();
	$(".a_shopemployeeupdate").attr(
			'href',
			"/seller/zhglshop/showEmployeeManagementEdit?employeeID="
					+ employeeID);
}

/*
 * callback 成功 有数据时 调的方法 event 事件
 */
function getRoleStartwithName(callback, event) {
	var name = $("#select_role").val();
	var shopid = $("#shopid").val();
	if (event)
		name += String.fromCharCode(event.keyCode);
	$.ajax({
		url : "/seller/zhglshop/queryRoleLikeName",
		type : "Post",
		data : {
			"name" : name,
			"shopid" : shopid
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 0) {
				var listdata = {
					list : data.data
				}
				var html = template('select_rolelist', listdata);

				if (callback) {
					callback(html);
				}
			} else {
				Dalert(data.data);
			}
		}
	});
}

// 修改密码
function updateshopemployeePW() {
	var employeeID = $(this).parent().find(".hidden_employeeid").val();
	var employeename = $(this).parent().find(".hidden_employeename").val();
	var contentHTML = "";
	contentHTML += "<span>修改密码：</span><input type='password' name='pass' class='text-inp' value='' /><p style='height: 10px;'></p>";
	contentHTML += "<span>确认密码：</span><input type='password' name='compass' class='text-inp' value='' /><p style='height: 10px;'></p>";
	contentHTML += "<span id='yanz' class='red'>注：密码长度大于等于6，两次密码输入必须一致</span>";
	d = dialog({
		title : "用户 " + employeename,
		content : contentHTML,
		width : 350,
		height : 80,
		button : [
				{
					value : '确认',
					callback : function() {

						if ($("input[name=pass]").val() != ""
								&& $("input[name=pass]").val() != undefined
								&& $("input[name=pass]").val().length >= 6
								&& $("input[name=pass]").val() == $(
										"input[name=compass]").val()) {
							$
									.ajax({
										url : "/seller/zhglshop/updateEmployeePassword",
										type : "Post",
										data : {
											"id" : employeeID,
											"password" : $("input[name=pass]")
													.val()
										},
										dataType : "json",
										success : function(data) {
											if (data.code < 0) {
												Dalert(data.desc);
											} else {
												Dalert(data.desc);
												// location.reload();
											}
										},
										error : function() {

										}
									});
						} else {

							return false;
						}

					},
					autofocus : true
				}, {
					value : '关闭'
				} ]
	// modal: true, //蒙层
	});
	d.show();
}
