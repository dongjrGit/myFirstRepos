//加载权限树结构
var setting = {
    view: {
        dblClickExpand: false
    },
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    }
};
$(function () {
    $("input[name=rpsave]").bind("click", save);
})

var permids = "";
var rptype = "";
var zTree = {    
    edit: function (roleid, type) {
        var edpermids = "";
        var sUrl = "/platform/role/getMenusTree";
        if(type==1){  
        	sUrl = "/platform/role/getShopMenusTree";
        }
        rptype = type;
        //获取当前角色拥有的权限ID集合
        $.ajax({
            url: "/platform/role/getMenuByRoleID",
            type: "Post",
            data: { "roleid" : roleid},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                }
                else {                
                    if (data.data.length > 0) {
                        for (var i = 0; i < data.data.length; i++) {
                            if (edpermids == "") {
                                edpermids += "," + data.data[i].menusid + ",";
                            }
                            else {
                                edpermids += data.data[i].menusid + ",";
                            }
                        }
                        permids = edpermids;
                    }
                }
            }
        });
        $.ajax({
            url: sUrl,
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {
                    //ztree加载
                    var len = data.data.length;
                    if (len > 0) {
                        for (var i = 0; i < len; i++) {
                            if (permids.indexOf("," + data.data[i].id + ",") >= 0) {
                                data.data[i].checked=true;
                            }
                            //data.Data[i].open = true;                        
                        }
                    }

                    $.fn.zTree.init($("#treeDemo"), setting, data.data);
                    setCheck();
                }
            },
            error: function () {
                Dalert("数据加载失败");
            }
        });
    },
    xx: function (roleid, type) {     
        var strnodes = [];
        var xxpermids = "";
        var sUrl = "/platform/role/getMenusTree";
        if(type==1){
        	sUrl = "/platform/role/getShopMenusTree";
        }
        //获取当前角色拥有的权限ID集合
        $.ajax({
            url: "/platform/role/getMenuByRoleID",
            type: "Post",
            data: { "roleid": roleid },
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                }
                else {
                    if (data.data.length > 0) {
                        for (var i = 0; i < data.data.length; i++) {
                            if (xxpermids == "") {
                                xxpermids += "," + data.data[i].menusid + ",";
                            }
                            else {
                                xxpermids += data.data[i].menusid + ",";
                            }
                        }
                        permids = xxpermids;
                    }
                }
            }
        });
        //chkDisabled:true
        $.ajax({
            url: sUrl,
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.code < 0) {
                    Dalert(data.desc);
                } else {                 
                    //ztree加载
                    var len = data.data.length;
                    if (len > 0) {
                        for (var i = 0; i < len; i++) {
                            if (permids.indexOf("," + data.data[i].id + ",") >= 0) {
                                data.data[i].checked=true;
                            }
                            else {
                                data.data[i].chkDisabled = true;
                            }                         
                        }
                    }
                  
                    $.fn.zTree.init($("#treeDemo"), setting, data.data);
                }
            },
            error: function () {
                Dalert("数据加载失败");
            }
        });
    }
}

function setCheck() {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    //{ "Y" : "s", "N" : "s" };父子关联关系：关联子
    //{ "Y": "ps", "N": "ps" };父子关联关系：关联父，关联子
    zTree.setting.check.chkboxType = { "Y": "ps", "N": "ps" };

}
//角色编辑权限
function save() {
    var roleid = $("#roleid").val();
    var rUrl = "",savepermids="";
        if (rptype == 0) {
            rUrl = "/platform/role/addRoleMenu";
        }
        else {
            rUrl = "/platform/role/addShopRoleMenu";
        }
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    var nodes = treeObj.getCheckedNodes(true);
    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].checked) {
            if (savepermids == "") {
                savepermids = nodes[i].id;
            }
            else {
                savepermids += "," + nodes[i].id;
            }
        }
    }
    var delUrl = "";
    if (savepermids == "") {
        if (rptype == 0) {
            delUrl = "/platform/role/deleteRoleMenu";
        }
        else {
            delUrl = "/platform/role/deleteShopRoleMenu";
        }
        if (confirm('确定清空该角色下的权限?')) {
            $.ajax({
                url: delUrl,
                type: "Post",
                data: { roleid: roleid },
                dataType: "json",
                success: function (data) {
                    if (data.code > 0) {
                        Dalert("操作成功");
                    }
                    else {
                        Dalert(data.desc);
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
            data: { 'roleid': roleid, 'menuids': savepermids },
            dataType: "json",
            success: function (data) {
                if (data.code > 0) {
                    Dalert("操作成功");
                }
                else {
                    Dalert(data.desc);
                }
            },
            error: function () {

            }
        });
    }
}
