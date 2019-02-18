//运费模版
$(document).ready(function() {
	/*var uid = $("#userID").val();
	var sid = $("#shopName").val();
	var uType = $("#userType").val();// 用户类型

	if (uType == 3 || uType == 4) {
		
	}*/
	var ftid = $("#ftid").val();
	Freight.bind(ftid);
	/*var index = "";
	$(".sel-yfmbdz").live("click", function() {
		var i = $(this).attr("id");
		if (i != "" && i != undefined) {
			$(".yfmb-qycon").eq(i).css("display", "block")
			index = i;
		} else {
			$(".yfmb-qycon").css("display", "block")
		}
	});
	// 地点选择框 X关闭
	$(".xzqy-yfmbtop-close").live("click", function() {
		var i = $(this).attr("id");
		if (i != "" && i != undefined) {
			$(".yfmb-qycon").eq(i).css("display", "none")
		} else {
			$(".yfmb-qycon").css("display", "none")
		}
	});
	// 地点选择框 确定/取消
	$(".text-inp").live("click", function() {
		var i = $(this).attr("name");
		if (i != "" && i != undefined) {
			$(".yfmb-qycon").eq(i).css("display", "none");
		} else {
			$(".yfmb-qycon").css("display", "none");
		}
	});
	// 关闭运费详细修改或添加
	$(".xgyfxxgz-close").live("click", function() {
		$(".xgyfxxgz").css("display", "none");
		$(".mrgzxxgz").css("display", "none");
	});
	// 取消运费详细修改或添加
	$(".cancelbtn").live("click", function() {
		$(".xgyfxxgz").css("display", "none");
		$(".mrgzxxgz").css("display", "none");
	});
	// 修改
	$(".yfmb-xiugai").live("click", function() {
		$("#editfreightshow").css("display", "block");
		var id = $(this).parent().parent().find("input").val();
		GetTemplateByID(id)
		$("#editfID").attr("value", "");
		$("#editfID").attr("value", id);
	});
	// 修改运费模板包邮选择
	$("#IsPostage").change(function() {
		if ($(this).val() == 1) {
			$("input[name=FirstCount]").attr("value", "1");
			$("input[name=FirstCount]").attr("disabled", "true")// 首件
			$("input[name=FirstPrice]").attr("value", "0");
			$("input[name=FirstPrice]").attr("disabled", "true")// 首费
			$("input[name=ElseCount]").attr("value", "1");
			$("input[name=ElseCount]").attr("disabled", "true")// 续件
			$("input[name=ElsePrice]").attr("value", "0");
			$("input[name=ElsePrice]").attr("disabled", "true")// 续费
		} else {
			$("input[name=FirstCount]").removeAttr("disabled")// 首件
			$("input[name=FirstPrice]").removeAttr("disabled")// 首费
			$("input[name=ElseCount]").removeAttr("disabled")// 续件
			$("input[name=ElsePrice]").removeAttr("disabled")// 续费
		}
	})
	// 删除
	$(".delTemplate").live("click", function() {
		var id = $(this).parent().parent().find("input").val();
		ConfirmShow("确认要删除运费模版吗？", del, id);
	})
	// 删除模板详细
	$(".deleteAttr").live("click", function() {
		var id = $(this).find("input").val();
		ConfirmShow("确认要删除运费模版详细吗？", delAttr, id);
		// delAttr(id);
	})
	// 设为默认
	$(".setDefault").live("click", function() {
		if ($(this).text() == "设为默认") {
			var id = $(this).parent().parent().find("input").val();
			setDef(id, sid);
		}
	})
	// 添加模版按钮
	$("#addTemplate").click(function() {
		if (uType == 3 || uType == 4) {
			$("#addfreight").css("display", "block");
		}
	})
	// 确认添加模版
	$("#Adtempleat").live("click", function() {
		if (uType == 3 || uType == 4) {
			AddFreight();
		}
	})
	// 添加模版详细
	$(".addFreightAtrr").live("click", function() {
		if (uType == 3 || uType == 4) {
			$("#addfreightInfo").css("display", "block");
			var id = $(this).parent().parent().find("input").val();
			if (id != "" && id != null) {
				$("#AttID").attr("value", id);
				GetFreightAtr(id);
			}
		}
	})
	// 确认模版详细
	$("#addFreightAtrr").live("click", function() {
		var id = $("#AttID").val();
		if (id != "" && id != null) {
			AddFreightAtrr(id);
		}
	})
	// 地点勾选修改
	$("input[name=Atareas]").live("change", function() {
		if (index == "" || index == undefined) {
			index = "";
		}
		if ($(this).attr("checked") == "checked") {// 选中
			var areas = $("#AtAreas" + index).val();
			var city = $(this).val();
			var have = "0";
			if (areas.indexOf(",") != -1) {
				var areasplit = areas.split(',');
				for ( var i in areasplit) {
					if (areasplit[i] == city) {
						have = "1";
					}
				}
			} else {
				if (areas == city) {
					have = "1";
				}
			}
			if (have != "1") {
				if (areas != "") {
					areas += "," + city;
					$("#AtAreas" + index).attr("value", areas);

				} else {
					areas += city;
					$("#AtAreas" + index).attr("value", areas);
				}
			}
		} else {
			var areas = $("#AtAreas" + index).val();
			var city = $(this).val();
			if (areas.indexOf(",") != -1) {
				var areasplit = areas.split(',');
				for ( var i in areasplit) {
					if (areasplit[i] == city) {// text 中是否含有复选框的值
						if (areasplit.length - 1 == i) {// 去除text 最后的复选框的值
							areas = areas.replace("," + city, "");
							$("#AtAreas" + index).attr("value", areas);
						} else {
							areas = areas.replace(city + ",", "");
							$("#AtAreas" + index).attr("value", areas);
						}
					}
				}
			} else {
				if (areas == city) {
					$("#AtAreas" + index).attr("value", "");
				}
			}

		}
	})
	// 地点勾选添加
	$("input[name=AreasAtrr]").live("change", function() {

		if ($(this).attr("checked") == "checked") {// 选中
			var areas = $("#AreasAtrr").val();
			var city = $(this).val();
			var have = "0";
			if (areas.indexOf(",") != -1) {
				var areasplit = areas.split(',');
				for ( var i in areasplit) {
					if (areasplit[i] == city) {
						have = "1";
					}
				}
			} else {
				if (areas == city) {
					have = "1";
				}
			}
			if (have != "1") {
				if (areas != "") {
					areas += "," + city;
					$("#AreasAtrr").attr("value", areas);

				} else {
					areas += city;
					$("#AreasAtrr").attr("value", areas);
				}
			}
		} else {
			var areas = $("#AreasAtrr").val();
			var city = $(this).val();
			if (areas.indexOf(",") != -1) {
				var areasplit = areas.split(',');
				for ( var i in areasplit) {
					if (areasplit[i] == city) {// text 中是否含有复选框的值
						if (areasplit.length - 1 == i) {// 去除text 最后的复选框的值
							areas = areas.replace("," + city, "");
							$("#AreasAtrr").attr("value", areas);
						} else {
							areas = areas.replace(city + ",", "");
							$("#AreasAtrr").attr("value", areas);
						}
					}
				}
			} else {
				if (areas == city) {
					$("#AreasAtrr").attr("value", "");
				}
			}

		}
	})
	// 地点勾选添加
	// -----------------------------------------------------
	$("input[name=AreasSelect]").live("change", function() {

		var s = $(this).parent().parent().find("input[name=AreasAtrr]");
		if (s == "" || s == undefined || s.length == 0) {
			var t = $(this).parent().parent().find("input[name=Atareas]");
			if ($(this).attr("checked") == "checked") {// 选中
				t.attr("checked", "checked");
			} else {
				t.removeAttr("checked");
			}
			$("input[name=Atareas]").trigger("change");
		} else {
			if ($(this).attr("checked") == "checked") {// 选中
				s.attr("checked", "checked");
			} else {
				s.removeAttr("checked");
			}
			$("input[name=AreasAtrr]").trigger("change");
		}
	});
	// -----------------------------------------------------

	// 编辑
	$("#editFreight").live("click", function() {
		var id = $("#editfID").val();
		var sid = $("#shopName").val();
		Edit(id, sid);
	})*/
	$("input[name=back]")
	
	.click(
			function() {
					location.href = "/seller/freight/showShopFreight";
			});
})

// 运费模版
var Freight = {
	
	bind : function(ftid) {
		$.ajax({
			type : "post",
			url : "/seller/freight/querySellerFreightAttrList",
			dataType : "json",
			data : {
				ftid: ftid,
				page : 1,
				size : 100
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('datalist', listdata);

					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#freightList").parent().children().each(function() {
						if ($(this).attr('id') != "freightList") {
							this.parentNode.removeChild(this);
						}
					})
					$("#freightList").after(html);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {
				Dalert(e.statusText);
			}
		});
	}
}
// 设为默认模板 did：模板id
/*function setDef(did, sid) {
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/setDefault",
		dataType : "json",
		data : {
			id : did,
			shopid : sid
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", refresh);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {
			// alert(es.statusText);
		}
	});
};*/
// 删除模板 did：模板id
/*function del(did) {
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/deleteFreightManagerById",
		dataType : "json",
		data : {
			id : did
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", refresh);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {
			// Dalert(es.statusText);
		}
	});
};*/
// 删除模板详细 did：模板详细id
/*function delAttr(did) {
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/deleteAttr ",
		dataType : "json",
		data : {
			id : did
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", refresh);
				// location.reload();
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {
			// alert(es.statusText);
		}
	});

};*/
// 编辑模板详细 获取模板 tid：模板id
/*function GetTemplateByID(tid) {
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/qeuryFreightById",
		dataType : "json",
		data : {
			id : tid
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var tp = rsl.data;
				$("#tName").attr("value", tp.name);
				$("#description").val(tp.description);
				$("#pModel").val(tp.pricingmode);
				$("input[name=ftype]:eq(" + tp.transportmode + ")").attr(
						"checked", 'checked');
				$("#IsPostage").val(tp.isexemptionpostage);
				
				if (tp.isexemptionpostage == "1") {
					$("#sz").val(tp.num);
					$(
							"input[name=IsCondition]:eq("
									+ tp.iscondition + ")").attr(
							"checked", 'checked');
					$(".num1").show();
				}else{
					$(".num1").css("display","none");

				}
				var listdata = {
					list : tp.freightAttrs
				}
				var html = template('ftAttr', listdata);
				
				// 翻页时删除表头以外的所有节点，避免after()方法重复加载
				$("#falist").parent().children().each(function() {
					if ($(this).attr("class") == "edittr") {
						this.parentNode.removeChild(this);
					}
				})
				$("#falist").after(html);

				for (var i = 0; i < listdata.list.length; i++) {
					var value = listdata.list[i].areas;
					var id = "#edittr" + i;
					$(id).find("input[name=Atareas]").each(function() {
						var a = $(this).val();
						if (value.indexOf(a) >= 0) {
							$(this).attr("checked", "checked")
						}
					})
				}

				for (var i = 0; i < listdata.list.length; i++) {
					var value = listdata.list[i].firstprice;
					if (tp.isexemptionpostage == "1") {
						$("input[name=FirstCount]").attr("value", "1");
						$("input[name=FirstCount]").css('display', 'none');
						$("input[name=FirstPrice]").attr("value", value);
						// $("input[name=FirstPrice]").attr("disabled",
						// "true")//首费
						$("input[name=ElseCount]").attr("value", "1");
						$("input[name=ElseCount]").css('display', 'none');

						$("input[name=ElsePrice]").attr("value", "0");
						// $("input[name=ElsePrice]").attr("disabled", "true")//
						// 续费
						$("input[name=ElsePrice]").css('display', 'none');
					}
				}

			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {
			// alert(es.statusText);
		}
	});
};*/
// 编辑模板 fid：模板id，sid：店铺id
/*function Edit(fid, sid) {
	var na = $("#tName").val();
	var ep = $("#IsPostage").val();
	var pm = $("#pModel").val();
	var tm = $("input[name=ftype]:checked").val();
	var sz = $("#sz").val();
	var ad = $("input[name=IsCondition]:checked").val();
	var description = $("#description").val();

	var jl = "";
	var areas = $("input[name=areas]");
	var firstCount = $("input[name=FirstCount]");
	var firstPrice = $("input[name=FirstPrice]");
	var elseCount = $("input[name=ElseCount]");
	var elsePrice = $("input[name=ElsePrice]");
	var ID = $("input[name=faID]");// 模板详细id
	// "'areas':'安徽';'firstCount':2;'firstPrice':20;'elseCount':3;'elsePrice':30;'id':1|
	for (var i = 0; i < areas.length; i++) {
		jl += "'Areas':'" + areas.eq(i).val() + "';'FirstCount':'"
				+ firstCount.eq(i).val() + "';'FirstPrice':'"
				+ firstPrice.eq(i).val() + "';'ElseCount':'"
				+ elseCount.eq(i).val() + "';'ElsePrice':'"
				+ elsePrice.eq(i).val() + "';'id':'" + ID.eq(i).val()
				+ "';'Status':'"
				+ $("input[name=fIsInNum" + i + "]:checked").val() + "'|";
	}
	jl = jl.substring(0, jl.length - 1);
	$.ajax({
		type : "post",
		url : "/platform/freightmanager/update ",
		dataType : "json",
		data : {
			id : fid,
			name : na,
			isExemptionPostage : ep,
			pricingModel : pm,
			transportModel : tm,
			setNum : sz,
			iscondition:ad,
			json_attrList : jl,
			description : description
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", closeForm);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {
			// Dalert(es.statusText);
		}
	});
};*/
/*// 添加模板
function AddFreight() {
	$("#Adtempleat").hide();
	var num = $("#Adsz").val();
	var na = $("#AdtName").val();
	var ep = $("input[name=AdIsPostage]:checked").val();
	var ad = $("input[name=IsCondition]:checked").val();
	var pm = $("#AdpModel").val();
	var tm = $("input[name=Adftype]:checked").val();
	var sid = $("#shopName").val();
	var description = $("#description").val();
	if (sid == "" || sid == undefined) {
		// alert("该用户无店铺");
	} else {
		$.ajax({
			type : "post",
			url : "/platform/freightmanager/add ",
			dataType : "json",
			data : {
				name : na,
				isExemptionPostage : ep,
				pricingModel : pm,
				transportModel : tm,
				setNum : num,
				iscondition:ad,
				shopid : sid,
				description : description
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					Dalert(rsl.desc, "", refresh);
					$(".xgyfxxgz").css("display", "none");
					// location.reload();
				} else {
					Dalert(rsl.desc);
					$("#Adtempleat").show();
				}

			},
			error : function(es) {
				// alert(es.statusText);
			}
		});
	}

};*/
/*// 添加模板详细
function AddFreightAtrr(id) {
	$("#addFreightAtrr").hide();
	var ar = $("#AreasAtrr").val();
	var fc = $("#AtFirstCount").val();
	var fp = $("#AtFirstPrice").val();
	var ec = $("#AtElseCount").val();
	var ep = $("#AtElsePrice").val();
	var isInNum = $("input[name=AtInNum]:checked").val();

	$.ajax({
		type : "post",
		url : "/platform/freightmanager/addAttr ",
		dataType : "json",
		data : {
			freightid : id,
			areas : ar,
			firstcount : fc,
			firstprice : fp,
			elsecount : ec,
			elseprice : ep,
			atInNum : isInNum
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", refresh);
				$("#AreasAtrr").attr("value", "");
				$("#AtFirstCount").attr("value", "");
				$("#AtFirstPrice").attr("value", "");
				$("#AtElseCount").attr("value", "");
				$("#AtElsePrice").attr("value", "");
				$(".xgyfxxgz").css("display", "none");
				// location.reload();
			} else {
				Dalert(rsl.desc);
				$("#addFreightAtrr").show();
			}
		},
		error : function(es) {
			// alert(es.statusText);
		}
	});
};*/
// 获取直营店铺
/*function GetOwnShop() {
	$.ajax({
		type : "post",
		url : "/platform/Freight_Platform/P_GetOwnShop ",
		dataType : "json",
		data : {},
		success : function(rsl) {
			if (rsl.Code == 0) {
				var shop = rsl.Data;
				$("#shopName").attr("value", shop.ID);
				if (shop.ID != "" && shop.ID != undefined) {
					// Freight.bind(shop.ID);
				}
			} else {
			}
		},
		error : function(es) {
			// Dalert(es.statusText);
		}
	});
};*/
/*// 获取店铺模板详细 tid：运费模板id
function GetFreightAtr(tid) {

	if (tid != "" && tid != undefined) {
		$.ajax({
			type : "post",
			url : "/platform/freightmanager/qeuryFreightById",
			dataType : "json",
			data : {
				id : tid
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var tp = rsl.data;
					$("#AttName").attr("value", tp.name);
					$("#AtpModel").val(tp.pricingmode);
					$("input[name=Atftype]:eq(" + tp.transportmode + ")").attr(
							"checked", 'checked');
					$(
							"input[name=AtIsPostage]:eq("
									+ tp.isexemptionpostage + ")").attr(
							"checked", 'checked');
					if (tp.isexemptionpostage == "1") {
						$("#Atsz").attr("value", tp.num);
						$("#AtFirstCount").attr("value", "1");
						$("#AtFirstCount").hide();
						$("#AtFirstPrice").attr("value", "0");
						// $("#AtFirstPrice").attr("disabled", "true")//首费
						$("#AtElseCount").hide();
						$("#AtElseCount").css('display', 'none');
						// $("#AtElseCount").attr("disabled", "true")// 续件
						$("#AtElsePrice").attr("value", "0");
						$("#AtElsePrice").hide();
						$(
								"input[name=AtInNum]:eq("
										+ tp.iscondition + ")").attr(
								"checked", 'checked');
						$(".num1").show();
						$(".dd").css("display","none");
						$(".aa").css("display","none");
						// $("#AtElsePrice").attr("disabled", "true")// 续费
					} else {
						$("#AtFirstCount").show();
						$("#AtFirstPrice").show();
						$("#AtElseCount").show();
						$("#AtElsePrice").show();
						$(".num1").css("display","none");
						$(".dd").show();
						$(".aa").show();

					}
				} else {
					// Dalert(rsl.Desc);
				}
			},
			error : function(es) {
				// Dalert(es.statusText);
			}
		});
	}
}*/
// 刷新
function refresh() {
	location.reload();
}
// 关闭编辑窗口
function closeForm() {
	$(".xgyfxxgz").css("display", "none");
	$(".mrgzxxgz").css("display", "none");
}