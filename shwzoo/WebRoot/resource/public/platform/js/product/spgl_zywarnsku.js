//直营商品-库存管理
var pcount;
var pindex;
var psize = 10;
var indexlog;
var SKU = {
    bind: function () {
        $(".chaxun").first().bind("click", search);
    },
    getSKUList: function (index) {        
        var shopid = $("#select_shop").attr("data");
        var gname = $("#select_gname").val();
        var wbnum = $("#warnbnum").val();
        var wenum = $("#warnenum").val();
        var sbnum = $("#stockbnum").val();
        var senum = $("#stockenum").val();
        $.ajax({
            url: "/platform/WarnSku/getZYWarnSkuList",
            type: "Post",
            data: {
                "shopid" : shopid,
                "page" : index,
                "size" : psize,
                "gname" : gname,
                "warnbnum" : wbnum,
                "warnenum" : wenum,
                "stockbnum" : sbnum,
                "stockenum" : senum  
                  },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('skulist', listdata);
                    $("#list_title").html(html);
                    
                    indexlog = index;
                    pcount = data.maxRow;
					pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                }
            },
            error: function () {

            }
        })
    }
}

//分页回调
function pagelist(index) {
	if (indexlog != index) {
		SKU.getSKUList(index);
	}
}


// $(function () {	  
    // autoxl.bind("select_shop", getShopStartwithName);
// })
var d;
function ConfigShow(skuid) {

    var title = "库存设置";
    var contentHTML = "";
    contentHTML += "<form id='SetForm'><div class='tjcpxx-con-mk1'><div class='tjcpxx-con-form-title1'><label id='td_SetName'>库存数量：</label></div>";
    contentHTML += "<div class='tjcpxx-con-form1'><input class='inp-seller' id='skustock' name='skustock' type='text' value=''></div></div>";

    contentHTML += "</div></form>";

    d = dialog({
        title : title,
        id : "showid", //防止重复弹出
        content : contentHTML,
        width : 400,
        height : 100,
        button : [{
            value : '保存',
            callback : function() {
                var stock = $("#skustock").val();
                if (check().form()) {
                    UpdateStock(skuid, stock);
                } else {
                    return false;
                };
            },
            autofocus : true
        }, {
            value : '取消',
            callback : function() {

            }
        }]
        //,modal: true //蒙层
    });
    d.show();
}

//表单验证
function check() {
    return $("#SetForm").validate({
        rules : {
            skustock : {
                required : true,
                digits : true,
                min : 0
            }
        },
        //设置提示信息
        messages : {
            skustock : {
                required : "请输入库存值",
                digits : "请输入整数",
                min : "请输入大于0整数"
            }
        },
        //设置错误信息存放标签
        errorElement : "label",
        //设置验证触发事件
        focusInvalid : true

    })
}

function UpdateStock(skuid, stock) {
    $.ajax({
        url : "/platform/WarnSku/updateStock",
        type : "Post",
        data : {
            "skuid" : skuid,
            "stock" : stock
        },
        dataType : "json",
        success : function(data) {
            if (data.code == 0) {
               SKU.getSKUList(1);
            } else {
               Dalert(data.desc); 
            }
        }
    });
}

// function getShopStartwithName(callback, event) {
// 	
	// var name = $("#select_shop").val();
// 	
	// if (event)
		// name += String.fromCharCode(event.keyCode);
	// $.ajax({
		// url : "/platform/shop/getShopStartWithName",
		// type : "Post",
		// data : {
			// "name" : name
		// },
		// dataType : "json",
		// success : function(data) {
			// if (data.code == 0) {
				// var listdata = {
					// list : data.data
				// }
				// var html = template('select_shoplist', listdata);
// 
				// if (callback) {
					// callback(html);
				// }
			// } else {
				// Dalert(data.desc);
			// }
		// }
	// });
// }



