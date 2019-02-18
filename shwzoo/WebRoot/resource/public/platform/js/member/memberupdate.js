$(function(){
	

//会员ID
var memberID;
// 初始化
var Init = {
	bind : function() {
		// 获取会员ID
		memberID = GetQueryStringByName("memberID");
		// 获取出生日期、性别、证件类型、月收入水平数据
		GetSelectData();
		// 区域数据绑定
		BindRegion();
		// 会员数据绑定
		BindMemberData();

	}
}
//初始化
        Init.bind();

        //表单提交按钮
        $("#submit_ok").click(function(){
        	if(check().form()){
        		Submit.bind();
        	}
        })  
// 表单验证
function check() {	
	return	$("#updatememberForm").validate({
			rules : {
				text_username : {
					required : true,
					// stringCheck: true,
					byteRangeLength : [ 3, 16 ]
				},
				text_loginpwd : {
					required : true,
					byteRangeLength : [ 6, 15 ]
				},
				text_relname : {
					// required: true,
					rangelength : [ 2, 15 ]
				},
				text_mobile : {
					required: true,
					isMobile : true
				},
				text_tel : {
					digits : true
				},
				text_email : {
					// required: true,
					email : true
				},
				text_postcode : {
					digits : true,
					minlength : 6
				},
				text_card : {
					digits : true
				}
			},
			messages : {
				text_username : {
					required : "请输入用户名",
					byteRangeLength : "用户名字符长度不正确（3-16）"
				},
				text_loginpwd : {
					required : "请输入密码",
					byteRangeLength : "密码字符长度不正确（6-15）"
				},
				text_relname : {
					// required: "请输入真实姓名",
					rangelength : "真实姓名字符长度不正确（4-15）"
				},
				text_mobile : {
					required: "请输入手机号",
					isMobile : "输入的手机号格式不正确"
				},
				text_tel : {
					digits : " 输入的电话号码格式不正确"
				},
				text_email : {
					// required: "请输入邮箱",
					email : "输入的邮箱格式不正确"
				},
				text_postcode : {
					digits : "输入的邮政编码不正确",
					minlength : "输入的邮政编码不正确"
				},
				text_card : {
					digits : "输入的证件号格式错误"
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			}
	});	
}

// 表单提交
var Submit = {
	bind : function() {
		var memberid = $("#hidden_memberid").val();
		var username = $("#text_username").val();
		var imgurl = $("input[name=img]").val();
		var idcard = $("#text_idcard").val();
		// var loginpwd = $("#text_loginpwd").val();
		var nickname = $("#text_nickname").val();
		var relname = $("#text_relname").val();
		var mobile = $("#text_mobile").val();
		var email = $("#text_email").val();
		var phone = $("#text_tel").val();
		var sex = $("#select_sex").val();
		var levelid = $("#select_level").val();

		// var birthday = $("#").val();
		var BYear = $(".birth-year").val();
		var BMonth = $(".birth-month").val();
		var BDay = $(".birth-day").val();
		var brithday = $("#select_date").val();
		
		var provincecode = $("#select_provice").val();
		var provincename = $("#select_provice option:selected").text();
		var citycode = $("#select_city").val();
		var cityname = $("#select_city option:selected").text();
		var areacode = $("#select_area").val();
		var areaname = $("#select_area option:selected").text();
		var address = $("#text_address").val();
		var postcode = $("#text_postcode").val();
		var cardtype = $("#select_cardtype").val();
		var card = $("#text_card").val();
		var incomeMonth = $("#select_incomemonth").val();
		$.ajax(({
			type : "post",
			url : "/platform/membermanagement/updateMenberById",
			dataType : "json",
			data : {
				usertype : 2,
				memberid : memberid,
				username : username,
				imgurl : imgurl,
				idcard : idcard,
				pwd : "",
				nickname : nickname,
				realname : relname,
				mobile : mobile,
				email : email,
				phone : phone,
				sex : sex,
				birthyear : BYear,
				birthmonth : BMonth,
				birthday : BDay,
				birth: brithday,
				provincecode : provincecode,
				provincename : provincename,
				citycode : citycode,
				cityname : cityname,
				areacode : areacode,
				areaname : areaname,
				address : address,
				postcode : postcode,
				idcardtype : cardtype,
				incomemonth : incomeMonth,
				levelid : levelid
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert("编辑成功","",function(){
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

	// 证件类型数据绑定
	$.ajax(({
		type : "post",
		url : "/platform/membermanagement/queryCardType",
		dataType : "json",
		cache:false,
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
		url : "/platform/membermanagement/queryInComeMonth",
		dataType : "json",
		cache:false,
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

// 根据区域类型和编码获取区域数据
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

// 会员数据绑定
function BindMemberData() {
	$.ajax(({
		type : "post",
		url : "/platform/membermanagement/queryMemberById",
		dataType : "json",
		cache:false,
		async : false,
		data : {
			memberid : memberID
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var memberdetail = rsl.data;
				$("#hidden_memberid").attr("value", memberdetail.userid);
				$("#text_username").attr("value", memberdetail.username);
				// $("#text_loginpwd").attr("value",
				// memberdetail.User.Password);
				$("#text_nickname").attr("value", memberdetail.nickname);
				$("#text_relname").attr("value", memberdetail.realname);
				$("#text_mobile").attr("value", memberdetail.mobile);
				$("#text_email").attr("value", memberdetail.email);
				$("#loadimg").attr("src", memberdetail.imgurl);
				$("#text_idcard").attr("value", memberdetail.idcard);
				if (memberdetail.levelid == null || memberdetail.levelid == ""
						|| memberdetail.levelid == "0") {
					memberdetail.levelid = "-1";
				}
				//$("#select_level").attr("value", memberdetail.levelid);
				
				 $.each($("#select_level option"),function(index,item){
                 	if(item.value==memberdetail.levelid){
                 		$(this).attr("selected","selected");
                 	}
                 });
				 

//				if (memberdetail.sex == null || memberdetail.sex == "") {
//					memberdetail.sex = "-1";
//				}
				
				//$("#select_sex").attr("value", memberdetail.sex);
				 $.each($("#select_sex option"),function(index,item){
	                 	if(item.value==memberdetail.sex){
	                 		$(this).attr("selected","selected");
	                 	}
	            });
				
				// 生日
				var stryear = 0;
				if (memberdetail.year != null && memberdetail.year != "") {
					stryear = memberdetail.year;
				}
				var strmonth = 0;
				if (memberdetail.month != null && memberdetail.month != "") {
					strmonth = memberdetail.month;
				}
				var strday = 0;
				if (memberdetail.day != null && memberdetail.day != "") {
					strday = memberdetail.day;
				}
				$(".birth-year").attr("value", stryear);
				$(".birth-month").attr("value", strmonth);
				$(".birth-day").attr("value", strday);
				$("#select_date").attr("value", memberdetail.birthdaystr);

				$("#text_tel").attr("value", memberdetail.phone);
				// 区域
				if (memberdetail.provincecode == null
						|| memberdetail.provincecode == "") {
					memberdetail.provincecode = -1;
				}
				if (memberdetail.citycode == null
						|| memberdetail.citycode == "") {
					memberdetail.citycode = -1;
				}
				if (memberdetail.areacode == null
						|| memberdetail.areacode == "") {
					memberdetail.areacode = -1;
				}
				
				//$("#select_provice").attr("value", memberdetail.provincecode);
				 $.each($("#select_provice option"),function(index,item){
	                 	if(item.value==memberdetail.provincecode){
	                 		$(this).attr("selected","selected");
	                 	}
	            });
				
                
				GetRegionData(1, memberdetail.provincecode);
				//$("#select_city").attr("value", memberdetail.citycode);
				$.each($("#select_city option"),function(index,item){
	                 	if(item.value==memberdetail.citycode){
	                 		$(this).attr("selected","selected");
	                 	}
	            });
                
				GetRegionData(2, memberdetail.citycode);
				//$("#select_area").attr("value", memberdetail.areacode);
				$.each($("#select_area option"),function(index,item){
                 	if(item.value==memberdetail.areacode){
                 		$(this).attr("selected","selected");
                 	}
                });

				$("#text_address").attr("value", memberdetail.address);
				$("#text_postcode").attr("value", memberdetail.postcode);

				// 证件
				if (memberdetail.idcardtype == null
						|| memberdetail.idcardtype == "") {
					memberdetail.idcardtype = "-1";
				}
				//$("#select_cardtype").attr("value", memberdetail.idcardtype);
				$.each($("#select_cardtype option"),function(index,item){
                 	if(item.value==memberdetail.idcardtype){
                 		$(this).attr("selected","selected");
                 	}
                });
				
				$("#text_card").attr("value", memberdetail.idcard);
				// 月收入水平
				if (memberdetail.incomemonth == null
						|| memberdetail.incomemonth == "") {
					memberdetail.incomemonth = "-1";
				}
				//$("#select_incomemonth").attr("value", memberdetail.incomemonth);
				$.each($("#select_incomemonth option"),function(index,item){
                 	if(item.value==memberdetail.incomemonth){
                 		$(this).attr("selected","selected");
                 	}
                });
				
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {

		}
	}));
}
})