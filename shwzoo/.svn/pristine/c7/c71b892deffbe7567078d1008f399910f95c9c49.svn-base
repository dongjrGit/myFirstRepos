//对账单列表
var pcount, pindex, psize = 10;
var list = {
    //对账单列表
		 getlist: function (index) {
		        var datahtml = "";
		        var shopid = $("#shopid").val();
		        var orderdate = $("#orderdate").val();
		        $.ajax({
		            url: "/seller/shoporder/getBbillsorderList",
		            type: "Post",
		            data: {
		                "page": index,
		                "size": psize,
		                "shopid": shopid,
		                "orderdate":orderdate
		            },
		            dataType: "json",
		            success: function (data) {
		                if (data.code < 0) {
		                    Dalert(data.desc,10000);
		                } else {
		                    var listdata = {

		                        list: data.data
		                    }

		                    var html = template('ddlist', listdata);

		                    //html 填充
		                    $("#datalist").html(html);
		                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
		                    init();
		                    pcount = data.maxRow;
		                    pindex = data.pageIndex;
		                    //分页
		                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
		                }
		            },
		            error: function () {
		            }
		        });
		    },
}
//分页回调函数
function pagelist(index) {
    list.getlist(index);
}

//查询
function search() {
    list.getlist(1);
}

function formCancel(){
	var status = $("#status").val();
	if(status==1){
		location.href = "/seller/shopdd/billlist?status=1";
	}else{
		location.href = "/seller/shopdd/billlist?status=0";
	}
	
}
