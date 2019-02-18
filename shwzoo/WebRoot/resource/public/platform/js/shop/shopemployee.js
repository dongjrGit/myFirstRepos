//店铺员工列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var ShopEmployeeList = {
	bind : function() {
		autoxl.bind("select_shop", ShopEmployeeList.getShopStartwithName, true);
	},
	getEmployeeList : function(index) {
		var name = $("#select_name").val();
		var username = $("#select_username").val();
		var sid = $("#select_shop").attr("data");
		$
				.ajax(({
					type : "post",
					url : "/platform/employee/queryEmployeeByShopidCriteria",
					dataType : "json",
					data : {
						page : index,
						size : psize,
						"employeename" : name,
						"username" : username,
						"shopid" : sid
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
							// Dalert(pindex);
							$("#pager").html(
									pagination(pcount, pindex, psize,
											"shopemployee_pagelist"));

							// 更新店铺员工
							$(".a_shopemployeeupdate").bind("click",
									updateshopemployee);
							// 锁定或解锁店铺员工
							$(".a_lockshopemployee").bind(
									"click",
									function() {
										var employeeID = $(this).parent().find(
												".hidden_employeeid").val();
										var islock = $(this).parent().find(
												".hidden_employeelock").val();
										var str = employeeID + ',' + islock;
										ConfirmShow("确定要执行该操作吗？",
												lockshopemployee, str);
									});
							// 修改密码
							$(".a_shopemployeepwdupdate").bind("click",
									updateshopemployeepwd);
							// 删除店铺员工
							$(".a_shopemployeedelete").bind(
									"click",
									function() {
										var employeeID = $(this).parent().find(
												".hidden_employeeid").val();
										ConfirmShow("确定要删除吗？", delshopemployee,
												employeeID);
									});
						} else {
							Dalert(rsl.Desc);
						}
					},
					error : function(e) {

					}
				}));
	},
	getShopStartwithName : function(callback, event) {
		var name = $("#select_shop").val();
		if (event)
			name += String.fromCharCode(event.keyCode);
		$.ajax({
			url : "/platform/shop/queryShopByLikeName",
			type : "Post",
			data : {
				"name" : name
			},
			dataType : "json",
			success : function(data) {

				if (data.code == 0) {
					var listdata = {
						list : data.data
					}
					var html = template('select_shoplist', listdata);

					if (callback) {
						callback(html);
					}
				} else {
					Dalert(data.data);
				}
			}
		});
	}
}
// 分页回调
function shopemployee_pagelist(index) {
	if (indexlog != index) {
		ShopEmployeeList.getEmployeeList(index);
	}
}

// 修改密码
function updateshopemployeepwd() {
	var employeeID = $(this).parent().find(".hidden_employeeid").val();
	$(".a_shopemployeepwdupdate")
			.attr(
					'href',
					"/platform/shop/showShopEmployeePwdUpdate?employeeID="
							+ employeeID);
}

// 删除店铺员工
function delshopemployee(employeeID) {
	$.ajax(({
		type : "post",
		url : "/platform/employee/delete",
		dataType : "json",
		data : {
			id : employeeID
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				ShopEmployeeList.getEmployeeList(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	}));
}

// 锁定或解锁店铺员工
function lockshopemployee(str) {
	var strs = str.split(',');
	var employeeID = strs[0];
	var islock = strs[1];
	$.ajax(({
		type : "post",
		url : "/platform/employee/lock",
		dataType : "json",
		data : {
			id : employeeID,
			islock : islock
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				ShopEmployeeList.getEmployeeList(indexlog);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	}));
}

// 更新店铺员工
function updateshopemployee() {
	var employeeID = $(this).parent().find(".hidden_employeeid").val();
	var shopID = $(this).parent().find(".hidden_shopid").val();
	var shopName = $(this).parent().find(".hidden_shopname").val();
	var empnum = $(this).parent().find(".hidden_empnum").val();
	var empname = $(this).parent().find(".hidden_empname").val();
	var emplogionname = $(this).parent().find(".hidden_emplogionname").val();
	var emploginpwd = $(this).parent().find(".hidden_emploginpwd").val();
	var empmobile = $(this).parent().find(".hidden_empmobile").val();
	var emproleid = $(this).parent().find(".hidden_emproleid").val();
	var gourl = "showShopEmployeeUpdate?employeeID=" + employeeID + "&shopID="
			+ shopID + "&shopName=" + shopName + "&empnum=" + empnum
			+ "&empname=" + empname + "&emplogionname=" + emplogionname
			+ "&emploginpwd=" + emploginpwd + "&empmobile=" + empmobile
			+ "&emproleid=" + emproleid;
	window.location.href = encodeURI(gourl);
}