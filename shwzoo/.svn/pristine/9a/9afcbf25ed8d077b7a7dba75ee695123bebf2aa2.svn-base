//操作员列表

var pcount;
var pindex;
var psize = size_common;
var userrole = "", userdepart = "";
var roleid = "", departid = "";
//按钮事件绑定
$(function () {
    $("input[name=add]").bind("click", add);
    $("input[name=search]").bind("click", search);
    autoxl.bind("select_role", getRoleStartwithName);

    //autoxl.bind("select_buyer", getBuyerStartwithName);
})
//添加
function add() {
    self.location = "operator_edit";
}


//查询
function search() {
    Operator.bind(1);
} 
//列表数据加载
var Operator = {
    bind: function (index) {
        var dlhtml = ""
        var loginname = $("#name").val();
        var rid = $("#select_role").attr("data");
        var did = "";
        if ($("#thirdID").val() != "0") {
            did = $("#thirdID").val();
        }
        else {
            if ($("#secondID").val() != "0") {
                did = $("#secondID").val();
            }
            else {
                if ($("#firstID").val() != "0") {
                    did = $("#firstID").val();
                }
            }
        }
        $.ajax({
            url: "/platform/operator/getList",
            type: "Post",
            data: { 'page': index, 'size': psize, 'name': loginname, 'roleid': rid, 'departid': did },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) { Dalert(data.desc); }
                else
                {
                    var listdata = {

                        list: data.data
                    }

                    var html = template('operlist', listdata);
                    $("#datalist").html(html);
                    init();
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "Operator.bind"));
                }

            },
            error: function () {

            }
        })
    },
    unit: function (callback) {
        Operator.getFirstDepart(callback);
    },
    getFirstDepart: function (callback) {
        $.ajax({
            url: "/platform/department/getByFatherID",
            type: "Post",
            data: {"fatherid": 0},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    var html = template('flist', listdata);
                    $("#defaultfc").after(html);
                    if (callback) {
                        callback("fc");
                    }
                }
            },
            error: function () {

            }
        });
    },
    firstChange: function (callback, value) {
        var fid = $("#firstID").val();

        if (value == "fc") {
            fid = $("#firstID").val();
        }
        else if (value == "sc") {
            fid = $("#secondID").val();
        }
        $.ajax({
            url: "/platform/department/getByFatherID",
            type: "Post",
            data: { "fatherid": fid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    if (value == "fc") {
                        var html = '<option value="0" id="defaultsc" selected>无</option>' + template('slist', listdata);
                        $("#secondID").html(html);
                        if (callback) {
                            callback("sc");
                        }
                    }
                    else {
                        if (value == "sc") {
                            var html = '<option value="0" id="defaulttc" selected>无</option>' + template('tlist', listdata);
                            $("#thirdID").html(html);
                            getthird();
                        }
                    }

                }
            },
            error: function () {

            }
        })
    },
    callback: function (value) {
        var fid = $("#fid").val(), sid = $("#sid").val(); tid = $("#tid").val();
        if (value == "fc" && fid > 0) {
            $("#firstID option").each(function () {
                if ($(this).val() == fid) {
                    $(this).attr("selected", "selected");
                } else {
                    $(this).removeAttr("selected");
                }
            });
        }
        if (value == "sc" && sid > 0) {
            $("#secondID option").each(function () {
                if ($(this).val() == sid) {
                    $(this).attr("selected", "selected");
                } else {
                    $(this).removeAttr("selected");
                }
            });
        }
        Operator.firstChange(Operator.callback, value);
    },
    // 删除
	del : function(id) {
		if (confirm("您确定要删除该用户吗？")) {
	        $.ajax({
	            url: "/platform/operator/deleteOperator",
	            type: "Post",
	            data: { "id": id },
	            dataType: "json",
	            success: function (data) {
	                if (data.code < 0) { Dalert(data.desc); }
	                else
	                {
	                    Operator.bind(1);
	                }
	            },
	            error: function () {

	            }
	        });
	    }
//		ConfirmShow("确定要删除吗？", confrimDel, id, "");
	},
}

function confrimDel(id) {
	alert(id);
	$.ajax({
		url : "/platform/operator/deleteOperator",
		type : "Post",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(data) {
			 if (data.code < 0) { Dalert(data.desc); }
             else
             {
                 Operator.bind(1);
             }
		}
	});
	
}

/*
//删除用户
function del(id) {
    if (confirm("您确定要删除该用户吗？")) {
        $.ajax({
            url: "/platform/operator/deleteOperator",
            type: "Post",
            data: { userid: id },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) { Dalert(data.desc); }
                else
                {
                    Operator.bind();
                }

            },
            error: function () {

            }
        });
    }
}*/

//修改密码
function changePassword(id, UserName) {

    var contentHTML = "";
    contentHTML += "<span>修改密码：</span><input type='password' name='pass' style='width:220px;' value='' /><p style='height: 10px;'></p>";
    contentHTML += "<span>确认密码：</span><input type='password' name='compass' style='width:220px;' value='' /><p style='height: 10px;'></p>";
    contentHTML += "<span id='yanz' class='red'>注：密码长度大于等于6，两次密码输入必须一致</span>";
    d = dialog({
        title: "用户 " + UserName,
        content: contentHTML,
        width: 350,
        height: 80,
        button: [
    {
        value: '确认',
        callback: function () {
            if ($("input[name=pass]").val() != "" && $("input[name=pass]").val() != undefined && $("input[name=pass]").val().length >= 6 && $("input[name=pass]").val() == $("input[name=compass]").val()) {
                $.ajax({
                    url: "/platform/operator/updatePass",
                    type: "Post",
                    data: { "id": id, "password": $("input[name=pass]").val() },
                    dataType: "json",
                    success: function (data) {
                        if (data.code < 0) {
                            Dalert(data.desc);
                        } else {
                            Dalert(data.desc);
                            //location.reload();
                        }
                    },
                    error: function () {

                    }
                });
            }
            else {

                return false;
            }

        },
        autofocus: true
    },
    {
        value: '关闭'
    }
        ]
        //modal: true, //蒙层
    });
    d.show();
}

/*
callback 成功 有数据时 调的方法 
event 事件
*/
function getRoleStartwithName(callback, event) {
    var name = $("#select_role").val();
    if (event)
        name += String.fromCharCode(event.keyCode);
    $.ajax({
        url: "/platform/role/getPlatRoleStartWithName",
        type: "Post",
        data: { "name": name},
        dataType: "json",
        success: function (data) {
            if (data.code == 0) {
                var listdata = {
                    list: data.data
                }
                var html = template('select_rolelist', listdata);

                if (callback) {
                    callback(html);
                }
            } else {
                Dalert(data.desc);
            }
        }
    });
}
//获取第三级部门
function getthird() {
    tid = $("#tid").val();
    if (tid > 0) {
        $("#thirdID option").each(function () {
            if ($(this).val() == tid) {
                $(this).attr("selected", "selected");
            } else {
                $(this).removeAttr("selected");
            }
        })
    }

}