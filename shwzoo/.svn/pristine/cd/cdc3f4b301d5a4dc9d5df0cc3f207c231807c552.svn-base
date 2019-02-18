//分类链接规格类型

$(document).ready(function () {
    //全选
	$("#ch_All").click(function() {
		if ($(this).prop("checked")) {
			$("input[name=ck_list]").prop("checked", true);
		} else {
			$("input[name=ck_list]").prop("checked", false);
		}
	});
    //全选删除
    $("#delAll").click(function () {
        var idList = "";
        $('input[name="ck_list"]:checked').each(function () {
            var id = $(this).val();
            idList += id + ",";
        });
        if (idList != "") {
            var d = dialog({
                title: '提示',
                content: '确认要删除库存售价吗？',
                okValue: '确定',
                cancelValue: '取消',
                ok: function () {
                    idList = idList.substring(0, idList.length - 1);
                    $.ajax({
                        type: "post",
                        url: "/seller/shopproduct/delTimeStockList",
                        dataType: "json",
                        data: {
                            'ids': idList
                        },
                        success: function (rsl) {
                            if (rsl.code == 0) {
                                Dalert(rsl.desc,"",refresh); //window.location.reload();
                            }
                            else {
                                Dalert(rsl.desc,10000);
                            }
                        },
                        error: function (e) {
                            Dalert(e.statusText);
                        }
                    })
                },
                cancel: function () { }
            }).show();

        } else {
            var d = dialog({
                title: '提示',
                content: '还没有选取哦',
                okValue: '确定',
                ok: function () { }
            }).show();
        }
    });
});
var pcount, pindex, psize = 30;
var claid = "";//商品分类id
//页面加载数据
var type = {
    bind: function (index) {
    	var showy=$("#showy").val();
    	var showm=$("#showm").val();
    	var showd=$("#showd").val();
        $.ajax({
            url: "/seller/shopproduct/getSpuStocklist",
            type: "Post",
            data: { 
            	'showy':showy,
            	'showm':showm,
            	'showd':showd,
            	'spuid':$("#spuid").val(),
            	'index' : index,
				'size' : psize
				},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc,10000);
                } else {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('typelist', listdata);


                    //html 填充
                    $("#datalist").html(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                
                    pcount = data.maxRow;
                    pindex = data.pageIndex;
                    type.unit();
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "type.bind"));
                    $("input[name=ck_list]").attr("checked", false);
                }
            },
            error: function () {
                //Dalert("数据加载失败");
            }
        });
    },	
    unit : function() {
		$(".del").each(function() {
			$(this).bind("click", type.del);
		});
	    
        $(".edit").each(function () {
            $(this).bind("click", type.stock_shownew);
        });
        
        $("#sumbit_save").bind("click", Save);
	        
	},
	
	stock_shownew: function(){
		    var id = $(this).attr("data_id");
	    	var dateStr = $(this).attr("data_datestr");
	        var stock = $(this).attr("data_stock");
	        var price = $(this).attr("data_price");
	        $("#addnew_id").val(id);
	        $("#addnew_datestr").val(dateStr);
	        $("#addnew_stock").val(stock);
	        $("#addnew_price").val(price);
	        
	        $("#addnew_tr").show();
	 },
	    
	del : function() {
		var id = $(this).attr("data-id");
		if (confirm('确定将此记录删除?')) {
			$.ajax({
				url : "/seller/shopproduct/delSpuTimeStock",
				type : "Post",
				data : {
					'id' : id
				},
				dataType : "json",
				success : function(data) {
					if (data.code < 0) {
						Dalert(data.desc,10000);
					} else {
						 if(pindex>1){
  	              		   //判断如果不是第一页，当前页数据删除完就跳转到前一页
							 pindex=reloadpage("datalist",pindex);
  	              	    }
						type.bind(pindex);
					}
				},
				error : function() {
					Dalert("删除失败");
				}
			});
		}
	}
}

function Save() {
	var id = $("#addnew_id").val();
    var stock = $("#addnew_stock").val();
    var price = $("#addnew_price").val();
    $.ajax({
        url: "/seller/shopproduct/editSpuTimeStock",
        type: "Post",
        data: {"stock":stock,
        	"price":price,
        	"id":id},
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                Dalert("保存成功！","",backhref);
                //window.location.href = '/Platform/sp/class_specstype?id=' + $("#ClassID").val();
            } else {
                Dalert(data.desc,10000);
            }
        },
        error: function () {
        }
    });
}
//返回刷新
function backhref() {
	var spuid=$("#spuid").val();
    window.location.href = "/seller/productshop/showSpuStock?spuid="+spuid;
}
//刷新
function refresh() {
    window.location.reload();
}


//返回绑定
var d = []; var sUrl;
var reUrl = {
  load: function (sType) {
      sUrl = sType;
  }
}
var timestock = {

  add: function () {
      //添加
	  var spuid=$("#spuid").val();
  	 d = dialog({
           title: "添加库存售价",
           url: "addSpuStock?spuid="+spuid ,
           width: 900,
           height: 1500,
       });
      d.show();
  },
  addMonth: function () {
      //添加
	  var spuid=$("#spuid").val();
  	 d = dialog({
           title: "添加库存售价",
           url: "addSpuStockMonth?spuid="+spuid ,
           width: 900,
           height: 500,
       });
      d.show();
  }

}
//关闭弹出框
function closeDialog() { d.close(); }
function refresh() {
  location.reload();
}
