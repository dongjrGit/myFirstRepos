//角色关联权限
var permids = "";
var type = "";  //0-平台 1-店铺
var rp={
    bind: function (roleid, action,rp_type) {
        var edhtml = "";
        var rpUrl = "";
        if (rp_type == 0) {
            rpUrl = "/Permission/P_getSysPermWeb";
        }
        else { rpUrl = "/Permission/G_getSysPermWeb"; }
        type = rp_type;
        if (action == "0") {
            //获取当前角色拥有的权限ID集合
            $.ajax({
                url: "/Permission/G_GetPermListByRole",
                type: "Post",
                data: { RoleId: roleid },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        alert(data.Desc);
                    }
                    else {
                        if (data.Data.length > 0) {
                            for (var i = 0; i < data.Data.length; i++) {
                                if (permids == "") {
                                    permids += "," + data.Data[i].ID + ",";
                                }
                                else {
                                    permids += data.Data[i].ID + ",";
                                }
                            }
                        }
                    }
                }
            });

            //获取当前有效权限
            $.ajax({
                url: rpUrl,
                type: "Post",
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        alert(data.Desc);
                    }
                    else {
                        edhtml += "<tr>";
                        edhtml += "<th width='10%'>选择</th>";
                        edhtml += "<th width='25%'>权限名称</th>";
                        edhtml += "<th width='*'>权限描述</th>";
                        edhtml += "</tr>";
                        if (data.Data.length > 0) {
                            for (var i = 0; i < data.Data.length; i++) {
                                edhtml += "<tr>";
                                if (permids.indexOf("," + data.Data[i].ID + ",") >= 0) {
                                    edhtml += "<td><input type='checkbox' name='chkperm' value='" + data.Data[i].ID + "' checked /></td>";
                                }
                                else {
                                    edhtml += "<td><input type='checkbox' name='chkperm' value='" + data.Data[i].ID + "' /></td>";
                                }
                                edhtml += "<td>" + data.Data[i].Name + "</td>";
                                edhtml += "<td>" + data.Data[i].Description + "</td>";
                                edhtml += "</tr>";
                            }
                        }
                        $("#rp_list").html(edhtml);
                    }
                }
            });
        }
        else
        {
            $.ajax({
                url: "/Permission/G_GetPermListByRole",
                type: "Post",
                data: { RoleId: roleid },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) {
                        alert(data.Desc);
                    }
                    else {
                        var listdata = {

                            list: data.Data
                        }

                        var html = template('rplist', listdata);

                        //翻页时删除表头以外的所有节点，避免after()方法重复加载
                        $("#rp_title").parent().children().each(function () {
                            if ($(this).attr('id') != "rp_title") {
                                this.parentNode.removeChild(this);
                            }
                        })
                        //html 填充
                        $("#rp_title").after(html);
                        //加载table样式：改变偶数行背景色，鼠标移动时背景色
                        init();
                    }
                },
                error: function () {

                }
            });
        }
    }
}
//按钮事件绑定
$(function () {

    $("input[name=rpsave]").bind("click",save);
    $("input[name=rpselectall]").bind("click", selectall); //全选
    $("input[name=rpremoveall]").bind("click", removeall); //取消
})
//保存
function save() {
    var roleid = $("#roleid").val();
    var rUrl = "";
    if (permids == "") {
        if (type == 0) {
            rUrl = "/Role/P_AddRolePermWeb";
        }
        else {
            rUrl = "/Role/P_AddRolePermShop";
        }
        
    }
    else {
        if (type == 0) {
            rUrl = "/Role/P_UpdateRolePermWeb";
        }
        else {
            rUrl = "/Role/P_UpdateRolePermShop";
        }
    }
    permids = "";
    //var chks = document.getElementsByName("chkperm");
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var nodes = treeObj.getCheckedNodes(true);

    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].checked) {
            if (permids == "") {
                permids = nodes[i].id;
            }
            else {
                permids += "," + nodes[i].id;
            }
        }
        
       
    }
    var delUrl = "";
    if (permids == "") {
        //alert("请选择权限。");
        if (type == 0) {
            delUrl = "/Role/P_DelRolePermWeb";
        }
        else {
            delUrl = "/Role/P_DelRolePermShop";
        }
        if (confirm('确定清空该角色下的权限?')) {
            $.ajax({
                url: delUrl,
                type: "Post",
                data: { roleid: roleid },
                dataType: "json",
                success: function (data) {
                    if (data.Code > 0) {
                        alert("操作成功");
                    }
                    else {
                        alert(data.Desc);
                    }
                },
                error: function () {
                    alert("删除失败");
                }
            });
        }
    }
    else {
        $.ajax({
            url: rUrl,
            type: "Post",
            data: { roleid: roleid, permids: permids },
            dataType: "json",
            success: function (data) {
                if (data.Code > 0) {
                    alert("操作成功");
                }
                else {
                    alert(data.Desc);
                }
            },
            error: function () {

            }
        });
    }
}
function selectall() {
   $("input[name=chkperm]").attr("checked", true);
}
function removeall() {
    $("input[name=chkperm]").attr("checked", false);
}
