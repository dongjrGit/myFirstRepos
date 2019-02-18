//初始化
var Init = {
	bind : function() {
		// 获取出生日期、性别、证件类型、月收入水平数据
		GetSelectData();

		// 区域数据绑定
		BindRegion();
		var userid = $("#hidden_userid").val();
		// 卖家数据绑定
		BindSellerData(userid);
	}
}
// 表单验证
var Vaildate = {
	bind : function() {
		$("#saveSellerForm").validate({
			rules : {
				text_nickname : {
					required : true
				},
				text_realname : {
					required : true
				},
				select_sex : {
					selectRequired : true
				}
			},
			messages : {
				text_nickname : {
					required : "请输入昵称"
				},
				text_realname : {
					required : "请输入真实姓名"
				},
				select_sex : {
					selectRequired : "请选择性别"
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
		// var email = $(".text_email").val();
		var userid = $("#hidden_userid").val();
		var userattrid = $("#hidden_userattrid").val();

		// var imgurl = $("#imgurl").val();
		var nickname = $("#text_nickname").val();
		var realname = $("#text_realname").val();
		var sex = $("#select_sex").val();

		var BYear = $(".birth-year").val();
		var BMonth = $(".birth-month").val();
		var BDay = $(".birth-day").val();

		var phone = $("#text_phone").val();
		// var mobile = $(".text_mobile").val();
		var provincecode = "";
		var provincename = "";
		if ($("#select_province").val() != "-1") {
			provincecode = $("#select_province").val();
			provincename = $("#select_province  option:selected").text();
		}
		var citycode = "";
		var cityname = "";
		if ($("#select_city").val() != "-1") {
			var citycode = $("#select_city").val();
			var cityname = $("#select_city  option:selected").text();
		}
		var areacode = "";
		var areaname = "";
		if ($("#select_area").val() != "-1") {
			areacode = $("#select_area").val();
			areaname = $("#select_area  option:selected").text();
		}

		var address = $("#text_address").val();
		var postcode = $("#text_postcode").val();
		var cardtype = $("#select_cardtype").val();
		var card = $("#text_card").val();
		var incomemonth = "";
		if ($("#select_incomemonth").val() != "") {
			incomemonth = $("#select_incomemonth").val();
		}

		if (!(BYear != 0 && BMonth != 0 && BDay != 0)) {
			if (!(BYear == 0 && BMonth == 0 && BDay == 0)) {
				Dalert("请选择出生日期！")
				return;
			}
		}

		if (phone != "" && phone != null) {
			var myreg = /^[0-9]+$/;
			if (!myreg.test(phone)) {
				Dalert("请输入正确的固定电话！");
				return;
			}
		}

		if (provincecode == "" || citycode == "" || areacode == "") {
			Dalert("请选择所在地区！")
			return;
		}

		if (postcode != "" && postcode != null) {
			var myreg = /^[1-9][0-9]{5}$/;
			if (!myreg.test(postcode)) {
				Dalert("请输入正确的邮政编码！");
				return;
			}
		}

		if (cardtype == "1") {
			if (card == "") {
				Dalert("请输入证件号！");
				return;
			} else {
				var myreg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
				if (!myreg.test(card)) {
					Dalert("请输入正确的身份证！");
					return;
				}
			}

		} else if (cardtype == "2") {
			if (card == "") {
				Dalert("请输入证件号！");
				return;
			} else {
				var myreg = /^[1-9][0-9]{5}$/;
				if (!myreg.test(card)) {
					Dalert("请输入正确的学生证！");
					return;
				}
			}
		} else {
			Dalert("请选择证件类型！");
			return;
		}

		$.ajax(({
			type : "post",
			url : "/seller/zhglshop/updateSellerById",
			dataType : "json",
			data : {
				userid : userid,
				userattrid : userattrid,
				nickname : nickname,
				realname : realname,
				sex : sex,
				birthyear : BYear,
				birthmonth : BMonth,
				birthday : BDay,
				phone : phone,
				provincename : provincename,
				provincecode : provincecode,
				cityname : cityname,
				citycode : citycode,
				areaname : areaname,
				areacode : areacode,
				address : address,
				postcode : postcode,
				idcardtype : cardtype,
				idcard : card,
				incomemonth : incomemonth
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert("保存成功", "", refresh);
					// location.reload();
					// alert(rsl.Desc);
					// getmemberbasicinfo();
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	}
}

function GetRegionData(rType, pCode) {
	$.ajax(({
		type : "post",
		url : "/seller/zhglshop/queryRegion",
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
		$.ajax(({
			type : "post",
			url : "/seller/zhglshop/queryRegion",
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
	})

	// 市改变
	$("#select_city").change(function() {
		var p1 = $(this).children('option:selected').val();
		$.ajax(({
			type : "post",
			url : "/seller/zhglshop/queryRegion",
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
	})
}

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
		url : "/seller/zhglshop/querySexAll",
		dataType : "json",
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

	// 证件类型数据绑定
	$.ajax(({
		type : "post",
		url : "/seller/zhglshop/queryCardType",
		dataType : "json",
		async : false,
		data : {},
		success : function(rsll) {
			if (rsll.code == 0) {
				var listdata = {
					list : rsll.data
				}
				var html = template('cardtypeselect', listdata);
				$("#select_cardtype").append(html);
			} else {
				Dalert(rsll.desc);
			}
		},
		error : function(es) {

		}
	}));

	// 月收入水平类型数据绑定
	$.ajax(({
		type : "post",
		url : "/seller/zhglshop/queryInComeMonth",
		dataType : "json",
		async : false,
		data : {},
		success : function(rsll) {
			if (rsll.code == 0) {
				var listdata = {
					list : rsll.data
				}
				var html = template('incomemonthtypeselect', listdata);
				$("#select_incomemonth").append(html);
			} else {
				Dalert(rsll.desc);
			}
		},
		error : function(es) {

		}
	}));
}

function BindSellerData(userid) {
	$.ajax(({
				type : "post",
				url : "/seller/zhglshop/queryMemberById",
				dataType : "json",
				data : {
					userid : userid
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						var jsonObj = rsl.data;
						$("#text_nickname").attr("value", jsonObj.nickname);
						$("#text_realname").attr("value", jsonObj.realname);
						$("#text_phone").attr("value", jsonObj.phone);
						$("#text_address").attr("value", jsonObj.address);
						$("#text_postcode").attr("value", jsonObj.postcode);
						$("#hidden_userid").attr("value", jsonObj.userid);
						$("#hidden_userattrid").attr("value", jsonObj.id);
						// 证件类型
						if (jsonObj.idcardtype == null
								|| jsonObj.idcardtype == "") {
							jsonObj.idcardtype = "-1";
						}
						$("#select_cardtype").attr("value", jsonObj.idcardtype);

						$("#text_card").attr("value", jsonObj.idcard);
						// 收入水平
						if (jsonObj.incomemonth == null
								|| jsonObj.incomemonth == "") {
							jsonObj.incomemonth = "-1";
						}
						$("#select_incomemonth").attr("value",
								jsonObj.incomemonth);
						// 性别
						if (jsonObj.sex == null || jsonObj.sex == "") {
							jsonObj.sex = "-1";
						}
						$("#select_sex").attr("value", jsonObj.sex);
						// 生日
						var stryear = 0;
						if (jsonObj.year != null && jsonObj.year != "") {
							stryear = jsonObj.year;
						}
						var strmonth = 0;
						if (jsonObj.month != null && jsonObj.month != "") {
							strmonth = jsonObj.month;
						}
						var strday = 0;
						if (jsonObj.day != null && jsonObj.day != "") {
							strday = jsonObj.day;
						}
						$(".birth-year").addClass("sel_allmost");
						$(".birth-year").attr("value", stryear);
						$(".birth-month").addClass("sel_allmost");
						$(".birth-month").attr("value", strmonth);
						$(".birth-day").addClass("sel_allmost");
						$(".birth-day").attr("value", strday);
						// 所在地域
						var strprovincecode = -1;
						if (jsonObj.provincecode != null
								&& jsonObj.provincecode != "") {
							strprovincecode = jsonObj.provincecode;
						}
						var strcitycode = -1;
						if (jsonObj.citycode != null && jsonObj.citycode != "") {
							strcitycode = jsonObj.citycode;
						}
						var strareacode = -1;
						if (jsonObj.areacode != null && jsonObj.areacode != "") {
							strareacode = jsonObj.areacode;
						}
						$("#select_province").attr("value", strprovincecode);
						GetRegionData(1, strprovincecode);
						$("#select_city").attr("value", strcitycode);
						GetRegionData(2, strcitycode);
						$("#select_area").attr("value", strareacode);
					} else {
						Dalert(rsl.desc);
					}
				},
				error : function(es) {

				}
			}));
}
function refresh() {
	location.reload();
}