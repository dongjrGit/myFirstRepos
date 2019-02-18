//商品销量统计
var pcount, pindex, psize = size_reports;
var sid, stype;
var glist = {
    //获取商品销售日数据
    bind: function (index) {
    	var stype = $("#select_type").val();
        var timef, timet,isSearch=true;
        switch (stype) {
            case "1": timef = $("#time").val(); timet = $("#time1").val(); if ((timef == undefined || timef == "") && (timet == undefined || timet == "")) { isSearch = false; Dalert("请选择日期"); } break;
            case "2":; break;
            case "3": timef = $("#time2").val(); if (timef == undefined || timef == "") { isSearch = false; Dalert("请选择月份"); } break;
            case "4": timef = $("#time3").val(); if (timef == undefined || timef == "") { isSearch = false; Dalert("请选择季度"); } break;
            case "5": timef = $("#time4").val(); if (timef == undefined || timef == "") { isSearch = false; Dalert("请选择年度"); } break;
            default: break;
        }
        if (isSearch) {
            if (stype == 4) { timef = timef.substring(0, 6); }
            $.ajax({
                url: "/seller/sales/getsalelist",
                type: "Post",
                data: { "page": index, "size": psize, "type": stype,
                	"datef": timef, "datet": timet,"proname":$("#proname").val(), 
                	"pronum" :$("#pronum").val(), "countfrom":$("#countf").val(), 
                	"countto":$("#countt").val(),"site":$("#select_site").val()},
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        var listdata = {

                            list: data.data
                        }

                        var html = template('gdlist', listdata);
                        //html 填充
                        $("#datalist").html(html);
                        pcount = data.maxRow;
                        pindex = data.pageIndex;
                        //分页
                        $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                    }
                },
                error: function () {

                }
            });
        }
    },
        typeChange: function () {
            var stype = $("#select_type").val();
            switch (stype) {
                case "1": 
                	$("#divDay").show(); 
                	$("#divMonth").hide(); 
                	$("#divQuarter").hide(); 
                	$("#divYear").hide(); break;
                case "2": 
                	$("#divDay").hide(); 
                	$("#divMonth").hide(); 
                	$("#divQuarter").hide(); 
                	$("#divYear").hide(); 
                	glist.bind(1); $("#thday").hide(); break;
                case "3": $("#divDay").hide(); $("#divMonth").show(); $("#divQuarter").hide(); $("#divYear").hide(); break;
                case "4": $("#divDay").hide(); $("#divMonth").hide(); $("#divQuarter").show(); $("#divYear").hide(); break;
                case "5": $("#divDay").hide(); $("#divMonth").hide(); $("#divQuarter").hide(); $("#divYear").show(); break;
                default: break;
            }
        } 
}

function pagelist(index) {
    glist.bind(index);
}
$(function () {
    $("#search").bind("click",function(){
    	glist.bind(1);
    	if($("#select_type").val()=="1"){
    		$("#thday").show(); 
    	}else{
    		$("#thday").hide(); 
    	}
    	});
})
