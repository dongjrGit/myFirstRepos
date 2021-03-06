
$(function(){
	$("input[name=Save]").bind("click",save);
	if($("#id").val()>0){
		var province=$("#province").val();
		var city=$("#city").val();
		var area=$("#area").val();
		GetRegionData(0,"");
		$("#select_province").val(province);
		GetRegionData(1, province);
		$("#select_city").val(city);
		GetRegionData(2, city);
		$("#select_area").val(area);
	}
});

function save(){
	if (formSubmit()) {
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		var type=$("#type").val();
		var ctype = $("#ctype").val();
		var str="";
		$(".h_imgcon ul").find("img").each(function(x,item){
			var $obj=$(this);
			if(!$obj.parent().is(":hidden")){
				str+= $obj.attr("src")+"|";
			}
		});
		$("#imgs").val(str);
		editor.sync();
		$.ajax({
			url : "/platform/news/editnewsimg",
			type : "Post",
			data : $("#form").serialize(),
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					Dalert("保存成功！", "", function(){window.location.href="/platform/news/mapnewsimg?type="+type+"&ctype="+ctype});
				} else {
					$("input[name='Save']").show();
					Dalert(data.desc);
				}
			},
			error : function() {
			}
		});
	}
}

//保存前参数验证
function check() {
	return $("#form").validate({
		rules : {
			name : {
				required : true,
				stringCheck : true,
				rangelength : [ 1, 100 ]
			},
		},
		message : {
			name : {
				required : "标题不可为空"
			}
		}
	});
}

function formSubmit() {
	if (check().form()) {
		if ($("#thirdID").val() != 0) {
			$("#ClassID").val($("#thirdID").val());
		}else if ($("#secondID").val()!= 0) {
			$("#ClassID").val($("#secondID").val());
		}else if ($("#firstID").val()!= 0) {
			$("#ClassID").val($("#firstID").val());
		}
		var tpobj=$("#select_province").parent("div").find("label.error");
		var ishead=$("input[name=ishead]").parent().find("label.error");
		var longitude=$("#longitude").parent().find("label.error");
		tpobj.hide();
		tpobj.html("");
		ishead.hide();
		longitude.hide();
		var is=true;
		if ($("#select_province option:selected").text()=="请选择") {
			tpobj.append("省不能为空！");
			is=false;
		}
		if ($("#select_city option:selected").text()=="请选择") {
			tpobj.append("市不能为空！");
			is=false;
		}
		if ($("#select_area option:selected").text()=="请选择") {
			tpobj.append("区不能为空！");
			tpobj.show();
			is=false;
		}
		if ($("#text_companyadress").val()=="") {
			tpobj.append("详细地址不能为空！");
			is=false;
		}
		var isheadcount=0;
		$("input[name=ishead]").parent().find("input").each(function(){
			if ($(this)[0].checked) {
				isheadcount++;
			}
		});
		if (isheadcount<=0) {
			ishead.html("请选择至少一个功能操作");
			is=false;
		}
		if($("#longitude").val()==""||$("#latitude").val()==""){
			longitude.html("经度纬度不能为空!")
			is=false;
		}
		if (is==false) {
			ishead.show();
			tpobj.show();
			longitude.show();
		}
		return is;
	}

}


//所在地数据绑定
function GetRegionData(rType, pCode) {
	$.ajax(({
		type : "post",
		url : "/platform/news/queryRegion",
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
				case 2:
					html = template('areaselect', listdata);
					$("#select_area").append(html);
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
			url : "/platform/news/queryRegion",
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
					$("#select_area option[value!='-1']").remove();
					$("#select_city").append(html);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	})

	// 市改变
	$("#select_city").change(function() {
		var p1 = $(this).children('option:selected').val();
		$.ajax(({
			type : "post",
			url : "/platform/news/queryRegion",
			dataType : "json",
			data : {
				type : 2,
				code : p1
			},
			success : function(rsl) {
				if (rsl.code == 0) {
					var listdata = {
						list : rsl.data
					}
					var html = template('areaselect', listdata);
					$("#select_area option[value!='-1']").remove();
					$("#select_area").append(html);
				} else {
					Dalert(rsl.desc);
				}
			},
			error : function(e) {

			}
		}));
	})

}





