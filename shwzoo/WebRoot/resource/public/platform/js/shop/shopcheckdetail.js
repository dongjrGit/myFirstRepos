//店铺ID
var shopID;
// 数据初始化
var Init = {
	bind : function() {
		// 获取店铺ID
		shopID = GetQueryStringByName("shopid");
		// 店铺审核数据绑定
		$.ajax(({
			type : "post",
			url : "/platform/shop/queryShopDetail",
			dataType : "json",
			async : false,
			data : {
				shopid : shopID
			},
			success : function(rsll) {
				if (rsll.code == 0) {
					var listdata = {
						list : rsll.data
					}
					if (rsll.data.certificate==null||rsll.data.certificate=="") {
						listdata.qita="";
					}else{
						listdata.qita=rsll.data.certificate.split(",");
					}
					if (rsll.data.examinereport==null||rsll.data.examinereport=="") {
						listdata.jcbg="";
					}else{
						listdata.jcbg=rsll.data.examinereport.split(",");
					}
					if (rsll.data.producelicence==null||rsll.data.producelicence=="") {
						rsll.data.producelicence="";
					}else{
						listdata.scxkz=rsll.data.producelicence.split(",");
					}
					listdata.spy=rsll.data.adoctrinecredential;
					var html = template('shopcheckdetail', listdata);
					$("#tb_shopcheckdetail").append(html);
					// 确认提交按钮
					$("#btn_submit").click(Submit);
					// 返回按钮
					$("#btn_goback").click(function() {
						var fatherUrl = GetQueryStringByName("fatherUrl");
						window.location.href = decodeURIComponent(fatherUrl);
					});

				} else {
					Dalert(rsll.Desc);
				}
			},
			error : function(es) {
				Dalert(es.statusText);
			}
		}));
	}
}

function Submit() {
	var shopStatus = $("input[name='rd']:checked").val();
	var lastShopStatus = $("#hidden_oldshopstatus").val();

	$.ajax(({
		type : "post",
		url : "/platform/shop/updateStatus",
		dataType : "json",
		data : {
			shopid : shopID,
			status : shopStatus,
			lastStatus : lastShopStatus
		},
		success : function(rsll) {
			if (rsll.code == 0) {
				$("#hidden_oldshopstatus").attr("value", shopStatus);
				Dalert("保存成功", "", backhref);
			} else {
				Dalert(rsll.desc);
			}
		},
		error : function(es) {

		}
	}));
}

function backhref() {
	var fatherUrl = GetQueryStringByName("fatherUrl");
	window.location.href = decodeURIComponent(fatherUrl);
}