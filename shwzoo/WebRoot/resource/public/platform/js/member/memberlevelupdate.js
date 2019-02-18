//用户等级编辑或添加
$(document).ready(function() {
	var id = GetQueryStringByName("leID");

	if (GetQueryStringByName("isEdOrAd") == "0") {
		$("#leveltitle").html("编辑等级");
		GetByID(id);
	} else {
		$("#leveltitle").html("添加等级");
	}
	// 返回
	$("#backBtn").click(function() {
		location.href = "/platform/member/showMember_level";
	})
	// 保存
	$("#submit_ok").click(function() {
		if (check().form()) {
			level();
		}
	})
	// 验证表单
	function check() {
		return $("#updatelevelForm").validate({
			rules : {
				levelnum : {
					required : true,
					digits : true
				},
				levelname : {
					rangelength : [ 1, 15 ],
					required : true
				},
				levelup : {
					required : true
				},
				Cycle : {
					required : true,
					digits : true
				},
				levelsta : {
					required : true
				},
				LevelDown : {
					required : true,
					digits : true
				},
				PointDown : {
					required : true,
					digits : true
				}
			},
			// 设置提示信息
			messages : {
				levelnum : {
					required : "必填",
					digits : "请输入大于0的正数"
				},
				levelname : {
					rangelength : "请输入1-15字符",
					required : "必填"
				},
				levelup : {
					required : "必填"
				},
				Cycle : {
					required : "必填",
					digits : "请输入大于0的正数"
				},
				levelsta : {
					required : "必填"
				},
				LevelDown : {
					required : "必填",
					digits : "请输入大于0的正数"
				},
				PointDown : {
					required : "必填",
					digits : "请输入大于0的正数"
				}
			},
			// 设置错误信息存放标签
			errorElement : "label",
			// 设置验证触发事件
			focusInvalid : true
		})
	}

	function level() {
		var lenum = $("#levelnum").val();
		;
		var lename = $("#levelname").val();
		var leup = $("#levelup").val();
		var lecy = $("#Cycle").val();
		var lecyu = $("#CycleUnit").val();
		var ledon = $("#LevelDown").val();
		var leusd = $("#PointDown").val();
		var sta = $("#levelsta").val();
		if (GetQueryStringByName("isEdOrAd") == "0") {
			editLevel(id, lename, lenum, lecy, lecyu, ledon, leusd, leup, sta);
		} else {
			addLevel(lename, lenum, lecy, lecyu, ledon, leusd, leup, sta);
		}
	}

});
// 返回
function backhref() {
	window.location.href = "/platform/member/showMember_level";
}
// 编辑 用户等级
// id：用户id，name：等级名称，le：用户等级，cy：周期，cyu：周期单位，led：下降等级，pod：下降积分，usd：降级标准，sta：状态（0正常，1冻结）
function editLevel(id, name, le, cy, cyu, led, pod, usd, sta) {
	$.ajax({
		url : "/platform/memberlevel/updateMemberLevelById",
		type : "post",
		dataType : "json",
		data : {
			id : id,
			name : name,
			level : le,
			cycle : cy,
			cycleunit : cyu,
			leveldown : led,
			pointdown : pod,
			upstandard : usd,
			status : sta
		},
		success : function(data) {
			if (data.code < 0) {
				Dalert(data.desc);
			} else {
				Dalert(data.desc, "", backhref);
			}
		},
		error : function() {
		}
	});
}
// 添加
// name：等级名称，le：用户等级，cy：周期，cyu：周期单位，led：下降等级，pod：下降积分，usd：降级标准，sta：状态（0正常，1冻结）
function addLevel(name, le, cy, cyu, led, pod, usd, sta) {
	$.ajax({
		url : "/platform/memberlevel/addMemberLevel",
		type : "post",
		dataType : "json",
		data : {
			name : name,
			level : le,
			cycle : cy,
			cycleunit : cyu,
			leveldown : led,
			pointdown : pod,
			upstandard : usd,
			status : sta
		},
		success : function(data) {
			if (data.code < 0) {
				Dalert(data.desc);
			} else {
				Dalert(data.desc, "", backhref);
			}
		},
		error : function() {
		}
	});
}
// 获取编辑列表 id：等级id
function GetByID(id) {
	$.ajax({
		url : "/platform/member/queryMemberLevelById",
		type : "post",
		data : {
			id : id
		},
		dataType : "json",
		success : function(data) {
			if (data.code < 0) {
				// Dalert(data.Desc);
			} else {
				var af = data.data;
				$("#levelnum").attr("value", af.level);
				$("#levelname").attr("value", af.name);
				$("#levelup").attr("value", af.upstandard);
				$("#Cycle").attr("value", af.cycle);
				$("#CycleUnit").attr("value", af.cycleunit);
				$("#LevelDown").attr("value", af.leveldown);
				$("#PointDown").attr("value", af.pointdown);
				$("#meID_hid").attr("value", id);
				if (af.status == "0") {
					$("#levelsta")[0].selectedIndex = 0;
				} else {
					$("#levelsta")[0].selectedIndex = 1;
				}

			}

		}
	});
}
