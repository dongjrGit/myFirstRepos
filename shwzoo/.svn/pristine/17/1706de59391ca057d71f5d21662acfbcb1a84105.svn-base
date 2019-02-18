
var pindex,psize=10,pcount=0;
var data={
	bind:function(index){
		
		$.ajax({
			url:"/platform/hotcity/getList",
			type:"post",
			data:{"pageindex":index,"pagesize":psize,"province":$("#select_province").val(),
				"city":$("#select_city").val(),},
			dataType:"json",
			success:function(data){
				if(data.code<0){
					Dalert(data.desc);
				}else{
					pindex=index;
					var listdata={
							list:data.data
					}
					var html=template("tdatalist",listdata);
					
					//翻页时删除表头以外的所有节点，避免after()方法重复加载
//                    $("#buy_title").parent().children().each(function () {
//                        if ($(this).attr('id') != "buy_title") {
//                            this.parentNode.removeChild(this);
//                        }
//                    })
                    //html 填充
                    $("#datalist").html(html);
                    pcount = data.maxRow;
					pindex = data.pageIndex;
                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
				}
			},
			error:function(){}
		});
	},
}

function pagelist(index) {
	data.bind(index);
}


function refresh() {
	// location.reload();
	data.bind(pindex);
}

function save() {
	if (formSubmit()) {
		var item = document.getElementById("select_city"); 
		var cityname = item.options[item.selectedIndex].text; 
		$.ajax({
			url : "/platform/hotcity/edit",
			type : "Post",
			data : {
				"id" : $("#id").val(),
				"province":$("#select_province").val(),
				"city":$("#select_city").val(),
				"sort":$("#sort").val(),
				"imgurl":$("#imgurl").val(),
				"name":cityname
			},
			dataType : "json",
			success : function(data) {

				if (data.code == 0) {
					Dalert("保存成功！","",function(){window.location.href = "/platform/rmcs/list"});
				} else {
					Dalert(data.desc);
				}
			}
		});
	}
}


function formSubmit() {
	if ($("#select_city").val() != -1) {
		return true;
	}else {
		Dalert("请选择热门城市");
		$("#select_city").focus();
		return false;
	}

}


//所在地数据绑定
function GetRegionData(rType, pCode) {
	$.ajax(({
		type : "post",
		url : "/platform/hotcity/queryRegion",
		dataType : "json",
		async : false,
		data : {
			type : rType,
			code : pCode
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				var listdata = {
					list : rsl.data
				};
				var html;
				switch (rType) {
				case 0:
					html = template('proviceselect', listdata);
					$("#select_province").append(html);
					break;
				case 1:
					html = template('cityselect', listdata);
					$("#select_city").append(html);
					break;
				default:
					break;
				}

			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {

		}
	}));
}

function BindRegion() {
	// 省数据绑定
	GetRegionData(0, "");

	// 省改变
	$("#select_province").change(function() {
		var p1 = $(this).children('option:selected').val();
		$.ajax(({
			type : "post",
			url : "/platform/hotcity/queryRegion",
			dataType : "json",
			data : {
				type : 1,
				code : p1
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('cityselect', listdata);
					$("#select_city option[value!='-1']").remove();
					$("#select_city").append(html);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	})


}


