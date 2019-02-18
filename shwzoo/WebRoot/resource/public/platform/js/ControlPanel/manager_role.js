//平台角色管理

var pcount, pindex, psize = size_common;
//页面加载数据
var role = {
    bind: function (index) {
        var rolename = $("#name").val();
        $.ajax({
            url: "/platform/role/getRoleList",
            type: "Post",
            data: { 'page': index, 'size': psize, 'name': rolename },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    var listdata = {
                        list: data.data
                    }

                    var html = template('rolelist', listdata);
                    //html 填充
                    $("#datalist").html(html);
                    //加载table样式：改变偶数行背景色，鼠标移动时背景色
                    init();
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "pagelist"));
                }
            },
            error: function () {
                Dalert("数据加载失败");
            }
        });
    }
}
//分页回调函数
function pagelist(index) {
    role.bind(index);
}
//按钮事件绑定
$(function () {

    $("input[name=roleadd]").bind("click", roleadd);
    $("input[name=rolesearch]").bind("click", rolesearch);
    $("input[name=Save]").bind("click", Save);
})

//添加
function roleadd() {
    self.location = "Control_RoleEdit";

}
//查询
function rolesearch() {
    role.bind(1);
}
//删除
function del(roleid) {
    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/platform/role/deleteRole",
            type: "Post",
            data: { id: roleid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                }
                else {
                    pagelist(pindex);
                }
            },
            error: function () {
                Dalert("删除失败");
            }
        });
    }
}
var d = [];
//获取角色对应权限 rname-角色名称，roleid-角色ID，raction-修改或查看
function getperm(rname, roleid, raction) {
    var stype = 1;
    d = dialog({
        title: rname + '的权限列表',
        url: 'Control_RolePerm?action=' + raction + '&id=' + roleid,
        width: 500,
        //height: 600,
        //fixed: true
        //modal: true, //蒙层
    });
    d.show();
}
function closeDialog() { d.close(); }

//更改状态  ss-状态
function setStatus(roleid, ss) {
    $.ajax({
        url: "/platform/role/updateStatus",
        type: "Post",
        data: { 'id': roleid, 'status': ss },
        dataType: "json",
        success: function (data) {
            if (data.code < 0) { Dalert(data.desc); }
            else {
                var td_html = "";
                if (ss == 0) {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + roleid + ",1)>启用</a></span>";
                }
                else {
                    td_html = "<span class='lvs'><a href='#' onclick=setStatus(" + roleid + ",0)>禁用</a></span>";
                }
                $("#td_" + roleid).html(td_html);
            }
        },
        error: function () {
        }
    });
}
//保存前参数验证
function check() {
    return $("#form").validate({
        rules: {
            name: {
                required: true,
                stringCheck: true,
                rangelength: [1, 25]
            },
            description: {
                maxlength: 20
            }
        },

        message: {
            name: {
                required: "角色名称不可为空"
            },
            description: {
                maxlength: "角色描述不能超过20个字"
            }
        }
    });
}
//保存
function Save() {
    var action = $("#role_action").val();

    if (check().form()) {
        //防止重复提交 点击保存后隐藏按钮
        $("input[name='Save']").hide();
            $.ajax({
                url: "/platform/role/" + action,
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
                    }
                },
                error: function () {
                }
            });
        }
}

//刷新
function backhref() {
 window.location.href = 'control_RoleList';
}

