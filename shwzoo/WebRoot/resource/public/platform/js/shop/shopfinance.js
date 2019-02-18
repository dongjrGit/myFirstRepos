// 分页数据绑定
var pcount;
var pindex;
var psize = 10;
var indexlog;
var PointsList = {
	bind : function(index) {
		var shopcode = $("#select_shop").val();
		var shopname = $("#select_name").val();
		var type = $("#select_type").val();
		/*var paytype=$("#select_paytype").val();*/
		var paytype="";
		$("input:checkbox").each(function(){
			if($(this).is(':checked')){
				paytype += $(this).val()+",";
			}
		});
		var number=$("#select_number").val();
		var paynum=$("#select_paynum").val();
		var begin = $("#select_begin").val();
		var end = $("#select_end").val();
		var moneybegin=$("#money_begin").val();
		var moneyend=$("#money_end").val();
		$.ajax({
			type : "post",
			url : "/platform/shopfinance/getFinanceList",
			dataType : "json",
			data : {
				pageindex : index,
				pagesize : psize,
				shopcode:shopcode,
				shopname : shopname,
				type : type,
				paytype:paytype,
				number:number,
				paynum:paynum,
				starttime : begin,
				endtime : end,
				moneybegin:moneybegin,
				moneyend:moneyend
			},
			success : function(rsl) {

				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('goodconsultlist', listdata);

					// 翻页时删除表头以外的所有节点，避免after()方法重复加载
					$("#goodconsultlist_title").parent().children().each(function() {
						if ($(this).attr('id') != "goodconsultlist_title") {
							this.parentNode.removeChild(this);
						}
					})
					$("#goodconsultlist_title").after(html);

					indexlog = index;
					pcount = rsl.maxRow;
					pindex = rsl.pageIndex;					
					$("#pager").html(pagination(pcount, pindex, psize, "goodconsult_pagelist"));
				
				} else {
					Dalert(rsl.desc,10000);
				}
			},
			error : function(e) {

			}
		});
	},
	daochu:function () {
		if (confirm("确定要导出吗？")) {
			var paytype="";
			$("input:checkbox").each(function(){
				if($(this).is(':checked')){
					paytype += $(this).val()+",";
				}
			});
			location.href="/platform/shop/exportfinance?type="+$("#select_type").val()+"&" +
					"shopcode="+$("#select_shop").val()+"&starttime="+$("#select_begin").val()+"&endtime="+$("#select_end").val()+"&" +
					"shopname="+$("#select_name").val()+"&paytype="+paytype+"&" +
					"number="+$("#select_number").val()+"&paynum="+$("#select_paynum").val();
		}
	}
}
// 分页回调
function goodconsult_pagelist(index) {
	if (indexlog != index) {
		PointsList.bind(index);
	}
}

