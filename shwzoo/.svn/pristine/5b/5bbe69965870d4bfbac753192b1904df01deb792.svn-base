
//评论订单列表 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var pstatus;
var pseachstr;
var ptimebucket;
var Order = {
		deleteorder : function(id) {
			$.ajax(({
				type : "post",
				url : "/pc/order/delorder",
				dataType : "json",
				data : {id : id
				},
				success : function(rsl) {
					if (rsl.code == 0) {
//						alert("删除订单成功");
						Order.getorder(pindex);
					}

					else {
//						alert(rsl.desc);
					}
				},
				error : function(e) {

				}
			}));
		},
		//删除订单
		deletestatuesorder : function(id, statue) {
			$.ajax(({
				type : "post",
				url : "/pc/order/delOrder",
				dataType : "json",
				data : {
					id : id
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						alert("删除订单成功");
						location.href = "/member/order/getorder.html?page=1&&size=10";
					}

					else {
						alert(rsl.desc);
					}
				},
				error : function(e) {

				}
			}));
		},
		//确认收货
		confirmationgood : function(orderid) {
			$.ajax({
				type : "post",
				url : "/pc/order/comfirmreceive",
				dataType : "json",
		           cache:false,
		           async:false,
				data : {
					"orderid" : orderid,"ch":1
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						 Order.getorder(pindex);
					}

					else {
//						alert(rsl.desc);
					}
				}
			});
		},
		shouhuo:function(orderid){
			if(confirm("请务必已经收到货了再点击确认，否则可能会财货两空！")){
		    	Order_ConfirmGoods(orderid);
			}
		},
		//取消订单
		cancelorder : function(status, orderid, reason) {
			$.ajax({
				type : "post",
				url : "/pc/order/cancel",
				dataType : "json",
				data : {
					status : status,
					reason : reason,
					orderid : orderid
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						 Order.getorder(pindex);
					}

					else {
//						alert(rsl.desc);
					}
				},
				error : function(e) {

				}
			});
		},
		getorder:function(index){			
			 var timeBut = $("#select_timebucket").val();
			 if(pstatus!="0" && (pstatus=="" || pstatus==undefined)){
				 pstatus=$("#main").attr("value");
			 }
			$.ajax({
				type : "post",
				url : "/pc/order/getorderlist",
				dataType : "json",
				data : {
					"status" : pstatus,
					"type" : timeBut,
					"ch" : 1,
					"page":index,
					"size":psize
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						   var listdata = {
		                            list: rsl.data[0]
		                        }
						   var countdata = {
		                            count: rsl.data[1]
		                        }
	                    var html = template('orderslist', listdata);
						 
	                    //翻页时删除表头以外的所有节点，避免after()方法重复加载
	                    $("#div_listtitle").parent().children().each(function () {
	                        if ($(this).attr('id') != "div_listtitle") {
	                            this.parentNode.removeChild(this);
	                        }
	                    });
	                      //html 填充
	                    $("#div_listtitle").after(html);
	                      //订单详情竖线对齐
	                    $(".l_ddmkcon").each(function () {
	                		var rheight = $(this).height();
	                		$(this).find(".l_ddmk01con").css("height", rheight + "px");
	                		$(this).find(".l_ddmk02, .l_ddmk03, .l_ddmk04").css("height", rheight - 30 + "px");
	                	});

	                    pcount = rsl.maxRow;
	                    pindex = rsl.pageIndex;
	                    //绑定订单个数
	                    var htmlcount = template('listcount', countdata);
						 $("#ordercount").html(htmlcount);
						 switch(pstatus){
						 case -1:$("#li_all").attr('class', 'active');break;
						 case 0:$("#dfk").attr('class', 'active');break;
						 case 1:$("#dfh").attr('class', 'active');break;
						 case 2:$("#dsh").attr('class', 'active');break;
						 case 99:$("#dpj").attr('class', 'active');break;
						 default:break;
						 }
	                    //分页
	                    $("#pager").html(pagination(pcount, pindex, psize, "Order.getorder"));
	                    
	                    //操作按钮事件绑定
	                    //产品页面跳转
	                    //      $(".a_spuhref").bind("click", Goods_Href);
//	                          //拆单详情
//	                          $(".a_orderdisconnect").bind("click", Order_Disconnect);
	                          //订单详情
	                          $(".a_orderdetailinfo").bind("click", Order_DetailInfo);
//	                          //评价晒单
	                       //   $(".a_comment").bind("click", Order_Comment);
	                          //取消订单
	                         $(".a_ordercancel").bind("click", Order_Cancel);
//	                          //去付款
	                          //$(".p_gopay").bind("click", GoPayment);

	                          //申请售后
	                          $(".a_afterservice").bind("click", Order_Afterservice);
	                          //确认收货
	                        //  $(".a_confirmgoods").bind("click", Order_ConfirmGoods(this));
	                
					}

					else {
//						alert(rsl.desc);
					}
				},
				error : function(e) {

				}
			});
		},
		
	   
		getcancelorder:function(index){
			$.ajax({
				type : "post",
				url : "/pc/order/getcancelorder",
				dataType : "json",
				data : {
					"ch" : 1,
					"page":index,
					"size":10
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						   var listdata = {
		                            list: rsl.data
		                        }
	                    var html = template('orderslist', listdata);
						   //翻页时删除表头以外的所有节点，避免after()方法重复加载
		                    $("#orderslist_table").parent().children().each(function () {
		                        if ($(this).attr('id') != "orderslist_table") {
		                            this.parentNode.removeChild(this);
		                        }
		                    });
		                 //html 填充
		                $("#orderslist_table").after(html);
	                    pcount = rsl.maxRow;
	                    pindex = rsl.pageIndex;
	                    //分页
	                    $("#pager").html(pagination(pcount, pindex, psize, "Order.getcancelorder"));
	                    
					}

					else {
//						alert(rsl.desc);
					}
				},
				error : function(e) {

				}
			});
		},	
		deletecancel : function(id) {
			$.ajax({
				type : "post",
				url : "/pc/order/delorder",
				dataType : "json",
				data : {
					id : id
				},
				success : function(rsl) {
					if (rsl.code == 0) {
						Order.getcancelorder(pindex);
					}
				},
				error : function(e) {

				}
			});
		}
}


//改变评论类型
//function changetype(obj, type) {
    
   // $(".active").attr('class', 'normal');
   // $(obj).attr('class', 'active');
//}


//订单时段改变
function Order_TimeBucket() {
	$("#text_search").attr("value", "");
    var timeBut = $("#select_timebucket").val();
    Order.getorder(1);
    $(".active").attr('class', 'normal');
    $("#li_all").attr('class', 'active');
}
function changestatus(ostatus){
	pstatus=ostatus;
	Order.getorder(1);
}
//产品页面跳转
function Goods_Href() {
    var skuid = parseInt($(this).attr("sku-val"));
    window.location.href = "/web/products/proinfo.html?skuid=" + spuid;
}
//订单详情
function Order_DetailInfo() {
    var orderID = $(this).attr("orderid-val");
   
    window.location.href = "/member/order/showOrderDetail?orderid=" + orderID;
}


function Order_Afterservice() {
    var orderdetailID = $(this).attr("orderdetailid-val");
    var backUrl = encodeURIComponent(window.location.href);
    window.location.href = "/member/khFwmemb/RepairOrReturn.html?orderdetailID=" + orderdetailID + "&backUrl=" + backUrl;
}


/*function Order_Comment() {
    var orderdetailID = $(this).attr("orderdetailid-val");
    var backUrl = encodeURIComponent(window.location.href);
    window.location.href = "/member/khFwmemb/RepairOrReturn.html?orderdetailID=" + orderdetailID + "&backUrl=" + backUrl;
}
*/
//订单取消
function Order_Cancel() {
  var c =  prompt('请输入取消原因');
	if(c!=null){
		var orderid=$(this).attr("orderid-val");
		var statu =$(this).attr("value");
		Order.cancelorder(statu,orderid,c);
	}
}
//确认收货
function Order_ConfirmGoods(orderid) {
    Order.confirmationgood(orderid);
}
//申请售后
function Order_Afterservice(){
	  var detailID = $(this).attr("orderdetailid-val");
	window.location.href = "/member/khFwmemb/RepairOrReturn.html?orderdetailID=" + detailID;
}
