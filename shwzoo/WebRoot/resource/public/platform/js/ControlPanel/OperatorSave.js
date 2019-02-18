//操作员编辑

var userrole = "", userdepart = "";
var roleid = "", departid = "", userid="";
var oper = {
    unit: function (callback) {
    	oper.getFirstClass(callback); //获取一级分类
    }, 
    getFirstClass: function (callback) {
        $.ajax({
            url: "/platform/department/getByFatherID",
            type: "post",
            data: { 'fatherid': 0 },
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
    firstChange: function (callback, value) {  //根据父ID获取分类
        var fid = $("#firstID").val();
      
        if (value == "fc") {
            fid = $("#firstID").val();
        }
        else if (value == "sc") {
            fid = $("#secondID").val();
        }
        $.ajax({
            url: "/platform/department/getByFatherID",
            type: "post",
            data: { 'fatherid': fid },
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
                            var html = '<option value="" id="defaulttc" selected>无</option>' + template('tlist', listdata);
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
    callback: function (value) {   //分类回调
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
        oper.firstChange(oper.callback, value);
    }
    ,
	/*
	 * callback 成功 有数据时 调的方法 event 事件
	 */
    getRoleStartwithName : function(callback, event) {
		var name = $("#select_role").val();
		if (event)
			name += String.fromCharCode(event.keyCode);
		$.ajax({
			url : "/platform/role/getPlatRoleStartWithName",
			type : "Post",
			data : {
				"name" : name,"shopid":0
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 0) {
					var listdata = {
						list : data.data
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
}
//获取第三级分类
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
//按钮事件绑定
$(function(){
    $("input[name=Save]").bind("click", Save);
    autoxl.bind("select_role", oper.getRoleStartwithName, true);
})

//保存
function Save() {
    var action = $("#oper_action").val();
    if($("#thirdID").val()!="" && $("#thirdID").val()!="0"){
    	$("#departid").val($("#thirdID").val());
    }else{
    	if($("#secondID").val()!="" && $("#secondID").val()!="0"){
        	$("#departid").val($("#secondID").val());
        }else{
        	$("#departid").val($("#firstID").val());
        }
    }
    $("#roleid").val($("#select_role").attr("data"));
    if (check().form()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/platform/operator/" + action,
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.desc);
                }
                else {
                    Dalert(data.desc,"",backhref);
                    //window.location.href = '/Platform/kz/OperatorList';
                }
            },
            error: function () {
            }
        });
    }
}
//保存前参数验证
function check() {
    return $("#form").validate({
        rules: {
            loginname: {
                required: true,
                stringCheck: true
            },
            password: {
                required: true,
                minlength: 6

            },
            compassword: {
                required: true,
                minlength: 6,
                equalTo: "#password"
            },
            realname: {
                required: true
            },
            email: {
                required: true,
                email: true
            }
        },
        message: {
        	loginname: {
                required: "登录名不可为空"
            },
            password: {
                required: "密码不可为空",
                minlength: "密码长度必须大于等于6"
            },
            compassword: {
                required: true,
                minlength: "密码长度必须大于等于6",
            },
            realname: {
                required: "真实姓名不可为空"
            },
            email: {
                required: "邮箱不可为空",
                email: "邮箱格式不合法"
            }
        }
    });
}
//刷新
function backhref() {
    window.location.href = 'operator_list';
}