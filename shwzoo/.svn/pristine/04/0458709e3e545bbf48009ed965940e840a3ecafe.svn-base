//礼品卡充值记录
var pcount;
var pindex;
var psize = 5;
var Balance = {
	//列表
	getList : function(index) {

		$.ajax({
			type : "post",
			url : "/pc/coupon/getcardchargelist",
			dataType : "json",
			data : {
				page : index,
				size : psize
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('balancelist', listdata);

					//翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#tr_title").parent().children().each(function() {
						if ($(this).attr('id') != "tr_title") {
							this.parentNode.removeChild(this);
						}
					})
					$("#tr_title").after(html);

					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					$("#pager").html(pagination(pcount, pindex, psize, "balance_pagelist"));
				} else {
					//Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		});
	}
}

//分页回调
function balance_pagelist(index) {
	Balance.getList(index);
}

//发送短信校验
function SendCheck() {
	return $("#checkForm").validate({
		rules : {
			checkphone : {
				required : true
			}
		},
		messages : {
			checkphone : {
				required : "请输入手机号"
			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		}
	})
}

//充值
function charge() {
	var num = $("#cardnum").val();
	var cardpwd=$("#cardpwd").val();
	var phone=$("#checkphone").val();
	var phonecode=$("#checknum").val();
	$.ajax({
		type : "post",
		url : "/pc/user/chargecard",
		dataType : "json",
		data : {
			 "cardnum":num, "cardpwd":cardpwd, "phonecode":phonecode, "mobile":phone
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				alert(rsl.desc);
				  $("#cardnum").val("");
	              $("#cardpwd").val("");
	              $("#checkphone").val("");
	              $("#checknum").val("");
	              Balance.getList(1);
			} else {
				alert(rsl.desc);
			}
		},
		error : function(e) {

		}
	});
}

//校验
function check() {
	return $("#checkForm").validate({
		rules : {
			cardnum : {
				required : true
			},
			cardpwd : {
				required : true
			},
			checknum : {
				required : true
			},
			checkphone : {
				required : true
			}
		},
		messages : {
			cardnum : {
				required : "请输入礼品卡号"
			},
			cardpwd : {
				required : "请输入礼品卡密码"
			},
			checknum : {
				required : "请输入手机验证码"
			},
			checkphone : {
				required : "请输入手机号"
			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		}
	})
}

