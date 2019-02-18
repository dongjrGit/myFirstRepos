
//表单验证
var Vaildate = {
	bind : function() {
		$("#addmemberForm").validate({
			rules : {
				text_username : {
					required : true,
					byteRangeLength : [ 3, 16 ]
				},
				text_loginpwd : {
					required : true,
					byteRangeLength : [ 6, 15 ]
				},
				text_loginpwdagain : {
					required : true,
					equalTo : "#text_loginpwd"
				},
				
				text_relname : {
					// required: true,
					rangelength : [ 2, 15 ]
				},
				text_mobile : {
				    required: true,
					isMobile : true,
					checkDataRepeat : [ 2 ]
				},
				text_email : {
					// required: true,
					email : true
				}
			},
			messages : {
				text_username : {
					required : "请输入用户名",
					byteRangeLength : "用户名字符长度不正确（3-16）26个大小写字母，数字或‘_’不能以数字开头",
					checkAccountRepeat : "用户名已注册",
				},
				text_loginpwd : {
					required : "请输入密码",
					byteRangeLength : "密码字符长度不正确（6-15）"
				},
				text_loginpwdagain : {
					required : "请输入确认密码",
					equalTo : "两次密码不一致"
				},
				
				text_relname : {
					// required: "请输入真实姓名",
					rangelength : "真实姓名字符长度不正确（4-15）"
				},
				text_mobile : {
					 required: "请输入手机号",
					isMobile : "输入的手机号格式不正确",
					checkDataRepeat : "该手机号已被注册"
				},
				text_email : {
					// required: "请输入邮箱",
					email : "输入的邮箱格式不正确"
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

// 获取出生日期、性别、证件类型、月收入水平数据
function GetSelectData() {
	// 出生日期数据绑定
	var options = {
		"dateFormat" : "bigEndian",
		"wraper" : "span",
		"maxAge" : "100",
	};
	$(".div_birthday").birthdaypicker(options);

	// 性别数据绑定
	$.ajax(({
		type : "post",
		url : "/platform/membermanagement/querySexAll",
		dataType : "json",
		cache:false,
		async : false,
		data : {},
		success : function(rsll) {
			if (rsll.code == 0) {
				var listdata = {
					list : rsll.data
				}
				
				var html = template('sexselect', listdata);
				$("#select_sex").append(html);
			} else {
				Dalert(rsll.desc);
			}
		},
		error : function(es) {

		}
	}));
}

//根据区域类型和编码获取区域数据
function GetRegionData(rType, pCode) {
	$.ajax(({
		type : "post",
		url : "/platform/membermanagement/queryRegion",
		dataType : "json",
		cache:false,
		async : false,
		data : {
			type : rType,
			code : pCode
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var listdata = {
					list : rsl.data
				};
				var html;
				switch (rType) {
				case 0:
					html = template('proviceselect', listdata);
					$("#select_provice").append(html);
					break;
				case 1:
					html = template('cityselect', listdata);
					$("#select_city").append(html);
					break;
				case 2:
					html = template('areaselect', listdata);
					$("#select_area").append(html);
					break;
				default:
					break;
				}

			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	}));
}
// 区域数据绑定
function BindRegion() {
	// 省数据绑定
	GetRegionData(0, "");

	// 省改变
	$("#select_provice").change(function() {
		var p1 = $(this).children('option:selected').val();
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/queryRegion",
			dataType : "json",
			cache:false,
			async : false,
			data : {
				type : 1,
				code : p1
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('cityselect', listdata);
					$("#select_city option[value!='-1']").remove();
					$("#select_area option[value!='-1']").remove();
					$("#select_city").append(html);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	})

	// 市改变
	$("#select_city").change(function() {
		var p1 = $(this).children('option:selected').val();
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/queryRegion",
			dataType : "json",
			cache:false,
			async : false,
			data : {
				type : 2,
				code : p1
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('areaselect', listdata);
					$("#select_area option[value!='-1']").remove();
					$("#select_area").append(html);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	})
}

function save(){
	if (check().form()) {
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		$.ajax({
			url : "/platform/member/memberAdd",
			type : "Post",
			data : $("#form").serialize(),
			dataType : "json",
			success : function(data) {
				var type = $("#type").val();
				
					Dalert("保存成功！", "", function(){location.href = "/platform/member/showMemberList"+type});
				},
			error : function() {
			}
		});
	}
}


// 表单提交
var Submit = {
	bind : function() {
		var username = $("#text_username").val();
		var nickname = $("#text_nickname").val();
		var relname = $("#text_relname").val();
		var loginpwd = $("#text_loginpwd").val();
		var mobile = $("#text_mobile").val();
		var email = $("#text_email").val();
		var levelid = $("#select_level").val();
		var idcard = $("#text_idcard").val();
		var sex = $("#select_sex").val();
		var imgurl = $("input[name=img]").val();
		
		var BYear = $(".birth-year").val();
		var BMonth = $(".birth-month").val();
		var BDay = $(".birth-day").val();
		var birthday = $("#select_date").val();
		
		var provincecode = $("#select_provice").val();
		var provincename = $("#select_provice option:selected").text();
		var citycode = $("#select_city").val();
		var cityname = $("#select_city option:selected").text();
		var areacode = $("#select_area").val();
		var areaname = $("#select_area option:selected").text();
		var address = $("#text_address").val();
		
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/addMenber",
			dataType : "json",
			data : {
				"usertype" : 2,
				"username" : username,
				"nickname" : nickname,
				"realname" : relname,
				"pwd" : loginpwd,
				"mobile" : mobile,
				"email" : email,
				"levelid" : levelid,
				"idcard" :idcard,
				"sex" :sex,
				"birthday" :birthday,
				"provincecode" :provincecode,
				"provincename" :provincename,
				"citycode" :citycode,
				"cityname":cityname,
				"areacode":areacode,
				"areaname":areaname,
				"address":address,
				"imgurl":imgurl
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert("添加成功","",function(){
						 window.location.href = "/platform/member/showMemberList";
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
