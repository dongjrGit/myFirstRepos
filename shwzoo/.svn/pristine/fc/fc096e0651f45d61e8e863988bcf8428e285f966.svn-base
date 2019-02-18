var ch = 3;
var receiveraddresss = {
	editaddress : function() {
		//添加收货地址
		//var orderid = GetQueryStringByName("orderid");
		var name = $("#name").val();
		var mobile = $("#mobile").val();
		var address = $("#address").val();
		var provinceCode = document.getElementById("province").value;
		var cityCode = document.getElementById("city").value;
		var areaCode = document.getElementById("area").value;
		var isDefault = 0;
		if ($("#moren").attr("checked"))
			isDefault = 1;
		$.ajax(({
			type : "post",
			url : "/api/wap/receiveraddress/addReceiverAddr",
			dataType : "json",
			data : {
				name : name,
				mobile : mobile,
				provinceCode : provinceCode,
				cityCode : cityCode,
				areaCode : areaCode,
				address : address,
				isDefault : isDefault,
				ch : ch
			},
			success : function(fh) {
				if (fh.code == 0) {
					alert(fh.desc);
					location.href = "/wap/receiveraddress/selectAddrByUserId?ch=3";
				} else {
					alert(fh.desc);
				}
			},
			error : function(e) {

			}
		}))

	},
	//删除收货地址
	deteleaddress : function(id) {
		$.ajax(({
			type : "post",
			url : "/api/wap/receiveraddress/delReceiverAddr",
			dataType : "json",
			data : {
				id : id,
				ch : ch
			},
			success : function(fh) {
				if (fh.code == 0) {
					alert("删除地址成功");
					location.href = "/wap/receiveraddress/selectAddrByUserId?ch=3";
				} else {
					alert(fh.desc);
				}
			},
			error : function(e) {

			}
		}))
	},
	//修改收货地址
	updateaddress : function() {

		var name = $("#name").val();
		var id = $("#province").attr("value");
		var mobile = $("#mobile").val();
		var address = $("#address").val();
		var selectprovinceIndex = document.getElementById("province").selectedIndex;//获得是第几个被选中了
		var province = document.getElementById("province").options[selectprovinceIndex].text;
		var selectcityIndex = document.getElementById("city").selectedIndex;//获得是第几个被选中了
		var city = document.getElementById("city").options[selectcityIndex].text;
		var selectareaIndex = document.getElementById("area").selectedIndex;//获得是第几个被选中了
		var area = document.getElementById("area").options[selectareaIndex].text;
		//var provinceCode = document.getElementById("province").value;
		//var cityCode = document.getElementById("city").value;
		//var areaCode = document.getElementById("area").value;
		var isDefault = 0;
		if ($("#moren").attr("checked"))
			isDefault = 1;
		$.ajax(({
			type : "post",
			url : "/api/wap/receiveraddress/updAddr",
			dataType : "json",
			data : {
				id : id,
				name : name,
				mobile : mobile,
				province : province,
				city : city,
				area : area,
				address : address,
				isDefault : isDefault,
				ch : ch
			},
			success : function(fh) {
				if (fh.code == 0) {
					alert(fh.desc);
					location.href = "/wap/receiveraddress/selectAddrByUserId?ch=3";
				} else {
					alert(fh.desc);
				}
			},
			error : function(e) {

			}
		}))

	},
	//查询所有的省
	GetProvData : function() {
		$
				.ajax(({
					type : "post",
					url : "/api/wap/getaddress/selectAllProvice",
					dataType : "json",
					data : {
						ch : ch
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							var optionstring = "";
							for ( var i in rsl.data) {
								var jsonObj = rsl.data[i];
								optionstring += "<option value=\""
										+ jsonObj.code + "\" >" + jsonObj.name
										+ "</option>";
							}
							$("#province").html(optionstring);
									//省改变
									$("#province")
											.change(
													function() {
														var p1 = document
																.getElementById("province").value;
														$
																.ajax(({
																	type : "post",
																	url : "/api/wap/getaddress/selectAllCity",
																	dataType : "json",
																	data : {
																		proviceCode : p1,
																		ch : ch
																	},
																	success : function(
																			rsl) {
																		if (rsl.code == 0) {
																			var optionstring = "";
																			for ( var i in rsl.data) {
																				var jsonObj = rsl.data[i];
																				optionstring += "<option value=\""
																						+ jsonObj.code
																						+ "\" >"
																						+ jsonObj.name
																						+ "</option>";
																			}
																			$(
																					"#city")
																					.html(
																							optionstring);
																		}
																	},
																	error : function(
																			e) {
																		Dalert(e.statusText);
																	}
																}));
													}),
									//市改变
									$("#city")
											.change(
													function() {
														var ci = document
																.getElementById("city").value;
														$
																.ajax(({
																	type : "post",
																	url : "/api/wap/getaddress/selectAllArea",
																	dataType : "json",
																	data : {
																		cityCode : ci,
																		ch : ch
																	},
																	success : function(
																			rsl) {
																		if (rsl.code == 0) {
																			var optionstring = "";
																			for ( var i in rsl.data) {
																				var jsonObj = rsl.data[i];
																				optionstring += "<option value=\""
																						+ jsonObj.code
																						+ "\" >"
																						+ jsonObj.name
																						+ "</option>";
																			}
																			$(
																					"#area")
																					.html(
																							optionstring);
																		}
																	},
																	error : function(
																			e) {
																		Dalert(e.statusText);
																	}
																}));
													})

						} else {
							Dalert(rsl.Desc);
						}
					},
					error : function(e) {
						//alert(e.statusText);
					}
				}));
	},
	//获取对应省的市
	GetCitySelect : function() {
		var p1 = document.getElementById("province").value;
		if (p1 != 110000) {
			$.ajax(({
				type : "post",
				url : "/api/wap/getaddress/selectAllCity",
				dataType : "json",
				data : {
					proviceCode : p1,
					ch : ch
				},
				async : false,
				success : function(rsl) {
					if (rsl.code == 0) {
						var optionstring = "";
						for ( var i in rsl.data) {
							var jsonObj = rsl.data[i];
							optionstring += "<option value=\"" + jsonObj.code
									+ "\" >" + jsonObj.name + "</option>";
						}
						$("#city").html(optionstring);
					}
				},
				error : function(e) {
					//alert(e.statusText);
				}
			}));
		} else {
		}
	}

}