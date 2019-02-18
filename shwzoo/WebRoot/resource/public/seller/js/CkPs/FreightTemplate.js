//运费模版
$(document).ready(
		function() {

			var uid = $("#userID").val();
			GetShopName(uid);
			// 店铺下拉框列表变化
			$("#shopName").change(function() {
				Freight.bind($("#shopName").val());
			})
			var index = "";
			$("body").on("click",".sel-yfmbdz", function() {
				var i = $(this).attr("id");
				if (i != "" && i != undefined) {
					$(".yfmb-qycon").eq(i).css("display", "block")
					index = i;
				} else {
					$(".yfmb-qycon").css("display", "block")
				}
			});
			// 地点选择框X按钮
			$("body").on("click",".xzqy-yfmbtop-close", function() {
				var i = $(this).attr("id");
				if (i != "" && i != undefined) {
					$(".yfmb-qycon").eq(i).css("display", "none")
				} else {
					$(".yfmb-qycon").css("display", "none")
				}
			});
			// 地点选择框 确定/取消
			$("body").on("click",".areBut", function() {
				var i = $(this).attr("name");
				if (i != "" && i != undefined) {
					$(".yfmb-qycon").eq(i).css("display", "none")
				} else {
					$(".yfmb-qycon").css("display", "none")
				}
			});
			// 关闭运费详细规则
			$("body").on("click",".xgyfxxgz-close", function() {
				$(".xgyfxxgz").css("display", "none");
				$(".mrgzxxgz").css("display", "none");
			});
			// 修改/添加运费详细返回
			$("body").on("click",".cancelbtn", function() {
				$(".xgyfxxgz").css("display", "none");
				$(".mrgzxxgz").css("display", "none");
			});

			// 修改运费模板
			$("body").on("click",".yfmb-xiugai", function() {
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
			$("body").on("click",".delete", function() {
				var id = $(this).parent().parent().find("input").val();
				ConfirmShow("确认要删除运费模版吗？", del, id)
				// del(id);
			})
			// 删除模板详细
			$("body").on("click",".deleteAttr", function() {
				var id = $(this).find("input").val();
				ConfirmShow("确认要删除运费模版详细吗？", delAttr, id)
				// delAttr(id);
			})
			// 设为默认
			$("body").on("click",".setDefault", function() {
				if ($(this).text() == "设为默认") {
					var id = $(this).parent().parent().find("input").val();
					setDef(id);
				}
			})
			// 添加模版按钮
			$("#addTemplate").click(function() {
				$("#addfreight").css("display", "block");
			})
			// 确认添加模版
			$("#Adtempleat").on("click",null, function() {
				var shopid = $("#shopName");
				if (shopid != "" && shopid != null)
					AddFreight();
			})
			// 添加模版详细
			$("body").on("click",".AddFreightAtrr", function() {
				$("#addfreightInfo").css("display", "block");
				var s = $("#shopName").val();
				if (s != "" && s != null && s != undefined) {
					var id = $(this).parent().parent().find("input").val();
					$("#AttID").attr("value", id);
					GetFreight(id)
				}
			})
			// 确认模版详细
			$("#addFreightAtrr").on("click",null, function() {
				var id = $("#AttID").val();
				if (id != "" && id != null) {
					AddFreightAtrr(id);
				}
			})
			// 修改 地点勾选
           $(document).on("change","input[name=Atareas]", function() {
				if (index == "" || index == undefined) {
					index = "";
				}
				if($(this).parent().parent().parent().parent().parent().parent().attr("id").substring(7)!=index){
					return;
				}
				if ($(this).prop("checked") == true) {// 选中
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
							if (areasplit[i] == city) {// text
								// 中是否含有复选框的值
								if (areasplit.length - 1 == i) {// 去除text
									// 最后的复选框的值
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
			// 添加 地点勾选
			 $(document).on("change","input[name=AreasAtrr]", function() {

				if ($(this).prop("checked") == true) {// 选中
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
							if (areasplit[i] == city) {// text
								// 中是否含有复选框的值
								if (areasplit.length - 1 == i) {// 去除text
									// 最后的复选框的值
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

			// -----------------------------------------------------
			 $(document).on("change","input[name=AreasSelect]",function() {
						var s = $(this).parent().parent().find(
								"input[name=AreasAtrr]");
						if (s == "" || s == undefined || s.length == 0) {
							var t = $(this).parent().parent().find(
									"input[name=Atareas]");
							if ($(this).prop("checked") == true) {// 选中
								t.prop({checked:true});
							} else {
								t.prop({checked:false});
							}
							$("input[name=Atareas]").trigger("change");
						} else {
							if ($(this).prop("checked") == true) {// 选中
								s.prop({checked:true});
							} else {
								s.prop({checked:false});
							}
							$("input[name=AreasAtrr]").trigger("change");
						}
					});
			// -----------------------------------------------------
			// 模版选择变化
			$("#AttName").on("change",null,function() {
						var tid = $("#AttName").val();
						if (tid != "" && tid != undefined) {
							$.ajax({
								type : "post",
								url : "/Freight/S_GetFreightByID ",
								dataType : "json",
								data : {
									id : tid
								},
								success : function(rsl) {
									if (rsl.code == 0) {
										var tp = rsl.data;

										$("#AtpModel").val(tp.pricingmode);
										$(
												"input[name=Atftype]:eq("
														+ tp.transportmode
														+ ")").attr("checked",
												'checked');
										$(
												"input[name=AtIsPostage]:eq("
														+ tp.isexemptionpostage
														+ ")").attr("checked",
												'checked');
									} else {
										Dalert(rsl.desc);
									}
								},
								error : function(es) {
									// Dalert(es.statusText);
								}
							});
						}
					})
			// 编辑
			$("#editFreight").on("click",null, function() {
				var id = $("#editfID").val();
				var sid = $("#shopName").val();
				Edit(id, sid);
			})
		})

// 运费模版
var Freight = {
	bind : function(sid) {
		$.ajax({
			type : "post",
			url : "/seller/freight/queryFreightListByCriteria",
			dataType : "json",
			data : {
				page : 1,
				size : 1000,
				shopid : sid
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
					// alert(rsl.Desc);
				}
			},
			error : function(e) {
				// Dalert(e.statusText);
			}
		});
	}
}
/*
 * 设为默认模板 @param did
 */
function setDef(did) {
	sid = $("#shopName").val();
	$.ajax({
		type : "post",
		url : "/seller/freight/setDefault ",
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
			// Dalert(es.statusText);
		}
	});
};
// 删除运费模板 did：运费模板id
function del(did) {

	$.ajax({
		type : "post",
		url : "/seller/freight/delete ",
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

};
// 删除运费模板详细 did：运费模板详细id
function delAttr(did) {

	$.ajax({
		type : "post",
		url : "/seller/freight/deleteAttr ",
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

};
// 获取单个运费模板 tid：运费模板id
function GetTemplateByID(tid) {
	$.ajax({
		type : "post",
		url : "/seller/freight/qeuryFreightById ",
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
				$("#ysfs input[name=ftype]:eq(" + tp.transportmode + ")").attr(
						"checked", 'checked');
				//$("#IsPostage").val(tp.isexemptionpostage);
				$("#falist input[name=IsPostage]:eq(" + tp.isexemptionpostage + ")").attr(
						"checked", 'checked');
				if (tp.isexemptionpostage == 1) {
					document.getElementById("pModel").options.remove(2); 
					document.getElementById("pModel").options.add(new Option('按金额',2));
					$("#pModel").val(tp.pricingmode);
					$("#sz").val(tp.num);
					$("input[name=FirstCount]").attr("value", "1");
					$("input[name=FirstCount]").css('display', 'none');
					// $("input[name=FirstCount]").attr("disabled", "true")//首件
					$("input[name=FirstPrice]").attr("value", "0");
					// $("input[name=FirstPrice]").attr("disabled", "true")//首费
					$("input[name=ElseCount]").attr("value", "1");
					$("input[name=ElseCount]").css('display', 'none');
					// $("input[name=ElseCount]").attr("disabled", "true")//续件
					$("input[name=ElsePrice]").attr("value", "0");
					$("input[name=ElsePrice]").css('display', 'none');
					// $("input[name=ElsePrice]").attr("disabled", "true")//续费
					$(".aa").css("display","none");
					$(".dd").css("display","none");
				//	alert(tp.iscondition);
				/*	$("input[name=IsCondition]:eq("
							+ tp.iscondition + ")").attr(
					"checked", 'checked');*/
					
					//$("#bygz input[name=IsCondition]").removeAttr("checked");
					
					$.each($("#bygz input[name=IsCondition]"),function(index,item){
						if(tp.iscondition==item.value){
							$(this).prop({checked:true});
						}
					});
					$(".num1").show();
					if(tp.pricingmode==0){
						$(".a").attr("style",null);
				    	$(".b").css("display","none");
				    	$(".c").css("display","none");	
					}else if(tp.pricingmode==1){
						$(".b").attr("style",null);
				    	$(".a").css("display","none");
				    	$(".c").css("display","none");
					}else if(tp.pricingmode==2){
						$(".c").attr("style",null);
				    	$(".b").css("display","none");
				    	$(".a").css("display","none");
					}
					
				}else{
					$(".num1").css("display","none");
					$(".dd").show();
					$(".aa").show();
					$(".cc").show();
					$(".a").css("display","none");
			    	$(".b").css("display","none");
			    	$(".c").css("display","none");
			    	document.getElementById("pModel").options.remove(2); 
				}
				var listdata = {
					list : tp.freightAttrs
				}
				var html = template('ftAttr', listdata);
				$("#tbodyarea").html(html);
				for (var i = 0; i < listdata.list.length; i++) {
					var value = listdata.list[i].areas;
					var firstprice = listdata.list[i].firstprice;
					var id = "#edittr" + i;
					if (tp.isexemptionpostage == "1") {
						$(id).find("input[name=FirstCount]").attr("value", "1");
						$(id).find("input[name=FirstCount]").css('display', 'none');
						$(id).find("input[name=FirstPrice]").attr("value", firstprice);
						// $("input[name=FirstPrice]").attr("disabled",
						// "true")//首费
						$(id).find("input[name=ElseCount]").attr("value", "1");
						$(id).find("input[name=ElseCount]").css('display', 'none');

						$(id).find("input[name=ElsePrice]").attr("value", "0");
						// $("input[name=ElsePrice]").attr("disabled", "true")//
						// 续费
						$(id).find("input[name=ElsePrice]").css('display', 'none');
						$(id).find(".aa").css("display","none");
						$(id).find(".dd").css("display","none");
//						$(id).find(".cc").css("display","none");
						$(id).find(".ff").hide();
						$(id).find(".kk").hide();
//						$(id).find(".gg").hide();
					}else{
					    $(id).find("input[name=FirstCount]").attr("value", listdata.list[i].firstcCount);
					    $(id).find("input[name=ElseCount]").attr('value', listdata.list[i].elsecount);
					    $(id).find("input[name=ElsePrice]").attr("value", listdata.list[i].elseprice);
					    $(id).find(".aa").show();
					    $(id).find(".gg").show();
					    $(id).find(".ff").hide();
					    $(id).find(".kk").hide();

			        }
					$(id).find("input[name=Atareas]").each(function() {
						var a = $(this).val();
						if (value.indexOf(a) >= 0) {
							$(this).prop({checked:true});
							//$(this).attr("checked", "checked")
						}
					})
				}
				// 地点选择框 确定/取消
				$("body").on("click",".areBut", function() {
					var i = $(this).attr("name");
					if (i != "" && i != undefined) {
						$(".yfmb-qycon").eq(i).css("display", "none")
					} else {
						$(".yfmb-qycon").css("display", "none")
					}
				});
			} else {
				 Dalert(rsl.desc);
			}
		},
		error : function(es) {
			// Dalert(es.statusText);
		}
	});
};
// 获取运费模板名称 sid：店铺id
function GetTemplateName(sid) {
	$.ajax({
		type : "post",
		url : "/seller/freight/qeuryFreightAttrById ",
		dataType : "json",
		data : {
			page : 1,
			size : 1000,
			shopid : sid
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var listdata = {
					list : rsl.data
				}
				var html = template('namelist', listdata);

				$("#AttName").html("<option value=''>请选择模版</option>" + html);
			} else {
				// Dalert(rsl.Desc);
			}
		},
		error : function(es) {
			// Dalert(es.statusText);
		}
	});
};
// 编辑运费模板 fid：运费模板，sid：店铺id
function Edit(fid, sid) {
	var na = $("#tName").val();
	var ep = $('input:radio[name="IsPostage"]:checked').val();
	var pm = $("#pModel").val();
	var tm = $('input:radio[name="ftype"]:checked').val();
	var sz = $("#sz").val();
	var ad =  $('input:radio[name="IsCondition"]:checked').val();
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
		url : "/seller/freight/update ",
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
			shopid : sid,
			description : description
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", closeForm);
				refresh();
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(es) {
			// Dalert(es.statusText);
		}
	});
};
// 添加运费模板
function AddFreight() {
	$("#Adtempleat").hide();
	var na = $("#AdtName").val();
	var ep = $("input[name=AdIsPostage]:checked").val();
	var ad = $("input[name=IsCondition]:checked").val();
	var pm = $("#AdpModel").val();
	var tm = $("input[name=Adftype]:checked").val();
	var sid = $("#shopName").val();
	var num = $("#Adsz").val();
	var description = $("#adddescription").val();

	$.ajax({
		type : "post",
		url : "/seller/freight/add ",
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
			// Dalert(es.statusText);
		}
	});
};
// 添加运费模板详细
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
		url : "/seller/freight/addAttr ",
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
		}
	// ,
	// error: function (es) {
	// alert(es.statusText);
	// }
	});
};
// 获取店铺名称 uid：用户id
function GetShopName(uid) {
	$.ajax({
		type : "post",
		url : "/seller/freight/queryListByUser ",
		dataType : "json",
		data : {
			userid : uid
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var listdata = {
					list : rsl.data
				}

				var html = template('sNamelist', listdata);

				$("#shopName").html(html);
				if (rsl.data.length != 0) {
					Freight.bind(rsl.data[0].id)
				} else {

				}
			} else {

			}
		},
		error : function(es) {
			// alert(es.statusText);
		}
	});
};
// 添加运费模板详细时获取运费模板 tid：模板id
function GetFreight(tid) {
	if (tid != "" && tid != undefined) {
		$.ajax({
			type : "post",
			url : "/seller/freight/qeuryFreightById ",
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
					$.each($("input[name=AtIsPostage]"),function(){
						if(this.value==tp.isexemptionpostage){
							$(this)[0].checked=true;
						}else{
							$(this)[0].checked=false;
						}
					});
					if (tp.isexemptionpostage == "1") {
						$("#Atsz").attr("value", tp.num);
						$("#AtFirstCount").attr("value", "1");
						$("#AtFirstCount").hide();
						// $("#AtFirstCount").attr("disabled", "true")//首件
						$("#AtFirstPrice").attr("value", "0");
						// $("#AtFirstPrice").attr("disabled", "true")//首费
						$("#AtElseCount").attr("value", "1");
						$("#AtElseCount").hide();
						// $("#AtElseCount").attr("disabled", "true")//续件
						$("#AtElsePrice").attr("value", "0");
						$("#AtElsePrice").hide();
						// $("#AtElsePrice").attr("disabled", "true")//续费
						//$("input[name=AtInNum]").removeAttr("checked");
						//$("input[name=AtInNum]:eq("+tp.iscondition+")").attr('checked','');
						$.each($("input[name=AtInNum]"),function(){
							if(this.value==tp.iscondition){
								$(this)[0].checked=true;
							}else{
								$(this)[0].checked=false;
							}
						});
						//$("input[name=AtInNum]:eq("+ tp.iscondition + ")").attr("checked", 'checked');
						$(".num1").show();
						$(".dd").css("display","none");
						$(".aa").css("display","none");
						$(".cc").css("display","none");
						$(".gg").hide();
						$(".ff").hide();
						$(".kk").hide();
						
						if(tp.pricingmode==0){
							$(".a").attr("style",null);
					    	$(".b").css("display","none");
					    	$(".c").css("display","none");	
						}else if(tp.pricingmode==1){
							$(".b").attr("style",null);
					    	$(".a").css("display","none");
					    	$(".c").css("display","none");
						}else if(tp.pricingmode==2){
							$(".c").attr("style",null);
					    	$(".b").css("display","none");
					    	$(".a").css("display","none");
						}
					} else {
						$("#AtFirstCount").show();
						$("#AtFirstPrice").show();
						$("#AtElseCount").show();
						$("#AtElsePrice").show();
						$(".num1").css("display","none");
						$(".dd").show();
						$(".aa").show();
						$(".cc").show();
						if(tp.pricingmode=="0"){   //按件数
							$(".aa").show();
							$(".gg").show();
							$(".ff").hide();
							$(".kk").hide();
						}else if(tp.pricingmode=="1"){  //按重量
							$(".aa").hide();
							$(".gg").hide();
							$(".ff").show();
							$(".kk").show();
						}
						$(".a").css("display","none");
				    	$(".b").css("display","none");
				    	$(".c").css("display","none");
					}
				} else {
					Dalert(rsl.desc);
				}
			}
		// ,
		// error: function (es) {
		// alert(es.statusText);
		// }
		});
	}

}
// 刷新
function refresh() {
	location.reload();
}
// 关闭编辑窗口
function closeForm() {
	$(".xgyfxxgz").css("display", "none");
	$(".mrgzxxgz").css("display", "none");
}
/**
 * 运费模板编辑 计价方式变更
 */
function change2(){
    var AdpModel=$("#pModel").val();
    if(AdpModel==0){
    	$(".a").attr("style",null);
    	$(".b").css("display","none");
    	$(".c").css("display","none");	
    }else  if(AdpModel==1){
    	$(".b").attr("style",null);
    	$(".a").css("display","none");
    	$(".c").css("display","none");
    }else if(AdpModel==2){
    	$(".c").attr("style",null);
    	$(".b").css("display","none");
    	$(".a").css("display","none");
    }
	
}