$(document).ready(function() {

	var id = GetQueryStringByName("id");

	$("#select_type").val($("#input_type").val());

	var type = $("#select_type").val();

	if (type == 1) {
		GetGroup();
	}

	/*$("#select_type").bind("change", function() {
		var type = $(this).val();
		$("#name").val("");
		$("#hidden_name").val("");
		$('#name').removeAttr("disabled");
		if (type == 1) {
			GetGroup();
		}
	});*/
	
	/*// 保存
	$("#savebtn").click(function() {
		edit(id)
	});
	// 返回
	$("#backbtn").click(function() {
		location.href = "/platform/controlpanel/showlotteryparam";
	});*/
	
	
});



$(function () {
	GetSelectData();
    //保存
   
    $("#savebtn").click(function () {
        var id = GetQueryStringByName("id");
        edit(id)
    });
    //返回
    $("#backbtn").click(function () {
        backhref();
    });
});

function GetSelectData() {
	$.ajax(({
		type : "post",
		url : "/platform/litteryrule/getCouponList",
		dataType : "json",
		async : false,
		data : {},
		success : function(item) {
			if (item.code == 0) {
				var listdata = {
					list : item.data
				}
				var html = template('name', listdata);
				$("#couponname").append(html);
			} else {
				Dalert(item.desc);
			}
		},
		error : function(es) {

		}
	}));
}

//页面返回
function backhref() {
    location.href = "/platform/controlpanel/showlottery_rule";
}

function GetGroup() {
	$.ajax({
		url : "/platform/litteryrule/queryGroup",
		type : "post",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {

			} else {
				var ob = data.data;
				$("#name").val(ob.couponname);
				$("#hidden_name").val(ob.id);
				$("#name").attr("disabled", "true");
			}
		},
		error : function() {
		}
	});
}



// 编辑
function edit(id) {
	var mark = GetQueryStringByName("mark");
	var ruleId= $("#rule_id").val();
	
	var groupid="";
	var name="";
	if(mark==1){
		 groupid = $("#couponname").val();
	}else{
		 name = $("#name").val();
	}
	
	var description = $("#description").val();
	var value = $("#value").val();
	//var type = $("#select_type").val();
	//var groupid = $("#hidden_name").val();
	$.ajax({
		type : "post",
		url : "/platform/litteryrule/edit",
		dataType : "json",
		data : {
			//id : id,
			name : name,
			ruleid:ruleId,
			/*type : type,*/
			description : description,
			value : value,
			groupid : groupid
		},
		success : function(rsl) {
			if (rsl.code == 0) {
				Dalert(rsl.desc, "", backhref);
			} else {
				Dalert(rsl.desc);
			}
		},
		error : function(e) {
			// Dalert(e.statusText);
		}
	});
}


/*// 返回列表
function backhref() {
	location.href = "/platform/controlpanel/showlotteryparam";
}*/