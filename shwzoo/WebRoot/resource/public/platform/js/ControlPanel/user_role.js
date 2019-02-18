//用户关联角色（暂时用不到）
$(function () {
    $("input[name=save]").bind("click", save);
    userRole.bind($("#userid").val());
})

var roleids = "";
var userRole = {
    bind: function (id) {
        var strhtml ="";
        $.ajax({
            url: "/Role/GetRoleByUser",
            type: "Post",
            data: { UserID: id },
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                }
                else {

                    var len = data.Data.length;
                    if (len > 0) {
                        for (var i = 0; i < len; i++) {
                            if (roleids == "") {
                                roleids = "," + data.Data[i].RoleID + ",";
                            }
                            else {
                                roleids += data.Data[i].RoleID + ",";
                            }
                        }
                    }
                }
            },
            error: function () {

            }
        });
        $.ajax({
            url: "/Role/P_getSysRoleWeb",
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
                
                    var len = data.Data.length;
                    strhtml += " <tr id='role_title'> ";
                    strhtml += " <th width='10%'>选择</th> ";
                    strhtml += " <th width='50%'>角色名称</th> ";
                    strhtml += " <th width='40%'>查看权限</th> ";
                    strhtml += " </tr> ";
                    if (len > 0) {

                        for (var i = 0; i < len; i++) {
                            strhtml += " <tr> ";
                            strhtml += " <td><input name='chk_list' id='" + data.Data[i].ID + "' type='checkbox' ";
                            if (roleids.indexOf("," + data.Data[i].ID + ",") >= 0) {
                                strhtml += " checked ";
                            }
                            strhtml += " ></td>";
                            strhtml += " <td>" + data.Data[i].Name + "</td>";
                            strhtml += " <td><a href='#' onclick=getperm('" + data.Data[i].Name + "'," + data.Data[i].ID + ",1,0)><span class='bjxx'>查看权限</span></a></td>";
                            strhtml += " </tr> ";
                        }
                        $(".data_list").html(strhtml);
                    }
                }
            },
            error: function () {
            }
        });
    }
}


//获取角色对应权限 rname-角色名称，roleid-角色ID，raction-修改或查看，type-平台或者店铺
function getperm(rname, roleid, raction,rtype) {
   
    var d = dialog({
        title: rname + '的权限列表',
        url: 'manager_role_permission?action=' + raction + '&type=' + rtype + '&id=' + roleid,
        width: 400,
        modal: true, //蒙层
    });
    d.show();
}

function save() {
   
    var Userid = $("#userid").val();
    var rUrl = "";
    if (roleids == "") {
        rUrl = "/Role/AddRoleByUser";
    }
    else {
        rUrl = "/Role/UpdateRoleByUser";
    }
    roleids = "";
    var chks = document.getElementsByName("chk_list");

    for (var i = 0; i < chks.length; i++) {
        if (chks[i].checked) {
            if (roleids == "") {
                roleids = chks[i].id;
            }
            else {
                roleids += "," + chks[i].id;
            }
        }
    }
    if (roleids == "") {
        rUrl = "/Role/DelRoleByUser";

        if (confirm('确定清空该用户关联的角色?')) {
            $.ajax({
                url: rUrl,
                type: "Post",
                data: { userid: Userid },
                dataType: "json",
                success: function (data) {
                    if (data.Code > 0) {
                        Dalert("操作成功");
                    }
                    else {
                        Dalert(data.Desc);
                    }
                },
                error: function () {
                    Dalert("删除失败");
                }
            });
        }
    }
    else {
        $.ajax({
            url: rUrl,
            type: "Post",
            data: { userid: Userid, roleids: roleids },
            dataType: "json",
            success: function (data) {
                if (data.Code > 0) {
                    Dalert("操作成功");
                }
                else {
                    Dalert(data.Desc);
                }
            },
            error: function () {

            }
        });
    }
}