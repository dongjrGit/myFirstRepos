$(document).ready(function() {
		advert.bind(1);
		$("#searchtitle").bind("click", function() {
				advert.bind(1);
		});
		Ad.unit();
		autoxl.bind("userName", getUserStartwithName,true);
});

var advert = {
	bind : function(index) {
		var type=$("#firstID option:selected").val();
		var scoure=$("#secendID option:selected").val();	
		var userid=$("#userName").attr("data");
		var descripttion=$("#descript").val();
		var createtime=$("#select_begin").val();
		var endintime=$("#select_end").val();
		$.ajax({
			url : "/platform/userOperater/queryOperater",
			type : "Get",
			dataType : "json",
			data : {
				page : index,
				size : 10,
				scoure:scoure,
				type:type,
				userid:userid,
				description:descripttion,
				createtime:createtime,endtime:endintime
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
					var html = template('Advertlist', listdata);
					$("#datalist").html(html);
					// 分页
					pcount = data.maxRow;
					pindex = data.pageIndex;
					$("#pager").html(pagination(pcount, pindex, 10, "pagelist"));
				}
			},
			error : function() {

			}
		});
	}
}
function pagelist(index) {
	advert.bind(index);
}

function refresh() {
	advert.bind(1);
}

function getUserStartwithName(callback, event) {
	var scoure=$("#secendID option:selected").val();
    var name = $("#userName").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/userOperater/getName",
        type: "Get",
        data: { "scoure" : scoure,
        		"name":name
        },
        dataType: "json",
        success: function (data) {
           
            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('select_userlist', listdata);
                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.data);
            }
        }
    });
}

var Ad = {
		unit : function() {
			Ad.getList();
			Ad.getScoure();
		},
		getList : function() {
			$.ajax({
				url : "/platform/userOperater/getType",
				type : "Post",
				data : {},
				dataType : "json",
				success : function(data) {
					
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						var listdata = {
							list : data.data
						}
						var html = template('flist', listdata);
						$("#firstID").append(html);
					}
				},
				error : function() {
				}
			});
		},
		getScoure : function() {
			$.ajax({
				url : "/platform/userOperater/getScoure",
				type : "Post",
				data : {},
				dataType : "json",
				success : function(data) {
					
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						var listdata = {
							list : data.data
						}
						var html = template('secend', listdata);
						$("#secendID").append(html);
					}
				},
				error : function() {
				}
			});
		}
	}


// 删除 id:图片id
function del(id) {
	var type=$("#firstID option:selected").val();
	var scoure=$("#secendID option:selected").val();
	var createtime=$("#select_begin").val();
	$.ajax({
		type : "post",
		url : "/platform/userOperater/deleteOperter",
		dataType : "json",
		data : {
			scoure:scoure,
			type:type,
			createtime:createtime
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", refresh);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	});
}
