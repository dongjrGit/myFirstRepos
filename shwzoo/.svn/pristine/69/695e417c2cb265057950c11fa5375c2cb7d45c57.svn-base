var index = 1, size = 2;
$(window).scroll(function() {
	if ($(document).height() - $(this).scrollTop() - $(this).height() < 100){
		index++;
		proList.getList(index);
	}
})
var proList = {
	getList : function(index) {
		$.ajax({
			type : "post",
			url : "/wap/products/getProducts",
			datatype : "json",
			data : {
				classID : $("#input_cid").val(),
				orderBy : null,
				orderType : null,
				index : index,
				size : size
			},
			success : function(data) {
				if (data.code == 0) {
					var html = "";
					for(var i = 0;i < data.data.length; i++){
						html += '<li class="fix">';
			            html +=	'<div class="l_mslistleft"><a href="#"><img src="/wap/images/img16.jpg" class="img-responsive" alt=""></a></div>';
			            html += '<div class="l_mslistright">';
	                	html += '<div><a href="#">' + data.data[i].name + '</a></div>';
	                    html += '<div class="l_mslistjg fix">￥' + data.data[i].price + '</div>';
	                    html += '<div class="huise mt5"><span>好评100%</span><span class="floatright">5364人</span></div>';
		                html += '</div></li>';
					}
					$("#ul_prolist").append(html);
				}
			}
		})
	},
	
}
