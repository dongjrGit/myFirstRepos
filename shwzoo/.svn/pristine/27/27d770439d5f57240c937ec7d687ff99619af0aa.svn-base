/**
 * 
 */
var pcount, pindex, psize = size_order;
var orderstatus = "";
var gorder = {
	getlist : function(index) {
		var datahtml = "";
		var code = $("#ordercode").val();
		var add_begin = $("#add_begin").val();
		var add_end = $("#add_end").val();
		var status = $("#status").val();
		$.ajax({
			url : "/seller/shopgrouporder/getGroupOrderList",
			type : "Post",
			data : {
				"page" : index,
				"size" : psize,
				"num" : code,
				"start" : add_begin,
				"end" : add_end,
				"status" : status
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {
						list : data.data
					}

					var html = template('Grouplist', listdata);

					$("#datalist").html(html);
					pcount = data.maxRow;
					pindex = data.pageIndex;

					// 分页
					$("#pager").html(pagination(pcount, pindex, psize,"gorder.getlist"));
				}
			},
			error : function() {

			}
		});
	},
	del : function(id) {
		if (confirm('确定将此记录删除?')) {
			$.ajax({
				url : "/seller/shopgrouporder/delgrouporder",
				type : "Post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						gorder.getlist(pindex);
					}
				},
				error : function() {
		            Dalert("删除失败");
				}
			});
		}
	},
	//使用团购劵
    use: function (id) {
    	var code=$(this).attr("data-code");
        if (confirm('确定要使用团购劵吗?')) {
            $.ajax({
                url: "/seller/shopgrouporder/useGroupOrder",
                type: "Post",
                data: { "id": id, "code": code},
                dataType: "json",
                success: function (data) {
                    if (data.code < 0) {
                        Dalert(data.desc);
                    } else {
                        Dalert(data.desc);
                        gorder.getlist(pindex);
                    }
                },
                error: function () {

                }
            });
        }
    },
}

$(function () {
    $("input[name=search]").bind("click", function () { gorder.getlist(1); });
})