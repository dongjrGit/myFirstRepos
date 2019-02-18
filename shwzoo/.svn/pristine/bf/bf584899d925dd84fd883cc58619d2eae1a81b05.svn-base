//库存商品调价
$(document)
		.ready(
				function() {
					Ad.unit();
		})
					
var Ad = {
		unit : function() {
			Ad.getList();
		},
		getList : function() {
			$.ajax({
				url : "/seller/shopsku/getPriceType",
				type : "Post",
				data : {},
				dataType : "json",
				success : function(data) {
					
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						var listdata = {
							list : data.data
						}
						var html = template('flist', listdata);
						$("#priceType").append(html);
						selectchange();
					}
				},
				error : function() {
				}
			});
		}
	}
//ptype 0-售价 1-原价 2-APP售价
function selectchange() {
    var ptype =$("#priceType").val();
    if (ptype == "0") {
        $("#spsj").show();
//        $("#spyj").hide();
        $("#spappsj").hide();
        $("#spwapsj").hide();
        $("#spxisj").hide();
    }
//    else if (ptype == "1") {
//        $("#spsj").hide();
//        $("#spyj").show();
//        $("#spappsj").hide();
//        $("#spwapsj").hide();
//        $("#spxisj").hide();
//    }
    else if(ptype=="2") {
		  $("#spsj").hide();
//	      $("#spyj").hide();
	      $("#spappsj").show();
	      $("#spwapsj").hide();
	      $("#spxisj").hide();
    }else if(ptype=="3"){
	   	 $("#spsj").hide();
//	     $("#spyj").hide();
	     $("#spappsj").hide();
	     $("#spwapsj").show();
	     $("#spxisj").hide();
    }else if(ptype="4"){
		 $("#spsj").hide();
//	     $("#spyj").hide();
	     $("#spappsj").hide();
	     $("#spwapsj").hide();
	     $("#spxisj").show();
    }
}
//保存
function sptjsave() {
    var spid = $("#spid").val();
    var type = $("#priceType").val();
    var price=$("#price").val();
    if(type=="0"){
    	var oldprice=$("#pcprice").val();
    }else if(type=="2"){
    	var oldprice=$("#appprice").val();
    }else if(type=="3"){
    	var oldprice=$("#wapprice").val();
    }else if(type=="4"){
    	var oldprice=$("#wxprice").val();
    }
    if (price == undefined || price == "") {
        Dalert("价格调整不能为空");
        $("#price").focus();
    }
    else {
        $.ajax({
            url: "/seller/shopsku/updatePrice",
            type: "Post",
            data: { "id": spid, "price": price, "pricetype": type, "oldprice": oldprice },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    Dalert("调整价格成功","",refresh);
                    //parent.window.closeDialog();
                    //parent.location.reload();
                    }
            },
            error: function () {

            }
        })
    }
}

$(function () {
    selectchange();
    $("input[name=sptjsave]").bind("click", sptjsave);
})
function isNull(data) {
    if(data==null || data==undefined || data=="")
    {
        return true;
    }
    return false;
}

var d = [];
function sptj(skuname, skuid) {
    d = dialog({
        title: skuname + '的调价',
        url: '/seller/productshop/spgl_skutj?skuid=' + skuid,
        width: 400,
        height: 240,
        //fixed: true
        //modal: true, //蒙层
    });
    d.show();
}
function closeDialog() { d.close(); }
function refresh() {
    parent.location.reload();
}