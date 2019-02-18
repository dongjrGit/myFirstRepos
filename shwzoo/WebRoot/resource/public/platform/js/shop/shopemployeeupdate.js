//员工ID
var employeeID;
// 初始化
var Init = {
	bind : function() {
		// 获取店铺员工信息
		employeeID = decodeURI(GetQueryStringByName("employeeID"));
		var shopID = decodeURI(GetQueryStringByName("shopID"));
		var shopName = decodeURI(GetQueryStringByName("shopName"));
		var empnum = decodeURI(GetQueryStringByName("empnum"));
		var empname = decodeURI(GetQueryStringByName("empname"));
		var emplogionname = decodeURI(GetQueryStringByName("emplogionname"));
		var emploginpwd = decodeURI(GetQueryStringByName("emploginpwd"));
		var empmobile = decodeURI(GetQueryStringByName("empmobile"));
		var emproleid = decodeURI(GetQueryStringByName("emproleid"));
		// 获取店铺角色列表
		$.ajax(({
			type : "post",
			url : "/platform/shop/queryShopRole",
			dataType : "json",
			data : {
				shopid : shopID
			},
			async : false,
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('shoproleselect', listdata);
					$("#select_employeerole").append(html);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
		// $("#text_shopname").attr("value", shopID);
		var html1 = shopName
				+ '<input type="hidden" id="hidden_shopid" value="' + shopID
				+ '" />';
		$("#div_shop").html(html1)

		$("#select_employeerole").attr("value", emproleid);
		$("#text_loginname").attr("value", emplogionname);
		$("#text_loginpwd").attr("value", emploginpwd);
		$("#text_employeenum").attr("value", empnum);
		$("#text_employeename").attr("value", empname);
		$("#text_employeemobile").attr("value", empmobile);
	}
}

// 表单验证
var Vaildate = {
	bind : function() {
		$("#addshopEmployeeForm").validate({
			rules : {
				// text_shopname: {
				// required: true
				// },
				select_employeerole : {
					selectRequired : true
				},
				text_loginname : {
					required : true
				},
				// text_loginpwd: {
				// required: true
				// },
				text_employeenum : {
					required : true
				},
				text_employeename : {
					required : true
				},
				text_employeemobile : {
					required : true,
					isMobile : true
				}
			},
			messages : {
				// text_shopname: {
				// required: "请输入店铺名称"
				// },
				select_employeerole : {
					selectRequired : "请选择员工角色"
				},
				text_loginname : {
					required : "请输入用户名"
				},
				// text_loginpwd: {
				// required: "请输入登录密码"
				// },
				text_employeenum : {
					required : "请输入员工编号"
				},
				text_employeename : {
					required : "请输入员工姓名"
				},
				text_employeemobile : {
					required : "请输入手机号",
					isMobile : "输入的手机号格式不正确"
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			},
			debug : true,
			submitHandler : function(form) {
				$(form).ajaxSubmit(Submit.bind());
			}
		})
	}
}
// 表单提交
var Submit = {
	bind : function() {
		var shopid = $("#hidden_shopid").val();
		var employeeroleid = $("#select_employeerole").val();
		var employeeloginname = $("#text_loginname").val();
		var employeenum = $("#text_employeenum").val();
		var employeename = $("#text_employeename").val();
		var employeemobile = $("#text_employeemobile").val();

		$.ajax(({
			type : "post",
			url : "/platform/employee/update",
			dataType : "json",
			data : {
				username : employeeloginname,
				empnum : employeenum,
				realname : employeename,
				mobile : employeemobile,
				roleid : employeeroleid,
				shopid : shopid,
				id : employeeID
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert("操作成功！", "", function(){
						window.location.href = "/platform/shop/showShopEmployeeList";
					});
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	}
}
function backhref() {
	window.location.href = "/platform/shop/showShopEmployeeList";
}