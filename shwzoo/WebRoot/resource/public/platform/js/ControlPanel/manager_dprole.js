//店铺角色管理

var pcount, pindex, psize = size_common;
// 页面加载数据
var role = {
	bind : function(index) {
		var rolename = $("#name").val();
		var select_shop = $("#select_shop").attr("data");
		$.ajax({
			url : "/platform/role/getShopRoleList",
			type : "Post",
			data : {
				'page' : index,
				'size' : psize,
				'name' : rolename,
				"shopid" : select_shop
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					var listdata = {

						list : data.data
					}

					var html = template('rolelist', listdata);

					// html 填充
					$("#datalist").html(html);
					// 加载table样式：改变偶数行背景色，鼠标移动时背景色
					init();
					pcount = data.maxRow;
					pindex = data.pageIndex;

					// 分页
					$("#pager").html(
							pagination(pcount, pindex, psize, "pagelist"));
				}
			},
			error : function() {
				Dalert("数据加载失败");
			}
		});
	}
}
// 分页函数
function pagelist(index) {
	role.bind(index);
}

// 按钮事件绑定
$(function() {

	$("input[name=roleadd]").bind("click", roleadd);
	$("input[name=rolesearch]").bind("click", rolesearch);
	$("input[name=Save]").bind("click", Save);
    autoxl.bind("select_shop", getShopStartwithName, true);
})
// 添加
function roleadd() {
	self.location = "Control_dpRoleEdit";
}
// 查询
function rolesearch() {
	role.bind(1);
}
// 删除
function del(roleid) {
	if (confirm('确定将此记录删除?')) {
		$.ajax({
			url : "/platform/role/deleteShopRole",
			type : "Post",
			data : {
				'id' : roleid
			},
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					Dalert(data.desc);
				} else {
					pagelist(pindex);
				}
			},
			error : function() {
				Dalert("删除失败");
			}
		});
	}
}
var d = [];
// 获取角色对应权限 rname-角色名称，roleid-角色ID，raction-修改或查看
function getperm(rname, roleid, raction) {
	var stype = 2;
	// if (type == 1) { stype = 3; }
	d = dialog({
		title : rname + '的权限列表',
		url : 'Control_RolePerm?action=' + raction + '&id=' + roleid,
		width : 500,
	// height: 600,
	// fixed: true
	// modal: true, //蒙层
	});
	d.show();
}
function closeDialog() {
	d.close();
}
/*
 * callback 成功 有数据时 调的方法 event 事件
 */
function getShopStartwithName(callback, event) {
	var name = $("#select_shop").val();
	if (event)
		name += String.fromCharCode(event.keyCode);
	$.ajax({
		url : "/platform/shop/getShopStartWithName",
		type : "Post",
		data : {
			"name" : name
		},
		dataType : "json",
		success : function(data) {

			if (data.code == 0) {
				var listdata = {
					list : data.data
				}
				var html = template('select_shoplist', listdata);

				if (callback) {
					callback(html);
				}
			} else {
				Dalert(data.desc);
			}
		}
	});
}
// 更改状态 ss-状态
function setStatus(roleid, ss) {
	$
			.ajax({
				url : "/platform/role/updateShopStatus",
				type : "Post",
				data : {
					'id' : roleid,
					'status' : ss
				},
				dataType : "json",
				success : function(data) {
					if (data.code < 0) {
						Dalert(data.desc);
					} else {
						var td_html = "";
						if (ss == 0) {
							td_html = "<span class='lvs'><a href='#' onclick=setStatus("
									+ roleid + ",1)>启用</a></span>";
						} else {
							td_html = "<span class='lvs'><a href='#' onclick=setStatus("
									+ roleid + ",0)>禁用</a></span>";
						}
						$("#td_" + roleid).html(td_html);
					}
				},
				error : function() {
				}
			});
}
// 保存前参数验证
function check() {
	return $("#form").validate({
		rules : {
			name : {
				required : true,
				stringCheck : true,
				rangelength : [ 1, 25 ]
			},
			description : {
				maxlength : 20
			},
			shopid : {
				required : true
			}
		},

		message : {
			name : {
				required : "角色名称不可为空"
			},
			description : {
				maxlength : "角色描述不能超过20个字"
			}
		}
	});
}
// 保存
function Save() {
	var action = $("#role_action").val();
	var $selectshop = $("#select_shop");
	if ($selectshop.val() == "") {
		$("#shopid").val("");
	} else {
		if ($selectshop.attr("data") != undefined)
			$("#shopid").val($selectshop.attr("data"));
	}
	if (check().form()) {
		// 防止重复提交 点击保存后隐藏按钮
		$("input[name='Save']").hide();
		$.ajax({
			url : "/platform/role/" + action,
			type : "Post",
			data : $("#form").serialize(),
			dataType : "json",
			success : function(data) {
				if (data.code < 0) {
					$("input[name='Save']").show();
					Dalert(data.desc);
				} else {
					Dalert(data.desc, "", backhref);
					// window.location.href = '/Platform/kz/manager_dp_role';
				}
			},
			error : function() {
			}
		});
	}
}
// 刷新
function backhref() {
	window.location.href = 'Control_dpRoleList';
}
