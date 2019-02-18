//初始化
var Init = {
	bind : function() {
		// 区域数据绑定
		BindRegion();

		// 新增校验
		$("#a_submit").bind("click", function() {
			if (!Check().form()) {
				return;
			}
			// 添加店铺
			AddShop();
		});

		// 编辑校验
		$("#a_submitedit").bind("click", function() {
			if (!EditCheck().form()) {
				return;
			}
			// 编辑店铺
			EditShop();
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

		$("#img_organization").bind("click", function() {
			$("#file_organization").click();
		});
		$("#file_organization").change(function() {
			regorganization();
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
		$("#file_certificate").change(function() {
			regcertificate();
		});

		$("#img_bank").bind("click", function() {
			$("#file_bank").click();
		});
		$("#file_bank").change(function() {
			regbank();
		});

		/*
		* 获取验证码
		*/
		/*
		* $("#g_hqyzm fl").bind("click", function() { getSmsValidata(); });
		*/

		// 编辑时绑定下拉列表数据
		if (parseInt($("#hidden_shopid").val()) > 0) {
			
			// 所在区域
			var privincecode = $("#hidden_province").val();

			var citycode = $("#hidden_city").val();
			var areacode = $("#hidden_area").val();
			$("#select_province").val(privincecode);
			GetRegionData(1, privincecode);
			$("#select_city").val(citycode);
			GetRegionData(2, citycode);
			$("#select_area").val(areacode);
			// 公司性质
			var companytype = $("#hidden_companytype").val();
			$("#select_companytype").val(companytype);
		}
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
				$("#img_shopimage").attr("src", result.data[0]);
				$("#shopimage").val(result.data[0]);
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
				$("#img_license").attr("src", result.data[0]);
				$("#license").val(result.data[0]);
			} else {
				$("#img_license-error").remove();
				var html1 = '<label id="img_license-error" class="error" for="img_license">' + result.desc + '</label>';
				$("#div_license").after(html1);
			}
			// TODO 结束正在加载中
		},
		error : function(e) {
			// Dalert(JSON.stringify(e));
			// TODO 结束正在加载中
		}
	});

	$("#file_license").remove();
	var input = '<input type="file" name="pic" id="file_license" onchange="reglicense()" hidden />';
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
				$("#img_organization").attr("src",result.data[0]);
				$("#organization").val(result.data[0]);
			} else {
				$("#img_organization-error").remove();
				var html2 = '<label id="img_organization-error" class="error" for="img_organization">' + result.desc + '</label>';
				$("#div_organization").after(html2);
			}
			// TODO 结束正在加载中
		},
		error : function(e) {
			// Dalert(JSON.stringify(e));
			// TODO 结束正在加载中
		}
	});

	$("#file_organization").remove();
	var input = '<input type="file" name="pic" id="file_organization" onchange="regorganization()" hidden />';
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
				$("#img_tax").attr("src", result.data[0]);
				$("#tax").val(result.data[0]);
			} else {
				$("#img_tax-error").remove();
				var html3 = '<label id="img_tax-error" class="error" for="img_tax">' + result.desc + '</label>';
				$("#div_tax").after(html3);
			}
			// TODO 结束正在加载中
		},
		error : function(e) {
			// Dalert(JSON.stringify(e));
			// TODO 结束正在加载中
		}
	});

	$("#file_tax").remove();
	var input = '<input type="file" name="pic" id="file_tax" onchange="regtax()" hidden />';
	$("#div_tax").append(input);
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
				$("#img_certificate").attr("src", result.data[0]);
				$("#certificate").val(result.data[0]);
			} else {
				$("#img_certificate-error").remove();
				var html4 = '<label id="img_certificate-error" class="error" for="img_certificate">' + result.desc + '</label>';
				$("#div_certificate").after(html4);
			}
			// TODO 结束正在加载中
		},
		error : function(e) {
			// Dalert(JSON.stringify(e));
			// TODO 结束正在加载中
		}
	});

	$("#file_certificate").remove();
	var input = '<input type="file" name="pic" id="file_certificate" onchange="regcertificate()" hidden />';
	$("#div_certificate").append(input);
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
				$("#img_bank").attr("src", result.data[0]);
				$("#bank").val(result.data[0]);
			} else {
				$("#img_bank-error").remove();
				var html5 = '<label id="img_bank-error" class="error" for="img_bank">' + result.desc + '</label>';
				$("#div_bank").after(html5);
			}
			// TODO 结束正在加载中
		},
		error : function(e) {
			// Dalert(JSON.stringify(e));
			// TODO 结束正在加载中
		}
	});

	$("#file_bank").remove();
	var input = '<input type="file" name="pic" id="file_bank" onchange="regbank()" hidden />';
	$("#div_bank").append(input);
}

// 所在地数据绑定
function GetRegionData(rType, pCode) {
	$.ajax(( {
		type : "post",
		url : "/platform/shop/queryRegion",
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
			url : "/platform/shop/queryRegion",
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
			url : "/platform/shop/queryRegion",
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
			text_shopname : {
				required : true
			},
			text_description : {
				required : true
			},
			text_margin : {
				required : true
			},
			text_supporttel : {
				required : true
			},
			text_principalname : {
				required : true
			},
			text_principalmobile : {
				required : true,
				isMobile : true
			},
			
			text_principalemail : {
				email : true
			},

			img_license : {

			},
			img_organization : {

			},
			img_tax : {

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
			longitude : {
				required : true
			},
			latitude : {
				required : true
			},
			text_postcode : {

			},
			text_phone : {
				required : true,
				isMobile : true
			},
			text_companytel : {

			},
			text_companyfox : {

			},
			select_companytype : {
				selectRequired : true
			},
			text_companyweb : {

			}
		},
		messages : {
			text_username : {
				required : "请输入用户名",
				checkDataRepeat : "该用户名已存在"
			},
			text_phone : {
				required : "请输入验证手机号",
				isMobile : "验证手机号格式不正确"
			},
			text_shopname : {
				required : "请输入店铺名称"
			},

			text_description : {
				required : "请输入店铺简介"
			},
			text_supporttel : {
				required : "请输入客服电话"
			},
			text_principalname : {
				required : "请输入联系人姓名"
			},
			text_principalmobile : {
				required : "请输入手机号",
				isMobile : "手机号格式不正确"
			},
			text_principalemail : {
				email : "邮箱格式不正确"
			},
			img_license : {

			},
			img_organization : {

			},
			img_tax : {

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
			text_postcode : {

			},
			text_companytel : {

			},
			text_companyfox : {

			},
			select_companytype : {
				selectRequired : "请选择公司性质"
			},
			text_companyweb : {

			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		},
		debug : true
	})
}

// 图片校验
function CheckImg() {
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

		if (license == "" || license == "/resource/public/seller/images/djsc.png") {
	 var html1 = '<label id="img_license-error" class="error" for="img_license">请上传营业执照扫描件</label>';
	 $("#div_license").after(html1);
	 return false;
	 }
	 if (organization == "" || organization == "/resource/public/seller/images/djsc.png") {
	 var html2 = '<label id="img_organization-error" class="error" for="img_organization">请上传组织机构代码证扫描件</label>';
	 $("#div_organization").after(html2);
	 return false;
	 }
	 if (tax == "" || tax == "/resource/public/seller/images/djsc.png") {
	 var html3 = '<label id="img_tax-error" class="error" for="img_tax">请上传税务登记证扫描件</label>';
	 $("#div_tax").after(html3);
	 return false;
	 }
	 if (certificate == "" || certificate == "/resource/public/seller/images/djsc.png") {
	 var html4 = '<label id="img_certificate-error" class="error" for="img_certificate">请上传公司资质</label>';
	 $("#div_certificate").after(html4);
	 return false;
	 }
	 if (bank == "" || bank == "/resource/public/seller/images/djsc.png") {
	 var html5 = '<label id="img_bank-error" class="error" for="img_bank">请上传开户银行许可证</label>';
	 $("#div_bank").after(html5);
	 return false;
	 }
	return true;
}

// 添加店铺
function AddShop() {

	$("#img_license-error").remove();
	$("#img_organization-error").remove();
	$("#img_tax-error").remove();
	$("#img_certificate-error").remove();
	$("#img_bank-error").remove();

	var license = $("#license").val();
	var organization = $("#organization").val();
	var tax = $("#tax").val();
	var bank = $("#bank").val();
	var shopimage = $("#shopimage").val();

	if (shopimage == "" || shopimage == "/resource/public/seller/images/djsc.png") {
		var html1 = '<label id="img_license-error" class="error" for="img_shopimage">请上传店铺头像</label>';
		$("#div_shopimage").append(html1);
		return;
	}

	var data = {};
	data.shoptype = $("#shoptype").val();
	data.marketingscope = $("#text_marketingscope").val();
	data.bewrite = $("#text_bewrite").val();
	data.loginname = $("#text_username").val();
	data.principalmobile = $("#text_principalmobile").val();
	data.phone = $("#text_phone").val();
	data.mobile = $("#text_principalmobile").val();
	data.email = $("#text_principalemail").val();
	data.username = $("#text_principalname").val();

	data.discount = $("#text_discount").val();
	data.popularity = $("#text_popularity").val();
	data.consumption = $("#text_consumption").val();
	data.accounttype = $("#select_accounttype option:selected").val();
	data.mobilecode = $("#text_mobilecode").val();
	data.shopcircleid = $("#shopCircle").val();
	data.isep = $('input[name="isep"]:checked ').val();
	data.issupport = $('input[name="issupport"]:checked ').val();
	data.isfree = $('input[name="isfree"]:checked ').val();
	data.isjck = $('input[name="isjck"]:checked ').val();
	data.banktype = $("#text_banktype").val();
	data.lineno = $("#text_lineno").val();
	data.bankname = $("#text_bankname").val();
	data.bankcardno = $("#text_bankcardno").val();
	data.hodername = $("#text_hodername").val();
	data.shopname = $("#text_shopname").val();
	data.description = $("#text_description").val();
	data.supporttel = $("#text_supporttel").val();
	data.classid = $("#ci_select").val();
	data.settlementType = $("#settlementType option:selected").val();

	data.principalname = $("#text_principalname").val();
	data.licenseNum=$("#licenseNum").val();
	data.companytel = $("#text_companytel").val();
	data.principalemail = $("#text_principalemail").val();
	data.companyname = $("#text_companyname").val();
	data.license = license;
	data.organization = organization;
	data.tax = tax;
	data.bank = bank;
	data.imageurl = shopimage;

	data.companyprovince = $("#select_province").val();
	if (data.companyprovince == "请选择" || data.companyprovince == -1) {
		data.companyprovince = "";
	}

	data.companycity = $("#select_city").val();
	if (data.companycity == "请选择" || data.companycity == -1) {
		data.companycity = "";
	}
	data.companyarea = $("#select_area").val();
	if (data.companycity == "请选择" || data.companycity == -1) {
		data.companycity = "";
	}

	data.provincename = $("#select_province").children('option:selected').text();
	data.cityname = $("#select_city").children('option:selected').text();
	data.areaname = $("#select_area").children('option:selected').text();
	if (data.provincename == "请选择" || data.provincename == -1) {
		data.provincename = "";
	}
	if (data.cityname == "请选择" || data.cityname == -1) {
		data.cityname = "";
	}
	if (data.areaname == "请选择" || data.areaname == -1) {
		data.areaname = "";
	}
	data.companyadress = $("#text_companyadress").val();
	data.companyfox = $("#text_companyfox").val();
	data.postcode = $("#text_postcode").val();
	data.companytype = $("#select_companytype").val();
	data.companyweb = $("#text_companyweb").val();
	data.companysales = $("#text_companysales").val();

	data.longitude = $("#longitude").val();
	// 经度
	data.latitude = $("#latitude").val();
	// 维度
	data.isowned = 'false';
	
	data.certificate=$("#certificate").val();
	data.examinereport=$("#examinereport").val();
	data.producelicence=$("#producelicence").val();
	data.adoctrinecredential=$("#adoctrinecredential").val();
	$.ajax({
		type : "post",
		url : "/platform/shop/add",
		dataType : "json",
		data : data,
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert("保存成功！", "", function(){
				window.location.href = "/platform/shop/list";
				});
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	});
}

// 编辑校验
function EditCheck() {
	var userid = parseInt($("#hidden_userid").val());
	return $("#signupForm").validate({
		rules : {
			text_username : {
				required : true,
			},

			text_shopname : {
				required : true
			},
			text_description : {
				required : true
			},
			text_supporttel : {
				required : true
			},
			text_principalname : {
				required : true
			},
			text_phone : {
				required : true,
				isMobile : true
			},
			text_principalmobile : {
				required : true,
				isMobile : true
			},
			text_principalemail : {
				email : true
			},

			/*text_companyname : {
			 required : true
			 },*/
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
			}
		},
		messages : {
			text_username : {
				required : "请输入用户名",
			},
			text_phone : {
				required : "请输入验证手机号",
				isMobile : "验证手机号格式不正确"
			},

			text_shopname : {
				required : "请输入店铺名称"
			},
			text_description : {
				required : "请输入店铺简介"
			},
			text_supporttel : {
				required : "请输入客服电话"
			},
			text_principalname : {
				required : "请输入联系人姓名"
			},
			text_principalmobile : {
				required : "请输入手机号",
				isMobile : "手机号格式不正确"
			},
			text_principalemail : {
				email : "邮箱格式不正确"
			},

			/*text_companyname : {
			 required : "请输入公司名称"
			 },*/
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
			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		}
	})
}

// 编辑店铺
function EditShop() {

	$("#img_license-error").remove();
	$("#img_organization-error").remove();
	$("#img_tax-error").remove();
	$("#img_certificate-error").remove();
	$("#img_bank-error").remove();

	var license = $("#license").val();
	var organization = $("#organization").val();
	var tax = $("#tax").val();
	var bank = $("#bank").val();
	var shopimage = $("#shopimage").val();

	if (shopimage == "" || shopimage == "/resource/public/seller/images/djsc.png") {
		var html1 = '<label id="img_license-error" class="error" for="img_shopimage">请上传店铺头像</label>';
		$("#div_shopimage").append(html1);
		return;
	}

	var data = {};
	data.shoptype = $("#shoptype").val();
	data.marketingscope = $("#text_marketingscope").val();
	data.bewrite = $("#text_bewrite").val();
	// data.username = $("#text_username").val();
	data.loginname = $("#text_username").val();
	data.userid = $("#hidden_userid").val();
	data.principalmobile = $("#text_principalmobile").val();
	data.phone = $("#text_phone").val();
	data.mobile = $("#text_principalmobile").val();
	data.email = $("#text_principalemail").val();
	data.username = $("#text_principalname").val();

	data.discount = $("#text_discount").val();
	data.popularity = $("#text_popularity").val();
	data.consumption = $("#text_consumption").val();
	data.shopid = $("#hidden_shopid").val();
	data.shopname = $("#text_shopname").val();
	data.description = $("#text_description").val();
	data.supporttel = $("#text_supporttel").val();
	data.shopAuthenticationid = $("#hidden_shopAuthenticationid").val();
	data.accountsid = $("#hidden_accountsid").val();

	data.settlementType = $("#settlementType option:selected").val();

	data.principalname = $("#text_principalname").val();
	data.companytel = $("#text_companytel").val();
	data.principalemail = $("#text_principalemail").val();
	data.companyname = $("#text_companyname").val();
	data.licenseNum=$("#licenseNum").val();
	data.license = license;
	data.organization = organization;
	data.tax = tax;
	// data.certificate = certificate;
	data.bank = bank;
	data.imageurl = shopimage;

	data.companyprovince = $("#select_province").val();
	data.companycity = $("#select_city").val();
	data.companyarea = $("#select_area").val();

	data.companyadress = $("#text_companyadress").val();

	data.provincename = $("#select_province").children('option:selected').text();

	data.cityname = $("#select_city").children('option:selected').text();
	data.areaname = $("#select_area").children('option:selected').text();
	if (data.provincename == "请选择" || data.provincename == -1) {
		data.provincename = "";
	}
	if (data.cityname == "请选择" || data.cityname == -1) {
		data.cityname = "";
	}
	if (data.areaname == "请选择" || data.areaname == -1) {
		data.areaname = "";
	}
	data.companyfox = $("#text_companyfox").val();
	data.postcode = $("#text_postcode").val();
	data.companytype = $("#select_companytype").val();
	data.companyweb = $("#text_companyweb").val();
	data.companysales = $("#text_companysales").val();
	data.longitude = $("#longitude").val();
	data.latitude = $("#latitude").val();

	data.accounttype = $("#select_accounttype").val();
	data.shopcircleid = $("#shopCircle").val();
	data.isep = $('input[name="isep"]:checked ').val();
	data.issupport = $('input[name="issupport"]:checked ').val();
	data.isfree = $('input[name="isfree"]:checked ').val();
	data.isjck = $('input[name="isjck"]:checked ').val();
	data.banktype = $("#text_banktype").val();
	data.lineno = $("#text_lineno").val();
	data.bankname = $("#text_bankname").val();
	data.bankcardno = $("#text_bankcardno").val();
	data.hodername = $("#text_hodername").val();
	data.classid = $("#ci_select").val();
	
	data.certificate=$("#certificate").val();
	data.examinereport=$("#examinereport").val();
	data.producelicence=$("#producelicence").val();
	data.adoctrinecredential=$("#adoctrinecredential").val();
	$.ajax({
		type : "post",
		url : "/platform/shop/update",
		dataType : "json",
		data : data,
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert("保存成功！", "", function(){
				window.location.href = "/platform/shop/list";
				});
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	});
}

function getSmsValidata() {
	alert("ss");
	var mobile = $("#text_principalmobile").val();
	var imgcode = $("#text_imgcode").val();
	if (!mobile) {
		Dalert("请输入手机号");
		return;
	}
	$.ajax(( {
		type : "post",
		url : "/seller/user/send",
		dataType : "json",
		data : {
			phone : mobile,
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
}

function backhref() {
	window.location.href = "/platform/shop/list";
}