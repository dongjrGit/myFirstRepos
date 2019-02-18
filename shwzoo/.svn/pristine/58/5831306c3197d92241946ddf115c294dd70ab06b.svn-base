//公司信息
$(document).ready(
		function() {
			var s = $("#shopID").val();
			$(".dpxx-tab").find("input[type=text]").each(function() {
				$(this).addClass("border")
			});
			// 编辑公司信息
			$("#editone").click(function() {// 在无信息时此按钮无效
				$(this).attr("style", "display:none");
				$("#saveone").attr("style", "display:block");
				$("#editonesave").find("input[type=text]").each(function() {
					$(this).removeAttr("readonly")
					$(this).removeClass("border")
				});
			})

			// 保存公司信息
			$("#saveone").click(function() {
				$(this).attr("style", "display:none");
				$("#editonesave").find("input[type=text]").each(function() {
					$(this).attr("readonly", "readonly")
					$(this).addClass("border")
				});
				var id = $("#EditID").val();
				edit(id);
				$("#editone").attr("style", "display:block");
			})

			// 编辑联系人
			$("#edittwo").click(function() {
				$(this).attr("style", "display:none");
				$("#savetwo").attr("style", "display:block");
				$("#lxrbj").find("input[type=text]").each(function() {
					$(this).removeAttr("readonly")
					$(this).removeClass("border")
				});
			})
			// 联系人保存
			$("#savetwo").click(function() {
				$(this).attr("style", "display:none");
				$("#lxrbj").find("input[type=text]").each(function() {
					$(this).attr("readonly", "readonly")
					$(this).addClass("border")
				});
				var id = $("#EditID").val();
				editPri(id);
				$("#edittwo").attr("style", "display:block");

			})
			GetForm(s);
			// 编辑公司信息 id：公司信息表id
			function edit(id) {
				var gsxxdz = $("#gsxxdz").val();
				var gddh = $("#gddh").val();
				var dzyx = $("#dzyx").val();
				var cz = $("#cz").val();
				var yzbm = $("#yzbm").val();
				var BankName = $("#BankName").val();
				var BankCardNo = $("#BankCardNo").val();
				var HoderName = $("#HoderName").val();
				$.ajax(({
					type : "post",
					url : "/seller/shop/saveShopAuthentication",
					dataType : "json",
					data : {
						id : id,
						companyaddress : gsxxdz,
						companymobile : gddh,
						companyemail : dzyx,
						companyfox : cz,
						postcode : yzbm,
						HoderName:HoderName,
						BankCardNo:BankCardNo,
						BankName:BankName
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							Dalert(rsl.desc);
						} else {
							Dalert(rsl.desc);
						}
					},
					error : function(e) {
						// Dalert(e.statusText);
					}
				}));
			}
			// 编辑联系人 id：公司信息表id
			function editPri(id) {
				var pn = $("#gsfzrxm").val();
				var pm = $("#gsfzrdh").val();
				var pe = $("#gsfzrdzyx").val();
				var pp = $("#gsfzrzw").val();

				$.ajax({
					type : "post",
					url : "/seller/shop/saveContact",
					dataType : "json",
					data : {
						id : id,
						pName : pn,
						pMobile : pm,
						pEmail : pe,
						pPost : pp
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							Dalert(rsl.desc);
						} else {
							Dalert(rsl.desc);
						}
					},
					error : function(e) {
						// Dalert(e.statusText);
					}
				});
			}
			// 获取公司信息 sid：店铺id
			function GetForm(sid) {

				$.ajax(({
					type : "post",
					url : "/seller/shop/queryInfoByShopId",
					dataType : "json",
					data : {
						shopid : sid
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							var com = rsl.data;
							$("#gsmc").html(com.companyname);
							$("#gsgwdz").html(com.companyweb);
							if (com.companytype == 0) {
								$("#gsxz").html("民营");
							} else {
								if (com.companytype == 1) {
									$("#gsxz").html("国企");
								} else {
									if (com.companytype == 2) {
										$("#gsxz").html("外企");
									} else {
										$("#gsxz").html("其他");
									}

								}
							}
							var pro = "";
							var ci = "";
							var ar = "";
							pro = GetRegionData("3", com.companyprovince);
							ci = GetRegionData("2", com.companycity);
							ar = GetRegionData("1", com.companyarea);
							$("#gsszd").html(pro + " " + ci + " " + ar);
							$("#gsry").html(com.CompanyHonor);
							$("#jypl").html(com.OperateType);
							$("#yyzzhm").html(com.license);

							$("#gsfzrxm").attr("value", com.principalname);
							$("#gsfzrdh").attr("value", com.principalmobile);
							$("#gsfzrdzyx").attr("value", com.principalemail);
							$("#gsfzrzw").attr("value", com.principalpost);

							$("#gsxxdz").attr("value", com.companyadress);
							$("#gddh").attr("value", com.companytel);
							$("#kfdh").attr("value", com.supporttel);
							$("#dzyx").attr("value", com.companyemail);
							$("#cz").attr("value", com.companyfox);
							$("#yzbm").attr("value", com.postcode);

							$("#zzjgdm").html(
									"<img  height='250' width='200' src='"
											+ $("#imgsrc").val()
											+ com.organization
											+ "' title='组织机构代码'/>");
							$("#yyzzxx").html(
									"<img  height='250' width='200' src='"
											+ $("#imgsrc").val() + com.license
											+ "' title='营业执照信息'/>");
							$("#swdjxx").html(
									"<img  height='250' width='200' src='"
											+ $("#imgsrc").val() + com.tax
											+ "' title='税务登记信息'/>");
                           /*$.each(com.certificate.split(","),function(index,vals){
                        	   var htmls="<img  height='250' width='200' src='"+ vals + "' title='其它资质'/>";
                        	   $("#gszz").append(htmls);
                           });*/
                           /*$.each(com.producelicence.split(","),function(index,vals){
                        	   var htmls="<img  height='250' width='200' src='"+ vals + "' title='生产许可证或流通许可证'/>";
                        	   $("#scxkz").append(htmls);
                           });*/
                           /*$.each(com.examinereport.split(","),function(index,vals){
                        	   var htmls="<img  height='250' width='200' src='"+ vals + "' title='近期产品检测报告'/>";
                        	   $("#jcbg").append(htmls);
                           });*/
                           /*$("#spy").html(
									"<img  height='250' width='200' src='"
											+ $("#imgsrc").val() + com.adoctrinecredential
											+ "' title='三品一标证书'/>");*/
							
                            
                            /*$("#khyhxkz").html(
									"<img  height='250' width='200' src='"
											+ $("#imgsrc").val() + com.bank
											+ "' title='开户银行许可证'/>");*/
							$("#EditID").attr("value", com.id);
						} else {
							$("#editone").attr("disabled", "disabled");// 在无信息时设置不能进行修改
							$("#edittwo").attr("disabled", "disabled");
						}
					},
					error : function(e) {
						// Dalert(e.statusText);
					}
				}));
			}
			function GetRegionData(rType, pCode) {
				var re = "";
				$.ajax({
					type : "post",
					url : "/seller/shop/queryArearByCode",
					dataType : "json",
					async : false,
					data : {
						type : rType,
						code : pCode
					},
					success : function(rsl) {
						if (rsl.code == 0) {
							var list = rsl.data;
							re = list.name;
						} else {

						}
					},
					error : function(e) {
						// Dalert(e.statusText);
					}
				});
				return re;
			}

		})
