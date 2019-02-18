$(document).ready(function() {
	$("#searchtitle").bind("click", function() {
		Groupde.bind(1);
	});
	Groupde.bind(1);
});
var at;
var Groupde = {
		bind : function(index, aftertype) {
			 autoxl.bind("select_shop", Groupde.getShopStartwithName, true);
			 at = aftertype;
			 var stype = $("#select_type").val();
		        var timef, timet, isSearch = true;
		        switch (stype) {
		            case "1": timef = $("#time").val(); timet = $("#time1").val(); if ((timef == undefined ) && (timet == undefined)) { isSearch = false; alert("请选择日期"); } break;
		            case "2":; break;
		            case "3": timef = $("#time2").val(); if (timef == undefined || timef == "") { isSearch = false; alert("请选择月份"); } break;
		            case "4": timef = $("#time3").val(); if (timef == undefined || timef == "") { isSearch = false; alert("请选择季度"); } break;
		            case "5": timef = $("#time4").val(); if (timef == undefined || timef == "") { isSearch = false; alert("请选择年度"); } break;
		            default: break;
		        }
			//var title = $("#stitle").val();
			 		var sid = $("#select_shop").attr("data");
					/*var timef = $("#time").val(); 
					var timet = $("#time1").val();*/
			 		if (isSearch) {
			            if (stype == 4) { timef = timef.substring(0, 6); }
					$.ajax({
						url : "/platform/groupcount/queryGroupOrderDetail",
						type : "Get",
						dataType : "json",
						data : {
							page : index,
							size : 10,
							StartFrom:timef,
							StartTo:timet,
							shopid:sid,
							stype:stype
						},
						success : function(data) {
							if (data.code < 0) {
								$("#divshow").attr("style", "display:none");
								$("#pager").attr("style", "display:none");
							} else {
								$("#divshow").attr("style", "display:block;")
								$("#pager").attr("style", "display:block");
								var listdata = {
									list : data.data
								}
								var html = template('Groupdelist', listdata);
								$("#dd_title").siblings().remove();
								$("#dd_title").after(html);
								// 分页
		//						title = title;
								pcount = data.maxRow;
								pindex = data.pageIndex;
								$("#pager")
										.html(pagination(pcount, pindex, 10, "pagelist"));
							}
						},
						error : function() {
							
						}
					});
			 		}     
		},

getShopStartwithName: function (callback, event) {
    var name = $("#select_shop").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/shop/getShopStartWithName",
        type: "Post",
        data: { "name": name },
        dataType: "json",
        success: function (data) {

            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('select_shoplist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.desc);
            }
        }
    });
}
		
		
	}
	function pagelist(index) {
		Groupde.bind(index, at);
	}

function refresh(aftertype) {
	// location.reload();
	Groupde.bind(1,aftertype);
}

function typeChange() {
    var stype = $("#select_type").val();
    switch (stype) {
        case "1": $("#divDay").show(); $("#thday").show(); $("#divMonth").hide(); $("#divQuarter").hide(); $("#divYear").hide(); break;
        case "2": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").hide(); $("#divQuarter").hide(); $("#divYear").hide(); Groupde.bindsh(1, 2); break;
        case "3": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").show(); $("#divQuarter").hide(); $("#divYear").hide(); break;
        case "4": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").hide(); $("#divQuarter").show(); $("#divYear").hide(); break;
        case "5": $("#divDay").hide(); $("#thday").hide(); $("#divMonth").hide(); $("#divQuarter").hide(); $("#divYear").show(); break;
        default: break;
    }
}