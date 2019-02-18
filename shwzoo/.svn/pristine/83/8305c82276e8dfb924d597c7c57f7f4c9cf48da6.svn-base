//初始化
var Init = {
	bind : function() {
		// 获取店铺角色列表
		$("#select_shop").blur(GetShopRoleList);
		// 店铺下拉数据绑定
		autoxl.bind("select_shop", getShopStartwithName);
	}
}
// 表单验证
var Vaildate = {
	bind : function() {
		$("#addshopEmployeeForm").validate({
			rules : {
				select_shop : {
					required : true
				},
				select_employeerole : {
					selectRequired : true
				},
				text_loginname : {
					required : true
				},
				text_loginpwd : {
					required : true
				},
				text_againpwd : {
					required : true,
					equalTo : "#text_loginpwd"
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
				}
			},
			messages : {
				select_shop : {
					required : "请输入店铺名称"
				},
				select_employeerole : {
					selectRequired : "请选择员工角色"
				},
				text_loginname : {
					required : "请输入用户名"
				},
				text_loginpwd : {
					required : "请输入密码"
				},
				text_againpwd : {
					required : "请输入确认密码",
					equalTo : "两次密码不一致"
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
		var shopid = $("#select_shop").attr("data");
		var employeeroleid = $("#select_employeerole").val();
		var employeeloginname = $("#text_loginname").val();
		var employeeloginpwd = $("#text_loginpwd").val();
		var employeenum = $("#text_employeenum").val();
		var employeename = $("#text_employeename").val();
		var employeemobile = $("#text_employeemobile").val();
		$.ajax(({
			type : "post",
			url : "/platform/employee/add",
			dataType : "json",
			data : {
				username : employeeloginname,
				password : employeeloginpwd,
				empnum : employeenum,
				realname : employeename,
				mobile : employeemobile,
				roleid : employeeroleid,
				shopid : shopid
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert("保存成功","",backhref);
					 
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	}
}
function backhref(){
	location.href='/platform/shop/showShopEmployeeList';
}
// 获取店铺角色列表
function GetShopRoleList() {
	$("#select_employeerole option[value!='-1']").remove();
	var shopid = $("#select_shop").attr("data");
	if (shopid != "select_shop" && parseInt(shopid) > 0)
		$.ajax(({
			type : "post",
			url : "/platform/shop/queryShopRole",
			dataType : "json",
			data : {
				shopid : shopid
			},
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
}

// 获取店铺下拉列表
function getShopStartwithName(callback, event) {
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