//初始化
var Init = {
	bind : function() {
		// 保存按钮点击
		$("#submit_ok").bind("click", function() {
			if (!Check().form()) {
				return;
			}
			// 添加店铺会员
			AddShopEmployee();
		});
	}
}
// 验证
function Check() {
	return $("#addshopEmployeeForm").validate({
		rules : {
			text_loginname : {
				required : true
			},
			text_loginpwd : {
				required : true
			},
			text_loginpwd2 : {
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
			},
			select_employeerole : {
				selectRequired : true
			},
			text_employeeemail : {
				email : true
			}
		},
		messages : {
			text_loginname : {
				required : "请输入用户名"
			},
			text_loginpwd : {
				required : "请输入密码"
			},
			text_loginpwd2 : {
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
		debug : true
	})
}
// 添加店铺会员
function AddShopEmployee() {
	var shopid = $("#hidden_shopid").val();
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
		url : "/seller/zhglshop/addAccount",
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
			shopid : shopid
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				$("#text_loginname").attr("value", "");
				$("#text_loginpwd").attr("value", "");
				$("#text_loginpwd2").attr("value", "");
				$("#text_employeenum").attr("value", "");
				$("#text_employeename").attr("value", "");
				$("#text_employeemobile").attr("value", "");
				$("#text_employeeemail").attr("value", "");
				$("#text_tel").attr("value", "");
				$("#text_mark").attr("value", "");
				Dalert(rsl.desc, "", function () { window.location.href = '/seller/zhglshop/showEmployeeManagement'; });
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	}));
}
