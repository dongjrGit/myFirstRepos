//部门Ztree树结构
var setting = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick
	}
};

$(function() {
	$("#FullPath").mousedown(function() {
		showMenu();
	});
})
// 部门树结构加载
var zTree = {
	init : function() {
		var nodeshtml = [];
		$.ajax({
					url : "/platform/department/queryDeparAll",
					type : "Post",
					data : {},
					dataType : "json",
					success : function(data) {
						
						if (data.code < 0) {
							Dalert(data.desc);
						} else {
							
							// ztree加载
							var len = data.data.length;
							if (len > 0) {
								for (var i = 0; i < len; i++) {
									if (data.data[i].fatherid == "0") {
										
										nodeshtml.push({
											id : data.data[i].id,
											pId : 0,
											num : data.data[i].num,
											name : data.data[i].name,
											open : false
										});
										for (var j = 0; j < len; j++) {
											if (data.data[j].fatherid == data.data[i].id) {
												nodeshtml
														.push({
															id : data.data[j].id,
															pId : data.data[j].fatherid,
															num : data.data[j].num,
															name : data.data[j].name
														});
												for (var k = 0; k < len; k++) {
													if (data.data[k].fatherid == data.data[j].id) {
														nodeshtml
																.push({
																	id : data.data[k].id,
																	pId : data.data[k].fatherid,
																	num : data.data[k].num,
																	name : data.data[k].name
																});
													}
												}
											}
										}
									}
								}
							}

							$.fn.zTree.init($("#treeDemo"), setting, nodeshtml);
						}
					},
					error : function() {
						
					}
				});
	}
}

// 点击树节点事件
function onClick(e, treeId, treeNode) {
 var departid=$("#h_department_id").val();
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), name = "", fid = "";
	fid = nodes[0].id;
	name = nodes[0].name;
    if(parseInt(departid)!=parseInt(fid)){
	if (nodes[0].level > 0) {
		nodes = nodes[0].getParentNode();
		name = nodes.name + "->" + name;
		if (nodes.level > 0) {
			nodes = nodes.getParentNode();
			name = nodes.name + "->" + name;
			if (nodes.level > 0) {
				nodes = nodes.getParentNode();
				name = nodes.name + "->" + name;
			}
		}
	}
	var Cname = $("#FullPath");
	Cname.attr("value", name);
	$("#fid").val(fid);
	}
}
// 单击显示部门树结构div
function showMenu() {
	var CnameObj = $("#FullPath");
	var CnameOffset = $("#FullPath").offset();
	$("#menuContent").css({
		left : CnameOffset.left + "px",
		top : CnameOffset.top + CnameObj.outerHeight() + "px",
		width : CnameObj.width()
	}).slideDown("fast");
	$("#treeDemo").css({
		width : CnameObj.width() - 10
	});
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
// 隐藏部门树结构div
function onBodyDown(event) {
	if (!(event.target.id == "FullPath" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}
