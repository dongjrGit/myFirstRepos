//用户关联部门（暂时用不到）
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
    },
    callback: {
        //onClick: onClick
    }
};

var departids = "";
var zTree = {
    init: function (id) {
        var nodeshtml = [];
            $.ajax({
                url: "/Department/P_GetDepartByUser",
                type: "Post",
                data: { UserID: id },
                dataType: "json",
                success: function (data) {
                    if (data.Code < 0) { Dalert(data.Desc);
                     }
                        else{
                        
                        var len = data.Data.length;
                        if (len > 0) {
                            for (var i = 0; i < len; i++) {
                                if (departids == "") {
                                    departids = "," + data.Data[i].ID + ",";
                                }
                                else {
                                    departids += data.Data[i].ID + ",";
                                }
                            }
                        }
                    }
                },
                error: function () {

                }
            });
        $.ajax({
            url: "/Department/P_GetSysDepartment",
            type: "Post",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.Code < 0) {
                    Dalert(data.Desc);
                } else {
             
                    //ztree加载
                    var len = data.Data.length;
                    if (len > 0) {
                        for (var i = 0; i < len; i++) {
                            if (data.Data[i].FatherID == "0") {
                                if (departids.indexOf("," + data.Data[i].ID + ",") >= 0) { nodeshtml.push({ id: data.Data[i].ID, pId: 0, num: data.Data[i].Num, name: data.Data[i].Name, checked: true, open: false }); }
                                else nodeshtml.push({ id: data.Data[i].ID, pId: 0, num: data.Data[i].Num, name: data.Data[i].Name, open: false });
                                for (var j = 0; j < len ; j++) {
                                    if (data.Data[j].FatherID == data.Data[i].ID) {
                                        if (departids.indexOf("," + data.Data[j].ID + ",") >= 0) { nodeshtml.push({ id: data.Data[j].ID, pId: data.Data[j].FatherID, num: data.Data[j].Num, name: data.Data[j].Name, checked: true }); }
                                        else nodeshtml.push({ id: data.Data[j].ID, pId: data.Data[j].FatherID, num: data.Data[j].Num, name: data.Data[j].Name });
                                        for (var k = 0; k < len ; k++) {
                                            if (data.Data[k].FatherID == data.Data[j].ID) {
                                                if (departids.indexOf("," + data.Data[k].ID + ",") >= 0) { nodeshtml.push({ id: data.Data[k].ID, pId: data.Data[k].FatherID, num: data.Data[k].Num, name: data.Data[k].Name, checked: true, open: false }); }
                                                else nodeshtml.push({ id: data.Data[k].ID, pId: data.Data[k].FatherID, num: data.Data[k].Num, name: data.Data[k].Name });
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    $.fn.zTree.init($("#treeDemo"), setting, nodeshtml);
                    setCheck();
                }
            },
            error: function () {
            }
        });
    }
}


function setCheck() {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    //{ "Y" : "s", "N" : "s" };父子关联关系：关联子
    //{ "Y": "ps", "N": "ps" };父子关联关系：关联父，关联子
    zTree.setting.check.chkboxType = { "Y": "s", "N": "s" };

}

$(function () {
    $("input[name=save]").bind("click", save);
    zTree.init($("#userid").val());
})

function save() {
  
    var Userid = $("#userid").val();
    var rUrl = "";
    if (departids == "") {
        rUrl = "/Department/P_AddDepartByUser";
    }
    else {
        rUrl = "/Department/P_UpdateDepartByUser";
    }
    departids = "";
    //var chks = document.getElementsByName("chkperm");
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    var nodes = treeObj.getCheckedNodes(true);

    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].checked) {
            if (departids == "") {
                departids = nodes[i].id;
            }
            else {
                departids += "," + nodes[i].id;
            }
        }
    }
    if (departids == "") {
        rUrl = "/Department/P_DelDepartByUser";

        if (confirm('确定清空该用户关联的部门?')) {
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
            data: { userid: Userid, DepartIds: departids },
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
