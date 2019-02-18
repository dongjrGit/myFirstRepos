//添加品牌申请
$(document).ready(function() {
	var shopid = GetQueryStringByName("shopid");
	// 保存
	$("#savebtn").click(function() {
		if (check().form()) {
			Add(shopid);
		}
	})
	// 返回
	$("#backbtn").click(function() {
		location.href = "/seller/shop/showBrandInfo?shopid=" + shopid;
	})
	// 表单验证
	function check() {
		return $("#brandForm").validate({
			rules : {
				brandName : {
					required : true
				},
				brandDesc : {
					required : true
				},
				brandImg : {
					required : true
				},
				brandUrl : {
					required : true
				}
			},
			messages : {
				brandName : {
					required : "请输入品牌名"
				},
				brandDesc : {
					required : "请输入品牌描述"
				},
				brandImg : {
					required : "请输入品牌图片"
				},
				brandUrl : {
					required : "请输入官网"
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			}
		})
	}
});
// 添加品牌申请
function Add(shopid) {
	$("#savebtn").hide();

	var na = $("#brandName").val();
	var de = $("#brandDesc").val();
	var img = $("#img").val();
	var url = $("#brandUrl").val();
	$.ajax({
		type : "post",
		url : "/seller/shop/saveBrand",
		dataType : "json",
		// data: { name: na, shopID: id, desc: de, img: img, officialUrl: url },
		data : {
			shopid : shopid,
			name : na,
			description : de,
			img : img,
			officialurl : url
		},
		success : function(data) {
			if (data.code == 0) {
				Dalert(data.desc, "", backhref);
				location.href = "/seller/shop/showBrandInfo?shopid=" + shopid;
			} else {
				Dalert(data.desc, "", backhref);
				$("#savebtn").show();
			}// location.href = "BrandInfo";
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}
// 返回链接
function backhref() {
	location.href = "/seller/shop/showBrandInfo?shopid=" + shopid;
}