var changebool = false;
var strmobile = "";
var strmobiletoken = "";
var img;//准备上传图片元素
// 初始化
var Init = {

	bind : function() {
		/*
		* window.basePath = getRootPath(); alert(basePath);
		*/
		// 经营范围
		GetBusinessScopHtml();
		BindClassNew();
		// 图片验证码
		$(".refleshverification").bind("click", refleshverification);

		// 获取短信验证码倒计时
		var stinxasss;
		var curTImr = 60;
		$(".g_hqyzm").click(function tim() {
			if ($("#text_principalmobile").val() == "") {
				return;
			}
			strmobiletoken = "";
			$("#text_mobilecode").attr("disabled", false);
			$(".g_hqyzm").unbind("click");
			$(this).find("b").show();
			$(this).find("font").html("秒后重新获取");
			$(this).css("color", "#999");
			curTImr = 60;
			var thisOBJ = $(this);
			stinxasss = setInterval(function() {
				curTImr--;
				thisOBJ.find("b").html(curTImr);
				if (curTImr == 0) {
					thisOBJ.find("b").hide();
					thisOBJ.find("font").html("获取短信验证码")
					thisOBJ.css("color", "#000");
					$("#text_mobilecode").attr("disabled", true);
					$(".g_hqyzm").bind("click", tim);
					clearInterval(stinxasss);
				}

			}, 1000)

			var strregistermobile = $("#text_principalmobile").val();
			var imgcode = $("#text_imgcode").val();
			var sdfds = "";
			if (!changebool) {
				if (strregistermobile == "") {
					Dalert("请输入手机号！");
					return;
				}
				sdfds = strregistermobile;
			} else {
				sdfds = strmobile;
			}
			$.ajax(( {
				type : "post",
				url : "/seller/user/send",
				dataType : "json",
				data : {
					phone : sdfds,
					type : 0,
					imgcode: imgcode
				},
				async : false,
				success : function(rsl) {
					if (rsl.code == 0) {
						var jsonObj = rsl.data;
						strmobiletoken = jsonObj.token;
					} else {
						Dalert(rsl.desc);
					}

				},
				error : function(e) {

				}
			}));

		})
		// 区域数据绑定
		BindRegion();

		// 校验
		$("#a_submit").bind("click", function() {
			if (!Check().form() || !CheckBusinessScope()) {
				return;
			}
			// 注册
			Register();
		});

		// 图片上传
		$("#img_shopimage").bind("click", function() {
			$("#file_shopimage").click();
		});
		$("#file_shopimage").change(function() {
			regshopimage();
		});
		$("#img_license").bind("click", function() {
			$("#file_license").click();
		});
		$("#file_license").change(function() {
			reglicense();
		});
		$("#file_organization").change(function(){
			regorganization();
		});
		$("#img_organization").bind("click", function() {
			$("#file_organization").click();
		});

		$("#img_tax").bind("click", function() {
			$("#file_tax").click();
		});
		$("#file_tax").change(function() {
			regtax();
		});

		$("#img_certificate").bind("click", function() {
			$("#file_certificate").click();
		});
		$("#img_certificate").bind("click", function() {
			$("#file_certificate").click();
		});
		$("#file_certificate").change(function() {
			regcertificate();
		});
		$("#file_jcbg").change(function(){
			regjcbg();
		});
		
		$("#file_scxkz").change(function(){
			regimg($(this));
		});
		$("#file_spy").change(function(){
			regimg($(this));
		});

		$("#img_bank").bind("click", function() {
			$("#file_bank").click();
		});
		$("#file_bank").change(function() {
			regbank();
		});
		
		$(".img_add").bind("click", function() {
			img=$(this);
			$(this).parent().parent().find("input[type=file]").click();
		});

	}
}

// 店铺头像
function regshopimage() {
	$.ajaxFileUpload({
		url : "/app/api/img/upload",
		secureuri : false,
		fileElementId : 'file_shopimage',
		dataType : "json",
		// ftype:上传文件类型（图片文件=1，其他文件=2）
		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
		data : {
			relationtype : 1
		},
		type : 'POST',
		success : function(result) {
			if (result.code == 0) {
				Dalert("上传成功");
				$("#img_shopimage").attr("src", $("#imgsrc").val() + result.data[0]);
				$("#shopimage").val($("#imgsrc").val() + result.data[0]);
			} else {
				var html1 = '<label id="img_license-error" class="error" for="img_shopimage">' + result.desc + '</label>';
				$("#div_shopimage").append(html1);
			}

		},
		error : function(e) {
			// alert(JSON.stringify(e));

		}
	});

	$("#file_shopimage").remove();
	var input = '<input type="file" name="file_shopimage" id="file_shopimage" onchange="regshopimage()" hidden />';
	$("#div_shopimage").append(input);
}

// 营业执照
function reglicense() {
	$.ajaxFileUpload({
		url : "/app/api/img/upload",
		secureuri : false,
		fileElementId : 'file_license',
		dataType : "json",
		// ftype:上传文件类型（图片文件=1，其他文件=2）
		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
		data : {
			relationtype : 1
		},
		type : 'POST',
		success : function(result) {
			if (result.code == 0) {
				Dalert("上传成功");
				$("#img_license").attr("src", $("#imgsrc").val() + result.data[0]);
				$("#license").val($("#imgsrc").val() + result.data[0]);
			} else {
				var html1 = '<label id="img_license-error" class="error" for="img_license">' + result.desc + '</label>';
				$("#div_license").append(html1);
			}

		},
		error : function(e) {
			// alert(JSON.stringify(e));

		}
	});

	$("#file_license").remove();
	var input = '<input type="file" name="file_license" id="file_license" onchange="reglicense()" hidden />';
	$("#div_license").append(input);
}

// 组织机构代码
function regorganization() {
	$.ajaxFileUpload({
		url : "/app/api/img/upload",
		secureuri : false,
		fileElementId : 'file_organization',
		dataType : "json",
		// ftype:上传文件类型（图片文件=1，其他文件=2）
		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
		data : {
			relationtype : 1
		},
		type : 'POST',
		success : function(result) {
			if (result.code == 0) {
				Dalert("上传成功");
				$("#img_organization").attr("src", $("#imgsrc").val() + result.data[0]);
				$("#organization").val($("#imgsrc").val() + result.data[0]);
			} else {
				var html2 = '<label id="img_organization-error" class="error" for="img_organization">' + result.desc + '</label>';
				$("#div_organization").append(html2);
			}

		},
		error : function(e) {
			// alert(JSON.stringify(e));

		}
	});

	$("#file_organization").remove();
	var input = '<input type="file" name="file_organization" id="file_organization" onchange="regorganization()" hidden />';
	$("#div_organization").append(input);
}

// 税务登录证
function regtax() {
	$.ajaxFileUpload({
		url : "/app/api/img/upload",
		secureuri : false,
		fileElementId : 'file_tax',
		dataType : "json",
		// ftype:上传文件类型（图片文件=1，其他文件=2）
		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
		data : {
			relationtype : 1
		},
		type : 'POST',
		success : function(result) {
			if (result.code == 0) {
				Dalert("上传成功");
				$("#img_tax").attr("src", $("#imgsrc").val() + result.data[0]);
				$("#tax").val($("#imgsrc").val() + result.data[0]);
			} else {
				var html3 = '<label id="img_tax-error" class="error" for="img_tax">' + result.desc + '</label>';
				$("#div_tax").append(html3);
			}

		},
		error : function(e) {
			// alert(JSON.stringify(e));
			// TODO 结束正在加载中
		}
	});

	$("#file_tax").remove();
	var input = '<input type="file" name="file_tax" id="file_tax" onchange="regtax()" hidden />';
	$("#div_tax").append(input);
}
//检测报告
function regjcbg(){
	$.ajaxFileUpload({
		url : "/app/api/img/upload",
		secureuri : false,
		fileElementId : 'file_jcbg',
		dataType : "json",
		// ftype:上传文件类型（图片文件=1，其他文件=2）
		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
		data : {
			relationtype : 1
		},
		type : 'POST',
		success : function(result) {
			if (result.code == 0) {
				Dalert("上传成功");
				$(img).attr("src", $("#imgsrc").val() + result.data[0]);
				
				$(img).next("input[type=hidden]").val($("#imgsrc").val() + result.data[0]);
			} else {
				var html4 = '<label id="img_certificate-error" class="error" for="img_certificate">' + result.desc + '</label>';
				$(img).parent().parent().find("div[class=clear]").append(html4);
			}

		},
		error : function(e) {
			// alert(JSON.stringify(e));

		}
	});
	$("#file_jcbg").remove();
	var input = '<input type="file" name="file_jcbg" id="file_jcbg" onchange="regjcbg()" hidden />';
	$(img).parent().parent().append(input);
}
//上传图片
function regimg(obj){
	$.ajaxFileUpload({
		url : "/app/api/img/upload",
		secureuri : false,
		fileElementId : $(obj).attr("id"),
		dataType : "json",
		// ftype:上传文件类型（图片文件=1，其他文件=2）
		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
		data : {
			relationtype : 1
		},
		type : 'POST',
		success : function(result) {
			if (result.code == 0) {
				Dalert("上传成功");
				$(img).attr("src", $("#imgsrc").val() + result.data[0]);
				
				$(img).next("input[type=hidden]").val($("#imgsrc").val() + result.data[0]);
			} else {
				var html4 = '<label id="img_certificate-error" class="error" for="img_certificate">' + result.desc + '</label>';
				$(img).parent().parent().find("div[class=clear]").append(html4);
			}

		},
		error : function(e) {
			// alert(JSON.stringify(e));

		}
	});
	$("#"+$(obj).attr("name")).remove();
	var input = '<input type="file" name="'+$(obj).attr("name")+'" id="'+$(obj).attr("name")+'" onchange="regimg(this)" hidden />';
	$(img).parent().parent().append(input);
}
// 公司资质
function regcertificate() {
	$.ajaxFileUpload({
		url : "/app/api/img/upload",
		secureuri : false,
		fileElementId : 'file_certificate',
		dataType : "json",
		// ftype:上传文件类型（图片文件=1，其他文件=2）
		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
		data : {
			relationtype : 1
		},
		type : 'POST',
		success : function(result) {
			if (result.code == 0) {
				Dalert("上传成功");
				$(img).attr("src", $("#imgsrc").val() + result.data[0]);
				
				$(img).next("input[type=hidden]").val($("#imgsrc").val() + result.data[0]);
			} else {
				var html4 = '<label id="img_certificate-error" class="error" for="img_certificate">' + result.desc + '</label>';
				$("#div_certificate").append(html4);
			}

		},
		error : function(e) {
			// alert(JSON.stringify(e));

		}
	});
	$("#file_certificate").remove();
	var input = '<input type="file" name="file_certificate" id="file_certificate" onchange="regcertificate()" hidden />';
	$(img).parent().parent().append(input);
}

// 开户银行许可证
function regbank() {
	$.ajaxFileUpload({
		url : "/app/api/img/upload",
		secureuri : false,
		fileElementId : 'file_bank',
		dataType : "json",
		// ftype:上传文件类型（图片文件=1，其他文件=2）
		// module: 模块（ 会员 = 0, 店铺 = 1, 平台 = 2, 产品 = 3, 其他 = 4,）
		data : {
			relationtype : 1
		},
		type : 'POST',
		success : function(result) {
			if (result.code == 0) {
				Dalert("上传成功");
				$("#img_bank").attr("src", $("#imgsrc").val() + result.data[0]);
				$("#bank").val($("#imgsrc").val() + result.data[0]);
			} else {
				var html5 = '<label id="img_bank-error" class="error" for="img_bank">' + result.desc + '</label>';
				$("#div_bank").append(html5);
			}

		},
		error : function(e) {
			// alert(JSON.stringify(e));

		}
	});

	$("#file_bank").remove();
	var input = '<input type="file" name="file_bank" id="file_bank" onchange="regbank()" hidden />';
	$("#div_bank").append(input);
}

// 刷新验证码
function refleshverification() {
	$(".refleshverification_img").attr("src", "/VerifyCodeServlet?t=" + Math.random() * 100000);
}

// 经营范围 New
function GetBusinessScopHtml() {
	$.ajax(( {
		type : "post",
		url : "/seller/user/qeuryBusinessScop",
		dataType : "json",
		data : {},
		async : false,
		success : function(rsl) {
			var num = 0;
			if (rsl.code == 0) {
				num = parseInt(rsl.data);
			}
			if (num - 1 > 0) {
				var htm = '';
				for (var i = 0; i < num - 1; i++) {
					htm += '<div class="zhxxmk">'
					htm += '<div class="zhxxmk-left"></div>'
					htm += '<div class="zhxxmk-right-szbm">'

					htm += '<div class="selcon">'
					htm += '<select class="n_inpqyzc fc_select">'
					htm += '<option value="-1">请选择</option>'
					htm += '<script id="flist" type="text/html">'
					htm += '{{each list as fclass i}}'
					htm += '<option value="{{fclass.id}}" ffullpath-data="{{fclass.fullpath}}">{{fclass.name}}</option>'
					htm += '{{/each}}'
					htm += '<\/script>'
					htm += '</select>'
					htm += '</div>'

					htm += '<div class="selcon">'
					htm += '<select class="n_inpqyzc sc_select">'
					htm += '<option value="-1">请选择</option>'
					htm += '<script id="slist" type="text/html">'
					htm += '<option value="-1">全部</option>'
					htm += '{{each list as sclass i}}'
					htm += '<option value="{{sclass.id}}" sfullpath-data="{{sclass.fullpath}}">{{sclass.name}}</option>'
					htm += '{{/each}}'
					htm += '<\/script>'
					htm += '</select>'
					htm += '</div>'

					htm += '<div class="selcon">'
					htm += '<select class="n_inpqyzc tc_select">'
					htm += '<option value="-1">请选择</option>'
					htm += '<script id="tlist" type="text/html">'
					htm += '<option value="-1">全部</option>'
					htm += '{{each list as tclass i}}'
					htm += '<option value="{{tclass.id}}" tfullpath-data="{{tclass.fullpath}}">{{tclass.name}}</option>'
					htm += '{{/each}}'
					htm += '<\/script>'
					htm += '</select>'
					htm += '</div>'

					htm += '</div>'
					htm += '</div>'
				}
				$("#div_scope").after(htm);
			}
		},
		error : function(e) {
		}
	}));
}

function GetFirstClassNew() {
	$.ajax({
		url : "/seller/user/getClassByFatherID",
		type : "Post",
		data : {
			fatherid : 0
		},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {
				Dalert(data.desc);
			} else {
				var listdata = {
					list : data.data
				}
				var html = template('flist', listdata);
				$(".fc_select").append(html);
			}
		},
		error : function() {
		}
	});
}

function BindClassNew() {

	// 一级数据绑定
	GetFirstClassNew();
	// 一级改变
	$(".fc_select").change(function() {
		var obj = $(this);
		var p1 = obj.children('option:selected').val();
		if (parseInt(p1) < 0) {
			obj.parent().parent().find(".sc_select option[value!='-1']").remove();
			obj.parent().parent().find(".tc_select option[value!='-1']").remove();
		} else {
			$.ajax(( {
				type : "post",
				url : "/seller/user/getClassByFatherID",
				dataType : "json",
				data : {
					fatherid : p1
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						var listdata = {
							list : rsl.data
						}
						var html = template('slist', listdata);
						obj.parent().parent().find(".sc_select option[value!='-1']").remove();
						obj.parent().parent().find(".tc_select option[value!='-1']").remove();
						obj.parent().parent().find(".sc_select").html(html);
					} else {
						Dalert(rsl.desc);
					}
				},
				error : function(e) {

				}
			}));
		}

	})
	// 二级改变
	$(".sc_select").change(function() {
		var obj = $(this);
		var p1 = obj.children('option:selected').val();
		if (parseInt(p1) < 0) {
			obj.parent().parent().find(".tc_select option[value!='-1']").remove();
		} else {
			$.ajax(( {
				type : "post",
				url : "/seller/user/getClassByFatherID",
				dataType : "json",
				data : {
					fatherid : p1
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						var listdata = {
							list : rsl.data
						}
						var html = template('tlist', listdata);
						obj.parent().parent().find(".tc_select option[value!='-1']").remove();
						obj.parent().parent().find(".tc_select").html(html);
					} else {
						Dalert(rsl.desc);
					}
				},
				error : function(e) {

				}
			}));
		}
	})
}

// 所在地数据绑定
function GetRegionData(rType, pCode) {
	$.ajax(( {
		type : "post",
		url : "/seller/user/queryRegion",
		dataType : "json",
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
					$("#select_province").append(html);
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

function BindRegion() {
	// 省数据绑定
	GetRegionData(0, "");

	// 省改变
	$("#select_province").change(function() {
		var p1 = $(this).children('option:selected').val();
		$.ajax(( {
			type : "post",
			url : "/seller/user/queryRegion",
			dataType : "json",
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
		$("#text_companyadress").val("");
	})
	// 市改变
	$("#select_city").change(function() {
		var p1 = $(this).children('option:selected').val();
		$.ajax(( {
			type : "post",
			url : "/seller/user/queryRegion",
			dataType : "json",
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
		$("#text_companyadress").val("");
	})
}

// 校验
function Check() {
	return $("#signupForm").validate({
		rules : {
			text_username : {
				required : true,
				checkDataRepeat : [5]
			},
			password_pwd : {
				required : true,
				byteRangeLength : [6, 16]
			},
			password_pwdagian : {
				required : true,
				equalTo : "#password_pwd"
			},

			text_shopname : {
				required : true
			},
			text_description : {
				required : true
			},

			text_principalname : {
				required : true
			},
			text_principalmobile : {
				required : true,
				isMobile : true,
				checkMobileRepeat : [5]
			},
			text_mobilecode : {
				required : true
			},
			text_principalemail : {
				email : true
			},
			text_companyname : {
				required : true
			},
			select_province : {
				selectRequired : true
			},
			select_city : {
				selectRequired : true
			},
			select_area : {
				selectRequired : true
			},
			text_companyadress : {
				required : true
			},
			select_companytype : {
				selectRequired : true
			},
			text_verification : {
				required : true,
				checkImgCode : true
			},
			settlementType : {
				selectRequired : true
			}
		},
		messages : {
			text_username : {
				required : "请输入用户名",
				checkDataRepeat : "该用户名已存在"
			},
			password_pwd : {
				required : "请输入密码",
				byteRangeLength : "密码长度不正确，请重新设置(6-16位)"
			},
			password_pwdagian : {
				required : "请输入确认密码",
				equalTo : "两次密码不一致"
			},
			text_shopname : {
				required : "请输入店铺名称"
			},
			text_description : {
				required : "请输入店铺简介"
			},
			text_principalname : {
				required : "请输入联系人姓名"
			},
			text_principalmobile : {
				required : "请输入手机号",
				isMobile : "手机号格式不正确",
				checkMobileRepeat : "该手机号已被注册"
			},
			text_mobilecode : {
				required : "请输入短信验证码"
			},
			text_principalemail : {
				email : "邮箱格式不正确"
			},
			text_companyname : {
				required : "请输入公司名称"
			},
			select_province : {
				selectRequired : "请选择公司所在省"
			},
			select_city : {
				selectRequired : "请选择公司所在市"
			},
			select_area : {
				selectRequired : "请选择公司所在区"
			},
			text_companyadress : {
				required : "请输入公司详细地址"
			},
			select_companytype : {
				selectRequired : "请选择公司性质"
			},
			settlementType : {
				selectRequired : "请选择结算类型"
			},
			text_verification : {
				required : "请输入验证码",
				checkImgCode : "验证码错误"
			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		}
	})
}

// 经营范围校验
function CheckBusinessScope() {
	$("#tc_select-error").remove();
	var result = false;
	var ary = new Array();
	$(".fc_select").each(function(e) {
		var obj = $(this);
		var val = parseInt(obj.val());
		if (val > 0) {
			result = true;
			ary[ary.length] = val;
		}
	});

	var thirdclass = new Array();
	var thirdfullpath = new Array();
	$(".jyfwbox").each(function() {
		var tcselect = $(this).find(".tc_select option:selected").val();
		var sc_select = $(this).find(".sc_select option:selected").val();
		var fc_select = $(this).find(".fc_select option:selected").val();
		if (tcselect == "-1") {
			if (sc_select == "-1") {
				if (fc_select != "-1") {
					thirdclass.push(fc_select);
					thirdfullpath.push($(this).find(".fc_select option:selected").attr("ffullpath-data"));
				}
			} else {
				thirdclass.push(sc_select);
				thirdfullpath.push($(this).find(".sc_select option:selected").attr("sfullpath-data"));
			}
		} else {
			thirdclass.push(tcselect);
			thirdfullpath.push($(this).find(".tc_select option:selected").attr("tfullpath-data"));
		}
	});

	if (!result) {
		var html1 = '<label id="tc_select-error" class="error" for="tc_select">请选择至少一种经营范围</label>';
		$("#div_scope").find(".zhxxmk-right-szbm").append(html1);
	} else {
		var nary = ary.sort();
		for (var i = 0; i < ary.length; i++) {

			if (nary[i] == nary[i + 1]) {
				result = false;
				var html1 = '<label id="tc_select-error" class="error" for="tc_select">请去除重复经营范围</label>';
				$("#div_scope").find(".zhxxmk-right-szbm").append(html1);
				break;
			}

		}

		if (result) {
			var th1 = new Array();
			var th2 = new Array();
			var th3 = new Array();
			for (var i = 0; i < thirdfullpath.length; i++) {
				var thlength = thirdfullpath[i].split(",").length;
				if (thlength == 1) {
					th1.push(thirdfullpath[i]);
				} else if (thlength == 2) {
					th2.push(thirdfullpath[i]);
				} else if (thlength == 3) {
					th3.push(thirdfullpath[i]);
				} else {
					continue;
				}
			}
			if (result) {
				for (var i = 0; i < th1.length; i++) {
					for (var k = 0; k < th2.length; k++) {
						if (th2[k].indexOf(th1[i]) >= 0) {
							var html1 = '<label id="tc_select-error" class="error" for="tc_select">请去除重复经营范围</label>';
							$("#div_scope").append(html1);
							result = false;
							break;
						}
					}
					if (result == false) {
						break;
					}
				}
				for (var i = 0; i < th1.length; i++) {
					for (var k = 0; k < th3.length; k++) {
						var th = th3[k];
						var ths = th1[i];
						var s = th3[k].indexOf(th1[i]);
						if (th3[k].indexOf(th1[i]) >= 0) {
							var html1 = '<label id="tc_select-error" class="error" for="tc_select">请去除重复经营范围</label>';
							$("#div_scope").append(html1);
							result = false;
							break;
						}
					}
					if (result == false) {
						break;
					}
				}
			}
			if (result == true) {
				for (var j = 0; j < th2.length; j++) {
					for (var k = 0; k < th3.length; k++) {
						if (th3[k].indexOf(th2[i]) >= 0) {
							var html1 = '<label id="tc_select-error" class="error" for="tc_select">请去除重复经营范围</label>';
							$("#div_scope").append(html1);
							result = false;
							break;
						}
					}
					if (result == false) {
						break;
					}
				}
			}
		}
	}

	return result;
}

// 图片校验
function CheckImg() {
	var is=true;
	$("#img_license-error").remove();
	$("#img_organization-error").remove();
	$("#img_tax-error").remove();
	$("#img_certificate-error").remove();
	$("#img_bank-error").remove();

	var license = $("#img_license").attr("src");
	var organization = $("#img_organization").attr("src");
	var tax = $("#img_tax").attr("src");
	var certificate = $("#img_certificate").attr("src");
	var bank = $("#img_bank").attr("src");
	var spy=$("input[name=spy]").val();

	if (shopimage == "" || shopimage == "/resource/public/seller/images/djsc.png") {
		var html1 = '<label id="img_license-error" class="error" for="img_shopimage">请上传店铺头像</label>';
		$("#div_shopimage").append(html1);
		is=false;
	}
	if (license == "" || license == "/resource/public/seller/images/djsc.png") {
		var html1 = '<label id="img_license-error" class="error" for="img_license">请上传营业执照扫描件</label>';
		$("#div_license").append(html1);
		is=false;
	}

	if (organization == "" || organization == "/resource/public/seller/images/djsc.png") {
		var html2 = '<label id="img_organization-error" class="error" for="img_organization">请上传组织机构代码证扫描件</label>';
		$("#div_organization").append(html2);
		is=false;
	}
	if (tax == "" || tax == "/resource/public/seller/images/djsc.png") {
		var html3 = '<label id="img_tax-error" class="error" for="img_tax">请上传税务登记证扫描件</label>';
		$("#div_tax").append(html3);
		is=false;
	}
	/*if (certificate == "" || certificate == "/sv/images/djsc.png") {
		var html4 = '<label id="img_certificate-error" class="error" for="img_certificate">请上传公司资质</label>';
		$("#div_certificate").append(html4);
		return false;
	}*/
	if (bank == "" || bank == "/resource/public/seller/images/djsc.png") {
		var html5 = '<label id="img_bank-error" class="error" for="img_bank">请上传开户银行许可证</label>';
		$("#div_bank").append(html5);
		is=false;
	}
	$.each($("input[name=jcbg]"),function(index,value){
		if ($(value).val()=="") {
			var html5 = '<label id="img_jcbg-error" class="error" for="img_jcbg">请上传近期产品检测报告</label>';
			$("#jcbg_msg").html(html5);
			is=false;
		}
	});
	$.each($("input[name=scxkz]"),function(index,value){
		if ($(value).val()=="") {
			var html5 = '<label id="img_scxkz-error" class="error" for="img_scxkz">请上传近期产品检测报告</label>';
			$("#scxkz_msg").html(html5);
			is=false;
		}
	});
	if (spy=="") {
		var html5 = '<label id="img_spy-error" class="error" for="img_scxkz">请上传近期产品检测报告</label>';
		$("#spy_msg").html(html5);
		is=false;
	}
	return is;
}

// 注册
function Register() {
	if (!CheckImg()) {
		return;
	}
	$("#img_license-error").remove();
	$("#img_organization-error").remove();
	$("#img_tax-error").remove();
	$("#img_certificate-error").remove();
	$("#img_bank-error").remove();

	// var licensevalidata = $("#img_license").attr("src");
	var organizationvalidata = $("#img_organization").attr("src");
	var taxvalidata = $("#img_tax").attr("src");
	var certificatevalidata = $("#img_certificate").attr("src");
	var bankvalidata = $("#img_bank").attr("src");
	var shopimagevalidata = $("#img_shopimage").attr("src");

	var license = $("#license").val();
	var organization = $("#organization").val();
	var tax = $("#tax").val();
	var certificate = "";
	$.each($("input[name=certificate]"),function(index,value){
		if ($(value).val()!="") {
			if (($("input[name=certificate]").length-1)==index) {
				certificate+=$(value).val();
			}else{
				certificate+=$(value).val()+",";
			}
		}
	});
	var bank = $("#bank").val();
	var shopimage = $("#shopimage").val();

	var data = {};
	data.loginname = $("#text_username").val();
	data.passwrd_pwd = $("#password_pwd").val();
	data.confirmpassword = $("#password_pwdagian").val();
	data.principalmobile = $("#text_principalmobile").val();
	// 验证码
	data.imgverification = $("#text_verification").val();
	// 短信验证码
	data.mobilecode = $("#text_mobilecode").val();
	data.mobilecodetoken = strmobiletoken;

	data.supporttel = $("#text_supporttel").val();
	data.shopname = $("#text_shopname").val();
	data.description = $("#text_description").val();
	data.thirdclass = '';
	data.thirdfullpath = '';
	data.jcbg='';
	data.scxkz='';
	data.spy=$("#spy").val();
	$.each($("input[name=jcbg]"),function(index,value){
		if ($(value).val()!="") {
			if (($("input[name=jcbg]").length-1)==index) {
				data.jcbg+=$(value).val();
			}else{
				data.jcbg+=$(value).val()+",";
			}
		}
	});
	$.each($("input[name=scxkz]"),function(index,value){
		if ($(value).val() != "") {
			if (($("input[name=scxkz]").length-1)==index) {
				data.scxkz+=$(value).val();
			}else{
				data.scxkz+=$(value).val()+",";
			}
		} 
	});
	$(".tc_select").each(function(e) {
		var obj = $(this);
		var val = parseInt(obj.val());
		if (val > 0) {
			data.thirdclass += obj.val() + '|';
			data.thirdfullpath += obj.find("option:selected").attr("tfullpath-data") + '|';
		}
	});

	if (data.thirdclass == "") {
		$(".sc_select").each(function(e) {
			var obj = $(this);
			var val = parseInt(obj.val());
			if (val > 0) {
				data.thirdclass += obj.val() + '|';
				data.thirdfullpath += obj.find("option:selected").attr("sfullpath-data") + '|';
				data.classlevel += 2 + '|';
			}
		});
	}

	if (data.thirdclass == "") {
		$(".fc_select").each(function(e) {
			var obj = $(this);
			var val = parseInt(obj.val());
			if (val > 0) {
				data.thirdclass += obj.val() + '|';
				data.thirdfullpath += obj.find("option:selected").attr("ffullpath-data") + '|';
				data.classlevel += 1 + '|';
			}
		});
	}

	data.principalname = $("#text_principalname").val();
	data.companytel = $("#text_companytel").val();
	data.principalemail = $("#text_principalemail").val();
	data.companyname = $("#text_companyname").val();
	data.licenseNum=$("#licensenum").val();  //营业执照号码
	data.license = license;
	data.organization = organization;
	data.tax = tax;
	data.certificate = certificate;
	data.bank = bank;

	data.discount = $("#text_discount").val();
	data.popularity = $("#text_popularity").val();
	data.consumption = $("#text_consumption").val();
	data.imageurl = shopimage;

	data.companyprovince = $("#select_province").val();
	data.companycity = $("#select_city").val();
	data.companyarea = $("#select_area").val();
	data.companyadress = $("#text_companyadress").val();

	data.provincename = $("#select_province").children('option:selected').text();
	data.cityname = $("#select_city").children('option:selected').text();
	data.areaname = $("#select_area").children('option:selected').text();

	data.banktype = $("#text_banktype").val();
	data.lineno = $("#text_lineno").val();
	data.bankname = $("#text_bankname").val();
	data.bankcardno = $("#text_bankcardno").val();
	data.hodername = $("#text_hodername").val();
	data.accounttype = $("#select_accounttype option:selected").val();
	data.shopcircleid = "";
	//$("#shopCircle").val();
	data.isep = $('input[name="isep"]:checked ').val();
	data.issupport = $('input[name="issupport"]:checked ').val();
	data.isfree = $('input[name="isfree"]:checked ').val();
	data.isjck = $('input[name="isjck"]:checked ').val();
	data.classid = $("#ci_select").val();

	data.companyfox = $("#text_companyfox").val();
	data.postcode = $("#text_postcode").val();
	data.companytype = $("#select_companytype").val();
	data.companyweb = $("#text_companyweb").val();
	data.companysales = $("#text_companysales").val();
	data.settlementType=$("#settlementType").val();
	// 年销售额
	// data.longitude = $("#longitude").val();
	// 经度
	// data.latitude = $("#latitude").val();
	// 维度

	$.ajax(( {
		type : "post",
		url : "/seller/user/toRegister",
		dataType : "json",
		data : data,
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert("注册成功！", "", backhref);
				window.location.href = "/seller/regsuccess";
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

			refleshverification();
		}
	}));
}

function backhref() {
	window.location.href = "/seller/login";
}