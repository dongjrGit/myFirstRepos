var ch=3;
var changcity = {
		//查询所有的省
		GetProvData : function() {
			$.ajax(({
						type : "post",
						url : "/api/wap/getaddress/selectAllProvice",
						dataType : "json",
						data : {ch : ch},
						success : function(rsl) {
							if (rsl.code == 0) {
								var optionstring = "<option>请选择</option>";
								for ( var i in rsl.data) {
									var jsonObj = rsl.data[i];
									optionstring += "<option value=\""+ jsonObj.code + "\" >" + jsonObj.name+ "</option>";
								}
								$("#province").html(optionstring);
										//省改变
										$("#province").change(function() {
											var p1 = document.getElementById("province").value;
												$.ajax(({
												     type : "post",
													 url : "/api/wap/getaddress/selectAllCity",
													 dataType : "json",
													 data : {proviceCode : p1,ch : ch},
													 success : function(rsl) {
														if (rsl.code == 0) {
															var oSel = document.getElementById('area');
														    var oOp = oSel.children;        //获取select列表的所有子元素。
														    for(var i=0,len = oOp.length;i<len;i++)   
														    {
														        oSel.removeChild(oOp[0]); 
														        }
															var optionstring = "<option>请选择</option>";
															$("#area").html(optionstring);
															//$("#area").appendChild(optionstring);}
															//$("#area").find("option").remove();
															for ( var i in rsl.data) {
																var jsonObj = rsl.data[i];
																optionstring += "<option value=\""+ jsonObj.code+ "\" >"+ jsonObj.name+ "</option>";
																			}
																$("#city").html(optionstring);
														}
															},
													error : function(e) {
																		Dalert(e.statusText);
																		}
																	}));
														}),
										//市改变
										$("#city").change(function() {
															var ci = document.getElementById("city").value;
															$.ajax(({
																type : "post",
																url : "/api/wap/getaddress/selectAllArea",
																dataType : "json",
																data : {cityCode : ci,ch : ch},
																success : function(rsl) {
																	if (rsl.code == 0) {
																	var optionstring = "<option>请选择</option>";
																	for ( var i in rsl.data) {
																		var jsonObj = rsl.data[i];
																		optionstring += "<option value=\""+ jsonObj.code+ "\" >"+ jsonObj.name+ "</option>";
																			}
																	$("#area").html(optionstring);
																	}
																		},
																error : function(e) {
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
							var optionstring = "<option>请选择</option>";
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