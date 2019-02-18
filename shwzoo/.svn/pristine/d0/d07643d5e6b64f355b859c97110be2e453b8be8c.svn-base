//角色维护
var pcount;
var pindex;
var psize = 10;
//页面加载数据
var role = {
    bind: function (index) {
    	var rolename=$("#rolename").val();
        $.ajax({
            url: "/seller/shoprole/getShopRoleList",
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
                    pcount = data.maxRow;
                    pindex = data.pageIndex;

                    //分页
                    $("#pager").html(pagination(pcount, pindex, psize, "role.bind"));
                }
            },
            error: function () {
                Dalert("数据加载失败");
            }
        });
    }
}

//删除
function del(roleid) {
    if (confirm('确定将此记录删除?')) {
        $.ajax({
            url: "/seller/shoprole/deleteRole",
            type: "Post",
            data: { 'id': roleid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                }
                else {
                	role.bind(1);
                }
            },
            error: function () {
                Dalert("删除失败");
            }
        });
    }
}
//获取角色对应权限 rname-角色名称，roleid-角色ID，raction-修改或查看，type-卖家
var d = [];
function getperm(rname, roleid, raction) {

    d = dialog({
        id:"test",
        title: rname + '的权限列表',
        url: 'zhgl_RolePerm?action=' + raction + '&id=' + roleid,
        width: 500,
    });
    d.show();
}
function closeDialog() { d.close();}
$(function () {
    $("input[name=rolesearch]").bind("click", function(){role.bind(1);});
    $("input[name=Save]").bind("click", Save);
})
//保存
function Save() {
    var action = $("#role_action").val();
    if (check().form()) {
        $.ajax({
            url: "/seller/shoprole/" + action,
            type: "Post",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                }
                else {
                    Dalert(data.desc,"",refresh);
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
                stringCheck: true,
                rangelength: [1, 20]
            }
        },
        message: {
            name: {
                required: "角色名称不可为空"
            }
        }
    });
}
function refresh() {
    window.location.href = 'zhgl_RoleList';
}