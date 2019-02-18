//订单列表
var psize = 10;
var orderstatus = "";
var cd = {
    bind: function () {
    },
    sl:function (id){
    	 var contentHTML = "";
         contentHTML += "<div class='l_qxyytop' style='margin-top:15px;'>催单处理返回结果：</div>";
         contentHTML += "<div><textarea name='sljg' cols='' rows='6' class='l_ckxqarea_big'>商家已经处理了您的催单，尽快给您安排发货</textarea></div>";
         d = dialog({
             title: '催单处理',
             content: contentHTML,
             width: 500,
             height: 180,
             button: [{
                          value: '处理',
                          callback: function () {
                        	  if($("textarea[name=sljg]").val()==""){
                        		  Dalert("请填写受理结果！")
                        		  return false;
                        	  }
                              $.ajax({
                                  url: "/platform/order/slcdResult",
                                  type: "Post",
                                  data: { "orderid": id,"sljg": $("textarea[name=sljg]").val() },
                                  dataType: "json",
                                  success: function (data) {
                                      if (data.code < 0) {
                                          Dalert(data.desc);
                                      } else {
                                          Dalert(data.desc, "", refresh);
                                          closeDialog();
                                      }
                                  },
                                  error: function () {

                                  }
                              });
                          },
                          autofocus: true
                      }]
         });
        
         d.show();
    },
    getlist: function (index) {
    	var begin = $("#select_begin").val();
	    var end = $("#select_end").val();
	    var isread=$("#isread").val();
		$.ajax({
			type : "post",
			url : "/platform/order/cdorderList",
			dataType : "json",
			data : {
				"page": index,"isread": isread, "size": psize, "start": begin, "end": end
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('noticelist', listdata);
					$("#data_list").html(html);
					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;
					$("#pager").html(
							pagination(pcount, pindex, psize, "pagelist"));

				} else {
					// Dalert(rsl.Desc);
				}
			},
			error : function(e) {
				//alert(e.statusText);
				// Dalert(e.statusText);
			}
		});
    }
    

}

 
//分页回调
function pagelist(index) {
	cd.getlist(index);
}
//页面刷新
function refresh() {
	window.location.href = "/platform/shop/cdorderList";
}