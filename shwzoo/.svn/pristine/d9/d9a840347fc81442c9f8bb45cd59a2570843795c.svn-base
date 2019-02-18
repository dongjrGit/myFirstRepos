//员工ID
var employeeID;
// 店铺ID
var shopID;
// 员工角色ID
var emproleid;
// 初始化
var Init = {
	bind : function() {
		// 获取员工ID
		employeeID = GetQueryStringByName("employeeID");
		// 根据员工ID获取员工信息
		$.ajax(({
			type : "post",
			url : "/seller/zhglshop/queryEmployeeById",
			dataType : "json",
			data : {
				id : employeeID
			},
			async : false,
			success : function(rsl) {
				if (rsl.code == 0) {
					var jsonObj = rsl.data;
					$("#text_loginname").attr("value", jsonObj.username);
					$("#text_loginpwd").attr("value", jsonObj.password);
					$("#text_employeenum").attr("value", jsonObj.empnum);
					$("#text_employeename").attr("value", jsonObj.realname);
					$("#text_employeemobile").attr("value", jsonObj.mobile);
					$("#text_employeeemail").attr("value", jsonObj.email);
					$("#text_tel").attr("value", jsonObj.tel);
					$("#text_mark").attr("value", jsonObj.mark);
					shopID = jsonObj.shopid;
					emproleid = jsonObj.roleid;
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));

		// 获取店铺角色列表
		$.ajax(({
			type : "post",
			url : "/seller/zhglshop/queryRoleListByShopId",
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
		$("#select_employeerole").prop("value", emproleid);
	}
}

// 表单验证
var Vaildate = {
	bind : function() {
		$("#addshopEmployeeForm").validate({
			rules : {
				text_loginname : {
					required : true
				},
				text_loginpwd : {
					required : true
				},
				text_employeenum : {
					required : true
				},
				text_employeename : {
					required : true
				},
				text_employeemobile : {
					required : true,
					isMobile : true
				},
				select_employeerole : {
					selectRequired : true
				},
				text_employeeemail : {
					email : false
				}
			},
			messages : {
				text_loginname : {
					required : "请输入用户名"
				},
				text_loginpwd : {
					required : "请输入密码"
				},
				text_employeenum : {
					required : "请输入员工编号"
				},
				text_employeename : {
					required : "请输入员工姓名"
				},
				text_employeemobile : {
					required : "请输入手机号",
					isMobile : "输入的手机号格式不正确"
				},
				select_employeerole : {
					selectRequired : "请选择员工角色"
				},
				text_employeeemail : {
					email : "输入的邮箱地址格式不正确"
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
		var employeeroleid = $("#select_employeerole").val();
		var employeeloginname = $("#text_loginname").val();
		var employeeloginpwd = $("#text_loginpwd").val();
		var employeenum = $("#text_employeenum").val();
		var employeename = $("#text_employeename").val();
		var employeemobile = $("#text_employeemobile").val();
		var employeeemail = $("#text_employeeemail").val();
		var employeetel = $("#text_tel").val();
		var employeemark = $("#text_mark").val();

		$.ajax(({
			type : "post",
			url : "/seller/zhglshop/editAccount",
			dataType : "json",
			data : {
				username : employeeloginname,
				password : employeeloginpwd,
				empnum : employeenum,
				realname : employeename,
				mobile : employeemobile,
				roleid : employeeroleid,
				email : employeeemail,
				tel : employeetel,
				mark : employeemark,
				shopid : shopID,
				id : employeeID
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					
					Dalert(rsl.desc, "", function () { window.location.href = '/seller/zhglshop/showEmployeeManagement'; });
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	}
}