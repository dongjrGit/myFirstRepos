var Menu = {
    unit: function (callback) {
        Menu.getFirstMenu(callback); //获取一级分类
    },
    getFirstMenu: function (callback) {
        $.ajax({
            url: "/platform/menu/getByFatherID",
            type: "Post",
            data: {"fid": 0},
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
                        callback("fm");
                    }
                }
            },
            error: function () {

            }
        });
    },
    firstChange: function (callback, value) {  //根据父ID获取分类
        var fid = $("#firstID").val();

        if (value == "fm") {
            fid = $("#firstID").val();
        }
        else if (value == "sm") {
            fid = $("#secondID").val();
        }
        $.ajax({
            url: "/platform/menu/getByFatherID",
            type: "Post",
            data: { "fid": fid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }
                    if (value == "fm") {
                        var html = '<option value="0" id="defaultsc" selected>无</option>' + template('slist', listdata);
                        $("#secondID").html(html);
                        if (callback) {
                            callback("sm");
                        }
                    }
                    else {
                        if (value == "sm") {
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
    callback: function (value) {   //分类回调
        var fid = $("#fid").val(), sid = $("#sid").val(); tid = $("#tid").val();
        if (value=='fm' && fid > 0) {
            $("#firstID option").each(function () {
                if ($(this).val() == fid) {
                    $(this).attr("selected", "selected");
                } else {
                    $(this).removeAttr("selected");
                }
            });
        }
        if (value == 'sm' && sid > 0) {
            $("#secondID option").each(function () {
                if ($(this).val() == sid) {
                    $(this).attr("selected", "selected");
                } else {
                    $(this).removeAttr("selected");
                }
            });
        }
        if (tid > 0) {
            $("#thirdID option").each(function () {
                if ($(this).val() == tid) {
                    $(this).attr("selected", "selected");
                } else {
                    $(this).removeAttr("selected");
                }
            });
        }
        Menu.firstChange(Menu.callback, value);
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
$(function () {
    $("input[name=Save]").bind("click", Save);
})
//保存
function Save() {
    var action = $("#action").val();
    if (check().form()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
        $.ajax({
            url: "/platform/menu/" + action,
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    $("input[name='Save']").show();
                    Dalert(data.desc);
                }
                else {
                    Dalert(data.desc);

                    Dalert("保存成功！", "", function () { window.location.href = '/platform/controlpanel/Control_Menulist'; });
                  
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
            name: {
                required: true,
                rangelength: [1, 30]
            }
        },
        message: {
            name: {
                required: "菜单名称不可为空"
            }
        }
    });
}
